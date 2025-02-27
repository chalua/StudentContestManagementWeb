package com.datn.qlct.repository;

import com.datn.qlct.entity.CuocThiEntity;
import com.datn.qlct.entity.ThanhVienNhomCompositeID;
import com.datn.qlct.entity.ThanhVienNhomEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ThanhVienNhomRepository extends JpaRepository<ThanhVienNhomEntity, ThanhVienNhomCompositeID> {
    @Query("Select tv from ThanhVienNhomEntity tv where tv.id.sinhVien.maSinhVien = :maSinhVien")
    List<ThanhVienNhomEntity> findAllByMaSinhVien(@Param("maSinhVien") String maSinhVien);

    @Query("Select tv from ThanhVienNhomEntity tv where tv.id.nhom.maNhom = :maNhom")
    List<ThanhVienNhomEntity> findAllByMaNhom(@Param("maNhom") String maNhom);

    @Query("Select tv from ThanhVienNhomEntity tv where tv.id.nhom.maNhom = :maNhom AND tv.id.sinhVien.maSinhVien = :maSinhVien")
    Optional<ThanhVienNhomEntity> findOneByMaNhomAndMaSinhVien(@Param("maNhom") String maNhom,
            @Param("maSinhVien") String maSinhVien);
    // ThanhVienNhomEntity findByMaNhom_MaNhomAndMaSinhVien_MaSinhVien(String id,
    // String maSinhVien);
    //
    // List<ThanhVienNhomEntity>findAllByMaNhom_MaNhom(String maNhom);

    List<ThanhVienNhomEntity> findByIdSinhVienMaSinhVien(String maSinhVien);
}
