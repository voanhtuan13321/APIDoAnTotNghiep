package com.graduation.projectgraduation.controllers;

import com.graduation.projectgraduation.config.ConfigVNPay;
import com.graduation.projectgraduation.model.PaymentModel;
import com.graduation.projectgraduation.model.ResponseObject;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TimeZone;

/**
 * Controller PaymentController.
 *
 * @author AnhTuan
 * @version 1.0
 * @since 25/05/2023
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/api/payment")
public class PaymentController {

  /**
   * Create payment.
   *
   * @param req thong tin payment
   * @return ResponseEntity
   * @throws UnsupportedEncodingException unsupported
   * @author AnhTuan
   * @since 25/05/2023
   */
  @PostMapping("/create-payment")
  public ResponseEntity<ResponseObject> createPayment(@RequestBody PaymentModel req)
      throws UnsupportedEncodingException {
    int amount = req.getAmount() * 100;
    Map<String, String> vnp_Params = new HashMap<>();
    vnp_Params.put("vnp_Version", ConfigVNPay.VNP_VERSION);
    vnp_Params.put("vnp_Command", ConfigVNPay.VNP_COMMAND);
    vnp_Params.put("vnp_TmnCode", ConfigVNPay.VNP_TIME_CODE);
    vnp_Params.put("vnp_Amount", String.valueOf(amount));
    vnp_Params.put("vnp_CurrCode", ConfigVNPay.VNP_CURR_CODE);
    vnp_Params.put("vnp_OrderInfo", req.getVnp_OrderInfo());
    vnp_Params.put("vnp_OrderType", ConfigVNPay.VNP_ORDER_TYPE);
    vnp_Params.put("vnp_BankCode", ConfigVNPay.VNP_BANK_CODE);
    vnp_Params.put("vnp_TxnRef", ConfigVNPay.getRandomNumber(8));
    vnp_Params.put("vnp_Locale", "vn");
    vnp_Params.put("vnp_IpAddr", ConfigVNPay.DEFAULT_IP);
//        vnp_Params.put("vnp_ReturnUrl", ConfigVNPay.vnp_ReturnUrl);
    vnp_Params.put("vnp_ReturnUrl", req.getVnp_ReturnUrl());

    Calendar cld = Calendar.getInstance(TimeZone.getTimeZone("Etc/GMT+7"));
    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
    String vnp_CreateDate = formatter.format(cld.getTime());
    vnp_Params.put("vnp_CreateDate", vnp_CreateDate);
    cld.add(Calendar.MINUTE, 15);
    vnp_Params.put("vnp_ExpireDate", formatter.format(cld.getTime()));

    //Build data to hash and querystring
    List fieldNames = new ArrayList(vnp_Params.keySet());
    Collections.sort(fieldNames);
    StringBuilder hashData = new StringBuilder();
    StringBuilder query = new StringBuilder();
    Iterator itr = fieldNames.iterator();
    while (itr.hasNext()) {
      String fieldName = (String) itr.next();
      String fieldValue = (String) vnp_Params.get(fieldName);
      if ((fieldValue != null) && (fieldValue.length() > 0)) {
        //Build hash data
        hashData.append(fieldName);
        hashData.append('=');
        hashData.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII));
        //Build query
        query.append(URLEncoder.encode(fieldName, StandardCharsets.US_ASCII));
        query.append('=');
        query.append(URLEncoder.encode(fieldValue, StandardCharsets.US_ASCII));
        if (itr.hasNext()) {
          query.append('&');
          hashData.append('&');
        }
      }
    }
    String queryUrl = query.toString();
    String vnp_SecureHash = ConfigVNPay
        .hmacSHA512(ConfigVNPay.VNP_HASH_SECRET, hashData.toString());
    queryUrl += "&vnp_SecureHash=" + vnp_SecureHash;
    String paymentUrl = ConfigVNPay.VNP_PAY_URL + "?" + queryUrl;

    return ResponseEntity.ok()
        .body(new ResponseObject(
            "ok",
            "vnpay return",
            paymentUrl
        ));
  }

  /**
   * Check payment.
   *
   * @param req thong tin payment
   * @return ResponseEntity
   * @author AnhTuan
   * @since 25/05/2023
   */
  @PostMapping("/check-payment")
  public ResponseEntity<?> checkPayment(@RequestBody PaymentModel req) {
    if (req == null) {
      return ResponseEntity.notFound().build();
    }
    if (req.getVnp_ResponseCode().equals("00")) {
      return ResponseEntity.ok()
          .body(new ResponseObject(
              "ok",
              "vnpay return",
              null
          ));
    }

    return ResponseEntity.notFound().build();
  }

}
