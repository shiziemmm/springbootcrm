package com.trkj.crmproject.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.trkj.crmproject.entity.Warehouse;
import com.trkj.crmproject.service.WarehouseService;
import com.trkj.crmproject.util.MyResult;
import com.trkj.crmproject.util.ResultVoUtil;
import com.trkj.crmproject.vo.ResultVo;
import com.trkj.crmproject.vo.SearchListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author MarkerHub
 * @since 2021-11-06
 */
@RestController
@RequestMapping("/warehouse")
public class WarehouseController {
    @Autowired
    private WarehouseService warehouseService;

    /**
     * 新增仓库
     */
    @PostMapping("/add")
    public MyResult add(@RequestBody Warehouse warehouse){
        warehouse.setWaTimeliness(true);
        return MyResult.SUCCESS_Object(warehouseService.save(warehouse));
    }
    /**
     * 根据id删除仓库
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public MyResult delete(@PathVariable("id") int id){
        return MyResult.SUCCESS_Object(warehouseService.removeById(id));
    }

    /**
     * 根据id修改仓库
     * @return
     */
    @PutMapping("/update/{id}")
    public MyResult update(@PathVariable("id") int id, @RequestBody Warehouse warehouse){
        warehouse.setWaId(id);
        return MyResult.SUCCESS_Object(warehouseService.saveOrUpdate(warehouse));
    }
    /**
     * 根据id查询仓库
     * @return
     */
    @GetMapping("/find/{id}")
    public MyResult find(@PathVariable("id") int id){
        return MyResult.SUCCESS_Object(warehouseService.getById(id));
    }
    /**
     * 查询全部仓库
     * @return
     */
    @PostMapping("/findAll")
    public ResultVo findAll(@RequestBody SearchListVo vo){
        IPage<Warehouse> list = warehouseService.findList(vo);
        return ResultVoUtil.success(list);
    }
}
