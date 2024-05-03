package DAO;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.chitietsanphamDTO;
import DTO.SanPhamDTO;

public class chitietsanpham_DAO {
	private ConnectDataBase c;
	private SanPhamDTO SanPhamDTO;
	public chitietsanpham_DAO(SanPhamDTO h) {
		this.SanPhamDTO = h;
		try {
			c = new ConnectDataBase();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public ArrayList<String> select_size(SanPhamDTO h){
		ArrayList<String> k = new ArrayList<String>();
		try {
			c.connect();
			
			String sql = "select TENSIZE from size where MASIZE in ("
					+ "SELECT DISTINCT MASIZE FROM chitietsanpham WHERE MASP = '"+ h .getMaSP() +"' )";
			
			ResultSet rs = c.executeQuery(sql);
			while(rs.next()) {
				String t = rs.getString("TENSIZE");
				k.add(t);
				
			}
			c.disconnect();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return k;
	}
	
	public ArrayList<chitietsanphamDTO> select_all(SanPhamDTO h){
		ArrayList<chitietsanphamDTO> ds = new ArrayList<chitietsanphamDTO>();
		
		try {
			c.connect();
			
			String sql = "select * from chitietsanpham where MASP = '" + h.getMaSP() +"'";
			
			ResultSet rs = c.executeQuery(sql);
			
			while (rs.next()) {
				
				String maSP = rs.getString("MASP");
				String masize = rs.getString("MASIZE");
				int solong = rs.getInt("SOLUONG");
				
				chitietsanphamDTO k = new chitietsanphamDTO(maSP, masize, solong);
				ds.add(k);
			}
			c.disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
	}
	public static void main(String[] args) {
		SanPhamDTO m = new SanPhamDTO();
		chitietsanpham_DAO c = new chitietsanpham_DAO(m);
		
		for(String t : c.select_size(m)) {
			System.out.println(t);
		}
		
		
	}
	
}
