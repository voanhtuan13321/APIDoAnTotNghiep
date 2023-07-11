package com.graduation.projectgraduation.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Table(name = "GOP_Y_SAN_PHAM")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DanhGiaSanPham {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @ManyToOne()
  @JoinColumn(name = "id_khach_hang")
  private KhachHang khachHang;
  @ManyToOne()
  @JoinColumn(name = "id_sach")
  private Sach sach;
  private String noiDung;
  @Temporal(TemporalType.TIMESTAMP)
  @Column(name = "ngay_gio_binh_luan")
  private LocalDateTime dateTime;

  @Override
  public String toString() {
    return "DanhGiaSanPham{"
        + "id=" + id
        + ", khachHang=" + khachHang
        + ", sach=" + sach
        + ", noiDung='" + noiDung
        + "'}";
  }
}

