
package BUS;

import DTO.Hoadon_DTO;
import DTO.chitietphieunhap_DTO;
import DTO.chitietsanpham_DTO;
import DAO.Hoadon_DAO;
import java.util.ArrayList;

public final class Hoadon_BUS {
    // public ArrayList<Hoadon_DTO> dshoadon;
    
    // public Hoadon_BUS(){
    //     dshoadon = new ArrayList<>();
    //     list();
    // }
    
    // public void list(){
    //     Hoadon_DAO dshd = new Hoadon_DAO();
    //     dshoadon = dshd.listchucnang();
    // }

    public static ArrayList<Hoadon_DTO> list(){
        return Hoadon_DAO.list();
    }

    public static int getNumberOfRow() {
        return Hoadon_DAO.getNumberOfRow();
    }

    public static boolean addHoaDon(Hoadon_DTO hoaDon) {
        return Hoadon_DAO.add(hoaDon);
    }
    
     public static void main(String[] args) {
        Hoadon_BUS cthd = new Hoadon_BUS();
        cthd.list();
    }
    public static int getpointtichluy(int a)
    {
        return Hoadon_DAO.getpointtichluy(a);
    }
}
