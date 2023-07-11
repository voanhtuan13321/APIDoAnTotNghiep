package com.graduation.projectgraduation.repositories;

import com.graduation.projectgraduation.entities.GioHang;
import com.graduation.projectgraduation.entities.KhachHang;
import com.graduation.projectgraduation.util.MyProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GioHangRepository extends JpaRepository<GioHang, Long> {
  @Query(value = MyProperties.SELECT_GIO_HANG_BY_ID_KHACH_HANG_AND_ID_SACH, nativeQuery = true)
  Optional<GioHang> findByIdKhachHangAndIdSach(Long idKhachHang, Long idSach);

  List<GioHang> findByKhachHang(KhachHang khachHang);

  List<GioHang> findAllByKhachHang(KhachHang khachHang);
}