package com.datn.qlct.api.admin;

import com.datn.qlct.dto.CapNhatTrangThaiDTO;
import com.datn.qlct.dto.DuyetNhomCuocThiDTO;
import com.datn.qlct.dto.GiangVienDTO;
import com.datn.qlct.dto.TuChoiNhomCuocThiDTO;
import com.datn.qlct.dto.response.CuocThiResponse;
import com.datn.qlct.dto.response.DangKyThamGiaNhomResponse;
import com.datn.qlct.dto.response.GiangVienRespones;
import com.datn.qlct.entity.DangKyThamGiaEntity;
import com.datn.qlct.entity.GiangVienEntity;
import com.datn.qlct.service.Impl.CuocThiService;
import com.datn.qlct.service.Impl.GiangVienService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

@RestController
@RequestMapping(value = "/api/giangvien")
public class GiangVienAPI {
    @Autowired
    private GiangVienService giangVienService;

    @Autowired
    private CuocThiService cuocThiService;

    @GetMapping()
    public List<GiangVienRespones> getAllGV() {
        return giangVienService.getAllGV();
    }

    @GetMapping("/{maGiangVien}/cuocthi")
    public List<CuocThiResponse> getCuocThiByGiangVien(@PathVariable String maGiangVien) {
        return giangVienService.getCuocThiByGiangVien(maGiangVien);
    }

    @GetMapping("/nhom/{maCuocThi}")
    public ResponseEntity<List<DangKyThamGiaNhomResponse>> getDsDkThamGia(
            @PathVariable Long maCuocThi) {

        List<DangKyThamGiaNhomResponse> danhSachDangKy = giangVienService.getDsDkThamGia(maCuocThi);

        return ResponseEntity.ok(danhSachDangKy);
    }

    @PostMapping
    public void addAndUpdateGiangVien(@RequestBody GiangVienDTO newGiangVien) {
        giangVienService.addAndUpdateGiangVien(newGiangVien);
    }

    @PutMapping("/duyet-nhom")
    public ResponseEntity<?> duyetNhom(@RequestBody DuyetNhomCuocThiDTO dto) {
        cuocThiService.duyetNhomThamGia(dto);
        return ResponseEntity.ok().body(null);
    }

    @PutMapping("/tu-choi-nhom")
    public ResponseEntity<?> tuChoiNhom(@RequestBody TuChoiNhomCuocThiDTO dto) {
        cuocThiService.tuChoiNhomThamGia(dto);
        return ResponseEntity.ok().body(null);
    }

    @PutMapping("/update-password")
    public ResponseEntity<String> updatePassword(
            @RequestBody Map<String, String> request) {
        try {
            String newPassword = request.get("newPassword");
            String mssv = request.get("username");
            giangVienService.updatePassword(mssv, newPassword);
            return ResponseEntity.ok("Cập nhật mật khẩu thành công");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Cập nhật mật khẩu thất bại: " + e.getMessage());
        }
    }

    @PostMapping("/upload-danhsach")
    public ResponseEntity<?> uploadSinhVienData(@RequestParam("file") MultipartFile file) {
        // giangVienService.saveSinhVien(file);
        giangVienService.saveGiangVien(file);
        return ResponseEntity.ok(Map.of("Message", "upload dữ liệu danh giảng viên thành công"));
    }

    @GetMapping("/me")
    public ResponseEntity<?> getCurrentUser(HttpServletRequest request) {

        GiangVienRespones userData = giangVienService.getCurrentUser(request);
        return ResponseEntity.ok(userData);

    }
}
