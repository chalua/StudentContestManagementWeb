package com.datn.qlct.converter;

import com.datn.qlct.dto.GiangVienDTO;
import com.datn.qlct.dto.response.DeTaiResponse;
import com.datn.qlct.entity.DeTaiEntity;
import com.datn.qlct.entity.GiangVienEntity;
import com.datn.qlct.repository.GiangVienRepository;
import com.datn.qlct.repository.HocHamRepository;
import com.datn.qlct.repository.HocViRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class GiangVienConverter {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private HocHamRepository hocHamRepository;
    @Autowired
    private HocViRepository hocViRepository;

    public GiangVienDTO convertToDto(GiangVienEntity entity){
        GiangVienDTO result = modelMapper.map(entity, GiangVienDTO.class);
        return result;
    }

    public GiangVienEntity convertToEntity(GiangVienDTO dto){
        GiangVienEntity result = modelMapper.map(dto, GiangVienEntity.class);
        if(!dto.getHocHam().equals("")){
            result.setMaHocHam(hocHamRepository.findByTenHocHamEqualsIgnoreCase(dto.getHocHam()).get());
        }
        else{
            result.setMaHocHam(null);
        }
        if(!dto.getHocVi().equals("")){
            result.setMaHocVi(hocViRepository.findByTenHocViEqualsIgnoreCase(dto.getHocVi()).get());
        }
        else{
            result.setMaHocVi(null);
        }
        Date date = java.sql.Date.valueOf(dto.getNgaySinh());
        result.setNgaySinh(date);
        return result;
    }

    public GiangVienDTO toDeTaiResponse(GiangVienEntity giangVienEntity){
        GiangVienDTO giangVienDTO = modelMapper.map(giangVienEntity, GiangVienDTO.class);
        String hocHam = giangVienEntity.getMaHocHam().getTenHocHam();
        String hocVi = giangVienEntity.getMaHocVi().getTenHocVi();
        giangVienDTO.setHocHam(hocHam);
        giangVienDTO.setHocVi(hocVi);
        return giangVienDTO;
    }
}
