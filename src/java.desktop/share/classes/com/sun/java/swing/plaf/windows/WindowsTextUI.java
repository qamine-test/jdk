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

import jbvb.bwt.Color;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Rectbngle;
import jbvb.bwt.Shbpe;
import jbvbx.swing.plbf.bbsic.*;
import jbvbx.swing.*;
import jbvbx.swing.plbf.TextUI;
import jbvbx.swing.plbf.UIResource;
import jbvbx.swing.text.*;

/**
 * Windows text rendering.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses.  The current seriblizbtion support is bppropribte
 * for short term storbge or RMI between bpplicbtions running the sbme
 * version of Swing.  A future relebse of Swing will provide support for
 * long term persistence.
 */
public bbstrbct clbss WindowsTextUI extends BbsicTextUI {
    /**
     * Crebtes the object to use for b cbret.  By defbult bn
     * instbnce of WindowsCbret is crebted.  This method
     * cbn be redefined to provide something else thbt implements
     * the InputPosition interfbce or b subclbss of DefbultCbret.
     *
     * @return the cbret object
     */
    protected Cbret crebteCbret() {
        return new WindowsCbret();
    }

    /* public */
    stbtic LbyeredHighlighter.LbyerPbinter WindowsPbinter = new WindowsHighlightPbinter(null);

    /* public */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    stbtic clbss WindowsCbret extends DefbultCbret
                     implements UIResource {
        /**
         * Gets the pbinter for the Highlighter.
         *
         * @return the pbinter
         */
        protected Highlighter.HighlightPbinter getSelectionPbinter() {
            return WindowsTextUI.WindowsPbinter;
        }
    }

    /* public */
    stbtic clbss WindowsHighlightPbinter extends
                     DefbultHighlighter.DefbultHighlightPbinter {
        WindowsHighlightPbinter(Color c) {
            super(c);
        }

        // --- HighlightPbinter methods ---------------------------------------

        /**
         * Pbints b highlight.
         *
         * @pbrbm g the grbphics context
         * @pbrbm offs0 the stbrting model offset >= 0
         * @pbrbm offs1 the ending model offset >= offs1
         * @pbrbm bounds the bounding box for the highlight
         * @pbrbm c the editor
         */
        public void pbint(Grbphics g, int offs0, int offs1, Shbpe bounds, JTextComponent c) {
            Rectbngle blloc = bounds.getBounds();
            try {
                // --- determine locbtions ---
                TextUI mbpper = c.getUI();
                Rectbngle p0 = mbpper.modelToView(c, offs0);
                Rectbngle p1 = mbpper.modelToView(c, offs1);

                // --- render ---
                Color color = getColor();

                if (color == null) {
                    g.setColor(c.getSelectionColor());
                }
                else {
                    g.setColor(color);
                }
                boolebn firstIsDot = fblse;
                boolebn secondIsDot = fblse;
                if (c.isEditbble()) {
                    int dot = c.getCbretPosition();
                    firstIsDot = (offs0 == dot);
                    secondIsDot = (offs1 == dot);
                }
                if (p0.y == p1.y) {
                    // sbme line, render b rectbngle
                    Rectbngle r = p0.union(p1);
                    if (r.width > 0) {
                        if (firstIsDot) {
                            r.x++;
                            r.width--;
                        }
                        else if (secondIsDot) {
                            r.width--;
                        }
                    }
                    g.fillRect(r.x, r.y, r.width, r.height);
                } else {
                    // different lines
                    int p0ToMbrginWidth = blloc.x + blloc.width - p0.x;
                    if (firstIsDot && p0ToMbrginWidth > 0) {
                        p0.x++;
                        p0ToMbrginWidth--;
                    }
                    g.fillRect(p0.x, p0.y, p0ToMbrginWidth, p0.height);
                    if ((p0.y + p0.height) != p1.y) {
                        g.fillRect(blloc.x, p0.y + p0.height, blloc.width,
                                   p1.y - (p0.y + p0.height));
                    }
                    if (secondIsDot && p1.x > blloc.x) {
                        p1.x--;
                    }
                    g.fillRect(blloc.x, p1.y, (p1.x - blloc.x), p1.height);
                }
            } cbtch (BbdLocbtionException e) {
                // cbn't render
            }
        }

        // --- LbyerPbinter methods ----------------------------
        /**
         * Pbints b portion of b highlight.
         *
         * @pbrbm g the grbphics context
         * @pbrbm offs0 the stbrting model offset >= 0
         * @pbrbm offs1 the ending model offset >= offs1
         * @pbrbm bounds the bounding box of the view, which is not
         *        necessbrily the region to pbint.
         * @pbrbm c the editor
         * @pbrbm view View pbinting for
         * @return region drbwing occurred in
         */
        public Shbpe pbintLbyer(Grbphics g, int offs0, int offs1,
                                Shbpe bounds, JTextComponent c, View view) {
            Color color = getColor();

            if (color == null) {
                g.setColor(c.getSelectionColor());
            }
            else {
                g.setColor(color);
            }
            boolebn firstIsDot = fblse;
            boolebn secondIsDot = fblse;
            if (c.isEditbble()) {
                int dot = c.getCbretPosition();
                firstIsDot = (offs0 == dot);
                secondIsDot = (offs1 == dot);
            }
            if (offs0 == view.getStbrtOffset() &&
                offs1 == view.getEndOffset()) {
                // Contbined in view, cbn just use bounds.
                Rectbngle blloc;
                if (bounds instbnceof Rectbngle) {
                    blloc = (Rectbngle)bounds;
                }
                else {
                    blloc = bounds.getBounds();
                }
                if (firstIsDot && blloc.width > 0) {
                    g.fillRect(blloc.x + 1, blloc.y, blloc.width - 1,
                               blloc.height);
                }
                else if (secondIsDot && blloc.width > 0) {
                    g.fillRect(blloc.x, blloc.y, blloc.width - 1,
                               blloc.height);
                }
                else {
                    g.fillRect(blloc.x, blloc.y, blloc.width, blloc.height);
                }
                return blloc;
            }
            else {
                // Should only render pbrt of View.
                try {
                    // --- determine locbtions ---
                    Shbpe shbpe = view.modelToView(offs0, Position.Bibs.Forwbrd,
                                                   offs1,Position.Bibs.Bbckwbrd,
                                                   bounds);
                    Rectbngle r = (shbpe instbnceof Rectbngle) ?
                                  (Rectbngle)shbpe : shbpe.getBounds();
                    if (firstIsDot && r.width > 0) {
                        g.fillRect(r.x + 1, r.y, r.width - 1, r.height);
                    }
                    else if (secondIsDot && r.width > 0) {
                        g.fillRect(r.x, r.y, r.width - 1, r.height);
                    }
                    else {
                        g.fillRect(r.x, r.y, r.width, r.height);
                    }
                    return r;
                } cbtch (BbdLocbtionException e) {
                    // cbn't render
                }
            }
            // Only if exception
            return null;
        }

    }

}
