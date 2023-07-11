package com.graduation.projectgraduation.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author AnhTuan
 * @date 25/05/2023
 */
@Entity
@Table(name = "CHI_TIET_DON_HANG")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ChiTietDonHang {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_chi_tiet_don_hang")
  private Long id;
  @ManyToOne
  @JoinColumn(name = "id_sach")
  private Sach sach;
  @Column(name = "so_luong")
  private Integer soLuong;
  @ManyToOne
  @JoinColumn(name = "id_don_hang")
  private DonHang donHang;

  @Override
  public String toString() {
    return "ChiTietDonHang{"
        + "id=" + id
        + ", sach=" + sach
        + ", soLuong=" + soLuong
        + ", donHang=" + donHang
        + '}';
  }
}
