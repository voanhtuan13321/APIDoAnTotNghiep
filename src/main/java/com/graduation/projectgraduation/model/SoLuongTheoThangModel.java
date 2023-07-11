package com.graduation.projectgraduation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * class dung de luu tru thong tin so luong sach da ban theo tung thang.
 *
 * @author AnhTuan
 * @date 02/05/2023
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SoLuongTheoThangModel {
  private int thang;
  private int soLuong;

  @Override
  public String toString() {
    return "SoLuongTheoThangModel{"
        + "thang=" + thang
        + ", soLuong=" + soLuong
        + '}';
  }
}
