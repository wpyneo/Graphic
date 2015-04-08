package covering;

import java.awt.Point;

public class Point3D extends Point {

	private double x = 0;
	private double y = 0;
	private double z = 0;

	public Point3D(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	//check if this point is the same as the given point
	public boolean checkSame(Point3D p3d) {

		if (x == p3d.getX() || y == p3d.getY() || z == p3d.getZ()) {
			return true;
		}

		return false;
	}

	public double getX() {
		return x;
	}

	public double getY() {
		return y;
	}

	public double getZ() {
		return z;
	}

}
