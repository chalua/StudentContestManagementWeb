package com.datn.qlct.dto.response;

import java.time.LocalDate;

import lombok.Data;

@Data
public class DeTaiResponse {
    private Long cuocThiId;
    private String maDeTai;
    private String tenDeTai;
    private String maGiangVien;
    private String tenGiangVien;
    private String moTa;
    private String trangThai;
    private LocalDate ngayDeXuat;
    private LocalDate ngayDuyet;
    private LocalDate ngayTuChoi;
    private Boolean daCoNhom;
    private String lyDoTuChoi;
}
