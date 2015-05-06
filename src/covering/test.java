package covering;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;

import transformation.Coordinate3D;
import transformation.CoordinateSystem;

public class test {

	private static CreateShapesOf3D cso3d;
	private static ArrayList<Coordinate3D> test3D;
	private static Dimension d;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		// draw Shape, only for testing
		test3D = new ArrayList<Coordinate3D>();

		test3D.add(new Coordinate3D(100, 200, 350));
		test3D.add(new Coordinate3D(200, 300, 100));
		test3D.add(new Coordinate3D(200, 100, 300));
		test3D.add(new Coordinate3D(300, 100, 250));

		d = new Dimension(1000, 700);
		
		test t = new test();
		t.createFrame();

		System.out.println(cso3d.getLineList3D().size() + " lines created");
		System.out.println(cso3d.getSurfaceList3D().size()
				+ " surfaced created");

	}

	public void createFrame() {
		JFrame jf = new JFrame();
		Canvas c = new C();

		CreateShapesOf3D cso3d = new CreateShapesOf3D(test3D, d,
				c.getGraphics());

		jf.add(c);
		
		Shape3D s3d = cso3d.getFinalShape3D();
		
		s3d.fillSurfaceByDepth();
	}
	
	private class C extends Canvas {
		public void paint(Graphics g) {


		}
	}

}
