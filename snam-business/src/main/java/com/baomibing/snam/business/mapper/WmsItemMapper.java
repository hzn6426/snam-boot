package com.baomibing.snam.business.mapper;

import com.baomibing.snam.business.entity.WmsClass;
import com.baomibing.snam.business.entity.WmsItem;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * WmsItemMapper
 *
 * @author frog 2023/6/4 21:57
 * @version 1.0.0
 **/
public interface WmsItemMapper extends BaseMapper<WmsItem> {

    List<WmsItem> listByKeyWord(@Param("keyWord") String keyWord);

    Integer getCountItemsByMonth(@Param("date") String date);
}
