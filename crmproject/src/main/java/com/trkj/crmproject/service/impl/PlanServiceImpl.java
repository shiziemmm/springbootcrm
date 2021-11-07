package com.trkj.crmproject.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.trkj.crmproject.entity.Plan;
import com.trkj.crmproject.dao.PlanMapper;
import com.trkj.crmproject.entity.Returned;
import com.trkj.crmproject.service.PlanService;
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
public class PlanServiceImpl extends ServiceImpl<PlanMapper, Plan> implements PlanService {
        @Autowired
        PlanMapper planMapper;

        public MyResult planlist(){
            return MyResult.SUCCESS_DATA(planMapper.selectList(null));
        }

        public int addplan(Plan plan){
            return planMapper.insert(plan);
        }
        public int delet(Integer id){
            return planMapper.deleteById(id);
        }

        public int upd(Plan plan){
            return planMapper.updateById(plan);
        }
    public MyResult state(String value){
        Plan r=new Plan();
        MyResult m=new MyResult();
        if(value.equals("已回")||value.equals("未回")){
            r.setPlaWhether(value);
            QueryWrapper<Plan> queryWrapper =
                    new QueryWrapper<Plan>(r);
            List<Plan> returneds = planMapper.selectList(queryWrapper);
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
            QueryWrapper<Plan> queryWrapper =
                    new QueryWrapper<Plan>(r);
            List<Plan> returneds = planMapper.selectList(queryWrapper);
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
}
