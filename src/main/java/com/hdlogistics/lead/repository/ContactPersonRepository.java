package com.hdlogistics.lead.repository;

import com.hdlogistics.lead.entity.ContactPerson;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface ContactPersonRepository extends JpaRepository<ContactPerson, UUID> {

    /** Lấy tất cả người phụ trách, sắp xếp theo fullName */
    List<ContactPerson> findAllByOrderByFullNameAsc();

    /** Lấy tất cả người phụ trách thuộc một văn phòng */
    List<ContactPerson> findByOfficeIdOrderByFullNameAsc(UUID officeId);

    /** Dùng để auto-assign lead (lấy người đầu tiên theo thứ tự) */
    Optional<ContactPerson> findFirstByOrderByIdAsc();
}
