package com.aspectworks.active24.api.rest;

import com.aspectworks.active24.api.rest.vo.CommentVO;
import com.aspectworks.active24.api.rest.vo.TopicEntity;

import java.util.List;
import java.util.Optional;

public interface TopicService {
    public void addTopic(TopicEntity topic);

    public TopicEntity searchTopicByName(String name);

    public void deleteTopic(String name);

    public List<TopicEntity> getAllTopics(String sort, String type, String order);

    public void addComment(CommentVO comment, String name);

    public List<CommentVO> getAllComments(String name);
}
