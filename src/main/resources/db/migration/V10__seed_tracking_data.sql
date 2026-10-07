-- V10: Seed sample tracking shipments for demo/testing

INSERT INTO shipments (id, tracking_code, service_name, weight_info, origin, destination, eta_date, status, created_by) VALUES
  (
    'aaaabbbb-0001-0001-0001-aaaaaaaaaaaa',
    'HDVN2026001234',
    'Vận tải đường biển – FCL',
    '2 x 20ft Container / 28.5 tấn',
    'Cảng Hải Phòng, Việt Nam',
    'Cảng Busan, Hàn Quốc',
    '05/11/2026',
    'TRANSIT',
    'system'
  ),
  (
    'aaaabbbb-0002-0002-0002-aaaaaaaaaaaa',
    'HDVN2026005678',
    'Vận tải hàng không – Economy',
    '1 kiện / 125 kg / 0.85 m³',
    'Sân bay Nội Bài, Hà Nội',
    'Sân bay Incheon, Hàn Quốc',
    '02/11/2026',
    'DELIVERED',
    'system'
  ),
  (
    'aaaabbbb-0003-0003-0003-aaaaaaaaaaaa',
    'HDVN2026009999',
    'Vận tải đường bộ nội địa',
    '1 xe tải 15 tấn',
    'Hà Nội',
    'TP. Hồ Chí Minh',
    '03/11/2026',
    'PENDING',
    'system'
  )
ON CONFLICT (tracking_code) DO NOTHING;

-- Events cho HDVN2026001234 (đang transit)
INSERT INTO shipment_events (shipment_id, event_time, location, description, order_no, created_by) VALUES
  ('aaaabbbb-0001-0001-0001-aaaaaaaaaaaa', '25/10 08:00', 'Kho HDT, Hà Nội',          'Hàng được đóng gói và kiểm tra tại kho HDT Logistics', 1, 'system'),
  ('aaaabbbb-0001-0001-0001-aaaaaaaaaaaa', '26/10 14:30', 'Cảng Hải Phòng',            'Container được kéo ra cảng, hoàn tất thủ tục hải quan xuất khẩu', 2, 'system'),
  ('aaaabbbb-0001-0001-0001-aaaaaaaaaaaa', '27/10 22:00', 'Cảng Hải Phòng',            'Hàng xếp lên tàu Yang Ming YM Efficiency', 3, 'system'),
  ('aaaabbbb-0001-0001-0001-aaaaaaaaaaaa', '29/10 06:45', 'Biển Đông – Vị trí HK0235', 'Tàu đang trên hành trình, dự kiến cập cảng Busan 05/11', 4, 'system'),
  ('aaaabbbb-0001-0001-0001-aaaaaaaaaaaa', '01/11 10:00', 'Cảng Busan, Hàn Quốc',      'Tàu cập cảng Busan, bắt đầu dỡ hàng', 5, 'system')
ON CONFLICT DO NOTHING;

-- Events cho HDVN2026005678 (đã giao)
INSERT INTO shipment_events (shipment_id, event_time, location, description, order_no, created_by) VALUES
  ('aaaabbbb-0002-0002-0002-aaaaaaaaaaaa', '01/11 07:00', 'Kho HDT, Hà Nội',              'Hàng được đóng gói, dán nhãn AWB', 1, 'system'),
  ('aaaabbbb-0002-0002-0002-aaaaaaaaaaaa', '01/11 13:00', 'Sân bay Nội Bài, Hà Nội',      'Hàng thông quan xuất khẩu, giao cho hãng hàng không', 2, 'system'),
  ('aaaabbbb-0002-0002-0002-aaaaaaaaaaaa', '01/11 23:40', 'Đang bay – VN931',             'Khởi hành từ Nội Bài đi Incheon (thời gian bay ~4h)', 3, 'system'),
  ('aaaabbbb-0002-0002-0002-aaaaaaaaaaaa', '02/11 08:30', 'Sân bay Incheon, Hàn Quốc',   'Hàng đến Incheon, thủ tục nhập khẩu hoàn tất', 4, 'system'),
  ('aaaabbbb-0002-0002-0002-aaaaaaaaaaaa', '02/11 15:00', 'TP. Seoul, Hàn Quốc',          'Giao hàng thành công tại địa chỉ người nhận', 5, 'system')
ON CONFLICT DO NOTHING;

-- Events cho HDVN2026009999 (chờ khởi hành)
INSERT INTO shipment_events (shipment_id, event_time, location, description, order_no, created_by) VALUES
  ('aaaabbbb-0003-0003-0003-aaaaaaaaaaaa', '02/11 09:00', 'Kho HDT, Hà Nội', 'Tiếp nhận hàng, lên lịch xe tải xuất phát ngày 03/11', 1, 'system')
ON CONFLICT DO NOTHING;
