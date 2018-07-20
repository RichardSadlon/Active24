package com.aspectworks.active24.api.rest;

import com.aspectworks.active24.api.rest.vo.CommentVO;
import com.aspectworks.active24.api.rest.vo.TopicVO;
import com.aspectworks.active24.api.rest.vo.TopicEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.hibernate.annotations.common.util.impl.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collector;
import java.util.stream.Collectors;



@RestController
@RequestMapping("/topics")
@Api(value="/topics")
public class TopicController {
    @Autowired
    TopicServiceImpl topicService;

    @ApiOperation(value="add topic")
    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addTopic(@RequestBody TopicVO topicVO) {
        TopicEntity topicEntity = new TopicEntity(topicVO);
        topicService.addTopic(topicEntity);
    }

    @ApiOperation(value="search topic by name")
    @RequestMapping(method = RequestMethod.GET, value = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TopicVO searchTopicByName(@PathVariable("name") String name) {
        TopicEntity topicEntity = topicService.searchTopicByName(name);
        return new TopicVO(topicEntity);
    }

    @ApiOperation(value="delete topic")
    @RequestMapping(method = RequestMethod.DELETE, value = "{name}")
    public void deleteTopic(@PathVariable("name") String tittle) {
        topicService.deleteTopic(tittle);
    }


    @RequestMapping(method = RequestMethod.GET)
    @ApiOperation(value="get all topics")
    public List<TopicVO> getAllTopics(@RequestParam(value = "sort", required = false) String sort,
                                      @RequestParam(value = "type", required = false) String type,
                                      @RequestParam(value = "order", required = false) String order) {
        List<TopicEntity> topicEntities = topicService.getAllTopics(sort, type, order);

        List<TopicVO> topics = topicEntities.stream().map(topicEntity -> new TopicVO(topicEntity)).
                collect(Collectors.toList());
        return topics;
    }

    @ApiOperation(value="add comment")
    @RequestMapping(method = RequestMethod.POST, value = "/{name}/comment", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addComment(@RequestBody CommentVO comment, @PathVariable("name") String name) {
        topicService.addComment(comment, name);
    }

    @ApiOperation(value="get all comments for topic")
    @RequestMapping(method = RequestMethod.GET, value = "/{name}/comment", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<CommentVO> getAllCommentsForTopic(@PathVariable("name") String name) {
        return topicService.getAllComments(name);
    }

}
