package com.datn.qlct.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.TreeMap;

@Getter
@RequiredArgsConstructor
public enum LoaiCuocThiEnum {
    LAP_TRINH_THI_DAU ("Lập Trình Thi Đấu"),
    LAP_TRINH_UNG_DUNG ("Lập Trình Ứng Dụng"),
    NGHIEN_CUU_KHOA_HOC ("Nghiên Cứu Khoa Học");

    private final String loaiCuocThi;

    public static Map<String,String> type(){
        Map<String,String> listType = new TreeMap<>();
        for(LoaiCuocThiEnum item : LoaiCuocThiEnum.values()){
            listType.put(item.toString() , item.getLoaiCuocThi());
        }
        return listType;
    }
}
