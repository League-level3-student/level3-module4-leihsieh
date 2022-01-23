package _00_IntroToStacks;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class _02_TextUndoRedo implements KeyListener {
	/* 
	 * Create a JFrame with a JPanel and a JLabel.
	 * 
	 * Every time a key is pressed, add that character to the JLabel. It should
	 * look like a basic text editor.
	 * 
	 * Make it so that every time the BACKSPACE key is pressed, the last
	 * character is erased from the JLabel.
	 * 
	 * Save that deleted character onto a Stack of Characters.
	 * 
	 * Choose a key to be the Undo key. Make it so that when that key is
	 * pressed, the top Character is popped  off the Stack and added back to
	 * the JLabel.
	 */
	JFrame frame = new JFrame();
	JPanel panel = new JPanel();
	JLabel label = new JLabel("Text area: ");
	Stack<Character> deleted = new Stack<Character>();

	public static void main(String[] args) {
		new _02_TextUndoRedo();
	}
	
	_02_TextUndoRedo(){
		panel.add(label);
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addKeyListener(this);
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		String labeltext = label.getText();
		if(e.getKeyCode() == 8) {
			deleted.push(labeltext.charAt(labeltext.length()-1));
			labeltext = labeltext.substring(0, labeltext.length() - 1);
			label.setText(labeltext);
		}
		else if(e.getKeyCode() == 38) {
			char undo = deleted.pop();
			label.setText(labeltext + undo);
		}
		else {
			label.setText(labeltext + e.getKeyChar());
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}
