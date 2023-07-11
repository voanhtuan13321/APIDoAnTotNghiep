package com.graduation.projectgraduation.servicies;

import com.graduation.projectgraduation.entities.ChiTietDonHang;

import java.util.List;

/**
 * Service chi tiet don hang.
 *
 * @author AnhTuan
 * @date 25/05/2023
 */
public interface ChiTietDonHangService {
  List<ChiTietDonHang> getAllByIdDonHang(Long idDonHang);

  List<ChiTietDonHang> getTop5();

  int getTongTienDonHang(Long id);
}
