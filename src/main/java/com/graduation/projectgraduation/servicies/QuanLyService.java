package com.graduation.projectgraduation.servicies;

import com.graduation.projectgraduation.entities.QuanLy;
import com.graduation.projectgraduation.model.LoggingModel;

import java.util.List;

/**
 * Service quan ly.
 *
 * @author AnhTuan
 * @date 25/05/2023
 */
public interface QuanLyService {
  QuanLy findById(Long id);

  void updateQuanLy(QuanLy quanLy, final boolean isMd5);

  void sendMail(final Long id, final String href);

  void insertQuanLy(QuanLy quanLy);

  QuanLy isLogging(LoggingModel loggingModel);

  List<QuanLy> getAll();

  boolean isTaiKhoan(String taiKhoan);
}
