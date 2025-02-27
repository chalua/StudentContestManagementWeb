package com.datn.qlct.repository;

import com.datn.qlct.entity.ChuyenNganhEntity;
import com.datn.qlct.entity.DeTaiEntity;
import com.datn.qlct.entity.HocHamEntity;
import com.datn.qlct.entity.HocViEntity;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

public interface HocHamRepository extends JpaRepository<HocHamEntity, String> {

    Optional<HocHamEntity> findByTenHocHamEqualsIgnoreCase(String keyword);
}
