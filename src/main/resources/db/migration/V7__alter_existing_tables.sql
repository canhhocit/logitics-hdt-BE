-- ============================================================
-- V7: Alter existing tables + seed missing site_settings keys
-- ============================================================

-- 1. logistics_services: thêm short_desc, icon, color
ALTER TABLE logistics_services
  ADD COLUMN IF NOT EXISTS short_desc TEXT,
  ADD COLUMN IF NOT EXISTS icon       VARCHAR(50),
  ADD COLUMN IF NOT EXISTS color      VARCHAR(30);

-- Cập nhật icon/color/short_desc cho 6 dịch vụ đã seed
UPDATE logistics_services SET
  short_desc = 'Air freight quốc tế tốc độ cao — hàng giá trị, hàng gấp, hàng công nghệ. Kết nối 50+ hãng bay, tính cước theo kg thể tích.',
  icon       = 'plane',
  color      = 'blue'
WHERE slug = 'van-tai-hang-khong';

UPDATE logistics_services SET
  short_desc = 'FCL, LCL, container lạnh, hàng rời — tới 100+ cảng biển toàn cầu. Booking trực tiếp Yang Ming, SITC, CMA CGM...',
  icon       = 'ship',
  color      = 'cyan'
WHERE slug = 'van-tai-duong-bien';

UPDATE logistics_services SET
  short_desc = 'Ghép hàng lẻ vào container chung, tính cước theo CBM. Lịch gom hàng cố định hàng tuần, tiết kiệm chi phí tối đa.',
  icon       = 'boxes',
  color      = 'teal'
WHERE slug = 'van-chuyen-lcl';

UPDATE logistics_services SET
  short_desc = 'Vận tải nội địa Bắc–Trung–Nam và xuyên biên giới ASEAN. Xe tải/container 1–40 tấn, GPS tracking 24/7.',
  icon       = 'truck',
  color      = 'green'
WHERE slug = 'van-tai-duong-bo';

UPDATE logistics_services SET
  short_desc = 'Khai báo tờ khai điện tử VNACCS, phân luồng, thông quan, nộp thuế. Tỷ lệ thông quan lần đầu >95%.',
  icon       = 'file-text',
  color      = 'orange'
WHERE slug = 'khai-bao-hai-quan';

UPDATE logistics_services SET
  short_desc = 'Hàng OOG, siêu trường siêu trọng, thiết bị dự án, hàng nguy hiểm/lạnh. Xử lý kịp thời, an toàn, chi phí hợp lý.',
  icon       = 'star',
  color      = 'rose'
WHERE slug = 'hang-dac-biet';

-- 2. posts: thêm read_minutes
ALTER TABLE posts
  ADD COLUMN IF NOT EXISTS read_minutes INT DEFAULT 5;

UPDATE posts SET read_minutes = 8  WHERE slug = 'chinh-sach-thu-tuc-xnk-2026';
UPDATE posts SET read_minutes = 10 WHERE slug = 'thu-tuc-nhap-khau-hang-hoa';
UPDATE posts SET read_minutes = 9  WHERE slug = 'thu-tuc-xuat-khau-hang-hoa';
UPDATE posts SET read_minutes = 6  WHERE slug = 'cap-nhat-van-tai-2026';

-- 3. offices: thêm email
ALTER TABLE offices
  ADD COLUMN IF NOT EXISTS email VARCHAR(150);

-- 4. contact_persons: thêm initials + region (vùng phụ trách)
ALTER TABLE contact_persons
  ADD COLUMN IF NOT EXISTS initials VARCHAR(10),
  ADD COLUMN IF NOT EXISTS region   VARCHAR(100);

-- 5. site_settings: seed thêm các key còn thiếu
INSERT INTO site_settings (key, value, description, created_by) VALUES
  ('hotline_raw',           '0929959699',                      'Hotline raw (dùng cho tel: link)', 'system'),
  ('hotline_contact',       '0563 877 509',                    'Số liên hệ phụ trang Liên hệ', 'system'),
  ('hotline_contact_raw',   '0563877509',                      'Số liên hệ phụ raw', 'system'),
  ('email_sales',           'sales@hdtlogistics.vn',            'Email kinh doanh', 'system'),
  ('company_short_name',    'HDT Logistics',                   'Tên viết tắt công ty', 'system'),
  ('company_full_name',     'Công ty TNHH Đầu Tư Kinh Doanh Và Xây Dựng Hải Đăng', 'Tên đầy đủ công ty', 'system'),
  ('tax_code',              '4601607741',                      'Mã số thuế', 'system'),
  ('license_date',          '15/07/2023',                      'Ngày cấp phép kinh doanh', 'system'),
  ('license_org',           'Sở Tài Chính Thái Nguyên',        'Cơ quan cấp phép', 'system'),
  ('instagram_url',         'https://instagram.com/hdtlogistics', 'Link Instagram', 'system'),
  ('twitter_url',           'https://twitter.com/hdtlogistics',   'Link Twitter', 'system'),
  ('counter_experience',    '10+',                             'Số năm kinh nghiệm', 'system'),
  ('about_vision',          'Trở thành đối tác logistics B2B số 1 tại Việt Nam — tin cậy, minh bạch và đúng hẹn trong mọi lô hàng.', 'Tầm nhìn công ty', 'system'),
  ('about_mission',         'Cung cấp giải pháp logistics toàn diện theo mô hình H.D.T: High Delivery – High Discipline – High Transparency, giúp doanh nghiệp tối ưu chuỗi cung ứng và giảm chi phí vận chuyển.', 'Sứ mệnh công ty', 'system'),
  ('hero_title',            'Logistics Toàn Diện — Đúng Hẹn, Đúng Cam Kết',  'Hero section title trang chủ', 'system'),
  ('hero_subtitle',         'HDT Logistics cung cấp dịch vụ vận tải đa phương thức, khai thuê hải quan và tối ưu chuỗi cung ứng cho doanh nghiệp B2B.', 'Hero section subtitle', 'system'),
  ('cta_title',             'Bắt đầu hợp tác cùng HDT Logistics',            'CTA section title', 'system'),
  ('cta_subtitle',          'Đội ngũ chuyên gia sẵn sàng phân tích nhu cầu và đề xuất phương án vận chuyển phù hợp — phản hồi trong vòng 2 giờ làm việc.', 'CTA section subtitle', 'system'),
  ('about_tagline',         'H.D.T — High Delivery · High Discipline · High Transparency', 'Tagline công ty', 'system')
ON CONFLICT (key) DO NOTHING;

-- 6. menu_items: đảm bảo position FOOTER_SERVICES tồn tại (nếu chưa có)
INSERT INTO menu_items (label, url, position, order_no, created_by) VALUES
  ('Trang chủ',             '/',                         'FOOTER_COMPANY', 1, 'system'),
  ('Về chúng tôi',          '/ve-chung-toi',             'FOOTER_COMPANY', 2, 'system'),
  ('Tin tức',               '/tin-tuc',                  'FOOTER_COMPANY', 3, 'system'),
  ('Liên hệ',               '/lien-he',                  'FOOTER_COMPANY', 4, 'system'),
  ('Tuyển dụng',            '/tuyen-dung',               'FOOTER_COMPANY', 5, 'system')
ON CONFLICT DO NOTHING;
