/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BUS;


import DAO.SizeDAO;
import DTO.SizeDTO;
import java.util.ArrayList;

/**
 *
 * @author hp
 */
public class SizeBUS {
    private ArrayList<SizeDTO> listSize;
    
    public SizeBUS() {
        listSize = new ArrayList<>();
        init();
    }
    
     private void init() {
        SizeDAO n = new SizeDAO();
        listSize = n.listSize();
    }
     
     
    public ArrayList<SizeDTO> getList() {
        return listSize;
    }
}
