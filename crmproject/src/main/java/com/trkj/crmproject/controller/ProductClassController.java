package com.trkj.crmproject.controller;
import com.trkj.crmproject.entity.ProductClass;
import com.trkj.crmproject.service.ProductClassService;
import com.trkj.crmproject.util.MyResult;
import com.trkj.crmproject.util.ResultVoUtil;
import com.trkj.crmproject.vo.ResultVo;
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
@RequestMapping("/product-class")
public class ProductClassController {
    @Resource
    private ProductClassService productClassService;
    /**
     * 新增产品类别
     * @param productClass
     * @return
     */
    @PostMapping("/add")
    public MyResult add(@RequestBody ProductClass productClass){
            return MyResult.SUCCESS_Object(productClassService.save(productClass));
    }
    /**
     * 根据id删除产品类别
     * @return
     */
    @DeleteMapping("/delete/{id}")
    public MyResult delete(@PathVariable("id") int id){
        return MyResult.SUCCESS_Object(productClassService.removeById(id));
    }

    /**
     * 根据id修改产品类别
     * @return
     */
    @PutMapping("/update/{id}")
    public MyResult update(@PathVariable("id") int id, @RequestBody ProductClass productClass){
        productClass.setPcId(id);
        return MyResult.SUCCESS_Object(productClassService.saveOrUpdate(productClass));
    }
    /**
     * 根据id查询产品类别
     * @return
     */
    @GetMapping("/find/{id}")
    public MyResult find(@PathVariable("id") String id){
        ProductClass productClass = productClassService.getById(id);
        return MyResult.SUCCESS_Object(productClass);
    }
    /**
     * 查询全部产品类别
     * @return
     */
    @GetMapping("/findAll")
    public ResultVo findAll(){
        List<ProductClass> all = productClassService.list();
        return ResultVoUtil.success(all);
    }
    /**
     * 查询全部类别and产品
     */
    @GetMapping("/findJoin")
    public ResultVo findJoin(){
        List<ProductClass> all = productClassService.findAnPr();
        return ResultVoUtil.success(all);
    }
}
