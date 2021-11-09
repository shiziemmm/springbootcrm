package com.trkj.crmproject.dao;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.trkj.crmproject.entity.QuotationDetails;
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
public interface QuotationDetailsMapper extends BaseMapper<QuotationDetails> {
    List<QuotationDetails> selectAll(Page<QuotationDetails> page, @Param("qd") QuotationDetails quotationDetails);
}
