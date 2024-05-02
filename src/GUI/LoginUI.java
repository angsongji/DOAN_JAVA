package GUI;


import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class LoginUI {
    private JFrame frame;
    private JPanel mainPanel;
    private JPanel storePanel;
    private JPanel loginPanel;

    public LoginUI() {
        frame = new JFrame("Đăng nhập");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400); 
        frame.setLocationRelativeTo(null); 
        frame.setBackground(new Color(10, 61, 98));
        frame.setUndecorated(true);

        mainPanel = new JPanel(new BorderLayout());
        mainPanel.setPreferredSize(new Dimension(600, 400));
        mainPanel.setBackground(Color.WHITE);
        mainPanel.setBackground(new Color(10, 61, 98));

        createStorePanel();
        createLoginPanel();

        mainPanel.add(storePanel, BorderLayout.WEST);
        mainPanel.add(loginPanel, BorderLayout.EAST);

        frame.add(mainPanel);
        frame.pack();
        frame.setVisible(true);
    }

    private void createStorePanel() {
        storePanel = new JPanel();
        storePanel.setLayout(null);
        storePanel.setPreferredSize(new Dimension(300, 400));
        storePanel.setBackground(new Color(10, 61, 98));

       /* JPanel logoPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                int radius = Math.min(getWidth(), getHeight()) / 2;
                g.setColor(Color.WHITE);
                g.fillOval(getWidth() / 2 - radius, getHeight() / 2 - radius, 2 * radius, 2 * radius);
                g.setColor(Color.BLACK);
                g.drawOval(getWidth() / 2 - radius, getHeight() / 2 - radius, 2 * radius, 2 * radius);
            }
        };
        logoPanel.setBounds(100, 50, 100, 100); // Toạ độ và kích thước hình tròn
        logoPanel.setOpaque(false); */// Đặt thành true nếu muốn hiển thị nền màu của storePanel
        JPanel logoPanel = new JPanel();
        logoPanel.setBounds(50, 25, 200, 200); 
        logoPanel.setBackground(Cacthuoctinh_phuongthuc_chung.darkness_blue);
        logoPanel.setOpaque(true);
        logoPanel.add(new JLabel(Cacthuoctinh_phuongthuc_chung.storeLogoLogout));
        storePanel.add(logoPanel);

        // Tạo label cho tên cửa hàng và đặt vị trí
        JLabel storeNameLabel = new JLabel(Cacthuoctinh_phuongthuc_chung.storeName.toUpperCase());
        storeNameLabel.setFont(new Font(Cacthuoctinh_phuongthuc_chung.font_family, Font.BOLD, 20));
        storeNameLabel.setForeground(Color.WHITE);
        storeNameLabel.setBounds(100, 240, 200, 30);
        storePanel.add(storeNameLabel);

        mainPanel.add(storePanel, BorderLayout.CENTER);
    }

    private void createLoginPanel() {
        loginPanel = new JPanel();
        loginPanel.setLayout(null); // Sử dụng absolute positioning
        loginPanel.setPreferredSize(new Dimension(300, 400));
        loginPanel.setBackground(new Color(96, 163, 188));

        // Tạo các label và text fields và đặt vị trí
        JLabel loginLabel = new JLabel("ĐĂNG NHẬP");
        loginLabel.setForeground(new Color(10, 61, 98));
        loginLabel.setBounds(100, 25, 150, 50);
        loginLabel.setFont(new Font(Cacthuoctinh_phuongthuc_chung.font_family,Font.BOLD, 19));
        loginPanel.add(loginLabel);
    
        JLabel usernameLabel = new JLabel("Tên đăng nhập:");
        usernameLabel.setBounds(55, 80, 120, 30);
        usernameLabel.setForeground(new Color(10, 61, 98));
        usernameLabel.setFont(new Font(Cacthuoctinh_phuongthuc_chung.font_family,Font.BOLD,14));
        loginPanel.add(usernameLabel);

        JLabel passwordLabel = new JLabel("Mật khẩu:");
        passwordLabel.setBounds(55, 175, 120, 30);
        passwordLabel.setForeground(new Color(10, 61, 98));
        passwordLabel.setFont(new Font(Cacthuoctinh_phuongthuc_chung.font_family,Font.BOLD,14));
        loginPanel.add(passwordLabel);

        JTextField usernameField = new JTextField(15);
        usernameField.setBounds(55, 110, 180, 30);
        loginPanel.add(usernameField);        


        JPasswordField passwordField = new JPasswordField(15);
        passwordField.setBounds(55, 205, 180, 30);
        loginPanel.add(passwordField);

        JLabel noBlank1 = new JLabel("*Không được để trống!");
        noBlank1.setBounds(55, 140, 180, 30);
        noBlank1.setForeground(Cacthuoctinh_phuongthuc_chung.error);
        noBlank1.setFont(new Font(Cacthuoctinh_phuongthuc_chung.font_family,Font.PLAIN,13));
        loginPanel.add(noBlank1);
        JLabel noBlank2 = new JLabel("*Không được để trống!");
        noBlank2.setBounds(55, 235, 180, 30);
        noBlank2.setForeground(Cacthuoctinh_phuongthuc_chung.error);
        noBlank2.setFont(new Font(Cacthuoctinh_phuongthuc_chung.font_family,Font.PLAIN,13));
        loginPanel.add(noBlank2);

        // Tạo label "Bạn chưa có tài khoản?" có gạch chân và đặt vị trí
        JLabel registerLabel = new JLabel("<html><u>Bạn chưa có tài khoản?</u></html>");
       // registerLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        registerLabel.setBounds(30, 280, 150, 30); 
        loginPanel.add(registerLabel);

        // Tạo nút đăng ký và đặt vị trí
        JButton registerButton = new JButton("Đăng ký");
        registerButton.setFont(new Font(Cacthuoctinh_phuongthuc_chung.font_family,Font.PLAIN,13));
        registerButton.setBounds(180, 285, 80, 25);
        registerButton.setBackground(new Color(10, 61, 98));
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false);
        registerButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginPanel.add(registerButton);
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                SignUp s = new SignUp();
            }
        });

        // Tạo nút đăng nhập và đặt vị trí
        JButton loginButton = new JButton("XÁC NHẬN");
        loginButton.setBounds(100, 335, 100, 35);
        loginButton.setFont(new Font(Cacthuoctinh_phuongthuc_chung.font_family,Font.PLAIN,13));
        loginButton.setBackground(new Color(10, 61, 98));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusPainted(false);
        loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        loginPanel.add(loginButton);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                //Lay duoc doi tuong TaikhoanDTO sau do khoi tao StoreScreen voi tham so la TaikhoanDTO do
                StoreScreen s = new StoreScreen();
            }
        });

        JButton closeButton = new JButton("X");
        closeButton.setBounds(250, 0, 60, 30); 
        closeButton.setContentAreaFilled(true); 
        closeButton.setFont(new Font("Tahoma",Font.BOLD, 20));
        closeButton.setBackground(new Color(96, 163, 188));
        closeButton.setForeground(new Color(10, 61, 98));
        closeButton.setBorderPainted(false); // Xoá viền của nút 
        closeButton.setFocusPainted(false); // Xoá viền focus khi nhấn
        closeButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                closeButton.setForeground(Color.RED);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                closeButton.setForeground(new Color(10, 61, 98)); // Khi di chuột ra, xoá màu nền
            }
        });
        closeButton.addActionListener(e -> System.exit(0)); // Tắt ứng dụng khi nhấn nút tắt
        loginPanel.add(closeButton);

        mainPanel.add(loginPanel, BorderLayout.EAST);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(LoginUI::new);
    }
}


