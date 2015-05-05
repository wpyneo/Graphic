package covering;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import transformation.Coordinate3D;
import edgeCutting.Line;

public class CreateShapesOf3D {

	private ArrayList<Line3D> lineList3D = new ArrayList<Line3D>();
	private ArrayList<Surface> surfaceList3D = new ArrayList<Surface>();

	// surfaceList3D without duplicate shape
	private ArrayList<Surface> realSurfaceList = new ArrayList<Surface>();

	private Shape3D finalShape3D;

	private Dimension d;

	// this is to fill in lineList3D of a shape
	public CreateShapesOf3D(ArrayList<Coordinate3D> coodList3D, Dimension d) {

		fillLineList3D(coodList3D);
		this.d = d;

		createSurface(lineList3D);

		finalShape3D = formShape3D(surfaceList3D);
	}

	// form the coodList3D into line list
	public void fillLineList3D(ArrayList<Coordinate3D> coodList3D) {

		System.out.println(coodList3D.size());

		for (int i = 0; i < coodList3D.size(); i++) {
			for (int j = i; j < coodList3D.size(); j++) {

				Point3D sp = new Point3D(coodList3D.get(i).getX(), coodList3D
						.get(i).getY(), coodList3D.get(i).getZ());

				Point3D ep = new Point3D(coodList3D.get(j).getX(), coodList3D
						.get(j).getY(), coodList3D.get(j).getZ());

//				System.out.println(coodList3D.get(i).getX() + " "
//						+ coodList3D.get(i).getY() + " "
//						+ coodList3D.get(i).getZ());
//				System.out.println(coodList3D.get(j).getX() + " "
//						+ coodList3D.get(j).getY() + " "
//						+ coodList3D.get(j).getZ());

				if (sp.checkSame(ep) == false) {
					// make sure not to form a line with same Point

					// create a new line3D
					Line3D l3d = new Line3D(sp, ep);

					if (lineList3D.size() > 0) {
						// make sure lineList3D is not empty

						// flag for checking if new line3D is already exist in
						// lineList3D
						boolean flag = false;

						for (int k = 0; k < lineList3D.size(); k++) {

							if (l3d.checkSame(lineList3D.get(k))) {
								// if line3D exist, then set flag = true

								flag = true;
							}
						}

						if (flag != true) {
							// if line3D doesn't exist, then add new line3D
							lineList3D.add(l3d);
						}
					} else if (lineList3D.size() == 0) {

						// if lineList3D is empty, then add the new line3D
						lineList3D.add(l3d);
						System.out.println("Add first line.");
					} else {
						System.out.println("No line added. Strange");
					}
				} else {
					System.out.println("Point are same. move on!");
				}
			}
		}

		System.out.println(lineList3D.size());
	}

	// this is to create surfaces of a shape
	public void createSurface(ArrayList<Line3D> lineList3D) {

		for (int i = 0; i < lineList3D.size(); i++) {
			for (int j = i; j < lineList3D.size(); j++) {
				for (int k = j; k < lineList3D.size(); k++) {

					Line3D line1 = lineList3D.get(i);
					Line3D line2 = lineList3D.get(j);
					Line3D line3 = lineList3D.get(k);

					// this is to identify if 3 line3D are able to for a
					// surface3D
					boolean flag = false;

					if (!line1.checkSame(line2) && !line2.checkSame(line3)
							&& !line1.checkSame(line3)) {
						// make sure all 3 lines are different

						if ((line1.getSp().checkSame(line2.getSp()))
								&& (line1.getEp().checkSame(line3.getSp()))
								&& (line2.getEp().checkSame(line3.getEp()))) {
							// 1 s = 2 s && 1 e = 3 s && 2 e = 3 e

							flag = true;

						} else if ((line1.getSp().checkSame(line2.getSp()))
								&& (line1.getEp().checkSame(line3.getSp()))
								&& (line2.getEp().checkSame(line3.getEp()))) {
							// 1 s = 2 s && 1 e = 3 e && 2 e = 3 s

							flag = true;

						} else if ((line1.getSp().checkSame(line2.getSp()))
								&& (line1.getEp().checkSame(line3.getSp()))
								&& (line2.getEp().checkSame(line3.getEp()))) {
							// 1 s = 2 e && 1 e = 3 s && 2 s = 3 e

							flag = true;

						} else if ((line1.getSp().checkSame(line2.getSp()))
								&& (line1.getEp().checkSame(line3.getSp()))
								&& (line2.getEp().checkSame(line3.getEp()))) {
							// 1 s = 2 e && 1 e = 3 e && 2 s = 3 s

							flag = true;
						}

						if (flag) {

							// form a surface with 3 lines
							ArrayList<Line3D> surfaceLine3D = new ArrayList<Line3D>();
							surfaceLine3D.add(line1);
							surfaceLine3D.add(line2);
							surfaceLine3D.add(line3);

							surfaceList3D.add(new Surface(surfaceLine3D, d));
						}
					}
				}
			}
		}
	}

	// this is to form a shape3D by removing the duplicated surface in
	// SurfaceList3D. If new version of createSurface works then this method can
	// be removed.
	public Shape3D formShape3D(ArrayList<Surface> surfaceList3D) {

		// add the first of the surface into surface list;
		realSurfaceList.add(surfaceList3D.get(0));

		for (int i = 0; i < surfaceList3D.size(); i++) {
			for (int j = 0; j < realSurfaceList.size(); j++) {

				if (!surfaceList3D.get(i).checkSame(realSurfaceList.get(j))) {
					// if a surface is not in the RealSurfaceList, then add it
					// into the list
					realSurfaceList.add(surfaceList3D.get(i));

				}
			}
		}

		return new Shape3D(realSurfaceList);
	}

	public Shape3D getFinalShape3D() {
		return finalShape3D;
	}

	public ArrayList<Surface> getSurfaceList3D() {
		return surfaceList3D;
	}

	public ArrayList<Surface> getRealSurfaceList() {
		return realSurfaceList;
	}

	public ArrayList<Line3D> getLineList3D() {
		return lineList3D;
	}

}
