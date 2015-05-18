package edgeCutting;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.Window;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class DrawFrame {

	private JFrame frame = new JFrame();
	private Canvas c = new C();
	private JPanel panel = new JPanel();
	private static AdvancedRectangle window = new AdvancedRectangle();

	private static AdvancedRectangle[] windowSeries = new AdvancedRectangle[9];

	public DrawFrame() {
		// setup Canvas size
		c.setSize(800, 500);

		// setup panel which can hold the button
		panel.setLayout(new FlowLayout());

		// create frame
		frame.setSize(800, 600);
//		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setVisible(true);

		frame.setLayout(new BorderLayout());
		frame.add(c, BorderLayout.CENTER);
		frame.add(panel, BorderLayout.SOUTH);
	}

	public JFrame getFrame() {
		return this.frame;
	}

	public Canvas getCanvas() {
		return this.c;
	}

	public JPanel getPanel() {
		return this.panel;
	}

	public static AdvancedRectangle[] getWindowRectangleSeries() {
		return windowSeries;
	}
	
	public static AdvancedRectangle getWindow(){
		return window;
	}

	private class C extends Canvas {
		public void paint(Graphics g) {
			// draw the window
			g.drawRect(201, 151, 400, 200);
			window.setBounds(201, 151, 400, 200);
			// based on the Cohen�SutherLand theory, setup the window area.
			for (int i = 0; i < windowSeries.length; i++) {
				windowSeries[i] = new AdvancedRectangle();

				switch (i) {
				case 0:
					windowSeries[i].setBounds(201, 151, 400, 200);
					windowSeries[i].setAreaCode(false, false, false, false);
					break;
				case 1:
					windowSeries[i].setBounds(601, 151, 200, 200);
					windowSeries[i].setAreaCode(false, false, true, false);
					break;
				case 2:
					windowSeries[i].setBounds(601, 0, 200, 150);
					windowSeries[i].setAreaCode(true, false, true, false);
					break;
				case 3:
					windowSeries[i].setBounds(201, 0, 400, 150);
					windowSeries[i].setAreaCode(true, false, false, false);
					break;
				case 4:
					windowSeries[i].setBounds(0, 0, 200, 150);
					windowSeries[i].setAreaCode(true, false, false, true);
					break;
				case 5:
					windowSeries[i].setBounds(0, 151, 200, 200);
					windowSeries[i].setAreaCode(false, false, false, true);
					break;
				case 6:
					windowSeries[i].setBounds(0, 351, 200, 150);
					windowSeries[i].setAreaCode(false, true, false, true);
					break;
				case 7:
					windowSeries[i].setBounds(201, 351, 400, 150);
					windowSeries[i].setAreaCode(false, true, false, false);
					break;
				case 8:
					windowSeries[i].setBounds(601, 351, 200, 150);
					windowSeries[i].setAreaCode(false, true, true, false);
					break;
				}
				System.out.print(windowSeries[i].toString());
				System.out.println(windowSeries[i].getAreaCode()[0]
						+" "+ windowSeries[i].getAreaCode()[1]
						+" "+ windowSeries[i].getAreaCode()[2]
						+" "+ windowSeries[i].getAreaCode()[3]);
			}
		}
	}
}
