package com.graduation.projectgraduation.repositories;

import com.graduation.projectgraduation.entities.ChiTietDonHang;
import com.graduation.projectgraduation.entities.DonHang;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface ChiTietDonHangRepository extends JpaRepository<ChiTietDonHang, Long> {
  List<ChiTietDonHang> findAllByDonHang(DonHang donHang);

  @Transactional
  void deleteByDonHang(DonHang donHang);
}