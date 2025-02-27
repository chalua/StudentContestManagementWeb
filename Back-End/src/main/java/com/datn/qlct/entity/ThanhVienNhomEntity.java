package com.datn.qlct.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "thanhviennhom")
@Getter
@Setter
public class ThanhVienNhomEntity {
    @EmbeddedId
    private ThanhVienNhomCompositeID id;

    @Column(name = "nhomtruong")
    private Integer nhomTruong;

}
