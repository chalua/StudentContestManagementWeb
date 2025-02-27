package com.datn.qlct.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Embeddable
@Data
public class LoiMoiThamGiaNhomCompositeID {
    @NotNull
    @ManyToOne(cascade = CascadeType.PERSIST, optional = false)
    @JoinColumn(nullable = false, name = "ma_nhom")
    private NhomSinhVienEntity nhomSinhVienEntity;

    @ManyToOne(cascade = CascadeType.PERSIST, optional = false)
    @JoinColumn(name = "ma_sinh_vien", nullable = false)
    private SinhVienEntity sinhVienEntity;
}
