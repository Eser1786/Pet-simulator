package Pet.species;
import Pet.Pet;
import Pet.Habitat.ground;
import Player.Player;
import Utils.typeWriter;

public class Dog extends Pet implements ground {
    public Dog(){
        super();
    }

    public Dog(int petID, String species, String name, int health, int level, int hunger, int mentalHealth, String sex, String item){
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
                typeWriter.write("Woof? ...",50,150);
                typeWriter.write("(" + this.getName() + " is scared being around you... you should take " + this.getName() + " on a walk!)",50,150);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        else if(this.getMentalHealth() < 75){
            try{
                typeWriter.write("Woof... Woof...",50,150);
                typeWriter.write("(Even though you gained its trust, its still being distance...)",50,150);
            }catch (InterruptedException e){
                e.printStackTrace();
            }
        }
        else{
            try{
                typeWriter.write("Woof! Woof! Woof!...",50,150);
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
                typeWriter.write(this.getName() + " is barking with joy around you. ", 50, 150);
                typeWriter.write("maybe you should take " + this.getName() +" on a walk...?", 50, 150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        
        System.out.println();
    }

    public void walkAroundThePark(Player player) throws InterruptedException{
        typeWriter.write(this.getName() + " and you go for a walk in the park. " + this.getName() + "looks excited!", 50);
        typeWriter.write(this.getName() + " running around playing with other dogs " , 50, 150);
        typeWriter.write("...", 450, 150);
        typeWriter.write( this.getName() + " and you have a great time!", 50, 150);
        this.gainedLevel(2);
        this.gainedMentalHealth(10);
        this.setHunger(this.getHunger()-20);
    }
}
