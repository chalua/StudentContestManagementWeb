package com.datn.qlct.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "chuyennganh")
@Getter
@Setter
public class ChuyenNganhEntity {
    @Id
    @Column(name = "machuyennganh")
    private String maChuyenNganh;

    @Column(name = "tenchuyennganh")
    private String tenChuyenNganh;
}
