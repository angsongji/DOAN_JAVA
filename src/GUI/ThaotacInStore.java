package GUI;

import BUS.SanPhamBUS;
import BUS.TaiKhoanBUS;
import java.awt.Font;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Cursor;
import java.awt.Component;

import javax.swing.SwingConstants;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;
import java.util.ArrayList;
import BUS.chitietquyenBUS;
import BUS.loaiSPBUS;
import BUS.nhacungcapBUS;
import BUS.quyenBUS;
import DAO.chitietquyenDAO;
import DTO.chitietquyenDTO;
import DTO.chucnangDTO;
import DAO.chucnangDAO;
import DTO.TaiKhoanDTO;
import DTO.nhacungcapDTO;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.TableCellEditor;

public class ThaotacInStore extends JPanel implements MouseListener {

    private ArrayList<chitietquyenDTO> listHanhdong;
    private ArrayList<String> titleHanhdong;
    // private String[] titleThaotac = {"Thêm", "Sửa", "Xóa", "Import Excel", "Export Excel", "In PDF"};
    private Font font_title;
    private String MACHUCNANG;
    private String MAQUYEN;
    private Component pageContent;
    private StoreScreen s;
    //kích thước trang tai khoan
    int widthTK;
    int heightTK;

    public ThaotacInStore(String MACHUCNANG, String MAQUYEN, Component pageContent, StoreScreen s) {
        this.s = s;
        this.pageContent = pageContent;
        this.MACHUCNANG = MACHUCNANG;
        this.MAQUYEN = MAQUYEN;
        listHanhdong = new ArrayList<>();
        font_title = new Font("Tahoma", Font.PLAIN, 14);
        init();

    }

    public ThaotacInStore(String MACHUCNANG, String MAQUYEN, Component pageContent) {
        this.pageContent = pageContent;
        this.MACHUCNANG = MACHUCNANG;
        this.MAQUYEN = MAQUYEN;
        listHanhdong = new ArrayList<>();
        font_title = new Font("Tahoma", Font.PLAIN, 14);
        init();

    }

    private void init() {

        //setPreferredSize(new Dimension(chieurong, 150));
        listHanhdong = listHANHDONG(MACHUCNANG, MAQUYEN);
        if (!listHanhdong.isEmpty()) {
            JPanel jp_title = new JPanel();
            jp_title.setLayout(new BorderLayout());
            jp_title.setBackground(Cacthuoctinh_phuongthuc_chung.light_gray);
            jp_title.setOpaque(true);
            JLabel jl_title = new JLabel("<html><h1 style='font-family:Tahoma;font-weight:600;font-size:16;'>Thao tác</h1></html>");
            jp_title.add(jl_title, BorderLayout.WEST);

            JPanel x = new JPanel();

            x.setLayout(new FlowLayout(3, 5, 0));
            x.setBackground(Cacthuoctinh_phuongthuc_chung.light_gray);
            x.setOpaque(true);
//        x.setPreferredSize(new Dimension(chieurong, 80));
            for (int i = 0; i < listHanhdong.size(); i++) {
                String pathImangeIcon = "./src/images/";
                switch (listHanhdong.get(i).getHANHDONG()) {
                    case "Thêm":
                        pathImangeIcon += "add_icon.png";
                        break;
                    case "Sửa":
                        pathImangeIcon += "edit_icon.png";
                        break;
                    case "Xóa":
                        pathImangeIcon += "remove_icon.png";
                        break;
                    case "Import Excel":
                        pathImangeIcon += "import_icon.png";
                        break;
                    case "Export Excel":
                        pathImangeIcon += "export_icon.png";
                        break;
                    case "In PDF":
                        pathImangeIcon += "pdf_icon.png";
                        break;

                }
                hanhdongGUI thaotac = new hanhdongGUI(listHanhdong.get(i).getHANHDONG(), pathImangeIcon);
                thaotac.setName(listHanhdong.get(i).toString());
                thaotac.addMouseListener(this);
                x.add(thaotac);
            }
            setLayout(new FlowLayout(3, 0, 0));
            //setBackground(Cacthuoctinh_phuongthuc_chung.light_gray);
            jp_title.setPreferredSize(new Dimension((int) jl_title.getPreferredSize().getWidth() + 10, (int) jl_title.getPreferredSize().getHeight()));
            add(jp_title);
            x.setPreferredSize(new Dimension((int) x.getPreferredSize().getWidth(), 70));
            add(x);
            setBackground(Cacthuoctinh_phuongthuc_chung.light_gray);
            setOpaque(true);
        }
    }

    private ArrayList<chitietquyenDTO> listHANHDONG(String MACHUCNANG, String MAQUYEN) {
        ArrayList<chitietquyenDTO> hanhdong = new ArrayList<>();
        if (!MACHUCNANG.contains("NULL")) {
            System.out.println("MACHUCNANG" + MACHUCNANG);
            chitietquyenDAO ctqDAO = new chitietquyenDAO();
            String query = "SELECT * FROM chitietquyen WHERE MAQUYEN='" + MAQUYEN + "' AND MACHUCNANG='" + MACHUCNANG + "'";

            ArrayList<chitietquyenDTO> listChitietquyen = ctqDAO.searchReturnArray(query);
            for (chitietquyenDTO i : listChitietquyen) {
                if (!i.getHANHDONG().equals("Xem") && !(i.getMACHUCNANG().equals("HD") && i.getHANHDONG().equals("Thêm"))) {
                    hanhdong.add(i);
                }
            }
        }
        if (MACHUCNANG.equals("HD")) {
            hanhdong.add(new chitietquyenDTO(MAQUYEN, MACHUCNANG, "In PDF"));
        }
        if (MACHUCNANG.equals("NCC")) {
            hanhdong.add(new chitietquyenDTO(MAQUYEN, MACHUCNANG, "Import Excel"));
        }
        return hanhdong;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        hanhdongGUI itemClicked = (hanhdongGUI) e.getSource();
        String chitietquyen = itemClicked.getName();
        chitietquyenDTO ctqDTO = new chitietquyenDTO(chitietquyen);
        switch (ctqDTO.getMACHUCNANG()) {
            case "TK": {
                chucnangTaikhoan cntk_p = (chucnangTaikhoan) pageContent;
                widthTK = (int) cntk_p.JP_contentCuaNameChucnangCon.getPreferredSize().getWidth();
                heightTK = (int) cntk_p.JP_contentCuaNameChucnangCon.getPreferredSize().getHeight();
                switch (ctqDTO.getHANHDONG()) {
                    case "Thêm":
                        TaiKhoanGUI a = new TaiKhoanGUI(widthTK, heightTK);
                        a.initPnThaoTacTK(450, 600);
                        a.initThem();
                        cntk_p.JP_contentCuaNameChucnangCon.removeAll();
                        cntk_p.JP_contentCuaNameChucnangCon.add(a, BorderLayout.CENTER);
                        cntk_p.JP_contentCuaNameChucnangCon.revalidate();
                        cntk_p.JP_contentCuaNameChucnangCon.repaint();
                        cntk_p.tkGUI = a;
                        break;
                    case "Sửa":
                        if (cntk_p.tkGUI.selectedTK.getMaNV() == null) {
                            JOptionPane.showMessageDialog(null,
                                    "Xin vui lòng chọn tài khoản cần sửa !", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                        } else {
                            TaiKhoanGUI b = new TaiKhoanGUI(widthTK, heightTK);
                            b.selectedTK = cntk_p.tkGUI.selectedTK;
                            b.initPnThaoTacTK(450, 600);
                            b.initSua();
                            cntk_p.JP_contentCuaNameChucnangCon.removeAll();
                            cntk_p.JP_contentCuaNameChucnangCon.add(b, BorderLayout.CENTER);
                            cntk_p.JP_contentCuaNameChucnangCon.revalidate();
                            cntk_p.JP_contentCuaNameChucnangCon.repaint();
                            cntk_p.tkGUI = b;
                        }
                        break;
                    case "Xóa":
                        if (cntk_p.tkGUI.selectedTK.getMaNV() == null) {
                            JOptionPane.showMessageDialog(null,
                                    "Xin vui lòng chọn tài khoản cần xoá !", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                        } else {
                            int result = JOptionPane.showConfirmDialog(
                                    null,
                                    "Bạn có chắc chắn muốn xoá tài khoản này ?", // Nội dung thông báo
                                    "Xác nhận xoá", // Tiêu đề
                                    JOptionPane.YES_NO_OPTION, // Tùy chọn Yes/No
                                    JOptionPane.QUESTION_MESSAGE // Biểu tượng dấu hỏi
                            );

                            // Xử lý kết quả
                            if (result == JOptionPane.YES_OPTION) {
                                TaiKhoanGUI b = new TaiKhoanGUI(widthTK, heightTK);
                                b.selectedTK = cntk_p.tkGUI.selectedTK;
                                b.DeleteTK();
                                cntk_p.JP_contentCuaNameChucnangCon.removeAll();
                                cntk_p.JP_contentCuaNameChucnangCon.add(b, BorderLayout.CENTER);
                                cntk_p.JP_contentCuaNameChucnangCon.revalidate();
                                cntk_p.JP_contentCuaNameChucnangCon.repaint();
                                cntk_p.tkGUI = b;
                                JOptionPane.showMessageDialog(null,
                                        "Bạn đã xoá tài khoản thành công!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                            }
                        }
                        break;
                }
                break;
            }

            case "PQ": {
                thaotacPQ(ctqDTO.getHANHDONG(), itemClicked);
                break;
            }
            case "NCC": {
                thaotacNCC(ctqDTO.getHANHDONG(), itemClicked);
                break;
            }
            case "LOAI": {
                thaotacLOAI(ctqDTO.getHANHDONG(), itemClicked);
                break;
            }
            case "SP": {
                thaotacSP(ctqDTO.getHANHDONG(), itemClicked);
            }
        }
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException(
                "Unimplemented method 'mouseClicked'");
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
        JPanel item = (JPanel) e.getSource();
        item.setBackground(Cacthuoctinh_phuongthuc_chung.darkness_blue);
        item.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        Component[] components = item.getComponents();
        for (Component component : components) {
            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                label.setForeground(Color.WHITE);
            }
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        JPanel item = (JPanel) e.getSource();
        item.setBackground(Color.WHITE);
        Component[] components = item.getComponents();
        for (Component component : components) {
            if (component instanceof JLabel) {
                JLabel label = (JLabel) component;
                label.setForeground(Color.BLACK);
            }
        }
    }

    public void thaotacSP(String hanhdong, hanhdongGUI itemClicked) {
        SanPhamGUI spGUI = (SanPhamGUI) pageContent;
        SanPhamBUS spBUS = new SanPhamBUS();
        switch (hanhdong) {
            case "Sửa": {
                ChucNangSanPhamGUI sp = new ChucNangSanPhamGUI(spGUI, 500, 600);
                sp.initThem();
                break;
            }
            case "Xóa": {
                System.out.println(spGUI.selectedSP.getMaSP());
                if (spGUI.selectedSP.getMaSP() == null) {
                    JOptionPane.showMessageDialog(null,
                            "Xin vui lòng chọn sản phẩm cần xoá !", "Cảnh báo", JOptionPane.WARNING_MESSAGE);
                } else {
                    int result = JOptionPane.showConfirmDialog(
                            null,
                            "Bạn có chắc chắn muốn xoá sản phẩm này ?", // Nội dung thông báo
                            "Xác nhận xoá", // Tiêu đề
                            JOptionPane.YES_NO_OPTION, // Tùy chọn Yes/No
                            JOptionPane.QUESTION_MESSAGE // Biểu tượng dấu hỏi
                    );

                    // Xử lý kết quả
                    if (result == JOptionPane.YES_OPTION) {
                        spGUI.DeleteSP();
                        JOptionPane.showMessageDialog(null,
                                "Bạn đã xoá sản phẩm thành công!", "Thông báo", JOptionPane.WARNING_MESSAGE);
                    }
                }
                break;
            }

        }
    }

    public void thaotacPQ(String hanhdong, hanhdongGUI itemClicked) {
        phanquyen pq = (phanquyen) pageContent;
        quyenBUS qBUS = new quyenBUS();
        chitietquyenBUS ctqBUS = new chitietquyenBUS();
        switch (hanhdong) {
            case "Thêm":
                addQuyen addquyen = new addQuyen(pq);
                break;
            case "Sửa": {
                switch (itemClicked.title.getText()) {
                    case "Sửa":
                        //JOptionPane.showMessageDialog(null, "Double Click vào ô cần sửa thông tin\nKhông thể sửa đổi MANCC!\nTên nhà cung cấp không chứa chữ số và các kí tự đặc biệt\nSố điện thoại 10 số bắt đầu là số 0\nHoàn thành sửa đổi thì ấn nút Lưu");
                        JOptionPane.showMessageDialog(null, "Bạn đang thực hiện chỉnh sửa quyền\nẤn Lưu/Thoát khi đã hoàn tất chỉnh sửa!");
                        itemClicked.title.setText("Lưu/Thoát");
                        itemClicked.icon = new JLabel(new ImageIcon("./src/images/finish_icon.png"));
                        pq.updateTENQUYEN(pq.currentQuyen, 0);
                        pq.isEditingEnabled = true;
                        break;
                    case "Lưu/Thoát":
                        int r2 = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn lưu chỉnh sửa?", "Sửa quyền ", JOptionPane.YES_NO_OPTION);
                        if (r2 == JOptionPane.YES_OPTION) {
                            itemClicked.title.setText("Sửa");
                            itemClicked.icon = new JLabel(new ImageIcon("./src/images/edit_icon.png"));
                            System.out.println("Quyen truoc khi sua" + pq.currentQuyen.toString());
                            pq.updateTENQUYEN(pq.currentQuyen, 1);
                            System.out.println("Quyen sau khi sua" + pq.currentQuyen.toString());
                            qBUS.updateTENQUYEN(pq.currentQuyen);
                            ctqBUS.updateChitietquyen(pq.getListUpdateCtqTheoMAUQYEN(), pq.currentQuyen.getMAQUYEN());
                            pq.isEditingEnabled = false;
                            JOptionPane.showMessageDialog(null, "Lưu chỉnh sửa thành công!\nĐăng nhập lại để xem những thay đổi vừa lưu!");
                            s.dispose();
                            LoginUI l = new LoginUI();
                        } else {
                            int r2_1 = JOptionPane.showConfirmDialog(null, "Bạn có muốn tiếp tục chỉnh sửa?", "Sửa quyền ", JOptionPane.YES_NO_OPTION);
                            if (r2_1 == JOptionPane.NO_OPTION) {
                                int r2_2 = JOptionPane.showConfirmDialog(null, "Những sửa đổi sẽ không được lưu sau khi bạn thoát!!\nBạn có chắc chắn thoát chỉnh sửa??", "Thoát", JOptionPane.YES_NO_OPTION);
                                if (r2_2 == JOptionPane.YES_OPTION) {

                                    itemClicked.title.setText("Sửa");
                                    itemClicked.icon = new JLabel(new ImageIcon("./src/images/edit_icon.png"));
                                    pq.updateTENQUYEN(pq.currentQuyen, 2);

                                    pq.isEditingEnabled = false;
                                }
                            }
                        }

                        break;
                }
                break;
            }
            case "Xóa": {
                int r2 = JOptionPane.showConfirmDialog(null, "Chỉ có thể xóa quyền này khi không tồn tại tài khoản thuộc quyền này\nBạn có chắc chắn muốn xóa quyền đang chọn?", "Xóa quyền ", JOptionPane.YES_NO_OPTION);
                if (r2 == JOptionPane.YES_OPTION) {
                    System.out.println(pq.currentQuyen);
                    System.out.println(qBUS.checkCanDelete(pq.currentQuyen));
                    if (qBUS.checkCanDelete(pq.currentQuyen)) {
                        qBUS.delete(pq.currentQuyen.getMAQUYEN());
                        pq.deleteJP_NameQuyen(pq.currentQuyen);

                        System.out.println("tencurrent quyen sau khi xoa tren thanh lau chon: " + pq.currentQuyen.toString());

                        JOptionPane.showMessageDialog(null, "Xóa quyền thành công");
                    } else {
                        JOptionPane.showMessageDialog(null, "Xóa thất bại do có tài khoản thuộc quyền này");
                    }
                }
                break;

            }
        }

    }

    public void thaotacNCC(String hanhdong, hanhdongGUI itemClicked) {
        nhacungcapGUI nccGUI = (nhacungcapGUI) pageContent;
        nhacungcapBUS nccBUS = new nhacungcapBUS();
        switch (hanhdong) {

            case "Thêm": {

                addNhacungcapGUI addNCC = new addNhacungcapGUI(nccGUI);

                break;
            }
            case "Sửa": {
                switch (itemClicked.title.getText()) {
                    case "Sửa":
                        JOptionPane.showMessageDialog(null, "Double Click vào ô cần sửa thông tin\nKhông thể sửa đổi MANCC!\nTên nhà cung cấp không chứa chữ số và các kí tự đặc biệt\nSố điện thoại 10 số bắt đầu là số 0\nHoàn thành sửa đổi thì ấn nút Lưu");

                        itemClicked.title.setText("Lưu/Thoát");
                        itemClicked.icon = new JLabel(new ImageIcon("./src/images/finish_icon.png"));

                        nccGUI.isEditingEnabled = true;
                        break;
                    case "Lưu/Thoát":
                        TableCellEditor cellEditor = nccGUI.table.getCellEditor();
                        if (cellEditor != null) {
                            // Loại bỏ trình chỉnh sửa, gián đoạn việc chỉnh sửa
                            cellEditor.cancelCellEditing();
                        }

                        int r1 = JOptionPane.showConfirmDialog(null, "kiểm tra chắc chắn:\nTên nhà cung cấp không chứa số và các kí tự đặc biệt\nSố điện thoại 10 số bắt đầu là số 0\nNếu có dữ liệu để trống hoặc nhập sai dữ liệu, tất cả dữ liệu sẽ không được lưu thay đổi\nBạn có chắc chắn lưu?", "Sửa nhà cung cấp ", JOptionPane.YES_NO_OPTION);
                        if (r1 == JOptionPane.YES_OPTION) {
                            if (nccBUS.checkNewListNCC(nccGUI.getListNCC())) {
                                nccGUI.addDataInTable(nccBUS.getList());
                                JOptionPane.showMessageDialog(null, "Sửa thành công");
                                nccBUS.updateInSQL();
                            } else {
                                nccGUI.addDataInTable(nccBUS.getList());
                                JOptionPane.showMessageDialog(null, "Sửa thất bại do có ô không khớp kiểu dữ liệu");
                            }

                            itemClicked.title.setText("Sửa");
                            itemClicked.icon = new JLabel(new ImageIcon("./src/images/edit_icon.png"));
                            nccGUI.isEditingEnabled = false;
                        } else {
                            int r11 = JOptionPane.showConfirmDialog(null, "Bạn có muốn tiếp tục sửa?", "Sửa nhà cung cấp ", JOptionPane.YES_NO_OPTION);
                            if (!(r11 == JOptionPane.YES_OPTION)) {
                                int r111 = JOptionPane.showConfirmDialog(null, "Những dữ liệu sẽ không được lưu sau khi thoát\nBạn có chắc chắn thoát?", "Thoát", JOptionPane.YES_NO_OPTION);
                                if (r111 == JOptionPane.YES_OPTION) {
                                    nccGUI.addDataInTable(nccBUS.getList());
                                    itemClicked.title.setText("Sửa");
                                    itemClicked.icon = new JLabel(new ImageIcon("./src/images/edit_icon.png"));
                                    nccGUI.isEditingEnabled = false;
                                }
                            }
                        }

                        break;

                }

                break;
            }
            case "Xóa": {
                switch (itemClicked.title.getText()) {
                    case "Xóa":
                        JOptionPane.showMessageDialog(null, "Để chọn nhiều ô cần xóa:\nKéo chuột\nGiữ Ctrl và click vào các ô cần xóa");
                        itemClicked.title.setText("Lưu/Thoát");
                        itemClicked.icon = new JLabel(new ImageIcon("./src/images/finish_icon.png"));
                        break;
                    case "Lưu/Thoát":
                        int r2 = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn xóa?\nHành động này sẽ không thể hoàn tác", "Xóa nhà cung cấp ", JOptionPane.YES_NO_OPTION);
                        if (r2 == JOptionPane.YES_OPTION) {
                            ArrayList<String> listDelete = nccGUI.getSelectedListNCC();
                            for (String i : listDelete) {
                                nccBUS.delete(i);
                                nccBUS.deleteInSQL(i);
                            }
                            nccGUI.addDataInTable(nccBUS.getList());
                            JOptionPane.showMessageDialog(null, "Xóa thành công");
                        }
                        itemClicked.title.setText("Xóa");
                        itemClicked.icon = new JLabel(new ImageIcon("./src/images/remove_icon.png"));
                        break;
                }
            }

        }
    }

    public void thaotacLOAI(String hanhdong, hanhdongGUI itemClicked) {
        loaiSPGUI loaiGUI = (loaiSPGUI) pageContent;
        loaiSPBUS loaiBUS = new loaiSPBUS();
        switch (hanhdong) {
            case "Thêm": {

                addLoaiSPGUI addNCC = new addLoaiSPGUI(loaiGUI);

                break;
            }
            case "Sửa": {
                switch (itemClicked.title.getText()) {
                    case "Sửa":
                        JOptionPane.showMessageDialog(null, "Double Click vào ô cần sửa thông tin\nKhông thể sửa đổi MALOAI!\nTên loại không chứa chữ số và các kí tự đặc biệt\nHoàn thành sửa đổi thì ấn nút Lưu");

                        itemClicked.title.setText("Lưu/Thoát");
                        itemClicked.icon = new JLabel(new ImageIcon("./src/images/finish_icon.png"));

                        loaiGUI.isEditingEnabled = true;
                        loaiGUI.changeStatusLoai();
                        break;
                    case "Lưu/Thoát":
                        TableCellEditor cellEditor = loaiGUI.table.getCellEditor();
                        if (cellEditor != null) {
                            // Loại bỏ trình chỉnh sửa, gián đoạn việc chỉnh sửa
                            cellEditor.cancelCellEditing();
                        }

                        int r1 = JOptionPane.showConfirmDialog(null, "kiểm tra chắc chắn:\nTên loại không chứa số và các kí tự đặc biệt\nNếu có dữ liệu để trống hoặc nhập sai dữ liệu, tất cả dữ liệu sẽ không được lưu thay đổi\nBạn có chắc chắn lưu?", "Sửa loại sản phẩm ", JOptionPane.YES_NO_OPTION);
                        if (r1 == JOptionPane.YES_OPTION) {
                            if (loaiBUS.checkNewListList(loaiGUI.getListLoai())) {
                                loaiGUI.addDataInTable(loaiBUS.getList());
                                JOptionPane.showMessageDialog(null, "Sửa thành công");
                                loaiBUS.updateInSQL();
                            } else {
                                loaiGUI.addDataInTable(loaiBUS.getList());
                                JOptionPane.showMessageDialog(null, "Sửa thất bại do có ô không khớp kiểu dữ liệu");
                            }

                            itemClicked.title.setText("Sửa");
                            itemClicked.icon = new JLabel(new ImageIcon("./src/images/edit_icon.png"));
                            loaiGUI.isEditingEnabled = false;
                        } else {
                            int r11 = JOptionPane.showConfirmDialog(null, "Bạn có muốn tiếp tục sửa?", "Sửa loại sản phẩm ", JOptionPane.YES_NO_OPTION);
                            if (!(r11 == JOptionPane.YES_OPTION)) {
                                int r111 = JOptionPane.showConfirmDialog(null, "Những dữ liệu sẽ không được lưu sau khi thoát\nBạn có chắc chắn thoát?", "Thoát", JOptionPane.YES_NO_OPTION);
                                if (r111 == JOptionPane.YES_OPTION) {
                                    loaiGUI.addDataInTable(loaiBUS.getList());
                                    itemClicked.title.setText("Sửa");
                                    itemClicked.icon = new JLabel(new ImageIcon("./src/images/edit_icon.png"));
                                    loaiGUI.isEditingEnabled = false;
                                }
                            }
                        }

                        break;

                }

                break;
            }
            case "Xóa": {
                switch (itemClicked.title.getText()) {
                    case "Xóa":
                        JOptionPane.showMessageDialog(null, "Để chọn nhiều ô cần xóa:\nKéo chuột\nGiữ Ctrl và click vào các ô cần xóa");
                        itemClicked.title.setText("Lưu/Thoát");
                        itemClicked.icon = new JLabel(new ImageIcon("./src/images/finish_icon.png"));
                        break;
                    case "Lưu/Thoát":
                        int r2 = JOptionPane.showConfirmDialog(null, "Bạn có chắc chắn xóa?\nHành động này sẽ không thể hoàn tác", "Xóa loại sản phẩm ", JOptionPane.YES_NO_OPTION);
                        if (r2 == JOptionPane.YES_OPTION) {
                            ArrayList<String> listDelete = loaiGUI.getSelectedListLoai();
                            for (String i : listDelete) {
                                loaiBUS.delete(i);
                                loaiBUS.deleteInSQL(i);
                            }
                            loaiGUI.addDataInTable(loaiBUS.getList());
                            JOptionPane.showMessageDialog(null, "Xóa thành công");
                        }
                        itemClicked.title.setText("Xóa");
                        itemClicked.icon = new JLabel(new ImageIcon("./src/images/remove_icon.png"));
                        break;
                }
            }
        }
    }

}
