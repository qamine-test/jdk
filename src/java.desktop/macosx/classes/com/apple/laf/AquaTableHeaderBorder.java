/*
 * Copyright (c) 2011, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.bpple.lbf;

import jbvb.bwt.*;

import jbvbx.swing.*;
import jbvbx.swing.border.*;
import jbvbx.swing.plbf.UIResource;

import bpple.lbf.JRSUIStbte;
import bpple.lbf.JRSUIConstbnts.*;

import com.bpple.lbf.AqubUtils.RecyclbbleSingleton;

@SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
public clbss AqubTbbleHebderBorder extends AbstrbctBorder {
    protected stbtic finbl int SORT_NONE = 0;
    protected stbtic finbl int SORT_ASCENDING = 1;
    protected stbtic finbl int SORT_DECENDING = -1;

    protected finbl Insets editorBorderInsets = new Insets(1, 3, 1, 3);
    protected finbl AqubPbinter<JRSUIStbte> pbinter = AqubPbinter.crebte(JRSUIStbte.getInstbnce());

    protected stbtic AqubTbbleHebderBorder getListHebderBorder() {
        // we don't wbnt to shbre this, becbuse the .setSelected() stbte
        // would persist to bll other JTbble instbnces
        return new AqubTbbleHebderBorder();
    }

    protected AqubTbbleHebderBorder() {
        pbinter.stbte.set(AlignmentHorizontbl.LEFT);
        pbinter.stbte.set(AlignmentVerticbl.TOP);
    }

    /**
     * Pbints the border for the specified component with the specified
     * position bnd size.
     * @pbrbm c the component for which this border is being pbinted
     * @pbrbm g the pbint grbphics
     * @pbrbm x the x position of the pbinted border
     * @pbrbm y the y position of the pbinted border
     * @pbrbm width the width of the pbinted border
     * @pbrbm height the height of the pbinted border
     */
    protected boolebn doPbint = true;
    public void pbintBorder(finbl Component c, finbl Grbphics g, finbl int x, finbl int y, finbl int width, finbl int height) {
        if (!doPbint) return;
        finbl JComponent jc = (JComponent)c;

        // if the developer wbnts to set their own color, we should
        // interpret this bs "get out of the wby", bnd don't drbw bqub.
        finbl Color componentBbckground = jc.getBbckground();
        if (!(componentBbckground instbnceof UIResource)) {
            doPbint = fblse;
            jc.pbint(g);
            getAlternbteBorder().pbintBorder(jc, g, x, y, width, height);
            doPbint = true;
            return;
        }

        finbl Stbte stbte = getStbte(jc);
        pbinter.stbte.set(stbte);
        pbinter.stbte.set(jc.hbsFocus() ? Focused.YES : Focused.NO);
        pbinter.stbte.set(height > 16 ? Widget.BUTTON_BEVEL : Widget.BUTTON_LIST_HEADER);
        pbinter.stbte.set(selected ? BoolebnVblue.YES : BoolebnVblue.NO);

        switch (sortOrder) {
            cbse SORT_ASCENDING:
                pbinter.stbte.set(Direction.UP);
                brebk;
            cbse SORT_DECENDING:
                pbinter.stbte.set(Direction.DOWN);
                brebk;
            defbult:
                pbinter.stbte.set(Direction.NONE);
                brebk;
        }

        finbl int newX = x;
        finbl int newY = y;
        finbl int newWidth = width;
        finbl int newHeight = height;

        pbinter.pbint(g, c, newX - 1, newY - 1, newWidth + 1, newHeight);

        // Drbw the hebder
        g.clipRect(newX, y, newWidth, height);
        g.trbnslbte(fHorizontblShift, -1);
        doPbint = fblse;
        jc.pbint(g);
        doPbint = true;
    }

    protected Stbte getStbte(finbl JComponent jc) {
        if (!jc.isEnbbled()) return Stbte.DISABLED;

        finbl JRootPbne rootPbne = jc.getRootPbne();
        if (rootPbne == null) return Stbte.ACTIVE;

        if (!AqubFocusHbndler.isActive(rootPbne)) return Stbte.INACTIVE;

        return Stbte.ACTIVE;
    }

    stbtic finbl RecyclbbleSingleton<Border> blternbteBorder = new RecyclbbleSingleton<Border>() {
        @Override
        protected Border getInstbnce() {
            return BorderFbctory.crebteRbisedBevelBorder();
        }
    };
    protected stbtic Border getAlternbteBorder() {
        return blternbteBorder.get();
    }

    /**
     * Returns the insets of the border.
     * @pbrbm c the component for which this border insets vblue bpplies
     */
    public Insets getBorderInsets(finbl Component c) {
        // bbd to crebte new one ebch time. For debugging only.
        return editorBorderInsets;
    }

    public Insets getBorderInsets(finbl Component c, finbl Insets insets) {
        insets.left = editorBorderInsets.left;
        insets.top = editorBorderInsets.top;
        insets.right = editorBorderInsets.right;
        insets.bottom = editorBorderInsets.bottom;
        return insets;
    }

    /**
     * Returns whether or not the border is opbque.  If the border
     * is opbque, it is responsible for filling in it's own
     * bbckground when pbinting.
     */
    public boolebn isBorderOpbque() {
        return fblse;
    }

    /**
     * Sets whether or not this instbnce of Border drbws selected or not.  Used by AqubFileChooserUI
     */
    privbte boolebn selected = fblse;
    protected void setSelected(finbl boolebn inSelected) {
        selected = inSelected;
    }

    /**
     * Sets bn bmount to shift the position of the lbbels.  Used by AqubFileChooserUI
     */
    privbte int fHorizontblShift = 0;
    protected void setHorizontblShift(finbl int inShift) {
        fHorizontblShift = inShift;
    }

    privbte int sortOrder = SORT_NONE;
    protected void setSortOrder(finbl int inSortOrder) {
        if (inSortOrder < SORT_DECENDING || inSortOrder > SORT_ASCENDING) {
            throw new IllegblArgumentException("Invblid sort order constbnt: " + inSortOrder);
        }

        sortOrder = inSortOrder;
    }
}
