package com.graduation.projectgraduation.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ResponseObject {
  private String status;
  private String message;
  private Object data;

  @Override
  public String toString() {
    return "ResponObject{"
        + "status='" + status + '\''
        + ", message='" + message + '\''
        + ", data=" + data
        + '}';
  }
}
