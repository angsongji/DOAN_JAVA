package BUS;

import java.util.ArrayList;

import DAO.chitietsanpham_DAO;
import DTO.chitietsanphamDTO;
import DTO.SanPhamDTO;

public class chitietsanphamBUS {
	ArrayList<chitietsanphamDTO> ds;
	public chitietsanphamBUS(SanPhamDTO h) {
		newlist(h);
	
		
	}
	public void newlist(SanPhamDTO h) {
		chitietsanpham_DAO c = new chitietsanpham_DAO(h);
		
		ds = c.select_all(h);
	}
	
	public ArrayList<String> select_masize(SanPhamDTO SanPhamDTO){
		ArrayList<String> t = new ArrayList<String>();
		chitietsanpham_DAO c = new chitietsanpham_DAO(SanPhamDTO);
		
		t = c.select_size(SanPhamDTO);
		return t;
	}
	public static void main(String[] args) {
		SanPhamDTO m = new SanPhamDTO();
		chitietsanphamBUS c = new chitietsanphamBUS(m);
		for (String t : c.select_masize(m)) {
			System.out.println(t);
		}
	}
}
