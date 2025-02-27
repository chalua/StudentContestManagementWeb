package com.datn.qlct.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Embeddable
public class DangKiThamGiaNCKHCompositeID {
    @ManyToOne
    @JoinColumn(name = "madetaithamgia")
    private DeTaiEntity maDeTai;

    @ManyToOne
    @JoinColumn(name = "manhomsinhvien")
    private NhomSinhVienEntity maNhomSinhVien;
}
