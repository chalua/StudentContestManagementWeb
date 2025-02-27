package com.datn.qlct.converter;

import com.datn.qlct.dto.response.DeTaiResponse;
import com.datn.qlct.dto.response.VongThiResponse;
import com.datn.qlct.entity.DeTaiEntity;
import com.datn.qlct.entity.VongThiEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class VongThiConverter {
    @Autowired
    private ModelMapper modelMapper;

    public VongThiResponse toVongThiResponse(VongThiEntity vongThiEntity, int index) {
        VongThiResponse vongThiResponse = new VongThiResponse();
        vongThiResponse.setMaVongThi(vongThiEntity.getMaVongThi());
        vongThiResponse.setTenVongThi("Vòng thi " + index);
        return vongThiResponse;
    }
}
