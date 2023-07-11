package com.graduation.projectgraduation.servicies.impl;

import com.graduation.projectgraduation.entities.DanhMuc;
import com.graduation.projectgraduation.repositories.DanhMucRepository;
import com.graduation.projectgraduation.servicies.DanhMucService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service DanhMuc.
 *
 * @author AnhTuan
 * @version 1.0
 * @since 25/05/2023
 */
@Service
@RequiredArgsConstructor
public class DanhMucServiceImpl implements DanhMucService {

  private final DanhMucRepository danhMucRepository;

  /**
   * Lay all danh muc.
   *
   * @return list of danh muc
   * @author AnhTuan
   * @since 25/05/2023
   */
  @Override
  public List<DanhMuc> getAllDanhMuc() {
    return danhMucRepository.findAll();
  }

  /**
   * Lay danh muc theo id.
   *
   * @param id id cua danh muc
   * @return danh muc
   * @author AnhTuan
   * @since 25/05/2023
   */
  @Override
  public DanhMuc getDanhMucById(Long id) {
    return danhMucRepository.findById(id).orElse(new DanhMuc());
  }

  /**
   * Them thong tin danh muc.
   *
   * @param danhMuc thong tin danh muc
   * @return true neu them thanh cong
   * @author AnhTuan
   * @since 25/05/2023
   */
  @Override
  public synchronized boolean insertDanhMuc(DanhMuc danhMuc) {
    DanhMuc finalDanhMuc = danhMuc;
    if (!getAllDanhMuc()
        .stream()
        .filter(item -> item.getTen().equals(finalDanhMuc.getTen()))
        .toList()
        .isEmpty()
    ) {
      return false;
    }

    Long id = danhMuc.getIdDanhMuc();
    if (id != null) {
      DanhMuc oldDanhMuc = danhMucRepository.findById(id).orElse(new DanhMuc());
      oldDanhMuc.setTen(danhMuc.getTen());
      oldDanhMuc.setMoTa(danhMuc.getMoTa());
      danhMuc = oldDanhMuc;
    }
    danhMucRepository.save(danhMuc);
    return true;
  }

  /**
   * Xoa danh muc.
   *
   * @param id id cua danh muc
   * @return message
   * @author AnhTuan
   * @since 25/05/2023
   */
  @Override
  public synchronized String deleteDanhMuc(Long id) {
    try {
      danhMucRepository.deleteById(id);
      return "xoa thanh cong";
    } catch (Exception e) {
      e.printStackTrace();
      return "loi rang buoc khoa ngoai";
    }
  }
}
