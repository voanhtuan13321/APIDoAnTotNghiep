package com.graduation.projectgraduation.controllers;

import com.graduation.projectgraduation.entities.QuanLy;
import com.graduation.projectgraduation.model.LoggingModel;
import com.graduation.projectgraduation.model.MailModel;
import com.graduation.projectgraduation.model.ResponseObject;
import com.graduation.projectgraduation.servicies.QuanLyService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Controller QuanLyController.
 *
 * @author AnhTuan
 * @version 1.0
 * @since 25/05/2023
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/api/quan-ly")
@Slf4j
@RequiredArgsConstructor
public class QuanLyController {

  private final QuanLyService quanLyService;

  /**
   * Lay thong tin cua toan bo quan ly.
   *
   * @return ResponseEntity
   * @author AnhTuan
   * @since 25/05/2023
   */
  @GetMapping()
  public ResponseEntity<ResponseObject> getAllQuanLy() {
    log.info("getAllQuanLy");
    return ResponseEntity.ok()
        .body(new ResponseObject(
            "ok",
            "danh sach quan ly",
            quanLyService.getAll()
        ));
  }

  /**
   * Lay thong tin cua quan ly theo id.
   *
   * @param id id cua quan ly
   * @return ResponseEntity
   * @author AnhTuan
   * @since 25/05/2023
   */
  @GetMapping(path = "{id}")
  public ResponseEntity<ResponseObject> getQuanLyById(@PathVariable Long id) {
    log.info("getQuanLyById");
    return ResponseEntity.ok()
        .body(new ResponseObject(
            "ok",
            "thong tin quan ly",
            quanLyService.findById(id)
        ));
  }

  /**
   * Them quan ly.
   *
   * @param quanLy thong tin cua quan ly
   * @return ResponseEntity
   * @author AnhTuan
   * @since 25/05/2023
   */
  @PostMapping()
  public ResponseEntity<ResponseObject> insertQuanLy(@RequestBody QuanLy quanLy) {
    log.info("insertQuanLy");
    quanLyService.insertQuanLy(quanLy);
    return ResponseEntity.ok()
        .body(new ResponseObject(
            "ok",
            "cap nhat database thanh cong",
            ""
        ));
  }

  /**
   * Cap nhat thong tin quan ly.
   *
   * @param quanLy thong tin cua quan ly
   * @return ResponseEntity
   * @author AnhTuan
   * @since 25/05/2023
   */
  @PutMapping()
  public ResponseEntity<ResponseObject> updateQuanLy(@RequestBody QuanLy quanLy) {
    log.info("updateQuanLy");
    quanLyService.updateQuanLy(quanLy, false);
    return ResponseEntity.ok()
        .body(new ResponseObject(
            "ok",
            "cap database thanh cong",
            null
        ));
  }

  /**
   * Gui mail de xac nhan doi mat khau.
   *
   * @param id id cua quan ly
   * @param mailModel thong tin mail
   * @return ResponseEntity
   * @author AnhTuan
   * @since 25/05/2023
   */
  @PostMapping(path = "/email/{id}")
  public ResponseEntity<ResponseObject> sendEmailQuanLy(
      @PathVariable Long id,
      @RequestBody MailModel mailModel
  ) {
    log.info("sendEmailQuanLy");
    try {
      quanLyService.sendMail(id, mailModel.getHref());
      return ResponseEntity.ok()
          .body(new ResponseObject(
              "ok",
              "da gui email",
              null));

    } catch (RuntimeException e) {
      e.printStackTrace();
      return ResponseEntity.ok()
          .body(new ResponseObject(
              "fail",
              "gui mail that bai",
              null
          ));
    }
  }

  /**
   * Cap nhat mat khau.
   *
   * @param quanLy thong tin quan ly
   * @return ResponseEntity
   * @author AnhTuan
   * @since 25/05/2023
   */
  @PutMapping(path = "/password")
  public ResponseEntity<ResponseObject> updateMatKhau(@RequestBody QuanLy quanLy) {
    log.info("updateMatKhauQuanLy");
    quanLyService.updateQuanLy(quanLy, true);
    return ResponseEntity.ok()
        .body(new ResponseObject(
            "ok",
            "cap nhat database thanh cong",
            null
        ));
  }

  /**
   * Kiem tra dang nhap.
   *
   * @param loggingModel thong tin loging
   * @return ResponseEntity
   * @author AnhTuan
   * @since 25/05/2023
   */
  @PostMapping(path = "/logging")
  public ResponseEntity<ResponseObject> logging(@RequestBody LoggingModel loggingModel) {
    log.info("logging");
    QuanLy quanLy = quanLyService.isLogging(loggingModel);

    return ResponseEntity.ok()
        .body(new ResponseObject(
            (quanLy != null ? "ok" : "fail"),
            (quanLy != null ? "mat khau trung khop" : "mat khau khong dung"),
            quanLy
        ));
  }

  /**
   * Kiem ta ton tai tai khoan.
   *
   * @param loggingModel thong tin tai khoan
   * @return ResponseEntity
   * @author AnhTuan
   * @since 25/05/2023
   */
  @PostMapping(path = "/check-tai-khoan")
  public ResponseEntity<ResponseObject> checkTaiKhoan(@RequestBody LoggingModel loggingModel) {
    log.info("Checking tai khoan");
    boolean isTaiKhoan = quanLyService.isTaiKhoan(loggingModel.getTaiKhoan());
    return ResponseEntity.ok()
        .body(new ResponseObject(String.valueOf(isTaiKhoan), "", null));
  }

}
