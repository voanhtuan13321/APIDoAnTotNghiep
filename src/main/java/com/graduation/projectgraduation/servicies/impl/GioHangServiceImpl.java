package com.graduation.projectgraduation.servicies.impl;

import com.graduation.projectgraduation.entities.GioHang;
import com.graduation.projectgraduation.entities.KhachHang;
import com.graduation.projectgraduation.entities.Sach;
import com.graduation.projectgraduation.model.GioHangModel;
import com.graduation.projectgraduation.model.ListIdGioHang;
import com.graduation.projectgraduation.repositories.GioHangRepository;
import com.graduation.projectgraduation.repositories.KhachHangRepository;
import com.graduation.projectgraduation.repositories.SachRepository;
import com.graduation.projectgraduation.servicies.GioHangService;
import com.graduation.projectgraduation.util.MaHoa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Service GioHang.
 *
 * @author AnhTuan
 * @version 1.0
 * @since 09/05/2023
 */
@Service
@RequiredArgsConstructor
public class GioHangServiceImpl implements GioHangService {

  private final GioHangRepository gioHangRepository;
  private final KhachHangRepository khachHangRepository;
  private final SachRepository sachRepository;

  /**
   * Lay all gio hang theo id khach hang.
   *
   * @param id id cua khach hang
   * @return list of gio hang
   * @author AnhTuan
   * @since 09/05/2023
   */
  @Override
  public List<GioHang> getAllGioHangByIdKhachHang(Long id) {
    KhachHang khachHang = khachHangRepository.findById(id).orElseThrow();
    return gioHangRepository.findByKhachHang(khachHang)
        .stream()
        .peek(item -> {
          try {
            String hinhAnh = item.getSach().getHinhAnh();
            item.getSach().setHinhAnh(MaHoa.convertFileToBase64(hinhAnh));
          } catch (IOException e) { }
        })
        .toList();
  }

  /**
   * Lay all don hang.
   *
   * @return list of don hang
   * @author AnhTuan
   * @since 09/05/2023
   */
  @Override
  public List<GioHang> getAllDonHang() {
    return gioHangRepository.findAll()
        .stream()
        .peek(item -> {
          String anh = item.getSach().getHinhAnh();
          try {
            item.getSach().setHinhAnh(MaHoa.convertFileToBase64(anh));
          } catch (IOException e) { }
        })
        .toList();
  }

  /**
   * Them sach vao gio hang.
   *
   * @param gioHangModel thong tin gio hang
   * @return gio hang
   * @author AnhTuan
   * @since 09/05/2023
   */
  @Override
  public synchronized GioHang insertSachVaoGioHang(GioHangModel gioHangModel) {
    // tim sang trong gio hang cua khach hang
    Optional<GioHang> optionalGioHang = gioHangRepository.findByIdKhachHangAndIdSach(
            gioHangModel.getIdKhachHang(),
            gioHangModel.getIdSach()
        )
        .stream()
        .filter(g -> g.getTrangThai().equals("none"))
        .findFirst();

    if (optionalGioHang.isPresent()) {
      // Đã có sản phẩm trong giỏ hàng và chỉ cần cập nhật số lượng
      GioHang gioHang = optionalGioHang.get();
      int newSoLuong = gioHang.getSoLuong() + 1;

      // Kiểm tra số lượng mới vượt quá số lượng hiện có của sản phẩm
      Sach sach = sachRepository.findById(gioHangModel.getIdSach()).orElseThrow();
      if (sach.getSoLuong() < newSoLuong) {
        return gioHang;
      }

      gioHang.setSoLuong(newSoLuong);
      gioHangRepository.save(gioHang);
      return gioHang;
    } else {
      // Chưa có sản phẩm trong giỏ hàng
      try {
        KhachHang khachHang = khachHangRepository
            .findById(gioHangModel.getIdKhachHang())
            .orElseThrow();
        Sach sach = sachRepository
            .findById(gioHangModel.getIdSach())
            .orElseThrow();
        GioHang newGioHang = new GioHang(khachHang, sach, 1, "none");

        gioHangRepository.save(newGioHang);
        return newGioHang;
      } catch (NoSuchElementException e) {
        e.printStackTrace();
        return null;
      }
    }
  }

  /**
   * Xoa so luong trong gio hang.
   *
   * @param gioHangModel thong tin gio hang
   * @author AnhTuan
   * @since 09/05/2023
   */
  @Override
  public synchronized void removeSoLuong(GioHangModel gioHangModel) {
    Optional<GioHang> optionalGioHang = gioHangRepository.findById(gioHangModel.getId());

    if (optionalGioHang.isPresent()) {
      GioHang gioHang = optionalGioHang.get();
      if (gioHang.getSoLuong() == 1) {
        // Trường hợp chỉ còn 1 sản phẩm, xóa luôn khỏi giỏ hàng
        gioHangRepository.deleteById(gioHangModel.getId());
      } else {
        // Giảm đi 1 như bình thường
        int newSoLuong = gioHang.getSoLuong() - 1;
        gioHang.setSoLuong(newSoLuong);
        gioHangRepository.save(gioHang);
      }
    }
  }

  /**
   * Xoa gio hang.
   *
   * @param listIdGioHang danh sach gio hang can xoa
   * @author AnhTuan
   * @since 09/05/2023
   */
  @Override
  public synchronized void deleteGioHang(ListIdGioHang listIdGioHang) {
    gioHangRepository.deleteAllById(listIdGioHang.getListId()
        .stream()
        .map(Long::valueOf)
        .toList()
    );
  }

  /**
   * Xoa gio hang.
   *
   * @param id id gio hang
   * @author AnhTuan
   * @since 09/05/2023
   */
  @Override
  public synchronized void deleteGioHang(Long id) {
    gioHangRepository.deleteById(id);
  }

  /**
   * Cap nhat trang thai gio hang.
   *
   * @param id id cua gio hang
   * @param trangThai trang thai moi
   * @author AnhTuan
   * @since 09/05/2023
   */
  @Override
  public void updateTrangThai(Long id, String trangThai) {
    GioHang gioHang = gioHangRepository.findById(id).orElseThrow();
    gioHang.setTrangThai(trangThai);
    gioHangRepository.save(gioHang);
  }

  /**
   * Lay gio hang theo id.
   *
   * @param id id cua gio hang
   * @return gio
   */
  @Override
  public GioHang getAllGioHangById(Long id) {
    return gioHangRepository.findById(id).orElseThrow();
  }
}
