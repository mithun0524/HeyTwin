package com.heytwin.domain.repository;

import com.heytwin.domain.entity.StudentTopicProfile;
import com.heytwin.domain.entity.Topic;
import com.heytwin.domain.entity.User;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentTopicProfileRepository extends JpaRepository<StudentTopicProfile, UUID> {
    List<StudentTopicProfile> findByStudent(User student);
    Optional<StudentTopicProfile> findByStudentAndTopic(User student, Topic topic);
}
