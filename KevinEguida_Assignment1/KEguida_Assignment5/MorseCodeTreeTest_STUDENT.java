import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class MorseCodeTreeTest_STUDENT {
	
	@Test
	public void testGetRoot() {	
		assertEquals("",MorseCodeConverter.morseTree.root.getData());
	}
	
	@Test
	public void testSetRoot() {	
		TreeNode<String> newRoot = new TreeNode<String>("/");
		MorseCodeConverter.morseTree.setRoot(newRoot);
		assertEquals("/",MorseCodeConverter.morseTree.getRoot().getData());
	}
	
	@Test
	public void testinsert() {	
		MorseCodeConverter.morseTree.insert("..--", "?");
		MorseCodeConverter.morseTree.insert(".-.-", "!");
		MorseCodeConverter.morseTree.insert("---.", ",");
		MorseCodeConverter.morseTree.insert("----", ".");
		String converter1 = MorseCodeConverter.convertToEnglish(". ..- .... ---- ---- ---- / .-- .- .. - ---. / .-- .... .- - ..-- .-.- ");
		assertEquals("euh... wait, what?!", converter1);
	}
	
	@Test
	public void testFetch() {
		String message="";
		message += MorseCodeConverter.morseTree.fetch(".--");
		message += MorseCodeConverter.morseTree.fetch("---");
		message += MorseCodeConverter.morseTree.fetch(".--");
		message += MorseCodeConverter.morseTree.fetch("..--");
		message += MorseCodeConverter.morseTree.fetch(".-.-");
		message += MorseCodeConverter.morseTree.fetch("---.");
		message += MorseCodeConverter.morseTree.fetch("----");
		assertEquals("wow?!,.",message);
	}
	
	
	

}
