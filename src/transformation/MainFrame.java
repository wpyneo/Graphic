package transformation;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainFrame {

	// input of Coordinate3D value
	private JTextField xValue = new JTextField("X Axis");
	private JTextField yValue = new JTextField("Y Axis");
	private JTextField zValue = new JTextField("Z Axis");
	private JButton draw = new JButton("Draw");

	// input the change value, like move 200pixel, or zoom 2 times, or rotate Pi
	// degree
	private JLabel notificationLabel = new JLabel("Please input Coordinates");
	private JTextField changeValueX = new JTextField("Input Value for X");
	private JTextField changeValueY = new JTextField("Input Value for Y");
	private JTextField changeValueZ = new JTextField("Input Value for Z");

	private JButton shift = new JButton("Shift");
	private JButton rotate = new JButton("Rotate");
	private JButton zoom = new JButton("Zoom");

	private JFrame frame = new JFrame();
	private JPanel buttonPanel = new JPanel();
	private JPanel coordinateInputPanel = new JPanel();
	private JPanel changePanel = new JPanel();
	private JPanel mainPanel = new JPanel();

	private Canvas c = null;
	// rectangle
	// this is to hold all the 3D coordinate input.
	private ArrayList<Coordinate3D> coodList3D = new ArrayList<Coordinate3D>();
	private ArrayList<Coordinate2D> coodList2D = new ArrayList<Coordinate2D>();

	public MainFrame() {
		// Create Frame and canvas

		c = new C();
		frame.setSize(900, 700);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);

		// add canvas to frame
		frame.add(c, BorderLayout.CENTER);

		// create input panel to hold input coordinate

		coordinateInputPanel.setLayout(new GridLayout(4, 1));

		coordinateInputPanel.add(xValue);
		coordinateInputPanel.add(yValue);
		coordinateInputPanel.add(zValue);
		coordinateInputPanel.add(draw);

		zValue.addKeyListener(new CoodInput());
		zValue.addFocusListener(new CoodFocus());
		draw.addActionListener(new Draw());

		// create button panel to hold button
		buttonPanel.setLayout(new GridLayout(1, 4));

		buttonPanel.add(changeValueX);
		buttonPanel.add(changeValueY);
		buttonPanel.add(changeValueZ);
		buttonPanel.add(shift);
		buttonPanel.add(rotate);
		buttonPanel.add(zoom);

		changeValueX.addFocusListener(new changeValueFocus());
		shift.addActionListener(new ShapeTransform());
		rotate.addActionListener(new ShapeTransform());
		zoom.addActionListener(new ShapeTransform());

		// create value change Panel
		changePanel.setLayout(new FlowLayout());
		changePanel.add(notificationLabel);

		// create Main Panel to hole button panel and value change panel

		mainPanel.setLayout(new GridLayout(2, 1));
		mainPanel.add(changePanel);
		mainPanel.add(buttonPanel);

		// add panels to frame;

		frame.add(mainPanel, BorderLayout.SOUTH);
		frame.add(coordinateInputPanel, BorderLayout.EAST);
	}

	public JTextField getxValue() {
		return xValue;
	}

	private class C extends Canvas {
		public void paint(Graphics g) {
			// draw the system
			new CoordinateSystem(g);

		}
	}

	// coordinate input action
	private class CoodInput implements KeyListener {

		@Override
		public void keyPressed(KeyEvent arg0) {
			// TODO Auto-generated method stub
			if (arg0.getKeyCode() == KeyEvent.VK_ENTER) // 判断按下的键是否是回车键
			{
				System.out.println("get cood");
				// retrieve the coodinate3D information from the input
				Coordinate3D c3d = new Coordinate3D(Integer.parseInt(xValue
						.getText()), Integer.parseInt(yValue.getText()),
						Integer.parseInt(zValue.getText()));

				// add all Coordinate3D into coodList3D
				coodList3D.add(c3d);
				// transfer Coordinate3D into Coordinate2D then add into
				// coodList2D for draw shape
				coodList2D.add(c3d.getCoordinate2D());

				System.out.println(c3d.getX() + " " + c3d.getY() + " "
						+ c3d.getZ() + "transformed to "
						+ c3d.getCoordinate2D().getX() + " "
						+ c3d.getCoordinate2D().getY() + " cood added");
			}

		}

		@Override
		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}
	}

	// this is to set the label content when change focus to Z value of
	// coordinate 3d input
	private class CoodFocus implements FocusListener {

		@Override
		public void focusLost(FocusEvent arg0) {
			// TODO Auto-generated method stub

		}

		@Override
		public void focusGained(FocusEvent e) {
			// TODO Auto-generated method stub

			notificationLabel
					.setText("Please press enter to finish inputing Coordinate, then go back to X Axis to input the second Coordinate. Or press Draw to draw a shape");
			notificationLabel.setForeground(Color.RED);

		}

	}

	// this is to draw the shape with previously stored coodList2D.
	private class Draw implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub

			// draw Shape, for production
			// DrawShape.Draw(c, coodList2D);

			// draw Shape, only for testing
			ArrayList<Coordinate3D> test3D = new ArrayList<Coordinate3D>();
			test3D.add(new Coordinate3D(100, 200, 300));
			test3D.add(new Coordinate3D(200, 300, 100));
			test3D.add(new Coordinate3D(200, 100, 300));
			test3D.add(new Coordinate3D(300, 100, 200));

			coodList3D = test3D;

			ArrayList<Coordinate2D> test2D = new ArrayList<Coordinate2D>();

			test2D.add(new Coordinate3D(100, 200, 300).calculate2DCoordinate());
			test2D.add(new Coordinate3D(200, 300, 100).calculate2DCoordinate());
			test2D.add(new Coordinate3D(200, 100, 300).calculate2DCoordinate());
			test2D.add(new Coordinate3D(300, 100, 200).calculate2DCoordinate());
			DrawShape.Draw(c, test2D, test3D);
			coodList2D = test2D;
		}

	}

	// this is the change label when change focus to transformation value
	private class changeValueFocus implements FocusListener {

		@Override
		public void focusGained(FocusEvent e) {
			// TODO Auto-generated method stub
			notificationLabel
					.setText("Input change value, like move 200 pixel, or zoom 2 times, or rotate Pi degree");
			notificationLabel.setForeground(Color.MAGENTA);
		}

		@Override
		public void focusLost(FocusEvent e) {
			// TODO Auto-generated method stub

		}

	}

	//this is the action to perform Shape transformation
	private class ShapeTransform implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent arg0) {
			// TODO Auto-generated method stub
			// call Shape Transform
			System.out.println(arg0.getActionCommand());

			double[] value = { Double.parseDouble(changeValueX.getText()),
					Double.parseDouble(changeValueY.getText()),
					Double.parseDouble(changeValueZ.getText()) };

			if (arg0.getActionCommand().equalsIgnoreCase("shift")) {

				System.out.println("perform shift");
				coodList3D = TransformShape.shift(c, coodList3D, value);

				System.out.println("shifted");

			} else if (arg0.getActionCommand().equalsIgnoreCase("rotate")) {

				System.out.println("perform Rotate");
				coodList3D = TransformShape.rotate(c, coodList3D, value);
				System.out.println("Rotated");

			} else if (arg0.getActionCommand().equalsIgnoreCase("Zoom")) {
				System.out.println("perform Zoom");
				coodList3D = TransformShape.zoom(c, coodList3D, value);
				System.out.println("Zoomed");
			}

		}

	}

}
