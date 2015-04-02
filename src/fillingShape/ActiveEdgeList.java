package fillingShape;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class ActiveEdgeList {

	private HashMap<Integer, Stack<EdgeTableComponent>> allETCGroup = new HashMap<Integer, Stack<EdgeTableComponent>>();
	private ArrayList<EdgeTableComponent> currentAEL = new ArrayList<EdgeTableComponent>();
	private Dimension d = new Dimension();
	private int resoParameter;

	// private ArrayList<EdgeTableComponent> currentAEL = new
	// ArrayList<EdgeTableComponent>();

	public ActiveEdgeList(HashMap<Integer, Stack<EdgeTableComponent>> hm,
			Dimension d, int resoPara) {

		allETCGroup = hm;
		this.d = d;
		resoParameter = resoPara;
	}

	// this is to search the HashMap based on the scanLine, when the
	// cooresponding y value is found in hashmap, return the value ETC Stack and
	// store ETC into ArrayList
	public ArrayList<EdgeTableComponent> fitAEL(int scanLine) {

		// ArrayList<EdgeTableComponent> currentAEL = new
		// ArrayList<EdgeTableComponent>();
		Stack<EdgeTableComponent> etcGroup;

		if (allETCGroup.containsKey(scanLine)) {

			etcGroup = allETCGroup.get(scanLine);
			System.out.println(etcGroup.size());

			if (etcGroup.size() > 1) {

//				// clean current AEL
//				if (currentAEL.size() == 0) {
//					currentAEL = new ArrayList<EdgeTableComponent>();
//				}

				for (int i = 0; i < etcGroup.size() + 1; i++) {
					// insert ETC into AEL
					currentAEL.add((EdgeTableComponent) etcGroup.pop());
					System.out.println("Current AEL contain "
							+ currentAEL.size() + " objects");
				}

			} else {
				// check if reaching a line's y max, also if hashMap still
				// contains the
				// corresponding etcGroup, then swap the line reaching y max to
				// new line
				for (int j = 0; j < currentAEL.size(); j++) {
					if (checkIfETCReachLimit(scanLine, currentAEL.get(j))
							&& allETCGroup.containsKey(scanLine)) {

						etcGroup = allETCGroup.get(scanLine);
						currentAEL.set(j, etcGroup.pop());
					}
				}
			}
		}

		return currentAEL;
	}

	// this is to check if the scanLine had reached the y max of a line (ETC)
	// this is to check if the scanline had reach the max of an line (ETC)
	public static boolean checkIfETCReachLimit(int scanLine, EdgeTableComponent etc) {
		if (etc.getyMax() <= scanLine) {
			return true;
		}

		return false;
	}

	// this is to rearrange a current AEL to sort x by ascending order
	// this is to re-arrange the line of the input AEL
	public ArrayList<EdgeTableComponent> rearrangeETC(
			ArrayList<EdgeTableComponent> al, int scanLine) {

		ArrayList<EdgeTableComponent> newCurrentAEL = new ArrayList<EdgeTableComponent>();

		Object[] temp = al.toArray();

		// sorting all ETC by X value calculated by current Y value
		if (temp.length > 1) {
			for (int i = 0; i < temp.length; i++) {
				for (int j = i; j < temp.length; j++) {

					EdgeTableComponent tempETC;

					if (((EdgeTableComponent) temp[i]).calculateX(scanLine) > ((EdgeTableComponent) temp[j])
							.calculateX(scanLine)) {

						tempETC = (EdgeTableComponent) temp[i];
						temp[i] = (EdgeTableComponent) temp[j];
						temp[j] = tempETC;
					}
				}
			}
		}

		//
		for (int i = 0; i < temp.length; i++) {

			newCurrentAEL.add((EdgeTableComponent) temp[i]);
			System.out.print(((EdgeTableComponent) temp[i])
					.calculateX(scanLine) + " ");
		}
		System.out.println("");

		return newCurrentAEL;
	}

	// this is to add new ETCs into AEL
	public ArrayList<EdgeTableComponent> insertNewETC(
			ArrayList<EdgeTableComponent> al,
			Stack<EdgeTableComponent> newETCs, int scanLine) {

		for (int i = 0; i < newETCs.size(); i++) {
			al.add((EdgeTableComponent) newETCs.pop());
		}

		rearrangeETC(al, scanLine);

		return al;
	}

}
