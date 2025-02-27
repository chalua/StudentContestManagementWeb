package com.datn.qlct.dto.response;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GiangVienRespones {
    private String maGiangVien;
    private String tenGiangVien;
    private Date ngaySinh;
    private String soDienThoai;
    private Integer gioiTinh;
    private String queQuan;
}
