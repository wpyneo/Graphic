package fillingShape;

import java.awt.Dimension;

public class CoordinateTransformer {

	// // this is to transfer the coordiante in theory to coordinate in frame
	// when
	// // Original Point is in the middle of the frame
	//
	// public static int calculateFrameX(Dimension d, int x) {
	// // this is to transfer the X in theory to coordinate in frame
	// return (((int) d.getWidth() / 2) + x);
	// }
	//
	// public static int calculateFrameY(Dimension d, int y) {
	// // this is to transfer the Y in theory to coordinate in frame
	// return (((int) d.getHeight() / 2) - y);
	// }
	//
	// // this is to transfer the coordiante in frame to coordinate in theory
	// public static int calculateTheoryX(Dimension d, int x) {
	// // this is to transfer the X in frame to coordinate in theory
	// return (x - ((int) d.getWidth() / 2));
	// }
	//
	// public static int calculateTheoryY(Dimension d, int y) {
	// // this is to transfer the Y in frame to coordinate in theory
	// return (((int) d.getHeight() / 2) - y);
	// }

	// this is to transfer the coordiante in theory to coordinate in frame when
	// Original Point is at the lower-left corner

	public static int calculateFrameX(Dimension d, int x) {
		// this is to transfer the X in theory to coordinate in frame
		return (x);
	}

	public static int calculateFrameY(Dimension d, int y) {
		// this is to transfer the Y in theory to coordinate in frame
		return (((int) d.getHeight()) - y);
	}

	// this is to transfer the coordiante in frame to coordinate in theory
	public static int calculateTheoryX(Dimension d, int x) {
		// this is to transfer the X in frame to coordinate in theory
		return (x);
	}

	public static int calculateTheoryY(Dimension d, int y) {
		// this is to transfer the Y in frame to coordinate in theory
		return (((int) d.getHeight()) - y);
	}

}
