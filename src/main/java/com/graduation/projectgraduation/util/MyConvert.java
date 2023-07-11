package com.graduation.projectgraduation.util;

import com.graduation.projectgraduation.entities.DanhMuc;
import com.graduation.projectgraduation.entities.Sach;
import com.graduation.projectgraduation.model.SachModel;

/**
 * Chuyen tu model sang entity.
 *
 * @author AnhTuan
 * @date 25/05/2023
 */
public class MyConvert {

  /**
   * phuong thuc nay dung de chuyen sach modol thanh sach entity.
   */
  public static Sach fromSachModelToEntity(SachModel sachModel, DanhMuc danhMuc) {
    Sach sach = new Sach();
    sach.setIdSach(sachModel.getIdSach() != null ? sachModel.getIdSach() : null);
    sach.setTen(sachModel.getTen());
    sach.setTacGia(sachModel.getTacGia());
    sach.setNhaXuatBan(sachModel.getNhaXuatBan());
    sach.setGiaSach(sachModel.getGiaSach());
    sach.setMoTa(sachModel.getMoTa());
    sach.setHinhAnh(sachModel.getHinhAnh());
    sach.setNgayThem(sachModel.getNgayThem());
    sach.setNgayXuatBan(sachModel.getNgayXuatBan());
    sach.setNgayCapNhat(sachModel.getNgayCapNhat());
    sach.setSoLuong(sachModel.getSoLuong());
    sach.setDanhMuc(danhMuc);
    return sach;
  }
}
