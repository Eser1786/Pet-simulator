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
                            food = new Food();
                            food.setItemID(FoodID);
                            food.setItemName(name);
                            food.setQuantity(quantity);
                            food.setSat(quantity);
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

}
