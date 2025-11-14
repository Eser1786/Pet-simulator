package Item.Food;

import java.util.*;


import Utils.textColor;
import Utils.typeWriter;

import java.io.*;

public class FoodList {
    public static final String FOOD_PATH = "pet-simulator\\Item\\Food\\Food.txt";
    public static final String OWNED_FOOD_PATH = "pet-simulator\\Item\\Food\\ownedFood.txt";

    
    private int generateID(String name){   
        switch(name){
            case "bone": return 100;
            case "beef": return 200;
            case "pate":  return 300;
            case "seeds":  return 400;
            case "fruit bowl": return 500;
            case "rice ball": return 600;
            case "honey treat": return 700;
            case "egg roll": return 800;
            case "shrimp snack": return 900;
            default: return 1000;
        }
    }
        
        
        
    public void saveFood(String filePath){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))){
            for(Food food : ownedFood){
                String line = food.getItemID() + "|" + food.getItemName() + "|" + food.getQuantity();
                bw.write(line);
                bw.newLine();
            }
            }catch (IOException e){
            e.getMessage();
        }
    }



    public void loadFood(String filePath){
        ownedFood.clear();
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String line;

            while( (line = br.readLine()) != null){
                String[] parts = line.split("\\|");

                if(parts.length == 3){
                    int FoodID = Integer.parseInt(parts[0].trim());
                    String name = parts[1];
                    int quantity = Integer.parseInt(parts[2].trim());
                    

                    Food food = null;
                    
                    switch(name.toLowerCase()){
                        case "bone":
                        case "beef":
                        case "pate": 
                        case "seeds": 
                        case "fruit bowl":
                        case "rice ball":
                        case "honey treat":
                        case "egg roll":
                        case "shrimp snack":
                            food = new Food();
                            food.setItemID(FoodID);
                            food.setItemName(name);
                            food.setQuantity(quantity);
                            break;
                    }
                    if(food != null){
                        ownedFood.add(food);
                    }
                }


            }
        }catch (IOException e){
            e.getMessage();
        }
    }



    public Food findFoodByID(int id){
        for(Food a : ownedFood){
            if(a.getItemID() == id){
                return a;
            }
        }
        return null;
    }

    public String findFoodNameByID(int id){
        loadFood(OWNED_FOOD_PATH);
        for(Food a : ownedFood){
            if(a.getItemID() == id*100){
                return a.getItemName();
            }
        }
        return null;
    }

    public Food findFoodByName(String name){
        loadFood(OWNED_FOOD_PATH);
        for(Food food : ownedFood){
            // pet.getName() != null && pet.getName().equalsIgnoreCase(name)
            if(food.getItemName() != null && food.getItemName().equalsIgnoreCase(name) && food.getQuantity() > 0){
                return food;
            }
        }
        return null;
    }

    

    
    ArrayList<Food> ownedFood = new ArrayList<>();


    public void addFood(int FoodID, int quantity){      
        loadFood(OWNED_FOOD_PATH);
        try(BufferedReader br = new BufferedReader(new FileReader(FOOD_PATH))){
            String line;

            while( (line = br.readLine()) != null){
                String[] parts = line.split("\\|");

                if(parts.length == 3){
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    int sat = Integer.parseInt(parts[2].trim());

                    
                    if(FoodID == id){
                        int fixedID = generateID(name);
                        Food existed = findFoodByID(fixedID);
                        
                        boolean updated = false;

                        if(existed != null){
                            existed.setQuantity(existed.getQuantity() + quantity);
                            updated = true;
                        }
                        else{
                            Food Food = new Food();
                            
                            Food.setItemID(generateID(name));
                            Food.setItemName(name);
                            Food.setSat(sat);
                            Food.setQuantity(quantity);
                            
                            ownedFood.add(Food);
                            updated = true;
                        }

                        if(updated){
                            saveFood(OWNED_FOOD_PATH);
                        }
                    }
                }
            }
        } catch(IOException e){
            e.getMessage();
        }
    }


    // public void itemDetails() throws InterruptedException{
    //     typeWriter.write("[" + getItemName() + "]" , 50, 300);
    //     typeWriter.write(" - Quantity: " + getQuantity(), 50, 300);
    // }                            





    // @Override
    // public void itemDetails() throws InterruptedException{
    //     super.itemDetails();
    //     typeWriter.write(" - Saturation Points: ", 50);
    //     textColor.blueText(getSat());
    // }

    public int findSat(int id){
        try(BufferedReader br = new BufferedReader(new FileReader(FOOD_PATH))){
            String line;

            while( (line = br.readLine()) != null){
                String[] parts = line.split("\\|");

                if(parts.length == 3){
                    int foodID = Integer.parseInt(parts[0].trim());
                    int Sat = Integer.parseInt(parts[2].trim());

                    if(id == foodID){
                        return Sat;
                    }
                }
                else{
                    return 0;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }



    public void printListFood() throws InterruptedException{
        System.out.println();
        typeWriter.write("======= FOOD SECTION =======", 50, 300);
        loadFood(OWNED_FOOD_PATH);

        int j = 0;

        for(Food a : ownedFood){
            a.setSat(findSat(a.getItemID()/100));

            typeWriter.write("Name: ", 50);
            textColor.yellowText(a.getItemName());
            System.out.print("    ");

            typeWriter.write(" | Quantity: ", 50);
            typeWriter.write(""+a.getQuantity(),50);

            System.out.print("    ");

            typeWriter.write(" | Saturation Points: ", 50);
            textColor.orangeText(a.getSat());

            j++;
            if(j % 4 == 0){
                    System.out.println();
                    j = 0;
            }
        }

        if(j%4 != 0){
            System.out.println();
        }

    }

    public void removeFood(int foodID, int quantity) throws InterruptedException{
        loadFood(OWNED_FOOD_PATH);
        Food target = findFoodByID(foodID * 100);
        if(target == null){
            typeWriter.write("This item doesn't exist...?", 50,300);
            return;
        }

        if(target.getQuantity() < quantity ){
            typeWriter.write("You don't have enough item to remove...", 50,300);
            return; 
        }   

        target.setQuantity(target.getQuantity() - quantity);

        if(target.getQuantity() == 0){
            ownedFood.remove(target);
            saveFood(OWNED_FOOD_PATH);
        }else{
            saveFood(OWNED_FOOD_PATH);
        }
    }

    public void printFoodForShop() throws InterruptedException{
        ownedFood.clear();
        try(BufferedReader br = new BufferedReader(new FileReader(FOOD_PATH))){
            String line;

     
            while( (line = br.readLine()) != null){
                String[] parts = line.split("\\|");
                Food food = new Food();
                if(parts.length == 3){
                    int foodID = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    int Sat = Integer.parseInt(parts[2].trim());
                    food.setItemID(foodID);
                    food.setItemName(name);
                    food.setSat(Sat);
                        
                    ownedFood.add(food);
                }
            }
        } catch(IOException e){
            e.getMessage();
        }

        typeWriter.write("==== FOOD ====", 10, 150);
        int index = 0;
        
        for(Food food : ownedFood){
            typeWriter.write(index + 1 + ". ", 10);
            textColor.blueText( food.getItemName());
            typeWriter.write(" | Saturation points: ",  10);
            textColor.orangeText(food.getSat()); 
            typeWriter.write("Cost: ", 10);
            textColor.yellowText("20 coins");
            System.out.println();
            index++;
        }
    }



}
