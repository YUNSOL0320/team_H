package game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListener extends KeyAdapter {

	@Override
	public void keyPressed(KeyEvent e) {
		
		switch(e.getKeyCode()) {
		case KeyEvent.VK_ESCAPE:
			System.exit(0);
			break;
		}
		
		if(MainFrame.play != null) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_S:
				MainFrame.play.pressS();
				break;
			case KeyEvent.VK_D:
				MainFrame.play.pressD();
				break;
			case KeyEvent.VK_F:
				MainFrame.play.pressF();
				break;
			case KeyEvent.VK_SPACE:
				MainFrame.play.pressSpace();
				break;
			case KeyEvent.VK_J:
				MainFrame.play.pressJ();
				break;
			case KeyEvent.VK_K:
				MainFrame.play.pressK();
				break;
			case KeyEvent.VK_L:
				MainFrame.play.pressL();
				break;
				
			}
			
		}
		
		
	}
	
	@Override
	public void keyReleased(KeyEvent e) {
		
		if(MainFrame.play != null) {
			switch(e.getKeyCode()) {
			case KeyEvent.VK_S:
				MainFrame.play.releaseS();
				break;
			case KeyEvent.VK_D:
				MainFrame.play.releaseD();
				break;
			case KeyEvent.VK_F:
				MainFrame.play.releaseF();
				break;
			case KeyEvent.VK_SPACE:
				MainFrame.play.releaseSpace();
				break;
			case KeyEvent.VK_J:
				MainFrame.play.releaseJ();
				break;
			case KeyEvent.VK_K:
				MainFrame.play.releaseK();
				break;
			case KeyEvent.VK_L:
				MainFrame.play.releaseL();
				break;
			}
		}
		
	}
	
}