package com.datn.qlct.service;

import com.datn.qlct.dto.GiangVienDTO;
import com.datn.qlct.dto.KetQuaDTO;
import com.datn.qlct.dto.response.DeTaiResponse;
import com.datn.qlct.dto.response.KetQuaResponse;
import com.datn.qlct.dto.response.ThongTinKetQuaResponse;
import com.datn.qlct.dto.response.VongThiResponse;

import java.util.List;

public interface IKetQuaService {
    void addAndUpdateKetQua(KetQuaDTO ketQuaDTO);
    List<VongThiResponse> findCuocThi(Long idCuocThi);
    List<KetQuaResponse> findNhomSinhVien(String idVongThi);
    ThongTinKetQuaResponse findThongTinKetQua(KetQuaDTO ketQuaDTO);

    List<KetQuaResponse> getVongThiCurrently(Long idCuocThi);
}
