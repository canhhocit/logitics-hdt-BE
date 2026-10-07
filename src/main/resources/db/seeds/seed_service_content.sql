UPDATE logistics_services
SET content_blocks = '{
  "advantages": [
    "Thời gian vận chuyển siêu tốc chỉ từ 1-3 ngày toàn cầu",
    "Lịch trình bay cố định hàng ngày, không bị trễ chuyến",
    "Đảm bảo an toàn tuyệt đối cho hàng hóa giá trị cao",
    "Thủ tục hải quan sân bay nhanh chóng ưu tiên"
  ],
  "risks": [
    "Cước phí cao hơn đáng kể so với vận tải đường biển",
    "Hạn chế về kích thước và trọng lượng hàng hóa",
    "Nhiều quy định nghiêm ngặt về hàng nguy hiểm (DG)"
  ],
  "methods": [
    { "title": "Express (Chuyển phát nhanh)", "desc": "Dành cho tài liệu, bưu kiện khẩn cấp" },
    { "title": "Air Freight (Vận tải hàng không thường)", "desc": "Cho lô hàng lớn hơn 45kg" },
    { "title": "Air Charter (Thuê chuyến)", "desc": "Thuê toàn bộ máy bay cho hàng dự án" }
  ],
  "process_steps": [
    "Tiếp nhận yêu cầu booking và tư vấn chuyến bay",
    "Lấy hàng tại kho khách hàng (Pick up)",
    "Thực hiện khai báo hải quan xuất khẩu",
    "Phát hành Không vận đơn (AWB)",
    "Hàng lên máy bay và theo dõi lộ trình (Tracking)",
    "Khai hải quan nhập khẩu và giao tận nơi (Door delivery)"
  ],
  "documents": [
    "Hóa đơn thương mại (Commercial Invoice)",
    "Phiếu đóng gói (Packing List)",
    "Giấy chứng nhận xuất xứ (C/O)",
    "Không vận đơn (Air Waybill)",
    "Tờ khai hải quan xuất nhập khẩu"
  ]
}'::jsonb
WHERE slug = 'van-tai-hang-khong';
