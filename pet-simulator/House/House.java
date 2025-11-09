package House;

import java.util.*;
import java.io.*;

import Item.Furniture.*;
import Utils.typeWriter;


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

    public void getTotalComfort() throws InterruptedException{
        int sumComfort = 0;
        for(int i = 0; i < furnitureList.size(); i++){
            sumComfort += furnitureList.get(i).getComfort();
        }
        typeWriter.write("The comfort of this house is " + sumComfort, 500);
    }






    
}
