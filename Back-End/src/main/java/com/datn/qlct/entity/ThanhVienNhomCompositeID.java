package com.datn.qlct.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
@Embeddable
public class ThanhVienNhomCompositeID {
    @ManyToOne
    @JoinColumn(name = "masinhvien")
    private SinhVienEntity sinhVien;

    @ManyToOne
    @JoinColumn(name = "manhom")
    private NhomSinhVienEntity nhom;
}
