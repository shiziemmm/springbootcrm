package com.trkj.crmproject.controller;

import com.trkj.crmproject.entity.WarehouseLeaveGoods;
import com.trkj.crmproject.service.WarehouseLeaveGoodsService;
import com.trkj.crmproject.util.ResultVoUtil;
import com.trkj.crmproject.vo.ResultVo;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author MarkerHub
 * @since 2021-11-06
 */
@RestController
@RequestMapping("/warehouse-leave-goods")
public class WarehouseLeaveGoodsController {
    @Resource
    private WarehouseLeaveGoodsService leaveGoodsService;
    /**
     * 新增出库明细单
     */
    @PostMapping("/add")
    public ResultVo add(@RequestBody List<WarehouseLeaveGoods> leaveGoods){
        return ResultVoUtil.success(leaveGoodsService.saveBatch(leaveGoods));
    }
}
