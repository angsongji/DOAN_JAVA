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

    
    private int Maxid(String prefix) {
        int maxNumber = 0;
        for (int i = 0; i < listnv.size(); i++) {
            String employeeId = listnv.get(i).getManv();
            if (employeeId.startsWith(prefix)) {
                String numberPart = employeeId.substring(2);
                    int number = Integer.parseInt(numberPart);
                    if (number > maxNumber) {
                        maxNumber = number;
                        System.out.println(maxNumber);
                    }
            }
        }
            return maxNumber;
       
    }
    
    
    
    private String createidNV() {
        int stt = Maxid("NV") + 1;
        return   "NV00" + stt;
    }
    
    private String createidQL() {
        int stt = Maxid("QL") + 1;
        return   "QL00" + stt;
    }
    
    private String createidAD() {
        int stt = Maxid("AD") + 1;
        return   "AD00" + stt;
    }
    

    public void add( String ten, String cv, int sdt, String dc,String e) {
        if ( cv == "Nhân viên")
        {
        String id = createidNV();
        Nhanvien_DTO nv = new Nhanvien_DTO(id,ten,cv,sdt,dc,e);
        listnv.add(nv);
        Nhanvien_DAO nvDAO = new Nhanvien_DAO();
        nvDAO.add(nv);
        }   
        else{
            if( cv == "Quản lý bán hàng" || cv == "Quản lý kho")
            {
                String id = createidQL();
                Nhanvien_DTO ql = new Nhanvien_DTO(id,ten,cv,sdt,dc,e);
                listnv.add(ql);
                Nhanvien_DAO nvDAO = new Nhanvien_DAO();
                nvDAO.add(ql);
            }   
            else{
                String id = createidAD();
                Nhanvien_DTO ad = new Nhanvien_DTO(id,ten,cv,sdt,dc,e);
                listnv.add(ad);
                Nhanvien_DAO nvDAO = new Nhanvien_DAO();
                nvDAO.add(ad);
            }
        }
    }
   
    
    public void update(Nhanvien_DTO nv) {
        Nhanvien_DAO nvDAO = new Nhanvien_DAO();
        nvDAO.update(nv);
    }
     
    
    public void delete(Nhanvien_DTO nv) {
        Nhanvien_DAO nvDAO = new Nhanvien_DAO();
        nvDAO.delete(nv.getManv());
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
        Nhanvien_BUS nv = new Nhanvien_BUS();
        nv.add("haha","Nhân viên",987690009 ,"HCM","638ejn@gmail.com");
//        Nhanvien_DTO n2 = new Nhanvien_DTO("NV005","kkkk","Nhân viên",987666789 ,"TP HCM","6383uyejn@gmail.com");
//        nv.update(n2);
//        nv.list(); 
         };
}
