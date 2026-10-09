package com.hdtlogistics.cms.repository;

import com.hdtlogistics.cms.entity.ShipmentEvent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ShipmentEventRepository extends JpaRepository<ShipmentEvent, UUID> {
    List<ShipmentEvent> findByShipmentIdOrderByOrderNoDesc(UUID shipmentId);
}
