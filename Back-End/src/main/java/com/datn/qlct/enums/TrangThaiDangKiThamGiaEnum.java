package com.datn.qlct.enums;

public enum TrangThaiDangKiThamGiaEnum {
    DANG_CHO_DUYET("Đang Chờ Duyệt"),
    DA_DUYET("Đã Duyệt"),
    TU_CHOI("Từ Chối");

    private final String tenTrangThai;

    TrangThaiDangKiThamGiaEnum(String tenTrangThai) {
        this.tenTrangThai = tenTrangThai;
    }

    public String getTenTrangThai() {
        return tenTrangThai;
    }
}
