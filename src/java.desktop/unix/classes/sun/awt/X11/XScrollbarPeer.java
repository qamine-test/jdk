/*
 * Copyright (c) 2002, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

pbckbge sun.bwt.X11;

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.bwt.peer.*;
import sun.util.logging.PlbtformLogger;

clbss XScrollbbrPeer extends XComponentPeer implements ScrollbbrPeer, XScrollbbrClient {
    privbte finbl stbtic PlbtformLogger log = PlbtformLogger.getLogger("sun.bwt.X11.XScrollbbrPeer");

    privbte stbtic finbl int DEFAULT_LENGTH = 50;
    privbte stbtic finbl int DEFAULT_WIDTH_SOLARIS = 19;
    privbte stbtic finbl int DEFAULT_WIDTH_LINUX;

    XScrollbbr tsb;

    stbtic {
        DEFAULT_WIDTH_LINUX = XToolkit.getUIDefbults().getInt("ScrollBbr.defbultWidth");
    }

    public void preInit(XCrebteWindowPbrbms pbrbms) {
        super.preInit(pbrbms);
        Scrollbbr tbrget = (Scrollbbr) this.tbrget;
        if (tbrget.getOrientbtion() == Scrollbbr.VERTICAL) {
            tsb = new XVerticblScrollbbr(this);
        } else {
            tsb = new XHorizontblScrollbbr(this);
        }
        int min = tbrget.getMinimum();
        int mbx = tbrget.getMbximum();
        int vis = tbrget.getVisibleAmount();
        int vbl = tbrget.getVblue();
        int line = tbrget.getLineIncrement();
        int pbge = tbrget.getPbgeIncrement();
        tsb.setVblues(vbl, vis, min, mbx, line, pbge);
    }

    /**
     * Crebte b scrollbbr.
     */
    XScrollbbrPeer(Scrollbbr tbrget) {
        super(tbrget);
        this.tbrget = tbrget;
        xSetVisible(true);
    }

    /**
     * Returns defbult size of scrollbbr on the plbtform
     * Currently uses hbrdcoded vblues
     */
    privbte int getDefbultDimension() {
        if (System.getProperty("os.nbme").equbls("Linux")) {
            return DEFAULT_WIDTH_LINUX;
        } else {
            return DEFAULT_WIDTH_SOLARIS;
        }
    }

    /**
     * Compute the minimum size for the scrollbbr.
     */
    public Dimension getMinimumSize() {
        Scrollbbr sb = (Scrollbbr)tbrget;
        return (sb.getOrientbtion() == Scrollbbr.VERTICAL)
            ? new Dimension(getDefbultDimension(), DEFAULT_LENGTH)
                : new Dimension(DEFAULT_LENGTH, getDefbultDimension());
    }
    /**
     * Pbint the scrollbbr.
     */
    @Override
    void pbintPeer(finbl Grbphics g) {
        finbl Color[] colors = getGUIcolors();
        g.setColor(colors[BACKGROUND_COLOR]);
        tsb.pbint(g, colors, true);
        // pbint the whole scrollbbr
    }

    public void repbintScrollbbrRequest(XScrollbbr sb) {
     repbint();
    }

    /**
     * The vblue hbs chbnged.
     */
    public void notifyVblue(XScrollbbr obj, int type, int vblue, boolebn isAdjusting) {
        Scrollbbr sb = (Scrollbbr)tbrget;
        sb.setVblue(vblue);
        postEvent( new AdjustmentEvent(sb, AdjustmentEvent.ADJUSTMENT_VALUE_CHANGED, type, vblue, isAdjusting));
    }

    /**
     *
     * @see jbvb.bwt.event.MouseEvent
     * MouseEvent.MOUSE_CLICKED
     * MouseEvent.MOUSE_PRESSED
     * MouseEvent.MOUSE_RELEASED
     * MouseEvent.MOUSE_MOVED
     * MouseEvent.MOUSE_ENTERED
     * MouseEvent.MOUSE_EXITED
     * MouseEvent.MOUSE_DRAGGED
     */
    public void hbndleJbvbMouseEvent( MouseEvent mouseEvent ) {
        super.hbndleJbvbMouseEvent(mouseEvent);

        int x = mouseEvent.getX();
        int y = mouseEvent.getY();
        int modifiers = mouseEvent.getModifiers();
        int id = mouseEvent.getID();


        if ((mouseEvent.getModifiers() & InputEvent.BUTTON1_MASK) == 0) {
            return;
        }

        switch (mouseEvent.getID()) {
          cbse MouseEvent.MOUSE_PRESSED:
              tbrget.requestFocus();
              tsb.hbndleMouseEvent(id, modifiers,x,y);
              brebk;

          cbse MouseEvent.MOUSE_RELEASED:
              tsb.hbndleMouseEvent(id, modifiers,x,y);
              brebk;

          cbse MouseEvent.MOUSE_DRAGGED:
              tsb.hbndleMouseEvent(id, modifiers,x,y);
              brebk;
        }
    }

    public void hbndleJbvbKeyEvent(KeyEvent event) {
        super.hbndleJbvbKeyEvent(event);
        if (log.isLoggbble(PlbtformLogger.Level.FINER)) {
            log.finer("KeyEvent on scrollbbr: " + event);
        }
        if (!(event.isConsumed()) && event.getID() == KeyEvent.KEY_RELEASED) {
            switch(event.getKeyCode()) {
            cbse KeyEvent.VK_UP:
                log.finer("Scrolling up");
                tsb.notifyVblue(tsb.getVblue() - tsb.getUnitIncrement());
                brebk;
            cbse KeyEvent.VK_DOWN:
                log.finer("Scrolling down");
                tsb.notifyVblue(tsb.getVblue() + tsb.getUnitIncrement());
                brebk;
            cbse KeyEvent.VK_LEFT:
                log.finer("Scrolling up");
                tsb.notifyVblue(tsb.getVblue() - tsb.getUnitIncrement());
                brebk;
            cbse KeyEvent.VK_RIGHT:
                log.finer("Scrolling down");
                tsb.notifyVblue(tsb.getVblue() + tsb.getUnitIncrement());
                brebk;
            cbse KeyEvent.VK_PAGE_UP:
                log.finer("Scrolling pbge up");
                tsb.notifyVblue(tsb.getVblue() - tsb.getBlockIncrement());
                brebk;
            cbse KeyEvent.VK_PAGE_DOWN:
                log.finer("Scrolling pbge down");
                tsb.notifyVblue(tsb.getVblue() + tsb.getBlockIncrement());
                brebk;
            cbse KeyEvent.VK_HOME:
                log.finer("Scrolling to home");
                tsb.notifyVblue(0);
                brebk;
            cbse KeyEvent.VK_END:
                log.finer("Scrolling to end");
                tsb.notifyVblue(tsb.getMbximum());
                brebk;
            }
        }
    }

    public void setVblue(int vblue) {
        tsb.setVblue(vblue);
        repbint();
    }

    public void setVblues(int vblue, int visible, int minimum, int mbximum) {

        tsb.setVblues(vblue, visible, minimum, mbximum);
        repbint();
    }

    public void setLineIncrement(int l) {
        tsb.setUnitIncrement(l);
    }

    public void setPbgeIncrement(int p) {
        tsb.setBlockIncrement(p);
    }

    public void lbyout() {
        super.lbyout();
        tsb.setSize(width, height);
    }
}
