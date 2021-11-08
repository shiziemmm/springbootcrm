package com.trkj.crmproject.service;

import com.trkj.crmproject.entity.Complaint;
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
public interface ComplaintService extends IService<Complaint> {
    MyResult myResult();
    int add(Complaint complaint);
    int upa(Complaint complaint);
    MyResult state1(String value);
    int shanchu(Integer plaId);
    MyResult myResults(String name);


}
