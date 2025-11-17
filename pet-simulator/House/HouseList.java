package House;

import Item.Furniture.*;
import Pet.Pet;
import Pet.PetManager;
import Utils.textColor;
import Utils.typeWriter;
import java.io.*;
import java.util.*;

public class HouseList {
    private static final String OWNED_HOUSE_PATH = "pet-simulator\\House\\ownedHouse.txt";
    private static final int NULL = -999;

    ArrayList<House> ownedHouse = new ArrayList<>();

    PetManager petManager = new PetManager();

    public HouseList(){}

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


    public void loadHouse() throws InterruptedException {
    ownedHouse.clear();
    petManager.loadPetFromFile("pet-simulator\\Pet\\ownedPets.txt");

    try (BufferedReader br = new BufferedReader(new FileReader(OWNED_HOUSE_PATH))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("\\|");
            if (parts.length != 5) continue;

            int houseID = Integer.parseInt(parts[0].trim());
            int petID   = Integer.parseInt(parts[1].trim());
            int fur1ID  = Integer.parseInt(parts[2].trim());
            int fur2ID  = Integer.parseInt(parts[3].trim());
            int fur3ID  = Integer.parseInt(parts[4].trim());

            House house = new House();
            house.setHouseID(houseID);

            // Gán pet
            if (petID != NULL) {
                Pet pet = petManager.findPetByID(petID);
                if (pet != null) {
                    house.setPetSilent(pet);
                }
            }

            // GÁN FURNITURE: Tìm thông tin từ Furniture.txt (mẫu) rồi tạo mới có comfort
            int[] furIDs = {fur1ID, fur2ID, fur3ID};
            for (int furID : furIDs) {
                if (furID != NULL) {
                    Furniture furniture = createFurnitureFromTemplate(furID);
                    if (furniture != null) {
                        house.addFurnitureSilent(furniture); // có tên + comfort luôn!
                    }
                }
            }

            ownedHouse.add(house);
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
}

// Tạo furniture từ file mẫu Furniture.txt (chỉ đọc file 1 lần mỗi lần loadHouse)
private Furniture createFurnitureFromTemplate(int targetID) {
    try (BufferedReader br = new BufferedReader(new FileReader("pet-simulator\\Item\\Furniture\\Furniture.txt"))) {
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split("\\|");
            if (parts.length == 3) {
                int id = Integer.parseInt(parts[0].trim());
                if (id == targetID) {
                    String name = parts[1].trim();
                    int comfort = Integer.parseInt(parts[2].trim());
                    
                    Furniture f = new Furniture();
                    f.setItemID(targetID);
                    f.setItemName(name);
                    f.setComfort(comfort);
                    f.setQuantity(1);
                    return f;
                }
            }
        }
    } catch (Exception e) {
        e.printStackTrace();
    }
    return null;
}


    public void saveHouse(String filePath){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))){
            for(House house : ownedHouse){
                int houseID = house.getHouseID();
                if(house.hasPet()){
                    int petID = house.getPet().getPetID();

                    ArrayList<Furniture> furnitures = house.getFurnitureList();
                    int fur1ID = furnitures.size() > 0 ? furnitures.get(0).getItemID() : -999;
                    int fur2ID = furnitures.size() > 1 ? furnitures.get(1).getItemID() : -999;
                    int fur3ID = furnitures.size() > 2 ? furnitures.get(2).getItemID() : -999;

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

    public int generateHouseID() throws InterruptedException{
        ownedHouse.clear();
        loadHouse();
        return ownedHouse.size()+1;
    }

    public int getHouseCount(){
        return ownedHouse.size();
    }

    
    public void addHouse() throws InterruptedException{
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
            furList.loadFur("pet-simulator\\Item\\Furniture\\ownedFurniture.txt");
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

    public void printHouseForShop() throws InterruptedException{

        typeWriter.write("==== HOUSE ====", 50, 150);
        System.out.println();
        
        typeWriter.write("1. ", 30);
        textColor.orangeText(" House");
        typeWriter.write(" | cost: ", 10);
        textColor.yellowText("50 coins");
        typeWriter.write("- Buying a house will add a slot to contain your pet", 50, 150);
        System.out.println();
        
    }

    public void printHouse() throws InterruptedException{
        if(ownedHouse.isEmpty()){
            loadHouse();
        }
        int index = 1;
        for(House house : ownedHouse){
            typeWriter.write("House " + index, 50, 150);
            
            if(house.hasPet()){
                typeWriter.write(" | Pet: " + house.getPet().getName(), 50, 150);
            }
            else{
                typeWriter.write(" | Pet: none", 50, 150);      
            }
          
            house.seeFurniture(); 
            System.out.println();
            index++;
        }
    }
    
    public int totalHouse() throws InterruptedException{
        loadHouse();
        int count = 0;
        for(House house : ownedHouse){
            count++;
        }
        return count;
    }
}
