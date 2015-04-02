package fillingShape;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import edgeCutting.Line;

public class FillShape {

	// private ArrayList<Line> allLines = new ArrayList<Line>();

	private EdgeTable et;
	private ActiveEdgeList ael;
	private Dimension d;
	private int resoParameter;
	private Graphics g;
	private ArrayList<EdgeTableComponent> fillPair = new ArrayList<EdgeTableComponent>();
	private ArrayList<EdgeTableComponent> currentPair = new ArrayList<EdgeTableComponent>();

	// this constructor needs to input
	// allLines -> All lines which form the shape
	// d -> Dimension of the screen
	// resoParameter -> The resolution Parameter when select Low, Medium or High
	// resolution
	// g -> Used to draw the pixels
	public FillShape(ArrayList<Line> allLines, Dimension d, int resoParameter,
			Graphics g) {

		et = new EdgeTable(allLines, d);
		System.out.println("Edge Table Created");

		// create Edge Table , mapping Y value with cooresponding EdgeTable
		// Components
		HashMap<Integer, Stack<EdgeTableComponent>> hm = et.classifyLines();

		ael = new ActiveEdgeList(hm, d, resoParameter);
		System.out.println("Active Edge List Created");

		this.d = d;
		this.resoParameter = resoParameter;
		this.g = g;

		scanET();

	}

	// this is to go through ET and find the lines (ETC) which currently is
	// scanned
	public void scanET() {
		// need to modify

		ArrayList<EdgeTableComponent> currentAEL = new ArrayList<EdgeTableComponent>();

		// scanLine will move by the interval of resoParameter
		// for (int i = 0; i < d.height; i += resoParameter) {
		for (int i = 0; i < d.height; i++) {
			currentAEL = ael.fitAEL(i);

			currentAEL = ael.rearrangeETC(currentAEL, i);

			System.out.println("Filling line " + i);

			fillETCPair(g, i, currentAEL);

		}
	}

	// this is to group up each ETCs to create fill area
	public void fillETCPair(Graphics g, int scanLine,
			ArrayList<EdgeTableComponent> currentAEL) {

		// filling area indicator
		boolean fillFlag = false;

		// reach the Y max indicator
		boolean topFlag = true;

		// System.out.println(currentAEL.size());
		for (int i = 0; i < currentAEL.size(); i++) {

			// check if it is a filling area
			if (i % 2 != 0) {
				fillFlag = false;
			} else {
				fillFlag = true;
			}
			System.out.println(" " + (scanLine % resoParameter));

			// if (fillFlag) {
			// int xStart = ((EdgeTableComponent) currentAEL.get(i))
			// .calculateX(scanLine);
			//
			// int xEnd = ((EdgeTableComponent) currentAEL.get(i + 1))
			// .calculateX(scanLine);
			//
			// System.out.println("xStart " + xStart + " xEnd " + xEnd);
			// }

			if (fillFlag) {

				int xStart = ((EdgeTableComponent) currentAEL.get(i))
						.calculateX(scanLine);

				int xEnd = ((EdgeTableComponent) currentAEL.get(i + 1))
						.calculateX(scanLine);

				System.out.println("xStart " + xStart + " xEnd " + xEnd);

				// check if reach the y max of both Line (ETC)
				if (ActiveEdgeList.checkIfETCReachLimit(scanLine,
						(EdgeTableComponent) currentAEL.get(i))
						&& ActiveEdgeList.checkIfETCReachLimit(scanLine,
								(EdgeTableComponent) currentAEL.get(i + 1))) {

					topFlag = false;
				} else {
					topFlag = true;
				}

				if (scanLine % resoParameter == 0) {
					System.out.println("Current fill flag is " + fillFlag);
					if (topFlag) {
						xStart = calculateSolutionStartXValue(xStart,
								resoParameter);
						xEnd = calculateSolutionEndXValue(xEnd, resoParameter);

						System.out
								.println("xStart " + xStart + " xEnd " + xEnd);
//						g.setColor(Color.BLUE);

						for (int j = xStart; j < xEnd; j += resoParameter) {
							g.drawOval(j, CoordinateTransformer
									.calculateFrameY(d, scanLine), 3, 3);

						}
						System.out.println("Filled Line " + i);
					}
				}
			}
		}
	}

	// this is to give the cloeset x Start value on Resolution selected
	public int calculateSolutionStartXValue(int xStart, int resoParameter) {

		int j = xStart / resoParameter;

		xStart = (j + 1) * resoParameter;

		return xStart;
	}

	// this is to give the cloeset x End value on Resolution selected
	public int calculateSolutionEndXValue(int xEnd, int resoParameter) {

		int j = xEnd / resoParameter;

		xEnd = (j + 1) * resoParameter;

		return xEnd;
	}

}
