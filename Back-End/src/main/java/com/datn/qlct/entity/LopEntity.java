package com.datn.qlct.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "lop")
@Getter
@Setter
public class LopEntity {
    @Id
    @Column(name = "malop")
    private String maLop;

    @Column(name = "tenlop")
    private String tenLop;

}
