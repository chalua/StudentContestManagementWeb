package com.datn.qlct.service;

import java.util.Map;

import com.datn.qlct.dto.GiangVienDTO;
import com.datn.qlct.dto.response.GiangVienRespones;

import jakarta.servlet.http.HttpServletRequest;

public interface IGiangVienService {
    void addAndUpdateGiangVien(GiangVienDTO giangVienDTO);

    // String login(String mssv, String password) throws Exception;

    void updatePassword(String maGV, String newPassword) throws Exception;

    String login(String mssv, String password) throws Exception;

    GiangVienRespones getCurrentUser(HttpServletRequest request);
}
