package com.zjc.hustoj.news.controller;

import com.zjc.hustoj.core.constant.ServerResponse;
import com.zjc.hustoj.core.controller.BaseController;
import com.zjc.hustoj.core.utils.PageUtils;
import com.zjc.hustoj.news.service.NewsService;
import com.zjc.hustoj.news.vo.NewsDetailVo;
import com.zjc.hustoj.news.vo.NewsPageReqVo;
import com.zjc.hustoj.news.vo.NewsPageRespVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;

/**
 * @author David Hsiang
 * @date 2021/04/07/3:21 下午
 */
@RestController
@RequestMapping("/news")
@Slf4j
public class NewsController extends BaseController {

    @Resource
    private NewsService newsService;

    @PostMapping("/page")
    public ResponseEntity page(@RequestBody @Valid NewsPageReqVo newsPageReqVo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ServerResponse.errorMsg(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        try {
            PageUtils page = newsService.queryPage(newsPageReqVo);
            return ServerResponse.ok(page);
        } catch (Exception e) {
            log.error("查询失败", e);
            return ServerResponse.errorMsg(e.getMessage());
        }
    }

    @PostMapping("/updateUsable/{id}")
    public ResponseEntity updateUsable(@PathVariable("id") Integer newsId){
        try {
            NewsPageRespVo newsPageRespVo = newsService.updateUsable(newsId);
            return ServerResponse.ok("更新成功", newsPageRespVo);
        } catch (Exception e) {
            log.error("更新失败", e);
            return ServerResponse.errorMsg(e.getMessage());
        }
    }

    @GetMapping("/detail/{id}")
    public ResponseEntity detail(@PathVariable("id") Integer newsId){
        try {
            NewsDetailVo detailVo = newsService.detail(newsId);
            return ServerResponse.ok("查询成功", detailVo);
        } catch (Exception e) {
            log.error("查询失败", e);
            return ServerResponse.errorMsg(e.getMessage());
        }
    }

    @PostMapping("/saveOrUpdate")
    public ResponseEntity saveOrUpdate(@RequestBody @Valid NewsDetailVo detailVo, BindingResult bindingResult){
        if (bindingResult.hasErrors()) {
            return ServerResponse.errorMsg(bindingResult.getAllErrors().get(0).getDefaultMessage());
        }
        try {
            Integer newsId = newsService.saveOrUpdate(detailVo, getCurrentUserId());
            return ServerResponse.ok("添加/更新成功", newsId);
        } catch (Exception e) {
            log.error("添加/更新失败", e);
            return ServerResponse.errorMsg(e.getMessage());
        }
    }


    /**
     * todo 得到公告
     */
    @GetMapping("/announcement")
    public ResponseEntity getAnnouncement(){
        return ServerResponse.ok("欢迎来到浙工大之江学院软件工程专业的专用编程工作室，希望大家能够奋力拼搏，挑战自我！<img src=\"http://10.248.6.72:8080/kindeditor/plugins/emoticons/images/110.gif\" border=\"0\" alt=\"\" /><img src=\"http://10.248.6.72:8080/kindeditor/plugins/emoticons/images/110.gif\" border=\"0\" alt=\"\" /><img src=\"http://10.248.6.72:8080/kindeditor/plugins/emoticons/images/110.gif\" border=\"0\" alt=\"\" /> <br />\n" +
                "吃得苦中苦，方为人上人");
    }

    /**
     * todo 设置公告
     */
    @PostMapping("/announcement")
    public ResponseEntity setAnnouncement(@RequestBody String announcement){
        return ServerResponse.okMsg("设置成功");
    }
}
