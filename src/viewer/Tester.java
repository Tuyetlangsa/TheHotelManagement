/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewer;

import Controller.ListHotel;
import Model.HotelInformation;

/**
 *
 * @author long
 */
public class Tester {

    public static void main(String[] args) {
        final String path = "src\\Data\\ListOfHotels.txt";
        ListHotel lht = new ListHotel();
        lht.loadFromFile(path);
              for (HotelInformation hotelInformation : lht) {
            System.out.println(hotelInformation);
        }

        lht.updateHotelInformation();
        lht.saveToFile(path);
        
        
        
        
        
        for (HotelInformation hotelInformation : lht) {
            System.out.println(hotelInformation);
        }
        
        
    }
}
