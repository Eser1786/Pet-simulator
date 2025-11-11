package House;

import java.io.*;
import java.util.*;

import Pet.PetManager;
import Utils.typeWriter;
import Pet.Pet;
import Item.Furniture.*;

public class HouseList {
    private static final String OWNED_HOUSE_PATH = "pet-simulator\\House\\ownedHouse.txt";
    private static final int NULL = -999;

    ArrayList<House> ownedHouse = new ArrayList<>();

    PetManager petManager = new PetManager();

    public HouseList(PetManager petManager){
        this.petManager = petManager;
    }

    public House findHouseByID(int id){
        for(House house : ownedHouse){
            if(house.getHouseID() == id){
                return house;
            }
        }
        return null;
    }


    public void loadHouse() throws InterruptedException{
        try( BufferedReader br = new BufferedReader(new FileReader(OWNED_HOUSE_PATH))){
            String line;

            while(( line = br.readLine())!= null ){
                String[] parts = line.split("\\|");
                
                if(parts.length == 5){
                    int houseID = Integer.parseInt(parts[0].trim());
                    int petID = Integer.parseInt(parts[1].trim());
                    int fur1ID = Integer.parseInt(parts[2].trim());
                    int fur2ID = Integer.parseInt(parts[3].trim());
                    int fur3ID = Integer.parseInt(parts[4].trim());

                    House house = new House();
                    house.setHouseID(houseID);

                    if(petID != -999){
                        Pet pet = petManager.findPetByID(petID);
                        if(pet!=null){
                            house.setPet(pet);
                        }
                    }

                    for(int furID : new int[]{fur1ID, fur2ID, fur3ID}){
                        if(furID != -999){
                            FurnitureList furList = new FurnitureList();
                            Furniture furniture = furList.findFurByID(furID);
                            if(furniture != null){
                                house.addFurniture(furniture);
                            }
                        }
                    }
                    ownedHouse.add(house);
                }
            }


        } catch (IOException e){
            e.getStackTrace();
        }
    }


    public void saveHouse(String filePath){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))){
            for(House house : ownedHouse){
                int houseID = house.getHouseID();
                if(house.hasPet()){
                    int petID = house.getPet().getPetID();

                    ArrayList<Furniture> furnitures = house.getFurnitureList();
                    int fur1ID = furnitures.size() > 0 ? furnitures.get(0).getItemID() : -999;
                    int fur2ID = furnitures.size() > 0 ? furnitures.get(1).getItemID() : -999;
                    int fur3ID = furnitures.size() > 0 ? furnitures.get(2).getItemID() : -999;

                    String line = houseID + "|" + petID + "|" + fur1ID + "|" + fur2ID + "|" + fur3ID;
                    bw.write(line);
                    bw.newLine(); 
                }
                else{
                    int petID = NULL;

                    ArrayList<Furniture> furnitures = house.getFurnitureList();
                    int fur1ID = furnitures.size() > 0 ? furnitures.get(0).getItemID() : -999;
                    int fur2ID = furnitures.size() > 1 ? furnitures.get(1).getItemID() : -999;
                    int fur3ID = furnitures.size() > 2 ? furnitures.get(2).getItemID() : -999;

                    String line = houseID + "|" + petID + "|" + fur1ID + "|" + fur2ID + "|" + fur3ID;
                    bw.write(line);
                    bw.newLine(); 
                }

            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public int generateHouseID(){
        return ownedHouse.size()+1;
    }

    
    public void addHouse(){
        int newHouseID = generateHouseID();
        House newHouse = new House();
        newHouse.setHouseID(newHouseID);

        ownedHouse.add(newHouse);
        saveHouse(OWNED_HOUSE_PATH);
    }

    public void assignPetToHouse(int houseID, int petID) throws InterruptedException{
        petManager.loadPetFromFile("pet-simulator\\Pet\\ownedPets.txt");
        House target = findHouseByID(houseID);
        if(target != null){
            Pet pet = petManager.findPetByID(petID);
            if(pet != null){
                target.setPet(pet);
                saveHouse(OWNED_HOUSE_PATH);
            }
            else{
                typeWriter.write("pet not found!", 50, 150);
            }

        }else{
            typeWriter.write("House not found!", 50, 150);
        }
        
    }
    
    public boolean canAddFur(House house){
        return house.getFurnitureList().size() < 3;
    }

    public void assignFurToHouse(int houseID, int furID) throws InterruptedException{
        House target = findHouseByID(houseID);
        if(target!=null){
            if(!canAddFur(target)){
                typeWriter.write("This house already have 3 furniture", 50, 150);
                return;
            }  
            
            FurnitureList furList = new FurnitureList();
            Furniture furniture = furList.findFurByID(furID);
            if(furniture!=null){
                target.addFurniture(furniture);
                saveHouse(OWNED_HOUSE_PATH);
            }
            else{
                typeWriter.write("furniture not found", 50, 150);
            }
        }
        else{
            typeWriter.write("house not found", 50, 150);
        }
    }


}
