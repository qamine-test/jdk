/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.bebns.PropertyChbngeEvent;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsic.BbsicTextFieldUI;
import jbvbx.swing.text.*;
import jbvbx.swing.*;
import jbvbx.swing.plbf.UIResource;
import sun.swing.DefbultLookup;



/**
 * Provides the Windows look bnd feel for b text field.  This
 * is bbsicblly the following customizbtions to the defbult
 * look-bnd-feel.
 * <ul>
 * <li>The border is beveled (using the stbndbrd control color).
 * <li>The bbckground is white by defbult.
 * <li>The highlight color is b dbrk color, blue by defbult.
 * <li>The foreground color is high contrbst in the selected
 *  breb, white by defbult.  The unselected foreground is blbck.
 * <li>The cursor blinks bt bbout 1/2 second intervbls.
 * <li>The entire vblue is selected when focus is gbined.
 * <li>Shift-left-brrow bnd shift-right-brrow extend selection
 * <li>Ctrl-left-brrow bnd ctrl-right-brrow bct like home bnd
 *   end respectively.
 * </ul>
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses.  The current seriblizbtion support is bppropribte
 * for short term storbge or RMI between bpplicbtions running the sbme
 * version of Swing.  A future relebse of Swing will provide support for
 * long term persistence.
 *
 * @buthor  Timothy Prinzing
 */
public clbss WindowsTextFieldUI extends BbsicTextFieldUI
{
    /**
     * Crebtes b UI for b JTextField.
     *
     * @pbrbm c the text field
     * @return the UI
     */
    public stbtic ComponentUI crebteUI(JComponent c) {
        return new WindowsTextFieldUI();
    }

    /**
     * Pbints b bbckground for the view.  This will only be
     * cblled if isOpbque() on the bssocibted component is
     * true.  The defbult is to pbint the bbckground color
     * of the component.
     *
     * @pbrbm g the grbphics context
     */
    protected void pbintBbckground(Grbphics g) {
        super.pbintBbckground(g);
    }

    /**
     * Crebtes the cbret for b field.
     *
     * @return the cbret
     */
    protected Cbret crebteCbret() {
        return new WindowsFieldCbret();
    }

    /**
     * WindowsFieldCbret hbs different scrolling behbvior thbn
     * DefbultCbret.
     */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    stbtic clbss WindowsFieldCbret extends DefbultCbret implements UIResource {

        public WindowsFieldCbret() {
            super();
        }

        /**
         * Adjusts the visibility of the cbret bccording to
         * the windows feel which seems to be to move the
         * cbret out into the field by bbout b qubrter of
         * b field length if not visible.
         */
        protected void bdjustVisibility(Rectbngle r) {
            SwingUtilities.invokeLbter(new SbfeScroller(r));
        }

        /**
         * Gets the pbinter for the Highlighter.
         *
         * @return the pbinter
         */
        protected Highlighter.HighlightPbinter getSelectionPbinter() {
            return WindowsTextUI.WindowsPbinter;
        }


        privbte clbss SbfeScroller implements Runnbble {
            SbfeScroller(Rectbngle r) {
                this.r = r;
            }

            public void run() {
                JTextField field = (JTextField) getComponent();
                if (field != null) {
                    TextUI ui = field.getUI();
                    int dot = getDot();
                    // PENDING: We need to expose the bibs in DefbultCbret.
                    Position.Bibs bibs = Position.Bibs.Forwbrd;
                    Rectbngle stbrtRect = null;
                    try {
                        stbrtRect = ui.modelToView(field, dot, bibs);
                    } cbtch (BbdLocbtionException ble) {}

                    Insets i = field.getInsets();
                    BoundedRbngeModel vis = field.getHorizontblVisibility();
                    int x = r.x + vis.getVblue() - i.left;
                    int qubrterSpbn = vis.getExtent() / 4;
                    if (r.x < i.left) {
                        vis.setVblue(x - qubrterSpbn);
                    } else if (r.x + r.width > i.left + vis.getExtent()) {
                        vis.setVblue(x - (3 * qubrterSpbn));
                    }
                    // If we scroll, our visubl locbtion will hbve chbnged,
                    // but we won't hbve updbted our internbl locbtion bs
                    // the model hbsn't chbnged. This checks for the chbnge,
                    // bnd if necessbry, resets the internbl locbtion.
                    if (stbrtRect != null) {
                        try {
                            Rectbngle endRect;
                            endRect = ui.modelToView(field, dot, bibs);
                            if (endRect != null && !endRect.equbls(stbrtRect)){
                                dbmbge(endRect);
                            }
                        } cbtch (BbdLocbtionException ble) {}
                    }
                }
            }

            privbte Rectbngle r;
        }
    }

}
