package Item.Accessory;
import java.util.*;


import Utils.textColor;
import Utils.typeWriter;

import java.io.*;

public class AccessoryList {
    public static final String ACCESSORY_PATH = "pet-simulator\\Item\\Accessory\\Accessory.txt";
    public static final String OWNED_ACCESSORY_PATH = "pet-simulator\\Item\\Accessory\\ownedAccessory.txt";

    
    
    private int generateID(String name){   
        switch(name){
            case "hat": return 100;
            case "cape": return 200;
            case "collar": return 300;
            case "crown": return 400;
            case "backpack":  return 500;
            default: return 1000;
            }
    }
        


         
    public void saveAccessory(String filePath){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))){
            for(Accessory accessory: ownedAccessory){
                String line = accessory.getItemID() + "|" + accessory.getItemName() + "|" + accessory.getQuantity();
                bw.write(line);
                bw.newLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }



    public void loadAccessory(String filePath){
        ownedAccessory.clear();
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String line;

            while( (line = br.readLine()) != null){
                String[] parts = line.split("\\|");

                if(parts.length == 3){
                    int accessoryID = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    int quantity = Integer.parseInt(parts[2].trim());
                    
                    // int stylePoint = Integer.parseInt(parts[2]);

                    Accessory accessory = null;
                    
                    switch(name.toLowerCase()){
                        case "hat":
                        case "cape":
                        case "collar": 
                        case "crown": 
                        case "backpack": 
                            accessory = new Accessory();
                            accessory.setItemID(accessoryID);
                            accessory.setItemName(name);
                            accessory.setQuantity(quantity);
                            break;
                    }
                    if(accessory != null){
                        ownedAccessory.add(accessory);
                    }
                }


            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }



    public Accessory findAccessoryByID(int id){
        for (Accessory a : ownedAccessory){
            if(a.getItemID() == id){
                return a;
            }
        }
        return null;
    }

    public String findAccessoryNameByID(int id){
        for(Accessory a : ownedAccessory){
            if(a.getItemID() == id * 100){
                return a.getItemName();
            }
        }
        return null;
    }


    
    ArrayList<Accessory> ownedAccessory = new ArrayList<>();


    
    public void addAccessory(int accessoryID, int quantity){      // đọc file từ Accessory.txt rồi lưu vào ownedAccessory.txt
        loadAccessory(OWNED_ACCESSORY_PATH);
        try(BufferedReader br = new BufferedReader(new FileReader(ACCESSORY_PATH))){
            String line;

            while( (line = br.readLine()) != null){
                String[] parts = line.split("\\|");

                if(parts.length == 3){
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    int stylePoint = Integer.parseInt(parts[2].trim());
                    
                    
                    if(accessoryID == id){
                        int fixedID = generateID(name);
                        Accessory existed = findAccessoryByID(fixedID);

                        boolean updated = false;    

                        if(existed != null){
                            existed.setQuantity(existed.getQuantity() + quantity);
                            updated = true;
                        }
                        else{
                            Accessory accessory = new Accessory();
                            accessory.setItemID(fixedID);
                            accessory.setItemName(name);
                            accessory.setStylePoint(stylePoint);
                            accessory.setQuantity(quantity);

                            ownedAccessory.add(accessory);
                            updated = true;
                        }
                        if(updated){
                            saveAccessory(OWNED_ACCESSORY_PATH);
                        }
                    }   

                }
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    









    public int findStylePoint(int id){
        try(BufferedReader br = new BufferedReader(new FileReader(ACCESSORY_PATH))){
            String line;

            while( (line = br.readLine()) != null){
                String[] parts = line.split("\\|");

                if(parts.length == 3){
                    int AccessoryID = Integer.parseInt(parts[0].trim());
                    int Sat = Integer.parseInt(parts[2].trim());

                    if(id == AccessoryID){
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



    public void printListAccessory() throws InterruptedException{
        System.out.println();
        typeWriter.write("======= ACCESSORY SECTION =======", 50, 300);
        loadAccessory(OWNED_ACCESSORY_PATH);

        int j = 0;

        for(Accessory a : ownedAccessory){
            a.setStylePoint(findStylePoint(a.getItemID()/100));

            typeWriter.write("Name: ", 50);
            textColor.yellowText(a.getItemName());
            System.out.print("    ");

            typeWriter.write(" | Quantity: ", 50);
            typeWriter.write(""+a.getQuantity(),50);

            System.out.print("    ");

            typeWriter.write(" | Style Points: ", 50);
            textColor.orangeText(a.getStylePoint());

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
