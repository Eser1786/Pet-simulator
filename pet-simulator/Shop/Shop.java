package Shop;

import Item.Food.*;
import Item.Accessory.*;
import Item.Furniture.*;

import java.util.*;

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
        }
        else{
            typeWriter.write(player.getName() + " doesn't have enough money...", 50,50) ;
            typeWriter.write("Please come back later...", 50, 150);
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
        }
        else{
            typeWriter.write(player.getName() + " doesn't have enough money...", 50,50) ;
            typeWriter.write("Please come back later...", 50, 150);
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
        }
        else{
            typeWriter.write(player.getName() + " doesn't have enough money...", 50,50) ;
            typeWriter.write("Please come back later...", 50, 150); 
        }
    }


    public void menuShop(Player player) throws InterruptedException{
        Scanner scan = new Scanner(System.in);

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

        typeWriter.write("Choose the number of which item you want to buy!", 50, 150);
        typeWriter.write("-> ", 0);
        int choice;
        choice = scan.nextInt();

        int buyOption;
        int quantity;
        String confirm;

        switch(choice){
            case 1:
                FoodList foodList = new FoodList();

                foodList.printFoodForShop();
                
                typeWriter.write("Which food do you want to buy?", 50 , 150);
                typeWriter.write("-> ", 50 );
                
                buyOption = scan.nextInt();
                scan.nextLine();
                
                Food food = new Food();
                food = foodList.findFoodByID(buyOption);
                
                if(food!=null){
                    typeWriter.write("how many item do you want to buy? ", 50, 150);
                    typeWriter.write("->  ", 50);
                    quantity = scan.nextInt();
                    scan.nextLine();
                    typeWriter.write("That will be ", 50);
                    textColor.yellowText(quantity * 20 +" coins");
                    
                    typeWriter.write("Do you sure you want to buy " + quantity + " " + food.getItemName() +"? (Y/N)",50,150);
                    typeWriter.write("-> ", 50);
                    confirm = scan.nextLine();
                    confirm = confirm.toLowerCase();
                    if(confirm.equals("y") || confirm.equals("yes") || confirm.equals("true")){
                        buyFood(player,buyOption,quantity,20);
                    }
                    else{
                        typeWriter.write("Food not found!", 50, 150);
                        typeWriter.write("Try again later...", 50, 150);

                    }
                }

            case 2:





        }
    }




















































    
}