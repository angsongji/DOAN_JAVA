package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import DTO.SanPhamDTO;

import DTO.chitietsanpham_DTO;
import DTO.SanPhamDTO;

public class DAO_chitietsanpham {
	private ConnectDataBase c;
	private SanPhamDTO sanpham_DTO;
	public DAO_chitietsanpham() {
		
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
	
	public ArrayList<chitietsanpham_DTO> select_all(){
		ArrayList<chitietsanpham_DTO> ds = new ArrayList<chitietsanpham_DTO>();
		
		try {
			c.connect();
			
			String sql = "select * from chitietsanpham ";
			
			ResultSet rs = c.executeQuery(sql);
			
			while (rs.next()) {
				
				String masp = rs.getString("MASP");
				String masize = rs.getString("MASIZE");
				int solong = rs.getInt("SOLUONG");
				
				chitietsanpham_DTO k = new chitietsanpham_DTO(masp, masize, solong);
				ds.add(k);
			}
			c.disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ds;
	}
	
	public void add(chitietsanpham_DTO d) {
		try {
			c.connect();
			
			String sql = "INSERT INTO chitietsanpham (MASP,MASIZE,SOLUONG) " 
					+ "VALUES ('" +d.getMASP() + "','" + d.getMASIZE() + "',"+d.getSoluong()+")";
			
			c.executeUpdate(sql);
			c.disconnect();
			
			System.out.println(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public chitietsanpham_DTO search(String MASP,String MASIZE) {
		chitietsanpham_DTO h = null;
		try {
			c.connect();
			String sql = "SELECT * FROM chitietsanpham WHERE MASP = '" + MASP + "' and MASIZE = '" + MASIZE +"'";
			
			ResultSet rs = c.executeQuery(sql);
			
			while (rs.next()) {
				h = new chitietsanpham_DTO(MASP, MASIZE, rs.getInt("SOLUONG"));
			}
			c.disconnect();
			
			System.out.println(sql);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return h ;
		
	}
	
	public void update(chitietsanpham_DTO d) {
		try {
			c.connect();
			String sql = "update chitietsanpham set SOLUONG = SOLUONG + " + d.getSoluong() +" where MASP = '" + d.getMASP()  + "' and MASIZE = '" + d.getMASIZE() +"'";             
					
			c.executeUpdate(sql);
			
			c.disconnect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
   
   public void Restore_pro (chitietsanpham_DTO cp){
        c.connect();
        String query= "UPDATE chitietsanpham set SOLUONG = '" + cp.getSoluong() + "' WHERE MASP='" + cp.getMASP() + "' AND MASIZE='" + cp.getMASIZE()+"';";
        boolean result = c.executeUpdate(query);
        if(result) {
            System.out.println("Phục hồi số lượng sản phẩm sau hủy hóa đơn thành công!");
        } else {
            System.out.println("Phục hồi số lượng sản phẩm sau hủy hóa đơn thất bại!");
        }
        c.disconnect();    
      }
    
	public static void main(String[] args) {
		SanPhamDTO m = new SanPhamDTO("SP8", null,null, 0, args, 0);
		DAO_chitietsanpham c = new DAO_chitietsanpham();
		
		System.out.println(c.select_all().toString());
		
		
		
	}
	
}
