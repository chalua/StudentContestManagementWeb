package com.datn.qlct.enums;

import java.util.Map;
import java.util.TreeMap;

public enum TrangThaiEnum {
    AN ("Ẩn"),
    DANG_DIEN_RA ("Đang Diễn Ra"),
    KET_THUC ("Kết Thúc"),
    CHUA_DIEN_RA("Chưa Diễn Ra");

    private final String tenTrangThai;
    TrangThaiEnum(String tenTrangThai) {
        this.tenTrangThai = tenTrangThai;
    }

    public String getTenTrangThai() {
        return tenTrangThai;
    }

    public static Map<String,String> type(){
        Map<String,String> listType = new TreeMap<>();
        for(TrangThaiEnum item : TrangThaiEnum.values()){
            listType.put(item.toString() , item.tenTrangThai);
        }
        return listType;
    }
}
