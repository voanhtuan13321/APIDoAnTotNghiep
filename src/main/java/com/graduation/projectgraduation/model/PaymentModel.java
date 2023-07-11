package com.graduation.projectgraduation.model;

import lombok.Data;

@Data
public class PaymentModel {
  private Integer amount;
  private String vnp_OrderInfo;
  private String vnp_BankCode;
  private String vnp_TxnRef;
  private String txt_billing_mobile;
  private String txt_billing_email;
  private String txt_billing_fullname;
  private String vnp_ReturnUrl;
  private String vnp_ResponseCode;  // check response code: vnp_ResponseCode == 0 => success
}
