package com.datn.qlct.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.TreeMap;

@Getter
@RequiredArgsConstructor
public enum LoaiNhomEnum {
    THI_DAU("Thi Đấu"),
    NGHIEN_CUU_KHOA_HOC("Nghiên Cứu Khoa Học");

    private final String loaiNhom;

    @Override
    public String toString() {
        return loaiNhom;
    }
}
