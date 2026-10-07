package com.hdlogistics.cms.dto;

import com.hdlogistics.cms.entity.Shipment;
import com.hdlogistics.cms.entity.ShipmentEvent;
import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class TrackingResponse {
    private Shipment shipment;
    private List<ShipmentEvent> timeline;
}
