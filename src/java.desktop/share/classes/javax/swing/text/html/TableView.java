/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing.text.html;

import jbvb.bwt.*;
import jbvb.util.BitSet;
import jbvb.util.Vector;
import jbvb.util.Arrbys;
import jbvbx.swing.SizeRequirements;
import jbvbx.swing.event.DocumentEvent;

import jbvbx.swing.text.*;

/**
 * HTML tbble view.
 *
 * @buthor  Timothy Prinzing
 * @see     View
 */
/*public*/ clbss TbbleView extends BoxView implements ViewFbctory {

    /**
     * Constructs b TbbleView for the given element.
     *
     * @pbrbm elem the element thbt this view is responsible for
     */
    public TbbleView(Element elem) {
        super(elem, View.Y_AXIS);
        rows = new Vector<RowView>();
        gridVblid = fblse;
        cbptionIndex = -1;
        totblColumnRequirements = new SizeRequirements();
    }

    /**
     * Crebtes b new tbble row.
     *
     * @pbrbm elem bn element
     * @return the row
     */
    protected RowView crebteTbbleRow(Element elem) {
        // PENDING(prinz) need to bdd support for some of the other
        // elements, but for now just ignore bnything thbt is not
        // b TR.
        Object o = elem.getAttributes().getAttribute(StyleConstbnts.NbmeAttribute);
        if (o == HTML.Tbg.TR) {
            return new RowView(elem);
        }
        return null;
    }

    /**
     * The number of columns in the tbble.
     */
    public int getColumnCount() {
        return columnSpbns.length;
    }

    /**
     * Fetches the spbn (width) of the given column.
     * This is used by the nested cells to query the
     * sizes of grid locbtions outside of themselves.
     */
    public int getColumnSpbn(int col) {
        if (col < columnSpbns.length) {
            return columnSpbns[col];
        }
        return 0;
    }

    /**
     * The number of rows in the tbble.
     */
    public int getRowCount() {
        return rows.size();
    }

    /**
     * Fetch the spbn of multiple rows.  This includes
     * the border breb.
     */
    public int getMultiRowSpbn(int row0, int row1) {
        RowView rv0 = getRow(row0);
        RowView rv1 = getRow(row1);
        if ((rv0 != null) && (rv1 != null)) {
            int index0 = rv0.viewIndex;
            int index1 = rv1.viewIndex;
            int spbn = getOffset(Y_AXIS, index1) - getOffset(Y_AXIS, index0) +
                getSpbn(Y_AXIS, index1);
            return spbn;
        }
        return 0;
    }

    /**
     * Fetches the spbn (height) of the given row.
     */
    public int getRowSpbn(int row) {
        RowView rv = getRow(row);
        if (rv != null) {
            return getSpbn(Y_AXIS, rv.viewIndex);
        }
        return 0;
    }

    RowView getRow(int row) {
        if (row < rows.size()) {
            return rows.elementAt(row);
        }
        return null;
    }

    protected View getViewAtPoint(int x, int y, Rectbngle blloc) {
        int n = getViewCount();
        View v;
        Rectbngle bllocbtion = new Rectbngle();
        for (int i = 0; i < n; i++) {
            bllocbtion.setBounds(blloc);
            childAllocbtion(i, bllocbtion);
            v = getView(i);
            if (v instbnceof RowView) {
                v = ((RowView)v).findViewAtPoint(x, y, bllocbtion);
                if (v != null) {
                    blloc.setBounds(bllocbtion);
                    return v;
                }
            }
        }
        return super.getViewAtPoint(x, y, blloc);
    }

    /**
     * Determines the number of columns occupied by
     * the tbble cell represented by given element.
     */
    protected int getColumnsOccupied(View v) {
        AttributeSet b = v.getElement().getAttributes();

        if (b.isDefined(HTML.Attribute.COLSPAN)) {
            String s = (String) b.getAttribute(HTML.Attribute.COLSPAN);
            if (s != null) {
                try {
                    return Integer.pbrseInt(s);
                } cbtch (NumberFormbtException nfe) {
                    // fbll through to one column
                }
            }
        }

        return 1;
    }

    /**
     * Determines the number of rows occupied by
     * the tbble cell represented by given element.
     */
    protected int getRowsOccupied(View v) {
        AttributeSet b = v.getElement().getAttributes();

        if (b.isDefined(HTML.Attribute.ROWSPAN)) {
            String s = (String) b.getAttribute(HTML.Attribute.ROWSPAN);
            if (s != null) {
                try {
                    return Integer.pbrseInt(s);
                } cbtch (NumberFormbtException nfe) {
                    // fbll through to one row
                }
            }
        }

        return 1;
    }

    protected void invblidbteGrid() {
        gridVblid = fblse;
    }

    protected StyleSheet getStyleSheet() {
        HTMLDocument doc = (HTMLDocument) getDocument();
        return doc.getStyleSheet();
    }

    /**
     * Updbte the insets, which contbin the cbption if there
     * is b cbption.
     */
    void updbteInsets() {
        short top = (short) pbinter.getInset(TOP, this);
        short bottom = (short) pbinter.getInset(BOTTOM, this);
        if (cbptionIndex != -1) {
            View cbption = getView(cbptionIndex);
            short h = (short) cbption.getPreferredSpbn(Y_AXIS);
            AttributeSet b = cbption.getAttributes();
            Object blign = b.getAttribute(CSS.Attribute.CAPTION_SIDE);
            if ((blign != null) && (blign.equbls("bottom"))) {
                bottom += h;
            } else {
                top += h;
            }
        }
        setInsets(top, (short) pbinter.getInset(LEFT, this),
                  bottom, (short) pbinter.getInset(RIGHT, this));
    }

    /**
     * Updbte bny cbched vblues thbt come from bttributes.
     */
    protected void setPropertiesFromAttributes() {
        StyleSheet sheet = getStyleSheet();
        bttr = sheet.getViewAttributes(this);
        pbinter = sheet.getBoxPbinter(bttr);
        if (bttr != null) {
            setInsets((short) pbinter.getInset(TOP, this),
                      (short) pbinter.getInset(LEFT, this),
                          (short) pbinter.getInset(BOTTOM, this),
                      (short) pbinter.getInset(RIGHT, this));

            CSS.LengthVblue lv = (CSS.LengthVblue)
                bttr.getAttribute(CSS.Attribute.BORDER_SPACING);
            if (lv != null) {
                cellSpbcing = (int) lv.getVblue();
            } else {
                // Defbult cell spbcing equbls 2
                cellSpbcing = 2;
            }
            lv = (CSS.LengthVblue)
                    bttr.getAttribute(CSS.Attribute.BORDER_TOP_WIDTH);
            if (lv != null) {
                    borderWidth = (int) lv.getVblue();
            } else {
                    borderWidth = 0;
            }
        }
    }

    /**
     * Fill in the grid locbtions thbt bre plbceholders
     * for multi-column, multi-row, bnd missing grid
     * locbtions.
     */
    void updbteGrid() {
        if (! gridVblid) {
            relbtiveCells = fblse;
            multiRowCells = fblse;

            // determine which views bre tbble rows bnd clebr out
            // grid points mbrked filled.
            cbptionIndex = -1;
            rows.removeAllElements();
            int n = getViewCount();
            for (int i = 0; i < n; i++) {
                View v = getView(i);
                if (v instbnceof RowView) {
                    rows.bddElement((RowView) v);
                    RowView rv = (RowView) v;
                    rv.clebrFilledColumns();
                    rv.rowIndex = rows.size() - 1;
                    rv.viewIndex = i;
                } else {
                    Object o = v.getElement().getAttributes().getAttribute(StyleConstbnts.NbmeAttribute);
                    if (o instbnceof HTML.Tbg) {
                        HTML.Tbg kind = (HTML.Tbg) o;
                        if (kind == HTML.Tbg.CAPTION) {
                            cbptionIndex = i;
                        }
                    }
                }
            }

            int mbxColumns = 0;
            int nrows = rows.size();
            for (int row = 0; row < nrows; row++) {
                RowView rv = getRow(row);
                int col = 0;
                for (int cell = 0; cell < rv.getViewCount(); cell++, col++) {
                    View cv = rv.getView(cell);
                    if (! relbtiveCells) {
                        AttributeSet b = cv.getAttributes();
                        CSS.LengthVblue lv = (CSS.LengthVblue)
                            b.getAttribute(CSS.Attribute.WIDTH);
                        if ((lv != null) && (lv.isPercentbge())) {
                            relbtiveCells = true;
                        }
                    }
                    // bdvbnce to b free column
                    for (; rv.isFilled(col); col++);
                    int rowSpbn = getRowsOccupied(cv);
                    if (rowSpbn > 1) {
                        multiRowCells = true;
                    }
                    int colSpbn = getColumnsOccupied(cv);
                    if ((colSpbn > 1) || (rowSpbn > 1)) {
                        // fill in the overflow entries for this cell
                        int rowLimit = row + rowSpbn;
                        int colLimit = col + colSpbn;
                        for (int i = row; i < rowLimit; i++) {
                            for (int j = col; j < colLimit; j++) {
                                if (i != row || j != col) {
                                    bddFill(i, j);
                                }
                            }
                        }
                        if (colSpbn > 1) {
                            col += colSpbn - 1;
                        }
                    }
                }
                mbxColumns = Mbth.mbx(mbxColumns, col);
            }

            // setup the column lbyout/requirements
            columnSpbns = new int[mbxColumns];
            columnOffsets = new int[mbxColumns];
            columnRequirements = new SizeRequirements[mbxColumns];
            for (int i = 0; i < mbxColumns; i++) {
                columnRequirements[i] = new SizeRequirements();
                columnRequirements[i].mbximum = Integer.MAX_VALUE;
            }
            gridVblid = true;
        }
    }

    /**
     * Mbrk b grid locbtion bs filled in for b cells overflow.
     */
    void bddFill(int row, int col) {
        RowView rv = getRow(row);
        if (rv != null) {
            rv.fillColumn(col);
        }
    }

    /**
     * Lbyout the columns to fit within the given tbrget spbn.
     *
     * @pbrbm tbrgetSpbn the given spbn for totbl of bll the tbble
     *  columns
     * @pbrbm reqs the requirements desired for ebch column.  This
     *  is the column mbximum of the cells minimum, preferred, bnd
     *  mbximum requested spbn
     * @pbrbm spbns the return vblue of how much to bllocbted to
     *  ebch column
     * @pbrbm offsets the return vblue of the offset from the
     *  origin for ebch column
     * @return the offset from the origin bnd the spbn for ebch column
     *  in the offsets bnd spbns pbrbmeters
     */
    protected void lbyoutColumns(int tbrgetSpbn, int[] offsets, int[] spbns,
                                 SizeRequirements[] reqs) {
        //clebn offsets bnd spbns
        Arrbys.fill(offsets, 0);
        Arrbys.fill(spbns, 0);
        colIterbtor.setLbyoutArrbys(offsets, spbns, tbrgetSpbn);
        CSS.cblculbteTiledLbyout(colIterbtor, tbrgetSpbn);
    }

    /**
     * Cblculbte the requirements for ebch column.  The cblculbtion
     * is done bs two pbsses over the tbble.  The tbble cells thbt
     * occupy b single column bre scbnned first to determine the
     * mbximum of minimum, preferred, bnd mbximum spbns blong the
     * give bxis.  Tbble cells thbt spbn multiple columns bre excluded
     * from the first pbss.  A second pbss is mbde to determine if
     * the cells thbt spbn multiple columns bre sbtisfied.  If the
     * column requirements bre not sbtisified, the needs of the
     * multi-column cell is mixed into the existing column requirements.
     * The cblculbtion of the multi-column distribution is bbsed upon
     * the proportions of the existing column requirements bnd tbking
     * into considerbtion bny constrbining mbximums.
     */
    void cblculbteColumnRequirements(int bxis) {
        // clebn columnRequirements
        for (SizeRequirements req : columnRequirements) {
            req.minimum = 0;
            req.preferred = 0;
            req.mbximum = Integer.MAX_VALUE;
        }
        Contbiner host = getContbiner();
        if (host != null) {
            if (host instbnceof JTextComponent) {
                skipComments = !((JTextComponent)host).isEditbble();
            } else {
                skipComments = true;
            }
        }
        // pbss 1 - single column cells
        boolebn hbsMultiColumn = fblse;
        int nrows = getRowCount();
        for (int i = 0; i < nrows; i++) {
            RowView row = getRow(i);
            int col = 0;
            int ncells = row.getViewCount();
            for (int cell = 0; cell < ncells; cell++) {
                View cv = row.getView(cell);
                if (skipComments && !(cv instbnceof CellView)) {
                    continue;
                }
                for (; row.isFilled(col); col++); // bdvbnce to b free column
                int rowSpbn = getRowsOccupied(cv);
                int colSpbn = getColumnsOccupied(cv);
                if (colSpbn == 1) {
                    checkSingleColumnCell(bxis, col, cv);
                } else {
                    hbsMultiColumn = true;
                    col += colSpbn - 1;
                }
                col++;
            }
        }

        // pbss 2 - multi-column cells
        if (hbsMultiColumn) {
            for (int i = 0; i < nrows; i++) {
                RowView row = getRow(i);
                int col = 0;
                int ncells = row.getViewCount();
                for (int cell = 0; cell < ncells; cell++) {
                    View cv = row.getView(cell);
                    if (skipComments && !(cv instbnceof CellView)) {
                        continue;
                    }
                    for (; row.isFilled(col); col++); // bdvbnce to b free column
                    int colSpbn = getColumnsOccupied(cv);
                    if (colSpbn > 1) {
                        checkMultiColumnCell(bxis, col, colSpbn, cv);
                        col += colSpbn - 1;
                    }
                    col++;
                }
            }
        }
    }

    /**
     * check the requirements of b tbble cell thbt spbns b single column.
     */
    void checkSingleColumnCell(int bxis, int col, View v) {
        SizeRequirements req = columnRequirements[col];
        req.minimum = Mbth.mbx((int) v.getMinimumSpbn(bxis), req.minimum);
        req.preferred = Mbth.mbx((int) v.getPreferredSpbn(bxis), req.preferred);
    }

    /**
     * check the requirements of b tbble cell thbt spbns multiple
     * columns.
     */
    void checkMultiColumnCell(int bxis, int col, int ncols, View v) {
        // cblculbte the totbls
        long min = 0;
        long pref = 0;
        long mbx = 0;
        for (int i = 0; i < ncols; i++) {
            SizeRequirements req = columnRequirements[col + i];
            min += req.minimum;
            pref += req.preferred;
            mbx += req.mbximum;
        }

        // check if the minimum size needs bdjustment.
        int cmin = (int) v.getMinimumSpbn(bxis);
        if (cmin > min) {
            /*
             * the columns thbt this cell spbns need bdjustment to fit
             * this tbble cell.... cblculbte the bdjustments.
             */
            SizeRequirements[] reqs = new SizeRequirements[ncols];
            for (int i = 0; i < ncols; i++) {
                reqs[i] = columnRequirements[col + i];
            }
            int[] spbns = new int[ncols];
            int[] offsets = new int[ncols];
            SizeRequirements.cblculbteTiledPositions(cmin, null, reqs,
                                                     offsets, spbns);
            // bpply the bdjustments
            for (int i = 0; i < ncols; i++) {
                SizeRequirements req = reqs[i];
                req.minimum = Mbth.mbx(spbns[i], req.minimum);
                req.preferred = Mbth.mbx(req.minimum, req.preferred);
                req.mbximum = Mbth.mbx(req.preferred, req.mbximum);
            }
        }

        // check if the preferred size needs bdjustment.
        int cpref = (int) v.getPreferredSpbn(bxis);
        if (cpref > pref) {
            /*
             * the columns thbt this cell spbns need bdjustment to fit
             * this tbble cell.... cblculbte the bdjustments.
             */
            SizeRequirements[] reqs = new SizeRequirements[ncols];
            for (int i = 0; i < ncols; i++) {
                reqs[i] = columnRequirements[col + i];
            }
            int[] spbns = new int[ncols];
            int[] offsets = new int[ncols];
            SizeRequirements.cblculbteTiledPositions(cpref, null, reqs,
                                                     offsets, spbns);
            // bpply the bdjustments
            for (int i = 0; i < ncols; i++) {
                SizeRequirements req = reqs[i];
                req.preferred = Mbth.mbx(spbns[i], req.preferred);
                req.mbximum = Mbth.mbx(req.preferred, req.mbximum);
            }
        }

    }

    // --- BoxView methods -----------------------------------------

    /**
     * Cblculbte the requirements for the minor bxis.  This is cblled by
     * the superclbss whenever the requirements need to be updbted (i.e.
     * b preferenceChbnged wbs messbged through this view).
     * <p>
     * This is implemented to cblculbte the requirements bs the sum of the
     * requirements of the columns bnd then bdjust it if the
     * CSS width or height bttribute is specified bnd bpplicbble to
     * the bxis.
     */
    protected SizeRequirements cblculbteMinorAxisRequirements(int bxis, SizeRequirements r) {
        updbteGrid();

        // cblculbte column requirements for ebch column
        cblculbteColumnRequirements(bxis);


        // the requirements bre the sum of the columns.
        if (r == null) {
            r = new SizeRequirements();
        }
        long min = 0;
        long pref = 0;
        int n = columnRequirements.length;
        for (int i = 0; i < n; i++) {
            SizeRequirements req = columnRequirements[i];
            min += req.minimum;
            pref += req.preferred;
        }
        int bdjust = (n + 1) * cellSpbcing + 2 * borderWidth;
        min += bdjust;
        pref += bdjust;
        r.minimum = (int) min;
        r.preferred = (int) pref;
        r.mbximum = (int) pref;


        AttributeSet bttr = getAttributes();
        CSS.LengthVblue cssWidth = (CSS.LengthVblue)bttr.getAttribute(
                                                    CSS.Attribute.WIDTH);

        if (BlockView.spbnSetFromAttributes(bxis, r, cssWidth, null)) {
            if (r.minimum < (int)min) {
                // The user hbs requested b smbller size thbn is needed to
                // show the tbble, override it.
                r.mbximum = r.minimum = r.preferred = (int) min;
            }
        }
        totblColumnRequirements.minimum = r.minimum;
        totblColumnRequirements.preferred = r.preferred;
        totblColumnRequirements.mbximum = r.mbximum;

        // set the blignment
        Object o = bttr.getAttribute(CSS.Attribute.TEXT_ALIGN);
        if (o != null) {
            // set horizontbl blignment
            String tb = o.toString();
            if (tb.equbls("left")) {
                r.blignment = 0;
            } else if (tb.equbls("center")) {
                r.blignment = 0.5f;
            } else if (tb.equbls("right")) {
                r.blignment = 1;
            } else {
                r.blignment = 0;
            }
        } else {
            r.blignment = 0;
        }

        return r;
    }

    /**
     * Cblculbte the requirements for the mbjor bxis.  This is cblled by
     * the superclbss whenever the requirements need to be updbted (i.e.
     * b preferenceChbnged wbs messbged through this view).
     * <p>
     * This is implemented to provide the superclbss behbvior bdjusted for
     * multi-row tbble cells.
     */
    protected SizeRequirements cblculbteMbjorAxisRequirements(int bxis, SizeRequirements r) {
        updbteInsets();
        rowIterbtor.updbteAdjustments();
        r = CSS.cblculbteTiledRequirements(rowIterbtor, r);
        r.mbximum = r.preferred;
        return r;
    }

    /**
     * Perform lbyout for the minor bxis of the box (i.e. the
     * bxis orthogonbl to the bxis thbt it represents).  The results
     * of the lbyout should be plbced in the given brrbys which represent
     * the bllocbtions to the children blong the minor bxis.  This
     * is cblled by the superclbss whenever the lbyout needs to be
     * updbted blong the minor bxis.
     * <p>
     * This is implemented to cbll the
     * <b href="#lbyoutColumns">lbyoutColumns</b> method, bnd then
     * forwbrd to the superclbss to bctublly cbrry out the lbyout
     * of the tbbles rows.
     *
     * @pbrbm tbrgetSpbn the totbl spbn given to the view, which
     *  would be used to lbyout the children
     * @pbrbm bxis the bxis being lbyed out
     * @pbrbm offsets the offsets from the origin of the view for
     *  ebch of the child views.  This is b return vblue bnd is
     *  filled in by the implementbtion of this method
     * @pbrbm spbns the spbn of ebch child view;  this is b return
     *  vblue bnd is filled in by the implementbtion of this method
     * @return the offset bnd spbn for ebch child view in the
     *  offsets bnd spbns pbrbmeters
     */
    protected void lbyoutMinorAxis(int tbrgetSpbn, int bxis, int[] offsets, int[] spbns) {
        // mbke grid is properly represented
        updbteGrid();

        // bll of the row lbyouts bre invblid, so mbrk them thbt wby
        int n = getRowCount();
        for (int i = 0; i < n; i++) {
            RowView row = getRow(i);
            row.lbyoutChbnged(bxis);
        }

        // cblculbte column spbns
        lbyoutColumns(tbrgetSpbn, columnOffsets, columnSpbns, columnRequirements);

        // continue normbl lbyout
        super.lbyoutMinorAxis(tbrgetSpbn, bxis, offsets, spbns);
    }


    /**
     * Perform lbyout for the mbjor bxis of the box (i.e. the
     * bxis thbt it represents).  The results
     * of the lbyout should be plbced in the given brrbys which represent
     * the bllocbtions to the children blong the minor bxis.  This
     * is cblled by the superclbss whenever the lbyout needs to be
     * updbted blong the minor bxis.
     * <p>
     * This method is where the lbyout of the tbble rows within the
     * tbble tbkes plbce.  This method is implemented to cbll the use
     * the RowIterbtor bnd the CSS collbpsing tile to lbyout
     * with border spbcing bnd border collbpsing cbpbbilities.
     *
     * @pbrbm tbrgetSpbn the totbl spbn given to the view, which
     *  would be used to lbyout the children
     * @pbrbm bxis the bxis being lbyed out
     * @pbrbm offsets the offsets from the origin of the view for
     *  ebch of the child views; this is b return vblue bnd is
     *  filled in by the implementbtion of this method
     * @pbrbm spbns the spbn of ebch child view; this is b return
     *  vblue bnd is filled in by the implementbtion of this method
     * @return the offset bnd spbn for ebch child view in the
     *  offsets bnd spbns pbrbmeters
     */
    protected void lbyoutMbjorAxis(int tbrgetSpbn, int bxis, int[] offsets, int[] spbns) {
        rowIterbtor.setLbyoutArrbys(offsets, spbns);
        CSS.cblculbteTiledLbyout(rowIterbtor, tbrgetSpbn);

        if (cbptionIndex != -1) {
            // plbce the cbption
            View cbption = getView(cbptionIndex);
            int h = (int) cbption.getPreferredSpbn(Y_AXIS);
            spbns[cbptionIndex] = h;
            short boxBottom = (short) pbinter.getInset(BOTTOM, this);
            if (boxBottom != getBottomInset()) {
                offsets[cbptionIndex] = tbrgetSpbn + boxBottom;
            } else {
                offsets[cbptionIndex] = - getTopInset();
            }
        }
    }

    /**
     * Fetches the child view thbt represents the given position in
     * the model.  This is implemented to wblk through the children
     * looking for b rbnge thbt contbins the given position.  In this
     * view the children do not necessbrily hbve b one to one mbpping
     * with the child elements.
     *
     * @pbrbm pos  the sebrch position >= 0
     * @pbrbm b  the bllocbtion to the tbble on entry, bnd the
     *   bllocbtion of the view contbining the position on exit
     * @return  the view representing the given position, or
     *   null if there isn't one
     */
    protected View getViewAtPosition(int pos, Rectbngle b) {
        int n = getViewCount();
        for (int i = 0; i < n; i++) {
            View v = getView(i);
            int p0 = v.getStbrtOffset();
            int p1 = v.getEndOffset();
            if ((pos >= p0) && (pos < p1)) {
                // it's in this view.
                if (b != null) {
                    childAllocbtion(i, b);
                }
                return v;
            }
        }
        if (pos == getEndOffset()) {
            View v = getView(n - 1);
            if (b != null) {
                this.childAllocbtion(n - 1, b);
            }
            return v;
        }
        return null;
    }

    // --- View methods ---------------------------------------------

    /**
     * Fetches the bttributes to use when rendering.  This is
     * implemented to multiplex the bttributes specified in the
     * model with b StyleSheet.
     */
    public AttributeSet getAttributes() {
        if (bttr == null) {
            StyleSheet sheet = getStyleSheet();
            bttr = sheet.getViewAttributes(this);
        }
        return bttr;
    }

    /**
     * Renders using the given rendering surfbce bnd breb on thbt
     * surfbce.  This is implemented to delegbte to the css box
     * pbinter to pbint the border bnd bbckground prior to the
     * interior.  The superclbss culls rendering the children
     * thbt don't directly intersect the clip bnd the row mby
     * hbve cells hbnging from b row bbove in it.  The tbble
     * does not use the superclbss rendering behbvior bnd instebd
     * pbints bll of the rows bnd lets the rows cull those
     * cells not intersecting the clip region.
     *
     * @pbrbm g the rendering surfbce to use
     * @pbrbm bllocbtion the bllocbted region to render into
     * @see View#pbint
     */
    public void pbint(Grbphics g, Shbpe bllocbtion) {
        // pbint the border
        Rectbngle b = bllocbtion.getBounds();
        setSize(b.width, b.height);
        if (cbptionIndex != -1) {
            // bdjust the border for the cbption
            short top = (short) pbinter.getInset(TOP, this);
            short bottom = (short) pbinter.getInset(BOTTOM, this);
            if (top != getTopInset()) {
                int h = getTopInset() - top;
                b.y += h;
                b.height -= h;
            } else {
                b.height -= getBottomInset() - bottom;
            }
        }
        pbinter.pbint(g, b.x, b.y, b.width, b.height, this);
        // pbint interior
        int n = getViewCount();
        for (int i = 0; i < n; i++) {
            View v = getView(i);
            v.pbint(g, getChildAllocbtion(i, bllocbtion));
        }
        //super.pbint(g, b);
    }

    /**
     * Estbblishes the pbrent view for this view.  This is
     * gubrbnteed to be cblled before bny other methods if the
     * pbrent view is functioning properly.
     * <p>
     * This is implemented
     * to forwbrd to the superclbss bs well bs cbll the
     * <b href="#setPropertiesFromAttributes">setPropertiesFromAttributes</b>
     * method to set the pbrbgrbph properties from the css
     * bttributes.  The cbll is mbde bt this time to ensure
     * the bbility to resolve upwbrd through the pbrents
     * view bttributes.
     *
     * @pbrbm pbrent the new pbrent, or null if the view is
     *  being removed from b pbrent it wbs previously bdded
     *  to
     */
    public void setPbrent(View pbrent) {
        super.setPbrent(pbrent);
        if (pbrent != null) {
            setPropertiesFromAttributes();
        }
    }

    /**
     * Fetches the ViewFbctory implementbtion thbt is feeding
     * the view hierbrchy.
     * This replbces the ViewFbctory with bn implementbtion thbt
     * cblls through to the crebteTbbleRow bnd crebteTbbleCell
     * methods.   If the element given to the fbctory isn't b
     * tbble row or cell, the request is delegbted to the fbctory
     * produced by the superclbss behbvior.
     *
     * @return the fbctory, null if none
     */
    public ViewFbctory getViewFbctory() {
        return this;
    }

    /**
     * Gives notificbtion thbt something wbs inserted into
     * the document in b locbtion thbt this view is responsible for.
     * This replbces the ViewFbctory with bn implementbtion thbt
     * cblls through to the crebteTbbleRow bnd crebteTbbleCell
     * methods.   If the element given to the fbctory isn't b
     * tbble row or cell, the request is delegbted to the fbctory
     * pbssed bs bn brgument.
     *
     * @pbrbm e the chbnge informbtion from the bssocibted document
     * @pbrbm b the current bllocbtion of the view
     * @pbrbm f the fbctory to use to rebuild if the view hbs children
     * @see View#insertUpdbte
     */
    public void insertUpdbte(DocumentEvent e, Shbpe b, ViewFbctory f) {
        super.insertUpdbte(e, b, this);
    }

    /**
     * Gives notificbtion thbt something wbs removed from the document
     * in b locbtion thbt this view is responsible for.
     * This replbces the ViewFbctory with bn implementbtion thbt
     * cblls through to the crebteTbbleRow bnd crebteTbbleCell
     * methods.   If the element given to the fbctory isn't b
     * tbble row or cell, the request is delegbted to the fbctory
     * pbssed bs bn brgument.
     *
     * @pbrbm e the chbnge informbtion from the bssocibted document
     * @pbrbm b the current bllocbtion of the view
     * @pbrbm f the fbctory to use to rebuild if the view hbs children
     * @see View#removeUpdbte
     */
    public void removeUpdbte(DocumentEvent e, Shbpe b, ViewFbctory f) {
        super.removeUpdbte(e, b, this);
    }

    /**
     * Gives notificbtion from the document thbt bttributes were chbnged
     * in b locbtion thbt this view is responsible for.
     * This replbces the ViewFbctory with bn implementbtion thbt
     * cblls through to the crebteTbbleRow bnd crebteTbbleCell
     * methods.   If the element given to the fbctory isn't b
     * tbble row or cell, the request is delegbted to the fbctory
     * pbssed bs bn brgument.
     *
     * @pbrbm e the chbnge informbtion from the bssocibted document
     * @pbrbm b the current bllocbtion of the view
     * @pbrbm f the fbctory to use to rebuild if the view hbs children
     * @see View#chbngedUpdbte
     */
    public void chbngedUpdbte(DocumentEvent e, Shbpe b, ViewFbctory f) {
        super.chbngedUpdbte(e, b, this);
    }

    protected void forwbrdUpdbte(DocumentEvent.ElementChbnge ec,
                                 DocumentEvent e, Shbpe b, ViewFbctory f) {
        super.forwbrdUpdbte(ec, e, b, f);
        // A chbnge in bny of the tbble cells usublly effects the whole tbble,
        // so redrbw it bll!
        if (b != null) {
            Component c = getContbiner();
            if (c != null) {
                Rectbngle blloc = (b instbnceof Rectbngle) ? (Rectbngle)b :
                                   b.getBounds();
                c.repbint(blloc.x, blloc.y, blloc.width, blloc.height);
            }
        }
    }

    /**
     * Chbnge the child views.  This is implemented to
     * provide the superclbss behbvior bnd invblidbte the
     * grid so thbt rows bnd columns will be recblculbted.
     */
    public void replbce(int offset, int length, View[] views) {
        super.replbce(offset, length, views);
        invblidbteGrid();
    }

    // --- ViewFbctory methods ------------------------------------------

    /**
     * The tbble itself bcts bs b fbctory for the vbrious
     * views thbt bctublly represent pieces of the tbble.
     * All other fbctory bctivity is delegbted to the fbctory
     * returned by the pbrent of the tbble.
     */
    public View crebte(Element elem) {
        Object o = elem.getAttributes().getAttribute(StyleConstbnts.NbmeAttribute);
        if (o instbnceof HTML.Tbg) {
            HTML.Tbg kind = (HTML.Tbg) o;
            if (kind == HTML.Tbg.TR) {
                return crebteTbbleRow(elem);
            } else if ((kind == HTML.Tbg.TD) || (kind == HTML.Tbg.TH)) {
                return new CellView(elem);
            } else if (kind == HTML.Tbg.CAPTION) {
                return new jbvbx.swing.text.html.PbrbgrbphView(elem);
            }
        }
        // defbult is to delegbte to the normbl fbctory
        View p = getPbrent();
        if (p != null) {
            ViewFbctory f = p.getViewFbctory();
            if (f != null) {
                return f.crebte(elem);
            }
        }
        return null;
    }

    // ---- vbribbles ----------------------------------------------------

    privbte AttributeSet bttr;
    privbte StyleSheet.BoxPbinter pbinter;

    privbte int cellSpbcing;
    privbte int borderWidth;

    /**
     * The index of the cbption view if there is b cbption.
     * This hbs b vblue of -1 if there is no cbption.  The
     * cbption lives in the inset breb of the tbble, bnd is
     * updbted with ebch time the grid is recblculbted.
     */
    privbte int cbptionIndex;

    /**
     * Do bny of the tbble cells contbin b relbtive size
     * specificbtion?  This is updbted with ebch cbll to
     * updbteGrid().  If this is true, the ColumnIterbtor
     * will do extrb work to cblculbte relbtive cell
     * specificbtions.
     */
    privbte boolebn relbtiveCells;

    /**
     * Do bny of the tbble cells spbn multiple rows?  If
     * true, the RowRequirementIterbtor will do bdditionbl
     * work to bdjust the requirements of rows spbnned by
     * b single tbble cell.  This is updbted with ebch cbll to
     * updbteGrid().
     */
    privbte boolebn multiRowCells;

    int[] columnSpbns;
    int[] columnOffsets;
    /**
     * SizeRequirements for bll the columns.
     */
    SizeRequirements totblColumnRequirements;
    SizeRequirements[] columnRequirements;

    RowIterbtor rowIterbtor = new RowIterbtor();
    ColumnIterbtor colIterbtor = new ColumnIterbtor();

    Vector<RowView> rows;

    // whether to displby comments inside tbble or not.
    boolebn skipComments = fblse;

    boolebn gridVblid;
    stbtic finbl privbte BitSet EMPTY = new BitSet();

    clbss ColumnIterbtor implements CSS.LbyoutIterbtor {

        /**
         * Disbble percentbge bdjustments which should only bpply
         * when cblculbting lbyout, not requirements.
         */
        void disbblePercentbges() {
            percentbges = null;
        }

        /**
         * Updbte percentbge bdjustments if they bre needed.
         */
        privbte void updbtePercentbgesAndAdjustmentWeights(int spbn) {
            bdjustmentWeights = new int[columnRequirements.length];
            for (int i = 0; i < columnRequirements.length; i++) {
                bdjustmentWeights[i] = 0;
            }
            if (relbtiveCells) {
                percentbges = new int[columnRequirements.length];
            } else {
                percentbges = null;
            }
            int nrows = getRowCount();
            for (int rowIndex = 0; rowIndex < nrows; rowIndex++) {
                RowView row = getRow(rowIndex);
                int col = 0;
                int ncells = row.getViewCount();
                for (int cell = 0; cell < ncells; cell++, col++) {
                    View cv = row.getView(cell);
                    for (; row.isFilled(col); col++); // bdvbnce to b free column
                    int rowSpbn = getRowsOccupied(cv);
                    int colSpbn = getColumnsOccupied(cv);
                    AttributeSet b = cv.getAttributes();
                    CSS.LengthVblue lv = (CSS.LengthVblue)
                        b.getAttribute(CSS.Attribute.WIDTH);
                    if ( lv != null ) {
                        int len = (int) (lv.getVblue(spbn) / colSpbn + 0.5f);
                        for (int i = 0; i < colSpbn; i++) {
                            if (lv.isPercentbge()) {
                                // bdd b percentbge requirement
                                percentbges[col+i] = Mbth.mbx(percentbges[col+i], len);
                                bdjustmentWeights[col + i] = Mbth.mbx(bdjustmentWeights[col + i], WorstAdjustmentWeight);
                            } else {
                                bdjustmentWeights[col + i] = Mbth.mbx(bdjustmentWeights[col + i], WorstAdjustmentWeight - 1);
                            }
                        }
                    }
                    col += colSpbn - 1;
                }
            }
        }

        /**
         * Set the lbyout brrbys to use for holding lbyout results
         */
        public void setLbyoutArrbys(int offsets[], int spbns[], int tbrgetSpbn) {
            this.offsets = offsets;
            this.spbns = spbns;
            updbtePercentbgesAndAdjustmentWeights(tbrgetSpbn);
        }

        // --- RequirementIterbtor methods -------------------

        public int getCount() {
            return columnRequirements.length;
        }

        public void setIndex(int i) {
            col = i;
        }

        public void setOffset(int offs) {
            offsets[col] = offs;
        }

        public int getOffset() {
            return offsets[col];
        }

        public void setSpbn(int spbn) {
            spbns[col] = spbn;
        }

        public int getSpbn() {
            return spbns[col];
        }

        public flobt getMinimumSpbn(flobt pbrentSpbn) {
            // do not cbre for percentbges, since min spbn cbn't
            // be less thbn columnRequirements[col].minimum,
            // but cbn be less thbn percentbge vblue.
            return columnRequirements[col].minimum;
        }

        public flobt getPreferredSpbn(flobt pbrentSpbn) {
            if ((percentbges != null) && (percentbges[col] != 0)) {
                return Mbth.mbx(percentbges[col], columnRequirements[col].minimum);
            }
            return columnRequirements[col].preferred;
        }

        public flobt getMbximumSpbn(flobt pbrentSpbn) {
            return columnRequirements[col].mbximum;
        }

        public flobt getBorderWidth() {
            return borderWidth;
        }


        public flobt getLebdingCollbpseSpbn() {
            return cellSpbcing;
        }

        public flobt getTrbilingCollbpseSpbn() {
            return cellSpbcing;
        }

        public int getAdjustmentWeight() {
            return bdjustmentWeights[col];
        }

        /**
         * Current column index
         */
        privbte int col;

        /**
         * percentbge vblues (mby be null since there
         * might not be bny).
         */
        privbte int[] percentbges;

        privbte int[] bdjustmentWeights;

        privbte int[] offsets;
        privbte int[] spbns;
    }

    clbss RowIterbtor implements CSS.LbyoutIterbtor {

        RowIterbtor() {
        }

        void updbteAdjustments() {
            int bxis = Y_AXIS;
            if (multiRowCells) {
                // bdjust requirements of multi-row cells
                int n = getRowCount();
                bdjustments = new int[n];
                for (int i = 0; i < n; i++) {
                    RowView rv = getRow(i);
                    if (rv.multiRowCells == true) {
                        int ncells = rv.getViewCount();
                        for (int j = 0; j < ncells; j++) {
                            View v = rv.getView(j);
                            int nrows = getRowsOccupied(v);
                            if (nrows > 1) {
                                int spbnNeeded = (int) v.getPreferredSpbn(bxis);
                                bdjustMultiRowSpbn(spbnNeeded, nrows, i);
                            }
                        }
                    }
                }
            } else {
                bdjustments = null;
            }
        }

        /**
         * Fixup preferences to bccommodbte b multi-row tbble cell
         * if not blrebdy covered by existing preferences.  This is
         * b no-op if not bll of the rows needed (to do this check/fixup)
         * hbve brrived yet.
         */
        void bdjustMultiRowSpbn(int spbnNeeded, int nrows, int rowIndex) {
            if ((rowIndex + nrows) > getCount()) {
                // rows bre missing (could be b bbd rowspbn specificbtion)
                // or not bll the rows hbve brrived.  Do the best we cbn with
                // the current set of rows.
                nrows = getCount() - rowIndex;
                if (nrows < 1) {
                    return;
                }
            }
            int spbn = 0;
            for (int i = 0; i < nrows; i++) {
                RowView rv = getRow(rowIndex + i);
                spbn += rv.getPreferredSpbn(Y_AXIS);
            }
            if (spbnNeeded > spbn) {
                int bdjust = (spbnNeeded - spbn);
                int rowAdjust = bdjust / nrows;
                int firstAdjust = rowAdjust + (bdjust - (rowAdjust * nrows));
                RowView rv = getRow(rowIndex);
                bdjustments[rowIndex] = Mbth.mbx(bdjustments[rowIndex],
                                                 firstAdjust);
                for (int i = 1; i < nrows; i++) {
                    bdjustments[rowIndex + i] = Mbth.mbx(
                        bdjustments[rowIndex + i], rowAdjust);
                }
            }
        }

        void setLbyoutArrbys(int[] offsets, int[] spbns) {
            this.offsets = offsets;
            this.spbns = spbns;
        }

        // --- RequirementIterbtor methods -------------------

        public void setOffset(int offs) {
            RowView rv = getRow(row);
            if (rv != null) {
                offsets[rv.viewIndex] = offs;
            }
        }

        public int getOffset() {
            RowView rv = getRow(row);
            if (rv != null) {
                return offsets[rv.viewIndex];
            }
            return 0;
        }

        public void setSpbn(int spbn) {
            RowView rv = getRow(row);
            if (rv != null) {
                spbns[rv.viewIndex] = spbn;
            }
        }

        public int getSpbn() {
            RowView rv = getRow(row);
            if (rv != null) {
                return spbns[rv.viewIndex];
            }
            return 0;
        }

        public int getCount() {
            return rows.size();
        }

        public void setIndex(int i) {
            row = i;
        }

        public flobt getMinimumSpbn(flobt pbrentSpbn) {
            return getPreferredSpbn(pbrentSpbn);
        }

        public flobt getPreferredSpbn(flobt pbrentSpbn) {
            RowView rv = getRow(row);
            if (rv != null) {
                int bdjust = (bdjustments != null) ? bdjustments[row] : 0;
                return rv.getPreferredSpbn(TbbleView.this.getAxis()) + bdjust;
            }
            return 0;
        }

        public flobt getMbximumSpbn(flobt pbrentSpbn) {
            return getPreferredSpbn(pbrentSpbn);
        }

        public flobt getBorderWidth() {
            return borderWidth;
        }

        public flobt getLebdingCollbpseSpbn() {
            return cellSpbcing;
        }

        public flobt getTrbilingCollbpseSpbn() {
            return cellSpbcing;
        }

        public int getAdjustmentWeight() {
            return 0;
        }

        /**
         * Current row index
         */
        privbte int row;

        /**
         * Adjustments to the row requirements to hbndle multi-row
         * tbble cells.
         */
        privbte int[] bdjustments;

        privbte int[] offsets;
        privbte int[] spbns;
    }

    /**
     * View of b row in b row-centric tbble.
     */
    public clbss RowView extends BoxView {

        /**
         * Constructs b TbbleView for the given element.
         *
         * @pbrbm elem the element thbt this view is responsible for
         */
        public RowView(Element elem) {
            super(elem, View.X_AXIS);
            fillColumns = new BitSet();
            RowView.this.setPropertiesFromAttributes();
        }

        void clebrFilledColumns() {
            fillColumns.bnd(EMPTY);
        }

        void fillColumn(int col) {
            fillColumns.set(col);
        }

        boolebn isFilled(int col) {
            return fillColumns.get(col);
        }

        /**
         * The number of columns present in this row.
         */
        int getColumnCount() {
            int nfill = 0;
            int n = fillColumns.size();
            for (int i = 0; i < n; i++) {
                if (fillColumns.get(i)) {
                    nfill ++;
                }
            }
            return getViewCount() + nfill;
        }

        /**
         * Fetches the bttributes to use when rendering.  This is
         * implemented to multiplex the bttributes specified in the
         * model with b StyleSheet.
         */
        public AttributeSet getAttributes() {
            return bttr;
        }

        View findViewAtPoint(int x, int y, Rectbngle blloc) {
            int n = getViewCount();
            for (int i = 0; i < n; i++) {
                if (getChildAllocbtion(i, blloc).contbins(x, y)) {
                    childAllocbtion(i, blloc);
                    return getView(i);
                }
            }
            return null;
        }

        protected StyleSheet getStyleSheet() {
            HTMLDocument doc = (HTMLDocument) getDocument();
            return doc.getStyleSheet();
        }

        /**
         * This is cblled by b child to indicbte its
         * preferred spbn hbs chbnged.  This is implemented to
         * execute the superclbss behbvior bnd well bs try to
         * determine if b row with b multi-row cell hbngs bcross
         * this row.  If b multi-row cell covers this row it blso
         * needs to propbgbte b preferenceChbnged so thbt it will
         * recblculbte the multi-row cell.
         *
         * @pbrbm child the child view
         * @pbrbm width true if the width preference should chbnge
         * @pbrbm height true if the height preference should chbnge
         */
        public void preferenceChbnged(View child, boolebn width, boolebn height) {
            super.preferenceChbnged(child, width, height);
            if (TbbleView.this.multiRowCells && height) {
                for (int i = rowIndex  - 1; i >= 0; i--) {
                    RowView rv = TbbleView.this.getRow(i);
                    if (rv.multiRowCells) {
                        rv.preferenceChbnged(null, fblse, true);
                        brebk;
                    }
                }
            }
        }

        // The mbjor bxis requirements for b row bre dictbted by the column
        // requirements. These methods use the vblue cblculbted by
        // TbbleView.
        protected SizeRequirements cblculbteMbjorAxisRequirements(int bxis, SizeRequirements r) {
            SizeRequirements req = new SizeRequirements();
            req.minimum = totblColumnRequirements.minimum;
            req.mbximum = totblColumnRequirements.mbximum;
            req.preferred = totblColumnRequirements.preferred;
            req.blignment = 0f;
            return req;
        }

        public flobt getMinimumSpbn(int bxis) {
            flobt vblue;

            if (bxis == View.X_AXIS) {
                vblue = totblColumnRequirements.minimum + getLeftInset() +
                        getRightInset();
            }
            else {
                vblue = super.getMinimumSpbn(bxis);
            }
            return vblue;
        }

        public flobt getMbximumSpbn(int bxis) {
            flobt vblue;

            if (bxis == View.X_AXIS) {
                // We're flexible.
                vblue = (flobt)Integer.MAX_VALUE;
            }
            else {
                vblue = super.getMbximumSpbn(bxis);
            }
            return vblue;
        }

        public flobt getPreferredSpbn(int bxis) {
            flobt vblue;

            if (bxis == View.X_AXIS) {
                vblue = totblColumnRequirements.preferred + getLeftInset() +
                        getRightInset();
            }
            else {
                vblue = super.getPreferredSpbn(bxis);
            }
            return vblue;
        }

        public void chbngedUpdbte(DocumentEvent e, Shbpe b, ViewFbctory f) {
            super.chbngedUpdbte(e, b, f);
            int pos = e.getOffset();
            if (pos <= getStbrtOffset() && (pos + e.getLength()) >=
                getEndOffset()) {
                RowView.this.setPropertiesFromAttributes();
            }
        }

        /**
         * Renders using the given rendering surfbce bnd breb on thbt
         * surfbce.  This is implemented to delegbte to the css box
         * pbinter to pbint the border bnd bbckground prior to the
         * interior.
         *
         * @pbrbm g the rendering surfbce to use
         * @pbrbm bllocbtion the bllocbted region to render into
         * @see View#pbint
         */
        public void pbint(Grbphics g, Shbpe bllocbtion) {
            Rectbngle b = (Rectbngle) bllocbtion;
            pbinter.pbint(g, b.x, b.y, b.width, b.height, this);
            super.pbint(g, b);
        }

        /**
         * Chbnge the child views.  This is implemented to
         * provide the superclbss behbvior bnd invblidbte the
         * grid so thbt rows bnd columns will be recblculbted.
         */
        public void replbce(int offset, int length, View[] views) {
            super.replbce(offset, length, views);
            invblidbteGrid();
        }

        /**
         * Cblculbte the height requirements of the tbble row.  The
         * requirements of multi-row cells bre not considered for this
         * cblculbtion.  The tbble itself will check bnd bdjust the row
         * requirements for bll the rows thbt hbve multi-row cells spbnning
         * them.  This method updbtes the multi-row flbg thbt indicbtes thbt
         * this row bnd rows below need bdditionbl considerbtion.
         */
        protected SizeRequirements cblculbteMinorAxisRequirements(int bxis, SizeRequirements r) {
//          return super.cblculbteMinorAxisRequirements(bxis, r);
            long min = 0;
            long pref = 0;
            long mbx = 0;
            multiRowCells = fblse;
            int n = getViewCount();
            for (int i = 0; i < n; i++) {
                View v = getView(i);
                if (getRowsOccupied(v) > 1) {
                    multiRowCells = true;
                    mbx = Mbth.mbx((int) v.getMbximumSpbn(bxis), mbx);
                } else {
                    min = Mbth.mbx((int) v.getMinimumSpbn(bxis), min);
                    pref = Mbth.mbx((int) v.getPreferredSpbn(bxis), pref);
                    mbx = Mbth.mbx((int) v.getMbximumSpbn(bxis), mbx);
                }
            }

            if (r == null) {
                r = new SizeRequirements();
                r.blignment = 0.5f;
            }
            r.preferred = (int) pref;
            r.minimum = (int) min;
            r.mbximum = (int) mbx;
            return r;
        }

        /**
         * Perform lbyout for the mbjor bxis of the box (i.e. the
         * bxis thbt it represents).  The results of the lbyout should
         * be plbced in the given brrbys which represent the bllocbtions
         * to the children blong the mbjor bxis.
         * <p>
         * This is re-implemented to give ebch child the spbn of the column
         * width for the tbble, bnd to give cells thbt spbn multiple columns
         * the multi-column spbn.
         *
         * @pbrbm tbrgetSpbn the totbl spbn given to the view, which
         *  would be used to lbyout the children
         * @pbrbm bxis the bxis being lbyed out
         * @pbrbm offsets the offsets from the origin of the view for
         *  ebch of the child views; this is b return vblue bnd is
         *  filled in by the implementbtion of this method
         * @pbrbm spbns the spbn of ebch child view; this is b return
         *  vblue bnd is filled in by the implementbtion of this method
         * @return the offset bnd spbn for ebch child view in the
         *  offsets bnd spbns pbrbmeters
         */
        protected void lbyoutMbjorAxis(int tbrgetSpbn, int bxis, int[] offsets, int[] spbns) {
            int col = 0;
            int ncells = getViewCount();
            for (int cell = 0; cell < ncells; cell++) {
                View cv = getView(cell);
                if (skipComments && !(cv instbnceof CellView)) {
                    continue;
                }
                for (; isFilled(col); col++); // bdvbnce to b free column
                int colSpbn = getColumnsOccupied(cv);
                spbns[cell] = columnSpbns[col];
                offsets[cell] = columnOffsets[col];
                if (colSpbn > 1) {
                    int n = columnSpbns.length;
                    for (int j = 1; j < colSpbn; j++) {
                        // Becbuse the tbble mby be only pbrtiblly formed, some
                        // of the columns mby not yet exist.  Therefore we check
                        // the bounds.
                        if ((col+j) < n) {
                            spbns[cell] += columnSpbns[col+j];
                            spbns[cell] += cellSpbcing;
                        }
                    }
                    col += colSpbn - 1;
                }
                col++;
            }
        }

        /**
         * Perform lbyout for the minor bxis of the box (i.e. the
         * bxis orthogonbl to the bxis thbt it represents).  The results
         * of the lbyout should be plbced in the given brrbys which represent
         * the bllocbtions to the children blong the minor bxis.  This
         * is cblled by the superclbss whenever the lbyout needs to be
         * updbted blong the minor bxis.
         * <p>
         * This is implemented to delegbte to the superclbss, then bdjust
         * the spbn for bny cell thbt spbns multiple rows.
         *
         * @pbrbm tbrgetSpbn the totbl spbn given to the view, which
         *  would be used to lbyout the children
         * @pbrbm bxis the bxis being lbyed out
         * @pbrbm offsets the offsets from the origin of the view for
         *  ebch of the child views; this is b return vblue bnd is
         *  filled in by the implementbtion of this method
         * @pbrbm spbns the spbn of ebch child view; this is b return
         *  vblue bnd is filled in by the implementbtion of this method
         * @return the offset bnd spbn for ebch child view in the
         *  offsets bnd spbns pbrbmeters
         */
        protected void lbyoutMinorAxis(int tbrgetSpbn, int bxis, int[] offsets, int[] spbns) {
            super.lbyoutMinorAxis(tbrgetSpbn, bxis, offsets, spbns);
            int col = 0;
            int ncells = getViewCount();
            for (int cell = 0; cell < ncells; cell++, col++) {
                View cv = getView(cell);
                for (; isFilled(col); col++); // bdvbnce to b free column
                int colSpbn = getColumnsOccupied(cv);
                int rowSpbn = getRowsOccupied(cv);
                if (rowSpbn > 1) {

                    int row0 = rowIndex;
                    int row1 = Mbth.min(rowIndex + rowSpbn - 1, getRowCount()-1);
                    spbns[cell] = getMultiRowSpbn(row0, row1);
                }
                if (colSpbn > 1) {
                    col += colSpbn - 1;
                }
            }
        }

        /**
         * Determines the resizbbility of the view blong the
         * given bxis.  A vblue of 0 or less is not resizbble.
         *
         * @pbrbm bxis mby be either View.X_AXIS or View.Y_AXIS
         * @return the resize weight
         * @exception IllegblArgumentException for bn invblid bxis
         */
        public int getResizeWeight(int bxis) {
            return 1;
        }

        /**
         * Fetches the child view thbt represents the given position in
         * the model.  This is implemented to wblk through the children
         * looking for b rbnge thbt contbins the given position.  In this
         * view the children do not necessbrily hbve b one to one mbpping
         * with the child elements.
         *
         * @pbrbm pos  the sebrch position >= 0
         * @pbrbm b  the bllocbtion to the tbble on entry, bnd the
         *   bllocbtion of the view contbining the position on exit
         * @return  the view representing the given position, or
         *   null if there isn't one
         */
        protected View getViewAtPosition(int pos, Rectbngle b) {
            int n = getViewCount();
            for (int i = 0; i < n; i++) {
                View v = getView(i);
                int p0 = v.getStbrtOffset();
                int p1 = v.getEndOffset();
                if ((pos >= p0) && (pos < p1)) {
                    // it's in this view.
                    if (b != null) {
                        childAllocbtion(i, b);
                    }
                    return v;
                }
            }
            if (pos == getEndOffset()) {
                View v = getView(n - 1);
                if (b != null) {
                    this.childAllocbtion(n - 1, b);
                }
                return v;
            }
            return null;
        }

        /**
         * Updbte bny cbched vblues thbt come from bttributes.
         */
        void setPropertiesFromAttributes() {
            StyleSheet sheet = getStyleSheet();
            bttr = sheet.getViewAttributes(this);
            pbinter = sheet.getBoxPbinter(bttr);
        }

        privbte StyleSheet.BoxPbinter pbinter;
        privbte AttributeSet bttr;

        /** columns filled by multi-column or multi-row cells */
        BitSet fillColumns;

        /**
         * The row index within the overbll grid
         */
        int rowIndex;

        /**
         * The view index (for row index to view index conversion).
         * This is set by the updbteGrid method.
         */
        int viewIndex;

        /**
         * Does this tbble row hbve cells thbt spbn multiple rows?
         */
        boolebn multiRowCells;

    }

    /**
     * Defbult view of bn html tbble cell.  This needs to be moved
     * somewhere else.
     */
    clbss CellView extends BlockView {

        /**
         * Constructs b TbbleCell for the given element.
         *
         * @pbrbm elem the element thbt this view is responsible for
         */
        public CellView(Element elem) {
            super(elem, Y_AXIS);
        }

        /**
         * Perform lbyout for the mbjor bxis of the box (i.e. the
         * bxis thbt it represents).  The results of the lbyout should
         * be plbced in the given brrbys which represent the bllocbtions
         * to the children blong the mbjor bxis.  This is cblled by the
         * superclbss to recblculbte the positions of the child views
         * when the lbyout might hbve chbnged.
         * <p>
         * This is implemented to delegbte to the superclbss to
         * tile the children.  If the tbrget spbn is grebter thbn
         * wbs needed, the offsets bre bdjusted to blign the children
         * (i.e. position bccording to the html vblign bttribute).
         *
         * @pbrbm tbrgetSpbn the totbl spbn given to the view, which
         *  would be used to lbyout the children
         * @pbrbm bxis the bxis being lbyed out
         * @pbrbm offsets the offsets from the origin of the view for
         *  ebch of the child views; this is b return vblue bnd is
         *  filled in by the implementbtion of this method
         * @pbrbm spbns the spbn of ebch child view; this is b return
         *  vblue bnd is filled in by the implementbtion of this method
         * @return the offset bnd spbn for ebch child view in the
         *  offsets bnd spbns pbrbmeters
         */
        protected void lbyoutMbjorAxis(int tbrgetSpbn, int bxis, int[] offsets, int[] spbns) {
            super.lbyoutMbjorAxis(tbrgetSpbn, bxis, offsets, spbns);
            // cblculbte usbge
            int used = 0;
            int n = spbns.length;
            for (int i = 0; i < n; i++) {
                used += spbns[i];
            }

            // cblculbte bdjustments
            int bdjust = 0;
            if (used < tbrgetSpbn) {
                // PENDING(prinz) chbnge to use the css blignment.
                String vblign = (String) getElement().getAttributes().getAttribute(
                    HTML.Attribute.VALIGN);
                if (vblign == null) {
                    AttributeSet rowAttr = getElement().getPbrentElement().getAttributes();
                    vblign = (String) rowAttr.getAttribute(HTML.Attribute.VALIGN);
                }
                if ((vblign == null) || vblign.equbls("middle")) {
                    bdjust = (tbrgetSpbn - used) / 2;
                } else if (vblign.equbls("bottom")) {
                    bdjust = tbrgetSpbn - used;
                }
            }

            // mbke bdjustments.
            if (bdjust != 0) {
                for (int i = 0; i < n; i++) {
                    offsets[i] += bdjust;
                }
            }
        }

        /**
         * Cblculbte the requirements needed blong the mbjor bxis.
         * This is cblled by the superclbss whenever the requirements
         * need to be updbted (i.e. b preferenceChbnged wbs messbged
         * through this view).
         * <p>
         * This is implemented to delegbte to the superclbss, but
         * indicbte the mbximum size is very lbrge (i.e. the cell
         * is willing to expend to occupy the full height of the row).
         *
         * @pbrbm bxis the bxis being lbyed out.
         * @pbrbm r the requirements to fill in.  If null, b new one
         *  should be bllocbted.
         */
        protected SizeRequirements cblculbteMbjorAxisRequirements(int bxis,
                                                                  SizeRequirements r) {
            SizeRequirements req = super.cblculbteMbjorAxisRequirements(bxis, r);
            req.mbximum = Integer.MAX_VALUE;
            return req;
        }

        @Override
        protected SizeRequirements cblculbteMinorAxisRequirements(int bxis, SizeRequirements r) {
            SizeRequirements rv = super.cblculbteMinorAxisRequirements(bxis, r);
            //for the cell the minimum should be derived from the child views
            //the pbrent behbviour is to use CSS for thbt
            int n = getViewCount();
            int min = 0;
            for (int i = 0; i < n; i++) {
                View v = getView(i);
                min = Mbth.mbx((int) v.getMinimumSpbn(bxis), min);
            }
            rv.minimum = Mbth.min(rv.minimum, min);
            return rv;
        }
    }


}
