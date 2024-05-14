package GUI;

import DTO.TaiKhoanDTO;
import javax.swing.JFrame;

public class frame_them_phieunhap  extends JFrame{
	private panel_them_phieunhap c;
	 public frame_them_phieunhap(int w,int h,phieunhap_GUI phieunhap_GUI,TaiKhoanDTO taiKhoanDTO ) {
		 this.setSize(w,h);
		 this.setLocationRelativeTo(null);
		 this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		 
		 
		 this.c = new panel_them_phieunhap(w,h,phieunhap_GUI,taiKhoanDTO);
		 this.add(c);
		 
		 
		 
		 this.setVisible(true);
//                 this.setUndecorated(true);
	 }
	 public void them_chitietphieunhap() {
		 this.c.them_chitietphieunhap();
	 }
	 
	 
	 public void add_phieunhap() {
		 this.c.them_phieunhap();	
	 }
	 
	 
//	 public void update_price() {
//		 this.c.update_price();
//	 }
	 
	 public void update_chitietsanpham() {
		 this.c.update_chitietsanpham();
	 }
	 
	 public void update_gia_thap_hon() {
		 this.c.update_gia_thap_hon();
	 }
	 
	 public panel_them_phieunhap panel_them_phieunhap() {
		 return this.c;
	 }
         
         public static void main(String[] args) {
             System.out.println("GUI.frame_them_phieunhap.main()");
    }
}
