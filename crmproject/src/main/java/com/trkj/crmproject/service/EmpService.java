package com.trkj.crmproject.service;

import com.trkj.crmproject.entity.Emp;
import com.baomidou.mybatisplus.extension.service.IService;
import com.trkj.crmproject.util.MyResult;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zzl
 * @since 2021-11-06
 */

public interface EmpService extends IService<Emp> {
 MyResult login(Emp e);

 List<Emp> selectList();


}
