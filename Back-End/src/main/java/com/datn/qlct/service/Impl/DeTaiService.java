package com.datn.qlct.service.Impl;

import com.datn.qlct.converter.DangKyThamGiaConverter;
import com.datn.qlct.converter.DangKyThamGiaNCKHConverter;
import com.datn.qlct.converter.DeTaiConverter;
import com.datn.qlct.dto.DangKyThamGiaNCKHDTO;
import com.datn.qlct.dto.DeTaiDTO;
import com.datn.qlct.dto.DuyetDeTaiDTO;
import com.datn.qlct.dto.TuChoiDangKyThamGiaNCKHDTO;
import com.datn.qlct.dto.response.CuocThiResponse;
import com.datn.qlct.dto.response.DangKyThamGiaNCKHResponse;
import com.datn.qlct.dto.response.DangKyThamGiaNhomResponse;
import com.datn.qlct.dto.response.DangKyThamGiaResponse;
import com.datn.qlct.dto.response.DeTaiResponse;
import com.datn.qlct.entity.CuocThiEntity;
import com.datn.qlct.entity.DangKiThamGiaNCKHCompositeID;
import com.datn.qlct.entity.DangKyThamGiaEntity;
import com.datn.qlct.entity.DangKyThamGiaNCKHEntity;
import com.datn.qlct.entity.DeTaiEntity;
import com.datn.qlct.entity.NhomSinhVienEntity;
import com.datn.qlct.entity.VongThiEntity;
import com.datn.qlct.enums.LoaiNhomEnum;
import com.datn.qlct.enums.TrangThaiDangKiThamGiaEnum;
import com.datn.qlct.enums.TrangThaiDuyetDeTaiEnum;
import com.datn.qlct.repository.CuocThiRepository;
import com.datn.qlct.repository.DangKyThamGIaNCKHRepository;
import com.datn.qlct.repository.DeTaisRepository;
import com.datn.qlct.repository.NhomSinhVienRepository;
import com.datn.qlct.repository.VongThiRepository;
import com.datn.qlct.service.IDeTaiService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DeTaiService implements IDeTaiService {
    private String index = "DT";

    @Autowired
    private DeTaisRepository deTaiRepository;

    @Autowired
    private CuocThiRepository cuocThiRepository;

    @Autowired
    private VongThiRepository vongThiRepository;

    @Autowired
    private DangKyThamGIaNCKHRepository dangKyThamGIaNCKHRepository;

    @Autowired
    private NhomSinhVienRepository nhomSinhVienRepository;

    @Autowired
    private DeTaiConverter deTaiConverter;

    @Autowired
    private DangKyThamGiaNCKHConverter dangKyThamGiaNCKHConverter;

    @Autowired
    private PrefixTrackerService prefixTrackerService;

    @Transactional
    public void dangKyDeTai(DangKyThamGiaNCKHDTO dangKyThamGiaNCKHDTO) {
        DeTaiEntity deTai = deTaiRepository.findOneByMaDeTai(dangKyThamGiaNCKHDTO.getMaDeTai())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đề tài"));

        if (deTai.getTrangThaiDuyetDeTaiEnum() != TrangThaiDuyetDeTaiEnum.DA_DUYET) {
            throw new RuntimeException("Đề tài không được duyệt");
        }

        NhomSinhVienEntity nhomSinhVien = nhomSinhVienRepository
                .findOneByMaNhom(dangKyThamGiaNCKHDTO.getMaNhomSinhVien())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhóm này"));

        if (nhomSinhVien.getLoaiNhom() != LoaiNhomEnum.NGHIEN_CUU_KHOA_HOC) {
            throw new RuntimeException("Nhóm của bạn chỉ có thể đăng ký cuộc thi");
        }

        Optional<DangKyThamGiaNCKHEntity> dangKyThamGiaNCKHEntities = dangKyThamGIaNCKHRepository
                .findByMaNhom(dangKyThamGiaNCKHDTO.getMaNhomSinhVien());

        if (dangKyThamGiaNCKHEntities.isPresent()
                && dangKyThamGiaNCKHEntities.get()
                        .getTrangThaiDangKiThamGiaEnum() != TrangThaiDangKiThamGiaEnum.TU_CHOI) {
            throw new RuntimeException("Nhóm của bạn đã đăng ký đề tài");
        }

        Optional<DangKyThamGiaNCKHEntity> existDeTai = dangKyThamGIaNCKHRepository
                .findOneByMaDeTai(dangKyThamGiaNCKHDTO.getMaDeTai());

        if (existDeTai.isPresent()
                && existDeTai.get().getTrangThaiDangKiThamGiaEnum() != TrangThaiDangKiThamGiaEnum.TU_CHOI) {
            throw new RuntimeException("Đã có nhóm đăng ký đề tài này");
        }

        CuocThiEntity cuocThiEntity = cuocThiRepository.findById(deTai.getCuocThi().getId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy cuộc thi"));

        System.out.println(cuocThiEntity.getNgayBatDau().getTime());
        System.out.println(System.currentTimeMillis());

        if (cuocThiEntity.getNgayBatDau().getTime() < System.currentTimeMillis()) {
            throw new RuntimeException("Cuộc thi này đã diễn ra");
        }

        DangKiThamGiaNCKHCompositeID compositeID = new DangKiThamGiaNCKHCompositeID();
        compositeID.setMaDeTai(deTai);
        compositeID.setMaNhomSinhVien(nhomSinhVien);

        DangKyThamGiaNCKHEntity dangKyThamGiaNCKHEntity = new DangKyThamGiaNCKHEntity();

        dangKyThamGiaNCKHEntity.setId(compositeID);
        dangKyThamGiaNCKHEntity.setTrangThaiDangKiThamGiaEnum(TrangThaiDangKiThamGiaEnum.DANG_CHO_DUYET);
        dangKyThamGiaNCKHEntity.setNgayDangKy(LocalDate.now());

        dangKyThamGIaNCKHRepository.save(dangKyThamGiaNCKHEntity);
    }

    @Transactional
    public void duyetNhomThamGiaDeTai(DangKyThamGiaNCKHDTO dangKyThamGiaNCKHDTO) {
        DangKyThamGiaNCKHEntity dangKyThamGiaNCKHEntity = dangKyThamGIaNCKHRepository
                .findOneByMaNhomAndMaDeTai(dangKyThamGiaNCKHDTO.getMaNhomSinhVien(),
                        dangKyThamGiaNCKHDTO.getMaDeTai())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy"));

        if (!dangKyThamGiaNCKHEntity.getId().getMaDeTai().getGiangVien().getMaGiangVien()
                .equals(dangKyThamGiaNCKHDTO.getMaGiangVien())) {
            throw new RuntimeException("Bạn không có quyền duyệt nhóm này");
        }

        dangKyThamGiaNCKHEntity.setTrangThaiDangKiThamGiaEnum(TrangThaiDangKiThamGiaEnum.DA_DUYET);
        dangKyThamGiaNCKHEntity.setNgayDuyet(LocalDate.now());

        dangKyThamGIaNCKHRepository.save(dangKyThamGiaNCKHEntity);
    }

    @Transactional
    public void tuChoiNhomThamGiaDeTai(TuChoiDangKyThamGiaNCKHDTO dangKyThamGiaNCKHDTO) {
        DangKyThamGiaNCKHEntity dangKyThamGiaNCKHEntity = dangKyThamGIaNCKHRepository
                .findOneByMaNhomAndMaDeTai(dangKyThamGiaNCKHDTO.getMaNhomSinhVien(),
                        dangKyThamGiaNCKHDTO.getMaDeTai())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy"));

        if (!dangKyThamGiaNCKHEntity.getId().getMaDeTai().getGiangVien().getMaGiangVien()
                .equals(dangKyThamGiaNCKHDTO.getMaGiangVien())) {
            throw new RuntimeException("Bạn không có quyền từ chối nhóm này");
        }

        dangKyThamGiaNCKHEntity.setTrangThaiDangKiThamGiaEnum(TrangThaiDangKiThamGiaEnum.TU_CHOI);
        dangKyThamGiaNCKHEntity.setNgayTuChoi(LocalDate.now());
        dangKyThamGiaNCKHEntity.setLyDoTuChoi(dangKyThamGiaNCKHDTO.getLyDoTuChoi());

        dangKyThamGIaNCKHRepository.save(dangKyThamGiaNCKHEntity);
    }

    @Transactional
    public List<DeTaiResponse> xemDsDeTai(Long cuocThiId) {
        List<DeTaiEntity> deTaiEntities = deTaiRepository.findAllByCuocThiId(cuocThiId);

        return deTaiEntities.stream().map(deTai -> deTaiConverter.toDeTaiResponse(deTai)).toList();
    }

    // @Transactional
    // public List<DangKyThamGiaNCKHResponse> xemDsNhomThamGiaDeTai(String maDeTai)
    // {

    // List<DangKyThamGiaNCKHEntity> dangKyThamGiaNCKHEntities =
    // dangKyThamGIaNCKHRepository.findAllByMaDeTai(maDeTai);

    // return dangKyThamGiaNCKHEntities.stream().map(dk ->
    // dangKyThamGiaNCKHConverter.convertToResponse(dk)).toList();

    // }

    @Transactional
    public List<DangKyThamGiaNhomResponse> xemDsNhomThamGiaDeTai(String maDeTai) {
        List<DangKyThamGiaNCKHEntity> dangKyThamGiaNCKHEntities = dangKyThamGIaNCKHRepository.getDsDkThamGia(maDeTai);

        return dangKyThamGiaNCKHEntities.stream()
                .map(dangKy -> {
                    DangKyThamGiaNhomResponse dto = new DangKyThamGiaNhomResponse();
                    dto.setMaNhom(dangKy.getId().getMaNhomSinhVien().getMaNhom());
                    dto.setTenNhom(dangKy.getId().getMaNhomSinhVien().getTenNhom());
                    dto.setMaCuocThi(dangKy.getId().getMaDeTai().getCuocThi().getId());
                    dto.setMaDeTai(dangKy.getId().getMaDeTai().getMaDeTai());
                    dto.setTrangThai(dangKy.getTrangThaiDangKiThamGiaEnum());
                    dto.setNgayDangKy(dangKy.getNgayDangKy());
                    dto.setNgayDuyet(dangKy.getNgayDuyet());
                    dto.setNgayTuChoi(dangKy.getNgayTuChoi());
                    return dto;
                })
                .collect(Collectors.toList());

    }

    @Override
    @Transactional
    public List<DeTaiResponse> findAll(String tenDeTai) {
        List<DeTaiResponse> result = new ArrayList<>();
        List<DeTaiEntity> deTaiEntities = deTaiRepository.findAll();
        deTaiEntities.forEach(item -> {
            DeTaiResponse deTaiResponse = deTaiConverter.toDeTaiResponse(item);
            result.add(deTaiResponse);
        });
        return result;
    }

    public List<DeTaiResponse> findAllDeXuat() {
        List<DeTaiResponse> result = new ArrayList<>();
        List<DeTaiEntity> deTaiEntities = deTaiRepository.findAll();

        deTaiEntities.forEach(item -> {
            DeTaiResponse deTaiResponse = deTaiConverter.toDeTaiResponse(item);

            List<DangKyThamGiaNCKHEntity> thamGiaList = dangKyThamGIaNCKHRepository.findAllByMaDeTai(item.getMaDeTai());

            if (!thamGiaList.isEmpty() && thamGiaList.stream()
                    .anyMatch(d -> d.getTrangThaiDangKiThamGiaEnum() != TrangThaiDangKiThamGiaEnum.TU_CHOI)) {
                deTaiResponse.setDaCoNhom(true);
            } else {
                deTaiResponse.setDaCoNhom(false);
            }

            result.add(deTaiResponse);
        });

        return result;
    }

    @Override
    public List<DeTaiResponse> findAllDeXuatCuaGV(String maGV) {
        List<DeTaiResponse> result = new ArrayList<>();
        List<DeTaiEntity> deTaiEntities = deTaiRepository.findByGiangVien(maGV);

        deTaiEntities.forEach(item -> {
            DeTaiResponse deTaiResponse = deTaiConverter.toDeTaiResponse(item);
            result.add(deTaiResponse);
        });
        return result;
    }

    @Override
    @Transactional
    public DeTaiResponse DeXuatDeTai(DeTaiDTO deTaiDTO) {

        CuocThiEntity cuocThiEntity = cuocThiRepository.findById(deTaiDTO.getMaCuocThi())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy cuộc thi"));

        if (cuocThiEntity.getNgayBatDau().getTime() <= System.currentTimeMillis()) {
            throw new RuntimeException("Cuộc thi đã diễn ra");
        }

        DeTaiEntity deTaiEntity = deTaiConverter.convertToEntity(deTaiDTO, prefixTrackerService.getNext(index));

        DeTaiEntity result = deTaiRepository.save(deTaiEntity);

        return deTaiConverter.toDeTaiResponse(result);
    }

    @Override
    @Transactional
    public List<DeTaiResponse> TuChoiDeTai(DuyetDeTaiDTO duyetDeTaiDTO) {
        List<DeTaiResponse> deTaiResponses = new ArrayList<>();

        for (String item : duyetDeTaiDTO.getDeTaiIds()) {
            DeTaiEntity deTaiEntity = deTaiRepository.findById(item)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy đề tài"));
            deTaiEntity.setTrangThaiDuyetDeTaiEnum(TrangThaiDuyetDeTaiEnum.TU_CHOI);
            deTaiEntity.setNgayTuChoi(LocalDate.now());
            deTaiEntity.setLyDoTuChoi(duyetDeTaiDTO.getLyDoTuChoi());
            DeTaiEntity savedEntity = deTaiRepository.save(deTaiEntity);
            deTaiResponses.add(deTaiConverter.toDeTaiResponse(savedEntity));
        }

        return deTaiResponses;
    }

    private String generateNewMaVongThi(String latestMaVongThi) {
        if (latestMaVongThi == null || latestMaVongThi.isEmpty()) {
            return "VT01";
        }

        int latestNumber = Integer.parseInt(latestMaVongThi.substring(2));
        int newNumber = latestNumber + 1;

        return String.format("VT%02d", newNumber);
    }

    @Override
    @Transactional
    public List<DeTaiResponse> duyetDeTai(DuyetDeTaiDTO duyetDeTaiDTO) {
        List<DeTaiResponse> deTaiResponses = new ArrayList<>();

        for (String item : duyetDeTaiDTO.getDeTaiIds()) {

            DeTaiEntity deTaiEntity = deTaiRepository.findById(item)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy đề tài"));
            deTaiEntity.setTrangThaiDuyetDeTaiEnum(TrangThaiDuyetDeTaiEnum.DA_DUYET);
            deTaiEntity.setNgayDuyet(LocalDate.now());
            DeTaiEntity savedEntity = deTaiRepository.save(deTaiEntity);

            deTaiResponses.add(deTaiConverter.toDeTaiResponse(savedEntity));
        }

        return deTaiResponses;
    }
}
