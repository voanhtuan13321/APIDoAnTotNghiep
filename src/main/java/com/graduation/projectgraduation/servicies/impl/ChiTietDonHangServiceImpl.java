package com.graduation.projectgraduation.servicies.impl;

import com.graduation.projectgraduation.entities.ChiTietDonHang;
import com.graduation.projectgraduation.entities.DonHang;
import com.graduation.projectgraduation.repositories.ChiTietDonHangRepository;
import com.graduation.projectgraduation.repositories.DonHangRepository;
import com.graduation.projectgraduation.servicies.ChiTietDonHangService;
import com.graduation.projectgraduation.util.MaHoa;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

/**
 * Service ChiTietDonHang.
 *
 * @author AnhTuan
 * @version 1.0
 * @since 25/05/2023
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class ChiTietDonHangServiceImpl implements ChiTietDonHangService {

  private final ChiTietDonHangRepository chiTietDonHangRepository;
  private final DonHangRepository donHangRepository;

  /**
   * Lay thong tin chi tiet don hang theo id don hang.
   *
   * @param idDonHang id don hang
   * @return List of ChiTietDonHang
   * @author AnhTuan
   * @since 25/05/2023
   */
  @Override
  public List<ChiTietDonHang> getAllByIdDonHang(Long idDonHang) {
    Optional<DonHang> donHangOptional = donHangRepository.findById(idDonHang);

    if (donHangOptional.isEmpty()) {
      return new ArrayList<>();
    }

    DonHang donHang = donHangOptional.get();
    return chiTietDonHangRepository.findAllByDonHang(donHang)
        .stream()
        .peek(donHangItem -> {
          String anh = donHangItem.getSach().getHinhAnh();
          try {
            donHangItem.getSach().setHinhAnh(MaHoa.convertFileToBase64(anh));
          } catch (IOException e) {
            log.error("ma hoa anh loi");
          }
        })
        .toList();
  }

  /**
   * Lay top 5 sach ban chay.
   *
   * @return List of chi tiet don hang
   * @author AnhTuan
   * @since 25/05/2023
   */
  @Override
  public List<ChiTietDonHang> getTop5() {
    List<ChiTietDonHang> chiTietDonHangsMain = new ArrayList<>();
    List<ChiTietDonHang> chiTietDonHangs = chiTietDonHangRepository.findAll()
        .stream()
        .filter(chiTietDonHang -> chiTietDonHang.getDonHang().getTrangThai().equals("da_xac_nhan"))
        .peek(chiTietDonHang -> {
          String anh = chiTietDonHang.getSach().getHinhAnh();
          try {
            chiTietDonHang.getSach().setHinhAnh(MaHoa.convertFileToBase64(anh));
          } catch (IOException e) {
            log.error("ma hoa anh loi");
          }
        })
        .toList();

    for (ChiTietDonHang chiTietDonHang : chiTietDonHangs) {
      int index = isTonTai(chiTietDonHang, chiTietDonHangsMain);
      if (index == -1) {
        // truong hop khong ton tai
        chiTietDonHangsMain.add(chiTietDonHang);
      } else {
        // truong hop ton tai
        int newSoLuong = chiTietDonHangsMain.get(index).getSoLuong() + chiTietDonHang.getSoLuong();
        chiTietDonHangsMain.get(index).setSoLuong(newSoLuong);
      }
    }

    return chiTietDonHangsMain.size() >= 8
        ? chiTietDonHangsMain.subList(0, 8) : chiTietDonHangsMain;
  }

  /**
   * Tinh tong tien cac san pham trong don hang.
   *
   * @param id cua don hang
   * @return tong so tien
   * @author AnhTuan
   * @since 25/05/2023
   */
  @Override
  public int getTongTienDonHang(Long id) {
    Optional<DonHang> donHangOptional = donHangRepository.findById(id);

    if (donHangOptional.isEmpty()) {
      return 0;
    }

    DonHang donHang = donHangOptional.get();
    return chiTietDonHangRepository.findAllByDonHang(donHang)
        .stream()
        .mapToInt(chiTiet -> chiTiet.getSoLuong() * chiTiet.getSach().getGiaSach())
        .sum();
  }

  /**
   * Kiem tra ton tai.
   *
   * @param chiTietDonHang      thong tin chi tiet don hang
   * @param chiTietDonHangsMain danh sach chi tiet don hang
   * @return chi tiet don hang co ton tai trong danh sach khong (-1 la khong)
   * @author AnhTuan
   * @since 25/05/2023
   */
  private int isTonTai(ChiTietDonHang chiTietDonHang, List<ChiTietDonHang> chiTietDonHangsMain) {
    for (int i = 0; i < chiTietDonHangsMain.size(); i++) {
      if (Objects.equals(
          chiTietDonHang.getSach().getIdSach(),
          chiTietDonHangsMain.get(i).getSach().getIdSach()
      )) {
        return i;
      }
    }
    return -1;
  }

}
