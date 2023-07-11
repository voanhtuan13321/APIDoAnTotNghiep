package com.graduation.projectgraduation.repositories;

import com.graduation.projectgraduation.entities.DanhGiaSanPham;
import com.graduation.projectgraduation.entities.Sach;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DanhGiaSanPhamRepository extends JpaRepository<DanhGiaSanPham, Long> {
  void deleteBySach(Sach sach);
}