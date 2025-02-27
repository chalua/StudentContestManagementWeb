package com.datn.qlct.dto;

import com.datn.qlct.entity.NhomSinhVienEntity;
import lombok.Data;

@Data
public class KetQuaDTO {
    private String maNhomSinhVien;
    private Integer maGiaiThuong;
    private String maVongThi;
    private Float ketQua;
    private String dat;
}
