package com.datn.qlct.api.web;

import com.datn.qlct.dto.SinhVienDTO;
import com.datn.qlct.exception.DataNotFoundException;
import com.datn.qlct.service.ISinhVienService;

import jakarta.servlet.http.HttpServletRequest;
import com.datn.qlct.service.Impl.SinhVienService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticatedPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping("/api/sinhvien")
public class SinhVienAPI {
    @Autowired
    private SinhVienService sinhVienServices;
    @Autowired
    private ISinhVienService sinhVienService;

    @GetMapping("/lich-su-tham-gia-nckh/{mssv}")
    public ResponseEntity<?> getLsThamGiaNCKH(@PathVariable(name = "mssv") String mssv) throws Exception {
        return ResponseEntity.ok().body(sinhVienServices.getLichSuThamGiaNCKH(mssv));
    }

    @GetMapping("/lich-su-tham-gia/{mssv}")
    public ResponseEntity<?> getLsThamGiaCuocThi(@PathVariable(name = "mssv") String mssv) throws Exception {
        return ResponseEntity.ok().body(sinhVienServices.getLichSuThamGiaCuocThi(mssv));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody SinhVienDTO sinhVienDTO) throws Exception {

        Map<String, Object> response = sinhVienService.login(sinhVienDTO.getMaSinhVien(),
                sinhVienDTO.getPassword());
        return ResponseEntity.ok().body(response);

    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(HttpServletRequest request) {

        Map<String, Object> userData = sinhVienService.getCurrentUser(request);
        return ResponseEntity.ok(userData);

    }

    @PutMapping("/update-password")
    public ResponseEntity<String> updatePassword(
            @RequestBody Map<String, String> request) {
        try {
            String newPassword = request.get("newPassword");
            String mssv = request.get("maSinhVien");
            sinhVienService.updatePassword(mssv, newPassword);
            return ResponseEntity.ok("Cập nhật mật khẩu thành công");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Cập nhật mật khẩu thất bại: " + e.getMessage());
        }
    }

    @PostMapping("/upload-danhsach")
    public ResponseEntity<?> uploadSinhVienData(@RequestParam("file") MultipartFile file) {
        sinhVienServices.saveSinhVien(file);
        return ResponseEntity.ok(Map.of("Message", "upload dữ liệu danh sinh viên thành công"));
    }
}
