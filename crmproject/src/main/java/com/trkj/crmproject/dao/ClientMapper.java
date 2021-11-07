package com.trkj.crmproject.dao;

import com.trkj.crmproject.entity.Client;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zzl
 * @since 2021-11-06
 */
public interface ClientMapper extends BaseMapper<Client> {
    List<Client> selectByName(String clientName);

}
