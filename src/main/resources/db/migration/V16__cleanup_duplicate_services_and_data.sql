-- ============================================================
-- V16: Clean up duplicate logistics services and standardize menus
-- ============================================================

-- 0. Gỡ bỏ liên kết khóa ngoại từ leads nếu có
UPDATE leads SET service_id = NULL WHERE service_id IS NOT NULL;

-- 1. Xóa toàn bộ service trùng lặp, alias hoặc đã soft-delete
DELETE FROM logistics_services;

-- 2. Chèn chuẩn 6 dịch vụ chính thức (không trùng lặp, đồng bộ 100% với Frontend)
INSERT INTO logistics_services (id, name, slug, order_no, thumbnail, intro, short_desc, icon, color, status, created_by) VALUES
  ('5dc5e55e-249e-4745-aa0f-3da923a84c74',
   'Vận Chuyển Đường Không',
   'van-chuyen-duong-khong',
   1,
   '/images/services/air-freight.jpg',
   'Vận tải đa phương thức bằng đường hàng không',
   'Air freight quốc tế tốc độ cao — hàng giá trị, hàng gấp, hàng công nghệ. Kết nối 50+ hãng bay, tính cước theo kg thể tích.',
   'plane',
   'blue',
   'PUBLISHED',
   'system'),

  ('388bc35e-1010-42a4-8395-db8dbbc5598c',
   'Vận Chuyển Đường Biển',
   'van-chuyen-duong-bien',
   2,
   '/images/services/sea-freight.jpg',
   'Vận tải đa phương thức bằng đường biển',
   'FCL, LCL, container lạnh, hàng rời — tới 100+ cảng biển toàn cầu. Booking trực tiếp Yang Ming, SITC, CMA CGM...',
   'ship',
   'cyan',
   'PUBLISHED',
   'system'),

  ('f176a081-2cb2-40ab-b068-7055569fa00a',
   'Gom Hàng Lẻ (LCL)',
   'gom-hang-le-lcl',
   3,
   '/images/services/lcl.jpg',
   'Ghép hàng lẻ vào container chung, tính cước theo CBM. Lịch gom hàng cố định hàng tuần, tiết kiệm chi phí tối đa.',
   'Ghép hàng lẻ vào container chung, tính cước theo CBM. Lịch gom hàng cố định hàng tuần, tiết kiệm chi phí tối đa.',
   'boxes',
   'teal',
   'PUBLISHED',
   'system'),

  ('c51d78ac-1806-4af8-b850-5874aa97984c',
   'Vận Chuyển Đường Bộ',
   'van-chuyen-duong-bo',
   4,
   '/images/services/road.jpg',
   'Vận tải đa phương thức bằng đường bộ',
   'Vận tải nội địa Bắc–Trung–Nam và xuyên biên giới ASEAN. Xe tải/container 1–40 tấn, GPS tracking 24/7.',
   'truck',
   'green',
   'PUBLISHED',
   'system'),

  ('1b639024-c7b6-477e-a26a-d8c6c234982d',
   'Khai Thuê Hải Quan',
   'khai-thue-hai-quan',
   5,
   '/images/services/customs.jpg',
   'Dịch vụ khai báo hải quan',
   'Khai báo tờ khai điện tử VNACCS, phân luồng, thông quan, nộp thuế. Tỷ lệ thông quan lần đầu >95%.',
   'file-text',
   'orange',
   'PUBLISHED',
   'system'),

  ('7e891234-5678-4321-abcd-ef0123456789',
   'Hàng Đặc Biệt & Dự Án',
   'hang-dac-biet-du-an',
   6,
   '/images/services/project-cargo.jpg',
   'Vận chuyển hàng đặc biệt và hàng dự án',
   'Hàng OOG, siêu trường siêu trọng, thiết bị dự án, hàng nguy hiểm/lạnh. Xử lý kịp thời, an toàn, chi phí hợp lý.',
   'star',
   'rose',
   'PUBLISHED',
   'system');

-- 3. Chuẩn hóa Menu sub-items của Dịch vụ (MAIN)
DELETE FROM menu_items WHERE parent_id = '33333333-3333-3333-3333-333333333333';

INSERT INTO menu_items (label, url, parent_id, position, order_no, created_by) VALUES
  ('Vận Chuyển Đường Không', '/services/van-chuyen-duong-khong', '33333333-3333-3333-3333-333333333333', 'MAIN', 1, 'system'),
  ('Vận Chuyển Đường Biển', '/services/van-chuyen-duong-bien', '33333333-3333-3333-3333-333333333333', 'MAIN', 2, 'system'),
  ('Gom Hàng Lẻ (LCL)', '/services/gom-hang-le-lcl', '33333333-3333-3333-3333-333333333333', 'MAIN', 3, 'system'),
  ('Vận Chuyển Đường Bộ', '/services/van-chuyen-duong-bo', '33333333-3333-3333-3333-333333333333', 'MAIN', 4, 'system'),
  ('Khai Thuê Hải Quan', '/services/khai-thue-hai-quan', '33333333-3333-3333-3333-333333333333', 'MAIN', 5, 'system'),
  ('Hàng Đặc Biệt & Dự Án', '/services/hang-dac-biet-du-an', '33333333-3333-3333-3333-333333333333', 'MAIN', 6, 'system');

-- 4. Chuẩn hóa Menu FOOTER_SERVICES
DELETE FROM menu_items WHERE position = 'FOOTER_SERVICES';

INSERT INTO menu_items (label, url, position, order_no, created_by) VALUES
  ('Vận Chuyển Đường Không', '/services/van-chuyen-duong-khong', 'FOOTER_SERVICES', 1, 'system'),
  ('Vận Chuyển Đường Biển', '/services/van-chuyen-duong-bien', 'FOOTER_SERVICES', 2, 'system'),
  ('Gom Hàng Lẻ (LCL)', '/services/gom-hang-le-lcl', 'FOOTER_SERVICES', 3, 'system'),
  ('Vận Chuyển Đường Bộ', '/services/van-chuyen-duong-bo', 'FOOTER_SERVICES', 4, 'system'),
  ('Khai Thuê Hải Quan', '/services/khai-thue-hai-quan', 'FOOTER_SERVICES', 5, 'system'),
  ('Hàng Đặc Biệt & Dự Án', '/services/hang-dac-biet-du-an', 'FOOTER_SERVICES', 6, 'system');
