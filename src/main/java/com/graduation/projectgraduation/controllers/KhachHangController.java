package com.graduation.projectgraduation.controllers;

import com.graduation.projectgraduation.entities.KhachHang;
import com.graduation.projectgraduation.model.MailModel;
import com.graduation.projectgraduation.model.ResponseObject;
import com.graduation.projectgraduation.servicies.KhachHangService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller KhachHangController.
 *
 * @author AnhTuan
 * @version 1.0
 * @since 25/05/2023
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/api/khach-hang")
@Slf4j
@RequiredArgsConstructor
public class KhachHangController {

  private final KhachHangService khachHangService;

  /**
   * Lay all khach hang.
   *
   * @return ResponseEntity
   * @author AnhTuan
   * @since 25/05/2023
   */
  @GetMapping()
  public ResponseEntity<ResponseObject> getAllKhachHang() {
    log.info("getAllKhachHang");
    return ResponseEntity.ok()
        .body(new ResponseObject(
            "ok",
            "danh sach khach hang",
            khachHangService.getAllKhachHang()
        ));
  }

  /**
   * Tim khach hang theo id.
   *
   * @param id cua khach hang
   * @return ResponseEntity
   * @author AnhTuan
   * @since 25/05/2023
   */
  @GetMapping(path = "/{id}")
  public ResponseEntity<ResponseObject> getKhachHangById(@PathVariable Long id) {
    log.info("get khach khach khach khach");
    return ResponseEntity.ok()
        .body(new ResponseObject(
            "ok",
            "thong tin khach hang",
            khachHangService.getKhachHangById(id)
        ));
  }

  /**
   * Tra ve danh sach khach hang can tim kiem.
   *
   * @param search tu khoa tim kiem
   * @return ResponseEntity
   * @author AnhTuan
   * @since 25/05/2023
   */
  @GetMapping(path = "/searching")
  public ResponseEntity<ResponseObject> searchKhachHang(
      @RequestParam(name = "search") String search
  ) {
    log.info("search khach hang");
    return ResponseEntity.ok()
        .body(new ResponseObject(
            "ok",
            "danh sach khach hang can tim kiem",
            khachHangService.searchKhachHang(search)
        ));
  }

  /**
   * Them thong tin khach hang, bao gom ca doi mat khau.
   *
   * @param khachHang thong tin khach hang
   * @return ResponseEntity
   * @author AnhTuan
   * @since 25/05/2023
   */
  @PostMapping()
  public ResponseEntity<ResponseObject> insertKhachHang(@RequestBody KhachHang khachHang) {
    log.info("insert khach hang");
    ResponseObject object = new ResponseObject();

    khachHang = khachHangService.insertKhachHang(khachHang);
    if (khachHang != null) {
      object.setStatus("successful");
      object.setMessage("cap nhat database thanh cong");
      object.setData(khachHang);
    } else {
      object.setStatus("unsuccessful");
      object.setMessage("cap nhat database that bai");
      object.setData(null);
    }
    return ResponseEntity.ok().body(object);
  }

  /**
   * Cap nhat thong tin khach hang.
   *
   * @param khachHang thong tin khach hang
   * @return ResponseEntity
   * @author AnhTuan
   * @since 25/05/2023
   */
  @PutMapping()
  public ResponseEntity<ResponseObject> updateKhachHang(@RequestBody KhachHang khachHang) {
    log.info("update khach hang");
    ResponseObject object = new ResponseObject();

    boolean isSuccess = khachHangService.updateKhachHang(khachHang);
    if (isSuccess) {
      object.setStatus("successful");
      object.setMessage("cap nhat database thanh cong");
    } else {
      object.setStatus("unsuccessful");
      object.setMessage("cap nhat database that bai");
    }
    object.setData(null);
    return ResponseEntity.ok().body(object);
  }

  /**
   * Xoa khach hang theo id.
   *
   * @param id id cua khach hang
   * @return ResponseEntity
   * @author AnhTuan
   * @since 25/05/2023
   */
  @DeleteMapping(path = "/{id}")
  public ResponseEntity<ResponseObject> deleteKhachHang(@PathVariable Long id) {
    log.info("delete khach khach khach");
    khachHangService.deleteKhachHang(id);
    return ResponseEntity.ok()
        .body(new ResponseObject(
            "ok",
            "xoa khach hang thanh cong",
            null
        ));
  }

  /**
   * Gui mail.
   *
   * @param mailModel thong tin mail
   * @return ResponseEntity
   * @author AnhTuan
   * @since 25/05/2023
   */
  @PostMapping(path = "/email")
  public ResponseEntity<ResponseObject> sendMail(@RequestBody MailModel mailModel) {
    log.info("sendMail");
    KhachHang khachHang = khachHangService.findByTaiKhoan(mailModel.getTaiKhoan());
    khachHangService.sendMail(khachHang.getIdKhachHang(), mailModel.getHref());

    return ResponseEntity.ok()
        .body(new ResponseObject(
            "ok",
            "da gui qua email",
            null)
        );
  }

  /**
   * Kiem tra dang nhap.
   *
   * @param khachHang thong tin khach hang
   * @return ResponseEntity
   * @author AnhTuan
   * @since 25/05/2023
   */
  @PostMapping(path = "/check-logging")
  public ResponseEntity<ResponseObject> checkLoggin(@RequestBody KhachHang khachHang) {
    log.info("checkLoggin");
    khachHang = khachHangService.checkLogging(khachHang.getTaiKhoan(), khachHang.getMatKhau());

    return ResponseEntity.ok()
        .body(new ResponseObject(
            (khachHang != null ? "ok" : "fail"),
            (khachHang != null ? "logging thanh cong" : "logging khong thanh cong"),
            khachHang
        ));
  }

  /**
   * Kiem tra tai khoan co ton tai khong.
   *
   * @param khachHang thong tin khach hang
   * @return ResponseEntity
   * @author AnhTuan
   * @since 25/05/2023
   */
  @PostMapping(path = "/check-tai-khoan")
  public ResponseEntity<ResponseObject> checkTaiKhoan(@RequestBody KhachHang khachHang) {
    log.info("Check tai khoan");
    boolean isCheck = khachHangService.checkTaiKhoan(khachHang.getTaiKhoan());
    return ResponseEntity.ok()
        .body(new ResponseObject(
            (isCheck ? "ok" : "fail"),
            (isCheck ? "ton tai tai khoan" : "khong ton tai tai khoan"),
            null
        ));
  }

  /**
   * Cap nhat mat khau.
   *
   * @param khachHang thong tin khach hang
   * @return ResponseEntity
   * @author AnhTuan
   * @since 25/05/2023
   */
  @PostMapping(path = "doi-mat-khau")
  public ResponseEntity<ResponseObject> doiMatKhau(@RequestBody KhachHang khachHang) {
    log.info("doi-mat-khau");
    boolean isChanged = khachHangService.updateMatKhau(khachHang);
    return ResponseEntity.ok()
        .body(new ResponseObject(
            (isChanged ? "ok" : "fail"),
            (isChanged ? "doi mat khau thanh cong" : "doi mat khau that bai"),
            null
        ));
  }
}
