package com.baomibing.snam.business.service.impl;

import com.baomibing.core.annotation.Action;
import com.baomibing.core.annotation.ActionConnect;
import com.baomibing.core.common.Assert;
import com.baomibing.core.common.SearchResult;
import com.baomibing.core.exception.ServerRuntimeException;
import com.baomibing.orm.base.MBaseServiceImpl;
import com.baomibing.snam.business.dto.WmsItemDto;
import com.baomibing.snam.business.entity.WmsItem;
import com.baomibing.snam.business.mapper.WmsItemMapper;
import com.baomibing.snam.business.service.WmsItemService;
import com.baomibing.snam.feign.authority.api.BusinessAuthApi;
import com.baomibing.tool.util.Checker;
import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

import static com.baomibing.snam.business.exception.WmsExceptionEnum.ITEM_CODE_DUPLICATE;

/**
 * WmsItemServiceImpl
 *
 * @author frog 2023/6/4 22:01
 * @version 1.0.0
 **/
@Service
public class WmsItemServiceImpl extends MBaseServiceImpl<WmsItemMapper, WmsItem, WmsItemDto> implements WmsItemService {
    @Action(value = "ITEM_SEARCH")
    @ActionConnect(value = {"selectList","selectCount"})
    @Override
    public SearchResult<WmsItemDto> search(WmsItemDto item, int pageNo, int pageSize) {
        return search(lambdaQuery().like(Checker.beNotEmpty(item.getCode()), WmsItem::getCode, item.getCode())
                .like(Checker.beNotEmpty(item.getName()), WmsItem::getName, item.getName())
                .eq(Checker.beNotEmpty(item.getClassId()), WmsItem::getClassId, item.getClassId())
                .eq(Checker.beNotEmpty(item.getProviderId()), WmsItem::getProviderId, item.getProviderId()), pageNo, pageSize);
    }

    private List<WmsItemDto> listByCode(String code) {
        if (Checker.beEmpty(code)) {
            return Lists.newArrayList();
        }
        return mapper(baseMapper.selectList(lambdaQuery().eq(WmsItem::getCode, code)));
    }

    @Override
    public void saveItem(WmsItemDto item) {
        Assert.CheckArgument(item, item.getCode());
        if (Checker.beNotEmpty(listByCode(item.getCode()))) {
            throw  new ServerRuntimeException(ITEM_CODE_DUPLICATE, item.getCode());
        }
        saveIt(item);
    }

    @Action(value = "ITEM_UPDATE")
    @ActionConnect(value = "updateById")
    @Override
    public void updateItem(WmsItemDto item) {
        Assert.CheckArgument(item);
        updateIt(item);
    }

    @Action(value = "ITEM_DELETE")
    @ActionConnect(value = "deleteBatchIds")
    @Override
    public void deleteItems(Set<String> ids) {
        Assert.CheckArgument(ids);
        deletes(ids);
    }
}
