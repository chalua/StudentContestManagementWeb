package com.datn.qlct.service.Impl;

import com.datn.qlct.converter.ThongBaoConveter;
import com.datn.qlct.dto.ThongBaoPutRequest;
import com.datn.qlct.dto.ThongBaoRequest;
import com.datn.qlct.dto.response.ThongBaoResponse;
import com.datn.qlct.entity.ThongBaoEntity;
import com.datn.qlct.repository.ThongBaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ThongBaoService {

    @Autowired
    private ThongBaoRepository thongBaoRepository;

    @Autowired
    private ThongBaoConveter thongBaoConveter;

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    public ThongBaoResponse createThongBao(ThongBaoRequest request) {
        try {
            ThongBaoEntity thongBao = thongBaoConveter.toEntity(request);

            ThongBaoEntity savedThongBao = thongBaoRepository.save(thongBao);

            ThongBaoResponse thongBaoResponse = thongBaoConveter.toResponse(savedThongBao);

            messagingTemplate.convertAndSend("/topic/notifications", thongBaoResponse);

            return thongBaoResponse;
        } catch (DataAccessException e) {
            throw new RuntimeException("Lỗi khi lưu thông báo: " + e.getMessage(), e);
        }
    }

    public ThongBaoResponse getThongBao(Integer id) {
        return thongBaoRepository.findById(id)
                .map(thongBaoConveter::toResponse)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy thông báo với id: " + id));
    }

    public List<ThongBaoResponse> getAllThongBao() {
        return thongBaoConveter.toResponse(thongBaoRepository.findAll());
    }

    public ThongBaoResponse updateThongBao(ThongBaoPutRequest request) {
        try {
            ThongBaoEntity thongBao = thongBaoConveter.toEntity(request);

            ThongBaoEntity savedThongBao = thongBaoRepository.save(thongBao);

            ThongBaoResponse thongBaoResponse = thongBaoConveter.toResponse(savedThongBao);

            messagingTemplate.convertAndSend("/topic/notifications", thongBaoResponse);

            return thongBaoResponse;
        } catch (DataAccessException e) {
            throw new RuntimeException("Lỗi khi cập nhật thông báo: " + e.getMessage(), e);
        }
    }

    public boolean deleteThongBao(Integer id) {
        try {
            thongBaoRepository.deleteById(id);
            return true;
        } catch (DataAccessException e) {
            throw new RuntimeException("Lỗi khi xóa thông báo: " + e.getMessage(), e);
        }
    }
}
