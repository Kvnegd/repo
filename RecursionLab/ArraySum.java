public class ArraySum {
	
	//Finds the sum of the element in an array starting from a specified index
	//to the beginning of the array
	public int sumOfArray (Integer[] a, int index )
	{
		int sum= a[index];
		
		if (index==0)
			return sum;
		else
			return sum += sumOfArray(a, --index);
	}
	
	
	//Finds the sum of all elements in an array
	public int sumOfArray (Integer[] a)
	{
		int index = -1;
		// This for loop finds the last index that contains a value
		for (int i =0; i<a.length; i++)
		{
			if (a[i]!=null)
				index++;
		}
		
		int sum= a[index];
		
		if (index==0)
			return sum;
		else
			return sum += sumOfArray(a, --index);
	}

}
