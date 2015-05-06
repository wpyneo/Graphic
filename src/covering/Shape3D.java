package covering;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import fillingShape.FillShape;

public class Shape3D {

	private ArrayList<Surface> surfaceList3D = new ArrayList<Surface>();
	private Object[] sfl;
	private Graphics g;

	public Shape3D(ArrayList<Surface> surfaceList3D, Graphics g) {
		this.surfaceList3D = surfaceList3D;
		this.g = g;
		sortSurfaceDepth();
	}

	// this will sort the order of surface by its depth by asending order
	public void sortSurfaceDepth() {

		sfl = surfaceList3D.toArray();

		for (int i = 0; i < sfl.length; i++) {
			for (int j = 0; j < sfl.length; j++) {

				if (((Surface) sfl[i]).getSurfaceDepth() > ((Surface) sfl[j])
						.getSurfaceDepth()) {

					Surface temp = (Surface) sfl[i];
					sfl[i] = (Surface) sfl[j];
					sfl[j] = temp;
				}
			}
		}
	}

	public void fillSurfaceByDepth() {

		for (int i = 0; i < sfl.length; i++) {
			System.out.println(((Surface) sfl[i]).getSurfaceDepth());
			// System.out.println(((Surface)sfl[i]).getC());
			Color c = ((Surface) sfl[i]).getC();

			g.setColor(c);
			// fill color to shape

			FillShape fs = new FillShape(
					((Surface) sfl[i]).getSurfaceLines2D(),
					((Surface) sfl[i]).getD(), 1, g);
		}

	}
}
