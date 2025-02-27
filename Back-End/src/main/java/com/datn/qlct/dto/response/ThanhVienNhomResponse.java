package com.datn.qlct.dto.response;

import com.datn.qlct.entity.NhomSinhVienEntity;
import com.datn.qlct.entity.SinhVienEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class ThanhVienNhomResponse {
    private String maNhom;
    private boolean nhomTruong;
    private String maSinhVien;
    private String tenSinhVien;
}
