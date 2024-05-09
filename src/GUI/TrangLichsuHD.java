
package GUI;

import GUI.ChitietHD_GUI;
import DTO.Hoadon_DTO;
import BUS.Hoadon_BUS;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

//import java.sql.*;
public final class TrangLichsuHD extends JPanel  {
    private JPanel left, right;
    private JPanel p;
    private Hoadon_BUS lshd ;
    private int chieurong,chieucao;
    private Color backGroundColor;
    private Font f = new Font("Tahoma", Font.BOLD, 16);
    
    public TrangLichsuHD(int chieurong,int chieucao) {
       this.chieurong=chieurong;
       this.chieucao=chieucao;
       init();
        
    }
    
    public void init() {
        this.setPreferredSize(new Dimension(chieurong,chieucao));
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            right = new JPanel(); 
            right.setPreferredSize(new Dimension(chieurong*3/5, 0));
            right.setBackground(Color.white); 
            right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
                p = new JPanel(); 
                p.setLayout(new BorderLayout());
                p.add(new JLabel("Chưa lựa chọn Hóa đơn để hiển thị chi tiết",JLabel.CENTER),BorderLayout.CENTER);
                p.setPreferredSize(new Dimension(chieurong*2/3,0));
                p.setBackground(Color.WHITE);
            right.add(p);
                
            
            
            left = new JPanel();
            left.setPreferredSize(new Dimension(chieurong*2/5, 0));
            left.setBackground(Color.white);
            left.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 4,  Color.decode("#60A3BC")));
        this.add(left);
        this.add(right);
            
        // create title
        String[] columnNames = {"Ngay", "KH", "Nhanvien","Hoadon","Giamgia","Thanhtien" }; 
                    JPanel titlePanel = new JPanel();
                        titlePanel.setLayout(new FlowLayout(1,0,0));
                        titlePanel.setBackground(Color.decode("#60A3BC"));
                        titlePanel.setPreferredSize(new Dimension((chieurong), 30));
                     for (String col : columnNames) {
                         if(col == columnNames[0]){
                             JLabel l = new JLabel(col,JLabel.CENTER);
                                l.setPreferredSize(new Dimension(((chieurong)*2/5)/3, 30));
                                l.setFont(new Font(l.getFont().getName(), Font.CENTER_BASELINE, 12));
                                l.setForeground(Color.white);
                        titlePanel.add(l);
                         }
                         else{
                             if(col == columnNames[1]){
                                 JLabel l = new JLabel(col,JLabel.CENTER);
                                l.setPreferredSize(new Dimension(((chieurong)*2/5)/9, 30));
                                l.setFont(new Font(l.getFont().getName(), Font.CENTER_BASELINE, 12));
                                l.setForeground(Color.white);
                        titlePanel.add(l);
                               }
                             else{
                            JLabel l = new JLabel(col,JLabel.CENTER);
                                l.setPreferredSize(new Dimension(((chieurong)*2/5)/8, 30));
                                l.setFont(new Font(l.getFont().getName(), Font.CENTER_BASELINE, 12));
                                l.setForeground(Color.white);
                        titlePanel.add(l);
                             }
                         }
                     }
            left.add(titlePanel);       
        lshd = new Hoadon_BUS();
            for (Hoadon_DTO hd : lshd.dshoadon) {
               addHD_gui(this,hd);
            }
    }
    
    
     public void control() {
        this.setPreferredSize(new Dimension(chieurong,chieucao));
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            right = new JPanel(); 
            right.setPreferredSize(new Dimension(chieurong*3/5, 0));
            right.setBackground(Color.white); 
            right.setLayout(new BoxLayout(right, BoxLayout.Y_AXIS));
                p = new JPanel(); 
                p.setLayout(new BorderLayout());
                p.add(new JLabel("Chưa lựa chọn Hóa đơn để hiển thị chi tiết",JLabel.CENTER),BorderLayout.CENTER);
                p.setPreferredSize(new Dimension(chieurong*2/3,0));
                p.setBackground(Color.WHITE);
            right.add(p);
                
            
            
            left = new JPanel();
            left.setPreferredSize(new Dimension(chieurong*2/5, 0));
            left.setBackground(Color.white);
            left.setBorder(BorderFactory.createMatteBorder(0, 0, 0, 4,  Color.decode("#60A3BC")));
        this.add(left);
        this.add(right);
            
        // create title
        String[] columnNames = {"Ngay", "KH", "Nhanvien","Hoadon","Giamgia","Thanhtien" }; 
                    JPanel titlePanel = new JPanel();
                        titlePanel.setLayout(new FlowLayout(1,0,0));
                        titlePanel.setBackground(Color.decode("#60A3BC"));
                        titlePanel.setPreferredSize(new Dimension((chieurong), 30));
                     for (String col : columnNames) {
                         if(col == columnNames[0]){
                             JLabel l = new JLabel(col,JLabel.CENTER);
                                l.setPreferredSize(new Dimension(((chieurong)*2/5)/3, 30));
                                l.setFont(new Font(l.getFont().getName(), Font.CENTER_BASELINE, 12));
                                l.setForeground(Color.white);
                        titlePanel.add(l);
                         }
                         else{
                             if(col == columnNames[1]){
                                 JLabel l = new JLabel(col,JLabel.CENTER);
                                l.setPreferredSize(new Dimension(((chieurong)*2/5)/9, 30));
                                l.setFont(new Font(l.getFont().getName(), Font.CENTER_BASELINE, 12));
                                l.setForeground(Color.white);
                        titlePanel.add(l);
                               }
                             else{
                            JLabel l = new JLabel(col,JLabel.CENTER);
                                l.setPreferredSize(new Dimension(((chieurong)*2/5)/8, 30));
                                l.setFont(new Font(l.getFont().getName(), Font.CENTER_BASELINE, 12));
                                l.setForeground(Color.white);
                        titlePanel.add(l);
                             }
                         }
                     }
            left.add(titlePanel);       
        lshd = new Hoadon_BUS();
            for (Hoadon_DTO hd : lshd.dshoadon) {
               show_control(this,hd);
            }
    }
     
     
     public void show_control(TrangLichsuHD hdGUI,Hoadon_DTO hd) {
          JPanel pa = new JPanel();
                    pa.setLayout(new FlowLayout(1,0,0));
                    pa.setPreferredSize(new Dimension((chieurong)*2/5-4, 30));                   
                    pa.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.decode("#0A3D62")));
                    pa.setBackground(Color.white);
                    JLabel lab1 = new JLabel((String) hd.getNgayHD(), JLabel.CENTER);
                    JLabel lab2 = new JLabel(Integer.toString(hd.getMaKH()), JLabel.CENTER);
                    JLabel lab3 = new JLabel((String) hd.getMaNV(), JLabel.CENTER);
                    JLabel lab4 = new JLabel((String) hd.getMaHD(), JLabel.CENTER);
                    JLabel lab5 = new JLabel(Integer.toString(hd.getTongTien()) , JLabel.CENTER);
                    JLabel lab6 = new JLabel(Integer.toString(hd.getGiamgia()) , JLabel.CENTER);

                    lab1.setPreferredSize(new Dimension((chieurong*2/5)/3, 30));
                    lab2.setPreferredSize(new Dimension((chieurong*2/5)/9, 30));
                    lab3.setPreferredSize(new Dimension((chieurong*2/5)/8, 30));
                    lab4.setPreferredSize(new Dimension((chieurong*2/5)/8, 30));
                    lab6.setPreferredSize(new Dimension((chieurong*2/5)/8, 30));                    
                    lab5.setPreferredSize(new Dimension((chieurong*2/5)/8, 30));
                    
                pa.add(lab1);
                pa.add(lab2);
                pa.add(lab3);
                pa.add(lab4);
                pa.add(lab6);
                pa.add(lab5);
        left.add(pa);
                pa.addMouseListener(new MouseAdapter() {
                @Override
                    public void mouseClicked(MouseEvent e) {
                        JPanel HDItem = (JPanel) e.getSource();
                            HDItem.setBackground(Color.decode("#0A3D62"));
                            Component[] components = HDItem.getComponents();
                            for (Component component : components) {
                                if (component instanceof JLabel) {
                                    JLabel label = (JLabel) component;
                                        label.setForeground(Color.WHITE);
                                }
                            }
                            HDItem.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.decode("#60A3BC")));
                        right.removeAll();
                        ArrayList<String> LCont = extractLabelContents(pa);
                            for (String a : LCont) {
                                System.out.print(a);
                            }
                        try {
                            ChitietHD_GUI s = new ChitietHD_GUI(chieurong, chieucao, LCont.get(3), LCont.get(0), LCont.get(1), LCont.get(2), LCont.get(4),LCont.get(5));
                            right.add(s);
                            right.revalidate();
                            right.repaint();
                      } catch (SQLException ex) {
                            Logger.getLogger(TrangLichsuHD.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        int r2 = JOptionPane.showConfirmDialog(null, "Sau khi xóa hóa đơn sẽ bị hủy và hoàn tất thao tác trả hàng \nBạn chắc chắn muốn xóa hóa đơn?", "Xóa hóa đơn ", JOptionPane.YES_NO_OPTION);
                        if (r2 == JOptionPane.YES_OPTION) {
                            try {
                                lshd.delete(hd);
                            } catch (SQLException ex) {
                                Logger.getLogger(TrangLichsuHD.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            JOptionPane.showMessageDialog(null, "Xóa hóa đơn thành công!");
                            try {
                                hdGUI.reloadPage();
                            } catch (SQLException ex) {
                                Logger.getLogger(TrangLichsuHD.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        } else {
                            // Thực hiện hành động khi người dùng chọn No hoặc đóng cửa sổ
                        }

                        
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                       
                    }

                     @Override
                    public void mouseReleased(MouseEvent e) {
                    }
    
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        JPanel HDItem = (JPanel) e.getSource();
                        HDItem.setBackground(Cacthuoctinh_phuongthuc_chung.light_gray);
                        setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.decode("#60A3BC")));
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
                            HDItem.setBackground(Color.white);
                            HDItem.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.decode("#60A3BC")));
                        Component[] components = HDItem.getComponents();
                        for (Component component : components) {
                            if (component instanceof JLabel label) {
                                label.setForeground(Color.black);
                            }
                        }
                    }

                    private ArrayList<String> extractLabelContents(JPanel pa) {
                        ArrayList<String> contents = new ArrayList<>();
                            Component[] components = pa.getComponents();
                                for (Component component : components) {
                                    if (component instanceof JLabel) {
                                        JLabel label = (JLabel) component;
                                        contents.add(label.getText());
                                    }
                                }
                        return contents;                  
                    }
                });
     }
    public void addHD_gui( TrangLichsuHD hdGUI,Hoadon_DTO hd) {
         JPanel pa = new JPanel();
                    pa.setLayout(new FlowLayout(1,0,0));
                    pa.setPreferredSize(new Dimension((chieurong)*2/5-4, 30));                   
                    pa.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.decode("#0A3D62")));
                    pa.setBackground(Color.white);
                    JLabel lab1 = new JLabel((String) hd.getNgayHD(), JLabel.CENTER);
                    JLabel lab2 = new JLabel(Integer.toString(hd.getMaKH()), JLabel.CENTER);
                    JLabel lab3 = new JLabel((String) hd.getMaNV(), JLabel.CENTER);
                    JLabel lab4 = new JLabel((String) hd.getMaHD(), JLabel.CENTER);
                    JLabel lab5 = new JLabel(Integer.toString(hd.getTongTien()) , JLabel.CENTER);
                    JLabel lab6 = new JLabel(Integer.toString(hd.getGiamgia()) , JLabel.CENTER);

                    lab1.setPreferredSize(new Dimension((chieurong*2/5)/3, 30));
                    lab2.setPreferredSize(new Dimension((chieurong*2/5)/9, 30));
                    lab3.setPreferredSize(new Dimension((chieurong*2/5)/8, 30));
                    lab4.setPreferredSize(new Dimension((chieurong*2/5)/8, 30));
                    lab6.setPreferredSize(new Dimension((chieurong*2/5)/8, 30));                    
                    lab5.setPreferredSize(new Dimension((chieurong*2/5)/8, 30));
                    
                pa.add(lab1);
                pa.add(lab2);
                pa.add(lab3);
                pa.add(lab4);
                pa.add(lab5);
                pa.add(lab6);
        left.add(pa);
                pa.addMouseListener(new MouseAdapter() {
                @Override
                    public void mouseClicked(MouseEvent e) {
                        JPanel HDItem = (JPanel) e.getSource();
                            HDItem.setBackground(Color.decode("#0A3D62"));
                            Component[] components = HDItem.getComponents();
                            for (Component component : components) {
                                if (component instanceof JLabel) {
                                    JLabel label = (JLabel) component;
                                        label.setForeground(Color.WHITE);
                                }
                            }
                            HDItem.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.decode("#60A3BC")));
                        right.removeAll();
                        ArrayList<String> LCont = extractLabelContents(pa);
                            for (String a : LCont) {
                                System.out.print(a);
                            }
                        try {
                            ChitietHD_GUI s = new ChitietHD_GUI(chieurong, chieucao, LCont.get(3), LCont.get(0), LCont.get(1), LCont.get(2), LCont.get(4),LCont.get(5));
                            right.add(s);
                            right.revalidate();
                            right.repaint();
                      } catch (SQLException ex) {
                            Logger.getLogger(TrangLichsuHD.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {
                       
                    }

                     @Override
                    public void mouseReleased(MouseEvent e) {
                    }
    
                    @Override
                    public void mouseEntered(MouseEvent e) {
                        JPanel HDItem = (JPanel) e.getSource();
                        HDItem.setBackground(Cacthuoctinh_phuongthuc_chung.light_gray);
                        setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.decode("#60A3BC")));
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
                            HDItem.setBackground(Color.white);
                            HDItem.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.decode("#60A3BC")));
                        Component[] components = HDItem.getComponents();
                        for (Component component : components) {
                            if (component instanceof JLabel label) {
                                label.setForeground(Color.black);
                            }
                        }
                    }

                    private ArrayList<String> extractLabelContents(JPanel pa) {
                        ArrayList<String> contents = new ArrayList<>();
                            Component[] components = pa.getComponents();
                                for (Component component : components) {
                                    if (component instanceof JLabel) {
                                        JLabel label = (JLabel) component;
                                        contents.add(label.getText());
                                    }
                                }
                        return contents;                  
                    }
                });
    }            
       
    
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
     public static void main (String[] args){
        JFrame f = new JFrame ();
        f.setSize(1200,800);
        TrangLichsuHD p = new TrangLichsuHD(1200,800);
        f.add(p);
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.setVisible(true);
    }
}
      
    
    


           



