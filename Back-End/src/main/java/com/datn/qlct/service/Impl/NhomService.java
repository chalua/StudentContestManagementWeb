package com.datn.qlct.service.Impl;

import com.datn.qlct.converter.DangKyThamGiaConverter;
import com.datn.qlct.converter.NhomConverter;
import com.datn.qlct.dto.ChapNhanLoiMoiDTO;
import com.datn.qlct.dto.GiaiTanNhomDTO;
import com.datn.qlct.dto.NhomSinhVienDTO;
import com.datn.qlct.dto.TaoNhomDTO;
import com.datn.qlct.dto.ThanhVienNhomDTO;
import com.datn.qlct.dto.response.CuocThiResponse;
import com.datn.qlct.dto.response.DangKyThamGiaResponse;
import com.datn.qlct.dto.response.LoiMoiThamNhomResponse;
import com.datn.qlct.dto.response.NhomResponse;
import com.datn.qlct.dto.response.ThanhVienNhomResponse;
import com.datn.qlct.entity.*;
import com.datn.qlct.enums.LoaiNhomEnum;
import com.datn.qlct.enums.TrangThaiDangKiThamGiaEnum;
import com.datn.qlct.enums.TrangThaiEnum;
import com.datn.qlct.enums.TrangThaiLoiMoiEnum;
import com.datn.qlct.repository.CuocThiRepository;
import com.datn.qlct.repository.DangKyThamGIaNCKHRepository;
import com.datn.qlct.repository.DangKyThamGiaRepository;
import com.datn.qlct.repository.LoiMoiThamGiaNhomRepository;
import com.datn.qlct.repository.NhomSinhVienRepository;
import com.datn.qlct.repository.SinhVienRepository;
import com.datn.qlct.repository.ThanhVienNhomRepository;
import com.datn.qlct.service.INhomService;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class NhomService implements INhomService {
    private String index = "NH";

    private String maNhom = "";

    private Integer soLuongThanhVien = 1;

    @Autowired
    private ThanhVienNhomRepository thanhVienNhomRepository;

    @Autowired
    private NhomSinhVienRepository nhomSinhVienRepository;

    @Autowired
    private SinhVienRepository sinhVienRepository;

    @Autowired
    private LoiMoiThamGiaNhomRepository loiMoiThamGiaNhomRepository;

    @Autowired
    private DangKyThamGiaRepository dangKyThamGiaRepository;

    @Autowired
    private DangKyThamGIaNCKHRepository dangKyThamGIaNCKHRepository;

    @Autowired
    private CuocThiRepository cuocThiRepository;

    @Autowired
    private NhomConverter nhomConverter;

    @Autowired
    private DangKyThamGiaConverter dangKyThamGiaConverter;

    @Autowired
    private PrefixTrackerService prefixTrackerService;

    @Autowired
    private ModelMapper modelMapper;

    @Transactional
    public NhomResponse findNhom(String maNhom) {
        NhomSinhVienEntity nhomSinhVienEntity = nhomSinhVienRepository.findOneByMaNhom(maNhom)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhóm"));

        return nhomConverter.toNhomResponse(nhomSinhVienEntity);
    }

    @Override
    @Transactional
    public List<NhomResponse> findAllNhomByMaSV(String maSinhVien) {
        if (maSinhVien == null || maSinhVien.isBlank()) {
            throw new NullPointerException("Mã sinh viên không được để trống");
        }

        List<ThanhVienNhomEntity> thanhVienNhomEntities = thanhVienNhomRepository.findAllByMaSinhVien(maSinhVien);

        return nhomConverter.toListNhomResponse(thanhVienNhomEntities);
    }

    @Transactional
    public List<DangKyThamGiaResponse> findCuocThiByNhom(String maNhom) {
        if (maNhom == null || maNhom.isBlank()) {
            throw new NullPointerException("Mã nhóm viên không được để trống");
        }

        List<DangKyThamGiaEntity> dangKyThamGiaEntities = dangKyThamGiaRepository.findCuocThiByNhom(maNhom);

        return dangKyThamGiaEntities.stream().map(dk -> dangKyThamGiaConverter.convertToResponse(dk)).toList();
    }

    @Transactional
    public NhomResponse findNhomHienTaiByMaSV(String maSinhVien) {
        if (maSinhVien == null || maSinhVien.isBlank()) {
            throw new RuntimeException("Mã sinh viên không được để trống");
        }

        List<ThanhVienNhomEntity> thanhVienNhomEntities = thanhVienNhomRepository.findAllByMaSinhVien(maSinhVien);

        if (thanhVienNhomEntities.size() > 0) {
            for (ThanhVienNhomEntity thanhVienNhomEntity : thanhVienNhomEntities) {
                String maNhom = thanhVienNhomEntity.getId().getNhom().getMaNhom();

                NhomSinhVienEntity nhomSinhVienEntity = nhomSinhVienRepository.findOneByMaNhom(maNhom)
                        .orElseThrow(() -> new RuntimeException("Không tìm thấy nhóm"));

                // NCKH
                Optional<DangKyThamGiaNCKHEntity> dangKyThamGiaNCKHEntity = dangKyThamGIaNCKHRepository
                        .findByMaNhom(maNhom);

                if (dangKyThamGiaNCKHEntity.isPresent() && dangKyThamGiaNCKHEntity.get()
                        .getTrangThaiDangKiThamGiaEnum() != TrangThaiDangKiThamGiaEnum.TU_CHOI
                        && dangKyThamGiaNCKHEntity.get().getId().getMaDeTai().getCuocThi().getNgayKetThuc()
                                .getTime() > System
                                        .currentTimeMillis()) {
                    return nhomConverter.toNhomResponse(dangKyThamGiaNCKHEntity.get().getId().getMaNhomSinhVien());
                }

                // cuoc thi
                Optional<DangKyThamGiaEntity> dangKyThamGiaEntity = dangKyThamGiaRepository
                        .findByMaNhom(maNhom);

                if (dangKyThamGiaEntity.isPresent() && dangKyThamGiaEntity.get()
                        .getTrangThaiDangKiThamGiaEnum() != TrangThaiDangKiThamGiaEnum.TU_CHOI
                        && dangKyThamGiaEntity.get().getId().getMaCuocThi().getNgayKetThuc()
                                .getTime() > System
                                        .currentTimeMillis()) {
                    return nhomConverter.toNhomResponse(dangKyThamGiaEntity.get().getId().getMaNhomSinhVien());
                }

                // return
                if (nhomSinhVienEntity.getLoaiNhom() == LoaiNhomEnum.NGHIEN_CUU_KHOA_HOC
                        && (!dangKyThamGiaNCKHEntity.isPresent()
                                || dangKyThamGiaNCKHEntity.get()
                                        .getTrangThaiDangKiThamGiaEnum() == TrangThaiDangKiThamGiaEnum.TU_CHOI)) {
                    try {
                        return nhomConverter.toNhomResponse(dangKyThamGiaNCKHEntity.get().getId().getMaNhomSinhVien());
                    } catch (RuntimeException ex) {
                        return nhomConverter.toNhomResponse(nhomSinhVienEntity);

                    }
                }

                if (nhomSinhVienEntity.getLoaiNhom() == LoaiNhomEnum.THI_DAU && (!dangKyThamGiaEntity.isPresent()
                        || dangKyThamGiaEntity.get()
                                .getTrangThaiDangKiThamGiaEnum() == TrangThaiDangKiThamGiaEnum.TU_CHOI)) {
                    try {
                        return nhomConverter.toNhomResponse(dangKyThamGiaEntity.get().getId().getMaNhomSinhVien());

                    } catch (RuntimeException ex) {
                        return nhomConverter.toNhomResponse(nhomSinhVienEntity);

                    }
                }
            }
        }

        return null;

    }

    @Override
    @Transactional
    public LoiMoiThamNhomResponse moiThanhVien(ThanhVienNhomDTO thanhVienNhomDTO) {
        List<ThanhVienNhomEntity> thanhVienNhomEntities = thanhVienNhomRepository
                .findAllByMaSinhVien(thanhVienNhomDTO.getMaSinhVien());

        NhomResponse nhomHienTai = this.findNhomHienTaiByMaSV(thanhVienNhomDTO.getMaSinhVien());

        if (nhomHienTai == null) {
            LoiMoiThamGiaNhomEntity loiMoiThamGiaNhomEntity = nhomConverter.toLoiMoiThamGiaNhomEntity(thanhVienNhomDTO);

            return nhomConverter.toLoiMoiThamNhomResponse(loiMoiThamGiaNhomRepository.save(loiMoiThamGiaNhomEntity));
        }

        if (thanhVienNhomEntities.size() > 0) {
            for (ThanhVienNhomEntity thanhVienNhomEntity : thanhVienNhomEntities) {
                String maNhom = thanhVienNhomEntity.getId().getNhom().getMaNhom();

                // NCKH
                Optional<DangKyThamGiaNCKHEntity> dangKyThamGiaNCKHEntity = dangKyThamGIaNCKHRepository
                        .findByMaNhom(maNhom);

                if (dangKyThamGiaNCKHEntity.isPresent() && dangKyThamGiaNCKHEntity.get()
                        .getTrangThaiDangKiThamGiaEnum() != TrangThaiDangKiThamGiaEnum.TU_CHOI
                        && dangKyThamGiaNCKHEntity.get().getId().getMaDeTai().getCuocThi().getNgayKetThuc()
                                .getTime() > System
                                        .currentTimeMillis()) {
                    throw new RuntimeException("Thành viên này đã có nhóm");
                }

                // CuocThi
                Optional<DangKyThamGiaEntity> dangKyThamGiaEntity = dangKyThamGiaRepository
                        .findByMaNhom(maNhom);

                if (dangKyThamGiaEntity.isPresent() && dangKyThamGiaEntity.get()
                        .getTrangThaiDangKiThamGiaEnum() != TrangThaiDangKiThamGiaEnum.TU_CHOI
                        && dangKyThamGiaEntity.get().getId().getMaCuocThi().getNgayKetThuc()
                                .getTime() > System
                                        .currentTimeMillis()) {
                    throw new RuntimeException("Thành viên này đã có nhóm");
                }

                // Prevent
                if (!dangKyThamGiaNCKHEntity.isPresent()) {
                    throw new RuntimeException("Thành viên đã có nhóm");
                }

                if (!dangKyThamGiaEntity.isPresent()) {
                    throw new RuntimeException("Thành viên đã có nhóm");
                }
            }

        }

        LoiMoiThamGiaNhomEntity loiMoiThamGiaNhomEntity = nhomConverter.toLoiMoiThamGiaNhomEntity(thanhVienNhomDTO);

        return nhomConverter.toLoiMoiThamNhomResponse(loiMoiThamGiaNhomRepository.save(loiMoiThamGiaNhomEntity));
    }

    @Override
    @Transactional
    public NhomResponse chapNhanLoiMoi(ThanhVienNhomDTO thanhVienNhomDTO) {
        LoiMoiThamGiaNhomEntity loiMoiThamGiaNhomEntity = loiMoiThamGiaNhomRepository
                .findByMaNhomAndMaSinhVien(thanhVienNhomDTO.getMaNhom(), thanhVienNhomDTO.getMaSinhVien());

        if (loiMoiThamGiaNhomEntity == null) {
            throw new RuntimeException("Lời mời không tồn tại.");
        }

        loiMoiThamGiaNhomEntity.setTrangThaiLoiMoiEnum(TrangThaiLoiMoiEnum.DA_DONG_Y);
        LoiMoiThamGiaNhomEntity result = loiMoiThamGiaNhomRepository.save(loiMoiThamGiaNhomEntity);

        loiMoiThamGiaNhomRepository.tuChoiLoiMoiKhac(
                thanhVienNhomDTO.getMaSinhVien(),
                thanhVienNhomDTO.getMaNhom(),
                TrangThaiLoiMoiEnum.DA_TU_CHOI);
        return nhomConverter.toNhomResponse(result.getId().getNhomSinhVienEntity());
    }

    @Transactional
    public void giaiTanNhom(GiaiTanNhomDTO giaiTanNhomDTO) {
        ThanhVienNhomEntity thanhVienNhomEntity = thanhVienNhomRepository
                .findOneByMaNhomAndMaSinhVien(giaiTanNhomDTO.getMaNhomSinhVien(), giaiTanNhomDTO.getMaSinhVien())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhóm"));

        if (thanhVienNhomEntity.getNhomTruong() == 0) {
            throw new RuntimeException("Chỉ có nhóm trưởng mới được quyền giải tán nhóm");
        }

        Optional<NhomSinhVienEntity> nhomSinhVienEntity = nhomSinhVienRepository
                .findById(giaiTanNhomDTO.getMaNhomSinhVien());

        if (nhomSinhVienEntity.isPresent()) {
            Optional<DangKyThamGiaEntity> dangKyThamGiaEntity = dangKyThamGiaRepository
                    .findByMaNhom(nhomSinhVienEntity.get().getMaNhom());

            Optional<DangKyThamGiaNCKHEntity> dangKyThamGiaNCKHEntity = dangKyThamGIaNCKHRepository
                    .findByMaNhom(nhomSinhVienEntity.get().getMaNhom());

            if (dangKyThamGiaEntity.isPresent() || dangKyThamGiaNCKHEntity.isPresent()) {
                throw new RuntimeException("Nhóm đã đăng ký cuộc thi, không thể xoám");
            }

            loiMoiThamGiaNhomRepository.deleteByMaNhom(giaiTanNhomDTO.getMaNhomSinhVien());
            nhomSinhVienRepository.delete(nhomSinhVienEntity.get());
        }

    }

    @Transactional
    public NhomResponse tuChoiLoiMoi(ThanhVienNhomDTO thanhVienNhomDTO) {
        LoiMoiThamGiaNhomEntity loiMoiThamGiaNhomEntity = loiMoiThamGiaNhomRepository
                .findByMaNhomAndMaSinhVien(thanhVienNhomDTO.getMaNhom(), thanhVienNhomDTO.getMaSinhVien());
        loiMoiThamGiaNhomEntity.setTrangThaiLoiMoiEnum(TrangThaiLoiMoiEnum.DA_TU_CHOI);
        LoiMoiThamGiaNhomEntity result = loiMoiThamGiaNhomRepository.save(loiMoiThamGiaNhomEntity);

        return nhomConverter.toNhomResponse(result.getId().getNhomSinhVienEntity());
    }

    //
    // @Override
    // @Transactional
    // public void deleteThanhVien(Long[] id) {
    // for(Long item: id) {
    // ThanhVienNhomEntity result = thanhVienNhomRepository.findById(item).get();
    // if (result.getNhomTruong() == 0) {
    // result.setMaSinhVien(null);
    // result.setMaNhom(null);
    // thanhVienNhomRepository.deleteById(result.getId());
    // }
    // }
    // }
    //
    @Override
    @Transactional
    public NhomResponse taoNhom(TaoNhomDTO thanhVienNhomDTO) {
        // Check xem sv này có nhóm ở cuộc thi hiện tai hay chưa

        NhomResponse nhomHienTai = this.findNhomHienTaiByMaSV(thanhVienNhomDTO.getMssvNhomTruong());

        if (nhomHienTai == null) {
            NhomSinhVienEntity nhomSinhVienEntity = new NhomSinhVienEntity();
            nhomSinhVienEntity.setMaNhom(prefixTrackerService.getNext(index));
            nhomSinhVienEntity.setTenNhom(thanhVienNhomDTO.getTenNhom());
            nhomSinhVienEntity.setSoLuongThanhVien(0);
            nhomSinhVienEntity.setLoaiNhom(thanhVienNhomDTO.getLoaiNhom());
            NhomSinhVienEntity result = nhomSinhVienRepository.save(nhomSinhVienEntity);
            result.setSoLuongThanhVien(1);

            addNhomTruong(result, sinhVienRepository.findById(thanhVienNhomDTO.getMssvNhomTruong())
                    .orElseThrow(() -> new NullPointerException("Không tìm thấy sinh viên")));
            return nhomConverter.toNhomResponse(result);
        }

        List<ThanhVienNhomEntity> thanhVienNhomEntities = thanhVienNhomRepository
                .findAllByMaSinhVien(thanhVienNhomDTO.getMssvNhomTruong());

        List<LoiMoiThamGiaNhomEntity> loiMoiThamGiaNhomEntities = loiMoiThamGiaNhomRepository
                .findAllLoiMoi(thanhVienNhomDTO.getMssvNhomTruong());

        if (loiMoiThamGiaNhomEntities.size() > 0) {
            for (LoiMoiThamGiaNhomEntity loiMoiThamGiaNhomEntity : loiMoiThamGiaNhomEntities) {
                if (loiMoiThamGiaNhomEntity.getTrangThaiLoiMoiEnum() != TrangThaiLoiMoiEnum.DA_TU_CHOI) {
                    throw new RuntimeException("Vui lòng từ chối các lời mời trước khi tạo nhóm");
                }
            }
        }

        if (thanhVienNhomEntities.size() > 0) {
            for (ThanhVienNhomEntity thanhVienNhomEntity : thanhVienNhomEntities) {
                String maNhom = thanhVienNhomEntity.getId().getNhom().getMaNhom();

                // NCKH
                Optional<DangKyThamGiaNCKHEntity> dangKyThamGiaNCKHEntity = dangKyThamGIaNCKHRepository
                        .findByMaNhom(maNhom);

                if (dangKyThamGiaNCKHEntity.isPresent() && dangKyThamGiaNCKHEntity.get()
                        .getTrangThaiDangKiThamGiaEnum() != TrangThaiDangKiThamGiaEnum.TU_CHOI
                        && dangKyThamGiaNCKHEntity.get().getId().getMaDeTai().getCuocThi().getNgayKetThuc()
                                .getTime() > System
                                        .currentTimeMillis()) {
                    throw new RuntimeException("Bạn đã có nhóm");
                }

                // CuocThi
                Optional<DangKyThamGiaEntity> dangKyThamGiaEntity = dangKyThamGiaRepository
                        .findByMaNhom(maNhom);

                if (dangKyThamGiaEntity.isPresent() && dangKyThamGiaEntity.get()
                        .getTrangThaiDangKiThamGiaEnum() != TrangThaiDangKiThamGiaEnum.TU_CHOI
                        && dangKyThamGiaEntity.get().getId().getMaCuocThi().getNgayKetThuc()
                                .getTime() > System
                                        .currentTimeMillis()) {
                    throw new RuntimeException("Thành viên này đã có nhóm");
                }

                // Prevent
                if (!dangKyThamGiaNCKHEntity.isPresent()) {
                    throw new RuntimeException("Bạn đã có nhóm, vui lòng đăng ký cuộc thi");
                }

                if (!dangKyThamGiaEntity.isPresent()) {
                    throw new RuntimeException("Thành viên đã có nhóm");
                }
            }

        }

        NhomSinhVienEntity nhomSinhVienEntity = new NhomSinhVienEntity();
        nhomSinhVienEntity.setMaNhom(prefixTrackerService.getNext(index));
        nhomSinhVienEntity.setTenNhom(thanhVienNhomDTO.getTenNhom());
        nhomSinhVienEntity.setSoLuongThanhVien(0);
        nhomSinhVienEntity.setLoaiNhom(thanhVienNhomDTO.getLoaiNhom());
        NhomSinhVienEntity result = nhomSinhVienRepository.save(nhomSinhVienEntity);
        result.setSoLuongThanhVien(1);

        addNhomTruong(result, sinhVienRepository.findById(thanhVienNhomDTO.getMssvNhomTruong())
                .orElseThrow(() -> new NullPointerException("Không tìm thấy sinh viên")));
        return nhomConverter.toNhomResponse(result);
    }

    private void addNhomTruong(NhomSinhVienEntity nhomSinhVien, SinhVienEntity sinhVien) {
        ThanhVienNhomEntity entity = new ThanhVienNhomEntity();
        ThanhVienNhomCompositeID id = new ThanhVienNhomCompositeID();
        id.setNhom(nhomSinhVien);
        id.setSinhVien(sinhVien);
        entity.setId(id);
        entity.setNhomTruong(1);
        thanhVienNhomRepository.save(entity);
    }
    //
    // @Override
    // @Transactional
    // public void duyetNhom(String maNhom) {
    // List<ThanhVienNhomEntity> thanhVienNhomEntities =
    // thanhVienNhomRepository.findAllByMaNhom_MaNhom(maNhom);
    // for(ThanhVienNhomEntity item: thanhVienNhomEntities){
    // thanhVienNhomRepository.deleteById(item.getId());
    // }
    // nhomSinhVienRepository.deleteById(maNhom);
    // }

    public List<NhomResponse> findAllNhom() {
        List<NhomSinhVienEntity> nhomSinhVienEntities = nhomSinhVienRepository.findAll();
        return nhomConverter.toNhomResponses(nhomSinhVienEntities);
    }

    public List<LoiMoiThamNhomResponse> findAllLoiMoiNhomCuaToi(String mssv) {
        List<LoiMoiThamGiaNhomEntity> loiMoiNhomEntities = loiMoiThamGiaNhomRepository.findAllLoiMoi(mssv);

        if (loiMoiNhomEntities.size() == 0)
            return null;

        return loiMoiNhomEntities.stream().map(l -> nhomConverter.toLoiMoiThamNhomResponse(l)).toList();
    }

}
