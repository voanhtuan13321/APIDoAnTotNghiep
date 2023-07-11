package com.graduation.projectgraduation.util;

/**
 * Luu tru string.
 *
 * @author AnhTuan
 * @date 25/05/2023
 */
public class MyProperties {
  public static final String PATH_SAVE_IMG = "src\\main\\resources\\static\\img_sach\\";
  public static final String PATH_FILE_MAIL_CHANGE_PASSWORD
      = "src\\main\\resources\\templates\\MailForChangePassword.html";

  public static final String FROM_EMAIL = "voanhtuan13321@gmail.com";
  public static final String SUBJECT = "[QLBS] Thay đổi mật khẩu của bạn";

  // query sach
  public static final String SEARCHING_SACH = "SELECT * FROM SACH"
      + " WHERE ten LIKE ?1 OR tac_gia LIKE ?1 OR nha_xuat_ban LIKE ?1"
      + " OR mo_ta LIKE ?1";

  // query khach hang
  public static final String SEARCHING_KHACH_HANG = "SELECT * FROM khach_hang"
      + " WHERE ten LIKE ?1 OR email LIKE ?1 OR dia_chi LIKE ?1"
      + " OR so_dien_thoai LIKE ?1 OR tai_khoan LIKE ?1";

  public static final String SELECT_GIO_HANG_BY_ID_KHACH_HANG_AND_ID_SACH
      = "SELECT * FROM gio_hang"
      + " WHERE id_khach_hang = ?1 AND id_sach = ?2";
}
