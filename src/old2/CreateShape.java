package old2;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.awt.Rectangle;

public class CreateShape {
	private Graphics2D g = null;
	private Rectangle rec = null;

	public CreateShape(String type, Canvas c) {

		// create a shape, as of now, a rectangle
		if (type.equalsIgnoreCase("shape")) {

			rec = new Rectangle(100, 100, 200, 100);
			g = (Graphics2D) c.getGraphics();
			g.fillRect(200, 100, 100, 100);
			g.draw(rec);
		}
	}

	public Rectangle getRectangle() {
		return rec;
	}

}
