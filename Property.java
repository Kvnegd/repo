public class Property {
	
	private String city;
	private String owner;
	private String propertyName;
	private double rentAmount;
	private Plot plot;
	
	public Property() {
		city = "";
		owner = "";
		propertyName = "";
		rentAmount = 0.0;
		plot = new Plot();
	}
	
	public Property(Property p) {
		this.city = p.city;
		this.owner = p.owner;
		this.propertyName = p.propertyName;
		this.rentAmount = p.rentAmount;
		this.plot = new Plot(p.plot);
	}
	
	public Property(String propertyName, String city, double rentAmount, String owner) {
		this.city = city;
		this.owner = owner;
		this.propertyName = propertyName;
		this.rentAmount = rentAmount;
		this.plot = new Plot();
	}
	
	public Property(String propertyName, String city, double rentAmount, String owner, int x, int y, int width, int depth) {
		this.city = city;
		this.owner = owner;
		this.propertyName = propertyName;
		this.rentAmount = rentAmount;
		this.plot = new Plot(x, y, width, depth);
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getPropertyName() {
		return propertyName;
	}

	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}

	public double getRentAmount() {
		return rentAmount;
	}

	public void setRentAmount(double rentAmount) {
		this.rentAmount = rentAmount;
	}

	public void setPlot(Plot plot) {
		this.plot = plot;
	}
	

	public Plot getPlot() {
		return this.plot;
	}

	
	public String toString() {
		return "\n\nProperty Name: " + this.propertyName +
				"\nLocated in " + this.city +
				"\nBelonging to: " + this.owner +
				"\nRent Amount: " + this.rentAmount;
	}
	

}
