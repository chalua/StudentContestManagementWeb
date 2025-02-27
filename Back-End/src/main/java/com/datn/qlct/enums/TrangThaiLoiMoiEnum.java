package com.datn.qlct.enums;

public enum TrangThaiLoiMoiEnum {
    DA_MOI("Đã Mời"),
    DA_DONG_Y("Đã Đồng Ý"),
    DA_TU_CHOI("Đã Từ Chối");

    private final String trangThaiLoiMoi;

    TrangThaiLoiMoiEnum(String trangThaiLoiMoi) {
        this.trangThaiLoiMoi = trangThaiLoiMoi;
    }

    public String getTrangThaiLoiMoi() {
        return trangThaiLoiMoi;
    }
}
