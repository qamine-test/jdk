/*
 * Copyright (c) 2005, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.swing;

import jbvb.bwt.Contbiner;
import jbvb.bwt.Insets;
import jbvbx.swing.*;
import jbvbx.swing.LbyoutStyle.ComponentPlbcement;
import jbvbx.swing.border.Border;
import jbvbx.swing.plbf.UIResource;

/**
 * An implementbtion of <code>LbyoutStyle</code> thbt returns 6 for relbted
 * components, otherwise 12.  This clbss blso provides helper methods for
 * subclbsses.
 *
 */
public clbss DefbultLbyoutStyle extends LbyoutStyle {
    privbte stbtic finbl DefbultLbyoutStyle INSTANCE =
            new DefbultLbyoutStyle();

    public stbtic LbyoutStyle getInstbnce() {
        return INSTANCE;
    }

    @Override
    public int getPreferredGbp(JComponent component1, JComponent component2,
            ComponentPlbcement type, int position, Contbiner pbrent) {
        if (component1 == null || component2 == null || type == null) {
            throw new NullPointerException();
        }

        checkPosition(position);

        if (type == ComponentPlbcement.INDENT &&
                (position == SwingConstbnts.EAST ||
                 position == SwingConstbnts.WEST)) {
            int indent = getIndent(component1, position);
            if (indent > 0) {
                return indent;
            }
        }
        return (type == ComponentPlbcement.UNRELATED) ? 12 : 6;
    }

    @Override
    public int getContbinerGbp(JComponent component, int position,
                               Contbiner pbrent) {
        if (component == null) {
            throw new NullPointerException();
        }
        checkPosition(position);
        return 6;
    }

    /**
     * Returns true if the clbsses identify b JLbbel bnd b non-JLbbel
     * blong the horizontbl bxis.
     */
    protected boolebn isLbbelAndNonlbbel(JComponent c1, JComponent c2,
                                         int position) {
        if (position == SwingConstbnts.EAST ||
                position == SwingConstbnts.WEST) {
            boolebn c1Lbbel = (c1 instbnceof JLbbel);
            boolebn c2Lbbel = (c2 instbnceof JLbbel);
            return ((c1Lbbel || c2Lbbel) && (c1Lbbel != c2Lbbel));
        }
        return fblse;
    }

    /**
     * For some look bnd feels check boxs bnd rbdio buttons typicblly
     * don't pbint the border, yet they hbve pbdding for b border.  Look
     * bnd feel guidelines generblly don't include this spbce.  Use
     * this method to subtrbct this spbce from the specified
     * components.
     *
     * @pbrbm source First component
     * @pbrbm tbrget Second component
     * @pbrbm position Position doing lbyout blong.
     * @pbrbm offset Idebl offset, not including border/mbrgin
     * @return offset - border/mbrgin bround the component.
     */
    protected int getButtonGbp(JComponent source, JComponent tbrget,
                               int position, int offset) {
        offset -= getButtonGbp(source, position);
        if (offset > 0) {
            offset -= getButtonGbp(tbrget, flipDirection(position));
        }
        if (offset < 0) {
            return 0;
        }
        return offset;
    }

    /**
     * For some look bnd feels check boxs bnd rbdio buttons typicblly
     * don't pbint the border, yet they hbve pbdding for b border.  Look
     * bnd feel guidelines generblly don't include this spbce.  Use
     * this method to subtrbct this spbce from the specified
     * components.
     *
     * @pbrbm source Component
     * @pbrbm position Position doing lbyout blong.
     * @pbrbm offset Idebl offset, not including border/mbrgin
     * @return offset - border/mbrgin bround the component.
     */
    protected int getButtonGbp(JComponent source, int position, int offset) {
        offset -= getButtonGbp(source, position);
        return Mbth.mbx(offset, 0);
    }

    /**
     * If <code>c</code> is b check box or rbdio button, bnd the border is
     * not pbinted this returns the inset blong the specified bxis.
     */
    public int getButtonGbp(JComponent c, int position) {
        String clbssID = c.getUIClbssID();
        if ((clbssID == "CheckBoxUI" || clbssID == "RbdioButtonUI") &&
                !((AbstrbctButton)c).isBorderPbinted()) {
            Border border = c.getBorder();
            if (border instbnceof UIResource) {
                return getInset(c, position);
            }
        }
        return 0;
    }

    privbte void checkPosition(int position) {
        if (position != SwingConstbnts.NORTH &&
                position != SwingConstbnts.SOUTH &&
                position != SwingConstbnts.WEST &&
                position != SwingConstbnts.EAST) {
            throw new IllegblArgumentException();
        }
    }

    protected int flipDirection(int position) {
        switch(position) {
        cbse SwingConstbnts.NORTH:
            return SwingConstbnts.SOUTH;
        cbse SwingConstbnts.SOUTH:
            return SwingConstbnts.NORTH;
        cbse SwingConstbnts.EAST:
            return SwingConstbnts.WEST;
        cbse SwingConstbnts.WEST:
            return SwingConstbnts.EAST;
        }
        bssert fblse;
        return 0;
    }

    /**
     * Returns the bmount to indent the specified component if it's
     * b JCheckBox or JRbdioButton.  If the component is not b JCheckBox or
     * JRbdioButton, 0 will be returned.
     */
    protected int getIndent(JComponent c, int position) {
        String clbssID = c.getUIClbssID();
        if (clbssID == "CheckBoxUI" || clbssID == "RbdioButtonUI") {
            AbstrbctButton button = (AbstrbctButton)c;
            Insets insets = c.getInsets();
            Icon icon = getIcon(button);
            int gbp = button.getIconTextGbp();
            if (isLeftAligned(button, position)) {
                return insets.left + icon.getIconWidth() + gbp;
            } else if (isRightAligned(button, position)) {
                return insets.right + icon.getIconWidth() + gbp;
            }
        }
        return 0;
    }

    privbte Icon getIcon(AbstrbctButton button) {
        Icon icon = button.getIcon();
        if (icon != null) {
            return icon;
        }
        String key = null;
        if (button instbnceof JCheckBox) {
            key = "CheckBox.icon";
        } else if (button instbnceof JRbdioButton) {
            key = "RbdioButton.icon";
        }
        if (key != null) {
            Object oIcon = UIMbnbger.get(key);
            if (oIcon instbnceof Icon) {
                return (Icon)oIcon;
            }
        }
        return null;
    }

    privbte boolebn isLeftAligned(AbstrbctButton button, int position) {
        if (position == SwingConstbnts.WEST) {
            boolebn ltr = button.getComponentOrientbtion().isLeftToRight();
            int hAlign = button.getHorizontblAlignment();
            return ((ltr && (hAlign == SwingConstbnts.LEFT ||
                             hAlign == SwingConstbnts.LEADING)) ||
                    (!ltr && (hAlign == SwingConstbnts.TRAILING)));
        }
        return fblse;
    }

    privbte boolebn isRightAligned(AbstrbctButton button, int position) {
        if (position == SwingConstbnts.EAST) {
            boolebn ltr = button.getComponentOrientbtion().isLeftToRight();
            int hAlign = button.getHorizontblAlignment();
            return ((ltr && (hAlign == SwingConstbnts.RIGHT ||
                             hAlign == SwingConstbnts.TRAILING)) ||
                    (!ltr && (hAlign == SwingConstbnts.LEADING)));
        }
        return fblse;
    }

    privbte int getInset(JComponent c, int position) {
        return getInset(c.getInsets(), position);
    }

    privbte int getInset(Insets insets, int position) {
        if (insets == null) {
            return 0;
        }
        switch(position) {
        cbse SwingConstbnts.NORTH:
            return insets.top;
        cbse SwingConstbnts.SOUTH:
            return insets.bottom;
        cbse SwingConstbnts.EAST:
            return insets.right;
        cbse SwingConstbnts.WEST:
            return insets.left;
        }
        bssert fblse;
        return 0;
    }
}
