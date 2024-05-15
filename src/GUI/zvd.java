package GUI;

import javax.swing.JFrame;

import DAO.DAO_phieunhap;
import DTO.TaiKhoanDTO;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class zvd extends JFrame{
	public zvd() {
		this.setSize(1200,800);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                StoreScreen s = new StoreScreen();
		CenterContentStore c = new CenterContentStore(800, 800, s);
                c.setBackground(Color.red);
                
		DAO_phieunhap dao_phieunhap = new DAO_phieunhap();
		TaiKhoanDTO d = new TaiKhoanDTO();
//		phieunhap_GUI phieunhap_GUI = new phieunhap_GUI(1200, 800,d);
//		panel_bang_dspn1 h = new panel_bang_dspn1(800, dao_phieunhap.select_all(), phieunhap_GUI);
		
                ImageIcon ic = new ImageIcon("./src/images/edit");
                
//		this.add(phieunhap_GUI);
		this.setVisible(true);
	}
        
	public static void main(String[] args) {
		new zvd();
	}
}
