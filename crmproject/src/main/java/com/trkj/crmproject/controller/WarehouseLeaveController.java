package com.trkj.crmproject.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.trkj.crmproject.dao.OrderFromMapper;
import com.trkj.crmproject.entity.OrderFrom;
import com.trkj.crmproject.entity.WarehouseLeave;
import com.trkj.crmproject.service.WarehouseLeaveGoodsService;
import com.trkj.crmproject.service.WarehouseLeaveService;
import com.trkj.crmproject.util.MyResult;
import com.trkj.crmproject.util.ResultVoUtil;
import com.trkj.crmproject.vo.ResultVo;
import com.trkj.crmproject.vo.SearchListVo;
import org.apache.ibatis.annotations.Delete;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author MarkerHub
 * @since 2021-11-06
 */
@RestController
@RequestMapping("/warehouse-leave")
public class WarehouseLeaveController {
    @Resource
    private WarehouseLeaveService leaveService;
    @Resource
    private OrderFromMapper orderFromMapper;
    /**
     * 新增出库单
     */
    @PostMapping("/add")
    public ResultVo add(@RequestBody WarehouseLeave warehouseLeave){
        return ResultVoUtil.success(leaveService.save(warehouseLeave));
    }
    /**
     * 根据出库单id查询出库单和明细
     * @return
     */
    @GetMapping("/find/{id}")
    public ResultVo find(@PathVariable("id") int id){
        WarehouseLeave warehouseLeave = leaveService.findByIde(id);
        return ResultVoUtil.success(warehouseLeave);
    }
    /**
     * 查询全部出库单
     * @return
     */
    @PostMapping("/findAll")
    public ResultVo findAll(@RequestBody SearchListVo vo){
        IPage<Map> list = leaveService.findList(vo);
        return ResultVoUtil.success(list);
    }

    /**
     * 出库
     * @param warehouseLeave
     * @return
     */
    @PutMapping("/update")
    public ResultVo update(@RequestBody WarehouseLeave warehouseLeave){
        if (leaveService.update(warehouseLeave)){
            return ResultVoUtil.success();
        }
        return ResultVoUtil.error("出库失败");
    }

    /**
     *删除出库单及明细
     */
    @DeleteMapping("/delete/{id}")
    public ResultVo delete(@PathVariable("id") int id){
        if (leaveService.updateAll(id)){
            return ResultVoUtil.success();
        }
        return  ResultVoUtil.error("删除失败");
    }
}
