package com.graduation.projectgraduation.repositories;

import com.graduation.projectgraduation.entities.QuanLy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface QuanLyRepository extends JpaRepository<QuanLy, Long> {
  Optional<QuanLy> findByTaiKhoanAndMatKhau(String taiKhoan, String matKhau);

  Optional<QuanLy> findByTaiKhoan(String taiKhoan);
}
