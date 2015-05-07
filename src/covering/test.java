package covering;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JFrame;

import transformation.Coordinate3D;
import transformation.CoordinateSystem;

public class test {

	private CreateShapesOf3D cso3d;
	private ArrayList<Coordinate3D> test3D;
	private Dimension d;

	JFrame jf = new JFrame();
	Canvas c = new Canvas();
	Graphics g = c.getGraphics();

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		test t = new test();
		t.createFrame();

	}

	public void createFrame() {

		d = new Dimension(1000, 700);

		jf.setSize(d);
		jf.setLayout(new BorderLayout());

		jf.setDefaultCloseOperation(jf.EXIT_ON_CLOSE);
		jf.setLayout(new BorderLayout());
		jf.setVisible(true);

		jf.add(c, BorderLayout.CENTER);

		// draw Shape, only for testing
		test3D = new ArrayList<Coordinate3D>();

		test3D.add(new Coordinate3D(100, 200, 350));
		test3D.add(new Coordinate3D(200, 300, 100));
		test3D.add(new Coordinate3D(200, 100, 300));
		test3D.add(new Coordinate3D(300, 100, 250));

		CreateShapesOf3D cso3d = new CreateShapesOf3D(test3D, d, g);

		Shape3D s3d = cso3d.getFinalShape3D();

		s3d.fillSurfaceByDepth();

		System.out.println(cso3d.getLineList3D().size() + " lines created");
		System.out.println(cso3d.getSurfaceList3D().size()
				+ " surfaced created");
	}

	private class C extends Canvas {
		public void paint(Graphics g) {

		}
	}

}
