package com.trkj.crmproject.service;

import com.trkj.crmproject.entity.Concerned;
import com.baomidou.mybatisplus.extension.service.IService;
import com.trkj.crmproject.util.MyResult;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zzl
 * @since 2021-11-06
 */
public interface ConcernedService extends IService<Concerned> {

    MyResult myResult();

    int addcon(Concerned concerned);

    int upacon(Concerned concerned);

    MyResult state1(String value);
    int delete(Integer conId);
    MyResult result(String name);

}
