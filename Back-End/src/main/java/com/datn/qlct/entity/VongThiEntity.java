package com.datn.qlct.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "vongthi")
@Getter
@Setter
public class VongThiEntity {
    @Id
    @Column(name = "mavongthi")
    private String maVongThi;

    // @Column(name = "soluongnhom")
    // private Integer soLuongNhom;

    // @Column(name = "sothutuvongthi")
    // private Integer soThuTuVongThi;

    @Column(name = "noidung", columnDefinition = "text")
    private String noiDung;

    @Column(name = "ngaybatdau")
    private Date ngayBatDau;

    @Column(name = "ngayketthuc")
    private Date ngayKetThuc;

    // @Column(name = "trangthai")
    // private Integer trangThai;

    @ManyToOne
    @JoinColumn(name = "macuocthi")
    private CuocThiEntity cuocThi;

}
