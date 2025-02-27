package com.datn.qlct.dto;

import lombok.Data;

import java.util.Date;

@Data
public class VongThiDTO {
    private String maVongThi;

    private String noiDung;

    private Date ngayBatDau;

    private Date ngayKetThuc;

    private Integer trangThai;

    private Long cuocThiId;

    private Integer soThuTuVongThi;
}
