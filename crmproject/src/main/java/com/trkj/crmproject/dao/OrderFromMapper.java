package com.trkj.crmproject.dao;

import com.trkj.crmproject.entity.OrderFrom;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.trkj.crmproject.entity.Vo.SelectWhere;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author zzl
 * @since 2021-11-06
 */
public interface OrderFromMapper extends BaseMapper<OrderFrom> {

    List<OrderFrom> selectOrderByTj(SelectWhere selectWhere);
    int updateprice(@Param("odrReturnPrice") double odrReturnPrice,@Param("odrOn") String odrOn);
    int updateprices(@Param("odrReturnPrice") double odrReturnPrice,@Param("odrOn") String odrOn);


    OrderFrom selectOrderByOdrId(String odrOn);


}
