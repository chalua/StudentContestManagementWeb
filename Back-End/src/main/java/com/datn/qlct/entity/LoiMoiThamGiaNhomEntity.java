package com.datn.qlct.entity;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.datn.qlct.enums.TrangThaiLoiMoiEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "loi_moi_tham_gia_nhom")
@EntityListeners(AuditingEntityListener.class)
public class LoiMoiThamGiaNhomEntity {
    @EmbeddedId
    private LoiMoiThamGiaNhomCompositeID id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "trang_thai_loi_moi", nullable = false)
    private TrangThaiLoiMoiEnum trangThaiLoiMoiEnum;

    @CreatedDate
    @Column(name = "ngay_moi")
    private LocalDate ngayMoi;
}