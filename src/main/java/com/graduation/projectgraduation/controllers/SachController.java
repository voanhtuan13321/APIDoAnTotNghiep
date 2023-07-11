package com.graduation.projectgraduation.controllers;

import com.graduation.projectgraduation.entities.Sach;
import com.graduation.projectgraduation.model.ResponseObject;
import com.graduation.projectgraduation.model.SachModel;
import com.graduation.projectgraduation.servicies.SachService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

/**
 * Controller SachController.
 *
 * @author AnhTuan
 * @version 1.0
 * @since 25/05/2023
 */
@CrossOrigin
@RestController
@RequestMapping(path = "/api/sach")
@Slf4j
@RequiredArgsConstructor
public class SachController {

  private final SachService sachService;

  /**
   * Lay all sach.
   *
   * @return ResponseEntity
   * @author AnhTuan
   * @since 25/05/2023
   */
  @GetMapping()
  public ResponseEntity<ResponseObject> getAllBooks() {
    log.info("Get all books");
    return ResponseEntity.ok()
        .body(new ResponseObject(
            "ok",
            "danh sach cac quyen sach",
            sachService.getAllBooks()
                .stream()
                .sorted(Comparator.comparing(Sach::getIdSach, Comparator.reverseOrder()))
                .toList()
        ));
  }

  /**
   * Lay all sach theo danh muc.
   *
   * @param id id cua danh muc
   * @return ResponseEntity
   * @author AnhTuan
   * @since 25/05/2023
   */
  @GetMapping(path = "/by-danh-muc/{id}")
  public ResponseEntity<ResponseObject> getAllBooksByDanhMuc(@PathVariable Long id) {
    log.info("Get all books by danh muc");
    return ResponseEntity.ok()
        .body(new ResponseObject(
            "ok",
            "danh sach cac quyen sach theo danh muc",
            sachService.getAllBooksByDanhMuc(id)
        ));
  }

  /**
   * Lay sach theo id.
   *
   * @param id id cua sach
   * @return ResponseEntity
   * @author AnhTuan
   * @since 25/05/2023
   */
  @GetMapping(value = "/{id}")
  public ResponseEntity<ResponseObject> getBookById(@PathVariable Long id) {
    log.info("getBookById");
    return ResponseEntity.ok()
        .body(new ResponseObject(
            "ok",
            "thong tin sach",
            sachService.findById(id)
        ));
  }

  /**
   * Them hoac cap nhat thong tin sach.
   *
   * @param sachModel thong tin sach
   * @return ResponseEntity
   * @author AnhTuan
   * @since 25/05/2023
   */
  @PostMapping()
  public ResponseEntity<ResponseObject> insertAndUpdateBook(@RequestBody SachModel sachModel) {
    log.info("Insert and update book");

    boolean isSuccess = false;
    try {
      isSuccess = sachService.insertOrUpdateSach(sachModel);
    } catch (IOException e) {
      log.error("them hinh sanh co loi", e);
      e.printStackTrace();
    }

    return ResponseEntity.ok()
        .body(new ResponseObject(
            (isSuccess ? "successful" : "unsuccessful"),
            (isSuccess ? "cap nhat database thanh cong" : "cap nhat database that bai"),
            null
        ));
  }

  /**
   * Xoa sach theo id.
   *
   * @param id id cua sach
   * @return ResponseEntity
   * @author AnhTuan
   * @since 25/05/2023
   */
  @DeleteMapping(path = "/{id}")
  public ResponseEntity<ResponseObject> deleteBookById(@PathVariable Long id) {
    log.info("deleteBookById");
    String message = sachService.deleteSach(id);
    return ResponseEntity.ok()
        .body(new ResponseObject(
            "xoa thanh cong".equals(message) ? "ok" : "fail",
            message,
            null
        ));
  }

  /**
   * Tim kiem sach.
   *
   * @param search tu khoa tim kiem
   * @return ResponseEntity
   * @author AnhTuan
   * @since 25/05/2023
   */
  @GetMapping(path = "/searching")
  public ResponseEntity<ResponseObject> searchBooks(@RequestParam(name = "search") String search) {
    log.info("Searching books...");
    return ResponseEntity.ok()
        .body(new ResponseObject(
            "ok",
            "danh sach tat ca quyen sach",
            sachService.searchBooks(search)
        ));
  }

  /**
   * Lay top sach moi nhat.
   *
   * @return ResponseEntity
   * @author AnhTuan
   * @since 25/05/2023
   */
  @GetMapping(path = "/sach-moi-nhat")
  public ResponseEntity<ResponseObject> sachMoiNhat() {
    log.info("Sach moi nhat");
    List<Sach> saches = sachService.sachMoiNhat();
    return ResponseEntity.ok()
        .body(new ResponseObject(
            "ok",
            "danh sach tat ca quyen sach",
            saches.subList(0, Math.min(saches.size(), 8))
        ));
  }

}
