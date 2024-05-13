
package DTO;

public class ChitietHD_DTO {
    private String  maSP, tenSP, maSize ;
    private int sl;
    private double gia, tt;
    public ChitietHD_DTO() {
    }

    public ChitietHD_DTO(String maSP, String tenSP, String maSize, int sl, double gia, double tt) {
        this.maSP = maSP;
        this.tenSP = tenSP;
        this.maSize = maSize;
        this.sl = sl;
        this.gia = gia;
        this.tt = tt;
    }

    public ChitietHD_DTO(String maHD, String maSP, String tenSP, String masize, int soluong, double price, double totalPrice) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String getMaSP() {
        return maSP;
    }

    public void setMaSP(String maSP) {
        this.maSP = maSP;
    }

    public void setTenSP(String tenSP) {
        this.tenSP = tenSP;
    }

    public void setMaSize(String maSize) {
        this.maSize = maSize;
    }

    public void setSl(int sl) {
        this.sl = sl;
    }

    public void setGia(double gia) {
        this.gia = gia;
    }

    public void setTt(double tt) {
        this.tt = tt;
    }

    public String getTenSP() {
        return tenSP;
    }

    public String getMaSize() {
        return maSize;
    }

    public int getSl() {
        return sl;
    }

    public double getGia() {
        return gia;
    }

    public double getTt() {
        return tt;
    }

    
}
