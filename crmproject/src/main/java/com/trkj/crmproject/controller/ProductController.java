package com.trkj.crmproject.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.trkj.crmproject.entity.Product;
import com.trkj.crmproject.service.ProductService;
import com.trkj.crmproject.util.IdWorker;
import com.trkj.crmproject.util.MyResult;
import com.trkj.crmproject.util.ResultVoUtil;
import com.trkj.crmproject.vo.ResultVo;
import com.trkj.crmproject.vo.SearchListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/product")
public class ProductController {
    @Resource
    private ProductService productService;
    @Resource
    private IdWorker idWorker;
    /**
     * 新增产品
     */
    @PostMapping("/add")
    public MyResult add(@RequestBody Product product){
        product.setPrTimeliness(true);
        product.setPrCoding(idWorker.nextId()+"");
        return MyResult.SUCCESS_Object(productService.save(product));
    }
    /**
     * 根据id删除产品
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public MyResult delete(@PathVariable("id") int id){
        return MyResult.SUCCESS_Object(productService.removeById(id));
    }

    /**
     * 根据id修改产品
     * @return
     */
    @PutMapping("/update/{id}")
    public MyResult update(@PathVariable("id") int id, @RequestBody Product product){
        product.setPrId(id);
        return MyResult.SUCCESS_Object(productService.saveOrUpdate(product));
    }
    /**
     * 根据id查询产品
     * @return
     */
    @GetMapping("/find/{id}")
    public MyResult find(@PathVariable("id") int id){
        Product product = productService.getById(id);
        return MyResult.SUCCESS_Object(product);
    }
    /**
     * 查询全部产品
     * @return
     */
    @PostMapping("/findAll")
    public ResultVo findAll(@RequestBody SearchListVo vo){
        IPage<Product> list = productService.findList(vo);
        return ResultVoUtil.success(list);
    }
    /**
     * 查询所以在售产品
     * @return
     */
    @GetMapping("/findSale")
    public ResultVo findSale(){
        List<Product> all = productService.findAll();
        return ResultVoUtil.success(all);
    }

}
