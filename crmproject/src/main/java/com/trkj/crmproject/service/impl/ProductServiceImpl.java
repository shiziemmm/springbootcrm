package com.trkj.crmproject.service.impl;

import com.trkj.crmproject.entity.Product;
import com.trkj.crmproject.dao.ProductDao;
import com.trkj.crmproject.service.ProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
