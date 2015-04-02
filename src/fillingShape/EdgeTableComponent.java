package fillingShape;

import java.awt.Point;

import edgeCutting.Line;

public class EdgeTableComponent extends Line {

	private int yMax = 0;
	private int xCurrent = 0;
	private double delta = 0;
	private Point endPoint = null;
	private boolean grouped = false;

	public EdgeTableComponent(Line line, int yMax, int xCurrent, double delta, Point ep) {
		super(line);
		this.yMax = yMax;
		this.xCurrent = xCurrent;
		this.delta = delta;
		this.endPoint = ep;

	}

	// this is to test if input etc has the same end point as this one.
	public boolean compareEndPoint(EdgeTableComponent etc) {

		if (endPoint.getX() == etc.getEndPoint().getX()
				&& endPoint.getY() == etc.getEndPoint().getY()) {
			return true;
		}

		return false;

	}

	// this is to test if input etc is identical to this one
	public boolean isItself(EdgeTableComponent etc) {

		if (compareEndPoint(etc) == true && yMax == etc.getyMax()
				&& xCurrent == etc.getxCurrent() && delta == etc.getDelta()) {
			return true;
		}

		return false;
	}

	public int getyMax() {
		return yMax;
	}

	public void setyMax(int yMax) {
		this.yMax = yMax;
	}

	public int getxCurrent() {
		return xCurrent;
	}

	public void setxCurrent(int xCurrent) {
		this.xCurrent = xCurrent;
	}

	public double getDelta() {
		return delta;
	}

	public void setDelta(double delta) {
		this.delta = delta;
	}

	public Point getEndPoint() {
		return endPoint;
	}

	// this will be mark true if this etc is already grouped
	public boolean isGrouped() {
		return grouped;
	}

	public void setGrouped(boolean grouped) {
		this.grouped = grouped;
	}

}
