package Pet;
import java.io.*;
import Pet.species.*;

import java.util.*;

public class PetManager {

    //  Hàm tạo id riêng cho pet nếu sở hữu
    public int generatePetID(String species){
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
    public int rollTheDice(){
        Random random = new Random();
        return random.nextInt(100) + 1;
    }

    //  Lưu các thông tin của pet vào 1 file riêng 
    public void savePetToFile(Pet pet, String filePath){
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
                        case "cat":
                            pet = new Cat(petID, species, name, health, level, hunger, mentalHealth, sex, item, favoriteDish);
                        case "parrot":
                            pet = new Parrot(petID, species, name, health, level, hunger, mentalHealth, sex, item, favoriteDish);
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
    // manager.loadPetFromFile("ownedPets.txt") để tải lại các pets đã có




    //  tạo danh sách để lưu trữ các pets
    ArrayList<Pet> ownedPet = new ArrayList<>();

    public void AddPet(String filePath, int petID){

        Scanner scan = new Scanner(System.in);
        
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
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

                            System.out.println("Hãy đặt tên cho chú " + pet.getSpecies() +": ");
                            pet.setName(scan.nextLine());
                            pet.setHealth(100);
                            pet.setHunger(0);
                            pet.setLevel(0);
                            pet.setMentalHealth(100);
                            pet.setItem(null);
                            pet.setFavoriteDish(null);  // mốt thêm sau dựa theo species
                            
                            ownedPet.add(pet);
                            savePetToFile(pet, "ownedPets.txt");

                            System.out.println("Đã lưu");
                        }
                    }
                }
            }
        }   catch(IOException e){
            e.getMessage();
        }
    }
}
