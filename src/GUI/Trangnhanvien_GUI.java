package GUI;

import BUS.Nhanvien_BUS;
import DAO.ConnectDataBase;
import DTO.Nhanvien_DTO;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.function.Function;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Trangnhanvien_GUI extends JPanel {
    private int chieurong,chieucao;
    private Font f = new Font("Tahoma", Font.BOLD, 14);
    private ConnectDataBase mySQL = new ConnectDataBase();
    private Nhanvien_BUS dsnv;
    
     public Trangnhanvien_GUI(int chieurong,int chieucao) throws SQLException {
       this.chieurong=chieurong;
       this.chieucao=chieucao;
       init();
    }
    
      public void init() throws SQLException{   
        this.setPreferredSize(new Dimension(chieurong,chieucao));
        this.setLayout(new FlowLayout(0,0,0));

                    String[] columnNames = {"MANV", "TENNV", "CHUCVU","SDT","DIACHI","EMAIL" };
                    
                    JPanel titlePanel = new JPanel();
                        titlePanel.setLayout(new FlowLayout(0,0,0));
                        titlePanel.setBackground(Color.decode("#60A3BC"));
                        titlePanel.setPreferredSize(new Dimension((chieurong), 50));

//                        title.setForeground(Color.red);
                        for (String col : columnNames) {
                            if( col == columnNames[4] || col == columnNames[5] ){
                            JLabel l = new JLabel(col,JLabel.CENTER);
                                l.setPreferredSize(new Dimension((chieurong)/4, 50));
                                l.setFont(new Font(l.getFont().getName(), Font.BOLD, 18));
                                l.setForeground(Color.decode("#0A3D62"));
                        titlePanel.add(l);
                            }
                            else {
                                JLabel l = new JLabel(col,JLabel.CENTER);
                                l.setPreferredSize(new Dimension((chieurong)/8, 30));
                                l.setFont(new Font(l.getFont().getName(), Font.BOLD, 18));
                                l.setForeground(Color.decode("#0A3D62"));
                        titlePanel.add(l);
                            }
                        }
                this.add(titlePanel);
                
                JPanel listPanel = new JPanel();
                        listPanel.setLayout(new FlowLayout(0,0,3));
                        listPanel.setBackground(Color.white);
                        listPanel.setPreferredSize(new Dimension((chieurong), 600));

                    dsnv = new Nhanvien_BUS();
                        for (Nhanvien_DTO nv : dsnv.listnv) {
                            System.out.println(nv.getManv());
                            addNV_gui(this,nv);
                        }
    }
      
 public void control(){
        this.setPreferredSize(new Dimension(chieurong,chieucao));
        this.setLayout(new FlowLayout(0,0,0));
         JPanel Panel = new JPanel();
                        Panel.setLayout(null);
                        Panel.setBackground(Cacthuoctinh_phuongthuc_chung.light_gray);
                        Panel.setPreferredSize(new Dimension((chieurong), 50));
                   
         JButton submit = new JButton("Hoàn tất");
            submit.setBounds(chieurong-100, 0, 100, 40);
            submit.setBackground(Color.decode("#0A3D62"));
            submit.setForeground(Color.white);
                        Panel.add(submit);
this.add(Panel);
            
JPanel Pa = new JPanel();
                        Pa.setBackground(Cacthuoctinh_phuongthuc_chung.light_gray);
                        Pa.setPreferredSize(new Dimension((chieurong), 10));
this.add(Pa);
                 
                    String[] columnNames = {"MANV", "TENNV", "CHUCVU","SDT","DIACHI","EMAIL" };
                    
                    JPanel titlePanel = new JPanel();
                        titlePanel.setLayout(new FlowLayout(0,0,0));
                        titlePanel.setBackground(Color.decode("#60A3BC"));
                        titlePanel.setPreferredSize(new Dimension((chieurong), 50));

//                        title.setForeground(Color.red);
                        for (String col : columnNames) {
                            if( col == columnNames[4] || col == columnNames[5] ){
                            JLabel l = new JLabel(col,JLabel.CENTER);
                                l.setPreferredSize(new Dimension((chieurong)/4, 50));
                                l.setFont(new Font(l.getFont().getName(), Font.BOLD, 18));
                                l.setForeground(Color.decode("#0A3D62"));
                        titlePanel.add(l);
                            }
                            else {
                                JLabel l = new JLabel(col,JLabel.CENTER);
                                l.setPreferredSize(new Dimension((chieurong)/8, 30));
                                l.setFont(new Font(l.getFont().getName(), Font.BOLD, 18));
                                l.setForeground(Color.decode("#0A3D62"));
                        titlePanel.add(l);
                            }
                        }
                this.add(titlePanel);
                
                JPanel listPanel = new JPanel();
                        listPanel.setLayout(new FlowLayout(0,0,3));
                        listPanel.setBackground(Color.white);
                        listPanel.setPreferredSize(new Dimension((chieurong), 600));

                    dsnv = new Nhanvien_BUS();
                        for (Nhanvien_DTO nv : dsnv.listnv) {
                            System.out.println(nv.getManv());
                            show_control(this,nv);
                        }  
    submit.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            try {
                reloadPage();
            } catch (SQLException ex) {
                Logger.getLogger(Trangnhanvien_GUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
        
    });
 }
 
public void show_control( Trangnhanvien_GUI nvGUI,Nhanvien_DTO nv) {    
   
    JPanel itemNV = new JPanel();
    itemNV.setLayout(new FlowLayout(0, 0, 0));
    itemNV.setPreferredSize(new Dimension(chieurong, 50));
    itemNV.setBackground(Color.decode("#d3eaf2"));
    itemNV.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.decode("#60A3BC")));
    JLabel lab1 = new JLabel(nv.getManv(), JLabel.CENTER);
    JLabel lab2 = new JLabel(nv.getTennv(), JLabel.CENTER);
    JLabel lab3 = new JLabel(nv.getChucvu(), JLabel.CENTER);
    JLabel lab4 = new JLabel(String.valueOf(nv.getSdt()), JLabel.CENTER);
    JLabel lab5 = new JLabel(nv.getDiachi(), JLabel.CENTER);
    JLabel lab6 = new JLabel(nv.getEmail(), JLabel.CENTER);
    lab1.setPreferredSize(new Dimension(chieurong / 8, 40));
    lab2.setPreferredSize(new Dimension(chieurong / 8, 40));
    lab3.setPreferredSize(new Dimension(chieurong / 8, 40));
    lab4.setPreferredSize(new Dimension(chieurong / 8, 40));
    lab5.setPreferredSize(new Dimension(chieurong / 4, 40));
    lab6.setPreferredSize(new Dimension(chieurong / 4, 40));
    itemNV.add(lab1);
    itemNV.add(lab2);
    itemNV.add(lab3);
    itemNV.add(lab4);
    itemNV.add(lab5);
    itemNV.add(lab6);
    this.add(itemNV);
    
    JPanel control = new JPanel();
    control.setVisible(false);
    control.setBounds((chieurong - 300) / 2, (chieucao - 280) / 2, chieurong, 200);
    control.setBackground(Cacthuoctinh_phuongthuc_chung.light_gray);
    control.setLayout(new FlowLayout(1, 10, 0));
    JLabel title = new JLabel("Bạn muốn thay đổi thông tin nhân viên?", JLabel.LEFT);
    title.setForeground(Color.decode("#60A3BC"));
    title.setPreferredSize(new Dimension(chieurong-294, 50)); 
    title.setFont(new Font(title.getFont().getName(), Font.BOLD, 14));
    control.add(title);
    
    // Tạo nút "Sửa"
    JButton editButton = new JButton("Sửa");
    editButton.setPreferredSize(new Dimension(80, 30));
    editButton.setBackground(Cacthuoctinh_phuongthuc_chung.darkness_blue);
    editButton.setForeground(Color.white);
    editButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            removePanel(itemNV, control);
            UpdateNV_GUI k = new UpdateNV_GUI(nvGUI,nv);
            control.setVisible(false);
        }
    });

    // Tạo nút "Xóa"
    JButton deleteButton = new JButton("Xóa");
    deleteButton.setPreferredSize(new Dimension(80, 30));
    deleteButton.setBackground(Cacthuoctinh_phuongthuc_chung.darkness_blue);
    deleteButton.setForeground(Color.white);
    deleteButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
            int r1 = JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn xóa nhân viên " + nv.getTennv() + "?", "Xóa", JOptionPane.YES_NO_OPTION);
            if (r1 == JOptionPane.YES_OPTION) {
                removePanel(itemNV, control);
                dsnv.delete(nv);
            }
        }
    });

    // Tạo nút "Hủy"
    JButton cancelButton = new JButton("Hủy");
    cancelButton.setPreferredSize(new Dimension(80, 30));
    cancelButton.setBackground(Cacthuoctinh_phuongthuc_chung.sky_blue);
    cancelButton.setForeground(Color.black);
    cancelButton.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent e) {
      
                    control.setVisible(false);
            
        }
    });
    
    // Thêm nút "Sửa", "Xóa" và "Hủy" vào panel control
    control.add(editButton);
    control.add(deleteButton);
    control.add(cancelButton);
    control.setBorder(BorderFactory.createMatteBorder(0, 2, 2, 2, Color.decode("#60A3BC")));
    control.setVisible(false);
    this.add(control);
                  
    itemNV.addMouseListener(new MouseAdapter() {
        @Override
        public void mouseClicked(MouseEvent e) {
            control.setVisible(true);
            control.revalidate();
            control.repaint();
        }

        @Override
        public void mouseEntered(MouseEvent e) {
            JPanel HDItem = (JPanel) e.getSource();
            HDItem.setBackground(Cacthuoctinh_phuongthuc_chung.light_gray);
            HDItem.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.decode("#60A3BC")));
            HDItem.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            Component[] components = HDItem.getComponents();
            for (Component component : components) {
                if (component instanceof JLabel label) {
                    label.setForeground(Color.decode("#0A3D62"));
                }
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            JPanel HDItem = (JPanel) e.getSource();
            HDItem.setBackground(Color.decode("#d3eaf2"));
            HDItem.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.decode("#60A3BC")));
            Component[] components = HDItem.getComponents();
            for (Component component : components) {
                if (component instanceof JLabel label) {
                    label.setForeground(Color.black);
                }
            }
        }
    });
}


public void addNV_gui( Trangnhanvien_GUI nvGUI,Nhanvien_DTO nv) {      
    JPanel itemNV = new JPanel();
    itemNV.setLayout(new FlowLayout(0, 0, 0));
    itemNV.setPreferredSize(new Dimension(chieurong, 50));
    itemNV.setBackground(Color.decode("#d3eaf2"));
    itemNV.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.decode("#60A3BC")));
    JLabel lab1 = new JLabel(nv.getManv(), JLabel.CENTER);
    JLabel lab2 = new JLabel(nv.getTennv(), JLabel.CENTER);
    JLabel lab3 = new JLabel(nv.getChucvu(), JLabel.CENTER);
    JLabel lab4 = new JLabel(String.valueOf(nv.getSdt()), JLabel.CENTER);
    JLabel lab5 = new JLabel(nv.getDiachi(), JLabel.CENTER);
    JLabel lab6 = new JLabel(nv.getEmail(), JLabel.CENTER);
    lab1.setPreferredSize(new Dimension(chieurong / 8, 40));
    lab2.setPreferredSize(new Dimension(chieurong / 8, 40));
    lab3.setPreferredSize(new Dimension(chieurong / 8, 40));
    lab4.setPreferredSize(new Dimension(chieurong / 8, 40));
    lab5.setPreferredSize(new Dimension(chieurong / 4, 40));
    lab6.setPreferredSize(new Dimension(chieurong / 4, 40));
    itemNV.add(lab1);
    itemNV.add(lab2);
    itemNV.add(lab3);
    itemNV.add(lab4);
    itemNV.add(lab5);
    itemNV.add(lab6);
    this.add(itemNV);
}

// Hàm xóa panel khỏi container
public void removePanel(JPanel itemNV, JPanel control) {
    this.remove(itemNV); // Xóa panel itemNV khỏi container
    this.remove(control); // Xóa panel control khỏi container
    this.revalidate(); // Cập nhật container
    this.repaint(); // Vẽ lại container để hiển thị sự thay đổi
}

// reload trang nhân viên
    public void reloadPage() throws SQLException {
        // Xóa toàn bộ nội dung của frame
        this.removeAll();

        // Khởi tạo lại giao diện mới
        this.init();

        // Vẽ lại frame
        revalidate();
        repaint();
    }
public void reloadPagecontrol() throws SQLException {
        // Xóa toàn bộ nội dung của frame
        this.removeAll();

        // Khởi tạo lại giao diện mới
        this.control();

        // Vẽ lại frame
        revalidate();
        repaint();
    }
     public static void main (String[] args) throws SQLException{
        JFrame f = new JFrame ();
        f.setSize(1200,800);
        Trangnhanvien_GUI p = new Trangnhanvien_GUI(800,400);
        f.add(p);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
                        }
