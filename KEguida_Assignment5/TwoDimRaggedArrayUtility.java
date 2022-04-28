import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Scanner;

public class TwoDimRaggedArrayUtility {
	
	public static double getAverage(double[][] data){
	    
		int element = 0;
	    double sum = 0;
	    
	    for (int i = 0; i < data.length; i++) {
	      for (int j = 0;j < data[i].length;j++) {
	        sum += data[i][j];
	        ++element;
	      }
	    }
	    
	    return sum / element;
	}
	
	public static double getColumnTotal(double[][] data, int col){
		 
		double tot = 0;
		
		    for (int i=0; i<data.length; i++) 
		    {
		    	if (col < data[i].length) 
			      {
			    	  tot += data[i][col];
			      }
		    }
	
		return tot;
	}
	
	public static double getHighestInArray(double[][] data){
		
		double high = Double.MIN_VALUE;
		
	    for (int i = 0; i < data.length; i++)
	    {
	    	for (int k = 0;k < data[i].length;k++)
	    	{
		        if (data[i][k] > high) {
		        	high = data[i][k];
		        }
		    }
	    }
	    
	    return high;
	}
	
	public static double getHighestInColumn(double[][] data, int col){
		
		double highest = Double.MIN_VALUE;
		
	    for (int i = 0; i < data.length; i++) 
	    {
	      if (col < data[i].length) 
	      {
	    	  if (data[i][col] > highest) {
	    		  highest = data[i][col];
	  	      }
	      }
	    }
	    
	    return highest;
	}
	
	public static int getHighestInColumnIndex(double[][] data, int col){
		
		double highest = Double.MIN_VALUE;
		int index=0;
		
	    for (int i = 0; i < data.length; i++) 
	    {
	      if (col < data[i].length) 
	      {
	    	  if (data[i][col] > highest) {
	    		  highest = data[i][col];
	  	        index=i;
	  	      }
	      }
	    }
	    
	    return index;
	}
	
	public static double getHighestInRow(double[][] data, int row){
	    
		double highest = Double.MIN_VALUE;
	    
	    for (int i = 0; i < data[row].length; i++) 
	    {
	      if (data[row][i] > highest) {
	    	  highest = data[row][i];
	      }
	    }
	    
	    return highest;
	}
	
	public static int getHighestInRowIndex(double[][] data, int row){
		
		double highest = Double.MIN_VALUE;
		int index=0;
	    
	    for (int i = 0; i < data[row].length; i++) 
	    {
	      if (data[row][i] > highest) {
	        highest = data[row][i];
	        index=i;
	      }
	    }
	    
	    return index;

	}
	
	public static double getLowestInArray(double[][] data){
		
	    double lowest = Double.MAX_VALUE;
	    
	    for (int i = 0; i < data.length; i++) 
	    { 
	      for (int k = 0; k < data[i].length; k++) 
	      {
	        if (data[i][k] < lowest) {
	          lowest = data[i][k];
	        }
	      }
	    }
	    return lowest;

	}
	
	public static double getLowestInColumn(double[][] data, int col){
		
	    double lowest = Double.MAX_VALUE;
	    
	    for (int i = 0; i < data.length; i++) 
	    {
	      if (col < data[i].length) 
	      {
		      if (data[i][col] < lowest) {
		    	  lowest = data[i][col];
		      }
	      }
	    }
	    
	    return lowest;

	}
	
	public static int getLowestInColumnIndex(double[][] data, int col){
		
	    double lowest = Double.MAX_VALUE;
	    int index=0;
	    
	    for (int i = 0; i < data.length; i++) 
	    {
	      if (col < data[i].length) 
	      {
		      if (data[i][col] < lowest) {
		    	  lowest = data[i][col];
		    	  index=i;
		      }
	      }
	    }
	    
	    return index;
	}
	
	public static double getLowestInRow(double[][] data, int row){
		
	    double lowest = Double.MAX_VALUE;
	    
	    for (int i = 0; i < data[row].length; i++) 
	    {
	      if (data[row][i] < lowest) {
	    	  lowest = data[row][i];
	      }
	    }
	    
	    return lowest;
	}
	
	public static int getLowestInRowIndex(double[][] data, int row){
		
	    double lowest = Double.MAX_VALUE;
	    int index=0;
	    
	    for (int i = 0; i < data[row].length; i++) 
	    {
	      if (data[row][i] < lowest) {
	    	  lowest = data[row][i];
	    	  index=i;
	      }
	    }
	    
	    return index;
	}
	
	public static double getRowTotal(double[][] data, int row){
		
	    double sum = 0;
	    for (int i = 0; i < data[row].length; i++) {
	        sum += data[row][i];
	    }
	    
	    return sum;
	}
	
	public static double getTotal(double[][] data){
		
	    double sum = 0;
	    for (int i = 0; i < data.length; i++) { 
	      for (int k = 0; k < data[i].length; k++) {
	        sum += data[i][k];
	      }
	    }
	    
	    return sum;
	}
	
	public static double[][] readFile(File file) throws FileNotFoundException{
		
		String [][] fileInput=new String[10][];
		Scanner input =new Scanner(file);
		
		int row = 0;
		
		while(input.hasNext())
		{
			
			String line = input.nextLine();
			
			fileInput[row]=line.split(" ");
			
			row++;
		}
		
		double [][] data=new double[row][];
		
		for(int i=0; i<row; i++) 
		{
			data[i] = new double[fileInput[i].length];
			
			for(int k=0; k<fileInput[i].length; k++) 
			{
				data[i][k] = Double.parseDouble(fileInput[i][k]); 
			}

		}
		
		// Close the file
		input.close();

		return data;

	}
	
	public static void writeToFile(double[][] data, File outputFile) throws FileNotFoundException 
	{
		    
		PrintWriter output = new PrintWriter(outputFile);
		StringBuilder text = new StringBuilder();
		
		for (int i=0; i<data.length; i++) 
		{
			for (int k=0; k<data[i].length; k++) 
			{
				  text.append(data[i][k]+ " ");
			}
		    text.append("\n");
		 }
		
		output.print(text);
		
		output.close();
	}


}
