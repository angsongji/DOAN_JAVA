package BUS;

import DAO.Nhanvien_DAO;
import DTO.Nhanvien_DTO;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Nhanvien_BUS {

    public ArrayList<Nhanvien_DTO> listnv;
    
    public Nhanvien_BUS(){
        list();
    }
    
    public void list(){
        Nhanvien_DAO dsnv = new Nhanvien_DAO();
        listnv = dsnv.list();
    }
    
    public boolean checkTENNV(String t) {
        //tên nhân viên không chứa số và các kí tự đặc biệt
        String regex = "^[\\p{L} ]+$";
        return t.matches(regex);
    }
//     public boolean checkCHUCVU(String date) {
//        
//        }

    
    public boolean checkSDT(String t) {
        //tổng cộng 10 chữ số: bắt đầu là số 0
        String regex = "^0[0-9]{9}$";
        return t.matches(regex);
    }
    
    public boolean checkDCHI(String address) {
                return address.length() <= 250;
    }
    
    public boolean checkEMAIL (String email) {
         String regex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private String createMANV() {
        int max =0;
        for(int i=0;i<listnv.size();i++){
            String MANVlast = listnv.get(i).getManv();
            String so = MANVlast.replaceAll("[^0-9]","");
        int stt = Integer.parseInt(so) + 1;
        if(stt > max) max = stt;
        }
        return "NV00" + max;
      
        
    }
    
     public Nhanvien_DTO update(Nhanvien_DTO nv) {
        listnv.add(nv);
        Nhanvien_DAO nvDAO = new Nhanvien_DAO();
        nvDAO.add(nv);
        return nv;
    }

    public Nhanvien_DTO add(Nhanvien_DTO nv) {
        nv.setManv(createMANV());
        listnv.add(nv);
        Nhanvien_DAO nvDAO = new Nhanvien_DAO();
        nvDAO.add(nv);
        return nv;
    }

    public void deleteInSQL(String maDelete){
        Nhanvien_DAO nvDAO = new  Nhanvien_DAO();
        nvDAO.delete(maDelete);
    }
    
    public void updateInSQL(){
        Nhanvien_DAO nvDAO = new  Nhanvien_DAO();
        for(Nhanvien_DTO nv : listnv){
            nvDAO.update(nv);
        }
    }
    
    public void delete(String MANCC){
        for(int i=0;i<listnv.size();i++){
            if(listnv.get(i).getManv().equals(MANCC))
                listnv.remove(i);
        }
    }
    
    public ArrayList<Nhanvien_DTO> search(ArrayList<String> data_filter){
        ArrayList<Nhanvien_DTO> re = new ArrayList<>();
        for(String i : data_filter){
            for(Nhanvien_DTO j : listnv){
                if(j.getManv().equals(i) || j.getTennv().equalsIgnoreCase(i))
                    re.add(j);
                    
            }      
    }
        return re;
    }
    
    public boolean checkNewListNV(ArrayList<Nhanvien_DTO> newList) {
        boolean flag = true;
        for (int i = 0; i < listnv.size(); i++) {
            if (!listnv.get(i).equals(newList.get(i))) {
                if(newList.get(i).getTennv().equals("") || String.valueOf(newList.get(i).getSdt()).equals("")) continue;
                if (checkTENNV(newList.get(i).getTennv()) && checkSDT("0"+String.valueOf(newList.get(i).getSdt()))) {
                    listnv.get(i).setTennv(newList.get(i).getTennv());
                    listnv.get(i).setSdt(newList.get(i).getSdt());
                }
                
                else {
                            flag = false;
                            break;
                            }
            }
        }
        return flag;
    }
    
         public static void main(String[] args) {
        Nhanvien_BUS cthd = new Nhanvien_BUS();
        cthd.list();
    }
}
