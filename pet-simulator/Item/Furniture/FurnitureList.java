package Item.Furniture;

import java.io.*;
import java.util.*;


import Utils.textColor;
import Utils.typeWriter;


public class FurnitureList {
    public static final String FURNITURE_PATH = "pet-simulator\\Item\\Furniture\\Furniture.txt";
    public static final String OWNED_FURNITURE_PATH = "pet-simulator\\Item\\Furniture\\ownedFurniture.txt";

    
    private int generateID(String name){   
        switch(name.toLowerCase()){
            case "chair": return 1;
            case "table": return 2;
            case "sofa":  return 3;
            case "TV":  return 4;
            case "radio": return 5;
            case "bookshelf": return 6;
            case "lamp": return 7;
            case "bed": return 8;
            case "rug": return 9;
            default: return 10;
        }
    }
        
        
        
    public void saveFur(String filePath){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))){
            for(Furniture fur : ownedFur){
                if(fur.getQuantity() > 0){
                    String line = fur.getItemID() + "|" + fur.getItemName() + "|" + fur.getQuantity();
                    bw.write(line);
                    bw.newLine();
                }
            }
            }catch (IOException e){
            e.getMessage();
        }
    }



    public void loadFur(String filePath){
        ownedFur.clear();
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String line;

            while( (line = br.readLine()) != null){
                String[] parts = line.split("\\|");

                if(parts.length == 3){
                    int furID = Integer.parseInt(parts[0].trim());
                    String name = parts[1];
                    int quantity = Integer.parseInt(parts[2].trim());
                    

                    Furniture fur = null;
                    
                    switch(name.toLowerCase()){
                        case "chair":
                        case "table":
                        case "sofa": 
                        case "TV":
                        case "radio": 
                        case "bookshelf":
                        case "lamp":
                        case "bed": 
                        case "rug": 
                            fur = new Furniture();
                            fur.setItemID(furID);
                            fur.setItemName(name);
                            fur.setQuantity(quantity);
                            break;
                    }
                    if(fur != null){
                        ownedFur.add(fur);
                    }
                }


            }
        }catch (IOException e){
            e.getMessage();
        }
    }


    public Furniture findFurByID(int id){
        loadFur(OWNED_FURNITURE_PATH);
        for(Furniture a : ownedFur){
            if(a.getItemID() == id){
                return a;
            }
        }
        return null;
    }

    public Furniture findFurByIndex(int index){
        try(BufferedReader br = new BufferedReader(new FileReader(FURNITURE_PATH))){
            String line;
            int count = 0;
            while( (line = br.readLine())!=null){
                String[] parts = line.split("\\|");
                if(parts.length == 3){
                    count++;
                    if(count == index){
                        int furID = Integer.parseInt(parts[0].trim());
                        String furName = parts[1].trim();
                        int Comfort = Integer.parseInt(parts[2].trim());
                        Furniture fur = new Furniture();
                        fur.setItemID(generateID(furName));
                        fur.setItemName(furName);
                        fur.setComfort(Comfort);
                        return fur;
                    }
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }

    public String findFurnitureNameByID(int index){
        try(BufferedReader br = new BufferedReader(new FileReader(FURNITURE_PATH))){
            String line;
            int count = 0;
            while( (line = br.readLine())!=null){
                String[] parts = line.split("\\|");
                if(parts.length == 3){
                    count++;
                    if(count == index){
                        String furName = parts[1].trim();
                        return furName;
                    }
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }



    
    ArrayList<Furniture> ownedFur = new ArrayList<>();


    
    public void addFurniture(int furID, int quantity){      
        loadFur(OWNED_FURNITURE_PATH);
        try(BufferedReader br = new BufferedReader(new FileReader(FURNITURE_PATH))){
            String line;

            while( (line = br.readLine()) != null){
                String[] parts = line.split("\\|");

                if(parts.length == 3){
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    int comfort = Integer.parseInt(parts[2].trim());

                    
                    if(furID == id){
                        int fixedID = generateID(name);
                        Furniture existed = findFurByID(fixedID);
                        
                        boolean updated = false;

                        if(existed != null){
                            existed.setQuantity(existed.getQuantity() + quantity);
                            updated = true;
                        }
                        else{
                            Furniture fur = new Furniture();
                            
                            fur.setItemID(generateID(name));
                            fur.setItemName(name);
                            fur.setComfort(comfort);
                            fur.setQuantity(quantity);
                            
                            ownedFur.add(fur);
                            updated = true;
                        }

                        if(updated){
                            saveFur(OWNED_FURNITURE_PATH);
                        }
                    }
                }
            }
        } catch(IOException e){
            e.getMessage();
        }
    }

    public int findComfort(int id){
        try(BufferedReader br = new BufferedReader(new FileReader(FURNITURE_PATH))){
            String line;

            while( (line = br.readLine()) != null){
                String[] parts = line.split("\\|");

                if(parts.length == 3){
                    int furID = Integer.parseInt(parts[0].trim());
                    int comfort = Integer.parseInt(parts[2].trim());

                    if(id == furID){
                        return comfort;
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

    public int findComfortByName(String name){
        try(BufferedReader br = new BufferedReader(new FileReader(FURNITURE_PATH))){
            String line;

            while( (line = br.readLine()) != null){
                String[] parts = line.split("\\|");

                if(parts.length == 3){
                    String furName = parts[1].trim();
                    int comfort = Integer.parseInt(parts[2].trim());

                    if(furName.toLowerCase().equals(name.toLowerCase())){
                        return comfort;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }



    public void printListFurniture() throws InterruptedException{       //for inventory
        System.out.println();
        typeWriter.write("======= FURNITURE SECTION =======", 50, 300);
        ownedFur.clear();
        loadFur(OWNED_FURNITURE_PATH);

        int j = 0;

        for(Furniture a : ownedFur){
            if(a.getComfort() == 0){
                a.setComfort(findComfortByName(a.getItemName()));
            }

            typeWriter.write("Name: ", 50);
            textColor.yellowText(a.getItemName());

            System.out.print("    ");

            typeWriter.write(" | Quantity: ", 50);
            typeWriter.write("" + a.getQuantity(),50);

            System.out.print("    ");

            typeWriter.write(" | Comfort Points: ", 50);
            textColor.orangeText(a.getComfort());

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


    public void removeFurniture(int FurnitureID, int quantity) throws InterruptedException{
        ownedFur.clear();
        loadFur(OWNED_FURNITURE_PATH);
        Furniture target = findFurByID(FurnitureID);
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
            ownedFur.remove(target);
            saveFur(OWNED_FURNITURE_PATH);
        }else{
            saveFur(OWNED_FURNITURE_PATH);
        }
    }


    public void printFurForShop() throws InterruptedException{
        ownedFur.clear();
        try(BufferedReader br = new BufferedReader(new FileReader(FURNITURE_PATH))){
            String line;

            while( (line = br.readLine()) != null){
                String[] parts = line.split("\\|");
                
                Furniture fur = new Furniture();
                if(parts.length == 3){
                    
                    String name = parts[1].trim();
                    int comfort = Integer.parseInt(parts[2].trim());

                        fur.setItemName(name);
                        fur.setComfort(comfort);
                        
                        ownedFur.add(fur);
                    }
            }
        } catch(IOException e){
            e.getMessage();
        }

        typeWriter.write("==== FURNITURE ====", 50, 150);

        int index = 0;
        for(Furniture fur : ownedFur){
            typeWriter.write(index+1 + ". ", 10);
            textColor.blueText(fur.getItemName());
            typeWriter.write(" | Comfort points: ",  50);
            textColor.orangeText(fur.getComfort()); 
            typeWriter.write("Cost: ", 50);
            textColor.yellowText("30 coins");

            System.out.println();
            index++;
        }
    }
    public Furniture findFurnitureByName(String name){
        ownedFur.clear();
        loadFur(OWNED_FURNITURE_PATH);
        for(Furniture furniture : ownedFur){
            if(furniture.getItemName() != null && furniture.getItemName().equalsIgnoreCase(name) && furniture.getQuantity() > 0){
                return furniture;
            }
        }
        return null;
    }

    public int totalFurniture(){
        loadFur(OWNED_FURNITURE_PATH);
        int count = 0;
        for(Furniture furniture : ownedFur){
            count += furniture.getQuantity();
        }
        return count;
    }










}
    


