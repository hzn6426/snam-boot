package com.baomibing.snam.business.controller;

import com.baomibing.snam.business.dto.WmsClassDto;
import com.baomibing.snam.business.dto.WmsItemDto;
import com.baomibing.snam.business.service.WmsItemService;
import com.baomibing.tool.common.PageQuery;
import com.baomibing.web.base.MBaseController;
import com.baomibing.web.common.R;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * WmsItemController
 *
 * @author frog 2023/6/4 22:04
 * @version 1.0.0
 **/
@RestController
@RequestMapping(path = { "/api/item"}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class WmsItemController extends MBaseController<WmsItemDto> {

    @Autowired private WmsItemService itemService;

    @PostMapping("search")
    public R<WmsItemDto> search(@RequestBody PageQuery<WmsItemDto> pageQuery) {
        return R.build(itemService.search(pageQuery.getDto(), pageQuery.getPageNo(), pageQuery.getPageSize()));
    }

    @GetMapping("/{id}")
    public WmsItemDto getItem(@PathVariable("id") String id) {
        return itemService.getIt(id);
    }

    @PutMapping
    public void updateItem(@RequestBody WmsItemDto item) {
        itemService.updateItem(item);
    }

    @PostMapping
    public void saveItem(@RequestBody WmsItemDto item) {
        itemService.saveItem(item);
    }

    @DeleteMapping
    public void deleteItems(@RequestBody List<String> ids) {
        itemService.deleteItems(Sets.newHashSet(ids));
    }

    @GetMapping("listByKeyWord")
    public List<WmsItemDto> listByKeyWord(@RequestParam String keyWord) {
        return itemService.listByKeyWord(keyWord);
    }

}
