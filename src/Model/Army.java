package Model;

class Army {
	protected int army;
	protected String continent;
	
	public Army(String continent) {
		this.continent = continent;
	}
	public String retrieveContinent() {
		return this.continent;
	}
    public void addArmy(int num) {
		this.army += num;
	}
	public int retrieveArmyCount() {
		return this.army;
	}
}
