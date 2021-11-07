package com.trkj.crmproject.service;

import com.trkj.crmproject.entity.OrderFromDetail;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author zzl
 * @since 2021-11-06
 */
public interface OrderFromDetailService extends IService<OrderFromDetail> {

    boolean addOrderDetails(List<OrderFromDetail> detailsList);

}
