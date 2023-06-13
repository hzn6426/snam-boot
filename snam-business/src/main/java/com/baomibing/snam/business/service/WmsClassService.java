package com.baomibing.snam.business.service;

import com.baomibing.core.base.MBaseService;
import com.baomibing.core.common.SearchResult;
import com.baomibing.snam.business.dto.WmsClassDto;

import java.util.List;
import java.util.Set;

public interface WmsClassService extends MBaseService<WmsClassDto> {

    SearchResult<WmsClassDto> search(WmsClassDto v, int pageNo, int pageSize);

    void saveClass(WmsClassDto v);

    void updateClass(WmsClassDto v);

    void deleteClasses(Set<String> ids);

    List<WmsClassDto> listByKeyWord(String keyword);
}
