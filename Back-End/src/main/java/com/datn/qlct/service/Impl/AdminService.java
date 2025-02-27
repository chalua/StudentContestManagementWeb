package com.datn.qlct.service.Impl;

import com.datn.qlct.components.JwtTokenUtil;
import com.datn.qlct.converter.SinhVienConverter;
import com.datn.qlct.dto.ChangePasswordDTO;
import com.datn.qlct.entity.AdminEntity;
import com.datn.qlct.entity.GiangVienEntity;
import com.datn.qlct.entity.SinhVienEntity;
import com.datn.qlct.exception.DataNotFoundException;
import com.datn.qlct.repository.AdminRepository;
import com.datn.qlct.service.IAdminService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class AdminService implements IAdminService {
    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    private AdminRepository adminRepository;
    private final AuthenticationManager authenticationManager;

    @Override
    public String login(String usename, String password) throws Exception {
        Optional<AdminEntity> optionalUser = adminRepository.findById(usename);
        if (optionalUser.isEmpty()) {
            throw new DataNotFoundException("Tài khoản không tồn tại");
        }
        AdminEntity existingUser = optionalUser.get();
        if (!passwordEncoder.matches(password, existingUser.getPassword())) {
            throw new DataNotFoundException("Sai mật khẩu");
        }
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(usename,
                password,
                existingUser.getAuthorities());
        return jwtTokenUtil.generateTokenAdmin(optionalUser.get());// muốn trả JWT token ?
    }

    @Override
    public Map<String, Object> getCurrentUser(HttpServletRequest request) {
        // Get the Bearer token from the Authorization header
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new SecurityException("Token không hợp lệ");
        }

        String token = authorizationHeader.substring(7); // Remove "Bearer " prefix

        // Extract username (or user ID) from token
        String username = jwtTokenUtil.getUsernameAdminFromToken(token);

        // Retrieve user from database
        Optional<AdminEntity> optionalUser = adminRepository.findById(username);
        if (optionalUser.isEmpty()) {
            throw new DataNotFoundException("Người dùng không tồn tại");
        }

        AdminEntity user = optionalUser.get();

        // Convert user entity to map (excluding password)
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(user, Map.class);
    }

    @Override
    public void updatePassword(String usename, String newPassword) throws Exception {
        Optional<AdminEntity> optionalUser = adminRepository.findById(usename);
        if (optionalUser.isEmpty()) {
            throw new DataNotFoundException("Mã sinh viên không tồn tại");
        }
        AdminEntity existingUser = optionalUser.get();
        String encodedPassword = passwordEncoder.encode(newPassword);
        existingUser.setPassword(encodedPassword);
        adminRepository.save(existingUser);
    }

    @Transactional
    public void changePassword(String username, ChangePasswordDTO changePasswordDTO) {
        Optional<AdminEntity> optionalUser = adminRepository.findById(username);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("Tài khoản không tồn tại");
        }

        AdminEntity existingUser = optionalUser.get();

        if (!passwordEncoder.matches(changePasswordDTO.getOldPassword(), existingUser.getPassword())) {
            throw new RuntimeException("Mật khẩu cũ không chính xác");
        }

        if (!changePasswordDTO.getNewPassword().equals(changePasswordDTO.getConfirmPassword())) {
            throw new RuntimeException("Xác nhận mật khẩu không khớp");
        }

        String encodedPassword = passwordEncoder.encode(changePasswordDTO.getNewPassword());
        existingUser.setPassword(encodedPassword);
        adminRepository.save(existingUser);
    }
}
