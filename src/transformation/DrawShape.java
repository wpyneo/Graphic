package transformation;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;

import covering.CreateShapesOf3D;
import covering.Shape3D;

public class DrawShape {

	private static Object[] pointList;

	public static void Draw(Canvas c, ArrayList<Coordinate2D> coodList2D,
			ArrayList<Coordinate3D> coodList3D) {

		Graphics2D g = (Graphics2D) c.getGraphics();

		// retrieve points collected from input
		pointList = coodList2D.toArray();

		// clear original shape
		g.clearRect(0, 0, 1280, 800);
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

	public static void DrawWithCover(Canvas c,
			ArrayList<Coordinate2D> coodList2D,
			ArrayList<Coordinate3D> coodList3D, Dimension d) {

		Graphics2D g = (Graphics2D) c.getGraphics();

		// retrieve points collected from input
		pointList = coodList2D.toArray();


		// clear original shape and restore coordinate System
		g.clearRect(0, 0, 1280, 800);
		new CoordinateSystem(g);

		// draw new shape with inputed CoodList3D

		Shape3D s3d = new CreateShapesOf3D(coodList3D, d, g).getFinalShape3D();
		
		s3d.fillSurfaceByDepth();

	}
	
}
