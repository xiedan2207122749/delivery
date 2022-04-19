package com.jxyj.delivery.app.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxyj.delivery.app.dao.OrderDetailDao;
import com.jxyj.delivery.app.entity.OrderDetailEntity;
import com.jxyj.delivery.app.service.OrderDetailService;
import com.jxyj.delivery.common.util.ExcelUtil;
import com.jxyj.delivery.sys.bo.ExportOrderDetailBO;
import com.jxyj.delivery.sys.vo.OrderDetailVO;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service("orderDetailService")
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailDao, OrderDetailEntity> implements OrderDetailService {


    @Override
    public List<OrderDetailVO> getOrderDetail(Long orderId) {
        return this.baseMapper.getOrderDetail(orderId);
    }

    @Override
    public void exportOrder(Long[] ids, HttpServletResponse response) {
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet("配送单列表");
        CellStyle cellStyle = ExcelUtil.getContentHSSFCellStyle(wb);
        List<ExportOrderDetailBO> exportOrderDetailBOList = this.baseMapper.listExportOrderDetailData(ids);
        // 设置订单详情头部
        setOrderDetail(wb, sheet, cellStyle, exportOrderDetailBOList);
        ExcelUtil.outputExcel(response, wb, "配送单详情.xls");
    }

    private void setOrderDetail(HSSFWorkbook wb, HSSFSheet sheet, CellStyle cellStyle, List<ExportOrderDetailBO> list) {
        int[] width = {3000, 4000, 3500, 4000, 4000, 4000};
        String[] headerStrArr = {"序号", "客户", "下单时间", "下单商品", "下单数量", "商品（单位）"};
        ExcelUtil.setHeaderStyleAndValue(wb, sheet, 0, width, (short) 800, headerStrArr);
        for (int i = 0; i < list.size(); i++) {
            HSSFRow row = sheet.createRow(i + 1);
            Cell cell = row.createCell(0);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(i + 1);
            cell = row.createCell(1);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(list.get(i).getCustomerName());
            cell = row.createCell(2);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(list.get(i).getCreateTime());
            cell = row.createCell(3);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(list.get(i).getCommodityName());
            cell = row.createCell(4);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(list.get(i).getQuantity().toString());
            cell = row.createCell(5);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(list.get(i).getUnit());
        }
    }
}
