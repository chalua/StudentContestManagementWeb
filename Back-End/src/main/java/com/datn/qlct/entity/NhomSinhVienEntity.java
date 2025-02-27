package com.datn.qlct.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.GenericGenerator;

import com.datn.qlct.enums.LoaiNhomEnum;

@Entity
@Table(name = "nhomsinhvien")
@Getter
@Setter
public class NhomSinhVienEntity {
    @Id
    @Column(name = "manhom")
    private String maNhom;

    @Column(name = "tenNhom")
    private String tenNhom;

    @Column(name = "soluongthanhvien")
    private Integer soLuongThanhVien;

    @Enumerated(EnumType.STRING)
    @Column(name = "loaiNhom")
    private LoaiNhomEnum loaiNhom;

    @OneToMany(mappedBy = "id.nhom", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ThanhVienNhomEntity> thanhVienNhoms = new ArrayList<>();
}
