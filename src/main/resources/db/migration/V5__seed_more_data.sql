-- Seed Logistics Services
INSERT INTO logistics_services (name, slug, order_no, thumbnail, intro, status, created_by) VALUES
  ('Vận tải đường biển', 'van-tai-duong-bien', 1, '/images/services/sea-freight.jpg', 'Vận tải đa phương thức bằng đường biển', 'PUBLISHED', 'system'),
  ('Vận tải hàng không', 'van-tai-hang-khong', 2, '/images/services/air-freight.jpg', 'Vận tải đa phương thức bằng đường hàng không', 'PUBLISHED', 'system'),
  ('Vận tải đường bộ', 'van-tai-duong-bo', 3, '/images/services/road.jpg', 'Vận tải đa phương thức bằng đường bộ', 'PUBLISHED', 'system'),
  ('Khai báo hải quan', 'khai-bao-hai-quan', 4, '/images/services/customs.jpg', 'Dịch vụ khai báo hải quan', 'PUBLISHED', 'system'),
  ('Vận chuyển LCL', 'van-chuyen-lcl', 5, '/images/services/lcl.jpg', 'Dịch vụ vận chuyển hàng lẻ', 'PUBLISHED', 'system'),
  ('Hàng đặc biệt & Hàng dự án', 'hang-dac-biet', 6, '/images/services/project-cargo.jpg', 'Vận chuyển hàng đặc biệt và hàng dự án', 'PUBLISHED', 'system')
ON CONFLICT (slug) DO NOTHING;

-- Seed Categories
INSERT INTO categories (id, name, slug, created_by) VALUES
  ('aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'Hướng dẫn và Thủ tục', 'huong-dan-va-thu-tuc', 'system'),
  ('bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'Tin ngành Logistics', 'tin-nganh-logistics', 'system')
ON CONFLICT (slug) DO NOTHING;

-- Seed Posts
INSERT INTO posts (title, slug, excerpt, thumbnail, category_id, status, published_at, created_by) VALUES
  ('Chính sách & Thủ tục Xuất Nhập Khẩu — Tổng hợp mới nhất 2026', 'chinh-sach-thu-tuc-xnk-2026', 'Tổng hợp các chính sách và thủ tục xuất nhập khẩu mới nhất năm 2026. Doanh nghiệp cần nắm rõ các quy định về mã HS...', '/images/news/news-xnk-policy.jpg', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'PUBLISHED', CURRENT_TIMESTAMP, 'system'),
  ('Thủ tục Nhập khẩu hàng hóa — Quy trình & Hồ sơ chi tiết', 'thu-tuc-nhap-khau-hang-hoa', 'Chi tiết các bước thực hiện thủ tục nhập khẩu hàng hóa vào Việt Nam, các loại giấy tờ chứng từ cần thiết...', '/images/news/news-import.jpg', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'PUBLISHED', CURRENT_TIMESTAMP, 'system'),
  ('Thủ tục Xuất khẩu hàng hóa — Quy trình & Hồ sơ chi tiết', 'thu-tuc-xuat-khau-hang-hoa', 'Hướng dẫn chi tiết quy trình khai báo hải quan và thông quan hàng xuất khẩu tại các cảng và sân bay quốc tế...', '/images/news/news-export.jpg', 'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'PUBLISHED', CURRENT_TIMESTAMP, 'system'),
  ('Cập nhật Vận tải & Chuỗi cung ứng — Xu hướng 2026', 'cap-nhat-van-tai-2026', 'Những thay đổi đáng kể trong chuỗi cung ứng toàn cầu và cước vận tải biển năm 2026, cách doanh nghiệp ứng phó...', '/images/news/news-logistics-trend.jpg', 'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'PUBLISHED', CURRENT_TIMESTAMP, 'system')
ON CONFLICT (slug) DO NOTHING;

-- Seed Partners (CARRIER)
INSERT INTO partners (type, name, logo, order_no, created_by) VALUES
  ('CARRIER', 'Yang Ming', '/images/partners/yang-ming.svg', 1, 'system'),
  ('CARRIER', 'SITC', '/images/partners/sitc.svg', 2, 'system'),
  ('CARRIER', 'RCL', '/images/partners/rcl.svg', 3, 'system'),
  ('CARRIER', 'PIL', '/images/partners/pil.svg', 4, 'system'),
  ('CARRIER', 'OOCL', '/images/partners/oocl.svg', 5, 'system'),
  ('CARRIER', 'ONE', '/images/partners/one.svg', 6, 'system'),
  ('CARRIER', 'COSCO', '/images/partners/cosco.svg', 7, 'system'),
  ('CARRIER', 'CMA CGM', '/images/partners/cma-cgm.svg', 8, 'system');

-- Seed Partners (ASSOCIATION)
INSERT INTO partners (type, name, logo, order_no, created_by) VALUES
  ('ASSOCIATION', 'FIATA', '/images/associations/fiata.svg', 1, 'system'),
  ('ASSOCIATION', 'IATA', '/images/associations/iata.svg', 2, 'system'),
  ('ASSOCIATION', 'VIFFAS', '/images/associations/viffas.svg', 3, 'system'),
  ('ASSOCIATION', 'VLA', '/images/associations/vla.svg', 4, 'system'),
  ('ASSOCIATION', 'LOGNET', '/images/associations/lognet.svg', 5, 'system'),
  ('ASSOCIATION', 'MLN', '/images/associations/mln.svg', 6, 'system'),
  ('ASSOCIATION', 'WCA', '/images/associations/wca.svg', 7, 'system'),
  ('ASSOCIATION', 'OLO', '/images/associations/olo.svg', 8, 'system');

-- Seed Testimonials
INSERT INTO testimonials (customer_name, customer_type, content, order_no, created_by) VALUES
  ('Doanh nghiệp A', 'Sản xuất linh kiện', 'HDT Logistics hỗ trợ rất sát trong khâu chứng từ, giải quyết các vướng mắc hải quan kịp thời...', 1, 'system'),
  ('Công ty B', 'Thương mại xuất nhập khẩu', 'Điểm tốt là tư vấn tuyến vận chuyển tối ưu chi phí, xử lý hàng hóa cẩn thận, chuyên nghiệp...', 2, 'system'),
  ('Doanh nghiệp C', 'FDI ngành công nghiệp', 'Chúng tôi cần đối tác logistics có khả năng cung cấp chuỗi dịch vụ liền mạch. HDT Logistics đáp ứng tốt yêu cầu này.', 3, 'system');

