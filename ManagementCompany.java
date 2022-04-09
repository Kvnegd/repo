
public class ManagementCompany {
	
	private final int MAX_PROPERTY=5;
	private double mgmFeePer;
	private String name;
	private Property[] properties;
	private String taxID;
	private final int MGMT_WIDTH=10;
	private final int MGMT_DEPTH=10;
	private Plot plot;
	private int index=0;
	
	public ManagementCompany() {
		name = "";
		taxID = "";
		mgmFeePer = 0.0;
		plot = new Plot(0, 0, MGMT_WIDTH, MGMT_DEPTH);
		
		this.properties = new Property[MAX_PROPERTY];
	}
	
	public ManagementCompany(String name, String taxID, double mgmFee) {
		this.name = name;
		this.taxID = taxID;
		this.mgmFeePer = mgmFee;
		plot = new Plot(0, 0, MGMT_WIDTH, MGMT_DEPTH);
		
		this.properties = new Property[MAX_PROPERTY];
	}
	
	public ManagementCompany(String name, String taxID, double mgmFee, int x, int y, int width, int depth) {
		this.name = name;
		this.taxID = taxID;
		this.mgmFeePer = mgmFee;
		plot = new Plot(x, y, width, depth);
		
		this.properties = new Property[MAX_PROPERTY];
	}
	
	public ManagementCompany(ManagementCompany otherCompany) 
	{
		this.name = otherCompany.name;
		this.taxID = otherCompany.taxID;
		this.mgmFeePer = otherCompany.mgmFeePer;
		plot = new Plot(otherCompany.plot);
		
		this.properties = new Property[MAX_PROPERTY];
	}
	
	public int addProperty(Property property) 
	{
		int hasOverlaped=0;
			
			for(int k=0; k<index; k++) {
				if (properties[k].getPlot().overlaps(property.getPlot()))
					++hasOverlaped;
			}
			
			if (index==MAX_PROPERTY)
				return -1;
			else if (property.equals(null))
				return -2;
			else if (!this.plot.encompasses(property.getPlot()))
				return -3;
			else if (hasOverlaped!=0)
				return -4;
			else {
				properties[index] = new Property(property);
				index++;
			}
			
			return index-1;
	}
	
	public int addProperty(String name,String city,double rent,String owner) 
	{
		int hasOverlaped=0;
			
			Property prop = new Property(name, city, rent, owner);
			
			for(int k=0; k<index; k++) {
				if (properties[k].getPlot().overlaps(prop.getPlot()))
					++hasOverlaped;
			}
			
			if (index==MAX_PROPERTY)
				return -1;
			else if (prop.equals(null))
				return -2;
			else if (this.plot.encompasses(prop.getPlot()))
				return -3;
			else if (hasOverlaped!=0)
				return -4;
			else {
				properties[index] = new Property(prop);
				index++;
			}
			
			return index;
	}
	
	public int addProperty(String name, String city, double rent, String owner, int x, int y, int width, int depth) 
	{
		int hasOverlaped=0;
		
		Property prop = new Property(name, city, rent, owner, x, y, width, depth);
		
		for(int k=0; k<index; k++) {
			if (properties[k].getPlot().overlaps(prop.getPlot()))
				++hasOverlaped;
		}
		
		if (index==MAX_PROPERTY)
			return -1;
		else if (prop.equals(null))
			return -2;
		else if (this.plot.encompasses(prop.getPlot()))
			return -3;
		else if (hasOverlaped!=0)
			return -4;
		else {
			properties[index] = new Property(prop);
			index++;
		}
		
		return index;
	}
	
	public String displayPropertyAtIndex(int i) {
		return properties[i].toString();
	}
	
	public double maxRentProp() {
		double maxRent=0.0;
		
		for (int k=0; k<index; k++) {
			if (properties[k].getRentAmount()>maxRent)
				maxRent = properties[k].getRentAmount();
		}
		return maxRent;
	}
	
	public int maxRentPropertyIndex() {
		double maxRent=0.0;
		int maxRentIndex=0;
		
		for (int k=0; k<index; k++) {
			if (properties[k].getRentAmount()>maxRent) {
				maxRent = properties[k].getRentAmount();
				maxRentIndex = k;
			}
		}
		return maxRentIndex;
	}
	
	public int getMAX_PROPERTY() {
		return MAX_PROPERTY;
	}

	public String toString() {
		String str = "";
		for (int k=0; k<index; k++) {
			str += properties[k].toString();
		}
		
		return "List of properties for " + this.name + ", taxID: " + this.taxID
				+ "\n___________________________________________________________"
				+ str;
	}
	
	public double totalRent() {
		double totalRent=0.0;
		
		for (int k=0; k<index; k++) {
			totalRent += properties[k].getRentAmount();
		}
		return totalRent;
	}

	public String getName() {
		return name;
	}

	public String getTaxID() {
		return taxID;
	}

	public Plot getPlot() {
		return plot;
	}

	public int getIndex() {
		return index;
	}
	
	
	

}
