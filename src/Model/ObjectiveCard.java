package Model;

abstract class ObjectiveCard {
    protected String description;
    protected Player owner;
    protected String image; 
    protected String name;

    public void checkOwner(Player player){
        this.owner = player;
    }

    public String getDescription(){
        return this.description;
    }

    public String getImage() {
        return this.image;
    }
    
    public String getName() {
    	return this.name;
    }

    public abstract boolean checkStatus();
}
