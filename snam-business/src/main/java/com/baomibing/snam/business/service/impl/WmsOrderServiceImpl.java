package com.baomibing.snam.business.service.impl;

import com.baomibing.core.common.Assert;
import com.baomibing.core.common.SearchResult;
import com.baomibing.orm.base.MBaseServiceImpl;
import com.baomibing.snam.business.dto.WmsOrderDto;
import com.baomibing.snam.business.entity.WmsOrder;
import com.baomibing.snam.business.mapper.WmsOrderMapper;
import com.baomibing.snam.business.service.WmsOrderService;
import com.baomibing.tool.util.Checker;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * WmsOrderServiceImpl
 *
 * @author frog 2023/7/13 10:14
 * @version 1.0.0
 **/
@Service
public class WmsOrderServiceImpl extends MBaseServiceImpl<WmsOrderMapper, WmsOrder, WmsOrderDto> implements WmsOrderService {

    @Override
    public SearchResult<WmsOrderDto> search(WmsOrderDto v, int pageNo, int pageSize) {
        LambdaQueryWrapper<WmsOrder> wrapper = lambdaQuery();
        wrapper.like(Checker.beNotEmpty(v.getCode()), WmsOrder::getCode, v.getCode())
                .ge(Checker.beNotNull(v.getStart()), WmsOrder::getCreateTime, v.getStart())
                .le(Checker.beNotNull(v.getEnd()), WmsOrder::getCreateTime, v.getEnd())
                .eq(Checker.beNotEmpty(v.getSellerCode()), WmsOrder::getSellerCode, v.getServiceCode());
        return search(wrapper, pageNo, pageSize);
    }

    @Override
    public void saveOrder(WmsOrderDto order) {
        super.saveIt(order);
    }

    @Override
    public void updateOrder(WmsOrderDto order) {
        super.updateIt(order);
    }

    @Override
    public void deleteOrders(Set<String> ids) {
        super.deletes(ids);
    }

    @Override
    public WmsOrderDto getOrder(String id) {
        return getIt(id);
    }
}
