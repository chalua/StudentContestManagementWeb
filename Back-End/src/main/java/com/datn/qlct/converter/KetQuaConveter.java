package com.datn.qlct.converter;

import com.datn.qlct.dto.GiaiThuongDTO;
import com.datn.qlct.dto.response.KetQuaResponse;
import com.datn.qlct.dto.response.ThongTinKetQuaResponse;
import com.datn.qlct.dto.response.VongThiResponse;
import com.datn.qlct.entity.*;
import com.datn.qlct.repository.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class KetQuaConveter {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private NhomSinhVienRepository nhomSinhVienRepository;
    @Autowired
    private GiaiThuongRepository giaiThuongRepository;
    @Autowired
    private ThanhVienNhomRepository thanhVienNhomRepository;
    @Autowired
    private DangKyThamGiaRepository dangKyThamGiaRepository;

    @Autowired
    private NhomConverter nhomConverter;
    @Autowired
    private DeTaisRepository deTaisRepository;

    public ThongTinKetQuaResponse toThongTinKetQuaResponse(KetQuaVongThiEntity entity) {
        ThongTinKetQuaResponse thongTinKetQuaResponse = new ThongTinKetQuaResponse();
        List<DangKyThamGiaEntity> dangKyThamGiaEntity = dangKyThamGiaRepository
                .findByMaNhomSinhVien(entity.getId().getMaNhomSinhVien().getMaNhom());
        // thongTinKetQuaResponse.setTenDeTai(deTaisRepository.findById(dangKyThamGiaEntity.get(0).getMaDeTaiThamGia().getMaDeTai()).get().getTenDeTai());
        if (entity.getKetQua() != null) {
            thongTinKetQuaResponse.setKetQua(entity.getKetQua());
        } else {
            thongTinKetQuaResponse.setKetQua(null);
        }
        thongTinKetQuaResponse.setTenNhom(
                nhomSinhVienRepository.findById(entity.getId().getMaNhomSinhVien().getMaNhom()).get().getTenNhom());
        if (entity.getMaGiaiThuong() != null) {
            thongTinKetQuaResponse.setGiaiThuong(
                    giaiThuongRepository.findById(entity.getMaGiaiThuong().getMaGiaiThuong()).get().getTenGiaiThuong());
        } else {
            thongTinKetQuaResponse.setGiaiThuong("Chưa có giải");
        }
        List<ThanhVienNhomEntity> thanhVienNhomEntities = thanhVienNhomRepository
                .findAllByMaNhom(entity.getId().getMaNhomSinhVien().getMaNhom());
        List<String> nhom = new ArrayList<>();
        for (ThanhVienNhomEntity item : thanhVienNhomEntities) {
            String tenThanhVien = item.getId().getSinhVien().getHoTen();
            nhom.add(tenThanhVien);
        }
        thongTinKetQuaResponse.setTenThanhVien(nhom);
        thongTinKetQuaResponse.setDat(entity.isDat());
        return thongTinKetQuaResponse;
    }

    public KetQuaResponse toKetQuaResponse(KetQuaVongThiEntity entity) {
        KetQuaResponse ketQuaResponse = new KetQuaResponse();
        ketQuaResponse.setMaNhomSinhVien(entity.getId().getMaNhomSinhVien().getMaNhom());
        ketQuaResponse.setMaVongThi(entity.getId().getMaVongThi().getMaVongThi());
        ketQuaResponse.setTenNhom(
                nhomSinhVienRepository.findById(entity.getId().getMaNhomSinhVien().getMaNhom()).get().getTenNhom());
        // if (entity.getKetQua() >= 5) {
        // ketQuaResponse.setKetQua("Đậu");
        // } else if (entity.getKetQua() < 5 && entity.getKetQua() != 0) {
        // ketQuaResponse.setKetQua("Rớt");
        // } else {
        // ketQuaResponse.setKetQua("Chưa có điểm");
        // }
        ketQuaResponse.setKetQua(entity.getKetQua());
        ketQuaResponse.setDau(entity.isDat());
        return ketQuaResponse;
    }

    public KetQuaResponse toKetQuaResponse(KetQuaVongThiEntity entity, Integer soThuTuVongThi) {

        NhomSinhVienEntity nhomSinhVienEntity = nhomSinhVienRepository
                .findOneByMaNhom(entity.getId().getMaNhomSinhVien().getMaNhom())
                .orElseThrow();

        KetQuaResponse ketQuaResponse = new KetQuaResponse();
        ketQuaResponse.setMaNhomSinhVien(entity.getId().getMaNhomSinhVien().getMaNhom());
        ketQuaResponse.setMaVongThi(entity.getId().getMaVongThi().getMaVongThi());
        ketQuaResponse.setTenNhom(
                nhomSinhVienRepository.findById(entity.getId().getMaNhomSinhVien().getMaNhom()).get().getTenNhom());
        ketQuaResponse.setKetQua(entity.getKetQua());
        ketQuaResponse.setDau(entity.isDat());
        ketQuaResponse.setSoThuTuVongThi(soThuTuVongThi);

        if (entity.getMaGiaiThuong() != null) {
            GiaiThuongEntity giaiThuongEntity = giaiThuongRepository
                    .findById(entity.getMaGiaiThuong().getMaGiaiThuong())
                    .orElse(null);
            ketQuaResponse.setTenGiaiThuong(giaiThuongEntity.getTenGiaiThuong());

        }

        ketQuaResponse.setNhomResponse(nhomConverter.toNhomResponse(nhomSinhVienEntity));

        return ketQuaResponse;
    }
}
