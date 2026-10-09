package com.hdtlogistics.cms.repository;

import com.hdtlogistics.cms.entity.MenuItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.UUID;

public interface MenuItemRepository extends JpaRepository<MenuItem, UUID> {
    List<MenuItem> findByPositionOrderByOrderNoAsc(String position);
    List<MenuItem> findByParentIdOrderByOrderNoAsc(UUID parentId);
}
