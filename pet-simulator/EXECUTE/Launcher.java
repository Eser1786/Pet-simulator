package EXECUTE;

import Player.Player;
import Player.Inventory.Inventory;
import Shop.Shop;
import Pet.PetManager;
import Pet.Pet;

import java.util.Scanner;

import House.HouseList;
import Item.Accessory.*;
import Item.Food.*;
import Item.Furniture.*;
import Minigame.*;
import Utils.*;

public class Launcher {
    public void Start() throws InterruptedException{
        Player player = new Player();
        player = player.loadPlayer("pet-simulator\\Player\\player.txt");
        
        PetManager petManager = new PetManager();
        FoodList foodList = new FoodList();
        AccessoryList accessoryList = new AccessoryList();
        FurnitureList furnitureList = new FurnitureList();
        HouseList houseList = new HouseList();
        
        Scanner scan = new Scanner(System.in);

        Thread.sleep(250);
        clearScreen.clear();


        typeWriter.write("===== WELCOME =====", 50, 150);
        System.out.println();

        if(player != null){
            typeWriter.write("Welcome back " + textColor.YELLOW + player.getName()  + textColor.RESET + "!" , 30, 150);
            typeWriter.write("Let's get started", 30, 150);
            System.out.println();
            typeWriter.write("Press any key to continue!", 30, 150);
            typeWriter.write("-> ",30);
            scan.nextLine();
            Thread.sleep(250);
            clearScreen.clear();
        }
        else{
            player = new Player();
            typeWriter.write("This will be the beginning of your journey! Let's start!", 50 , 150);
    
            player.playerMenu();
            petManager.buyEgg(player, 0);
    
            Thread.sleep(250);
            clearScreen.clear();
        }

        int choice = 999;

        while(choice != 0){
            typeWriter.write("=== WHAT DO YOU WANT TO DO ===", 50, 150);
            System.out.println();

            typeWriter.write("Name: " + textColor.YELLOW + player.getName() + "!" + textColor.RESET, 30, 150);
            typeWriter.write("Coin: " + textColor.YELLOW + player.getCoin() + " coins" + textColor.RESET, 30, 150);


            System.out.println();

            typeWriter.write("1. Interact with pet", 50, 150);
            typeWriter.write("2. Go to shop", 50, 150);
            typeWriter.write("3. Open inventory", 50, 150);
            typeWriter.write("4. Play minigame (to get coins)", 50, 150);
            System.out.println();
            typeWriter.write("0. Exit from the game", 50, 150);
            System.out.println();
            typeWriter.write("What will you choose?", 50, 150);
            typeWriter.write("-> ", 50);
            choice = scan.nextInt();

            Thread.sleep(250);
            clearScreen.clear();

            if(choice == 0){
                player.savePlayer(player, "pet-simulator\\Player\\player.txt");
                typeWriter.write("Thank you for playing our game!", 50, 150);
                Thread.sleep(250);
                clearScreen.clear();
                break;
            }

            switch(choice){
                case 1:
                    petManager.loadPetFromFile("pet-simulator\\Pet\\ownedPets.txt");
                    petManager.viewAllPets(petManager);
                    System.out.println();
                    String petName;
                    typeWriter.write("Which pet do you want to interact with (input the name of the pet you want to interact with)", 50, 150);
                    typeWriter.write("-> ", 50);
                    scan.nextLine(); 
                    petName = scan.nextLine();
                    Pet pet = petManager.findPetByName(petName);
                    System.out.println();
                    if (pet == null) {
                        typeWriter.write("Pet not found! Please try again.", 50, 150);
                        Thread.sleep(250);
                        clearScreen.clear();
                        break;
                    }

                    int petChoice = 999;
                    while(petChoice != 0){
                        typeWriter.write("==== CHOICE ====", 30, 150);
                        System.out.println();
                
                        typeWriter.write("1. How is " + textColor.PURPLE + pet.getName() + textColor.RESET + " doing.", 50, 150);
                        typeWriter.write("2. talk to " + textColor.PURPLE + pet.getName() + textColor.RESET + ".", 50, 150);
                        typeWriter.write("3. Interact with " + textColor.PURPLE + pet.getName() + textColor.RESET + ".", 50, 150);
                        System.out.println();
                        typeWriter.write("0. Exit from interact with pet", 50, 150);
                        System.out.println();
                        typeWriter.write("What will be your choice? (input the number of the choice you want to make)", 50, 150);
                        typeWriter.write("-> ", 50);
                        petChoice = scan.nextInt();

                        if(petChoice == 0){
                            typeWriter.write("Exiting...", 50);
                            Thread.sleep(250);
                            clearScreen.clear();
                            break;
                        }

                        switch(petChoice){
                            case 1:
                                pet.feeling();
                                System.out.println();
                                typeWriter.write("Press any key to continue!", 30, 150);
                                typeWriter.write("-> ",30);
                                scan.nextLine();
                                scan.nextLine();
                                Thread.sleep(250);
                                clearScreen.clear();
                                break;
                            case 2:
                                pet.sound();
                                System.out.println();
                                typeWriter.write("Press any key to continue!", 50,150);
                                typeWriter.write("-> ", 30);
                                scan.nextLine();
                                scan.nextLine();
                                Thread.sleep(250);
                                clearScreen.clear();
                                break;
                            case 3:
                                interactWithPet(pet, player);
                                petManager.saveAllPets(PetManager.OWNED_PETS_PATH);
                                player.savePlayer(player, "pet-simulator\\Player\\player.txt");
                                System.out.println();
                                typeWriter.write("Press any key to continue!", 50,150);
                                typeWriter.write("-> ", 30);
                                scan.nextLine();
                                scan.nextLine();
                                Thread.sleep(250);
                                clearScreen.clear();
                                break;
                        }
                    }
                    break;
                case 2:
                    Shop shop = new Shop();
                    shop.menuShop(player);
                    player.savePlayer(player, "pet-simulator\\Player\\player.txt");
                    break;
                case 3:
                    Inventory inventory = new Inventory();
                    inventory.menuInventory(player, petManager);
                    player.savePlayer(player, "pet-simulator\\Player\\player.txt");
                    break;
                case 4:
                    int minigameChoice;
                    typeWriter.write("==== MINIGAME ====", 50, 150);
                    System.out.println();
                    typeWriter.write("1. AniQuiz", 50, 150);
                    typeWriter.write("2. SpeedMath", 50, 150);
                    System.out.println();
                    typeWriter.write("0. Exit from minigame", 50, 150);
                    System.out.println();
                    typeWriter.write("What will be your choice?", 50, 150);
                    typeWriter.write("-> ", 50);

                    minigameChoice = scan.nextInt();    

                    Thread.sleep(250);
                    clearScreen.clear();


                    if(minigameChoice == 0){
                        Thread.sleep(250);
                        clearScreen.clear();
                        player.savePlayer(player, "pet-simulator\\Player\\player.txt");
                        break;
                    }

                    switch(minigameChoice){
                        case 1:
                            try {
                                petManager.loadPetFromFile("pet-simulator\\Pet\\ownedPets.txt");
                                petManager.viewAllPets(petManager);
                                System.out.println();
                                
                                typeWriter.write("Which pet do you want to play with during this minigame", 50, 150);
                                typeWriter.write("-> ", 50);
                                scan.nextLine();
                                String gamePetName = scan.nextLine();
                                Pet gamePet = petManager.findPetByName(gamePetName);
                                
                                if (gamePet == null) {
                                    typeWriter.write("Pet not found! Please try again.", 50, 150);
                                    Thread.sleep(250);
                                    clearScreen.clear();
                                    break;
                                }
                                
                                AniQuiz aniQuiz = new AniQuiz();
                                aniQuiz.playAniQuiz(player, gamePet);
                                petManager.saveAllPets(PetManager.OWNED_PETS_PATH);
                                player.savePlayer(player, "pet-simulator\\Player\\player.txt");
                                
                                Thread.sleep(250);
                                clearScreen.clear();
                            } catch (Exception e) {
                                typeWriter.write("An error occurred: " + e.getMessage(), 50, 150);
                                Thread.sleep(250);
                                clearScreen.clear();
                            }
                            break;
                            
                        case 2:
                            try {
                                petManager.loadPetFromFile("pet-simulator\\Pet\\ownedPets.txt");
                                petManager.viewAllPets(petManager);
                                System.out.println();
                                
                                typeWriter.write("Which pet do you want to play with during this minigame", 50, 150);
                                typeWriter.write("-> ", 50);
                                scan.nextLine(); 
                                String gamePetName2 = scan.nextLine();
                                Pet gamePet2 = petManager.findPetByName(gamePetName2);
                                
                                if (gamePet2 == null) {
                                    typeWriter.write("Pet not found! Please try again.", 50, 150);
                                    Thread.sleep(250);
                                    clearScreen.clear();
                                    break;
                                }
                                
                                SpeedMath speedMath = new SpeedMath();
                                speedMath.playSpeedMath(player, gamePet2);
                                petManager.saveAllPets(PetManager.OWNED_PETS_PATH);
                                player.savePlayer(player, "pet-simulator\\Player\\player.txt");
                                
                                Thread.sleep(250);
                                clearScreen.clear();
                            } catch (Exception e) {
                                typeWriter.write("An error occurred: " + e.getMessage(), 50, 150);
                                Thread.sleep(250);
                                clearScreen.clear();
                            }
                            break;
                            
                        default:
                            typeWriter.write("Choice not appropriate!", 50, 150);
                            Thread.sleep(250);
                            clearScreen.clear();
                            break;
                    }
                    break;

            }

        }
    }
    
    
    private void interactWithPet(Pet pet, Player player) throws InterruptedException {
        pet.interact(player);
    }
        







}

