package com.datn.qlct.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.List;

@Entity
@Table(name = "giangvien")
@Getter
@Setter
public class GiangVienEntity {
    @Id
    @Column(name = "magiangvien")
    private String maGiangVien;

    @Column(name = "tengiangvien")
    private String tenGiangVien;

    @Column(name = "ngaysinh")
    private Date ngaySinh;

    @Column(name = "sodienthoai")
    private String soDienThoai;

    @Column(name = "gioitinh")
    private Integer gioiTinh;

    @Column(name = "quequan")
    private String queQuan;

    @Column(name = "password")
    private String password;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
    @JoinColumn(name = "mahocham")
    private HocHamEntity maHocHam;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
    @JoinColumn(name = "mahocvi")
    private HocViEntity maHocVi;

    @ManyToMany(mappedBy = "giangViens")
    private Set<CuocThiEntity> cuocThis = new HashSet<>();

    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorityList = new ArrayList<>();
        // authorityList.add(new
        // SimpleGrantedAuthority("ROLE_"+getRole().getName().toUpperCase()));
        authorityList.add(new SimpleGrantedAuthority("ROLE_USER"));
        return authorityList;
    }
}
