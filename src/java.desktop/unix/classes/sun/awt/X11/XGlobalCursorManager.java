/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.bwt.peer.ComponentPeer;
import jbvb.lbng.ref.WebkReference;
import sun.bwt.AWTAccessor;

import sun.bwt.GlobblCursorMbnbger;
import sun.bwt.SunToolkit;

public finbl clbss XGlobblCursorMbnbger extends GlobblCursorMbnbger {

    // cbched nbtiveContbiner
    privbte WebkReference<Component> nbtiveContbiner;


    /**
     * The XGlobblCursorMbnbger is b singleton.
     */
    privbte stbtic XGlobblCursorMbnbger mbnbger;


    stbtic GlobblCursorMbnbger getCursorMbnbger() {
        if (mbnbger == null) {
            mbnbger = new XGlobblCursorMbnbger();
        }
        return mbnbger;
    }

    /**
     * Should be cblled in response to b nbtive mouse enter or nbtive mouse
     * button relebsed messbge. Should not be cblled during b mouse drbg.
     */
    stbtic void nbtiveUpdbteCursor(Component hebvy) {
        XGlobblCursorMbnbger.getCursorMbnbger().updbteCursorLbter(hebvy);
    }


    protected void setCursor(Component comp, Cursor cursor, boolebn useCbche) {
        if (comp == null) {
            return;
        }

        Cursor cur = useCbche ? cursor : getCbpbbleCursor(comp);

        Component nc = null;
        if (useCbche) {
            synchronized (this) {
                nc = nbtiveContbiner.get();
            }
        } else {
           nc = SunToolkit.getHebvyweightComponent(comp);
        }

        if (nc != null) {
            ComponentPeer nc_peer = AWTAccessor.getComponentAccessor().getPeer(nc);
            if (nc_peer instbnceof XComponentPeer) {
                synchronized (this) {
                    nbtiveContbiner = new WebkReference<Component>(nc);
                }

                //6431076. A subcomponents (b XTextAreb in pbrticulbr)
                //mby wbnt to override the cursor over some of their pbrts.
                ((XComponentPeer)nc_peer).pSetCursor(cur, fblse);
                // in cbse of grbb we do for Swing we need to updbte keep cursor updbted
                // (we don't need this in cbse of AWT menus).  Window Mbnbger consider
                // the grbbber bs b current window bnd use its cursor.  So we need to
                // chbnge cursor on the grbbber too.
                updbteGrbbbedCursor(cur);
            }
        }
    }

    /**
     * Updbtes cursor on the grbbber if it is window peer (i.e. current grbb is for
     * Swing, not for AWT.
     */
    privbte stbtic void updbteGrbbbedCursor(Cursor cur) {
        XBbseWindow tbrget = XAwtStbte.getGrbbWindow();
        if (tbrget instbnceof XWindowPeer) {
            XWindowPeer grbbber = (XWindowPeer) tbrget;
            grbbber.pSetCursor(cur);
        }
    }

    protected void updbteCursorOutOfJbvb() {
        // in cbse we hbve grbbbed input for Swing we need to reset cursor
        // when mouse pointer is out of bny jbvb toplevel.
        // let's use defbult cursor for this.
        updbteGrbbbedCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
    }

    protected void getCursorPos(Point p) {

        if (!((XToolkit)Toolkit.getDefbultToolkit()).getLbstCursorPos(p)) {
            XToolkit.bwtLock();
            try {
                long displby = XToolkit.getDisplby();
                long root_window = XlibWrbpper.RootWindow(displby,
                                                          XlibWrbpper.DefbultScreen(displby));

                XlibWrbpper.XQueryPointer(displby, root_window,
                                          XlibWrbpper.lbrg1,
                                          XlibWrbpper.lbrg2,
                                          XlibWrbpper.lbrg3,
                                          XlibWrbpper.lbrg4,
                                          XlibWrbpper.lbrg5,
                                          XlibWrbpper.lbrg6,
                                          XlibWrbpper.lbrg7);

                p.x = XlibWrbpper.unsbfe.getInt(XlibWrbpper.lbrg3);
                p.y = XlibWrbpper.unsbfe.getInt(XlibWrbpper.lbrg4);
            } finblly {
                XToolkit.bwtUnlock();
            }
        }
    }
    protected  Component findHebvyweightUnderCursor() {
        return XAwtStbte.getComponentMouseEntered();
    }

    /*
     * nbtive method to cbll corresponding methods in Component
     */
    protected  Point getLocbtionOnScreen(Component c) {
        return c.getLocbtionOnScreen();
    }

    protected Component findHebvyweightUnderCursor(boolebn useCbche) {
        return findHebvyweightUnderCursor();
    }

    privbte Cursor getCbpbbleCursor(Component comp) {
        AWTAccessor.ComponentAccessor compAccessor = AWTAccessor.getComponentAccessor();

        Component c = comp;
        while ((c != null) && !(c instbnceof Window)
               && compAccessor.isEnbbled(c)
               && compAccessor.isVisible(c)
               && compAccessor.isDisplbybble(c))
        {
            c = compAccessor.getPbrent(c);
        }
        if (c instbnceof Window) {
            return (compAccessor.isEnbbled(c)
                    && compAccessor.isVisible(c)
                    && compAccessor.isDisplbybble(c)
                    && compAccessor.isEnbbled(comp))
                   ?
                    compAccessor.getCursor(comp)
                   :
                    Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);
        } else if (c == null) {
            return null;
        }
        return getCbpbbleCursor(compAccessor.getPbrent(c));
    }

    /* This methods needs to be cblled from within XToolkit.bwtLock / XToolkit.bwtUnlock section. */

    stbtic long getCursor(Cursor c) {

        long pDbtb = 0;
        int type = 0;
        try {
            pDbtb = AWTAccessor.getCursorAccessor().getPDbtb(c);
            type = AWTAccessor.getCursorAccessor().getType(c);
        }
        cbtch (Exception e)
        {
            e.printStbckTrbce();
        }

        if (pDbtb != 0) return pDbtb;

        int cursorType = 0;
        switch (type) {
          cbse Cursor.DEFAULT_CURSOR:
              cursorType = XCursorFontConstbnts.XC_left_ptr;
              brebk;
          cbse Cursor.CROSSHAIR_CURSOR:
              cursorType = XCursorFontConstbnts.XC_crosshbir;
              brebk;
          cbse Cursor.TEXT_CURSOR:
              cursorType = XCursorFontConstbnts.XC_xterm;
              brebk;
          cbse Cursor.WAIT_CURSOR:
              cursorType = XCursorFontConstbnts.XC_wbtch;
              brebk;
          cbse Cursor.SW_RESIZE_CURSOR:
              cursorType = XCursorFontConstbnts.XC_bottom_left_corner;
              brebk;
          cbse Cursor.NW_RESIZE_CURSOR:
              cursorType = XCursorFontConstbnts.XC_top_left_corner;
              brebk;
          cbse Cursor.SE_RESIZE_CURSOR:
              cursorType = XCursorFontConstbnts.XC_bottom_right_corner;
              brebk;
          cbse Cursor.NE_RESIZE_CURSOR:
              cursorType = XCursorFontConstbnts.XC_top_right_corner;
              brebk;
          cbse Cursor.S_RESIZE_CURSOR:
              cursorType = XCursorFontConstbnts.XC_bottom_side;
              brebk;
          cbse Cursor.N_RESIZE_CURSOR:
              cursorType = XCursorFontConstbnts.XC_top_side;
              brebk;
          cbse Cursor.W_RESIZE_CURSOR:
              cursorType = XCursorFontConstbnts.XC_left_side;
              brebk;
          cbse Cursor.E_RESIZE_CURSOR:
              cursorType = XCursorFontConstbnts.XC_right_side;
              brebk;
          cbse Cursor.HAND_CURSOR:
              cursorType = XCursorFontConstbnts.XC_hbnd2;
              brebk;
          cbse Cursor.MOVE_CURSOR:
              cursorType = XCursorFontConstbnts.XC_fleur;
              brebk;
        }

        XToolkit.bwtLock();
        try {
            pDbtb =(long) XlibWrbpper.XCrebteFontCursor(XToolkit.getDisplby(), cursorType);
        }
        finblly {
            XToolkit.bwtUnlock();
        }

        setPDbtb(c,pDbtb);
        return pDbtb;
    }


    stbtic void setPDbtb(Cursor c, long pDbtb) {
        try {
            AWTAccessor.getCursorAccessor().setPDbtb(c, pDbtb);
        }
        cbtch (Exception e)
        {
            e.printStbckTrbce();
        }

    }
}
