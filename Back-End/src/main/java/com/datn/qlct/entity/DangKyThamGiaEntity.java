package com.datn.qlct.entity;

import java.time.LocalDate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.datn.qlct.enums.TrangThaiDangKiThamGiaEnum;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "dangkythamgia")
@Getter
@Setter
@EntityListeners(AuditingEntityListener.class)
public class DangKyThamGiaEntity {
    @EmbeddedId
    private DangKiThamGiaCompositeID id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "trang_thai_dang_ki_tham_gia", nullable = false)
    private TrangThaiDangKiThamGiaEnum trangThaiDangKiThamGiaEnum;

    private String lyDoTuChoi;

    @CreatedDate
    private LocalDate ngayDangKy;

    private LocalDate ngayDuyet;

    private LocalDate ngayTuChuoi;

}
