package Item;

public class Item {
    private int id;
    private String name;

    public int getItemID(){return id;}
    public String getItemName(){return name;}

    public void setItemID(int id){this.id = id;}
    public void setItemName(String name){this.name = name;}

    public Item(){}
    public Item(int id, String name){
        setItemID(id);
        setItemName(name);
    }

}
