/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.HotelInformation;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import static util.DataInput.getID;
import static util.DataInput.getString;
import static util.DataInput.getStringFormat;

/**
 *
 * @author long
 */
public class ListHotel extends ArrayList<HotelInformation> {

    // LOAD LIST OF HOTELS FROM FILE
    public ArrayList<HotelInformation> loadFromFile(String path) {
        ArrayList<HotelInformation> tmp = new ArrayList();
        try {
            File filehotels = new File(path);
            String fullpath = filehotels.getAbsolutePath();
            FileReader fr = new FileReader(fullpath);
            BufferedReader br = new BufferedReader(fr);
            String thisLineHotel;
            HotelInformation hotelTmp = new HotelInformation();
            while ((thisLineHotel = br.readLine()) != null) {
                if (!(thisLineHotel.isEmpty())) {
                    String splits[] = thisLineHotel.split("-");
                    hotelTmp.setHotel_Id(splits[0]);
                    hotelTmp.setHotel_Name(splits[1]);
                    hotelTmp.setHotel_Room_Available(Integer.parseInt(splits[2]));
                    hotelTmp.setHotel_Address(splits[3]);
                    hotelTmp.setHotel_Phone(splits[4]);
                    hotelTmp.setHotel_Rating(Integer.parseInt(splits[5]));
                }
                tmp.add(hotelTmp);
                this.add(hotelTmp);
            }
            br.close();
            fr.close();
        } catch (IOException | NumberFormatException e) {
        }
        return tmp;
    }

    public void saveToFile(String path) {
        try {
            if (this.isEmpty()) {
                System.out.println("NO HOTEL IN LIST.");
            } else {
                PrintWriter pw = new PrintWriter(path);
                for (HotelInformation thi : this) {
                    pw.println(thi);
                }
                pw.close();
            }
            System.out.println("SAVE SUCCESSFULLY.");
        } catch (FileNotFoundException e) {
        }
    }
    public void addAHotel() {
        System.out.println("--------------- ADDING A HOTEL ---------------");
        HotelInformation hotelTmp = new HotelInformation();
        boolean check = true;
        while(check) {
            hotelTmp.setID();
            if(this.searchHotelByID(hotelTmp.getHotel_Id()) != -1) {
                System.out.println("EXISTED HOTEL. ENTER ANOTHER ID.");
            }
            else {
                check = false;
            }
        }
        hotelTmp.setName();
        hotelTmp.setHotel_Room_Available();
        hotelTmp.setHotel_Address();
        hotelTmp.setHotel_Phone();
        hotelTmp.setHotel_Rating();
        this.add(hotelTmp);
        
               
            System.out.println("ADD HOTEL SUCCESSFULLY");
        
    }
    public boolean check() {
        String id = getString("Enter hotel's ID to check: ", "INVALID ID");
        boolean tmp = false;
        for (HotelInformation hi : this) {
            if (hi.getHotel_Id().equalsIgnoreCase(id)) {
                tmp = true;
                break;
            }
        }
        return tmp;
    }


    public int searchHotelByID(String id) {
        int tmp = -1;
        for (HotelInformation hi : this) {
            if (hi.getHotel_Id().equalsIgnoreCase(id)) {
                tmp = this.indexOf(hi);
                break;
            }
        }
        return tmp;
    }
    
    
    
    public HotelInformation searchHotelByIDV2(String id) {
        HotelInformation tmp = null;
        for (HotelInformation hi : this) {
            if (hi.getHotel_Id().equalsIgnoreCase(id)) {
                tmp = hi;
                break;
            }
        }
        return tmp;
    }
    

    public void updateHotelInformation() {
        String id = getID("Enter  the hotel's ID(HXXXX): ", "The format of ID stands for HXXXX.", "^[H/h]\\d+");
        HotelInformation tmp = this.searchHotelByIDV2(id);
        if (tmp == null) {
            System.out.println("HOTEL DOES NOT EXIST");
        } else {
            tmp.getInputID();
            tmp.getInputName();
            tmp.getHotel_Room_Available();
            tmp.getInputAddress();
            tmp.getInputPhoneNum();
            tmp.getInputRating();
            System.out.println("UPDATE SUCCESSFULLY");
        }
    }

    public void deleteHotel() {
        if (this.isEmpty()) {
            System.out.println("No hotel in the list");
        } else {
            String id = getID("Enter the hotel's ID to delete: ", "INVALID ID","^[H/h]\\d{4}$");
            int i = this.searchHotelByID(id);
            if ( i == -1) {
                System.out.println("Hotel does not exist!");
            } else {
                String choice = getStringFormat("Enter Y/N", "INVALID CHOICE","^[Y/N/y/n]$");
                if ("Y".equals(choice)) this.remove(i);
                System.out.println("DELETE SUCCESSFULLY");

            }
        }

    }

}