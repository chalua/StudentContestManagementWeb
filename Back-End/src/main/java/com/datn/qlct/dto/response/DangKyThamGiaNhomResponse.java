package com.datn.qlct.dto.response;

import java.time.LocalDate;

import com.datn.qlct.enums.TrangThaiDangKiThamGiaEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class DangKyThamGiaNhomResponse {
    private String maNhom;
    private String tenNhom;
    private Long maCuocThi;
    private String maDeTai;
    private TrangThaiDangKiThamGiaEnum trangThai;
    private LocalDate ngayDangKy;
    private LocalDate ngayDuyet;
    private LocalDate ngayTuChoi;

}
