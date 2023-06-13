package com.baomibing.snam.business.controller;

import com.baomibing.snam.business.dto.WmsClassDto;
import com.baomibing.snam.business.service.WmsClassService;
import com.baomibing.tool.common.PageQuery;
import com.baomibing.web.base.MBaseController;
import com.baomibing.web.common.R;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * WmsClassController
 *
 * @author frog 2023/6/4 22:04
 * @version 1.0.0
 **/
@RestController
@RequestMapping(path = { "/api/class"}, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class WmsClassController extends MBaseController<WmsClassDto> {

    @Autowired private WmsClassService classService;

    @PostMapping("search")
    public R<WmsClassDto> search(@RequestBody PageQuery<WmsClassDto> pageQuery) {
        return R.build(classService.search(pageQuery.getDto(), pageQuery.getPageNo(), pageQuery.getPageSize()));
    }

    @PutMapping
    public void updateClass(@RequestBody WmsClassDto v) {
        classService.updateClass(v);
    }

    @PostMapping
    public void saveClass(@RequestBody WmsClassDto v) {
        classService.saveClass(v);
    }


    @GetMapping("/{id}")
    public WmsClassDto getClazz(@PathVariable("id") String id) {
        return classService.getIt(id);
    }

    @DeleteMapping
    public void deleteClass(@RequestBody List<String> ids) {
        classService.deleteClasses(Sets.newHashSet(ids));
    }

    @GetMapping("listByKeyWord")
    public List<WmsClassDto> listByKeyWord(@RequestParam String keyWord) {
        return classService.listByKeyWord(keyWord);
    }

}
