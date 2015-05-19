package drawCurve;

public class BezierCurve {

	private double[][] vector = null;
	private Matrix M = new Matrix(4, 4);
	private Matrix tMatrix = new Matrix(4);
	private double tZone = 1;
	private int interval;
	private double t = 0;
	private Matrix MT = new Matrix(4);
	private double[][] newVector;

	public BezierCurve(double[][] vector, int interval) {

		this.vector = vector;
		this.interval = interval;
		this.newVector = new double[vector.length][4];

		M.setMatrix2D(new double[][] { { 1, -3, 3, -1 }, { 0, 3, -6, 3 },
				{ 0, 0, 3, -3 }, { 0, 0, 0, 1 } });

		calculateGM();

	}

	// this is to calculate the value of each value of G*M
	public void calculateGM() {

		for (int i = 0; i < vector.length; i++) {
			for (int j = 0; j < vector[i].length; j++) {
				for (int k = 0; k < M.getMatrix2D().length; k++) {
					
					newVector[i][j] += vector[i][k] * M.getMatrix2D()[k][j];
					
				}
				System.out.print(newVector[i][j] + "  ");
			}
			System.out.println();
		}
	}

	public double[] calculateGMT() {
		double[] newVector = new double[vector.length];

		for (int i = 0; i < vector.length; i++) {
			for (int j = 0; j < vector[i].length; j++) {
				newVector[i] += this.newVector[i][j] * tMatrix.getMatrix1D()[j];

			}
			// System.out.println(newVector[i]);
		}

		return newVector;
	}

	// this is the calculate t value
	public void calculateT() {

		t += tZone / interval;

		// return t;
	}

	// this is to calculate the curve Matrix M * T
	public void getMT() {

		double[] mt = { (1 - 3 * t * t + 2 * t * t * t),
				(3 * t * t - 2 * t * t * t), (t - 2 * t * t + t * t * t),
				(t * t * t - t * t) };

		MT.setMatrix1D(mt);

		// return MT.getMatrix1D();

	}

	public void setTMatrix() {
		tMatrix.setMatrix1D(new double[] { 1, t, t * t, t * t * t });
	}

	public int getInterval() {
		return interval;
	}

	public void setInterval(int interval) {
		this.interval = interval;
	}

	public double getT() {
		return t;
	}

	public void setT(double t) {
		this.t = t;
	}

}
