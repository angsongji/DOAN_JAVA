
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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Trangnhanvien_GUI extends JPanel {
    private int chieurong,chieucao;
    private Font f = new Font("Tahoma", Font.BOLD, 14);
    private ConnectDataBase mySQL = new ConnectDataBase();
    private Nhanvien_BUS dsnv;
    
     public Trangnhanvien_GUI(int chieurong,int chieucao) throws SQLException {
       this.chieurong=chieurong;
       this.chieucao=chieucao;
       init( );
    }
    
      public void init() throws SQLException{   
        this.setPreferredSize(new Dimension(chieurong,chieucao));
        this.setLayout(new BorderLayout());

                    String[] columnNames = {"MANV", "TENNV", "CHUCVU","SDT","DIACHI","EMAIL" };
                    
                    JPanel titlePanel = new JPanel();
                        titlePanel.setLayout(new FlowLayout(0,0,0));
                        titlePanel.setBackground(Color.decode("#60A3BC"));
                        titlePanel.setPreferredSize(new Dimension((chieurong), 100));

//                        title.setForeground(Color.red);
                        for (String col : columnNames) {
                            if( col == columnNames[4] || col == columnNames[5] ){
                            JLabel l = new JLabel(col,JLabel.CENTER);
                                l.setPreferredSize(new Dimension((chieurong)/4, 40));
                                l.setFont(new Font(l.getFont().getName(), Font.BOLD, 18));
                                l.setForeground(Color.decode("#0A3D62"));
                        titlePanel.add(l);
                            }
                            else {
                                JLabel l = new JLabel(col,JLabel.CENTER);
                                l.setPreferredSize(new Dimension((chieurong)/8, 40));
                                l.setFont(new Font(l.getFont().getName(), Font.BOLD, 18));
                                l.setForeground(Color.decode("#0A3D62"));
                        titlePanel.add(l);
                            }
                        }
                this.add(titlePanel, BorderLayout.NORTH);
                
                JPanel listPanel = new JPanel();
                        listPanel.setLayout(new FlowLayout(0,0,3));
                        listPanel.setBackground(Color.white);
                        titlePanel.setPreferredSize(new Dimension((chieurong), 60));

                    dsnv = new Nhanvien_BUS();
                        for (Nhanvien_DTO nv : dsnv.listnv) {
                            JPanel itemNV = new JPanel();
                                itemNV.setLayout(new FlowLayout(0,0,0));
                                itemNV.setPreferredSize(new Dimension((chieurong), 50));
                                itemNV.setBackground(Color.decode("#d3eaf2"));
                                JLabel lab1 = new JLabel( nv.getManv(), JLabel.CENTER);
                                JLabel lab2 = new JLabel( nv.getTennv(), JLabel.CENTER);
                                JLabel lab3 = new JLabel(nv.getChucvu(), JLabel.CENTER);
                                JLabel lab4 = new JLabel(Integer.toString(nv.getSdt()), JLabel.CENTER);
                                JLabel lab5 = new JLabel(nv.getDiachi() , JLabel.CENTER);
                                JLabel lab6 = new JLabel( nv.getEmail(), JLabel.CENTER);
                                    lab1.setPreferredSize(new Dimension((chieurong)/8, 40));
                                    lab2.setPreferredSize(new Dimension((chieurong)/8, 40));
                                    lab3.setPreferredSize(new Dimension((chieurong)/8, 40));
                                    lab4.setPreferredSize(new Dimension((chieurong)/8, 40));
                                    lab5.setPreferredSize(new Dimension((chieurong)/4, 40));
                                    lab6.setPreferredSize(new Dimension((chieurong)/4, 40));
                            itemNV.add(lab1);
                            itemNV.add(lab2);
                            itemNV.add(lab3);
                            itemNV.add(lab4);
                            itemNV.add(lab5);
                            itemNV.add(lab6);
                        listPanel.add(itemNV);  

                itemNV.addMouseListener(new MouseAdapter() {
                @Override
                    public void mouseClicked(MouseEvent e) {
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
                        this.add(listPanel, BorderLayout.CENTER);
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

    
