package com.baomibing.snam.business.mapper;

import com.baomibing.snam.business.entity.WmsOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

public interface WmsOrderMapper extends BaseMapper<WmsOrder> {

    Integer getCountOrdersByMonth(@Param("date") String date);
}
