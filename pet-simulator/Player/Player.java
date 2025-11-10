package Player;

public class Player {
    private String name;
    private int coins;
    
    public String getName(){return name;}
    public int getCoin(){return coins;}

    public void setName(String name){this.name = name;}
    public void setCoin(int coins){this.coins = coins;}

    public Player(){
        setCoin(10000);
    }

    public Player(String name, int coins){
        setName(name);
        setCoin(coins);
    }
    
}
