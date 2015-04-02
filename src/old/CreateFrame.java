package old;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import old2.CreateShape;

public class CreateFrame {

	private JButton mirror = new JButton("Mirror");
	private JButton rotation = new JButton("Rotation");
	private JButton zoom = new JButton("Zoom");
	private JFrame frame = new JFrame();
	private JPanel panel = new JPanel();
//	private static Canvas c = null;
	private Canvas c = null;
	private Rectangle rec = null; //this is to hold the value of original rectangle

	public CreateFrame() {
		// Create Frame and canvas

		c = new C();
		frame.setSize(800, 600);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);

		// add canvas to frame
		frame.add(c, BorderLayout.CENTER);

		// create panel to hold button
		panel.setLayout(new FlowLayout());
		// add action listener to each button
		mirror.addActionListener(new Mirror());
		zoom.addActionListener(new Zoom());
		rotation.addActionListener(new Rotation());
		
		panel.add(mirror);
		panel.add(rotation);
		panel.add(zoom);

		// add panel to frame;
		frame.add(panel, BorderLayout.SOUTH);
	}

	public Graphics getGraphics() {
		//return the current graphic
		c.getGraphics().clearRect(0, 0, 399, 299);
		c.getGraphics().clearRect(401,0,799,299);
		c.getGraphics().clearRect(401,301,799,599);
		c.getGraphics().clearRect(0,301,399,599);
		
		return c.getGraphics();
	}
	
	public Rectangle getRectangle(){
		return rec;
	}
	
	public Canvas getCanvas(){
		return c;
	}

	private class C extends Canvas {
		public void paint(Graphics g) {
			//draw the system
			g.drawLine(0, 300, 800, 300);
			g.drawLine(400, 0, 400, 600);
			
			//draw original shape
			CreateShape cs = new CreateShape("shape", c);
			rec = cs.getRectangle();
		}
	}

	private class Mirror implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Graphics g = getGraphics();
			
			//clear the previous shape

			CreateShape cs = new CreateShape("shape", c);
			rec = cs.getRectangle();
			
			TransferSelector ts = new TransferSelector("Mirror", getRectangle(), getCanvas());
			
		}
	}
	
	private class Rotation implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Graphics g = getGraphics();
			
			//clear the previous shape

			CreateShape cs = new CreateShape("shape", c);
			rec = cs.getRectangle();
			
			TransferSelector ts = new TransferSelector("Rotation", getRectangle(), getCanvas());
			
		}
	}
	
	private class Zoom implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Graphics g = getGraphics();
			
			//clear the previous shape

			CreateShape cs = new CreateShape("shape", c);
			rec = cs.getRectangle();
			
			TransferSelector ts = new TransferSelector("Zoom", getRectangle(), getCanvas());
			
		}
	}

}
