package com.trkj.crmproject.service.impl;

import com.trkj.crmproject.entity.Issue;
import com.trkj.crmproject.dao.IssueMapper;
import com.trkj.crmproject.entity.Returned;
import com.trkj.crmproject.service.IssueService;
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
public class IssueServiceImpl extends ServiceImpl<IssueMapper, Issue> implements IssueService {
    @Autowired
    IssueMapper issueMapper;

    public MyResult issuelist(){
        return MyResult.SUCCESS_DATA(issueMapper.selectList(null));

    }

}
