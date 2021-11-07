package com.trkj.crmproject.dao;

import com.trkj.crmproject.entity.Product;
import com.trkj.crmproject.entity.ProductClass;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author MarkerHub
 * @since 2021-11-06
 */
public interface ProductClassDao extends BaseMapper<ProductClass> {
    List<ProductClass> findAnPr();
}
