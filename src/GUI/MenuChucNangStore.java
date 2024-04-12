package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.ArrayList;
import java.awt.Cursor;

import javax.swing.JScrollPane;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.GridBagConstraints;
import java.awt.Component;

import DAO.ConnectDataBase;
import java.sql.*;
import BUS.chucnangBUS;
import DTO.chucnangDTO;
public class MenuChucNangStore extends JPanel implements MouseListener{
    private StoreScreen main;
    private JLabel nameStaff;
    private ArrayList<chucnangDTO> listChucnang;
    private ArrayList<String> iconChucnangStore;
    private Font font_chucnang;
    private JPanel inforMenu;
    private JPanel listChucnangMenu;
    private int chieurong,chieucao;
    private int selectedItem;
    
 
    public MenuChucNangStore(int chieurong,int chieucao,StoreScreen s){
        main=s;
        
        this.chieurong=(int)chieurong/7;
        this.chieucao=chieucao;
        selectedItem=-1;
        
        init();
        
    }

    public void init(){
        setPreferredSize(new Dimension(chieurong, 0));
        setLayout(new BorderLayout());
        setBackground(Cacthuoctinh_phuongthuc_chung.darkness_blue);
        setOpaque(true);

        inforMenu= new JPanel();
        inforMenu.setLayout(new FlowLayout(1,0,15));
        inforMenu.setBackground(Cacthuoctinh_phuongthuc_chung.darkness_blue);
        inforMenu.setOpaque(true);
        inforMenu.setPreferredSize(new Dimension(chieurong, chieurong+chieurong/3));
        JLabel logoStore= new JLabel(Cacthuoctinh_phuongthuc_chung.logoNVbanhang);
        logoStore.setPreferredSize(new Dimension(chieurong,chieurong));
        logoStore.setBackground(Cacthuoctinh_phuongthuc_chung.darkness_blue);
        logoStore.setOpaque(true);
        inforMenu.add(logoStore);


        
        nameStaff= new JLabel("<html>ABCre asdá ư</html>");
        nameStaff.setFont(Cacthuoctinh_phuongthuc_chung.font_header);
        nameStaff.setForeground(Cacthuoctinh_phuongthuc_chung.light_gray);
        inforMenu.add(nameStaff);
        
       

       
        
        
        add(inforMenu,BorderLayout.NORTH);

        
        chucnangBUS chucnangBus= new chucnangBUS();
        listChucnang= chucnangBus.getList();
       
        iconChucnangStore =  new ArrayList<>();
        iconChucnangStore.add("./src/images/user_icon.png");
        iconChucnangStore.add("./src/images/shirt_icon.png");
        iconChucnangStore.add("./src/images/account_icon.png");
        iconChucnangStore.add("./src/images/customer_icon.png");
        iconChucnangStore.add("./src/images/product_icon.png");
        iconChucnangStore.add("./src/images/bill_icon.png");
        iconChucnangStore.add("./src/images/bill_icon.png");
        iconChucnangStore.add("./src/images/bill_icon.png");
        iconChucnangStore.add("./src/images/bill_icon.png");
        iconChucnangStore.add("./src/images/signout_icon.png");
       
        if(listChucnang.size()<9){
            listChucnangMenu = new JPanel(new FlowLayout());

        }else{
            listChucnangMenu = new JPanel(new GridLayout(0,1));
        }
        listChucnangMenu.setBackground(Cacthuoctinh_phuongthuc_chung.darkness_blue);
        listChucnangMenu.setOpaque(true);
     
        // Tạo một JList chứa danh sách
        JScrollPane scrollPane = new JScrollPane( listChucnangMenu);
        scrollPane.setBorder(null);
        
       for(int i=0;i<listChucnang.size();i++){
            JPanel chucnang;
            String nameIcon="./src/images/";
            switch (listChucnang.get(i).getTENCHUCNANG()){
                case "Tài khoản cá nhân":
                    nameIcon+="user_icon.png";
                    break;
                case "Sản phẩm":
                    nameIcon+="shirt_icon.png";
                    break;
                case "Giỏ hàng":
                    nameIcon+="cart_icon.png";
                    break;
                case "Lịch sử hóa đơn":
                    nameIcon+="bill_icon.png";
                    break;
                case "Nhà cung cấp":
                    nameIcon+="product_icon.png";
                    break;
                case "Quản lý nhân viên":
                    nameIcon+="staff_icon.png";
                    break;
                case "Quản lý tài khoản":
                    nameIcon+="account_icon.png";
                    break;
                case "Quản lý khách hàng":
                    nameIcon+="customer_icon.png";
                    break;
                case "Quản lí phiếu nhập":
                    nameIcon+="receipt_icon.png";
                    break;
                case "Phân Quyền":
                    nameIcon+="phanquyen_icon.png";
                    break;
                case "Đăng xuất":
                    nameIcon+="signout_icon.png";
                    break;
            }
            if(listChucnang.size()<9){
                listChucnangMenu.setPreferredSize(new Dimension(chieurong,40));
                chucnang = new chucnangGUI(listChucnang.get(i).getTENCHUCNANG(),nameIcon,(int)listChucnangMenu.getPreferredSize().getWidth(), (int)listChucnangMenu.getPreferredSize().getHeight());
            }else{
                chucnang = new chucnangGUI(listChucnang.get(i).getTENCHUCNANG(),nameIcon,(int)scrollPane.getPreferredSize().getWidth(),40);
            }
            
            chucnang.addMouseListener(this);
           
            listChucnangMenu.add(chucnang);
           
        }
       // JScrollPane scrollListChucnang= new JScrollPane(listChucnangMenu);
        //scrollListChucnang.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

         // Luôn hiển thị thanh cuộn dọc
        //add(scrollListChucnang);
        
        add(scrollPane,BorderLayout.CENTER);
        JPanel south= new JPanel();
        south.setPreferredSize(new Dimension(chieurong,20));
        south.setBackground(Cacthuoctinh_phuongthuc_chung.darkness_blue);
        south.setOpaque(true);
        add(south,BorderLayout.SOUTH);
        
       // showThongtintaikhoan(main.pageContent);
    }
    public void changeColorJPanelChildFromParent(JPanel parent,int index,Color bg,Color fg){
        Component[] components = parent.getComponents();
        for(int i=0;i<components.length;i++){
            JPanel p= (JPanel) components[i];
            if(i==index) changeColorJPanelChild(p, bg, fg);
        }
    }
    public void changeColorJPanelChild( JPanel p,Color bg,Color fg){
        p.setBackground(bg);
        Component[] components = p.getComponents();
        for (Component component : components) {
            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                    try{
                        label.setForeground(fg);

                    }catch (NullPointerException t) {
                        // Xử lý logic khi JLabel có thể null
                        t.printStackTrace(); // In ra thông tin về lỗi (tùy chọn)
                    }
            }
        }
        MouseListener[] mouseListeners = p.getMouseListeners();
                                for (MouseListener listener : mouseListeners) {
                                    p.removeMouseListener(listener);
                                }
        p.addMouseListener(this);
        
    
      
    }
    public int findSelectedItem(JPanel find,JPanel parent){
        Component[] components = parent.getComponents();
        for(int i=0;i<components.length;i++){
            if(components[i]==find) return i; 
        }
        return 0;
    }
    public void showThongtintaikhoan(JPanel wrap){
        int crong=(int)wrap.getPreferredSize().getWidth();
        int ccao=(int)wrap.getPreferredSize().getHeight();
        wrap.removeAll();
        wrap.add(new ThongTinTaiKhoan("Nguyễn Hoàng Thanh Phương","sinh viên","thanhphuong2004","","","15-03-2024", crong, ccao,main.pageContent.getBackground()));
        wrap.revalidate(); // Cập nhật lại cấu trúc của JPanel
        wrap.repaint();

    }
    public void showTrangsanpham(JPanel wrap){
        int crong=(int)wrap.getPreferredSize().getWidth();
        int ccao=(int)wrap.getPreferredSize().getHeight();
        wrap.removeAll();
        TrangSanPham t = new TrangSanPham(crong, 600,main.pageContent.getBackground());
        JScrollPane scrollPane = new JScrollPane(t);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        wrap.add(scrollPane);
        wrap.revalidate(); // Cập nhật lại cấu trúc của JPanel
        wrap.repaint();
    }
    public void showTrangtaikhoan(JPanel wrap){
        int crong=(int)wrap.getPreferredSize().getWidth();
        int ccao=(int)wrap.getPreferredSize().getHeight();
        wrap.removeAll();
        TrangTaiKhoan t = new TrangTaiKhoan(crong,600);
        JScrollPane scrollPane = new JScrollPane(t);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        wrap.add(scrollPane);
        wrap.revalidate(); // Cập nhật lại cấu trúc của JPanel
        wrap.repaint();
    }
    
     public void showTrangLichsuHD(JPanel wrap){
        int crong=(int)wrap.getPreferredSize().getWidth();
        int ccao=(int)wrap.getPreferredSize().getHeight();
        wrap.removeAll();
        TrangLichsuHD lshd = new TrangLichsuHD(crong,600);
        JScrollPane scrollPane = new JScrollPane(lshd);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        wrap.add(scrollPane);
        wrap.revalidate(); 
        wrap.repaint();
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {

         JPanel menuItem = (JPanel) e.getSource();
            Component[] components = menuItem.getComponents();
            for (Component component : components) {
                if (component instanceof JLabel ) {
                    JLabel label = (JLabel) component;
                    try{
                        String labelText =label.getText();
                        if(labelText != null){
                            if(selectedItem>=0 & selectedItem!=findSelectedItem(menuItem,listChucnangMenu)){
                                changeColorJPanelChildFromParent(listChucnangMenu,selectedItem,Cacthuoctinh_phuongthuc_chung.darkness_blue, Cacthuoctinh_phuongthuc_chung.sky_blue);
                                
                            }
                            selectedItem=findSelectedItem(menuItem,listChucnangMenu);
                            MouseListener[] mouseListeners = menuItem.getMouseListeners();
                                for (MouseListener listener : mouseListeners) {
                                    menuItem.removeMouseListener(listener);
                                }
                                menuItem.addMouseListener(new MouseAdapter() {
                                    @Override
                                    public void mouseClicked(MouseEvent e) {
                                       this.mouseClicked(e);
                                    }
                                });
                            menuItem.setBackground(Cacthuoctinh_phuongthuc_chung.sky_blue);
                            label.setForeground(Cacthuoctinh_phuongthuc_chung.darkness_blue);
                           
      
                            switch(labelText){
                                case "Tài khoản cá nhân":
                                    showThongtintaikhoan(main.pageContent);
                                    break;
                                case "Sản phẩm":
                                    showTrangsanpham(main.pageContent);
                                    break;
                                case "Quản lý tài khoản":
                                    showTrangtaikhoan(main.pageContent);
                                    break;
                                case "Quản lý khách hàng":
                                    
                                    break;
                                case "Quản lý sản phẩm":
                                    
                                    break;
                                case "Lịch sử hóa đơn":
                                    showTrangLichsuHD(main.pageContent);
                                    break;
                                case "Đăng xuất":
                                    view_thong_bao a=new view_thong_bao(400, 300,main,"Bạn có chắc chắn muốn đăng xuất?","Xác nhận đăng xuất");
                                    main.pageContent.removeAll();
                                    main.pageContent.validate();
                                    main.pageContent.repaint();
                                        break;
                            }
                                 
                               
                              
                            
                        }
                    } catch (NullPointerException t) {
                        // Xử lý logic khi JLabel có thể null
                        t.printStackTrace(); // In ra thông tin về lỗi (tùy chọn)
                    }
    
                    
                    
        
                }
            }
       
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'");
    }

    
    @Override
        public void mouseEntered(MouseEvent e) {
            JPanel menuItem = (JPanel) e.getSource();
           
            menuItem.setBackground(Cacthuoctinh_phuongthuc_chung.sky_blue);
            menuItem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            Component[] components = menuItem.getComponents();
            for (Component component : components) {
                if (component instanceof JLabel) {
                    JLabel label = (JLabel) component;
                    
                        
                        label.setForeground(Cacthuoctinh_phuongthuc_chung.darkness_blue);
                    
                }
            }
            
        }

        @Override
        public void mouseExited(MouseEvent e) {
            JPanel menuItem = (JPanel) e.getSource();
            menuItem.setBackground(Cacthuoctinh_phuongthuc_chung.darkness_blue);
            Component[] components = menuItem.getComponents();
            for (Component component : components) {
                if (component instanceof JLabel) {
                    JLabel label = (JLabel) component;
                   
                   
                       
                        label.setForeground(Cacthuoctinh_phuongthuc_chung.sky_blue);
                    
                    
                }
            }
        }
}
