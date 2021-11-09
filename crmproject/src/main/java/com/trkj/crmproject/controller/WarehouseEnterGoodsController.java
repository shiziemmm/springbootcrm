package com.trkj.crmproject.controller;


import com.trkj.crmproject.service.WarehouseEnterGoodsService;
import com.trkj.crmproject.util.ResultVoUtil;
import com.trkj.crmproject.vo.ResultVo;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author MarkerHub
 * @since 2021-11-06
 */
@RestController
@RequestMapping("/warehouse-enter-goods")
public class WarehouseEnterGoodsController {
    @Resource
    private WarehouseEnterGoodsService goodsService;
    /**
     * 根据id删除入库明细
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public ResultVo delete(@PathVariable("id") int id){
        if (goodsService.removeById(id)){
            return ResultVoUtil.success();
        }
        return ResultVoUtil.success("删除失败");
    }
}
