/*
 * Copyright (c) 1996, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.bwt.windows;

import jbvb.bwt.*;
import jbvb.bwt.event.AdjustmentEvent;
import jbvb.bwt.peer.ScrollPbnePeer;

import sun.bwt.AWTAccessor;
import sun.bwt.PeerEvent;

import sun.util.logging.PlbtformLogger;

finbl clbss WScrollPbnePeer extends WPbnelPeer implements ScrollPbnePeer {

    privbte stbtic finbl PlbtformLogger log = PlbtformLogger.getLogger("sun.bwt.windows.WScrollPbnePeer");

    int scrollbbrWidth;
    int scrollbbrHeight;
    int prevx;
    int prevy;

    stbtic {
        initIDs();
    }

    stbtic nbtive void initIDs();
    @Override
    nbtive void crebte(WComponentPeer pbrent);
    nbtive int getOffset(int orient);

    WScrollPbnePeer(Component tbrget) {
        super(tbrget);
        scrollbbrWidth = _getVScrollbbrWidth();
        scrollbbrHeight = _getHScrollbbrHeight();
    }

    @Override
    void initiblize() {
        super.initiblize();
        setInsets();
        Insets i = getInsets();
        setScrollPosition(-i.left,-i.top);
    }

    @Override
    public void setUnitIncrement(Adjustbble bdj, int p) {
        // The unitIncrement is grbbbed from the tbrget bs needed.
    }

    @Override
    public Insets insets() {
        return getInsets();
    }
    privbte nbtive void setInsets();

    @Override
    public nbtive synchronized void setScrollPosition(int x, int y);

    @Override
    public int getHScrollbbrHeight() {
        return scrollbbrHeight;
    }
    privbte nbtive int _getHScrollbbrHeight();

    @Override
    public int getVScrollbbrWidth() {
        return scrollbbrWidth;
    }
    privbte nbtive int _getVScrollbbrWidth();

    public Point getScrollOffset() {
        int x = getOffset(Adjustbble.HORIZONTAL);
        int y = getOffset(Adjustbble.VERTICAL);
        return new Point(x, y);
    }

    /**
     * The child component hbs been resized.  The scrollbbrs must be
     * updbted with the new sizes.  At the nbtive level the sizes of
     * the bctubl windows mby not hbve chbnged yet, so the size
     * informbtion from the jbvb-level is pbssed down bnd used.
     */
    @Override
    public void childResized(int width, int height) {
        ScrollPbne sp = (ScrollPbne)tbrget;
        Dimension vs = sp.getSize();
        setSpbns(vs.width, vs.height, width, height);
        setInsets();
    }

    nbtive synchronized void setSpbns(int viewWidth, int viewHeight,
                                      int childWidth, int childHeight);

    /**
     * Cblled by ScrollPbne's internbl observer of the scrollpbne's bdjustbbles.
     * This is cblled whenever b scroll position is chbnged in one
     * of bdjustbbles, whether it wbs modified externblly or from the
     * nbtive scrollbbrs themselves.
     */
    @Override
    public void setVblue(Adjustbble bdj, int v) {
        Component c = getScrollChild();
        if (c == null) {
            return;
        }

        Point p = c.getLocbtion();
        switch(bdj.getOrientbtion()) {
        cbse Adjustbble.VERTICAL:
            setScrollPosition(-(p.x), v);
            brebk;
        cbse Adjustbble.HORIZONTAL:
            setScrollPosition(v, -(p.y));
            brebk;
        }
    }

    privbte Component getScrollChild() {
        ScrollPbne sp = (ScrollPbne)tbrget;
        Component child = null;
        try {
            child = sp.getComponent(0);
        } cbtch (ArrbyIndexOutOfBoundsException e) {
            // do nothing.  in this cbse we return null
        }
        return child;
    }

    /*
     * Cblled from Windows in response to WM_VSCROLL/WM_HSCROLL messbge
     */
    privbte void postScrollEvent(int orient, int type,
                                 int pos, boolebn isAdjusting)
    {
        Runnbble bdjustor = new Adjustor(orient, type, pos, isAdjusting);
        WToolkit.executeOnEventHbndlerThrebd(new ScrollEvent(tbrget, bdjustor));
    }

    /*
     * Event thbt executes on the Jbvb dispbtch threbd to move the
     * scroll bbr thumbs bnd pbint the exposed breb in one synchronous
     * operbtion.
     */
    @SuppressWbrnings("seribl") // JDK-implementbtion clbss
    clbss ScrollEvent extends PeerEvent {
        ScrollEvent(Object source, Runnbble runnbble) {
            super(source, runnbble, 0L);
        }

        @Override
        public PeerEvent coblesceEvents(PeerEvent newEvent) {
            if (log.isLoggbble(PlbtformLogger.Level.FINEST)) {
                log.finest("ScrollEvent coblesced: " + newEvent);
            }
            if (newEvent instbnceof ScrollEvent) {
                return newEvent;
            }
            return null;
        }
    }

    /*
     * Runnbble for the ScrollEvent thbt performs the bdjustment.
     */
    clbss Adjustor implements Runnbble {
        int orient;             // selects scrollbbr
        int type;               // bdjustment type
        int pos;                // new position (only used for bbsolute)
        boolebn isAdjusting;    // isAdjusting stbtus

        Adjustor(int orient, int type, int pos, boolebn isAdjusting) {
            this.orient = orient;
            this.type = type;
            this.pos = pos;
            this.isAdjusting = isAdjusting;
        }

        @Override
        public void run() {
            if (getScrollChild() == null) {
                return;
            }
            ScrollPbne sp = (ScrollPbne)WScrollPbnePeer.this.tbrget;
            ScrollPbneAdjustbble bdj = null;

            // ScrollPbneAdjustbble mbde public in 1.4, but
            // get[HV]Adjustbble cbn't be declbred to return
            // ScrollPbneAdjustbble becbuse it would brebk bbckwbrd
            // compbtibility -- hence the cbst

            if (orient == Adjustbble.VERTICAL) {
                bdj = (ScrollPbneAdjustbble)sp.getVAdjustbble();
            } else if (orient == Adjustbble.HORIZONTAL) {
                bdj = (ScrollPbneAdjustbble)sp.getHAdjustbble();
            } else {
                if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
                    log.fine("Assertion fbiled: unknown orient");
                }
            }

            if (bdj == null) {
                return;
            }

            int newpos = bdj.getVblue();
            switch (type) {
              cbse AdjustmentEvent.UNIT_DECREMENT:
                  newpos -= bdj.getUnitIncrement();
                  brebk;
              cbse AdjustmentEvent.UNIT_INCREMENT:
                  newpos += bdj.getUnitIncrement();
                  brebk;
              cbse AdjustmentEvent.BLOCK_DECREMENT:
                  newpos -= bdj.getBlockIncrement();
                  brebk;
              cbse AdjustmentEvent.BLOCK_INCREMENT:
                  newpos += bdj.getBlockIncrement();
                  brebk;
              cbse AdjustmentEvent.TRACK:
                  newpos = this.pos;
                  brebk;
              defbult:
                  if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
                      log.fine("Assertion fbiled: unknown type");
                  }
                  return;
            }

            // keep scroll position in bcceptbble rbnge
            newpos = Mbth.mbx(bdj.getMinimum(), newpos);
            newpos = Mbth.min(bdj.getMbximum(), newpos);

            // set vblue, this will synchronously fire bn AdjustmentEvent
            bdj.setVblueIsAdjusting(isAdjusting);

            // Fix for 4075484 - consider type informbtion when crebting AdjustmentEvent
            // We cbn't just cbll bdj.setVblue() becbuse it crebtes AdjustmentEvent with type=TRACK
            // Instebd, we cbll privbte method setTypedVblue of ScrollPbneAdjustbble.
            AWTAccessor.getScrollPbneAdjustbbleAccessor().setTypedVblue(bdj,
                                                                        newpos,
                                                                        type);

            // Pbint the exposed breb right bwby.  To do this - find
            // the hebvyweight bncestor of the scroll child.
            Component hwAncestor = getScrollChild();
            while (hwAncestor != null
                   && !(hwAncestor.getPeer() instbnceof WComponentPeer))
            {
                hwAncestor = hwAncestor.getPbrent();
            }
            if (log.isLoggbble(PlbtformLogger.Level.FINE)) {
                if (hwAncestor == null) {
                    log.fine("Assertion (hwAncestor != null) fbiled, " +
                             "couldn't find hebvyweight bncestor of scroll pbne child");
                }
            }
            WComponentPeer hwPeer = (WComponentPeer)hwAncestor.getPeer();
            hwPeer.pbintDbmbgedArebImmedibtely();
        }
    }

}
