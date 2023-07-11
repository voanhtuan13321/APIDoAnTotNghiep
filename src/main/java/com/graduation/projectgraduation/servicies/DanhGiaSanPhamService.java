package com.graduation.projectgraduation.servicies;

import com.graduation.projectgraduation.entities.DanhGiaSanPham;
import com.graduation.projectgraduation.entities.Sach;
import com.graduation.projectgraduation.model.DanhGiaSanPhamModel;

import java.util.List;

/**
 * Service danh gia san pham.
 *
 * @author AnhTuan
 * @date 25/05/2023
 */
public interface DanhGiaSanPhamService {
    List<DanhGiaSanPham> getAllDanhSachSanPhamByIdSach(Long id);

    void insertBinhLuan(DanhGiaSanPhamModel danhGiaSanPhamModel);

    void deleteBinhLuan(Long id);

    List<Sach> getAllSachCoBinhLuan();
}
