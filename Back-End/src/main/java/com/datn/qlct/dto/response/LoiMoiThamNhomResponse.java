package com.datn.qlct.dto.response;

import java.sql.Date;
import java.time.LocalDate;

import lombok.Data;

@Data
public class LoiMoiThamNhomResponse {
    private String maNhom;
    private String tenNhom;
    private LocalDate ngayMoi;
    private String maSinhVien;
    private String trangThai;
}
