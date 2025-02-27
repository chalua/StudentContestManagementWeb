package com.datn.qlct.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "thong_bao")
public class ThongBaoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "tieu_de", nullable = false)
    private String tieuDe;

    @Column(name = "noi_dung", nullable = false)
    private String noiDung;

    @Lob
    @Column(name = "hinh_anh", columnDefinition = "LONGBLOB")
    private byte[] hinhAnh;

    @Lob
    @Column(name = "file", columnDefinition = "LONGBLOB")
    private byte[] file;

    @Column(name = "ngay_tao", nullable = false, updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date ngayTao;

    @Column(name = "ngay_chinh_sua")
    private Date ngayChinhSua;
}