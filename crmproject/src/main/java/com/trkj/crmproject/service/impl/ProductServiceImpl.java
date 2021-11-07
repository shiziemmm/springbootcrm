package com.trkj.crmproject.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trkj.crmproject.entity.Product;
import com.trkj.crmproject.dao.ProductDao;
import com.trkj.crmproject.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trkj.crmproject.vo.SearchListVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author MarkerHub
 * @since 2021-11-06
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductDao, Product> implements ProductService {
    @Resource
    private ProductDao productDao;
    @Override
    public IPage<Product> findList(SearchListVo vo) {
        if (vo.getPageNum()==null){
            vo.setPageNum(1);
        }
        if (vo.getPageSize()==null){
            vo.setPageSize(10);
        }
        Page<Product> page=new Page<>(vo.getPageNum(),vo.getPageSize());
        QueryWrapper<Product> wrapper=new QueryWrapper<>();
        if (vo.getKeyword()!=null){
            wrapper.like("CONCAT(pr_coding,pr_name,pr_model)",vo.getKeyword());
        }
        wrapper.eq("pr_timeliness",1);
        IPage<Product> productIPage = productDao.selectList(page, wrapper);
        return productIPage;
    }

    @Override
    public List<Product> findAll() {
        QueryWrapper<Product> wrapper =new QueryWrapper<>();
        wrapper.eq("pr_state",0);
        List<Product> list = list(wrapper);
        return list;
    }
}
