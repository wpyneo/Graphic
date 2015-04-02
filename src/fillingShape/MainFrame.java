package fillingShape;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Enumeration;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import edgeCutting.Line;

public class MainFrame {

	// store all point list and line list.
	private ArrayList<Point> pointList = new ArrayList<Point>();
	private ArrayList<Line> allLines = new ArrayList<Line>();

	// setup frame dimension;
	private Dimension d;

	// Resolution checkbox

	private ButtonGroup resoGroup = new ButtonGroup();
	private JRadioButton highResolution = new JRadioButton("High Resolution");
	private JRadioButton mediumResolution = new JRadioButton(
			"Medium Resolution");
	private JRadioButton lowResolution = new JRadioButton("Low Resolution");

	// Command Button initialization

	private JButton draw = new JButton("Draw");
	private JButton fill = new JButton("Fill");
	private JButton clear = new JButton("Clear");

	// Status message Label initialization
	private JLabel notificationLabel = new JLabel(
			"Please select resolution type");

	// container initialization
	private JFrame frame = new JFrame();
	private JPanel buttonPanel = new JPanel();
	private JPanel mainPanel = new JPanel();

	private Canvas c = null;

	// return the current resolution parameter
	private int p;

	public MainFrame() {

		// setup Dimension

		d = new Dimension(800, 600);
		// Create Frame and canvas

		c = new C();
		c.addMouseListener(new ShapePoint());
		frame.setSize(d);

		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);
		c.addComponentListener(new ResizeListener());

		// add canvas to frame
		frame.add(c, BorderLayout.CENTER);

		// create button panel to hold button
		buttonPanel.setLayout(new GridLayout(1, 4));

		resoGroup.add(lowResolution);
		resoGroup.add(mediumResolution);
		resoGroup.add(highResolution);

		lowResolution.addItemListener(new cbItemListener());
		mediumResolution.addItemListener(new cbItemListener());
		highResolution.addItemListener(new cbItemListener());

		draw.addActionListener(new DrawButtonAction());
		fill.addActionListener(new FillButtonAction());
		clear.addActionListener(new ClearButtonAction());

		buttonPanel.add(lowResolution);
		buttonPanel.add(mediumResolution);
		buttonPanel.add(highResolution);
		buttonPanel.add(draw);
		buttonPanel.add(fill);
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

			// this will transfer the coordinate in Frame to Coordinate in
			// Theory
			int x = CoordinateTransformer.calculateTheoryX(d, (int) e
					.getPoint().getX());
			int y = CoordinateTransformer.calculateTheoryY(d, (int) e
					.getPoint().getY());

			// gather the points clicked and add to ArrayList as we don't
			// know how many times user will click.
			Point p = new Point(x, y);
			pointList.add(p);

			// this is to watch the coordinate of each click
			System.out.println(p.toString());

//			// // only for testing
//			 pointList.add(new Point(201, 531));
//			 pointList.add(new Point(275, 429));
//			 pointList.add(new Point(419, 524));
//			 pointList.add(new Point(437, 227));
//			 pointList.add(new Point(326, 146));
//			 pointList.add(new Point(119, 222));

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

	private class DrawButtonAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			Graphics g = c.getGraphics();

			if (allLines.size() == 0) {

				// get the arraylist which contains all the points user clicked
				ArrayList<Point> al = pointList;
				// convert it to array so that we could use loop access each
				// point
				Object[] ob = al.toArray();

				if (ob.length == 1) {
					System.out.println("Only one click is made");
				} else if (ob.length == 0) {
					System.out.println("No click is made");
				} else if (ob.length == 2) {
					System.out.println("2 points are made");
					System.out.println("1 line is made");
					Point p1 = (Point) ob[0];
					Point p2 = (Point) ob[1];

					g.drawLine(
							CoordinateTransformer.calculateFrameX(d,
									(int) p1.getX()),
							CoordinateTransformer.calculateFrameY(d,
									(int) p1.getY()),
							CoordinateTransformer.calculateFrameX(d,
									(int) p2.getX()),
							CoordinateTransformer.calculateFrameY(d,
									(int) p2.getY()));

					// add line to allLines arraylist
					allLines.add(new Line(1, p1, p2));

				} else {
					System.out.println(ob.length + " points are made");
					for (int i = 0; i < ob.length; i++) {

						Point p1 = (Point) ob[i];
						// if it is not yet reaching the last point, then we
						// need to draw a line to connect the first point and
						// the second point
						Point p2;
						// if it is reaching the last point, then we need to
						// draw a
						// line to connect the last one and the first one
						if (i == ob.length - 1) {
							p2 = (Point) ob[0];
						} else {
							p2 = (Point) ob[i + 1];
						}
						g.drawLine(CoordinateTransformer.calculateFrameX(d,
								(int) p1.getX()), CoordinateTransformer
								.calculateFrameY(d, (int) p1.getY()),
								CoordinateTransformer.calculateFrameX(d,
										(int) p2.getX()), CoordinateTransformer
										.calculateFrameY(d, (int) p2.getY()));

						// add line to allLines arraylist
						allLines.add(new Line(i + 1, p1, p2));
					}
					System.out.println(allLines.toArray().length
							+ " Lines are made");
				}

			} else {
				// get the arraylist which contains all the points user clicked
				ArrayList<Point> al = pointList;
				// convert it to array so that we could use loop access each
				// point
				Object[] ob = al.toArray();

				if (ob.length == 1) {
					System.out.println("Only one click is made");
				} else if (ob.length == 0) {
					System.out.println("No click is made");
				} else if (ob.length == 2) {
					System.out.println("2 points are made");
					System.out.println("1 line is made");
					Point p1 = (Point) ob[0];
					Point p2 = (Point) ob[1];

					g.drawLine(
							CoordinateTransformer.calculateFrameX(d,
									(int) p1.getX()),
							CoordinateTransformer.calculateFrameY(d,
									(int) p1.getY()),
							CoordinateTransformer.calculateFrameX(d,
									(int) p2.getX()),
							CoordinateTransformer.calculateFrameY(d,
									(int) p2.getY()));

					// // add line to allLines arraylist
					// allLines.add(new Line(1, p1, p2));

				} else {
					System.out.println(ob.length + " points are made");
					for (int i = 0; i < ob.length; i++) {

						Point p1 = (Point) ob[i];
						// if it is not yet reaching the last point, then we
						// need to draw a line to connect the first point and
						// the second point
						Point p2;
						// if it is reaching the last point, then we need to
						// draw a
						// line to connect the last one and the first one
						if (i == ob.length - 1) {
							p2 = (Point) ob[0];
						} else {
							p2 = (Point) ob[i + 1];
						}
						g.drawLine(CoordinateTransformer.calculateFrameX(d,
								(int) p1.getX()), CoordinateTransformer
								.calculateFrameY(d, (int) p1.getY()),
								CoordinateTransformer.calculateFrameX(d,
										(int) p2.getX()), CoordinateTransformer
										.calculateFrameY(d, (int) p2.getY()));

						// // add line to allLines arraylist
						// allLines.add(new Line(i + 1, p1, p2));
					}
					System.out.println(allLines.toArray().length
							+ " Lines are made");
				}
			}

			// create Edge Table
			new EdgeTable(allLines, d);
		}
	}

	private class FillButtonAction implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			new FillShape(allLines, d, p, c.getGraphics());
		}

	}

	private class ClearButtonAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			Graphics g = c.getGraphics();

			// clear area.
			g.clearRect(0, 0, (int) d.getWidth() + 50, (int) d.getHeight() + 50);

			pointList = new ArrayList<Point>();
			allLines = new ArrayList<Line>();

			System.out.println("Clean the frame~~~~~~~~~~~~~~~~~~~~~~~~~~");
		}
	}

	// this is to set the current resolution
	private class cbItemListener implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent arg0) {
			// TODO Auto-generated method stub
			String reso = "";
			// retrieve the selection from radio button group

			for (Enumeration<AbstractButton> buttons = resoGroup.getElements(); buttons
					.hasMoreElements();) {
				AbstractButton button = buttons.nextElement();

				if (button.isSelected()) {
					reso = button.getText();
					// for each resolution, construct resolution and pixel map.
					p = new ResolutionConstructor(reso, c, d).getPValue();
					System.out.println("Current Resolution Parameter is " + p);
				}
			}
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

}
