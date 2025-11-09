package Item.Accessory;
import java.util.*;
import java.io.*;

public class AccessoryList {
    
    
    //có thể có 1 vật phẩm nhiều lần và sử dụng cho nhiều thứ cưng khác nhau dựa trên id
    private int generateID(String name){   // AccessoryID lấy từ file ID của vật phẩm      // là id lưu trữ vào trong file để sử dụng trong túi đồ của người chơi
        switch(name){
            case "hat": return 100;
            case "cape": return 200;
            case "collar": return 300;
            case "crown": return 400;
            case "backpack":  return 500;
            default: return 1000;
            }
    }
        


        //  lưu thông tin của accessory vào file 
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



    //  khởi tạo danh sách chứa các biến accessory
    ArrayList<Accessory> ownedAccessory = new ArrayList<>();


    
    public void addAccessory(String filePath,int accessoryID, int quantity){      // đọc file từ Accessory.txt rồi lưu vào ownedAccessory.txt
        loadAccessory("pet-simulator\\Item\\Accessory\\ownedAccessory.txt");
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
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
                            saveAccessory("pet-simulator\\Item\\Accessory\\ownedAccessory.txt");
                        }
                    }   

                }
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    

    
}
