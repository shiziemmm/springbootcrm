package com.trkj.crmproject.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.trkj.crmproject.entity.Emp;
import com.trkj.crmproject.dao.EmpMapper;
import com.trkj.crmproject.service.EmpService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trkj.crmproject.util.MyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zzl
 * @since 2021-11-06
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class EmpServiceImpl extends ServiceImpl<EmpMapper, Emp> implements EmpService {
    @Autowired
    EmpMapper empMapper;
    public MyResult login(Emp e){
        QueryWrapper<Emp> queryWrapper =
                new QueryWrapper<Emp>(e);
        List<Emp> userList =
                empMapper.selectList(queryWrapper);
        MyResult s=new MyResult();
        if(userList.size()>0){
            s.setCode(200);
            s.setObj(userList);
            s.setMsg("登录成功");
        }else{
            s.setCode(404);
            s.setMsg("登录成功");
        }
        return s;
    }

    /**
     * 查询所有员工
     * @return
     */
    public List<Emp> selectList(){
        return empMapper.selectList(null);
    }

    /**
     * 查询所有负责订单员工
     */
    public List<Emp> empSelectAll(){
        return empMapper.empSelectAll();
    }

}
