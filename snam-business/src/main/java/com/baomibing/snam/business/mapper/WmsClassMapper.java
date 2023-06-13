package com.baomibing.snam.business.mapper;

import com.baomibing.snam.business.dto.WmsClassDto;
import com.baomibing.snam.business.entity.WmsClass;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface WmsClassMapper extends BaseMapper<WmsClass> {

    List<WmsClass> listByKeyWord(@Param("keyWord") String keyWord);
}
