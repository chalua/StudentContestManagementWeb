package com.datn.qlct.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Embeddable
public class DangKiThamGiaCompositeID {
    @ManyToOne
    @JoinColumn(name = "macuocthi")
    private CuocThiEntity maCuocThi;

    @ManyToOne
    @JoinColumn(name = "manhomsinhvien")
    private  NhomSinhVienEntity maNhomSinhVien;
}
