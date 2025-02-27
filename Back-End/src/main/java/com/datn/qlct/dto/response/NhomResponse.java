package com.datn.qlct.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class NhomResponse {
    private String maNhom;
    private String tenNhom;
    private Integer soLuongThanhVien;
    private List<ThanhVienNhomResponse> thanhVien;
}
