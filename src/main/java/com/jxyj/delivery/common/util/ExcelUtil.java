package com.jxyj.delivery.common.util;

import com.jxyj.delivery.common.exception.BaseException;
import com.jxyj.delivery.common.exception.GlobalErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.text.NumberFormat;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author dell
 * @Classname ExcelUtil
 * @Description
 * @Date 2020/11/27 11:15
 * @Created by mr_xie
 */
@Slf4j
public class ExcelUtil {
    
    /**
     * 通过文件后缀判断使用哪个api
     *
     * @param multipartFile
     * @return
     * @throws IOException
     */
    public static Workbook judgeUseHSSFOrXSSF(MultipartFile multipartFile) throws IOException {
        if (multipartFile.getOriginalFilename().endsWith(".xlsx")) {
            return new XSSFWorkbook(multipartFile.getInputStream());
        } else if (multipartFile.getOriginalFilename().endsWith(".xls")) {
            return new HSSFWorkbook(multipartFile.getInputStream());
        } else {
            throw new BaseException(GlobalErrorEnum.FILE_FORMAT_ERROR);
        }
    }
    
    /**
     * 获取单元格字符串值
     *
     * @param cell
     * @return
     */
    public static String getCellStringValue(Cell cell) {
        String cellValue;
        if (cell != null) {
            switch (cell.getCellType()) {
                case NUMERIC: {
                    NumberFormat nf = NumberFormat.getInstance();
                    cellValue = nf.format(cell.getNumericCellValue());
                    cellValue = cellValue.replaceAll(",", "");
                    break;
                }
                case FORMULA: {
                    if (DateUtil.isCellDateFormatted(cell)) {
                        cellValue = cell.getDateCellValue() + "";
                    } else {
                        cellValue = String.valueOf(cell.getNumericCellValue());
                    }
                    break;
                }
                case STRING: {
                    cellValue = cell.getRichStringCellValue().getString();
                    break;
                }
                default:
                    cellValue = "";
            }
        } else {
            cellValue = "";
        }
        return cellValue.trim();
    }
    
    public static String getCellStringValue(Cell cell, GlobalErrorEnum globalErrorEnum) {
        String cellValue = getCellStringValue(cell);
        BaseDataTypeConvertUtil.judgeThrowException(cellValue, globalErrorEnum);
        return cellValue;
    }
    
    public static Double getCellDoubleValue(Cell cell) {
        if (Objects.nonNull(cell)) {
            switch (cell.getCellType()) {
                case NUMERIC:
                    return cell.getNumericCellValue();
                case STRING:
                    String value = cell.getStringCellValue().trim();
                    if (StringUtils.isBlank(value)) {
                        return null;
                    } else {
                        try {
                            return Double.parseDouble(value);
                        } catch (NumberFormatException e) {
                            return null;
                        }
                    }
                default:
                    return null;
            }
        } else {
            return null;
        }
    }
    
    public static Double getCellDoubleValue(Cell cell, GlobalErrorEnum globalErrorEnum) {
        Double cellValue = getCellDoubleValue(cell);
        BaseDataTypeConvertUtil.judgeThrowException(cellValue, globalErrorEnum);
        return cellValue;
    }
    
    public static Integer getCellIntValue(Cell cell) {
        Double cellDoubleValue = getCellDoubleValue(cell);
        if (Objects.isNull(cellDoubleValue)) {
            return null;
        } else {
            return cellDoubleValue.intValue();
        }
    }
    
    public static Integer getCellIntValue(Cell cell, GlobalErrorEnum globalErrorEnum) {
        Integer cellValue = getCellIntValue(cell);
        BaseDataTypeConvertUtil.judgeThrowException(cellValue, globalErrorEnum);
        return cellValue;
    }
    
    /**
     * 判断此行是否是有效行
     *
     * @return 有效true 无效 false
     */
    public static boolean judgeRowIsValid(Row row) {
        if (row == null || StringUtils.isBlank(getCellStringValue(row.getCell(0)))
            && StringUtils.isBlank(getCellStringValue(row.getCell(1)))
            && StringUtils.isBlank(getCellStringValue(row.getCell(2)))) {
            return true;
        }
        return false;
    }
    
    public static void setCellValue(Cell cell, Object value) {
        if (value != null) {
            if (value instanceof Double) {
                cell.setCellValue((Double) value);
            } else if (value instanceof Integer) {
                cell.setCellValue((int) value);
            }
        }
    }
    
    /**
     * 设置头excel头部样式和值
     *
     * @param wb
     * @param sheet
     * @param rowNum       头部是哪一行
     * @param width        列宽
     * @param height       表头高度
     * @param headerStrArr 表头的值
     */
    public static void setHeaderStyleAndValue(HSSFWorkbook wb, HSSFSheet sheet, int rowNum, int[] width, short height, String[] headerStrArr) {
        HSSFRow row = sheet.createRow(rowNum);
        // 锁定第一行
        sheet.createFreezePane(0, rowNum + 1);
        row.setHeight(height);
        HSSFCellStyle cellStyle = wb.createCellStyle();
        // 居中
        cellStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        // 设置前景色
        cellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.index);
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        setCellBorder(cellStyle);
        // 设置单元格内\n可以换行
        cellStyle.setWrapText(true);
        // 设置字体
        HSSFFont font = wb.createFont();
        font.setFontName("Microsoft YaHei");
        font.setBold(true);
        cellStyle.setFont(font);
        for (int i = 0; i < headerStrArr.length; i++) {
            sheet.setColumnWidth(i, width[i]);
            Cell cell = row.createCell(i);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(headerStrArr[i]);
        }
        
    }
    
    /**
     * 设置特殊单元格的内容
     *
     * @param wb
     * @param sheet
     * @param height    行高
     * @param rowIndex  行的位置
     * @param cellIndex 单元格的位置
     * @param title     标题
     * @param value     内容
     */
    public static void setSpecialCellStyleAndValue(HSSFWorkbook wb, HSSFSheet sheet, short height, int rowIndex, int cellIndex, String title, String value) {
        HSSFRow row = sheet.createRow(rowIndex);
        setSpecialCellStyleAndValue(wb, height, row, cellIndex, title, value);
    }
    
    /**
     * 设置特殊单元格的内容
     *
     * @param wb
     * @param height    行高
     * @param row       行
     * @param cellIndex 单元格的位置
     * @param title     标题
     * @param value     内容
     */
    public static void setSpecialCellStyleAndValue(HSSFWorkbook wb, short height, HSSFRow row, int cellIndex, String title, String value) {
        HSSFCellStyle cellStyle = getSpecialHssfCellStyle(wb, height, row);
        // 设置边框  这种两格的需要设置边框
        setCellBorder(cellStyle);
        
        Cell cell = row.createCell(cellIndex);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(title);
        Cell cell2 = row.createCell(cellIndex + 1);
        cell2.setCellStyle(cellStyle);
        cell2.setCellValue(value);
        
    }
    
    /**
     * 设置特殊单元格的内容
     *
     * @param wb
     * @param height    行高
     * @param row       行
     * @param cellIndex 单元格的位置
     * @param title     标题
     */
    public static void setSpecialCellStyleAndValue(HSSFWorkbook wb, short height, HSSFRow row, int cellIndex, String title) {
        HSSFCellStyle cellStyle = getSpecialHssfCellStyle(wb, height, row);
        Cell cell = row.createCell(cellIndex);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(title);
    }
    
    /**
     * 获取特殊单元格样式
     *
     * @param wb
     * @param height
     * @param row
     * @return
     */
    private static HSSFCellStyle getSpecialHssfCellStyle(HSSFWorkbook wb, short height, HSSFRow row) {
        row.setHeight(height);
        HSSFCellStyle cellStyle = wb.createCellStyle();
        // 居中
        cellStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        // 设置字体
        HSSFFont font = wb.createFont();
        font.setFontName("Microsoft YaHei");
        font.setBold(true);
        cellStyle.setFont(font);
        return cellStyle;
    }
    
    private static void setCellBorder(HSSFCellStyle cellStyle) {
        cellStyle.setBorderBottom(BorderStyle.THIN);
        cellStyle.setBorderLeft(BorderStyle.THIN);
        cellStyle.setBorderTop(BorderStyle.THIN);
        cellStyle.setBorderRight(BorderStyle.THIN);
    }
    
    /**
     * 获取表内容的样式
     *
     * @param wb
     * @return
     */
    public static HSSFCellStyle getContentHSSFCellStyle(HSSFWorkbook wb) {
        return getContentHSSFCellStyle(wb, null);
    }
    
    /**
     * 获取表内容的样式
     *
     * @param wb
     * @return
     */
    public static HSSFCellStyle getContentHSSFCellStyle(HSSFWorkbook wb, Short format) {
        HSSFCellStyle cellStyle = wb.createCellStyle();
        if (format != null) {
            cellStyle.setDataFormat(format);
        }
        cellStyle.setWrapText(true);
        cellStyle.setAlignment(HorizontalAlignment.CENTER_SELECTION);
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        setCellBorder(cellStyle);
        return cellStyle;
    }
    
    /**
     * 设置excel内容的风格和值
     *
     * @param wb
     * @param sheet
     * @param rowIndex
     * @param height
     * @param contentValues
     */
    public static void setContentStyleAndValue(HSSFWorkbook wb, HSSFSheet sheet, int rowIndex, short height, List<String[]> contentValues) {
        setContentStyleAndValue(sheet, rowIndex, height, contentValues, getContentHSSFCellStyle(wb));
    }
    
    public static void setContent(Row row, String value, int cellIndex) {
        Cell cell = row.createCell(cellIndex);
        cell.setCellValue(value);
    }
    
    public static void setContent(Row row, double value, int cellIndex) {
        Cell cell = row.createCell(cellIndex);
        cell.setCellValue(value);
    }
    
    public static void setContent(Row row, int value, int cellIndex) {
        Cell cell = row.createCell(cellIndex);
        cell.setCellValue(value);
    }
    
    /**
     * 设置excel内容的风格和值
     *
     * @param sheet
     * @param rowIndex
     * @param height
     * @param contentValues
     * @param cellStyle
     */
    public static void setContentStyleAndValue(HSSFSheet sheet, int rowIndex, short height, List<String[]> contentValues, HSSFCellStyle cellStyle) {
        AtomicInteger rowNum = new AtomicInteger(rowIndex);
        contentValues.forEach(item -> {
            HSSFRow row = sheet.createRow(rowNum.getAndIncrement());
            row.setHeight(height);
            for (int i = 0; i < item.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(item[i]);
            }
        });
    }
    
    /**
     * 设置excel内容的风格和值
     *
     * @param wb
     * @param sheet
     * @param rowIndex
     * @param height
     * @param contentValues
     */
    public static void setContentStyleAndValue(HSSFWorkbook wb, HSSFSheet sheet, int rowIndex, short[] height, List<String[]> contentValues) {
        setContentStyleAndValue(sheet, rowIndex, height, contentValues, getContentHSSFCellStyle(wb));
    }
    
    /**
     * 设置excel内容的风格和值
     *
     * @param sheet
     * @param rowIndex
     * @param height
     * @param contentValues
     * @param cellStyle
     */
    public static void setContentStyleAndValue(HSSFSheet sheet, int rowIndex, short[] height, List<String[]> contentValues, HSSFCellStyle cellStyle) {
        AtomicInteger rowNum = new AtomicInteger(rowIndex);
        AtomicInteger heightIndex = new AtomicInteger(0);
        contentValues.forEach(item -> {
            HSSFRow row = sheet.createRow(rowNum.getAndIncrement());
            row.setHeight(height[heightIndex.getAndIncrement()]);
            for (int i = 0; i < item.length; i++) {
                Cell cell = row.createCell(i);
                cell.setCellStyle(cellStyle);
                cell.setCellValue(item[i]);
            }
        });
    }
    
    /**
     * 导出excel
     *
     * @param response
     * @param wb
     * @param fileName
     */
    public static void outputExcel(HttpServletResponse response, HSSFWorkbook wb, String fileName) {
        try (OutputStream output = response.getOutputStream()) {
            response.setCharacterEncoding("utf-8");
            response.setHeader("Content-Disposition",
                "attachment;filename=" + URLEncoder.encode(fileName, "utf-8").replace("+","%20"));
            response.setContentType("application/vnd.ms-excel;charset=utf-8");
            wb.write(output);
            output.flush();
            wb.close();
        } catch (IOException e) {
            log.error("导出excel失败", e);
            throw new BaseException(GlobalErrorEnum.EXPORT_EXCEL_IO_EXCEPTION);
        }
    }
}
