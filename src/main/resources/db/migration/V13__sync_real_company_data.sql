-- V13: Đồng bộ thông tin thực tế công ty từ web-logitics-hdt/src/data/index.ts

-- ─────────────────────────────────────────────
-- 1. Site Settings — thông tin công ty thực tế
-- ─────────────────────────────────────────────
INSERT INTO site_settings (key, value, description, created_by) VALUES
  ('hotline',              '0983 116 063',                      'Hotline hiển thị trên website', 'system'),
  ('hotline_raw',          '0983116063',                        'Hotline raw cho tel: link', 'system'),
  ('hotline_contact',      '0983 116 063',                      'Số liên hệ phụ', 'system'),
  ('hotline_contact_raw',  '0983116063',                        'Số liên hệ phụ raw', 'system'),
  ('email',                'hdt.logistics.jsc@gmail.com',       'Email chính', 'system'),
  ('email_sales',          'hdt.logistics.jsc@gmail.com',       'Email kinh doanh', 'system'),
  ('company_full_name',    'Công ty TNHH Đầu Tư Kinh Doanh Và Xây Dựng Hải Đăng', 'Tên đầy đủ', 'system'),
  ('company_short_name',   'HDT Logistics',                     'Tên viết tắt', 'system'),
  ('tax_code',             '4601607741',                        'Mã số thuế', 'system'),
  ('license_date',         '15/07/2023',                        'Ngày cấp phép', 'system'),
  ('license_org',          'Sở Tài Chính Thái Nguyên',          'Cơ quan cấp phép', 'system'),
  ('working_hours',        '06:00 – 22:00',                     'Giờ làm việc', 'system'),
  ('facebook_url',         'https://facebook.com',              'Facebook URL', 'system'),
  ('instagram_url',        'https://instagram.com',             'Instagram URL', 'system'),
  ('twitter_url',          'https://twitter.com',               'Twitter URL', 'system'),
  ('counter_staff',        '20',                                'Số nhân sự thực tế', 'system'),
  ('counter_offices',      '5',                                 'Số văn phòng thực tế', 'system'),
  ('counter_shipments',    '1000+',                             'Số chuyến hàng', 'system'),
  ('counter_experience',   '2',                                 'Số năm kinh nghiệm', 'system'),
  ('about_vision',         'Trở thành đối tác logistics B2B uy tín tại Việt Nam và Đông Dương — tin cậy, minh bạch và đúng hẹn trong mọi lô hàng.', 'Tầm nhìn', 'system'),
  ('about_mission',        'Cung cấp giải pháp logistics toàn diện theo mô hình H.D.T: High Delivery – High Discipline – High Transparency, giúp doanh nghiệp tối ưu chuỗi cung ứng và giảm chi phí vận chuyển.', 'Sứ mệnh', 'system'),
  ('about_tagline',        'H.D.T — High Delivery · High Discipline · High Transparency', 'Tagline', 'system'),
  ('hero_title',           'Logistics Toàn Diện — Đúng Hẹn, Đúng Cam Kết', 'Hero title', 'system'),
  ('hero_subtitle',        'HDT Logistics cung cấp dịch vụ vận tải đa phương thức, khai thuê hải quan và tối ưu chuỗi cung ứng cho doanh nghiệp B2B.', 'Hero subtitle', 'system'),
  ('cta_title',            'Bắt đầu hợp tác cùng HDT Logistics', 'CTA title', 'system'),
  ('cta_subtitle',         'Đội ngũ chuyên gia sẵn sàng phân tích nhu cầu và đề xuất phương án vận chuyển phù hợp — phản hồi trong vòng 2 giờ làm việc.', 'CTA subtitle', 'system')
ON CONFLICT (key) DO UPDATE SET value = EXCLUDED.value, updated_at = NOW();

-- ─────────────────────────────────────────────
-- 2. Offices — 5 văn phòng thực tế
-- ─────────────────────────────────────────────
TRUNCATE offices RESTART IDENTITY CASCADE;

INSERT INTO offices (name, address, phone, email, region, order_no, created_by) VALUES
  ('Chi nhánh Hà Nội',          'Hà Nội',                   '0983 116 063', 'hdt.logistics.jsc@gmail.com', 'BAC',   1, 'system'),
  ('Chi nhánh Bắc Ninh',        'Bắc Ninh',                 '0983 116 063', 'hdt.logistics.jsc@gmail.com', 'BAC',   2, 'system'),
  ('Chi nhánh Đà Nẵng',         'Đà Nẵng',                  '0983 116 063', 'hdt.logistics.jsc@gmail.com', 'TRUNG', 3, 'system'),
  ('Chi nhánh Ninh Bình',       'Ninh Bình',                '0983 116 063', 'hdt.logistics.jsc@gmail.com', 'TRUNG', 4, 'system'),
  ('Văn phòng Phnom Pênh',      'Phnom Pênh, Cambodia',     '0983 116 063', 'hdt.logistics.jsc@gmail.com', 'NAM',   5, 'system');

-- ─────────────────────────────────────────────
-- 3. Contact Persons — 3 người phụ trách thực tế
-- ─────────────────────────────────────────────
TRUNCATE contact_persons RESTART IDENTITY CASCADE;

INSERT INTO contact_persons (full_name, title, email, phone, initials, region, created_by) VALUES
  ('Trần Văn Toản',   'Tổng giám đốc',                             'hdt.logistics.jsc@gmail.com', '0983 116 063', 'TVT', 'Toàn quốc',             'system'),
  ('Trần Đình Đức',   'Giám đốc phụ trách miền Bắc',               'hdt.logistics.jsc@gmail.com', '0983 116 063', 'TĐĐ', 'Miền Bắc',              'system'),
  ('Trần Đình Long',  'Giám đốc phụ trách miền Trung & Cambodia',  'hdt.logistics.jsc@gmail.com', '0983 116 063', 'TĐL', 'Miền Trung & Cambodia', 'system');

-- ─────────────────────────────────────────────
-- 4. Services — 5 dịch vụ thực tế (bỏ kho bãi, thêm pickup)
-- ─────────────────────────────────────────────
-- Xóa service cũ không còn dùng
DELETE FROM logistics_services WHERE slug IN ('van-chuyen-lcl', 'hang-dac-biet', 'gom-hang-le-lcl');

-- Thêm service "Pickup" nếu chưa có
INSERT INTO logistics_services (name, slug, order_no, thumbnail, intro, short_desc, icon, color, status, created_by) VALUES
  ('Pickup',
   'pickup', 3,
   '/images/services/lcl.jpg',
   'Ghép hàng lẻ vào container chung, tính cước theo CBM. Lịch gom hàng cố định hàng tuần, tiết kiệm chi phí tối đa.',
   'Ghép hàng lẻ vào container chung, tính cước theo CBM. Lịch gom hàng cố định hàng tuần, tiết kiệm chi phí tối đa.',
   'boxes', 'teal', 'PUBLISHED', 'system')
ON CONFLICT (slug) DO NOTHING;

-- Cập nhật lại order_no cho 5 dịch vụ chính xác
UPDATE logistics_services SET order_no = 1 WHERE slug = 'van-tai-hang-khong';
UPDATE logistics_services SET order_no = 2 WHERE slug = 'van-tai-duong-bien';
UPDATE logistics_services SET order_no = 3 WHERE slug = 'pickup';
UPDATE logistics_services SET order_no = 4 WHERE slug = 'van-tai-duong-bo';
UPDATE logistics_services SET order_no = 5 WHERE slug = 'khai-bao-hai-quan';

-- Thêm slug aliases để cả slug cũ lẫn slug mới đều hoạt động
INSERT INTO logistics_services (name, slug, order_no, thumbnail, intro, short_desc, icon, color, status, created_by)
SELECT name, 'van-chuyen-duong-khong', order_no, thumbnail, intro, short_desc, icon, color, status, created_by
FROM logistics_services WHERE slug = 'van-tai-hang-khong'
ON CONFLICT (slug) DO NOTHING;

INSERT INTO logistics_services (name, slug, order_no, thumbnail, intro, short_desc, icon, color, status, created_by)
SELECT name, 'van-chuyen-duong-bien', order_no, thumbnail, intro, short_desc, icon, color, status, created_by
FROM logistics_services WHERE slug = 'van-tai-duong-bien'
ON CONFLICT (slug) DO NOTHING;

INSERT INTO logistics_services (name, slug, order_no, thumbnail, intro, short_desc, icon, color, status, created_by)
SELECT name, 'van-chuyen-duong-bo', order_no, thumbnail, intro, short_desc, icon, color, status, created_by
FROM logistics_services WHERE slug = 'van-tai-duong-bo'
ON CONFLICT (slug) DO NOTHING;

INSERT INTO logistics_services (name, slug, order_no, thumbnail, intro, short_desc, icon, color, status, created_by)
SELECT name, 'khai-thue-hai-quan', order_no, thumbnail, intro, short_desc, icon, color, status, created_by
FROM logistics_services WHERE slug = 'khai-bao-hai-quan'
ON CONFLICT (slug) DO NOTHING;

-- ─────────────────────────────────────────────
-- 5. Jobs — Cập nhật 5 vị trí thực tế
-- ─────────────────────────────────────────────
TRUNCATE job_postings RESTART IDENTITY CASCADE;

INSERT INTO job_postings (title, department, location, job_type, salary, deadline, description, requirements, benefits, status, order_no, created_by) VALUES
  ('Nhân viên Kinh doanh Logistics', 'Kinh doanh', 'Hà Nội / TP. Hồ Chí Minh', 'FULL_TIME', '15 – 25 triệu VNĐ', '2026-10-31',
   'Tìm kiếm và phát triển khách hàng doanh nghiệp trong lĩnh vực vận tải và logistics. Duy trì quan hệ khách hàng, tư vấn giải pháp vận chuyển và hải quan phù hợp.',
   '["Tốt nghiệp Đại học chuyên ngành Kinh tế, Ngoại thương hoặc liên quan","Kinh nghiệm 1–2 năm trong lĩnh vực logistics/vận tải","Kỹ năng giao tiếp và thuyết phục tốt","Chịu được áp lực công việc, tư duy kinh doanh tốt"]',
   '["Lương cơ bản + hoa hồng hấp dẫn","Bảo hiểm xã hội đầy đủ","Đào tạo nghiệp vụ logistics chuyên sâu","Môi trường làm việc trẻ, năng động"]',
   'OPEN', 1, 'system'),

  ('Chuyên viên Khai báo Hải quan', 'Hải quan', 'Hà Nội / Hải Phòng', 'FULL_TIME', '12 – 20 triệu VNĐ', '2026-10-31',
   'Khai báo hải quan điện tử VNACCS/VCIS cho hàng xuất nhập khẩu. Tư vấn phân loại HS Code, xử lý hồ sơ thông quan và làm việc với cơ quan hải quan.',
   '["Tốt nghiệp Đại học Ngoại thương, Hải quan hoặc liên quan","Có chứng chỉ đại lý hải quan là lợi thế","Kinh nghiệm khai báo VNACCS 1+ năm","Am hiểu Incoterms, quy trình XNK"]',
   '["Lương cạnh tranh theo năng lực","Đào tạo cập nhật chính sách hải quan thường xuyên","Phụ cấp xăng xe, điện thoại","Thưởng KPI theo quý"]',
   'OPEN', 2, 'system'),

  ('Nhân viên Điều phối Vận tải', 'Vận hành', 'Hà Nội / TP. HCM', 'FULL_TIME', '10 – 16 triệu VNĐ', '2026-10-31',
   'Điều phối xe tải, liên hệ đối tác vận chuyển, theo dõi lịch trình và xử lý phát sinh trong quá trình vận chuyển hàng hóa nội địa và quốc tế.',
   '["Tốt nghiệp Đại học/Cao đẳng chuyên ngành liên quan","Kinh nghiệm điều phối vận tải 1+ năm","Kỹ năng giao tiếp tốt, chịu áp lực","Biết sử dụng Excel và phần mềm quản lý vận tải"]',
   '["Môi trường năng động, nhiều cơ hội học hỏi","BHXH đầy đủ","Thưởng tháng 13","Phụ cấp công tác"]',
   'OPEN', 3, 'system'),

  ('Chuyên viên Kinh doanh Hàng Hải (Sea Freight)', 'Kinh doanh', 'Hải Phòng / TP. HCM', 'FULL_TIME', '15 – 28 triệu VNĐ', '2026-10-31',
   'Phát triển khách hàng sử dụng dịch vụ vận chuyển đường biển FCL/LCL. Đàm phán cước với hãng tàu, tư vấn tuyến phù hợp và theo dõi hành trình container.',
   '["Kinh nghiệm 2+ năm trong lĩnh vực Sea Freight","Có mạng lưới khách hàng xuất nhập khẩu là lợi thế lớn","Tiếng Anh thương mại tốt","Am hiểu Incoterms, B/L và quy trình booking hãng tàu"]',
   '["Hoa hồng không giới hạn","Cơ hội tiếp xúc khách hàng quốc tế","Lộ trình thăng tiến rõ ràng","Bảo hiểm sức khỏe"]',
   'OPEN', 4, 'system'),

  ('Kế toán Logistics', 'Kế toán – Tài chính', 'Hà Nội', 'FULL_TIME', '10 – 15 triệu VNĐ', '2026-10-31',
   'Hạch toán doanh thu và chi phí dịch vụ logistics, đối chiếu công nợ với khách hàng và đối tác, lập báo cáo tài chính định kỳ.',
   '["Tốt nghiệp Đại học Kế toán, Tài chính","Kinh nghiệm kế toán 1+ năm, ưu tiên ngành logistics","Thành thạo Excel, phần mềm kế toán (MISA, FAST...)","Cẩn thận, tỉ mỉ, trung thực"]',
   '["Môi trường làm việc ổn định","Lương tháng 13","BHXH, BHYT đầy đủ","Được đào tạo nghiệp vụ kế toán logistics"]',
   'OPEN', 5, 'system');

-- ─────────────────────────────────────────────
-- 6. Menu — Cập nhật hotline trong footer
-- ─────────────────────────────────────────────
UPDATE menu_items SET url = '/news?categorySlug=huong-dan-va-thu-tuc'
  WHERE label = 'Hướng dẫn và Thủ tục' AND parent_id IS NOT NULL;
UPDATE menu_items SET url = '/news?categorySlug=tin-nganh-logistics'
  WHERE label = 'Tin ngành Logistics' AND parent_id IS NOT NULL;
