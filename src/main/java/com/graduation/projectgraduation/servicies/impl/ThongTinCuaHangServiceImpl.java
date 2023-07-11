package com.graduation.projectgraduation.servicies.impl;

import com.graduation.projectgraduation.entities.ThongTinCuaHang;
import com.graduation.projectgraduation.repositories.ThongTinCuaHangRepository;
import com.graduation.projectgraduation.servicies.ThongTinCuaHangService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ThongTinCuaHangServiceImpl implements ThongTinCuaHangService {

  private final ThongTinCuaHangRepository thongTinCuaHangRepository;

  @Override
  public List<ThongTinCuaHang> getAll() {
    return thongTinCuaHangRepository.findAll();
  }

  @Override
  public synchronized boolean insertOrUpdateThongTinCuaHang(ThongTinCuaHang thongTinCuaHang) {
    thongTinCuaHangRepository.save(thongTinCuaHang);
    return true;
  }
}
