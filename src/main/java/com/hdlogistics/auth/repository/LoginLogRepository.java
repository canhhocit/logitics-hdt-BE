package com.hdlogistics.auth.repository;

import com.hdlogistics.auth.entity.LoginLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface LoginLogRepository extends JpaRepository<LoginLog, UUID> {
}
