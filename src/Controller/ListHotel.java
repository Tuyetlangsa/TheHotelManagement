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
import java.util.Collections;
import java.util.Comparator;
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

            while ((thisLineHotel = br.readLine()) != null) {
                HotelInformation hotelTmp = new HotelInformation();
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
                System.out.println("NO HOTEL IN LIST!");
            } else {
                PrintWriter pw = new PrintWriter(path);
                for (HotelInformation thi : this) {
                    pw.println(thi);
                }
                pw.close();
            }
            
        } catch (FileNotFoundException e) {
        }
    }

    public void addAHotel() {
        System.out.println("--------------- ADDING A HOTEL ---------------");
        HotelInformation hotelTmp = new HotelInformation();
        boolean check = true;
        while (check) {
            hotelTmp.setID();
            if (this.searchHotelByID(hotelTmp.getHotel_Id()) != -1) {
                System.out.println("EXISTED HOTEL. ENTER ANOTHER ID!");
            } else {
                check = false;
            }
        }
        hotelTmp.setName();
        hotelTmp.setHotel_Room_Available();
        hotelTmp.setHotel_Address();
        hotelTmp.setHotel_Phone();
        hotelTmp.setHotel_Rating();
        this.add(hotelTmp);

        System.out.println("ADD HOTEL SUCCESSFULLY!");

    }

    public boolean check() {
        String id = getString("Enter hotel's ID to check: ", "INVALID ID!");
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
        String id = getID("Enter  the hotel's ID(HXXXX): ", "The format of ID stands for HXXXX!", "^[H/h]\\d{4}");
        HotelInformation tmp = this.searchHotelByIDV2(id);
        if (tmp == null) {
            System.out.println("HOTEL DOES NOT EXIST!");
        } else {       
            tmp.getInputName();
            tmp.getInputRoomAvailable();
            tmp.getInputAddress();
            tmp.getInputPhoneNum();
            tmp.getInputRating();
            System.out.println("UPDATE SUCCESSFULLY!");
            tmp.displayAHotel();
                    
        }
    }

    public void deleteHotel() {
        if (this.isEmpty()) {
            System.out.println("HOTEL DOES NOT EXIST!");
            System.out.println("DELETE FAIL!");
        } else {
            String id = getID("Enter the hotel's ID to delete: ", "INVALID ID!", "^[H/h]\\d{4}");
            HotelInformation hotelDel = this.searchHotelByIDV2(id);
            if (hotelDel == null) {
                System.out.println("HOTEL DOES NOT EXIST!");
            } else {
                hotelDel.displayAHotel();
                System.out.println("Do you ready want to delete this hotel?");
                String choice = getStringFormat("Enter Y/N: ", "INVALID CHOICE!", "^[Y/N/y/n]$");
                if ("Y".equals(choice)) {
                    this.remove(hotelDel);
                    System.out.println("DELETE SUCCESSFULLY!");
                }
                

            }
        }
    }
    
    public ListHotel searchHotelContainID(String id) {
    ListHotel tmp = new ListHotel();
        for (HotelInformation thi : this) {
            if(thi.getHotel_Id().contains(id)) {
                tmp.add(thi);
            }
        }
    return tmp ;
    }
        public ListHotel searchHotelByName(String name) {
        ListHotel tmp = new ListHotel();
        for (HotelInformation thi : this) {
            if(thi.getHotel_Name().equalsIgnoreCase(name)) {
                tmp.add(thi);
            }
        }
    return tmp ;
    }
    
    public void displayHotelListDescendingById(ListHotel list) {
//        this.loadFromFile(path);
        if (list.isEmpty()) {
            System.out.println("NO HOTEL IN THE LIST!");
        } else {
            ListHotel listTmp = (ListHotel) list.clone();
            
    Comparator<HotelInformation> com = new Comparator<HotelInformation>(){
            public int compare(HotelInformation o1, HotelInformation o2) {
                int check = o1.getHotel_Id().compareTo(o2.getHotel_Id());
                if (check > 0) {
                    return -1;
                } else if (check < 0) {
                    return 1;
                } else {
                    return o1.getHotel_Name().compareTo(o2.getHotel_Name());
                }
            }
        };
            Collections.sort(listTmp,com);
            System.out.println("--------------------------------------- THE HOTEL INFORMATION LIST ---------------------------------------");
            System.out.println("|     ID   |        Name        |    Room Available    |            Address           |   Phone Number   | Rating (Star) |");
            for (HotelInformation hotelInf : listTmp) {
                System.out.printf("|%-10s|%-20s|%-22d|%-30s|%-18s|%-15d|\n", hotelInf.getHotel_Id(), hotelInf.getHotel_Name(), hotelInf.getHotel_Room_Available(), hotelInf.getHotel_Address(), hotelInf.getHotel_Phone(), hotelInf.getHotel_Rating());
                for (int i = 0; i < 122; i++) {
                    System.out.print("-");
                }
                System.out.println("");
            }
        }
    }

    public void displayHotelList(ListHotel list) {
//        this.loadFromFile(path);
        if (list.isEmpty()) {
            System.out.println("NO HOTEL IN THE LIST!");
        } else {
            ListHotel listTmp = (ListHotel) list.clone();
            Collections.sort(listTmp);
            System.out.println("--------------------------------------- THE HOTEL INFORMATION LIST ---------------------------------------");
            System.out.println("|     ID   |        Name        |    Room Available    |            Address           |   Phone Number   | Rating (Star) |");
            for (HotelInformation hotelInf : listTmp) {
                System.out.printf("|%-10s|%-20s|%-22d|%-30s|%-18s|%-15d|\n", hotelInf.getHotel_Id(), hotelInf.getHotel_Name(), hotelInf.getHotel_Room_Available(), hotelInf.getHotel_Address(), hotelInf.getHotel_Phone(), hotelInf.getHotel_Rating());
                for (int i = 0; i < 122; i++) {
                    System.out.print("-");
                }
                System.out.println("");
            }
        }
    }

}
