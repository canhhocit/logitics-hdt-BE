package com.hdtlogistics.auth.repository;

import com.hdtlogistics.auth.entity.LoginLog;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface LoginLogRepository extends JpaRepository<LoginLog, UUID> {
}
