package covering;

import java.awt.Canvas;
import java.awt.Graphics;
import java.util.ArrayList;

public class Shape3D {

	private ArrayList<Surface> surfaceList3D = new ArrayList<Surface>();

	public Shape3D(ArrayList<Surface> surfaceList3D) {
		this.surfaceList3D = surfaceList3D;
		sortSurfaceDepth();
	}

	// this will sort the order of surface by its depth by asending order
	public void sortSurfaceDepth() {

		Object[] sfl = surfaceList3D.toArray();

		for (int i = 0; i < sfl.length; i++) {
			for (int j = 0; j < sfl.length; j++) {

				if (((Surface) sfl[i]).getSurfaceDepth() < ((Surface) sfl[j])
						.getSurfaceDepth()) {

					Surface temp = (Surface) sfl[i];
					sfl[i] = (Surface) sfl[j];
					sfl[j] = temp;
				}
			}
		}

		for (int i = 0; i < sfl.length; i++) {
			System.out.println(((Surface)sfl[i]).getSurfaceDepth());
		}
	}
}
