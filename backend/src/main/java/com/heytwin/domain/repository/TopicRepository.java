package com.heytwin.domain.repository;

import com.heytwin.domain.entity.Topic;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TopicRepository extends JpaRepository<Topic, UUID> {
    List<Topic> findBySubject(String subject);
}
