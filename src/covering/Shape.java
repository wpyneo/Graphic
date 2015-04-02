package covering;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.geom.Path2D;
import java.util.ArrayList;

import edgeCutting.Line;
import fillingShape.CoordinateTransformer;
import fillingShape.FillShape;

public class Shape {

	private ArrayList<Line> shapeLines = new ArrayList<Line>();
	private int layerNumber;
	private Canvas c;
	private Graphics g;
	// private Path2D.Double path2d = new Path2D.Double();
	private Dimension d;

	public Shape(ArrayList<Line> shapeLines, int layer, Canvas c, Dimension d) {

		this.shapeLines = shapeLines;
		layerNumber = layer;
		this.c = c;
		g = c.getGraphics();
		this.d = d;
	}

	public void drawShape() {

		for (int i = 0; i < shapeLines.size(); i++) {

			g.setColor(Color.RED);

			drawLine(g, shapeLines.get(i).getStartPoint(), shapeLines.get(i)
					.getEndPoint());

			// path2d.moveTo(shapeLines.get(i).getStartPoint().getX(),
			// shapeLines
			// .get(i).getStartPoint().getY());
			// path2d.lineTo(shapeLines.get(i).getEndPoint().getX(), shapeLines
			// .get(i).getEndPoint().getY());

			System.out.println("Draw Line " + (i + 1));
		}
		g.setColor(Color.RED);
		FillShape fs = new FillShape(shapeLines, d, 1, g);
	}

	// this is to check if the given point is within the shape
	public boolean checkWithIn(Point p) {

		ArrayList<Line> crossLines = checkCrossLines(p);

		if (crossLines.size() != 0) {

			for (int i = 0; i < crossLines.size() - 1; i++) {

				// calculate the x value of each line
				int x = crossLines.get(i).calculateX((int) p.getY());
				int y = crossLines.get(i + 1).calculateX((int) p.getY());

				// if given point x value is below both edges, then it is within
				// the shape
				if ((x < p.getX() && y > p.getX())
						|| (x > p.getX() && y < p.getX())) {
					return true;
				}
			}
		}

		return false;
	}

	// this is to check if the given point Y value is crossing any of the edges,
	// if yes, provide the edges lines
	public ArrayList<Line> checkCrossLines(Point p) {

		ArrayList<Line> crossLines = new ArrayList<Line>();

		for (int i = 0; i < shapeLines.size(); i++) {

			if ((shapeLines.get(i).getStartPoint().getY() > p.getY() && shapeLines
					.get(i).getEndPoint().getY() < p.getY())
					|| (shapeLines.get(i).getEndPoint().getY() > p.getY() && shapeLines
							.get(i).getStartPoint().getY() < p.getY())) {

				crossLines.add(shapeLines.get(i));

				System.out.println(shapeLines.get(i).getStartPoint() + " "
						+ shapeLines.get(i).getEndPoint());
			}
		}

		return crossLines;
	}

	// this is to re-draw the moved shape with a giving x and y
	public void moveShape(int xMoved, int yMoved, Graphics g) {

		for (int i = 0; i < shapeLines.size(); i++) {
			Point newStartPoint = new Point((int) (shapeLines.get(i)
					.getStartPoint().getX() + xMoved), (int) (shapeLines.get(i)
					.getStartPoint().getY() + yMoved));

			Point newEndPoint = new Point((int) (shapeLines.get(i)
					.getEndPoint().getX() + xMoved), (int) (shapeLines.get(i)
					.getEndPoint().getY() + yMoved));

			shapeLines.set(i, new Line(newStartPoint, newEndPoint));

			drawLine(g, shapeLines.get(i).getStartPoint(), shapeLines.get(i)
					.getEndPoint());
		}

	}

	// this is to draw a line by given start point and end point
	public void drawLine(Graphics g, Point sp, Point ep) {
		g.drawLine(CoordinateTransformer.calculateFrameX(d, (int) sp.getX()),
				CoordinateTransformer.calculateFrameY(d, (int) sp.getY()),
				CoordinateTransformer.calculateFrameX(d, (int) ep.getX()),
				CoordinateTransformer.calculateFrameY(d, (int) ep.getY()));

	}

}
