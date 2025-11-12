package Pet.species;


import Pet.Habitat.*;
import Player.Player;
import Utils.textColor;
import Utils.typeWriter;

import java.util.Random;

import Pet.Pet;

public class Shark extends Pet implements aquatic {
    public Shark(){
        super();
    }

    public Shark(int petID, String species, String name, int health, int level, int hunger, int mentalHealth, String sex, String item){
        super( petID, species, name, health, level, hunger, mentalHealth, sex, item);
    }
    @Override
    public void sound(){
        if(this.getMentalHealth() < 25){
            try{
                typeWriter.write("... ... ...?",50,150);
                typeWriter.write("("+this.getName() + " doesn't trust you yet.... you should take " + this.getName() +" on a walk!)",50,150);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        else if(this.getMentalHealth() < 50){
            try{
                typeWriter.write("shwooshh? ...",50,150);
                typeWriter.write("(" + this.getName() + " is scared being around you... you should take " + this.getName() + " on a walk!)",50,150);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        else if(this.getMentalHealth() < 75){
            try{
                typeWriter.write("shwooshh... shwooshh...",50,150);
                typeWriter.write("(Even though you gained its trust, its still being distance...)",50,150);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        else{
            try{
                typeWriter.write("shwooshh! shwooshh! shwooshh!...",50,150);
                typeWriter.write("(" + this.getName() + " has completely trust you)",50,150);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println();
    }

    

    @Override
    public void feeling(){

        if(this.getMentalHealth() < 25){
            try {
                typeWriter.write(this.getName() + " doesn't feel good...", 50, 150);
                typeWriter.write("maybe you should spend some time with " + this.getName() +"...?", 50, 150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else if(this.getMentalHealth() <= 50){
            try {
                typeWriter.write(this.getName() + " is sitting alone...", 50, 150);
                typeWriter.write("maybe you should take " + this.getName() +" on a walk...?", 50, 150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else if(this.getMentalHealth() <= 75){
            try {
                typeWriter.write(this.getName() + " is happy.", 50, 150);
                typeWriter.write("maybe you should take " + this.getName() +" on a walk...?", 50, 150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else{
            try {
                typeWriter.write(this.getName() + " is swimming around you, and touching you lightly with its head. ", 50, 150);
                typeWriter.write("maybe you should take " + this.getName() +" on a walk...?", 50, 150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        System.out.println();
    }

    public void takeYouToSwimming(Player player) throws InterruptedException{
        Random random = new Random();
        int unexpectedCoins = random.nextInt(20)+1;
        typeWriter.write("you go for a swim with " + this.getName(), 50);
        typeWriter.write(this.getName() + " let you ride on its back, " + this.getName() +" swim really fast...", 50, 150);
        typeWriter.write(".... "+this.getName()+" found something on the ocean... It's ", 50, 150);
        textColor.yellowText(unexpectedCoins + " coins");

        player.setCoin(player.getCoin() + unexpectedCoins);
        typeWriter.write("Coins left: ", 50);
        textColor.yellowText(player.getCoin() + " coins");
        
        typeWriter.write( this.getName() + " and you have a great time!", 50, 150);
        this.gainedLevel(2);
        this.gainedMentalHealth(10);
        this.setHunger(this.getHunger()-20);
    }
}
