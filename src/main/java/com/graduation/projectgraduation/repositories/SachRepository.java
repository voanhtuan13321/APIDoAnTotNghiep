package com.graduation.projectgraduation.repositories;

import com.graduation.projectgraduation.entities.DanhMuc;
import com.graduation.projectgraduation.entities.Sach;
import com.graduation.projectgraduation.util.MyProperties;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface SachRepository extends JpaRepository<Sach, Long> {
  @Query(value = MyProperties.SEARCHING_SACH, nativeQuery = true)
  List<Sach> searchBooks(String search);

  List<Sach> findByDanhMuc(DanhMuc danhMuc);
}