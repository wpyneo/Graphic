package edgeCutting;

import java.awt.Point;

import transformation.Coordinate2D;

public class Line {

	private Point sp = null;
	private Point ep = null;
	private boolean cutStatus = false;
	private int lineNumber = 0;

	// this is to define the edge of the shape
	public Line(int lineNumber, Point s, Point e) {

		this.sp = s;
		this.ep = e;
		setLineNumber(lineNumber);
	}

	public Line(Point s, Point e) {
		this.sp = s;
		this.ep = e;
	}

	public Line(Coordinate2D s, Coordinate2D e) {
		this.sp = new Point(s.getX(), s.getY());
		this.ep = new Point(e.getX(), e.getY());
	}

	public Line(Line line) {
		this.sp = line.getStartPoint();
		this.ep = line.getEndPoint();
	}

	// to get the start point of a line
	public Point getStartPoint() {
		return sp;
	}

	// to get the end point of a line
	public Point getEndPoint() {
		return ep;
	}

	// to get the gradient of a line
	public double getGradient() {

		if (sp.getX() != ep.getX()) {
			return (sp.getY() - ep.getY()) / (sp.getX() - ep.getX());
		}
		return 999999999;
	}

	public int getB() {

		return (int) ((ep.getX() * sp.getY() - sp.getX() * ep.getY()) / (ep
				.getX() - sp.getX()));

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

		if (line.getStartPoint().getX() == sp.getX()
				&& line.getStartPoint().getY() == sp.getY()
				&& line.getEndPoint().getX() == ep.getX()
				&& line.getEndPoint().getY() == ep.getY()
				&& line.getGradient() == getGradient()) {

			return true;
		}

		return false;
	}

	public String toString() {
		return "SP is " + sp.getX() + " " + sp.getX() + " EP is " + ep.getX()
				+ " " + ep.getY();
	}
}
