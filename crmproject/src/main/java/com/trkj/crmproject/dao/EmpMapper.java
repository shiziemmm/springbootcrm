package com.trkj.crmproject.dao;

import com.trkj.crmproject.entity.Emp;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zzl
 * @since 2021-11-06
 */
@Mapper
public interface EmpMapper extends BaseMapper<Emp> {

    List<Emp> empSelectAll();

}
