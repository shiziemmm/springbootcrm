package com.trkj.crmproject.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.trkj.crmproject.entity.Product;
import com.trkj.crmproject.entity.WarehouseEnter;
import com.trkj.crmproject.service.ProductService;
import com.trkj.crmproject.service.WarehouseEnterService;
import com.trkj.crmproject.util.IdWorker;
import com.trkj.crmproject.util.MyResult;
import com.trkj.crmproject.util.ResultVoUtil;
import com.trkj.crmproject.vo.ResultVo;
import com.trkj.crmproject.vo.SearchListVo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.sql.Timestamp;
import java.util.Date;
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
@RequestMapping("/warehouse-enter")
public class WarehouseEnterController {
    @Resource
    private WarehouseEnterService enterService;
    /**
     * 新增入库单
     */
    @PostMapping("/add")
    public MyResult add(@RequestBody WarehouseEnter enter){
        return MyResult.SUCCESS_Object(enterService.addfind(enter));
    }
    /**
     * 根据id删除入库单
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public MyResult delete(@PathVariable("id") int id){
        return MyResult.SUCCESS_Object(enterService.deleteAll(id));
    }

    /**
     * 编辑入库单
     * @return
     */
    @PutMapping("/update")
    public MyResult update(@RequestBody WarehouseEnter enter){
        return MyResult.SUCCESS_Object(enterService.updatel(enter));
    }
    /**
     * 确认入库
     * @return
     */
    @PutMapping("/updateState")
    public MyResult updateState(@RequestBody WarehouseEnter enter){
        return MyResult.SUCCESS_Object(enterService.updateState(enter));
    }
    /**
     * 根据id查询入库
     * @return
     */
    @GetMapping("/find/{id}")
    public MyResult find(@PathVariable("id") int id){
        WarehouseEnter byId = enterService.findById(id);
        return MyResult.SUCCESS_Object(byId);
    }
    /**
     * 查询全部入库
     * @return
     */
    @PostMapping("/findAll")
    public ResultVo findAll(@RequestBody SearchListVo vo){
        IPage<WarehouseEnter> list = enterService.findList(vo);
        return ResultVoUtil.success(list);
    }
}
