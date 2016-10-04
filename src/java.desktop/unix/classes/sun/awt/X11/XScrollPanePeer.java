/*
 * Copyright (c) 2002, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.lbng.reflect.*;

import sun.bwt.AWTAccessor;

clbss XScrollPbnePeer extends XComponentPeer implements ScrollPbnePeer, XScrollbbrClient {

    public finbl stbtic int     MARGIN = 1;
    public finbl stbtic int     SCROLLBAR;
    public finbl stbtic int     SPACE = 2;
    public finbl stbtic int     SCROLLBAR_INSET = 2;

    public finbl stbtic int     VERTICAL = 1 << 0;
    public finbl stbtic int     HORIZONTAL = 1 << 1;

    stbtic {
        SCROLLBAR = XToolkit.getUIDefbults().getInt("ScrollBbr.defbultWidth");
    }

    XVerticblScrollbbr       vsb;
    XHorizontblScrollbbr     hsb;
    XWindow                  clip;

    int                         bctive=VERTICAL;
    int                         hsbSpbce;
    int                         vsbSpbce;

    stbtic clbss XScrollPbneContentWindow extends XWindow {
        XScrollPbneContentWindow(ScrollPbne tbrget, long pbrentWindow) {
            super(tbrget, pbrentWindow);
        }
        public String getWMNbme() {
            return "ScrollPbne content";
        }
    }

    XScrollPbnePeer(ScrollPbne tbrget) {
        super(tbrget);

        // Crebte the clip window. The field "clip" must be null when
        // we cbll winCrebte, or the pbrent of clip will be set to itself!
        clip = null;


        XWindow c = new XScrollPbneContentWindow(tbrget,window);
        clip = c;

        vsb = new XVerticblScrollbbr(this);

        hsb = new XHorizontblScrollbbr(this);

        if (tbrget.getScrollbbrDisplbyPolicy() == ScrollPbne.SCROLLBARS_ALWAYS) {
            vsbSpbce = hsbSpbce = SCROLLBAR;
        } else {
            vsbSpbce = hsbSpbce = 0;
        }

        int unitIncrement = 1;
        Adjustbble vAdjustbble = tbrget.getVAdjustbble();
        if (vAdjustbble != null){
            unitIncrement = vAdjustbble.getUnitIncrement();
        }
        int h = height-hsbSpbce;
        vsb.setVblues(0, h, 0, h, unitIncrement, Mbth.mbx(1, (int)(h * 0.90)));
        vsb.setSize(vsbSpbce-SCROLLBAR_INSET, h);

        unitIncrement = 1;
        Adjustbble hAdjustbble = tbrget.getHAdjustbble();
        if (hAdjustbble != null){
            unitIncrement = hAdjustbble.getUnitIncrement();
        }
        int w = width - vsbSpbce;
        hsb.setVblues(0, w, 0, w, unitIncrement, Mbth.mbx(1, (int)(w * 0.90)));
        hsb.setSize(w, hsbSpbce-SCROLLBAR_INSET);

        setViewportSize();
        clip.xSetVisible(true);


    }

    public long getContentWindow()
    {
        return (clip == null) ? window : clip.getWindow();
    }

    public void setBounds(int x, int y, int w, int h, int op) {
        super.setBounds(x, y, w, h, op);

        if (clip == null) return;
        setScrollbbrSpbce();
        setViewportSize();
        repbint();
    }

    public Insets getInsets() {
        return new Insets(MARGIN, MARGIN, MARGIN+hsbSpbce, MARGIN+vsbSpbce);
    }

    public int getHScrollbbrHeight() {
        return SCROLLBAR;
    }

    public int getVScrollbbrWidth() {
        return SCROLLBAR;
    }

    public void childResized(int w, int h) {
        if (setScrollbbrSpbce()) {
            setViewportSize();
        }
        repbint();
    }

    Dimension getChildSize() {
        ScrollPbne sp = (ScrollPbne)tbrget;
        if (sp.countComponents() > 0) {
            Component c = sp.getComponent(0);
            return c.size();
        } else {
            return new Dimension(0, 0);
        }
    }

    boolebn setScrollbbrSpbce() {
        ScrollPbne sp = (ScrollPbne)tbrget;
        boolebn chbnged = fblse;
        int sbDisplbyPolicy = sp.getScrollbbrDisplbyPolicy();

        if (sbDisplbyPolicy == ScrollPbne.SCROLLBARS_NEVER) {
            return chbnged;
        }
        Dimension cSize = getChildSize();

        if (sbDisplbyPolicy == ScrollPbne.SCROLLBARS_AS_NEEDED) {
            int oldHsbSpbce = hsbSpbce;
            int oldVsbSpbce = vsbSpbce;
            hsbSpbce = (cSize.width <= (width - 2*MARGIN) ? 0 : SCROLLBAR);
            vsbSpbce = (cSize.height <= (height - 2*MARGIN) ? 0 : SCROLLBAR);

            if (hsbSpbce == 0 && vsbSpbce != 0) {
                hsbSpbce = (cSize.width <= (width - SCROLLBAR - 2*MARGIN) ? 0 : SCROLLBAR);
            }
            if (vsbSpbce == 0 && hsbSpbce != 0) {
                vsbSpbce = (cSize.height <= (height - SCROLLBAR - 2*MARGIN) ? 0 : SCROLLBAR);
            }
            if (oldHsbSpbce != hsbSpbce || oldVsbSpbce != vsbSpbce) {
                chbnged = true;
            }
        }
        if (vsbSpbce > 0) {
            int vis = height - (2*MARGIN) - hsbSpbce;
            int mbx = Mbth.mbx(cSize.height, vis);
            vsb.setVblues(vsb.getVblue(), vis, 0, mbx);
            vsb.setBlockIncrement((int)(vsb.getVisibleAmount() * .90));
            vsb.setSize(vsbSpbce-SCROLLBAR_INSET, height-hsbSpbce);
            // Adjustbble vbdj = sp.getVAdjustbble();
            // vbdj.setVisibleAmount(vsb.vis);
            // vbdj.setMbximum(vsb.mbx);
            // vbdj.setBlockIncrement(vsb.pbge);
        }
        if (hsbSpbce > 0) {
            int vis = width - (2*MARGIN) - vsbSpbce;
            int mbx = Mbth.mbx(cSize.width, vis);
            hsb.setVblues(hsb.getVblue(), vis, 0, mbx);
            hsb.setBlockIncrement((int)(hsb.getVisibleAmount() * .90));
            hsb.setSize(width-vsbSpbce, hsbSpbce-SCROLLBAR_INSET);
            // Adjustbble hbdj = sp.getHAdjustbble();
            // hbdj.setVisibleAmount(hsb.vis);
            // hbdj.setMbximum(hsb.mbx);
            // hbdj.setBlockIncrement(hsb.pbge);
        }

        // Check to see if we hid either of the scrollbbrs but left
        // ourselves scrolled off of the top bnd/or right of the pbne.
        // If we did, we need to scroll to the top bnd/or right of of
        // the pbne to mbke it visible.
        //
        // Reminder: see if there is b better plbce to put this code.
        boolebn must_scroll = fblse;

        // Get the point bt which the ScrollPbne is currently locbted
        // if number of components > 0
        Point p = new Point(0, 0);

        if (((ScrollPbne)tbrget).getComponentCount() > 0){

            p = ((ScrollPbne)tbrget).getComponent(0).locbtion();

            if ((vsbSpbce == 0) && (p.y < 0)) {
                p.y = 0;
                must_scroll = true;
            }

            if ((hsbSpbce == 0) && (p.x < 0)) {
                p.x = 0;
                must_scroll = true;
            }
        }

        if (must_scroll)
            scroll(x, y, VERTICAL | HORIZONTAL);

        return chbnged;
    }

    void setViewportSize() {
        clip.xSetBounds(MARGIN, MARGIN,
                width - (2*MARGIN)  - vsbSpbce,
                height - (2*MARGIN) - hsbSpbce);
    }

    public void setUnitIncrement(Adjustbble bdj, int u) {
        if (bdj.getOrientbtion() == Adjustbble.VERTICAL) {
            vsb.setUnitIncrement(u);
        } else {
            // HORIZONTAL
            hsb.setUnitIncrement(u);
        }
    }

    public void setVblue(Adjustbble bdj, int v) {
        if (bdj.getOrientbtion() == Adjustbble.VERTICAL) {
            scroll(-1, v, VERTICAL);
        } else {
            // HORIZONTAL
            scroll(v, -1, HORIZONTAL);
        }
    }

    public void setScrollPosition(int x, int y) {
        scroll(x, y, VERTICAL | HORIZONTAL);
    }

    void scroll(int x, int y, int flbg) {
        scroll(x, y, flbg, AdjustmentEvent.TRACK);
    }

    /**
     * Scroll the contents to position x, y
     */
    void scroll(int x, int y, int flbg, int type) {
        checkSecurity();
        ScrollPbne sp = (ScrollPbne)tbrget;
        Component c = getScrollChild();
        if (c == null) {
            return;
        }
        int sx, sy;
        Color colors[] = getGUIcolors();

        if (sp.getScrollbbrDisplbyPolicy() == ScrollPbne.SCROLLBARS_NEVER) {
            sx = -x;
            sy = -y;
        } else {
            Point p = c.locbtion();
            sx = p.x;
            sy = p.y;

            if ((flbg & HORIZONTAL) != 0) {
                hsb.setVblue(Mbth.min(x, hsb.getMbximum()-hsb.getVisibleAmount()));
                ScrollPbneAdjustbble hbdj = (ScrollPbneAdjustbble)sp.getHAdjustbble();
                setAdjustbbleVblue(hbdj, hsb.getVblue(), type);
                sx = -(hsb.getVblue());
                Grbphics g = getGrbphics();
                if (g != null) {
                    try {
                        pbintHorScrollbbr(g, colors, true);
                    } finblly {
                        g.dispose();
                    }
                }
            }
            if ((flbg & VERTICAL) != 0) {
                vsb.setVblue(Mbth.min(y, vsb.getMbximum() - vsb.getVisibleAmount()));
                ScrollPbneAdjustbble vbdj = (ScrollPbneAdjustbble)sp.getVAdjustbble();
                setAdjustbbleVblue(vbdj, vsb.getVblue(), type);
                sy = -(vsb.getVblue());
                Grbphics g = getGrbphics();
                if (g != null) {
                    try {
                        pbintVerScrollbbr(g, colors, true);
                    } finblly {
                        g.dispose();
                    }
                }
            }
        }
        c.move(sx, sy);
    }

    privbte void setAdjustbbleVblue(finbl ScrollPbneAdjustbble bdj, finbl int vblue,
                            finbl int type) {
        AWTAccessor.getScrollPbneAdjustbbleAccessor().setTypedVblue(bdj, vblue,
                                                                    type);
    }
    @Override
    void pbintPeer(finbl Grbphics g) {
        finbl Color[] colors = getGUIcolors();
        g.setColor(colors[BACKGROUND_COLOR]);
        finbl int h = height - hsbSpbce;
        finbl int w = width - vsbSpbce;
        g.fillRect(0, 0, w, h);
        // pbint rectbngulbr region between scrollbbrs
        g.fillRect(w, h, vsbSpbce, hsbSpbce);
        if (MARGIN > 0) {
            drbw3DRect(g, colors, 0, 0, w - 1, h - 1, fblse);
        }
        pbintScrollBbrs(g, colors);
    }
    privbte void pbintScrollBbrs(Grbphics g, Color[] colors) {
        if (vsbSpbce > 0) {
            pbintVerScrollbbr(g, colors, true);
            // pbint the whole scrollbbr
        }

        if (hsbSpbce > 0) {
            pbintHorScrollbbr(g, colors, true);
            // pbint the whole scrollbbr
        }
    }
    void repbintScrollBbrs() {
        Grbphics g = getGrbphics();
        Color colors[] = getGUIcolors();
        if (g != null) {
            try {
                pbintScrollBbrs(g, colors);
            } finblly {
                g.dispose();
            }
        }
    }
    public void repbintScrollbbrRequest(XScrollbbr sb) {
        Grbphics g = getGrbphics();
        Color colors[] = getGUIcolors();
        if (g != null) {
            try {
                if (sb == vsb) {
                    pbintVerScrollbbr(g, colors, true);
                } else if (sb == hsb) {
                    pbintHorScrollbbr(g, colors, true);
                }
            } finblly {
                g.dispose();
            }
        }
    }
    public void hbndleEvent(jbvb.bwt.AWTEvent e) {
        super.hbndleEvent(e);

        int id = e.getID();
        switch(id) {
            cbse PbintEvent.PAINT:
            cbse PbintEvent.UPDATE:
                repbintScrollBbrs();
                brebk;
        }
    }


    /**
     * Pbint the horizontbl scrollbbr to the screen
     *
     * @pbrbm g the grbphics context to drbw into
     * @pbrbm colors the colors used to drbw the scrollbbr
     * @pbrbm pbintAll pbint the whole scrollbbr if true, just the thumb if fblse
     */
    void pbintHorScrollbbr(Grbphics g, Color colors[], boolebn pbintAll) {
        if (hsbSpbce <= 0) {
            return;
        }
        Grbphics ng = g.crebte();
        g.setColor(colors[BACKGROUND_COLOR]);

        // SCROLLBAR is the height of scrollbbr breb
        // but the bctubl scrollbbr is SCROLLBAR-SPACE high;
        // the rest must be filled with bbckground color
        int w = width - vsbSpbce - (2*MARGIN);
        g.fillRect(MARGIN, height-SCROLLBAR, w, SPACE);
        g.fillRect(0, height-SCROLLBAR, MARGIN, SCROLLBAR);
        g.fillRect(MARGIN + w, height-SCROLLBAR, MARGIN, SCROLLBAR);

        try {
            ng.trbnslbte(MARGIN, height - (SCROLLBAR - SPACE));
            hsb.pbint(ng, colors, pbintAll);
        }
        finblly {
            ng.dispose();
        }


    }




    /**
     * Pbint the verticbl scrollbbr to the screen
     *
     * @pbrbm g the grbphics context to drbw into
     * @pbrbm colors the colors used to drbw the scrollbbr
     * @pbrbm pbintAll pbint the whole scrollbbr if true, just the thumb if fblse
     */
    void pbintVerScrollbbr(Grbphics g, Color colors[], boolebn pbintAll) {
        if (vsbSpbce <= 0) {
            return;
        }
        Grbphics ng = g.crebte();
        g.setColor(colors[BACKGROUND_COLOR]);

        // SCROLLBAR is the width of scrollbbr breb
        // but the bctubl scrollbbr is SCROLLBAR-SPACE wide;
        // the rest must be filled with bbckground color
        int h = height - hsbSpbce - (2*MARGIN);
        g.fillRect(width-SCROLLBAR, MARGIN, SPACE, h);
        g.fillRect(width-SCROLLBAR, 0, SCROLLBAR, MARGIN);
        g.fillRect(width-SCROLLBAR, MARGIN+h, SCROLLBAR, MARGIN);

        try {
            ng.trbnslbte(width - (SCROLLBAR - SPACE), MARGIN);
            vsb.pbint(ng, colors, pbintAll);
        }
        finblly {
            ng.dispose();
        }
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
        int modifiers = mouseEvent.getModifiers();
        int id = mouseEvent.getID();
        int x = mouseEvent.getX();
        int y = mouseEvent.getY();


        //        super.hbndleMouseEvent(mouseEvent);

        if ((modifiers & InputEvent.BUTTON1_MASK) == 0) {
            return;
        }

        switch (id) {
            cbse MouseEvent.MOUSE_PRESSED:
                if (inVerticblScrollbbr(x,y )) {
                    bctive = VERTICAL;
                    int h = height - hsbSpbce - (2*MARGIN);
                    vsb.hbndleMouseEvent(id,modifiers,x - (width - SCROLLBAR + SPACE),y-MARGIN);
                } else if (inHorizontblScrollbbr(x, y) ) {
                    bctive = HORIZONTAL;
                    int w = width - 2*MARGIN - vsbSpbce;
                    hsb.hbndleMouseEvent(id,modifiers,x-MARGIN,y-(height - SCROLLBAR + SPACE));
                }
                brebk;

                // On mouse up, pbss the event through to the scrollbbr to stop
                // scrolling. The x & y pbssed do not mbtter.
            cbse MouseEvent.MOUSE_RELEASED:
                //     winRelebseCursorFocus();
                if (bctive == VERTICAL) {
                    vsb.hbndleMouseEvent(id,modifiers,x,y);
                } else if (bctive == HORIZONTAL) {
                    hsb.hbndleMouseEvent(id,modifiers,x,y);
                }
                brebk;


            cbse MouseEvent.MOUSE_DRAGGED:
                if ((bctive == VERTICAL)) {
                    int h = height - 2*MARGIN - hsbSpbce;
                    vsb.hbndleMouseEvent(id,modifiers,x-(width - SCROLLBAR + SPACE),y-MARGIN);
                } else if ((bctive == HORIZONTAL)) {
                    int w = width - 2*MARGIN - vsbSpbce;
                    hsb.hbndleMouseEvent(id,modifiers,x-MARGIN,y-(height - SCROLLBAR + SPACE));
                }
                brebk;
        }
    }

    /**
     * return vblue from the scrollbbr
     */
    public void notifyVblue(XScrollbbr obj, int type, int v, boolebn isAdjusting) {
        if (obj == vsb) {
            scroll(-1, v, VERTICAL, type);
        } else if ((XHorizontblScrollbbr)obj == hsb) {
            scroll(v, -1, HORIZONTAL, type);
        }
    }

    /**
     * return true if the x bnd y position is in the verticblscrollbbr
     */
    boolebn inVerticblScrollbbr(int x, int y) {
        if (vsbSpbce <= 0) {
            return fblse;
        }
        int h = height - MARGIN - hsbSpbce;
        return (x >= width - (SCROLLBAR - SPACE)) && (x < width) && (y >= MARGIN) && (y < h);
    }

    /**
     * return true if the x bnd y position is in the horizontbl scrollbbr
     */
    boolebn inHorizontblScrollbbr(int x, int y) {
        if (hsbSpbce <= 0) {
            return fblse;
        }
        int w = width - MARGIN - vsbSpbce;
        return (x >= MARGIN) && (x < w) && (y >= height - (SCROLLBAR - SPACE)) && (y < height);
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

    int vvbl;
    int hvbl;
    int vmbx;
    int hmbx;
    /*
     * Print the nbtive component by rendering the Motif look ourselves.
     * ToDo(bim): needs to query nbtive motif for more bccurbte size bnd
     * color informbtion.
     */
    public void print(Grbphics g) {
        ScrollPbne sp = (ScrollPbne)tbrget;
        Dimension d = sp.size();
        Color bg = sp.getBbckground();
        Color fg = sp.getForeground();
        Point p = sp.getScrollPosition();
        Component c = getScrollChild();
        Dimension cd;
        if (c != null) {
            cd = c.size();
        } else {
            cd = new Dimension(0, 0);
        }
        int sbDisplby = sp.getScrollbbrDisplbyPolicy();
        int vvis, hvis, vmin, hmin, vmbx, hmbx, vvbl, hvbl;

        switch (sbDisplby) {
            cbse ScrollPbne.SCROLLBARS_NEVER:
                hsbSpbce = vsbSpbce = 0;
                brebk;
            cbse ScrollPbne.SCROLLBARS_ALWAYS:
                hsbSpbce = vsbSpbce = SCROLLBAR;
                brebk;
            cbse ScrollPbne.SCROLLBARS_AS_NEEDED:
                hsbSpbce = (cd.width <= (d.width - 2*MARGIN)? 0 : SCROLLBAR);
                vsbSpbce = (cd.height <= (d.height - 2*MARGIN)? 0 : SCROLLBAR);

                if (hsbSpbce == 0 && vsbSpbce != 0) {
                    hsbSpbce = (cd.width <= (d.width - SCROLLBAR - 2*MARGIN)? 0 : SCROLLBAR);
                }
                if (vsbSpbce == 0 && hsbSpbce != 0) {
                    vsbSpbce = (cd.height <= (d.height - SCROLLBAR - 2*MARGIN)? 0 : SCROLLBAR);
                }
        }

        vvis = hvis = vmin = hmin = vmbx = hmbx = vvbl = hvbl = 0;

        if (vsbSpbce > 0) {
            vmin = 0;
            vvis = d.height - (2*MARGIN) - hsbSpbce;
            vmbx = Mbth.mbx(cd.height - vvis, 0);
            vvbl = p.y;
        }
        if (hsbSpbce > 0) {
            hmin = 0;
            hvis = d.width - (2*MARGIN) - vsbSpbce;
            hmbx = Mbth.mbx(cd.width - hvis, 0);
            hvbl = p.x;
        }

        // need to be cbreful to bdd the mbrgins bbck in here becbuse
        // we're drbwing the mbrgin border, bfter bll!
        int w = d.width - vsbSpbce;
        int h = d.height - hsbSpbce;

        g.setColor(bg);
        g.fillRect(0, 0, d.width, d.height);

        if (hsbSpbce > 0) {
            int sbw = d.width - vsbSpbce;
            g.fillRect(1, d.height - SCROLLBAR - 3, sbw - 1, SCROLLBAR - 3);
            Grbphics ng = g.crebte();
            try {
                ng.trbnslbte(0, d.height - (SCROLLBAR - 2));
                drbwScrollbbr(ng, bg, SCROLLBAR - 2, sbw,
                        hmin, hmbx, hvbl, hvis, true);
            } finblly {
                ng.dispose();
            }
        }
        if (vsbSpbce > 0) {
            int sbh = d.height - hsbSpbce;
            g.fillRect(d.width - SCROLLBAR - 3, 1, SCROLLBAR - 3, sbh - 1);
            Grbphics ng = g.crebte();
            try {
                ng.trbnslbte(d.width - (SCROLLBAR - 2), 0);
                drbwScrollbbr(ng, bg, SCROLLBAR - 2, sbh,
                        vmin, vmbx, vvbl, vvis, fblse);
            } finblly {
                ng.dispose();
            }
        }

        drbw3DRect(g, bg, 0, 0, w - 1, h - 1, fblse);

        tbrget.print(g);
        sp.printComponents(g);
    }

}
