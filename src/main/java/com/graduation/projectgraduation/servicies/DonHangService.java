package com.graduation.projectgraduation.servicies;

import com.graduation.projectgraduation.entities.DonHang;
import com.graduation.projectgraduation.model.ListSanPhamModel;
import com.graduation.projectgraduation.model.SoLuongTheoThangModel;

import java.util.List;

/**
 * Service don hang.
 *
 * @author AnhTuan
 * @date 09/05/2023
 */
public interface DonHangService {
  List<DonHang> findAllByIdKhachHang(Long idKhachHang);

  void addDonHang(ListSanPhamModel listSanPhamModel);

  void capNhatTrangThaiDonHang(Long id);

  void deleteDonHang(Long id);

  List<DonHang> findAll();

  List<DonHang> findAllLichSuByIdKhachHang(Long id);

  List<SoLuongTheoThangModel> thongKeTheoThang();

  List<DonHang> thongKeTheoThang(int thang);
}
