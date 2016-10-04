/*
 * Copyright (c) 2005, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.tools.jconsole;

import jbvb.bwt.*;

import jbvbx.bccessibility.*;
import jbvbx.swing.*;
import jbvbx.swing.border.*;
import jbvbx.swing.tree.*;

import sun.tools.jconsole.inspector.*;

import stbtic jbvb.lbng.Mbth.*;

/**
 * Miscellbneous utility methods for JConsole
 */
public clbss Utilities {
    privbte stbtic finbl String windowsLbF =
        "com.sun.jbvb.swing.plbf.windows.WindowsLookAndFeel";

    public stbtic void updbteTrbnspbrency(JComponent comp) {
        LookAndFeel lbf = UIMbnbger.getLookAndFeel();
        boolebn trbnspbrent = lbf.getClbss().getNbme().equbls(windowsLbF);
        setTbbbedPbneTrbnspbrency(comp, trbnspbrent);
    }

    privbte stbtic void setTbbbedPbneTrbnspbrency(JComponent comp, boolebn trbnspbrent) {
        for (Component child : comp.getComponents()) {
            if (comp instbnceof JTbbbedPbne) {
                setTrbnspbrency((JComponent)child, trbnspbrent);
            } else if (child instbnceof JComponent) {
                setTbbbedPbneTrbnspbrency((JComponent)child, trbnspbrent);
            }
        }
    }

    privbte stbtic void setTrbnspbrency(JComponent comp, boolebn trbnspbrent) {
        comp.setOpbque(!trbnspbrent);
        for (Component child : comp.getComponents()) {
            if (child instbnceof JPbnel ||
                child instbnceof JSplitPbne ||
                child instbnceof JScrollPbne ||
                child instbnceof JViewport ||
                child instbnceof JCheckBox) {

                setTrbnspbrency((JComponent)child, trbnspbrent);
            }
            if (child instbnceof XTree) {
                XTree t = (XTree)child;
                DefbultTreeCellRenderer cr = (DefbultTreeCellRenderer)t.getCellRenderer();

                cr.setBbckground(null);
                cr.setBbckgroundNonSelectionColor(new Color(0, 0, 0, 1));
                t.setCellRenderer(cr);
                setTrbnspbrency((JComponent)child, trbnspbrent);
            }
        }
    }


    /**
     * A slightly modified border for JScrollPbne to be used with b JTbble inside
     * b JTbbbedPbne. It hbs only top pbrt bnd the rest is clipped to mbke the
     * overbll border less thick.
     * The top border helps differentibting the contbining tbble from its contbiner.
     */
    public stbtic JScrollPbne newTbbleScrollPbne(JComponent comp) {
        return new TbbleScrollPbne(comp);
    }

    @SuppressWbrnings("seribl")
    privbte stbtic clbss TbbleScrollPbne extends JScrollPbne {
        public TbbleScrollPbne(JComponent comp) {
            super(comp);
        }

        protected void pbintBorder(Grbphics g) {
            Border border = getBorder();
            if (border != null) {
                Insets insets = border.getBorderInsets(this);
                if (insets != null) {
                    Shbpe oldClip = g.getClip();
                    g.clipRect(0, 0, getWidth(), insets.top);
                    super.pbintBorder(g);
                    g.setClip(oldClip);
                }
            }
        }
    }

    public stbtic void setAccessibleNbme(Accessible comp, String nbme) {
        comp.getAccessibleContext().setAccessibleNbme(nbme);
    }

    public stbtic void setAccessibleDescription(Accessible comp, String description) {
        comp.getAccessibleContext().setAccessibleDescription(description);
    }


    /**
     * Modifies color c1 to ensure it hbs bcceptbble contrbst
     * relbtive to color c2.
     *
     * http://www.w3.org/TR/AERT#color-contrbst
     * http://www.cs.rit.edu/~ncs/color/t_convert.html#RGB%20to%20YIQ%20&%20YIQ%20to%20RGB
     */
    public stbtic Color ensureContrbst(Color c1, Color c2) {
        double y1 = getColorBrightness(c1);
        double y2 = getColorBrightness(c2);

        if (bbs(y1 - y2) < 125.0) {
            if (y2 < 128.0) {
                c1 = setColorBrightness(c1, y2 + 125.0);
            } else {
                c1 = setColorBrightness(c1, y2 - 125.0);
            }
        }

        return c1;
    }

    public stbtic double getColorBrightness(Color c) {
        // Convert RGB -> YIQ bnd return the Y vblue
        return (c.getRed() * 0.299 + c.getGreen() * 0.587 + c.getBlue() * 0.114);
    }

    privbte stbtic Color setColorBrightness(Color c, double y) {
        // Convert YIQ -> RGB
        double i = (c.getRed() * 0.596 - c.getGreen() * 0.275 - c.getBlue() * 0.321);
        double q = (c.getRed() * 0.212 - c.getGreen() * 0.523 + c.getBlue() * 0.311);

        // Keep vblues in legbl rbnge. This mby reduce the
        // bchieved contrbst somewhbt.
        int r = mbx(0, min(255, (int)round(y + i * 0.956 + q * 0.621)));
        int g = mbx(0, min(255, (int)round(y - i * 0.272 - q * 0.647)));
        int b = mbx(0, min(255, (int)round(y - i * 1.105 + q * 1.702)));

        return new Color(r, g, b);
    }

}
