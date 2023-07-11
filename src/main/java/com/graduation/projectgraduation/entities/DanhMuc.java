package com.graduation.projectgraduation.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "DANH_MUC")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DanhMuc {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id_danh_muc")
  private Long idDanhMuc;
  @Column(name = "ten", columnDefinition = "varchar(50) NOT NULL")
  private String ten;
  @Column(name = "mo_ta")
  private String moTa;

  @Override
  public String toString() {
    return "DanhMuc{"
        + "idDanhMuc=" + idDanhMuc
        + ", ten='" + ten + '\''
        + ", moTa='" + moTa + '\''
        + '}';
  }
}

