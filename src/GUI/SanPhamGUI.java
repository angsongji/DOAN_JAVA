package GUI;

import BUS.SanPhamBUS;
import BUS.loaiSPBUS;
import DTO.SanPhamDTO;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import javax.swing.*;
import javax.swing.border.Border;

public class SanPhamGUI extends JPanel implements MouseListener {

    SanPhamBUS spBUS = new SanPhamBUS();
    ArrayList<SanPhamDTO> dsSP;
    SanPhamDTO selectedSP = new SanPhamDTO();
    private JLabel lblChiTietSP;

    JPanel[] product;
    private int chieurong, chieucao;//Oanh them
    private Color backGroundColor;
    private Color normal = Color.decode("#0A3D62");
    Font font_family = new Font("Tahoma", Font.BOLD, 12);

    // Định dạng sử dụng dấu phân cách hàng nghìn
    DecimalFormat FormatInt = new DecimalFormat("#,###");

    public SanPhamGUI(int chieurong, int chieucao, Color backG_thisJPanel) {//Oanh them doi so truyen vao
        this.chieurong = chieurong;
        this.chieucao = chieucao;
        backGroundColor = backG_thisJPanel;
        dsSP = new ArrayList<>();
        for (int i = 0; i < spBUS.getDsSP().size(); i++) {
            if (spBUS.getDsSP().get(i).getTrangThai() == 1) {
                dsSP.add(spBUS.getDsSP().get(i));
            }
        }
        init();

    }

    public void init() {
        this.setPreferredSize(new Dimension(chieurong, chieucao));
        this.setLayout(new BorderLayout());
        this.setBackground(backGroundColor);
        this.setOpaque(true);

        JPanel mainPanel = initContent(dsSP);
        this.add(mainPanel, BorderLayout.CENTER);
    }

    public JPanel initContent(ArrayList<SanPhamDTO> dsSP) {
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(chieurong, chieucao));
        panel.setLayout(new FlowLayout(3, 15, 15));
        panel.setBackground(backGroundColor);
        panel.setOpaque(true);
        product = new JPanel[dsSP.size()];
        for (int i = 0; i < dsSP.size(); i++) {
            final int index = i;
            //san pham con ban moi hien thi
            product[i] = new JPanel();
            product[i].setPreferredSize(new Dimension(180, 300));
            product[i].setLayout(new BoxLayout(product[i], BoxLayout.Y_AXIS));
            product[i].setAlignmentY(TOP_ALIGNMENT);

            ImageIcon icon;
            if (dsSP.get(i).getTenHinh().length == 0) {
                icon = new ImageIcon("./src/images/t-shirt.png");
            } else {
                icon = new ImageIcon((String) "./src/images/" + dsSP.get(i).tenHinh[0]);
            }
            Image scaledImage = icon.getImage().getScaledInstance(180, 210, Image.SCALE_SMOOTH);
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

            String gia = FormatInt.format(dsSP.get(i).getPrice());
            JLabel productPrice = new JLabel("Giá: " + gia);
            productPrice.setAlignmentX(Component.CENTER_ALIGNMENT); // Căn giữa theo chiều ngang
            productPrice.setAlignmentY(Component.CENTER_ALIGNMENT); // Căn giữa theo chiều dọc
            product[i].add(productPrice);
            product[i].add(Box.createVerticalStrut(5));
            product[i].setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

            lblChiTietSP = new JLabel("Xem chi tiết", JLabel.CENTER);
            lblChiTietSP.setMinimumSize(new Dimension(100, 30));
            lblChiTietSP.setPreferredSize(new Dimension(100, 30));
            lblChiTietSP.setMaximumSize(new Dimension(100, 30));
            lblChiTietSP.setBackground(normal);
            lblChiTietSP.setOpaque(true);
            lblChiTietSP.setFont(font_family);
            lblChiTietSP.setForeground(Color.WHITE);
            lblChiTietSP.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            lblChiTietSP.setAlignmentX(Component.CENTER_ALIGNMENT); // Căn giữa theo chiều ngang
            lblChiTietSP.setAlignmentY(Component.CENTER_ALIGNMENT);
            lblChiTietSP.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    //gọi chi tiết sản phẩm ở đây
                    frame_chitietsanpham h = new frame_chitietsanpham(dsSP.get(index));
                }
            });

            product[i].add(lblChiTietSP);
            product[i].addMouseListener(this);
            panel.add(product[i]);

            if (i < product.length - 1) {
                panel.add(Box.createHorizontalStrut(10)); // Khoảng cách 10 pixel
            }
        }
        return panel;
    }

    public void refresh() {
        this.removeAll(); // Xóa tất cả các thành phần hiện tại
        dsSP = new ArrayList<>(); // Tải lại dữ liệu
        for (int i = 0; i < spBUS.getDsSP().size(); i++) {
            if (spBUS.getDsSP().get(i).getTrangThai() == 1) {
                dsSP.add(spBUS.getDsSP().get(i));
            }
        }
        JPanel mainPanel = initContent(dsSP); // Tạo lại JPanel chính
        this.add(mainPanel, BorderLayout.CENTER); // Thêm lại JPanel chính

        this.revalidate(); // Cập nhật lại giao diện
        this.repaint(); // Vẽ lại giao diện
    }

    public void EditSP(SanPhamDTO sp) {
        spBUS.set(sp);
        refresh();
        this.revalidate(); // Cập nhật lại giao diện
        this.repaint(); // Vẽ lại giao diện
    }

    public void AddSP(SanPhamDTO sp) {
        spBUS.add(sp);
        refresh();
        this.revalidate(); // Cập nhật lại giao diện
        this.repaint(); // Vẽ lại giao diện

    }

    public void DeleteSP() {
        spBUS.delete(selectedSP.getMaSP());
        selectedSP = new SanPhamDTO();
        refresh();

    }

    public void SearchSP(ArrayList<String> sp) {
        loaiSPBUS loaiBUS = new loaiSPBUS();
        String maLoai = sp.get(1);
        for (int i = 0; i < loaiBUS.getList().size(); i++) {
            if (loaiBUS.getList().get(i).getTENLOAI().equals(maLoai)) {
                maLoai = loaiBUS.getList().get(i).getMALOAI();
                break;
            }
        }

        String timkiem = sp.get(0);
        ArrayList<SanPhamDTO> dsNew = new ArrayList<>();
        if (timkiem.isEmpty()) {
            if (!maLoai.equals("Tất cả")) {
                for (int i = 0; i < dsSP.size(); i++) {
                    if (dsSP.get(i).getMaLoai().equals(maLoai)) {
                        dsNew.add(dsSP.get(i));
                    }
                }
            } else if (maLoai.equals("Tất cả")){
                dsNew = dsSP;
            }
        } else {
            ArrayList<SanPhamDTO> ds = new ArrayList<>();
            for (int i = 0; i < dsSP.size(); i++) {
                String maSP = dsSP.get(i).getMaSP().toLowerCase();
                String tenSP = dsSP.get(i).getTenSP().toLowerCase();
                timkiem = timkiem.toLowerCase();
                if (maSP.contains(timkiem) || tenSP.contains(timkiem)) {
                    ds.add(dsSP.get(i));
                }
            }
            if (!maLoai.equals("Tất cả")) {
                for (int i = 0; i < ds.size(); i++) {
                    if (ds.get(i).getMaLoai().equals(maLoai)) {
                        dsNew.add(ds.get(i));
                    }
                }
            } else {
                dsNew = ds;
            }
        }
        this.removeAll(); // Xóa tất cả các thành phần hiện tại
        JPanel mainPanel = initContent(dsNew); // Tạo lại JPanel chính

        this.add(mainPanel, BorderLayout.CENTER); // Thêm lại JPanel chính

        this.revalidate(); // Cập nhật lại giao diện

        this.repaint(); // Vẽ lại giao diện
    }

    public void clear(int x) {
        for (int i = 0; i < dsSP.size(); i++) {
            if (i != x) {
                product[i].setBorder(null);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource().getClass() == JPanel.class) {
            JPanel pn = (JPanel) e.getSource();
            for (int i = 0; i < dsSP.size(); i++) {
                if (pn == product[i]) {
                    selectedSP = dsSP.get(i);
                    Border lineBorder = BorderFactory.createLineBorder(Color.LIGHT_GRAY, 4); // Đường viền ngoài
                    pn.setBorder(lineBorder);
                    clear(i);
                }
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    //     for (int i = 0; i < dsSP.size(); i++){
    //         if (e.getSource() == product[i]){
    //             product[i].setBackground(Color.white);
    //             frame_chitietsanpham h = new frame_chitietsanpham(dsSP.get(i));
    //         }
    // }
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