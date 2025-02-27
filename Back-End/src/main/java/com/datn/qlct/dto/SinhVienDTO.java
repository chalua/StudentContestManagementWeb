package com.datn.qlct.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Date;

@Getter
@Setter
public class SinhVienDTO {

    private String maSinhVien;

    private String hoTen;

    private LocalDate ngaySinh;

    private String email;

    private String diaChi;

    private String queQuan;

    private String gioiTinh;

    private String soDienThoai;

    private String noiSinh;

    private String khoaHoc;

    private String bacDaoTao;

    private String loaiHinhDaoTao;

    private String nganh;

    private String password;

    private String lop;
}
