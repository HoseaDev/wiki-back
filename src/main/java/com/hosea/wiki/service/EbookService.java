package com.hosea.wiki.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hosea.wiki.dao.domain.Ebook;
import com.hosea.wiki.dao.domain.EbookExample;
import com.hosea.wiki.dao.domain.Test;
import com.hosea.wiki.dao.mapper.EbookMapper;
import com.hosea.wiki.dao.mapper.TestMapper;
import com.hosea.wiki.request.EbookReq;
import com.hosea.wiki.resp.EbookResp;
import com.hosea.wiki.resp.PageResponse;
import com.hosea.wiki.utils.CopyUtil;
import com.hosea.wiki.utils.SnowFlake;
import com.mysql.cj.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class EbookService {
    private static final Logger logger = LoggerFactory.getLogger(EbookService.class);
    @Resource
    EbookMapper ebookMapper;
    @Resource
    TestMapper testMapper;

    @Resource
    SnowFlake snowFlake;


    public PageResponse<EbookResp> getList(EbookReq bookDto) {

        EbookExample ebookExample = new EbookExample();
        EbookExample.Criteria criteria = ebookExample.createCriteria();
        if (!StringUtils.isNullOrEmpty(bookDto.getName())) {
            criteria.andNameLike("%" + bookDto.getName() + "%");
        }
        if (bookDto.getCategory2Id()!=null ) {
            criteria.andCategory2IdEqualTo(bookDto.getCategory2Id());
        }
        PageHelper.startPage(bookDto.getCurrentPage(), bookDto.getSizeOfPage());
        List<Ebook> ebooks = ebookMapper.selectByExample(ebookExample);
        PageInfo<Ebook> book = new PageInfo<>(ebooks);
        logger.info("总条数:{}", book.getTotal());
        logger.info("总页数:{}", book.getPages());
        // 列表复制
        List<EbookResp> list = CopyUtil.copyList(ebooks, EbookResp.class);
        PageResponse<EbookResp> result = new PageResponse<EbookResp>(book.getTotal(), list);
        return result;
    }


    public List<Test> getTest() {
        return testMapper.selectById(11L);
    }


    public void save(EbookReq req) {
        Ebook ebook = CopyUtil.copy(req, Ebook.class);
        if (ebook.getId() == null) {
            long id = snowFlake.nextId();
            ebook.setId(id);
            ebook.setDocCount(0);
            ebook.setViewCount(0);
            ebook.setVoteCount(0);
            ebookMapper.insert(ebook);
        } else {
            updateBook(ebook);
        }
    }

    public void delete(Long id) {
        ebookMapper.deleteByPrimaryKey(id);

    }


    public void updateBook(Ebook book) {
        ebookMapper.updateByPrimaryKey(book);
    }
}
