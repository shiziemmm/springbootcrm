package com.trkj.crmproject.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.trkj.crmproject.entity.Complaint;
import com.trkj.crmproject.entity.Concerned;
import com.trkj.crmproject.dao.ConcernedMapper;
import com.trkj.crmproject.entity.Invoice;
import com.trkj.crmproject.service.ConcernedService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trkj.crmproject.util.MyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
public class ConcernedServiceImpl extends ServiceImpl<ConcernedMapper, Concerned> implements ConcernedService {

    @Autowired
    ConcernedMapper concernedMapper;

    public MyResult myResult(){
        return MyResult.SUCCESS_DATA(concernedMapper.selectList(null));
    }
    public int addcon(Concerned concerned){
        return concernedMapper.insert(concerned);
    }

    public int upacon(Concerned concerned){
        return concernedMapper.updateById(concerned);
    }


    public MyResult state1(String value){
        Concerned r=new Concerned();
        MyResult m=new MyResult();
        r.setConType(value);
        QueryWrapper<Concerned> queryWrapper =
                new QueryWrapper<Concerned>(r);
        List<Concerned> returneds = concernedMapper.selectList(queryWrapper);
        if(returneds.size()>0){
            m.setCode(200);
            m.setMsg("查询成功");
            m.setData(returneds);
        }else{
            m.setCode(404);
            m.setData(returneds);
            m.setMsg("失败成功");

        }
        return m;

    }


    public int delete(Integer conId){
        return concernedMapper.deleteById(conId);
    }

    public MyResult result(String name){
        Concerned r =new Concerned();
        r.setConExecutor(name);
        QueryWrapper<Concerned> queryWrapper=new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(r.getConExecutor()),"con_executor",r.getConExecutor());
        System.err.println(concernedMapper.selectList(queryWrapper));
        return MyResult.SUCCESS_DATA(concernedMapper.selectList(queryWrapper));
    }
}
