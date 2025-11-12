package EXECUTE;

import Player.Player;
import Pet.PetManager;
import Pet.Pet;

import java.util.Scanner;

import House.HouseList;
import Item.Accessory.*;
import Item.Food.*;
import Item.Furniture.*;
import Minigame.*;
import Utils.*;

import Minigame.AniQuiz.AnimalQuiz;;













public class Launcher {
    public void Start() throws InterruptedException{
        Player player = new Player();
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
        typeWriter.write("This will be the beginning of your journey! Let's start!", 50 , 150);

        player.playerMenu();
        petManager.buyEgg(player, 0);

        Thread.sleep(250);
        clearScreen.clear();

        int choice = 999;

        while(choice != 0){
            typeWriter.write("=== WHAT DO YOU WANT TO DO ===", 50, 150);
            System.out.println();

            typeWriter.write("1. Interact with pet", 50, 150);
            typeWriter.write("2. Go to shop", 50, 150);
            typeWriter.write("3. Open inventory", 50, 150);
            typeWriter.write("4. Play minigame (to get coins)", 50, 150);
            System.out.println();
            typeWriter.write("0. Exit from the game", 50, 150);
            
            choice = scan.nextInt();

            Thread.sleep(250);
            clearScreen.clear();

            if(choice == 0){
                typeWriter.write("Thank you for playing our game!", 50, 150);
                return;
            }

            switch(choice){
                case 1:
                    petManager.viewAllPets(petManager);
                    System.out.println();
                    String petName;
                    typeWriter.write("Which pet do you want to interact with", 50, 150);
                    typeWriter.write("-> ", 50, 150);
                    petName = scan.nextLine();
                    scan.nextLine();
                    Pet pet = petManager.findPetByName(petName);
                    petManager.loadPetFromFile("pet-simulator\\Pet\\ownedPets.txt");

                    int petChoice = 999;
                    while(petChoice != 0){
                        typeWriter.write("1. See pet feeling.", 50, 150);
                        typeWriter.write("2. Hear pet sound.", 50, 150);
                        typeWriter.write("3. Interact with pet", 50, 150);
                        System.out.println();
                        typeWriter.write("0. Exit from interact with pet", 50, 150);
                        System.out.println();
                        typeWriter.write("What will be your choice?", 50, 150);
                        typeWriter.write("-> ", 50);
                        petChoice = scan.nextInt();

                        if(petChoice == 0){
                            typeWriter.write("Exiting...", 50);
                            Thread.sleep(250);
                            clearScreen.clear();
                            return;
                        }

                        switch(petChoice){
                            case 1:
                                int escape;
                                pet.feeling();
                                System.out.println();
                                typeWriter.write("(press 0 to escape) -> ", 50);
                                break;
                            case 2:
                                pet.sound();
                                System.out.println();
                                typeWriter.write("(press 0 to escape) -> ", 50);
                                break;
                            case 3:
                                switch(pet.getSpecies()){
                                    case "Dog":
                                }
                        }
        










                        
                    }

















                case 2:

                case 3:

                case 4:
                    int minigameChoice;
                    typeWriter.write("==== MINIGAME ====", 50, 150);
                    System.out.println();
                    typeWriter.write("1. AniQuiz", 50, 150);
                    typeWriter.write("2. SpedMath", 50, 150);
                    System.out.println();
                    typeWriter.write("0. Exit from minigame", 50, 150);
                    System.out.println();
                    typeWriter.write("What will be your choice?", 50, 150);
                    typeWriter.write("-> ", 50);

                    minigameChoice = scan.nextInt();

                    switch(minigameChoice){
                        case 1:
                            
                            
                            petManager.viewAllPets(petManager);
                            System.out.println();
                            
                            typeWriter.write("Which pet do you want to play with during this minigame", 50, 150);
                            typeWriter.write("-> ", 50, 150);
                            petName = scan.nextLine();
                            pet = petManager.findPetByName(petName);
                            AniQuiz game = new AniQuiz();
                            
                            
                            
                           
                    }


                    if(minigameChoice == 0){
                        return;
                    }






            }















        }
        
        







    }
}
