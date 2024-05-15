
package DTO;

import java.sql.Timestamp;
import java.util.ArrayList;

public class Hoadon_DTO {
    private String maHD,maNV;
    private String ngayHD;
    private String Thoigian;
    private double  giamgia;
    private int maKH;
    private double tongTien;
    private ArrayList<ChitietHD_DTO> dsctHD;

    public Hoadon_DTO(String maHD, String ngayHD, int maKH, String maNV,  double giamgia, double tongTien, String Thoigian, ArrayList<ChitietHD_DTO> dsctHD) {
        this.maHD = maHD;
        this.maKH = maKH;
        this.maNV = maNV;
        this.ngayHD = ngayHD;
        this.giamgia = giamgia;
        this.tongTien = tongTien;
        this.Thoigian = Thoigian;
        this.dsctHD = dsctHD;
    }

    public ArrayList<ChitietHD_DTO> getDsctHD() {
        return dsctHD;
    }

    public void setDsctHD(ArrayList<ChitietHD_DTO> dsctHD) {
        this.dsctHD = dsctHD;
    }

    public String getMaHD() {
        return maHD;
    }

    public String getMaNV() {
        return maNV;
    }

    public String getNgayHD() {
        return ngayHD;
    }

    public double getGiamgia() {
        return giamgia;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setMaHD(String maHD) {
        this.maHD = maHD;
    }

    public int getMaKH() {
        return maKH;
    }

    public void setMaKH(int maKH) {
        this.maKH = maKH;
    }


    public void setMaNV(String maNV) {
        this.maNV = maNV;
    }

    public void setNgayHD(String ngayHD) {
        this.ngayHD = ngayHD;
    }

    public void setGiamgia(int giamgia) {
        this.giamgia = giamgia;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public String getThoigian() {
        return Thoigian;
    }

    public void setThoigian(String thoigian) {
        Thoigian = thoigian;
    }
    

}
