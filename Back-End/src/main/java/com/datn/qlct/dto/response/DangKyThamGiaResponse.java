package com.datn.qlct.dto.response;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DangKyThamGiaResponse {
    private NhomResponse nhom;
    private CuocThiResponse cuocThi;
    private String trangThai;
    private LocalDate ngayThamGia;
}
