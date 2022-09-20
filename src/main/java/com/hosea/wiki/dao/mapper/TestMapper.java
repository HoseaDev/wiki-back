package com.hosea.wiki.dao.mapper;

import com.hosea.wiki.dao.domain.Test;

import java.util.List;

public interface TestMapper {

    List<Test> selectById(Long id);
}
