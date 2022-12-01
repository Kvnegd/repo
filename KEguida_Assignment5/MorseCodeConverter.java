import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * 
 * @author Kevin Eguida
 *
 */

public class MorseCodeConverter {
	static MorseCodeTree morseTree = new MorseCodeTree();
	public MorseCodeConverter() {
		
	}
	/**
	 * returns all the letters in the tree following in-order traversal 
	 * @return string that contains the letters with a space between each letter
	 */
	public static String  printTree() {
		ArrayList<String> treeList = morseTree.toArrayList();
		String treeStr = "";
		
		for(String temp: treeList)
		{
			treeStr+=temp + " ";
		}
		
		return treeStr.trim();
	}
	
	public static String convertToEnglish(String code) {
		String[] letters;
		String[] words= code.split(" / ");
		String convertedStr= "";

		for (String word: words) {
			letters= word.split(" ");

			for (String eachLetter: letters)
				convertedStr+= morseTree.fetch(eachLetter);

			convertedStr+= " ";
		}

		return convertedStr.trim();
		
	}
	public  static String convertToEnglish(File codeFile) throws FileNotFoundException {
		Scanner scanner= new Scanner(codeFile);
		String fileCode= "";
		
		while(scanner.hasNextLine()) 
			fileCode+= scanner.nextLine()+ "\n";
		
		scanner.close();
		
		return convertToEnglish(fileCode.trim());
		
	}

}
