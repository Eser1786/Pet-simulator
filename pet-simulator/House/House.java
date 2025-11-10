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
        if(furnitureList.size() >= 3){
            typeWriter.write("This house already has enough furnitures!", 500,1000);
            return false;
        }
        furnitureList.add(furniture);
        return true;
    }

    public void seeFurniture() throws InterruptedException{
        
        for(Furniture furniture : furnitureList){
            textColor.yellowText(furniture.getItemName());
            typeWriter.write(" | " , 50);
            textColor.blueText(furniture.getComfort());
            System.out.println();
        }
        typeWriter.write("Total comfort of this house is " + sumOfComfort(), 50, 300);
    }

    public int sumOfComfort(){
        int sum = 0;
        for(Furniture furniture : furnitureList){
            sum += furniture.getComfort();
        }
        return sum;
    }


    // public void getTotalComfort() throws InterruptedException{
    //     int sumComfort = 0;
    //     for(int i = 0; i < furnitureList.size(); i++){
    //         sumComfort += furnitureList.get(i).getComfort();
    //     }
    //     typeWriter.write("The comfort of this house is " + sumComfort, 500);
    // }

    private Pet pet;

    public void setPet(Pet pet) throws InterruptedException{
        if(this.pet != null){
            typeWriter.write("This house already has a pet !", 50, 150);
            return;
        }
        else{
            this.pet = pet;
            typeWriter.write( pet.getName() + " is now the owner of this house!", 50, 150);
        }
    }

    public boolean hasPet(){
        if(this.pet != null){
            return true;
        }
        return false;
    }

    public Pet getPet(){
        return this.pet;
    }

    public void removePet(){
        this.pet = null;
    }
    
}
