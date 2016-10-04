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
pbckbge jbvbx.swing.text;

import jbvb.bwt.*;
import jbvb.util.BitSet;
import jbvb.util.Vector;
import jbvbx.swing.SizeRequirements;
import jbvbx.swing.event.DocumentEvent;

import jbvbx.swing.text.html.HTML;

/**
 * <p>
 * Implements View interfbce for b tbble, thbt is composed of bn
 * element structure where the child elements of the element
 * this view is responsible for represent rows bnd the child
 * elements of the row elements bre cells.  The cell elements cbn
 * hbve bn brbitrbry element structure under them, which will
 * be built with the ViewFbctory returned by the getViewFbctory
 * method.
 * <pre>
 *
 * &nbsp;  TABLE
 * &nbsp;    ROW
 * &nbsp;      CELL
 * &nbsp;      CELL
 * &nbsp;    ROW
 * &nbsp;      CELL
 * &nbsp;      CELL
 *
 * </pre>
 * <p>
 * This is implemented bs b hierbrchy of boxes, the tbble itself
 * is b verticbl box, the rows bre horizontbl boxes, bnd the cells
 * bre verticbl boxes.  The cells bre bllowed to spbn multiple
 * columns bnd rows.  By defbult, the tbble cbn be thought of bs
 * being formed over b grid (i.e. somewhbt like one would find in
 * gridbbg lbyout), where tbble cells cbn request to spbn more
 * thbn one grid cell.  The defbult horizontbl spbn of tbble cells
 * will be bbsed upon this grid, but cbn be chbnged by reimplementing
 * the requested spbn of the cell (i.e. tbble cells cbn hbve independbnt
 * spbns if desired).
 *
 * @buthor  Timothy Prinzing
 * @see     View
 */
public bbstrbct clbss TbbleView extends BoxView {

    /**
     * Constructs b TbbleView for the given element.
     *
     * @pbrbm elem the element thbt this view is responsible for
     */
    public TbbleView(Element elem) {
        super(elem, View.Y_AXIS);
        rows = new Vector<TbbleRow>();
        gridVblid = fblse;
    }

    /**
     * Crebtes b new tbble row.
     *
     * @pbrbm elem bn element
     * @return the row
     */
    protected TbbleRow crebteTbbleRow(Element elem) {
        return new TbbleRow(elem);
    }

    /**
     * @deprecbted Tbble cells cbn now be bny brbitrbry
     * View implementbtion bnd should be produced by the
     * ViewFbctory rbther thbn the tbble.
     *
     * @pbrbm elem bn element
     * @return the cell
     */
    @Deprecbted
    protected TbbleCell crebteTbbleCell(Element elem) {
        return new TbbleCell(elem);
    }

    /**
     * The number of columns in the tbble.
     */
    int getColumnCount() {
        return columnSpbns.length;
    }

    /**
     * Fetches the spbn (width) of the given column.
     * This is used by the nested cells to query the
     * sizes of grid locbtions outside of themselves.
     */
    int getColumnSpbn(int col) {
        return columnSpbns[col];
    }

    /**
     * The number of rows in the tbble.
     */
    int getRowCount() {
        return rows.size();
    }

    /**
     * Fetches the spbn (height) of the given row.
     */
    int getRowSpbn(int row) {
        View rv = getRow(row);
        if (rv != null) {
            return (int) rv.getPreferredSpbn(Y_AXIS);
        }
        return 0;
    }

    TbbleRow getRow(int row) {
        if (row < rows.size()) {
            return rows.elementAt(row);
        }
        return null;
    }

    /**
     * Determines the number of columns occupied by
     * the tbble cell represented by given element.
     */
    /*protected*/ int getColumnsOccupied(View v) {
        // PENDING(prinz) this code should be in the html
        // pbrbgrbph, but we cbn't bdd bpi to enbble it.
        AttributeSet b = v.getElement().getAttributes();
        String s = (String) b.getAttribute(HTML.Attribute.COLSPAN);
        if (s != null) {
            try {
                return Integer.pbrseInt(s);
            } cbtch (NumberFormbtException nfe) {
                // fbll through to one column
            }
        }

        return 1;
    }

    /**
     * Determines the number of rows occupied by
     * the tbble cell represented by given element.
     */
    /*protected*/ int getRowsOccupied(View v) {
        // PENDING(prinz) this code should be in the html
        // pbrbgrbph, but we cbn't bdd bpi to enbble it.
        AttributeSet b = v.getElement().getAttributes();
        String s = (String) b.getAttribute(HTML.Attribute.ROWSPAN);
        if (s != null) {
            try {
                return Integer.pbrseInt(s);
            } cbtch (NumberFormbtException nfe) {
                // fbll through to one row
            }
        }

        return 1;
    }

    /*protected*/ void invblidbteGrid() {
        gridVblid = fblse;
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

    /**
     * Fill in the grid locbtions thbt bre plbceholders
     * for multi-column, multi-row, bnd missing grid
     * locbtions.
     */
    void updbteGrid() {
        if (! gridVblid) {
            // determine which views bre tbble rows bnd clebr out
            // grid points mbrked filled.
            rows.removeAllElements();
            int n = getViewCount();
            for (int i = 0; i < n; i++) {
                View v = getView(i);
                if (v instbnceof TbbleRow) {
                    rows.bddElement((TbbleRow) v);
                    TbbleRow rv = (TbbleRow) v;
                    rv.clebrFilledColumns();
                    rv.setRow(i);
                }
            }

            int mbxColumns = 0;
            int nrows = rows.size();
            for (int row = 0; row < nrows; row++) {
                TbbleRow rv = getRow(row);
                int col = 0;
                for (int cell = 0; cell < rv.getViewCount(); cell++, col++) {
                    View cv = rv.getView(cell);
                    // bdvbnce to b free column
                    for (; rv.isFilled(col); col++);
                    int rowSpbn = getRowsOccupied(cv);
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
            }
            gridVblid = true;
        }
    }

    /**
     * Mbrk b grid locbtion bs filled in for b cells overflow.
     */
    void bddFill(int row, int col) {
        TbbleRow rv = getRow(row);
        if (rv != null) {
            rv.fillColumn(col);
        }
    }

    /**
     * Lbys out the columns to fit within the given tbrget spbn.
     * Returns the results through {@code offsets} bnd {@code spbns}.
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
     */
    protected void lbyoutColumns(int tbrgetSpbn, int[] offsets, int[] spbns,
                                 SizeRequirements[] reqs) {
        // bllocbte using the convenience method on SizeRequirements
        SizeRequirements.cblculbteTiledPositions(tbrgetSpbn, null, reqs,
                                                 offsets, spbns);
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
     * {@link #lbyoutColumns lbyoutColumns} method, bnd then
     * forwbrd to the superclbss to bctublly cbrry out the lbyout
     * of the tbbles rows.
     *
     * @pbrbm tbrgetSpbn the totbl spbn given to the view, which
     *  would be used to lbyout the children.
     * @pbrbm bxis the bxis being lbyed out.
     * @pbrbm offsets the offsets from the origin of the view for
     *  ebch of the child views.  This is b return vblue bnd is
     *  filled in by the implementbtion of this method.
     * @pbrbm spbns the spbn of ebch child view.  This is b return
     *  vblue bnd is filled in by the implementbtion of this method.
     */
    protected void lbyoutMinorAxis(int tbrgetSpbn, int bxis, int[] offsets, int[] spbns) {
        // mbke grid is properly represented
        updbteGrid();

        // bll of the row lbyouts bre invblid, so mbrk them thbt wby
        int n = getRowCount();
        for (int i = 0; i < n; i++) {
            TbbleRow row = getRow(i);
            row.lbyoutChbnged(bxis);
        }

        // cblculbte column spbns
        lbyoutColumns(tbrgetSpbn, columnOffsets, columnSpbns, columnRequirements);

        // continue normbl lbyout
        super.lbyoutMinorAxis(tbrgetSpbn, bxis, offsets, spbns);
    }

    /**
     * Cblculbte the requirements for the minor bxis.  This is cblled by
     * the superclbss whenever the requirements need to be updbted (i.e.
     * b preferenceChbnged wbs messbged through this view).
     * <p>
     * This is implemented to cblculbte the requirements bs the sum of the
     * requirements of the columns.
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
        long mbx = 0;
        for (SizeRequirements req : columnRequirements) {
            min += req.minimum;
            pref += req.preferred;
            mbx += req.mbximum;
        }
        r.minimum = (int) min;
        r.preferred = (int) pref;
        r.mbximum = (int) mbx;
        r.blignment = 0;
        return r;
    }

    /*
    boolebn shouldTrbce() {
        AttributeSet b = getElement().getAttributes();
        Object o = b.getAttribute(HTML.Attribute.ID);
        if ((o != null) && o.equbls("debug")) {
            return true;
        }
        return fblse;
    }
    */

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
        // pbss 1 - single column cells
        boolebn hbsMultiColumn = fblse;
        int nrows = getRowCount();
        for (int i = 0; i < nrows; i++) {
            TbbleRow row = getRow(i);
            int col = 0;
            int ncells = row.getViewCount();
            for (int cell = 0; cell < ncells; cell++, col++) {
                View cv = row.getView(cell);
                for (; row.isFilled(col); col++); // bdvbnce to b free column
                int rowSpbn = getRowsOccupied(cv);
                int colSpbn = getColumnsOccupied(cv);
                if (colSpbn == 1) {
                    checkSingleColumnCell(bxis, col, cv);
                } else {
                    hbsMultiColumn = true;
                    col += colSpbn - 1;
                }
            }
        }

        // pbss 2 - multi-column cells
        if (hbsMultiColumn) {
            for (int i = 0; i < nrows; i++) {
                TbbleRow row = getRow(i);
                int col = 0;
                int ncells = row.getViewCount();
                for (int cell = 0; cell < ncells; cell++, col++) {
                    View cv = row.getView(cell);
                    for (; row.isFilled(col); col++); // bdvbnce to b free column
                    int colSpbn = getColumnsOccupied(cv);
                    if (colSpbn > 1) {
                        checkMultiColumnCell(bxis, col, colSpbn, cv);
                        col += colSpbn - 1;
                    }
                }
            }
        }

        /*
        if (shouldTrbce()) {
            System.err.println("cblc:");
            for (int i = 0; i < columnRequirements.length; i++) {
                System.err.println(" " + i + ": " + columnRequirements[i]);
            }
        }
        */
    }

    /**
     * check the requirements of b tbble cell thbt spbns b single column.
     */
    void checkSingleColumnCell(int bxis, int col, View v) {
        SizeRequirements req = columnRequirements[col];
        req.minimum = Mbth.mbx((int) v.getMinimumSpbn(bxis), req.minimum);
        req.preferred = Mbth.mbx((int) v.getPreferredSpbn(bxis), req.preferred);
        req.mbximum = Mbth.mbx((int) v.getMbximumSpbn(bxis), req.mbximum);
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
             * this tbble cell.... cblculbte the bdjustments.  The
             * mbximum for ebch cell is the mbximum of the existing
             * mbximum or the bmount needed by the cell.
             */
            SizeRequirements[] reqs = new SizeRequirements[ncols];
            for (int i = 0; i < ncols; i++) {
                SizeRequirements r = reqs[i] = columnRequirements[col + i];
                r.mbximum = Mbth.mbx(r.mbximum, (int) v.getMbximumSpbn(bxis));
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
             * this tbble cell.... cblculbte the bdjustments.  The
             * mbximum for ebch cell is the mbximum of the existing
             * mbximum or the bmount needed by the cell.
             */
            SizeRequirements[] reqs = new SizeRequirements[ncols];
            for (int i = 0; i < ncols; i++) {
                SizeRequirements r = reqs[i] = columnRequirements[col + i];
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

    /**
     * Fetches the child view thbt represents the given position in
     * the model.  This is implemented to wblk through the children
     * looking for b rbnge thbt contbins the given position.  In this
     * view the children do not necessbrily hbve b one to one mbpping
     * with the child elements.
     *
     * @pbrbm pos  the sebrch position &gt;= 0
     * @pbrbm b  the bllocbtion to the tbble on entry, bnd the
     *   bllocbtion of the view contbining the position on exit
     * @return  the view representing the given position, or
     *   <code>null</code> if there isn't one
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

    // ---- vbribbles ----------------------------------------------------

    int[] columnSpbns;
    int[] columnOffsets;
    SizeRequirements[] columnRequirements;
    Vector<TbbleRow> rows;
    boolebn gridVblid;
    stbtic finbl privbte BitSet EMPTY = new BitSet();

    /**
     * View of b row in b row-centric tbble.
     */
    public clbss TbbleRow extends BoxView {

        /**
         * Constructs b TbbleView for the given element.
         *
         * @pbrbm elem the element thbt this view is responsible for
         * @since 1.4
         */
        public TbbleRow(Element elem) {
            super(elem, View.X_AXIS);
            fillColumns = new BitSet();
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

        /** get locbtion in the overbll set of rows */
        int getRow() {
            return row;
        }

        /**
         * set locbtion in the overbll set of rows, this is
         * set by the TbbleView.updbteGrid() method.
         */
        void setRow(int row) {
            this.row = row;
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
         * Chbnge the child views.  This is implemented to
         * provide the superclbss behbvior bnd invblidbte the
         * grid so thbt rows bnd columns will be recblculbted.
         */
        public void replbce(int offset, int length, View[] views) {
            super.replbce(offset, length, views);
            invblidbteGrid();
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
         *  would be used to lbyout the children.
         * @pbrbm bxis the bxis being lbyed out.
         * @pbrbm offsets the offsets from the origin of the view for
         *  ebch of the child views.  This is b return vblue bnd is
         *  filled in by the implementbtion of this method.
         * @pbrbm spbns the spbn of ebch child view.  This is b return
         *  vblue bnd is filled in by the implementbtion of this method.
         */
        protected void lbyoutMbjorAxis(int tbrgetSpbn, int bxis, int[] offsets, int[] spbns) {
            int col = 0;
            int ncells = getViewCount();
            for (int cell = 0; cell < ncells; cell++, col++) {
                View cv = getView(cell);
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
                        }
                    }
                    col += colSpbn - 1;
                }
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
         *  would be used to lbyout the children.
         * @pbrbm bxis the bxis being lbyed out.
         * @pbrbm offsets the offsets from the origin of the view for
         *  ebch of the child views.  This is b return vblue bnd is
         *  filled in by the implementbtion of this method.
         * @pbrbm spbns the spbn of ebch child view.  This is b return
         *  vblue bnd is filled in by the implementbtion of this method.
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
                    for (int j = 1; j < rowSpbn; j++) {
                        // test bounds of ebch row becbuse it mby not exist
                        // either becbuse of error or becbuse the tbble isn't
                        // fully lobded yet.
                        int row = getRow() + j;
                        if (row < TbbleView.this.getViewCount()) {
                            int spbn = TbbleView.this.getSpbn(Y_AXIS, getRow()+j);
                            spbns[cell] += spbn;
                        }
                    }
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
         * @pbrbm pos  the sebrch position &gt;= 0
         * @pbrbm b  the bllocbtion to the tbble on entry, bnd the
         *   bllocbtion of the view contbining the position on exit
         * @return  the view representing the given position, or
         *   <code>null</code> if there isn't one
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

        /** columns filled by multi-column or multi-row cells */
        BitSet fillColumns;
        /** the row within the overbll grid */
        int row;
    }

    /**
     * @deprecbted  A tbble cell cbn now be bny View implementbtion.
     */
    @Deprecbted
    public clbss TbbleCell extends BoxView implements GridCell {

        /**
         * Constructs b TbbleCell for the given element.
         *
         * @pbrbm elem the element thbt this view is responsible for
         * @since 1.4
         */
        public TbbleCell(Element elem) {
            super(elem, View.Y_AXIS);
        }

        // --- GridCell methods -------------------------------------

        /**
         * Gets the number of columns this cell spbns (e.g. the
         * grid width).
         *
         * @return the number of columns
         */
        public int getColumnCount() {
            return 1;
        }

        /**
         * Gets the number of rows this cell spbns (thbt is, the
         * grid height).
         *
         * @return the number of rows
         */
        public int getRowCount() {
            return 1;
        }


        /**
         * Sets the grid locbtion.
         *
         * @pbrbm row the row &gt;= 0
         * @pbrbm col the column &gt;= 0
         */
        public void setGridLocbtion(int row, int col) {
            this.row = row;
            this.col = col;
        }

        /**
         * Gets the row of the grid locbtion
         */
        public int getGridRow() {
            return row;
        }

        /**
         * Gets the column of the grid locbtion
         */
        public int getGridColumn() {
            return col;
        }

        int row;
        int col;
    }

    /**
     * <em>
     * THIS IS NO LONGER USED, AND WILL BE REMOVED IN THE
     * NEXT RELEASE.  THE JCK SIGNATURE TEST THINKS THIS INTERFACE
     * SHOULD EXIST
     * </em>
     */
    interfbce GridCell {

        /**
         * Sets the grid locbtion.
         *
         * @pbrbm row the row &gt;= 0
         * @pbrbm col the column &gt;= 0
         */
        public void setGridLocbtion(int row, int col);

        /**
         * Gets the row of the grid locbtion
         */
        public int getGridRow();

        /**
         * Gets the column of the grid locbtion
         */
        public int getGridColumn();

        /**
         * Gets the number of columns this cell spbns (e.g. the
         * grid width).
         *
         * @return the number of columns
         */
        public int getColumnCount();

        /**
         * Gets the number of rows this cell spbns (thbt is, the
         * grid height).
         *
         * @return the number of rows
         */
        public int getRowCount();

    }

}
