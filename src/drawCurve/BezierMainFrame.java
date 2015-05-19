package drawCurve;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import fillingShape.CoordinateTransformer;

public class BezierMainFrame {

	// store all point list and line list.
	private double[][] pointList = new double[2][4];

	// setup frame dimension;
	private Dimension d;

	// Command Button initialization

	private JButton draw = new JButton("Draw");
	private JButton clear = new JButton("Clear");

	// Instruction Panel
	private JPanel instructionPanel = new JPanel();
	private JLabel titleLabel = new JLabel("Bezier 曲线");
	private JLabel instructionsLabel = new JLabel(
			"请在屏幕上点四个点, 第一个点和最后一个点为曲线的起点和终点。之后请在下方输入t的分割程度,默认值2000");

	// Status message Label initialization
	private JLabel notificationLabel = new JLabel(
			"Please select 4 points to create a curve");

	// container initialization
	private JFrame frame = new JFrame();
	private JPanel buttonPanel = new JPanel();
	private JPanel mainPanel = new JPanel();

	private Canvas c = null;

	// this is to input the interval of t value
	private JLabel intervalLabel = new JLabel("Please Input Interval");
	private JTextField intervalValue = new JTextField("2000");

	// this is the start point and end point of the curve
	private Point pStart;
	private Point pEnd;

	// this is to store the point which need to draw curve
	private Point drawPoint;

	// this is count how many clicks had been clicked. In theory it
	// shouldn't exceed 4.
	private int clickCount = 0;

	public BezierMainFrame() {

		// setup Dimension

		d = new Dimension(800, 600);
		// Create Frame and canvas

		c = new C();
		c.addMouseListener(new ShapePoint());
		frame.setSize(d);

		// frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		c.addComponentListener(new ResizeListener());
		

		// add canvas to frame
		frame.add(c, BorderLayout.CENTER);

		// add instruction panel
		instructionPanel.setLayout(new GridLayout(2, 1));
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setFont(new Font("Title", Font.BOLD, 30));
		instructionsLabel.setHorizontalAlignment(JLabel.CENTER);
		instructionPanel.add(titleLabel);
		instructionPanel.add(instructionsLabel);
		frame.add(instructionPanel, BorderLayout.NORTH);

		// create button panel to hold button
		buttonPanel.setLayout(new GridLayout(1, 4));

		draw.addActionListener(new DrawButtonAction());
		clear.addActionListener(new ClearButtonAction());

		buttonPanel.add(intervalLabel);
		buttonPanel.add(intervalValue);
		buttonPanel.add(draw);
		buttonPanel.add(clear);

		// create Main Panel to hole button panel and value change panel

		mainPanel.setLayout(new GridLayout(2, 1));
		mainPanel.add(notificationLabel);
		mainPanel.add(buttonPanel);

		// add panels to frame;

		frame.add(mainPanel, BorderLayout.SOUTH);

	}

	private class C extends Canvas {
		public void paint(Graphics g) {
			// draw the system

		}
	}

	private class ShapePoint implements MouseListener {

		public void mouseClicked(MouseEvent e) {

			if (clickCount < 4) {
				// this will transfer the coordinate in Frame to Coordinate in
				// Theory
				int x = CoordinateTransformer.calculateTheoryX(d, (int) e
						.getPoint().getX());
				int y = CoordinateTransformer.calculateTheoryY(d, (int) e
						.getPoint().getY());

				// gather the points clicked and transfer into vector

				pointList[0][clickCount] = x;
				pointList[1][clickCount] = y;

				// this is to watch the coordinate of each click
				System.out.println(x + " " + y);

				if (clickCount == 0) {
					pStart = new Point(x, y);
				}

				if (clickCount == 3) {
					pEnd = new Point(x, y);
				}

				clickCount++;
			} else {
				notificationLabel.setForeground(Color.red);
				notificationLabel.setText("More Than 4 clicks are made!!");

			}

			// only for testing
			//
			// pointList[0][0] = 169;
			// pointList[0][1] = 535;
			// pointList[0][2] = 165-169;
			// pointList[0][3] = 645-535;
			// pointList[1][0] = 244;
			// pointList[1][1] = 235;
			// pointList[1][2] = 587-244;
			// pointList[1][3] = 124-235;
			//
			// pStart = new Point(169, 244);
			//
			// pEnd = new Point(165, 387);
			//
			// c.getGraphics().drawOval(CoordinateTransformer.calculateFrameX(d,
			// 169), CoordinateTransformer.calculateFrameY(d, 244), 1, 1);
			// c.getGraphics().drawOval(CoordinateTransformer.calculateFrameX(d,
			// 535), CoordinateTransformer.calculateFrameY(d, 235), 1, 1);
			// c.getGraphics().drawOval(CoordinateTransformer.calculateFrameX(d,
			// 165), CoordinateTransformer.calculateFrameY(d, 587), 1, 1);
			// c.getGraphics().drawOval(CoordinateTransformer.calculateFrameX(d,
			// 645), CoordinateTransformer.calculateFrameY(d, 124), 1, 1);
			//
			// clickCount = 4;
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub

		}
	}

	// this is to get the current frame size
	private class ResizeListener implements ComponentListener {

		public void componentHidden(ComponentEvent e) {
		}

		public void componentMoved(ComponentEvent e) {
		}

		public void componentShown(ComponentEvent e) {
		}

		public void componentResized(ComponentEvent e) {
			Dimension newSize = e.getComponent().getBounds().getSize();
			notificationLabel.setText(newSize.toString() + "");
		}
	}

	// this is the action for Clear button
	private class ClearButtonAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			Graphics g = c.getGraphics();

			// clear area.
			g.clearRect(0, 0, (int) d.getWidth() + 50, (int) d.getHeight() + 50);

			// reset ArrayList which holds the points
			pointList = new double[2][4];

			clickCount = 0;

			System.out.println("Clean the frame~~~~~~~~~~~~~~~~~~~~~~~~~~");
		}
	}

	// this is the action for Draw button

	private class DrawButtonAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			if (clickCount == 4) {

				System.out.println(pointList[0].length);

				Graphics g = c.getGraphics();

				BezierCurve hc = new BezierCurve(pointList,
						Integer.parseInt(intervalValue.getText()));

				Point drawPoint1 = null;

				for (int i = 0; i < hc.getInterval(); i++) {

					hc.calculateT();
					// hc.getMT();
					hc.setTMatrix();

					double[] currentDrawPoint = hc.calculateGMT();

					drawPoint1 = new Point((int) currentDrawPoint[0],
							(int) currentDrawPoint[1]);

					if (i == 0) {
						drawPoint = pStart;
					}

					int a = CoordinateTransformer.calculateFrameX(d,
							(int) drawPoint.getX());
					int b = CoordinateTransformer.calculateFrameY(d,
							(int) drawPoint.getY());
					int c = CoordinateTransformer.calculateFrameX(d,
							(int) drawPoint1.getX());
					int dd = CoordinateTransformer.calculateFrameY(d,
							(int) drawPoint1.getY());
					g.drawLine(a, b, c, dd);

					// shift the new point to old point
					drawPoint = drawPoint1;
					System.out.println(drawPoint.toString());
				}

				drawPoint1 = pEnd;
				int a = CoordinateTransformer.calculateFrameX(d,
						(int) drawPoint.getX());
				int b = CoordinateTransformer.calculateFrameY(d,
						(int) drawPoint.getY());
				int c = CoordinateTransformer.calculateFrameX(d,
						(int) drawPoint1.getX());
				int dd = CoordinateTransformer.calculateFrameY(d,
						(int) drawPoint1.getY());
				g.drawLine(a, b, c, dd);

			} else {
				notificationLabel.setText((4 - clickCount)
						+ " more click needed!!");
			}
		}
	}

	// this is to calculate the vertor R0 and R1
	public void calculatePPRR() {
		pointList[0][2] = (pointList[0][2] - pointList[0][0]) * 5;
		pointList[0][3] = (pointList[0][3] - pointList[0][1]) * 5;

		pointList[1][2] = (pointList[1][2] - pointList[1][0]) * 5;
		pointList[1][3] = (pointList[1][3] - pointList[1][1]) * 5;
	}

}