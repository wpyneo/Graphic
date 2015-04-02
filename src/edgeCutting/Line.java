package edgeCutting;

import java.awt.Point;

public class Line {

	private Point pA = null;
	private Point pB = null;
	private boolean cutStatus = false;
	private int lineNumber = 0;

	// this is to define the edge of the shape
	public Line(int lineNumber, Point s, Point e) {

		this.pA = s;
		this.pB = e;
		setLineNumber(lineNumber);
	}

	// this is to defind the edge of the window
	public Line(Point s, Point e) {
		this.pA = s;
		this.pB = e;
	}

	public Line(Line line) {
		this.pA = line.getStartPoint();
		this.pB = line.getEndPoint();
	}

	// to get the start point of a line
	public Point getStartPoint() {
		return pA;
	}

	// to get the end point of a line
	public Point getEndPoint() {
		return pB;
	}

	// to get the gradient of a line
	public double getGradient() {

		if (pA.getX() != pB.getX()) {
			return (pA.getY() - pB.getY()) / (pA.getX() - pB.getX());
		}
		return 999999999;
	}

	public int getB() {

		return (int) ((pB.getX() * pA.getY() - pA.getX() * pB.getY()) / (pB
				.getX() - pA.getX()));

	}

	// this is to calculate X value of a point based on Y value
	public int calculateX(int y) {
		return (int) ((y - getB()) / getGradient());
	}

	// this is to calculate Y value of a point based on X value
	public int calculateY(int x) {
		return (int) (x * getGradient() + getB());
	}

	// this is to set the number of a line in a shape
	public void setLineNumber(int number) {
		lineNumber = number;
	}

	public int getLineNumber() {
		return lineNumber;
	}

	// this is to set if this line is cutting the edge
	public void setCutStatus(boolean cut) {
		cutStatus = cut;
	}

	public boolean getCutStatus() {
		return cutStatus;
	}

	// this is to test if input line is the same as myself.
	public boolean CompareLine(Line line) {

		if (line.getStartPoint().getX() == pA.getX()
				&& line.getStartPoint().getY() == pA.getY()
				&& line.getEndPoint().getX() == pB.getX()
				&& line.getEndPoint().getY() == pB.getY()
				&& line.getGradient() == getGradient()) {

			return true;
		}

		return false;
	}
}
