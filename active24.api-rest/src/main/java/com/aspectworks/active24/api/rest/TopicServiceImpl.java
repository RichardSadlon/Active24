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

    public void addTopic(TopicEntity topic) {
        topic.setDate(new Date());
        if (tr.findByName(topic.getName()) == null) {
            tr.save(topic);
        }
    }

    public TopicEntity searchTopicByName(String name) {
        return tr.findByName(name);
    }

    public void deleteTopic(String name) {
        tr.delete(tr.findByName(name));
    }

    public List<TopicEntity> getAllTopics(String sort, String type, String order) {
        List<TopicEntity> list = new ArrayList<>();
        if (sort.equals("yes")) {
            if (type.equals("date")) {
                if(order.equals("asc"))
                    tr.findAll().sort(Comparator.comparing(TopicEntity::getDate));
                else
                    tr.findAll().sort(Comparator.comparing(TopicEntity::getDate).reversed());
            } else if (type.equals("alphabetically")) {
                if (order.equals("asc")) {
                    tr.findAll().sort(Comparator.comparing(TopicEntity::getName));
                } else { //desc
                    tr.findAll().sort(Comparator.comparing(TopicEntity::getName).reversed());
                }
            }
        }
        return tr.findAll();
    }


    public void addComment(CommentVO comment, String name) {
        TopicEntity topicEntity = tr.findByName(name);
        topicEntity.addComment(comment);
        tr.save(topicEntity);

    }

    public List<CommentVO> getAllComments(String name) {
        return tr.findByName(name).getCommentVOS();
    }


}