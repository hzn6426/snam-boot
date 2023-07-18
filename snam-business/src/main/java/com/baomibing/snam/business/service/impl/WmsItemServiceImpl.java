package com.baomibing.snam.business.service.impl;

import com.baomibing.core.annotation.Action;
import com.baomibing.core.annotation.ActionConnect;
import com.baomibing.core.common.Assert;
import com.baomibing.core.common.SearchResult;
import com.baomibing.orm.base.MBaseServiceImpl;
import com.baomibing.snam.business.dto.WmsItemDto;
import com.baomibing.snam.business.entity.WmsItem;
import com.baomibing.snam.business.mapper.WmsItemMapper;
import com.baomibing.snam.business.service.WmsItemService;
import com.baomibing.snam.feign.authority.api.GroupApi;
import com.baomibing.snam.feign.authority.constant.UserTag;
import com.baomibing.snam.feign.authority.dto.GroupWrapDto;
import com.baomibing.tool.constant.Formats;
import com.baomibing.tool.constant.Strings;
import com.baomibing.tool.util.CharacterUtil;
import com.baomibing.tool.util.Checker;
import com.google.common.collect.Lists;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * WmsItemServiceImpl
 *
 * @author frog 2023/6/4 22:01
 * @version 1.0.0
 **/
@Service
public class WmsItemServiceImpl extends MBaseServiceImpl<WmsItemMapper, WmsItem, WmsItemDto> implements WmsItemService {

    @Autowired private GroupApi groupApi;

    @Action(value = "ITEM_SEARCH")
    @ActionConnect(value = {"selectList","selectCount"},
        userAuthColumn= UserTag.KEEPER + Strings.HASH + "keeper_code",
        groupAuthColumn = UserTag.KEEPER + Strings.HASH + "keeper_code"
    )
    @Override
    public SearchResult<WmsItemDto> search(WmsItemDto item, int pageNo, int pageSize) {
        SearchResult<WmsItemDto> result =  search(lambdaQuery().like(Checker.beNotEmpty(item.getCode()), WmsItem::getCode, item.getCode())
                .like(Checker.beNotEmpty(item.getName()), WmsItem::getName, item.getName())
                .eq(Checker.beNotEmpty(item.getClassId()), WmsItem::getClassId, item.getClassId())
                .eq(Checker.beNotEmpty(item.getProviderId()), WmsItem::getProviderId, item.getProviderId()), pageNo, pageSize);
        List<GroupWrapDto> companys = groupApi.listBranchCompanines();
        Map<String, String> groupmap = companys.stream().collect(Collectors.toMap(GroupWrapDto::getId, GroupWrapDto::getGroupName));
        result.getDataList().forEach(r -> {
            String key = groupmap.keySet().stream().filter(k -> r.getGroupId().startsWith(k)).findFirst().orElse(null);
            if (Checker.beNotEmpty(key)) {
                r.setCompanyName(groupmap.get(key));
            }
        });
        return result;
    }

    private List<WmsItemDto> listByCode(String code) {
        if (Checker.beEmpty(code)) {
            return Lists.newArrayList();
        }
        return mapper(baseMapper.selectList(lambdaQuery().eq(WmsItem::getCode, code)));
    }

    @Override
    public void saveItem(WmsItemDto item) {
        Assert.CheckArgument(item);
        Assert.CheckArgument(item.getKeeperCode());
        DateTime dateTime = new DateTime();
        Integer count = baseMapper.getCountItemsByMonth(dateTime.toString(Formats.DEFAULT_MONTH_FORMAT));
        String code = "IT" + dateTime.toString(Formats.yearMonth) + CharacterUtil.leftPadSomeChars(String.valueOf(count), 4, "0");
//        if (Checker.beNotEmpty(listByCode(item.getCode()))) {
//            throw  new ServerRuntimeException(ITEM_CODE_DUPLICATE, item.getCode());
//        }
        item.setCode(code);
        String groupId = groupApi.getGroupIdByUserNo(item.getKeeperCode());
        item.setKeeperCode(groupId + Strings.HASH + item.getKeeperCode());
        saveIt(item);
    }

    @Action(value = "ITEM_UPDATE")
    @ActionConnect(value = "updateById")
    @Override
    public void updateItem(WmsItemDto item) {
        Assert.CheckArgument(item);
        Assert.CheckArgument(item.getKeeperCode());
        String groupId = groupApi.getGroupIdByUserNo(item.getKeeperCode());
        item.setKeeperCode(groupId + Strings.HASH + item.getKeeperCode());
        updateIt(item);
    }

    @Action(value = "ITEM_DELETE")
    @ActionConnect(value = "deleteBatchIds")
    @Override
    public void deleteItems(Set<String> ids) {
        Assert.CheckArgument(ids);
        deletes(ids);
    }

    @Override
    public List<WmsItemDto> listByKeyWord(String keyword) {
        return mapper(baseMapper.listByKeyWord(keyword));
    }
}
