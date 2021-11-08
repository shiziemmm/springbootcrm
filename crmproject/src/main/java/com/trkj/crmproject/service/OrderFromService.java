package com.trkj.crmproject.service;

import com.trkj.crmproject.entity.Client;
import com.trkj.crmproject.entity.OrderFrom;
import com.baomidou.mybatisplus.extension.service.IService;
import com.trkj.crmproject.entity.OrderFromDetail;
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
public interface OrderFromService extends IService<OrderFrom> {

    List<Client> selectByName(String name);

    boolean addOrder(OrderFrom orderFrom);

    List<OrderFrom> selectOrderByTj(SelectWhere selectWhere);

    OrderFrom selectOrderByOdrId(String odrOn);

}
