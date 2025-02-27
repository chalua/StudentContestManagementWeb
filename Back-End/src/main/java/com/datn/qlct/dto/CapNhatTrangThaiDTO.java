package com.datn.qlct.dto;

import com.datn.qlct.enums.TrangThaiDangKiThamGiaEnum;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CapNhatTrangThaiDTO {
    private String maGiangVien;

    private Long maCuocThi;

    private String maNhom;

    private TrangThaiDangKiThamGiaEnum trangThai;
}
