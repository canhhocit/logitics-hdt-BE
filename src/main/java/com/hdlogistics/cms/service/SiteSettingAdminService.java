package com.hdlogistics.cms.service;

import com.hdlogistics.cms.entity.SiteSetting;
import com.hdlogistics.cms.repository.SiteSettingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class SiteSettingAdminService {

    private final SiteSettingRepository siteSettingRepository;

    @Transactional(readOnly = true)
    public List<SiteSetting> getAll() {
        return siteSettingRepository.findAll();
    }

    /**
     * Upsert nhiều key cùng lúc.
     * Nếu key đã tồn tại → update value; nếu chưa → tạo mới.
     * Đồng thời evict cache siteSettings để các endpoint public nhận data mới.
     */
    @Transactional
    @CacheEvict(value = "siteSettings", allEntries = true)
    public List<SiteSetting> upsert(Map<String, String> updates) {
        updates.forEach((key, value) -> {
            SiteSetting setting = siteSettingRepository.findById(key)
                    .orElse(SiteSetting.builder().key(key).build());
            setting.setValue(value);
            siteSettingRepository.save(setting);
        });
        return siteSettingRepository.findAll();
    }
}
