package com.datn.qlct.enums;

public enum TrangThaiDuyetDeTaiEnum {
    DANG_CHO_DUYET("Đang Chờ Duyệt"),
    DA_DUYET("Đã Duyệt"),
    TU_CHOI("Từ Chối");

    private final String tenTrangThai;

    TrangThaiDuyetDeTaiEnum(String tenTrangThai) {
        this.tenTrangThai = tenTrangThai;
    }

    public String getTenTrangThai() {
        return tenTrangThai;
    }
}
