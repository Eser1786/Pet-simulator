package Pet.species;
import Pet.Pet;
import Utils.typeWriter;

public class Parrot extends Pet {
    public Parrot(){
        super();
    }

    public Parrot(int petID, String species, String name, int health, int level, int hunger, int mentalHealth, String sex, String item, String favoriteDish){
        super( petID, species, name, health, level, hunger, mentalHealth, sex, item, favoriteDish);
    }

    @Override
    public void sound(){
        try{
            typeWriter.write("squawk! squawk!...",100);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    

    @Override
    public void feeling(){
        try{
            typeWriter.write("Có vẻ " + getName() +" rất muốn đi dạo...",100);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
