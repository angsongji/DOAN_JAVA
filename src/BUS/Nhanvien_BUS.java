package BUS;

import DAO.Nhanvien_DAO;
import DTO.Nhanvien_DTO;
import java.util.ArrayList;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author GAMING-PC
 */
public class Nhanvien_BUS {

    public ArrayList<Nhanvien_DTO> listnv;
    public Nhanvien_BUS(){
        list();
    }
    
    public void list(){
        Nhanvien_DAO dsnv = new Nhanvien_DAO();
        listnv = dsnv.list();
    }
    
         public static void main(String[] args) {
        Hoadon_BUS cthd = new Hoadon_BUS();
        cthd.list();
    }
}
