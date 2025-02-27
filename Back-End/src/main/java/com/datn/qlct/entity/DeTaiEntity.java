package com.datn.qlct.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.datn.qlct.enums.TrangThaiDuyetDeTaiEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "detai")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class DeTaiEntity {
    @Id
    @Column(name = "madetai", updatable = false, nullable = false)
    private String maDeTai;

    @Column(name = "tendetai")
    private String tenDeTai;

    @Column(name = "mota", columnDefinition = "TEXT")
    private String moTa;

    @ManyToOne(fetch = FetchType.LAZY, cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
    @JoinColumn(name = "magiangvien")
    private GiangVienEntity giangVien;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "trang_thai_duyet_de_tai", nullable = false)
    private TrangThaiDuyetDeTaiEnum trangThaiDuyetDeTaiEnum;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ma_cuoc_thi")
    private CuocThiEntity cuocThi;

    @Column(name = "ngay_de_xuat")
    @CreatedDate
    private LocalDate ngayDeXuat;
    @Column(name = "ngay_duyet")
    private LocalDate ngayDuyet;
    @Column(name = "ngay_tu_choi")
    private LocalDate ngayTuChoi;

    private String lyDoTuChoi;
}
