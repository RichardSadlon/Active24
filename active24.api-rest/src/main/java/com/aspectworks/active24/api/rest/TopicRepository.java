package com.aspectworks.active24.api.rest;

import com.aspectworks.active24.api.rest.vo.TopicEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

@Transactional
public interface TopicRepository extends JpaRepository<TopicEntity, Long> {
    TopicEntity findByName(String name);

    TopicEntity findByTextContaining(String searchText);

}
