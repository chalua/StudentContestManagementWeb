package com.datn.qlct.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CuocThiDTO {
    private String tenCuocThi;

    private Integer soLuongVongThi;

    private Date ngayBatDau;

    private Date ngayKetThuc;

    private Long soLuongNhom;

    private String moTa;

    private String trangThai;

    private String chuDe;

    private Integer maLoaiCuocThi;
}
