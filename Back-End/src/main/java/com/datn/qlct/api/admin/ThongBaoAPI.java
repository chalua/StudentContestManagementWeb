package com.datn.qlct.api.admin;

import com.datn.qlct.dto.ThongBaoPutRequest;
import com.datn.qlct.dto.ThongBaoRequest;
import com.datn.qlct.dto.response.ThongBaoResponse;
import com.datn.qlct.service.Impl.ThongBaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping(value = "/api/thongbao")
public class ThongBaoAPI {

    @Autowired
    private ThongBaoService thongBaoService;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<ThongBaoResponse> createThongBao(
            @RequestParam("tieuDe") String tieuDe,
            @RequestParam("noiDung") String noiDung,
            @RequestParam(value = "hinhAnh", required = false) MultipartFile hinhAnh,
            @RequestParam(value = "file", required = false) MultipartFile file) {

        ThongBaoRequest request = new ThongBaoRequest();
        request.setTieuDe(tieuDe);
        request.setNoiDung(noiDung);
        request.setHinhAnh(hinhAnh);
        request.setFile(file);

        return ResponseEntity.status(HttpStatus.CREATED).body(thongBaoService.createThongBao(request));
    }

    @PutMapping(consumes = "multipart/form-data")
    public ResponseEntity<ThongBaoResponse> updateThongBao(
            @RequestParam("id") Integer id,
            @RequestParam("tieuDe") String tieuDe,
            @RequestParam("noiDung") String noiDung,
            @RequestParam(value = "hinhAnh", required = false) MultipartFile hinhAnh,
            @RequestParam(value = "file", required = false) MultipartFile file) {

        ThongBaoPutRequest request = new ThongBaoPutRequest();
        request.setId(id);
        request.setTieuDe(tieuDe);
        request.setNoiDung(noiDung);
        request.setHinhAnh(hinhAnh);
        request.setFile(file);

        return ResponseEntity.ok(thongBaoService.updateThongBao(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ThongBaoResponse> getThongBao(@PathVariable Integer id) {
        return ResponseEntity.ok(thongBaoService.getThongBao(id));
    }

    @GetMapping("/all")
    public ResponseEntity<List<ThongBaoResponse>> getAllThongBao() {
        return ResponseEntity.ok(thongBaoService.getAllThongBao());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteThongBao(@PathVariable Integer id) {
        thongBaoService.deleteThongBao(id);
        return ResponseEntity.ok().build();
    }
}
