package Pet;

public abstract class Pet{
    private int petID;       // bao gồm 3 chữ số
    private String species;
    private String name;
    private int health;
    private int level;
    private int hunger;
    private int mentalHealth; 
    private String sex;     // ĐÂY LÀ GIỚI TÍNH
    private String item;
    private String favoriteDish;


    public int getPetID(){return petID;}
    public String getSpecies(){return species;}
    public String getName(){return name;}
    public int getHealth(){return health;}
    public int getLevel(){return level;}
    public int getHunger(){return hunger;}
    public int getMentalHealth(){return mentalHealth;}
    public String getSex(){return sex;}
    public String getItem(){return item;}
    public String getFavoriteDish(){return favoriteDish;}



    public void setPetID(int petID){this.petID = petID;}
    public void setSpecies(String species){this.species = species;}
    public void setName(String name){this.name = name;}
    public void setHealth(int health){this.health = health;}
    public void setLevel(int level){this.level = level;}
    public void setHunger(int hunger){this.hunger = hunger;}
    public void setMentalHealth(int mentalHealth){this.mentalHealth = mentalHealth;}
    public void setSex(String sex){this.sex = sex;}
    public void setItem(String item){this.item = item;}
    public void setFavoriteDish(String favoriteDish){this.favoriteDish = favoriteDish;}


    public abstract void sound();
    public abstract void feeling();

    public Pet(int petID, String species, String name, int health, int level, int hunger, int mentalHealth, String sex, String item, String favoriteDish){
        
        if(petID >= 100 && petID <= 999){
            setPetID(petID);
        }
        else{
            System.out.println("Lỗi id");
        }

        setSpecies(species);
        setName(name);
        setHealth(health);
        setLevel(level);
        setHunger(hunger);
        setMentalHealth(mentalHealth);
        
        if(sex.equals("Male") || sex.equals("Female") || sex.equals("male") || sex.equals("female")){
            setSex(sex);
        }
        else{
            setSex("male");
        }

        setItem(item);
        setFavoriteDish(favoriteDish);

    }


    public Pet(){}

    // public boolean isAccessoryEquip(){

    // }
























}