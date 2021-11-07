package com.trkj.crmproject.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.trkj.crmproject.dao.EmpMapper;
import com.trkj.crmproject.dao.OrderFromMapper;
import com.trkj.crmproject.entity.Emp;
import com.trkj.crmproject.entity.OrderFrom;
import com.trkj.crmproject.entity.Returned;
import com.trkj.crmproject.dao.ReturnedMapper;
import com.trkj.crmproject.service.ReturnedService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.trkj.crmproject.util.MyResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.Date;
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
public class ReturnedServiceImpl extends ServiceImpl<ReturnedMapper, Returned> implements ReturnedService {
    @Autowired
    ReturnedMapper returnedMapper;

    @Autowired
    EmpMapper empMapper;


    @Autowired
    OrderFromMapper orderFromMapper;

    public MyResult returneds(){
        List<Returned> returneds = returnedMapper.selectList(null);
//        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (int i = 0; i < returneds.size(); i++) {
            returneds.get(i).setRetTime(returneds.get(i).getRetTime());
        }
        System.err.println(returneds);
        MyResult m=new MyResult();
        if(returneds.size()>0){
            m.setCode(200);
            m.setMsg("查询成功");
            m.setData(returneds);
        }else{
            m.setCode(404);
            m.setMsg("查询失败");
        }
        return m;
    }

    public MyResult state(String value){
        Returned r=new Returned();
        MyResult m=new MyResult();
        if(value.equals("已回")||value.equals("未回")){
            r.setRetWhether(value);
            QueryWrapper<Returned> queryWrapper =
                    new QueryWrapper<Returned>(r);
            List<Returned> returneds = returnedMapper.selectList(queryWrapper);
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

        }else{
            r.setInvoice(value);
            QueryWrapper<Returned> queryWrapper =
                    new QueryWrapper<Returned>(r);
            List<Returned> returneds = returnedMapper.selectList(queryWrapper);
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
    }

    public List<Emp> emp(){
        return empMapper.selectList(null);
    }

    public List<OrderFrom> select(){
        return orderFromMapper.selectList(null);
    }
    public int insert(Returned returned){
        return returnedMapper.insert(returned);
    }
    public int upa(Returned returned){
        return returnedMapper.updateById(returned);
    }
    public int delete(Integer reId){
        return returnedMapper.deleteById(reId);
    }
}
