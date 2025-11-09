package Item.Furniture;
import Item.Item;
public class Furniture extends Item {
    private int comfort;

    public int getComfort(){return comfort;}
    public void setComfort(int comfort){this.comfort = comfort;}

    Furniture(){
        super();
    }
    Furniture(int id, String name,int quantity, int comfort){
        super(id,name,quantity);
        setComfort(comfort);
    }
}
