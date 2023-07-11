package com.graduation.projectgraduation.controllers;

import com.graduation.projectgraduation.entities.DanhMuc;
import com.graduation.projectgraduation.model.ResponseObject;
import com.graduation.projectgraduation.servicies.DanhMucService;
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
 * Controller DanhMucController.
 *
 * @author AnhTuan
 * @version 1.0
 * @since 25/05/2023
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/api/danh-muc")
@Slf4j
@RequiredArgsConstructor
public class DanhMucController {

  private final DanhMucService danhMucService;

  /**
   * Tra ve danh sach danh muc.
   *
   * @return ResponseEntity
   * @author AnhTuan
   * @since 25/05/2023
   */
  @GetMapping()
  public ResponseEntity<ResponseObject> getAllDanhMuc() {
    log.info("getAllDanhMuc");
    return ResponseEntity.ok()
        .body(new ResponseObject(
            "ok",
            "danh sach danh muc",
            danhMucService.getAllDanhMuc()
        ));
  }

  /**
   * Tra ve danh muc theo id.
   *
   * @param id id cua danh muc
   * @return ResponseEntity
   * @author AnhTuan
   * @since 25/05/2023
   */
  @GetMapping(path = "/{id}")
  public ResponseEntity<ResponseObject> getDanhMucById(@PathVariable Long id) {
    log.info("getDanhMucById");
    return ResponseEntity.ok()
        .body(new ResponseObject(
            "ok",
            "thong tin danh muc",
            danhMucService.getDanhMucById(id)
        ));
  }

  /**
   * Them danh muc hoac cap nhat danh muc.
   *
   * @param danhMuc thong tin danh muc
   * @return ResponseEntity
   * @author AnhTuan
   * @since 25/05/2023
   */
  @PostMapping()
  public ResponseEntity<ResponseObject> insertAndUpdateDanhMuc(@RequestBody DanhMuc danhMuc) {
    log.info("insertAndUpdateDanhMuc");
    boolean isSuccess = danhMucService.insertDanhMuc(danhMuc);

    ResponseEntity<ResponseObject> responseEntity = null;
    ResponseObject object = new ResponseObject();

    if (isSuccess) {
      object.setStatus("successful");
      object.setMessage("cap nhat database thanh cong");
    } else {
      object.setStatus("unsuccessful");
      object.setMessage("cap nhat database that bai");
    }
    object.setData(null);
    responseEntity = ResponseEntity.ok().body(object);

    return responseEntity;
  }

  /**
   * Xoa danh muc dua vao id.
   *
   * @param id id danh muc
   * @return ResponseEntity
   * @author AnhTuan
   * @since 25/05/2023
   */
  @DeleteMapping(path = "/{id}")
  public ResponseEntity<ResponseObject> deleteDanhMuc(@PathVariable Long id) {
    log.info("deleteDanhMuc");
    String message = danhMucService.deleteDanhMuc(id);
    return ResponseEntity.ok()
        .body(new ResponseObject(
            "xoa thanh cong".equals(message) ? "ok" : "fail",
            message,
            null
        ));
  }
}
