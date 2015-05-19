package covering2;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.util.ArrayList;

import edgeCutting.Line;
import fillingShape.FillShape;

public class Block {

	Graphics2D originalG = null;
	Graphics2D leftG = null;
	Graphics2D middleG = null;
	Graphics2D rightG = null;

	public void originalBlock(Graphics gd) {
		originalG = (Graphics2D) gd;

		// draw original block
		originalG.drawRect(100, 100, 100, 100);
		originalG.drawRect(140, 80, 100, 100);
		originalG.drawLine(100, 100, 140, 80);
		originalG.drawLine(200, 100, 240, 80);
		originalG.drawLine(100, 200, 140, 180);
		originalG.drawLine(200, 200, 240, 180);

		// draw the available click area
		originalG.drawRect(99, 249, 800, 300);
	}

	// get the new input point and draw the new block
	// fp is front point, which is the mouse click point
	// bp is back point, which is the back panel point calculated based on front
	// point
	public void getBlock(Point fp, Point bp, Graphics gd) {
		leftG = (Graphics2D) gd;
		// clear the background and draw the available click area
		leftG.clearRect(0, 201, 1024, 800);
		leftG.drawRect(0, 249, 800, 300);

		// draw new block
		leftG.drawRect((int) fp.getX() - 50, (int) fp.getY() - 50, 100, 100);
		leftG.drawRect((int) bp.getX() - 40, (int) bp.getY() - 40, 80, 80);
		leftG.drawLine((int) fp.getX() - 50, (int) fp.getY() - 50,
				(int) bp.getX() - 40, (int) bp.getY() - 40);
		leftG.drawLine((int) fp.getX() + 50, (int) fp.getY() + 50,
				(int) bp.getX() + 40, (int) bp.getY() + 40);
		leftG.drawLine((int) fp.getX() - 50, (int) fp.getY() + 50,
				(int) bp.getX() - 40, (int) bp.getY() + 40);
		leftG.drawLine((int) fp.getX() + 50, (int) fp.getY() - 50,
				(int) bp.getX() + 40, (int) bp.getY() - 40);

	}

	// involving the covering
	public void getBlock(Point fp, Point bp, Graphics g, Dimension d) {
		leftG = (Graphics2D) g;
		// clear the background and draw the available click area
		leftG.clearRect(0, 201, 1024, 800);
		leftG.drawRect(99, 249, 800, 300);

		// get Points for front panel
		Point fpa = new Point((int) fp.getX() - 50, (int) fp.getY() - 10);
		Point fpb = new Point((int) fp.getX() + 10, (int) fp.getY() - 50);
		Point fpc = new Point((int) fp.getX() + 50, (int) fp.getY() + 10);
		Point fpd = new Point((int) fp.getX() - 10, (int) fp.getY() + 50);

		System.out.println("fpa is " + fpa.toString());
		System.out.println("fpb is " + fpb.toString());
		System.out.println("fpc is " + fpc.toString());
		System.out.println("fpd is " + fpd.toString());
		
		// get Points for rear panel
		Point rpa = new Point((int) bp.getX() - 40, (int) bp.getY() - 8);
		Point rpb = new Point((int) bp.getX() + 8, (int) bp.getY() - 40);
		Point rpc = new Point((int) bp.getX() + 40, (int) bp.getY() + 8);
		Point rpd = new Point((int) bp.getX() - 8, (int) bp.getY() + 40);

		// get Lines for front panel
		Line fla = new Line(fpa, fpb);
		Line flb = new Line(fpb, fpc);
		Line flc = new Line(fpc, fpd);
		Line fld = new Line(fpd, fpa);

		// get Arraylist created to draw front panel
		ArrayList<Line> frontPanelLine = new ArrayList<Line>();
		frontPanelLine.add(fla);
		frontPanelLine.add(flb);
		frontPanelLine.add(flc);
		frontPanelLine.add(fld);

		// get Lines for rear panel
		Line rla = new Line(rpa, rpb);
		Line rlb = new Line(rpb, rpc);
		Line rlc = new Line(rpc, rpd);
		Line rld = new Line(rpd, rpa);

		// get Arraylist created to draw rear panel
		ArrayList<Line> rearPanelLine = new ArrayList<Line>();
		rearPanelLine.add(rla);
		rearPanelLine.add(rlb);
		rearPanelLine.add(rlc);
		rearPanelLine.add(rld);

		// get Lines for bottom left panel
		Line lla = new Line(rpa, fpa);
		Line llb = new Line(fpa, fpd);
		Line llc = new Line(fpd, rpd);
		Line lld = new Line(rpd, rpa);

		// get Arraylist created to draw bottom left panel
		ArrayList<Line> bottomLeftPanelLine = new ArrayList<Line>();
		bottomLeftPanelLine.add(lla);
		bottomLeftPanelLine.add(llb);
		bottomLeftPanelLine.add(llc);
		bottomLeftPanelLine.add(lld);

		// get Lines for top left panel
		Line tla = new Line(rpa, fpa);
		Line tlb = new Line(fpa, fpb);
		Line tlc = new Line(fpb, rpb);
		Line tld = new Line(rpb, rpa);

		// get Arraylist created to draw top left panel
		ArrayList<Line> topLeftPanelLine = new ArrayList<Line>();
		topLeftPanelLine.add(tla);
		topLeftPanelLine.add(tlb);
		topLeftPanelLine.add(tlc);
		topLeftPanelLine.add(tld);

		// get Lines for top right panel
		Line rila = new Line(rpb, fpb);
		Line rilb = new Line(fpb, fpc);
		Line rilc = new Line(fpc, rpc);
		Line rild = new Line(rpc, rpb);

		// get Arraylist created to draw top right panel
		ArrayList<Line> topRightPanelLine = new ArrayList<Line>();
		topRightPanelLine.add(rila);
		topRightPanelLine.add(rilb);
		topRightPanelLine.add(rilc);
		topRightPanelLine.add(rild);

		// get Lines for bottom right panel
		Line bla = new Line(rpd, fpd);
		Line blb = new Line(fpd, fpc);
		Line blc = new Line(fpc, rpc);
		Line bld = new Line(rpc, rpd);

		// get Arraylist created to draw bottom right panel
		ArrayList<Line> bottomRightPanelLine = new ArrayList<Line>();
		bottomRightPanelLine.add(bla);
		bottomRightPanelLine.add(blb);
		bottomRightPanelLine.add(blc);
		bottomRightPanelLine.add(bld);

		if (fp.getX() < 500) {
			if (fp.getY() < 400) {
				leftG.setColor(Color.RED);
				FillShape rbs = new FillShape(rearPanelLine, d, 1, leftG);
				leftG.setColor(Color.BLUE);
				FillShape blbs = new FillShape(bottomLeftPanelLine, d, 1, leftG);
				leftG.setColor(Color.BLACK);
				FillShape tlbs = new FillShape(topLeftPanelLine, d, 1, leftG);
				leftG.setColor(Color.CYAN);
				FillShape trbs = new FillShape(topRightPanelLine, d, 1, leftG);
				leftG.setColor(Color.GRAY);
				FillShape brbs = new FillShape(bottomRightPanelLine, d, 1, leftG);
				leftG.setColor(Color.ORANGE);
				FillShape fbs = new FillShape(frontPanelLine, d, 1, leftG);
			} else if (fp.getY() >= 400) {
				leftG.setColor(Color.RED);
				FillShape rbs = new FillShape(rearPanelLine, d, 1, leftG);
				leftG.setColor(Color.BLACK);
				FillShape tlbs = new FillShape(topLeftPanelLine, d, 1, leftG);
				leftG.setColor(Color.CYAN);
				FillShape blbs = new FillShape(bottomLeftPanelLine, d, 1, leftG);
				leftG.setColor(Color.BLUE);
				FillShape brbs = new FillShape(bottomRightPanelLine, d, 1, leftG);
				leftG.setColor(Color.GRAY);
				FillShape trbs = new FillShape(topRightPanelLine, d, 1, leftG);
				leftG.setColor(Color.ORANGE);
				FillShape fbs = new FillShape(frontPanelLine, d, 1, leftG);
			}

		} else if (fp.getX() >= 500) {
			if (fp.getY() < 400) {
				leftG.setColor(Color.RED);
				FillShape rbs = new FillShape(rearPanelLine, d, 1, leftG);
				leftG.setColor(Color.BLUE);
				FillShape brbs = new FillShape(bottomRightPanelLine, d, 1, leftG);
				leftG.setColor(Color.GRAY);
				FillShape trbs = new FillShape(topRightPanelLine, d, 1, leftG);
				leftG.setColor(Color.BLACK);
				FillShape tlbs = new FillShape(topLeftPanelLine, d, 1, leftG);
				leftG.setColor(Color.CYAN);
				FillShape blbs = new FillShape(bottomLeftPanelLine, d, 1, leftG);
				leftG.setColor(Color.ORANGE);
				FillShape fbs = new FillShape(frontPanelLine, d, 1, leftG);

			} else if (fp.getY() >= 400) {
				leftG.setColor(Color.RED);
				FillShape rbs = new FillShape(rearPanelLine, d, 1, leftG);
				leftG.setColor(Color.CYAN);
				FillShape brbs = new FillShape(bottomRightPanelLine, d, 1, leftG);
				leftG.setColor(Color.GRAY);
				FillShape trbs = new FillShape(topRightPanelLine, d, 1, leftG);
				leftG.setColor(Color.BLUE);
				FillShape blbs = new FillShape(bottomLeftPanelLine, d, 1, leftG);
				leftG.setColor(Color.BLACK);
				FillShape tlbs = new FillShape(topLeftPanelLine, d, 1, leftG);
				leftG.setColor(Color.ORANGE);
				FillShape fbs = new FillShape(frontPanelLine, d, 1, leftG);
			}
		}
	}

}
