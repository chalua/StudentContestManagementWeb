package com.datn.qlct.api.web;

import com.datn.qlct.dto.ChangePasswordDTO;
import com.datn.qlct.dto.SinhVienDTO;
import com.datn.qlct.exception.DataNotFoundException;
import com.datn.qlct.service.IAdminService;
import com.datn.qlct.service.IGiangVienService;
import com.datn.qlct.service.ISinhVienService;
import com.datn.qlct.service.Impl.AdminService;
import com.datn.qlct.service.Impl.GiangVienService;
import com.datn.qlct.service.Impl.SinhVienService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/api/login")
public class LoginAPI {
    @Autowired
    private ISinhVienService sinhVienService;
    @Autowired
    private IAdminService adminService;
    @Autowired
    private IGiangVienService giangVienService;

    @Autowired
    private GiangVienService giangVienService2;

    @Autowired
    private AdminService adminService2;

    @Autowired
    private SinhVienService sinhVienService2;

    @PostMapping("/{username}/change-password/admin")
    public ResponseEntity<?> changePasswordAdmin(
            @PathVariable String username,
            @RequestBody ChangePasswordDTO changePasswordDTO) {
        adminService2.changePassword(username, changePasswordDTO);
        return ResponseEntity.ok("Đổi mật khẩu thành công");

    }

    @PostMapping("/{maGV}/change-password/gv")
    public ResponseEntity<?> changePassword(
            @PathVariable String maGV,
            @RequestBody ChangePasswordDTO changePasswordDTO) {
        giangVienService2.changePassword(maGV, changePasswordDTO);
        return ResponseEntity.ok("Đổi mật khẩu thành công");

    }

    @PostMapping("/{mssv}/change-password/sv")
    public ResponseEntity<?> changePasswordSv(
            @PathVariable String mssv,
            @RequestBody ChangePasswordDTO changePasswordDTO) {
        sinhVienService2.changePassword(mssv, changePasswordDTO);
        return ResponseEntity.ok("Đổi mật khẩu thành công");

    }

    @PostMapping
    public ResponseEntity<?> login(@Valid @RequestBody Map<String, String> account) {
        try {
            if (account.get("username").length() == 10) {
                return ResponseEntity.ok()
                        .body(Map.of("token", sinhVienService.login(account.get("username"), account.get("password"))));
            } else if (account.get("username").length() == 7) {
                return ResponseEntity.ok().body(Map.of("token",
                        giangVienService.login(account.get("username"), account.get("password"))));
            } else {
                return ResponseEntity.ok()
                        .body(Map.of("token", adminService.login(account.get("username"), account.get("password"))));
            }
        } catch (DataNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
