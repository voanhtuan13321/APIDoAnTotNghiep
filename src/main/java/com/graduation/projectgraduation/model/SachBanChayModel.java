package com.graduation.projectgraduation.model;

import com.graduation.projectgraduation.entities.Sach;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * class dung de luu thong tin cua sach da ban.
 *
 * @author AnhTuan
 * @date 02/05/2023
 */
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SachBanChayModel {
  private Sach sach;
  private int soLuongBan;

  @Override
  public String toString() {
    return "SachBanChayModel{"
        + "sach=" + sach
        + ", soLuongBan=" + soLuongBan
        + '}';
  }
}
