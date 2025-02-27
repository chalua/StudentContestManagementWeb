package com.datn.qlct.converter;

import com.datn.qlct.dto.DangKyThamGiaDTO;
import com.datn.qlct.dto.response.DangKyDeTaiResponse;
import com.datn.qlct.dto.response.DangKyThamGiaNCKHResponse;
import com.datn.qlct.dto.response.DangKyThamGiaResponse;
import com.datn.qlct.entity.*;
import com.datn.qlct.enums.TrangThaiDangKiThamGiaEnum;
import com.datn.qlct.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DangKyThamGiaNCKHConverter {
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private CuocThiRepository cuocThiRepository;

    @Autowired
    private SinhVienRepository sinhVienRepository;

    @Autowired
    private ThanhVienNhomRepository thanhVienNhomRepository;

    @Autowired
    private NhomSinhVienRepository nhomSinhVienRepository;

    @Autowired
    private DeTaisRepository deTaisRepository;

    @Autowired
    private CuocThiConveter cuocThiConveter;

    @Autowired
    private DeTaiConverter deTaiConverter;

    @Autowired
    private NhomConverter nhomConverter;

    public DangKyThamGiaDTO convertToDto(DangKyThamGiaEntity entity) {
        DangKyThamGiaDTO result = modelMapper.map(entity, DangKyThamGiaDTO.class);
        return result;
    }

    public DangKyThamGiaEntity convertToEntity(DangKyThamGiaDTO dto) {
        DangKiThamGiaCompositeID id = new DangKiThamGiaCompositeID();
        id.setMaCuocThi(cuocThiRepository.findById(dto.getMaCuocThi())
                .orElseThrow(() -> new NullPointerException("Cuộc thi không tồn tại")));
        id.setMaNhomSinhVien(nhomSinhVienRepository.findById(dto.getMaNhomSinhVien())
                .orElseThrow(() -> new NullPointerException("Nhóm sinh viên không tồn tại")));

        DangKyThamGiaEntity entity = new DangKyThamGiaEntity();
        entity.setId(id);
        entity.setTrangThaiDangKiThamGiaEnum(TrangThaiDangKiThamGiaEnum.DANG_CHO_DUYET);

        return entity;
    }

    // public DangKyThamGiaEntity convertToEntityWithNCKH(DangKyThamGiaDTO dto){
    // DangKiThamGiaCompositeID id = new DangKiThamGiaCompositeID();
    // id.setMaCuocThi(cuocThiRepository.findById(dto.getMaCuocThi()).orElseThrow(()
    // -> new NullPointerException("Cuộc thi không tồn tại")));
    // id.setMaNhomSinhVien(nhomSinhVienRepository.findById(dto.getMaNhomSinhVien()).orElseThrow(()
    // -> new NullPointerException("Nhóm sinh viên không tồn tại")));
    //
    // DangKyThamGiaEntity entity = new DangKyThamGiaEntity();
    // entity.setId(id);
    // entity.setMaDeTaiThamGia(deTaisRepository.findById(dto.getMaDeTaiThamGia()).orElseThrow(()
    // -> new NullPointerException("Đề tài không tồn tại")));
    // entity.setTrangThaiDangKiThamGiaEnum(TrangThaiDangKiThamGiaEnum.DANG_CHO_DUYET);
    //
    // return entity;
    // }

    public DangKyThamGiaNCKHResponse convertToResponse(DangKyThamGiaNCKHEntity entity) {
        DangKyThamGiaNCKHResponse response = new DangKyThamGiaNCKHResponse();

        response.setCuocThi(cuocThiConveter.toCuocThiResponse(entity.getId().getMaDeTai().getCuocThi()));
        response.setDeTai(
                deTaiConverter.toDeTaiResponse(deTaisRepository.findById(entity.getId().getMaDeTai().getMaDeTai())
                        .orElseThrow(() -> new NullPointerException("Đề tài không tồn tại"))));

        response.setNhom(nhomConverter
                .toNhomResponse(nhomSinhVienRepository.findById(entity.getId().getMaNhomSinhVien().getMaNhom())
                        .orElseThrow(() -> new NullPointerException("Nhóm sinh viên không tồn tại"))));
        response.setTrangThai(entity.getTrangThaiDangKiThamGiaEnum().getTenTrangThai());
        response.setNgayThamGia(entity.getNgayDangKy());
        response.setNgayDuyet(entity.getNgayDuyet());

        return response;
    }

    // public DangKyThamGiaResponse toThamGiaResponse(DangKyThamGiaEntity
    // dangKyThamGiaEntity){
    // DangKyThamGiaResponse dangKyThamGiaResponse =
    // modelMapper.map(dangKyThamGiaEntity, DangKyThamGiaResponse.class);
    // CuocThiEntity cuocThiEntity =
    // cuocThiRepository.findById(dangKyThamGiaEntity.getMaCuocThi().getId()).get();
    // dangKyThamGiaResponse.setChuDe(cuocThiEntity.getChuDe());
    // ThanhVienNhomEntity thanhVienNhomEntity =
    // thanhVienNhomRepository.findAllByMaNhom_MaNhom(dangKyThamGiaEntity.getMaNhomSinhVien().getMaNhom()).get(0);
    // SinhVienEntity sinhVienEntity =
    // sinhVienRepository.findById(thanhVienNhomEntity.getMaSinhVien().getMaSinhVien()).get();
    // dangKyThamGiaResponse.setTenNhomTruong(sinhVienEntity.getHoTen());
    // return dangKyThamGiaResponse;
    // }
}
