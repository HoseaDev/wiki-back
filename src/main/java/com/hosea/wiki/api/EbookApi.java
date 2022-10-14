package com.hosea.wiki.api;


import com.hosea.wiki.request.EbookReq;
import com.hosea.wiki.resp.EbookResp;
import com.hosea.wiki.resp.JsonResponse;
import com.hosea.wiki.resp.PageResponse;
import com.hosea.wiki.service.EbookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@RestController
@RequestMapping("/ebook")
public class EbookApi {
    private static final Logger logger = LoggerFactory.getLogger(EbookApi.class);
    @Resource
    EbookService ebookService;


    @GetMapping("/list")
    public JsonResponse<PageResponse<EbookResp>> getEbookList(@Valid EbookReq bookDto) {
        PageResponse<EbookResp> list = ebookService.getList(bookDto);
        return new JsonResponse(list);
    }

    @PostMapping("/save")
    public JsonResponse save(@RequestBody EbookReq ebook) {
        logger.info(ebook.toString());
        ebookService.save(ebook);
        return JsonResponse.success();
    }

    @DeleteMapping("/book/{id}")
    public JsonResponse delete(@NotNull @PathVariable Long id) {
        ebookService.delete(id);
        return JsonResponse.success();
    }


}
