package transformation;

import java.awt.Canvas;
import java.util.ArrayList;

public class TransformShape {

	public static ArrayList shift(Canvas c, ArrayList coodList3D,
			double[] changeValue) {

		// transfer arrarylist into array, which is holding all Coordinate3D
		Object[] coodList3DArray = coodList3D.toArray();

		// create new arraylist to store all shifted coordinate2D
		ArrayList<Coordinate3D> shiftCoodList3D = new ArrayList<Coordinate3D>();
		ArrayList<Coordinate2D> shiftCoodList2D = new ArrayList<Coordinate2D>();

		// perform shift for each coordinate3d stored in CoodList3D
		for (int i = 0; i < coodList3DArray.length; i++) {

			// shift each coodinate3d based on the given changeValue,
			// then change Coordinate3D into Coordinate2D, then add
			// into shiftCoodList
			shiftCoodList2D
					.add((Matrix.shift(changeValue,
							(Coordinate3D) coodList3DArray[i]))
							.calculate2DCoordinate());

			shiftCoodList3D.add((Matrix.shift(changeValue,
					(Coordinate3D) coodList3DArray[i])));
		}

		// draw new shape
		DrawShape.Draw(c, shiftCoodList2D);
		return shiftCoodList3D;
	}

	public static ArrayList rotate(Canvas c, ArrayList coodList3D,
			double[] changeValue) {

		// transfer arrarylist into array, which is holding all Coordinate3D
		Object[] coodList3DArray = coodList3D.toArray();

		// create new arraylist to store all shifted coordinate2D
		ArrayList<Coordinate3D> rotateCoodList3D = new ArrayList<Coordinate3D>();
		ArrayList<Coordinate2D> rotateCoodList2D = new ArrayList<Coordinate2D>();

		// perform shift for each coordinate3d stored in CoodList3D
		for (int i = 0; i < coodList3DArray.length; i++) {

			// rotate each coodinate3d based on the given changeValue,
			// then change Coordinate3D into Coordinate2D, then add
			// into shiftCoodList
			rotateCoodList2D
					.add((Matrix.rotate(changeValue,
							(Coordinate3D) coodList3DArray[i]))
							.calculate2DCoordinate());

			rotateCoodList3D.add((Matrix.rotate(changeValue,
					(Coordinate3D) coodList3DArray[i])));
		}

		// draw new shape
		DrawShape.Draw(c, rotateCoodList2D);
		return rotateCoodList3D;

	}

	public static ArrayList zoom(Canvas c, ArrayList coodList3D,
			double[] changeValue) {

		// transfer arrarylist into array, which is holding all Coordinate3D
		Object[] coodList3DArray = coodList3D.toArray();

		// create new arraylist to store all zoomed coordinate2D
		ArrayList<Coordinate3D> zoomCoodList3D = new ArrayList<Coordinate3D>();
		ArrayList<Coordinate2D> zoomCoodList2D = new ArrayList<Coordinate2D>();

		for (int i = 0; i < coodList3DArray.length; i++) {

			// zoom each coodinate3d based on the given changeValue,
			// then change Coordinate3D into Coordinate2D, then add
			// into shiftCoodList
			zoomCoodList2D
					.add((Matrix.zoom(changeValue,
							(Coordinate3D) coodList3DArray[i]))
							.calculate2DCoordinate());

			zoomCoodList3D.add((Matrix.zoom(changeValue,
					(Coordinate3D) coodList3DArray[i])));
		}

		// draw new shape
		DrawShape.Draw(c, zoomCoodList2D);
		return zoomCoodList3D;

	}

	// public static Object[] arrayListChange(ArrayList al) {
	// Object[] coodList3dArray = al.toArray();
	//
	// return coodList3dArray;
	//
	// }
}
