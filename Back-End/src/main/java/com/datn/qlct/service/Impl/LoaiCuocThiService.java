package com.datn.qlct.service.Impl;

import com.datn.qlct.converter.LoaiCuocThiConverter;
import com.datn.qlct.dto.response.LoaiCuocThiResponse;
import com.datn.qlct.entity.LoaiCuocThiEntity;
import com.datn.qlct.repository.LoaiCuocThiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LoaiCuocThiService {

    @Autowired
    private LoaiCuocThiRepository loaiCuocThiRepository;

    @Autowired
    private LoaiCuocThiConverter loaiCuocThiConverter;

    public List<LoaiCuocThiResponse> getAllLoaiCuocThi() {
        List<LoaiCuocThiEntity> loaiCuocThiEntities = loaiCuocThiRepository.findAll();
        List<LoaiCuocThiResponse> loaiCuocThiResponse = loaiCuocThiConverter.convertToListResponse(loaiCuocThiEntities);
        return loaiCuocThiResponse;
    }
}
