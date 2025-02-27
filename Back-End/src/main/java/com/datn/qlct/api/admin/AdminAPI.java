package com.datn.qlct.api.admin;

import com.datn.qlct.dto.AdminDTO;
import com.datn.qlct.dto.SinhVienDTO;
import com.datn.qlct.exception.DataNotFoundException;
import com.datn.qlct.service.IAdminService;
import com.datn.qlct.service.ISinhVienService;
import com.datn.qlct.service.Impl.SinhVienService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping(value = "/api/admin")
public class AdminAPI {
    @Autowired
    private IAdminService adminService;

    @PutMapping("/update-password")
    public ResponseEntity<String> updatePassword(
            @RequestBody Map<String, String> request) {
        try {
            String newPassword = request.get("newPassword");
            String mssv = request.get("username");
            adminService.updatePassword(mssv, newPassword);
            return ResponseEntity.ok("Cập nhật mật khẩu thành công");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Cập nhật mật khẩu thất bại: " + e.getMessage());
        }
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(HttpServletRequest request) {

        Map<String, Object> userData = adminService.getCurrentUser(request);
        return ResponseEntity.ok(userData);

    }
}
