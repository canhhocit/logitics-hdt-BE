-- V12: Seed thêm bài viết để trang News đủ nội dung

INSERT INTO posts (title, slug, excerpt, thumbnail, category_id, status, published_at, read_minutes, created_by) VALUES

  ('Hướng dẫn phân loại mã HS Code — Tránh sai mã dẫn đến truy thu thuế',
   'huong-dan-ma-hs-code',
   'Mã HS Code (Harmonized System Code) là nền tảng của khai báo hải quan. Sai mã HS có thể dẫn đến truy thu thuế, phạt vi phạm và chậm thông quan. Bài viết hướng dẫn cách tra mã HS chính xác và tránh các lỗi thường gặp.',
   '/images/news/news-xnk-policy.jpg',
   'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'PUBLISHED',
   CURRENT_TIMESTAMP - INTERVAL '2 days', 7, 'system'),

  ('Incoterms 2020 — Hướng dẫn chọn điều kiện giao hàng phù hợp',
   'incoterms-2020-huong-dan',
   'Incoterms 2020 gồm 11 quy tắc giao hàng quốc tế. Lựa chọn Incoterms phù hợp giúp doanh nghiệp kiểm soát chi phí vận chuyển, trách nhiệm bảo hiểm và phân chia rủi ro rõ ràng với đối tác nước ngoài.',
   '/images/news/news-export.jpg',
   'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'PUBLISHED',
   CURRENT_TIMESTAMP - INTERVAL '5 days', 9, 'system'),

  ('Vận tải đường biển LCL — Tiết kiệm chi phí cho hàng lẻ nhỏ',
   'van-tai-lcl-hang-le',
   'Đối với lô hàng dưới 15 CBM, LCL (Less than Container Load) thường là lựa chọn kinh tế hơn FCL. Tuy nhiên cần cân nhắc thời gian, rủi ro và các chi phí phát sinh khi ghép hàng.',
   '/images/news/news-import.jpg',
   'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'PUBLISHED',
   CURRENT_TIMESTAMP - INTERVAL '7 days', 6, 'system'),

  ('Hiệp định RCEP — Cơ hội và thách thức cho xuất khẩu Việt Nam 2026',
   'rcep-co-hoi-xuat-khau-2026',
   'RCEP tạo ra thị trường tự do thương mại lớn nhất thế giới với 15 quốc gia ASEAN+. Doanh nghiệp Việt Nam cần nắm rõ quy tắc xuất xứ và hồ sơ C/O để tận dụng ưu đãi thuế quan hiệu quả.',
   '/images/news/news-logistics-trend.jpg',
   'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'PUBLISHED',
   CURRENT_TIMESTAMP - INTERVAL '10 days', 8, 'system'),

  ('Kiểm tra chuyên ngành hàng nhập khẩu — Danh mục và thủ tục 2026',
   'kiem-tra-chuyen-nganh-2026',
   'Hàng hóa nhập khẩu thuộc diện kiểm tra chuyên ngành cần có kết quả kiểm tra từ cơ quan quản lý ngành trước hoặc sau thông quan. Cập nhật danh mục và thủ tục mới nhất năm 2026.',
   '/images/news/news-xnk-policy.jpg',
   'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'PUBLISHED',
   CURRENT_TIMESTAMP - INTERVAL '12 days', 7, 'system'),

  ('Giấy chứng nhận xuất xứ C/O — Các loại C/O và cách xin theo FTA',
   'co-chung-nhan-xuat-xu-fta',
   'Certificate of Origin (C/O) là chứng từ quan trọng để được hưởng thuế suất ưu đãi FTA. Mỗi FTA có mẫu C/O riêng: Form D (ASEAN), Form E (ACFTA), EUR.1 (EVFTA), Form AI (AIFTA).',
   '/images/news/news-export.jpg',
   'aaaaaaaa-aaaa-aaaa-aaaa-aaaaaaaaaaaa', 'PUBLISHED',
   CURRENT_TIMESTAMP - INTERVAL '15 days', 6, 'system'),

  ('HDT Logistics — Giải pháp vận chuyển & hải quan toàn diện cho Doanh nghiệp',
   'hdt-logistics-giai-phap-van-chuyen',
   'HDT Logistics cung cấp dịch vụ logistics tổng thể theo mô hình High Delivery – High Discipline – High Transparency: vận chuyển đa phương thức, khai thuê hải quan và tối ưu chuỗi cung ứng cho doanh nghiệp B2B.',
   '/images/news/news-logistics-trend.jpg',
   'bbbbbbbb-bbbb-bbbb-bbbb-bbbbbbbbbbbb', 'PUBLISHED',
   CURRENT_TIMESTAMP - INTERVAL '18 days', 5, 'system')

ON CONFLICT (slug) DO NOTHING;
