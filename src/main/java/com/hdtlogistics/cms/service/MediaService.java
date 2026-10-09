package com.hdtlogistics.cms.service;

import com.hdtlogistics.cms.dto.MediaResponse;
import org.springframework.web.multipart.MultipartFile;
import java.util.UUID;

public interface MediaService {
    MediaResponse uploadFile(MultipartFile file);
    void deleteFile(UUID id);
}
