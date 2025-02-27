package com.datn.qlct.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.datn.qlct.entity.NhomSinhVienEntity;

public interface NhomSinhVienRepository extends JpaRepository<NhomSinhVienEntity, String> {
    @Query("SELECT n FROM NhomSinhVienEntity n WHERE n.maNhom = :maNhom")
    Optional<NhomSinhVienEntity> findOneByMaNhom(String maNhom);

    @Query("SELECT n FROM NhomSinhVienEntity n JOIN ThanhVienNhomEntity t ON n.maNhom = t.id.nhom.maNhom WHERE t.id.sinhVien.maSinhVien = :mssv")
    List<NhomSinhVienEntity> findGroupsByStudentId(@Param("mssv") String mssv);
}
