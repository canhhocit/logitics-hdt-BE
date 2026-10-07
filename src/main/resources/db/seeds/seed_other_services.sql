UPDATE logistics_services
SET content_blocks = '{
  "advantages": [
    "Chi phí thấp nhất cho vận chuyển quốc tế khối lượng lớn",
    "Không giới hạn trọng lượng và kích thước hàng hóa",
    "Kết nối với hơn 100 cảng biển quốc tế trên toàn cầu",
    "Lịch tàu đa dạng từ nhiều hãng tàu lớn"
  ],
  "risks": [
    "Thời gian vận chuyển dài (từ 10 - 45 ngày tùy tuyến)",
    "Dễ chịu ảnh hưởng bởi thời tiết và điều kiện hàng hải"
  ],
  "methods": [
    { "title": "FCL (Hàng nguyên container)", "desc": "Bao nguyên container 20ft, 40ft, 40HQ" },
    { "title": "LCL (Hàng lẻ)", "desc": "Gom chung container với các lô hàng khác" },
    { "title": "Bulk (Hàng rời)", "desc": "Vận chuyển ngũ cốc, than, quặng" }
  ],
  "process_steps": [
    "Tư vấn lịch tàu và booking",
    "Lấy vỏ container và đóng hàng",
    "Hạ bãi và khai báo hải quan xuất khẩu",
    "Phát hành vận đơn đường biển (B/L)",
    "Hàng lên tàu và cập nhật lịch trình",
    "Cập cảng đích và giao hàng"
  ],
  "documents": [
    "Hóa đơn thương mại (Commercial Invoice)",
    "Phiếu đóng gói (Packing List)",
    "Vận đơn đường biển (Bill of Lading)",
    "Tờ khai hải quan"
  ]
}'::jsonb
WHERE slug = 'van-tai-duong-bien';

UPDATE logistics_services
SET content_blocks = '{
  "advantages": [
    "Giao nhận linh hoạt tận nơi (Door-to-door)",
    "Mạng lưới rộng khắp 63 tỉnh thành và xuyên biên giới",
    "Đội xe đa dạng tải trọng từ 1 tấn đến 40 tấn",
    "Theo dõi định vị GPS 24/7"
  ],
  "risks": [
    "Chịu tác động bởi tắc đường và quy định giao thông",
    "Giới hạn tải trọng cầu đường ở một số tuyến"
  ],
  "methods": [
    { "title": "FTL (Nguyên chuyến)", "desc": "Bao nguyên xe tải cho lô hàng riêng biệt" },
    { "title": "LTL (Ghép chuyến)", "desc": "Ghép chung với hàng khác để tiết kiệm chi phí" },
    { "title": "Cross-border", "desc": "Vận tải xuyên biên giới Lào, Campuchia, Trung Quốc" }
  ],
  "process_steps": [
    "Khảo sát hàng hóa và báo giá",
    "Điều phối phương tiện phù hợp",
    "Nhận hàng và ký biên bản giao nhận",
    "Vận chuyển và theo dõi lộ trình GPS",
    "Giao hàng và thu hồi chứng từ (POD)"
  ],
  "documents": [
    "Biên bản giao nhận hàng hóa",
    "Phiếu xuất kho",
    "Hóa đơn VAT (bản sao)"
  ]
}'::jsonb
WHERE slug = 'van-tai-duong-bo';

UPDATE logistics_services
SET content_blocks = '{
  "advantages": [
    "Tỷ lệ thông quan lần đầu luôn đạt trên 95%",
    "Thời gian thông quan nhanh chóng trong vòng 24h",
    "Đội ngũ chuyên gia am hiểu chính sách mặt hàng",
    "Tối ưu chi phí thuế hợp pháp bằng FTA"
  ],
  "risks": [
    "Chính sách hải quan thay đổi thường xuyên",
    "Sai lệch mã HS dẫn đến phạt và truy thu thuế"
  ],
  "methods": [
    { "title": "Khai báo VNACCS", "desc": "Truyền tờ khai hải quan điện tử" },
    { "title": "Xin giấy phép", "desc": "Xin giấy phép xuất nhập khẩu chuyên ngành" },
    { "title": "Tham vấn giá", "desc": "Hỗ trợ tham vấn giá trị tính thuế" }
  ],
  "process_steps": [
    "Tiếp nhận và kiểm tra tính hợp lệ của bộ chứng từ",
    "Tư vấn áp mã HS và chính sách thuế",
    "Truyền tờ khai nháp để khách hàng duyệt",
    "Truyền tờ khai chính thức lên hệ thống VNACCS",
    "Xử lý luồng tờ khai (Xanh, Vàng, Đỏ) tại chi cục hải quan",
    "Thông quan và lấy hàng"
  ],
  "documents": [
    "Hợp đồng ngoại thương (Sales Contract)",
    "Hóa đơn thương mại (Commercial Invoice)",
    "Phiếu đóng gói (Packing List)",
    "Giấy chứng nhận xuất xứ (C/O)"
  ]
}'::jsonb
WHERE slug = 'khai-bao-hai-quan';

UPDATE logistics_services
SET content_blocks = '{
  "advantages": [
    "Tiết kiệm chi phí tối đa cho lô hàng nhỏ (dưới 15 CBM)",
    "Lịch đóng console hàng tuần cố định",
    "Mạng lưới đại lý toàn cầu xử lý hàng chuyên nghiệp",
    "Giá cước minh bạch, không phụ phí ẩn"
  ],
  "risks": [
    "Thời gian vận chuyển dài hơn hàng FCL do phải chờ gom hàng",
    "Rủi ro thất lạc hoặc hỏng hóc cao hơn nếu đóng gói không kỹ"
  ],
  "methods": [
    { "title": "Gom hàng lẻ xuất khẩu", "desc": "Từ Việt Nam đi các cảng lớn trên thế giới" },
    { "title": "Gom hàng lẻ nhập khẩu", "desc": "Từ các nước về kho CFS Việt Nam" }
  ],
  "process_steps": [
    "Booking và nhận Lệnh cấp vỏ/Lệnh giao hàng",
    "Khách hàng giao hàng đến kho CFS",
    "Đo đạc kích thước thực tế (CBM) và cân trọng lượng",
    "Đóng hàng vào container chung",
    "Vận chuyển đường biển",
    "Khai thác hàng tại kho CFS đích và giao cho người nhận"
  ],
  "documents": [
    "Hóa đơn thương mại (Commercial Invoice)",
    "Phiếu đóng gói (Packing List)",
    "Vận đơn gom hàng (House Bill of Lading)"
  ]
}'::jsonb
WHERE slug = 'van-chuyen-lcl';

UPDATE logistics_services
SET content_blocks = '{
  "advantages": [
    "Năng lực xử lý hàng hóa siêu trường, siêu trọng",
    "Đội ngũ kỹ sư khảo sát tuyến đường và lập phương án an toàn",
    "Phương tiện chuyên dụng đặc chủng (Mooc lùn, Rơ mooc thủy lực)",
    "Mua bảo hiểm hàng hóa giá trị cao"
  ],
  "risks": [
    "Yêu cầu kỹ thuật cao, rủi ro lớn về an toàn",
    "Cần xin giấy phép lưu hành đặc biệt mất thời gian",
    "Chi phí logistics rất cao"
  ],
  "methods": [
    { "title": "Hàng dự án công nghiệp", "desc": "Nhà máy điện, thiết bị năng lượng mặt trời" },
    { "title": "Hàng OOG", "desc": "Hàng quá khổ quá tải (Out of Gauge)" },
    { "title": "Hàng nguy hiểm (DG)", "desc": "Hóa chất, vật liệu dễ cháy nổ" }
  ],
  "process_steps": [
    "Khảo sát hàng hóa thực tế và đo đạc kỹ thuật",
    "Khảo sát tuyến đường (cầu đường, dây điện, góc cua)",
    "Lập phương án vận chuyển và xin giấy phép",
    "Điều động phương tiện chuyên dụng",
    "Chằng buộc (Lashing) và gia cố an toàn",
    "Vận chuyển dưới sự hộ tống",
    "Bốc dỡ và lắp đặt tại điểm đích"
  ],
  "documents": [
    "Bản vẽ kỹ thuật hàng hóa",
    "Giấy phép lưu hành xe siêu trường siêu trọng",
    "Chứng nhận MSDS (cho hàng nguy hiểm)",
    "Biên bản lashing chằng buộc"
  ]
}'::jsonb
WHERE slug = 'hang-dac-biet';
