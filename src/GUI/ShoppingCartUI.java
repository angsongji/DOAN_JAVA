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

import BUS.SanPhamBUS;
import DTO.SanPhamDTO;

import java.awt.*;
import java.awt.event.*;
import java.math.BigDecimal;
import java.util.ArrayList;

public class ShoppingCartUI extends JPanel {
    private JPanel cartPanel, headerPanel, endPanel, quantityValuePanel;
    // private int panelWidth = 1000; // Độ rộng của mỗi panel đơn hàng
    // private int panelHeight = 170; // Độ cao của mỗi panel đơn hàng
    // private int verticalGap = 10; // Khoảng cách theo chiều dọc giữa các panel
    private SanPhamBUS spBUS = new SanPhamBUS();
    private ArrayList<SanPhamDTO> dsSP = new ArrayList<SanPhamDTO>();
    private double totalPrice = 0.0; // Tổng tiền
    private JLabel totalPriceLabel, titleLabel, closeButton;
    private int chieurong, chieucao;
    //int sl=1;

    public ShoppingCartUI() {
//        setTitle("Giỏ hàng");
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(chieurong, chieucao));
        setBackground(new Color(255, 255, 255));
        setLayout(null); // Sử dụng null layout để có thể đặt vị trí và kích thước bằng tọa độ tuyệt đối

        //headerPanel
        headerPanel = new JPanel();
        headerPanel.setLayout(null);
        headerPanel.setBounds(0, 0, 1300, 70);
        headerPanel.setBackground(new Color(96, 163, 188));

        titleLabel = new JLabel("Giỏ Hàng");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 50));
        titleLabel.setBounds(500, 0, 250, 50);
        titleLabel.setForeground(new Color(10, 61, 98));
        headerPanel.add(titleLabel);

        // closeButton = new JLabel("X");
        // closeButton.setFont(new Font("Arial", Font.BOLD, 30));
        // closeButton.setBounds(1350, 0, 30, 30);
        // closeButton.setForeground(new Color(10, 61, 98));
        // headerPanel.add(closeButton);
        // closeButton.addMouseListener(new MouseAdapter() {
        //     @Override
        //     public void mouseClicked(MouseEvent e) {
        //         System.exit(0);
        //     }
        // });

        add(headerPanel);

        //cartPanel
        cartPanel = new JPanel();
        cartPanel.setLayout(new GridBagLayout());
        cartPanel.setBackground(new Color(255, 255, 255));
        Border cartBorder = BorderFactory.createLineBorder(new Color(10, 61, 98), 3);
        cartPanel.setBorder(cartBorder);
        JScrollPane scrollPane = new JScrollPane(cartPanel);
        scrollPane.setBounds(150, 70, 1000, 500);
        // scrollPane.setBackground(new Color(10, 61, 98));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        add(scrollPane);

        //endPanel
        endPanel = new JPanel();
        endPanel.setLayout(null);
        endPanel.setBounds(0, 70, 1300, 700);
        endPanel.setBackground(new Color(96, 163, 188));
        add(endPanel);

        // Mã giảm giá
        JLabel discountCodeLabel = new JLabel("Mã giảm giá:");
        discountCodeLabel.setBounds(300, 510, 150, 30);
        discountCodeLabel.setForeground(new Color(10, 61, 98));
        discountCodeLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        endPanel.add(discountCodeLabel);

        JTextField discountCodeField = new JTextField();
        discountCodeField.setBounds(300, 540, 200, 30);
        discountCodeField.setFont(new Font("Arial", Font.PLAIN, 20));
        endPanel.add(discountCodeField);

        JButton applyDiscountButton = new JButton("Áp dụng");
        applyDiscountButton.setBounds(520, 540, 100, 30);
        applyDiscountButton.setForeground(new Color(96, 163, 188));
        applyDiscountButton.setBackground(new Color(10, 61, 98));
        applyDiscountButton.setFont(new Font("Arial", Font.PLAIN, 16));
        applyDiscountButton.setFocusPainted(false);
        endPanel.add(applyDiscountButton);

        applyDiscountButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
            String discountCode = discountCodeField.getText();
        // Thực hiện kiểm tra mã giảm giá và áp dụng

        // Cập nhật tổng tiền và hiển thị lại
            }
        });

        // Thanh toán
        JButton payButton = new JButton("Thanh toán");
        payButton.setBounds(1050, 520, 150, 50);
        payButton.setForeground(new Color(96, 163, 188));
        payButton.setBackground(new Color(10, 61, 98));
        payButton.setFont(new Font("Arial", Font.PLAIN, 20));
        payButton.setFocusPainted(false);
        payButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Xử lý khi nút thanh toán được nhấn
                StringBuilder selectedOrders = new StringBuilder();
                totalPrice = 0.0; // Reset tổng tiền trước khi tính toán lại
                Component[] components = cartPanel.getComponents();
                for (Component component : components) {
                    if (component instanceof JPanel) {
                        JPanel panel = (JPanel) component;
                        Component[] innerComponents = panel.getComponents();
                        for (Component innerComponent : innerComponents) {
                            if (innerComponent instanceof JCheckBox) {
                                JCheckBox checkBox = (JCheckBox) innerComponent;
                                if (checkBox.isSelected()) {
                                    selectedOrders.append(panel.getName()).append(", ");
                                    // Lấy số tiền từ nhãn giá của đơn hàng và cộng vào tổng tiền
                                    JLabel priceLabel = (JLabel) panel.getComponent(4); // Giả sử nhãn giá ở vị trí 4
                                    String priceString = priceLabel.getText().replace("Giá: $", "");
                                    totalPrice += Double.parseDouble(priceString);
                                    break;
                                }
                            }
                        }
                    }
                }
                if (selectedOrders.length() > 0) {
                    selectedOrders.setLength(selectedOrders.length() - 2); // Loại bỏ dấu phẩy cuối cùng và khoảng trắng
                    JOptionPane.showMessageDialog(null, "Bạn đã thanh toán thành công cho các đơn hàng: " + selectedOrders.toString() + ".\nTổng tiền: $" + totalPrice);
                } else {
                    JOptionPane.showMessageDialog(null, "Vui lòng chọn ít nhất một đơn hàng để thanh toán!");
                }
            }
        });
        endPanel.add(payButton);

        // Tổng tiền
        totalPriceLabel = new JLabel("Tổng tiền:" + totalPrice);
        totalPriceLabel.setBounds(800, 530, 200, 50);
        totalPriceLabel.setForeground(new Color(10, 61, 98));
        totalPriceLabel.setFont(new Font("Arial", Font.PLAIN, 20));
        endPanel.add(totalPriceLabel);

        // Tạo và thêm các đơn hàng vào giỏ hàng
        dsSP = spBUS.getDsSP();
        for (int i = 0; i < dsSP.size(); i++) {
            addOrderToCart(createOrderPanel(dsSP.get(i)));
            revalidate();
            repaint();
        }

        // pack();
        // setLocationRelativeTo(null); // Hiển thị cửa sổ ở trung tâm màn hình
    }

    private JPanel createOrderPanel(SanPhamDTO sp) {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(null);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(20, 40, 10, 30); // Khoảng cách giữa các thành phần

        JLabel nameLabel = new JLabel("Sản phẩm: " + sp.getTenSP(), SwingConstants.LEFT);
        nameLabel.setForeground(new Color(10, 61, 98));
        nameLabel.setFont(new Font("Arial", Font.BOLD,15));
        nameLabel.setPreferredSize(new Dimension(300, 30));
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.gridwidth = 8; // Số hàng mà label price chiếm
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(nameLabel, gbc);

        JLabel typeLabel = new JLabel("Loại: " + sp.getMaLoai());
        typeLabel.setForeground(new Color(10, 61, 98));
        typeLabel.setFont(new Font("Arial", Font.BOLD,15));
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        panel.add(typeLabel, gbc);

        JLabel sizeLabel = new JLabel("Size: " + "L");
        sizeLabel.setForeground(new Color(10, 61, 98));
        sizeLabel.setFont(new Font("Arial", Font.BOLD,15));
        gbc.gridx = 1;
        gbc.gridy = 2;
        panel.add(sizeLabel, gbc);

        // JLabel quantityLabel = new JLabel("Số lượng: ");
        // quantityLabel.setForeground(new Color(10, 61, 98));
        // gbc.gridx = 2;
        // gbc.gridy = 3;
        // panel.add(quantityLabel, gbc);

        JLabel priceLabel = new JLabel("Giá: " + new BigDecimal(((sp.getPrice() ))).toBigInteger() + " VND"); // Giả sử giá cố định
        priceLabel.setFont(new Font("Arial", Font.BOLD, 18));
        priceLabel.setForeground(new Color(10, 61, 98));
        priceLabel.setPreferredSize(new Dimension(300, 30));
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.gridwidth = 5;
        // gbc.gridheight = 4; // Số hàng mà label price chiếm
        // gbc.fill = GridBagConstraints.VERTICAL;
        panel.add(priceLabel, gbc);

        sp.getTenHinh();
        JLabel imageLabel = new JLabel("Image");
        imageLabel.setPreferredSize(new Dimension(100, 70));
        // ImageIcon imageIcon = new ImageIcon(new ImageIcon(sp.getTenHinh().toString()).getImage().getScaledInstance(imageLabel.getWidth(), imageLabel.getHeight(), Image.SCALE_SMOOTH));
        imageLabel.setIcon(new ImageIcon());
        imageLabel.setForeground(new Color(10, 61, 98));
        imageLabel.setBorder(BorderFactory.createLineBorder(new Color(10, 61, 98), 2));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 1;
        gbc.gridheight = 8; // Số hàng mà label ảnh chiếm
        gbc.fill = GridBagConstraints.BOTH;
        panel.add(imageLabel, gbc);
        
        JPanel quantityValuePanel = new JPanel();
        quantityValuePanel.setLayout(null);
        quantityValuePanel.setPreferredSize(new Dimension(150, 30)); // Đặt kích thước cho panel số lượng
        gbc.gridx = 6;
        gbc.gridy = 4;


        JButton decreaseButton = new JButton("-");
        decreaseButton.setBackground(new Color(10, 61, 98));
        decreaseButton.setBounds(0, 0, 50, 30);
        decreaseButton.setForeground(new Color(255, 255, 255));
        decreaseButton.setFont(new Font("Arial", Font.BOLD, 20));
        decreaseButton.setBorder(BorderFactory.createLineBorder(new Color(10, 61, 98), 2));
        decreaseButton.setFocusPainted(false);
        // gbc.gridx = 0;
        // gbc.gridy = 0;
        // gbc.gridheight = 1; // Reset số hàng
        // gbc.fill = GridBagConstraints.NONE;
        quantityValuePanel.add(decreaseButton);

        
        
        JLabel quantityValueLabel = new JLabel("1", JLabel.CENTER); // Giả sử số lượng ban đầu là 1
        quantityValueLabel.setForeground(new Color(10, 61, 98));
        quantityValueLabel.setBackground(new Color(255, 255, 255));
        quantityValueLabel.setFont(new Font("Arial", Font.BOLD, 15));
        quantityValueLabel.setBounds(50, 0, 50, 30);
        quantityValueLabel.setBorder(BorderFactory.createLineBorder(new Color(10, 61, 98), 2));
        quantityValuePanel.add(quantityValueLabel);
        // gbc.gridx = 1;
        // gbc.gridy = 0;
        

        JButton increaseButton = new JButton("+");
        increaseButton.setBackground(new Color(10, 61, 98));
        increaseButton.setBounds(100, 0, 50, 30);
        increaseButton.setForeground(new Color(255, 255, 255));
        increaseButton.setFont(new Font("Arial", Font.BOLD,15));
        increaseButton.setBorder(BorderFactory.createLineBorder(new Color(10, 61, 98), 2));
        increaseButton.setFocusPainted(false);
        // gbc.gridx = 2;
        // gbc.gridy = 0;
        quantityValuePanel.add(increaseButton);
        
        panel.add(quantityValuePanel, gbc);

        JCheckBox checkBox = new JCheckBox("Chọn thanh toán");
        checkBox.setForeground(new Color(10, 61, 98));
        checkBox.setFocusPainted(false);
        gbc.gridx = 7;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Số cột mà checkbox chiếm
        gbc.fill = GridBagConstraints.HORIZONTAL;
        panel.add(checkBox, gbc);

        JButton deleteOrderButton = new JButton("Xoá đơn hàng");
        deleteOrderButton.setBackground(new Color(10, 61, 98));
        deleteOrderButton.setForeground(new Color(255, 255, 255));
        deleteOrderButton.setFocusPainted(false);
        gbc.gridy = 2;
        gbc.gridwidth = 2; // Số cột mà nút xoá chiếm
        panel.add(deleteOrderButton, gbc);

        // Xử lý sự kiện khi nút giảm số lượng được nhấn
        decreaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Xử lý logic giảm số lượng
                int currentQuantity = Integer.parseInt(quantityValueLabel.getText());
                if (currentQuantity > 1) {
                    currentQuantity--;
                    quantityValueLabel.setText(String.valueOf(currentQuantity));
                }
            }
        });

        // Xử lý sự kiện khi nút tăng số lượng được nhấn
        increaseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Xử lý logic tăng số lượng
                int currentQuantity = Integer.parseInt(quantityValueLabel.getText());
                currentQuantity++;
                quantityValueLabel.setText(String.valueOf(currentQuantity));
            }
        });

        // Xử lý sự kiện khi checkbox được chọn
        checkBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Xử lý logic khi checkbox được chọn hoặc bỏ chọn
                JCheckBox checkBox = (JCheckBox) e.getSource();
                JPanel panel = (JPanel) checkBox.getParent();
                if (checkBox.isSelected()) {
                    quantityValueLabel.setBackground(new Color(245,245,245));
                    panel.setBackground(new Color(96, 163, 188));
                    checkBox.setBackground(new Color(96, 163, 188));
                } else {
                    panel.setBackground(new Color(255, 255, 255)); // Khôi phục màu nền mặc định
                    quantityValueLabel.setBackground(new Color(255, 255, 255));
                    checkBox.setBackground(new Color(255, 255, 255));
                }
            }
        });

        // Xử lý sự kiện khi nút "Xoá đơn hàng" được nhấn
        deleteOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Xử lý logic xoá đơn hàng
                cartPanel.remove(panel); // Xoá panel đơn hàng khi nút "Xoá đơn hàng" được nhấn
                revalidate(); // Cập nhật giao diện
                repaint();
            }
        });
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        panel.setName("Đơn hàng #" + sp.getMaLoai());
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
        // orderPanel.setBounds(50, (panelHeight + verticalGap) * numberOfOrders + 10, panelWidth, panelHeight);
        cartPanel.add(orderPanel, gbcNew);
        // revalidate();
        //         repaint();
        // System.out.println(numberOfOrders);
    }

    // public static void main(String[] args) {
    //     SwingUtilities.invokeLater(() -> {
    //         ShoppingCartUI shoppingCart = new ShoppingCartUI();
    //         shoppingCart.setVisible(true);
    //     });
    // }
}