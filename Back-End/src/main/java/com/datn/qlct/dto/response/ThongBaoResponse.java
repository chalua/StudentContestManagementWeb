package com.datn.qlct.dto.response;

import lombok.Data;
import java.util.Date;

@Data
public class ThongBaoResponse {
    private Integer id;
    private String tieuDe;
    private String noiDung;
    private String hinhAnhBase64;
    private String fileBase64;
    private Date ngayTao;
    private Date ngayChinhSua;
}
