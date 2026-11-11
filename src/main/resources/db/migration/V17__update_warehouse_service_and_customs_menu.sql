-- ============================================================
-- V17: Update Warehouse Service and Customs Consultation Menu
-- ============================================================

-- 1. Chuẩn hóa tên & icon dịch vụ số 5 trong logistics_services
UPDATE logistics_services
SET name = 'Dịch Vụ Thuê Kho Bãi',
    slug = 'dich-vu-thue-kho-bai',
    icon = 'package',
    color = 'cyan',
    intro = 'Hệ thống kho bãi tiêu chuẩn cao, an ninh 24/7 và giải pháp quản lý tồn kho WMS chuyên nghiệp.',
    short_desc = 'Hệ thống kho bãi tiêu chuẩn cao, quản lý WMS thông minh, PCCC tự động, camera 24/7 và dịch vụ giá trị gia tăng (VAS) trọn gói.'
WHERE id = '1b639024-c7b6-477e-a26a-d8c6c234982d' OR slug = 'dich-vu-thue-kho-bai';

-- 2. Cập nhật menu Dịch vụ con (MAIN) thay Khai thuế hải quan bằng Dịch vụ thuê kho bãi
UPDATE menu_items
SET label = 'Dịch Vụ Thuê Kho Bãi',
    url = '/services/dich-vu-thue-kho-bai'
WHERE parent_id = '33333333-3333-3333-3333-333333333333' AND order_no = 5;

-- 3. Cập nhật menu FOOTER_SERVICES
UPDATE menu_items
SET label = 'Dịch Vụ Thuê Kho Bãi',
    url = '/services/dich-vu-thue-kho-bai'
WHERE position = 'FOOTER_SERVICES' AND order_no = 5;

-- 4. Thêm sub-menus cho mục 'Tư vấn hải quan' (Parent: 44444444-4444-4444-4444-444444444444)
DELETE FROM menu_items WHERE parent_id = '44444444-4444-4444-4444-444444444444';

INSERT INTO menu_items (label, url, parent_id, position, order_no, created_by) VALUES
  ('Khai Thuê Hải Quan', '/services/khai-thue-hai-quan', '44444444-4444-4444-4444-444444444444', 'MAIN', 1, 'system'),
  ('Cẩm nang & Tư vấn thủ tục', '/tu-van-hai-quan', '44444444-4444-4444-4444-444444444444', 'MAIN', 2, 'system');
