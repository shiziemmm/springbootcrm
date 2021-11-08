package com.trkj.crmproject.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trkj.crmproject.entity.Quotation;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zzl
 * @since 2021-11-06
 */
public interface QuotationMapper extends BaseMapper<Quotation> {
    List<Quotation> selectAll(Page<Quotation> page,@Param("quotation") Quotation quotation);
    Quotation selectByQuId(@Param("quId") Integer quId);
}
