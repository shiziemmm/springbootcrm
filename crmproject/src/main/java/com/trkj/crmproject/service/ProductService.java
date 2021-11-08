package com.trkj.crmproject.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.trkj.crmproject.entity.Product;
import com.baomidou.mybatisplus.extension.service.IService;
import com.trkj.crmproject.vo.SearchListVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author MarkerHub
 * @since 2021-11-06
 */
public interface ProductService extends IService<Product> {
    IPage<Product> findList(SearchListVo vo);
    List<Product> findAll(SearchListVo vo);
}
