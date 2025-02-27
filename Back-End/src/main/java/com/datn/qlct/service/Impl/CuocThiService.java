package com.datn.qlct.service.Impl;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import com.datn.qlct.dto.*;
import com.datn.qlct.dto.response.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.datn.qlct.converter.CuocThiConveter;
import com.datn.qlct.converter.DangKyThamGiaConverter;
import com.datn.qlct.converter.DangKyThamGiaNCKHConverter;
import com.datn.qlct.entity.CuocThiEntity;
import com.datn.qlct.entity.DangKyThamGiaEntity;
import com.datn.qlct.entity.DangKyThamGiaNCKHEntity;
import com.datn.qlct.entity.GiangVienEntity;
import com.datn.qlct.entity.NhomSinhVienEntity;
import com.datn.qlct.entity.ThanhVienNhomEntity;
import com.datn.qlct.entity.VongThiEntity;
import com.datn.qlct.enums.LoaiNhomEnum;
import com.datn.qlct.enums.TrangThaiDangKiThamGiaEnum;
import com.datn.qlct.repository.CuocThiRepository;
import com.datn.qlct.repository.DangKyThamGIaNCKHRepository;
import com.datn.qlct.repository.DangKyThamGiaRepository;
import com.datn.qlct.repository.DeTaisRepository;
import com.datn.qlct.repository.GiangVienRepository;
import com.datn.qlct.repository.KetQuaRepository;
import com.datn.qlct.repository.LoaiCuocThiRepository;
import com.datn.qlct.repository.NhomSinhVienRepository;
import com.datn.qlct.repository.ThanhVienNhomRepository;
import com.datn.qlct.repository.VongThiRepository;
import com.datn.qlct.service.ICuocThiService;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class CuocThiService implements ICuocThiService {
    @Autowired
    private CuocThiRepository cuocThiRepository;
    @Autowired
    private LoaiCuocThiRepository loaiCuocThiRepository;
    @Autowired
    private CuocThiConveter cuocThiConveter;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private NhomSinhVienRepository nhomSinhVienRepository;
    @Autowired
    private GiangVienRepository giangVienRepository;
    @Autowired
    private ThanhVienNhomRepository thanhVienNhomRepository;

    @Autowired
    private DeTaisRepository deTaisRepository;
    @Autowired
    private DangKyThamGiaRepository dangKyThamGiaRepository;
    @Autowired
    private DangKyThamGIaNCKHRepository dangKyThamGIaNCKHRepository;
    @Autowired
    private VongThiRepository vongThiRepository;
    @Autowired
    private DangKyThamGiaConverter dangKyThamGiaConverter;
    @Autowired
    private DangKyThamGiaNCKHConverter dangKyThamGiaNCKHConverter;
    @Autowired
    private KetQuaRepository ketQuaRepository;

    public List<DangKyThamGiaResponse> thongKe() {
        List<DangKyThamGiaEntity> dangKyThamGiaEntities = dangKyThamGiaRepository.findAll();

        return dangKyThamGiaEntities.stream().map(dk -> dangKyThamGiaConverter.convertToResponse(dk)).toList();
    }

    public List<Object> thongKeCombie() {
        List<DangKyThamGiaEntity> dangKyThamGiaEntities = dangKyThamGiaRepository.findAll();
        List<DangKyThamGiaNCKHEntity> dangKyThamGiaNCKHEntities = dangKyThamGIaNCKHRepository.findAll();

        List<Object> dangKyThamGiaResponses = new ArrayList<>();

        dangKyThamGiaEntities.forEach(item -> {
            dangKyThamGiaResponses.add(dangKyThamGiaConverter.convertToResponse(item));
        });

        dangKyThamGiaNCKHEntities.forEach(item -> {
            dangKyThamGiaResponses.add(dangKyThamGiaNCKHConverter.convertToResponse(item));
        });

        return dangKyThamGiaResponses;
    }

    public List<DangKyThamGiaNCKHResponse> thongKeNCKH() {
        List<DangKyThamGiaNCKHEntity> dangKyThamGiaEntities = dangKyThamGIaNCKHRepository.findAll();

        return dangKyThamGiaEntities.stream().map(dk -> dangKyThamGiaNCKHConverter.convertToResponse(dk)).toList();
    }

    @Transactional
    public List<CuocThiResponse> findDsCuocThiTheoLoai(Integer loaiId) {
        List<CuocThiEntity> cuocThiEntities = cuocThiRepository.findAllByMaLoaiCuocThi(loaiId);
        return cuocThiConveter.convertToListResponse(cuocThiEntities);
    }

    @Override
    @Transactional
    public List<CuocThiResponse> findDsCuocThi() {
        List<CuocThiEntity> cuocThiEntities = cuocThiRepository.findAllCuocThi();
        List<CuocThiResponse> result = new ArrayList<>();

        cuocThiEntities.forEach(item -> {
            CuocThiResponse cuocThiResponse = cuocThiConveter.toCuocThiResponse(item);

            List<DangKyThamGiaEntity> thamGiaList = dangKyThamGiaRepository.findAllByCuocThi(item.getId());

            result.add(cuocThiResponse);
        });

        return result;
    }

    @Override
    @Transactional
    public List<CuocThiResponse> findDsNCKH() {
        List<CuocThiEntity> cuocThiEntities = cuocThiRepository.findAllByMaLoaiCuocThi(3);
        return cuocThiConveter.convertToListResponse(cuocThiEntities);
    }

    @Override
    @Transactional
    public List<CuocThiResponse> findAllByTenCuocThi(String tenCuocThi) {
        if (tenCuocThi == null || tenCuocThi.isBlank()) {
            throw new NullPointerException("Tên cuộc thi không được để trống");
        }

        List<CuocThiEntity> cuocThiEntities = cuocThiRepository.findAllByTenCuocThi(tenCuocThi);
        return cuocThiConveter.convertToListResponse(cuocThiEntities);
    }

    @Override
    @Transactional
    public VongThiDTO taoVongThi(VongThiDTO vongThiDTO) {
        CuocThiEntity cuocThiEntity = cuocThiRepository.findById(vongThiDTO.getCuocThiId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy cuộc thi này"));

        if (vongThiDTO.getNgayBatDau().getTime() < cuocThiEntity.getNgayBatDau().getTime()
                || vongThiDTO.getNgayBatDau().getTime() > cuocThiEntity.getNgayKetThuc().getTime()) {
            throw new RuntimeException("Ngày bắt đầu của vòng thi phải nằm trong khoảng thời gian của cuộc thi");
        }

        if (vongThiDTO.getNgayKetThuc().getTime() > cuocThiEntity.getNgayKetThuc().getTime()
                || vongThiDTO.getNgayKetThuc().getTime() < cuocThiEntity.getNgayBatDau().getTime()) {
            throw new RuntimeException("Ngày kết thúc của vòng thi phải nằm trong khoảng thời gian của cuộc thi");
        }

        if (vongThiDTO.getNgayBatDau().getTime() >= vongThiDTO.getNgayKetThuc().getTime()) {
            throw new RuntimeException("Ngày bắt đầu phải nhỏ hơn ngày kết thúc");

        }

        VongThiEntity lastVongThi = vongThiRepository.findFirstByCuocThiOrderByNgayKetThucDesc(cuocThiEntity);
        if (lastVongThi != null && vongThiDTO.getNgayBatDau().getTime() <= lastVongThi.getNgayKetThuc().getTime()) {
            throw new RuntimeException("Ngày bắt đầu của vòng thi phải lớn hơn ngày kết thúc của vòng thi trước đó.");
        }

        VongThiEntity result = modelMapper.map(vongThiDTO, VongThiEntity.class);
        result.setCuocThi(cuocThiEntity);
        result.setMaVongThi(generateNewMaVongThi(vongThiRepository.findLatestMaVongThi()));
        VongThiEntity savedEntity = vongThiRepository.save(result);

        return modelMapper.map(savedEntity, VongThiDTO.class);
    }

    @Override
    public List<VongThiDTO> getAllVongByCuocThi(Long cuocThiId) {
        List<VongThiEntity> vongThiEntities = vongThiRepository.findAllByCuocThiId(cuocThiId);

        vongThiEntities.sort(Comparator.comparing(VongThiEntity::getNgayBatDau));

        AtomicInteger counter = new AtomicInteger(1);
        return vongThiEntities.stream()
                .map(vongThi -> {
                    VongThiDTO dto = modelMapper.map(vongThi, VongThiDTO.class);
                    dto.setSoThuTuVongThi(counter.getAndIncrement());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public VongThiDTO updateVongThi(VongThiDTO vongThiDTO) {
        VongThiEntity vongThiEntity = vongThiRepository.findByCuocThiIdAndMaVongThi(vongThiDTO.getCuocThiId(),
                vongThiDTO.getMaVongThi()).orElseThrow(() -> new EntityNotFoundException("Không tìm thấy vong thi"));

        vongThiEntity.setNgayBatDau(vongThiDTO.getNgayBatDau());
        vongThiEntity.setNgayKetThuc(vongThiDTO.getNgayKetThuc());
        vongThiEntity.setNoiDung(vongThiDTO.getNoiDung());

        VongThiEntity savedEntity = vongThiRepository.save(vongThiEntity);

        return modelMapper.map(savedEntity, VongThiDTO.class);
    }

    @Override
    public void deleteVongThi(Long cuocThiId, String maVongThi) {
        VongThiEntity vongThiEntity = vongThiRepository.findByCuocThiIdAndMaVongThi(cuocThiId, maVongThi)
                .orElseThrow(() -> new EntityNotFoundException("Không tìm thấy vong thi"));

        if (!"Chưa diễn ra".equals(vongThiEntity.getCuocThi().getTrangThai())) {
            throw new RuntimeException("Cuộc thi này đang diễn ra hoặc đã kết thúc, không thể xóa");
        }

        vongThiRepository.delete(vongThiEntity);

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
    public CuocThiResponse addCuocThi(CuocThiDTO cuocThiDTO) {
        CuocThiEntity result = cuocThiConveter.convertToEntity(cuocThiDTO);
        CuocThiEntity savedEntity = cuocThiRepository.save(result);

        if (cuocThiDTO.getNgayBatDau().getTime() <= System.currentTimeMillis()) {
            throw new RuntimeException("Ngày bắt đầu phải lớn hơn ngày hiện tại");
        }

        if (cuocThiDTO.getNgayBatDau().getTime() >= cuocThiDTO.getNgayKetThuc().getTime()) {
            throw new RuntimeException("Ngày bắt đầu phải bé hơn ngày kết thúc");
        }

        if (cuocThiDTO.getMaLoaiCuocThi() == 3) {
            // Chuyển Date -> LocalDate
            LocalDate ngayBatDau = savedEntity.getNgayBatDau()
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();

            LocalDate ngayKetThuc = savedEntity.getNgayKetThuc()
                    .toInstant()
                    .atZone(ZoneId.systemDefault())
                    .toLocalDate();

            // Chia đôi tháng
            int totalMonths = ngayBatDau.until(ngayKetThuc).getMonths();
            int halfMonths = totalMonths / 2;

            LocalDate midPointDate = ngayBatDau.plusMonths(halfMonths);

            // Kết thúc của vòng Sơ khảo: 1 tháng sau khi midPointDate
            LocalDate endDateVongThi1 = midPointDate.plusMonths(1);

            VongThiEntity vongThiEntity = new VongThiEntity();
            vongThiEntity.setMaVongThi(generateNewMaVongThi(vongThiRepository.findLatestMaVongThi()));
            vongThiEntity.setCuocThi(savedEntity);
            vongThiEntity.setNgayBatDau(Date.from(ngayBatDau.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            vongThiEntity.setNgayKetThuc(Date.from(endDateVongThi1.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            vongThiEntity.setNoiDung("Vòng Sơ khảo");
            vongThiRepository.save(vongThiEntity);

            // Vòng kết thúc
            VongThiEntity vongThiEntity2 = new VongThiEntity();
            vongThiEntity2.setMaVongThi(generateNewMaVongThi(vongThiRepository.findLatestMaVongThi()));
            vongThiEntity2.setCuocThi(savedEntity);
            vongThiEntity2.setNgayBatDau(
                    Date.from(endDateVongThi1.plusDays(1).atStartOfDay(ZoneId.systemDefault()).toInstant()));
            vongThiEntity2.setNgayKetThuc(Date.from(ngayKetThuc.atStartOfDay(ZoneId.systemDefault()).toInstant()));
            vongThiEntity2.setNoiDung("Vòng kết thúc");
            vongThiRepository.save(vongThiEntity2);
        }

        if (cuocThiDTO.getMaLoaiCuocThi() == 2) {
            VongThiEntity vongThiEntity = new VongThiEntity();
            vongThiEntity.setCuocThi(savedEntity);
            vongThiEntity.setMaVongThi(generateNewMaVongThi(vongThiRepository.findLatestMaVongThi()));
            vongThiEntity.setNgayBatDau(savedEntity.getNgayBatDau());
            vongThiEntity.setNgayKetThuc(savedEntity.getNgayKetThuc());
            vongThiEntity.setNoiDung(savedEntity.getMoTa());

            vongThiRepository.save(vongThiEntity);
        }

        return cuocThiConveter.toCuocThiResponse(savedEntity);
    }

    @Override
    @Transactional
    public CuocThiResponse updateCuocThi(Long id, CuocThiDTO cuocThiDTO) {
        CuocThiEntity existingEntity = cuocThiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy cuộc thi này"));

        BeanUtils.copyProperties(cuocThiDTO, existingEntity);

        CuocThiEntity updatedEntity = cuocThiRepository.save(existingEntity);
        return cuocThiConveter.toCuocThiResponse(updatedEntity);
    }

    @Override
    @Transactional
    public void deleteCuocThi(Long id) {
        // cuocThiRepository.deleteById(id);
        cuocThiRepository.softDeleteById(id);
    }

    @Override
    @Transactional
    public DangKyThamGiaResponse dangKyThamGia(DangKyThamGiaDTO dangKyThamGiaDTO) {

        CuocThiEntity existingEntity = cuocThiRepository.findById(dangKyThamGiaDTO.getMaCuocThi())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy cuộc thi này"));

        if (existingEntity.getNgayBatDau().getTime() <= System.currentTimeMillis()) {
            throw new RuntimeException("Cuộc thi đang diễn ra hoặc đã kết thúc");
        }

        Date ngayBatDau = existingEntity.getNgayBatDau();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(ngayBatDau);
        calendar.add(Calendar.DATE, -7);
        Date ngayKhoaDangKy = calendar.getTime();

        if (ngayKhoaDangKy.getTime() <= System.currentTimeMillis()) {
            throw new RuntimeException("Đã khóa đăng ký cuộc thi này");
        }

        ThanhVienNhomEntity thanhVienNhomEntity = thanhVienNhomRepository
                .findOneByMaNhomAndMaSinhVien(dangKyThamGiaDTO.getMaNhomSinhVien(), dangKyThamGiaDTO.getMaSinhVien())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhóm"));

        if (thanhVienNhomEntity.getNhomTruong() == 0) {
            throw new RuntimeException("Chỉ có nhóm trưởng mới được quyền đăng ký cuộc thi");
        }

        List<DangKyThamGiaEntity> allThamGia = dangKyThamGiaRepository.getDsDkThamGia(dangKyThamGiaDTO.getMaCuocThi());

        List<DangKyThamGiaEntity> validThamGia = allThamGia.stream()
                .filter(thamGia -> thamGia.getTrangThaiDangKiThamGiaEnum() != TrangThaiDangKiThamGiaEnum.TU_CHOI)
                .collect(Collectors.toList());

        if (validThamGia.size() >= existingEntity.getSoLuongNhom()) {
            throw new RuntimeException("Đã đủ số lượng nhóm tham gia");
        }

        NhomSinhVienEntity nhomSinhVien = nhomSinhVienRepository
                .findOneByMaNhom(dangKyThamGiaDTO.getMaNhomSinhVien())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhóm này"));

        if (nhomSinhVien.getLoaiNhom() != LoaiNhomEnum.THI_DAU) {
            throw new RuntimeException("Nhóm của bạn chỉ được đăng ký đề tài nghiên cứu khoa học");

        }

        Optional<DangKyThamGiaEntity> dangKyThamGiaEntities = dangKyThamGiaRepository
                .findByMaNhom(dangKyThamGiaDTO.getMaNhomSinhVien());

        if (dangKyThamGiaEntities.isPresent()
                && dangKyThamGiaEntities.get()
                        .getTrangThaiDangKiThamGiaEnum() != TrangThaiDangKiThamGiaEnum.TU_CHOI) {
            throw new RuntimeException("Nhóm của bạn đã đăng ký cuộc thi");
        }

        DangKyThamGiaEntity dangKyThamGiaEntity = dangKyThamGiaConverter.convertToEntity(dangKyThamGiaDTO);
        dangKyThamGiaEntity.setNgayDangKy(LocalDate.now());
        DangKyThamGiaEntity savedEntity = dangKyThamGiaRepository.save(dangKyThamGiaEntity);
        return dangKyThamGiaConverter.convertToResponse(savedEntity);

    }

    @Override
    public DangKyDeTaiResponse dangKyDeTai(DangKyDeTaiDTO dangKyThamGiaDTO) {
        return null;
    }

    @Transactional
    public void duyetNhomThamGia(DuyetNhomCuocThiDTO duyetNhomCuocThiDTO) {
        DangKyThamGiaEntity dangKyThamGiaEntity = dangKyThamGiaRepository
                .findByMaCuocThiAndMaNhomSinhVien(duyetNhomCuocThiDTO.getMaCuocThi(),
                        duyetNhomCuocThiDTO.getMaNhomSinhVien())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy"));

        boolean giangVienExists = dangKyThamGiaRepository.existsGiangVienInCuocThi(duyetNhomCuocThiDTO.getMaCuocThi(),
                duyetNhomCuocThiDTO.getMaGiangVien());
        if (!giangVienExists) {
            throw new RuntimeException("Giảng viên này không có quyền duyệt");
        }

        dangKyThamGiaEntity.setTrangThaiDangKiThamGiaEnum(TrangThaiDangKiThamGiaEnum.DA_DUYET);
        dangKyThamGiaEntity.setNgayDuyet(LocalDate.now());

        dangKyThamGiaRepository.save(dangKyThamGiaEntity);
    }

    @Transactional
    public void tuChoiNhomThamGia(TuChoiNhomCuocThiDTO tuChoiNhomCuocThiDTO) {
        DangKyThamGiaEntity dangKyThamGiaEntity = dangKyThamGiaRepository
                .findByMaCuocThiAndMaNhomSinhVien(tuChoiNhomCuocThiDTO.getMaCuocThi(),
                        tuChoiNhomCuocThiDTO.getMaNhomSinhVien())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy"));

        boolean giangVienExists = dangKyThamGiaRepository.existsGiangVienInCuocThi(tuChoiNhomCuocThiDTO.getMaCuocThi(),
                tuChoiNhomCuocThiDTO.getMaGiangVien());
        if (!giangVienExists) {
            throw new RuntimeException("Giảng viên này không có quyền từ chối");
        }

        dangKyThamGiaEntity.setTrangThaiDangKiThamGiaEnum(TrangThaiDangKiThamGiaEnum.TU_CHOI);
        dangKyThamGiaEntity.setNgayTuChuoi(LocalDate.now());
        dangKyThamGiaEntity.setLyDoTuChoi(tuChoiNhomCuocThiDTO.getLyDoTuChoi());

        dangKyThamGiaRepository.save(dangKyThamGiaEntity);
    }

    // @Override
    // @Transactional
    // public DangKyDeTaiResponse dangKyDeTai(DangKyDeTaiDTO dangKyThamGiaDTO) {
    // if (!CheckLoaiCuocThiNCKH(dangKyThamGiaDTO.getMaCuocThi())
    // && !CheckLoaiCuocThiUD(dangKyThamGiaDTO.getMaCuocThi())) {
    // throw new RuntimeException(
    // "Chỉ có thể đăng ký đề tài cho cuộc thi nghiên cứu khoa học hoặc lập trình
    // ứng dụng");
    // }

    // if (!CheckTrangThaiCuocThi(dangKyThamGiaDTO.getMaCuocThi())) {
    // throw new RuntimeException("Cuộc thi này đã diễn ra hoặc đã kết thúc");
    // }

    // if (!CheckTrangThaiDangKyThamGia(dangKyThamGiaDTO.getMaCuocThi(),
    // dangKyThamGiaDTO.getMaNhomSinhVien())) {
    // throw new RuntimeException("Nhóm sinh viên chưa được duyệt tham gia cuộc
    // thi");
    // }

    // try {
    // DangKyThamGiaEntity dangKyThamGiaEntity =
    // dangKyThamGiaRepository.findByMaCuocThiAndMaNhomSinhVien(
    // dangKyThamGiaDTO.getMaCuocThi(), dangKyThamGiaDTO.getMaNhomSinhVien());
    // //
    // dangKyThamGiaEntity.setMaDeTaiThamGia(deTaisRepository.findById(dangKyThamGiaDTO.getMaDeTai())
    // // .orElseThrow(() -> new NullPointerException("Không tìm thấy đề tài")));
    // return dangKyThamGiaConverter
    // .convertToDangKyDeTaiResponse(dangKyThamGiaRepository.save(dangKyThamGiaEntity));
    // } catch (DataIntegrityViolationException e) {
    // throw new ContrainViolationExecption(e.getMessage());
    // }
    // }

    private boolean CheckLoaiCuocThiNCKH(Long cuocThiId) {
        return Objects.equals(cuocThiRepository.findById(cuocThiId)
                .orElseThrow(() -> new NullPointerException("Không tìm thấy cuộc thi"))
                .getLoaiCuocThi()
                .getTenLoaiCuocThi(), "NGHIEN_CUU_KHOA_HOC");
    }

    private boolean CheckLoaiCuocThiUD(Long cuocThiId) {
        return Objects.equals(cuocThiRepository.findById(cuocThiId)
                .orElseThrow(() -> new NullPointerException("Không tìm thấy cuộc thi"))
                .getLoaiCuocThi()
                .getTenLoaiCuocThi(), "LAP_TRINH_UNG_DUNG");
    }

    private boolean CheckTrangThaiCuocThi(Long cuocThiId) {
        return Objects.equals(cuocThiRepository.findById(cuocThiId)
                .orElseThrow(() -> new NullPointerException("Không tìm thấy cuộc thi"))
                .getTrangThai(), "CHUA_DIEN_RA");
    }

    // private boolean CheckTrangThaiDangKyThamGia(Long cuocThiId, String maNhom) {
    // return
    // Objects.equals(dangKyThamGiaRepository.findByMaCuocThiAndMaNhomSinhVien(cuocThiId,
    // maNhom)
    // .getTrangThaiDangKiThamGiaEnum().getTenTrangThai(),
    // TrangThaiDangKiThamGiaEnum.DA_DUYET.getTenTrangThai());
    // }

    @Override
    public CuocThiEntity assignGiangVienToCuocThi(Long cuocThiId, String giangVienId) {
        CuocThiEntity cuocThi = cuocThiRepository.findById(cuocThiId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy cuộc thi: " + cuocThiId));

        GiangVienEntity giangVien = giangVienRepository.findById(giangVienId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy giảng viên: " + giangVienId));

        cuocThi.getGiangViens().add(giangVien);

        return cuocThiRepository.save(cuocThi);
    }

    @Override
    public CuocThiEntity deleteGiangVienFromCuocThi(Long cuocThiId, String giangVienId) {
        CuocThiEntity cuocThi = cuocThiRepository.findById(cuocThiId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy cuộc thi: " + cuocThiId));

        GiangVienEntity giangVien = giangVienRepository.findById(giangVienId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy giảng viên: " + giangVienId));

        cuocThi.getGiangViens().remove(giangVien);

        return cuocThiRepository.save(cuocThi);
    }

    public List<CuocThiGiangVienResponse> getDsGiangVienCuocThi(Long cuocThiId) {
        CuocThiEntity cuocThi = cuocThiRepository.findById(cuocThiId)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy cuộc thi: " + cuocThiId));

        List<CuocThiGiangVienResponse> dtoList = new ArrayList<>();

        for (GiangVienEntity giangVien : cuocThi.getGiangViens()) {
            CuocThiGiangVienResponse dto = new CuocThiGiangVienResponse(cuocThi.getId(), giangVien.getMaGiangVien(),
                    giangVien.getTenGiangVien(), cuocThi.getTenCuocThi());
            dtoList.add(dto);
        }

        return dtoList;

    }

    @Override
    public CuocThiResponse getCuocThiByID(Long id) {
        CuocThiEntity cuocThiEntity = cuocThiRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy cuộc thi này"));
        return cuocThiConveter.toCuocThiResponse(cuocThiEntity);
    }

    // @Override
    // @Transactional
    // public List<DangKyThamGiaResponse> loadNhomThamGia() {
    // List<DangKyThamGiaResponse> result = new ArrayList<>();
    // List<DangKyThamGiaEntity> deTaiEntities = dangKyThamGiaRepository.findAll();
    // deTaiEntities.forEach(item->{
    // DangKyThamGiaResponse dangKyThamGiaResponse =
    // dangKyThamGiaConverter.toThamGiaResponse(item);
    // result.add(dangKyThamGiaResponse);
    // });
    // return result;
    // }
}
