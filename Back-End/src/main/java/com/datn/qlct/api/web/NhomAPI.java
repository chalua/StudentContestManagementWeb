package com.datn.qlct.api.web;

import com.datn.qlct.dto.ChapNhanLoiMoiDTO;
import com.datn.qlct.dto.GiaiTanNhomDTO;
import com.datn.qlct.dto.TaoNhomDTO;
import com.datn.qlct.dto.ThanhVienNhomDTO;
import com.datn.qlct.dto.response.DangKyThamGiaResponse;
import com.datn.qlct.dto.response.LoiMoiThamNhomResponse;
import com.datn.qlct.dto.response.NhomResponse;
import com.datn.qlct.dto.response.ThanhVienNhomResponse;
import com.datn.qlct.service.Impl.NhomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/nhom")
public class NhomAPI {
    @Autowired
    private NhomService nhomService;

    @GetMapping("/get-nhom/{maNhom}")
    public ResponseEntity<NhomResponse> findNhom(@PathVariable(name = "maNhom") String maNhom) {
        return ResponseEntity.ok().body(nhomService.findNhom(maNhom));
    }

    @GetMapping
    public ResponseEntity<List<NhomResponse>> findNhomByMaSV(@RequestParam(value = "masinhvien") String maSinhVien) {
        return ResponseEntity.ok().body(nhomService.findAllNhomByMaSV(maSinhVien));
    }

    @GetMapping("/cuoc-thi")
    public ResponseEntity<List<DangKyThamGiaResponse>> findCuocThiByNhom(
            @RequestParam(value = "manhom") String maNhom) {
        return ResponseEntity.ok().body(nhomService.findCuocThiByNhom(maNhom));
    }

    @GetMapping("/hien-tai")
    public ResponseEntity<NhomResponse> findNhomHienTaiByMaSV(
            @RequestParam(value = "masinhvien") String maSinhVien) {
        return ResponseEntity.ok().body(nhomService.findNhomHienTaiByMaSV(maSinhVien));
    }

    @GetMapping("/all")
    public ResponseEntity<List<NhomResponse>> findAllNhom() {
        return ResponseEntity.ok().body(nhomService.findAllNhom());
    }

    @GetMapping("/xem-loi-moi")
    public ResponseEntity<List<LoiMoiThamNhomResponse>> findAllLoiMoi(@RequestParam(name = "mssv") String mssv) {
        return ResponseEntity.ok().body(nhomService.findAllLoiMoiNhomCuaToi(mssv));
    }

    @PostMapping("/thanhvien")
    public ResponseEntity<LoiMoiThamNhomResponse> moiThanhVien(@RequestBody ThanhVienNhomDTO thanhVienNhomDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(nhomService.moiThanhVien(thanhVienNhomDTO));
    }

    @PostMapping("/chapnhanloimoi")
    public ResponseEntity<NhomResponse> chapNhanLoiMoi(@RequestBody ThanhVienNhomDTO chapNhanLoiMoiDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(nhomService.chapNhanLoiMoi(chapNhanLoiMoiDTO));
    }

    @PostMapping("/tuchoiloimoi")
    public ResponseEntity<NhomResponse> tuChoiLoiMoi(@RequestBody ThanhVienNhomDTO tuChoiDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(nhomService.tuChoiLoiMoi(tuChoiDTO));
    }

    @PostMapping("/giai-tan")
    public ResponseEntity<NhomResponse> giaiTan(@RequestBody GiaiTanNhomDTO giaiTanNhomDTO) {
        nhomService.giaiTanNhom(giaiTanNhomDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    // @DeleteMapping("/duyetnhom")
    // public void duyetNhom(@RequestBody String maNhom){
    // NhomService.duyetNhom(maNhom);
    // }
    //
    // @DeleteMapping
    // public void deleteThanhVienNhom(@RequestBody Long[] id){
    // NhomService.deleteThanhVien(id);
    // }
    //
    @PostMapping("/taonhom")
    public ResponseEntity<NhomResponse> createNhom(@RequestBody TaoNhomDTO nhomSinhVienDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(nhomService.taoNhom(nhomSinhVienDTO));
    }
}
