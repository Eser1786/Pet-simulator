package Item;

public class Item {
    private int id;     
    private String name;
    private int quantity;   // số lượng

    public int getItemID(){return id;}
    public String getItemName(){return name;}
    public int getQuantity(){return quantity;}

    public void setItemID(int id){this.id = id;}
    public void setItemName(String name){this.name = name;}
    public void setQuantity(int quantity){this.quantity = quantity;}

    public Item(){}
    public Item(int id, String name, int quantity){
        setItemID(id);
        setItemName(name);
        setQuantity(quantity);
    }

}
