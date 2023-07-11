package com.graduation.projectgraduation.servicies.impl;

import com.graduation.projectgraduation.entities.DanhGiaSanPham;
import com.graduation.projectgraduation.entities.KhachHang;
import com.graduation.projectgraduation.entities.Sach;
import com.graduation.projectgraduation.model.DanhGiaSanPhamModel;
import com.graduation.projectgraduation.repositories.DanhGiaSanPhamRepository;
import com.graduation.projectgraduation.repositories.KhachHangRepository;
import com.graduation.projectgraduation.repositories.SachRepository;
import com.graduation.projectgraduation.servicies.DanhGiaSanPhamService;
import com.graduation.projectgraduation.util.MaHoa;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

/**
 * Service DanhGiaSanPham.
 *
 * @author AnhTuan
 * @version 1.0
 * @since 25/05/2023
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class DanhGiaSanPhamServiceImpl implements DanhGiaSanPhamService {

  private final DanhGiaSanPhamRepository danhGiaSanPhamRepository;
  private final KhachHangRepository khachHangRepository;
  private final SachRepository sachRepository;

  /**
   * Lay all sach co binh luan.
   *
   * @return List of sach
   * @author AnhTuan
   * @since 25/05/2023
   */
  @Override
  public List<Sach> getAllSachCoBinhLuan() {
    Set<Sach> sachList = new HashSet<>();

    danhGiaSanPhamRepository.findAll().forEach(item -> sachList.add(item.getSach()));

    sachList.forEach(item -> {
      String anh = item.getHinhAnh();
      try {
        anh = MaHoa.convertFileToBase64(anh);
        item.setHinhAnh(anh);
      } catch (IOException e) {
        log.error("Ma hoa anh loi", e);
      }
    });
    return new ArrayList<>(sachList);
  }

  /**
   * Lay all danh gia san pham theo id sach.
   *
   * @param id id cua sach
   * @return list of danh gia san pham
   * @author AnhTuan
   * @since 25/05/2023
   */
  @Override
  public List<DanhGiaSanPham> getAllDanhSachSanPhamByIdSach(Long id) {
    return danhGiaSanPhamRepository.findAll()
        .stream()
        .filter(item -> Objects.equals(id, item.getSach().getIdSach()))
        .peek(item -> item.setSach(null))
        .toList();
  }

  /**
   * Them binh luạn.
   *
   * @param danhGiaSanPhamModel thong tin binh luan
   * @author AnhTuan
   * @since 25/05/2023
   */
  @Override
  public synchronized void insertBinhLuan(DanhGiaSanPhamModel danhGiaSanPhamModel) {
    DanhGiaSanPham danhGiaSanPham = null;
    Long id = danhGiaSanPhamModel.getId();
    if (id == null) {
      danhGiaSanPham = new DanhGiaSanPham();
    } else {
      Optional<DanhGiaSanPham> danhGiaSanPhamOptional = danhGiaSanPhamRepository.findById(id);
      if (danhGiaSanPhamOptional.isEmpty()) {
        return;
      }
      danhGiaSanPham = danhGiaSanPhamOptional.get();
    }

    // get khach hang entity
    Optional<KhachHang> khachHangOptional = khachHangRepository
        .findById(danhGiaSanPhamModel.getIdKhachHang());
    if (khachHangOptional.isEmpty()) {
      return;
    }
    KhachHang khachHang = khachHangOptional.get();

    // get sach entity
    Optional<Sach> sachOptional = sachRepository.findById(danhGiaSanPhamModel.getIdSach());
    if (sachOptional.isEmpty()) {
      return;
    }
    Sach sach = sachOptional.get();

    danhGiaSanPham.setKhachHang(khachHang);
    danhGiaSanPham.setSach(sach);
    danhGiaSanPham.setNoiDung(danhGiaSanPhamModel.getNoiDung());
    danhGiaSanPham.setDateTime(LocalDateTime.now());
    danhGiaSanPhamRepository.save(danhGiaSanPham);
  }

  /**
   * Xoa binh luan.
   *
   * @param id cua binh luan
   * @author AnhTuan
   * @since 25/05/2023
   */
  @Override
  public synchronized void deleteBinhLuan(Long id) {
    danhGiaSanPhamRepository.deleteById(id);
  }
}
