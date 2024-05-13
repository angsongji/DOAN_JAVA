/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.Hoadon_DTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Hoadon_DAO {
    private  ConnectDataBase mySQL  ;
    public Hoadon_DAO() throws SQLException{
        mySQL = new ConnectDataBase();
   }
    
    public ArrayList<Hoadon_DTO> listchucnang()
    {
        ArrayList<Hoadon_DTO> dshd = new ArrayList<>();
        try {
            mySQL.connect();
            String sql = "SELECT * FROM hoadon WHERE 1";
            try (ResultSet rs = mySQL.executeQuery(sql)) {
                while(rs.next())
                {
                    String maHD = rs.getString("SOHD");
                    String ngayHD = rs.getDate("NGAYHD").toString();
                    System.out.println(ngayHD);
                    String thoigian = rs.getTime("THOIGIAN").toString();
                    System.out.println(thoigian);
                    int maKH = rs.getInt("MAKH");
                    String maNV = rs.getString("MANV");
                    int giamgia = rs.getInt("TIENGIAMGIA");
                    int tongtien = rs.getInt("TONGTIEN");
                    
                    Hoadon_DTO hd = new Hoadon_DTO(maHD, ngayHD,thoigian, maKH, maNV,giamgia, tongtien);
                    dshd.add(hd);
                }
            }
             System.out.println("Lay danh sach hoa don thanh cong");
             mySQL.disconnect();
        } catch (SQLException ex) {
            System.out.println("Lay danh sach hoa don that bai");
        }
        
        return dshd;
    }
    
    
    // xóa hóa đơn
    public boolean delete(String m) throws SQLException {
    boolean success = false;
    mySQL.connect();
    String query= "DELETE FROM hoadon WHERE SOHD = '" + m +"';";
    boolean result = mySQL.executeupdate(query);
    if(result) {
        if (result)
        System.out.println("Xoa hoa don thanh cong!");
        success = true;
    } else {
        System.out.println("Xoa hoa don that bai!");
    }
    mySQL.disconnect();
    return success;
}
   
     
    
     
     public static void main (String[] args) throws SQLException{
        Hoadon_DAO hd = new Hoadon_DAO();
        ArrayList<Hoadon_DTO> a = hd.listchucnang();
    }
}
