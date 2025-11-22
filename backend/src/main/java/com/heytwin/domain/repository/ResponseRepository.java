package com.heytwin.domain.repository;

import com.heytwin.domain.entity.PracticeSession;
import com.heytwin.domain.entity.Response;
import com.heytwin.domain.entity.User;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResponseRepository extends JpaRepository<Response, UUID> {
    List<Response> findByStudent(User student);
    List<Response> findBySession(PracticeSession session);
}
