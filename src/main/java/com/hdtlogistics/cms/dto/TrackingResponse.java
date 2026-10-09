package com.hdtlogistics.cms.dto;

import com.hdtlogistics.cms.entity.Shipment;
import com.hdtlogistics.cms.entity.ShipmentEvent;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class TrackingResponse {
    private Shipment shipment;
    private List<ShipmentEvent> timeline;
}
