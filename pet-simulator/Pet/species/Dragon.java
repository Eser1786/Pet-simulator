package Pet.species;

import Pet.Habitat.*;
import Player.Player;
import Utils.textColor;
import Utils.typeWriter;
import Pet.Pet;


public class Dragon extends Pet implements aerial {
    public Dragon(){
        super();
    }

    public Dragon(int petID, String species, String name, int health, int level, int hunger, int mentalHealth, String sex, String item){
        super( petID, species, name, health, level, hunger, mentalHealth, sex, item);
    }
    @Override
    public void sound(){
        if(this.getMentalHealth() < 25){
            try{
                typeWriter.write("... ... ...?",50,150);
                typeWriter.write("("+textColor.PURPLE + this.getName() + textColor.RESET + " doesn't trust you yet.... you should take " + textColor.PURPLE + this.getName() + textColor.RESET +" on a walk!)",50,150);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        else if(this.getMentalHealth() < 50){
            try{
                typeWriter.write("Roarr? ...",50,150);
                typeWriter.write("(" + textColor.PURPLE + this.getName() + textColor.RESET + " is scared being around you... you should take " + textColor.PURPLE + this.getName() + textColor.RESET + " on a walk!)",50,150);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        else if(this.getMentalHealth() < 75){
            try{
                typeWriter.write("Roarr... Roarr...",50,150);
                typeWriter.write("(Even though you gained its trust, its still being distance...)",50,150);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        else{
            try{
                typeWriter.write("Roarr! Roarr! Roarr!...",50,150);
                typeWriter.write("(" + textColor.PURPLE + this.getName() + textColor.RESET + " has completely trust you)",50,150);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        System.out.println();
    }

    

    @Override
    public void feeling(){
        System.out.println();
        if(this.getMentalHealth() < 25){
            try {
                typeWriter.write(textColor.PURPLE + this.getName() + textColor.RESET + " doesn't feel good...", 50, 150);
                typeWriter.write("maybe you should spend some time with " + textColor.PURPLE + this.getName() + textColor.RESET +"...?", 50, 150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else if(this.getMentalHealth() <= 50){
            try {
                typeWriter.write(textColor.PURPLE + this.getName() + textColor.RESET + " is sitting alone...", 50, 150);
                typeWriter.write("maybe you should take " + textColor.PURPLE + this.getName() + textColor.RESET +" on a walk...?", 50, 150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else if(this.getMentalHealth() <= 75){
            try {
                typeWriter.write(textColor.PURPLE + this.getName() + textColor.RESET + " is happy.", 50, 150);
                typeWriter.write("maybe you should take " + textColor.PURPLE + this.getName() + textColor.RESET +" on a walk...?", 50, 150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        else{
            try {
                typeWriter.write(textColor.PURPLE + this.getName() + textColor.RESET + " is bowing its head down for you to pet it. ", 50, 150);
                typeWriter.write("maybe you should take " + textColor.PURPLE + this.getName() + textColor.RESET +" on a walk...?", 50, 150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        System.out.println();
    }

    public void flyYouAround(Player player) throws InterruptedException{
        typeWriter.write(textColor.PURPLE + this.getName() + textColor.RESET + " let you ride on its back.", 50);
        typeWriter.write(textColor.PURPLE + this.getName() + textColor.RESET + " proceed to do a front flip with you on its back... " + textColor.PURPLE + this.getName() + textColor.RESET +" looks proud!", 50, 150);
        typeWriter.write("...", 450, 150);
        typeWriter.write( textColor.PURPLE + this.getName() + textColor.RESET + " and you have a great time!", 50, 150);
        this.gainedLevel(2);
        this.gainedMentalHealth(10);
        this.addHunger(20);
    }
    
    @Override
    public void interact(Player player) throws InterruptedException {
        flyYouAround(player);
    }
}
