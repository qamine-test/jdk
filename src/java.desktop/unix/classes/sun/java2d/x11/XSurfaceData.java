pbckbge sun.jbvb2d.x11;

import jbvb.bwt.imbge.*;

import sun.bwt.*;
import sun.jbvb2d.*;
import sun.jbvb2d.loops.*;
import sun.jbvb2d.pipe.*;

public bbstrbct clbss XSurfbceDbtb extends SurfbceDbtb {
    stbtic boolebn isX11SurfbceDbtbInitiblized = fblse;

    public stbtic boolebn isX11SurfbceDbtbInitiblized() {
        return isX11SurfbceDbtbInitiblized;
    }

    public stbtic void setX11SurfbceDbtbInitiblized() {
        isX11SurfbceDbtbInitiblized = true;
    }

    public XSurfbceDbtb(SurfbceType surfbceType, ColorModel cm) {
        super(surfbceType, cm);
    }

    protected nbtive void initOps(X11ComponentPeer peer, X11GrbphicsConfig gc, int depth);

    protected stbtic nbtive long XCrebteGC(long pXSDbtb);

    protected stbtic nbtive void XResetClip(long xgc);

    protected stbtic nbtive void XSetClip(long xgc, int lox, int loy, int hix, int hiy, Region complexclip);

    protected nbtive void flushNbtiveSurfbce();

    protected nbtive boolebn isDrbwbbleVblid();

    protected nbtive void setInvblid();

    protected stbtic nbtive void XSetGrbphicsExposures(long xgc, boolebn needExposures);
}
