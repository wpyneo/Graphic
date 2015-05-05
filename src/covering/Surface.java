package covering;

import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;

public class Surface {

	private ArrayList<Line3D> line3DList = new ArrayList<Line3D>();
	private int layerNumber;
	private Color c;
	// private Path2D.Double path2d = new Path2D.Double();
	private Dimension d;
	private int depth;

	public Surface(ArrayList<Line3D> surfaceLines3D, Dimension d) {

		this.line3DList = surfaceLines3D;
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

	// this is to check if this surface is the same as the given one

	public boolean checkSame(Surface sf) {

		int sameLine3D = 0;

		// count the same Line3D
		for (int i = 0; i < line3DList.size(); i++) {
			for (int j = 0; j < line3DList.size(); j++) {
				if (line3DList.get(i).checkSame(sf.getSurfaceLines3D().get(j))) {

					sameLine3D++;
				}

			}
		}
		// if 2 surface share all the edge (Line3D) then they are the same.
		if (sameLine3D == line3DList.size()) {
			return true;
		} else {
			return false;
		}
	}

	// this is to calculate the average z value of a shape
	public void calcualteAverageSurfaceDepth() {

		int surfaceDepth = 0;
		for (int i = 0; i < line3DList.size(); i++) {
			surfaceDepth += (line3DList.get(i).getLineDepth());
		}
		System.out.println("Surface depth is " + surfaceDepth);

		depth = surfaceDepth / line3DList.size();

	}

	public ArrayList<Line3D> getSurfaceLines3D() {

		return line3DList;
	}

	public Color getC() {
		return c;
	}

	public int getSurfaceDepth() {
		return depth;
	}

}
