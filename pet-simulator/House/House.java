package House;

import java.util.*;


import Item.Furniture.*;
import Utils.textColor;
import Utils.typeWriter;
import Pet.Pet;

public class House {

    private int id;
    private String name;
    
    public int getHouseID(){return id;}
    public String getHouseName(){return name;}
    
    public void setHouseID(int id){this.id = id;}
    public void setHouseName(String name){this.name = name;}
    
    private ArrayList<Furniture> furnitureList = new ArrayList<>();

    public boolean addFurniture(Furniture furniture) throws InterruptedException{
        if(furniture == null){
            typeWriter.write("Furniture cannot be null!", 50, 150);
            return false;
        }
        if(furnitureList.size() >= 3){
            typeWriter.write("This house already has enough furnitures!", 50, 150);
            return false;
        }
        furnitureList.add(furniture);
        typeWriter.write("This furniture has been added!", 50, 150);
        return true;
    }

    public boolean addFurnitureSilent(Furniture furniture){
        if(furniture == null){
            return false;
        }
        if(furnitureList.size() >= 3){
            return false;
        }
        furnitureList.add(furniture);
        return true;
    }

    public void seeFurniture() throws InterruptedException{
        typeWriter.write("=== FURNITURE OF THIS HOUSE ===", 50, 150);
        System.out.println();
        
        if(furnitureList.isEmpty()){
            typeWriter.write("This house has no furniture yet.", 50, 150);
            System.out.println();
            return;
        }

        for(Furniture furniture : furnitureList){
            if(furniture.getComfort() == 0){
                
                FurnitureList furList = new FurnitureList();
                int comfort = furList.findComfortByName(furniture.getItemName());
                furniture.setComfort(comfort);
            }
            
            typeWriter.write("- ", 50);
            textColor.yellowText(furniture.getItemName());
            typeWriter.write(" | Comfort: ", 50);
            textColor.blueText(String.valueOf(furniture.getComfort()));
            System.out.println();
        }
        typeWriter.write("Total comfort of this house: ", 50);
        textColor.orangeText(String.valueOf(sumOfComfort()));
        System.out.println();
    }

    public int sumOfComfort(){
        int sum = 0;
        for(Furniture furniture : furnitureList){
            sum += furniture.getComfort();
        }
        return sum;
    }

    public ArrayList<Furniture> getFurnitureList(){
        return furnitureList;
    }

    public Furniture removeFurnitureByName(String name) throws InterruptedException{
        for(Furniture furniture : furnitureList){
            if(furniture.getItemName() != null && furniture.getItemName().equalsIgnoreCase(name)){
                furnitureList.remove(furniture);
                typeWriter.write("This furniture has been removed!", 50, 150);
                return furniture;
            }
        }
        return null;
    }


    //     int sumComfort = 0;
    //     for(int i = 0; i < furnitureList.size(); i++){
    //         sumComfort += furnitureList.get(i).getComfort();
    //     }
    //     typeWriter.write("The comfort of this house is " + sumComfort, 500);
    // }

    private Pet pet;

    public void setPet(Pet pet) throws InterruptedException{
        if(pet == null){
            typeWriter.write("Pet cannot be null!", 50, 150);
            return;
        }
        if(this.pet != null){
            typeWriter.write("This house already has a pet!", 50, 150);
            return;
        }
        this.pet = pet;
        typeWriter.write(pet.getName() + " is now the owner of this house!", 50, 150);
        pet.setHouse(this);
    }

    public void setPetSilent(Pet pet) throws InterruptedException{
        if(pet == null){
            return;
        }
        if(this.pet != null){
            return;
        }
        this.pet = pet;
        pet.setHouse(this);
    }

    public boolean hasPet(){
        return this.pet != null;
    }

    public Pet getPet(){
        return this.pet;
    }

    public void removePet() throws InterruptedException{

        if(this.pet!=null){
            typeWriter.write(this.pet.getName() + " is no longer the owner of this house!", 30, 150);
        }
        this.pet.removeHouse(this);
        this.pet = null;
        
    }
    
}
