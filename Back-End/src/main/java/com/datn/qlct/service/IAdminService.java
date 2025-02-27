package com.datn.qlct.service;

import jakarta.servlet.http.HttpServletRequest;

import java.util.Map;

public interface IAdminService {
    String login(String user, String password) throws Exception;

    Map<String, Object> getCurrentUser(HttpServletRequest request);

    void updatePassword(String username, String newPassword) throws Exception;
}
