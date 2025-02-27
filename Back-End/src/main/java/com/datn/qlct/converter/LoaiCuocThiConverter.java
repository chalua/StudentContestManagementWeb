package com.datn.qlct.converter;

import com.datn.qlct.dto.response.LoaiCuocThiResponse;
import com.datn.qlct.entity.LoaiCuocThiEntity;
import com.datn.qlct.enums.LoaiCuocThiEnum;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LoaiCuocThiConverter {

    @Autowired
    private ModelMapper modelMapper;

    public LoaiCuocThiResponse convertToDto (LoaiCuocThiEntity entity){
        LoaiCuocThiResponse result = modelMapper.map(entity, LoaiCuocThiResponse.class);
        result.setTenLoaiCuocThi(LoaiCuocThiEnum.valueOf(entity.getTenLoaiCuocThi()).getLoaiCuocThi());
        return result;
    }

    public List<LoaiCuocThiResponse> convertToListResponse (List<LoaiCuocThiEntity> entities){
        return entities.stream().map(this::convertToDto).collect(Collectors.toList());
    }
}
