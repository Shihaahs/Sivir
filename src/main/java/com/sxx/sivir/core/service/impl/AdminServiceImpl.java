package com.sxx.sivir.core.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.sxx.sivir.core.common.enums.CarTypeEnum;
import com.sxx.sivir.core.common.enums.OrderTypeEnum;
import com.sxx.sivir.core.common.enums.UserPermissionEnum;
import com.sxx.sivir.core.common.page.PageResult;
import com.sxx.sivir.core.common.request.PageRequestDTO;
import com.sxx.sivir.core.common.response.RegionInfo;
import com.sxx.sivir.core.common.response.RegionTransCar;
import com.sxx.sivir.core.common.util.DateUtil;
import com.sxx.sivir.core.dal.dao.RegionDao;
import com.sxx.sivir.core.dal.domain.Car;
import com.sxx.sivir.core.dal.domain.Region;
import com.sxx.sivir.core.dal.domain.Sorder;
import com.sxx.sivir.core.dal.domain.User;
import com.sxx.sivir.core.dal.manager.CarManager;
import com.sxx.sivir.core.dal.manager.RegionManager;
import com.sxx.sivir.core.dal.manager.SorderManager;
import com.sxx.sivir.core.dal.manager.UserManager;
import com.sxx.sivir.core.service.AdminService;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static com.sxx.sivir.core.common.page.PageQuery.conditionAdapter;
import static com.sxx.sivir.core.common.page.PageQuery.initPage;

/**
 * @Package com.sxx.sivir.core.service.impl
 * @Author: 尚星
 * @Date: 2019/4/9 16:31
 * @Copyright: 2018-2099  ShangXing - Sivir All rights reserved.
 * @Description:
 */

@Slf4j
@Service
public class AdminServiceImpl implements AdminService {

    private static final Integer COLUMN_WIDTH = 25 * 256;

    @Autowired
    private UserManager userManager;
    @Autowired
    private CarManager carManager;
    @Autowired
    private SorderManager sorderManager;
    @Autowired
    private RegionManager regionManager;


    /**
     * 管理员接口 - 用户管理
     */
    @Override
    public PageResult<User> getAllUser(PageRequestDTO pageRequestDTO) {
        //分页条件查询
        Page<User> userPage = userManager.selectPage(
                initPage(pageRequestDTO),
                conditionAdapter(pageRequestDTO));

        return new PageResult<>(pageRequestDTO.getPageSize(),
                pageRequestDTO.getPageCurrent(),
                (int) userPage.getTotal(),
                userPage.getRecords());
    }

    @Override
    public Integer addUser(User user) {
        if (null == user.getPhone() || user.getPhone().isEmpty()) {
            log.error("新增用户未检测到手机号");
            return 0;
        }
        int count = userManager.selectCount(new EntityWrapper<User>().eq("phone", user.getPhone()));
        if (count == 0) {
            if (user.getPermission().equals(UserPermissionEnum.TRANS.getCode())) {
                //快递员注册初始 地区为1
                user.setTransRegionId(1L);
            } else {
                user.setTransRegionId(0L);
            }
            return userManager.insert(user);
        }

        return 2;
    }

    @Override
    public Integer updateUser(User user) {
        return userManager.updateById(user);
    }

    @Override
    public Integer deleteUser(User user) {
        return userManager.deleteById(user.getUserId());
    }

    @Override
    public User getUserById(User user) {
        if (null == user.getUserId() || user.getUserId().equals(0L)) {
            log.error("AdminServiceImpl - getUserById -> 根据id获取用户信息， id为空");
            return new User();
        }
        return userManager.selectOne(user);
    }

    /**
     * 管理员接口 - 订单管理
     */
    @Override
    public PageResult<Sorder> getAllSorder(PageRequestDTO pageRequestDTO) {
        //分页条件查询
        Page<Sorder> sorderPage = sorderManager.selectPage(
                initPage(pageRequestDTO),
                conditionAdapter(pageRequestDTO));

        return new PageResult<>(pageRequestDTO.getPageSize(),
                pageRequestDTO.getPageCurrent(),
                (int) sorderPage.getTotal(),
                sorderPage.getRecords());
    }

    @Override
    public Integer updateSorder(Sorder sorder) {
        return sorderManager.updateById(sorder);
    }

    @Override
    public Integer deleteSorder(Sorder sorder) {
        return sorderManager.deleteById(sorder.getOrderId());
    }

    @Override
    public Sorder getSorderById(Sorder sorder) {
        if (null == sorder.getOrderId() || sorder.getOrderId().equals(0L)) {
            log.error("AdminServiceImpl - getSorderById -> 根据id获取订单信息， id为空");
            return new Sorder();
        }
        return sorderManager.selectOne(sorder);
    }


    /**
     * 管理员接口 - 区域管理 - 配置区域内的快递员和车辆
     */
    @Override
    public Integer addRegionTransCar(RegionTransCar regionTransCar) {
        if (null == regionTransCar.getRegionId() || 0L == regionTransCar.getRegionId()) {
            log.error("AdminServiceImpl - addRegionTransCar -> 区域管理的区域id为空");
            return null;
        }
        int row = 0;
        if (null != regionTransCar.getRegionCarId() && 0L != regionTransCar.getRegionCarId()) {
            Car car = new Car();
            car.setCarId(regionTransCar.getRegionCarId());
            car.setCarRegionId(regionTransCar.getRegionId());
            car.setCarType(CarTypeEnum.IN_WAREHOUSE.getCode());
            car.setCarRegionName(regionManager.getAllRegionNameByRegionId(regionTransCar.getRegionId()));
            row = carManager.updateById(car);

            if (1 != row) {
                log.error("AdminServiceImpl - addRegionTransCar -> 区域管理的新增车辆失败");
                return 2;
            }
        }
        if (null != regionTransCar.getRegionTransId() && 0L != regionTransCar.getRegionTransId()) {
            User trans = new User();
            trans.setUserId(regionTransCar.getRegionTransId());
            trans.setTransRegionId(regionTransCar.getRegionId());
            row = userManager.updateById(trans);

            if (1 != row) {
                log.error("AdminServiceImpl - addRegionTransCar -> 区域管理的新增快递员失败");
                return 2;
            }
        }
        return row;
    }

    @Override
    public Integer deleteRegionTransCar(RegionTransCar regionTransCar) {
        if (null == regionTransCar.getRegionId() || 0L == regionTransCar.getRegionId()) {
            log.error("AdminServiceImpl - deleteRegionTransCar -> 区域管理的区域id为空");
            return null;
        }
        int row = 0;
        if (null != regionTransCar.getRegionCarId() && 0L == regionTransCar.getRegionCarId()) {
            Car car = new Car();
            car.setCarId(regionTransCar.getRegionCarId());
            car.setCarRegionId(0L);
            row = carManager.updateById(car);

            if (1 != row) {
                log.error("AdminServiceImpl - deleteRegionTransCar -> 区域管理的删除车辆失败");
                return null;
            }
        }
        if (null != regionTransCar.getRegionTransId() && 0L == regionTransCar.getRegionTransId()) {
            User trans = new User();
            trans.setUserId(regionTransCar.getRegionTransId());
            trans.setTransRegionId(0L);
            row = userManager.updateById(trans);

            if (1 != row) {
                log.error("AdminServiceImpl - deleteRegionTransCar -> 区域管理的删除快递员失败");
                return null;
            }
        }
        return row;
    }

    @Override
    public PageResult<RegionInfo> getAllRegionInfo(PageRequestDTO pageRequestDTO) {
        List<Long> regionIds;
        if (Objects.nonNull(pageRequestDTO.getRegionId()) && pageRequestDTO.getRegionId().equals(0L)) {
            regionIds = Collections.singletonList(pageRequestDTO.getRegionId());
        } else {
            Pagination page = new Page<Region>(pageRequestDTO.getPageCurrent(), pageRequestDTO.getPageSize());
            regionIds = regionManager.getRegionIdWithCarOrTrans(page);
        }

        //分页获取有车或人的region
        Map<Long, Region> regionList = regionManager.selectList(
                new EntityWrapper<Region>()
                        .in("region_id", regionIds))
                .stream().collect(Collectors.toMap(Region::getRegionId, x -> x));


        List<RegionInfo> regionInfoList = new ArrayList<>();
        regionList.forEach((regionId, region) -> {
            List<Car> carList = carManager.selectList(new EntityWrapper<Car>().eq("car_region_id", regionId));
            List<User> transList = userManager.selectList(new EntityWrapper<User>().eq("trans_region_id", regionId));
            //只要分配了车或者人 就加载出来
            if (0 != carList.size() || 0 != transList.size()) {
                RegionInfo regionInfo = new RegionInfo();
                regionInfo.setRegionId(regionId);
                regionInfo.setRegionName(region.getRegionName());
                if (0 != carList.size()) {
                    //车辆信息
                    regionInfo.setRegionContentType(1);
                    carList.forEach( car -> {
                        regionInfo.setRegionContent(car.getCarNo());
                        regionInfo.setRegionContentId(car.getCarId());
                        regionInfo.setRegionContentInfo(CarTypeEnum.getDesc(car.getCarType()));
                        regionInfo.setGmtModified(car.getGmtModified());
                        regionInfoList.add(regionInfo);
                    });

                }
                if (0 != transList.size()) {
                    //快递员信息
                    regionInfo.setRegionContentType(0);
                    transList.forEach( trans -> {
                        regionInfo.setRegionContent(trans.getUserName());
                        regionInfo.setRegionContentId(trans.getUserId());
                        regionInfo.setRegionContentInfo("工作中");
                        regionInfo.setGmtModified(trans.getGmtModified());
                        regionInfoList.add(regionInfo);
                    });
                }
            }
        });

        return new PageResult<>(
                pageRequestDTO.getPageSize(),
                pageRequestDTO.getPageCurrent(),
                regionInfoList.size(),
                regionInfoList);
    }


    /**
     * 管理员接口 - 存储管理 - 对存储的车辆进行管理
     */
    @Override
    public Integer addCar(Car car) {
        if (null == car.getCarNo() || car.getCarNo().isEmpty()) {
            log.error("新增车辆未检测到车牌号");
            return 0;
        }
        int count = carManager.selectCount(new EntityWrapper<Car>().eq("car_no", car.getCarNo()));
        if (count == 0) {
            return carManager.insert(car);
        }
        return 2;
    }

    @Override
    public Integer deleteCar(Car car) {

        //删除车辆时要对车辆的做涉及的订单做校验

        return carManager.deleteById(car.getCarId());
    }

    @Override
    public Integer updateCar(Car car) {
        return carManager.updateById(car);
    }

    @Override
    public PageResult<Car> getAllCar(PageRequestDTO pageRequestDTO) {
        Wrapper wrapper = conditionAdapter(pageRequestDTO);
        wrapper.ne("car_type", CarTypeEnum.OBSOLETED.getCode());
        //分页条件查询
        Page<Car> carPage = carManager.selectPage(
                initPage(pageRequestDTO),
                wrapper);

        return new PageResult<>(pageRequestDTO.getPageSize(),
                pageRequestDTO.getPageCurrent(),
                (int) carPage.getTotal(),
                carPage.getRecords());
    }

    /**
     * 管理员接口 - 存储配送管理 - 给订单安排运输的快递员和车辆
     */
    @Override
    public Integer arrangeSorderTransCar(Sorder sorder) {
        if (null == sorder.getOrderTransId() && null == sorder.getOrderCarId()) {
            log.error("AdminServiceImpl - arrangeSorderTransCar -> 订单安排运输的快递员和车辆均为空");
            return null;
        }
        return sorderManager.updateById(sorder);
    }

    @Override
    public Integer updateSorderTransCar(Sorder sorder) {
        if (null == sorder.getOrderTransId() || null == sorder.getOrderCarId()) {
            log.error("AdminServiceImpl - updateSorderTransCar -> 订单安排运输的快递员和车辆为空");
            return null;
        }
        return sorderManager.updateById(sorder);
    }

    @Override
    public PageResult<Sorder> getAllSorderTransCar(PageRequestDTO pageRequestDTO) {

        Wrapper<Sorder> wrapper = conditionAdapter(pageRequestDTO);
        //待揽件 和 配送中 需要 安排快递员
        //wrapper.in("order_type", new Integer[]{1, 4});
        wrapper.eq("order_type", OrderTypeEnum.WAIT_GET.getCode());

        //分页条件查询
        Page<Sorder> sorderPage = sorderManager.selectPage(
                initPage(pageRequestDTO),
                wrapper);

        return new PageResult<>(pageRequestDTO.getPageSize(),
                pageRequestDTO.getPageCurrent(),
                (int) sorderPage.getTotal(),
                sorderPage.getRecords());
    }

    /**
     * 管理员接口 - 业务统一生成报表
     */
    @Override
    public XSSFWorkbook getBusinessReport(PageRequestDTO pageRequestDTO) {

        Wrapper<Sorder> wrapper = conditionAdapter(pageRequestDTO);
        if (Objects.nonNull(pageRequestDTO.getRegionId()) && !pageRequestDTO.getRegionId().equals(0L)) {
            wrapper.eq("order_region_id", pageRequestDTO.getRegionId());
        }
        //分页条件查询
        Page<Sorder> sorderPage = sorderManager.selectPage(
                initPage(pageRequestDTO), wrapper);
        return createExcelInfo(sorderPage.getRecords());
    }

    private XSSFWorkbook createExcelInfo(List<Sorder> records) {

        //创建一个工作表
        XSSFWorkbook workbook = new XSSFWorkbook();
        String name = DateUtil.parseToString(new Date(), "yyyy-MM-dd");
        XSSFSheet sheet = workbook.createSheet(name);
        //添加表头
        XSSFRow xssfRow = sheet.createRow(0);

        XSSFFont font = workbook.createFont();
        font.setBold(true);
        font.setFontHeight(14);
        //表头格式
        XSSFCellStyle headCellStyle = workbook.createCellStyle();
        headCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        headCellStyle.setAlignment(HorizontalAlignment.CENTER);
        headCellStyle.setFont(font);
        //自动换行
        headCellStyle.setWrapText(true);
        //数据格式
        XSSFCellStyle cellStyle = workbook.createCellStyle();
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER);
        cellStyle.setAlignment(HorizontalAlignment.CENTER);
        //自动换行
        cellStyle.setWrapText(true);
        int column = 0;

        //添加表头内容
        XSSFCell headCell = xssfRow.createCell(column);
        headCell.setCellValue("订单编号");
        headCell.setCellStyle(headCellStyle);
        sheet.setColumnWidth(column, COLUMN_WIDTH);
        column++;

        headCell = xssfRow.createCell(column);
        headCell.setCellValue("订单状态");
        headCell.setCellStyle(headCellStyle);
        sheet.setColumnWidth(column, COLUMN_WIDTH);
        column++;

        headCell = xssfRow.createCell(column);
        headCell.setCellValue("寄件人");
        headCell.setCellStyle(headCellStyle);
        sheet.setColumnWidth(column, COLUMN_WIDTH);
        column++;

        headCell = xssfRow.createCell(column);
        headCell.setCellValue("寄件人手机号");
        headCell.setCellStyle(headCellStyle);
        sheet.setColumnWidth(column, COLUMN_WIDTH);
        column++;

        headCell = xssfRow.createCell(column);
        headCell.setCellValue("寄件地址");
        headCell.setCellStyle(headCellStyle);
        sheet.setColumnWidth(column, COLUMN_WIDTH);
        column++;

        headCell = xssfRow.createCell(column);
        headCell.setCellValue("收件人");
        headCell.setCellStyle(headCellStyle);
        sheet.setColumnWidth(column, COLUMN_WIDTH);
        column++;

        headCell = xssfRow.createCell(column);
        headCell.setCellValue("收件人手机号");
        headCell.setCellStyle(headCellStyle);
        sheet.setColumnWidth(column, COLUMN_WIDTH);
        column++;

        headCell = xssfRow.createCell(column);
        headCell.setCellValue("收件地址");
        headCell.setCellStyle(headCellStyle);
        sheet.setColumnWidth(column, COLUMN_WIDTH);
        column++;

        headCell = xssfRow.createCell(column);
        headCell.setCellValue("快递员");
        headCell.setCellStyle(headCellStyle);
        sheet.setColumnWidth(column, COLUMN_WIDTH);
        column++;

        headCell = xssfRow.createCell(column);
        headCell.setCellValue("快递车辆");
        headCell.setCellStyle(headCellStyle);
        sheet.setColumnWidth(column, COLUMN_WIDTH);

        int row = 1;
        //填充数据
        for (Sorder sorder : records) {
            column = 0;
            xssfRow = sheet.createRow(row);

            XSSFCell cell = xssfRow.createCell(column);
            cell.setCellValue(sorder.getOrderNo());
            cell.setCellStyle(cellStyle);
            column++;

            cell = xssfRow.createCell(column);
            cell.setCellValue(OrderTypeEnum.getDesc(sorder.getOrderType()));
            cell.setCellStyle(cellStyle);
            column++;

            cell = xssfRow.createCell(column);
            cell.setCellValue(sorder.getOrderSenderName());
            cell.setCellStyle(cellStyle);
            column++;

            cell = xssfRow.createCell(column);
            cell.setCellValue(sorder.getOrderSenderPhone());
            cell.setCellStyle(cellStyle);
            column++;

            cell = xssfRow.createCell(column);
            cell.setCellValue(sorder.getOrderSenderPosition());
            cell.setCellStyle(cellStyle);
            column++;

            cell = xssfRow.createCell(column);
            cell.setCellValue(sorder.getOrderReceiverName());
            cell.setCellStyle(cellStyle);
            column++;

            cell = xssfRow.createCell(column);
            cell.setCellValue(sorder.getOrderReceiverPhone());
            cell.setCellStyle(cellStyle);
            column++;

            cell = xssfRow.createCell(column);
            cell.setCellValue(sorder.getOrderReceiverPosition());
            cell.setCellStyle(cellStyle);
            column++;

            cell = xssfRow.createCell(column);
            cell.setCellValue(sorder.getOrderTransName().isEmpty() ? "暂未分配快递车辆" : sorder.getOrderTransName());
            cell.setCellStyle(cellStyle);
            column++;

            cell = xssfRow.createCell(column);
            cell.setCellValue(sorder.getOrderCarNo().isEmpty() ? "暂未分配快递车辆" : sorder.getOrderCarNo());
            cell.setCellStyle(cellStyle);
            row++;
        }
        return workbook;
    }
}
