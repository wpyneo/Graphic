package covering;

import java.awt.Point;

public class Line3D {

	private Point3D sp;
	private Point3D ep;

	public Line3D(Point3D sp, Point3D ep) {

		// make sure points get higher Y value is the Start Point of a line3D
		if (sp.getY() > ep.getY()) {
			this.sp = sp;
			this.ep = ep;
		} else {
			this.sp = ep;
			this.ep = sp;
		}
	}

	// this is to check if this line is the same as the given Line3D
	public boolean checkSame(Line3D l3d) {

		if (sp.checkSame(l3d.getSp()) || ep.checkSame(l3d.getEp())) {
			return true;
		}

		return false;
	}

	public Point3D getSp() {
		return sp;
	}

	public Point3D getEp() {
		return ep;
	}
}
