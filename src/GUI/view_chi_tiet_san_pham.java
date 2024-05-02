package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.concurrent.Flow;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class view_chi_tiet_san_pham extends JPanel implements MouseListener {

    private JPanel pn, pn1, pn2, pc, pcL, pcr;
    private JPanel[] pc1, pc2;
    private JLabel[] jlc1, jlc2, jl_mls;
    private JLabel jln1, jln2;
    private int soluong;

    public view_chi_tiet_san_pham() {
    }

    public view_chi_tiet_san_pham(int w, int h) {
        soluong = 1;

        jlc1 = new JLabel[3];
        this.setSize(w, h);

        Font x = new Font("Arial", ALLBITS, 20);
        Font tensp = new Font("Arial", Font.BOLD, 17);
        Font giohang = new Font("Arial", Font.BOLD, 13);
        Font size = new Font("Arial", ALLBITS, 15);
        //*************** panel north ***********//
        pn = new JPanel();
        pn.setLayout(new GridLayout());
        pn.setPreferredSize(new Dimension(0, 30));

        pn1 = new JPanel();
        pn1.setLayout(new FlowLayout(0));
        pn1.setBackground(Color.decode("#60A3BC"));
        jln1 = new JLabel("Chi tiết sản phẩm");
        pn1.add(jln1);

        pn2 = new JPanel();
        pn2.setLayout(new FlowLayout(2, 0, 0));
        pn2.setBackground(Color.decode("#60A3BC"));
        jln2 = new JLabel("X", jln2.CENTER);
        jln2.setPreferredSize(new Dimension(40, 30));
        jln2.setFont(x);
        jln2.addMouseListener(this);
        pn2.add(jln2);
        pn.add(pn1);
        pn.add(pn2);
        //**************************panel center*****************//		
        pc = new JPanel();
        pc.setLayout(new GridLayout(1, 2));
        pcL = new JPanel();
        pcL.setLayout(new FlowLayout());

        pc1 = new JPanel[3];

        jlc1[0] = new JLabel();

        pc1[0] = new JPanel();
        pc1[0].setPreferredSize(new Dimension(300, 20));

        pc1[1] = new JPanel();
        pc1[1].setPreferredSize(new Dimension(300, 260));

        //*************** hình lớn ****************//
        pc1[1].add(jlc1[0]);
        jlc1[0] = new JLabel();
        jlc1[0].setPreferredSize(new Dimension(190, 250));
        jlc1[0].setBackground(Color.white);
        jlc1[0].setOpaque(true);
        pc1[1].add(jlc1[0]);

        pc1[2] = new JPanel();
        pc1[2].setPreferredSize(new Dimension(300, 40));
        pc1[2].setLayout(new FlowLayout(1, 25, 10));
        //*** 2 hình nhỏ **////
        jlc1[1] = new JLabel();
        jlc1[1].setPreferredSize(new Dimension(40, 30));
        jlc1[2] = new JLabel();
        jlc1[2].setPreferredSize(new Dimension(40, 30));
        jlc1[1].setBackground(Color.white);
        jlc1[2].setBackground(Color.white);
        jlc1[1].setOpaque(true);
        jlc1[2].setOpaque(true);
        pc1[2].add(jlc1[1]);
        pc1[2].add(jlc1[2]);

        pcL.add(pc1[0]);
        pcL.add(pc1[1]);
        pcL.add(pc1[2]);

        pcr = new JPanel();
        pc2 = new JPanel[7];
        jlc2 = new JLabel[9];
        pc2[0] = new JPanel();
        pc2[0].setPreferredSize(new Dimension(300, 20));
        pc2[1] = new JPanel();
        pc2[1].setPreferredSize(new Dimension(300, 260));
        pc2[1].setLayout(new GridLayout(5, 1));

//***************** 7 dòng bên phải********************		
        for (int i = 2; i < 7; i++) {

            pc2[i] = new JPanel();
            pc2[i].setLayout(new FlowLayout(0, 10, 0));
            pc2[i].setBorder(BorderFactory.createEmptyBorder(0, 30, 0, 0));

        }

        jlc2[0] = new JLabel("Tên sản phẩm");
        jlc2[0].setFont(tensp);
        pc2[2].add(jlc2[0]);

//		size S M L 
        jlc2[1] = new JLabel("M", JLabel.CENTER);
        jlc2[1].setPreferredSize(new Dimension(40, 30));
        jlc2[1].setBackground(Color.white);
        jlc2[1].setOpaque(true);
        jlc2[1].setFont(size);
        jlc2[1].addMouseListener(this);

        jlc2[2] = new JLabel("L", JLabel.CENTER);
        jlc2[2].setPreferredSize(new Dimension(40, 30));
        jlc2[2].setBackground(Color.white);
        jlc2[2].setOpaque(true);
        jlc2[2].setFont(size);
        jlc2[2].addMouseListener(this);

        jlc2[3] = new JLabel("XL", JLabel.CENTER);
        jlc2[3].setPreferredSize(new Dimension(40, 30));
        jlc2[3].setBackground(Color.white);
        jlc2[3].setOpaque(true);
        jlc2[3].setFont(size);
        jlc2[3].addMouseListener(this);

        pc2[3].add(jlc2[1]);
        pc2[3].add(jlc2[2]);
        pc2[3].add(jlc2[3]);

        jlc2[4] = new JLabel("1.000.000");
        jlc2[4].setFont(tensp);
        pc2[4].add(jlc2[4]);

        pc2[5].setLayout(new FlowLayout(0, 0, 0));
        pc2[5].setBorder(BorderFactory.createEmptyBorder(0, 40, 0, 0));

        jlc2[5] = new JLabel("-", JLabel.CENTER);
        jlc2[5].setPreferredSize(new Dimension(40, 20));
        jlc2[5].addMouseListener(this);
        jlc2[5].setBackground(Color.decode("#0A3D62"));
        jlc2[5].setOpaque(true);
        jlc2[5].setForeground(Color.white);
        jlc2[6] = new JLabel(soluong + "", JLabel.CENTER);
        jlc2[6].setPreferredSize(new Dimension(80, 20));
        jlc2[6].setBackground(Color.white);
        jlc2[6].setOpaque(true);
        jlc2[7] = new JLabel("+", JLabel.CENTER);
        jlc2[7].setPreferredSize(new Dimension(40, 20));
        jlc2[7].setBackground(Color.decode("#0A3D62"));
        jlc2[7].setOpaque(true);
        jlc2[7].setForeground(Color.white);
        jlc2[7].addMouseListener(this);

        pc2[5].add(jlc2[5]);
        pc2[5].add(jlc2[6]);
        pc2[5].add(jlc2[7]);

        pc2[6].setBorder(BorderFactory.createEmptyBorder(0, 60, 0, 0));
        jlc2[8] = new JLabel("thêm vào giỏ", JLabel.CENTER);
        jlc2[8].setFont(giohang);
        jlc2[8].setForeground(Color.white);
        jlc2[8].setPreferredSize(new Dimension(100, 30));
        jlc2[8].setBackground(Color.decode("#0A3D62"));
        jlc2[8].setOpaque(true);
        jlc2[8].addMouseListener(this);

        pc2[6].add(jlc2[8]);

        for (int i = 2; i < 7; i++) {
            pc2[1].add(pc2[i]);
        }

        jl_mls = new JLabel[10];
        for (int i = 0; i < 10; i++) {
            jl_mls[i] = new JLabel();
        }
        jl_mls[0] = jln2;
        jl_mls[1] = jlc1[0];
        jl_mls[2] = jlc1[1];
        jl_mls[3] = jlc1[2];
        jl_mls[4] = jlc2[1];
        jl_mls[5] = jlc2[2];
        jl_mls[6] = jlc2[3];
        jl_mls[7] = jlc2[5];
        jl_mls[8] = jlc2[7];
        jl_mls[9] = jlc2[8];

        pcr.add(pc2[0]);
        pcr.add(pc2[1]);

        pc.add(pcL);
        pc.add(pcr);

        this.setLayout(new BorderLayout());
        this.add(pn, BorderLayout.NORTH);
        this.add(pc);

    }

    public int tang_sl() {
        return ++this.soluong;

    }

    public int giam_sl() {
        if (soluong == 0) {
            JOptionPane.showMessageDialog(this, "Số lượng không thể bé hơn 0 ");

        } else {
            return --this.soluong;
        }
        return soluong;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getSource() == jln2) {

            System.exit(0);
        }
        if (e.getSource() == jlc2[5]) {
            jlc2[6].setText(this.giam_sl() + "");
        }
        if (e.getSource() == jlc2[7]) {
            jlc2[6].setText(this.tang_sl() + "");
        }

    }

    @Override
    public void mousePressed(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        // TODO Auto-generated method stub

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        if (e.getSource() == jln2) {
            jln2.setBackground(Color.red);
            jln2.setOpaque(true);
        }
        if (e.getSource() == jlc2[1]) {
            jlc2[1].setBorder(BorderFactory.createLineBorder(Color.decode("#60A3BC")));
        }
        if (e.getSource() == jlc2[2]) {
            jlc2[2].setBorder(BorderFactory.createLineBorder(Color.decode("#60A3BC")));
        }
        if (e.getSource() == jlc2[3]) {
            jlc2[3].setBorder(BorderFactory.createLineBorder(Color.decode("#60A3BC")));
        }
        if (e.getSource() == jlc2[5]) {
            jlc2[5].setBackground(Color.decode("#2980b9"));
            jlc2[5].setOpaque(true);
        }
        if (e.getSource() == jlc2[7]) {
            jlc2[7].setBackground(Color.decode("#2980b9"));
            jlc2[7].setOpaque(true);
        }
        if (e.getSource() == jlc2[8]) {
            jlc2[8].setBackground(Color.decode("#2980b9"));
        }
    }

    @Override
    public void mouseExited(MouseEvent e) {
        if (e.getSource() == jln2) {
            jln2.setBackground(null);
        }
        if (e.getSource() == jlc2[1]) {
            jlc2[1].setBorder(BorderFactory.createLineBorder(Color.white));
        }
        if (e.getSource() == jlc2[2]) {
            jlc2[2].setBorder(BorderFactory.createLineBorder(Color.white));
        }
        if (e.getSource() == jlc2[3]) {
            jlc2[3].setBorder(BorderFactory.createLineBorder(Color.white));
        }
        if (e.getSource() == jlc2[5]) {
            jlc2[5].setBackground(Color.decode("#0A3D62"));
            jlc2[5].setOpaque(true);
        }
        if (e.getSource() == jlc2[7]) {
            jlc2[7].setBackground(Color.decode("#0A3D62"));
            jlc2[7].setOpaque(true);
        }
        if (e.getSource() == jlc2[8]) {
            jlc2[8].setBackground(Color.decode("#0A3D62"));
        }

    }

}
