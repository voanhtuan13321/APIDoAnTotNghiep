package com.graduation.projectgraduation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class LoggingModel {
  private String taiKhoan;
  private String matKhau;

  @Override
  public String toString() {
    return "LoggingModel{"
        + "taiKhoan='" + taiKhoan + '\''
        + ", matKhau='" + matKhau + '\''
        + '}';
  }
}
