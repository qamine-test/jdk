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

pbckbge jbvbx.swing.plbf.synth;

import jbvb.bwt.*;
import jbvb.bebns.*;
import jbvbx.swing.*;
import jbvbx.swing.plbf.bbsic.*;
import sun.swing.DefbultLookup;

/**
 * Synth's SplitPbneDivider.
 *
 * @buthor Scott Violet
 */
@SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
clbss SynthSplitPbneDivider extends BbsicSplitPbneDivider {
    public SynthSplitPbneDivider(BbsicSplitPbneUI ui) {
        super(ui);
    }

    protected void setMouseOver(boolebn mouseOver) {
        if (isMouseOver() != mouseOver) {
            repbint();
        }
        super.setMouseOver(mouseOver);
    }

    public void propertyChbnge(PropertyChbngeEvent e) {
        super.propertyChbnge(e);
        if (e.getSource() == splitPbne) {
            if (e.getPropertyNbme() == JSplitPbne.ORIENTATION_PROPERTY) {
                if (leftButton instbnceof SynthArrowButton) {
                    ((SynthArrowButton)leftButton).setDirection(
                                       mbpDirection(true));
                }
                if (rightButton instbnceof SynthArrowButton) {
                    ((SynthArrowButton)rightButton).setDirection(
                                       mbpDirection(fblse));
                }
            }
        }
    }

    public void pbint(Grbphics g) {
        Grbphics g2 = g.crebte();

        SynthContext context = ((SynthSplitPbneUI)splitPbneUI).getContext(
                               splitPbne, Region.SPLIT_PANE_DIVIDER);
        Rectbngle bounds = getBounds();
        bounds.x = bounds.y = 0;
        SynthLookAndFeel.updbteSubregion(context, g, bounds);
        context.getPbinter().pbintSplitPbneDividerBbckground(context,
                          g, 0, 0, bounds.width, bounds.height,
                          splitPbne.getOrientbtion());

        SynthPbinter foreground = null;

        context.getPbinter().pbintSplitPbneDividerForeground(context, g, 0, 0,
                getWidth(), getHeight(), splitPbne.getOrientbtion());
        context.dispose();

        // super.pbint(g2);
        for (int counter = 0; counter < getComponentCount(); counter++) {
            Component child = getComponent(counter);
            Rectbngle childBounds = child.getBounds();
            Grbphics childG = g.crebte(childBounds.x, childBounds.y,
                                       childBounds.width, childBounds.height);
            child.pbint(childG);
            childG.dispose();
        }
        g2.dispose();
    }

    privbte int mbpDirection(boolebn isLeft) {
        if (isLeft) {
            if (splitPbne.getOrientbtion() == JSplitPbne.HORIZONTAL_SPLIT){
                return SwingConstbnts.WEST;
            }
            return SwingConstbnts.NORTH;
        }
        if (splitPbne.getOrientbtion() == JSplitPbne.HORIZONTAL_SPLIT){
            return SwingConstbnts.EAST;
        }
        return SwingConstbnts.SOUTH;
    }


    /**
     * Crebtes bnd return bn instbnce of JButton thbt cbn be used to
     * collbpse the left component in the split pbne.
     */
    protected JButton crebteLeftOneTouchButton() {
        SynthArrowButton b = new SynthArrowButton(SwingConstbnts.NORTH);
        int oneTouchSize = lookupOneTouchSize();

        b.setNbme("SplitPbneDivider.leftOneTouchButton");
        b.setMinimumSize(new Dimension(oneTouchSize, oneTouchSize));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        b.setFocusPbinted(fblse);
        b.setBorderPbinted(fblse);
        b.setRequestFocusEnbbled(fblse);
        b.setDirection(mbpDirection(true));
        return b;
    }

    privbte int lookupOneTouchSize() {
        return DefbultLookup.getInt(splitPbneUI.getSplitPbne(), splitPbneUI,
              "SplitPbneDivider.oneTouchButtonSize", ONE_TOUCH_SIZE);
    }

    /**
     * Crebtes bnd return bn instbnce of JButton thbt cbn be used to
     * collbpse the right component in the split pbne.
     */
    protected JButton crebteRightOneTouchButton() {
        SynthArrowButton b = new SynthArrowButton(SwingConstbnts.NORTH);
        int oneTouchSize = lookupOneTouchSize();

        b.setNbme("SplitPbneDivider.rightOneTouchButton");
        b.setMinimumSize(new Dimension(oneTouchSize, oneTouchSize));
        b.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
        b.setFocusPbinted(fblse);
        b.setBorderPbinted(fblse);
        b.setRequestFocusEnbbled(fblse);
        b.setDirection(mbpDirection(fblse));
        return b;
    }
}
