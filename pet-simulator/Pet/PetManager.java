package Pet;
import java.io.*;
import Pet.species.*;
import Player.Player;
import Utils.textColor;
import Utils.typeWriter;


import java.util.*;

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
            String line = pet.getPetID() + "|" + pet.getSpecies() + "|" + pet.getName() + "|" + pet.getLevel() + "|" + pet.getHealth() + "|" + pet.getHunger() + "|" + pet.getMentalHealth() + "|" + pet.getSex() +"|" + pet.getItem() + "|" + pet.getFavoriteDish() ;
            bw.write(line);
            bw.newLine();
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    // Đọc lại danh sách pets đã sỡ hữu
    public void loadPetFromFile(String filePath){
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String line;
            
            while((line = br.readLine()) != null){
                String parts[] = line.split("\\|");

                if(parts.length == 10){
                    int petID = Integer.parseInt(parts[0]);
                    String species = parts[1];
                    String name = parts[2];
                    int level = Integer.parseInt(parts[3]);
                    int health = Integer.parseInt(parts[4]);
                    int hunger = Integer.parseInt(parts[5]);
                    int mentalHealth = Integer.parseInt(parts[6]);
                    String sex = parts[7];
                    String item = parts[8];
                    String favoriteDish = parts[9];

                    Pet pet = null;
                    switch (species.toLowerCase()){
                        case "dog":
                            pet = new Dog(petID, species, name, health, level, hunger, mentalHealth, sex, item, favoriteDish);
                            break;
                        case "cat":
                            pet = new Cat(petID, species, name, health, level, hunger, mentalHealth, sex, item, favoriteDish);
                            break;
                        case "parrot":
                            pet = new Parrot(petID, species, name, health, level, hunger, mentalHealth, sex, item, favoriteDish);
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
            switch(pet.getSpecies()){
                case "dog":
                    if(pet.getPetID() == 100 + petID){
                        return pet;
                    }
                    break;
                case "cat":
                    if(pet.getPetID() == 200 + petID){
                        return pet;
                    }
                    break;
                case "parrot":
                    if(pet.getPetID() == 300 + petID){
                        return pet;
                    }
                    break;
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

                            typeWriter.write("What will be the " + pet.getSpecies() + " name?", 50, 150);
                            typeWriter.write("-> ",50);
                            pet.setName(scan.nextLine());
                            pet.setHealth(100);
                            pet.setHunger(0);
                            pet.setLevel(0);
                            pet.setMentalHealth(100);
                            pet.setItem(null);
                            pet.setFavoriteDish(null);  // mốt thêm sau dựa theo species
                            
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

        if(player.getCoin() < cost){
            typeWriter.write(player.getName() + " doesn't have enough coin for this item", 50, 150);
            typeWriter.write("Come back when you have enough coin!", 50, 150);
            return;
        }

        player.setCoin(player.getCoin() - cost);
        typeWriter.write("You bought an egg!", 50, 150);
        typeWriter.write("The egg is hatching!", 50, 150);
        typeWriter.write("............", 50, 150);
        
        Random rd = new Random();
        int petID = rd.nextInt(3)+1;
        this.AddPet(petID);

    }

    public void viewAllPets(PetManager petManager) throws InterruptedException{
        System.out.println();
        loadPetFromFile(OWNED_PETS_PATH);
        typeWriter.write("====== PETS ======", 50, 150);
        System.out.println();
        for(Pet pet : ownedPet){
            typeWriter.write(pet.getName(), 50, 150);
            typeWriter.write(" | Species: " + pet.getSpecies(), 50);

            typeWriter.write(" | Gender: " + pet.getSex() , 50,150);

            typeWriter.write(" | Level: " , 50);
            textColor.yellowText(pet.getLevel());

            typeWriter.write(" | HP: " , 50);
            textColor.greenText(pet.getHealth());

            typeWriter.write(" | Hunger: " ,50 );
            textColor.orangeText(pet.getHunger());

            if(pet.getItem() == "null"){
                typeWriter.write(" | Accessory: none" , 50);
            }
            else{
                typeWriter.write(" | Accessory: " + pet.getItem() , 50);
            }
            System.out.println();
            System.out.println();
        }
    }

































}
