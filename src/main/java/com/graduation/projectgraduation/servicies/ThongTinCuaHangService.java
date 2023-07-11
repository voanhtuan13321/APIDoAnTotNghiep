package com.graduation.projectgraduation.servicies;

import com.graduation.projectgraduation.entities.ThongTinCuaHang;

import java.util.List;

/**
 * Service thong tin cua hang.
 *
 * @author AnhTuan
 * @date 25/05/2023
 */
public interface ThongTinCuaHangService {
  List<ThongTinCuaHang> getAll();

  boolean insertOrUpdateThongTinCuaHang(ThongTinCuaHang thongTinCuaHang);
}
