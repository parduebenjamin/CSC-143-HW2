/*
 * Benjamin Pardue
 * CSC 143 - HW 2
 * Emilia Gan
 * 
 */

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;

public class BookAnalyzer {
	ArrayList<String> bookWords = new ArrayList();
	Set<String> posWords = new HashSet<>();
	Set<String> negWords = new HashSet<>();
	Set<File> savedBooks = new HashSet<>();
	String[] posList = {"good", "nice", "love", "excellent", "great",
			"awesome", "wonderful", "fantastic", "fabulous", "like"};
	String [] negList = {"bad", "awful", "hate", "terrible", "miserable",
			"sad", "dislike", "atrocious", "depressed", "cry"};
	public int posWordCount = 0;
	public int negWordCount = 0;
	public int totalPosWords = 0;
	public int totalNegWords = 0;
	public double startTime = 0;
	public double endTime = 0;
	public double totalTime = 0;
	public int mostCommonPos = 0;
	public int mostCommonNeg = 0;
	public int posQuantity = 0;
	public int negQuantity = 0;
	public String whichBookIsThis = "Title Error";
	
	//need a method here to (1) ask for an input text book file, (2) convert it to a string
	//need a method here to (1) ask for an input text positive words file, (2) convert it to a string
	//need a method here to (1) ask for an input text negative words file, (2) convert it to a string
	

	
	public Set<String> getWordsInString(String text) {
	    String[] wordArray = text.split(" ");
	    Set<String> set = new HashSet<String>();
	    for (String currentWord : wordArray) {
	      set.add(currentWord);
	    }
	    return set;
	}
	/*
	 * A CONSTRUCTOR that takes 3 INPUT PARAMETERS: the “positive”
and “negative” text files, which are used to construct the posWords and
negWords sets, and a book text file, which is used to construct the
bookWords ArrayList object.
Your class should handle bad input gracefully. In other words, if one of
your sets is not entered as expects, an option might be to have “default”
lists for positive and negative words, such as the lists shown below
(which you would need to convert to sets), that could be used instead:
	 */
	//	public static  ArrayList<String> BookAnalyzer (String positve, String negative, String book) {
	
		public BookAnalyzer() throws IOException{
		//to-do for later: verify this is a good place to put the start time
		String nextWord;
		startTime = System.currentTimeMillis();
		
		File file1 = null;
		File file2 = null;
		File file3 = null;
		
		//Scanner scanner1 = new Scanner(new File("Macbeth.txt"));
		Component frame = null;
		SentimentReport.intro();
		
		JOptionPane.showMessageDialog(frame,"First, give me a text file of a book you're interested in");
		//then we need to assign it to a variable
		final JFileChooser fc2 = new JFileChooser();

		int returnVal2 = fc2.showOpenDialog(null);
		if (returnVal2 == JFileChooser.APPROVE_OPTION) {
		    // Retrieve the selected file
		    File fileB = fc2.getSelectedFile();
		    try (FileInputStream fis = new FileInputStream(fileB)) {
		    	file3 = fileB;
		    }
		    /*List<String> string1 = Files.readAllLines(file1);
		    BookAnalyzer book = new BookAnalyzer(file1, file2, file3);*/
		}
		///////experimental option here; verify impacted by test_mode constant
		if (SentimentReport.TEST_MODE == false) {
			
	
        JPanel panel = new JPanel();
        JLabel label = new JLabel("Do you want to save or analyze this book?");
        label.setHorizontalAlignment(SwingConstants.CENTER);
        label.setBounds(0, 0, 250, 32);
        panel.add(label);

        UIManager.put("OptionPane.minimumSize",new Dimension(270, 120));

        Object[] options = {"Save", "Analyze"};
        Object l = JOptionPane.showInputDialog(null, panel, 
                "Save or Analyze?", JOptionPane.PLAIN_MESSAGE, null, options, options[1]);

        if(l instanceof String & l.equals("Save")){
            savedBooks.add(file3);
            JOptionPane.showMessageDialog(frame,"Your book was saved!");
            System.exit(0);
            
        }
 
		}
		
		///////experimental option above
		
		
		JOptionPane.showMessageDialog(frame,"Second, give me a text file of positive words");
		//then we need to assign it to a variable

		//then execute the BookAnalyzer and getReport() methods
		final JFileChooser fc = new JFileChooser();
		int returnVal = fc.showOpenDialog(null);
		if (returnVal == JFileChooser.APPROVE_OPTION) {
		    // Retrieve the selected file
		    File fileC = fc.getSelectedFile();
		    try (FileInputStream fis = new FileInputStream(fileC)) {
		    	file1 = fileC;
		    }
		}
		
		JOptionPane.showMessageDialog(frame,"Finally, give me a text file of negative words");
		//then we need to assign it to a variable
		final JFileChooser fc1 = new JFileChooser();

		int returnVal1 = fc.showOpenDialog(null);
		if (returnVal1 == JFileChooser.APPROVE_OPTION) {
		    // Retrieve the selected file
		    File fileA = fc.getSelectedFile();
		    try (FileInputStream fis = new FileInputStream(fileA)) {
		    	file2 = fileA;
		    }
		}
	
		
		
		BufferedReader scanner1 = new BufferedReader(new FileReader(file1)); 
		BufferedReader scanner2 = new BufferedReader(new FileReader(file2)); 
		BufferedReader scanner3 = new BufferedReader(new FileReader(file3)); 
		
		
		while((nextWord=scanner1.readLine())!=null) {
			String[] word = nextWord.split(" ");
			int len = word.length;
			for (int i = 0; i < len; i++) {
				posWords.add(word[i]);
			} //scans the txt file with positive words and makes strings
		}
			
			while((nextWord=scanner2.readLine())!=null) {
				String[] word = nextWord.split(" ");
				int len = word.length;
				for (int i = 0; i < len; i++) {
					negWords.add(word[i]);
				} //scans the txt file with negative words and makes strings
			
		}
			
			while((nextWord=scanner3.readLine())!=null) {
				String[] word = nextWord.split(" ");
				int len = word.length;
				for (int i = 0; i < len; i++) {
					bookWords.add(word[i]);
				} //scans the book file and makes strings
		}
			
		endTime = System.currentTimeMillis();
		totalTime = endTime - startTime;
		whichBookIsThis = file3.getName();
		
	}
	


	public String getCategory() {
		String category = null;
		int len = bookWords.size();
		for (int i = 0; i<len;i++) {
			if(posWords.contains(bookWords.get(i))) {
				posWordCount++;
			} else if(negWords.contains(bookWords.get(i))) {
				negWordCount++;
				}
			if (negWordCount > (posWordCount)) {
				category = "Comedy";
			}
			else if (posWordCount > (negWordCount)){
				category = "Tragedy";
			}
			else if (posWordCount == (negWordCount)){
				category = "Boring Book";
			} else {
				category = "There was an error with the word count";
			}
		}
		return category;
		
	}
	
	public int getBookSize() {
		return bookWords.size();
	}
	
	public int getPosWordCount() {
		int len = bookWords.size();
		for (int i = 0; i<len;i++) {
			if(posWords.contains(bookWords.get(i))) {
				totalPosWords++;
			} }
		return totalPosWords;
	}
	
	public int getNegWordCount() {
		int len = bookWords.size();
		for (int i = 0; i<len;i++) {
			if(negWords.contains(bookWords.get(i))) {
				totalNegWords++;
			} }
		return totalNegWords;
	}
	
	public double posWordPercent() {
		return (totalPosWords / bookWords.size()) * 100.0;
	}
	public double negWordPercent() {
		return (totalNegWords / bookWords.size()) * 100.0;
	}
	
	public double getTimeToProcess(){
		return totalTime;
	}
	public String getBookTitle() {
		return whichBookIsThis;
	}
	
	public String mostCommonPos() {
		String mostCommonPosWord = "Error in Counting Positive Words";
		 posQuantity = 0;
		java.util.Iterator<String> i = posWords.iterator();
		while(i.hasNext()) {
			String currentWord = i.next();
			mostCommonPos = Collections.frequency(bookWords, currentWord);
			if(mostCommonPos > posQuantity) {
				mostCommonPosWord = currentWord;
				posQuantity = mostCommonPos;
			}
		} return mostCommonPosWord;
		
	}
	public int mostCommonPosQuantity() {
		return posQuantity;
	}
	
	public String mostCommonNeg() {
		String mostCommonNegWord = "Error in Counting Negative Words";
		negQuantity = 0;
		java.util.Iterator<String> i = negWords.iterator();
		while(i.hasNext()) {
			String currentWord = i.next();
			mostCommonNeg = Collections.frequency(bookWords, currentWord);
			if(mostCommonNeg > negQuantity) {
				mostCommonNegWord = currentWord;
				negQuantity = mostCommonNeg;
			}
		} return mostCommonNegWord;
		
	}
	
	public int mostCommonNegQuantity() {
		return negQuantity;
	}
	

	
}
