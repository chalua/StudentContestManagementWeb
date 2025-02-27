package com.datn.qlct.converter;

import com.datn.qlct.dto.CuocThiDTO;
import com.datn.qlct.dto.DeTaiDTO;
import com.datn.qlct.dto.response.CuocThiResponse;
import com.datn.qlct.dto.response.DeTaiResponse;
import com.datn.qlct.entity.CuocThiEntity;
import com.datn.qlct.entity.DangKyThamGiaEntity;
import com.datn.qlct.entity.DeTaiEntity;
import com.datn.qlct.entity.LoaiCuocThiEntity;
import com.datn.qlct.enums.LoaiCuocThiEnum;
import com.datn.qlct.enums.TrangThaiDangKiThamGiaEnum;
import com.datn.qlct.enums.TrangThaiEnum;
import com.datn.qlct.repository.CuocThiRepository;
import com.datn.qlct.repository.DangKyThamGiaRepository;
import com.datn.qlct.repository.LoaiCuocThiRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CuocThiConveter {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DangKyThamGiaRepository dangKyThamGiaRepository;

    @Autowired
    private LoaiCuocThiRepository loaiCuocThiRepository;

    public CuocThiDTO convertToDto(CuocThiEntity entity) {
        CuocThiDTO result = modelMapper.map(entity, CuocThiDTO.class);
        return result;
    }

    public CuocThiEntity convertToEntity(CuocThiDTO dto) {
        CuocThiEntity result = modelMapper.map(dto, CuocThiEntity.class);
        LoaiCuocThiEntity loaiCuocThiEntity = loaiCuocThiRepository.findById(dto.getMaLoaiCuocThi())
                .orElseThrow(() -> new NullPointerException("Loại cuộc thi không tồn tại"));
        result.setLoaiCuocThi(loaiCuocThiEntity);
        return result;
    }

    public CuocThiResponse toCuocThiResponse(CuocThiEntity cuocThiEntity) {

        List<DangKyThamGiaEntity> allThamGia = dangKyThamGiaRepository.getDsDkThamGia(cuocThiEntity.getId());

        List<DangKyThamGiaEntity> validThamGia = allThamGia.stream()
                .filter(thamGia -> thamGia.getTrangThaiDangKiThamGiaEnum() != TrangThaiDangKiThamGiaEnum.TU_CHOI)
                .collect(Collectors.toList());

        List<DangKyThamGiaEntity> daDuyetThamGia = allThamGia.stream()
                .filter(thamGia -> thamGia.getTrangThaiDangKiThamGiaEnum() == TrangThaiDangKiThamGiaEnum.DA_DUYET)
                .collect(Collectors.toList());

        CuocThiResponse cuocThiResponse = modelMapper.map(cuocThiEntity, CuocThiResponse.class);
        cuocThiResponse.setLoaiCuocThi(
                LoaiCuocThiEnum.valueOf(cuocThiEntity.getLoaiCuocThi().getTenLoaiCuocThi()).getLoaiCuocThi());
        cuocThiResponse.setMaLoaiCuocThi(cuocThiEntity.getLoaiCuocThi().getId());
        cuocThiResponse.setSoLuongNhomDangKy(Long.valueOf(validThamGia.size()));
        cuocThiResponse.setSoLuongNhomDaDuyet(Long.valueOf(daDuyetThamGia.size()));
        return cuocThiResponse;
    }

    public List<CuocThiResponse> convertToListResponse(List<CuocThiEntity> cuocThiEntities) {
        return cuocThiEntities.stream().map(this::toCuocThiResponse).toList();
    }
}
