package com.baomibing.snam.business.service.impl;

import com.baomibing.core.common.Assert;
import com.baomibing.core.common.SearchResult;
import com.baomibing.core.exception.ServerRuntimeException;
import com.baomibing.orm.base.MBaseServiceImpl;
import com.baomibing.snam.business.dto.WmsProviderDto;
import com.baomibing.snam.business.entity.WmsProvider;
import com.baomibing.snam.business.exception.WmsExceptionEnum;
import com.baomibing.snam.business.mapper.WmsProviderMapper;
import com.baomibing.snam.business.service.WmsProviderService;
import com.baomibing.tool.util.Checker;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * WmsProviderServiceImpl
 *
 * @author frog 2023/6/4 22:03
 * @version 1.0.0
 **/
@Service
public class WmsProviderServiceImpl extends MBaseServiceImpl<WmsProviderMapper, WmsProvider, WmsProviderDto> implements WmsProviderService {

    @Override
    public SearchResult<WmsProviderDto> search(WmsProviderDto v, int pageNo, int pageSize) {
        return search(lambdaQuery(), pageNo, pageSize);
    }

    @Override
    public void saveProvider(WmsProviderDto v) {
        Assert.CheckArgument(v, v.getCode(), v.getName(), v.getSimpleName());
        List<WmsProvider> list = baseMapper.selectList(lambdaQuery().eq(WmsProvider::getCode, v.getCode())
                        .or().eq(WmsProvider::getName, v.getName())
                        .or().eq(WmsProvider::getSimpleName, v.getSimpleName()));
        if (Checker.beNotEmpty(list)) {
            throw new ServerRuntimeException(WmsExceptionEnum.PROVIDER_CODE_OR_NAME_OR_SNAME_DUPLICATE);
        }
        saveIt(v);
    }

    @Override
    public void updateProvider(WmsProviderDto v) {
        Assert.CheckArgument(v);
        updateIt(v);
    }

    @Override
    public void deleteProviders(Set<String> ids) {
        Assert.CheckArgument(ids);
        deletes(ids);
    }

    @Override
    public List<WmsProviderDto> listByKeyWord(String keyword) {
        return mapper(baseMapper.listByKeyWord(keyword));
    }
}
