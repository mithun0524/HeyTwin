package com.heytwin.domain.repository;

import com.heytwin.domain.entity.DigitalTwinHistory;
import com.heytwin.domain.entity.User;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DigitalTwinHistoryRepository extends JpaRepository<DigitalTwinHistory, UUID> {
    List<DigitalTwinHistory> findTop50ByStudentOrderByCapturedAtDesc(User student);
}
