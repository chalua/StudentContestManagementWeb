package com.datn.qlct.service.Impl;

import com.datn.qlct.converter.KetQuaConveter;
import com.datn.qlct.converter.VongThiConverter;
import com.datn.qlct.dto.GiaiThuongDTO;
import com.datn.qlct.dto.GiangVienDTO;
import com.datn.qlct.dto.KetQuaDTO;
import com.datn.qlct.dto.VongThiDTO;
import com.datn.qlct.dto.response.KetQuaResponse;
import com.datn.qlct.dto.response.ThongTinKetQuaResponse;
import com.datn.qlct.dto.response.VongThiResponse;
import com.datn.qlct.entity.GiaiThuongEntity;
import com.datn.qlct.entity.GiangVienEntity;
import com.datn.qlct.entity.KetQuaVongThiCompositeID;
import com.datn.qlct.entity.KetQuaVongThiEntity;
import com.datn.qlct.entity.NhomSinhVienEntity;
import com.datn.qlct.entity.VongThiEntity;
import com.datn.qlct.repository.*;
import com.datn.qlct.service.IKetQuaService;
import jxl.write.DateTime;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javax.management.RuntimeErrorException;

@Service
public class KetQuaService implements IKetQuaService {
    @Autowired
    private KetQuaRepository ketQuaRepository;
    @Autowired
    private VongThiRepository vongThiRepository;
    @Autowired
    private GiaiThuongRepository giaiThuongRepository;
    @Autowired
    private NhomSinhVienRepository nhomSinhVienRepository;

    @Autowired
    private CuocThiService cuocThiService;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private VongThiConverter vongThiConverter;
    @Autowired
    private KetQuaConveter ketQuaConveter;

    @Override
    public void addAndUpdateKetQua(KetQuaDTO ketQuaDTO) {
        // Map DTO to entity
        KetQuaVongThiEntity result = modelMapper.map(ketQuaDTO, KetQuaVongThiEntity.class);

        // Initialize the composite ID if null
        if (result.getId() == null) {
            result.setId(new KetQuaVongThiCompositeID());
        }

        result.getId().setMaVongThi(vongThiRepository.findById(ketQuaDTO.getMaVongThi()).orElseThrow(
                () -> new RuntimeException("Không tìm thấy vòng thi")));
        result.getId().setMaNhomSinhVien(nhomSinhVienRepository.findById(ketQuaDTO.getMaNhomSinhVien()).orElseThrow(
                () -> new RuntimeException("Không tìm thấy nhóm")));

        if (vongThiRepository.findById(ketQuaDTO.getMaVongThi()).get().getNgayKetThuc().getTime() > System
                .currentTimeMillis()) {
            throw new RuntimeException("Vòng thi này chưa kết thúc");
        }

        if (ketQuaDTO.getMaGiaiThuong() != null) {
            result.setMaGiaiThuong(giaiThuongRepository.findById(ketQuaDTO.getMaGiaiThuong()).orElseThrow(
                    () -> new RuntimeException("Không tìm thấy giải thưởng")));
        } else {
            result.setMaGiaiThuong(null);
        }
        result.setKetQua(ketQuaDTO.getKetQua());

        ketQuaRepository.save(result);
    }

    public List<GiaiThuongEntity> getAllGiaiThuong() {
        List<GiaiThuongEntity> giaiThuongEntities = giaiThuongRepository.findAll();

        return giaiThuongEntities;

    }

    public void traoGiai(KetQuaDTO ketQuaDTO) {
        // get all vong thi by nhom
        List<KetQuaVongThiEntity> ketQuaVongThiEntities = ketQuaRepository
                .findKetQuaBymaNhomSinhVien(ketQuaDTO.getMaNhomSinhVien());

        if (ketQuaVongThiEntities.isEmpty()) {
            throw new RuntimeException("Không tìm thấy vòng thi nào cho nhóm sinh viên này.");
        }

        // get vong thi cuoi cung
        KetQuaVongThiEntity vongThiCuoiCung = ketQuaVongThiEntities.stream()
                .max(Comparator.comparing(ketQua -> ketQua.getId().getMaVongThi().getNgayBatDau()))
                .orElseThrow(() -> new IllegalArgumentException("Không thể xác định vòng thi cuối cùng."));

        GiaiThuongEntity giaiThuongEntity = giaiThuongRepository.findById(ketQuaDTO.getMaGiaiThuong())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy giải thưởng"));

        // boolean isGiaiThuongDaDuocTrao =
        // ketQuaRepository.existsByIdMaVongThiAndMaGiaiThuong(
        // vongThiCuoiCung.getId().getMaVongThi(), giaiThuongEntity);

        // if (isGiaiThuongDaDuocTrao) {
        // throw new RuntimeException("Giải thưởng này đã được trao cho nhóm khác.");
        // }

        vongThiCuoiCung.setMaGiaiThuong(giaiThuongEntity);

        ketQuaRepository.save(vongThiCuoiCung);
    }

    public GiaiThuongDTO getGiaiByNhom(String maNhom) {
        List<KetQuaVongThiEntity> ketQuaVongThiEntities = ketQuaRepository.findKetQuaBymaNhomSinhVien(maNhom);

        if (ketQuaVongThiEntities.isEmpty()) {
            throw new RuntimeException("Không tìm thấy kết quả nào cho nhóm này.");
        }

        KetQuaVongThiEntity vongThiCuoiCung = ketQuaVongThiEntities.stream()
                .max(Comparator.comparing(kq -> kq.getId().getMaVongThi().getNgayBatDau()))
                .orElseThrow(() -> new RuntimeException("Không thể xác định vòng thi cuối cùng."));

        GiaiThuongEntity giaiThuongEntity = vongThiCuoiCung.getMaGiaiThuong();

        if (giaiThuongEntity == null) {
            return null;
        }

        return modelMapper.map(giaiThuongEntity, GiaiThuongDTO.class);
    }

    @Override
    public List<VongThiResponse> findCuocThi(Long idCuocThi) {
        List<VongThiResponse> result = new ArrayList<>();
        List<VongThiEntity> vongThiEntities = vongThiRepository.findAllByCuocThiId(idCuocThi);
        int index = 1;
        for (VongThiEntity item : vongThiEntities) {
            VongThiResponse vongThiResponse = vongThiConverter.toVongThiResponse(item, index);
            result.add(vongThiResponse);
            index++;
        }
        return result;
    }

    @Override
    public List<KetQuaResponse> findNhomSinhVien(String idVongThi) {
        List<KetQuaResponse> result = new ArrayList<>();
        List<KetQuaVongThiEntity> ketQuaVongThiEntities = ketQuaRepository.findByMaVongThi(idVongThi);
        for (KetQuaVongThiEntity item : ketQuaVongThiEntities) {
            KetQuaResponse ketQuaResponse = ketQuaConveter.toKetQuaResponse(item);
            result.add(ketQuaResponse);
        }
        return result;
    }

    @Override
    public ThongTinKetQuaResponse findThongTinKetQua(KetQuaDTO ketQuaDTO) {
        KetQuaVongThiEntity ketQuaVongThiEntity = ketQuaRepository
                .findByMaNhomAndMaVongThi(ketQuaDTO.getMaNhomSinhVien(), ketQuaDTO.getMaVongThi());
        ThongTinKetQuaResponse thongTinKetQuaResponse = ketQuaConveter.toThongTinKetQuaResponse(ketQuaVongThiEntity);
        return thongTinKetQuaResponse;
    }

    public ThongTinKetQuaResponse findKetQua(String maNhom, String maVong) {
        KetQuaVongThiEntity ketQuaVongThiEntity = ketQuaRepository
                .findKetQuaBymaVongThiAndmaNhomSinhVien(maVong, maNhom)
                .orElse(null);

        if (ketQuaVongThiEntity == null)
            return null;

        ThongTinKetQuaResponse thongTinKetQuaResponse = ketQuaConveter.toThongTinKetQuaResponse(ketQuaVongThiEntity);
        return thongTinKetQuaResponse;
    }

    public List<KetQuaResponse> getAllKetQuaByCuocThi(Long cuocThiId) {

        List<VongThiDTO> vongThiDTOs = cuocThiService.getAllVongByCuocThi(cuocThiId);

        List<KetQuaResponse> kQuaResponses = new ArrayList<>();

        vongThiDTOs.forEach(item -> {
            List<KetQuaVongThiEntity> ketQuaVongThiEntities = ketQuaRepository.findByMaVongThi(item.getMaVongThi());

            ketQuaVongThiEntities.forEach(entity -> {
                kQuaResponses.add(ketQuaConveter.toKetQuaResponse(entity, item.getSoThuTuVongThi()));
            });

        });

        return kQuaResponses;
    }

    @Override
    public List<KetQuaResponse> getVongThiCurrently(Long idCuocThi) {
        Date currentTime = new Date(); // Lấy thời gian hiện tại

        // Lấy danh sách vòng thi hiện tại
        List<VongThiEntity> vongThiEntities = vongThiRepository.findCurrentVongThi(idCuocThi, currentTime);

        List<KetQuaResponse> result = new ArrayList<>();
        for (VongThiEntity vongThi : vongThiEntities) {
            // Lấy kết quả của từng vòng thi
            List<KetQuaVongThiEntity> ketQuaVongThiEntities = ketQuaRepository.findByMaVongThi(vongThi.getMaVongThi());
            for (KetQuaVongThiEntity item : ketQuaVongThiEntities) {
                KetQuaResponse ketQuaResponse = ketQuaConveter.toKetQuaResponse(item);
                result.add(ketQuaResponse);
            }
        }

        return result;
    }

}
