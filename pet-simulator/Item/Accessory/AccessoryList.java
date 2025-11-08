package Item.Accessory;
import java.util.*;
import java.io.*;

public class AccessoryList {
    
    
    //có thể có 1 vật phẩm nhiều lần và sử dụng cho nhiều thứ cưng khác nhau dựa trên id
    private int generateAccessoryID(String name){   // AccessoryID lấy từ file ID của vật phẩm
        int IDinFile;       // là id lưu trữ vào trong file để sử dụng trong túi đồ của người chơi
        switch(name){
            case "hat":
                IDinFile = 1;
                break;
            case "cape":
                IDinFile = 2;
                break;
            case "collar": 
                IDinFile = 3;
                break;
            case "crown": 
                IDinFile = 4;
                break;
            case "backpack": 
                IDinFile = 5;
                break;
            default:
                IDinFile = 10;
                break;
            }
            int count = 0;
            for( Accessory accessory : ownedAccessory){
                if(accessory.getItemName().equalsIgnoreCase(name)){
                    count++;
                }
            }
            return 100 * IDinFile + (count + 1);
        }
        
        // khởi tạo điểm Style random bất kì
        private int randomStylePoint(){
            Random rd = new Random();
            return rd.nextInt(100) + 1;
        }
        
        //  lưu thông tin của accessory vào file 
        public void saveAccessory(Accessory accessory, String filePath){
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(filePath, true))){
                String line = accessory.getItemID() + "|" + accessory.getItemName() + "|" + accessory.getStylePoint();
                bw.write(line);
                bw.newLine();
            }catch (IOException e){
                e.getMessage();
            }
        }

        public void loadAccessory(String filePath){
            try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
                String line;

                while( (line = br.readLine()) != null){
                    String[] parts = line.split("\\|");

                    if(parts.length == 3){
                        int accessoryID = Integer.parseInt(parts[0]);
                        String name = parts[1];
                        int stylePoint = Integer.parseInt(parts[2]);

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
                                accessory.setStylePoint(stylePoint);
                                break;
                        }
                        if(accessory != null){
                            ownedAccessory.add(accessory);
                        }
                    }


                }
            }catch (IOException e){
                e.getMessage();
            }
        }
    
    //  khởi tạo danh sách chứa các biến accessory
    ArrayList<Accessory> ownedAccessory = new ArrayList<>();
    
    public void addAccessory(String filePath,int accessoryID){      // đọc file từ Accessory.txt rồi lưu vào ownedAccessory.txt
        loadAccessory("pet-simulator\\Item\\Accessory\\ownedAccessory.txt");
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String line;

            while( (line = br.readLine()) != null){
                String[] parts = line.split("\\|");

                if(parts.length == 2){
                    int id = Integer.parseInt(parts[0].trim());
                    String name = parts[1].trim();
                    
                    if(accessoryID == id){
                        Accessory accessory = new Accessory();

                        accessory.setItemID(generateAccessoryID(name));
                        accessory.setItemName(name);
                        accessory.setStylePoint(randomStylePoint());

                        ownedAccessory.add(accessory);
                        saveAccessory(accessory,"pet-simulator\\Item\\Accessory\\ownedAccessory.txt");
                        
                    }

                }
            }
        } catch(IOException e){
            e.getMessage();
        }

    }
    
    
    
    
    
    





}
