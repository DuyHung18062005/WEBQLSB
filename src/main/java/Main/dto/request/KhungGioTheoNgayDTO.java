package Main.dto.request;

import java.time.LocalDate;
import java.util.List;

public class KhungGioTheoNgayDTO {
    private LocalDate ngay;
    private List<KhungGioCoDinhDTO> khungGioList;

    public KhungGioTheoNgayDTO(List<KhungGioCoDinhDTO> khungGioList, LocalDate ngay) {
        this.khungGioList = khungGioList;
        this.ngay = ngay;
    }

    public List<KhungGioCoDinhDTO> getKhungGioList() {
        return khungGioList;
    }

    public void setKhungGioList(List<KhungGioCoDinhDTO> khungGioList) {
        this.khungGioList = khungGioList;
    }

    public LocalDate getNgay() {
        return ngay;
    }

    public void setNgay(LocalDate ngay) {
        this.ngay = ngay;
    }
}
