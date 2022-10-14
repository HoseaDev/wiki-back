package com.hosea.wiki.service;

import com.hosea.wiki.dao.domain.Category;
import com.hosea.wiki.dao.domain.CategoryExample;
import com.hosea.wiki.dao.domain.Test;
import com.hosea.wiki.dao.mapper.CategoryMapper;
import com.hosea.wiki.dao.mapper.TestMapper;
import com.hosea.wiki.request.CategoryReq;
import com.hosea.wiki.resp.CategoryResp;
import com.hosea.wiki.utils.CopyUtil;
import com.hosea.wiki.utils.SnowFlake;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CategoryService {
    private static final Logger logger = LoggerFactory.getLogger(CategoryService.class);
    @Resource
    CategoryMapper categoryMapper;
    @Resource
    TestMapper testMapper;

    @Resource
    SnowFlake snowFlake;


//    public PageResponse<Category> getList(CategoryDto bookDto) {
//
//        CategoryExample categoryExample = new CategoryExample();
//        CategoryExample.Criteria criteria = categoryExample.createCriteria();
//        if (!StringUtils.isNullOrEmpty(bookDto.getName())) {
//            criteria.andNameLike("%" + bookDto.getName() + "%");
//        }
//        PageHelper.startPage(bookDto.getCurrentPage(), bookDto.getSizeOfPage());
//        List<Category> categorys = categoryMapper.selectByExample(categoryExample);
//        PageInfo<Category> book = new PageInfo<>(categorys);
//        logger.info("总条数:{}", book.getTotal());
//        logger.info("总页数:{}", book.getPages());
//        PageResponse<Category> result = new PageResponse<Category>(book.getTotal(), book.getList());
//        return result;
//    }


    public List<Test> getTest() {
        return testMapper.selectById(11L);
    }


    public void save(CategoryReq req) {
        Category category = CopyUtil.copy(req, Category.class);
        if (category.getId() == null) {
            long id = snowFlake.nextId();
            category.setId(id);
            categoryMapper.insert(category);
        } else {
            updateBook(category);
        }

    }

    public void delete(Long id) {
        CategoryExample example = new CategoryExample();
        example.createCriteria().andParentEqualTo(id);
        Category category = new Category();
        category.setParent(0L);
//        categoryMapper.deleteByPrimaryKey(id);
        categoryMapper.updateByExampleSelective(category,example);
    }


    public void updateBook(Category book) {
        categoryMapper.updateByPrimaryKey(book);
    }


    public List<CategoryResp> getAll() {
        CategoryExample categoryExample = new CategoryExample();
        List<Category> categorys = categoryMapper.selectByExample(categoryExample);
        logger.info(categorys.toString());
        List<CategoryResp> list = CopyUtil.copyList(categorys, CategoryResp.class);
        logger.info(list.toString());
        return list;
    }
}
