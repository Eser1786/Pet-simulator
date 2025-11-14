package Player;


import java.io.*;
import java.util.*;

import Utils.clearScreen;

// import Player.Inventory.*;

import Utils.textColor;
import Utils.typeWriter;

public class Player {
    private String name;
    private int coins;
    
    public String getName(){return name;}
    public int getCoin(){return coins;}

    public void setName(String name){this.name = name;}
    public void setCoin(int coins){this.coins = coins;}

    public Player(){
        setCoin(10000);
    }

    public Player(String name, int coins){
        setName(name);
        setCoin(coins);
    }

    public void seeCoin() throws InterruptedException{
        typeWriter.write("Coins left: ", 50);
        textColor.yellowText(getCoin() + " coins");    
        System.out.println();
    }


    

    public void playerMenu() throws InterruptedException{
        Scanner scan = new Scanner(System.in);

        typeWriter.write("What is your name?", 50, 150);
        typeWriter.write("-> ", 50);
        setName(scan.nextLine());
        setCoin(100);
        
        System.out.println();
        textColor.yellowText(getName());
        typeWriter.write("Let's start your journey!", 50, 150);
        seeCoin();

        // Inventory inventory = new Inventory();
        // inventory.openInventory();

    }


    public void profile(Player player) throws InterruptedException{
        Scanner scan = new Scanner(System.in);
        typeWriter.write("==== MYSELF ====", 50, 150);
        System.out.println();
        typeWriter.write("Name: ", 50);
        textColor.yellowText(player.getName());
        typeWriter.write("Coins: ", 50);
        textColor.yellowText(player.getCoin() + " Coins");
        typeWriter.write("'despite everything, it's still you...'", 50, 150);

        System.out.println();
        typeWriter.write("What do you want to do?", 50, 150);
        typeWriter.write("1. Change my name", 50, 150);
        typeWriter.write("2. Exit", 50, 150);
        System.out.println();
        typeWriter.write("What will be your choice?", 50, 150);
        typeWriter.write("-> ", 50);
        int choice = 0;
        choice = scan.nextInt();
        scan.nextLine();
        
        if(choice == 2){
            typeWriter.write("Exiting...", 50, 150);
            Thread.sleep(250);
            clearScreen.clear();
            return;
        }
        else if(choice == 1){
            typeWriter.write("Please input your name below...", 50, 150);
            typeWriter.write("->", 50);
            String name = scan.nextLine();
            player.setName(name);
            typeWriter.write(name +"! What a nice name!", 50, 150);
            Thread.sleep(250);
            clearScreen.clear();
            return;
        }
        
    }


    public Player loadPlayer(String filePath){
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String line;
            if((line = br.readLine()) != null){
                String[] parts = line.split("\\|");
                if(parts.length == 2){
                    String name = parts[0].trim();
                    int coin = Integer.parseInt(parts[1].trim());
                    Player player = new Player(name, coin);
                    return player;
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return null;
    }


    public void savePlayer(Player player,String filePath){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))){
            String line = player.getName() + "|" + player.getCoin();
            bw.write(line);
        }catch (IOException e){
            e.printStackTrace();
        }
    }

}
