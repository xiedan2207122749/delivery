package com.jxyj.delivery.app.service.impl;

import cn.hutool.core.date.DateUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jxyj.delivery.app.dao.OrderDao;
import com.jxyj.delivery.app.entity.CommodityEntity;
import com.jxyj.delivery.app.entity.OrderDetailEntity;
import com.jxyj.delivery.app.entity.OrderEntity;
import com.jxyj.delivery.app.enums.OrderStatusEnum;
import com.jxyj.delivery.app.enums.WhetherEnum;
import com.jxyj.delivery.app.form.SaveOrderForm;
import com.jxyj.delivery.app.service.CommodityService;
import com.jxyj.delivery.app.service.OrderDetailService;
import com.jxyj.delivery.app.service.OrderService;
import com.jxyj.delivery.common.exception.BaseException;
import com.jxyj.delivery.common.exception.GlobalErrorEnum;
import com.jxyj.delivery.common.util.ExcelUtil;
import com.jxyj.delivery.common.util.UserContext;
import com.jxyj.delivery.sys.form.SearchOrderForm;
import com.jxyj.delivery.sys.vo.OrderDetailVO;
import com.jxyj.delivery.sys.vo.OrderVO;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletResponse;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.Collectors;


@Service("orderService")
public class OrderServiceImpl extends ServiceImpl<OrderDao, OrderEntity> implements OrderService {

    @Autowired
    private OrderDetailService orderDetailService;
    @Autowired
    private CommodityService commodityService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        this.removeById(id);
        orderDetailService.update().set("deleted", WhetherEnum.IS.getValue()).eq("order_id", id).update();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addOrder(SaveOrderForm form) {
        AtomicReference<BigDecimal> totalExpense = new AtomicReference<>(BigDecimal.valueOf(0.0));
        List<OrderDetailEntity> orderDetailEntityList = form.getOrderDetailFormList().stream().map(item -> {
            CommodityEntity commodityEntity = commodityService.getOne(new QueryWrapper<CommodityEntity>().select("id, price, unit").eq("id", item.getId()).last("limit 1"));
            OrderDetailEntity orderDetailEntity = new OrderDetailEntity();
            orderDetailEntity.setPrice(commodityEntity.getPrice());
            orderDetailEntity.setUnit(commodityEntity.getUnit());
            orderDetailEntity.setCommodityId(item.getId());
            orderDetailEntity.setQuantity(item.getQuantity());
            orderDetailEntity.setExpense(commodityEntity.getPrice().multiply(item.getQuantity()));
            totalExpense.set(totalExpense.get().add(orderDetailEntity.getExpense()));
            return orderDetailEntity;
        }).collect(Collectors.toList());
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setCustomerId(UserContext.getUserId());
        orderEntity.setTotalExpense(totalExpense.get());
        orderEntity.setDeliveryTime(form.getDeliveryTime());
        orderEntity.setRemark(form.getRemark());
        this.save(orderEntity);
        orderDetailEntityList.forEach(item -> {
            item.setOrderId(orderEntity.getId());
        });
        orderDetailService.saveBatch(orderDetailEntityList);
    }

    @Override
    public IPage<OrderVO> pageOrder(SearchOrderForm form) {
        IPage<OrderVO> page = form.getPage();
        this.baseMapper.pageOrder(page, form);
        return page;
    }

    @Override
    public void exportOrder(Long id, HttpServletResponse response) {
        String dateStr = DateUtil.format(new Date(), "yyyy-MM-dd");
        HSSFWorkbook wb = new HSSFWorkbook();
        HSSFSheet sheet = wb.createSheet(dateStr);
        CellStyle cellStyle = ExcelUtil.getContentHSSFCellStyle(wb);
        OrderVO orderVO = this.baseMapper.getExportOrderData(id);
        // 设置大订单信息
        setOrderValue(wb, sheet, cellStyle, orderVO);
        List<OrderDetailVO> orderDetailVOList = orderDetailService.getOrderDetail(id);
        // 设置订单详情头部
        setOrderDetail(wb, sheet, cellStyle, orderDetailVOList);
        ExcelUtil.outputExcel(response, wb, "配送单.xls");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteOfCustomer(Long id) {
        int count = this.count(new QueryWrapper<OrderEntity>().eq("id", id).eq("customer_id", UserContext.getUserId()).eq("status", OrderStatusEnum.NO_OUT.getValue()));
        if (count == 0) {
            throw new BaseException(GlobalErrorEnum.ILLEGAL_DATA);
        }
        this.removeById(id);
        orderDetailService.update().set("deleted", WhetherEnum.IS.getValue()).eq("order_id", id).update();
    }

    private void setOrderDetail(HSSFWorkbook wb, HSSFSheet sheet, CellStyle cellStyle, List<OrderDetailVO> list) {
        int[] width = {4000, 3500, 4000, 4000, 4000};
        String[] headerStrArr = {"商品名字", "下单数量", "单位", "单价", "费用"};
        ExcelUtil.setHeaderStyleAndValue(wb, sheet, 4, width, (short) 800, headerStrArr);
        for (int i = 0; i < list.size(); i++) {
            HSSFRow row = sheet.createRow(i + 5);
            Cell cell = row.createCell(0);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(list.get(i).getCommodityName());
            cell = row.createCell(1);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(list.get(i).getQuantity().toString());
            cell = row.createCell(2);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(list.get(i).getUnit());
            cell = row.createCell(3);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(list.get(i).getPrice().toString());
            cell = row.createCell(4);
            cell.setCellStyle(cellStyle);
            cell.setCellValue(list.get(i).getExpense().toString());
        }
    }

    private void setOrderValue(HSSFWorkbook wb, HSSFSheet sheet, CellStyle cellStyle, OrderVO orderVO) {
        int[] width = {4000, 3500, 4000, 4000, 4000, 7000};
        String[] headerStrArr = {"客户名称", "状态", "期望配送时间", "创建时间", "总费用", "备注"};
        ExcelUtil.setHeaderStyleAndValue(wb, sheet, 0, width, (short) 800, headerStrArr);
        HSSFRow row = sheet.createRow(1);
        Cell cell = row.createCell(0);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(orderVO.getCustomerName());
        cell = row.createCell(1);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(orderVO.getStatus() == 1 ? "未配送" : "已配送");
        cell = row.createCell(2);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(DateUtil.format(orderVO.getDeliveryTime(), "yyyy-MM-dd HH:mm:ss"));
        cell = row.createCell(3);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(DateUtil.format(orderVO.getCreateTime(), "yyyy-MM-dd HH:mm:ss"));
        cell = row.createCell(4);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(orderVO.getTotalExpense().toString());
        cell = row.createCell(5);
        cell.setCellStyle(cellStyle);
        cell.setCellValue(orderVO.getRemark());

    }
}
