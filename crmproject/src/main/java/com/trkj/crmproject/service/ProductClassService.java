package com.trkj.crmproject.service;

import com.trkj.crmproject.entity.ProductClass;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author MarkerHub
 * @since 2021-11-06
 */
public interface ProductClassService extends IService<ProductClass> {
    boolean add(ProductClass productClass);
    List<ProductClass> findAnPr();
}
