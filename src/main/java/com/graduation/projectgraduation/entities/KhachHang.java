package com.graduation.projectgraduation.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "KHACH_HANG")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class KhachHang {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_khach_hang")
  private Long idKhachHang;
  @Column(name = "ten", columnDefinition = "varchar(50) NOT NULL")
  private String ten;
  @Column(name = "email", columnDefinition = "varchar(100) NOT NULL")
  private String email;
  @Column(name = "so_dien_thoai", columnDefinition = "varchar(10) NOT NULL")
  private String soDienThoai;
  @Column(name = "dia_chi", columnDefinition = "varchar(50) NOT NULL")
  private String diaChi;
  @Column(name = "tai_khoan", columnDefinition = "varchar(50) NOT NULL UNIQUE")
  private String taiKhoan;
  @Column(name = "mat_khau", columnDefinition = "varchar(50) NOT NULL")
  private String matKhau;
  @Temporal(TemporalType.DATE)
  @Column(name = "ngay_tao")
  private LocalDate ngayTao;

  @Override
  public String toString() {
    return "KhachHang{"
        + "idKhachHang=" + idKhachHang
        + ", ten='" + ten + '\''
        + ", email='" + email + '\''
        + ", soDienThoai='" + soDienThoai + '\''
        + ", diaChi='" + diaChi + '\''
        + ", taiKhoan='" + taiKhoan + '\''
        + ", matKhau='" + matKhau + '\''
        + '}';
  }
}
