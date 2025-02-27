package com.datn.qlct.repository;

import com.datn.qlct.entity.DeTaiEntity;
import com.datn.qlct.entity.GiangVienEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface DeTaisRepository extends JpaRepository<DeTaiEntity, String> {
    @Query("SELECT d FROM DeTaiEntity d JOIN d.cuocThi c WHERE d.giangVien.maGiangVien = :maGV")
    List<DeTaiEntity> findByGiangVien(String maGV);

    @Query("SELECT d FROM DeTaiEntity d JOIN d.cuocThi")
    List<DeTaiEntity> findAll();

    @Query("SELECT d FROM DeTaiEntity d WHERE d.maDeTai = :maDeTai")
    Optional<DeTaiEntity> findOneByMaDeTai(String maDeTai);

    @Query("SELECT d FROM DeTaiEntity d WHERE d.cuocThi.id = :cuocThiId")
    List<DeTaiEntity> findAllByCuocThiId(@Param("cuocThiId") Long cuocThiId);
}
