package com.datn.qlct.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "hocham")
@Setter
@Getter
public class HocHamEntity {
    @Id
    @Column(name = "mahocham", updatable = false, nullable = false)
    private String maHocHam;

    @Column(name = "tenhocham")
    private String tenHocHam;
}
