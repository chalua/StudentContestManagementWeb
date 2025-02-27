package com.datn.qlct.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CuocThiGiangVienResponse {
    private Long cuocThiId;
    private String giangVienId;
    private String tenGiangVien;
    private String tenCuocThi;
}
