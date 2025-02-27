package com.datn.qlct.dto.response;

import com.datn.qlct.dto.GiaiThuongDTO;
import com.datn.qlct.dto.NhomSinhVienDTO;

import lombok.Data;

@Data
public class KetQuaResponse {
    private String maNhomSinhVien;
    private String maVongThi;
    private String tenNhom;
    private Float ketQua;
    private Boolean dau;
    private Integer soThuTuVongThi;

    private NhomResponse nhomResponse;
    private String tenGiaiThuong;
}
