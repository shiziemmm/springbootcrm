package com.trkj.crmproject.controller;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.trkj.crmproject.entity.WarehouseInventory;
import com.trkj.crmproject.service.WarehouseInventoryService;
import com.trkj.crmproject.util.MyResult;
import com.trkj.crmproject.util.ResultVoUtil;
import com.trkj.crmproject.vo.ResultVo;
import com.trkj.crmproject.vo.SearchListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
@RequestMapping("/warehouse-inventory")
public class WarehouseInventoryController {
    @Autowired
    private WarehouseInventoryService inventoryService;
    /**
     * 新增库存数据
     */
    @PostMapping("/add")
    public MyResult add(@RequestBody List<WarehouseInventory> inventory){
        return MyResult.SUCCESS_Object(inventoryService.add(inventory));
    }
    /**
     * 删除无库存的商品数据
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public MyResult delete(@PathVariable("id") int id){
        return null;
    }

    /**
     * 删减库存数量
     * @return
     */
    @PutMapping("/update")
    public MyResult update(@RequestBody List<WarehouseInventory> inventories){

        return MyResult.SUCCESS_Object(inventoryService.updateAll(inventories));
    }
    /**
     * 根据产品id查询库存
     * @return
     */
    @GetMapping("/find/{id}")
    public MyResult find(@PathVariable("id") int id){
        return MyResult.SUCCESS_Object(inventoryService.findById(id));
    }
    /**
     * 查询全部产品库存
     * @return
     */
    @PostMapping("/findAll")
    public ResultVo findAll(@RequestBody SearchListVo vo){
        IPage<Map> list = inventoryService.findList(vo);
        return ResultVoUtil.success(list);
    }
}
