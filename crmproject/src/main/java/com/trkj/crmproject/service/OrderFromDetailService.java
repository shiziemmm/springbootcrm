package com.trkj.crmproject.service;

import com.trkj.crmproject.entity.OrderFromDetail;
import com.baomidou.mybatisplus.extension.service.IService;
import com.trkj.crmproject.entity.Vo.SelectWhere;

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

    boolean addOrderDetails(List<OrderFromDetail> detailsList,String odrOn,Integer odrId);

    boolean addWrehouseLeave(List<OrderFromDetail> list,Integer odrId);

    List<OrderFromDetail> selectOrderDetailsWhere(SelectWhere selectWhere);

}
