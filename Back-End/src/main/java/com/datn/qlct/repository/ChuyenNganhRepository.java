package com.datn.qlct.repository;

import com.datn.qlct.entity.ChuyenNganhEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChuyenNganhRepository extends JpaRepository<ChuyenNganhEntity, String> {
    Optional<ChuyenNganhEntity> findByTenChuyenNganhContainingIgnoreCase(String keyword);
}
