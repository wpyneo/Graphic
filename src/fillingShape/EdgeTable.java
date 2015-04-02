package fillingShape;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import edgeCutting.Line;

public class EdgeTable {

	private Line[] lineList = null; // store lines which has greater start
									// point.
	Dimension d = null;
	// private Stack<EdgeTableComponent> etcGroup = new
	// Stack<EdgeTableComponent>();
	// private Stack[] totalNumberOfETCGroup = null;
	private HashMap<Integer, Stack<EdgeTableComponent>> allETCGroup = new HashMap<Integer, Stack<EdgeTableComponent>>();

	private int maxYOfShape = 0;

	public EdgeTable(ArrayList<Line> ll, Dimension d) {

		lineList = sortLinesAgain(sortLines(ll));
		this.d = d;

		// totalNumberOfETCGroup = setTotalNumberOfETCGroup(lineList);
		// classifyLines();

	}

	// this is to go through all the lines create, make sure lines start point
	// has greater Y value, end point has smaller Y value
	public Line[] sortLines(ArrayList<Line> lineList) {

		Object[] lines = lineList.toArray();
		// ArrayList<Line> newLineList = new ArrayList<Line>();
		Line[] newLines = new Line[lines.length];

		// compare the start point and end point, if end point got greater Y
		// value, swap the points
		for (int i = 0; i < lines.length; i++) {
			newLines[i] = (Line) lines[i];
			if (newLines[i].getEndPoint().getY() > newLines[i].getStartPoint()
					.getY()) {
				newLines[i] = new Line(newLines[i].getEndPoint(),
						newLines[i].getStartPoint());
			}
			// newLineList.add(newLines[i]);
		}
		return newLines;
	}

	// this is to sort all lines by end point y value ascending order. If same
	// then check the start point y value
	public Line[] sortLinesAgain(Line[] newLines) {

		Line spare = null;
		for (int i = 0; i < newLines.length; i++) {
			for (int j = 0; j < newLines.length; j++) {
				if (newLines[i].getEndPoint().getY() < newLines[j]
						.getEndPoint().getY()) {
					spare = newLines[i];
					newLines[i] = newLines[j];
					newLines[j] = spare;

				} else if (newLines[i].getEndPoint().getY() == newLines[j]
						.getEndPoint().getY()) {
					if (newLines[i].getStartPoint().getY() < newLines[j]
							.getStartPoint().getY()) {
						spare = newLines[i];
						newLines[i] = newLines[j];
						newLines[j] = spare;
					}
				}
			}
		}

		for (int i = 0; i < newLines.length; i++) {
			System.out.println("(" + newLines[i].getStartPoint().getX() + ", "
					+ newLines[i].getStartPoint().getY() + ") to ("
					+ newLines[i].getEndPoint().getX() + ", "
					+ newLines[i].getEndPoint().getY() + ")");
		}

		return newLines;
	}

	// this is to create an array which could hold the all ETC group.
	// NOTE that this only apply to a shape which for each 1 point, only 2 lines
	// can be involved
	public Stack[] setTotalNumberOfETCGroup(Line[] lineList) {

		Line[] lines = lineList;
		int linesShareSameEndPoint = 0;
		int singleLineGroupCount = 0;
		int etcGroupCount = 0;

		for (int i = 0; i < lines.length; i++) {
			for (int j = 0; j < lines.length; j++) {
				// check to make sure NOT to compare a line with itself
				if (lines[i].CompareLine(lines[j]) == false) {
					// check if 2 different lines has same end point. If
					// yes, then this should belongs to same ETC group
					if (lines[i].getEndPoint().getY() == lines[j].getEndPoint()
							.getY()
							&& lines[i].getEndPoint().getX() == lines[j]
									.getEndPoint().getX()) {
						// count total number of lines which share the same
						// start point or end point.
						// It cross check each line with others, so it will
						// count both lines.
						linesShareSameEndPoint++;
					}
				}
			}
		}

		// this is to calculate the number of etc group which only contain 1
		// line.
		singleLineGroupCount = lines.length - linesShareSameEndPoint;
		// this is to calculate total etcGroup needed, groups contain single
		// line and groups contain multiple line.
		etcGroupCount = singleLineGroupCount + linesShareSameEndPoint / 2;

		System.out.println(lines.length);
		System.out.println(linesShareSameEndPoint);
		System.out.println(etcGroupCount);

		Stack[] totalETCGroup = new Stack[etcGroupCount];

		return totalETCGroup;

	}

	// this is to classify lines into related ETC group.
	// public Stack[] classifyLines() {
	public HashMap<Integer, Stack<EdgeTableComponent>> classifyLines() {

		EdgeTableComponent[] etcg = new EdgeTableComponent[lineList.length];

		// this is to create ETC object for each line
		for (int i = 0; i < lineList.length; i++) {
			EdgeTableComponent etc = new EdgeTableComponent(lineList[i],
					(int) lineList[i].getStartPoint().getY(), (int) lineList[i]
							.getEndPoint().getX(), lineList[i].getGradient(),
					lineList[i].getEndPoint());
			etcg[i] = etc;
		}

		// this is to determine which 2 etc object should be classified into
		// same etcGroup

		int h = 0;
		for (int i = 0; i < etcg.length; i++) {
			for (int j = 0; j < etcg.length; j++) {
				if (etcg[i].compareEndPoint(etcg[j])
						&& (etcg[i].isItself(etcg[j]) != true && (etcg[i]
								.isGrouped() != true))) {
					// if both lines share same end points, then add both into
					// ETCGroup
					Stack<EdgeTableComponent> etcGroup = new Stack<EdgeTableComponent>();
					etcGroup.add(etcg[i]);
					etcGroup.add(etcg[j]);

					etcg[i].setGrouped(true);
					etcg[j].setGrouped(true);

					// totalNumberOfETCGroup[h] = etcGroup;
					// create hashmap to map the etcGroup with it's current end
					// point Y value
					allETCGroup.put((int) etcg[i].getEndPoint().getY(),
							etcGroup);

					System.out.println(h + ": group addeded");
					System.out.println(i + " " + j);
					h++;

				} else if ((etcg[i].isGrouped() != true)
						&& (j == (etcg.length - 1))) {
					// && (etcg[i].isItself(etcg[j]) != true)
					// if line is not share same end points with others but is
					// not yet grouped, add single line into ETCgroup
					// must compare all etc then decide if this is a single etc
					// group
					Stack<EdgeTableComponent> etcGroup = new Stack<EdgeTableComponent>();
					etcGroup.add(etcg[i]);
					etcg[i].setGrouped(true);
					// totalNumberOfETCGroup[h] = etcGroup;

					// create hashmap to map the etcGroup with it's current end
					// point Y value
					allETCGroup.put((int) etcg[i].getEndPoint().getY(),
							etcGroup);

					System.out.println(h + ": line addeded");
					System.out.println(i + " " + j);
					h++;

				} else if ((etcg[i].isGrouped() == true)) {
					// if line is not share same end points but already grouped,
					// just pass
					System.out.println(i + " Already grouped");
					System.out.println(i + " " + j);

				} else if (etcg[i].isItself(etcg[j]) == true) {
					// if the line is itself
					// just pass
					System.out.println(i + " is itself");
					System.out.println(i + " " + j);
				} else {

					System.out.println("nothing added");
					System.out.println(i + " " + j);
				}
			}
		}

		// return totalNumberOfETCGroup;
		return allETCGroup;

	}

}
