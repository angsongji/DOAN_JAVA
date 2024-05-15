package BUS;

import java.util.ArrayList;

import DAO.DAO_chitietsanpham;

import DTO.chitietsanpham_DTO;

import DTO.SanPhamDTO;

public class chitietsanpham_BUS {
	ArrayList<chitietsanpham_DTO> ds;
	public chitietsanpham_BUS() {
		newlist();
	
		
	}
	public void newlist() {
		DAO_chitietsanpham c = new DAO_chitietsanpham();
		
		ds = c.select_all();
	}
	
	public ArrayList<String> select_masize_by_MASP(SanPhamDTO sanpham_DTO){
		ArrayList<String> list_size = new ArrayList<String>();
		for (chitietsanpham_DTO h : ds) {
			if (h.getMASP().equals(sanpham_DTO.getMaSP()) && h.getSoluong()!=0) {
				list_size.add(h.getMASIZE());
			}
		}
		return list_size;
	}

	public chitietsanpham_DTO search(String MASP,String MASIZE) {
		DAO_chitietsanpham dao_chitietsanpham = new DAO_chitietsanpham();
		return dao_chitietsanpham.search(MASP, MASIZE);
	}

	public ArrayList<chitietsanpham_DTO> getlist(){
		return this.ds;
	}
	public void add( chitietsanpham_DTO d) {
		DAO_chitietsanpham c = new DAO_chitietsanpham();
		this.ds.add(d);
		c.add(d);
	}
	
	public void update(chitietsanpham_DTO d) {
		DAO_chitietsanpham c = new DAO_chitietsanpham();
		c.update(d);
		for (int i = 0; i < ds.size(); i++) {
			if (ds.get(i).getMASP().equals(d.getMASP()) && ds.get(i).getMASIZE().equals(d.getMASIZE())   ) {
				ds.get(i).setSoluong( ds.get(i).getSoluong()+d.getSoluong());
			}
		}
	}

	public void decreaseNumber(chitietsanpham_DTO d) {
		DAO_chitietsanpham dao_chitietsanpham = new DAO_chitietsanpham();
		dao_chitietsanpham.decreaseNumber(d);
	}
	
	
	public int getSoLuong(String masp, String masize) {
		int i = 0;
		for (chitietsanpham_DTO h : ds) {
			if (h.getMASP().equals(masp)  && h.getMASIZE().equals(masize) ) {
				i = h.getSoluong();
			}
		}
		return i;
	}

	// public static void main(String[] args) {
	// 	SanPhamDTO m = new SanPhamDTO("SP8", null,null, 0, args, 0);
	// 	chitietsanpham_BUS c = new chitietsanpham_BUS();
	// 	for (chitietsanpham_DTO h : c.getlist()) {
	// 		System.out.println(h.toString());
	// 	}
	// 	chitietsanpham_DTO d = new chitietsanpham_DTO("SP9", "SIZE3", 6);
	// 	c.update(d);
	// 	System.out.println();
	// 	for (chitietsanpham_DTO h : c.getlist()) {
	// 		System.out.println(h.toString());
	// 	}
	// }
		
	
	
}
