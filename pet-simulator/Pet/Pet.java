package Pet;

import Item.Accessory.*;
import Utils.textColor;
import Utils.typeWriter;
import House.House;
import Player.Player;

public abstract class Pet{
    private int petID;       // bao gồm 3 chữ số
    private String species;
    private String name;
    private int health;
    private int level;
    private int hunger;
    private int mentalHealth; 
    private String sex;     // ĐÂY LÀ GIỚI TÍNH
    private String item;


    public int getPetID(){return petID;}
    public String getSpecies(){return species;}
    public String getName(){return name;}
    public int getHealth(){return health;}
    public int getLevel(){return level;}
    public int getHunger(){return hunger;}
    public int getMentalHealth(){return mentalHealth;}
    public String getSex(){return sex;}
    public String getItem(){return item;}



    public void setPetID(int petID){this.petID = petID;}
    public void setSpecies(String species){this.species = species;}
    public void setName(String name){this.name = name;}
    public void setHealth(int health){this.health = health;}
    public void setLevel(int level){this.level = level;}
    public void setHunger(int hunger){this.hunger = hunger;}
    public void setMentalHealth(int mentalHealth){this.mentalHealth = mentalHealth;}
    public void setSex(String sex){this.sex = sex;}
    public void setItem(String item){this.item = item;}
  


    public abstract void sound();
    public abstract void feeling();
    
    
    public abstract void interact(Player player) throws InterruptedException;
    

    public Pet(int petID, String species, String name, int health, int level, int hunger, int mentalHealth, String sex, String item){
        
        if(petID >= 100 && petID <= 9999){
            setPetID(petID);
        }
        else{
            System.out.println("Lỗi id");
        }

        setSpecies(species);
        setName(name);
        setHealth(health);
        setLevel(level);
        setHunger(hunger);
        setMentalHealth(mentalHealth);
        
        if(sex.equals("Male") || sex.equals("Female") || sex.equals("male") || sex.equals("female")){
            setSex(sex);
        }
        else{
            setSex("male");
        }

        setItem(item);
        

    }


    public Pet(){}

    // public boolean isAccessoryEquip(){

    // }
    private Accessory accessory;
    public Accessory getAccessory(){
        return accessory;
    }

    public void setAccessory(Accessory accessory){
        this.accessory = accessory;
        setItem(accessory.getItemName());
    }

    public boolean equipAccessory(Accessory accessory) throws InterruptedException{
        if(this.accessory != null){
            typeWriter.write("This pet already has an accessory...", 50,150);
            typeWriter.write("Please remove the previous accessory before adding a new one to the pet!", 50, 150);
            System.out.println();
            return false;
        }
        this.accessory = accessory;
        typeWriter.write("The pet has equipped the accessory!", 50, 150);
        typeWriter.write("+"+accessory.getStylePoint()+" style point!", 50, 150);
        setItem(accessory.getItemName());
        System.out.println();
        return true;
    }

    public void unequipAccessory() throws InterruptedException{
        if(accessory == null){
            typeWriter.write("this pet doesn't have any accessory on", 50, 150);
        }
        this.accessory = null;
        setItem(null);
        typeWriter.write("The pet is no longer equip the accessory!", 50, 150);
        System.out.println();
    }



    public void gainedMentalHealth(int mentalHealth) throws InterruptedException{
        typeWriter.write( this.name + " gained " + mentalHealth +" mental health!", 50, 150);
        this.setMentalHealth(this.getMentalHealth() + mentalHealth);

        if(this.getMentalHealth() >100){
            this.setMentalHealth(100);
        }
        else if(this.getMentalHealth() < 0){
            this.setMentalHealth(0);
        }

        typeWriter.write(this.getMentalHealth() + "/100 mental health",50,150);
        System.out.println();
    }

    public void showLevelBar(int currentLevel) {  
        int maxLevel = 10;
        int barLength = 10;
        int filledLength = (int) ((double) currentLevel / maxLevel * barLength);

        StringBuilder bar = new StringBuilder("[");
        for (int i = 0; i < barLength; i++) {
            if (i < filledLength) {
                bar.append("==");
            } else {
                bar.append("--");
            }
        }
        bar.append("] \nLevel ").append(currentLevel).append("/").append(100);

        try {
            typeWriter.write(bar.toString(), 30, 150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void gainedLevel(int levelAdded) throws InterruptedException{
        this.setLevel(this.getLevel() + levelAdded);
        typeWriter.write(textColor.PURPLE + this.getName()  + textColor.RESET + " has gained " + levelAdded + " level!", 50, 150);

        showLevelBar(this.getLevel());
    }

    public void minusHealth(int minus) throws InterruptedException{
        this.setHealth(this.getHealth() - minus);

        if(this.getHealth() < 0){
            this.setHealth(1);
            typeWriter.write( textColor.RED +"WARNING!!! Your pet is on low hp, feed your pet NOW!!!" + textColor.RESET, 30, 150);
            typeWriter.write(textColor.PURPLE + this.getName()  + textColor.RESET + " health: " +  this.getHealth(), 30, 150);
            return;
        }
        typeWriter.write(textColor.PURPLE + this.getName()  + textColor.RESET + " health: " +  this.getHealth(), 30, 150);
        return;
    }

    public void gainedHealth(int plus) throws InterruptedException{
        this.setHealth(this.getHealth() + plus);

        if(this.getHealth() > 100){
            this.setHealth(100);
            typeWriter.write(textColor.PURPLE + this.getName()  + textColor.RESET + " health: " +  this.getHealth(), 30, 150);
            return;
        }
        typeWriter.write(textColor.PURPLE + this.getName()  + textColor.RESET + " health: " +  this.getHealth(), 30, 150);
        return;
    }

    public void addHunger(int plus) throws InterruptedException{
        this.setHunger(this.getHunger() + plus );

        if(this.getHunger() > 100){
            this.setHunger(100);
            typeWriter.write(textColor.PURPLE + this.getName()  + textColor.RESET + " is really hungry you should feed it...", 30, 150);
            typeWriter.write(textColor.PURPLE + this.getName()  + textColor.RESET + " hunger: " + this.getHunger(), 30   , 150);
            minusHealth(20);
        }
        else{
            typeWriter.write(textColor.PURPLE + this.getName()  + textColor.RESET + " hunger: " + this.getHunger(), 30   , 150);
        }
    }

    private House house;

    public void setHouse(House house) throws InterruptedException{
        if(this.house != null){
            typeWriter.write("this house already has a pet", 50, 150);
        }
        else{
            this.house = house;
        }
    }
    
















}