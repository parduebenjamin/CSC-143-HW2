
/*
 * Benjamin Pardue
 * CSC 143 - HW 2
 * Emilia Gan
 * 
 */
import java.awt.Component;
import java.io.*;
import java.lang.*;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.*;
import java.util.*;

public class SentimentReport {

	public static final boolean TEST_MODE = false;
	
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
	
		BookAnalyzer bookAnalyzer = new BookAnalyzer();
		
		Component frame = null;
		String bookTitle = bookAnalyzer.getBookTitle();
		int numberOfWords = bookAnalyzer.getBookSize();
		String category = bookAnalyzer.getCategory();
		int posCount = bookAnalyzer.getPosWordCount();
		int negCount = bookAnalyzer.getNegWordCount();
		double posPercent = bookAnalyzer.posWordPercent();
		double negPercent = bookAnalyzer.negWordPercent();
		String commonPos = bookAnalyzer.mostCommonPos();
		String commonNeg = bookAnalyzer.mostCommonNeg();
		int commonPosQuant = bookAnalyzer.mostCommonPosQuantity();
		int commonNegQuant = bookAnalyzer.mostCommonNegQuantity();
		double timeToProcess = bookAnalyzer.getTimeToProcess();
		GetReport(bookTitle, numberOfWords, posCount, posPercent, commonPos, commonPosQuant,
				negCount, negPercent, commonNeg, commonNegQuant, category, timeToProcess);

	}
	
	public static void intro() {

		Component frame = null;
		JOptionPane.showMessageDialog(frame,"This program predicts whether a book is a work of comedy, tragedy, or a boring book."
				 + "\n" + "To make this prediction, we use 'sentiment analysis'."
				 + "\n" + "Books with more positive words (i.e. 'good', 'nice', 'love') are more likely to be comedies."
				 + "\n" + "Books with more negative words (i.e. 'bad', 'terrible', 'hate') are more likely to be tragedies."
				 + "\n" + "We consider books with equal quantities of positive and negative words be boring"
				 + "\n" + "You need to provide three text files: (1) a list of positive words, (2) a list of negative words," 
				 + "\n" + "and (3) a book you'd like to analyze."
				);
	}
	
	public static void GetReport(String bookTitle1, int numberOfWords1, int posCount1, double posPercent1, String commonPos1, int commonPosQuant1,
			int negCount1, double negPercent1, String commonNeg1, int commonNegQuant1, String category1, double timeToProcess1){

		
		String report = "There was an error in calculating the report";
		Component frame = null;
		JOptionPane.showMessageDialog(frame,
				"File name: " + bookTitle1
				+ "\n" + "Total number of words: " + numberOfWords1
				+ "\n" + "Total number of positive words: " + posCount1
				+ "\n" + "Percentage of total words that are positive words: " + posPercent1
				+ "\n" + "The most common positive word, " + commonPos1 + ", occured " + commonPosQuant1 + " times."
				+ "\n" + "Total number of negative words: " + negCount1
				+ "\n" + "Percentage of total words that are negative words: " + negPercent1
				+ "\n" + "The most common negative word, " + commonNeg1 + ", occured " + commonNegQuant1 + " times."
				+ "\n" + "Category is: " + category1
				+ "\n" + "Time to compile report: " + timeToProcess1 + " milliseconds"
				);
		
	}
	




}
