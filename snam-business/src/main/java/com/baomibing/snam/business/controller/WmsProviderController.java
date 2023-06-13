package com.baomibing.snam.business.controller;

import com.baomibing.snam.business.dto.WmsProviderDto;
import com.baomibing.snam.business.service.WmsProviderService;
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
@RequestMapping(path = { "/api/provider"}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class WmsProviderController extends MBaseController<WmsProviderDto> {

    @Autowired private WmsProviderService providerService;

    @PostMapping("search")
    public R<WmsProviderDto> search(@RequestBody PageQuery<WmsProviderDto> pageQuery) {
        return R.build(providerService.search(pageQuery.getDto(), pageQuery.getPageNo(), pageQuery.getPageSize()));
    }

    @PutMapping
    public void updateProvider(@RequestBody WmsProviderDto item) {
        providerService.updateProvider(item);
    }

    @PostMapping
    public void saveProvider(@RequestBody WmsProviderDto item) {
        providerService.saveProvider(item);
    }

    @GetMapping("/{id}")
    public WmsProviderDto getProvider(@PathVariable("id") String id) {
        return providerService.getIt(id);
    }

    @DeleteMapping
    public void deleteProviders(@RequestBody List<String> ids) {
        providerService.deleteProviders(Sets.newHashSet(ids));
    }

    @GetMapping("listByKeyWord")
    public List<WmsProviderDto> listByKeyWord(@RequestParam String keyWord) {
        return providerService.listByKeyWord(keyWord);
    }


}
