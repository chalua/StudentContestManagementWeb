package com.datn.qlct.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.modelmapper.internal.bytebuddy.asm.Advice;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDate;
import java.util.*;

@Entity
@Table(name = "sinhvien")
@Getter
@Setter
public class SinhVienEntity {
    @Id
    @Column(name = "mssv")
    private String maSinhVien;

    @Column(name = "hoten")
    private String hoTen;

    @Column(name = "ngaysinh")
    private Date ngaySinh;

    @Column(name = "email")
    private String email;

    @Column(name = "diaChi")
    private String diaChi;

    @Column(name = "quequan")
    private String queQuan;

    @Column(name = "gioitinh")
    private Integer gioiTinh;

    @Column(name = "sodienthoai")
    private String soDienThoai;

    @Column(name = "noisinh")
    private String noiSinh;

    @Column(name = "khoahoc")
    private String khoaHoc;

    @Column(name = "bacdaotao")
    private String bacDaoTao;

    @Column(name = "loaihinhdaotao")
    private String loaiHinhDaoTao;

    @ManyToOne
    @JoinColumn(name = "machuyennganh")
    private ChuyenNganhEntity maChuyenNganh;

    @Column(name = "password")
    @JsonIgnore()
    private String password;

    @ManyToOne
    @JoinColumn(name = "malop")
    private LopEntity lop;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        // authorityList.add(new
        // SimpleGrantedAuthority("ROLE_"+getRole().getName().toUpperCase()));
        authorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorityList;
    }
}
