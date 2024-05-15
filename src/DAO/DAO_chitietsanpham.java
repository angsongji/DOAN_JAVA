package DAO;

import java.net.ConnectException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import DTO.SanPhamDTO;

import DTO.chitietsanpham_DTO;
import DTO.SanPhamDTO;

public class DAO_chitietsanpham {
	private static ConnectDataBase mySQL;
	private SanPhamDTO sanpham_DTO;
	public DAO_chitietsanpham() {
		
		try {
			mySQL = new ConnectDataBase();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private static void ConnectDataBase() {
        try {
            mySQL = new ConnectDataBase();
        } catch (SQLException ex) {
            Logger.getLogger(ChitietHD_DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

	
	public static ArrayList<String> select_size(String maSP){
		ArrayList<String> k = new ArrayList<String>();
		try {
			ConnectDataBase();
			mySQL.connect();
			
			String sql = "select MASIZE from size where MASIZE in ("
					+ "SELECT DISTINCT MASIZE FROM chitietsanpham WHERE MASP = '"+ maSP +"' AND SOLUONG !=0 )";
			
			ResultSet rs = mySQL.executeQuery(sql);
			while(rs.next()) {
				String t = rs.getString("MASIZE");
				k.add(t);
				
			}
			mySQL.disconnect();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return k;
	}
	
	public ArrayList<chitietsanpham_DTO> select_all(){
		ArrayList<chitietsanpham_DTO> ds = new ArrayList<chitietsanpham_DTO>();
		
		try {
			mySQL.connect();
			
			String sql = "select * from chitietsanpham ";
			
			ResultSet rs = mySQL.executeQuery(sql);
			
			while (rs.next()) {
				
				String masp = rs.getString("MASP");
				String masize = rs.getString("MASIZE");
				int solong = rs.getInt("SOLUONG");
				
				chitietsanpham_DTO k = new chitietsanpham_DTO(masp, masize, solong);
				ds.add(k);
			}
			mySQL.disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
	}
	
	public void add(chitietsanpham_DTO d) {
		try {
			mySQL.connect();
			
			String sql = "INSERT INTO chitietsanpham (MASP,MASIZE,SOLUONG) " 
					+ "VALUES ('" +d.getMASP() + "','" + d.getMASIZE() + "',"+d.getSoluong()+")";
			
			mySQL.executeUpdate(sql);
			mySQL.disconnect();
			
			System.out.println(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public chitietsanpham_DTO search(String MASP,String MASIZE) {
		chitietsanpham_DTO h = null;
		try {
			mySQL.connect();
			String sql = "SELECT * FROM chitietsanpham WHERE MASP = '" + MASP + "' and MASIZE = '" + MASIZE +"'";
			
			ResultSet rs = mySQL.executeQuery(sql);
			
			while (rs.next()) {
				h = new chitietsanpham_DTO(MASP, MASIZE, rs.getInt("SOLUONG"));
			}
			mySQL.disconnect();
			
			System.out.println(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return h ;
		
	}
	
	public void update(chitietsanpham_DTO d) {
		try {
			mySQL.connect();
			String sql = "update chitietsanpham set SOLUONG = SOLUONG + " + d.getSoluong() +" where MASP = '" + d.getMASP()  + "' and MASIZE = '" + d.getMASIZE() +"'";             
					
			mySQL.executeUpdate(sql);
			
			mySQL.disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void decreaseNumber(chitietsanpham_DTO d) {
		try {
			mySQL.connect();
			String sql = "update chitietsanpham set SOLUONG = SOLUONG - " + d.getSoluong() +" where MASP = '" + d.getMASP()  + "' and MASIZE = '" + d.getMASIZE() +"'";             
					
			mySQL.executeUpdate(sql);
			
			mySQL.disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	// public static void main(String[] args) {
	// 	SanPhamDTO m = new SanPhamDTO("SP8", null,null, 0, args, 0);
	// 	DAO_chitietsanpham c = new DAO_chitietsanpham();
		
	// 	System.out.println(mySQL.select_all().toString());
		
		
		
	// }
	
}
