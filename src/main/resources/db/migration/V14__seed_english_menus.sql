-- 1. Update existing top-level menus to English URLs
UPDATE menu_items SET url = '/about' WHERE url = '/ve-chung-toi';
UPDATE menu_items SET url = '/services' WHERE url = '/dich-vu';
UPDATE menu_items SET url = '/news' WHERE url = '/tin-tuc';
UPDATE menu_items SET url = '/contact' WHERE url = '/lien-he';
UPDATE menu_items SET url = '/careers' WHERE url = '/tuyen-dung';

-- 2. Clear existing sub-menus and FOOTER_SERVICES if any to avoid duplicates
DELETE FROM menu_items WHERE parent_id = '33333333-3333-3333-3333-333333333333';
DELETE FROM menu_items WHERE parent_id = '55555555-5555-5555-5555-555555555555';
DELETE FROM menu_items WHERE position = 'FOOTER_SERVICES';

-- 3. Insert new sub-menus for Services (Parent: 33333333-3333-3333-3333-333333333333)
INSERT INTO menu_items (label, url, parent_id, position, order_no, created_by) VALUES
  ('Vận Chuyển Đường Không', '/services/van-chuyen-duong-khong', '33333333-3333-3333-3333-333333333333', 'MAIN', 1, 'system'),
  ('Vận Chuyển Đường Biển', '/services/van-chuyen-duong-bien', '33333333-3333-3333-3333-333333333333', 'MAIN', 2, 'system'),
  ('Pickup', '/services/pickup', '33333333-3333-3333-3333-333333333333', 'MAIN', 3, 'system'),
  ('Vận Chuyển Đường Bộ', '/services/van-chuyen-duong-bo', '33333333-3333-3333-3333-333333333333', 'MAIN', 4, 'system'),
  ('Khai Thuê Hải Quan', '/services/khai-thue-hai-quan', '33333333-3333-3333-3333-333333333333', 'MAIN', 5, 'system');

-- 4. Update FOOTER_SERVICES to match Services
INSERT INTO menu_items (label, url, position, order_no, created_by) VALUES
  ('Vận Chuyển Đường Không', '/services/van-chuyen-duong-khong', 'FOOTER_SERVICES', 1, 'system'),
  ('Vận Chuyển Đường Biển', '/services/van-chuyen-duong-bien', 'FOOTER_SERVICES', 2, 'system'),
  ('Pickup', '/services/pickup', 'FOOTER_SERVICES', 3, 'system'),
  ('Vận Chuyển Đường Bộ', '/services/van-chuyen-duong-bo', 'FOOTER_SERVICES', 4, 'system'),
  ('Khai Thuê Hải Quan', '/services/khai-thue-hai-quan', 'FOOTER_SERVICES', 5, 'system');

-- 5. Insert new sub-menus for News (Parent: 55555555-5555-5555-5555-555555555555)
INSERT INTO menu_items (label, url, parent_id, position, order_no, created_by) VALUES
  ('Hướng dẫn và Thủ tục', '/news?categorySlug=huong-dan-va-thu-tuc', '55555555-5555-5555-5555-555555555555', 'MAIN', 1, 'system'),
  ('Tin ngành Logistics', '/news?categorySlug=tin-nganh-logistics', '55555555-5555-5555-5555-555555555555', 'MAIN', 2, 'system');
