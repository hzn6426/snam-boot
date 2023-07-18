package com.baomibing.snam.business.service;

import com.baomibing.core.common.SearchResult;
import com.baomibing.snam.business.dto.WmsOrderDto;

import java.util.Set;

public interface WmsOrderService {

    SearchResult<WmsOrderDto> search(WmsOrderDto v, int pageNo, int pageSize);

    void saveOrder(WmsOrderDto order);

    void updateOrder(WmsOrderDto order);

    void deleteOrders(Set<String> ids);

    WmsOrderDto getOrder(String id);
}
