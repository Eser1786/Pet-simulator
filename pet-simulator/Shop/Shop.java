package Shop;

import Item.Food.*;
import Item.Accessory.*;
import Item.Furniture.*;


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


    public void menuShop(Player player){
          
    }




















































    
}