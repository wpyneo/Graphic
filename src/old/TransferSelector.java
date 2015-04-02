package old;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.Shape;

public class TransferSelector {

	private Rectangle r; // create a rectangle for current use only as I only
							// use rectangle for this assignment
	private Point p; // this is to get the upper left coordinate of the input
						// shape (rectangle)
	private Point newP; // this is to define the upper left coordinate of the
						// new shape
	private Dimension d; // this is to get the size of input shape

	private Rectangle newR; // this is to draw the new rectangle based on the
							// input action

	public TransferSelector(String s, Shape shape, Canvas c) {
		// this is to decide which action is to be performed (Mirror, Rotation,
		// Zoom)

		r = (Rectangle) shape; // reform the input shape to a rectangle
		p = new Point(r.getLocation()); // get the location of the input
										// shape
		d = r.getSize(); // get the size of input shape

		System.out.println(p.toString()); // output the input shape location
		System.out.println(d.toString());

		if (s.equalsIgnoreCase("Mirror")) {
			// This is to Mirror the original shape against X

			// modify X coordination
			int x = 800 - (int) (p.getX()) - (int) (d.getWidth());
			// Y coordination should remain the same
			int y = (int) (p.getY());
			// new shape starting point
			newP = new Point(x, y);

			// based on the new coordination and width and height, create new
			// Rectangle
			newR = new Rectangle(x, y, (int) (d.getWidth()),
					(int) (d.getHeight()));
			// draw the new Rectangle
			Graphics2D g = (Graphics2D) c.getGraphics();
			g.draw(newR);
			g.fillRect(x, y, (int)(0.5*d.getWidth()), (int)(d.getHeight()));
			
			System.out.println(newP.toString());

		} else if (s.equalsIgnoreCase("Rotation")) {
			// this is to rotate the original shape again point O

			// Modify x and y coordination
			int x = 800 - (int) (p.getX()) - (int) (d.getWidth());
			int y = 600 - (int) (p.getY()) - (int) (d.getHeight());
			// new shape starting point
			newP = new Point(x, y);
			// based on the new coordination and width and height, create new
			// Rectangle
			newR = new Rectangle(x, y, (int) (d.getWidth()),
					(int) (d.getHeight()));
			// draw the new Rectangle
			Graphics2D g = (Graphics2D) c.getGraphics();
			g.draw(newR);
			g.fillRect(x, y, (int)(0.5*d.getWidth()), (int)(d.getHeight()));
			System.out.println(newP.toString());

		} else if (s.equalsIgnoreCase("Zoom")) {
			// this is to zoom the original shape to 30%
			
			// original start point should be the same
			int x = (int) (p.getX());
			int y = (int) (p.getY());

			newP = new Point(x, y);

			newR = new Rectangle(x, y, (int) (d.getWidth()*0.3),
					(int) (d.getHeight()*0.3));

			Graphics2D g = (Graphics2D) c.getGraphics();
			g.draw(newR);
			g.fillRect(x+(int)(0.5*0.3*d.getWidth()), y, (int)(0.5*0.3*d.getWidth()), (int)(0.3*d.getHeight()));
			System.out.println(newP.toString());
		}
	}

	public Point getPoint() {
		// this is to output the new point.
		return p;
	}

	public Point getNewPoint() {

		return newP;
	}

	public Rectangle getNewRectangle() {
		return newR;
	}

	// this is to perform test.
	// public static void main(String[] args) {
	// new TransferSelector("Mirror", new Rectangle(100, 100, 200, 100))
	// .getNewPoint();
	// }

}
