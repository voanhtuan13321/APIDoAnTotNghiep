package com.graduation.projectgraduation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class MailModel {
  private String taiKhoan;
  private String href;

  @Override
  public String toString() {
    return "MailModel{"
        + "taiKhoan='" + taiKhoan + '\''
        + ", href='" + href + '\''
        + '}';
  }
}
