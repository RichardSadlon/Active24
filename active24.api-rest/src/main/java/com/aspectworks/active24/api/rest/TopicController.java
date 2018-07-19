package com.aspectworks.active24.api.rest;

import com.aspectworks.active24.api.rest.vo.CommentVO;
import com.aspectworks.active24.api.rest.vo.TopicVO;
import com.aspectworks.active24.api.rest.vo.TopicEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/topics")
public class TopicController {
    @Autowired
    TopicServiceImpl topicService;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addTopic(@RequestBody TopicVO topicVO) {
        TopicEntity topicEntity = new TopicEntity(topicVO);
        topicService.addTopic(topicEntity);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{name}", produces = MediaType.APPLICATION_JSON_VALUE)
    public TopicVO searchTopicByName(@PathVariable("name") String name) {
        System.out.println("Searching for topic which name contains " + name);
        TopicEntity topicEntity = topicService.searchTopicByName(name);
        return new TopicVO(topicEntity);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "{name}")
    public void deleteTopic(@PathVariable("name") String tittle) {
        topicService.deleteTopic(tittle);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<TopicVO> getAllTopics(@RequestParam(value = "sort", required = false) String sort,
                                      @RequestParam(value = "type", required = false) String type,
                                      @RequestParam(value = "order", required = false) String order) {
        List<TopicEntity> topicEntities = topicService.getAllTopics(sort, type, order);
        List<TopicVO> topics = new ArrayList<>();
        for (TopicEntity topicEntity :
                topicEntities) {
            topics.add(new TopicVO(topicEntity));
        }
        return topics;
    }



        @RequestMapping(method = RequestMethod.POST, value = "/{name}/comment", consumes = MediaType.APPLICATION_JSON_VALUE)
        public void addComment(@RequestBody CommentVO comment, @PathVariable("name") String name) {
            topicService.addComment(comment, name);
        }

        @RequestMapping(method = RequestMethod.GET, value = "/{name}/comment", produces = MediaType.APPLICATION_JSON_VALUE)
        public List<CommentVO> getAllCommentsForTopic(@PathVariable("name") String name) {
            return topicService.getAllComments(name);
        }





}
