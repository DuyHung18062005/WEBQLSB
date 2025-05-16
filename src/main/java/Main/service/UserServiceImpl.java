package Main.service;

import Main.dto.request.*;
import Main.entity.*;
import Main.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private SanRepository sanRepository;

    @Autowired
    private ChiNhanhRepository chiNhanhRepository;

    @Autowired
    private DatsanRepository datsanRepository;

    @Autowired
    private UserRepository userRepository;


    @Autowired
    private NgayRepository ngayRepository;

    @Override
    public San filterSanByTen(String tenSan) {
        try {
            return sanRepository.findByTenSan(tenSan)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy sân với tên: " + tenSan));
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi tìm sân theo tên: " + tenSan, e);
        }
    }
    @Override
    public List<SanInfoDTO> filterSanByKhuVuc(Long chiNhanhId) {
        try {
            // Lấy danh sách sân theo chi nhánh ID
            List<San> danhSachSan = sanRepository.findByChiNhanhId(chiNhanhId);

            // Chuyển đổi các sân thành SanInfoDTO
            return danhSachSan.stream()
                    .map(san -> new SanInfoDTO(
                            san.getTenSan(),
                            san.getLoaiSan(),
                            san.getChiNhanh().getTenChiNhanh()
                    ))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi lấy danh sách sân theo khu vực", e);
        }
    }


    @Override
    public List<SanInfoDTO> getAllSan() {
        try {
            // Lấy danh sách sân từ repository
            List<San> danhSachSan = sanRepository.findAll();

            // Chuyển đổi các sân thành SanInfoDTO
            return danhSachSan.stream()
                    .map(san -> new SanInfoDTO(
                            san.getTenSan(),
                            san.getLoaiSan(),
                            san.getChiNhanh().getTenChiNhanh()
                    ))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi lấy danh sách tất cả sân", e);
        }
    }

    @Override
    public List<ChiNhanhDTO> getAllChiNhanh() {
        try {
            List<Chinhanh> danhSachChiNhanh = chiNhanhRepository.findAll();
            return danhSachChiNhanh.stream()
                    .map(chiNhanh -> new ChiNhanhDTO(chiNhanh.getId(), chiNhanh.getTenChiNhanh()))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi lấy danh sách tất cả chi nhánh", e);
        }
    }

    @Override
    public List<San> filterSanByLoaiSan(String loaiSan) {
        try {
            return sanRepository.findByLoaiSan(loaiSan);
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi tìm sân theo loại sân: " + loaiSan, e);
        }
    }

    @Override
    public SanDTO getSanInfoById(Long id) {
        try {
            // Lấy sân theo ID
            San san = sanRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Không tìm thấy sân với ID: " + id));

            LocalDate homNay = LocalDate.now();
            List<KhungGioTheoNgayDTO> dsTheoNgay = new ArrayList<>();

            for (int i = 0; i < 7; i++) {
                LocalDate ngay = LocalDate.now().plusDays(i);

                List<KhungGioCoDinhDTO> khungGioDTOCuaNgay = san.getKhungGioCoDinhs().stream()
                        .map(kg -> {
                            boolean daDat = datsanRepository.findBySanAndKhungGioCoDinhAndNgay(san, kg, ngay).isPresent();
                            String trangThai = daDat ? "Đã đặt" : "Còn trống";
                            String khungGio = kg.getThoiGianBatDau() + " - " + kg.getThoiGianKetThuc();
                            return new KhungGioCoDinhDTO(khungGio, trangThai);
                        })
                        .collect(Collectors.toList());
                dsTheoNgay.add(new KhungGioTheoNgayDTO(khungGioDTOCuaNgay, ngay));
            }

            return new SanDTO(
                    san.getTenSan(),
                    san.getLoaiSan(),
                    san.getChiNhanh().getDiaChi(),
                    san.getGiaSan(),
                    dsTheoNgay
            );
        } catch (Exception e) {
            throw new RuntimeException("Lỗi khi lấy thông tin sân theo ID", e);
        }
    }

}
