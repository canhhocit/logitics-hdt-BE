-- ============================================================
-- V8: Tạo bảng job_postings, site_awards, company_values
--     + seed dữ liệu thực
-- ============================================================

-- 1. Bảng tuyển dụng
CREATE TABLE IF NOT EXISTS job_postings (
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    title       VARCHAR(255)  NOT NULL,
    department  VARCHAR(100)  NOT NULL,
    location    VARCHAR(255)  NOT NULL,
    job_type    VARCHAR(50)   NOT NULL DEFAULT 'FULL_TIME',
    salary      VARCHAR(100),
    deadline    DATE,
    description TEXT,
    requirements TEXT,
    benefits    TEXT,
    status      VARCHAR(20)   NOT NULL DEFAULT 'OPEN',
    order_no    INT           NOT NULL DEFAULT 0,
    created_at  TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP,
    created_by  VARCHAR(100),
    updated_by  VARCHAR(100),
    CONSTRAINT chk_job_status CHECK (status IN ('OPEN', 'CLOSED', 'DRAFT'))
);
CREATE INDEX IF NOT EXISTS idx_job_status  ON job_postings(status);
CREATE INDEX IF NOT EXISTS idx_job_order   ON job_postings(order_no);

-- 2. Bảng giải thưởng / chứng nhận
CREATE TABLE IF NOT EXISTS site_awards (
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    title       VARCHAR(255) NOT NULL,
    year        VARCHAR(10),
    icon        VARCHAR(50),
    order_no    INT NOT NULL DEFAULT 0,
    created_at  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP
);

-- 3. Bảng giá trị cốt lõi
CREATE TABLE IF NOT EXISTS company_values (
    id          UUID PRIMARY KEY DEFAULT gen_random_uuid(),
    title       VARCHAR(255) NOT NULL,
    description TEXT,
    icon        VARCHAR(50),
    order_no    INT NOT NULL DEFAULT 0,
    created_at  TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMP
);

-- ============================================================
-- SEED: Offices (chi nhánh thực tế)
-- ============================================================
INSERT INTO offices (name, address, phone, email, region, order_no, created_by) VALUES
  ('Văn phòng Thái Nguyên',
   'Số 45 Đường Thống Nhất, TP. Thái Nguyên, Tỉnh Thái Nguyên',
   '0208 123 4567', 'thainguyen@hdtlogistics.vn', 'BAC', 1, 'system'),
  ('Chi nhánh Hà Nội',
   'Tầng 5, Tòa nhà Logistics, 123 Đường Vận Tải, Quận Bắc Từ Liêm, Hà Nội',
   '024 1234 5678', 'hanoi@hdtlogistics.vn', 'BAC', 2, 'system'),
  ('Chi nhánh Hải Phòng',
   'Khu Công nghiệp VSIP, Số 12 Đường Cảng, Quận Hải An, Hải Phòng',
   '0225 123 4567', 'haiphong@hdtlogistics.vn', 'BAC', 3, 'system'),
  ('Chi nhánh Đà Nẵng',
   'Số 89 Đường Cảng Biển, Quận Sơn Trà, TP. Đà Nẵng',
   '0236 123 4567', 'danang@hdtlogistics.vn', 'TRUNG', 4, 'system'),
  ('Chi nhánh TP. Hồ Chí Minh',
   'Tầng 3, 456 Đường Vận Tải, Phường Bình Hưng Hòa, Quận Bình Tân, TP. HCM',
   '028 1234 5678', 'hcm@hdtlogistics.vn', 'NAM', 5, 'system')
ON CONFLICT DO NOTHING;

-- ============================================================
-- SEED: Contact Persons (người phụ trách khu vực)
-- ============================================================
INSERT INTO contact_persons (full_name, title, email, phone, initials, region, created_by) VALUES
  ('Nguyễn Văn Minh',  'Giám đốc Khu vực Miền Bắc',        'minhngv@hdtlogistics.vn',  NULL, 'NVM', 'Miền Bắc',         'system'),
  ('Trần Thị Lan',      'Giám đốc Khu vực Trung – Nam',      'lantth@hdtlogistics.vn',   NULL, 'TTL', 'Miền Trung – Nam', 'system'),
  ('Lê Quốc Hùng',     'Phó Giám đốc Kinh doanh Hà Nội',   'hunglq@hdtlogistics.vn',   NULL, 'LQH', 'Hà Nội',           'system'),
  ('Phạm Thu Hằng',    'Giám đốc Chi nhánh TP. HCM',        'hangpt@hdtlogistics.vn',   NULL, 'PTH', 'TP. HCM',          'system'),
  ('Đỗ Mạnh Tuấn',     'Quản lý Văn phòng Thái Nguyên',     'tuandm@hdtlogistics.vn',   NULL, 'ĐMT', 'Thái Nguyên',      'system')
ON CONFLICT DO NOTHING;

-- ============================================================
-- SEED: Job Postings (tuyển dụng)
-- ============================================================
INSERT INTO job_postings (title, department, location, job_type, salary, deadline, description, requirements, benefits, status, order_no, created_by) VALUES
  (
    'Nhân viên Kinh doanh Logistics',
    'Kinh doanh',
    'Hà Nội / TP. Hồ Chí Minh',
    'FULL_TIME',
    '15 – 25 triệu VNĐ',
    '2026-10-31',
    'Tìm kiếm và phát triển khách hàng doanh nghiệp trong lĩnh vực vận tải và logistics. Duy trì quan hệ khách hàng, tư vấn giải pháp vận chuyển và hải quan phù hợp.',
    '["Tốt nghiệp Đại học chuyên ngành Kinh tế, Ngoại thương hoặc liên quan","Kinh nghiệm 1–2 năm trong lĩnh vực logistics/vận tải","Kỹ năng giao tiếp và thuyết phục tốt","Chịu được áp lực công việc, năng động"]',
    '["Lương cơ bản + hoa hồng hấp dẫn","Bảo hiểm xã hội đầy đủ","Đào tạo nghiệp vụ logistics chuyên sâu","Môi trường làm việc trẻ, năng động"]',
    'OPEN', 1, 'system'
  ),
  (
    'Nhân viên Khai báo Hải quan',
    'Vận hành',
    'Hà Nội / Hải Phòng',
    'FULL_TIME',
    '12 – 20 triệu VNĐ',
    '2026-10-31',
    'Khai báo tờ khai điện tử trên hệ thống VNACCS/VCIS. Xử lý hồ sơ hải quan xuất nhập khẩu, theo dõi phân luồng và thông quan hàng hóa.',
    '["Tốt nghiệp Đại học chuyên ngành Hải quan, Ngoại thương, Kinh tế","Có chứng chỉ đại lý hải quan là lợi thế","Thành thạo hệ thống VNACCS/VCIS","Cẩn thận, tỉ mỉ, có trách nhiệm cao"]',
    '["Lương cạnh tranh theo năng lực","Đào tạo cập nhật chính sách hải quan thường xuyên","Phụ cấp xăng xe, điện thoại","Thưởng KPI theo quý"]',
    'OPEN', 2, 'system'
  ),
  (
    'Chuyên viên Vận tải Quốc tế',
    'Vận hành',
    'TP. Hồ Chí Minh',
    'FULL_TIME',
    '15 – 22 triệu VNĐ',
    '2026-10-31',
    'Booking tàu biển và hàng không quốc tế, theo dõi lịch tàu/chuyến bay, phối hợp với các hãng vận chuyển để xử lý lô hàng FCL/LCL/Air freight.',
    '["Tốt nghiệp Đại học chuyên ngành Logistics, Ngoại thương","Kinh nghiệm 1–3 năm trong booking vận tải quốc tế","Tiếng Anh giao tiếp tốt (đọc/viết)","Am hiểu Incoterms, L/C, chứng từ xuất nhập khẩu"]',
    '["Cơ hội tiếp xúc khách hàng quốc tế","Lộ trình thăng tiến rõ ràng","Bảo hiểm sức khỏe cho nhân viên","Du lịch team building hàng năm"]',
    'OPEN', 3, 'system'
  ),
  (
    'Kế toán Logistics',
    'Tài chính – Kế toán',
    'Thái Nguyên',
    'FULL_TIME',
    '10 – 15 triệu VNĐ',
    '2026-10-31',
    'Quản lý công nợ, lập hóa đơn, theo dõi và đối chiếu cước phí vận chuyển. Kết hợp với bộ phận kinh doanh và vận hành để đảm bảo dòng tiền.',
    '["Tốt nghiệp Đại học Kế toán, Tài chính","Thành thạo phần mềm kế toán (MISA, Fast Accounting)","Tốt nghiệp loại khá trở lên","Cẩn thận, trung thực, có tinh thần trách nhiệm"]',
    '["Môi trường làm việc ổn định, chuyên nghiệp","Đào tạo nghiệp vụ kế toán logistics","Lương tháng 13 + thưởng cuối năm","Được đóng BHXH, BHYT đầy đủ"]',
    'OPEN', 4, 'system'
  ),
  (
    'Thực tập sinh Logistics',
    'Kinh doanh',
    'Hà Nội / TP. Hồ Chí Minh / Thái Nguyên',
    'INTERNSHIP',
    '3 – 5 triệu VNĐ',
    '2026-10-31',
    'Hỗ trợ bộ phận kinh doanh và vận hành trong các công việc hàng ngày: tìm kiếm thông tin khách hàng, soạn thảo báo giá, theo dõi đơn hàng.',
    '["Sinh viên năm 3–4 hoặc mới tốt nghiệp chuyên ngành Logistics, Ngoại thương, Kinh tế","Năng động, chủ động, ham học hỏi","Thành thạo MS Office","Có thể đi làm ít nhất 3 buổi/tuần"]',
    '["Được đào tạo thực tế về nghiệp vụ logistics","Hỗ trợ chi phí đi lại","Cơ hội nhận việc chính thức sau thực tập","Môi trường trẻ, thân thiện"]',
    'OPEN', 5, 'system'
  )
ON CONFLICT DO NOTHING;

-- ============================================================
-- SEED: Site Awards (giải thưởng / chứng nhận)
-- ============================================================
INSERT INTO site_awards (title, year, icon, order_no) VALUES
  ('Top 100 Doanh nghiệp Logistics Uy tín',   '2024', 'award',       1),
  ('Chứng nhận ISO 9001:2015',                 '2023', 'shield-check', 2),
  ('Thành viên VIFFAS',                         '2023', 'users',        3),
  ('Đối tác chiến lược Yang Ming & SITC',       '2024', 'handshake',    4)
ON CONFLICT DO NOTHING;

-- ============================================================
-- SEED: Company Values (giá trị cốt lõi)
-- ============================================================
INSERT INTO company_values (title, description, icon, order_no) VALUES
  ('Đúng hẹn',
   'Cam kết giao hàng đúng thời gian đã thỏa thuận — không để khách hàng chờ đợi hay bị phạt hợp đồng do lỗi vận chuyển.',
   'clock', 1),
  ('Minh bạch',
   'Báo cáo chi phí và tiến độ rõ ràng từng bước. Không phí ẩn, không bất ngờ — khách hàng luôn biết hàng đang ở đâu.',
   'eye', 2),
  ('Chuyên nghiệp',
   'Đội ngũ được đào tạo bài bản về nghiệp vụ logistics, hải quan và ngoại thương. Xử lý chính xác ngay từ lần đầu.',
   'briefcase', 3),
  ('Tận tâm',
   'Luôn đồng hành cùng khách hàng từ lúc lên kế hoạch đến khi hàng về kho — kể cả ngoài giờ hành chính khi cần thiết.',
   'heart', 4),
  ('Linh hoạt',
   'Sẵn sàng thích ứng với yêu cầu đặc thù của từng ngành hàng: hàng lạnh, hàng nguy hiểm, hàng dự án siêu trường siêu trọng.',
   'zap', 5),
  ('Tin cậy',
   'Hơn 10 năm kinh nghiệm, 1.500+ chuyến hàng thành công là minh chứng cho sự tin tưởng mà khách hàng đã dành cho HDT.',
   'shield', 6)
ON CONFLICT DO NOTHING;
