package edgeCutting;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;

public class CreateShape {

	private ArrayList<Point> pointList = new ArrayList<Point>();
	private JFrame frame = null;
	private JButton createButton = new JButton("Create Shape");
	private JButton clearButton = new JButton("Clear Area");
	private JButton cutButton = new JButton("Cut Visiable Area");
	private JButton summary = new JButton("Summary (Only for testing)");
	private DrawFrame df = null;
	private ArrayList<Line> allLines = new ArrayList<Line>();

	public CreateShape() {
		// initialize the frame
		createShapePoint();
	}

	public void createShapePoint() {

		df = new DrawFrame();

		frame = df.getFrame();

		// add button to bottom of the frame and add action
		createButton.addActionListener(new CreateButtonAction());
		df.getPanel().add(createButton);

		// add button to clear the area
		clearButton.addActionListener(new ClearButtonAction());
		df.getPanel().add(clearButton);

		// add button to cut visible area
		cutButton.addActionListener(new CutButtonAction());
		df.getPanel().add(cutButton);

		// add button to show the summary
		summary.addActionListener(new Summary());
		df.getPanel().add(summary);

		// add mouse listener to Canvas so that it will gather the clicks
		df.getCanvas().addMouseListener(new ShapePoint());

	}

	// this is to setup the list of points which is clicked by end user
	public ArrayList<Point> getPointList() {
		return pointList;
	}

	public void setPointList(ArrayList<Point> ap) {
		this.pointList = ap;
	}

	// this is to setup the list of lines a shape contains
	public ArrayList<Line> getAllLines() {
		return allLines;
	}

	public void setAllLines(ArrayList<Line> al) {
		this.allLines = al;
	}

	private class CreateButtonAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			Graphics g = df.getCanvas().getGraphics();

			// get the arraylist which contains all the points user clicked
			ArrayList<Point> al = getPointList();
			// convert it to array so that we could use loop access each
			// point
			Object[] ob = al.toArray();

			if (ob.length == 1) {
				System.out.println("Only one click is made");
			} else if (ob.length == 0) {
				System.out.println("No click is made");
			} else if (ob.length == 2) {
				System.out.println("1 line is made");
				Point p1 = (Point) ob[0];
				Point p2 = (Point) ob[1];

				g.drawLine((int) p1.getX(), (int) p1.getY(), (int) p2.getX(),
						(int) p2.getY());
				// add line to allLines arraylist
				allLines.add(new Line(1, p1, p2));

			} else {
				System.out.println(ob.length + " lines are made");
				for (int i = 0; i < ob.length; i++) {

					Point p1 = (Point) ob[i];
					// if it is not yet reaching the last point, then we
					// need to draw a line to connect the first point and
					// the second point
					Point p2;
					// if it is reaching the last point, then we need to draw a
					// line to connect the last one and the first one
					if (i == ob.length - 1) {
						p2 = (Point) ob[0];
					} else {
						p2 = (Point) ob[i + 1];
					}
					g.drawLine((int) p1.getX(), (int) p1.getY(),
							(int) p2.getX(), (int) p2.getY());
					// add line to allLines arraylist
					allLines.add(new Line(i + 1, p1, p2));
				}

			}
		}
	}

	private class ClearButtonAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			Graphics g = df.getCanvas().getGraphics();

			// clear area.
			g.clearRect(0, 0, 800, 500);
			// redraw the windows
			g.drawRect(201, 151, 400, 200);
			// clear the ArrayList
			setPointList(new ArrayList<Point>());
			setAllLines(new ArrayList<Line>());
			System.out.println("Clean the frame~~~~~~~~~~~~~~~~~~~~~~~~~~");
		}
	}

	private class CutButtonAction implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			// this is to create the list of lines of the shape
			Object[] lines = getAllLines().toArray();

			// this is to identify if each line of a shape is cutting the edge
			// of the window
			for (int i = 0; i < lines.length; i++) {

				Line a = (Line) lines[i];

				a.setCutStatus(Tools.checkLineVisibility((Line) lines[i]));
				System.out.println(a.getCutStatus());

				lines[i] = new Tools().getJointPoint(a);

			}
			Graphics g = df.getCanvas().getGraphics();
			// clean the old shape
			// clear area.
			g.clearRect(0, 0, 800, 500);
			// redraw the windows
			g.drawRect(201, 151, 400, 200);

			// draw the visible shape

			for (int i = 0; i < lines.length; i++) {
				if (lines[i] != null) {
					g.drawLine((int) ((Line) lines[i]).getStartPoint().getX(),
							(int) ((Line) lines[i]).getStartPoint().getY(),
							(int) ((Line) lines[i]).getEndPoint().getX(),
							(int) ((Line) lines[i]).getEndPoint().getY());
				}
			}
		}
	}

	private class Summary implements ActionListener {
		// this is to view all the line detail. Only for testing
		public void actionPerformed(ActionEvent arg0) {

			Object[] lines = getAllLines().toArray();

			for (int i = 0; i < lines.length; i++) {

				Line a = (Line) lines[i];
				System.out.println(a.getLineNumber() + " " + a.getCutStatus()
						+ " " + a.getStartPoint().toString() + " "
						+ a.getEndPoint().toString() + " Gradient is "
						+ a.getGradient() + " B is " + a.getB());

				// this is to get the visible part of the original line.
				// for the one which is obviously invisible, the value is null
				lines[i] = new Tools().getJointPoint(a);

			}
		}
	}

	private class ShapePoint implements MouseListener {
		public void mouseClicked(MouseEvent e) {

			// gather the points clicked and add to ArrayList as we don't know
			// how many times user will click.
			Point p = e.getPoint();
			pointList.add(p);

			// this is to watch the coordinate of each click
			System.out.println(p.toString());

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

}
