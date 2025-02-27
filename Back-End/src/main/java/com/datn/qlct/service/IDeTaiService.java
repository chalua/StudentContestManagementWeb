package com.datn.qlct.service;

import com.datn.qlct.dto.DeTaiDTO;
import com.datn.qlct.dto.DuyetDeTaiDTO;
import com.datn.qlct.dto.response.DeTaiResponse;

import java.util.List;

public interface IDeTaiService {
    List<DeTaiResponse> findAll(String tenDeTai);

    List<DeTaiResponse> findAllDeXuatCuaGV(String maGV);

    DeTaiResponse DeXuatDeTai(DeTaiDTO deTaiDTO);

    List<DeTaiResponse> TuChoiDeTai(DuyetDeTaiDTO duyetDeTaiDTO);

    List<DeTaiResponse> duyetDeTai(DuyetDeTaiDTO duyetDeTaiDTO);
}
