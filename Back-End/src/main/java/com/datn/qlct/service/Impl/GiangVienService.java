package com.datn.qlct.service.Impl;

import com.datn.qlct.components.JwtTokenUtil;
import com.datn.qlct.converter.CuocThiConveter;
import com.datn.qlct.converter.GiangVienConverter;
import com.datn.qlct.dto.CapNhatTrangThaiDTO;
import com.datn.qlct.dto.ChangePasswordDTO;
import com.datn.qlct.dto.DangKyDeTaiDTO;
import com.datn.qlct.dto.GiangVienDTO;
import com.datn.qlct.dto.response.CuocThiResponse;
import com.datn.qlct.dto.response.DangKyThamGiaNhomResponse;
import com.datn.qlct.dto.response.GiangVienRespones;
import com.datn.qlct.entity.*;
import com.datn.qlct.enums.TrangThaiDangKiThamGiaEnum;
import com.datn.qlct.repository.DangKyThamGiaRepository;
import com.datn.qlct.exception.DataNotFoundException;
import com.datn.qlct.repository.GiangVienRepository;
import com.datn.qlct.repository.HocHamRepository;
import com.datn.qlct.repository.HocViRepository;
import com.datn.qlct.service.IGiangVienService;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class GiangVienService implements IGiangVienService {
    private String index = "GV";
    @Autowired
    private GiangVienRepository giangVienRepository;
    @Autowired
    private GiangVienConverter giangVienConverter;
    @Autowired
    private DangKyThamGiaRepository dangKyThamGiaRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PrefixTrackerService prefixTrackerService;

    @Autowired
    private CuocThiConveter cuocThiConveter;
    @Autowired
    private HocViRepository hocViRepository;
    @Autowired
    private HocHamRepository hocHamRepository;
    private final JwtTokenUtil jwtTokenUtil;
    private final PasswordEncoder passwordEncoder;

    public List<GiangVienRespones> getAllGV() {
        List<GiangVienEntity> giangViens = giangVienRepository.findAll();

        return giangViens.stream()
                .map(gv -> modelMapper.map(gv, GiangVienRespones.class))
                .collect(Collectors.toList());
    }

    public List<CuocThiResponse> getCuocThiByGiangVien(String maGiangVien) {
        GiangVienEntity giangVien = giangVienRepository.findById(maGiangVien)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy giảng viên"));

        Set<CuocThiEntity> cuocThis = giangVien.getCuocThis();

        return cuocThis.stream()
                .map(cuocThi -> cuocThiConveter.toCuocThiResponse(cuocThi))
                .collect(Collectors.toList());

    }

    @Override
    @Transactional
    public void addAndUpdateGiangVien(GiangVienDTO giangVienDTO) {
        GiangVienEntity result = modelMapper.map(giangVienDTO, GiangVienEntity.class);
        if (result.getMaGiangVien() == null) {
            result.setMaGiangVien(prefixTrackerService.getNext(index));
        }
        if (giangVienDTO.getHocVi() == null || giangVienDTO.getHocVi().equals("")) {
            result.setMaHocVi(null);
        } else {
            result.setMaHocVi(hocViRepository.findById(giangVienDTO.getHocVi()).get());
        }
        if (giangVienDTO.getHocHam() == null || giangVienDTO.getHocHam().equals("")) {
            result.setMaHocHam(null);
        } else {
            result.setMaHocHam(hocHamRepository.findById(giangVienDTO.getHocHam()).get());
        }

        giangVienRepository.save(result);
    }

    @Transactional
    public void updateTrangThai(CapNhatTrangThaiDTO dto) {
        boolean giangVienExists = dangKyThamGiaRepository.existsGiangVienInCuocThi(dto.getMaCuocThi(),
                dto.getMaGiangVien());
        if (!giangVienExists) {
            throw new RuntimeException("Giảng viên này không có quyền duyệt");
        }

        DangKyThamGiaEntity dangKyThamGia = dangKyThamGiaRepository
                .findByCuocThiAndNhom(dto.getMaCuocThi(), dto.getMaNhom())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đăng ký tham gia với cuộc thi và nhóm này"));

        dangKyThamGia.setTrangThaiDangKiThamGiaEnum(dto.getTrangThai());

        if (dto.getTrangThai() == TrangThaiDangKiThamGiaEnum.DA_DUYET) {
            dangKyThamGia.setNgayDuyet(LocalDate.now());
            dangKyThamGia.setNgayTuChuoi(null);
        }

        if (dto.getTrangThai() == TrangThaiDangKiThamGiaEnum.TU_CHOI) {
            dangKyThamGia.setNgayTuChuoi(LocalDate.now());
            dangKyThamGia.setNgayDuyet(null);
        }

        dangKyThamGiaRepository.save(dangKyThamGia);
    }

    public List<DangKyThamGiaNhomResponse> getDsDkThamGia(Long maCuocThi) {
        List<DangKyThamGiaEntity> dangKyThamGiaEntities = dangKyThamGiaRepository.getDsDkThamGia(maCuocThi);

        return dangKyThamGiaEntities.stream()
                .map(dangKy -> {
                    DangKyThamGiaNhomResponse dto = new DangKyThamGiaNhomResponse();
                    dto.setMaNhom(dangKy.getId().getMaNhomSinhVien().getMaNhom());
                    dto.setTenNhom(dangKy.getId().getMaNhomSinhVien().getTenNhom());
                    dto.setMaCuocThi(dangKy.getId().getMaCuocThi().getId());
                    dto.setTrangThai(dangKy.getTrangThaiDangKiThamGiaEnum());
                    dto.setNgayDangKy(dangKy.getNgayDangKy());
                    dto.setNgayDuyet(dangKy.getNgayDuyet());
                    dto.setNgayTuChoi(dangKy.getNgayTuChuoi());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public String login(String username, String password) throws Exception {
        Optional<GiangVienEntity> optionalUser = giangVienRepository.findById(username);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("Tài khoản không tồn tại");
        }
        GiangVienEntity existingUser = optionalUser.get();
        if (!passwordEncoder.matches(password, existingUser.getPassword())) {
            throw new RuntimeException("Sai mật khẩu");
        }

        // Generate token
        String token = jwtTokenUtil.generateTokenGV(existingUser);

        // Convert entity to Map excluding password
        // ObjectMapper mapper = new ObjectMapper();
        // Map<String, Object> userData = mapper.convertValue(existingUser, Map.class);

        // Return token and user data
        return token;
    }

    @Transactional
    public void changePassword(String maGV, ChangePasswordDTO changePasswordDTO) {
        Optional<GiangVienEntity> optionalUser = giangVienRepository.findById(maGV);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("Mã giảng viên không tồn tại");
        }

        GiangVienEntity existingUser = optionalUser.get();

        if (!passwordEncoder.matches(changePasswordDTO.getOldPassword(), existingUser.getPassword())) {
            throw new RuntimeException("Mật khẩu cũ không chính xác");
        }

        if (!changePasswordDTO.getNewPassword().equals(changePasswordDTO.getConfirmPassword())) {
            throw new RuntimeException("Xác nhận mật khẩu không khớp");
        }

        String encodedPassword = passwordEncoder.encode(changePasswordDTO.getNewPassword());
        existingUser.setPassword(encodedPassword);
        giangVienRepository.save(existingUser);
    }

    @Override
    public void updatePassword(String maGV, String newPassword) throws Exception {
        Optional<GiangVienEntity> optionalUser = giangVienRepository.findById(maGV);
        if (optionalUser.isEmpty()) {
            throw new DataNotFoundException("Mã sinh viên không tồn tại");
        }
        GiangVienEntity existingUser = optionalUser.get();
        String encodedPassword = passwordEncoder.encode(newPassword);
        existingUser.setPassword(encodedPassword);
        giangVienRepository.save(existingUser);
    }

    @Override
    public GiangVienRespones getCurrentUser(HttpServletRequest request) {
        // Get the Bearer token from the Authorization header
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new SecurityException("Token không hợp lệ");
        }

        String token = authorizationHeader.substring(7); // Remove "Bearer " prefix

        // Extract username (or user ID) from token
        String maGiangVien = jwtTokenUtil.getUsernameGVFromToken(token);

        // Retrieve user from database
        Optional<GiangVienEntity> optionalUser = giangVienRepository.findById(maGiangVien);
        if (optionalUser.isEmpty()) {
            throw new DataNotFoundException("Người dùng không tồn tại");
        }
        GiangVienEntity user = optionalUser.get();
        // Hibernate.initialize(user.getMaHocHam()); // Initialize maHocHam, if needed
        // Hibernate.initialize(user.getMaHocVi());
        // Convert user entity to map (excluding password)
        // ObjectMapper mapper = new ObjectMapper();
        // mapper.registerModule(new JavaTimeModule());
        // mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        return modelMapper.map(user, GiangVienRespones.class);
    }

    public void saveGiangVien(MultipartFile file) {
        if (ExcelUploadService.isValodExcelFile(file)) {
            try {
                List<GiangVienDTO> giangVienDTOS = ExcelUploadService.getGiangVienDataFromExcel(file.getInputStream());
                for (GiangVienDTO gv : giangVienDTOS) {
                    if (gv.getGioiTinh().equals("Nam") || gv.getGioiTinh().equals("nam")) {
                        gv.setGioiTinh("1");
                    }
                    if (gv.getGioiTinh().equals("Nữ") || gv.getGioiTinh().equals("nữ")) {
                        gv.setGioiTinh("0");
                    }
                    String encodedPassword = passwordEncoder.encode(gv.getPassword());
                    gv.setPassword(encodedPassword);

                    Optional<HocHamEntity> optionalHocHam = gv.getHocHam().equals("")?null:hocHamRepository.findByTenHocHamEqualsIgnoreCase(gv.getHocHam());
                    HocHamEntity hocHamEntity;
                    if(!gv.getHocHam().equals("")){
                        if (optionalHocHam.isPresent()) {
                            hocHamEntity = optionalHocHam.get();
                        } else {
                            hocHamEntity = new HocHamEntity();
                            String[] words = gv.getHocHam().split("\\s+"); // Split the string by whitespace
                            StringBuilder abbreviation = new StringBuilder();

                            for (String word : words) {
                                if (!word.isEmpty()) { // Ensure no empty strings are processed
                                    abbreviation.append(word.charAt(0)); // Append the first character of each word
                                }
                            }

                            hocHamEntity.setMaHocHam(abbreviation.toString().toUpperCase());
                            hocHamEntity.setTenHocHam(gv.getHocHam());
                            hocHamRepository.save(hocHamEntity);
                        }
                    }

                    Optional<HocViEntity> optionalHocVi = gv.getHocVi().equals("")? null: hocViRepository.findByTenHocViEqualsIgnoreCase(gv.getHocVi());
                    HocViEntity hocViEntity;

                    if(!gv.getHocVi().equals("")){
                        if (optionalHocVi.isPresent()) {
                            hocViEntity = optionalHocVi.get();
                        } else {
                            hocViEntity = new HocViEntity();
                            String[] words = gv.getHocVi().split("\\s+"); // Split the string by whitespace
                            StringBuilder abbreviation = new StringBuilder();

                            for (String word : words) {
                                if (!word.isEmpty()) { // Ensure no empty strings are processed
                                    String wordUpper = word.toUpperCase();

                                    // Handle specific cases
                                    if (wordUpper.equals("TIẾN") || wordUpper.equals("SĨ")) {
                                        abbreviation.setLength(0);
                                        abbreviation.append("TS");
                                        break; // Exit loop since TS is determined
                                    } else if (wordUpper.equals("THẠC") || wordUpper.equals("SĨ")) {
                                        abbreviation.setLength(0);
                                        abbreviation.append("ThS");
                                        break;
                                    } else {
                                        abbreviation.append(word.charAt(0));
                                    }
                                }
                            }

                            hocViEntity.setMaHocVi(abbreviation.toString().toUpperCase());
                            hocViEntity.setTenHocVi(gv.getHocVi());
                            hocViRepository.save(hocViEntity);
                        }
                    }

                    giangVienRepository.save(giangVienConverter.convertToEntity(gv));
                }
            } catch (IOException e) {
                throw new IllegalArgumentException("Tệp Excel không hợp lệ");
            }
        }
    }
}
