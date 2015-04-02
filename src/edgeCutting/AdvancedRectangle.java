package edgeCutting;

import java.awt.Rectangle;

public class AdvancedRectangle extends Rectangle {

	private boolean ct = false;
	private boolean cb = false;
	private boolean cr = false;
	private boolean cl = false;

	// based on Cohen-Sutherland algorithm, for each area of the window, there
	// is a code to define them. The order is C top, C buttom, C right, C left.
	
	// to define a window area, an AreaCode is needed to be setup
	public void setAreaCode(boolean t, boolean b, boolean r, boolean l) {
		this.ct = t;
		this.cb = b;
		this.cr = r;
		this.cl = l;
	}

	public boolean[] getAreaCode() {
		return new boolean[] { ct, cb, cr, cl };
	}
}
