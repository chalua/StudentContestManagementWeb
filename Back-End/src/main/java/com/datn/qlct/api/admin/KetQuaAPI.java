package com.datn.qlct.api.admin;

import com.datn.qlct.dto.GiangVienDTO;
import com.datn.qlct.dto.KetQuaDTO;
import com.datn.qlct.dto.response.KetQuaResponse;
import com.datn.qlct.dto.response.ThongTinKetQuaResponse;
import com.datn.qlct.dto.response.VongThiResponse;
import com.datn.qlct.service.Impl.KetQuaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/ketqua")
public class KetQuaAPI {
    @Autowired
    private KetQuaService ketQuaService;

    @GetMapping("/thong-ke/{cuocThiId}")
    public ResponseEntity<?> thongKeTheoCuocThi(@PathVariable(name = "cuocThiId") Long cuocThiId) {
        return ResponseEntity.ok().body(ketQuaService.getAllKetQuaByCuocThi(cuocThiId));
    }

    @GetMapping("/get-giai")
    public ResponseEntity<?> getGiai(@RequestParam(name = "maNhom") String maNhom) {
        return ResponseEntity.ok().body(ketQuaService.getGiaiByNhom(maNhom));
    }

    @PostMapping
    public ResponseEntity<?> updateKetQua(@RequestBody KetQuaDTO ketQuaDTO) {
        ketQuaService.addAndUpdateKetQua(ketQuaDTO);
        return ResponseEntity.ok("Kết quả vòng thi đã cập nhật thành công");
    }

    @GetMapping("/{id}/vongthi")
    public ResponseEntity<List<VongThiResponse>> vongThi(@PathVariable Long id) {
        return ResponseEntity.ok().body(ketQuaService.findCuocThi(id));
    }

    @GetMapping("/{vongThi}/nhomthamgia")
    public ResponseEntity<List<KetQuaResponse>> listNhomThamGia(@PathVariable String vongThi) {
        return ResponseEntity.ok().body(ketQuaService.findNhomSinhVien(vongThi));
    }

    @PostMapping("/thongtinketqua")
    public ResponseEntity<ThongTinKetQuaResponse> thongTinKetQua(@RequestBody KetQuaDTO ketQuaDTO) {
        return ResponseEntity.ok().body(ketQuaService.findThongTinKetQua(ketQuaDTO));
    }

    @GetMapping("/thongtinketqua")
    public ResponseEntity<ThongTinKetQuaResponse> getThongTinKetQua(@RequestParam(name = "maNhom") String maNhom,
            @RequestParam(name = "maVong") String maVong) {
        return ResponseEntity.ok().body(ketQuaService.findKetQua(maNhom, maVong));
    }

    @GetMapping("/all-giai-thuong")
    public ResponseEntity<?> getAllGiaiThuong() {
        return ResponseEntity.ok().body(ketQuaService.getAllGiaiThuong());
    }

    @PostMapping("/trao-giai")
    public ResponseEntity<ThongTinKetQuaResponse> traoGiai(@RequestBody KetQuaDTO ketQuaDTO) {
        ketQuaService.traoGiai(ketQuaDTO);
        return ResponseEntity.ok().body(null);
    }

    @GetMapping("/{id}/currenly")
    public ResponseEntity<List<KetQuaResponse>> vongThiHienTai(@PathVariable Long id) {
        return ResponseEntity.ok().body(ketQuaService.getVongThiCurrently(id));
    }
}
