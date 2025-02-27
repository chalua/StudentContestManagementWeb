package com.datn.qlct.repository;

import com.datn.qlct.entity.LoiMoiThamGiaNhomCompositeID;
import com.datn.qlct.entity.LoiMoiThamGiaNhomEntity;
import com.datn.qlct.enums.TrangThaiLoiMoiEnum;

import jakarta.transaction.Transactional;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface LoiMoiThamGiaNhomRepository
                extends JpaRepository<LoiMoiThamGiaNhomEntity, LoiMoiThamGiaNhomCompositeID> {

        @Query("SELECT l FROM LoiMoiThamGiaNhomEntity l WHERE l.id.nhomSinhVienEntity.maNhom = :maNhom AND l.id.sinhVienEntity.maSinhVien = :maSinhVien")
        LoiMoiThamGiaNhomEntity findByMaNhomAndMaSinhVien(@Param("maNhom") String maNhom,
                        @Param("maSinhVien") String maSinhVien);

        @Query("SELECT l FROM LoiMoiThamGiaNhomEntity l WHERE l.id.sinhVienEntity.maSinhVien = :maSinhVien AND l.trangThaiLoiMoiEnum = 'DA_MOI'")
        List<LoiMoiThamGiaNhomEntity> findAllLoiMoi(@Param("maSinhVien") String maSinhVien);

        @Modifying
        @Transactional
        @Query("DELETE FROM LoiMoiThamGiaNhomEntity lm WHERE lm.id.nhomSinhVienEntity.maNhom = :maNhom")
        void deleteByMaNhom(@Param("maNhom") String maNhom);

        @Modifying
        @Query("UPDATE LoiMoiThamGiaNhomEntity l SET l.trangThaiLoiMoiEnum = :trangThai "
                        + "WHERE l.id.sinhVienEntity.maSinhVien = :maSinhVien "
                        + "AND l.id.nhomSinhVienEntity.maNhom != :maNhom "
                        + "AND l.trangThaiLoiMoiEnum = 'DA_MOI'")
        void tuChoiLoiMoiKhac(@Param("maSinhVien") String maSinhVien,
                        @Param("maNhom") String maNhom,
                        @Param("trangThai") TrangThaiLoiMoiEnum trangThai);

}
