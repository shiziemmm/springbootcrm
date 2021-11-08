package com.trkj.crmproject.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.trkj.crmproject.entity.Complaint;
import com.trkj.crmproject.dao.ComplaintMapper;
import com.trkj.crmproject.entity.Concerned;
import com.trkj.crmproject.entity.Invoice;
import com.trkj.crmproject.entity.Returned;
import com.trkj.crmproject.service.ComplaintService;
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
public class ComplaintServiceImpl extends ServiceImpl<ComplaintMapper, Complaint> implements ComplaintService {

    @Autowired
    ComplaintMapper complaintMapper;


    public MyResult myResult(){
        return MyResult.SUCCESS_DATA(complaintMapper.selectList(null));
    }

    public int add(Complaint complaint){
        int insert = complaintMapper.insert(complaint);
        if(insert>0){
            return 1;
        }else{
            return 0;
        }
    }

    public int upa(Complaint complaint){
        int i = complaintMapper.updateById(complaint);
        if(i>0){
            return 1;
        }else{
            return 0;
        }
    }

    public MyResult state1(String value){
        Complaint r=new Complaint();
        MyResult m=new MyResult();
        r.setComTreating(value);
        QueryWrapper<Complaint> queryWrapper =
                new QueryWrapper<Complaint>(r);
        List<Complaint> returneds = complaintMapper.selectList(queryWrapper);
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
    public int shanchu(Integer plaId){
        return complaintMapper.deleteById(plaId);
    }
    public MyResult myResults(String name){
        Complaint r=new Complaint();
        r.setComLinkman(name);
        QueryWrapper<Complaint> queryWrapper=new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(r.getComLinkman()),"com_linkman",r.getComLinkman());
        System.err.println(complaintMapper.selectList(queryWrapper));
        return MyResult.SUCCESS_DATA(complaintMapper.selectList(queryWrapper));
    }

}
