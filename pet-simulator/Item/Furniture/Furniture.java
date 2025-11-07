package Item.Furniture;
import Item.Item;
public class Furniture extends Item {
    private int comfort;

    public int getSat(){return comfort;}
    public void setSat(int comfort){this.comfort = comfort;}

    Furniture(){
        super();
    }
    Furniture(int id, String name, int comfort){
        super(id,name);
        setSat(comfort);
    }
}
