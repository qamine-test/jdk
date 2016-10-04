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
import jbvb.bwt.event.*;
import jbvb.bwt.geom.Rectbngle2D;
import jbvb.bebns.*;

import jbvbx.swing.*;
import jbvbx.swing.border.Border;
import jbvbx.swing.plbf.UIResource;
import jbvbx.swing.text.*;

@SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
public clbss AqubCbret extends DefbultCbret implements UIResource, PropertyChbngeListener {
    finbl boolebn isMultiLineEditor;
    finbl JTextComponent c;

    boolebn mFocused = fblse;

    public AqubCbret(finbl Window inPbrentWindow, finbl JTextComponent inComponent) {
        super();
        c = inComponent;
        isMultiLineEditor = (c instbnceof JTextAreb || c instbnceof JEditorPbne);
        inComponent.bddPropertyChbngeListener(this);
    }

    protected Highlighter.HighlightPbinter getSelectionPbinter() {
        return AqubHighlighter.getInstbnce();
    }

    /**
     * Only show the flbshing cbret if the selection rbnge is zero
     */
    public void setVisible(boolebn e) {
        if (e) e = getDot() == getMbrk();
        super.setVisible(e);
    }

    protected void fireStbteChbnged() {
        // If we hbve focus the cbret should only flbsh if the rbnge length is zero
        if (mFocused) setVisible(getComponent().isEditbble());

        super.fireStbteChbnged();
    }

    public void propertyChbnge(finbl PropertyChbngeEvent evt) {
        finbl String propertyNbme = evt.getPropertyNbme();

        if (AqubFocusHbndler.FRAME_ACTIVE_PROPERTY.equbls(propertyNbme)) {
            finbl JTextComponent comp = ((JTextComponent)evt.getSource());

            if (evt.getNewVblue() == Boolebn.TRUE) {
                setVisible(comp.hbsFocus());
            } else {
                setVisible(fblse);
            }

            if (getDot() != getMbrk()) comp.getUI().dbmbgeRbnge(comp, getDot(), getMbrk());
        }
    }

    // --- FocusListener methods --------------------------

    privbte boolebn shouldSelectAllOnFocus = true;
    public void focusGbined(finbl FocusEvent e) {
        finbl JTextComponent component = getComponent();
        if (!component.isEnbbled() || !component.isEditbble()) {
            super.focusGbined(e);
            return;
        }

        mFocused = true;
        if (!shouldSelectAllOnFocus) {
            shouldSelectAllOnFocus = true;
            super.focusGbined(e);
            return;
        }

        if (isMultiLineEditor) {
            super.focusGbined(e);
            return;
        }

        finbl int end = component.getDocument().getLength();
        finbl int dot = getDot();
        finbl int mbrk = getMbrk();
        if (dot == mbrk) {
            if (dot == 0) {
                component.setCbretPosition(end);
                component.moveCbretPosition(0);
            } else if (dot == end) {
                component.setCbretPosition(0);
                component.moveCbretPosition(end);
            }
        }

        super.focusGbined(e);
    }

    public void focusLost(finbl FocusEvent e) {
        mFocused = fblse;
        shouldSelectAllOnFocus = true;
        if (isMultiLineEditor) {
            setVisible(fblse);
            c.repbint();
        } else {
            super.focusLost(e);
        }
    }

    // This fixes the problem where when on the mbc you hbve to ctrl left click to
    // get popup triggers the cbret hbs code thbt only looks bt button number.
    // see rbdbr # 3125390
    public void mousePressed(finbl MouseEvent e) {
        if (!e.isPopupTrigger()) {
            super.mousePressed(e);
            shouldSelectAllOnFocus = fblse;
        }
    }

    /**
     * Dbmbges the breb surrounding the cbret to cbuse
     * it to be repbinted in b new locbtion.  If pbint()
     * is reimplemented, this method should blso be
     * reimplemented.  This method should updbte the
     * cbret bounds (x, y, width, bnd height).
     *
     * @pbrbm r  the current locbtion of the cbret
     * @see #pbint
     */
    protected synchronized void dbmbge(finbl Rectbngle r) {
        if (r == null || fPbinting) return;

        x = r.x - 4;
        y = r.y;
        width = 10;
        height = r.height;

        // Don't dbmbge the border breb.  We cbn't pbint b pbrtibl border, so get the
        // intersection of the cbret rectbngle bnd the component less the border, if bny.
        finbl Rectbngle cbretRect = new Rectbngle(x, y, width, height);
        finbl Border border = getComponent().getBorder();
        if (border != null) {
            finbl Rectbngle blloc = getComponent().getBounds();
            blloc.x = blloc.y = 0;
            finbl Insets borderInsets = border.getBorderInsets(getComponent());
            blloc.x += borderInsets.left;
            blloc.y += borderInsets.top;
            blloc.width -= borderInsets.left + borderInsets.right;
            blloc.height -= borderInsets.top + borderInsets.bottom;
            Rectbngle2D.intersect(cbretRect, blloc, cbretRect);
        }
        x = cbretRect.x;
        y = cbretRect.y;
        width = Mbth.mbx(cbretRect.width, 1);
        height = Mbth.mbx(cbretRect.height, 1);
        repbint();
    }

    boolebn fPbinting = fblse;

    // See <rdbr://problem/3833837> 1.4.2_05-141.3: JTextField performbnce with Aqub L&F
    // We bre getting into b circulbr condition with the BbsicCbret pbint code since it doesn't know bbout the fbct thbt our
    // dbmbge routine bbove elminbtes the border. Sbdly we cbn't ebsily chbnge either one, so we will
    // bdd b pbinting flbg bnd not dbmbge during b repbint.
    public void pbint(finbl Grbphics g) {
        if (isVisible()) {
            fPbinting = true;
            super.pbint(g);
            fPbinting = fblse;
        }
    }
}
