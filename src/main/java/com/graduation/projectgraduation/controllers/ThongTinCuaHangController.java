package com.graduation.projectgraduation.controllers;

import com.graduation.projectgraduation.entities.ThongTinCuaHang;
import com.graduation.projectgraduation.model.ResponseObject;
import com.graduation.projectgraduation.servicies.ThongTinCuaHangService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/thong-tin-cua-hang")
@Slf4j
@RequiredArgsConstructor
public class ThongTinCuaHangController {

  private final ThongTinCuaHangService thongTinCuaHangService;

  /**
   * Lay all thong tin cua hang.
   *
   * @return ResponseEntity
   * @author AnhTuan
   * @since 25/05/2023
   */
  @GetMapping()
  public ResponseEntity<ResponseObject> getThongTinCuaHang() {
    log.info("getThongTinCuaHang");
    return ResponseEntity.ok()
        .body(new ResponseObject(
            "ok",
            "danh sach thong tin cua hang",
            thongTinCuaHangService.getAll()
        ));
  }

  /**
   * Them hoac chinh sua thong tin cua hang.
   *
   * @param thongTinCuaHang thong tin cua hang
   * @return ResponseEntity
   * @author AnhTuan
   * @since 25/05/2023
   */
  @PostMapping()
  public ResponseEntity<ResponseObject> insertOrUpdate(
      @RequestBody ThongTinCuaHang thongTinCuaHang
  ) {
    log.info("insertOrUpdateThongTinCuaHang");
    boolean isSuccess = thongTinCuaHangService.insertOrUpdateThongTinCuaHang(thongTinCuaHang);
    return ResponseEntity.ok()
        .body(new ResponseObject(
            (isSuccess ? "successful" : "unsuccessful"),
            (isSuccess ? "cap nhat database thanh cong" : "cap nhat database that bai"),
            null
        ));
  }

}
