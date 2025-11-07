package Item.Accessory;
import Item.Item;

public class Accessory extends Item {
    private int stylePoint;     // điểm 

    public int getStylePoint(){return stylePoint;}
    public void setStylePoint(int stylePoint){this.stylePoint = stylePoint;}

    public Accessory(){
        super();
    }
    public Accessory(int id, String name, int stylePoint){
        super(id,name);
        setStylePoint(stylePoint);
    }
    
}
