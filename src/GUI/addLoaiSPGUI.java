/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

import BUS.loaiSPBUS;
import DTO.loaiSP;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 *
 * @author hp
 */
public class addLoaiSPGUI extends JFrame implements MouseListener{

    private class addLoaiSP extends JPanel {

        public JLabel error;
        public JTextField getData;
        private String title = "Tên";
        private Font font_data = new Font("Tahoma", Font.PLAIN, 14);
        protected JPanel btn_exit;
        protected JPanel btn_submit;
        
        public addLoaiSP(int chieurong, int chieucao) {
            
            getData = new JTextField();
            error = new JLabel();
            init(chieurong, chieucao);
        }

        private void init(int chieurong, int chieucao) {

            setLayout(new FlowLayout(3, 0, 0));
            setPreferredSize(new Dimension(chieurong+20, chieucao));
            JPanel titleGUI_wrap = new JPanel(new BorderLayout());
            titleGUI_wrap.setPreferredSize(new Dimension(chieurong, 40));
            JLabel titleGUI = new JLabel("Thêm loại sản phẩm".toUpperCase(), JLabel.CENTER);
            titleGUI.setFont(Cacthuoctinh_phuongthuc_chung.font_header);
            titleGUI_wrap.add(titleGUI, BorderLayout.CENTER);
            add(titleGUI_wrap);

            
                JPanel item = new JPanel(new FlowLayout(3, 10, 0));
                item.setPreferredSize(new Dimension(chieurong, 100));
                JLabel lb_title = new JLabel(title);
                lb_title.setPreferredSize(new Dimension(chieurong, 30));
                lb_title.setFont(font_data);
                lb_title.setForeground(Cacthuoctinh_phuongthuc_chung.sky_blue);
                item.add(lb_title);

                getData = new JTextField();
                getData.setPreferredSize(new Dimension(chieurong -10, 30));
                item.add(getData);

                error = new JLabel("");
                error.setFont(new Font("Tahoma", Font.ITALIC, 14) {
                });
                error.setPreferredSize(new Dimension(chieurong, 20));
                error.setForeground(Cacthuoctinh_phuongthuc_chung.error);
                item.add(error);

                add(item);
            

            JPanel btn_wrap = new JPanel(new FlowLayout(1));

            btn_exit = new JPanel();
            cssBtn(btn_exit, "Hủy", "btn_exit");

            btn_submit = new JPanel();
            cssBtn(btn_submit, "Xác nhận", "btn_submit");
            btn_wrap.setPreferredSize(new Dimension(chieurong, (int) btn_submit.getPreferredSize().getHeight() + 20));
            btn_wrap.add(btn_exit);
            btn_wrap.add(btn_submit);
            setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
            add(btn_wrap);
        }

        private void cssBtn(JPanel b, String text, String name) {
            JLabel t = new JLabel(text, JLabel.CENTER);
            t.setForeground(Color.WHITE);
            b.setName(name);
            b.add(t);
            b.setBackground(Cacthuoctinh_phuongthuc_chung.darkness_blue);
            b.setPreferredSize(new Dimension(100, (int) b.getPreferredSize().getHeight()));
            b.setOpaque(true);
        }
    }
    private int chieurong, chieucao;
    private addLoaiSP addLoai;
    private boolean flag_ten;
    private loaiSPGUI loaiGUI;
    public addLoaiSPGUI(loaiSPGUI loaiGUI) {
        this.loaiGUI=loaiGUI;
        chieurong = chieucao = 300;
        flag_ten  = false;
        init();
    }

    private void init() {
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        setSize(chieurong, chieucao);
        setBackground(Color.WHITE);
        addLoai = new addLoaiSP(getWidth(), getHeight());
        addLoai.btn_exit.addMouseListener(this);
        addLoai.btn_submit.addMouseListener(this);
        add(addLoai, BorderLayout.CENTER);
        pack();
        setLocationRelativeTo(null);
     
        setVisible(true);
    }
    
    @Override
    public void mouseClicked(MouseEvent e) {
      try {
            JPanel btn = (JPanel) e.getSource();
            switch (btn.getName()) {
                case "btn_exit":
                    int r1 = JOptionPane.showConfirmDialog(null, "Những thông tin sẽ không được lưu sau khi thoát!\nBạn có muốn tiếp tục?", "Thoát", JOptionPane.YES_NO_OPTION);
                    if (r1 == JOptionPane.YES_OPTION) {
                       
                    } else {
                        dispose();
                    }

                    break;
                case "btn_submit":
                    String ten = addLoai.getData.getText();
                    loaiSPBUS loaiBUS = new loaiSPBUS();
                    if (ten.equals("")) {
                        addLoai.error.setText("Không được để trống");
                    } else if (!loaiBUS.checkTENLOAI(ten)) {
                        addLoai.error.setText("Tên chỉ chứa chữ cái");
                    } else {
                        flag_ten = true;
                        addLoai.error.setText("");
                    }
                   

                    if (flag_ten) {
                        int r2 = JOptionPane.showConfirmDialog(null, "Bạn đã chắc chắn với thông tin nhập vào?", "Thêm loại sản phẩm ", JOptionPane.YES_NO_OPTION);
                        if (r2 == JOptionPane.YES_OPTION) {
                            loaiSP loaiDTO = new loaiSP(ten);
                            boolean flag = true;
                            for(loaiSP s: loaiBUS.getList()){
                                if(s.getTENLOAI().equals(loaiDTO.getTENLOAI())){
                                    flag = false;
                                    break;
                                }
                            }
                             if(flag){
                               loaiBUS.add(loaiDTO);
                           loaiGUI.addLineDataInTable(loaiDTO);
                            JOptionPane.showMessageDialog(null, "Thêm loại sản phẩm mới thành công!");
                            dispose();
                            }else
                                JOptionPane.showMessageDialog(null, "Thêm loại sản phẩm mới thất bại do tên bị trùng với tên đã có!");
                            
                          
                           
                            
                           

                        } else {
                             
                        }

                    }

                    break;
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        try {
            JPanel btn = (JPanel) e.getSource();
            btn.setBackground(Cacthuoctinh_phuongthuc_chung.sky_blue);
            btn.setOpaque(true);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
     try {
            JPanel btn = (JPanel) e.getSource();
            btn.setBackground(Cacthuoctinh_phuongthuc_chung.darkness_blue);
            btn.setOpaque(true);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
    
//
}
