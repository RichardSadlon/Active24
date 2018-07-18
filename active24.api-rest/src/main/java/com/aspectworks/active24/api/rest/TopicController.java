package com.aspectworks.active24.api.rest;

import com.aspectworks.active24.api.rest.vo.Comment;
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
        TopicEntity topicEntity = new TopicEntity(topicVO.getId(), topicVO.getTittle());
        topicService.addTopic(topicEntity);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{text}")
    public TopicVO searchTopicByText(@PathVariable("text") String text) {
        System.out.println("Searching for topic which tittle contains " + text);
        TopicEntity topicEntity = topicService.searchTopicByText(text);
        return new TopicVO(topicEntity.getId(), topicEntity.getTittle(), topicEntity.getDate());
    }


    @RequestMapping(method = RequestMethod.GET)
    public List<TopicVO> getAllTopics(@RequestParam(value = "sort", required = false) String sort,
                                      @RequestParam(value = "type", required = false) String type,
                                      @RequestParam(value = "order", required = false) String order) {
        List<TopicEntity> topicEntities = topicService.getAllTopics(sort, type, order);
        List<TopicVO> topics = new ArrayList<>();
        for (TopicEntity topicEntity :
                topicEntities) {
            topics.add(new TopicVO(topicEntity.getId(), topicEntity.getTittle(), topicEntity.getDate()));
        }
        return topics;
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{id}/comment", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addComment(@RequestBody Comment comment, @PathVariable("id") Long id) {
        topicService.addComment(comment, id);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/{id}/comment", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Comment> getAllCommentsForTopic(@PathVariable("id") Long id) {
        return topicService.getAllComments(id);
    }


    @RequestMapping(method=RequestMethod.DELETE, value="{id}")
    public void deleteTopic(@PathVariable("id") Long id){
        topicService.deleteTopic(id);
    }

}
