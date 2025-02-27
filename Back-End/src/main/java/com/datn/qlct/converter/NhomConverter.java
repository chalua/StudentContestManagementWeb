package com.datn.qlct.converter;

import com.datn.qlct.dto.ThanhVienNhomDTO;
import com.datn.qlct.dto.response.LoiMoiThamNhomResponse;
import com.datn.qlct.dto.response.NhomResponse;
import com.datn.qlct.dto.response.ThanhVienNhomResponse;
import com.datn.qlct.entity.*;
import com.datn.qlct.enums.TrangThaiLoiMoiEnum;
import com.datn.qlct.repository.NhomSinhVienRepository;
import com.datn.qlct.repository.SinhVienRepository;
import com.datn.qlct.repository.ThanhVienNhomRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class NhomConverter {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private ThanhVienNhomRepository thanhVienNhomRepository;

    @Autowired
    private NhomSinhVienRepository nhomSinhVienRepository;

    @Autowired
    private SinhVienRepository sinhVienRepository;

    public ThanhVienNhomResponse toThanhVienNhomResponse(ThanhVienNhomEntity thanhVienNhomEntity) {
        ThanhVienNhomResponse thanhVienNhomResponse = new ThanhVienNhomResponse();
        thanhVienNhomResponse.setMaNhom(thanhVienNhomEntity.getId().getNhom().getMaNhom());
        thanhVienNhomResponse.setMaSinhVien(thanhVienNhomEntity.getId().getSinhVien().getMaSinhVien());
        thanhVienNhomResponse.setTenSinhVien(thanhVienNhomEntity.getId().getSinhVien().getHoTen());
        if (thanhVienNhomEntity.getNhomTruong() == 1) {
            thanhVienNhomResponse.setNhomTruong(true);
        } else {
            thanhVienNhomResponse.setNhomTruong(false);
        }
        return thanhVienNhomResponse;
    }

    public List<ThanhVienNhomResponse> convertToListThanhVienNhomResponse(
            List<ThanhVienNhomEntity> thanhVienNhomEntities) {
        List<ThanhVienNhomResponse> result = new ArrayList<>();
        thanhVienNhomEntities.forEach(item -> {
            ThanhVienNhomResponse thanhVienNhomResponse = toThanhVienNhomResponse(item);
            result.add(thanhVienNhomResponse);
        });
        return result;
    }

    public NhomResponse toNhomResponse(NhomSinhVienEntity nhomSinhVienEntity) {
        NhomResponse nhomResponse = new NhomResponse();
        nhomResponse.setMaNhom(nhomSinhVienEntity.getMaNhom());
        nhomResponse.setTenNhom(nhomSinhVienEntity.getTenNhom());
        nhomResponse.setSoLuongThanhVien(nhomSinhVienEntity.getSoLuongThanhVien());

        List<ThanhVienNhomEntity> thanhVienNhomEntities = thanhVienNhomRepository
                .findAllByMaNhom(nhomSinhVienEntity.getMaNhom());
        List<ThanhVienNhomResponse> thanhVienNhomResponseList = convertToListThanhVienNhomResponse(
                thanhVienNhomEntities);

        nhomResponse.setThanhVien(thanhVienNhomResponseList);

        return nhomResponse;
    }

    public List<NhomResponse> toListNhomResponse(List<ThanhVienNhomEntity> thanhVienNhomEntities) {
        List<NhomResponse> result = new ArrayList<>();
        thanhVienNhomEntities.forEach(item -> {
            NhomSinhVienEntity nhomSinhVienEntity = item.getId().getNhom();
            NhomResponse nhomResponse = toNhomResponse(nhomSinhVienEntity);
            result.add(nhomResponse);
        });
        return result;
    }

    public List<NhomResponse> toNhomResponses(List<NhomSinhVienEntity> nhomSinhVienEntities) {
        List<NhomResponse> result = new ArrayList<>();
        nhomSinhVienEntities.forEach(item -> {
            NhomResponse nhomResponse = toNhomResponse(item);
            result.add(nhomResponse);
        });
        return result;
    }

    public LoiMoiThamGiaNhomEntity toLoiMoiThamGiaNhomEntity(ThanhVienNhomDTO thanhVienNhomDTO) {
        LoiMoiThamGiaNhomEntity loiMoiThamGiaNhomEntity = new LoiMoiThamGiaNhomEntity();

        LoiMoiThamGiaNhomCompositeID id = new LoiMoiThamGiaNhomCompositeID();
        id.setNhomSinhVienEntity(nhomSinhVienRepository.findById(thanhVienNhomDTO.getMaNhom())
                .orElseThrow(() -> new NullPointerException("Không tìm thấy nhóm")));
        id.setSinhVienEntity(sinhVienRepository.findById(thanhVienNhomDTO.getMaSinhVien())
                .orElseThrow(() -> new NullPointerException("Không tìm thấy sinh viên")));

        loiMoiThamGiaNhomEntity.setId(id);
        loiMoiThamGiaNhomEntity.setTrangThaiLoiMoiEnum(TrangThaiLoiMoiEnum.DA_MOI);

        return loiMoiThamGiaNhomEntity;
    }

    public LoiMoiThamNhomResponse toLoiMoiThamNhomResponse(LoiMoiThamGiaNhomEntity loiMoiThamGiaNhomEntity) {
        LoiMoiThamNhomResponse loiMoiThamNhomResponse = new LoiMoiThamNhomResponse();
        loiMoiThamNhomResponse.setMaNhom(loiMoiThamGiaNhomEntity.getId().getNhomSinhVienEntity().getMaNhom());
        loiMoiThamNhomResponse.setTenNhom(loiMoiThamGiaNhomEntity.getId().getNhomSinhVienEntity().getTenNhom());
        loiMoiThamNhomResponse.setNgayMoi(loiMoiThamGiaNhomEntity.getNgayMoi());
        loiMoiThamNhomResponse.setMaSinhVien(loiMoiThamGiaNhomEntity.getId().getSinhVienEntity().getMaSinhVien());
        loiMoiThamNhomResponse.setTrangThai(loiMoiThamGiaNhomEntity.getTrangThaiLoiMoiEnum().getTrangThaiLoiMoi());
        return loiMoiThamNhomResponse;
    }
}
