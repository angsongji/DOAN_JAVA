package BUS;

import DAO.TaiKhoanDAO;
import DTO.TaiKhoanDTO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class TaiKhoanBUS {
    private ArrayList<TaiKhoanDTO> dsTK;

    public TaiKhoanBUS() {
        list();
    }
    
    public void list(){
        TaiKhoanDAO tkDAO = new TaiKhoanDAO();
        dsTK = new ArrayList<>();
        dsTK = tkDAO.list();
    }
    
    public void add(TaiKhoanDTO tk){
        dsTK.add(tk);
        TaiKhoanDAO tkDAO = new TaiKhoanDAO();
        tkDAO.add(tk);  
    }
    
    public void delete (String maNV){
        for(TaiKhoanDTO t : dsTK){
            if(t.getMaNV().equalsIgnoreCase(maNV)){
                dsTK.remove(t);
                TaiKhoanDAO tkDAO = new TaiKhoanDAO();
                tkDAO.delete(maNV);
                return;
            }
        }
    }
    
    public void set(TaiKhoanDTO t){
        for(int i=0; i<dsTK.size(); i++){
            if(dsTK.get(i).getMaNV().equalsIgnoreCase(t.getMaNV())){
                dsTK.set(i, t);
                TaiKhoanDAO tkDAO = new TaiKhoanDAO();
                tkDAO.set(t);
            }
        }
    }

    public ArrayList<TaiKhoanDTO> getDsTK() {
        return dsTK;
    }
    
    public static void main(String[] args) {
        TaiKhoanBUS t = new TaiKhoanBUS();
        TaiKhoanDTO d = new TaiKhoanDTO("NV012", "hoa1234", "1234", "2016-09-03", "AD", 1);
        t.set(d);
    }
   
    
}
