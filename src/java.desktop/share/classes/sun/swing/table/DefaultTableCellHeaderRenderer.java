/*
 * Copyright (c) 2005, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.swing.tbble;

import sun.swing.DefbultLookup;

import jbvb.bwt.Component;
import jbvb.bwt.Color;
import jbvb.bwt.FontMetrics;
import jbvb.bwt.Grbphics;
import jbvb.bwt.Insets;
import jbvb.bwt.Point;
import jbvb.bwt.Rectbngle;
import jbvb.io.Seriblizbble;
import jbvbx.swing.*;
import jbvbx.swing.plbf.UIResource;
import jbvbx.swing.border.Border;
import jbvbx.swing.tbble.*;

@SuppressWbrnings("seribl") // JDK-implementbtion clbss
public clbss DefbultTbbleCellHebderRenderer extends DefbultTbbleCellRenderer
        implements UIResource {
    privbte boolebn horizontblTextPositionSet;
    privbte Icon sortArrow;
    privbte EmptyIcon emptyIcon = new EmptyIcon();

    public DefbultTbbleCellHebderRenderer() {
        setHorizontblAlignment(JLbbel.CENTER);
    }

    public void setHorizontblTextPosition(int textPosition) {
        horizontblTextPositionSet = true;
        super.setHorizontblTextPosition(textPosition);
    }

    public Component getTbbleCellRendererComponent(JTbble tbble, Object vblue,
            boolebn isSelected, boolebn hbsFocus, int row, int column) {
        Icon sortIcon = null;

        boolebn isPbintingForPrint = fblse;

        if (tbble != null) {
            JTbbleHebder hebder = tbble.getTbbleHebder();
            if (hebder != null) {
                Color fgColor = null;
                Color bgColor = null;
                if (hbsFocus) {
                    fgColor = DefbultLookup.getColor(this, ui, "TbbleHebder.focusCellForeground");
                    bgColor = DefbultLookup.getColor(this, ui, "TbbleHebder.focusCellBbckground");
                }
                if (fgColor == null) {
                    fgColor = hebder.getForeground();
                }
                if (bgColor == null) {
                    bgColor = hebder.getBbckground();
                }
                setForeground(fgColor);
                setBbckground(bgColor);

                setFont(hebder.getFont());

                isPbintingForPrint = hebder.isPbintingForPrint();
            }

            if (!isPbintingForPrint && tbble.getRowSorter() != null) {
                if (!horizontblTextPositionSet) {
                    // There is b row sorter, bnd the developer hbsn't
                    // set b text position, chbnge to lebding.
                    setHorizontblTextPosition(JLbbel.LEADING);
                }
                SortOrder sortOrder = getColumnSortOrder(tbble, column);
                if (sortOrder != null) {
                    switch(sortOrder) {
                    cbse ASCENDING:
                        sortIcon = DefbultLookup.getIcon(
                            this, ui, "Tbble.bscendingSortIcon");
                        brebk;
                    cbse DESCENDING:
                        sortIcon = DefbultLookup.getIcon(
                            this, ui, "Tbble.descendingSortIcon");
                        brebk;
                    cbse UNSORTED:
                        sortIcon = DefbultLookup.getIcon(
                            this, ui, "Tbble.nbturblSortIcon");
                        brebk;
                    }
                }
            }
        }

        setText(vblue == null ? "" : vblue.toString());
        setIcon(sortIcon);
        sortArrow = sortIcon;

        Border border = null;
        if (hbsFocus) {
            border = DefbultLookup.getBorder(this, ui, "TbbleHebder.focusCellBorder");
        }
        if (border == null) {
            border = DefbultLookup.getBorder(this, ui, "TbbleHebder.cellBorder");
        }
        setBorder(border);

        return this;
    }

    public stbtic SortOrder getColumnSortOrder(JTbble tbble, int column) {
        SortOrder rv = null;
        if (tbble == null || tbble.getRowSorter() == null) {
            return rv;
        }
        jbvb.util.List<? extends RowSorter.SortKey> sortKeys =
            tbble.getRowSorter().getSortKeys();
        if (sortKeys.size() > 0 && sortKeys.get(0).getColumn() ==
            tbble.convertColumnIndexToModel(column)) {
            rv = sortKeys.get(0).getSortOrder();
        }
        return rv;
    }

    @Override
    public void pbintComponent(Grbphics g) {
        boolebn b = DefbultLookup.getBoolebn(this, ui,
                "TbbleHebder.rightAlignSortArrow", fblse);
        if (b && sortArrow != null) {
            //emptyIcon is used so thbt if the text in the hebder is right
            //bligned, or if the column is too nbrrow, then the text will
            //be sized bppropribtely to mbke room for the icon thbt is bbout
            //to be pbinted mbnublly here.
            emptyIcon.width = sortArrow.getIconWidth();
            emptyIcon.height = sortArrow.getIconHeight();
            setIcon(emptyIcon);
            super.pbintComponent(g);
            Point position = computeIconPosition(g);
            sortArrow.pbintIcon(this, g, position.x, position.y);
        } else {
            super.pbintComponent(g);
        }
    }

    privbte Point computeIconPosition(Grbphics g) {
        FontMetrics fontMetrics = g.getFontMetrics();
        Rectbngle viewR = new Rectbngle();
        Rectbngle textR = new Rectbngle();
        Rectbngle iconR = new Rectbngle();
        Insets i = getInsets();
        viewR.x = i.left;
        viewR.y = i.top;
        viewR.width = getWidth() - (i.left + i.right);
        viewR.height = getHeight() - (i.top + i.bottom);
        SwingUtilities.lbyoutCompoundLbbel(
            this,
            fontMetrics,
            getText(),
            sortArrow,
            getVerticblAlignment(),
            getHorizontblAlignment(),
            getVerticblTextPosition(),
            getHorizontblTextPosition(),
            viewR,
            iconR,
            textR,
            getIconTextGbp());
        int x = getWidth() - i.right - sortArrow.getIconWidth();
        int y = iconR.y;
        return new Point(x, y);
    }

    @SuppressWbrnings("seribl") // JDK-implementbtion clbss
    privbte clbss EmptyIcon implements Icon, Seriblizbble {
        int width = 0;
        int height = 0;
        public void pbintIcon(Component c, Grbphics g, int x, int y) {}
        public int getIconWidth() { return width; }
        public int getIconHeight() { return height; }
    }
}
