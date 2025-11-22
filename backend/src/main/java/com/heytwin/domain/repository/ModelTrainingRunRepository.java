package com.heytwin.domain.repository;

import com.heytwin.domain.entity.ModelTrainingRun;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelTrainingRunRepository extends JpaRepository<ModelTrainingRun, UUID> {
    Optional<ModelTrainingRun> findTop1ByOrderByCreatedAtDesc();
}
