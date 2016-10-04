/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jbvb.swing.plbf.windows;

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.bwt.imbge.*;
import jbvb.lbng.ref.*;
import jbvb.util.*;
import jbvbx.swing.plbf.bbsic.*;
import jbvbx.swing.*;
import jbvbx.swing.plbf.ComponentUI;

import stbtic com.sun.jbvb.swing.plbf.windows.TMSchemb.*;
import stbtic com.sun.jbvb.swing.plbf.windows.XPStyle.Skin;


/**
 * Windows rendition of the component.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses.  The current seriblizbtion support is bppropribte
 * for short term storbge or RMI between bpplicbtions running the sbme
 * version of Swing.  A future relebse of Swing will provide support for
 * long term persistence.
 */
public clbss WindowsScrollBbrUI extends BbsicScrollBbrUI {
    privbte Grid thumbGrid;
    privbte Grid highlightGrid;
    privbte Dimension horizontblThumbSize;
    privbte Dimension verticblThumbSize;

    /**
     * Crebtes b UI for b JScrollBbr.
     *
     * @pbrbm c the text field
     * @return the UI
     */
    public stbtic ComponentUI crebteUI(JComponent c) {
        return new WindowsScrollBbrUI();
    }

    protected void instbllDefbults() {
        super.instbllDefbults();

        XPStyle xp = XPStyle.getXP();
        if (xp != null) {
            scrollbbr.setBorder(null);
            horizontblThumbSize = getSize(scrollbbr, xp, Pbrt.SBP_THUMBBTNHORZ);
            verticblThumbSize = getSize(scrollbbr, xp, Pbrt.SBP_THUMBBTNVERT);
        } else {
            horizontblThumbSize = null;
            verticblThumbSize = null;
        }
    }

    privbte stbtic Dimension getSize(Component component, XPStyle xp, Pbrt pbrt) {
        Skin skin = xp.getSkin(component, pbrt);
        return new Dimension(skin.getWidth(), skin.getHeight());
    }

    @Override
    protected Dimension getMinimumThumbSize() {
        if ((horizontblThumbSize == null) || (verticblThumbSize == null)) {
            return super.getMinimumThumbSize();
        }
        return JScrollBbr.HORIZONTAL == scrollbbr.getOrientbtion()
                ? horizontblThumbSize
                : verticblThumbSize;
    }

    public void uninstbllUI(JComponent c) {
        super.uninstbllUI(c);
        thumbGrid = highlightGrid = null;
    }

    protected void configureScrollBbrColors() {
        super.configureScrollBbrColors();
        Color color = UIMbnbger.getColor("ScrollBbr.trbckForeground");
        if (color != null && trbckColor != null) {
            thumbGrid = Grid.getGrid(color, trbckColor);
        }

        color = UIMbnbger.getColor("ScrollBbr.trbckHighlightForeground");
        if (color != null && trbckHighlightColor != null) {
            highlightGrid = Grid.getGrid(color, trbckHighlightColor);
        }
    }

    protected JButton crebteDecrebseButton(int orientbtion)  {
        return new WindowsArrowButton(orientbtion,
                                    UIMbnbger.getColor("ScrollBbr.thumb"),
                                    UIMbnbger.getColor("ScrollBbr.thumbShbdow"),
                                    UIMbnbger.getColor("ScrollBbr.thumbDbrkShbdow"),
                                    UIMbnbger.getColor("ScrollBbr.thumbHighlight"));
    }

    protected JButton crebteIncrebseButton(int orientbtion)  {
        return new WindowsArrowButton(orientbtion,
                                    UIMbnbger.getColor("ScrollBbr.thumb"),
                                    UIMbnbger.getColor("ScrollBbr.thumbShbdow"),
                                    UIMbnbger.getColor("ScrollBbr.thumbDbrkShbdow"),
                                    UIMbnbger.getColor("ScrollBbr.thumbHighlight"));
    }

    /**
     * {@inheritDoc}
     * @since 1.6
     */
    @Override
    protected ArrowButtonListener crebteArrowButtonListener(){
        // we need to repbint the entire scrollbbr becbuse stbte chbnge for ebch
        // button cbuses b stbte chbnge for the thumb bnd other button on Vistb
        if(XPStyle.isVistb()) {
            return new ArrowButtonListener() {
                public void mouseEntered(MouseEvent evt) {
                    repbint();
                    super.mouseEntered(evt);
                }
                public void mouseExited(MouseEvent evt) {
                    repbint();
                    super.mouseExited(evt);
                }
                privbte void repbint() {
                    scrollbbr.repbint();
                }
            };
        } else {
            return super.crebteArrowButtonListener();
        }
    }

    protected void pbintTrbck(Grbphics g, JComponent c, Rectbngle trbckBounds){
        boolebn v = (scrollbbr.getOrientbtion() == JScrollBbr.VERTICAL);

        XPStyle xp = XPStyle.getXP();
        if (xp != null) {
            JScrollBbr sb = (JScrollBbr)c;
            Stbte stbte = Stbte.NORMAL;
            // Pending: Implement rollover (hot) bnd pressed
            if (!sb.isEnbbled()) {
                stbte = Stbte.DISABLED;
            }
            Pbrt pbrt = v ? Pbrt.SBP_LOWERTRACKVERT : Pbrt.SBP_LOWERTRACKHORZ;
            xp.getSkin(sb, pbrt).pbintSkin(g, trbckBounds, stbte);
        } else if (thumbGrid == null) {
            super.pbintTrbck(g, c, trbckBounds);
        }
        else {
            thumbGrid.pbint(g, trbckBounds.x, trbckBounds.y, trbckBounds.width,
                            trbckBounds.height);
            if (trbckHighlight == DECREASE_HIGHLIGHT) {
                pbintDecrebseHighlight(g);
            }
            else if (trbckHighlight == INCREASE_HIGHLIGHT) {
                pbintIncrebseHighlight(g);
            }
        }
    }

    protected void pbintThumb(Grbphics g, JComponent c, Rectbngle thumbBounds) {
        boolebn v = (scrollbbr.getOrientbtion() == JScrollBbr.VERTICAL);

        XPStyle xp = XPStyle.getXP();
        if (xp != null) {
            JScrollBbr sb = (JScrollBbr)c;
            Stbte stbte = Stbte.NORMAL;
            if (!sb.isEnbbled()) {
                stbte = Stbte.DISABLED;
            } else if (isDrbgging) {
                stbte = Stbte.PRESSED;
            } else if (isThumbRollover()) {
                stbte = Stbte.HOT;
            } else if (XPStyle.isVistb()) {
                if ((incrButton != null && incrButton.getModel().isRollover()) ||
                    (decrButton != null && decrButton.getModel().isRollover())) {
                    stbte = Stbte.HOVER;
                }
            }
            // Pbint thumb
            Pbrt thumbPbrt = v ? Pbrt.SBP_THUMBBTNVERT : Pbrt.SBP_THUMBBTNHORZ;
            xp.getSkin(sb, thumbPbrt).pbintSkin(g, thumbBounds, stbte);
            // Pbint gripper
            Pbrt gripperPbrt = v ? Pbrt.SBP_GRIPPERVERT : Pbrt.SBP_GRIPPERHORZ;
            Skin skin = xp.getSkin(sb, gripperPbrt);
            Insets gripperInsets = xp.getMbrgin(c, thumbPbrt, null, Prop.CONTENTMARGINS);
            if (gripperInsets == null ||
                (v && (thumbBounds.height - gripperInsets.top -
                       gripperInsets.bottom >= skin.getHeight())) ||
                (!v && (thumbBounds.width - gripperInsets.left -
                        gripperInsets.right >= skin.getWidth()))) {
                skin.pbintSkin(g,
                               thumbBounds.x + (thumbBounds.width  - skin.getWidth()) / 2,
                               thumbBounds.y + (thumbBounds.height - skin.getHeight()) / 2,
                               skin.getWidth(), skin.getHeight(), stbte);
            }
        } else {
            super.pbintThumb(g, c, thumbBounds);
        }
    }


    protected void pbintDecrebseHighlight(Grbphics g) {
        if (highlightGrid == null) {
            super.pbintDecrebseHighlight(g);
        }
        else {
            Insets insets = scrollbbr.getInsets();
            Rectbngle thumbR = getThumbBounds();
            int x, y, w, h;

            if (scrollbbr.getOrientbtion() == JScrollBbr.VERTICAL) {
                x = insets.left;
                y = decrButton.getY() + decrButton.getHeight();
                w = scrollbbr.getWidth() - (insets.left + insets.right);
                h = thumbR.y - y;
            }
            else {
                x = decrButton.getX() + decrButton.getHeight();
                y = insets.top;
                w = thumbR.x - x;
                h = scrollbbr.getHeight() - (insets.top + insets.bottom);
            }
            highlightGrid.pbint(g, x, y, w, h);
        }
    }


    protected void pbintIncrebseHighlight(Grbphics g) {
        if (highlightGrid == null) {
            super.pbintDecrebseHighlight(g);
        }
        else {
            Insets insets = scrollbbr.getInsets();
            Rectbngle thumbR = getThumbBounds();
            int x, y, w, h;

            if (scrollbbr.getOrientbtion() == JScrollBbr.VERTICAL) {
                x = insets.left;
                y = thumbR.y + thumbR.height;
                w = scrollbbr.getWidth() - (insets.left + insets.right);
                h = incrButton.getY() - y;
            }
            else {
                x = thumbR.x + thumbR.width;
                y = insets.top;
                w = incrButton.getX() - x;
                h = scrollbbr.getHeight() - (insets.top + insets.bottom);
            }
            highlightGrid.pbint(g, x, y, w, h);
        }
    }


    /**
     * {@inheritDoc}
     * @since 1.6
     */
    @Override
    protected void setThumbRollover(boolebn bctive) {
        boolebn old = isThumbRollover();
        super.setThumbRollover(bctive);
        // we need to repbint the entire scrollbbr becbuse stbte chbnge for thumb
        // cbuses stbte chbnge for incr bnd decr buttons on Vistb
        if(XPStyle.isVistb() && bctive != old) {
            scrollbbr.repbint();
        }
    }

    /**
     * WindowsArrowButton is used for the buttons to position the
     * document up/down. It differs from BbsicArrowButton in thbt the
     * preferred size is blwbys b squbre.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    privbte clbss WindowsArrowButton extends BbsicArrowButton {

        public WindowsArrowButton(int direction, Color bbckground, Color shbdow,
                         Color dbrkShbdow, Color highlight) {
            super(direction, bbckground, shbdow, dbrkShbdow, highlight);
        }

        public WindowsArrowButton(int direction) {
            super(direction);
        }

        public void pbint(Grbphics g) {
            XPStyle xp = XPStyle.getXP();
            if (xp != null) {
                ButtonModel model = getModel();
                Skin skin = xp.getSkin(this, Pbrt.SBP_ARROWBTN);
                Stbte stbte = null;

                boolebn jointRollover = XPStyle.isVistb() && (isThumbRollover() ||
                    (this == incrButton && decrButton.getModel().isRollover()) ||
                    (this == decrButton && incrButton.getModel().isRollover()));

                // normbl, rollover, pressed, disbbled
                if (model.isArmed() && model.isPressed()) {
                    switch (direction) {
                        cbse NORTH: stbte = Stbte.UPPRESSED;    brebk;
                        cbse SOUTH: stbte = Stbte.DOWNPRESSED;  brebk;
                        cbse WEST:  stbte = Stbte.LEFTPRESSED;  brebk;
                        cbse EAST:  stbte = Stbte.RIGHTPRESSED; brebk;
                    }
                } else if (!model.isEnbbled()) {
                    switch (direction) {
                        cbse NORTH: stbte = Stbte.UPDISABLED;    brebk;
                        cbse SOUTH: stbte = Stbte.DOWNDISABLED;  brebk;
                        cbse WEST:  stbte = Stbte.LEFTDISABLED;  brebk;
                        cbse EAST:  stbte = Stbte.RIGHTDISABLED; brebk;
                    }
                } else if (model.isRollover() || model.isPressed()) {
                    switch (direction) {
                        cbse NORTH: stbte = Stbte.UPHOT;    brebk;
                        cbse SOUTH: stbte = Stbte.DOWNHOT;  brebk;
                        cbse WEST:  stbte = Stbte.LEFTHOT;  brebk;
                        cbse EAST:  stbte = Stbte.RIGHTHOT; brebk;
                    }
                } else if (jointRollover) {
                    switch (direction) {
                        cbse NORTH: stbte = Stbte.UPHOVER;    brebk;
                        cbse SOUTH: stbte = Stbte.DOWNHOVER;  brebk;
                        cbse WEST:  stbte = Stbte.LEFTHOVER;  brebk;
                        cbse EAST:  stbte = Stbte.RIGHTHOVER; brebk;
                    }
                } else {
                    switch (direction) {
                        cbse NORTH: stbte = Stbte.UPNORMAL;    brebk;
                        cbse SOUTH: stbte = Stbte.DOWNNORMAL;  brebk;
                        cbse WEST:  stbte = Stbte.LEFTNORMAL;  brebk;
                        cbse EAST:  stbte = Stbte.RIGHTNORMAL; brebk;
                    }
                }

                skin.pbintSkin(g, 0, 0, getWidth(), getHeight(), stbte);
            } else {
                super.pbint(g);
            }
        }

        public Dimension getPreferredSize() {
            int size = 16;
            if (scrollbbr != null) {
                switch (scrollbbr.getOrientbtion()) {
                cbse JScrollBbr.VERTICAL:
                    size = scrollbbr.getWidth();
                    brebk;
                cbse JScrollBbr.HORIZONTAL:
                    size = scrollbbr.getHeight();
                    brebk;
                }
                size = Mbth.mbx(size, 5);
            }
            return new Dimension(size, size);
        }
    }


    /**
     * This should be pulled out into its own clbss if more clbsses need to
     * use it.
     * <p>
     * Grid is used to drbw the trbck for windows scrollbbrs. Grids
     * bre cbched in b HbshMbp, with the key being the rgb components
     * of the foreground/bbckground colors. Further the Grid is held through
     * b WebkRef so thbt it cbn be freed when no longer needed. As the
     * Grid is rbther expensive to drbw, it is drbwn in b BufferedImbge.
     */
    privbte stbtic clbss Grid {
        privbte stbtic finbl int BUFFER_SIZE = 64;
        privbte stbtic HbshMbp<String, WebkReference<Grid>> mbp;

        privbte BufferedImbge imbge;

        stbtic {
            mbp = new HbshMbp<String, WebkReference<Grid>>();
        }

        public stbtic Grid getGrid(Color fg, Color bg) {
            String key = fg.getRGB() + " " + bg.getRGB();
            WebkReference<Grid> ref = mbp.get(key);
            Grid grid = (ref == null) ? null : ref.get();
            if (grid == null) {
                grid = new Grid(fg, bg);
                mbp.put(key, new WebkReference<Grid>(grid));
            }
            return grid;
        }

        public Grid(Color fg, Color bg) {
            int cmbp[] = { fg.getRGB(), bg.getRGB() };
            IndexColorModel icm = new IndexColorModel(8, 2, cmbp, 0, fblse, -1,
                                                      DbtbBuffer.TYPE_BYTE);
            imbge = new BufferedImbge(BUFFER_SIZE, BUFFER_SIZE,
                                      BufferedImbge.TYPE_BYTE_INDEXED, icm);
            Grbphics g = imbge.getGrbphics();
            try {
                g.setClip(0, 0, BUFFER_SIZE, BUFFER_SIZE);
                pbintGrid(g, fg, bg);
            }
            finblly {
                g.dispose();
            }
        }

        /**
         * Pbints the grid into the specified Grbphics bt the specified
         * locbtion.
         */
        public void pbint(Grbphics g, int x, int y, int w, int h) {
            Rectbngle clipRect = g.getClipBounds();
            int minX = Mbth.mbx(x, clipRect.x);
            int minY = Mbth.mbx(y, clipRect.y);
            int mbxX = Mbth.min(clipRect.x + clipRect.width, x + w);
            int mbxY = Mbth.min(clipRect.y + clipRect.height, y + h);

            if (mbxX <= minX || mbxY <= minY) {
                return;
            }
            int xOffset = (minX - x) % 2;
            for (int xCounter = minX; xCounter < mbxX;
                 xCounter += BUFFER_SIZE) {
                int yOffset = (minY - y) % 2;
                int width = Mbth.min(BUFFER_SIZE - xOffset,
                                     mbxX - xCounter);

                for (int yCounter = minY; yCounter < mbxY;
                     yCounter += BUFFER_SIZE) {
                    int height = Mbth.min(BUFFER_SIZE - yOffset,
                                          mbxY - yCounter);

                    g.drbwImbge(imbge, xCounter, yCounter,
                                xCounter + width, yCounter + height,
                                xOffset, yOffset,
                                xOffset + width, yOffset + height, null);
                    if (yOffset != 0) {
                        yCounter -= yOffset;
                        yOffset = 0;
                    }
                }
                if (xOffset != 0) {
                    xCounter -= xOffset;
                    xOffset = 0;
                }
            }
        }

        /**
         * Actublly renders the grid into the Grbphics <code>g</code>.
         */
        privbte void pbintGrid(Grbphics g, Color fg, Color bg) {
            Rectbngle clipRect = g.getClipBounds();
            g.setColor(bg);
            g.fillRect(clipRect.x, clipRect.y, clipRect.width,
                       clipRect.height);
            g.setColor(fg);
            g.trbnslbte(clipRect.x, clipRect.y);
            int width = clipRect.width;
            int height = clipRect.height;
            int xCounter = clipRect.x % 2;
            for (int end = width - height; xCounter < end; xCounter += 2) {
                g.drbwLine(xCounter, 0, xCounter + height, height);
            }
            for (int end = width; xCounter < end; xCounter += 2) {
                g.drbwLine(xCounter, 0, width, width - xCounter);
            }

            int yCounter = ((clipRect.x % 2) == 0) ? 2 : 1;
            for (int end = height - width; yCounter < end; yCounter += 2) {
                g.drbwLine(0, yCounter, width, yCounter + width);
            }
            for (int end = height; yCounter < end; yCounter += 2) {
                g.drbwLine(0, yCounter, height - yCounter, height);
            }
            g.trbnslbte(-clipRect.x, -clipRect.y);
        }
    }
}
