-- V19: Tạo bảng quản lý hồ sơ ứng tuyển (Job Applications) & nhận CV ứng viên
CREATE TABLE IF NOT EXISTS job_applications (
    id              UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    job_id          UUID REFERENCES job_postings(id) ON DELETE SET NULL,
    job_title       VARCHAR(255),
    full_name       VARCHAR(150) NOT NULL,
    phone           VARCHAR(50)  NOT NULL,
    email           VARCHAR(150),
    cv_url          TEXT         NOT NULL,
    cv_file_name    VARCHAR(255),
    cover_letter    TEXT,
    status          VARCHAR(50)  NOT NULL DEFAULT 'APPLIED',
    notes           TEXT,
    created_at      TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at      TIMESTAMP
);

CREATE INDEX IF NOT EXISTS idx_job_applications_job_id ON job_applications(job_id);
CREATE INDEX IF NOT EXISTS idx_job_applications_status ON job_applications(status);
CREATE INDEX IF NOT EXISTS idx_job_applications_created_at ON job_applications(created_at DESC);
