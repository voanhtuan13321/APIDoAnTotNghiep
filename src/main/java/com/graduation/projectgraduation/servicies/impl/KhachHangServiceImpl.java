package com.graduation.projectgraduation.servicies.impl;

import com.graduation.projectgraduation.entities.KhachHang;
import com.graduation.projectgraduation.repositories.KhachHangRepository;
import com.graduation.projectgraduation.servicies.KhachHangService;
import com.graduation.projectgraduation.util.Email;
import com.graduation.projectgraduation.util.MaHoa;
import com.graduation.projectgraduation.util.MyProperties;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Service KhachHang.
 *
 * @author AnhTuan
 * @version 1.0
 * @since 10/05/2023
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class KhachHangServiceImpl implements KhachHangService {

  private final KhachHangRepository khachHangRepository;

  /**
   * Lay all khach hang.
   *
   * @return list of khach hang
   * @author AnhTuan
   * @since 10/05/2023
   */
  @Override
  public List<KhachHang> getAllKhachHang() {
    return khachHangRepository.findAll()
        .stream()
        .sorted(Comparator.comparing(KhachHang::getNgayTao).reversed())
        .toList();
  }

  /**
   * Lay thong tin khach hang theo id.
   *
   * @param id id khach hang
   * @return list of khach hang
   * @author AnhTuan
   * @since 10/05/2023
   */
  @Override
  public KhachHang getKhachHangById(Long id) {
    return khachHangRepository.findById(id).orElseThrow();
  }

  /**
   * Tim kiem khach hang.
   *
   * @return list of khach hang
   * @author AnhTuan
   * @since 10/05/2023
   */
  @Override
  public List<KhachHang> searchKhachHang(String search) {
    return khachHangRepository.search("%" + search + "%");
  }

  /**
   * Them khach hang.
   *
   * @param khachHang thong tin khach hang
   * @return khach hang da them
   * @author AnhTuan
   * @since 10/05/2023
   */
  @Override
  public synchronized KhachHang insertKhachHang(KhachHang khachHang) {
    try {
      String md5 = MaHoa.encryptByMD5(khachHang.getMatKhau());
      khachHang.setMatKhau(md5);
      khachHang.setNgayTao(LocalDate.now());
      return khachHangRepository.save(khachHang);
    } catch (NoSuchAlgorithmException e) {
      log.error("ma hoa md5 khong thanh cong");
    } catch (DataIntegrityViolationException e) {
      log.error("trung tai khoan");
    }
    return null;
  }

  /**
   * Cap nhat thong tin khach hang.
   *
   * @param khachHang thong tin khach hang
   * @return cap nhat thanh cong hay khong
   * @author AnhTuan
   * @since 10/05/2023
   */
  @Override
  public synchronized boolean updateKhachHang(KhachHang khachHang) {
    Optional<KhachHang> optionalKhachHang = khachHangRepository
        .findById(khachHang.getIdKhachHang());
    if (optionalKhachHang.isEmpty()) {
      return false;
    }

    KhachHang oldKhachHang = optionalKhachHang.get();
    oldKhachHang.setTen(khachHang.getTen());
    oldKhachHang.setEmail(khachHang.getEmail());
    oldKhachHang.setSoDienThoai(khachHang.getSoDienThoai());
    oldKhachHang.setDiaChi(khachHang.getDiaChi());
    khachHangRepository.save(oldKhachHang);
    return true;
  }

  /**
   * Xoa khach hang.
   *
   * @param id id cua khach hang
   * @author AnhTuan
   * @since 10/05/2023
   */
  @Override
  public synchronized void deleteKhachHang(Long id) {
    khachHangRepository.deleteById(id);
  }

  /**
   * Gui mail.
   *
   * @param id   id cua khach hang
   * @param href url khi click trong mail
   * @author AnhTuan
   * @since 10/05/2023
   */
  @Override
  public void sendMail(Long id, String href) {
    try {
      KhachHang khachHang = khachHangRepository.findById(id).orElseThrow();
      Path path = Path.of(MyProperties.PATH_FILE_MAIL_CHANGE_PASSWORD);
      String body = String.join("\n", Files.readString(path)).replace("{href}", href);

      Email email = new Email();
      email.sendHtmlMail(MyProperties.FROM_EMAIL, khachHang.getEmail(), MyProperties.SUBJECT, body);
    } catch (IOException | MessagingException e) {
      e.printStackTrace();
    }
  }

  /**
   * Kiem tra dang nhap.
   *
   * @param taiKhoan tai khoan
   * @param matKhau  mat khau
   * @return thong tin khach hang
   * @author AnhTuan
   * @since 10/05/2023
   */
  @Override
  public KhachHang checkLogging(String taiKhoan, String matKhau) {
    try {
      matKhau = MaHoa.encryptByMD5(matKhau);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return khachHangRepository.findByTaiKhoanAndMatKhau(taiKhoan, matKhau);
  }

  /**
   * Kiem tra su ton tai cua tai khoan.
   *
   * @param taiKhoan tai khoan
   * @return tai khoan co ton tai khong
   * @author AnhTuan
   * @since 10/05/2023
   */
  @Override
  public boolean checkTaiKhoan(String taiKhoan) {
    return khachHangRepository.findByTaiKhoan(taiKhoan).isPresent();
  }

  /**
   * Tim kiem khach hang theo tai khoan.
   *
   * @param taiKhoan tai khoan
   * @return khach hang
   * @author AnhTuan
   * @since 10/05/2023
   */
  @Override
  public KhachHang findByTaiKhoan(String taiKhoan) {
    return khachHangRepository.findByTaiKhoan(taiKhoan).orElse(new KhachHang());
  }

  /**
   * Cap nhat mat khau.
   *
   * @param khachHang thong tin khach hang
   * @return trang thai cap nhat
   * @author AnhTuan
   * @since 10/05/2023
   */
  @Override
  public synchronized boolean updateMatKhau(KhachHang khachHang) {
    KhachHang oldKhachHang = khachHangRepository
        .findByTaiKhoan(khachHang.getTaiKhoan())
        .orElseThrow();
    try {
      String mk = MaHoa.encryptByMD5(khachHang.getMatKhau());
      oldKhachHang.setMatKhau(mk);
      khachHangRepository.save(oldKhachHang);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
    return true;
  }

}
