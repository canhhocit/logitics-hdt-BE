-- Seed Initial Site Settings
INSERT INTO site_settings (key, value, description, created_by) VALUES
  ('hotline', '0929 959 699', 'Số hotline hiển thị trên top bar', 'system'),
  ('working_hours', '06:00 - 22:00', 'Giờ làm việc', 'system'),
  ('email', 'info@hdlogistics.vn', 'Email liên hệ', 'system'),
  ('facebook_url', 'https://facebook.com/hdlogistics', 'Link Facebook', 'system'),
  ('zalo_url', 'https://zalo.me/hdlogistics', 'Link Zalo', 'system'),
  ('counter_staff', '50+', 'Số lượng nhân viên', 'system'),
  ('counter_offices', '5', 'Số lượng văn phòng', 'system'),
  ('counter_shipments', '1500+', 'Số lượng chuyến hàng', 'system'),
  ('company_name', 'CÔNG TY TNHH HD LOGISTICS', 'Tên công ty', 'system'),
  ('company_address', 'Số 123 Đường ABC, Quận XYZ, TP. HCM', 'Địa chỉ trụ sở chính', 'system')
ON CONFLICT (key) DO NOTHING;

-- Seed Initial Menu Items (MAIN)
INSERT INTO menu_items (id, label, url, position, order_no, created_by) VALUES
  ('11111111-1111-1111-1111-111111111111', 'Trang chủ', '/', 'MAIN', 1, 'system'),
  ('22222222-2222-2222-2222-222222222222', 'Về chúng tôi', '/ve-chung-toi', 'MAIN', 2, 'system'),
  ('33333333-3333-3333-3333-333333333333', 'Dịch vụ', '/dich-vu', 'MAIN', 3, 'system'),
  ('44444444-4444-4444-4444-444444444444', 'Tư vấn hải quan', '/tu-van-hai-quan', 'MAIN', 4, 'system'),
  ('55555555-5555-5555-5555-555555555555', 'Tin tức', '/tin-tuc', 'MAIN', 5, 'system'),
  ('66666666-6666-6666-6666-666666666666', 'Liên hệ', '/lien-he', 'MAIN', 6, 'system');

-- Seed Sub-menus for Dịch vụ
INSERT INTO menu_items (label, url, parent_id, position, order_no, created_by) VALUES
  ('Vận tải đường biển', '/dich-vu/van-tai-duong-bien', '33333333-3333-3333-3333-333333333333', 'MAIN', 1, 'system'),
  ('Vận tải hàng không', '/dich-vu/van-tai-hang-khong', '33333333-3333-3333-3333-333333333333', 'MAIN', 2, 'system'),
  ('Vận tải đường bộ', '/dich-vu/van-tai-duong-bo', '33333333-3333-3333-3333-333333333333', 'MAIN', 3, 'system'),
  ('Khai báo hải quan', '/tu-van-hai-quan', '33333333-3333-3333-3333-333333333333', 'MAIN', 4, 'system'),
  ('Hàng đặc biệt & Hàng dự án', '/dich-vu/hang-dac-biet', '33333333-3333-3333-3333-333333333333', 'MAIN', 5, 'system');

-- Seed Initial Menu Items (FOOTER_SERVICES)
INSERT INTO menu_items (label, url, position, order_no, created_by) VALUES
  ('Vận tải đường biển', '/dich-vu/van-tai-duong-bien', 'FOOTER_SERVICES', 1, 'system'),
  ('Vận tải hàng không', '/dich-vu/van-tai-hang-khong', 'FOOTER_SERVICES', 2, 'system'),
  ('Vận tải đường bộ', '/dich-vu/van-tai-duong-bo', 'FOOTER_SERVICES', 3, 'system'),
  ('Khai báo hải quan', '/tu-van-hai-quan', 'FOOTER_SERVICES', 4, 'system'),
  ('Hàng đặc biệt & Hàng dự án', '/dich-vu/hang-dac-biet', 'FOOTER_SERVICES', 5, 'system');
