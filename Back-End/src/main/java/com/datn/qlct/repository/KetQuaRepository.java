package com.datn.qlct.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.datn.qlct.entity.GiaiThuongEntity;
import com.datn.qlct.entity.KetQuaVongThiCompositeID;
import com.datn.qlct.entity.KetQuaVongThiEntity;
import com.datn.qlct.entity.NhomSinhVienEntity;
import com.datn.qlct.entity.VongThiEntity;

public interface KetQuaRepository extends JpaRepository<KetQuaVongThiEntity, KetQuaVongThiCompositeID> {
        @Query("SELECT k FROM KetQuaVongThiEntity k WHERE k.id.maVongThi.id = :idVongThi AND k.id.maNhomSinhVien.id = :idMaNhom")
        KetQuaVongThiEntity findByMaNhomAndMaVongThi(String idMaNhom, String idVongThi);

        @Query("SELECT k FROM KetQuaVongThiEntity k WHERE k.id.maVongThi.id = :idVongThi ORDER BY k.ketQua DESC")
        List<KetQuaVongThiEntity> findByMaVongThi(String idVongThi);

        @Query("SELECT k FROM KetQuaVongThiEntity k WHERE k.id.maVongThi.id = :idVongThi")
        Optional<KetQuaVongThiEntity> findOneByMaVongThi(String idVongThi);

        @Query("SELECT k FROM KetQuaVongThiEntity k " +
                        "WHERE k.id.maVongThi.id = :maVongThi " +
                        "AND k.id.maNhomSinhVien.id = :maNhomSinhVien")
        Optional<KetQuaVongThiEntity> findKetQuaBymaVongThiAndmaNhomSinhVien(
                        @Param("maVongThi") String maVongThi,
                        @Param("maNhomSinhVien") String maNhomSinhVien);

        @Query("SELECT k FROM KetQuaVongThiEntity k " +
                        "WHERE k.id.maNhomSinhVien.id = :maNhomSinhVien")
        List<KetQuaVongThiEntity> findKetQuaBymaNhomSinhVien(
                        @Param("maNhomSinhVien") String maNhomSinhVien);

        boolean existsByIdMaVongThiAndMaGiaiThuong(VongThiEntity maVongThi, GiaiThuongEntity maGiaiThuong);

}
