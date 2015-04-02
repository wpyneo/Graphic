package covering;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.event.MouseInputListener;

import edgeCutting.Line;

public class MainFrame {

	private JFrame mainFrame = new JFrame();
	private Canvas c = new C();

	private Dimension d = new Dimension();

	private JPanel shapePanel = new JPanel();
	private JLabel shapeListLabel = new JLabel("Shape List");

	private JPanel buttonPanel = new JPanel();
	private JButton addShape = new JButton("Add Shape");
	private JButton draw = new JButton("Draw");

	// this is the current shape selected;
	private Shape currentShape = null;
	// this will hold all the shapes created;
	private ArrayList<Shape> shapeList = new ArrayList<Shape>();

	// this is to hold all Shape Button;
	private ArrayList<JButton> shapeButtonList = new ArrayList<JButton>();

	public MainFrame() {

		d.setSize(1000, 700);
//		c.setBackground(Color.BLUE);
		c.addMouseListener(new DragShape());

		mainFrame.setSize(d);
		mainFrame.setLayout(new BorderLayout());

		mainFrame.setDefaultCloseOperation(mainFrame.EXIT_ON_CLOSE);
		mainFrame.setLayout(new BorderLayout());
		mainFrame.setVisible(true);

		mainFrame.add(c, BorderLayout.CENTER);

		

		// shape Panel layout will increase when more shape are created.
		shapePanel.setLayout(new GridLayout());
		// shapePanel.add(shapeListLabel);

		addShape.addActionListener(new AddShape());
		draw.addActionListener(new DrawAction());
		
		buttonPanel.setLayout(new GridLayout());
		buttonPanel.add(addShape);
		buttonPanel.add(draw);

		mainFrame.add(shapePanel, BorderLayout.EAST);
		mainFrame.add(buttonPanel, BorderLayout.SOUTH);
	}

	public Canvas getCanvas() {
		return c;
	}

	private class C extends Canvas {
		public void paint(Graphics g) {
			// draw the system

		}
	}

	private class DragShape implements MouseInputListener {

		Point start;
		Point end;
		Graphics g = c.getGraphics();

		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

			if (currentShape.checkWithIn(e.getPoint())) {
				start = e.getPoint();
				System.out.println(start);
			}

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseDragged(MouseEvent e) {
			// TODO Auto-generated method stub
			end = e.getPoint();

			int xMoved = (int) (end.getX() - start.getX());
			int yMoved = (int) (end.getY() - start.getX());

			currentShape.moveShape(xMoved, yMoved, g);
		}

		@Override
		public void mouseMoved(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	private class AddShape implements ActionListener {

		// this is to set the current layer number;
		int layer = 0;

		Graphics g = c.getGraphics();

		@Override
		public void actionPerformed(ActionEvent e) {

			// TODO Auto-generated method stub
			ArrayList<Line> shapeLines = new ArrayList<Line>();

			// create Random shape
			// setup a 4 edge shape

			int j = 4;

			// setup the start point and end point of each edge of shape
			Point sp = new Point();
			Point mp = new Point();
			Point ep = new Point();

			System.out.println(j);

			for (int i = 0; i <= j; i++) {
				int a = (int) (Math.round(Math.random() * 800));
				int b = (int) (Math.round(Math.random() * 600));

				if (i == 0) {
					// in the beginning, only setup the start point
					sp = new Point(a, b);
					mp = sp;

				} else if (i == (j)) {
					// in the end, draw line with last point with start point;
					shapeLines.add(new Line(mp, sp));
					System.out.println(mp.getX() + " " + mp.getY() + ", "
							+ sp.getX() + " " + sp.getY());
				} else {

					ep = new Point(a, b);
					shapeLines.add(new Line(mp, ep));
					System.out.println(mp.getX() + " " + mp.getY() + ", "
							+ ep.getX() + " " + ep.getY());
					mp = ep;
				}
			}

			// g.draw3DRect(200, 300, 100, 100, true);

			Shape newShape = new Shape(shapeLines, layer, c, d);
			System.out.println("Layer " + layer);

			// for testing
			currentShape = newShape;

			// draw new shape
			newShape.drawShape();

			// add this shape into shape list for covering order
			shapeList.add(newShape);

			// re arrange shapePanel
			shapePanel.setLayout(new GridLayout((layer + 2), 1));
			JButton shapeButton = new JButton("Shape " + layer);
			shapeButtonList.add(shapeButton);

			for (int i = -1; i < shapeButtonList.size(); i++) {
				// for (int i = 0; i < shapeButtonList.size(); i++) {
				if (i == -1) {
					shapePanel.add(shapeListLabel);
				} else {

					shapePanel.add(shapeButtonList.get(i));
				}
			}

			shapePanel.updateUI();
			layer++;
		}
	}
	
	private class DrawAction implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			currentShape.drawShape();
		}
		
	}
}
