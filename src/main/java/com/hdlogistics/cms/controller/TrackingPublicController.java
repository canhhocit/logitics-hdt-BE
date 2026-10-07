package com.hdlogistics.cms.controller;

import com.hdlogistics.cms.dto.TrackingResponse;
import com.hdlogistics.cms.entity.Shipment;
import com.hdlogistics.cms.entity.ShipmentEvent;
import com.hdlogistics.cms.repository.ShipmentEventRepository;
import com.hdlogistics.cms.repository.ShipmentRepository;
import com.hdlogistics.common.dto.ApiResponse;
import com.hdlogistics.common.exception.BaseException;
import com.hdlogistics.common.exception.ErrorCode;
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
