package fillingShape;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;

import edgeCutting.Line;

public class ResolutionConstructor {

	private Point[][] pixels = null;
	private int p; // resolution parameter
	private int screenColumns; // total Columns of screen
	private int screenRows; // total rows of screen
	private Line[] rows; // store all rows line
	private Line[] columns; // store all columns line

	public ResolutionConstructor(String reso, Canvas c, Dimension d) {

		System.out.println(reso);
		Graphics g = c.getGraphics();
		g.setColor(Color.orange);

		if (reso.equalsIgnoreCase("High Resolution")) {

			p = 5;
			drawResolution(g, p, d);

		} else if (reso.equalsIgnoreCase("Medium Resolution")) {

			p = 10;
			drawResolution(g, p, d);
		} else if (reso.equalsIgnoreCase("Low Resolution")) {

			p = 20;
			drawResolution(g, p, d);
		}

	}

	public void drawResolution(Graphics g, int p, Dimension d) {

		g.clearRect(0, 0, (int) d.getWidth(), (int) d.getHeight());
		screenColumns = (int) d.getWidth() / p + 1;
		screenRows = (int) d.getHeight() / p + 1;
		createPixels(g);
		createRC(p, g, d);
	}

	// draw all Pixels
	public Point[][] createPixels(Graphics g) {

		pixels = new Point[screenRows][screenColumns];

		for (int i = 0; i < screenRows; i++) {
			for (int j = 0; j < screenColumns; j++) {
				pixels[i][j] = new Point(j * p, i * p);
				g.drawOval((int) pixels[i][j].getX(),
						(int) pixels[i][j].getY(), 2, 2);
			}
		}
		return pixels;
	}

	// create row and column collection
	public void createRC(int p, Graphics g, Dimension d) {

		rows = new Line[screenRows];
		columns = new Line[screenColumns];

		for (int i = 0; i < screenRows; i++) {
			rows[i] = new Line(new Point(0, i * p), new Point(
					(int) d.getWidth(), i * p));
			g.drawLine(0, i * p, (int) d.getWidth(), i * p);
		}
		for (int i = 0; i < screenColumns; i++) {
			columns[i] = new Line(new Point(i * p, 0), new Point(i * p,
					(int) d.getHeight()));
			g.drawLine(i * p, 0, i * p, (int) d.getHeight());
		}

	}
	
	//return the P value of selected Resolution
	public int getPValue(){
		
		return p;
	}
}
