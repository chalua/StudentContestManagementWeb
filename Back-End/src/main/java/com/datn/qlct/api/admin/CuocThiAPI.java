package com.datn.qlct.api.admin;

import com.datn.qlct.dto.CuocThiDTO;
import com.datn.qlct.dto.DangKyDeTaiDTO;
import com.datn.qlct.dto.DangKyThamGiaDTO;
import com.datn.qlct.dto.VongThiDTO;
import com.datn.qlct.dto.CuocThiDeleteDTO;
import com.datn.qlct.dto.response.CuocThiGiangVienResponse;
import com.datn.qlct.dto.response.CuocThiResponse;
import com.datn.qlct.dto.response.DangKyDeTaiResponse;
import com.datn.qlct.dto.response.DangKyThamGiaResponse;
import com.datn.qlct.entity.CuocThiEntity;
import com.datn.qlct.entity.GiangVienEntity;
import com.datn.qlct.service.Impl.CuocThiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/cuocthi")
public class CuocThiAPI {
    @Autowired
    private CuocThiService cuocThiService;

    @GetMapping("/thong-ke")
    public ResponseEntity<?> thongKe() {
        return ResponseEntity.ok().body(cuocThiService.thongKeCombie());
    }

    @GetMapping("/thong-ke-nckh")
    public ResponseEntity<?> thongKeNCKH() {
        return ResponseEntity.ok().body(cuocThiService.thongKeNCKH());
    }

    @DeleteMapping("/{cuocThiId}/vong-thi/{vongThiId}")
    public ResponseEntity<?> updateVongThi(@PathVariable(name = "cuocThiId") Long cuocThiId,
            @PathVariable(name = "vongThiId") String vongThiId) {
        cuocThiService.deleteVongThi(cuocThiId, vongThiId);
        return ResponseEntity.ok().body(null);
    }

    @PutMapping("/vong-thi")
    public ResponseEntity<?> updateVongThi(@RequestBody VongThiDTO vongThiDTO) {
        VongThiDTO result = cuocThiService.updateVongThi(vongThiDTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/vong-thi/{cuocThiId}")
    public ResponseEntity<?> getVongThi(@PathVariable(name = "cuocThiId") Long cuocThiId) {
        List<VongThiDTO> result = cuocThiService.getAllVongByCuocThi(cuocThiId);
        return ResponseEntity.ok().body(result);
    }

    @PostMapping("/vong-thi")
    public ResponseEntity<?> taoVongThi(@RequestBody VongThiDTO vongThiDTO) {
        VongThiDTO result = cuocThiService.taoVongThi(vongThiDTO);
        return ResponseEntity.ok().body(result);
    }

    @GetMapping("/{cuocThiId}/giangviens")
    public ResponseEntity<List<CuocThiGiangVienResponse>> getDsGiangVienCuocThi(
            @PathVariable Long cuocThiId) {
        List<CuocThiGiangVienResponse> giangViens = cuocThiService.getDsGiangVienCuocThi(cuocThiId);
        return ResponseEntity.ok(giangViens);
    }

    @PostMapping("/{cuocThiId}/assign-giangvien/{giangVienId}")
    public ResponseEntity<?> assignGiangVienToCuocThi(
            @PathVariable(name = "cuocThiId") Long cuocThiId,
            @PathVariable(name = "giangVienId") String giangVienId) {
        CuocThiEntity updatedCuocThi = cuocThiService.assignGiangVienToCuocThi(cuocThiId, giangVienId);

        return ResponseEntity.ok().body(null);
    }

    @DeleteMapping("/{cuocThiId}/giangvien/{giangVienId}")
    public ResponseEntity<CuocThiEntity> deleteGiangVienFromCuocThi(
            @PathVariable Long cuocThiId,
            @PathVariable String giangVienId) {
        CuocThiEntity updatedCuocThi = cuocThiService.deleteGiangVienFromCuocThi(cuocThiId, giangVienId);
        return ResponseEntity.ok().body(null);
    }

    @GetMapping("/{loaiId}")
    public ResponseEntity<List<CuocThiResponse>> loadDsCuocThiTheoLoai(@PathVariable(name = "loaiId") Integer loaiId) {
        return ResponseEntity.ok().body(cuocThiService.findDsCuocThiTheoLoai(loaiId));
    }

    @GetMapping("/all")
    public ResponseEntity<List<CuocThiResponse>> loadDsCuocThi() {
        return ResponseEntity.ok().body(cuocThiService.findDsCuocThi());
    }

    @GetMapping("/all/nckh")
    public ResponseEntity<List<CuocThiResponse>> loadDsNCKH() {
        return ResponseEntity.ok().body(cuocThiService.findDsNCKH());
    }

    @GetMapping
    public ResponseEntity<List<CuocThiResponse>> loadCuocThi(@RequestParam(value = "tencuocthi") String tenCuocThi) {
        return ResponseEntity.ok().body(cuocThiService.findAllByTenCuocThi(tenCuocThi));
    }

    @PostMapping
    public ResponseEntity<CuocThiResponse> addCuocThi(@RequestBody CuocThiDTO cuocThiDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cuocThiService.addCuocThi(cuocThiDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CuocThiResponse> updateCuocThi(
            @PathVariable Long id,
            @RequestBody CuocThiDTO cuocThiDTO) {
        CuocThiResponse updatedCuocThi = cuocThiService.updateCuocThi(id, cuocThiDTO);
        return ResponseEntity.ok(updatedCuocThi);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCuocThi(@PathVariable(name = "id") Long id) {
        cuocThiService.deleteCuocThi(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/dangkythamgia")
    public ResponseEntity<DangKyThamGiaResponse> dangKyThamGia(@RequestBody DangKyThamGiaDTO dangKyThamGiaDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cuocThiService.dangKyThamGia(dangKyThamGiaDTO));
    }

    @PutMapping("/dangkydetai")
    public ResponseEntity<DangKyDeTaiResponse> dangKyDeTai(@RequestBody DangKyDeTaiDTO dangKyDeTaiDTO) {
        return ResponseEntity.ok().body(cuocThiService.dangKyDeTai(dangKyDeTaiDTO));
    }
    // @GetMapping("/nhomthamgia")
    // public List<DangKyThamGiaResponse> loadNhomThamGia(){
    // return cuocThiService.loadNhomThamGia();
    // }

    @GetMapping("/chi-tiet-cuoc-thi/{id}")
    public ResponseEntity<CuocThiResponse> getCuocThiByID(@PathVariable(name = "id") Long id) {
        return ResponseEntity.ok().body(cuocThiService.getCuocThiByID(id));
    }

}
