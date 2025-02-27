package com.datn.qlct.service;

import com.datn.qlct.dto.ChapNhanLoiMoiDTO;
import com.datn.qlct.dto.TaoNhomDTO;
import com.datn.qlct.dto.ThanhVienNhomDTO;
import com.datn.qlct.dto.response.LoiMoiThamNhomResponse;
import com.datn.qlct.dto.response.NhomResponse;

import java.util.List;

public interface INhomService {
    List<NhomResponse> findAllNhomByMaSV(String maSinhVien);
    LoiMoiThamNhomResponse moiThanhVien(ThanhVienNhomDTO thanhVienNhomDTO);
    NhomResponse chapNhanLoiMoi(ThanhVienNhomDTO thanhVienNhomDTO);
//    void deleteThanhVien(Long[] id);
    NhomResponse taoNhom(TaoNhomDTO thanhVienNhomDTO);
//    void duyetNhom(String maNhom);
}
