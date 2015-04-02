package transformation;

public class Matrix {

	private static int[] OriginalMatrix3D = new int[4];
	private static double[][] ShiftMatrix = new double[4][4];
	private static double[][] RotateMatrixX = new double[4][4];
	private static double[][] RotateMatrixY = new double[4][4];
	private static double[][] RotateMatrixZ = new double[4][4];
	private static double[][] ZoomMatrix = new double[4][4];
	private static int[] ResultMatrix3D = new int[4];

	public static Coordinate3D shift(double[] changeValue,
			Coordinate3D originalc3d) {

		// coordinate shift

		// import original Coordinate3D into Matrix system
		createMatrix(originalc3d);

		// create Shift Matrix
		for (int i = 0; i < ShiftMatrix.length; i++) {
			for (int j = 0; j < ShiftMatrix[i].length; j++) {
				ShiftMatrix[i][j] = 0;
			}
		}

		ShiftMatrix[0][0] = 1;
		ShiftMatrix[1][1] = 1;
		ShiftMatrix[2][2] = 1;
		ShiftMatrix[3][3] = 1;
		ShiftMatrix[0][3] = changeValue[0];
		ShiftMatrix[1][3] = changeValue[1];
		ShiftMatrix[2][3] = changeValue[2];

		// perform Matrix calculation

		// for (int i = 0; i < OriginalMatrix3D.length; i++) {
		// ResultMatrix3D[i] = (int) (ShiftMatrix[i][0] * OriginalMatrix3D[0]
		// + ShiftMatrix[i][1] * OriginalMatrix3D[1]
		// + ShiftMatrix[i][2] * OriginalMatrix3D[2] + ShiftMatrix[i][3]
		// * OriginalMatrix3D[3]);
		// }
		//
		ResultMatrix3D = matrixCalculation(ShiftMatrix, OriginalMatrix3D);

		return disassemblyMatrix(ResultMatrix3D);
	}

	public static Coordinate3D zoom(double[] changeValue,
			Coordinate3D originalc3d) {

		// coordinate zoom

		// import original Coordinate3D into Matrix system
		createMatrix(originalc3d);

		// create Shift Matrix
		for (int i = 0; i < ZoomMatrix.length; i++) {
			for (int j = 0; j < ZoomMatrix[i].length; j++) {
				ZoomMatrix[i][j] = 0;
			}
		}

		ZoomMatrix[0][0] = changeValue[0];
		ZoomMatrix[1][1] = changeValue[1];
		ZoomMatrix[2][2] = changeValue[2];
		ZoomMatrix[3][3] = 1;

		// perform Matrix calculation
		ResultMatrix3D = matrixCalculation(ZoomMatrix, OriginalMatrix3D);

		return disassemblyMatrix(ResultMatrix3D);
	}

	public static Coordinate3D rotate(double[] changeValue,
			Coordinate3D originalc3d) {

		// coordinate rotate

		// import original Coordinate3D into Matrix system
		createMatrix(originalc3d);

		// create rotate Matrix
		for (int i = 0; i < RotateMatrixX.length; i++) {
			for (int j = 0; j < RotateMatrixX[i].length; j++) {
				RotateMatrixX[i][j] = 0;
				RotateMatrixY[i][j] = 0;
				RotateMatrixZ[i][j] = 0;
			}
		}

		// for rotate again X axis
		RotateMatrixX[0][0] = 1;
		RotateMatrixX[1][1] = Math.cos(changeValue[0]);
		RotateMatrixX[1][2] = -Math.sin(changeValue[0]);
		RotateMatrixX[2][1] = Math.sin(changeValue[0]);
		RotateMatrixX[2][2] = Math.cos(changeValue[0]);
		RotateMatrixX[3][3] = 1;

		// for rotate again Y axis
		RotateMatrixY[0][0] = Math.cos(changeValue[1]);
		RotateMatrixY[0][2] = Math.sin(changeValue[1]);
		RotateMatrixY[1][1] = 1;
		RotateMatrixY[2][0] = -Math.sin(changeValue[1]);
		RotateMatrixY[2][2] = Math.cos(changeValue[1]);
		RotateMatrixY[3][3] = 1;

		// for rotate again Z axis
		RotateMatrixZ[0][0] = Math.cos(changeValue[2]);
		RotateMatrixZ[0][1] = -Math.sin(changeValue[2]);
		RotateMatrixZ[1][0] = Math.sin(changeValue[2]);
		RotateMatrixZ[1][1] = Math.cos(changeValue[2]);
		RotateMatrixZ[2][2] = 1;
		RotateMatrixZ[3][3] = 1;

		// perform Matrix calculation
		ResultMatrix3D = matrixCalculation(RotateMatrixX, OriginalMatrix3D);
		ResultMatrix3D = matrixCalculation(RotateMatrixY, ResultMatrix3D);
		ResultMatrix3D = matrixCalculation(RotateMatrixZ, ResultMatrix3D);

		return disassemblyMatrix(ResultMatrix3D);
	}

	// fit Coodinate3D object into Matrix format
	public static void createMatrix(Coordinate3D c3d) {

		OriginalMatrix3D[0] = c3d.getX();
		OriginalMatrix3D[1] = c3d.getY();
		OriginalMatrix3D[2] = c3d.getZ();
		OriginalMatrix3D[3] = 1;

	}

	// restore a Matrix to a Coordinate3D object
	public static Coordinate3D disassemblyMatrix(int[] m) {

		return new Coordinate3D(m[0], m[1], m[2]);

	}

	// Matrix calculation
	public static int[] matrixCalculation(double[][] a, int[] b) {

		int[] result = new int[4];
		for (int i = 0; i < b.length; i++) {
			result[i] = (int) (a[i][0] * b[0] + a[i][1] * b[1] + a[i][2] * b[2] + a[i][3]
					* b[3]);
		}

		return result;
	}
}
