package com.heytwin.domain.repository;

import com.heytwin.domain.entity.Question;
import com.heytwin.domain.model.enums.DifficultyLevel;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface QuestionRepository extends JpaRepository<Question, UUID> {

    List<Question> findTop50ByDifficulty(DifficultyLevel difficulty);

    @Query("SELECT q FROM Question q WHERE q.topic.id IN :topicIds AND q.active = true")
    List<Question> findActiveByTopicIds(@Param("topicIds") List<UUID> topicIds);
}
