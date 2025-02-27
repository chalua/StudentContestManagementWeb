package com.datn.qlct.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.datn.qlct.dto.DangKyThamGiaDTO;
import com.datn.qlct.dto.response.DangKyDeTaiResponse;
import com.datn.qlct.dto.response.DangKyThamGiaResponse;
import com.datn.qlct.entity.DangKiThamGiaCompositeID;
import com.datn.qlct.entity.DangKyThamGiaEntity;
import com.datn.qlct.enums.TrangThaiDangKiThamGiaEnum;
import com.datn.qlct.repository.CuocThiRepository;
import com.datn.qlct.repository.DeTaisRepository;
import com.datn.qlct.repository.NhomSinhVienRepository;
import com.datn.qlct.repository.SinhVienRepository;
import com.datn.qlct.repository.ThanhVienNhomRepository;

@Component
public class DangKyThamGiaConverter {
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

    public DangKyThamGiaResponse convertToResponse(DangKyThamGiaEntity entity) {
        DangKyThamGiaResponse response = new DangKyThamGiaResponse();
        response.setCuocThi(
                cuocThiConveter.toCuocThiResponse(cuocThiRepository.findById(entity.getId().getMaCuocThi().getId())
                        .orElseThrow(() -> new NullPointerException("Cuộc thi không tồn tại"))));
        response.setNhom(nhomConverter
                .toNhomResponse(nhomSinhVienRepository.findById(entity.getId().getMaNhomSinhVien().getMaNhom())
                        .orElseThrow(() -> new NullPointerException("Nhóm sinh viên không tồn tại"))));
        response.setTrangThai(entity.getTrangThaiDangKiThamGiaEnum().getTenTrangThai());
        response.setNgayThamGia(entity.getNgayDangKy());
        return response;
    }

    public DangKyDeTaiResponse convertToDangKyDeTaiResponse(DangKyThamGiaEntity entity) {
        DangKyDeTaiResponse response = new DangKyDeTaiResponse();
        // response.setMaDeTai(entity.getMaDeTaiThamGia().getMaDeTai());
        // response.setTenDeTai(entity.getMaDeTaiThamGia().getTenDeTai());
        response.setMaCuocThi(entity.getId().getMaCuocThi().getId());
        response.setTenCuocThi(entity.getId().getMaCuocThi().getTenCuocThi());
        response.setMaNhomSinhVien(entity.getId().getMaNhomSinhVien().getMaNhom());
        response.setTrangThai(entity.getTrangThaiDangKiThamGiaEnum().getTenTrangThai());
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
