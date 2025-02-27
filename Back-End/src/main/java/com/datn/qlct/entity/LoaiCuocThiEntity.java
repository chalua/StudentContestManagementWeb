package com.datn.qlct.entity;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="loaicuocthi")
@Getter
@Setter
public class LoaiCuocThiEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name= "tenloaicuocthi")
    private String tenLoaiCuocThi;
}