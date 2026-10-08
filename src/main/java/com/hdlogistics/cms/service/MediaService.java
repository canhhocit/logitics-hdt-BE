package com.hdlogistics.cms.service;

import com.hdlogistics.cms.dto.MediaResponse;
import org.springframework.web.multipart.MultipartFile;
import java.util.UUID;

public interface MediaService {
    MediaResponse uploadFile(MultipartFile file);
    void deleteFile(UUID id);
}
