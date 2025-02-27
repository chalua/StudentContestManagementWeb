package com.datn.qlct.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "hocvi")
@Getter
@Setter
public class HocViEntity {
    @Id
    @Column(name = "mahocvi", updatable = false, nullable = false)
    private String maHocVi;

    @Column(name = "tenhocvi")
    private String tenHocVi;
}
