package com.heytwin.domain.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.heytwin.domain.entity.Topic;

public interface TopicRepository extends JpaRepository<Topic, UUID> {
    List<Topic> findBySubject(String subject);

    Optional<Topic> findBySubjectIgnoreCaseAndNameIgnoreCase(String subject, String name);
}
