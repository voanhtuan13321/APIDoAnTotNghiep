package com.graduation.projectgraduation.servicies.impl;

import com.graduation.projectgraduation.entities.DanhMuc;
import com.graduation.projectgraduation.entities.Sach;
import com.graduation.projectgraduation.model.SachModel;
import com.graduation.projectgraduation.repositories.DanhGiaSanPhamRepository;
import com.graduation.projectgraduation.repositories.DanhMucRepository;
import com.graduation.projectgraduation.repositories.SachRepository;
import com.graduation.projectgraduation.servicies.SachService;
import com.graduation.projectgraduation.util.MaHoa;
import com.graduation.projectgraduation.util.MyConvert;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;

/**
 * Service Sach.
 *
 * @author AnhTuan
 * @version 1.0
 * @since 12/05/2023
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class SachServiceImpl implements SachService {

  private final SachRepository sachRepository;
  private final DanhMucRepository danhMucRepository;
  private final DanhGiaSanPhamRepository danhGiaSanPhamRepository;

  /**
   * Lay thong tin all sach.
   *
   * @return list of sach
   * @author AnhTuan
   * @since 12/05/2023
   */
  @Override
  public List<Sach> getAllBooks() {
    return sachRepository.findAll()
        .stream()
        .sorted(Comparator.comparing(Sach::getNgayThem).reversed())
        .peek(sach -> {
          try {
            String base64 = MaHoa.convertFileToBase64(sach.getHinhAnh());
            sach.setHinhAnh(base64);
          } catch (IOException e) {
            log.error("chuyen anh sang base64 bi loi");
            e.printStackTrace();
          }
        })
        .toList();
  }

  /**
   * Lay thong tin sach theo id.
   *
   * @param id id cua sach
   * @return thong tin sach
   * @author AnhTuan
   * @since 12/05/2023
   */
  @Override
  public Sach findById(Long id) {
    Sach sach = sachRepository.findById(id).orElseThrow();
    try {
      String base64 = MaHoa.convertFileToBase64(sach.getHinhAnh());
      sach.setHinhAnh(base64);
    } catch (IOException e) {
      log.error("chuyen anh sang base64 bi loi", e);
    }
    return sach;
  }

  /**
   * Them sach.
   *
   * @author AnhTuan
   * @since 12/05/2023
   */
  @Override
  public synchronized boolean insertOrUpdateSach(SachModel sachModel) throws IOException {
    DanhMuc danhMuc = danhMucRepository.findById(sachModel.getIdDanhMuc()).orElseThrow();

    Sach newSach = MyConvert.fromSachModelToEntity(sachModel, danhMuc);
    newSach.setNgayCapNhat(LocalDate.now());

    if (sachModel.getIdSach() != null) {
      Sach oldSach = sachRepository.findById(sachModel.getIdSach()).orElseThrow();

      if (!sachModel.getHinhAnh().equals("")) {
        MaHoa.deleteImage(oldSach.getHinhAnh());
        String fileImgName = MaHoa.convertBase64ToFile(sachModel.getHinhAnh());
        newSach.setHinhAnh(fileImgName);
        oldSach.setHinhAnh(newSach.getHinhAnh());
      }

      oldSach.setTen(newSach.getTen());
      oldSach.setTacGia(newSach.getTacGia());
      oldSach.setNhaXuatBan(newSach.getNhaXuatBan());
      oldSach.setGiaSach(newSach.getGiaSach());
      oldSach.setMoTa(newSach.getMoTa());
      oldSach.setNgayThem(newSach.getNgayThem());
      oldSach.setSoLuong(newSach.getSoLuong());
      oldSach.setDanhMuc(newSach.getDanhMuc());
      newSach = oldSach;

    } else {
      newSach.setNgayThem(LocalDate.now());
      // ma hoa hinh anh
      String fileImgName = MaHoa.convertBase64ToFile(sachModel.getHinhAnh());
      newSach.setHinhAnh(fileImgName);
    }

    sachRepository.save(newSach);
    return true;
  }

  /**
   * Xoa sach.
   *
   * @param id id cua sach
   * @author AnhTuan
   * @since 12/05/2023
   */
  @Override
  public synchronized String deleteSach(Long id) {
    String message = "";
    try {
      Sach sach = sachRepository.findById(id).orElseThrow();

      String fileName = sach.getHinhAnh();
      danhGiaSanPhamRepository.deleteBySach(sach);

      sachRepository.delete(sach);
      message = "xoa thanh cong";
      MaHoa.deleteImage(fileName);
    } catch (Exception e) {
      message = "loi rang buoc khoa ngoai";
      e.printStackTrace();
    }
    return message;
  }

  /**
   * Tim kiem sach.
   *
   * @param search tu khoa tim kiem
   * @return list of sach
   * @author AnhTuan
   * @since 12/05/2023
   */
  @Override
  public List<Sach> searchBooks(String search) {
    return sachRepository.searchBooks("%" + search + "%")
        .stream()
        .peek(item -> {
          String anh = item.getHinhAnh();
          try {
            item.setHinhAnh(MaHoa.convertFileToBase64(anh));
          } catch (IOException e) {
            e.printStackTrace();
          }
        })
        .toList();
  }

  /**
   * Lay thong tin all sach theo danh muc.
   *
   * @param id id cua danh muc
   * @return list of sach
   * @author AnhTuan
   * @since 12/05/2023
   */
  @Override
  public List<Sach> getAllBooksByDanhMuc(Long id) {
    DanhMuc danhMuc = danhMucRepository.findById(id).orElseThrow();
    List<Sach> sachList = sachRepository.findByDanhMuc(danhMuc);
    sachList.forEach(sach -> {
      try {
        sach.setHinhAnh(MaHoa.convertFileToBase64(sach.getHinhAnh()));
      } catch (IOException e) {
        log.error("chuyen anh tu file sang base64 bi loi", e);
      }
    });
    return sachList;
  }

  /**
   * Top sach moi nhat.
   *
   * @return list of sach
   * @author AnhTuan
   * @since 12/05/2023
   */
  @Override
  public List<Sach> sachMoiNhat() {
    return sachRepository.findAll()
        .stream()
        .peek(sach -> {
          String anh = sach.getHinhAnh();
          try {
            sach.setHinhAnh(MaHoa.convertFileToBase64(anh));
          } catch (IOException e) { }
        })
        .sorted(Comparator.comparing(Sach::getNgayXuatBan).reversed())
        .toList();
  }

}
