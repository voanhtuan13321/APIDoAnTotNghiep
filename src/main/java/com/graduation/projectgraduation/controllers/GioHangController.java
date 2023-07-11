package com.graduation.projectgraduation.controllers;

import com.graduation.projectgraduation.entities.GioHang;
import com.graduation.projectgraduation.model.GioHangModel;
import com.graduation.projectgraduation.model.ListIdGioHang;
import com.graduation.projectgraduation.model.ResponseObject;
import com.graduation.projectgraduation.servicies.GioHangService;
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
 * Controller GioHangController.
 *
 * @author AnhTuan
 * @version 1.0
 * @since 25/05/2023
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/api/gio-hang")
@Slf4j
@RequiredArgsConstructor
public class GioHangController {

  private final GioHangService gioHangService;

  /**
   * Lay danh sach gio hang cua khach hang.
   *
   * @param id cua khach hang
   * @return ResponseEntity
   * @author AnhTuan
   * @since 25/05/2023
   */
  @GetMapping(path = "/{id}")
  public ResponseEntity<ResponseObject> getAllGioHangByIdKhachHang(@PathVariable Long id) {
    log.info("getAllGioHangByIdKhachH");
    return ResponseEntity.ok()
        .body(new ResponseObject(
            "ok",
            "danh sach san pham trong gio hang",
            gioHangService.getAllGioHangByIdKhachHang(id)
        ));
  }

  /**
   * Lay all sach theo id cua gio hang.
   *
   * @param id cua gio hang
   * @return ResponseEntity
   * @author AnhTuan
   * @since 25/05/2023
   */
  @GetMapping(path = "/find-by/{id}")
  public ResponseEntity<ResponseObject> getAllGioHangById(@PathVariable Long id) {
    log.info("getAllGioHangById");
    return ResponseEntity.ok()
        .body(new ResponseObject(
            "ok",
            "danh sach san pham trong gio hang",
            gioHangService.getAllGioHangById(id)
        ));
  }

  /**
   * Lay all san pham trong gio hang da duoc dat hang.
   *
   * @return ResponseEntity
   * @author AnhTuan
   * @since 25/05/2023
   */
  @GetMapping(path = "/don-hang")
  public ResponseEntity<ResponseObject> getAllDonHang() {
    log.info("get all don hang");
    return ResponseEntity.ok()
        .body(new ResponseObject(
            "ok",
            "danh sach san pham trong gio hang theo trang thai",
            gioHangService.getAllDonHang()
        ));
  }

  /**
   * Cap nhat trang thai gio hang -> da thanh toan.
   *
   * @param id      cua gio hang
   * @param gioHang thong tin gio hang
   * @return ResponseEntity
   * @author AnhTuan
   * @since 25/05/2023
   */
  @PutMapping(path = "/cap-nhat-trang-thai/{id}")
  public ResponseEntity<ResponseObject> updateTrangThai(
      @PathVariable Long id,
      @RequestBody GioHang gioHang
  ) {
    log.info("xacNhanDonhang");
    gioHangService.updateTrangThai(id, gioHang.getTrangThai());
    return ResponseEntity.ok()
        .body(new ResponseObject(
            "ok",
            "xac nhan don hang",
            null
        ));
  }

  /**
   * Them sach vao gio hang.
   *
   * @param gioHangModel thong tin cua gio hang
   * @return ResponseEntity
   * @author AnhTuan
   * @since 25/05/2023
   */
  @PostMapping()
  public ResponseEntity<ResponseObject> insertSachVaoGoHang(
      @RequestBody GioHangModel gioHangModel
  ) {
    log.info("insertSachVaoGoHang");
    return ResponseEntity.ok()
        .body(new ResponseObject(
            "ok",
            "them thanh cong",
            gioHangService.insertSachVaoGioHang(gioHangModel)
        ));
  }

  /**
   * Giam so luong san pham.
   *
   * @param gioHangModel thong tin cua gio hang
   * @return ResponseEntity
   * @author AnhTuan
   * @since 25/05/2023
   */
  @PutMapping()
  public ResponseEntity<ResponseObject> removeSoLuongOfGioHang(
      @RequestBody GioHangModel gioHangModel
  ) {
    log.info("removeSoLuongOfGioHang");
    gioHangService.removeSoLuong(gioHangModel);
    return ResponseEntity.ok()
        .body(new ResponseObject(
            "ok",
            "giam thanh cong",
            null
        ));
  }

  /**
   * Xoa san pham khoi gio hang.
   *
   * @param listIdGioHang danh sach id gio hang
   * @return ResponseEntity
   * @author AnhTuan
   * @since 25/05/2023
   */
  @PostMapping(path = "/delete")
  public ResponseEntity<ResponseObject> deleteListGioHang(@RequestBody ListIdGioHang listIdGioHang) {
    log.info("deleteGioHang");
    gioHangService.deleteGioHang(listIdGioHang);
    return ResponseEntity.ok()
        .body(new ResponseObject(
            "ok",
            "xoa thanh cong",
            null
        ));
  }

  /**
   * Xoa gio hang theo id.
   *
   * @param id cua gio hang
   * @return ResponseEntity
   * @author AnhTuan
   * @since 25/05/2023
   */
  @DeleteMapping(path = "/{id}")
  public ResponseEntity<ResponseObject> deleteGioHang(@PathVariable Long id) {
    log.info("deleteGioHang");
    gioHangService.deleteGioHang(id);
    return ResponseEntity.ok()
        .body(new ResponseObject(
            "ok",
            "xoa thanh cong",
            null
        ));
  }
}
