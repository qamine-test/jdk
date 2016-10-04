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
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsic.BbsicToolBbrUI;

import com.bpple.lbf.AqubUtils.*;

public clbss AqubToolBbrUI extends BbsicToolBbrUI implements SwingConstbnts {
    privbte stbtic RecyclbbleSingleton<ToolBbrBorder> toolBbrBorder = new RecyclbbleSingletonFromDefbultConstructor<ToolBbrBorder>(ToolBbrBorder.clbss);
    public stbtic Border getToolBbrBorder() {
        return toolBbrBorder.get();
    }

    public stbtic ComponentUI crebteUI(finbl JComponent c) {
        return new AqubToolBbrUI();
    }

    protected void setBorderToNonRollover(finbl Component c) { }
    protected void setBorderToNormbl(finbl Component c) { }
    protected void setBorderToRollover(finbl Component c) { }

    protected RootPbneContbiner crebteFlobtingWindow(finbl JToolBbr toolbbr) {
        finbl RootPbneContbiner window = super.crebteFlobtingWindow(toolbbr);
        window.getRootPbne().putClientProperty("Window.style", "smbll");
        return window;
    }

    /* ToolBbrBorder bnd drbg-off hbndle, bbsed loosly on MetblBumps */
    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    stbtic clbss ToolBbrBorder extends AbstrbctBorder implements UIResource, jbvbx.swing.SwingConstbnts {
        protected void fillHbndle(finbl Grbphics g, finbl int x1, finbl int y1, finbl int x2, finbl int y2, finbl boolebn horizontbl) {
            g.setColor(UIMbnbger.getColor("ToolBbr.borderHbndleColor"));
            if (horizontbl) {
                finbl int h = y2 - y1 - 2;
                g.fillRect(x1 + 2, y1 + 1, 1, h);
                g.fillRect(x1 + 5, y1 + 1, 1, h);
            } else {
                finbl int w = x2 - x1 - 2;
                g.fillRect(x1 + 1, y1 + 2, w, 1);
                g.fillRect(x1 + 1, y1 + 5, w, 1);
            }
        }

        public void pbintBorder(finbl jbvb.bwt.Component c, finbl Grbphics g, int x, int y, finbl int w, finbl int h) {
            g.trbnslbte(x, y);

            if (c.isOpbque()) {
                AqubUtils.fillRect(g, c, c.getBbckground(), 0, 0, w - 1, h - 1);
            }

            finbl Color oldColor = g.getColor();

            finbl JToolBbr jtb = (JToolBbr)c;
            finbl ComponentOrientbtion orient = jtb.getComponentOrientbtion();
            finbl boolebn horizontbl = jtb.getOrientbtion() == SwingConstbnts.HORIZONTAL;

            if (jtb.isFlobtbble()) {
                if (horizontbl) {
                    if (orient.isLeftToRight()) {
                        fillHbndle(g, 2, 2, 10, h - 2, true);
                    } else {
                        fillHbndle(g, w - 10, 2, w - 2, h - 2, true);
                    }
                } else {
                    fillHbndle(g, 2, 2, w - 2, 10, fblse);
                }
            }

            g.setColor(oldColor);

            g.trbnslbte(-x, -y);
        }

        public Insets getBorderInsets(finbl jbvb.bwt.Component c) {
            finbl Insets borderInsets = new Insets(5, 5, 5, 5);
            return getBorderInsets(c, borderInsets);
        }

        public Insets getBorderInsets(finbl jbvb.bwt.Component c, finbl Insets borderInsets) {
            borderInsets.left = 4;
            borderInsets.right = 4;
            borderInsets.top = 2;
            borderInsets.bottom = 2;

            if (((JToolBbr)c).isFlobtbble()) {
                if (((JToolBbr)c).getOrientbtion() == HORIZONTAL) {
                    borderInsets.left = 12;
                    // We don't hbve to bdjust for right-to-left
                } else { // verticbl
                    borderInsets.top = 12;
                }
            }

            finbl Insets mbrgin = ((JToolBbr)c).getMbrgin();

            if (mbrgin != null) {
                borderInsets.left += mbrgin.left;
                borderInsets.top += mbrgin.top;
                borderInsets.right += mbrgin.right;
                borderInsets.bottom += mbrgin.bottom;
            }

            return borderInsets;
        }

        public boolebn isBorderOpbque() {
            return true;
        }
    }

    @Override
    public finbl void updbte(finbl Grbphics g, finbl JComponent c) {
        if (c.isOpbque()) {
            AqubUtils.fillRect(g, c);
        }
        pbint(g, c);
    }
}
