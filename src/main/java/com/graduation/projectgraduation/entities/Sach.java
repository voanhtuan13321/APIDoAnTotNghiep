package com.graduation.projectgraduation.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "SACH")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Sach {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_sach")
  private Long idSach;
  @Lob
  @Column(name = "ten", columnDefinition = "nvarchar(255)")
  private String ten;
  @Column(name = "tac_gia", columnDefinition = "varchar(50) NOT NULL")
  private String tacGia;
  @Column(name = "nha_xuat_ban", columnDefinition = "varchar(50) NOT NULL")
  private String nhaXuatBan;
  @Column(name = "gia_sach")
  private Integer giaSach;
  @Lob
  @Column(name = "mo_ta", columnDefinition = "longtext")
  private String moTa;
  private String hinhAnh;
  @Temporal(TemporalType.DATE)
  @Column(name = "ngay_them")
  private LocalDate ngayThem;
  @Temporal(TemporalType.DATE)
  @Column(name = "ngay_xuat_ban")
  private LocalDate ngayXuatBan;
  @Temporal(TemporalType.DATE)
  @Column(name = "ngay_cap_nhat")
  private LocalDate ngayCapNhat;
  @Column(name = "so_luong")
  private Integer soLuong;
  @ManyToOne()
  @JoinColumn(name = "id_danh_muc")
  private DanhMuc danhMuc;

  @Override
  public String toString() {
    return "Sach{"
        + "idSach=" + idSach
        + ", ten='" + ten + '\''
        + ", tacGia='" + tacGia + '\''
        + ", nhaXuatBan='" + nhaXuatBan + '\''
        + ", giaSach=" + giaSach
        + ", moTa='" + moTa + '\''
        + ", hinhAnh='" + hinhAnh + '\''
        + ", ngayThem=" + ngayThem
        + ", ngayXuatBan=" + ngayXuatBan
        + ", ngayCapNhat=" + ngayCapNhat
        + ", soLuong=" + soLuong
        + ", danhMuc=" + danhMuc
        + '}';
  }
}

