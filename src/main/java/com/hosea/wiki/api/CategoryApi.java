package com.hosea.wiki.api;

import com.hosea.wiki.dao.domain.Category;
import com.hosea.wiki.resp.BaseArrayResponse;
import com.hosea.wiki.resp.JsonResponse;
import com.hosea.wiki.service.CategoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryApi {
    private static final Logger logger = LoggerFactory.getLogger(CategoryApi.class);
    @Resource
    CategoryService categoryService;


    //    @GetMapping("/list")
//    public JsonResponse<PageResponse<Category>> getCategoryList(@Valid CategoryDto bookDto) {
//        PageResponse<Category> list = categoryService.getList(bookDto);
//        return new JsonResponse(list);
//    }
    @GetMapping("/all")
    public JsonResponse<BaseArrayResponse<Category>> getAll() {
        List<Category> list = categoryService.getAll();
        return new JsonResponse(new BaseArrayResponse(list));
    }

    @PostMapping("/save")
    public JsonResponse save(@RequestBody Category category) {
        logger.info(category.toString());
        categoryService.save(category);
        return JsonResponse.success();
    }

    @DeleteMapping("/category/{id}")
    public JsonResponse delete(@NotNull @PathVariable Long id) {
        categoryService.delete(id);
        return JsonResponse.success();
    }
}
