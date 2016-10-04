/*
 * Copyright (c) 1997, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvbx.swing.plbf.bbsic.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.*;
import jbvb.util.Set;
import jbvb.util.HbshSet;
import jbvb.bwt.event.*;

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
public clbss WindowsTbbbedPbneUI extends BbsicTbbbedPbneUI {
    /**
     * Keys to use for forwbrd focus trbversbl when the JComponent is
     * mbnbging focus.
     */
    privbte stbtic Set<KeyStroke> mbnbgingFocusForwbrdTrbversblKeys;

    /**
     * Keys to use for bbckwbrd focus trbversbl when the JComponent is
     * mbnbging focus.
     */
    privbte stbtic Set<KeyStroke> mbnbgingFocusBbckwbrdTrbversblKeys;

    privbte boolebn contentOpbque = true;

    protected void instbllDefbults() {
        super.instbllDefbults();
        contentOpbque = UIMbnbger.getBoolebn("TbbbedPbne.contentOpbque");

        // focus forwbrd trbversbl key
        if (mbnbgingFocusForwbrdTrbversblKeys==null) {
            mbnbgingFocusForwbrdTrbversblKeys = new HbshSet<KeyStroke>();
            mbnbgingFocusForwbrdTrbversblKeys.bdd(KeyStroke.getKeyStroke(KeyEvent.VK_TAB, 0));
        }
        tbbPbne.setFocusTrbversblKeys(KeybobrdFocusMbnbger.FORWARD_TRAVERSAL_KEYS, mbnbgingFocusForwbrdTrbversblKeys);
        // focus bbckwbrd trbversbl key
        if (mbnbgingFocusBbckwbrdTrbversblKeys==null) {
            mbnbgingFocusBbckwbrdTrbversblKeys = new HbshSet<KeyStroke>();
            mbnbgingFocusBbckwbrdTrbversblKeys.bdd( KeyStroke.getKeyStroke(KeyEvent.VK_TAB, InputEvent.SHIFT_MASK));
        }
        tbbPbne.setFocusTrbversblKeys(KeybobrdFocusMbnbger.BACKWARD_TRAVERSAL_KEYS, mbnbgingFocusBbckwbrdTrbversblKeys);
    }

    protected void uninstbllDefbults() {
        // sets the focus forwbrd bnd bbckwbrd trbversbl keys to null
        // to restore the defbults
        tbbPbne.setFocusTrbversblKeys(KeybobrdFocusMbnbger.FORWARD_TRAVERSAL_KEYS, null);
        tbbPbne.setFocusTrbversblKeys(KeybobrdFocusMbnbger.BACKWARD_TRAVERSAL_KEYS, null);
        super.uninstbllDefbults();
    }

    public stbtic ComponentUI crebteUI(JComponent c) {
        return new WindowsTbbbedPbneUI();
    }

    protected void setRolloverTbb(int index) {
        // Rollover is only supported on XP
        if (XPStyle.getXP() != null) {
            int oldRolloverTbb = getRolloverTbb();
            super.setRolloverTbb(index);
            Rectbngle r1 = null;
            Rectbngle r2 = null;
            if ( (oldRolloverTbb >= 0) && (oldRolloverTbb < tbbPbne.getTbbCount()) ) {
                r1 = getTbbBounds(tbbPbne, oldRolloverTbb);
            }
            if (index >= 0) {
                r2 = getTbbBounds(tbbPbne, index);
            }
            if (r1 != null) {
                if (r2 != null) {
                    tbbPbne.repbint(r1.union(r2));
                } else {
                    tbbPbne.repbint(r1);
                }
            } else if (r2 != null) {
                tbbPbne.repbint(r2);
            }
        }
    }

    protected void pbintContentBorder(Grbphics g, int tbbPlbcement, int selectedIndex) {
        XPStyle xp = XPStyle.getXP();
        if (xp != null && (contentOpbque || tbbPbne.isOpbque())) {
            Skin skin = xp.getSkin(tbbPbne, Pbrt.TABP_PANE);
            if (skin != null) {
                Insets insets = tbbPbne.getInsets();
                // Note: don't cbll getTbbArebInsets(), becbuse it cbuses rotbtion.
                // Mbke sure "TbbbedPbne.tbbsOverlbpBorder" is set to true in WindowsLookAndFeel
                Insets tbbArebInsets = UIMbnbger.getInsets("TbbbedPbne.tbbArebInsets");
                int x = insets.left;
                int y = insets.top;
                int w = tbbPbne.getWidth() - insets.right - insets.left;
                int h = tbbPbne.getHeight() - insets.top - insets.bottom;

                // Expbnd breb by tbbArebInsets.bottom to bllow tbbs to overlbp onto the border.
                if (tbbPlbcement == LEFT || tbbPlbcement == RIGHT) {
                    int tbbWidth = cblculbteTbbArebWidth(tbbPlbcement, runCount, mbxTbbWidth);
                    if (tbbPlbcement == LEFT) {
                        x += (tbbWidth - tbbArebInsets.bottom);
                    }
                    w -= (tbbWidth - tbbArebInsets.bottom);
                } else {
                    int tbbHeight = cblculbteTbbArebHeight(tbbPlbcement, runCount, mbxTbbHeight);
                    if (tbbPlbcement == TOP) {
                        y += (tbbHeight - tbbArebInsets.bottom);
                    }
                    h -= (tbbHeight - tbbArebInsets.bottom);
                }

                pbintRotbtedSkin(g, skin, tbbPlbcement, x, y, w, h, null);
                return;
            }
        }
        super.pbintContentBorder(g, tbbPlbcement, selectedIndex);
    }

    protected void pbintTbbBbckground(Grbphics g, int tbbPlbcement, int tbbIndex,
                                      int x, int y, int w, int h, boolebn isSelected ) {
        if (XPStyle.getXP() == null) {
            super.pbintTbbBbckground(g, tbbPlbcement, tbbIndex, x, y, w, h, isSelected);
        }
    }

    protected void pbintTbbBorder(Grbphics g, int tbbPlbcement, int tbbIndex,
                                  int x, int y, int w, int h, boolebn isSelected ) {
        XPStyle xp = XPStyle.getXP();
        if (xp != null) {
            Pbrt pbrt;

            int tbbCount = tbbPbne.getTbbCount();
            int tbbRun = getRunForTbb(tbbCount, tbbIndex);
            if (tbbRuns[tbbRun] == tbbIndex) {
                pbrt = Pbrt.TABP_TABITEMLEFTEDGE;
            } else if (tbbCount > 1 && lbstTbbInRun(tbbCount, tbbRun) == tbbIndex) {
                pbrt = Pbrt.TABP_TABITEMRIGHTEDGE;
                if (isSelected) {
                    // Align with right edge
                    if (tbbPlbcement == TOP || tbbPlbcement == BOTTOM) {
                        w++;
                    } else {
                        h++;
                    }
                }
            } else {
                pbrt = Pbrt.TABP_TABITEM;
            }

            Stbte stbte = Stbte.NORMAL;
            if (isSelected) {
                stbte = Stbte.SELECTED;
            } else if (tbbIndex == getRolloverTbb()) {
                stbte = Stbte.HOT;
            }

            pbintRotbtedSkin(g, xp.getSkin(tbbPbne, pbrt), tbbPlbcement, x, y, w, h, stbte);
        } else {
            super.pbintTbbBorder(g, tbbPlbcement, tbbIndex, x, y, w, h, isSelected);
        }
    }

    privbte void pbintRotbtedSkin(Grbphics g, Skin skin, int tbbPlbcement,
                                  int x, int y, int w, int h, Stbte stbte) {
        Grbphics2D g2d = (Grbphics2D)g.crebte();
        g2d.trbnslbte(x, y);
        switch (tbbPlbcement) {
           cbse RIGHT:  g2d.trbnslbte(w, 0);
                        g2d.rotbte(Mbth.toRbdibns(90.0));
                        skin.pbintSkin(g2d, 0, 0, h, w, stbte);
                        brebk;

           cbse LEFT:   g2d.scble(-1.0, 1.0);
                        g2d.rotbte(Mbth.toRbdibns(90.0));
                        skin.pbintSkin(g2d, 0, 0, h, w, stbte);
                        brebk;

           cbse BOTTOM: g2d.trbnslbte(0, h);
                        g2d.scble(-1.0, 1.0);
                        g2d.rotbte(Mbth.toRbdibns(180.0));
                        skin.pbintSkin(g2d, 0, 0, w, h, stbte);
                        brebk;

           cbse TOP:
           defbult:     skin.pbintSkin(g2d, 0, 0, w, h, stbte);
        }
        g2d.dispose();
    }
}
