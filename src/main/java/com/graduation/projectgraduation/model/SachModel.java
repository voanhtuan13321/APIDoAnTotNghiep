package com.graduation.projectgraduation.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
public class SachModel {
  private Long idSach;
  private String ten;
  private String tacGia;
  private String nhaXuatBan;
  private Integer giaSach;
  private String moTa;
  private String hinhAnh;
  private LocalDate ngayThem;
  private LocalDate ngayXuatBan;
  private LocalDate ngayCapNhat;
  private Integer soLuong;
  private Long idDanhMuc;

  /**
   * Constructor.
   */
  public SachModel(String ten, String tacGia, String nhaXuatBan, int giaSach, String moTa,
                   String hinhAnh, LocalDate ngayThem, LocalDate ngayXuatBan, LocalDate ngayCapNhat,
                   int soLuong, int khuyenMai, Long idDanhMuc) {
    this.ten = ten;
    this.tacGia = tacGia;
    this.nhaXuatBan = nhaXuatBan;
    this.giaSach = giaSach;
    this.moTa = moTa;
    this.hinhAnh = hinhAnh;
    this.ngayThem = ngayThem;
    this.ngayXuatBan = ngayXuatBan;
    this.ngayCapNhat = ngayCapNhat;
    this.soLuong = soLuong;
    this.idDanhMuc = idDanhMuc;
  }

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
        + ", danhMuc=" + idDanhMuc
        + '}';
  }
}

