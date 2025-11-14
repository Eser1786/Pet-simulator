package Pet;
import java.io.*;
import Pet.species.*;
import Player.Player;
import Utils.textColor;
import Utils.typeWriter;


import java.util.*;

import Item.Food.Food;
import Item.Food.FoodList;

public class PetManager {

    public static final String PET_PATH = "pet-simulator\\Pet\\species\\species.txt";
    public static final String OWNED_PETS_PATH = "pet-simulator\\Pet\\ownedPets.txt";

    


    //  Hàm tạo id riêng cho pet nếu sở hữu
    private int generatePetID(String species){
        int speciesID;
        switch (species){
            case "dog":
                speciesID = 1;
                break;
            case "cat":
                speciesID = 2;
                break;
            case "parrot":
                speciesID = 3;
                break;
            case "shark":
                speciesID = 4 ;
                break;
            case "frog":
                speciesID = 5;
                break;
            case "dragon":
                speciesID = 6;
                break;
            case "mole":
                speciesID = 7;
                break;
            case "axolotl":
                speciesID = 8;
                break;
            case "T-rex":
                speciesID = 9;
                break;
            default:
                speciesID = 10;    
                break; 
        }
        int count = 0;
        for(Pet pet : ownedPet){
            if (pet.getSpecies().equalsIgnoreCase(species)){
                count++;
            }
        }
        return speciesID * 100 + (count + 1);
    }

    //  tung xúc xắc để quyết định dựa theo 1 -> 100
    private int rollTheDice(){
        Random random = new Random();
        return random.nextInt(100) + 1;
    }

    //  Lưu các thông tin của pet vào 1 file riêng 
    private void savePetToFile(Pet pet, String filePath){
        try( BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))){
            String line = pet.getPetID() + "|" + pet.getSpecies() + "|" + pet.getName() + "|" + pet.getHealth() + "|" + pet.getLevel() + "|" + pet.getHunger() + "|" + pet.getMentalHealth() + "|" + pet.getSex() +"|" + pet.getItem();
            bw.write(line);
            bw.newLine();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public void saveAllPets(String filePath){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))){
            for(Pet pet : ownedPet){
                String line = pet.getPetID() + "|" + pet.getSpecies() + "|" + pet.getName() + "|" + pet.getHealth() + "|" + pet.getLevel() + "|" + pet.getHunger() + "|" + pet.getMentalHealth() + "|" + pet.getSex() +"|" + pet.getItem();
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    // Đọc lại danh sách pets đã sỡ hữu
    public void loadPetFromFile(String filePath){
        ownedPet.clear();
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String line;
            
            while((line = br.readLine()) != null){
                String parts[] = line.split("\\|");

                if(parts.length == 9){
                    int petID = Integer.parseInt(parts[0]);
                    String species = parts[1];
                    String name = parts[2];
                    int health = Integer.parseInt(parts[3]);
                    int level = Integer.parseInt(parts[4]);
                    int hunger = Integer.parseInt(parts[5]);
                    int mentalHealth = Integer.parseInt(parts[6]);
                    String sex = parts[7];
                    String item = parts[8];
                    

                    Pet pet = null;
                    switch (species.toLowerCase()){
                        case "dog":
                            pet = new Dog(petID, species, name, health, level, hunger, mentalHealth, sex, item);
                            break;
                        case "cat":
                            pet = new Cat(petID, species, name, health, level, hunger, mentalHealth, sex, item);
                            break;
                        case "parrot":
                            pet = new Parrot(petID, species, name, health, level, hunger, mentalHealth, sex, item);
                            break;
                        case "shark":
                            pet = new Shark(petID, species, name, health, level, hunger, mentalHealth, sex, item);
                            break;
                        case "frog":
                            pet = new Frog(petID, species, name, health, level, hunger, mentalHealth, sex, item);
                            break;
                        case "dragon":
                            pet = new Dragon(petID, species, name, health, level, hunger, mentalHealth, sex, item);
                            break;
                        case "mole":
                            pet = new Mole(petID, species, name, health, level, hunger, mentalHealth, sex, item);
                            break;
                        case "axolotl": 
                            pet = new Axolotl(petID, species, name, health, level, hunger, mentalHealth, sex, item);
                            break;
                        case "t-rex":
                            pet = new T_rex(petID, species, name, health, level, hunger, mentalHealth, sex, item);
                            break;
                    }
                    if( pet != null){
                        ownedPet.add(pet);
                    }
                }
            }
        } catch (IOException e){
            e.getMessage();
        }
    }

    // PetManager manager = new PetManage();
    // manager.loadPetFromFile("C:\Users\ACER\Documents\GitHub\Pet-simulator\pet-simulator\Pet\ownedPets.txt") để tải lại các pets đã có

    public Pet findPetByID(int petID){
        for(Pet pet : ownedPet){
            if(pet.getPetID() == petID){
                return pet;
            }
        }
        return null;
    }

    public Pet findPetByName(String name){
        loadPetFromFile(OWNED_PETS_PATH);
        for(Pet pet : ownedPet){
            if(pet.getName() != null && pet.getName().equalsIgnoreCase(name)){
                return pet;
            }
        }
        return null;
    }

    

    //  tạo danh sách để lưu trữ các pets
    ArrayList<Pet> ownedPet = new ArrayList<>();

    public void AddPet(int petID) throws InterruptedException{      // them vao danh sach chi su dung id 

        Scanner scan = new Scanner(System.in);

        loadPetFromFile(OWNED_PETS_PATH);
        
        try(BufferedReader br = new BufferedReader(new FileReader(PET_PATH))){
            String line;

            while( (line = br.readLine()) != null){
                String[] parts = line.split("\\|");

                if(parts.length == 2){
                    int id = Integer.parseInt(parts[0].trim());
                    String species = parts[1].trim();

                    if(id == petID){
                        Pet pet = null;
                        
                        switch(species.toLowerCase()){
                            case "dog":
                                pet = new Dog();
                                break;
                            case "cat":
                                pet = new Cat();
                                break;
                            case "parrot":
                                pet = new Parrot();
                                break;
                            case "shark":
                                pet = new Shark();
                                break;
                            case "frog":
                                pet = new Frog();
                                break;
                            case "dragon":
                                pet = new Dragon();
                                break;
                            case "mole":
                                pet = new Mole();
                                break;
                            case "axolotl":
                                pet = new Axolotl();
                                break;
                            case "t-rex":
                                pet = new T_rex();
                                break;
                        }

                        if(pet != null){
                            pet.setPetID(generatePetID(species));
                            pet.setSpecies(species);

                            if(rollTheDice() >= 50){
                                pet.setSex("male");
                            }
                            else{ 
                                pet.setSex("female");
                            }

                            typeWriter.write("What will be the " + textColor.RED+  pet.getSpecies() + textColor.RESET + " name?", 50, 150);
                            typeWriter.write("-> ",50);
                            Random rd = new Random();
                            pet.setName(scan.nextLine());
                            pet.setHealth(rd.nextInt(100) + 1);
                            pet.setHunger(rd.nextInt(100) + 1);
                            pet.setLevel(0);
                            pet.setMentalHealth(rd.nextInt(100) + 1);
                            pet.setItem(null);
                            
                            
                            ownedPet.add(pet);
                            savePetToFile(pet, OWNED_PETS_PATH);

                        }
                    }
                }
            }
        }   catch(IOException e){
            e.getMessage();
        }
    }

    public void buyEgg(Player player, int cost) throws InterruptedException{

        if(cost == 0){
            typeWriter.write("You have received an egg! \n", 30, 150);

        }

        if(player.getCoin() < cost){
            typeWriter.write(player.getName() + " doesn't have enough coin for this item", 50, 150);
            typeWriter.write("Come back when you have enough coin!", 50, 150);
            return;
        }

        player.setCoin(player.getCoin() - cost);
        if(cost > 0){
            typeWriter.write("You bought an egg! \n", 50, 150);
        }
        typeWriter.write("The egg is hatching!", 50, 150);
        typeWriter.write("............", 450, 150);
        
        Random rd = new Random();
        int petID = rd.nextInt(9)+1;
        this.AddPet(petID);
    }

    public void viewAllPets(PetManager petManager) throws InterruptedException{
        System.out.println();
        loadPetFromFile(OWNED_PETS_PATH);
        typeWriter.write("====== PETS ======", 50, 150);
        System.out.println();
        for(Pet pet : ownedPet){
            typeWriter.write( textColor.PURPLE + pet.getName() + textColor.RESET, 50, 150);
            typeWriter.write(" | Species: " + textColor.RED + pet.getSpecies() + textColor.RESET, 50);

            typeWriter.write(" | Gender: " + pet.getSex() , 50,150);

            typeWriter.write(" | Level: " , 50);
            textColor.yellowText(pet.getLevel());

            typeWriter.write(" | HP: " , 50);
            textColor.greenText(pet.getHealth());

            typeWriter.write(" | Hunger: " ,50 );
            textColor.orangeText(pet.getHunger());

            typeWriter.write(" | Mental health: " ,50 );
            textColor.blueText(pet.getMentalHealth());

            if("null".equals(pet.getItem())){
                typeWriter.write(" | Accessory: none" , 50);
            }
            else{
                typeWriter.write(" | Accessory: " + textColor.BLUE + pet.getItem() +textColor.RESET, 50);
            }
            System.out.println();
            System.out.println();
        }
    }

    public void printPetForShop() throws InterruptedException{
       
        PetManager pm = new PetManager();
        try(BufferedReader br = new BufferedReader(new FileReader("pet-simulator\\Pet\\species\\species.txt"))){
            String line;
           
            while((line = br.readLine())!= null){
                
                String[] parts = line.split("\\|");
                Pet pet = null;
                if(parts.length == 2){
                    int id = Integer.parseInt(parts[0].trim());
                    String species = parts[1].trim().toLowerCase();   
                    
                    switch(species){
                        case "dog":
                                pet = new Dog();
                                pet.setSpecies(species);
                                break;
                            case "cat":
                                pet = new Cat();
                                pet.setSpecies(species);
                                break;
                            case "parrot":
                                pet = new Parrot();
                                pet.setSpecies(species);
                                break;
                            case "shark":
                                pet = new Shark();
                                pet.setSpecies(species);
                                break;
                            case "frog":
                                pet = new Frog();
                                pet.setSpecies(species);
                                break;
                            case "dragon":
                                pet = new Dragon();
                                pet.setSpecies(species);
                                break;
                            case "mole":
                                pet = new Mole();
                                pet.setSpecies(species);
                                break;
                            case "axolotl":
                                pet = new Axolotl();
                                pet.setSpecies(species);
                                break;
                            case "t-rex":
                                pet = new T_rex();
                                pet.setSpecies(species);
                                break;
                    }
                    if(pet!=null){
                        pet.setSpecies(species);
                        ownedPet.add(pet);
                    }
                }
               
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        typeWriter.write("==== PET ====", 50, 150);
        
        int index=0;
        for(Pet pet : ownedPet){
            typeWriter.write(index + 1 + ". ", 50);
            textColor.orangeText( textColor.RED + pet.getSpecies() + textColor.RESET);
            typeWriter.write(" | cost: ", 50);
            textColor.yellowText(30 + " coins");
            System.out.println();
            index++;
        }
        typeWriter.write("The chances of having any pet are equal!", 50, 150);
    }

    public void feedPet(String petName, String foodName) throws InterruptedException{    // find pet by name
        PetManager petManager = new PetManager();
        Pet pet = petManager.findPetByName(petName.toLowerCase());
        FoodList fl = new FoodList();
        Food food = fl.findFoodByName(foodName.toLowerCase());
        if(food==null){
            typeWriter.write("you don't have this food", 50, 150);
            return;
        }
        if(pet==null){
            typeWriter.write("you don't have this pet", 0, 0);
            return;
        }

        typeWriter.write(textColor.PURPLE + pet.getName()  + textColor.RESET + " has eaten the " + food.getItemName(), 50, 150);
        pet.setHunger(pet.getHunger() - food.getSat());
        
       


        if(pet.getHunger() < 0){
            pet.setHunger(0);
        }
        typeWriter.write( textColor.PURPLE + pet.getName()  + textColor.RESET + " hunger: " + textColor.ORANGE +pet.getHunger() + textColor.RESET, 50, 150);
        pet.gainedHealth(20);
        food.setQuantity(food.getQuantity()-1);
    }

    
    public void viewOnePet(Pet pet) throws InterruptedException{
        typeWriter.write("=== PET ===", 50, 150);
        System.out.println();
        typeWriter.write( textColor.PURPLE +  pet.getName() + textColor.RESET , 50, 150);
        typeWriter.write(" | Species: " + textColor.RED + pet.getSpecies() + textColor.RESET, 50);

        typeWriter.write(" | Gender: " + pet.getSex() , 50,150);

        typeWriter.write(" | Level: " , 50);
        textColor.yellowText(pet.getLevel());

        typeWriter.write(" | HP: " , 50);
        textColor.greenText(pet.getHealth());

        typeWriter.write(" | Hunger: " ,50 );
        textColor.orangeText(pet.getHunger());

        typeWriter.write(" | Mental health: " ,50 );
        textColor.blueText(pet.getMentalHealth());

        if(pet.getItem().equals("null")){
            typeWriter.write(" | Accessory: none" , 50);
        }
        else{
            typeWriter.write(" | Accessory: " + textColor.ORANGE + pet.getItem() + textColor.RESET , 50);
        }
        System.out.println();
        System.out.println();
    }





}
