package com.heytwin.domain.repository;

import com.heytwin.domain.entity.Achievement;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AchievementRepository extends JpaRepository<Achievement, UUID> {
    Optional<Achievement> findByCode(String code);
}
