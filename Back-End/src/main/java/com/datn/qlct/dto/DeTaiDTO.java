package com.datn.qlct.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class DeTaiDTO {
    private String tenDeTai;
    private String maGiangVien;
    private String moTa;
    private Long maCuocThi;
    private String lyDoTuChoi;
    private LocalDate ngayDeXuat;
    private LocalDate ngayDuyet;
    private LocalDate ngayTuChoi;
}
