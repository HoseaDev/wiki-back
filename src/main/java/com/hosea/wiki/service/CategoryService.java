package com.hosea.wiki.service;

import com.hosea.wiki.dao.domain.Category;
import com.hosea.wiki.dao.domain.CategoryExample;
import com.hosea.wiki.dao.domain.Test;
import com.hosea.wiki.dao.mapper.CategoryMapper;
import com.hosea.wiki.dao.mapper.TestMapper;
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


    public void save(Category category) {
        if (category.getId() == null) {
            long id = snowFlake.nextId();
            category.setId(id);
            categoryMapper.insert(category);
        } else {
            updateBook(category);
        }

    }

    public void delete(Long id) {
        categoryMapper.deleteByPrimaryKey(id);
    }


    public void updateBook(Category book) {
        categoryMapper.updateByPrimaryKey(book);
    }


    public List<Category> getAll() {
        CategoryExample categoryExample = new CategoryExample();
        List<Category> categorys = categoryMapper.selectByExample(categoryExample);

        return categorys;
    }
}
