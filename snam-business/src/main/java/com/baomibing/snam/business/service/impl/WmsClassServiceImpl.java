package com.baomibing.snam.business.service.impl;

import com.baomibing.core.common.Assert;
import com.baomibing.core.common.SearchResult;
import com.baomibing.core.exception.ServerRuntimeException;
import com.baomibing.orm.base.MBaseServiceImpl;
import com.baomibing.snam.business.dto.WmsClassDto;
import com.baomibing.snam.business.entity.WmsClass;
import com.baomibing.snam.business.exception.WmsExceptionEnum;
import com.baomibing.snam.business.mapper.WmsClassMapper;
import com.baomibing.snam.business.service.WmsClassService;
import com.baomibing.tool.util.Checker;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

/**
 * WmsClassServiceImpl
 *
 * @author frog 2023/6/4 22:02
 * @version 1.0.0
 **/
@Service
public class WmsClassServiceImpl extends MBaseServiceImpl<WmsClassMapper, WmsClass, WmsClassDto> implements WmsClassService {

    @Override
    public SearchResult<WmsClassDto> search(WmsClassDto v, int pageNo, int pageSize) {
        return search(lambdaQuery(), pageNo, pageSize);
    }

    private List<WmsClassDto> listByName(String name) {
        if (Checker.beEmpty(name)) {
            return emptyList();
        }
        return mapper(baseMapper.selectList(lambdaQuery().eq(WmsClass::getClassName, name)));
    }

    @Override
    public void saveClass(WmsClassDto v) {
        Assert.CheckArgument(v, v.getClassName());
        if (Checker.beNotEmpty(listByName(v.getClassName()))) {
            throw new ServerRuntimeException(WmsExceptionEnum.CLASS_NAME_DUPLICATE, v.getClassName());
        }
        saveIt(v);
    }

    @Override
    public void updateClass(WmsClassDto v) {
        Assert.CheckArgument(v);
        updateIt(v);
    }

    @Override
    public void deleteClasses(Set<String> ids) {
        Assert.CheckArgument(ids);
        deletes(ids);
    }

    @Override
    public List<WmsClassDto> listByKeyWord(String keyword) {
        return mapper(baseMapper.listByKeyWord(keyword));
    }
}
