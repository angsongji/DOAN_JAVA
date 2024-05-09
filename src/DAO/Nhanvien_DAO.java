
package DAO;

import DTO.Nhanvien_DTO;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Nhanvien_DAO {
        public ConnectDataBase mySQL; 
    public Nhanvien_DAO() {
            try {
                mySQL = new ConnectDataBase();
            } catch (SQLException ex) {
                Logger.getLogger(Nhanvien_DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
    }
    public ArrayList<Nhanvien_DTO> list()
    {
        ArrayList<Nhanvien_DTO> dsnv = new ArrayList<>();
        try {
            mySQL.connect();
            String sql = "SELECT * FROM nhanvien WHERE 1";
            ResultSet rs = mySQL.executeQuery(sql);
            while(rs.next())
            {
                String manv = rs.getString("MANV");
                System.out.println(manv);
                String tennv = rs.getString("TENNV");
                System.out.println(tennv);
                String chucvu = rs.getString("CHUCVU");
                System.out.println(chucvu);
                int sdt = rs.getInt("SDT");System.out.println(sdt);
                System.out.println(sdt);
                String diachi = rs.getString("DIACHI");
                System.out.println(diachi);
                String email  = rs.getString("EMAIL");
                System.out.println(email);
                Nhanvien_DTO nv = new Nhanvien_DTO (manv, tennv, chucvu, sdt, diachi, email );
                dsnv.add(nv);
            }
            System.out.println("Lay danh sach nhan vien thanh cong");
            rs.close();        
           mySQL.disconnect();
        }
        catch (SQLException ex) {
            System.out.println("Lay danh sach nhan vien that bai");
        }
            return dsnv;
    }
    
    public Nhanvien_DTO showNV(String id){
    try{
        mySQL.connect();
        String sql= "SELECT * FROM nhanvien WHERE MANV='"+id+"';";
           ResultSet rs = mySQL.executeQuery(sql);
                String tennv = rs.getString("TENNV");
                String chucvu = rs.getString("CHUCVU");
                int sdt = rs.getInt("SDT");System.out.println(sdt);
                String diachi = rs.getString("DIACHI");
                String email  = rs.getString("EMAIL");
                Nhanvien_DTO nv = new Nhanvien_DTO (id, tennv, chucvu, sdt, diachi, email );
        System.out.println("Lấy thông tin nhân viên thành công!");
        return nv;
     }
        catch (SQLException ex) {
            System.out.println("Lay thông tin nhân viên that bai");
        }
            return null;
    }
    
    public boolean add(Nhanvien_DTO item) {
    boolean success = false;
            try {
                mySQL.connect();
            } catch (SQLException ex) {
                Logger.getLogger(Nhanvien_DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
    String query= "INSERT INTO nhanvien VALUES ('" + item.getManv() +"','"+ item.getTennv() +"','" +item.getChucvu() +"','" +item.getSdt() + "','" +item.getDiachi() +"','" +item.getEmail()+"');";
    boolean result = mySQL.executeUpdate(query);
    if(result) {
        System.out.println("Thêm nhân viên thành công!");
        success = true;
    } else {
        System.out.println("Thêm nhân viên thất bại!");
    }
    mySQL.disconnect();
    return success;
}

  
    public boolean update(Nhanvien_DTO item) {
    boolean success = false;
            try {
                mySQL.connect();
            } catch (SQLException ex) {
                Logger.getLogger(Nhanvien_DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
    String query= "UPDATE nhanvien set TENNV = '" + item.getTennv()+"', CHUCVU='"+ item.getChucvu()+ "', SDT='" + item.getSdt() + "', DIACHI='" + item.getDiachi()+ "', EMAIL='" + item.getEmail() + "' WHERE MANV='" + item.getManv() + "'";
    boolean result = mySQL.executeUpdate(query);
    if(result) {
        System.out.println("Cập nhật thông tin nhân viên thành công!");
        success = true;
    } else {
        System.out.println("Cập nhật thông tin nhân viên thất bại!");
    }
    mySQL.disconnect();
    return success;
}

 public boolean delete(String m) {
    boolean success = false;
            try {
                mySQL.connect();
            } catch (SQLException ex) {
                Logger.getLogger(Nhanvien_DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
    String query= "DELETE FROM hoadon WHERE MANV = '" + m +"';";
    boolean result = mySQL.executeUpdate(query);
    if(result) {
        System.out.println("Xóa nhân viên thành công!");
        success = true;
    } else {
        System.out.println("Xóa nhân viên thất bại!");
    }
    mySQL.disconnect();
    return success;
}


    public static void main (String[] args){
        Nhanvien_DAO nv = new Nhanvien_DAO();
//        Nhanvien_DTO n1 = new Nhanvien_DTO("NV005","haha","Nhân viên",987666789 ,"TP HCM","6383uyejn@gmail.com");
//        nv.add(n1);
//        Nhanvien_DTO n2 = new Nhanvien_DTO("NV005","UYEN","Nhân viên",987666789 ,"TP HCM","6383uyejn@gmail.com");
//        nv.update(n2);
        nv.delete("NV005");
        ArrayList<Nhanvien_DTO> list = nv.list();
        
    }
}
