package com.aspectworks.active24.api.rest;

import com.aspectworks.active24.annotation.MaxRequestPerUserCount;
import com.aspectworks.active24.api.rest.vo.CommentVO;
import com.aspectworks.active24.api.rest.vo.TopicVO;
import com.aspectworks.active24.api.rest.vo.TopicEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.ehcache.jsr107.EhcacheCachingProvider;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;


import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.CreatedExpiryPolicy;
import javax.cache.expiry.Duration;
import javax.cache.spi.CachingProvider;
import java.net.URISyntaxException;
import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/topics")
@Api(value = "/topics")
public class TopicController {
    @Autowired
    TopicServiceImpl topicService;

    final static Logger logger = org.slf4j.LoggerFactory.getLogger(TopicController.class);

    @MaxRequestPerUserCount(5L)
    @ApiOperation(value = "add topic")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addTopic(@RequestBody TopicVO topicVO) {
        logger.info("addTopic called");
        TopicEntity topicEntity = new TopicEntity(topicVO);
        topicService.addTopic(topicEntity);
    }

    @ApiOperation(value = "search topic by name")
    @RequestMapping(method = RequestMethod.GET, value = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TopicVO searchTopicByName(@PathVariable("name") String name) {
        TopicEntity topicEntity = topicService.searchTopicByName(name);
        return new TopicVO(topicEntity);
    }

    @ApiOperation(value = "delete topic")
    @RequestMapping(method = RequestMethod.DELETE, value = "{name}")
    public void deleteTopic(@PathVariable("name") String tittle) {
        topicService.deleteTopic(tittle);
    }


    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value = "get all topics")
    public List<TopicVO> getAllTopics(@RequestParam(value = "sort", required = false) String sort,
                                      @RequestParam(value = "type", required = false) String type,
                                      @RequestParam(value = "order", required = false) String order) {
        List<TopicEntity> topicEntities = topicService.getAllTopics(sort, type, order);

        List<TopicVO> topics = topicEntities.stream().map(topicEntity -> new TopicVO(topicEntity)).
                collect(Collectors.toList());
        return topics;
    }

    @ApiOperation(value = "add comment")
    @RequestMapping(method = RequestMethod.POST, value = "/{name}/comment", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addComment(@RequestBody CommentVO comment, @PathVariable("name") String name) {
        topicService.addComment(comment, name);
    }

    @ApiOperation(value = "get all comments for topic")
    @RequestMapping(method = RequestMethod.GET, value = "/{name}/comment", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CommentVO> getAllCommentsForTopic(@PathVariable("name") String name) {
        System.out.println("z cache");
        return topicService.getAllComments(name);
    }

    //skuska clear cache
    @RequestMapping(method = RequestMethod.GET, value = "/clear")
    public void clearCache() throws URISyntaxException {


    }


}
