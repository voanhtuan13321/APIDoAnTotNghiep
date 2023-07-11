package com.graduation.projectgraduation.servicies.impl;

import com.graduation.projectgraduation.entities.ChiTietDonHang;
import com.graduation.projectgraduation.entities.DonHang;
import com.graduation.projectgraduation.entities.GioHang;
import com.graduation.projectgraduation.entities.KhachHang;
import com.graduation.projectgraduation.entities.Sach;
import com.graduation.projectgraduation.model.ListSanPhamModel;
import com.graduation.projectgraduation.model.SoLuongTheoThangModel;
import com.graduation.projectgraduation.repositories.ChiTietDonHangRepository;
import com.graduation.projectgraduation.repositories.DonHangRepository;
import com.graduation.projectgraduation.repositories.GioHangRepository;
import com.graduation.projectgraduation.repositories.KhachHangRepository;
import com.graduation.projectgraduation.repositories.SachRepository;
import com.graduation.projectgraduation.servicies.DonHangService;
import com.graduation.projectgraduation.util.MaHoa;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.IntStream;

/**
 * Service DonHang.
 *
 * @author AnhTuan
 * @version 1.0
 * @since 09/05/2023
 */
@Service
@RequiredArgsConstructor
public class DonHangServiceImpl implements DonHangService {

  private final DonHangRepository donHangRepository;
  private final KhachHangRepository khachHangRepository;
  private final GioHangRepository gioHangRepository;
  private final SachRepository sachRepository;
  private final ChiTietDonHangRepository chiTietDonHangRepository;

  /**
   * Lay all don hang theo id khach hang.
   *
   * @param idKhachHang id khach hang
   * @return list of don hang
   * @author AnhTuan
   * @since 09/05/2023
   */
  @Override
  public List<DonHang> findAllByIdKhachHang(Long idKhachHang) {
    return khachHangRepository.findById(idKhachHang)
        .map(khachHang -> donHangRepository.findAllByKhachHang(khachHang)
            .stream()
            .filter(donHang -> donHang.getTrangThai().equals("cho_phe_duyet"))
            .toList()
        )
        .orElseGet(ArrayList::new);

  }

  /**
   * Lay lich su mua hang theo id khach hang.
   *
   * @param idKhachHang id khach hang
   * @return List of don hang
   * @author AnhTuan
   * @since 09/05/2023
   */
  @Override
  public List<DonHang> findAllLichSuByIdKhachHang(Long idKhachHang) {
    return khachHangRepository.findById(idKhachHang)
        .map(khachHang -> donHangRepository.findAllByKhachHang(khachHang)
            .stream()
            .filter(donHang -> donHang.getTrangThai().equals("da_xac_nhan"))
            .toList()
        )
        .orElseGet(ArrayList::new);
  }

  /**
   * Thong ke 12 thang trong nam hien tai.
   *
   * @return List so luong theo tung thang
   * @author AnhTuan
   * @since 09/05/023
   */
  @Override
  public List<SoLuongTheoThangModel> thongKeTheoThang() {
    // khoi tao list 12 thang
    List<SoLuongTheoThangModel> thongKeTheoThangs = IntStream.rangeClosed(1, 12)
        .mapToObj(i -> new SoLuongTheoThangModel(i, 0))
        .toList();

    int currentYear = LocalDate.now().getYear();

    donHangRepository.findAll()
        .stream()
        .filter(donHang -> Objects.equals(donHang.getTrangThai(), "da_xac_nhan"))
        .filter(donHang -> donHang.getNgayMua().getYear() == currentYear)
        .forEach(donHang -> {
          int thang = donHang.getNgayMua().getMonthValue();
          SoLuongTheoThangModel thangModel = thongKeTheoThangs.get(thang - 1);
          thangModel.setSoLuong((int) (thangModel.getSoLuong() + getSoLuongDonHang(donHang)));
        });

    return thongKeTheoThangs;
  }

  /**
   * Tinh tong so luong don hang.
   *
   * @param donHang thong tin don hang
   * @return tong so luong don hang
   * @author AnhTuan
   * @since 09/05/2023
   */
  private long getSoLuongDonHang(DonHang donHang) {
    return chiTietDonHangRepository.findAllByDonHang(donHang)
        .stream()
        .mapToInt(ChiTietDonHang::getSoLuong)
        .sum();
  }

  /**
   * Thong ke don hang theo thang.
   *
   * @param thang thang can thong ke
   * @return List of don hang
   * @author AnhTuan
   * @since 09/05/2023
   */
  @Override
  public List<DonHang> thongKeTheoThang(int thang) {
    return donHangRepository.findAll()
        .stream()
        .filter(donHang -> donHang.getNgayMua().getMonthValue() == thang)
        .filter(donHang -> donHang.getTrangThai().equals("da_xac_nhan"))
        .toList();
  }

  /**
   * Them don hang.
   *
   * @param listSanPhamModel danh sach san pham
   * @author AnhTuan
   * @since 09/05/2023
   */
  @Override
  public void addDonHang(ListSanPhamModel listSanPhamModel) {
    GioHang gioHang = gioHangRepository
        .findById(listSanPhamModel.getListSanPham().get(0).getId())
        .orElseThrow();
    KhachHang khachHang = khachHangRepository
        .findById(gioHang.getKhachHang().getIdKhachHang())
        .orElseThrow();

    String newMaDonHang = MaHoa.generateMaHoaDon(gioHang);

    // them don hang
    DonHang donHang = new DonHang();
    donHang.setTrangThai("cho_phe_duyet");
    donHang.setMaDonHang(newMaDonHang);
    donHang.setNgayMua(LocalDate.now());
    donHang.setKhachHang(khachHang);
    donHang.setPhuongThucThanhToan(
        listSanPhamModel.getListSanPham().get(0).getPhuongThucThanhToan()
    );
    donHangRepository.save(donHang);

    // them san pham vao don hang do
    listSanPhamModel.getListSanPham()
        .forEach(gioHangModel -> {
          GioHang gioHang1 = gioHangRepository.findById(gioHangModel.getId()).orElseThrow();
          Sach sach = gioHang1.getSach();
          Integer soLuong = gioHang1.getSoLuong();

          // cap nhat lai so luong
          Sach sachOld = sachRepository.findById(sach.getIdSach()).orElseThrow();
          sachOld.setSoLuong(sachOld.getSoLuong() - soLuong);
          sachRepository.save(sachOld);

          ChiTietDonHang chiTietDonHang = new ChiTietDonHang();
          chiTietDonHang.setSach(sach);
          chiTietDonHang.setSoLuong(soLuong);
          chiTietDonHang.setDonHang(donHang);

          chiTietDonHangRepository.save(chiTietDonHang);
        });
  }

  /**
   * Cap nhat trang thai don hang.
   *
   * @param id id don hang
   * @author AnhTuan
   * @since 09/05/2023
   */
  @Override
  public void capNhatTrangThaiDonHang(Long id) {
    DonHang donHang = donHangRepository.findById(id).orElseThrow();
    donHang.setTrangThai("da_xac_nhan");
    donHangRepository.save(donHang);
  }

  /**
   * Xoa don hang.
   *
   * @param id id cua don hang can xoa
   * @author AnhTuan
   * @since 09/05/2023
   */
  @Override
  public void deleteDonHang(Long id) {
    DonHang donHang = donHangRepository.findById(id).orElseThrow();
    chiTietDonHangRepository.deleteByDonHang(donHang);
    donHangRepository.deleteById(id);
  }

  /**
   * Lay all don hang.
   *
   * @return list of don hang
   * @author AnhTuan
   * @since 09/05/2023
   */
  @Override
  public List<DonHang> findAll() {
    return donHangRepository.findAll()
        .stream()
        .filter(donHang -> donHang.getTrangThai().equals("cho_phe_duyet"))
        .sorted(Comparator.comparing(DonHang::getNgayMua).reversed())
        .toList();
  }

}
