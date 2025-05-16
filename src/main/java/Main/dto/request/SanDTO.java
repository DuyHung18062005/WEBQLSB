package Main.dto.request;

import Main.dto.request.KhungGioCoDinhDTO;

import java.util.List;

public class SanDTO {
    private String tenSan;
    private String loaiSan;
    private String diaChi;
    private double giaSan;
    private List<KhungGioTheoNgayDTO> khungGio7Ngay;

    // Constructor tương ứng các tham số
    public SanDTO(String tenSan,
                  String loaiSan,
                  String diaChi,
                  double giaSan,
                  List<KhungGioTheoNgayDTO> khungGio7Ngay) {
        this.tenSan = tenSan;
        this.loaiSan = loaiSan;
        this.diaChi = diaChi;
        this.giaSan = giaSan;
        this.khungGio7Ngay = khungGio7Ngay;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    public double getGiaSan() {
        return giaSan;
    }

    public void setGiaSan(double giaSan) {
        this.giaSan = giaSan;
    }

    public List<KhungGioTheoNgayDTO> getKhungGio7Ngay() {
        return khungGio7Ngay;
    }

    public void setKhungGio7Ngay(List<KhungGioTheoNgayDTO> khungGio7Ngay) {
        this.khungGio7Ngay = khungGio7Ngay;
    }

    public String getLoaiSan() {
        return loaiSan;
    }

    public void setLoaiSan(String loaiSan) {
        this.loaiSan = loaiSan;
    }

    public String getTenSan() {
        return tenSan;
    }

    public void setTenSan(String tenSan) {
        this.tenSan = tenSan;
    }
}