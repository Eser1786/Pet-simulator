package Player.Inventory;

import Item.Accessory.*;
import Item.Food.*;
import Item.Furniture.*;
import Utils.*;

public class Inventory {
    
    public void openInventory() throws InterruptedException{
        AccessoryList a = new AccessoryList();
        FoodList f = new FoodList();
        FurnitureList fur = new FurnitureList();


        typeWriter.write("========== INVENTORY ==========", 30, 10);
        a.printListAccessory();
        f.printListFood();
        fur.printListFurniture();
        System.out.println();
    }








}

