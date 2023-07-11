package com.graduation.projectgraduation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DanhGiaSanPhamModel {
  private Long id;
  private Long idKhachHang;
  private Long idSach;
  private String noiDung;
  private LocalDateTime dateTime;

  @Override
  public String toString() {
    return "DanhGiaSanPhamModel{"
        + "id=" + id
        + ", idKhachHang=" + idKhachHang
        + ", idSach=" + idSach
        + ", noiDung='" + noiDung + '\''
        + '}';
  }
}
