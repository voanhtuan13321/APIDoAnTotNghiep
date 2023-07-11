package com.graduation.projectgraduation.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @author AnhTuan
 * @date 09/05/2023
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DonHangModel {
  private Long id;
  private Long idKhachHang;
  private Long idSach;
  private String maDonHang;

  private Integer soLuong;

  private LocalDate ngayMua;

  private String trangThai;
}
