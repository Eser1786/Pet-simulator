package Item.Food;
import Item.Item;


public class Food extends Item {
    private int saturation;

    public int getSat(){return saturation;}
    public void setSat(int saturation){this.saturation = saturation;}

    public Food(){
        super();
    }
    public Food(int id, String name, int quantity, int saturation){
        super(id,name, quantity);
        setSat(saturation);
    }

    
}
