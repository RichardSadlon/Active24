package com.aspectworks.active24.api.rest;

import com.aspectworks.active24.api.rest.vo.CommentVO;
import com.aspectworks.active24.api.rest.vo.TopicEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TopicServiceImpl implements TopicService {
    @Autowired
    private TopicRepository tr;

    @Override
    public void addTopic(TopicEntity topic) {
        topic.setDate(new Date());
        if (tr.findByName(topic.getName()) == null) {
            tr.save(topic);
        }
    }

    @Override
    public TopicEntity searchTopicByName(String name) {
        return tr.findByName(name);
    }

    @Override
    public void deleteTopic(String name) {
        tr.delete(tr.findByName(name));
    }

    @Override
    public List<TopicEntity> getAllTopics(String sort, String type, String order) {
        List<TopicEntity> topics=tr.findAll();
        if (sort.equals("yes")) {
            if (type.equals("date")) {
                if (order.equals("desc")) {
                    topics.sort(Comparator.comparing(TopicEntity::getDate).reversed());
                } else { //by default asc
                    topics.sort(Comparator.comparing(TopicEntity::getDate));
                }
            } else { //by default alphabetically
                if (order.equals("desc")) {
                    topics.sort(Comparator.comparing(TopicEntity::getName).reversed());
                } else { //by default asc
                    topics.sort(Comparator.comparing(TopicEntity::getName));
                }
            }
        }
        return topics;
    }


    @Override
    public void addComment(CommentVO comment, String name) {
        TopicEntity topicEntity = tr.findByName(name);
        topicEntity.addComment(comment);
        tr.save(topicEntity);
    }

    @Override
    public List<CommentVO> getAllComments(String name) {
        return tr.findByName(name).getCommentVOS();
    }


}