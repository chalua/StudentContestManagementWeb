package com.datn.qlct.api.web;

import com.datn.qlct.dto.DangKyThamGiaNCKHDTO;
import com.datn.qlct.dto.DeTaiDTO;
import com.datn.qlct.dto.DuyetDeTaiDTO;
import com.datn.qlct.dto.TuChoiDangKyThamGiaNCKHDTO;
import com.datn.qlct.dto.response.DeTaiResponse;
import com.datn.qlct.service.Impl.DeTaiService;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/detai")
public class DeTaiAPI {
    @Autowired
    private DeTaiService deTaiService;

    @GetMapping("/get-nhom-tham-gia/{maDeTai}")
    public ResponseEntity<?> getNhomThamGia(@PathVariable("maDeTai") String maDeTai) {
        return ResponseEntity.status(HttpStatus.OK).body(deTaiService.xemDsNhomThamGiaDeTai(maDeTai));
    }

    @PostMapping("/dang-ky")
    public ResponseEntity<?> dangKyDeTai(@RequestBody DangKyThamGiaNCKHDTO dangKyThamGiaNCKHDTO) {
        deTaiService.dangKyDeTai(dangKyThamGiaNCKHDTO);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping("/cuoc-thi/{cuocThiId}")
    public ResponseEntity<List<DeTaiResponse>> xemDsDeTai(@PathVariable("cuocThiId") Long cuocThiId) {
        return ResponseEntity.status(HttpStatus.OK).body(deTaiService.xemDsDeTai(cuocThiId));
    }

    @PostMapping("/duyet-nhom")
    public ResponseEntity<List<DeTaiResponse>> duyetNhom(@RequestBody DangKyThamGiaNCKHDTO dangKyThamGiaNCKHDTO) {
        deTaiService.duyetNhomThamGiaDeTai(dangKyThamGiaNCKHDTO);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @PostMapping("/tu-choi-nhom")
    public ResponseEntity<List<DeTaiResponse>> tuChoiNhom(
            @RequestBody TuChoiDangKyThamGiaNCKHDTO dangKyThamGiaNCKHDTO) {
        deTaiService.tuChoiNhomThamGiaDeTai(dangKyThamGiaNCKHDTO);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping
    public List<DeTaiResponse> loadDeTai(@RequestParam(value = "tendetai", required = false) String tenDeTai) {
        return deTaiService.findAll(tenDeTai);
    }

    @GetMapping("/de-xuat/all")
    public List<DeTaiResponse> loadDeXuatDeTai() {
        return deTaiService.findAllDeXuat();
    }

    @GetMapping("/de-xuat")
    public List<DeTaiResponse> loadDeXuatDeTaiCuaGV(
            @RequestParam(value = "maGV") String maGV) {
        return deTaiService.findAllDeXuatCuaGV(maGV);
    }

    @PostMapping
    public ResponseEntity<DeTaiResponse> DeXuatDeTai(@RequestBody DeTaiDTO deTaiDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(deTaiService.DeXuatDeTai(deTaiDTO));
    }

    @PutMapping("/tuchoidetai")
    public ResponseEntity<List<DeTaiResponse>> TuChoiDeTai(@RequestBody DuyetDeTaiDTO duyetDeTaiDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(deTaiService.TuChoiDeTai(duyetDeTaiDTO));
    }

    @PutMapping
    public ResponseEntity<List<DeTaiResponse>> duyetDeTai(@RequestBody DuyetDeTaiDTO duyetDeTaiDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(deTaiService.duyetDeTai(duyetDeTaiDTO));
    }

}