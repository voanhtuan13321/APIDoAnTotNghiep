package com.graduation.projectgraduation.servicies.impl;

import com.graduation.projectgraduation.entities.QuanLy;
import com.graduation.projectgraduation.model.LoggingModel;
import com.graduation.projectgraduation.repositories.QuanLyRepository;
import com.graduation.projectgraduation.servicies.QuanLyService;
import com.graduation.projectgraduation.util.Email;
import com.graduation.projectgraduation.util.MaHoa;
import com.graduation.projectgraduation.util.MyProperties;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * Service QuanLy.
 *
 * @author AnhTuan
 * @version 1.0
 * @since 11/05/2023
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class QuanLyServiceImpl implements QuanLyService {

  private final QuanLyRepository quanLyRepository;

  /**
   * Lay thong tin quan ly theo id.
   *
   * @param id id cua quan ly
   * @return quan ly
   * @author AnhTuan
   * @since 11/05/2023
   */
  @Override
  public QuanLy findById(Long id) {
    return quanLyRepository.findById(id).orElseThrow();
  }

  /**
   * Cap nhat thong tin quan ly.
   *
   * @param quanLy thong tin quan ly
   * @param isMD5  check xem có cap nhat mat khau khong
   * @author AnhTuan
   * @since 11/05/2023
   */
  @Override
  public synchronized void updateQuanLy(QuanLy quanLy, final boolean isMD5) {
    try {
      if (isMD5) {
        String matKhau = MaHoa.encryptByMD5(quanLy.getMatKhau());
        quanLy.setMatKhau(matKhau);
      }
    } catch (NoSuchAlgorithmException e) {
      log.error("ma hoa md5 that bai");
      e.printStackTrace();
    }
    quanLyRepository.findById(quanLy.getIdQuanLy()).ifPresent(oldQuanLy -> {
      oldQuanLy.setTen(quanLy.getTen());
      oldQuanLy.setEmail(quanLy.getEmail());
      oldQuanLy.setSoDienThoai(quanLy.getSoDienThoai());
      oldQuanLy.setMatKhau(quanLy.getMatKhau());
      quanLyRepository.save(oldQuanLy);
    });
  }

  /**
   * Gui mail.
   *
   * @param id   id cua quan ly
   * @param href url khi click trong mail
   * @author AnhTuan
   * @since 11/05/2023
   */
  @Override
  public void sendMail(final Long id, final String href) {
    QuanLy quanLy = quanLyRepository.findById(id).orElseThrow();
    Path path = Path.of(MyProperties.PATH_FILE_MAIL_CHANGE_PASSWORD);
    try {
      String body = String.join("\n", Files.readAllLines(path)).replace("{href}", href);
      Email email = new Email();
      email.sendHtmlMail(MyProperties.FROM_EMAIL, quanLy.getEmail(), MyProperties.SUBJECT, body);
    } catch (IOException | MessagingException e) {
      e.printStackTrace();
    }
  }

  /**
   * Them quan ly.
   *
   * @param quanLy thong tin quan ly
   * @author AnhTuan
   * @since 11/05/2023
   */
  @Override
  public synchronized void insertQuanLy(QuanLy quanLy) {
    try {
      String matKhau = MaHoa.encryptByMD5(quanLy.getMatKhau());
      quanLy.setMatKhau(matKhau);
      quanLyRepository.save(quanLy);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
    }
  }

  /**
   * Kiem tra dang nhap.
   *
   * @param loggingModel thong tin dang nhap
   * @return quanLy
   * @author AnhTuan
   * @since 11/05/2023
   */
  @Override
  public QuanLy isLogging(LoggingModel loggingModel) {
    try {
      loggingModel.setMatKhau(MaHoa.encryptByMD5(loggingModel.getMatKhau()));
      return quanLyRepository.findByTaiKhoanAndMatKhau(
              loggingModel.getTaiKhoan(),
              loggingModel.getMatKhau()
          )
          .orElse(null);
    } catch (NoSuchAlgorithmException e) {
      log.error("loi ma hoa");
      return null;
    }
  }

  /**
   * Lay all quan ly.
   *
   * @return list of quan ly
   * @author AnhTuan
   * @since 11/05/2023
   */
  @Override
  public List<QuanLy> getAll() {
    return quanLyRepository.findAll();
  }

  /**
   * Kiem tra tai khoan co ton tai.
   *
   * @param taiKhoan tai khoan
   * @return tai khoan co ton tai
   * @author AnhTuan
   * @since 11/05/2023
   */
  @Override
  public boolean isTaiKhoan(String taiKhoan) {
    return quanLyRepository.findByTaiKhoan(taiKhoan).isPresent();
  }
}
