/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;


import static util.DataInput.getAStringCanHaveBlank;
import static util.DataInput.getAnInteger;
import static util.DataInput.getAnIntegerCanHaveBlank;
import static util.DataInput.getString;
import static util.DataInput.getID;
import static util.DataInput.getPhoneNum;
import static util.DataInput.getString2Formats;

/**
 *
 * @author long
 */
public class HotelInformation implements Comparable<HotelInformation> {

    private String hotel_Id;
    private String hotel_Name;
    private int hotel_Room_Available;
    private String hotel_Address;
    private String hotel_Phone;
    private int hotel_Rating;

    public HotelInformation() {
    }

    public HotelInformation(String hotel_Id, String hotel_Name, int hotel_Room_Available, String hotel_Address, String hotel_Phone, int hotel_Rating) {
        this.hotel_Id = hotel_Id;
        this.hotel_Name = hotel_Name;
        this.hotel_Room_Available = hotel_Room_Available;
        this.hotel_Address = hotel_Address;
        this.hotel_Phone = hotel_Phone;
        this.hotel_Rating = hotel_Rating;
    }

    public String getHotel_Id() {
        return hotel_Id;
    }

    public String getHotel_Name() {
        return hotel_Name;
    }

    public int getHotel_Room_Available() {
        return hotel_Room_Available;
    }

    public String getHotel_Address() {
        return hotel_Address;
    }

    public String getHotel_Phone() {
        return hotel_Phone;
    }

    public int getHotel_Rating() {
        return hotel_Rating;
    }

    public void setHotel_Id(String hotel_Id) {
        this.hotel_Id = hotel_Id;
    }

    public void setHotel_Name(String hotel_Name) {
        this.hotel_Name = hotel_Name;
    }

    public void setHotel_Room_Available(int hotel_Room_Available) {
        this.hotel_Room_Available = hotel_Room_Available;
    }

    public void setHotel_Address(String hotel_Address) {
        this.hotel_Address = hotel_Address;
    }

    public void setHotel_Phone(String hotel_Phone) {
        this.hotel_Phone = hotel_Phone;
    }

    public void setHotel_Rating(int hotel_Rating) {
        this.hotel_Rating = hotel_Rating;
    }

    @Override
    public int compareTo(HotelInformation o) {
        int check = this.getHotel_Name().compareTo(o.getHotel_Name());
        if (check > 0) {
            return -1;
        } else if (check < 0) {
            return 1;
        } else {
            return this.hotel_Id.compareTo(o.getHotel_Id());
        }
    }

    public void setID() {
        this.hotel_Id = getID("Enter  the hotel's ID(HXXXX): ", "The format of ID stands for HXXXX.", "[H/h]\\d{4}");
    }

    public void setName() {
        this.hotel_Name = getString("Enter hotel's name: ", "INVALID NAME!");
    }

    public void setHotel_Room_Available() {
        this.hotel_Room_Available = getAnInteger("Enter the number of room available: ", "ROOM NUMBER IS FROM 0 TO 1000!", 0, 1000);

    }

    public void setHotel_Address() {
        this.hotel_Address = getString("Enter the hotel's address: ", "INVALID ADDRESS!");

    }

    public void setHotel_Phone() {
        this.hotel_Phone = getPhoneNum("Enter hotel's phone number(0XXXXXXXXX): ", "The format of phone number is 0XXXXXXXXX!", "^[0]\\d{9}");

    }

    public void setHotel_Rating() {
        this.hotel_Rating = getAnInteger("Enter hotel's rating star: ", "RATE NUMBER IS FROM 0 TO 6!", 0, 6);
    }

//    public String getInputID() {
//        String id = getString2Formats("Enter the hotel's ID(HXXXX):", "The format of ID stands for HXXXX", "", "^[H/h]\\d+").toUpperCase();
//        if (id != null) {
//            this.setHotel_Id(id);
//        }
//        return id;
//    }
    public String getInputPhoneNum() {
        String phone = getString2Formats("Enter the hotel's phone number(0XXXXXXXXX): ", "The format of phone number stands for 0XXXXXXXXX!", "", "^[0]\\d{9}");
        if (phone != null) {
            this.setHotel_Phone(phone);
        }
        return phone;
    }

    public String getInputName() {
        String name = getAStringCanHaveBlank("Enter the hotel's name: ", "INVALID NAME!");
        if (name != null) {
            this.setHotel_Name(name);
        }
        return name;
    }

    public int getInputRoomAvailable() {
        int num = getAnIntegerCanHaveBlank("Enter the room available: ", "ROOM NUMBER IS FROM 0 TO 1000!", 0, 1000);
        if (num != -1) {
            this.setHotel_Room_Available(num);
        }
        return num;
    }

    public String getInputAddress() {
        String address = getAStringCanHaveBlank("Enter the hotel's address: ", "INVALID ADDRESS!");
        if (address != null) {
            this.setHotel_Address(address.trim());

        }
        return address;
    }

    public int getInputRating() {
        int rate = getAnIntegerCanHaveBlank("Enter the hotel's rating: ", "RATE NUMBER IS FROM 0 TO 6!", 0, 6);
        if (rate != -1) {
            this.setHotel_Rating(rate);
        }
        return rate;
    }

    @Override
    public String toString() {
        return hotel_Id + "-" + hotel_Name + "-" + hotel_Room_Available + "-" + hotel_Address + "-" + hotel_Phone + "-" + hotel_Rating;
    }

    public void displayAHotel() {
        System.out.println("|     ID   |        Name        |    Room Available    |            Address           |   Phone Number   | Rating (Star) |");
        System.out.printf("|%-10s|%-20s|%-22d|%-30s|%-18s|%-15d|\n", this.getHotel_Id(), this.getHotel_Name(), this.getHotel_Room_Available(), this.getHotel_Address(), this.getHotel_Phone(), this.getHotel_Rating());
    }

}
