-- V18: Đồng bộ thông tin đăng ký doanh nghiệp & định danh pháp lý chính thức HDT Logistics
-- Căn cứ thông tin chính thức:
-- Tên công ty: CÔNG TY CỔ PHẦN HDT LOGISTICS
-- Mã số thuế / MSDN: 0111638260
-- Địa chỉ trụ sở / Địa chỉ thuế: Số 46/9/11 đường An Thọ, Xã An Khánh, TP Hà Nội, Việt Nam
-- Tình trạng: Đang hoạt động
-- Tên quốc tế: HDT LOGISTICS JOINT STOCK COMPANY
-- Tên viết tắt: HDT LOGISTICS., JSC
-- Người đại diện pháp luật: TRẦN VĂN TOẢN
-- Hotline: 0911 188 726 (raw: 0911188726)

INSERT INTO site_settings (key, value, description, created_by) VALUES
  ('company_full_name',    'CÔNG TY CỔ PHẦN HDT LOGISTICS',                                        'Tên đầy đủ công ty', 'system'),
  ('company_short_name',   'HDT LOGISTICS., JSC',                                                   'Tên viết tắt công ty', 'system'),
  ('company_int_name',     'HDT LOGISTICS JOINT STOCK COMPANY',                                    'Tên giao dịch quốc tế', 'system'),
  ('tax_code',             '0111638260',                                                            'Mã số thuế doanh nghiệp', 'system'),
  ('company_address',      'Số 46/9/11 đường An Thọ, Xã An Khánh, TP Hà Nội, Việt Nam',             'Địa chỉ trụ sở / Địa chỉ thuế', 'system'),
  ('representative',       'TRẦN VĂN TOẢN',                                                         'Người đại diện pháp luật', 'system'),
  ('business_status',      'Đang hoạt động',                                                        'Tình trạng hoạt động', 'system'),
  ('hotline',              '0911 188 726',                                                          'Hotline hiển thị trên website', 'system'),
  ('hotline_raw',          '0911188726',                                                            'Hotline raw cho tel: link', 'system'),
  ('hotline_contact',      '0911 188 726',                                                          'Số liên hệ phụ', 'system'),
  ('hotline_contact_raw',  '0911188726',                                                            'Số liên hệ phụ raw', 'system'),
  ('license_org',          'Sở Kế hoạch và Đầu tư TP Hà Nội',                                       'Cơ quan cấp phép', 'system'),
  ('license_date',         '08/04/2024',                                                            'Ngày cấp phép', 'system')
ON CONFLICT (key) DO UPDATE SET value = EXCLUDED.value, description = EXCLUDED.description, updated_at = NOW();

-- Cập nhật thông tin chi nhánh Hà Nội
UPDATE offices 
SET address = 'Số 46/9/11 đường An Thọ, Xã An Khánh, TP Hà Nội', phone = '0911 188 726' 
WHERE name = 'Chi nhánh Hà Nội';

-- Cập nhật số điện thoại người đại diện trong danh bạ liên hệ nội bộ nếu có
UPDATE contact_persons 
SET phone = '0911 188 726' 
WHERE full_name ILIKE '%Trần Văn Toản%';

