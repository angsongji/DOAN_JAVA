package DAO;

import DTO.SanPhamDTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SanPhamDAO {

    private ConnectDataBase mySQL;

    public SanPhamDAO() {
        try {
            mySQL = new ConnectDataBase();
        } catch (SQLException e) {
            System.out.println("That bai");
        }
    }

    public ArrayList<SanPhamDTO> list() {
        ArrayList<SanPhamDTO> dssp = new ArrayList<>();
        try {
            mySQL.connect();
            String query = "SELECT s.MASP, s.MALOAI, s.PRICE, s.TENSP, s.TRANGTHAI, GROUP_CONCAT(h.TENHINH) AS TENHINH "
                    + "FROM SanPham s "
                    + "LEFT JOIN hinh h ON s.MASP = h.MASP "
                    + "GROUP BY s.MASP";
            ResultSet rs = mySQL.executeQuery(query);
            while (rs.next()) {
                SanPhamDTO SanPham = new SanPhamDTO();
                SanPham.setMaSP(rs.getString("MASP"));
                SanPham.setMaLoai(rs.getString("MALOAI"));
                SanPham.setPrice(rs.getDouble("PRICE"));
                SanPham.setTenSP(rs.getString("TENSP"));
                SanPham.setTrangThai(rs.getInt("TRANGTHAI"));

                // Truy cập vào mảng tenHinh chỉ khi có dữ liệu
                if (rs.getString("TENHINH") != null) {
                    String[] tenHinh = rs.getString("TENHINH").split(",");
                    SanPham.setTenHinh(tenHinh);
                } else {
                    // Xử lý trường hợp không có hình ảnh
                    SanPham.setTenHinh(new String[0]); // Gán mảng rỗng cho trường tenHinh
                }

                dssp.add(SanPham);
            }
            rs.close();
            mySQL.disconnect();

        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDTO.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("danh sách = "+dssp.size());
        return dssp;
    }

    public void add(SanPhamDTO sp) {
        try {
            mySQL.connect();
            String query = "INSERT INTO SanPham VALUES ('"
                    + sp.getMaSP() + "', '"
                    + sp.getMaLoai() + "', '"
                    + sp.getPrice() + "', '"
                    + sp.getTenSP() + "', '"
                    + sp.getTrangThai() + "')";
            mySQL.executeUpdate(query);

            for (int i = 0; i < sp.getTenHinh().length; i++) {
                query = "INSERT INTO hinh VALUES ('";
                query += sp.tenHinh[i] + "', '"
                        + sp.getMaSP() + "')";
                mySQL.executeUpdate(query);
            }
            mySQL.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void set(SanPhamDTO sp) {
        try {
            mySQL.connect();
            String query = "UPDATE SanPham SET "
                    + "MALOAI='" + sp.getMaLoai() + "', "
                    + "PRICE='" + sp.getPrice() + "', "
                    + "TENSP='" + sp.getTenSP() + "', "
                    + "TRANGTHAI='" + sp.getTrangThai() + "' "
                    + "WHERE MASP ='" + sp.getMaSP() + "'";
            System.out.println(query);
            mySQL.executeUpdate(query);
            
            //xoá các hinh cu
            for (int i = 0; i < sp.getTenHinh().length; i++) {
                query = "DELETE FROM hinh "
                    + "WHERE MASP='" + sp.getMaSP() + "'";
                mySQL.executeUpdate(query);
            }
            
            //them cac hinh mơi
            for (int i = 0; i < sp.getTenHinh().length; i++) {
                query = "INSERT INTO hinh VALUES ('";
                query += sp.tenHinh[i] + "', '"
                        + sp.getMaSP() + "')";
                mySQL.executeUpdate(query);
            }
            
            mySQL.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void delete(String maSP){
        try {
            mySQL.connect();
            String query = "UPDATE SanPham SET "
                    + "TRANGTHAI='0' "
                    + "WHERE MASP='" + maSP + "'";
            System.out.println(query);
            mySQL.executeUpdate(query);
            mySQL.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(SanPhamDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
        SanPhamDAO sp = new SanPhamDAO();
        ArrayList<SanPhamDTO> d = new ArrayList<>();
        d = sp.list();
        for (int i = 0; i < d.size(); i++) {
            SanPhamDTO SanPham = d.get(i);
            System.out.println("Mã sản phẩm: " + SanPham.getMaSP());
            System.out.println("Mã loại: " + SanPham.getMaLoai());
            System.out.println("Tên sản phẩm: " + SanPham.getTenSP());
            System.out.println("Giá: " + SanPham.getPrice());
            System.out.println("Trang thai: "+ SanPham.getTrangThai());
            String[] tenHinh = SanPham.getTenHinh();
            System.out.println("Danh sách hình ảnh:");
            for (String ten : tenHinh) {
                System.out.println(ten);
            }
            System.out.println("---------------------");
        }
        System.out.println(d.size());

//        String t[] = {"b.jpg", "p.jpg", "o.jpg"};
//        SanPhamDTO m = new SanPhamDTO("maSP2", "maloai01", "name01", 12450, t, 1);
////        sp.add(m);
////        sp.set(m);
//            sp.delete("maSP2");
    }
}
