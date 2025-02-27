package com.datn.qlct.converter;

import com.datn.qlct.dto.GiangVienDTO;
import com.datn.qlct.dto.SinhVienDTO;
import com.datn.qlct.entity.GiangVienEntity;
import com.datn.qlct.entity.SinhVienEntity;
import com.datn.qlct.repository.ChuyenNganhRepository;
import com.datn.qlct.repository.LopRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class SinhVienConverter {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private LopRepository lopRepository;
    @Autowired
    private ChuyenNganhRepository chuyenNganhRepository;

    public SinhVienEntity convertToEntity(SinhVienDTO dto){
        SinhVienEntity result = modelMapper.map(dto, SinhVienEntity.class);
        result.setLop(lopRepository.findById(dto.getLop()).get());
        result.setMaChuyenNganh(chuyenNganhRepository.findByTenChuyenNganhContainingIgnoreCase(dto.getNganh()).get());
        Date date = java.sql.Date.valueOf(dto.getNgaySinh());
        result.setNgaySinh(date);
        return result;
    }
}
