package _03_Hangman;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Hangman implements KeyListener{
	public static void main(String[] args) {
		Hangman hangman = new Hangman();
	}
	
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	int lifeCount = 10;
	JLabel lives = new JLabel("Lives: " + lifeCount);
	JLabel wordSpace = new JLabel("                  ");
	Stack<String> words = new Stack<String>();
	boolean completed = false;
	String currentWord;
	String wordSpaceText;
	char[] wordChars;
	boolean found = false;
	JLabel missed = new JLabel("Missed characters: ");
	JLabel label = new JLabel();
	
	Hangman(){
		String input = JOptionPane.showInputDialog("Enter the number of words to guess (1-100): ");
		int wordCount = Integer.parseInt(input);
		for(int i = 0; i < wordCount; i++) {
			String randWord = Utilities.readRandomLineFromFile("dictionary.txt");
			System.out.println(randWord);
			if(words.contains(randWord) == false) {
				words.push(randWord);
			}
		}
		currentWord = words.pop();
		System.out.println(currentWord);
		wordSpaceText = createWordSpace(currentWord);
		wordChars = wordSpaceText.toCharArray();
		wordSpace.setText(wordSpaceText);
		label.setText("<html> Lives: " + lifeCount + wordSpace.getText() + "<br>" + missed.getText() + "</html>");
		panel.add(label);
		frame.addKeyListener(this);
		frame.add(panel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setSize(450, 85);
	}
	
	public String createWordSpace(String word) {
		String spaces = "";
		for(int i = 0; i < word.length(); i++) {
			spaces+="_";
		}
		return spaces;
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		System.out.println(e.getKeyChar());
		
		for(int i = 0; i < currentWord.length(); i++) {
			if(currentWord.charAt(i) == e.getKeyChar()) {
				wordChars[i] = e.getKeyChar();
				wordSpaceText = String.valueOf(wordChars);
				wordSpace.setText(wordSpaceText);
				found = true;
			}
		}
		if(found == false) {
			lifeCount--;
			missed.setText(missed.getText() + e.getKeyChar() + " ");
			label.setText("<html> Lives: " + lifeCount + "     " +  wordSpace.getText() + "<br>" + missed.getText() + "</html>");
		}
		else {
			int place = 0;
			for(int i = 0; i < currentWord.length(); i++) {
				if(e.getKeyChar() == currentWord.charAt(i)) {
					place = i;
				}
			}
			wordSpaceText = wordSpaceText.substring(0, place) + e.getKeyChar() + wordSpaceText.substring(place+1);
			wordSpace.setText(wordSpaceText);
			label.setText("<html> Lives: " + lifeCount + "     " +  wordSpace.getText() + "<br>" + missed.getText() + "</html>");
		}
		
		if(wordSpace.getText().contains("_") == false) {
			completed = true;
		}
		
		if(completed == true) {
			if(words.isEmpty()) {
				JOptionPane.showMessageDialog(null, "You won the game!");
				System.exit(0);
			}
			else {
				JOptionPane.showMessageDialog(null, "You have completed this word! You have " + words.size() + " left");
				currentWord = words.pop();
				wordSpaceText = createWordSpace(currentWord);
				wordChars = wordSpaceText.toCharArray();
				wordSpace.setText(wordSpaceText);
				missed.setText(missed.getText().substring(0,18));
				label.setText("<html> Lives: " + lifeCount + "     " +  wordSpace.getText() + "<br>" + missed.getText() + "</html>");
				completed = false;
			}
		}
		
		if(lifeCount == 0) {
			String input = JOptionPane.showInputDialog("Game Over! Would you like to play again? (yes/no)");
			if(input.equalsIgnoreCase("yes")) {
				if(words.size() != 0) {
					for(int i = 0; i < words.size(); i++) {
						words.pop();
					}
				}
				String newInput = JOptionPane.showInputDialog("Enter the number of words to guess (1-100): ");
				int wordCount = Integer.parseInt(newInput);
				for(int i = 0; i < wordCount; i++) {
					String randWord = Utilities.readRandomLineFromFile("dictionary.txt");
					System.out.println(randWord);
					if(words.contains(randWord) == false) {
						words.push(randWord);
					}
				}
				currentWord = words.pop();
				System.out.println(currentWord);
				wordSpaceText = createWordSpace(currentWord);
				wordChars = wordSpaceText.toCharArray();
				wordSpace.setText(wordSpaceText);
				missed.setText(missed.getText().substring(0,18));
				lifeCount = 10;
				label.setText("<html> Lives: " + lifeCount + wordSpace.getText() + "<br>" + missed.getText() + "</html>");
			}
			else if(input.equalsIgnoreCase("no")) {
				System.exit(0);
			}
		}
		found = false;
	}
	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
