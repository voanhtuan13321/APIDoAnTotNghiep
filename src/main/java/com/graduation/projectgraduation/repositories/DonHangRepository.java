package com.graduation.projectgraduation.repositories;

import com.graduation.projectgraduation.entities.DonHang;
import com.graduation.projectgraduation.entities.KhachHang;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author AnhTuan
 * @date 09/05/2023
 */
public interface DonHangRepository extends JpaRepository<DonHang, Long> {
  List<DonHang> findAllByKhachHang(KhachHang khachHang);
}
