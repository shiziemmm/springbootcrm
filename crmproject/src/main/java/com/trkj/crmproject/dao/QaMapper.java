package com.trkj.crmproject.dao;

import com.trkj.crmproject.entity.Qa;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zzl
 * @since 2021-11-06
 */
public interface QaMapper extends BaseMapper<Qa> {
    public List<Qa> findQa();

    public List<Qa> findQaProblem(String qaProblem);

    public List<Qa> findQaClassify(String qaClassify);

    public List<Qa> selectQa(Qa qa);

    public void addQa(Qa qa);

    public void updateQa(Qa qa);

}
