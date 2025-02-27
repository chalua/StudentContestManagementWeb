package com.datn.qlct.dto.response;

import lombok.Data;

import java.util.List;

@Data
public class ThongTinKetQuaResponse {
    private String tenNhom;
    private List<String> tenThanhVien;
    private Float ketQua;
    private String giaiThuong;
    private boolean dat;
}
