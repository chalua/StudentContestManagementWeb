package com.datn.qlct.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.datn.qlct.entity.DangKiThamGiaNCKHCompositeID;
import com.datn.qlct.entity.DangKyThamGiaEntity;
import com.datn.qlct.entity.DangKyThamGiaNCKHEntity;

public interface DangKyThamGIaNCKHRepository
                extends JpaRepository<DangKyThamGiaNCKHEntity, DangKiThamGiaNCKHCompositeID> {

        @Query("SELECT d FROM DangKyThamGiaNCKHEntity d WHERE d.id.maDeTai.id LIKE :maDeTai")
        Optional<DangKyThamGiaNCKHEntity> findOneByMaDeTai(@Param("maDeTai") String maDeTai);

        @Query("SELECT d FROM DangKyThamGiaNCKHEntity d WHERE d.id.maDeTai.id LIKE :maDeTai")
        List<DangKyThamGiaNCKHEntity> findAllByMaDeTai(@Param("maDeTai") String maDeTai);

        @Query("SELECT d FROM DangKyThamGiaNCKHEntity d WHERE d.id.maNhomSinhVien.id = :maNhom")
        Optional<DangKyThamGiaNCKHEntity> findByMaNhom(@Param("maNhom") String maNhom);

        @Query("SELECT d FROM DangKyThamGiaNCKHEntity d WHERE d.id.maNhomSinhVien.maNhom = :maNhom AND d.id.maDeTai.maDeTai = :maDeTai")
        Optional<DangKyThamGiaNCKHEntity> findOneByMaNhomAndMaDeTai(@Param("maNhom") String maNhom,
                        @Param("maDeTai") String maDeTai);

        @Query("SELECT dkt FROM DangKyThamGiaNCKHEntity dkt " +
                        "JOIN dkt.id.maDeTai ct " +
                        "WHERE ct.id = :maDeTai")
        List<DangKyThamGiaNCKHEntity> getDsDkThamGia(@Param("maDeTai") String maDeTai);
}
