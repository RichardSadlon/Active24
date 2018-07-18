package com.aspectworks.active24.api.rest;

import com.aspectworks.active24.api.rest.vo.Comment;
import com.aspectworks.active24.api.rest.vo.TopicEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TopicServiceImpl implements TopicService {
    List<TopicEntity> topics = new ArrayList<>();

    public void addTopic(TopicEntity topic) {
        topic.setDate(new Date());
        topics.add(topic);
    }

    public TopicEntity searchTopicByText(String text) {
        for (TopicEntity topic : topics) {
            if (topic.getTittle().contains(text)) {
                return topic;
            }
        }
        return null;
    }

    public List<TopicEntity> getAllTopics(String sort, String type, String order) {
        if (sort.equals("yes")) {
            if (type.equals("date")) {
                topics.sort(Comparator.comparing(o -> o.getDate()));
            } else if (type.equals("alphabetically")) {
                if (order.equals("asc")) {
                    topics.sort(Comparator.comparing(TopicEntity::getTittle));
                } else { //desc
                    topics.sort(Comparator.comparing(TopicEntity::getTittle).reversed());
                }
            }
        }
        return topics;
    }

    public void addComment(Comment comment, Long id) {
        for (TopicEntity topic :
                topics) {
            if (topic.getId().equals(id)) {
                topic.addComment(comment);
                System.out.println("comment was added");
                return;
            }
        }
    }

    public List<Comment> getAllComments(Long id) {
        for (TopicEntity topic :
                topics) {
            if (topic.getId().equals(id)) {
                return topic.getComments();
            }
        }
        return null;
    }

    public void deleteTopic(Long id) {
        for (int i = 0; i < topics.size(); i++) {
            if (topics.get(i).equals(id)) {
                topics.remove(i);
            }
        }
    }
}