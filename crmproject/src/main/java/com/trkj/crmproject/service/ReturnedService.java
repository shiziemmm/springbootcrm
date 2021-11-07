package com.trkj.crmproject.service;

import com.trkj.crmproject.entity.Returned;
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
public interface ReturnedService extends IService<Returned> {
    MyResult returneds();
    MyResult state(String value);
}
