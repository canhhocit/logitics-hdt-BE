package com.hdtlogistics.cms.dto;

import lombok.Builder;
import lombok.Data;
import java.util.UUID;

@Data
@Builder
public class MediaResponse {
    private UUID id;
    private String fileName;
    private String url;
    private String mimeType;
    private Long sizeBytes;
}
