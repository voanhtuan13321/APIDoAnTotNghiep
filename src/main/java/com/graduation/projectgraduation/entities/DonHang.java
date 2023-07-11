package com.graduation.projectgraduation.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

/**
 * @author AnhTuan
 * @date 09/05/2023
 */
@Entity
@Table(name = "DON_HANG")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DonHang {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_don_hang")
  private Long id;
  @ManyToOne
  @JoinColumn(name = "id_khach_hang")
  private KhachHang khachHang;
  @Column(name = "ma_don_hang")
  private String maDonHang;
  @Column(name = "ngay_mua")
  @Temporal(TemporalType.DATE)
  private LocalDate ngayMua;
  @Column(name = "trang_thai")
  private String trangThai; // cho_phe_duyet/da_xac_nhan
  @Column(name = "phuong_thuc_thanh_toan")
  private String phuongThucThanhToan;
}
