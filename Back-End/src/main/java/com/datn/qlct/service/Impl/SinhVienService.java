package com.datn.qlct.service.Impl;

import com.datn.qlct.components.JwtTokenUtil;
import com.datn.qlct.converter.DangKyThamGiaConverter;
import com.datn.qlct.converter.DangKyThamGiaNCKHConverter;
import com.datn.qlct.converter.NhomConverter;
import com.datn.qlct.converter.SinhVienConverter;
import com.datn.qlct.dto.ChangePasswordDTO;
import com.datn.qlct.dto.SinhVienDTO;
import com.datn.qlct.dto.response.DangKyThamGiaNCKHResponse;
import com.datn.qlct.dto.response.DangKyThamGiaResponse;
import com.datn.qlct.dto.response.NhomResponse;
import com.datn.qlct.entity.*;
import com.datn.qlct.enums.LoaiNhomEnum;
import com.datn.qlct.exception.DataNotFoundException;
import com.datn.qlct.repository.*;
import com.datn.qlct.service.ISinhVienService;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class SinhVienService implements ISinhVienService {

    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final SinhVienRepository sinhVienRepository;
    private final JwtTokenUtil jwtTokenUtil;
    private final LopRepository lopRepository;
    private final ChuyenNganhRepository chuyenNganhRepository;
    // @Override
    // public String login(String mssv, String password) throws Exception {
    // Optional<SinhVienEntity> optionalUser = sinhVienRepository.findById(mssv);
    // if(optionalUser.isEmpty()) {
    // throw new DataNotFoundException("Tài khoản không tồn tại");
    // }
    // SinhVienEntity existingUser = optionalUser.get();
    // if(!passwordEncoder.matches(password, existingUser.getPassword())) {
    // throw new DataNotFoundException("Sai mật khẩu");
    // }
    // UsernamePasswordAuthenticationToken authenticationToken = new
    // UsernamePasswordAuthenticationToken(
    // mssv, password,
    // existingUser.getAuthorities()
    // );
    // return jwtTokenUtil.generateToken(optionalUser.get());//muốn trả JWT token ?
    // }

    @Autowired
    private SinhVienConverter sinhVienConverter;

    @Autowired
    private NhomConverter nhomConverter;

    @Autowired
    private NhomSinhVienRepository nhomSinhVienRepository;

    @Autowired
    private DangKyThamGIaNCKHRepository dangKyThamGIaNCKHRepository;
    @Autowired
    private DangKyThamGiaRepository dangKyThamGiaRepository;

    @Autowired
    private DangKyThamGiaNCKHConverter dangKyThamGiaNCKHConverter;
    @Autowired
    private DangKyThamGiaConverter dangKyThamGiaConverter;

    public List<DangKyThamGiaNCKHResponse> getLichSuThamGiaNCKH(String mssv) {
        List<NhomSinhVienEntity> allNhoms = nhomSinhVienRepository.findGroupsByStudentId(mssv);

        List<DangKyThamGiaNCKHResponse> allThamGiaNCKH = new ArrayList<>();

        for (NhomSinhVienEntity nhomSinhVienEntity : allNhoms) {
            if (nhomSinhVienEntity.getLoaiNhom() == LoaiNhomEnum.NGHIEN_CUU_KHOA_HOC) {
                DangKyThamGiaNCKHEntity dangKyThamGiaNCKHEntity = dangKyThamGIaNCKHRepository
                        .findByMaNhom(nhomSinhVienEntity.getMaNhom())
                        .orElseThrow(() -> new RuntimeException("Không tìm thấy nhóm"));

                DangKyThamGiaNCKHResponse dangKyThamGiaNCKHResponse = dangKyThamGiaNCKHConverter
                        .convertToResponse(dangKyThamGiaNCKHEntity);
                allThamGiaNCKH.add(dangKyThamGiaNCKHResponse);

            }

        }

        return allThamGiaNCKH;
        // return nhomConverter.toNhomResponses(allNhoms);
    }

    @Transactional
    public void changePassword(String mssv, ChangePasswordDTO changePasswordDTO) {
        Optional<SinhVienEntity> optionalUser = sinhVienRepository.findById(mssv);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("MSSV không tồn tại");
        }

        SinhVienEntity existingUser = optionalUser.get();

        if (!passwordEncoder.matches(changePasswordDTO.getOldPassword(), existingUser.getPassword())) {
            throw new RuntimeException("Mật khẩu cũ không chính xác");
        }

        if (!changePasswordDTO.getNewPassword().equals(changePasswordDTO.getConfirmPassword())) {
            throw new RuntimeException("Xác nhận mật khẩu không khớp");
        }

        String encodedPassword = passwordEncoder.encode(changePasswordDTO.getNewPassword());
        existingUser.setPassword(encodedPassword);
        sinhVienRepository.save(existingUser);
    }

    public List<DangKyThamGiaResponse> getLichSuThamGiaCuocThi(String mssv) {
        List<NhomSinhVienEntity> allNhoms = nhomSinhVienRepository.findGroupsByStudentId(mssv);

        List<DangKyThamGiaResponse> allThamGia = new ArrayList<>();

        for (NhomSinhVienEntity nhomSinhVienEntity : allNhoms) {

            if (nhomSinhVienEntity.getLoaiNhom() == LoaiNhomEnum.THI_DAU) {
                DangKyThamGiaEntity dangKyThamGiaEntity = dangKyThamGiaRepository
                        .findByMaNhom(nhomSinhVienEntity.getMaNhom())
                        .orElseThrow(() -> new RuntimeException("Không tìm thấy nhóm"));

                DangKyThamGiaResponse dangKyThamGiaNCKHResponse = dangKyThamGiaConverter
                        .convertToResponse(dangKyThamGiaEntity);
                allThamGia.add(dangKyThamGiaNCKHResponse);

            }

        }

        return allThamGia;
        // return nhomConverter.toNhomResponses(allNhoms);
    }

    @Override
    public Map<String, Object> login(String mssv, String password) throws Exception {
        Optional<SinhVienEntity> optionalUser = sinhVienRepository.findById(mssv);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("Tài khoản không tồn tại");
        }
        SinhVienEntity existingUser = optionalUser.get();
        if (!passwordEncoder.matches(password, existingUser.getPassword())) {
            throw new RuntimeException("Sai mật khẩu");
        }

        // Generate token
        String token = jwtTokenUtil.generateToken(existingUser);

        // Convert entity to Map excluding password
        ObjectMapper mapper = new ObjectMapper();
        Map<String, Object> userData = mapper.convertValue(existingUser, Map.class);

        // Return token and user data
        return Map.of("token", token);
    }

    @Override
    public Map<String, Object> getCurrentUser(HttpServletRequest request) {
        // Get the Bearer token from the Authorization header
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new SecurityException("Token không hợp lệ");
        }

        String token = authorizationHeader.substring(7); // Remove "Bearer " prefix

        // Extract username (or user ID) from token
        String mssv = jwtTokenUtil.getUsernameFromToken(token);

        // Retrieve user from database
        Optional<SinhVienEntity> optionalUser = sinhVienRepository.findById(mssv);
        if (optionalUser.isEmpty()) {
            throw new DataNotFoundException("Người dùng không tồn tại");
        }

        SinhVienEntity user = optionalUser.get();

        // Convert user entity to map (excluding password)
        ObjectMapper mapper = new ObjectMapper();
        return mapper.convertValue(user, Map.class);
    }

    @Override
    public void updatePassword(String mssv, String newPassword) throws Exception {
        Optional<SinhVienEntity> optionalUser = sinhVienRepository.findById(mssv);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("Mã sinh viên không tồn tại");
        }
        SinhVienEntity existingUser = optionalUser.get();
        String encodedPassword = passwordEncoder.encode(newPassword);
        existingUser.setPassword(encodedPassword);
        sinhVienRepository.save(existingUser);
    }

    public void saveSinhVien(MultipartFile file) {
        if (ExcelUploadService.isValodExcelFile(file)) {
            try {
                List<SinhVienDTO> sinhVienDTOList = ExcelUploadService.getSinhVienDataFromExcel(file.getInputStream());
                for (SinhVienDTO sv : sinhVienDTOList) {
                    if (sv.getGioiTinh().equals("Nam") || sv.getGioiTinh().equals("nam")) {
                        sv.setGioiTinh("1");
                    }
                    if (sv.getGioiTinh().equals("Nữ") || sv.getGioiTinh().equals("nữ")) {
                        sv.setGioiTinh("0");
                    }
                    String encodedPassword = passwordEncoder.encode(sv.getPassword());
                    sv.setPassword(encodedPassword);
                    Optional<LopEntity> optionalLop = lopRepository.findById(sv.getLop());
                    Optional<ChuyenNganhEntity> optionalChuyenNganh = chuyenNganhRepository
                            .findByTenChuyenNganhContainingIgnoreCase(sv.getNganh());
                    LopEntity lopEntity;
                    ChuyenNganhEntity chuyenNganhEntity;
                    if (optionalLop.isPresent()) {
                        lopEntity = optionalLop.get();
                    } else {
                        lopEntity = new LopEntity();
                        lopEntity.setMaLop(sv.getLop());
                        lopEntity.setTenLop("Lớp " + sv.getLop());

                        lopRepository.save(lopEntity);
                    }
                    if (optionalChuyenNganh.isPresent()) {
                        chuyenNganhEntity = optionalChuyenNganh.get();
                    } else {
                        chuyenNganhEntity = new ChuyenNganhEntity();
                        String[] words = sv.getNganh().split("\\s+"); // Split the string by whitespace
                        StringBuilder abbreviation = new StringBuilder();

                        for (String word : words) {
                            if (!word.isEmpty()) { // Ensure no empty strings are processed
                                abbreviation.append(word.charAt(0)); // Append the first character of each word
                            }
                        }

                        chuyenNganhEntity.setMaChuyenNganh(abbreviation.toString().toUpperCase());
                        chuyenNganhEntity.setTenChuyenNganh(sv.getNganh());
                        chuyenNganhRepository.save(chuyenNganhEntity);
                    }
                    sinhVienRepository.save(sinhVienConverter.convertToEntity(sv));
                }
            } catch (IOException e) {
                throw new IllegalArgumentException("Tệp Excel không hợp lệ");
            }
        }
    }

}
