package transformation;

import java.awt.Graphics;

public class CoordinateSystem {

	public CoordinateSystem(Graphics g) {

		g.drawLine(CoordinateTransformer.calculateFrameX(-300),
				CoordinateTransformer.calculateFrameY(0),
				CoordinateTransformer.calculateFrameX(300),
				CoordinateTransformer.calculateFrameY(0));
		g.drawLine(CoordinateTransformer.calculateFrameX(0),
				CoordinateTransformer.calculateFrameY(250),
				CoordinateTransformer.calculateFrameX(0),
				CoordinateTransformer.calculateFrameY(-250));

		g.drawLine(CoordinateTransformer.calculateFrameX(new Coordinate3D(0, 0,
				250).getCoordinate2D().getX()), CoordinateTransformer
				.calculateFrameY(new Coordinate3D(0, 0, 250).getCoordinate2D()
						.getY()), CoordinateTransformer
				.calculateFrameX(new Coordinate3D(0, 0, -250).getCoordinate2D()
						.getX()), CoordinateTransformer
				.calculateFrameY(new Coordinate3D(0, 0, -250).getCoordinate2D()
						.getY()));
	}

}
