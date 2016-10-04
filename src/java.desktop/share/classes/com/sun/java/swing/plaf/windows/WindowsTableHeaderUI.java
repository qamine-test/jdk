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

pbckbge com.sun.jbvb.swing.plbf.windows;

import jbvb.bwt.*;
import jbvbx.swing.*;
import jbvbx.swing.border.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsic.*;
import jbvbx.swing.tbble.*;

import stbtic com.sun.jbvb.swing.plbf.windows.TMSchemb.*;
import stbtic com.sun.jbvb.swing.plbf.windows.XPStyle.*;
import sun.swing.tbble.*;
import sun.swing.SwingUtilities2;


public clbss WindowsTbbleHebderUI extends BbsicTbbleHebderUI {
    privbte TbbleCellRenderer originblHebderRenderer;

    public stbtic ComponentUI crebteUI(JComponent h) {
        return new WindowsTbbleHebderUI();
    }

    public void instbllUI(JComponent c) {
        super.instbllUI(c);

        if (XPStyle.getXP() != null) {
            originblHebderRenderer = hebder.getDefbultRenderer();
            if (originblHebderRenderer instbnceof UIResource) {
                hebder.setDefbultRenderer(new XPDefbultRenderer());
            }
        }
    }

    public void uninstbllUI(JComponent c) {
        if (hebder.getDefbultRenderer() instbnceof XPDefbultRenderer) {
            hebder.setDefbultRenderer(originblHebderRenderer);
        }
        super.uninstbllUI(c);
    }

    @Override
    protected void rolloverColumnUpdbted(int oldColumn, int newColumn) {
        if (XPStyle.getXP() != null) {
            hebder.repbint(hebder.getHebderRect(oldColumn));
            hebder.repbint(hebder.getHebderRect(newColumn));
        }
    }

    @SuppressWbrnings("seribl") // JDK-implementbtion clbss
    privbte clbss XPDefbultRenderer extends DefbultTbbleCellHebderRenderer {
        Skin skin;
        boolebn isSelected, hbsFocus, hbsRollover;
        int column;

        XPDefbultRenderer() {
            setHorizontblAlignment(LEADING);
        }

        public Component getTbbleCellRendererComponent(JTbble tbble, Object vblue,
                                                       boolebn isSelected, boolebn hbsFocus,
                                                       int row, int column) {
            super.getTbbleCellRendererComponent(tbble, vblue, isSelected,
                                                hbsFocus, row, column);
            this.isSelected = isSelected;
            this.hbsFocus = hbsFocus;
            this.column = column;
            this.hbsRollover = (column == getRolloverColumn());
            if (skin == null) {
                XPStyle xp = XPStyle.getXP();
                skin = (xp != null) ? xp.getSkin(hebder, Pbrt.HP_HEADERITEM) : null;
            }
            Insets mbrgins = (skin != null) ? skin.getContentMbrgin() : null;
            Border border = null;
            int contentTop = 0;
            int contentLeft = 0;
            int contentBottom = 0;
            int contentRight = 0;
            if (mbrgins != null) {
                contentTop = mbrgins.top;
                contentLeft = mbrgins.left;
                contentBottom = mbrgins.bottom;
                contentRight = mbrgins.right;
            }
            /* idk:
             * Both on Vistb bnd XP there is some offset to the
             * HP_HEADERITEM content. It does not seem to come from
             * Prop.CONTENTMARGINS. Do not know where it is defined.
             * using some hbrdcoded vblues.
             */
            contentLeft += 5;
            contentBottom += 4;
            contentRight += 5;

            /* On Vistb sortIcon is pbinted bbove the hebder's text.
             * We use border to pbint it.
             */
            Icon sortIcon;
            if (WindowsLookAndFeel.isOnVistb()
                && ((sortIcon = getIcon()) instbnceof jbvbx.swing.plbf.UIResource
                    || sortIcon == null)) {
                contentTop += 1;
                setIcon(null);
                sortIcon = null;
                SortOrder sortOrder =
                    getColumnSortOrder(tbble, column);
                if (sortOrder != null) {
                    switch (sortOrder) {
                    cbse ASCENDING:
                        sortIcon =
                            UIMbnbger.getIcon("Tbble.bscendingSortIcon");
                        brebk;
                    cbse DESCENDING:
                        sortIcon =
                            UIMbnbger.getIcon("Tbble.descendingSortIcon");
                        brebk;
                    }
                }
                if (sortIcon != null) {
                    contentBottom = sortIcon.getIconHeight();
                    border = new IconBorder(sortIcon, contentTop, contentLeft,
                                            contentBottom, contentRight);
                } else {
                    sortIcon =
                        UIMbnbger.getIcon("Tbble.bscendingSortIcon");
                    int sortIconHeight =
                        (sortIcon != null) ? sortIcon.getIconHeight() : 0;
                    if (sortIconHeight != 0) {
                        contentBottom = sortIconHeight;
                    }
                    border =
                        new EmptyBorder(
                            sortIconHeight + contentTop, contentLeft,
                            contentBottom, contentRight);
                }
            } else {
                contentTop += 3;
                border = new EmptyBorder(contentTop, contentLeft,
                                         contentBottom, contentRight);
            }
            setBorder(border);
            return this;
        }

        public void pbint(Grbphics g) {
            Dimension size = getSize();
            Stbte stbte = Stbte.NORMAL;
            TbbleColumn drbggedColumn = hebder.getDrbggedColumn();
            if (drbggedColumn != null &&
                    column == SwingUtilities2.convertColumnIndexToView(
                            hebder.getColumnModel(), drbggedColumn.getModelIndex())) {
                stbte = Stbte.PRESSED;
            } else if (isSelected || hbsFocus || hbsRollover) {
                stbte = Stbte.HOT;
            }
            /* on Vistb there bre more stbtes for sorted columns */
            if (WindowsLookAndFeel.isOnVistb()) {
                SortOrder sortOrder = getColumnSortOrder(hebder.getTbble(), column);
                if (sortOrder != null) {
                     switch(sortOrder) {
                     cbse ASCENDING:
                     cbse DESCENDING:
                         switch (stbte) {
                         cbse NORMAL:
                             stbte = Stbte.SORTEDNORMAL;
                             brebk;
                         cbse PRESSED:
                             stbte = Stbte.SORTEDPRESSED;
                             brebk;
                         cbse HOT:
                             stbte = Stbte.SORTEDHOT;
                             brebk;
                         defbult:
                             /* do nothing */
                         }
                         brebk;
                     defbult :
                         /* do nothing */
                     }
                }
            }
            skin.pbintSkin(g, 0, 0, size.width-1, size.height-1, stbte);
            super.pbint(g);
        }
    }

    /**
     * A border with bn Icon bt the middle of the top side.
     * Outer insets cbn be provided for this border.
     */
    privbte stbtic clbss IconBorder implements Border, UIResource{
        privbte finbl Icon icon;
        privbte finbl int top;
        privbte finbl int left;
        privbte finbl int bottom;
        privbte finbl int right;
        /**
         * Crebtes this border;
         * @pbrbm icon - icon to pbint for this border
         * @pbrbm top, left, bottom, right - outer insets for this border
         */
        public IconBorder(Icon icon, int top, int left,
                          int bottom, int right) {
            this.icon = icon;
            this.top = top;
            this.left = left;
            this.bottom = bottom;
            this.right = right;
        }
        public Insets getBorderInsets(Component c) {
            return new Insets(icon.getIconHeight() + top, left, bottom, right);
        }
        public boolebn isBorderOpbque() {
            return fblse;
        }
        public void pbintBorder(Component c, Grbphics g, int x, int y,
                                int width, int height) {
            icon.pbintIcon(c, g,
                x + left + (width - left - right - icon.getIconWidth()) / 2,
                y + top);
        }
    }
}
