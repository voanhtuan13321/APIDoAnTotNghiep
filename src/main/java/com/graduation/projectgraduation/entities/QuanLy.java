package com.graduation.projectgraduation.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "QUAN_LY")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class QuanLy {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_quan_ly")
  private Long idQuanLy;
  @Column(name = "ten", columnDefinition = "varchar(50) NOT NULL")
  private String ten;
  @Column(name = "email", columnDefinition = "varchar(50)")
  private String email;
  @Column(name = "so_dien_thoai", columnDefinition = "varchar(10) NOT NULL")
  private String soDienThoai;
  @Column(name = "tai_khoan", columnDefinition = "varchar(50) NOT NULL UNIQUE")
  private String taiKhoan;
  @Column(name = "mat_khau", columnDefinition = "varchar(50)")
  private String matKhau;

  @Override
  public String toString() {
    return "QuanLy{"
        + "idQuanLy=" + idQuanLy
        + ", ten='" + ten + '\''
        + ", email='" + email + '\''
        + ", soDienThoai='" + soDienThoai + '\''
        + ", taiKhoan='" + taiKhoan + '\''
        + ", matKhau='" + matKhau + '\''
        + '}';
  }
}

