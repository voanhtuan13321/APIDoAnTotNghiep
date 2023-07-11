package com.graduation.projectgraduation.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "THONG_TIN_CUA_HANG")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ThongTinCuaHang {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  @Column(name = "ten_cua_hang", columnDefinition = "varchar(50) NOT NULL")
  private String tenCuaHang;
  @Column(name = "mo_ta")
  private String moTa;
  @Column(name = "so_dien_thoai", columnDefinition = "varchar(10) NOT NULL")
  private String soDienThoai;
  @Column(name = "dia_chi", columnDefinition = "varchar(100) NOT NULL")
  private String diaChi;
  @Column(name = "website")
  private String website;
  @Column(name = "email", columnDefinition = "varchar(100) NOT NULL")
  private String email;

  @Override
  public String toString() {
    return "ThongTinCuaHang{"
        + "id=" + id
        + ", tenCuaHang='" + tenCuaHang + '\''
        + ", moTa='" + moTa + '\''
        + ", soDienThoai='" + soDienThoai + '\''
        + ", diaChi='" + diaChi + '\''
        + ", website='" + website + '\''
        + ", email='" + email + '\''
        + '}';
  }
}
