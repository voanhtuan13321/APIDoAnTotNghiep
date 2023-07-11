package com.graduation.projectgraduation.repositories;

import com.graduation.projectgraduation.entities.KhachHang;
import com.graduation.projectgraduation.util.MyProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface KhachHangRepository extends JpaRepository<KhachHang, Long> {

  @Query(value = MyProperties.SEARCHING_KHACH_HANG, nativeQuery = true)
  List<KhachHang> search(String s);

  KhachHang findByTaiKhoanAndMatKhau(String taiKhoan, String matKhau);

  Optional<KhachHang> findByTaiKhoan(String taiKhoan);
}