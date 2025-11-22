package com.heytwin.domain.repository;

import com.heytwin.domain.entity.PracticeSession;
import com.heytwin.domain.entity.User;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PracticeSessionRepository extends JpaRepository<PracticeSession, UUID> {
    List<PracticeSession> findByStudentOrderByStartedAtDesc(User student);
    Optional<PracticeSession> findTop1ByStudentOrderByStartedAtDesc(User student);
}
