package Item.Food;
import Item.Item;

public class Food extends Item {
    private int saturation;

    public int getSat(){return saturation;}
    public void setSat(int saturation){this.saturation = saturation;}

    Food(){
        super();
    }
    Food(int id, String name, int saturation){
        super(id,name);
        setSat(saturation);
    }
}
