package com.graduation.projectgraduation.controllers;

import com.graduation.projectgraduation.model.ResponseObject;
import com.graduation.projectgraduation.servicies.ChiTietDonHangService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * Controller ChiTietDonHang.
 *
 * @author AnhTuan
 * @version 1.0
 * @since 25/05/2023
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/api/chi-tiet-don-hang")
@Slf4j
@RequiredArgsConstructor
public class ChiTietDonHangController {

  private final ChiTietDonHangService chiTietDonHangService;

  /**
   * Lay thong tin chi tiet don hang theo id cua don hang.
   *
   * @param id id cua don hang
   * @return ResponseEntity
   * @author AnhTuan
   * @since 25/05/2023
   */
  @GetMapping(path = "/{id}")
  public ResponseEntity<ResponseObject> getAllChiTietDonHangById(@PathVariable Long id) {
    log.info("getAllChiTietDonHangById");
    return ResponseEntity.ok()
        .body(new ResponseObject(
            "ok",
            "danh sach chi tiet don hang",
            chiTietDonHangService.getAllByIdDonHang(id)
        ));
  }

  /**
   * Lay top 5 sach ban chay nhat.
   *
   * @return ResponseEntity
   * @author AnhTuan
   * @since 25/05/2023
   */
  @GetMapping(path = "/top-5")
  public ResponseEntity<ResponseObject> getTop5() {
    log.info("getTop5");
    return ResponseEntity.ok()
        .body(new ResponseObject(
            "ok",
            "top 5",
            chiTietDonHangService.getTop5()
        ));
  }

  /**
   * Tinh tong tien cua don hang.
   *
   * @param id cua don hang
   * @return ResponseEntity
   * @author AnhTuan
   * @since 25/05/2023
   */
  @GetMapping(path = "/tong-tien-don-hang/{id}")
  public ResponseEntity<ResponseObject> getTongTienDonHang(@PathVariable Long id) {
    log.info("getTongTienDonHang");
    return ResponseEntity.ok()
        .body(new ResponseObject(
            "ok",
            "top 5",
            chiTietDonHangService.getTongTienDonHang(id)
        ));
  }
}
