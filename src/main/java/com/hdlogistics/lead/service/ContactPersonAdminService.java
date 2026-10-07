package com.hdlogistics.lead.service;

import com.hdlogistics.common.exception.BaseException;
import com.hdlogistics.common.exception.ErrorCode;
import com.hdlogistics.lead.dto.ContactPersonRequest;
import com.hdlogistics.lead.entity.ContactPerson;
import com.hdlogistics.lead.entity.Office;
import com.hdlogistics.lead.repository.ContactPersonRepository;
import com.hdlogistics.lead.repository.OfficeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContactPersonAdminService {

    private final ContactPersonRepository contactPersonRepository;
    private final OfficeRepository officeRepository;

    @Transactional(readOnly = true)
    public Page<ContactPerson> getAll(Pageable pageable) {
        return contactPersonRepository.findAll(pageable);
    }

    @Transactional
    public ContactPerson create(ContactPersonRequest request) {
        Office office = null;
        if (request.getOfficeId() != null) {
            office = officeRepository.findById(request.getOfficeId())
                    .orElseThrow(() -> new BaseException("Chi nhánh không tồn tại", ErrorCode.RESOURCE_NOT_FOUND, 404));
        }

        ContactPerson person = ContactPerson.builder()
                .fullName(request.getFullName())
                .title(request.getTitle())
                .email(request.getEmail())
                .phone(request.getPhone())
                .initials(request.getInitials())
                .region(request.getRegion())
                .office(office)
                .build();
        return contactPersonRepository.save(person);
    }

    @Transactional
    public ContactPerson update(UUID id, ContactPersonRequest request) {
        ContactPerson person = contactPersonRepository.findById(id)
                .orElseThrow(() -> new BaseException("Người phụ trách không tồn tại", ErrorCode.RESOURCE_NOT_FOUND, 404));

        Office office = null;
        if (request.getOfficeId() != null) {
            office = officeRepository.findById(request.getOfficeId())
                    .orElseThrow(() -> new BaseException("Chi nhánh không tồn tại", ErrorCode.RESOURCE_NOT_FOUND, 404));
        }

        person.setFullName(request.getFullName());
        person.setTitle(request.getTitle());
        person.setEmail(request.getEmail());
        person.setPhone(request.getPhone());
        person.setInitials(request.getInitials());
        person.setRegion(request.getRegion());
        person.setOffice(office);

        return contactPersonRepository.save(person);
    }

    @Transactional
    public void delete(UUID id) {
        if (!contactPersonRepository.existsById(id)) {
            throw new BaseException("Người phụ trách không tồn tại", ErrorCode.RESOURCE_NOT_FOUND, 404);
        }
        contactPersonRepository.deleteById(id);
    }
}
