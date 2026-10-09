package com.hdtlogistics.cms.service.impl;

import com.hdtlogistics.cms.dto.MediaResponse;
import com.hdtlogistics.cms.entity.Media;
import com.hdtlogistics.cms.repository.MediaRepository;
import com.hdtlogistics.cms.service.MediaService;
import com.hdtlogistics.common.exception.BaseException;
import com.hdtlogistics.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class MediaServiceImpl implements MediaService {

    private final MediaRepository mediaRepository;


    @Override
    @Transactional
    public MediaResponse uploadFile(MultipartFile file) {
        try {
            String mimeType = file.getContentType();
            boolean isImage = mimeType != null && mimeType.startsWith("image/");
            String subDir = isImage ? "images/" : "files/";
            String currentUploadDir = "uploads/" + subDir;

            File dir = new File(currentUploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            String originalFilename = file.getOriginalFilename();
            String extension = "";
            if (originalFilename != null && originalFilename.contains(".")) {
                extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            }

            String newFilename = UUID.randomUUID().toString() + extension;
            Path filePath = Paths.get(currentUploadDir, newFilename);
            
            Files.write(filePath, file.getBytes());

            String fileUrl = "/uploads/" + subDir + newFilename;

            Media media = Media.builder()
                    .fileName(originalFilename)
                    .filePath(fileUrl)
                    .mimeType(mimeType)
                    .sizeBytes(file.getSize())
                    .build();
            
            mediaRepository.save(media);

            return MediaResponse.builder()
                    .id(media.getId())
                    .fileName(media.getFileName())
                    .url(media.getFilePath())
                    .mimeType(media.getMimeType())
                    .sizeBytes(media.getSizeBytes())
                    .build();

        } catch (IOException e) {
            log.error("Failed to upload file", e);
            throw new BaseException("Không thể tải lên file", ErrorCode.INTERNAL_SERVER_ERROR, 500);
        }
    }

    @Override
    @Transactional
    public void deleteFile(UUID id) {
        Media media = mediaRepository.findById(id)
                .orElseThrow(() -> new BaseException("Không tìm thấy file", ErrorCode.RESOURCE_NOT_FOUND, 404));
        
        try {
            String relativePath = media.getFilePath();
            if (relativePath != null && relativePath.startsWith("/uploads/")) {
                String subPath = relativePath.substring("/uploads/".length());
                Path filePath = Paths.get("uploads/", subPath);
                Files.deleteIfExists(filePath);
            }
            mediaRepository.delete(media);
        } catch (IOException e) {
            log.error("Failed to delete file", e);
            throw new BaseException("Không thể xóa file", ErrorCode.INTERNAL_SERVER_ERROR, 500);
        }
    }
}
