package entrance;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import transformation.MainFrame;
import covering2.ThreeD;
import drawCurve.BezierMainFrame;
import drawCurve.HermiteMainFrame;
import edgeCutting.CreateShape;
import fillingShape.FillMainFrame;

public class CreateFrame {

	private JButton edgeCut = new JButton("剪裁");
	private JButton transform = new JButton("变换");
	private JButton filling = new JButton("填充");
	private JButton covering = new JButton("消隐");
	private JButton hcurve = new JButton("hermit曲线");
	private JButton bcurve = new JButton("Beizer曲线");

	private JFrame frame = new JFrame();
	private JPanel panel = new JPanel();

	public CreateFrame() {
		// Create Frame and canvas

		frame.setSize(300, 200);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
		frame.setLayout(new BorderLayout());
		frame.setVisible(true);

		// create panel to hold button
		panel.setLayout(new GridLayout(3, 2));
		// add action listener to each button
		edgeCut.addActionListener(new edgeCut());
		transform.addActionListener(new transform());
		filling.addActionListener(new filling());
		covering.addActionListener(new covering());
		hcurve.addActionListener(new hcurve());
		bcurve.addActionListener(new bcurve());

		panel.add(edgeCut);
		panel.add(transform);
		panel.add(filling);
		panel.add(covering);
		panel.add(hcurve);
		panel.add(bcurve);

		// add panel to frame;
		frame.add(panel, BorderLayout.CENTER);
	}

	private class edgeCut implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			CreateShape cs = new CreateShape();
		}
	}

	private class transform implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			MainFrame cs = new MainFrame();
			cs.getxValue().requestFocus();
		}
	}

	private class filling implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new FillMainFrame();
		}
	}

	private class covering implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			ThreeD d = new ThreeD();

		}
	}

	private class hcurve implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new HermiteMainFrame();
		}
	}

	private class bcurve implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			new BezierMainFrame();
		}
	}

}
