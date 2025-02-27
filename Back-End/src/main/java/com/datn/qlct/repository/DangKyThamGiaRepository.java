package com.datn.qlct.repository;

import com.datn.qlct.entity.DangKiThamGiaCompositeID;
import com.datn.qlct.entity.DangKyThamGiaEntity;
import com.datn.qlct.entity.NhomSinhVienEntity;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DangKyThamGiaRepository extends JpaRepository<DangKyThamGiaEntity, DangKiThamGiaCompositeID> {
        @Query("SELECT d FROM DangKyThamGiaEntity d WHERE d.id.maCuocThi.id = :maCuocThi AND d.id.maNhomSinhVien.maNhom = :maNhomSinhVien")
        Optional<DangKyThamGiaEntity> findByMaCuocThiAndMaNhomSinhVien(@Param("maCuocThi") Long maCuocThi,
                        @Param("maNhomSinhVien") String maNhomSinhVien);

        @Query("SELECT COUNT(gv) > 0 FROM CuocThiEntity ct " +
                        "JOIN ct.giangViens gv " +
                        "WHERE ct.id = :cuocThiId AND gv.maGiangVien = :giangVienId")
        boolean existsGiangVienInCuocThi(@Param("cuocThiId") Long cuocThiId, @Param("giangVienId") String giangVienId);

        @Query("SELECT dktg FROM DangKyThamGiaEntity dktg " +
                        "WHERE dktg.id.maCuocThi.id = :cuocThiId " +
                        "AND dktg.id.maNhomSinhVien.id = :nhomId")
        Optional<DangKyThamGiaEntity> findByCuocThiAndNhom(Long cuocThiId, String nhomId);

        @Query("SELECT dktg FROM DangKyThamGiaEntity dktg " +
                        "WHERE dktg.id.maCuocThi.id = :cuocThiId ")
        Optional<DangKyThamGiaEntity> findOneByCuocThi(Long cuocThiId);

        @Query("SELECT dktg FROM DangKyThamGiaEntity dktg " +
                        "WHERE dktg.id.maNhomSinhVien.id = :nhomId")
        List<DangKyThamGiaEntity> findCuocThiByNhom(String nhomId);

        @Query("SELECT dkt FROM DangKyThamGiaEntity dkt " +
                        "JOIN dkt.id.maCuocThi ct " +
                        "WHERE ct.id = :maCuocThi")
        List<DangKyThamGiaEntity> getDsDkThamGia(@Param("maCuocThi") Long maCuocThi);

        @Query("SELECT d FROM DangKyThamGiaEntity d WHERE d.id.maCuocThi.id = :cuocThiId")
        List<DangKyThamGiaEntity> findAllByCuocThi(@Param("cuocThiId") Long cuocThiId);

        @Query("SELECT d FROM DangKyThamGiaEntity d WHERE d.id.maNhomSinhVien.maNhom = :maNhomSinhVien")
        List<DangKyThamGiaEntity> findByMaNhomSinhVien(@Param("maNhomSinhVien") String maNhomSinhVien);

        @Query("SELECT d FROM DangKyThamGiaEntity d WHERE d.id.maNhomSinhVien.maNhom = :maNhomSinhVien")
        Optional<DangKyThamGiaEntity> findByMaNhom(@Param("maNhomSinhVien") String maNhomSinhVien);
}
