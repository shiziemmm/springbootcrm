package com.trkj.crmproject.service.impl;

import com.trkj.crmproject.entity.ProductClass;
import com.trkj.crmproject.dao.ProductClassDao;
import com.trkj.crmproject.service.ProductClassService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class ProductClassServiceImpl extends ServiceImpl<ProductClassDao, ProductClass> implements ProductClassService {
    @Autowired
    private ProductClassDao productClassDao;
    @Override
    public boolean add(ProductClass productClass) {
        productClass.setPcTimeliness(true);
        return save(productClass);
    }

    @Override
    public List<ProductClass> findAnPr() {
        return productClassDao.findAnPr();
    }
}
