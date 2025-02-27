package com.datn.qlct.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "ketquavongthi")
@Getter
@Setter
public class KetQuaVongThiEntity {

    @EmbeddedId
    private KetQuaVongThiCompositeID id;
    @Column(name = "ketqua")
    private Float ketQua;

    @ManyToOne
    @JoinColumn(name = "magiaithuong")
    private GiaiThuongEntity maGiaiThuong;

    private boolean dat;

}
