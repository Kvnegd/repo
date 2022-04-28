public class HolidayBonus {
	
	public static double[] calculateHolidayBonus(double[][] data, double high, double low, double other) {
		
		 double[] bonus = new double[data.length];
		 
		 for(int row=0; row<data.length; row++) 
		 {
			 for(int col=0; col<data[row].length; col++) 
			 {
				 if (data[row][col]<=0)
					 bonus[row]+=0;
				 else if (TwoDimRaggedArrayUtility.getHighestInColumnIndex(data, col)==row) 
				 {
					 bonus[row]+=high;
				 }
				 else if (TwoDimRaggedArrayUtility.getLowestInColumnIndex(data, col)==row) 
				 {
					 bonus[row]+=low;
				 }
				 else
					 bonus[row]+=other;
			 }
		 }
		       
		 return bonus;
	}
	
	
	
	public static double calculateTotalHolidayBonus(double[][] data, double high, double low, double other) {
		
		double[] bonusArray = calculateHolidayBonus(data, high, low, other);
	    double total = 0;
	    
	    for (double bonus:bonusArray) {
	      total+=bonus;
	    }
	    return total;
	}

}
