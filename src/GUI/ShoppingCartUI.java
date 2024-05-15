/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package GUI;

/**
 *
 * @author ngotr
 */
import javax.swing.*;
import javax.swing.border.Border;
import DTO.chucnangDTO;
import BUS.BUS_qlkh;
import BUS.Hoadon_BUS;
import BUS.SanPhamBUS;
import BUS.chitietphieunhap_BUS;
import BUS.chitietsanpham_BUS;
import DAO.DAO_chitietsanpham;
import DTO.ChitietHD_DTO;
import DTO.Hoadon_DTO;
import DTO.SanPhamDTO;
import DTO.chitietsanpham_DTO;
import DAO.DAO_qlks;

import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.SimpleTimeZone;

import DTO.model_qlkh;

public class ShoppingCartUI extends JPanel {
    private JPanel cartPanel, headerPanel, endPanel;
    // private int panelWidth = 1000; // Độ rộng của mỗi panel đơn hàng
    // private int panelHeight = 170; // Độ cao của mỗi panel đơn hàng
    // private int verticalGap = 10; // Khoảng cách theo chiều dọc giữa các panel
    private SanPhamBUS spBUS = new SanPhamBUS();
    private ArrayList<SanPhamDTO> dsSP = new ArrayList<SanPhamDTO>();
    private ArrayList<SanPhamDTO> dsSP2 = new ArrayList<SanPhamDTO>();
    private ArrayList<chitietsanpham_DTO> dsctsp = new ArrayList<chitietsanpham_DTO>();
    public static ArrayList<chitietsanpham_DTO> dsctsptt = new ArrayList<chitietsanpham_DTO>();
    private ArrayList<ChitietHD_DTO> dscthd = new ArrayList<ChitietHD_DTO>();
    private double totalPrice = 0, discount = 0, finalPrice = 0;
    private JLabel totalPriceLabel, titleLabel, finalPriceLabel;
    private JTextField CustomerCodeField;
    private JCheckBox applyDiscountBox;
    private boolean discountFlag = false;
    private ArrayList<Boolean> checkBoxSPList = new ArrayList<Boolean>();
    private ArrayList<JCheckBox> checkBoxSPList1 = new ArrayList<JCheckBox>();
    private JLabel pointValueLabel;
    private int diemTL;
    // int sl=1;

    public ShoppingCartUI(int crong, int ccao, ArrayList<SanPhamDTO> dssptt, ArrayList<chitietsanpham_DTO> dsctsptt,
            int soluong, String maSize, String maNV) {
        // this.chucnang = chucnang;
        // int crong = chucnang.getCrong();
        // int heightJP_content = chucnang.getHeightJPContent();

        // setTitle("Giỏ hàng");
        // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dsSP = dssptt;
        this.dsctsptt = dsctsptt;
        for (SanPhamDTO s : dsSP) {
            System.out.println(
                    "1. MASP " + s.getMaSP()+ ", Ten san pham: " + s.getTenSP());
        }
        for (chitietsanpham_DTO ctsp : dsctsptt) {
            System.out.println(
                    "1. MASP " + ctsp.getMASP() + ", MASIZE " + ctsp.getMASIZE() + ", So luong: " + ctsp.getSoluong());
        }
        setPreferredSize(new Dimension(crong, ccao));
        setBackground(new Color(255, 255, 255));
        setLayout(new BorderLayout()); // Sử dụng null layout để có thể đặt vị trí và kích thước bằng tọa độ tuyệt đối

        // headerPanel
        headerPanel = new JPanel();
        headerPanel.setLayout(null);
        headerPanel.setPreferredSize(new Dimension(crong, 70));
        headerPanel.setBackground(Color.WHITE);
        add(headerPanel, BorderLayout.NORTH);

        JLabel CustomerCodeLabel = new JLabel("Mã KH");
        CustomerCodeLabel.setFont(new Font("Arial", Font.BOLD, 20));
        // titleLabel.setPreferredSize(new Dimension(crong - 500, 10));
        CustomerCodeLabel.setBounds(0, 0, 100, 80);
        // titleLabel.setPreferredSize(new Dimzension(200, 100));
        CustomerCodeLabel.setHorizontalAlignment(JLabel.CENTER);
        CustomerCodeLabel.setVerticalAlignment(JLabel.CENTER);
        CustomerCodeLabel.setForeground(new Color(10, 61, 98));
        headerPanel.add(CustomerCodeLabel);

        CustomerCodeField = new JTextField();
        CustomerCodeField.setBounds(100, 23, 100, 30);
        CustomerCodeField.setBorder(BorderFactory.createLineBorder(new Color(10, 61, 98), 2));
        CustomerCodeField.setFont(new Font("Arial", Font.PLAIN, 25));
        headerPanel.add(CustomerCodeField);

        JButton findCustomerButton = new JButton("Tìm kiếm");
        findCustomerButton.setBounds(210, 23, 100, 30);
        findCustomerButton.setForeground(new Color(96, 163, 188));
        findCustomerButton.setBackground(new Color(10, 61, 98));
        findCustomerButton.setFont(new Font("Arial", Font.PLAIN, 15));
        findCustomerButton.setFocusPainted(false);
        headerPanel.add(findCustomerButton);

        JPanel pointPanel = new JPanel();
        pointPanel.setLayout(null);
        pointPanel.setBounds(crong / 2 - 130, 25, 200, 30);
        pointPanel.setBackground(Color.WHITE);
        headerPanel.add(pointPanel);

        JLabel pointLabel = new JLabel("Điểm tích luỹ:");
        pointLabel.setBounds(0, 0, 150, 30);
        pointLabel.setHorizontalAlignment(JLabel.CENTER);
        pointLabel.setVerticalAlignment(JLabel.CENTER);
        pointLabel.setForeground(new Color(10, 61, 98));
        pointLabel.setFont(new Font("Arial", Font.BOLD, 20));
        pointPanel.add(pointLabel);

        pointValueLabel = new JLabel("");
        pointValueLabel.setBounds(150, 0, 60, 30);
        pointValueLabel.setForeground(new Color(10, 61, 98));
        pointValueLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        pointPanel.add(pointValueLabel);

        // closeButton = new JLabel("X");
        // closeButton.setFont(new Font("Arial", Font.BOLD, 30));
        // closeButton.setBounds(1350, 0, 30, 30);
        // closeButton.setForeground(new Color(10, 61, 98));
        // headerPanel.add(closeButton);
        // closeButton.addMouseListener(new MouseAdapter() {
        // @Override
        // public void mouseClicked(MouseEvent e) {
        // System.exit(0);
        // }
        // });

        // cartPanel
        cartPanel = new JPanel();
        cartPanel.setLayout(new GridBagLayout());
        cartPanel.setBackground(new Color(255, 255, 255));
        // cartPanel.setPreferredSize(new Dimension(crong -200, ccao));
        Border cartBorder = BorderFactory.createLineBorder(new Color(10, 61, 98), 3);
        cartPanel.setBorder(cartBorder);
        JScrollPane scrollPane = new JScrollPane(cartPanel);
        scrollPane.setPreferredSize(new Dimension(crong - 500, 700));
        // scrollPane.setBackground(new Color(10, 61, 98));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        // scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        add(scrollPane, BorderLayout.CENTER);

        // endPanel
        endPanel = new JPanel();
        endPanel.setLayout(null);
        endPanel.setPreferredSize(new Dimension(200, 150));
        endPanel.setBackground(Color.WHITE);
        add(endPanel, BorderLayout.SOUTH);

        JLabel discountLabel = new JLabel("Giảm giá:" + Integer.parseInt(new BigDecimal(discount).toBigInteger().toString()) + " VND");
        discountLabel.setBounds(50, 60, 300, 30);
        discountLabel.setForeground(new Color(10, 61, 98));
        discountLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        endPanel.add(discountLabel);

        applyDiscountBox = new JCheckBox("Sử dụng");
        applyDiscountBox.setBounds(crong / 2 + 110, 25, 100, 30);
        // applyDiscountBox.setPreferredSize(new Dimension(100, 50));
        // applyDiscountBox.setVerticalAlignment(JButton.TOP);
        applyDiscountBox.setBackground(Color.WHITE);
        applyDiscountBox.setForeground(new Color(10, 61, 98));
        applyDiscountBox.setFont(new Font("Arial", Font.PLAIN, 16));
        applyDiscountBox.setFocusPainted(false);
        headerPanel.add(applyDiscountBox);
        applyDiscountBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() == ItemEvent.SELECTED) {
                    discountFlag = true;
                    diemTL = Integer.parseInt(pointValueLabel.getText());
                    if (diemTL == 0) {
                        applyDiscountBox.setSelected(false);
                        JOptionPane.showMessageDialog(null, "Hiện đang có 0 điểm tích lũy", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        return;
                    }
                    discount = diemTL * 1000;
                    checkBoxForSP();
                } else {
                    discountFlag = false;
                    checkBoxForSP();
                    diemTL = 0;
                    discount = 0;
                }
                System.out.println(discountFlag);
                discountLabel.setText("Giảm giá: " + Integer.parseInt(new BigDecimal(discount).toBigInteger().toString()) + " VND");
                finalPriceLabel.setText("Thành tiền: " + Integer.parseInt(new BigDecimal(finalPrice).toBigInteger().toString()) + " VND");
            }
        });

        findCustomerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                applyDiscountBox.setSelected(false);
                BUS_qlkh Bus_qlkh = new BUS_qlkh();
                String maKH = CustomerCodeField.getText();
                if(maKH.equals("")){
                    pointValueLabel.setText("");
                }else{
                    diemTL = Bus_qlkh.getDiemTichLuy(maKH);
                pointValueLabel.setText("" + diemTL);
                }
                
            }
        });
        
        // applyDiscountBox.addActionListener(new ActionListener() {
        // @Override
        // public void actionPerformed(ActionEvent e) {
        // // Xử lý logic khi checkbox được chọn hoặc bỏ chọn
        // JCheckBox applyDiscountBox = (JCheckBox) e.getSource();
        // JLabel label = (JLabel) applyDiscountBox.getParent();
        // if (applyDiscountBox.isSelected()) {
        //
        // int dtl = Integer.parseInt(pointValueLabel.getText());
        // discount = dtl*1000;
        // } else {
        //
        // int dtl = Integer. parseInt(pointValueLabel.getText());
        // discount = dtl*0;
        // }
        // discountLabel.setText("Giảm giá: " + discount + " VND");
        // }
        // });

        // panel.add(checkBox, gbc);
        // panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        // panel.setName("Đơn hàng: " + sp.getMaSP());
        // return panel;

        // JTextField discountCodeField = new JTextField();
        // discountCodeField.setBounds(200, 20, 150, 30);
        // discountCodeField.setBorder(BorderFactory.createLineBorder(new Color(10, 61,
        // 98), 2));
        // discountCodeField.setFont(new Font("Arial", Font.PLAIN, 20));
        // endPanel.add(discountCodeField);

        JTextField PointField = new JTextField();
        PointField.setBounds(crong / 2, 20, 100, 30);
        PointField.setBorder(BorderFactory.createLineBorder(new Color(10, 61, 98), 2));
        PointField.setFont(new Font("Arial", Font.PLAIN, 20));
        // headerPanel.add(PointField);

        // JLabel CustomerCodeLabel = new JLabel("Mã khách hàng:");
        // CustomerCodeLabel.setBounds(800, 20, 150, 30);
        // CustomerCodeLabel.setForeground(new Color(10, 61, 98));
        // CustomerCodeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        // endPanel.add(CustomerCodeLabel);

        // JLabel pointLabel = new JLabel();
        // pointLabel.setText(Hoadon_BUS.getpointtichluy()+" ");
        // pointLabel.setBounds(950, 60, 100, 30);
        // endPanel.add(pointLabel);

        // applyDiscountBox.addActionListener(new ActionListener() {
        // @Override
        // public void actionPerformed(ActionEvent e) {
        // String discountCode = discountCodeField.getText();
        // // Thực hiện kiểm tra mã giảm giá và áp dụng

        // // Cập nhật tổng tiền và hiển thị lại
        // }
        // });

        // Tổng tiền
        totalPriceLabel = new JLabel("Tổng tiền:" + Integer.parseInt(new BigDecimal(totalPrice).toBigInteger().toString()) + " VND");
        totalPriceLabel.setBounds(50, 10, 500, 50);
        totalPriceLabel.setForeground(new Color(10, 61, 98));
        totalPriceLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        endPanel.add(totalPriceLabel);

        finalPriceLabel = new JLabel("Thành tiền:" + Integer.parseInt(new BigDecimal(finalPrice).toBigInteger().toString()));
        finalPriceLabel.setBounds(50, 95, 500, 40);
        finalPriceLabel.setForeground(new Color(10, 61, 98));
        finalPriceLabel.setFont(new Font("Arial", Font.PLAIN, 30));
        endPanel.add(finalPriceLabel);

        JCheckBox selectAllBox = new JCheckBox("Chọn tất cả");
        selectAllBox.setForeground(new Color(10, 61, 98));
        selectAllBox.setBackground(Color.WHITE);
        selectAllBox.setFont(new Font("Arial", Font.PLAIN, 18));
        selectAllBox.setBounds(crong-235, 5, 150, 40);
        selectAllBox.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                for (JCheckBox cb : checkBoxSPList1) {
                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        if (!cb.isSelected()) 
                            cb.doClick();
                    }
                    else {
                        if (cb.isSelected()) 
                            cb.doClick();
                        
                    }
                }
            }});

        endPanel.add(selectAllBox);


        // Thanh toán
        JButton payButton = new JButton("Thanh toán");
        payButton.setBounds(crong - 240, 85, 150, 50);
        payButton.setForeground(new Color(96, 163, 188));
        payButton.setBackground(new Color(10, 61, 98));
        payButton.setFont(new Font("Arial", Font.PLAIN, 20));
        payButton.setFocusPainted(false);
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Xử lý khi nút thanh toán được nhấn
                // StringBuilder selectedOrders = new StringBuilder();
                // totalPrice = 0; // Reset tổng tiền trước khi tính toán lại
                // Component[] components = cartPanel.getComponents();
                // for (Component component : components) {
                // if (component instanceof JPanel) {
                // JPanel panel = (JPanel) component;
                // Component[] innerComponents = panel.getComponents();
                // for (Component innerComponent : innerComponents) {
                // if (innerComponent instanceof JCheckBox) {
                // JCheckBox checkBox = (JCheckBox) innerComponent;
                // if (checkBox.isSelected()) {
                // selectedOrders.append(panel.getName()).append(", ");
                // // Lấy số tiền từ nhãn giá của đơn hàng và cộng vào tổng tiền
                // JLabel priceLabel = (JLabel) panel.getComponent(3); // Giả sử nhãn giá ở vị
                // trí 3
                // String priceString = priceLabel.getText().replace("Giá: ", "");
                // totalPrice += Double.parseDouble(new
                // BigDecimal(priceString).toBigInteger().toString());
                // totalPriceLabel.setText("Tổng tiền: " + totalPrice);
                // break;
                // }
                // }
                // }
                // }
                // }
                try {

                    if (dsSP2.size() > 0) {
                        String spMua = "";
                        int count = 0;
                        for (Hoadon_DTO hoadon_DTO : Hoadon_BUS.list()) {
                            if (hoadon_DTO.getMaHD().startsWith("HD")) {
                                count++;
                            }
                        }
                        count += 1;
                        String maHD = "HD" + count;
                        for (SanPhamDTO sp : dsSP2) {
                            spMua += sp.getTenSP() + "\n";
                            dssptt.remove(sp);
                            for (chitietsanpham_DTO ctsp : dsctsp) {
                                if (ctsp.getMASP().equals(sp.getMaSP())) { 
                                    ChitietHD_DTO cthd = new ChitietHD_DTO(maHD, sp.getMaSP(), sp.getTenSP(),
                                            ctsp.getMASIZE(), ctsp.getSoluong(), sp.getPrice(), totalPrice);
                                    dscthd.add(cthd);
                                    break;
                                }
                            }
                        }
                        payment(maHD, dscthd, Integer.parseInt(CustomerCodeField.getText()), maNV);
                        JOptionPane.showMessageDialog(null,
                                "Bạn đã thanh toán thành công cho các đơn hàng: \n" + spMua + "Tổng tiền: " +
                                        new BigDecimal(totalPrice - discount).toBigInteger().toString() + "VND");
                        selectAllBox.setSelected(false);
                        dsSP2.clear();
                        CustomerCodeField.setText("");
                        pointValueLabel.setText("0");
                        applyDiscountBox.setSelected(false);
                        totalPrice = 0;
                        finalPrice = 0;
                        discount = 0;
    
                        totalPriceLabel
                                .setText("Tổng tiền: " + new BigDecimal(totalPrice).toBigInteger().toString() + " VND");
                        finalPriceLabel
                                .setText("Thành tiền: " + new BigDecimal(finalPrice).toBigInteger().toString() + " VND");
                        pointValueLabel.setText("0"); /////****************************** */
                        discountLabel.setText("Giảm giá:");
                    } else {
                        JOptionPane.showMessageDialog(null, "Vui lòng chọn ít nhất một đơn hàng để thanh toán!");
                    }
                    refreshOrderPanel(dssptt);
                    
                
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Lỗi trong lúc thanh toán. Có thể chưa chọn khách hàng", "Lỗi", 
                    JOptionPane.ERROR_MESSAGE);
                }
        }});
        endPanel.add(payButton);

        // Tạo và thêm các đơn hàng vào giỏ hàng
        // dsSP = spBUS.getDsSP();
        refreshOrderPanel(dssptt);

        // pack();
        // setLocationRelativeTo(null); // Hiển thị cửa sổ ở trung tâm màn hình
    }

    private String getCurrentTimeStamp() {
        return new SimpleDateFormat("yyyy-MM-dd").format(new Date());
    }

    private String getCurrentTime() {
        return new SimpleDateFormat("HH:mm:ss").format(new Date());
    }

    private void checkBoxForSP() {
        boolean checkBoxSPFlag = false;
        boolean isDecreased = false;
        for (boolean isSPChecked : checkBoxSPList) {
            if (isSPChecked) {
                checkBoxSPFlag = true;
                break;
            }
            checkBoxSPFlag = false;
        }
        if (checkBoxSPFlag && discountFlag) {
            if (finalPrice - discount < 0) {
                JOptionPane.showMessageDialog(this, "Tổng số tiền thanh toán phải lớn hơn số tiền giảm giá", "Lỗi", JOptionPane.ERROR_MESSAGE);
                applyDiscountBox.setSelected(false);
                discount = 0;
                return;
            } else
                finalPrice -= discount;
                isDecreased = true;
        }
        else if (checkBoxSPFlag && !discountFlag && isDecreased)
            finalPrice += discount;
        else
            finalPrice = totalPrice;  
    }

    private void payment(String maHD, ArrayList<ChitietHD_DTO> dscthd, int maKH, String maNV) {
        Hoadon_DTO hoaDon = new Hoadon_DTO(maHD, getCurrentTimeStamp(), maKH, maNV, discount, finalPrice, getCurrentTime(),
                dscthd);
        if (Hoadon_BUS.addHoaDon(hoaDon)) {
            BUS_qlkh bus_qlkh = new BUS_qlkh();
            if (discountFlag) {
                bus_qlkh.setDTL(maKH, 0);
            } else
                bus_qlkh.increaseDTL(maKH, (int) finalPrice / 10000);
            JOptionPane.showMessageDialog(this, "Thêm hóa đơn thành công");

        }
        else
            JOptionPane.showMessageDialog(this, "Thêm hóa đơn thất bại");

    }

    private void refreshOrderPanel(ArrayList<SanPhamDTO> dssptt) {
        cartPanel.removeAll();
        for (int i = 0; i < dsctsptt.size(); i++) {
            for (int j = 0; j < dssptt.size(); j++) {
                if(dsctsptt.get(i).getMASP().equals(dssptt.get(j).getMaSP()))
                    addOrderToCart(createOrderPanel(dssptt.get(j),dsctsptt.get(i).getMASIZE(),dsctsptt.get(i).getSoluong()));
        }
        }
//        for (int i = 0; i < dssptt.size(); i++) {
//            addOrderToCart(createOrderPanel(dssptt.get(i)));
//        }
        revalidate();
        repaint();
    }

    private JPanel createOrderPanel(SanPhamDTO sp,String masizeluachon, int slluachon) {

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(new Color(255, 255, 255));
        panel.setBorder(null);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(20, 40, 10, 30); // Khoảng cách giữa các thành phần

        JLabel nameLabel = new JLabel("Sản phẩm: " + sp.getTenSP(), SwingConstants.LEFT);
        nameLabel.setForeground(new Color(10, 61, 98));
        nameLabel.setFont(new Font("Arial", Font.BOLD, 15));
        nameLabel.setPreferredSize(new Dimension(300, 30));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 8; // Số hàng mà label price chiếm
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(nameLabel, gbc);

        JLabel typeLabel = new JLabel("Loại: " + sp.getMaLoai());
        typeLabel.setForeground(new Color(10, 61, 98));
        typeLabel.setFont(new Font("Arial", Font.BOLD, 15));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(typeLabel, gbc);

        JLabel sizeLabel = new JLabel("Size: ");
        sizeLabel.setForeground(new Color(10, 61, 98));
        sizeLabel.setFont(new Font("Arial", Font.BOLD, 15));
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(sizeLabel, gbc);

        // JLabel quantityLabel = new JLabel("Số lượng: ");
        // quantityLabel.setForeground(new Color(10, 61, 98));
        // gbc.gridx = 2;
        // gbc.gridy = 3;
        // panel.add(quantityLabel, gbc);

        JLabel priceLabel = new JLabel("Giá: " + new BigDecimal(sp.getPrice()).toBigInteger());
        priceLabel.setFont(new Font("Arial", Font.BOLD, 18));
        priceLabel.setForeground(new Color(10, 61, 98));
        priceLabel.setPreferredSize(new Dimension(300, 30));
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 5;
        // gbc.gridheight = 4; // Số hàng mà label price chiếm
        // gbc.fill = GridBagConstraints.VERTICAL;
        panel.add(priceLabel, gbc);

        JLabel imageLabel = new JLabel();
        imageLabel.setPreferredSize(new Dimension(230, 250));
        ImageIcon imageIcon = new ImageIcon("./src/images/" + sp.getTenHinh()[0]);
        Image image = imageIcon.getImage().getScaledInstance(230, 250, Image.SCALE_SMOOTH);
        imageLabel.setIcon(new ImageIcon(image));
        imageLabel.setForeground(new Color(10, 61, 98));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 8; // Số hàng mà label ảnh chiếm
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(imageLabel, gbc);

        JLabel quantityLabel = new JLabel("Số lượng hiện có: ", JLabel.LEFT); // Giả sử số lượng ban đầu là 1
        quantityLabel.setForeground(new Color(10, 61, 98));
        // quantityLabel.setBackground(new Color(255, 255, 255));
        quantityLabel.setFont(new Font("Arial", Font.BOLD, 13));
        // quantityLabel.setBounds(0, 0, 150, 30);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridheight = 1;
        panel.add(quantityLabel, gbc);

        chitietsanpham_BUS ctsp_bus = new chitietsanpham_BUS();
        ArrayList<String> list = DAO_chitietsanpham.select_size(sp.getMaSP());
        String[] items = list.toArray(new String[list.size()]);
        JComboBox sizeComboBox = new JComboBox(items);
        sizeComboBox.setSelectedItem(masizeluachon);
        sizeComboBox.setForeground(new Color(10, 61, 98));
        // quantityLabel.setBackground(new Color(255, 255, 255));
        sizeComboBox.setPreferredSize(new Dimension(80, 30));
        sizeComboBox.setFont(new Font("Arial", Font.BOLD, 13));
        
        quantityLabel.setText(
                "Số lượng hiện có: " + ctsp_bus.getSoLuong(sp.getMaSP(), masizeluachon));
        // quantityLabel.setBounds(0, 0, 150, 30);
        gbc.gridx = 5;
        gbc.gridy = 2;
        gbc.gridheight = 1;
        
        JLabel quantityValueLabel = new JLabel(slluachon+"", JLabel.CENTER); // Giả sử số lượng ban đầu là 1
        sizeComboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                //
                // Get the source of the component, which is our combo
                // box.
                //
                JComboBox comboBox = (JComboBox) event.getSource();

                Object selected = comboBox.getSelectedItem();
                quantityLabel.setText("Số lượng hiện có: " + ctsp_bus.getSoLuong(sp.getMaSP(), selected.toString()));
                quantityValueLabel.setText("1");
            }
        });
        panel.add(sizeComboBox, gbc);

        JPanel quantityValuePanel = new JPanel();
        quantityValuePanel.setLayout(null);
        quantityValuePanel.setPreferredSize(new Dimension(150, 30)); // Đặt kích thước cho panel số lượng
        gbc.gridx = 7;
        gbc.gridy = 4;

        
        quantityValueLabel.setForeground(new Color(10, 61, 98));
        quantityValueLabel.setBackground(new Color(255, 255, 255));
        quantityValueLabel.setFont(new Font("Arial", Font.BOLD, 15));
        quantityValueLabel.setBounds(50, 0, 50, 30);
        quantityValueLabel.setBorder(BorderFactory.createLineBorder(new Color(10, 61, 98), 2));
        quantityValuePanel.add(quantityValueLabel);

        JButton decreaseButton = new JButton("-");
        decreaseButton.setBackground(new Color(10, 61, 98));
        decreaseButton.setBounds(0, 0, 50, 30);
        decreaseButton.setForeground(new Color(255, 255, 255));
        decreaseButton.setFont(new Font("Arial", Font.BOLD, 20));
        decreaseButton.setBorder(BorderFactory.createLineBorder(new Color(10, 61, 98), 2));
        decreaseButton.setFocusPainted(false);
        quantityValuePanel.add(decreaseButton);
        // gbc.gridx = 0;
        // gbc.gridy = 0;
        // gbc.gridheight = 1; // Reset số hàng
        // gbc.fill = GridBagConstraints.NONE;
        // Xử lý sự kiện khi nút giảm số lượng được nhấn
        decreaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Xử lý logic giảm số lượng
                int currentQuantity = Integer.parseInt(quantityValueLabel.getText());
                if (currentQuantity > 1) {
                    currentQuantity--;
                    quantityValueLabel.setText(currentQuantity + "");
                }
            }
        });

        // gbc.gridx = 1;
        // gbc.gridy = 0;

        JButton increaseButton = new JButton("+");
        increaseButton.setBackground(new Color(10, 61, 98));
        increaseButton.setBounds(100, 0, 50, 30);
        increaseButton.setForeground(new Color(255, 255, 255));
        increaseButton.setFont(new Font("Arial", Font.BOLD, 15));
        increaseButton.setBorder(BorderFactory.createLineBorder(new Color(10, 61, 98), 2));
        increaseButton.setFocusPainted(false);
        quantityValuePanel.add(increaseButton);
        // Xử lý sự kiện khi nút tăng số lượng được nhấn
        increaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Xử lý logic tăng số lượng
                int currentQuantity = Integer.parseInt(quantityValueLabel.getText());
                String sb = new String(quantityLabel.getText());
                String[] str = sb.split(":");
                if (currentQuantity < Integer.parseInt(str[1].trim()))
                    currentQuantity++;
                quantityValueLabel.setText(currentQuantity + "");
            }
        });
        // gbc.gridx = 2;
        // gbc.gridy = 0;
        panel.add(quantityValuePanel, gbc);

        JCheckBox checkBox = new JCheckBox("Chọn thanh toán");

        JButton deleteOrderButton = new JButton("Xoá đơn hàng");
        deleteOrderButton.setBackground(new Color(10, 61, 98));
        deleteOrderButton.setForeground(new Color(255, 255, 255));
        deleteOrderButton.setFocusPainted(false);
        gbc.gridx = 8;
        gbc.gridy = 2;
        gbc.gridwidth = 2; // Số cột mà nút xoá chiếm
        gbc.gridheight = 8;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Xử lý sự kiện khi nút "Xoá đơn hàng" được nhấn
        deleteOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Xử lý logic xoá đơn hàng
                cartPanel.remove(panel); // Xoá panel đơn hàng khi nút "Xoá đơn hàng" được nhấn
                checkBoxSPList1.remove(checkBox);
                revalidate(); // Cập nhật giao diện
                repaint();
                int countMASPcuasp=0;
                for(chitietsanpham_DTO c : dsctsptt){
                    if(c.getMASP().equals(sp.getMaSP())){ 
                        if(c.getMASIZE().equals(masizeluachon)){
                            dsctsptt.remove(c);
                            view_chi_tiet_san_pham.dsctsptt.remove(c);
                        }
                            
                        countMASPcuasp++;
                    }
                }
                if(countMASPcuasp==1){
                    for(SanPhamDTO s: dsSP){
                        if(s.getMaSP().equals(sp.getMaSP())){
                            dsSP.remove(s);
                            view_chi_tiet_san_pham.dssptt.remove(s);
                            
                        }
                            
                    }
                }
                
            }
        });
        panel.add(deleteOrderButton, gbc);

        
        checkBoxSPList1.add(checkBox);
        checkBox.setForeground(new Color(10, 61, 98));
        checkBox.setBackground(new Color(255, 255, 255));
        checkBox.setFocusPainted(false);
        gbc.gridy = 0;
        // Xử lý sự kiện khi checkbox được chọn
        checkBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Xử lý logic khi checkbox được chọn hoặc bỏ chọn
                JCheckBox checkBox = (JCheckBox) e.getSource();
                JPanel panel = (JPanel) checkBox.getParent();
                if (checkBox.isSelected()) {
                    quantityValueLabel.setBackground(new Color(245, 245, 245));
                    panel.setBackground(new Color(96, 163, 188));
                    checkBox.setBackground(new Color(96, 163, 188));
                    increaseButton.setEnabled(false);
                    decreaseButton.setEnabled(false);
                    deleteOrderButton.setEnabled(false);
                    sizeComboBox.setEnabled(false);
                    // xét đoạn này
                    BigDecimal bd = new BigDecimal(sp.getPrice());
                    int gia = Integer.parseInt(bd.toBigInteger().toString());
                    int sl = Integer.parseInt(quantityValueLabel.getText());
                    String size = sizeComboBox.getSelectedItem().toString();
                    totalPrice += gia * sl;
                    chitietsanpham_DTO ctsp = new chitietsanpham_DTO(sp.getMaSP(), size, sl);
                    dsctsp.add(ctsp);
                    dsSP2.add(sp);

                    checkBoxSPList.add(true);

                } else {
                    panel.setBackground(new Color(255, 255, 255)); // Khôi phục màu nền mặc định
                    checkBox.setBackground(new Color(255, 255, 255));
                    quantityValueLabel.setBackground(new Color(255, 255, 255));
                    increaseButton.setEnabled(true);
                    decreaseButton.setEnabled(true);
                    deleteOrderButton.setEnabled(true);
                    sizeComboBox.setEnabled(true);

                    BigDecimal bd = new BigDecimal(sp.getPrice());
                    int gia = Integer.parseInt(bd.toBigInteger().toString());
                    int sl = Integer.parseInt(quantityValueLabel.getText());
                    totalPrice -= gia * sl;
                    for (SanPhamDTO sp1 : dsSP2) {
                        if (sp1.equals(sp))
                            for (chitietsanpham_DTO ctsp : dsctsp)
                                if (ctsp.getMASP().equals(sp.getMaSP())) {
                                    dsctsp.remove(ctsp);
                                    break;
                                }
                    }
                    dsSP2.remove(sp);

                    int sl1 = checkBoxSPList.size() - 1;
                    if (sl1 >= 0)
                        checkBoxSPList.remove(sl1);
                }
                
                finalPrice = totalPrice;
                checkBoxForSP();
                totalPriceLabel.setText("Tổng tiền: " + Integer.parseInt(new BigDecimal(totalPrice).toBigInteger().toString()) + " VND");
                finalPriceLabel.setText("Thành tiền: " + Integer.parseInt(new BigDecimal(finalPrice).toBigInteger().toString()) + " VND");
            }
        });
        panel.add(checkBox, gbc);
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.setName("Đơn hàng: " + sp.getMaSP());
        return panel;
    }

    // Thêm panel đơn hàng vào giỏ hàng
    private void addOrderToCart(JPanel orderPanel) {
        GridBagConstraints gbcNew = new GridBagConstraints();
        gbcNew.gridx = 0;
        gbcNew.gridy = cartPanel.getComponentCount();
        gbcNew.anchor = GridBagConstraints.WEST;
        gbcNew.insets = new Insets(20, 10, 10, 10);
        // int numberOfOrders = cartPanel.getComponentCount();
        // orderPanel.setBounds(50, (panelHeight + verticalGap) * numberOfOrders + 10,
        // panelWidth, panelHeight);
        cartPanel.add(orderPanel, gbcNew);
        // revalidate();
        // repaint();
        // System.out.println(numberOfOrders);
    }

    // public static void main(String[] args) {
    // SwingUtilities.invokeLater(() -> {
    // ShoppingCartUI shoppingCart = new ShoppingCartUI();
    // shoppingCart.setVisible(true);
    // });
    // }
}