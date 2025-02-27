package com.datn.qlct.entity;

import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Embeddable
@Data
public class KetQuaVongThiCompositeID {
    @ManyToOne
    @JoinColumn(name= "mavongthi")
    private VongThiEntity maVongThi;

    @ManyToOne
    @JoinColumn(name = "manhomsinhvien")
    private NhomSinhVienEntity maNhomSinhVien;
}
