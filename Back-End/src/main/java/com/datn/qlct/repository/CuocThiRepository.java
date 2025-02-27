package com.datn.qlct.repository;

import com.datn.qlct.entity.CuocThiEntity;
import jakarta.persistence.LockModeType;
import jakarta.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CuocThiRepository extends JpaRepository<CuocThiEntity, Long> {
    // @Lock(LockModeType.PESSIMISTIC_READ)
    @Query("SELECT c FROM CuocThiEntity c WHERE c.tenCuocThi = :tenCuocThi AND c.deleted = false")
    List<CuocThiEntity> findAllByTenCuocThi(@Param("tenCuocThi") String tenCuocThi);

    @Query("SELECT c FROM CuocThiEntity c WHERE c.loaiCuocThi.id != 3 AND c.deleted = false")
    List<CuocThiEntity> findAllCuocThi();

    @Query("SELECT c FROM CuocThiEntity c WHERE c.loaiCuocThi.id = :maLoaiCuocThi AND c.deleted = false")
    List<CuocThiEntity> findAllByMaLoaiCuocThi(@Param("maLoaiCuocThi") Integer maLoaiCuocThi);

    @Modifying
    @Transactional
    @Query("UPDATE CuocThiEntity c SET c.deleted = true WHERE c.id = :id")
    void softDeleteById(Long id);
}
