package com.graduation.projectgraduation.servicies;

import com.graduation.projectgraduation.entities.DanhMuc;

import java.util.List;

/**
 * Service danh muc.
 *
 * @author AnhTuan
 * @date 25/05/2023
 */
public interface DanhMucService {
  List<DanhMuc> getAllDanhMuc();

  DanhMuc getDanhMucById(Long id);

  boolean insertDanhMuc(DanhMuc danhMuc);

  String deleteDanhMuc(Long id);
}
