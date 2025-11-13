package Shop;

import Item.Food.*;
import Item.Accessory.*;
import Item.Furniture.*;

import java.util.*;

import House.HouseList;
import Pet.*;
import Player.Player;

import Utils.*;

public class Shop {

    // public void buyPet(Player player, int petID, int cost) throws InterruptedException{

    //     if(player.getCoin() >= cost){
    //         player.setCoin(player.getCoin() - cost);
            
    //         PetManager petManager = new PetManager();
    //         petManager.AddPet(petID);

    //         typeWriter.write("You just bought a new pet! ", 50,150);
    //         typeWriter.write("Coins left: ", 50);
    //         textColor.yellowText(player.getCoin() + " coins");
    //     }
    //     else{
    //         typeWriter.write(player.getName() + " doesn't have enough money...", 50,50) ;
    //         typeWriter.write("Please come back later...", 50, 150);
    //     }
    // }
    
    public void buyFood(Player player, int foodID, int quantity, int cost) throws InterruptedException{

        int totalCost = quantity * cost;
       

        if(player.getCoin() >= totalCost){
            player.setCoin(player.getCoin() - totalCost);

            FoodList food = new FoodList();
            food.addFood(foodID, quantity);

            typeWriter.write(player.getName() + " just bought " + quantity + " " + food.findFoodNameByID(foodID), 50, 150);
            typeWriter.write("Coins left: ", 50);
            textColor.yellowText(player.getCoin() + " coins");
            typeWriter.write("Thank you for your purchase!", 50, 150);
            System.out.println();
        }
        else{
            typeWriter.write(player.getName() + " doesn't have enough money...", 50,50) ;
            typeWriter.write("Please come back later...", 50, 150);
            System.out.println();
        }
    }

    public void buyAccessory(Player player, int accessoryID, int quantity, int cost) throws InterruptedException  {
        int totalCost = quantity * cost;

        if(player.getCoin() >= totalCost){
            player.setCoin(player.getCoin() - totalCost);

            AccessoryList accessory = new AccessoryList();
            accessory.addAccessory(accessoryID, quantity);

            typeWriter.write(player.getName() + " just bought " + quantity + " " + accessory.findAccessoryNameByID(accessoryID), 50, 150);
            typeWriter.write("Coins left: ", 50);
            textColor.yellowText(player.getCoin() + " coins");
            typeWriter.write("Thank you for your purchase!", 50, 150);
            System.out.println();
        }
        else{
            typeWriter.write(player.getName() + " doesn't have enough money...", 50,50) ;
            typeWriter.write("Please come back later...", 50, 150);
            System.out.println();
        }
    }

    public void buyFurniture(Player player, int furnitureID, int quantity, int cost) throws InterruptedException{
        int totalCost = quantity * cost;

        if(player.getCoin() >= totalCost){
            player.setCoin(player.getCoin() - totalCost);

            FurnitureList fur = new FurnitureList();
            fur.addFurniture(furnitureID, quantity);

            typeWriter.write(player.getName() + " just bought " + quantity + " " + fur.findFurnitureNameByID(furnitureID), 50, 150);
            typeWriter.write("Coins left: ", 50);
            textColor.yellowText(player.getCoin() + " coins");
            typeWriter.write("Thank you for your purchase!", 50, 150);
            System.out.println();
        }
        else{
            typeWriter.write(player.getName() + " doesn't have enough money...", 50,50) ;
            typeWriter.write("Please come back later...", 50, 150); 
            System.out.println();
        }
    }

    public void buyHouse(Player player,int quantity, int cost) throws InterruptedException{
        int totalCost = quantity * cost;
        if(player.getCoin() >= totalCost){
            player.setCoin(player.getCoin() - totalCost);

            HouseList houseList = new HouseList();
            houseList.addHouse();

            typeWriter.write(player.getName() + " just bought " + quantity + " house", 50, 150);
            typeWriter.write("Coins left: ", 50);
            textColor.yellowText(player.getCoin() + " coins");
            typeWriter.write("Thank you for your purchase!", 50, 150);
            System.out.println();
        }
        else{
            typeWriter.write(player.getName() + " doesn't have enough money...", 50,50) ;
            typeWriter.write("Please come back later...", 50, 150); 
            System.out.println();
        }
    }


    public void menuShop(Player player) throws InterruptedException{
        
        clearScreen.clear();
        
        Scanner scan = new Scanner(System.in);

        int choice = 9999;

        while(choice != 0){
            try {
                typeWriter.write("===== SHOP =====", 50, 150);
                System.out.println();
                typeWriter.write("What do you want to buy?", 50, 150);
                System.out.println();
                typeWriter.write("1. Food?", 50, 150);
                typeWriter.write("2. Accessory?", 50, 150);
                typeWriter.write("3. Furniture?", 50, 150);
                typeWriter.write("4. House?", 50, 150);
                typeWriter.write("5. Pet?", 50, 150);
                System.out.println();
                typeWriter.write("0. Exit from the shop!", 50, 150);
                System.out.println();   

                typeWriter.write("Choose the number of which item you want to buy!", 50, 150);
                typeWriter.write("-> ", 0);
                
                if (!scan.hasNextInt()) {
                    typeWriter.write("Invalid input! Please enter a number.", 50, 150);
                    scan.nextLine(); // Clear invalid input
                    Thread.sleep(250);
                    clearScreen.clear();
                    continue;
                }
                
                choice = scan.nextInt();
                
                if(choice == 0){
                    Thread.sleep(250);
                    typeWriter.write("See you again!", 50, 150);
                    Thread.sleep(250);
                    clearScreen.clear();
                    return;
                }

                Thread.sleep(250);
                clearScreen.clear();

                int buyOption;
                int quantity;
                String confirm;

            switch(choice){
                case 1:
                    FoodList foodList = new FoodList();

                    foodList.printFoodForShop();
                    
                    typeWriter.write("Which food do you want to buy?", 10 , 150);
                    typeWriter.write("-> ", 50 );
                    
                    buyOption = scan.nextInt();
                    scan.nextLine();
                    
                    Food food = new Food();
                    food = foodList.findFoodByID(buyOption);
                    
                    if(food!=null){
                        typeWriter.write("how many item do you want to buy? ", 10, 150);
                        typeWriter.write("-> ", 50);
                        quantity = scan.nextInt();
                        scan.nextLine();
                        typeWriter.write("That will be ", 50);
                        textColor.yellowText(quantity * 20 +" coins");
                        
                        typeWriter.write("Do you sure you want to buy " + quantity + " " + food.getItemName() +"? (Y/N)",10,150);
                        typeWriter.write("-> ", 50);
                        confirm = scan.nextLine().toLowerCase();
                        
                        if(confirm.equals("y") || confirm.equals("yes") || confirm.equals("true")){
                            buyFood(player,buyOption,quantity,20);
                        }
                        else if(confirm.equals("no") || confirm.equals("false")|| confirm.equals("n")){
                            typeWriter.write("Thank you for checking by!", 50   , 150);
                            System.out.println();
                        }
                        else{
                            typeWriter.write("your choice is not appropriate! please try again...", 50, 150);
                            System.out.println();
                        }
                    }
                    else{
                        typeWriter.write("Food not found!", 10, 150);
                        typeWriter.write("Try again later...", 10, 150);
                    }

                    Thread.sleep(250);
                    clearScreen.clear();
                    
                    break;
                case 2:
                    AccessoryList al = new AccessoryList();
                    al.printAccessoryForShop();

                    typeWriter.write("Which accessory do you want to buy?", 10 , 150);
                    typeWriter.write("-> ", 50 );
                    
                    buyOption = scan.nextInt();
                    scan.nextLine();
                    
                    Accessory accessory = new Accessory();
                    accessory = al.findAccessoryByID(buyOption);
                    
                    if(accessory!=null){
                        typeWriter.write("how many item do you want to buy? ", 10, 150);
                        typeWriter.write("-> ", 50);
                        quantity = scan.nextInt();
                        scan.nextLine();
                        typeWriter.write("That will be ", 50);
                        textColor.yellowText(quantity * 20 +" coins");
                        
                        typeWriter.write("Do you sure you want to buy " + quantity + " " + accessory.getItemName() +"? (Y/N)",10,150);
                        typeWriter.write("-> ", 50);
                        confirm = scan.nextLine().toLowerCase();
                        
                        if(confirm.equals("y") || confirm.equals("yes") || confirm.equals("true")){
                            buyAccessory(player,buyOption,quantity,25);
                        }
                        else if(confirm.equals("no") || confirm.equals("false")|| confirm.equals("n")){
                            typeWriter.write("Thank you for checking by!", 50   , 150);
                            System.out.println();
                        }
                        else{
                            typeWriter.write("your choice is not appropriate! please try again...", 50, 150);
                            System.out.println();
                        }
                    }
                    else{
                        typeWriter.write("Accessory not found!", 10, 150);
                        typeWriter.write("Try again later...", 10, 150);
                    }

                    Thread.sleep(250);
                    clearScreen.clear();

                    break;

                case 3:

                    FurnitureList fl = new FurnitureList();
                    fl.printFurForShop();

                    typeWriter.write("Which furniture do you want to buy?", 10 , 150);
                    typeWriter.write("-> ", 50 );
                    
                    buyOption = scan.nextInt();
                    scan.nextLine();
                    
                    Furniture fur = new Furniture();
                    fur = fl.findFurByID(buyOption);
                    
                    if(fur!=null){
                        typeWriter.write("how many item do you want to buy? ", 10, 150);
                        typeWriter.write("-> ", 50);
                        quantity = scan.nextInt();
                        scan.nextLine();
                        typeWriter.write("That will be ", 50);
                        textColor.yellowText(quantity * 20 +" coins");
                        
                        typeWriter.write("Do you sure you want to buy " + quantity + " " + fur.getItemName() +"? (Y/N)",10,150);
                        typeWriter.write("-> ", 50);
                        confirm = scan.nextLine().toLowerCase();
                        
                        if(confirm.equals("y") || confirm.equals("yes") || confirm.equals("true")){
                            buyFurniture(player,buyOption,quantity,25);
                        }
                        else if(confirm.equals("no") || confirm.equals("false")|| confirm.equals("n")){
                            typeWriter.write("Thank you for checking by!", 50   , 150);
                        }
                        else{
                            typeWriter.write("your choice is not appropriate! please try again...", 50, 150);
                        }
                    }
                    else{
                        typeWriter.write("Furniture not found!", 10, 150);
                        typeWriter.write("Try again later...", 10, 150);
                    }

                    Thread.sleep(250);
                    clearScreen.clear();

                    break;

                case 4:
                    HouseList hl = new HouseList();
                    hl.printHouseForShop();
                    
                    typeWriter.write("Would you like to buy a house? (Y/N)", 30, 100);
                    typeWriter.write("-> ", 50);
                    scan.nextLine(); // Clear newline từ nextInt()
                    confirm = scan.nextLine().toLowerCase().trim();
                    
                    
                    if(confirm.equals("y") || confirm.equals("yes") || confirm.equals("true")){
                        buyHouse(player,1,50);
                    }
                    else if(confirm.equals("no") || confirm.equals("false")|| confirm.equals("n")){
                        typeWriter.write("Thank you for checking by!", 50   , 150);
                    }

                    Thread.sleep(250);
                    clearScreen.clear();

                    break;

                case 5:
                    PetManager pm = new PetManager();
                    pm.printPetForShop();
                    System.out.println();
                    typeWriter.write("Would you want to buy an Egg? (Y/N)", 50,150);
                    typeWriter.write("-> ", 50);
                    scan.nextLine(); // Clear newline từ nextInt()
                    confirm = scan.nextLine().toLowerCase().trim();

                    if(confirm.equals("y") || confirm.equals("yes") || confirm.equals("true")){
                        pm.buyEgg(player, 30);
                    }
                    else if(confirm.equals("no") || confirm.equals("false")|| confirm.equals("n")){
                        typeWriter.write("Thank you for checking by!", 50   , 150);
                    }

                    Thread.sleep(250);
                    clearScreen.clear();

                    break;

                 default:
                    typeWriter.write("choice not appropriate!", 50, 150);
                    Thread.sleep(250);
                    clearScreen.clear();

                    break;
            }
            } catch (Exception e) {
                typeWriter.write("An error occurred: " + e.getMessage(), 50, 150);
                scan.nextLine(); // Clear invalid input if any
                Thread.sleep(250);
                clearScreen.clear();
            }
        }
    }




















































    
}