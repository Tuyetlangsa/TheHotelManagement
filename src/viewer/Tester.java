/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package viewer;

import Controller.ListHotel;
import Model.HotelInformation;
import util.DataInput;

/**
 *
 * @author long
 */
public class Tester {
    
    public static void main(String[] args) {
        final String path = "src\\Data\\ListOfHotels.txt";
        ListHotel hotelList = new ListHotel();
        Menu menu = new Menu("THE HOTEL MANAGEMENT PROGRAM");
        menu.addNewOption("Add new hotel");
        menu.addNewOption("Check to exist hotel");
        menu.addNewOption("Update hotel information");
        menu.addNewOption("Delete hotel");
        menu.addNewOption("Search hotel");
        menu.addNewOption("Displaying a hotel list (descending by Hotel_Name)");
        menu.addNewOption("End program");
        hotelList.loadFromFile(path);
        int choice = 0;
        do {
            
            int check = 1;
            menu.printMenu();
            choice = menu.getChoice();
            switch (choice) {
                case 1:
                    do {
                        hotelList.addAHotel();
                        hotelList.saveToFile(path);
                        choice = DataInput.getAnInteger("Press 1 to continue or 2 to finish adding ", "CHOOSE 1 OR 2!", 1, 2);
                        if (choice == 2) {
                            check = 0;
                        }
                    } while (check != 0);
                    break;
                case 2:
                    do {
                        boolean exist = hotelList.check();
                        if (exist == true) {
                            System.out.println("Existed Hotel!");
                        } else {
                            System.out.println("No Hotel Found!");
                        }
                        choice = DataInput.getAnInteger("Press 1 to continue or 2 to finish checking ", "CHOOSE 1 OR 2!", 1, 2);
                        if (choice == 2) {
                            check = 0;
                        }
                    } while (check != 0);
                    break;
                case 3:
                    hotelList.updateHotelInformation();
                    hotelList.saveToFile(path);
                    break;
                case 4:
                    System.out.println("Welcome to delete hotel");
                    hotelList.deleteHotel();
                    hotelList.saveToFile(path);
                    break;
                case 5:
                    do {
                        Menu menuSearch = new Menu("Searching Hotel");
                        menuSearch.addNewOption("Search hotel list contains ID");
                        menuSearch.addNewOption("Search hotel list by name");
                        menuSearch.addNewOption("Back to menu");
                        menuSearch.printMenu();
                        choice = menuSearch.getChoice();
                        switch (choice) {
                            case 1:                                        
                                ListHotel listTmp = hotelList.searchHotelContainID(DataInput.getID("Enter ID to find: ", "INVALID ID", "[H/h]\\d+"));
                                listTmp.displayHotelListDescendingById(listTmp);
                                break;
                            case 2:
                                ListHotel listTmp2 = hotelList.searchHotelByName(DataInput.getString("Enter hotel's name to find: ", "INVALID NAME"));
                                listTmp2.displayHotelListDescendingById(listTmp2);
                                break;
                            case 3:
                                check = 0;
                                break;        
                        }                        
                    } while (check != 0);
                    break;
                case 6:
                    hotelList.displayHotelList(hotelList);
                    break;
                case 7:
                    System.out.println("Good Bye");
                    break;               
            }
        } while (choice != 7);
    }
}
