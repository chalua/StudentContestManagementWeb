package com.datn.qlct.service.Impl;

import com.datn.qlct.dto.GiangVienDTO;
import com.datn.qlct.dto.SinhVienDTO;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.hibernate.grammars.hql.HqlParser;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class ExcelUploadService {
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");

    public static boolean isValodExcelFile(MultipartFile file) {
        return Objects.equals(file.getContentType(),
                "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
    }

    public static List<SinhVienDTO> getSinhVienDataFromExcel(InputStream inputStream) {
        List<SinhVienDTO> sinhVienDTOS = new ArrayList<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("SinhVien");

            int rowIndex = 0;
            for (Row row : sheet) {
                if (rowIndex == 0 || rowIndex == 1) {// bỏ qua tiêu đề
                    rowIndex++;
                    continue;
                }
                // Kiểm tra nếu dòng hiện tại rỗng
                boolean isRowEmpty = true;
                for (Cell cell : row) {
                    if (cell.getCellType() != CellType.BLANK) {
                        isRowEmpty = false;
                        break;
                    }
                }
                if (isRowEmpty) {
                    break; // Hủy vòng lặp nếu dòng rỗng
                }
                Iterator<Cell> cellIterator = row.iterator();
                int cellIndex = 0;
                SinhVienDTO sinhVienDTO = new SinhVienDTO();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();
                    switch (cellIndex) {
                        case 0 ->
                            sinhVienDTO.setMaSinhVien(String.valueOf((int) Math.floor(cell.getNumericCellValue())));
                        case 1 -> sinhVienDTO.setHoTen(cell.getStringCellValue());
                        case 2 -> sinhVienDTO.setNgaySinh(LocalDate.parse(cell.toString(), formatter));
                        case 3 -> sinhVienDTO.setNoiSinh(cell.getStringCellValue());
                        case 4 -> sinhVienDTO.setEmail(cell.getStringCellValue());
                        case 5 -> sinhVienDTO.setBacDaoTao(cell.getStringCellValue());
                        case 6 -> sinhVienDTO.setDiaChi(cell.getStringCellValue());
                        case 7 -> sinhVienDTO.setQueQuan(cell.getStringCellValue());
                        case 8 -> sinhVienDTO.setGioiTinh(cell.getStringCellValue());
                        case 9 -> sinhVienDTO.setKhoaHoc(String.valueOf((int) Math.floor(cell.getNumericCellValue())));
                        case 10 -> sinhVienDTO.setLoaiHinhDaoTao(cell.getStringCellValue());
                        case 11 -> sinhVienDTO.setNganh(cell.getStringCellValue());
                        case 12 -> sinhVienDTO.setLop(cell.getStringCellValue());
                        case 13 -> sinhVienDTO.setSoDienThoai(cell.getStringCellValue());
                        case 14 -> {
                            if (cell.getCellType() == CellType.STRING)
                                sinhVienDTO.setPassword(cell.getStringCellValue());
                            else if (cell.getCellType() == CellType.NUMERIC)
                                sinhVienDTO.setPassword(String.valueOf((int) Math.floor(cell.getNumericCellValue())));
                        }
                    }
                    cellIndex++;
                }
                if (cellIndex != 1) {
                    sinhVienDTOS.add(sinhVienDTO);
                }
            }
        } catch (IOException e) {
            e.getStackTrace();
        }

        return sinhVienDTOS;
    }

    public static List<GiangVienDTO> getGiangVienDataFromExcel(InputStream inputStream) {
        List<GiangVienDTO> giangVienDTOS = new ArrayList<>();
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheet("GiangVien");

            int rowIndex = 0;
            for (Row row : sheet) {
                if (rowIndex == 0 || rowIndex == 1) { // Bỏ qua tiêu đề
                    rowIndex++;
                    continue;
                }

                // Kiểm tra nếu dòng hiện tại rỗng
                boolean isRowEmpty = true;
                for (Cell cell : row) {
                    if (cell.getCellType() != CellType.BLANK) {
                        isRowEmpty = false;
                        break;
                    }
                }
                if (isRowEmpty) {
                    break; // Hủy vòng lặp nếu dòng rỗng
                }

                Iterator<Cell> cellIterator = row.iterator();
                int cellIndex = 0;
                GiangVienDTO giangVienDTO = new GiangVienDTO();
                while (cellIterator.hasNext()) {
                    Cell cell = cellIterator.next();

                    switch (cellIndex) {
                        case 0 ->
                            giangVienDTO.setMaGiangVien(String.valueOf((int) Math.floor(cell.getNumericCellValue())));
                        case 1 -> giangVienDTO.setTenGiangVien(cell.getStringCellValue());
                        case 2 -> giangVienDTO.setNgaySinh(LocalDate.parse(cell.toString(), formatter));
                        case 3 -> giangVienDTO.setSoDienThoai(cell.getStringCellValue());
                        case 4 -> giangVienDTO.setGioiTinh(cell.getStringCellValue());
                        case 5 -> giangVienDTO.setHocHam(cell.getStringCellValue());
                        case 6 -> giangVienDTO.setHocVi(cell.getStringCellValue());
                        case 7 -> giangVienDTO.setQueQuan(cell.getStringCellValue());
                        case 8 -> {
                            if (cell.getCellType() == CellType.STRING)
                                giangVienDTO.setPassword(cell.getStringCellValue());
                            else if (cell.getCellType() == CellType.NUMERIC)
                                giangVienDTO.setPassword(String.valueOf((int) Math.floor(cell.getNumericCellValue())));
                        }
                    }
                    cellIndex++;
                }
                giangVienDTOS.add(giangVienDTO);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return giangVienDTOS;
    }

}
