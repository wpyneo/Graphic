package transformation;

import covering.Point3D;

public class Coordinate3D{

	private int x;
	private int y;
	private int z;
	Coordinate2D c2d = null;

	// public Coordinate3D() {
	//
	// }

	public Coordinate3D(int x, int y, int z) {

		// create 3D coordinate by input

		 this.x = x;
		 this.y = y;
		 this.z = z;

//		super(x, y, z);
		calculate2DCoordinate();
	}

	public Coordinate2D calculate2DCoordinate() {
		// calculate the offset value based on given Z.
		// consider z axis is a -225 degree line in a standard 2D system
		// so the offset should be the square root of (z^2)/2

		if (z > 0) {
			// positive part of Z axis
			int offset = (int) Math.sqrt((z * z) / 2);
			c2d = new Coordinate2D((x - offset), (y - offset));
		} else {
			// negative part of Z axis
			int offset = -(int) Math.sqrt((z * z) / 2);
			c2d = new Coordinate2D((x - offset), (y - offset));
		}
		return c2d;
	}

	public int getX() {
		return (int) x;
	}

	public int getY() {
		return (int) y;
	}

	public int getZ() {
		return (int) z;
	}

	// a projection of a 3D point to a 2D environment.
	public Coordinate2D getCoordinate2D() {
		return c2d;
	}
}
