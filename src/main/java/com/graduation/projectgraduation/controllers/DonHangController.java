package com.graduation.projectgraduation.controllers;

import com.graduation.projectgraduation.model.ListSanPhamModel;
import com.graduation.projectgraduation.model.ResponseObject;
import com.graduation.projectgraduation.servicies.DonHangService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller DonHangController.
 *
 * @author AnhTuan
 * @version 1.0
 * @since 25/05/2023
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/api/don-hang")
@Slf4j
@RequiredArgsConstructor
public class DonHangController {

  private final DonHangService donHangService;

  /**
   * Lay all don hang.
   *
   * @return ResponseEntity
   * @author AnhTuan
   * @since 25/05/2023
   */
  @GetMapping
  public ResponseEntity<ResponseObject> getAllDonHang() {
    log.info("getAllDonHang");
    return ResponseEntity.ok()
        .body(new ResponseObject(
            "ok",
            "danh sach don hang",
            donHangService.findAll()
        ));
  }

  /**
   * Lay don hang theo id khach hang.
   *
   * @param id id cua khach hang
   * @return ResponseEntity
   * @author AnhTuan
   * @since 25/05/2023
   */
  @GetMapping(path = "/{id}")
  public ResponseEntity<ResponseObject> getAllDonHangByIdKhachHang(@PathVariable Long id) {
    log.info("getAllDonHangByIdKhachHang");
    return ResponseEntity.ok()
        .body(new ResponseObject(
            "ok",
            "danh sach don hang",
            donHangService.findAllByIdKhachHang(id)
        ));
  }

  /**
   * Lay all lich su don hang theo id khach hang.
   *
   * @param id cua khach hang
   * @return ResponseEntity
   * @author AnhTuan
   * @since 25/05/2023
   */
  @GetMapping(path = "/lich-su/{id}")
  public ResponseEntity<ResponseObject> getAllLichSuDonHangByIdKhachHang(@PathVariable Long id) {
    log.info("getAllLichSuDonHangByIdKhachHang");
    return ResponseEntity.ok()
        .body(new ResponseObject(
            "ok",
            "danh sach don hang",
            donHangService.findAllLichSuByIdKhachHang(id)
        ));
  }

  /**
   * Them hoac cap nhat don hang.
   *
   * @param listSanPhamModel thong tin san pham
   * @return ResponseEntity
   * @author AnhTuan
   * @since 25/05/2023
   */
  @PostMapping()
  public ResponseEntity<ResponseObject> addDonHang(@RequestBody ListSanPhamModel listSanPhamModel) {
    log.info("addOrUpdateDonHang");
    donHangService.addDonHang(listSanPhamModel);
    return ResponseEntity.ok()
        .body(new ResponseObject(
            "ok",
            "cap nhat thanh cong",
            null
        ));
  }

  /**
   * Huy don hang.
   *
   * @param id id don hang
   * @return ResponseEntity
   * @author AnhTuan
   * @since 25/05/2023
   */
  @DeleteMapping(path = "/cancel/{id}")
  public ResponseEntity<ResponseObject> huyDonHang(@PathVariable Long id) {
    log.info("huyDonHang");
    donHangService.deleteDonHang(id);
    return ResponseEntity.ok()
        .body(new ResponseObject(
            "ok",
            "cap nhat thanh cong",
            null
        ));
  }

  /**
   * Cap nhat trang thai don hang.
   *
   * @param id cua don hang
   * @return ResponseEntity
   * @author AnhTuan
   * @since 25/05/2023
   */
  @PutMapping(path = "/{id}")
  public ResponseEntity<ResponseObject> updateTrangThai(@PathVariable Long id) {
    log.info("updateTrangThai");
    donHangService.capNhatTrangThaiDonHang(id);
    return ResponseEntity.ok()
        .body(new ResponseObject(
            "ok",
            "cap nhat thanh cong",
            null
        ));
  }

  /**
   * Thong ke theo thang trong nam hien tai.
   *
   * @return ResponseEntity
   * @author AnhTuan
   * @since 25/05/2023
   */
  @GetMapping(path = "/thong-ke-theo-thang")
  public ResponseEntity<ResponseObject> thongKeTheoThang() {
    log.info("thongKeTheoThang");
    return ResponseEntity.ok()
        .body(new ResponseObject(
            "ok",
            "thongKeTheoThang",
            donHangService.thongKeTheoThang()
        ));
  }

  /**
   * Thong ke chi tiet theo thang.
   *
   * @param thang thang can thong ke
   * @return ResponseEntity
   * @author AnhTuan
   * @since 25/05/2023
   */
  @GetMapping(path = "/thong-ke-theo-thang/{thang}")
  public ResponseEntity<ResponseObject> thongKeTheoThang2(@PathVariable int thang) {
    log.info("thongKeTheoThang2");
    return ResponseEntity.ok()
        .body(new ResponseObject(
            "ok",
            "thongKeTheoThang",
            donHangService.thongKeTheoThang(thang)
        ));
  }
}
