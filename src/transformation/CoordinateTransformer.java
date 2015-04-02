package transformation;

//this class transform the coordinate in system into coordinate in Frame

public class CoordinateTransformer {

	// Frame resolution is 1280 X 800.
	// calculate X in frame
	public static int calculateFrameX(int x) {

		return (400 + x);
	}

	// calculate Y in frame
	public static int calculateFrameY(int y) {

		return (300 - y);
	}

}
