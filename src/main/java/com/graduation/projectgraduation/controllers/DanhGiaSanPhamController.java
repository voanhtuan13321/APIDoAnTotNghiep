package com.graduation.projectgraduation.controllers;

import com.graduation.projectgraduation.model.DanhGiaSanPhamModel;
import com.graduation.projectgraduation.model.ResponseObject;
import com.graduation.projectgraduation.servicies.DanhGiaSanPhamService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller DanhGiaSanPhamController.
 *
 * @author AnhTuan
 * @version 1.0
 * @since 25/05/2023
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/api/danh-gia-san-pham")
@Slf4j
@RequiredArgsConstructor
public class DanhGiaSanPhamController {

  private final DanhGiaSanPhamService danhGiaSanPhamService;

  /**
   * Api tra ve danh sach san pham co binh luan.
   *
   * @return ResponseEntity
   * @author AnhTuan
   * @since 25/05/2023
   */
  @GetMapping()
  public ResponseEntity<ResponseObject> getAllSachCoBinhLuan() {
    log.info("getAllSachCoBinhLuan");
    return ResponseEntity.ok()
        .body(new ResponseObject(
            "ok",
            "danh sach cac binh luan",
            danhGiaSanPhamService.getAllSachCoBinhLuan()
        ));
  }

  /**
   * Api tra ve danh sach cac binh luan ve san pham do.
   *
   * @param id id cua sach
   * @return ResponseEntity
   * @author AnhTuan
   * @since 25/05/2023
   */
  @GetMapping(path = "/{id}")
  public ResponseEntity<ResponseObject> getAllDanhGiaByIdSach(@PathVariable Long id) {
    log.info("getAllDanhGiaByIdSach");
    return ResponseEntity.ok()
        .body(new ResponseObject(
            "ok",
            "danh sach cac binh luan",
            danhGiaSanPhamService.getAllDanhSachSanPhamByIdSach(id)
        ));
  }

  /**
   * Api dung de them 1 binh luan moi, hoac chinh sua binh luan.
   *
   * @param danhGiaSanPhamModel thong tin cua binh luan
   * @return ResponseEntity
   * @author AnhTuan
   */
  @PostMapping()
  public ResponseEntity<ResponseObject> insertBinhLuan(
      @RequestBody DanhGiaSanPhamModel danhGiaSanPhamModel
  ) {
    log.info("insertBinhLuan");
    danhGiaSanPhamService.insertBinhLuan(danhGiaSanPhamModel);
    return ResponseEntity.ok()
        .body(new ResponseObject(
            "ok",
            "them binh luan thanh cong",
            null
        ));
  }

  /**
   * Api dung de xoa binh luan theo id cua binh luan.
   *
   * @param id cua binh luan
   * @return ResponseEntity
   * @author AnhTuan
   * @since 25
   */
  @DeleteMapping(path = "/{id}")
  public ResponseEntity<ResponseObject> deleteBinhLuan(@PathVariable Long id) {
    log.info("deleteBinhLuan");
    danhGiaSanPhamService.deleteBinhLuan(id);
    return ResponseEntity.ok()
        .body(new ResponseObject(
            "ok",
            "xoa binh luan than cong",
            null
        ));
  }
}
