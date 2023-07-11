package com.graduation.projectgraduation.servicies;

import com.graduation.projectgraduation.entities.GioHang;
import com.graduation.projectgraduation.model.GioHangModel;
import com.graduation.projectgraduation.model.ListIdGioHang;

import java.util.List;

/**
 * Service gio hang.
 *
 * @author AnhTuan
 * @date 25/05/2023
 */
public interface GioHangService {
  List<GioHang> getAllGioHangByIdKhachHang(Long id);

  List<GioHang> getAllDonHang();

  GioHang insertSachVaoGioHang(GioHangModel gioHangModel);

  void removeSoLuong(GioHangModel gioHangModel);

  void deleteGioHang(ListIdGioHang listIdGioHang);

  void deleteGioHang(Long id);

  void updateTrangThai(Long id, String trangThai);

  GioHang getAllGioHangById(Long id);
}
