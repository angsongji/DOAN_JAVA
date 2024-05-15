package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.concurrent.Flow;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.Border;


import BUS.chitietphieunhap_BUS;
import BUS.nhacungcapBUS;
import BUS.phieunhap_BUS;
import DAO.DAO_phieunhap;
import DTO.TaiKhoanDTO;
import DTO.chitietphieunhap_DTO;
import DTO.nhacungcapDTO;
import DTO.phieunhap_DTO;

public class phieunhap_GUI extends JPanel implements MouseListener{
	private JPanel[] jp,jp1,jp3;
	private JLabel[] jl,jl1,jl3,jlha;
	private JTextField[] jt;
	private JPanel panel_north,panel_dspn;
	private JLabel submit,refresh;
	private String [] option_so,option_kitu,thaotacc,images;
	private JTextField[] jt_tien,jtngay;
	private JComboBox<String> option_mapn,option_manv,option_ngaytruoc,option_ngaysau,option_tongtien,option_mancc;
	private Border border_ttac;
	private panel_bang_dspn1 panel_bang_dspn;
	private boolean clickedchinhsua,clickedxoa;
	private chitietphieunhap_GUI chitietphieunhap_GUI;
	private phieunhap_BUS phieunhap_BUS;
	private chitietphieunhap_BUS chitietphieunhap_BUS;
	private frame_them_phieunhap frame_them_phieunhap;
	private panel_them_phieunhap panel_them_phieunhap;
	private nhacungcapBUS nhacungcapBUS;
	private TaiKhoanDTO taiKhoanDTO;
	private boolean frame_them;
	
	public phieunhap_GUI(int w,int h,TaiKhoanDTO d) {
		jp1 = new JPanel[7];
		jp = new JPanel[7];
		jl = new JLabel[5];
		jt = new JTextField[6];
		jl1 = new JLabel[6];
		jt_tien = new JTextField[2];
		 option_kitu = new String[] {"","a-z","z-a"};
		 option_so = new String[] {"", "min-max" , "max-min"};
		 thaotacc = new String[] {"Thêm","Sửa","Xóa","Export Excel"};
		 images = new String[] {"./src/images/add_icon.png","./src/images/edit_icon.png","./src/images/remove_icon.png","./src/images/import_icon.png"};
		 this.clickedchinhsua =false;
		 this.clickedxoa = false;
		 this.taiKhoanDTO = d;
		 this.frame_them = false;
                 
		 phieunhap_BUS = new phieunhap_BUS();
		 nhacungcapBUS = new nhacungcapBUS();
		 
		 jtngay = new  JTextField[2];
		 
		 
		 int o = (w-100)/8;
		 
		 
		jp3 = new JPanel[5];
		jl3= new JLabel[5];
		jlha = new JLabel[5];
		
		
		
		
		
		
		 border_ttac = BorderFactory.createMatteBorder(2, 0, 0, 0, Color.decode("#60A3BC"));
		
		panel_north = new JPanel();
		panel_north.setLayout(new FlowLayout());
		panel_north.setPreferredSize(new Dimension(w,260));
		
		
		/////////// dòng tìm kiếm ////////////////
		jp[0] = new JPanel();jp[0].setPreferredSize(new Dimension(w-100,30));
		jl[0] = new JLabel("TÌM KIẾM",JLabel.LEFT);
		jp[0].setLayout(new FlowLayout(0,10,0));
		jp[0].add(jl[0]);
		
		
		
		////////////////  các option tìm kiếm //////////////
		
		jp[1] = new JPanel(); jp[1].setPreferredSize(new Dimension(w-100,80));
		jp[1].setBackground(Color.white);
		jp[1].setLayout(new FlowLayout(0,0,0));
		jp[1].setBorder(BorderFactory.createCompoundBorder(getBorder(), BorderFactory.createMatteBorder(0, 3, 0, 0,Color.decode("#60A3BC"))));
		
		
		String[] thaotac = {"Mã phiếu nhập" , "Mã Nhân Viên", "Ngày nhập", "Tổng tiền","MANCC"};
		
		
		/////////// tìm kiếm theo mã phiếu nhập //////////////
		
		jl1[0] = new JLabel("Mã phiếu nhập",JLabel.CENTER);
		jl1[0].setPreferredSize(new Dimension((w-100)/8,40));
		jl1[0].setBorder(BorderFactory.createEmptyBorder(0, 0, 22, 0));
                
		
		
		option_mapn = new JComboBox<String>(option_kitu);
		option_mapn.setEditable(true);
		option_mapn.setPreferredSize(new Dimension(o-o/5,20));
		
		
		jp1[0] = new JPanel();jp1[0].setPreferredSize(new Dimension(o,80));
		jp1[0].add(jl1[0]);
		jp1[0].add(option_mapn);
                
		
		/////////// tìm kiếm theo mã nhân viên /////////////
		
		
		jl1[1] = new JLabel("Mã Nhân Viên",JLabel.CENTER);
		jl1[1].setPreferredSize(new Dimension((w-100)/8,40));
		jl1[1].setBorder(BorderFactory.createEmptyBorder(0,0,22,0));
             
		
		option_manv = new JComboBox<String>(option_kitu);
		option_manv.setEditable(true);
		option_manv.setPreferredSize(new Dimension(o - o/5,20));
		
		jp1[1] = new JPanel();jp1[1].setPreferredSize(new Dimension((w-100)/8,80));
		jp1[1].add(jl1[1]);
		jp1[1].add(option_manv);
		
                
		////////// tìm kiếm theo ngày nhập///////////////
		
		
		jl1[2] = new JLabel("Ngày nhập hàng",JLabel.CENTER);
		jl1[2].setPreferredSize(new Dimension(2*o,40));
		jl1[2].setBorder(BorderFactory.createEmptyBorder(0, 0, 22, 0));
		
		jtngay[0] = new JTextField("YYYY-MM-DD");
		jtngay[0].setForeground(Color.GRAY);
		jtngay[0].setPreferredSize(new Dimension(o-o/5,20));
		jtngay[0].addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if (jtngay[0].getText().equals("") ) {
					jtngay[0].setText("YYYY-MM-DD");
					jtngay[0].setForeground(Color.GRAY);
				}
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				if (jtngay[0].getText().equals("YYYY-MM-DD")  ) {
					jtngay[0].setText("");
					jtngay[0].setForeground(Color.black);
				} 
				
			}
		});
		
		
		jtngay[1] = new JTextField(LocalDate.now()+"");
		jtngay[1].setForeground(Color.GRAY);
		jtngay[1].setPreferredSize(new Dimension(o - o/5,20));
		jtngay[1].addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if (jtngay[1].getText().equals("") ) {
					jtngay[1].setText(LocalDate.now()+"");
					jtngay[1].setForeground(Color.GRAY);
				}
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				if (jtngay[1].getText().equals(LocalDate.now()+"")  ) {
					jtngay[1].setText("");
					jtngay[1].setForeground(Color.black);
				} 
				
			}
		});
		
		
		JLabel den = new JLabel("đến",JLabel.CENTER);
		den.setPreferredSize(new Dimension(30,20));
		
		jp1[2] = new JPanel();jp1[2].setPreferredSize(new Dimension(2*o,80));
		jp1[2].add(jl1[2]);
		jp1[2].add(jtngay[0]);
		jp1[2].add(den);
		jp1[2].add(jtngay[1]);
		
		
		
		//////// tìm kiếm theo tổng tiền ///////////////////
		
		
		jl1[3] = new JLabel("Tổng tiền nhập hàng",JLabel.CENTER);
		jl1[3].setPreferredSize(new Dimension(2*o,40));
		jl1[3].setBorder(BorderFactory.createEmptyBorder(0, 0, 22, 0));
		
		jt_tien[0] = new JTextField("0");
		jt_tien[0].setPreferredSize(new Dimension(o-o/5,22));
		jt_tien[0].addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if (jt_tien[0].getText().equals("")) {
					jt_tien[0].setText("0");
				}
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				if (jt_tien[0].getText().equals("0")) {
					jt_tien[0].setText("");
				}
				
			}
		});
		
		
		jt_tien[0].addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE )){
					e.consume();
				}
			}
		});
		
		
		
		jt_tien[1] = new JTextField("10000000000000");
		jt_tien[1].setPreferredSize(new Dimension(o-o/5,22));
		
		jt_tien[1].addFocusListener(new FocusListener() {
			
			@Override
			public void focusLost(FocusEvent e) {
				if (jt_tien[1].getText().equals("")) {
					jt_tien[1].setText("0");
				}
				
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				if (jt_tien[1].getText().equals("0")) {
					jt_tien[1].setText("");
				}
				
			}
		});
		
		jt_tien[1].addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char c = e.getKeyChar();
				if(!(Character.isDigit(c) || c == KeyEvent.VK_BACK_SPACE )){
					e.consume();
				}
			}
		});
		
		JLabel denn = new JLabel("đến");
		
		jp1[3] = new JPanel();jp1[3].setPreferredSize(new Dimension(2*o,80));
		jp1[3].add(jl1[3]); 
		jp1[3].add(jt_tien[0]);
		jp1[3].add(denn);
		jp1[3].add(jt_tien[1]);
               
		
		///////////// tìm kiếm theo mã nhà cung cấp //////////
		
		
		ArrayList<String> ds_ncc = new ArrayList<String>();
		ds_ncc.add("");
		for (nhacungcapDTO hh : nhacungcapBUS.getList()) {
			ds_ncc.add(hh.getMANCC());
		}
		String[] option_MANCC = {""};
		 option_MANCC = ds_ncc.toArray(new String[1]);
		
		
		jl1[4]= new JLabel("Mã Nhà cung cấp",JLabel.CENTER);
		jl1[4].setPreferredSize(new Dimension(o,40));
		jl1[4].setBorder(BorderFactory.createEmptyBorder(0, 0, 22, 0));
		
		
		option_mancc = new JComboBox<String>(option_MANCC);
		option_mancc.setPreferredSize(new Dimension(o-o/5,20));
//		option_mancc.setEditable(true);
		
		jp1[4] = new JPanel();jp1[4].setPreferredSize(new Dimension(o,80));
		
		
		jp1[4].add(jl1[4]);
		jp1[4].add(option_mancc);
		
	
		
		/////////////// SUBMIT va REFRESH /////////////////
		
		submit = new JLabel("SUBMIT",JLabel.CENTER);submit.setForeground(Color.white);
		submit.setPreferredSize(new Dimension(o-o/5,25));
		
		submit.setBackground(Color.decode("#0A3D62"));submit.setOpaque(true);
		submit.addMouseListener(this);
		
		refresh = new JLabel("REFRESH", JLabel.CENTER);refresh.setForeground(Color.white);
		refresh.setPreferredSize(new Dimension(o-o/5,25));
		
		refresh.setBackground(Color.decode("#0A3D62")); refresh.setOpaque(true);
		refresh.addMouseListener(this);
		
		jp1[5] = new JPanel();jp1[5].setPreferredSize(new Dimension(o,80));
		jp1[5].setLayout(new FlowLayout(1,0,10));
		jp1[5].add(submit);
		jp1[5].add(refresh);
		
		for (int i = 0; i < 6; i++) {
			jp1[i].setBackground(Color.white);
		}
		
		jp[1].add(jp1[0]);
		jp[1].add(jp1[1]);
		jp[1].add(jp1[2]);
		jp[1].add(jp1[3]);
		jp[1].add(jp1[4]);
		jp[1].add(jp1[5]);
		
		
		
		
	///////////////////////////////////////////////////////////// dòng thao tác ///////////////////////////////////////////////////////////////////////
		
		jp[2] = new  JPanel();
		jp[2].setPreferredSize(new Dimension(w,20));
		jp[2].setLayout(new FlowLayout(0,20,0));
		
		jl[2] = new JLabel("THAO TÁC",JLabel.LEFT);
		jp[2].add(jl[2]);
		
		
	//////////////// các thao tác thêm sửa xóa ///////////////
		jp[3] = new JPanel();
		jp[3].setPreferredSize(new Dimension(w,100));
		jp[3].setLayout(new FlowLayout(0,10,0));
		jp[3].setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));
		
		for (int i = 0; i < 4; i++) {
			ImageIcon ttac = new ImageIcon(images[i]);
			
			jp3[i] = new JPanel();jp3[i].setPreferredSize(new Dimension(100,100));jp3[i].setLayout(new FlowLayout());
			jp3[i].setBorder(border_ttac);
			
			jl3[i] = new JLabel(thaotacc[i],JLabel.CENTER);jl3[i].setPreferredSize(new Dimension(100,50));
			jl3[i].addMouseListener(this);
			
			jlha[i] = new JLabel();jlha[i].setIcon(ttac); 
			jlha[i].addMouseListener(this);
			
			jp3[i].setBackground(Color.white);
			jp3[i].addMouseListener(this);
			jp3[i].add(jl3[i]);
			jp3[i].add(jlha[i]);
			
			
		}
		
		
		
		
		
		////////////////////////////////////////////////////////////// add các thao tác theo mã quyền ///////////////////////////////////////////////////
         
                ArrayList<String> dsss= this.phieunhap_BUS.hanhdong_phieunhap(d.getMaQuyen());
                for (String t : dsss) {
			System.out.println(t);
			
			switch (t) {
			case "Thêm":
				jp[3].add(jp3[0]);
				break;
			case "Sửa" :
				jp[3].add(jp3[1]);
				break;
			case "Xóa" :
				jp[3].add(jp3[2]);
				break;
                                
                        case "Export Excel"   : 
                            jp[3].add(jp3[3]);
                            break;
			default:
				break;
			}
		}
		
		

		
		/////////////////// DANH SÁCH PHIẾU NHẬP //////////////////
		
		
		
		
		
		
		
		
		
		
		this.panel_bang_dspn = new panel_bang_dspn1(w, phieunhap_BUS.dsPN(), this);
		
		jp[4] = new JPanel();
		jp[4].setLayout(new BorderLayout());
		jp[4].add(this.panel_bang_dspn);
		
		
		
		
		panel_north.add(jp[0]);
		panel_north.add(jp[1]);
		panel_north.add(jp[2]);
		panel_north.add(jp[3]);
		
		JPanel jpp = new JPanel();
		jpp.setPreferredSize(new Dimension(800,400));
		jpp.setBackground(Color.red);
		
		this.setPreferredSize(new Dimension(w,h));
		this.setLayout(new BorderLayout());
		
		this.add(this.panel_north,BorderLayout.NORTH);
		this.add(jp[4],BorderLayout.CENTER);

		
		jp[5] = new JPanel();
		jp[5].setPreferredSize(new Dimension(400,20));
		jp[5].setLayout(new BorderLayout());
		jp[5].setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
	}
	
	
	public void show_chitietphieunhap(phieunhap_DTO phieunhap_DTO) {
		
		
		jp[5].removeAll();
		jp[4].removeAll();
		int t = this.getPreferredSize().width;
		this.panel_bang_dspn = new panel_bang_dspn1(t-500, phieunhap_BUS.dsPN(), this);
		jp[4].add(this.panel_bang_dspn);
//		
		this.chitietphieunhap_GUI = new chitietphieunhap_GUI(500, 300,phieunhap_DTO ,this);
		
		jp[5].add(chitietphieunhap_GUI);
		jp[5].setPreferredSize(new Dimension(550,300));
		
		
		this.add(jp[5],BorderLayout.EAST);	
		this.repaint();
		this.revalidate();
		
		
	}
	
	public void show_chitietphieunhap_chinhsua(phieunhap_DTO phieunhap_DTO) {
		jp[5].removeAll();
		jp[4].removeAll();
		int t = this.getPreferredSize().width;
		this.panel_bang_dspn = new panel_bang_dspn1(t-500, phieunhap_BUS.dsPN(), this);
		jp[4].add(this.panel_bang_dspn);
		
		this.chitietphieunhap_GUI = new chitietphieunhap_GUI(500, 300,phieunhap_DTO ,this);
		this.chitietphieunhap_GUI.che_do_sua();
		jp[5].add(chitietphieunhap_GUI);
		jp[5].setPreferredSize(new Dimension(550,300));
		
		
		this.add(jp[5],BorderLayout.EAST);	
		this.repaint();
		this.revalidate();
	}
	
	public frame_them_phieunhap Frame_them_phieunhap() {
		return this.frame_them_phieunhap;
	}
	
	public void dinh_dang() {
		if (clickedchinhsua) {
			jp3[1].setBackground(Color.decode("#356a7e"));
			jl3[1].setText("Lưu");
			jlha[1].setIcon(new ImageIcon("./src/images/save.png"));
		} else if (!clickedchinhsua) {
//			jp3[1].setBorder(border_thaotac);
			jp3[1].setBackground(Color.white);
			
			jl3[1].setText("Sửa");
			jlha[1].setIcon(new ImageIcon(images[1]));
		}
		if (clickedxoa) {
			
			jp3[2].setBackground(Color.decode("#356a7e"));
			jl3[2].setText("Xác nhận xóa");
			
		} else if (!clickedxoa) {
			
			jp3[2].setBackground(Color.white);
			jl3[2].setText("Xóa");
		}
	}
	
	
	public frame_them_phieunhap return_frame_them_phieunhap() {
		return this.frame_them_phieunhap;
	}
	
	
	public void update_gia_thap_hon() {
		this.frame_them_phieunhap.update_gia_thap_hon();
	}
	
	public boolean clicked_sua() {
		return this.clickedchinhsua;
	}
	

	public boolean so_sanh() {
		
		if (this.chitietphieunhap_GUI == null) {
			return true;
		} else {
			return this.chitietphieunhap_GUI.so_sanh();
		}
		
		
	}
//////////////////////////////////////////////////////// sử lí update /////////////////////////////////////////////////////////////////////	
	public void cap_nhap_tongtien() {
		this.chitietphieunhap_GUI.set_tongtien();
	}
	
	
	public void update_ctpn_sau_chinh_sua(){
			this.chitietphieunhap_GUI.update_ctpn_sau_chinh_sua();
	}
	
	
	public void update_ctsp_sau_chinh_sua() {
		this.chitietphieunhap_GUI.update_ctsp_sau_chinh_sua();
	}
	
	
	public frame_thong_bao_phieunhap thong_bao_update_thongtin(String t) {
		return new frame_thong_bao_phieunhap(t, this);
	}
	
	public void update_phieunhap() {
		this.chitietphieunhap_GUI.update_phieunhap();
	}
	
	public String thong_bao_doi_gia() {
		return this.chitietphieunhap_GUI.thong_bao_thay_doi_gia();
	}
	
	///////////////////////////////// sử lí xóa //////////////////////////////////////////////////////
	
	public void xoa_pn() {
		for (phieunhap_DTO h : this.panel_bang_dspn.ds_chon_xoa()) {
			phieunhap_BUS.delete(h);
		}
	}
	
	
	
	////////////////////////////// refresh ///////////////////////////////////
	
	public void Refresh_moi() {
		this.remove(jp[5]);
		jp[4].removeAll();
		jp[4].setLayout(new BorderLayout());
		this.panel_bang_dspn = new panel_bang_dspn1(this.getPreferredSize().width, phieunhap_BUS.dsPN(), this);
		jp[4].add(this.panel_bang_dspn);
		jp[4].repaint();
		jp[4].revalidate();
	}
	
	public void refresh_giu_ctpn() {
		
		int i = jp[4].getPreferredSize().width;
		jp[4].removeAll();
		jp[4].setLayout(new BorderLayout());
		this.panel_bang_dspn = new panel_bang_dspn1(i, phieunhap_BUS.dsPN(), this);
		jp[4].add(panel_bang_dspn);
		jp[4].repaint();
		jp[4].revalidate();
		if (this.chitietphieunhap_GUI != null) {
			this.chitietphieunhap_GUI.che_do_xem();
		}
		
	}
		
	///////////////////////////////////// tương tác các nút //////////////////////////////////////////////
	
	public boolean clickedxoa() {
		return this.clickedxoa;
	}
	
	public void return_false_clicksua() {
		this.clickedchinhsua = false;
		
	}
	
	public void return_false_clickedxoa() {
		this.clickedxoa = false;
	}
	
	public void return_true_clicked_xoa() {
		this.clickedxoa = true;
	}
	
	public void return_true_clicked_sua() {
		this.clickedchinhsua = true;
	}
	
	
	public void return_false_framethem(){
            this.frame_them = false;
        }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		///////////////////////// nút thêm phiếu nhập ///////////////////////////////////
		if (e.getSource() == jp3[0] || e.getSource() == jl3[0] || e.getSource() == jlha[0]) {
                   
                       
                       this.frame_them_phieunhap = new frame_them_phieunhap(800, 500, this,this.taiKhoanDTO);
                  
                   
                        
                   
			
		}
		//////////////////////////////// nút chỉnh sửa ////////////////////////////////////////////
		
		if (e.getSource() == jp3[1] || e.getSource() == jl3[1] || e.getSource() == jlha[1]) {
			if (clickedchinhsua == false) {
				
				if (clickedxoa) {
					String t = "Thoát chế độ xóa và hủy các thao tác";
					frame_thong_bao_phieunhap f = new frame_thong_bao_phieunhap(t, this);
					
				} else if (!clickedxoa) {
					clickedchinhsua = true;
					dinh_dang();
					JOptionPane.showMessageDialog(this, "Chọn phiếu nhập bạn muốn chỉnh sửa");
				}
				
				
				
					
			} else if (clickedchinhsua == true) {
				
				if (this.chitietphieunhap_GUI != null) {
					if (!this.chitietphieunhap_GUI.so_sanh()) {
						String t = "Hủy bỏ các thay đổi";
						frame_thong_bao_phieunhap f = new frame_thong_bao_phieunhap(t, this);
					} else {
						clickedchinhsua = false;
						this.refresh_giu_ctpn();
						dinh_dang();
						
					}
				} else {
					clickedchinhsua = false;
					dinh_dang();
				}
				
				
					
					
				
				
			}	
			
		}
		///////////////////////////////////  nút xóa ////////////////////////////////////////////
		if (e.getSource() == jp3[2] || e.getSource() == jl3[2] || e.getSource() == jlha[2]) {
				
				if (!clickedxoa) {
					if (clickedchinhsua) {
						String t = "Thoát trạng thái sửa và bắt đầu xóa";
						frame_thong_bao_phieunhap f = new frame_thong_bao_phieunhap(t, this);
					}
					else if (!clickedchinhsua) {
						JOptionPane.showMessageDialog(this, "Click vào phiếu nhập bạn muốn xóa");
						clickedxoa = true;
						this.dinh_dang();
					}
					
					
				} else if (clickedxoa) {
					if (this.panel_bang_dspn.ds_chon_xoa().isEmpty()) {
						clickedxoa = false;
						dinh_dang();
					} else {
						String t = "Xác nhận xóa ?";
						frame_thong_bao_phieunhap f = new frame_thong_bao_phieunhap(t, this);
					}
					
					
					
					
				}
			
		}
		
		if (e.getSource() == submit) {
			if (clickedchinhsua || clickedxoa) {
				String t = "Hủy bỏ các hoạt động và tiếp tục tìm kiếm";
				frame_thong_bao_phieunhap f = new frame_thong_bao_phieunhap(t, this);
			} else {
				
				int i = jp[4].getPreferredSize().width;
				jp[4].removeAll();
				jp[4].setLayout(new BorderLayout());
				String MAPN = (String) option_mapn.getSelectedItem();
				String MANV = (String) option_manv.getSelectedItem();
				double giabe = Double.parseDouble(jt_tien[0].getText()) ;
				double gialon = Double.parseDouble(jt_tien[1].getText()) ;
				String mancc = (String) option_mancc.getSelectedItem();
				String ngaydau = "0000-00-00";
				String ngaysau = "9999-12-30";
				
				try {
					LocalDate.parse(jtngay[0].getText());
					LocalDate.parse(jtngay[1].getText());
					ngaydau = jtngay[0].getText();
					ngaysau = jtngay[1].getText();
					} catch (Exception e2) {
					// TODO: handle exception
				}
				
				
				ArrayList<phieunhap_DTO> ds = phieunhap_BUS.search(MAPN, MANV, ngaydau, ngaysau, giabe, gialon, mancc);
				this.panel_bang_dspn = new panel_bang_dspn1(i, ds, this);
				
				
				jp[4].add(this.panel_bang_dspn);
				jp[4].repaint();
				jp[4].revalidate();
			}
			
			
		}
		
		if (e.getSource() == refresh) {
			if (clickedchinhsua || clickedxoa) {
				String t = "Làm mới bảng danh sách và hủy bỏ tất cả hoạt dộng";
				frame_thong_bao_phieunhap f = new frame_thong_bao_phieunhap(t, this);
			} else {
				this.Refresh_moi();
			}
			
			
		}
			
			
		
	}


	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void mouseEntered(MouseEvent e) {
		
		if(e.getSource() == jlha[0] || e.getSource() == jl3[0] || e.getSource() == jp3[0] ) {
			jp3[0].setBorder(BorderFactory.createLineBorder(Color.decode("#60A3BC"),2));
		}
		if(e.getSource() == jlha[1] || e.getSource() == jl3[1] || e.getSource() == jp3[1]) {
			if(!clickedchinhsua) {
				jp3[1].setBorder(BorderFactory.createLineBorder(Color.decode("#60A3BC"),2));
			}
		}
		if(e.getSource() == jlha[2] || e.getSource() == jl3[2] || e.getSource() == jp3[2] ) {
			jp3[2].setBorder(BorderFactory.createLineBorder(Color.decode("#60A3BC"),2));
		}
                if(e.getSource() == jlha[3] || e.getSource() == jl3[3] || e.getSource() == jp3[3] ) {
			jp3[3].setBorder(BorderFactory.createLineBorder(Color.decode("#60A3BC"),2));
		}
		if(e.getSource() == submit){
                    submit.setBackground(Color.decode("#60A3BC")); submit.setOpaque(true);
                }
                if (e.getSource() == refresh){
                    refresh.setBackground(Color.decode("#60A3BC"));refresh.setOpaque(true);
                }
	}


	@Override
	public void mouseExited(MouseEvent e) {
		
		if (e.getSource() == jp3[0] || e.getSource() == jl3[0] || e.getSource() == jlha[0]) {
			jp3[0].setBorder(border_ttac);
		}
		if (e.getSource() == jp3[1] || e.getSource() == jl3[1] || e.getSource() == jlha[1]) {
			jp3[1].setBorder(border_ttac);
		}
		if (e.getSource() == jp3[2] || e.getSource() == jl3[2] || e.getSource() == jlha[2]) {

			jp3[2].setBorder(border_ttac);
		}
                if (e.getSource() == jp3[3] || e.getSource() == jl3[3] || e.getSource() == jlha[3]) {

			jp3[3].setBorder(border_ttac);
		}
                if (e.getSource() == submit){
                    submit.setBackground(Color.decode("#0A3D62")); submit.setOpaque(true);
                }
                if (e.getSource() == refresh){
                    refresh.setBackground(Color.decode("#60A3BC"));refresh.setOpaque(true);
                }
	}
}
