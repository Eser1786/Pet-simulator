package Player.Inventory;

import java.util.*;

import House.House;
import House.HouseList;
import Item.Accessory.*;
import Item.Food.*;
import Item.Furniture.*;
import Utils.*;
import Pet.Pet;
import Pet.PetManager;
import Player.Player;

public class Inventory {
    public static final String OWNED_PETS_PATH = "pet-simulator\\Pet\\ownedPets.txt";

    public void printPetWithoutAccessory(PetManager pm) throws InterruptedException{
        ArrayList<Pet> ownedPet = new ArrayList<>();
        pm.loadPetFromFile(OWNED_PETS_PATH);
        typeWriter.write("==== PETS WITHOUT ACCESSORY ====", 0, 0);
        for(Pet pet : ownedPet){
            if(pet.getItem() != null && pet.getItem().equals("null")){
                typeWriter.write(pet.getName() + "|" + pet.getSpecies() , 50, 150);
                System.out.println();
            }
        }
    }
    
    public void openInventory(Player player) throws InterruptedException{
        AccessoryList a = new AccessoryList();
        FoodList f = new FoodList();
        FurnitureList fur = new FurnitureList();
        PetManager pm = new PetManager();

        typeWriter.write("========== INSIDE "+ player.getName().toUpperCase() +" INVENTORY ==========", 30, 10);
        
        a.printListAccessory();
        f.printListFood();
        fur.printListFurniture();   
        pm.viewAllPets(pm);
        System.out.println();
        Thread.sleep(3000);
        clearScreen.clear();
        return;

    }

    public void menuInventory(Player player, PetManager petManager) throws InterruptedException{
        Scanner scan = new Scanner(System.in);

        int firstChoice = 999;

        AccessoryList accessoryList = new AccessoryList();
        FoodList food = new FoodList();
        FurnitureList furnitureList = new FurnitureList();

        HouseList houseList = new HouseList(petManager);

        while(firstChoice!=0){
            try {
                typeWriter.write("===== INVENTORY =====", 50, 150);
                System.out.println();
                typeWriter.write("What do you want to do?", 50, 150);
                System.out.println();
                typeWriter.write("1. Myself", 50, 150);
                typeWriter.write("2. Open all inventory", 50, 150);
                typeWriter.write("3. Open food section", 50, 150);
                typeWriter.write("4. Open accessory section", 50, 150);
                typeWriter.write("5. Open furniture section", 50, 150);
                typeWriter.write("6. Your pets", 50, 150);
                typeWriter.write("7. Your houses", 50, 150);
                System.out.println();
                typeWriter.write("0. Exit from your inventory", 50, 150);
                System.out.println();
                typeWriter.write("What is your choice?", 50, 150);
                typeWriter.write("-> ", 50);
                
                if (!scan.hasNextInt()) {
                    typeWriter.write("Invalid input! Please enter a number.", 50, 150);
                    scan.nextLine();
                    Thread.sleep(250);
                    clearScreen.clear();
                    continue;
                }
                
                firstChoice = scan.nextInt();
                scan.nextLine();

                Thread.sleep(250);
                clearScreen.clear();
                
                if(firstChoice == 0){
                    Thread.sleep(250);
                    typeWriter.write("Exiting...!", 50, 150);
                    Thread.sleep(250);
                    clearScreen.clear();
                    return;
                }

                int secondChoice;

            switch(firstChoice){
                case 1:
                    player.profile(player,petManager, food, accessoryList, furnitureList, houseList);
                    break;
                case 2:
                    this.openInventory(player);
                    break;
                case 3:
                    food.loadFood("pet-simulator\\Item\\Food\\ownedFood.txt");
                    food.printListFood();

                    System.out.println();
                    typeWriter.write("What do you want to do?", 50, 150);
                    System.out.println();
                    typeWriter.write("1. Feed pet", 50, 150);
                    typeWriter.write("2. Exit", 50, 150);
                    System.out.println();
                    typeWriter.write("What is your choice?", 50, 150);
                    typeWriter.write("-> ", 50);
                    secondChoice = scan.nextInt();
                    scan.nextLine(); 
                    
                    if(secondChoice == 2){
                        typeWriter.write("Exiting...", 50, 150);
                        Thread.sleep(250);
                        clearScreen.clear();
                        break;
                    }
                    else if(secondChoice == 1){
                        petManager.loadPetFromFile(OWNED_PETS_PATH);
                        petManager.viewAllPets(petManager);
                        System.out.println();
                        String petName;
                        String foodName;

                        typeWriter.write("What food are you going to choose? (write the name of the food you want to choose)", 50, 150);
                        typeWriter.write("-> ", 50);
                        foodName = scan.nextLine();
                        
                        typeWriter.write("which pet are you going to choose? (write the name of the pet you want to choose)", 50, 150);
                        typeWriter.write("-> ", 50);
                        petName = scan.nextLine();

                        petManager.feedPet(petName, foodName);

                        Thread.sleep(250);
                        clearScreen.clear();
                    }

                    break;
                case 4: 
                    accessoryList.loadAccessory("pet-simulator\\Item\\Accessory\\ownedAccessory.txt");
                    

                    if(accessoryList.totalAccessory() == 0){
                        typeWriter.write("You don't have any accessory....Please buy any in the shop", 30, 150);
                        System.out.println();
                        typeWriter.write("Press any key to continue", 30, 150);
                        typeWriter.write("-> ", 30);
                        scan.nextLine();
                        Thread.sleep(250);
                        clearScreen.clear();
                        break;
                    }



                    accessoryList.printListAccessory();
                    System.out.println();
                    typeWriter.write("What do you want to do?", 50, 150);
                    System.out.println();
                    typeWriter.write("1. Equip accessory for pet", 50, 150);
                    typeWriter.write("2. Unequip accessory for pet", 50, 150);
                    typeWriter.write("3. Exit", 50, 150);
                    System.out.println();
                    typeWriter.write("What is your choice?", 50, 150);
                    typeWriter.write("-> ", 50);
                    secondChoice = scan.nextInt();
                    scan.nextLine(); 
                    
                    if(secondChoice == 3){
                        typeWriter.write("Exiting...", 50, 150);
                        Thread.sleep(250);
                        clearScreen.clear();
                        break;
                    }
                    else if(secondChoice == 1){
                        petManager.loadPetFromFile(OWNED_PETS_PATH);
                        petManager.viewAllPets(petManager);
                        System.out.println();
                        String petName;
                        String accessoryName;

                        typeWriter.write("What accessory are you going to choose? (write the name of the accessory you want to choose)", 50, 150);
                        typeWriter.write("-> ", 50);
                        accessoryName = scan.nextLine();
                        
                        typeWriter.write("which pet are you going to choose? (write the name of the pet you want to choose)", 50, 150);
                        typeWriter.write("-> ", 50);
                        petName = scan.nextLine();

                        Pet pet = petManager.findPetByName(petName);
                        Accessory accessory = accessoryList.findAccessoryByName(accessoryName);
                        
                        if(pet != null && accessory != null){
                            boolean equipped = pet.equipAccessory(accessory);
                            if(equipped){
                                accessoryList.removeAccessory(accessory.getItemID()/100, 1);
                                petManager.saveAllPets(OWNED_PETS_PATH);
                            }
                        }
                        else{
                            if(pet == null) typeWriter.write("Pet not found!", 50, 150);
                            if(accessory == null) typeWriter.write("Accessory not found!", 50, 150);
                        }

                        Thread.sleep(250);
                        clearScreen.clear();
                    }
                    else if(secondChoice == 2){
                        petManager.loadPetFromFile(OWNED_PETS_PATH);
                        petManager.viewAllPets(petManager);
                        System.out.println();
                        String petName;
                        
                        typeWriter.write("which pet are you going to choose? (write the name of the pet you want to choose)", 50, 150);
                        typeWriter.write("-> ", 50);
                        petName = scan.nextLine();

                        Pet pet = petManager.findPetByName(petName);
                        if(pet != null){
                            Accessory equipped = pet.getAccessory();
                            if(equipped != null){
                                pet.unequipAccessory();
                                
                                accessoryList.addAccessory(equipped.getItemID()/100, 1);
                                petManager.saveAllPets(OWNED_PETS_PATH);
                            } else {
                                typeWriter.write("This pet has no accessory to unequip.", 50, 150);
                            }
                        }
                        else{
                            typeWriter.write("Pet not found!", 50, 150);
                        }

                        Thread.sleep(250);
                        clearScreen.clear();
                    }
                    break;
                case 5:
                    
                    HouseList hl = new HouseList();
                    hl.loadHouse();
                    furnitureList.loadFur("pet-simulator\\Item\\Furniture\\ownedFurniture.txt");
                    if(furnitureList.totalFurniture() == 0){
                        typeWriter.write("You don't have any furniture... please buy one before checking?", 30, 150);
                        System.out.println();
                        typeWriter.write("Press any key to continue", 30, 150);
                        typeWriter.write("-> ", 30);
                        scan.nextLine();
                        Thread.sleep(250);
                        clearScreen.clear();
                        break;
                    }
                    
                    furnitureList.printListFurniture();

                    System.out.println();
                    typeWriter.write("What do you want to do?", 50, 150);
                    System.out.println();
                    typeWriter.write("1. Add furniture to house", 50, 150);
                    typeWriter.write("2. Exit", 50, 150);
                    System.out.println();
                    typeWriter.write("What is your choice?", 50, 150);
                    typeWriter.write("-> ", 50);
                    secondChoice = scan.nextInt();
                    scan.nextLine(); 

                    if(secondChoice == 2){
                        typeWriter.write("Exiting...", 50, 150);
                        Thread.sleep(250);
                        clearScreen.clear();
                        break;
                    }

                    switch(secondChoice){
                        
                        case 1:
                            hl.printHouse();
                            System.out.println();
                            typeWriter.write("Choose the house you want to add the furniture to (use the number of the house)", 50, 150);
                            typeWriter.write("-> ", 50);
                            int index;
                            index = scan.nextInt();
                            scan.nextLine(); // Clear newline
                            House house = hl.findHouseByID(index);
                            
                            if(house!=null){
                                furnitureList.printListFurniture();
                                String furName;
                                typeWriter.write("Type the name of the furniture you want to add (type the name)", 50, 150);
                                typeWriter.write("-> ", 50);
                                furName = scan.nextLine().toLowerCase();
                                Furniture furniture = furnitureList.findFurnitureByName(furName);
                                
                                if(furniture != null){
                                    house.addFurniture(furniture);
                                }
                                else{
                                    typeWriter.write("Furniture not found!", 50, 150);
                                }
                                
                                Thread.sleep(250);
                                clearScreen.clear();
                            }
                            else{
                                typeWriter.write("House not found...", 50, 150);
                                Thread.sleep(250);
                                clearScreen.clear();
                            }
                            break;
                    }
                    break;
                case 6:
                    petManager.loadPetFromFile(OWNED_PETS_PATH);
                    petManager.viewAllPets(petManager);
                    System.out.println();
                    typeWriter.write("Press any key to continue!", 50, 150);
                    typeWriter.write("-> ", 50);
                    
                    scan.nextLine();
                    Thread.sleep(250);
                    clearScreen.clear();
                    break;
                    

                case 7:
                    houseList = new HouseList(petManager);
                    houseList.loadHouse();
                    
                    if(houseList.totalHouse() == 0){
                        typeWriter.write("You don't have any house yet! Please buy a house from the shop.", 50, 150);
                        Thread.sleep(250);
                        clearScreen.clear();
                        return;
                    }
                    
                    houseList.printHouse();
                    System.out.println();
                    typeWriter.write("What do you want to do?", 50, 150);
                    System.out.println();
                    typeWriter.write("1. Add a pet to this house.", 50, 150);
                    typeWriter.write("2. Add furniture to this house.", 50, 150);
                    typeWriter.write("3. Remove pet from this house.", 50, 150);
                    System.out.println();   
                    typeWriter.write("0. Exit.", 50, 150);
                    System.out.println();
                    typeWriter.write("What is your choice?", 50, 150);
                    typeWriter.write("-> ", 50);
                    secondChoice = scan.nextInt();
                    scan.nextLine(); 

                    if(secondChoice == 0){
                        typeWriter.write("Exiting...", 50, 150);
                        Thread.sleep(250);
                        clearScreen.clear();
                        return;
                    }

                    Thread.sleep(250);
                    clearScreen.clear();

                    switch(secondChoice){
                        case 1:
                            houseList.printHouse();
                            System.out.println();
                            typeWriter.write("Choose the house you want to set the pet (use the number of the house)", 50, 150);
                            typeWriter.write("-> ", 50);
                            int index ;
                            index = scan.nextInt();
                            scan.nextLine(); 
                            House house = houseList.findHouseByID(index);
                            
                            if(house!=null){
                                if(house.hasPet()){
                                    typeWriter.write("This house already has a pet!", 50, 150);
                                }
                                else{
                                    petManager.loadPetFromFile(OWNED_PETS_PATH);
                                    petManager.viewAllPets(petManager);
                                    System.out.println();
                                    String petName;
                                    typeWriter.write("Which pet are you going to choose for this house (write the name)", 50, 150);
                                    typeWriter.write("-> ", 50);
                                    petName = scan.nextLine();
                                    Pet pet = petManager.findPetByName(petName);
                                    
                                    if(pet != null){
                                        houseList.assignPetToHouse(index,pet.getPetID());
                                        houseList.saveHouse("pet-simulator\\House\\ownedHouse.txt");
                                    }
                                    else{
                                        typeWriter.write("Pet not found!", 50, 150);
                                    }
                                }
                                Thread.sleep(250);
                                clearScreen.clear();
                                break;
                            }
                            else{
                                typeWriter.write("house not found...", 50, 150);
                                Thread.sleep(250);
                                clearScreen.clear();
                                break;
                            }
                        
                        case 2:
                            houseList.printHouse();
                            System.out.println();
                            typeWriter.write("Choose the house you want to add the furniture to (use the number of the house)", 50, 150);
                            typeWriter.write("-> ", 50);
                            
                            index = scan.nextInt();
                            scan.nextLine(); 
                            house = houseList.findHouseByID(index);
                            
                            if(house!=null){
                                furnitureList.loadFur("pet-simulator\\Item\\Furniture\\ownedFurniture.txt");
                                furnitureList.printListFurniture();
                                String furName;
                                typeWriter.write("Type the name of the furniture you want to add (type the name)", 50, 150);
                                typeWriter.write("-> ", 50);
                                furName = scan.nextLine().toLowerCase();
                                Furniture furniture = furnitureList.findFurnitureByName(furName);
                                
                                if(furniture != null){
                                    houseList.assignFurToHouse(index,furniture.getItemID());
                                    houseList.saveHouse("pet-simulator\\House\\ownedHouse.txt");
                                }
                                else{
                                    typeWriter.write("Furniture not found!", 50, 150);
                                }
                                
                                Thread.sleep(250);
                                clearScreen.clear();
                            }
                            else{
                                typeWriter.write("House not found...", 50, 150);
                                Thread.sleep(250);
                                clearScreen.clear();
                            }
                            break;

                        case 3:
                            houseList.printHouse();
                            System.out.println();
                            typeWriter.write("Choose the house you want to remove the pet from (use the number of the house)", 50, 150);
                            typeWriter.write("-> ", 50);
                            index = scan.nextInt();
                            scan.nextLine(); 
                            house = houseList.findHouseByID(index);
                            if(house != null){
                                if(!house.hasPet()){
                                    typeWriter.write("This house has no pet to remove.", 50, 150);
                                } else {
                                    house.removePet();
                                    System.out.println();
                                    typeWriter.write("Press any key to continue!", 50, 150);
                                    typeWriter.write("-> ", 50);
                                    houseList.saveHouse("pet-simulator\\House\\ownedHouse.txt");
                                    petManager.saveAllPets(OWNED_PETS_PATH);
                                }
                            } else {
                                typeWriter.write("House not found...", 50, 150);
                            }
                            scan.nextLine();
                            Thread.sleep(250);
                            clearScreen.clear();
                            break;
                    }

            }
            } catch (Exception e) {
                typeWriter.write("An error occurred: " + e.getMessage(), 50, 150);
                scan.nextLine();
                Thread.sleep(250);
                clearScreen.clear();
            }
        }
    }



}

