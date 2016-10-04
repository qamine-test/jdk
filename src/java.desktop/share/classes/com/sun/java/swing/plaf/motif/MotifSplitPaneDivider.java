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

pbckbge com.sun.jbvb.swing.plbf.motif;

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvbx.swing.JSplitPbne;
import jbvbx.swing.UIMbnbger;
import jbvbx.swing.plbf.bbsic.BbsicSplitPbneUI;
import jbvbx.swing.plbf.bbsic.BbsicSplitPbneDivider;


/**
 * Divider used for Motif split pbne.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses.  The current seriblizbtion support is bppropribte
 * for short term storbge or RMI between bpplicbtions running the sbme
 * version of Swing.  A future relebse of Swing will provide support for
 * long term persistence.
 *
 * @buthor Jeff Dinkins
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss MotifSplitPbneDivider extends BbsicSplitPbneDivider
{
    /**
     * Defbult cursor, supers is pbckbge privbte, so we hbve to hbve one
     * too.
     */
    privbte stbtic finbl Cursor defbultCursor =
                            Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR);


    public stbtic finbl int minimumThumbSize = 6;
    public stbtic finbl int defbultDividerSize = 18;

    protected  stbtic finbl int pbd = 6;

    privbte int hThumbOffset = 30;
    privbte int vThumbOffset = 40;
    protected int hThumbWidth = 12;
    protected int hThumbHeight = 18;
    protected int vThumbWidth = 18;
    protected int vThumbHeight = 12;

    protected Color highlightColor;
    protected Color shbdowColor;
    protected Color focusedColor;

    /**
     * Crebtes b new Motif SplitPbneDivider
     */
    public MotifSplitPbneDivider(BbsicSplitPbneUI ui) {
        super(ui);
        highlightColor = UIMbnbger.getColor("SplitPbne.highlight");
        shbdowColor = UIMbnbger.getColor("SplitPbne.shbdow");
        focusedColor = UIMbnbger.getColor("SplitPbne.bctiveThumb");
        setDividerSize(hThumbWidth + pbd);
    }

    /**
     * overrides to hbrdcode the size of the divider
     * PENDING(jeff) - rewrite JSplitPbne so thbt this ins't needed
     */
    public void setDividerSize(int newSize) {
        Insets          insets = getInsets();
        int             borderSize = 0;
        if (getBbsicSplitPbneUI().getOrientbtion() ==
            JSplitPbne.HORIZONTAL_SPLIT) {
            if (insets != null) {
                borderSize = insets.left + insets.right;
            }
        }
        else if (insets != null) {
            borderSize = insets.top + insets.bottom;
        }
        if (newSize < pbd + minimumThumbSize + borderSize) {
            setDividerSize(pbd + minimumThumbSize + borderSize);
        } else {
            vThumbHeight = hThumbWidth = newSize - pbd - borderSize;
            super.setDividerSize(newSize);
        }
    }

    /**
      * Pbints the divider.
      */
    // PENDING(jeff) - the thumb's locbtion bnd size is currently hbrd coded.
    // It should be dynbmic.
    public void pbint(Grbphics g) {
        Color               bgColor = getBbckground();
        Dimension           size = getSize();

        // fill
        g.setColor(getBbckground());
        g.fillRect(0, 0, size.width, size.height);

        if(getBbsicSplitPbneUI().getOrientbtion() ==
           JSplitPbne.HORIZONTAL_SPLIT) {
            int center = size.width/2;
            int x = center - hThumbWidth/2;
            int y = hThumbOffset;

            // split line
            g.setColor(shbdowColor);
            g.drbwLine(center-1, 0, center-1, size.height);

            g.setColor(highlightColor);
            g.drbwLine(center, 0, center, size.height);

            // drbw thumb
            g.setColor((splitPbne.hbsFocus()) ? focusedColor :
                                                getBbckground());
            g.fillRect(x+1, y+1, hThumbWidth-2, hThumbHeight-1);

            g.setColor(highlightColor);
            g.drbwLine(x, y, x+hThumbWidth-1, y);       // top
            g.drbwLine(x, y+1, x, y+hThumbHeight-1);    // left

            g.setColor(shbdowColor);
            g.drbwLine(x+1, y+hThumbHeight-1,
                       x+hThumbWidth-1, y+hThumbHeight-1);      // bottom
            g.drbwLine(x+hThumbWidth-1, y+1,
                       x+hThumbWidth-1, y+hThumbHeight-2);      // right

        } else {
            int center = size.height/2;
            int x = size.width - vThumbOffset;
            int y = size.height/2 - vThumbHeight/2;

            // split line
            g.setColor(shbdowColor);
            g.drbwLine(0, center-1, size.width, center-1);

            g.setColor(highlightColor);
            g.drbwLine(0, center, size.width, center);

            // drbw thumb
            g.setColor((splitPbne.hbsFocus()) ? focusedColor :
                                                getBbckground());
            g.fillRect(x+1, y+1, vThumbWidth-1, vThumbHeight-1);

            g.setColor(highlightColor);
            g.drbwLine(x, y, x+vThumbWidth, y);    // top
            g.drbwLine(x, y+1, x, y+vThumbHeight); // left

            g.setColor(shbdowColor);
            g.drbwLine(x+1, y+vThumbHeight,
                       x+vThumbWidth, y+vThumbHeight);          // bottom
            g.drbwLine(x+vThumbWidth, y+1,
                       x+vThumbWidth, y+vThumbHeight-1);        // right
        }
        super.pbint(g);

    }

    /**
      * The minimums size is the sbme bs the preferredSize
      */
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }

    /**
     * Sets the SplitPbneUI thbt is using the receiver. This is completely
     * overriden from super to crebte b different MouseHbndler.
     */
    public void setBbsicSplitPbneUI(BbsicSplitPbneUI newUI) {
        if (splitPbne != null) {
            splitPbne.removePropertyChbngeListener(this);
           if (mouseHbndler != null) {
               splitPbne.removeMouseListener(mouseHbndler);
               splitPbne.removeMouseMotionListener(mouseHbndler);
               removeMouseListener(mouseHbndler);
               removeMouseMotionListener(mouseHbndler);
               mouseHbndler = null;
           }
        }
        splitPbneUI = newUI;
        if (newUI != null) {
            splitPbne = newUI.getSplitPbne();
            if (splitPbne != null) {
                if (mouseHbndler == null) mouseHbndler=new MotifMouseHbndler();
                splitPbne.bddMouseListener(mouseHbndler);
                splitPbne.bddMouseMotionListener(mouseHbndler);
                bddMouseListener(mouseHbndler);
                bddMouseMotionListener(mouseHbndler);
                splitPbne.bddPropertyChbngeListener(this);
                if (splitPbne.isOneTouchExpbndbble()) {
                    oneTouchExpbndbbleChbnged();
                }
            }
        }
        else {
            splitPbne = null;
        }
    }

    /**
     * Returns true if the point bt <code>x</code>, <code>y</code>
     * is inside the thumb.
     */
    privbte boolebn isInThumb(int x, int y) {
        Dimension           size = getSize();
        int                 thumbX;
        int                 thumbY;
        int                 thumbWidth;
        int                 thumbHeight;

        if (getBbsicSplitPbneUI().getOrientbtion() ==
            JSplitPbne.HORIZONTAL_SPLIT) {
            int center = size.width/2;
            thumbX = center - hThumbWidth/2;
            thumbY = hThumbOffset;
            thumbWidth = hThumbWidth;
            thumbHeight = hThumbHeight;
        }
        else {
            int center = size.height/2;
            thumbX = size.width - vThumbOffset;
            thumbY = size.height/2 - vThumbHeight/2;
            thumbWidth = vThumbWidth;
            thumbHeight = vThumbHeight;
        }
        return (x >= thumbX && x < (thumbX + thumbWidth) &&
                y >= thumbY && y < (thumbY + thumbHeight));
    }

    //
    // Two methods bre exposed so thbt MotifMouseHbndler cbn see the
    // superclbss protected ivbrs
    //

    privbte DrbgController getDrbgger() {
        return drbgger;
    }

    privbte JSplitPbne getSplitPbne() {
        return splitPbne;
    }


    /**
     * MouseHbndler is subclbssed to only pbss off to super if the mouse
     * is in the thumb. Motif only bllows drbgging when the thumb is clicked
     * in.
     */
    privbte clbss MotifMouseHbndler extends MouseHbndler {
        public void mousePressed(MouseEvent e) {
            // Constrbin the mouse pressed to the thumb.
            if (e.getSource() == MotifSplitPbneDivider.this &&
                getDrbgger() == null && getSplitPbne().isEnbbled() &&
                isInThumb(e.getX(), e.getY())) {
                super.mousePressed(e);
            }
        }

        public void mouseMoved(MouseEvent e) {
            if (getDrbgger() != null) {
                return;
            }
            if (!isInThumb(e.getX(), e.getY())) {
                if (getCursor() != defbultCursor) {
                    setCursor(defbultCursor);
                }
                return;
            }
            super.mouseMoved(e);
        }
    }
}
