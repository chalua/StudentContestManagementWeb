package com.datn.qlct.repository;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.datn.qlct.entity.CuocThiEntity;
import com.datn.qlct.entity.VongThiEntity;

public interface VongThiRepository extends JpaRepository<VongThiEntity, String> {
    @Query("SELECT v.maVongThi FROM VongThiEntity v ORDER BY v.maVongThi DESC LIMIT 1")
    String findLatestMaVongThi();

    @Query("SELECT v FROM VongThiEntity v WHERE v.cuocThi.id = :cuocThiId ORDER BY v.ngayBatDau ASC")
    List<VongThiEntity> findAllByCuocThiId(Long cuocThiId);

    @Query("SELECT v FROM VongThiEntity v WHERE v.cuocThi.id = :cuocThiId AND v.maVongThi = :maVongThi")
    Optional<VongThiEntity> findByCuocThiIdAndMaVongThi(@Param("cuocThiId") Long cuocThiId,
            @Param("maVongThi") String maVongThi);

    @Query("SELECT v FROM VongThiEntity v WHERE v.cuocThi.id = :maCuocThi AND :currentTime BETWEEN v.ngayBatDau AND v.ngayKetThuc")
    List<VongThiEntity> findCurrentVongThi(@Param("maCuocThi") Long maCuocThi, @Param("currentTime") Date currentTime);

    VongThiEntity findFirstByCuocThiOrderByNgayKetThucDesc(CuocThiEntity cuocThi);
}
