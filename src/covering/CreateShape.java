package covering;

import java.util.ArrayList;

import transformation.Coordinate3D;
import edgeCutting.Line;

public class CreateShape {

	private ArrayList<Line3D> lineList3D = new ArrayList<Line3D>();

	public CreateShape(ArrayList<Coordinate3D> coodList3D) {

		// form the coodList3D into line list

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
}
