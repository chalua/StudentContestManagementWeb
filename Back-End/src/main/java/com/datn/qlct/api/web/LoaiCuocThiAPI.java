package com.datn.qlct.api.web;

import com.datn.qlct.constant.SystemConstant;
import com.datn.qlct.dto.response.LoaiCuocThiResponse;
import com.datn.qlct.service.Impl.LoaiCuocThiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(SystemConstant.LOAI_CUOC_THI_REQUEST_MAPPING)
public class LoaiCuocThiAPI {

    @Autowired
    private LoaiCuocThiService loaiCuocThiService;

    @GetMapping
    public ResponseEntity<List<LoaiCuocThiResponse>> getAllLoaiCuocThi() {
        return ResponseEntity.ok(loaiCuocThiService.getAllLoaiCuocThi());
    }
}
