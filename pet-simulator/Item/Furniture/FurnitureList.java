package Item.Furniture;

import java.io.*;
import java.util.*;


import Utils.textColor;
import Utils.typeWriter;


public class FurnitureList {
    public static final String FURNITURE_PATH = "pet-simulator\\Item\\Furniture\\Furniture.txt";
    public static final String OWNED_FURNITURE_PATH = "pet-simulator\\Item\\Furniture\\ownedFurniture.txt";

    
    private int generateID(String name){   
        switch(name){
            case "chair": return 100;
            case "table": return 200;
            case "sofa":  return 300;
            case "TV":  return 400;
            case "radio": return 500;
            default: return 1000;
        }
    }
        
        
        
    public void saveFur(String filePath){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))){
            for(Furniture fur : ownedFur){
                String line = fur.getItemID() + "|" + fur.getItemName() + "|" + fur.getQuantity();
                bw.write(line);
                bw.newLine();
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



    public Furniture findfurByID(int id){
        for(Furniture a : ownedFur){
            if(a.getItemID() == id){
                return a;
            }
        }
        return null;
    }

    public String findFurnitureNameByID(int id){
        for(Furniture a : ownedFur){
            if(a.getItemID() == id * 100){
                return a.getItemName();
            }
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
                        Furniture existed = findfurByID(fixedID);
                        
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



    public void printListFurniture() throws InterruptedException{
        System.out.println();
        typeWriter.write("======= FURNITURE SECTION =======", 50, 300);
        loadFur(OWNED_FURNITURE_PATH);

        int j = 0;

        for(Furniture a : ownedFur){
            a.setComfort(findComfort(a.getItemID()/100));

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



}
