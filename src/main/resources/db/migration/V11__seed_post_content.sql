-- V11: Seed HTML content for existing posts

UPDATE posts SET content = '
<p>Năm 2026, khung pháp lý về xuất nhập khẩu tại Việt Nam tiếp tục có nhiều thay đổi quan trọng theo hướng đơn giản hóa thủ tục và tận dụng tối đa các ưu đãi từ các Hiệp định Thương mại Tự do (FTA).</p>

<h2>1. Cập nhật Biểu thuế xuất nhập khẩu ưu đãi đặc biệt (FTA)</h2>
<p>Việt Nam hiện là thành viên của 16 FTA có hiệu lực, trong đó các FTA thế hệ mới như CPTPP, EVFTA và RCEP mang lại mức thuế suất ưu đãi 0% cho phần lớn nhóm hàng xuất khẩu. Để được hưởng ưu đãi, hàng hóa phải đáp ứng quy tắc xuất xứ (Rules of Origin) và có giấy chứng nhận xuất xứ (C/O) hợp lệ.</p>

<table>
  <tbody>
    <tr><td><strong>CPTPP</strong></td><td>Nhật Bản, Canada, Mexico, Peru, Chile, Úc, New Zealand, Singapore, Malaysia, Brunei, Vietnam</td></tr>
    <tr><td><strong>EVFTA</strong></td><td>27 quốc gia thành viên EU – thuế về 0% theo lộ trình 7–10 năm</td></tr>
    <tr><td><strong>RCEP</strong></td><td>ASEAN + Trung Quốc, Nhật Bản, Hàn Quốc, Úc, New Zealand</td></tr>
    <tr><td><strong>UKVFTA</strong></td><td>Vương quốc Anh – lộ trình ưu đãi tương tự EVFTA</td></tr>
  </tbody>
</table>

<h2>2. Khai báo hải quan điện tử VNACCS/VCIS – Cập nhật 2026</h2>
<p>Hệ thống thông quan điện tử VNACCS tiếp tục được nâng cấp, tích hợp thêm tính năng khai báo tự động cho hàng chuyển phát nhanh và hàng thương mại điện tử.</p>
<ul>
  <li>Tỷ lệ luồng xanh (thông quan tự động không cần kiểm tra): &gt;73% tổng số tờ khai</li>
  <li>Luồng vàng (kiểm tra hồ sơ): khoảng 20%</li>
  <li>Luồng đỏ (kiểm tra thực tế hàng hóa): ~7%</li>
  <li>Thời gian thông quan trung bình luồng xanh: 2–4 giờ làm việc</li>
</ul>

<h2>3. Chính sách hoàn thuế xuất khẩu</h2>
<p>Doanh nghiệp sản xuất hàng xuất khẩu được hoàn thuế nhập khẩu nguyên liệu theo quy định tại Luật Thuế xuất khẩu, nhập khẩu số 107/2016/QH13. Thủ tục hoàn thuế năm 2026 được đơn giản hóa, cho phép hoàn thuế điện tử qua Cổng thông tin điện tử Hải quan.</p>

<blockquote><strong>HDT Logistics</strong> cung cấp dịch vụ tư vấn hải quan toàn diện: phân loại mã HS, xác định chính sách thuế, xin C/O theo FTA và hỗ trợ hoàn thuế xuất khẩu. Liên hệ ngay để được tư vấn miễn phí.</blockquote>
' WHERE slug = 'chinh-sach-thu-tuc-xnk-2026';

UPDATE posts SET content = '
<p>Quy trình nhập khẩu hàng hóa vào Việt Nam bao gồm nhiều bước từ ký hợp đồng ngoại thương, mở L/C, vận chuyển về cảng đến khai báo hải quan điện tử VNACCS và thông quan nhận hàng.</p>

<h2>Bước 1: Ký hợp đồng ngoại thương (Sales Contract)</h2>
<p>Hợp đồng cần xác định rõ: Incoterms, phương thức thanh toán (L/C, T/T, D/P), điều kiện giao hàng, quy cách đóng gói và chứng từ yêu cầu.</p>

<h2>Bước 2: Mở L/C hoặc thanh toán trước</h2>
<p>Đối với thanh toán L/C, doanh nghiệp làm việc với ngân hàng để mở thư tín dụng (Letter of Credit) theo đúng điều khoản hợp đồng. Thời gian xử lý thường 2–5 ngày làm việc.</p>

<h2>Bước 3: Vận chuyển hàng về cảng/sân bay</h2>
<p>Người bán (hoặc forwarder) book tàu/máy bay, cấp Bill of Lading (B/L) hoặc Airway Bill (AWB). HDT Logistics hỗ trợ theo dõi hành trình và cập nhật lịch dự kiến đến cảng (ETA).</p>

<h2>Bước 4: Chuẩn bị bộ chứng từ nhập khẩu</h2>
<ul>
  <li>Commercial Invoice (Hóa đơn thương mại)</li>
  <li>Packing List (Phiếu đóng gói)</li>
  <li>Bill of Lading hoặc Airway Bill</li>
  <li>Certificate of Origin (C/O) – nếu hưởng ưu đãi FTA</li>
  <li>Giấy phép nhập khẩu chuyên ngành (nếu có)</li>
  <li>Phiếu kiểm tra chuyên ngành (kiểm dịch, kiểm tra chất lượng...)</li>
</ul>

<h2>Bước 5: Khai báo hải quan điện tử VNACCS</h2>
<p>Chuyên viên hải quan của HDT Logistics khai tờ khai nhập khẩu trên hệ thống VNACCS/VCIS. Hệ thống tự động phân luồng (xanh/vàng/đỏ) và thông báo kết quả trong vòng 1–4 giờ.</p>

<h2>Bước 6: Nộp thuế và nhận hàng</h2>
<p>Sau khi tờ khai được thông quan (luồng xanh), doanh nghiệp nộp thuế nhập khẩu và VAT, sau đó nhận lệnh thông quan để lấy hàng tại cảng/kho CFS.</p>

<blockquote>HDT Logistics hỗ trợ toàn bộ quy trình nhập khẩu: từ booking tàu, khai báo hải quan đến giao hàng tận kho. Liên hệ hotline <strong>0929 959 699</strong> để được tư vấn.</blockquote>
' WHERE slug = 'thu-tuc-nhap-khau-hang-hoa';

UPDATE posts SET content = '
<p>Quy trình xuất khẩu hàng hóa từ Việt Nam ra nước ngoài đòi hỏi sự chuẩn bị kỹ lưỡng về chứng từ, lựa chọn Incoterms phù hợp và phối hợp chặt chẽ với đơn vị vận tải.</p>

<h2>Bước 1: Đàm phán hợp đồng và chọn Incoterms</h2>
<p>Incoterms xác định trách nhiệm vận chuyển, bảo hiểm và rủi ro giữa người bán và người mua. Phổ biến nhất trong xuất khẩu từ Việt Nam:</p>
<ul>
  <li><strong>FOB (Free on Board):</strong> Người bán chịu trách nhiệm đến khi hàng xếp lên tàu tại cảng Việt Nam</li>
  <li><strong>CIF (Cost, Insurance, Freight):</strong> Người bán trả cước và bảo hiểm đến cảng đích</li>
  <li><strong>EXW (Ex Works):</strong> Người mua chịu toàn bộ chi phí từ kho người bán</li>
  <li><strong>DDP (Delivered Duty Paid):</strong> Người bán giao hàng tận nơi, đã nộp thuế</li>
</ul>

<h2>Bước 2: Chuẩn bị bộ chứng từ xuất khẩu</h2>
<ul>
  <li>Commercial Invoice</li>
  <li>Packing List</li>
  <li>Certificate of Origin (C/O Form D, EUR.1, AANZ...)</li>
  <li>Phytosanitary Certificate hoặc Health Certificate (nếu cần)</li>
  <li>Giấy phép xuất khẩu chuyên ngành (nếu hàng hạn chế)</li>
</ul>

<h2>Bước 3: Booking tàu/máy bay và đóng hàng</h2>
<p>HDT Logistics booking trực tiếp với các hãng tàu lớn (Yang Ming, SITC, CMA CGM, ONE...) để đảm bảo giá cước cạnh tranh và lịch tàu ổn định. Container được kéo đến kho đóng hàng và kiểm tra CRO trước khi ra cảng.</p>

<h2>Bước 4: Khai báo hải quan xuất khẩu</h2>
<p>Tờ khai xuất khẩu được khai trên hệ thống VNACCS. Hàng xuất khẩu thương mại thường được phân luồng xanh (>80%), thông quan nhanh trong 2–4 giờ làm việc.</p>

<h2>Bước 5: Giao hàng và thanh toán</h2>
<p>Sau khi hàng xuất khẩu, người bán gửi bộ chứng từ gốc (B/L, Invoice, P/L, C/O...) cho người mua hoặc ngân hàng để thanh toán L/C. HDT Logistics hỗ trợ lưu trữ và gửi chứng từ cho khách hàng.</p>

<blockquote>Với kinh nghiệm xử lý hàng nghìn lô hàng xuất khẩu, HDT Logistics cam kết tỷ lệ thông quan lần đầu &gt;95% và hỗ trợ xin C/O theo tất cả các FTA Việt Nam đã ký kết.</blockquote>
' WHERE slug = 'thu-tuc-xuat-khau-hang-hoa';

UPDATE posts SET content = '
<p>Ngành logistics toàn cầu năm 2026 đang trải qua nhiều biến động lớn, từ chuỗi cung ứng dịch chuyển sang Đông Nam Á đến xu hướng số hóa và sustainability logistics.</p>

<h2>1. Cước biển phục hồi sau giai đoạn biến động</h2>
<p>Sau giai đoạn cước biển tăng đột biến do tắc nghẽn kênh đào Suez và thiếu container, thị trường vận tải biển năm 2026 đã ổn định hơn. Tuy nhiên, các tuyến châu Á–châu Âu vẫn chịu áp lực do nhu cầu tăng từ hàng điện tử và xe điện.</p>

<h2>2. Chuỗi cung ứng dịch chuyển về ASEAN</h2>
<p>Xu hướng "China+1" và "nearshoring" tiếp tục đẩy mạnh đầu tư sản xuất vào Việt Nam, Thái Lan, Indonesia và Malaysia. Điều này tạo ra cơ hội lớn cho logistics Việt Nam nhưng cũng đòi hỏi năng lực hạ tầng và nhân lực chuyên nghiệp hơn.</p>

<h2>3. Số hóa và AI trong logistics</h2>
<p>Các xu hướng công nghệ đang định hình lại ngành logistics:</p>
<ul>
  <li><strong>AI dự báo nhu cầu:</strong> Giảm tồn kho và tối ưu tuyến vận chuyển</li>
  <li><strong>Blockchain trong chứng từ:</strong> Số hóa B/L, C/O, giảm gian lận</li>
  <li><strong>IoT tracking:</strong> Theo dõi hàng hóa real-time từ kho đến tay người nhận</li>
  <li><strong>Tự động hóa kho bãi:</strong> Robot và AGV tăng năng suất 300–500%</li>
</ul>

<h2>4. Logistics xanh (Green Logistics)</h2>
<p>Áp lực từ các quy định ESG và yêu cầu của khách hàng quốc tế buộc các hãng vận tải phải chuyển đổi sang năng lượng sạch. Nhiều hãng tàu lớn (Maersk, CMA CGM) đã cam kết Net Zero trước 2050.</p>

<h2>Cơ hội cho doanh nghiệp Việt Nam</h2>
<p>Với lợi thế địa lý và hiệp định FTA, Việt Nam có cơ hội trở thành trung tâm logistics của ASEAN. Doanh nghiệp cần đầu tư vào công nghệ, nhân lực chất lượng cao và tuân thủ các tiêu chuẩn quốc tế để cạnh tranh hiệu quả.</p>

<blockquote>HDT Logistics đang tích cực đầu tư vào hệ thống tracking real-time và quy trình khai báo hải quan tự động để đáp ứng xu hướng số hóa logistics. Liên hệ chúng tôi để tìm hiểu về các giải pháp logistics phù hợp cho doanh nghiệp của bạn.</blockquote>
' WHERE slug = 'cap-nhat-van-tai-2026';
