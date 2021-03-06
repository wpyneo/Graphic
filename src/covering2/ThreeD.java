package covering2;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ThreeD {

	private JFrame frame = new JFrame();
	private Canvas originalC = null;
	private Dimension d = null;
	
	// Instruction Panel
	private JPanel instructionPanel = new JPanel();
	private JLabel titleLabel = new JLabel("透视投影 与 消除隐藏面");
	private JLabel instructionsLabel = new JLabel(
			"清在屏幕中间的黑框中点击鼠标一次。点击后会生成一个正方体。");

	public ThreeD() {

		// create canvas to display original shape
		originalC = new C();
		originalC.setBackground(Color.white);

		// Create Frame
		d = new Dimension(1024, 800);
		frame.setSize(d);
//		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);

		// add Mouse click action
		originalC.addMouseListener(new Positions());
		
		// add instruction panel
		instructionPanel.setLayout(new GridLayout(2, 1));
		titleLabel.setHorizontalAlignment(JLabel.CENTER);
		titleLabel.setFont(new Font("Title", Font.BOLD, 30));
		instructionsLabel.setHorizontalAlignment(JLabel.CENTER);
		instructionPanel.add(titleLabel);
		instructionPanel.add(instructionsLabel);
		frame.add(instructionPanel, BorderLayout.NORTH);

		// add panel to frame;
		frame.add(originalC, BorderLayout.CENTER);
	}

	private class C extends Canvas {
		public void paint(Graphics g) {
			// draw original block
			Block b = new Block();
			b.originalBlock(g);
		}
	}

	public Graphics getGraphics() {
		return originalC.getGraphics();
	}

	private class Positions implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent me) {
			// TODO Auto-generated method stub

			Graphics g = getGraphics();
			// get the point which mouse clicked on the available area
			Point fp = me.getPoint();
			// print out the position of the mouse click point
			System.out.println("Front Point is " + fp.getX() + " " + fp.getY());

			// test if the point is out of range
			Point bp = getPoints(fp);
			if (bp.getX() != 0.0) {
				// print out the new block based on the point clicked
				Block b = new Block();
				b.getBlock(fp, bp, g, d);
			}
		}

		@Override
		public void mouseEntered(MouseEvent arg0) {
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
		}
	}

	// Calculate front panel and back panel middle points
	public Point getPoints(Point p) {
		int x = (int) p.getX();
		int y = (int) p.getY();
		int newX = 0;
		int newY = 0;

		// setup the available range
		if ((int) y > 250 && (int) y < 550 && (int) x > 99 && (int) x < 900) {
			// calculate the new position of the back panel middle point base on
			// the front panel middle point, which is the mouse click point
			newX = Math.abs((400 - x) * 6 / 7 - 400);
			newY = Math.abs((400 - y) * 6 / 7 - 400);
			// print out the position of both point

			System.out.println("Rear Point is " + newX + " " + newY);
			return new Point(newX, newY);
		} else {
			// pop out a message when point is out of range.
			new JOptionPane().showMessageDialog(frame, "Out of Range");
		}
		return new Point(0, 0);
	}
}
