package com.graduation.projectgraduation.util;

import com.graduation.projectgraduation.entities.GioHang;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

/**
 * Xu ly file va ma hoa.
 *
 * @author AnhTuan
 * @date 25/05/2023
 */
public class MaHoa {
  /**
   * phuong thuc dung dẻ chuyen tu base64 dang file.
   */
  public static String convertBase64ToFile(String base64) throws IOException {
    String fileName = generateRandomFileName("jpg");
    String fileOutputPath = MyProperties.PATH_SAVE_IMG;

    // Decode Base64 encoded string to binary data
    byte[] decodedBytes = Base64.getDecoder().decode(base64);

    // Write decoded binary data to a file
    File file = new File(fileOutputPath + fileName);
    if (!file.exists()) {
      file.createNewFile();
    }
    FileOutputStream outputStream = new FileOutputStream(file);
    outputStream.write(decodedBytes);

    return fileName;
  }

  /**
   * phuong thuc dung de chuyen tu file sang base64.
   */
  public static String convertFileToBase64(String fileName) throws IOException {
    Path path = Paths.get(MyProperties.PATH_SAVE_IMG + fileName);
    byte[] imageBytes = Files.readAllBytes(path);
    return Base64.getEncoder().encodeToString(imageBytes);
  }

  /**
   * phuong thuc dung de tao ten file ngau nhien.
   */
  private static String generateRandomFileName(String extension) {
    String randomUUID = UUID.randomUUID().toString();
    long timeStamp = new Date().getTime();
    return randomUUID + timeStamp + "." + extension;
  }

  /**
   * phuong thuc tap captcha.
   */
  public static String generateCaptcha(final int length) {
    StringBuilder captcha = new StringBuilder();
    String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
    for (int i = 0; i < length; i++) {
      int index = (int) (Math.random() * chars.length());
      captcha.append(chars.charAt(index));
    }
    return captcha.toString();
  }

  /**
   * Thuật toán mã hoá string MD5.
   */
  public static String encryptByMD5(String input) throws NoSuchAlgorithmException {
    MessageDigest messageDigest = MessageDigest.getInstance("MD5");

    // Chuyển đổi chuỗi đầu vào thành mảng byte
    byte[] inputBytes = input.getBytes(StandardCharsets.UTF_8);
    // Mã hoá mảng byte đầu vào bằng thuật toán MD5 và lấy ra kết quả mã hoá
    byte[] hashBytes = messageDigest.digest(inputBytes);

    // Chuyển đổi mảng byte kết quả mã hoá thành chuỗi hexa
    StringBuilder stringBuilder = new StringBuilder(2 * hashBytes.length);
    for (byte b : hashBytes) {
      stringBuilder.append(String.format("%02x", b & 0xff));
    }

    return stringBuilder.toString();
  }

  /**
   * xoa anh trong thu muc.
   */
  public static void deleteImage(String filename) {
    File file = new File(MyProperties.PATH_SAVE_IMG + filename);
    if (file.exists()) {
      file.delete();
    }
  }

  /**
   * fasdfa.
   */
  public static String generateMaHoaDon(GioHang gioHang) {
    return gioHang.getKhachHang().getIdKhachHang() + "-" + new Date().getTime();
  }

}
