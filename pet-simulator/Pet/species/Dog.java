package Pet.species;
import Pet.Pet;
import Pet.typeWriter;

public class Dog extends Pet {
    public Dog(){
        super();
    }

    public Dog(int petID, String species, String name, int health, int level, int hunger, int mentalHealth, String sex, String item, String favoriteDish){
        super( petID, species, name, health, level, hunger, mentalHealth, sex, item, favoriteDish);
    }

    @Override
    public void sound(){
        try{
            typeWriter.write("Woof! Woof!...",100);
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
