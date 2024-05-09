
package BUS;

import DTO.Hoadon_DTO;
import DAO.Hoadon_DAO;
import java.util.ArrayList;
import DTO.chitietsanpham_DTO;
import java.sql.SQLException;

public final class Hoadon_BUS {
    public ArrayList<Hoadon_DTO> dshoadon;
    
    public Hoadon_BUS(){
        dshoadon = new ArrayList<>();
        list();
    }
    
    public void list(){
        Hoadon_DAO dshd = new Hoadon_DAO();
        dshoadon = dshd.listchucnang();
    }

     public void delete(Hoadon_DTO hd) throws SQLException {
        Hoadon_DAO hdDAO = new Hoadon_DAO();
        chitietsanpham_BUS cp = new chitietsanpham_BUS();
        ChitietHD_BUS cd = new ChitietHD_BUS(hd.getMaHD());
        
        ArrayList<chitietsanpham_DTO> list = cd.listtorestore(hd.getMaHD());
        for(chitietsanpham_DTO ctsp : list){
           cp.Restore_pro(ctsp);
        }
        cd.delete(hd.getMaHD());
        hdDAO.delete(hd.getMaHD());   
    }
    
    public ArrayList<Hoadon_DTO> search(ArrayList<String> data_filter) {
    ArrayList<Hoadon_DTO> re = new ArrayList<>();
    for (String i : data_filter) {
        for (Hoadon_DTO j : dshoadon) {
            String idHD = j.getMaHD();
            if (idHD.equalsIgnoreCase(i))
                re.add(j);
            int idKH = j.getMaKH();
            if (idKH == Integer.parseInt(i))
                re.add(j);
            String idNV = j.getMaNV();
            if (idNV.equalsIgnoreCase(i))
                re.add(j);
        }
    }
    return re;
}

     public static void main(String[] args) {
        Hoadon_BUS cthd = new Hoadon_BUS();
        cthd.list();
    }
}
