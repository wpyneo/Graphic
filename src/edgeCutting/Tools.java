package edgeCutting;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.Rectangle;
import java.util.ArrayList;

public class Tools {

	private Rectangle windowRectangle = new Rectangle();

	private Line windowEdge;
	private Line shapeEdge;

	private Point JointPoint;

	// setup window edge
	public void setWindowEdge(Point a, Point b) {
		windowEdge = new Line(a, b);
	}

	// setup shape edge
	public void setShapeEdge(Point a, Point b) {
		shapeEdge = new Line(a, b);
	}

	public Point getJointPoint(Line a, Line b) {

		return JointPoint;
	}

	// check for the start point and end point of a line, where are those point
	// located.
	public static AdvancedRectangle checkPointLocation(Point a) {
		// get the area series
		AdvancedRectangle[] ar = DrawFrame.getWindowRectangleSeries();

		// for each area, check if point is within.
		for (int i = 0; i < ar.length; i++) {
			if (ar[i].contains(a) == true) {
				System.out.println(ar[i].toString());
				return ar[i];
			}
		}
		return null;
	}

	// check if line is obviously invisible.
	public static boolean checkLineVisibility(Line a) {

		// this is to get the area for start point and end point of a line
		AdvancedRectangle area1 = checkPointLocation(a.getStartPoint());
		AdvancedRectangle area2 = checkPointLocation(a.getEndPoint());

		boolean visible = false;
		boolean[] result = new boolean[4];

		for (int i = 0; i < 4; i++) {
			// compare the AND result the area code of area1 and area
			result[i] = area1.getAreaCode()[i] && area2.getAreaCode()[i];
			System.out.println("Area 1 is " + area1.getAreaCode()[i]
					+ " Area 2 is " + area2.getAreaCode()[i] + " Result is "
					+ result[i]);
			// setup result
			visible = visible || result[i];
		}
		return !visible;
	}

	public Line getJointPoint(Line a) {
		// this is to get the area for the start point and end point of a line
		AdvancedRectangle area1 = checkPointLocation(a.getStartPoint());
		AdvancedRectangle area2 = checkPointLocation(a.getEndPoint());

		// this is to hold the value of the result which identify if a line is
		// joint with the edge line of the window
		boolean[] result = new boolean[4];

		// this is to hold joint points of a line with the window edge
		ArrayList<Point> jointPointList = new ArrayList<Point>();

		// this is to define the value of edge line
		int valueX = 0;
		int valueY = 0;

		for (int i = 0; i < 4; i++) {
			// compare the AND result the area code of area1 and area
			result[i] = area1.getAreaCode()[i] || area2.getAreaCode()[i];

			System.out.print(result[i] + " ");
		}

		System.out.println(" ");

		if ((result[0] == true)) {
			// joint point with top line

			// this is to calculate the X value and Y value of the joint
			// point
			// Y value is set, needs to calculate X value of the joint point
			valueY = (int) DrawFrame.getWindow().getY();
			valueX = a.calculateX(valueY);

			System.out.println(DrawFrame.getWindow().contains(
					new Point(valueX, valueY)));
			// if this point is within the window, then add to
			// jointPointList Arraylist
			if (DrawFrame.getWindow().contains(new Point(valueX, valueY))) {
				jointPointList.add(new Point(valueX, valueY));
				System.out.println(new Point(valueX, valueY));
			}
		}

		if ((result[1] == true)) {
			// joint point with buttom line

			// this is to calculate the X value and Y value of the joint
			// point
			// Y value is set, needs to calculate X value of the joint point
			valueY = ((int) DrawFrame.getWindow().getMaxY() - 1);
			valueX = a.calculateX(valueY);

			System.out.println(DrawFrame.getWindow().contains(
					new Point(valueX, valueY)));
			// if this point is within the window, then add to
			// jointPointList Arraylist
			if (DrawFrame.getWindow().contains(new Point(valueX, valueY))) {
				jointPointList.add(new Point(valueX, valueY));
				System.out.println(new Point(valueX, valueY));
			}
		}

		if ((result[2] == true)) {
			// joint point with right line

			// this is to calculate the X value and Y value of the joint
			// point
			// X value is set, needs to calculate Y value of the joint point
			valueX = ((int) DrawFrame.getWindow().getMaxX() - 1);
			valueY = a.calculateY(valueX);

			System.out.println(DrawFrame.getWindow().contains(
					new Point(valueX, valueY)));
			// if this point is within the window, then add to
			// jointPointList Arraylist
			if (DrawFrame.getWindow().contains(new Point(valueX, valueY))) {
				jointPointList.add(new Point(valueX, valueY));
				System.out.println(new Point(valueX, valueY));
			}
		}

		if ((result[3] == true)) {
			// joint point with left line

			// this is to calculate the X value and Y value of the joint
			// point
			// X value is set, needs to calculate Y value of the joint point
			valueX = (int) DrawFrame.getWindow().getX();
			valueY = a.calculateY(valueX);

			System.out.println(DrawFrame.getWindow().contains(
					new Point(valueX, valueY)));
			// if this point is within the window, then add to
			// jointPointList Arraylist
			if (DrawFrame.getWindow().contains(new Point(valueX, valueY))) {
				jointPointList.add(new Point(valueX, valueY));
				System.out.println(new Point(valueX, valueY));
			}
		}

		//this is to redraw the new line which is cut by the edge
		if (jointPointList.toArray().length == 0) {
			//if no joint point then line is invisible
			System.out.println("Line is invisible.");
			return null;
		} else if (jointPointList.toArray().length == 1) {
			//if there is only a joint point, then decide which end is inside the window
			if (DrawFrame.getWindow().contains(a.getStartPoint())) {
				//start point is within window
				a = new Line(a.getLineNumber(), a.getStartPoint(),
						(Point) jointPointList.toArray()[0]);
				return a;
			} else {
				//end point is within window
				a = new Line(a.getLineNumber(), a.getEndPoint(),
						(Point) jointPointList.toArray()[0]);
				return a;
			}
		} else if (jointPointList.toArray().length == 2) {
			//if there are 2 joint points, then new line will be formed by those two joint point
			a = new Line(a.getLineNumber(),
					(Point) jointPointList.toArray()[0],
					(Point) jointPointList.toArray()[1]);
			return a;
		}

		return null;

	}
}
