package com.datn.qlct.dto.response;

import java.sql.Date;
import java.time.LocalDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DangKyThamGiaNCKHResponse {
    private NhomResponse nhom;
    private DeTaiResponse deTai;
    private CuocThiResponse cuocThi;
    private String trangThai;
    private LocalDate ngayThamGia;
    private LocalDate ngayDuyet;
}
