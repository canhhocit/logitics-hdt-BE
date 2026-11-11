-- V20: Thêm created_by và updated_by vào bảng job_applications để đồng bộ với BaseEntity của Spring Data JPA
ALTER TABLE job_applications ADD COLUMN IF NOT EXISTS created_by VARCHAR(100);
ALTER TABLE job_applications ADD COLUMN IF NOT EXISTS updated_by VARCHAR(100);
