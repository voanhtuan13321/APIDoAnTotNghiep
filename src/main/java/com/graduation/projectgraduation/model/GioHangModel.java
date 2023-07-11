package com.graduation.projectgraduation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GioHangModel {
  private Long id;
  private Long idKhachHang;
  private Long idSach;
  private Integer soLuong;
  private String trangThai;
  private String phuongThucThanhToan;

  @Override
  public String toString() {
    return "GioHangModel{"
        + "id=" + id
        + ", idKhachHang=" + idKhachHang
        + ", idSach=" + idSach
        + ", soLuong=" + soLuong
        + '}';
  }
}
