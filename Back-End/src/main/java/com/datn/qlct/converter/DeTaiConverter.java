package com.datn.qlct.converter;

import com.datn.qlct.dto.DeTaiDTO;
import com.datn.qlct.dto.GiangVienDTO;
import com.datn.qlct.dto.response.DeTaiResponse;
import com.datn.qlct.entity.DeTaiEntity;
import com.datn.qlct.entity.GiangVienEntity;
import com.datn.qlct.enums.TrangThaiDuyetDeTaiEnum;
import com.datn.qlct.repository.CuocThiRepository;
import com.datn.qlct.repository.GiangVienRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DeTaiConverter {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private GiangVienRepository giangVienRepository;

    @Autowired
    private CuocThiRepository cuocThiRepository;

    public DeTaiDTO convertToDto(DeTaiEntity entity) {
        DeTaiDTO result = modelMapper.map(entity, DeTaiDTO.class);
        return result;
    }

    public DeTaiEntity convertToEntity(DeTaiDTO dto, String maDeTai) {
        DeTaiEntity result = new DeTaiEntity();
        result.setMaDeTai(maDeTai);
        result.setTenDeTai(dto.getTenDeTai());
        result.setGiangVien(giangVienRepository.findById(dto.getMaGiangVien())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy giảng viên")));
        result.setMoTa(dto.getMoTa());
        result.setCuocThi(cuocThiRepository.findById(dto.getMaCuocThi())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đề tài")));

        result.setTrangThaiDuyetDeTaiEnum(TrangThaiDuyetDeTaiEnum.DANG_CHO_DUYET);

        return result;
    }

    public DeTaiResponse toDeTaiResponse(DeTaiEntity deTaiEntity) {
        DeTaiResponse deTaiResponse = new DeTaiResponse();
        deTaiResponse.setMaDeTai(deTaiEntity.getMaDeTai());
        deTaiResponse.setTenDeTai(deTaiEntity.getTenDeTai());
        deTaiResponse.setMaGiangVien(deTaiEntity.getGiangVien().getMaGiangVien());
        deTaiResponse.setTenGiangVien(deTaiEntity.getGiangVien().getTenGiangVien());
        deTaiResponse.setTrangThai(deTaiEntity.getTrangThaiDuyetDeTaiEnum().getTenTrangThai());
        deTaiResponse.setMoTa(deTaiEntity.getMoTa());
        deTaiResponse.setNgayDeXuat(deTaiEntity.getNgayDeXuat());
        deTaiResponse.setNgayTuChoi(deTaiEntity.getNgayTuChoi());
        deTaiResponse.setNgayDuyet(deTaiEntity.getNgayDuyet());
        deTaiResponse.setCuocThiId(deTaiEntity.getCuocThi().getId());
        deTaiResponse.setLyDoTuChoi(deTaiEntity.getLyDoTuChoi());

        return deTaiResponse;
    }
}
