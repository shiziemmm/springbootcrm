package com.trkj.crmproject.service.impl;

import com.trkj.crmproject.entity.Qa;
import com.trkj.crmproject.dao.QaMapper;
import com.trkj.crmproject.service.QaService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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
public class QaServiceImpl extends ServiceImpl<QaMapper, Qa> implements QaService {
    @Resource
    QaMapper qaMapper;

    public List<Qa> findQa(){
        return qaMapper.findQa();
    }

    public List<Qa> findQaProblem(String qaProblem){
        return qaMapper.findQaProblem(qaProblem);
    }

    public List<Qa> findQaClassify(String qaClassify){
        return qaMapper.findQaClassify(qaClassify);
    }

    public List<Qa> selectQa(Qa qa){
        return qaMapper.selectQa(qa);
    }

    public void saveQa(Qa qa){
        if (qa.getQaId()==null || qa.getQaId().equals("")){
            qaMapper.addQa(qa);
        }else{
            qaMapper.updateQa(qa);
        }
    }
}
