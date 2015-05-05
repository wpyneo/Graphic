package covering;

import java.awt.Point;

public class Line3D {

	private Point3D sp;
	private Point3D ep;
	private int lineNumber;
	private int depth;

	public Line3D(Point3D sp, Point3D ep) {

		// make sure points get higher Y value is the Start Point of a line3D
		if (sp.getY() > ep.getY()) {
			this.sp = sp;
			this.ep = ep;
		} else {
			this.sp = ep;
			this.ep = sp;
		}

		calcualteAverageLineDepth();
	}

	// this is to check if this line is the same as the given Line3D
	public boolean checkSame(Line3D l3d) {

		if (sp.checkSame(l3d.getSp()) && ep.checkSame(l3d.getEp())) {
			return true;
		}

		return false;
	}

	// this is to calculate the average z value of a line

	public void calcualteAverageLineDepth() {

		int spz = sp.getZ();
		int epz = ep.getZ();
		depth = (spz + epz) / 2;
		System.out.println("line depth is " + depth);

	}

	public Point3D getSp() {
		return sp;
	}

	public Point3D getEp() {
		return ep;
	}

	public void setLineNumber(int i) {
		lineNumber = i;
	}

	public int getLineDepth() {
		return depth;
	}

	public String toString() {
		// return "SP is " + sp.toString() + " EP is " + ep.toString();
		return "line " + lineNumber;
	}

	public String printID() {
		return "SP is " + sp.toString() + " EP is " + ep.toString();
		// return "line " + lineNumber;
	}

}
