package com.trkj.crmproject.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trkj.crmproject.entity.Warehouse;
import com.trkj.crmproject.dao.WarehouseDao;
import com.trkj.crmproject.service.WarehouseService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trkj.crmproject.vo.SearchListVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author MarkerHub
 * @since 2021-11-06
 */
@Service
public class WarehouseServiceImpl extends ServiceImpl<WarehouseDao, Warehouse> implements WarehouseService {
    @Resource
    private WarehouseDao warehouseDao;
    @Override
    public IPage<Warehouse> findList(SearchListVo vo) {
        if (vo.getPageSize()==null){
            vo.setPageSize(10);
        }
        if (vo.getPageNum()==null){
            vo.setPageNum(1);
        }
        Page<Warehouse> page = new Page<>(vo.getPageNum(), vo.getPageSize());
        QueryWrapper<Warehouse> wrapper=new QueryWrapper<>();
        if (vo.getKeyword()!=null){
            wrapper.like("wa_name",vo.getKeyword())
            .or().like("wa_location",vo.getKeyword());
        }
        wrapper.eq("wa_timeliness",1);
        return warehouseDao.selectList(page,wrapper);
    }
}
