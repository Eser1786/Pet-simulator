package House;

import java.io.*;
import java.util.*;


public class HouseList {
    private static final String OWNED_HOUSE_PATH = "pet-simulator\\House\\ownedHouse.txt";
    ArrayList<House> ownedHouse = new ArrayList<>();

    public void loadHouse(){
        try( BufferedReader br = new BufferedReader(new FileReader(OWNED_HOUSE_PATH))){
            String line;

            while(( line = br.readLine())!= null ){
                String[] parts = line.split("\\|");
                
                if(parts.length == 6){
                    int houseID = Integer.parseInt(parts[0].trim());
                    int petID = Integer.parseInt(parts[1].trim());
                    int fur1ID = Integer.parseInt(parts[2].trim());
                    int fur2ID = Integer.parseInt(parts[3].trim());
                    int fur3ID = Integer.parseInt(parts[4].trim());

                    
                }






            }






        } catch (IOException e){
            e.getStackTrace();
        }
    }




















    public void addHouse(House house){

    }









}
