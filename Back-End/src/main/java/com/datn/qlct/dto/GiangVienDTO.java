package com.datn.qlct.dto;

import com.datn.qlct.entity.HocHamEntity;
import com.datn.qlct.entity.HocViEntity;
import jakarta.persistence.Column;
import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class GiangVienDTO {
    private String maGiangVien;
    private String tenGiangVien;
    private LocalDate ngaySinh;
    private String soDienThoai;
    private String gioiTinh;
    private String queQuan;
    private String hocVi;
    private String hocHam;

    private String password;
}
