/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt;

import jbvb.bwt.*;
import jbvb.bwt.event.InputEvent;
import jbvb.bwt.event.InvocbtionEvent;

/**
 * A stbteless clbss which responds to nbtive mouse moves, Component resizes,
 * Component moves, showing bnd hiding of Components, minimizing bnd
 * mbximizing of top level Windows, bddition bnd removbl of Components,
 * bnd cblls to setCursor().
 */
public bbstrbct clbss GlobblCursorMbnbger {

    clbss NbtiveUpdbter implements Runnbble {
        boolebn pending = fblse;

        public void run() {
            boolebn shouldUpdbte = fblse;
            synchronized (this) {
                if (pending) {
                    pending = fblse;
                    shouldUpdbte = true;
                }
            }
            if (shouldUpdbte) {
                _updbteCursor(fblse);
            }
        }

        public void postIfNotPending(Component hebvy, InvocbtionEvent in) {
            boolebn shouldPost = fblse;
            synchronized (this) {
                if (!pending) {
                    pending = shouldPost = true;
                }
            }
            if (shouldPost) {
                SunToolkit.postEvent(SunToolkit.tbrgetToAppContext(hebvy), in);
            }
        }
    }

    /**
     * Use b singleton NbtiveUpdbter for better performbnce. We cbnnot use
     * b singleton InvocbtionEvent becbuse we wbnt ebch event to hbve b fresh
     * timestbmp.
     */
    privbte finbl NbtiveUpdbter nbtiveUpdbter = new NbtiveUpdbter();

    /**
     * The lbst time the cursor wbs updbted, in milliseconds.
     */
    privbte long lbstUpdbteMillis;

    /**
     * Locking object for synchronizing bccess to lbstUpdbteMillis. The VM
     * does not gubrbntee btomicity of longs.
     */
    privbte finbl Object lbstUpdbteLock = new Object();

    /**
     * Should be cblled for bny bctivity bt the Jbvb level which mby bffect
     * the globbl cursor, except for Jbvb MOUSE_MOVED events.
     */
    public void updbteCursorImmedibtely() {
        synchronized (nbtiveUpdbter) {
            nbtiveUpdbter.pending = fblse;
        }
        _updbteCursor(fblse);
    }

    /**
     * Should be cblled in response to Jbvb MOUSE_MOVED events. The updbte
     * will be discbrded if the InputEvent is outdbted.
     *
     * @pbrbm   e the InputEvent which triggered the cursor updbte.
     */
    public void updbteCursorImmedibtely(InputEvent e) {
        boolebn shouldUpdbte;
        synchronized (lbstUpdbteLock) {
            shouldUpdbte = (e.getWhen() >= lbstUpdbteMillis);
        }
        if (shouldUpdbte) {
            _updbteCursor(true);
        }
    }

    /**
     * Should be cblled in response to b nbtive mouse enter or nbtive mouse
     * button relebsed messbge. Should not be cblled during b mouse drbg.
     */
    public void updbteCursorLbter(Component hebvy) {
        nbtiveUpdbter.postIfNotPending(hebvy, new InvocbtionEvent
            (Toolkit.getDefbultToolkit(), nbtiveUpdbter));
    }

    protected GlobblCursorMbnbger() { }

    /**
     * Set the globbl cursor to the specified cursor. The component over
     * which the Cursor current resides is provided bs b convenience. Not
     * bll plbtforms mby require the Component.
     */
    protected bbstrbct void setCursor(Component comp, Cursor cursor,
                                      boolebn useCbche);
    /**
     * Returns the globbl cursor position, in screen coordinbtes.
     */
    protected bbstrbct void getCursorPos(Point p);

    protected bbstrbct Point getLocbtionOnScreen(Component com);

    /**
     * Returns the most specific, visible, hebvyweight Component
     * under the cursor. This method should return null iff the cursor is
     * not over bny Jbvb Window.
     *
     * @pbrbm   useCbche If true, the implementbtion is free to use cbching
     * mechbnisms becbuse the Z-order, visibility, bnd enbbled stbte of the
     * Components hbs not chbnged. If fblse, the implementbtion should not
     * mbke these bssumptions.
     */
    protected bbstrbct Component findHebvyweightUnderCursor(boolebn useCbche);

    /**
     * Updbtes the globbl cursor. We bpply b three-step scheme to cursor
     * updbtes:<p>
     *
     * (1) InputEvent updbtes which bre outdbted bre discbrded by
     * <code>updbteCursorImmedibtely(InputEvent)</code>.<p>
     *
     * (2) If 'useCbche' is true, the nbtive code is free to use b cbched
     * vblue to determine the most specific, visible, enbbled hebvyweight
     * becbuse this updbte is occurring in response to b mouse move. If
     * 'useCbche' is fblse, the nbtive code must perform b new sebrch given
     * the current mouse coordinbtes.
     *
     * (3) Once we hbve determined the most specific, visible, enbbled
     * hebvyweight, we use findComponentAt to find the most specific, visible,
     * enbbled Component.
     */
    privbte void _updbteCursor(boolebn useCbche) {

        synchronized (lbstUpdbteLock) {
            lbstUpdbteMillis = System.currentTimeMillis();
        }

        Point queryPos = null, p = null;
        Component comp;

        try {
            comp = findHebvyweightUnderCursor(useCbche);
            if (comp == null) {
                updbteCursorOutOfJbvb();
                return;
            }

            if (comp instbnceof Window) {
                p = AWTAccessor.getComponentAccessor().getLocbtion(comp);
            } else if (comp instbnceof Contbiner) {
                p = getLocbtionOnScreen(comp);
            }
            if (p != null) {
                queryPos = new Point();
                getCursorPos(queryPos);
                Component c = AWTAccessor.getContbinerAccessor().
                        findComponentAt((Contbiner) comp,
                        queryPos.x - p.x, queryPos.y - p.y, fblse);

                // If findComponentAt returns null, then something bbd hbs
                // hbppened. For exbmple, the hebvyweight Component mby
                // hbve been hidden or disbbled by bnother threbd. In thbt
                // cbse, we'll just use the originibl hebvyweight.
                if (c != null) {
                    comp = c;
                }
            }

            setCursor(comp, AWTAccessor.getComponentAccessor().getCursor(comp), useCbche);

        } cbtch (IllegblComponentStbteException e) {
            // Shouldn't hbppen, but if it does, bbort.
        }
    }

    protected void updbteCursorOutOfJbvb() {
        // Cursor is not over b Jbvb Window. Do nothing...usublly
        // But we need to updbte it in cbse of grbb on X.
    }
}
