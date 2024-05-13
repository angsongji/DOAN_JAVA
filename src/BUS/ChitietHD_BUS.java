
package BUS;

import DAO.ChitietHD_DAO;
import DTO.ChitietHD_DTO;
import DTO.chitietsanpham_DTO;
import java.util.ArrayList;
import java.sql.SQLException;

public class ChitietHD_BUS {
    public ArrayList<ChitietHD_DTO> list;

    public ChitietHD_BUS(String maHD) throws SQLException {
        list(maHD); 
    }
  
    public void list(String maHD) throws SQLException {
        ChitietHD_DAO listsphd = new ChitietHD_DAO();
        list = listsphd.list(maHD);
    }
    public ArrayList<ChitietHD_DTO> getList(){
        return list;
    }
    public void delete(String maHD) throws SQLException {
        ChitietHD_DAO cdDAO = new ChitietHD_DAO();
        cdDAO.delete(maHD);
    }
    public ArrayList<chitietsanpham_DTO> listtorestore(String maHD) throws SQLException {
        ChitietHD_DAO listsphd = new ChitietHD_DAO();
        return listsphd.listtorestore(maHD);
    }
         public static void main (String[] args) throws SQLException{
        ChitietHD_BUS hd = new ChitietHD_BUS("HD002");
        hd.listtorestore("HD002");
    }

         
         
}
