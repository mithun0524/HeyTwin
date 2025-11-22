package com.heytwin.domain.repository;

import com.heytwin.domain.entity.Achievement;
import com.heytwin.domain.entity.StudentAchievement;
import com.heytwin.domain.entity.User;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentAchievementRepository extends JpaRepository<StudentAchievement, UUID> {
    List<StudentAchievement> findByStudent(User student);
    Optional<StudentAchievement> findByStudentAndAchievement(User student, Achievement achievement);
}
