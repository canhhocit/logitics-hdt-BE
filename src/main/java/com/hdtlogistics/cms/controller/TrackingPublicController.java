package com.hdtlogistics.cms.controller;

import com.hdtlogistics.cms.dto.TrackingResponse;
import com.hdtlogistics.cms.entity.Shipment;
import com.hdtlogistics.cms.entity.ShipmentEvent;
import com.hdtlogistics.cms.repository.ShipmentEventRepository;
import com.hdtlogistics.cms.repository.ShipmentRepository;
import com.hdtlogistics.common.dto.ApiResponse;
import com.hdtlogistics.common.exception.BaseException;
import com.hdtlogistics.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/public/tracking")
@RequiredArgsConstructor
public class TrackingPublicController {

    private final ShipmentRepository shipmentRepository;
    private final ShipmentEventRepository shipmentEventRepository;

    @GetMapping("/{code}")
    public ResponseEntity<ApiResponse<TrackingResponse>> getTracking(@PathVariable String code) {
        Shipment shipment = shipmentRepository.findByTrackingCode(code)
                .orElseThrow(() -> new BaseException("Không tìm thấy vận đơn", ErrorCode.RESOURCE_NOT_FOUND, 404));
        
        List<ShipmentEvent> events = shipmentEventRepository.findByShipmentIdOrderByOrderNoDesc(shipment.getId());
        
        TrackingResponse response = TrackingResponse.builder()
                .shipment(shipment)
                .timeline(events)
                .build();
                
        return ResponseEntity.ok(ApiResponse.success(response));
    }
}
