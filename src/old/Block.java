package old;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

public class Block {

	Graphics2D originalG = null;
	Graphics2D leftG = null;
	Graphics2D middleG = null;
	Graphics2D rightG = null;

	public void originalBlock(Graphics gd) {
		originalG = (Graphics2D) gd;

		//draw original block
		originalG.drawRect(100, 100, 100, 100);
		originalG.drawRect(140, 80, 100, 100);
		originalG.drawLine(100, 100, 140, 80);
		originalG.drawLine(200, 100, 240, 80);
		originalG.drawLine(100, 200, 140, 180);
		originalG.drawLine(200, 200, 240, 180);
		
		//draw the available click area
		originalG.drawRect(0, 249, 800, 300);
	}

	// get the new input point and draw the new block
	// fp is front point, which is the mouse click point
	// bp is back point, which is the back panel point calculated based on front
	// point
	public void getBlock(Point fp, Point bp, Graphics gd) {
		leftG = (Graphics2D) gd;
		//clear the background and draw the available click area
		leftG.clearRect(0, 201, 1024, 800);
		leftG.drawRect(0, 249, 800, 300);
		
		//draw new block
		leftG.drawRect((int) fp.getX() - 50, (int) fp.getY() - 50, 100, 100);
		leftG.drawRect((int) bp.getX() - 40, (int) bp.getY() - 40, 80, 80);
		leftG.drawLine((int) fp.getX() - 50, (int) fp.getY() - 50, (int) bp.getX() - 40, (int) bp.getY() - 40);
		leftG.drawLine((int) fp.getX() + 50, (int) fp.getY() + 50, (int) bp.getX() + 40, (int) bp.getY() + 40);
		leftG.drawLine((int) fp.getX() - 50, (int) fp.getY() + 50, (int) bp.getX() - 40, (int) bp.getY() + 40);
		leftG.drawLine((int) fp.getX() + 50, (int) fp.getY() - 50, (int) bp.getX() + 40, (int) bp.getY() - 40);

	}

}
