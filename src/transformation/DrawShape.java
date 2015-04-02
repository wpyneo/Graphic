package transformation;

import java.awt.Canvas;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.Iterator;

public class DrawShape {

	private static Object[] pointList;

	public static void Draw(Canvas c, ArrayList coodList2D) {

		Graphics2D g = (Graphics2D) c.getGraphics();

		// retrieve points collected from input
		pointList = coodList2D.toArray();

		// print out each point
		// while (it.hasNext()) {
		// Coordinate2D c2d = it.next();
		// System.out.println(c2d.getX());
		// System.out.println(c2d.getY());
		// }

		g.clearRect(0, 0, 1280, 800);

		// clear original shape
		new CoordinateSystem(g);

		// draw new shape with inputed CoodList2D
		for (int i = 0; i < pointList.length; i++) {
			for (int j = 0; j < pointList.length; j++) {

				g.drawLine(CoordinateTransformer
						.calculateFrameX(((Coordinate2D) pointList[i]).getX()),
						CoordinateTransformer
								.calculateFrameY(((Coordinate2D) pointList[i])
										.getY()), CoordinateTransformer
								.calculateFrameX(((Coordinate2D) pointList[j])
										.getX()), CoordinateTransformer
								.calculateFrameY(((Coordinate2D) pointList[j])
										.getY()));

			}
		}
	}
}
