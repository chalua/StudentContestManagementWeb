package com.datn.qlct.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="giaithuong")
@Getter
@Setter
public class GiaiThuongEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer maGiaiThuong;

    @Column(name = "tengiaithuong")
    private String tenGiaiThuong;

    @Column(name = "tienthuong")
    private Long tienThuong;

}
