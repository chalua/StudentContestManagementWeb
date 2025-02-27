package com.datn.qlct.service;

import java.util.Map;

import jakarta.servlet.http.HttpServletRequest;

public interface ISinhVienService {
    // String login(String mssv, String password) throws Exception;
    Map<String, Object> login(String mssv, String password) throws Exception;

    Map<String, Object> getCurrentUser(HttpServletRequest request);

    void updatePassword(String mssv, String newPassword) throws Exception;
}
