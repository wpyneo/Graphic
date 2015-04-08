package covering;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import transformation.Coordinate3D;
import edgeCutting.Line;

public class CreateShapesOf3D {

	private ArrayList<Line3D> lineList3D = new ArrayList<Line3D>();
	private ArrayList<Shape> shappList3D = new ArrayList<Shape>();

	private Canvas c;
	private Graphics g;
	private Dimension d;

	// this is to fill in lineList3D of a shape
	public CreateShapesOf3D(ArrayList<Coordinate3D> coodList3D, Canvas c,
			Dimension d) {

		fillLineList3D(coodList3D);
		this.c = c;
		this.g = c.getGraphics();
		this.d = d;

	}

	// form the coodList3D into line list
	public void fillLineList3D(ArrayList<Coordinate3D> coodList3D) {

		for (int i = 0; i < coodList3D.size(); i++) {
			for (int j = 0; j < coodList3D.size(); j++) {

				Point3D sp = new Point3D(coodList3D.get(i).getX(), coodList3D
						.get(i).getY(), coodList3D.get(i).getZ());

				Point3D ep = new Point3D(coodList3D.get(j).getX(), coodList3D
						.get(j).getY(), coodList3D.get(j).getZ());

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
					} else {
						// if lineList3D is empty, then add the new line3D
						lineList3D.add(l3d);
					}
				}
			}
		}
	}

	// this is to create surfaces of a shape
	public void createShape(ArrayList<Line3D> lineList3D) {

		for (int i = 0; i < lineList3D.size(); i++) {
			for (int j = 0; j < lineList3D.size(); j++) {
				for (int k = 0; k < lineList3D.size(); k++) {

					Line3D line1 = lineList3D.get(i);
					Line3D line2 = lineList3D.get(j);
					Line3D line3 = lineList3D.get(k);

					if (line1.checkSame(line2) || line2.checkSame(line3)
							|| line1.checkSame(line3)) {
						// make sure all 3 lines are different

						if ((line1.getSp().checkSame(line2.getSp()))
								|| (line1.getEp().checkSame(line3.getSp()))
								|| (line2.getEp().checkSame(line3.getEp()))) {
							// 1 s = 2 s || 1 e = 3 s || 2 e = 3 e

							// add new shape

						} else if ((line1.getSp().checkSame(line2.getSp()))
								|| (line1.getEp().checkSame(line3.getSp()))
								|| (line2.getEp().checkSame(line3.getEp()))) {
							// 1 s = 2 s || 1 e = 3 e || 2 e = 3 s

							// add new shape

						} else if ((line1.getSp().checkSame(line2.getSp()))
								|| (line1.getEp().checkSame(line3.getSp()))
								|| (line2.getEp().checkSame(line3.getEp()))) {
							// 1 s = 2 e || 1 e = 3 s || 2 s = 3 e

							// add new shape

						} else if ((line1.getSp().checkSame(line2.getSp()))
								|| (line1.getEp().checkSame(line3.getSp()))
								|| (line2.getEp().checkSame(line3.getEp()))) {
							// 1 s = 2 e || 1 e = 3 e || 2 s = 3 s

							// add new shape
						}
					}
				}
			}
		}
	}
}
