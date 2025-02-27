package com.datn.qlct.dto;

import com.datn.qlct.enums.LoaiNhomEnum;

import lombok.Data;

@Data
public class TaoNhomDTO {
    private String mssvNhomTruong;
    private String tenNhom;
    private LoaiNhomEnum loaiNhom;
}
