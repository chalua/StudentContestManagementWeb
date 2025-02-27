package com.datn.qlct.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class CuocThiResponse {
    private Long id;

    private String tenCuocThi;

    private String moTa;

    private String trangThai;

    private String chuDe;

    private Date ngayBatDau;

    private Date ngayKetThuc;

    private Long soLuongNhom;
    private Long soLuongNhomDangKy;
    private Long soLuongNhomDaDuyet;

    private String loaiCuocThi;
    private Integer maLoaiCuocThi;

    private boolean daCoNhom;
}
