package DTO;

import java.awt.*;
import javax.swing.*;


public class SanPhamDTO {
    private String maSP, maLoai,tenSP;
    private double price;
    public String [] tenHinh;
    private int TrangThai;

    public SanPhamDTO() {
    }


    public SanPhamDTO(String maSP, String maLoai, String tenSP, double price, String[] tenHinh, int trangThai) {
        this.maSP = maSP;
        this.maLoai = maLoai;
        this.tenSP = tenSP;
        this.price = price;
        this.tenHinh = tenHinh;
        this.TrangThai = TrangThai;
    }

    public int getTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(int trangThai) {
        this.TrangThai = TrangThai;
    }
    
    public String[] getTenHinh() {
        return tenHinh;
    }

    public void setTenHinh(String[] tenHinh) {
        this.tenHinh = tenHinh;
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public String getMaLoai() {
        return maLoai;
    }

    public void setMaLoai(String maLoai) {
        this.maLoai = maLoai;
    }

    public String getTenSP() {
        return tenSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}