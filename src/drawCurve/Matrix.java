package drawCurve;

public class Matrix {

	double[] Matrix1D = null;
	double[][] Matrix2D = null;
	double[][][] Matrix3D = null;

	public Matrix(int a) {

		Matrix1D = new double[a];
	}

	public Matrix(int a, int b) {

		Matrix2D = new double[a][b];
	}

	public Matrix(int a, int b, int c) {

		Matrix3D = new double[a][b][c];
	}

	public double[] getMatrix1D() {
		return Matrix1D;
	}

	public void setMatrix1D(double[] matrix1d) {
		Matrix1D = matrix1d;
	}

	public double[][] getMatrix2D() {
		return Matrix2D;
	}

	public void setMatrix2D(double[][] matrix2d) {
		Matrix2D = matrix2d;
	}

	public double[][][] getMatrix3D() {
		return Matrix3D;
	}

	public void setMatrix3D(double[][][] matrix3d) {
		Matrix3D = matrix3d;
	}
	
	

}
