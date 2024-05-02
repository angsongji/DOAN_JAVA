
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
                String tennv = rs.getString("TENNV");
                String chucvu = rs.getString("CHUCVU");
                int sdt = rs.getInt("SDT");System.out.println(sdt);
                String diachi = rs.getString("DIACHI");
                String email  = rs.getString("EMAIL");
                Nhanvien_DTO nv = new Nhanvien_DTO (manv, tennv, chucvu, sdt, diachi, email );
                dsnv.add(nv);
            }
            System.out.println("Lay danh sach chuc nang thanh cong");
            rs.close();        
           mySQL.disconnect();
        }
        catch (SQLException ex) {
            System.out.println("Lay danh sach chuc nang that bai");
        }
        return dsnv;   
     
    }
    
    
    public void add(Nhanvien_DTO item) {
            try {
                mySQL.connect();
                String query= "INSERT INTO nhanvien VALUES ('" + item.getManv() +"','"+ item.getTennv() +"','" +item.getChucvu() +"','" +item.getSdt() + "','" +item.getDiachi() +"','" +item.getEmail()+");";
                mySQL.executeUpdate(query);
                System.out.println("Them nhan vien thanh cong!");
                mySQL.disconnect();
            } catch (SQLException ex) {
                Logger.getLogger(Nhanvien_DAO.class.getName()).log(Level.SEVERE, null, ex);
            }
      
    }
    public static void main (String[] args){
        Nhanvien_DAO nv = new Nhanvien_DAO();
        ArrayList<Nhanvien_DTO> list = nv.list();
    }
}