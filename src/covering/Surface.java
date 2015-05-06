package covering;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

import transformation.Coordinate2D;
import transformation.Coordinate3D;
import edgeCutting.Line;

public class Surface {

	private ArrayList<Line3D> lineList3D = new ArrayList<Line3D>();
	private ArrayList<Line> lineList2D = new ArrayList<Line>();

	private Color c;
	// private Path2D.Double path2d = new Path2D.Double();
	private Dimension d;
	private int depth;

	public Surface(ArrayList<Line3D> surfaceLines3D, Dimension d) {

		this.lineList3D = surfaceLines3D;
		// layerNumber = layer;

		// this is to setup random color to fill a shape
		float a = (float) Math.random();
		float b = (float) Math.random();
		float c = (float) Math.random();
		System.out.println(a + " " + b + " " + c);

		this.c = new Color(a, b, c);
		this.d = d;
		calcualteAverageSurfaceDepth();
	}

	// without Dimension Input
	public Surface(ArrayList<Line3D> surfaceLines3D) {

		this.lineList3D = surfaceLines3D;
		// layerNumber = layer;

		// this is to setup random color to fill a shape
		float a = (float) Math.random();
		float b = (float) Math.random();
		float c = (float) Math.random();
		System.out.println(a + " " + b + " " + c);

		this.c = new Color(a, b, c);
		calcualteAverageSurfaceDepth();
		convertLine3DToLine2D();
	}

	// this is to check if this surface is the same as the given one

	public boolean checkSame(Surface sf) {

		int sameLine3D = 0;

		// count the same Line3D
		for (int i = 0; i < lineList3D.size(); i++) {
			for (int j = 0; j < lineList3D.size(); j++) {
				if (lineList3D.get(i).checkSame(sf.getSurfaceLines3D().get(j))) {

					sameLine3D++;
				}

			}
		}
		// if 2 surface share all the edge (Line3D) then they are the same.
		if (sameLine3D == lineList3D.size()) {
			return true;
		} else {
			return false;
		}
	}

	// this is to calculate the average z value of a shape
	public void calcualteAverageSurfaceDepth() {

		int surfaceDepth = 0;
		for (int i = 0; i < lineList3D.size(); i++) {
			surfaceDepth += (lineList3D.get(i).getLineDepth());
		}
		System.out.println("Surface depth is " + surfaceDepth);

		depth = surfaceDepth / lineList3D.size();

	}

	// this is to transform all Line3D to line 2D
	public void convertLine3DToLine2D() {

		for (int i = 0; i < lineList3D.size(); i++) {
			Line3D l3d = lineList3D.get(i);
			Point3D sp3d = l3d.getSp();
			Point3D ep3d = l3d.getEp();

			Coordinate3D spc3d = new Coordinate3D(sp3d.getX(), sp3d.getY(),
					sp3d.getZ());
			Coordinate3D epc3d = new Coordinate3D(ep3d.getX(), ep3d.getY(),
					ep3d.getZ());

			Coordinate2D sp2d = spc3d.calculate2DCoordinate();
			Coordinate2D ep2d = epc3d.calculate2DCoordinate();

			Line l2d = new Line(sp2d, ep2d);

			lineList2D.add(l2d);
			System.out.println(l2d.toString());
		}

	}

	public ArrayList<Line3D> getSurfaceLines3D() {

		return lineList3D;
	}

	public Color getC() {
		return c;
	}

	public int getSurfaceDepth() {
		return depth;
	}

	public Dimension getD() {
		return d;
	}

	public ArrayList<Line> getSurfaceLines2D() {
		return lineList2D;
	}

}
