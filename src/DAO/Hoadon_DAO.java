/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import DTO.ChitietHD_DTO;
import DTO.Hoadon_DTO;
import DTO.chitietsanpham_DTO;
import DTO.model_qlkh;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import BUS.ChitietHD_BUS;

public class Hoadon_DAO {
    private  static ConnectDataBase mySQL  ;
    public Hoadon_DAO(){
        try {
            mySQL = new ConnectDataBase();
        } catch (SQLException ex) {
            Logger.getLogger(Hoadon_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
   }

   private static void ConnectDataBase() {
    try {
        mySQL = new ConnectDataBase();
    } catch (SQLException ex) {
        Logger.getLogger(ChitietHD_DAO.class.getName()).log(Level.SEVERE, null, ex);
    }
}
    
    public static ArrayList<Hoadon_DTO> list()
    {
        ArrayList<Hoadon_DTO> dshd = new ArrayList<Hoadon_DTO>();
        try {
            ConnectDataBase();
            mySQL.connect();
            String sql = "SELECT * FROM hoadon WHERE 1";
            try (ResultSet rs = mySQL.executeQuery(sql)) {
                while(rs.next())
                {
                    String maHD = rs.getString("SOHD");
                    String ngayHD = rs.getTimestamp("NGAYHD").toString();
                    int maKH = rs.getInt("MAKH");
                    String maNV = rs.getString("MANV");
                    int giamgia = rs.getInt("TIENGIAMGIA");
                    int tongtien = rs.getInt("TONGTIEN");
                    String Thoigian = rs.getTimestamp("THOIGIAN").toString();
                    
                    Hoadon_DTO hd = new Hoadon_DTO(maHD, ngayHD, maKH, maNV, giamgia, tongtien, Thoigian, ChitietHD_DAO.list(maHD));
                    dshd.add(hd);
                }
            }
             mySQL.disconnect();
        } catch (SQLException ex) {
        }
        
        return dshd;
    }

    public static int getNumberOfRow() {
        int rowCount = 0;
        try {
            ConnectDataBase();
            mySQL.connect();
            String query = "SELECT COUNT(*) AS row_count FROM hoadon;";
            ResultSet resultSet = mySQL.executeQuery(query);

            // Lấy kết quả từ ResultSet
            if (resultSet.next()) {
                rowCount = resultSet.getInt("row_count");
            }
            mySQL.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(Hoadon_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowCount;
    }
    public static int getpointtichluy(int a) {
        int rowCount = 0;
        try {
            ConnectDataBase();
            mySQL.connect();
            String query = "SELECT DIEMTICHLUY FROM khachhang WHERE MAKH ="+ a;
            ResultSet resultSet = mySQL.executeQuery(query);

            // Lấy kết quả từ ResultSet
            if (resultSet.next()) {
                rowCount = resultSet.getInt("row_count");
            }
          
            mySQL.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(Hoadon_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rowCount ;
    }
    
    public static boolean add(Hoadon_DTO item ){
        boolean result = false;
        try {
            ConnectDataBase();
            mySQL.connect();
            String query= "INSERT INTO `hoadon`(`SOHD`, `NGAYHD`, `MAKH`, `MANV`, `TONGTIEN`, `TIENGIAMGIA`,`THOIGIAN`) VALUES ('" + item.getMaHD() +"','"+ item.getNgayHD() +"','" +item.getMaKH() +"','" +item.getMaNV() +"','"  + (double)item.getTongTien() +"','"+ (double)item.getGiamgia()+"','"+item.getThoigian()+"');";
            for (ChitietHD_DTO ctHD : item.getDsctHD()) {
                DAO_chitietsanpham dao_chitietsanpham = new DAO_chitietsanpham();
                chitietsanpham_DTO ctsp = dao_chitietsanpham.search(ctHD.getMaSP(), ctHD.getMaSize());
                ctsp.setSoluong(ctHD.getSl());
                dao_chitietsanpham.decreaseNumber(ctsp);
                ChitietHD_DAO.addCTHD(ctHD);
            }
            result = mySQL.executeupdate(query);
            mySQL.disconnect();
        } catch (SQLException ex) {
            Logger.getLogger(Hoadon_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
     
    
     
    public static void main (String[] args){
       Hoadon_DAO hd = new Hoadon_DAO();
       hd.getpointtichluy(3);
   }
}
