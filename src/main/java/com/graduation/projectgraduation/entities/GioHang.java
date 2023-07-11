package com.graduation.projectgraduation.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "GIO_HANG")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class GioHang {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Long id;
  @ManyToOne()
  @JoinColumn(name = "id_khach_hang")
  private KhachHang khachHang;
  @ManyToOne()
  @JoinColumn(name = "id_sach")
  private Sach sach;
  @Column(name = "so_luong")
  private Integer soLuong;
  @Column(name = "trang_thai")
  private String trangThai;

  /**
   * constructor.
   */
  public GioHang(KhachHang khachHang, Sach sach, Integer soLuong, String trangThai) {
    this.khachHang = khachHang;
    this.sach = sach;
    this.soLuong = soLuong;
    this.trangThai = trangThai;
  }

  @Override
  public String toString() {
    return "GioHang{"
        + "id=" + id
        + ", khachHang=" + khachHang
        + ", sach=" + sach
        + ", soLuong=" + soLuong
        + '}';
  }
}

