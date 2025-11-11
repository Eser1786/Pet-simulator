package Player;

import java.io.*;
import java.util.*;

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
        typeWriter.write("Coins left", 50, 150);
        textColor.yellowText(getCoin());    
        System.out.println();
    }


    

    public void playerMenu() throws InterruptedException{
        Scanner scan = new Scanner(System.in);

        typeWriter.write("What is your name?", 50, 150);
        typeWriter.write("-> ", 50, 150);
        setName(scan.nextLine());
        setCoin(100);
        
        System.out.println();
        textColor.yellowText(getName());
        typeWriter.write(" let's start your journey!", 50, 150);
        seeCoin();

        



    }
















}
