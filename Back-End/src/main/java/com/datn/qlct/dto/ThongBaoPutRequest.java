package com.datn.qlct.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ThongBaoPutRequest {
    private Integer id;
    private String tieuDe;
    private String noiDung;
    private MultipartFile hinhAnh;
    private MultipartFile file;
}
