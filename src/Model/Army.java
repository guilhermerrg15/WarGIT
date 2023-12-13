package Model;

class Army {
	protected int army;
	protected Continent continent;
	
	private Army(Continent continent) {
		this.continent = continent;
	}

	Continent retrieveContinent() {
		return this.continent;
	}

    void addArmy(int num) {
		this.army += num;
	}

	int retrieveArmyCount() {
		return this.army;
	}
}
