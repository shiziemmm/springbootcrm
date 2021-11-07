package com.trkj.crmproject.service;

import com.trkj.crmproject.entity.Qa;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zzl
 * @since 2021-11-06
 */
public interface QaService extends IService<Qa> {

    public List<Qa> findQa();

    public List<Qa> findQaProblem(String qaProblem);

    public List<Qa> findQaClassify(String qaClassify);

    public List<Qa> selectQa(Qa qa);

    public void saveQa(Qa qa);
}
