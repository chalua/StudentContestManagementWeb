package com.datn.qlct.repository;

import com.datn.qlct.entity.SinhVienEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SinhVienRepository extends JpaRepository<SinhVienEntity, String> {

}
