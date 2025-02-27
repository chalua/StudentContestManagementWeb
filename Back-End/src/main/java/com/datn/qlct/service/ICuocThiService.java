package com.datn.qlct.service;

import com.datn.qlct.dto.CuocThiDTO;
import com.datn.qlct.dto.DangKyDeTaiDTO;
import com.datn.qlct.dto.DangKyThamGiaDTO;
import com.datn.qlct.dto.VongThiDTO;
import com.datn.qlct.dto.response.CuocThiResponse;
import com.datn.qlct.dto.response.DangKyDeTaiResponse;
import com.datn.qlct.dto.response.DangKyThamGiaResponse;
import com.datn.qlct.entity.CuocThiEntity;

import java.util.List;

public interface ICuocThiService {
    void deleteVongThi(Long cuocThiId, String maVongThi);

    VongThiDTO updateVongThi(VongThiDTO vongThiDTO);

    List<VongThiDTO> getAllVongByCuocThi(Long cuocThiId);

    VongThiDTO taoVongThi(VongThiDTO vongThiDTO);

    public List<CuocThiResponse> findDsCuocThiTheoLoai(Integer loaiId);

    CuocThiEntity assignGiangVienToCuocThi(Long cuocThiId, String giangVienId);

    public CuocThiEntity deleteGiangVienFromCuocThi(Long cuocThiId, String giangVienId);

    List<CuocThiResponse> findDsCuocThi();

    List<CuocThiResponse> findAllByTenCuocThi(String tenCuocThi);

    List<CuocThiResponse> findDsNCKH();

    CuocThiResponse addCuocThi(CuocThiDTO cuocThiDTO);

    CuocThiResponse updateCuocThi(Long id, CuocThiDTO cuocThiDTO);

    void deleteCuocThi(Long id);

    DangKyThamGiaResponse dangKyThamGia(DangKyThamGiaDTO dangKyThamGiaDTO);

    DangKyDeTaiResponse dangKyDeTai(DangKyDeTaiDTO dangKyThamGiaDTO);

    CuocThiResponse getCuocThiByID(Long id);
    // List<DangKyThamGiaResponse> loadNhomThamGia();
}
