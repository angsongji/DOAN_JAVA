package GUI;

import BUS.SanPhamBUS;
import DTO.SanPhamDTO;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.Border;

public class SanPhamGUI extends JPanel implements MouseListener {

    SanPhamBUS spBUS = new SanPhamBUS();
    ArrayList<SanPhamDTO> dsSP;
    SanPhamDTO selectedSP = new SanPhamDTO();

    JPanel[] product;
    private int chieurong, chieucao;//Oanh them
    private Color backGroundColor;

    
    public SanPhamGUI(int chieurong, int chieucao, Color backG_thisJPanel) {//Oanh them doi so truyen vao
        this.chieurong = chieurong;
        this.chieucao = chieucao;
        backGroundColor = backG_thisJPanel;
        dsSP = spBUS.getDsSP();
        init();

    }

    public void init() {
        this.setPreferredSize(new Dimension(chieurong, chieucao));
        this.setLayout(new BorderLayout());
        this.setBackground(backGroundColor);
        this.setOpaque(true);

        JPanel mainPanel =initContent(dsSP);
        this.add(mainPanel,BorderLayout.CENTER);
    }

    public JPanel initContent(ArrayList<SanPhamDTO> dsSP){
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(chieurong, chieucao));
        panel.setLayout(new FlowLayout(3, 15, 15));
        panel.setBackground(backGroundColor);
        panel.setOpaque(true);
        product = new JPanel[dsSP.size()];
        for (int i = 0; i < dsSP.size(); i++) {
            //san pham con ban moi hien thi
            if (dsSP.get(i).getTrangThai() == 1) {
                product[i] = new JPanel();
                product[i].setPreferredSize(new Dimension(174, 270));
                product[i].setLayout(new BoxLayout(product[i], BoxLayout.Y_AXIS));
                product[i].setAlignmentY(TOP_ALIGNMENT);

                ImageIcon icon = new ImageIcon((String) "./src/images/" + dsSP.get(i).tenHinh[0]);
                Image scaledImage = icon.getImage().getScaledInstance(174, 210, Image.SCALE_SMOOTH);
                ImageIcon resizedIcon = new ImageIcon(scaledImage);
                JLabel label = new JLabel(resizedIcon, JLabel.CENTER);
                label.setPreferredSize(new Dimension(174, 200));
                label.setAlignmentX(Component.CENTER_ALIGNMENT); // Căn giữa theo chiều ngang
                label.setAlignmentY(Component.CENTER_ALIGNMENT); // Căn giữa theo chiều dọc
                product[i].add(label);
                product[i].add(Box.createVerticalStrut(5));

                JLabel productName = new JLabel(dsSP.get(i).getTenSP());
                productName.setAlignmentX(Component.CENTER_ALIGNMENT); // Căn giữa theo chiều ngang
                productName.setAlignmentY(Component.CENTER_ALIGNMENT); // Căn giữa theo chiều dọc
                product[i].add(productName);
                product[i].add(Box.createVerticalStrut(5));

                JLabel productPrice = new JLabel("Giá: " + dsSP.get(i).getPrice());
                productPrice.setAlignmentX(Component.CENTER_ALIGNMENT); // Căn giữa theo chiều ngang
                productPrice.setAlignmentY(Component.CENTER_ALIGNMENT); // Căn giữa theo chiều dọc
                product[i].add(productPrice);

                product[i].addMouseListener(this);
                panel.add(product[i]);

                if (i < product.length - 1) {
                    panel.add(Box.createHorizontalStrut(10)); // Khoảng cách 10 pixel
                }
            }

        }
        return panel;
    }
     public void refresh() {
        this.removeAll(); // Xóa tất cả các thành phần hiện tại
        dsSP = spBUS.getDsSP(); // Tải lại dữ liệu
        JPanel mainPanel = initContent(dsSP); // Tạo lại JPanel chính
        this.add(mainPanel, BorderLayout.CENTER); // Thêm lại JPanel chính

        this.revalidate(); // Cập nhật lại giao diện
        this.repaint(); // Vẽ lại giao diện
    }
    
    public void AddSP(SanPhamDTO sp) {
        spBUS.add(sp);
        refresh();
        this.revalidate(); // Cập nhật lại giao diện
        this.repaint(); // Vẽ lại giao diện

    }
    
    public void DeleteSP(){
        spBUS.delete(selectedSP.getMaSP());
        selectedSP = new SanPhamDTO();
        refresh();
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Lấy ra JPanel được click
        JPanel pn = (JPanel) e.getSource();

        Border lineBorder = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 4); // Đường viền ngoài
        pn.setBorder(lineBorder);
        // Tìm vị trí của JPanel trong mảng product
        int index = -1;
        for (int i = 0; i < dsSP.size(); i++) {
            if (pn == product[i]) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            selectedSP = dsSP.get(index);
            // Hiển thị chi tiết sản phẩm
//            view_chi_tiet_san_pham t = new view_chi_tiet_san_pham(500, 500);
//            JFrame f = new JFrame();
//            f.add(t);
//            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//            f.setSize(1000, 600);
//            f.setVisible(true);
//            f.setLocationRelativeTo(null);

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
//        JPanel pn1 = (JPanel) e.getSource();
//        Border lineBorder = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 4); // Đường viền ngoài
//        pn1.setBorder(lineBorder);
    }

    @Override
    public void mouseExited(MouseEvent e) {
//        JPanel pn1 = (JPanel) e.getSource();
//        Border lineBorder = null;
//        pn1.setBorder(lineBorder);
    }
}
