package com.hdtlogistics.cms.service;

import com.hdtlogistics.cms.dto.request.TestimonialRequest;
import com.hdtlogistics.cms.entity.Testimonial;
import com.hdtlogistics.cms.repository.TestimonialRepository;
import com.hdtlogistics.common.exception.BaseException;
import com.hdtlogistics.common.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TestimonialAdminService {

    private final TestimonialRepository testimonialRepository;

    @Transactional(readOnly = true)
    public List<Testimonial> getAll() {
        return testimonialRepository.findAllByOrderByOrderNoAsc();
    }

    @Transactional
    public Testimonial create(TestimonialRequest request) {
        Testimonial t = Testimonial.builder()
                .customerName(request.getCustomerName())
                .customerType(request.getCustomerType())
                .logo(request.getLogo())
                .content(request.getContent())
                .orderNo(request.getOrderNo() != null ? request.getOrderNo() : 0)
                .build();
        return testimonialRepository.save(t);
    }

    @Transactional
    public Testimonial update(UUID id, TestimonialRequest request) {
        Testimonial t = testimonialRepository.findById(id)
                .orElseThrow(() -> new BaseException("Đánh giá không tồn tại", ErrorCode.RESOURCE_NOT_FOUND, 404));
        t.setCustomerName(request.getCustomerName());
        t.setCustomerType(request.getCustomerType());
        t.setLogo(request.getLogo());
        t.setContent(request.getContent());
        if (request.getOrderNo() != null) t.setOrderNo(request.getOrderNo());
        return testimonialRepository.save(t);
    }

    @Transactional
    public void delete(UUID id) {
        if (!testimonialRepository.existsById(id))
            throw new BaseException("Đánh giá không tồn tại", ErrorCode.RESOURCE_NOT_FOUND, 404);
        testimonialRepository.deleteById(id);
    }
}
