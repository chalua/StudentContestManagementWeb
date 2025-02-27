package com.datn.qlct.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cuocthi")
@Getter
@Setter
public class CuocThiEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "tencuocthi")
    private String tenCuocThi;

    @Column(name = "soluongvongthi")
    private Integer soLuongVongThi;

    @Column(name = "ngaybatdau")
    private Date ngayBatDau;

    @Column(name = "ngayketthuc")
    private Date ngayKetThuc;

    @Column(name = "soluongnhom")
    private Long soLuongNhom;

    @Column(name = "mota", columnDefinition = "text")
    private String moTa;

    @Column(name = "trangthai", insertable = false, updatable = false)
    @Transient
    private String trangThai;

    @Column(name = "chude")
    private String chuDe;

    @ManyToOne
    @JoinColumn(name = "maloaicuocthi")
    private LoaiCuocThiEntity loaiCuocThi;

    @Column(name = "deleted", nullable = false)
    private Boolean deleted = false;

    @ManyToMany
    @JoinTable(name = "cuocthi_giangvien", joinColumns = @JoinColumn(name = "cuocthi_id"), inverseJoinColumns = @JoinColumn(name = "magiangvien"))
    private Set<GiangVienEntity> giangViens = new HashSet<>();

    @OneToMany(mappedBy = "cuocThi")
    private Set<DeTaiEntity> dsDeTai;

    public String getTrangThai() {
        Date now = new Date();

        // Kiểm tra nếu ngày bắt đầu không null
        if (ngayBatDau != null) {
            long diffInMillies = ngayBatDau.getTime() - now.getTime();
            long diffDays = diffInMillies / (1000 * 60 * 60 * 24);

            // Kiểm tra trạng thái "Đã khóa"
            if (diffDays >= 0 && diffDays <= 7) {
                return "Đã khóa";
            } else if (diffDays > 7) {
                return "Chưa diễn ra";
            }
        }

        // Kiểm tra các trạng thái khác
        if (ngayBatDau != null && ngayKetThuc != null &&
                ngayBatDau.before(now) && ngayKetThuc.after(now)) {
            return "Đang diễn ra";
        } else if (ngayKetThuc != null && ngayKetThuc.before(now)) {
            return "Kết thúc";
        }

        return "Không xác định";
    }
}
