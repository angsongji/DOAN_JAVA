
package BUS;

import DTO.Hoadon_DTO;
import DAO.Hoadon_DAO;
import DTO.chitietsanpham_DTO;
import java.sql.SQLException;
import java.util.ArrayList;

public final class Hoadon_BUS {

    public static int getNumberOfRow() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public static boolean addHoaDon(Hoadon_DTO hoaDon) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public ArrayList<Hoadon_DTO> dshoadon;
    
    public Hoadon_BUS() throws SQLException{
        dshoadon = new ArrayList<>();
        list();
    }
    
    public void list() throws SQLException{
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

    
//     public static void main(String[] args) throws SQLException {
//        Hoadon_BUS cthd = new Hoadon_BUS();
//        Hoadon_DTO hd = new Hoadon_DTO("HD001","2023-08-13",3,"NV003",1260000,100000);
//        cthd.delete(hd);
    }

