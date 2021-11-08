package com.trkj.crmproject.service;

import com.trkj.crmproject.entity.Programme;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zzl
 * @since 2021-11-06
 */
public interface ProgrammeService extends IService<Programme> {
    Boolean addProgramme(Programme programme);
    Boolean del(Integer proid);
}
