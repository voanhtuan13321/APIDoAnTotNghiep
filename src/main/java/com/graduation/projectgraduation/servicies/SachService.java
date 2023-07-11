package com.graduation.projectgraduation.servicies;

import com.graduation.projectgraduation.entities.Sach;
import com.graduation.projectgraduation.model.SachModel;

import java.io.IOException;
import java.util.List;

/**
 * Service sach.
 *
 * @author AnhTuan
 * @date 25/05/2023
 */
public interface SachService {
  List<Sach> getAllBooks();

  Sach findById(Long id);

  boolean insertOrUpdateSach(SachModel sachModel) throws IOException;

  String deleteSach(Long id);

  List<Sach> searchBooks(String search);

  List<Sach> getAllBooksByDanhMuc(Long id);

  List<Sach> sachMoiNhat();
}
