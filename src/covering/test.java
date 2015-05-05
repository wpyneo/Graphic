package covering;

import java.awt.Dimension;
import java.util.ArrayList;

import transformation.Coordinate3D;

public class test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// draw Shape, only for testing
		ArrayList<Coordinate3D> test3D = new ArrayList<Coordinate3D>();

		test3D.add(new Coordinate3D(100, 200, 300));
		test3D.add(new Coordinate3D(200, 300, 100));
		test3D.add(new Coordinate3D(200, 100, 300));
		test3D.add(new Coordinate3D(300, 100, 200));

		Dimension d = new Dimension(1000, 700);

		CreateShapesOf3D cso3d = new CreateShapesOf3D(test3D, d);

		System.out.println(cso3d.getLineList3D().size() + " lines created");
		System.out.println(cso3d.getSurfaceList3D().size()
				+ " surfaced created");
		System.out.println(cso3d.getRealSurfaceList().size()
				+ " unique surface created");

	}

}
