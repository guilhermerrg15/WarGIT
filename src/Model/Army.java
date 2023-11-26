package Model;

class Army {
	protected int army;
	protected Continent continent;
	
	public Army(Continent continent) {
		this.continent = continent;
	}

	public Continent retrieveContinent() {
		return this.continent;
	}

    public void addArmy(int num) {
		this.army += num;
	}

	public int retrieveArmyCount() {
		return this.army;
	}
}
