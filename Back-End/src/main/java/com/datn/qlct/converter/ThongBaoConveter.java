package com.datn.qlct.converter;

import com.datn.qlct.dto.ThongBaoPutRequest;
import com.datn.qlct.dto.ThongBaoRequest;
import com.datn.qlct.dto.response.ThongBaoResponse;
import com.datn.qlct.repository.ThongBaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import com.datn.qlct.entity.ThongBaoEntity;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Base64;

@Component
public class ThongBaoConveter {

    @Autowired
    private ThongBaoRepository thongBaoRepository;

    public ThongBaoEntity toEntity(ThongBaoRequest request) {
        ThongBaoEntity thongBao = new ThongBaoEntity();
        thongBao.setTieuDe(request.getTieuDe());
        thongBao.setNoiDung(request.getNoiDung());
        thongBao.setNgayTao(Date.from(new Date().toInstant()));
        try {
            if (request.getHinhAnh() != null) {
                thongBao.setHinhAnh(request.getHinhAnh().getBytes());
            }
            if (request.getFile() != null) {
                thongBao.setFile(request.getFile().getBytes());
            }
        } catch (IOException e) {
            throw new RuntimeException("Error converting MultipartFile to byte array", e);
        }
        return thongBao;
    }

    public ThongBaoResponse toResponse(ThongBaoEntity entity) {
        ThongBaoResponse response = new ThongBaoResponse();
        response.setId(entity.getId());
        response.setTieuDe(entity.getTieuDe());
        response.setNoiDung(entity.getNoiDung());
        response.setHinhAnhBase64(entity.getHinhAnh() != null
                ? Base64.getEncoder().encodeToString(entity.getHinhAnh())
                : null);
        response.setFileBase64(entity.getFile() != null
                ? Base64.getEncoder().encodeToString(entity.getFile())
                : null);
        response.setNgayTao(entity.getNgayTao());
        response.setNgayChinhSua(entity.getNgayChinhSua() != null ? entity.getNgayChinhSua() : null);
        return response;
    }

    public List<ThongBaoResponse> toResponse(List<ThongBaoEntity> entities) {
        return entities.stream().map(this::toResponse).toList();
    }

    public ThongBaoEntity toEntity(ThongBaoPutRequest request) {
        ThongBaoEntity thongBao = thongBaoRepository.findById(request.getId())
                .orElseThrow(() -> new RuntimeException("Không tìm thấy thông báo với id: " + request.getId()));
        thongBao.setTieuDe(request.getTieuDe());
        thongBao.setNoiDung(request.getNoiDung());
        thongBao.setNgayChinhSua(Date.from(new Date().toInstant()));
        try {
            if (request.getHinhAnh() != null) {
                thongBao.setHinhAnh(request.getHinhAnh().getBytes());
            }
            if (request.getFile() != null) {
                thongBao.setFile(request.getFile().getBytes());
            }
        } catch (IOException e) {
            throw new RuntimeException("Error converting MultipartFile to byte array", e);
        }
        return thongBao;
    }
}
