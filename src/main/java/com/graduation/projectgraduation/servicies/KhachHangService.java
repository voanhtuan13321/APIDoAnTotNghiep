package com.graduation.projectgraduation.servicies;

import com.graduation.projectgraduation.entities.KhachHang;

import java.util.List;

/**
 * Service khach hang.
 *
 * @author AnhTuan
 * @date 25/05/2023
 */
public interface KhachHangService {
  List<KhachHang> getAllKhachHang();

  KhachHang getKhachHangById(Long id);

  List<KhachHang> searchKhachHang(String search);

  KhachHang insertKhachHang(KhachHang khachHang);

  boolean updateKhachHang(KhachHang khachHang);

  void deleteKhachHang(Long id);

  void sendMail(Long id, String href);

  KhachHang checkLogging(String taiKhoan, String matKhau);

  boolean checkTaiKhoan(String taiKhoan);

  KhachHang findByTaiKhoan(String taiKhoan);

  boolean updateMatKhau(KhachHang khachHang);
}
