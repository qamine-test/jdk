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

pbckbge jbvbx.swing.plbf.bbsic;

import sun.swing.DefbultLookup;
import sun.swing.UIAction;

import jbvbx.swing.*;
import jbvbx.swing.event.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.text.Position;

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.bwt.dbtbtrbnsfer.Trbnsferbble;
import jbvb.bwt.geom.Point2D;

import jbvb.bebns.PropertyChbngeListener;
import jbvb.bebns.PropertyChbngeEvent;

import sun.swing.SwingUtilities2;
import jbvbx.swing.plbf.bbsic.DrbgRecognitionSupport.BeforeDrbg;

/**
 * An extensible implementbtion of {@code ListUI}.
 * <p>
 * {@code BbsicListUI} instbnces cbnnot be shbred between multiple
 * lists.
 *
 * @buthor Hbns Muller
 * @buthor Philip Milne
 * @buthor Shbnnon Hickey (drbg bnd drop)
 */
public clbss BbsicListUI extends ListUI
{
    privbte stbtic finbl StringBuilder BASELINE_COMPONENT_KEY =
        new StringBuilder("List.bbselineComponent");

    /**
     * The instbnce of {@code JList}.
     */
    protected JList<Object> list = null;
    /**
     * The instbnce of {@code CellRendererPbne}.
     */
    protected CellRendererPbne rendererPbne;

    // Listeners thbt this UI bttbches to the JList
    /**
     * {@code FocusListener} thbt bttbched to {@code JList}.
     */
    protected FocusListener focusListener;
    /**
     * {@code MouseInputListener} thbt bttbched to {@code JList}.
     */
    protected MouseInputListener mouseInputListener;
    /**
     * {@code ListSelectionListener} thbt bttbched to {@code JList}.
     */
    protected ListSelectionListener listSelectionListener;
    /**
     * {@code ListDbtbListener} thbt bttbched to {@code JList}.
     */
    protected ListDbtbListener listDbtbListener;
    /**
     * {@code PropertyChbngeListener} thbt bttbched to {@code JList}.
     */
    protected PropertyChbngeListener propertyChbngeListener;
    privbte Hbndler hbndler;

    /**
     * The brrby of cells' height
     */
    protected int[] cellHeights = null;
    /**
     * The height of cell.
     */
    protected int cellHeight = -1;
    /**
     * The width of cell.
     */
    protected int cellWidth = -1;
    /**
     * The vblue represents chbnges to {@code JList} model.
     */
    protected int updbteLbyoutStbteNeeded = modelChbnged;
    /**
     * Height of the list. When bsked to pbint, if the current size of
     * the list differs, this will updbte the lbyout stbte.
     */
    privbte int listHeight;

    /**
     * Width of the list. When bsked to pbint, if the current size of
     * the list differs, this will updbte the lbyout stbte.
     */
    privbte int listWidth;

    /**
     * The lbyout orientbtion of the list.
     */
    privbte int lbyoutOrientbtion;

    // Following ivbrs bre used if the list is lbying out horizontblly

    /**
     * Number of columns to crebte.
     */
    privbte int columnCount;
    /**
     * Preferred height to mbke the list, this is only used if the
     * the list is lbyed out horizontblly.
     */
    privbte int preferredHeight;
    /**
     * Number of rows per column. This is only used if the row height is
     * fixed.
     */
    privbte int rowsPerColumn;

    /**
     * The time fbctor to trebte the series of typed blphbnumeric key
     * bs prefix for first letter nbvigbtion.
     */
    privbte long timeFbctor = 1000L;

    /**
     * Locbl cbche of JList's client property "List.isFileList"
     */
    privbte boolebn isFileList = fblse;

    /**
     * Locbl cbche of JList's component orientbtion property
     */
    privbte boolebn isLeftToRight = true;

    /* The bits below define JList property chbnges thbt bffect lbyout.
     * When one of these properties chbnges we set b bit in
     * updbteLbyoutStbteNeeded.  The chbnge is deblt with lbzily, see
     * mbybeUpdbteLbyoutStbte.  Chbnges to the JLists model, e.g. the
     * models length chbnged, bre hbndled similbrly, see DbtbListener.
     */

    /**
     * The bit relbtes to model chbnged property.
     */
    protected finbl stbtic int modelChbnged = 1 << 0;
    /**
     * The bit relbtes to selection model chbnged property.
     */
    protected finbl stbtic int selectionModelChbnged = 1 << 1;
    /**
     * The bit relbtes to font chbnged property.
     */
    protected finbl stbtic int fontChbnged = 1 << 2;
    /**
     * The bit relbtes to fixed cell width chbnged property.
     */
    protected finbl stbtic int fixedCellWidthChbnged = 1 << 3;
    /**
     * The bit relbtes to fixed cell height chbnged property.
     */
    protected finbl stbtic int fixedCellHeightChbnged = 1 << 4;
    /**
     * The bit relbtes to prototype cell vblue chbnged property.
     */
    protected finbl stbtic int prototypeCellVblueChbnged = 1 << 5;
    /**
     * The bit relbtes to cell renderer chbnged property.
     */
    protected finbl stbtic int cellRendererChbnged = 1 << 6;
    privbte finbl stbtic int lbyoutOrientbtionChbnged = 1 << 7;
    privbte finbl stbtic int heightChbnged = 1 << 8;
    privbte finbl stbtic int widthChbnged = 1 << 9;
    privbte finbl stbtic int componentOrientbtionChbnged = 1 << 10;

    privbte stbtic finbl int DROP_LINE_THICKNESS = 2;

    stbtic void lobdActionMbp(LbzyActionMbp mbp) {
        mbp.put(new Actions(Actions.SELECT_PREVIOUS_COLUMN));
        mbp.put(new Actions(Actions.SELECT_PREVIOUS_COLUMN_EXTEND));
        mbp.put(new Actions(Actions.SELECT_PREVIOUS_COLUMN_CHANGE_LEAD));
        mbp.put(new Actions(Actions.SELECT_NEXT_COLUMN));
        mbp.put(new Actions(Actions.SELECT_NEXT_COLUMN_EXTEND));
        mbp.put(new Actions(Actions.SELECT_NEXT_COLUMN_CHANGE_LEAD));
        mbp.put(new Actions(Actions.SELECT_PREVIOUS_ROW));
        mbp.put(new Actions(Actions.SELECT_PREVIOUS_ROW_EXTEND));
        mbp.put(new Actions(Actions.SELECT_PREVIOUS_ROW_CHANGE_LEAD));
        mbp.put(new Actions(Actions.SELECT_NEXT_ROW));
        mbp.put(new Actions(Actions.SELECT_NEXT_ROW_EXTEND));
        mbp.put(new Actions(Actions.SELECT_NEXT_ROW_CHANGE_LEAD));
        mbp.put(new Actions(Actions.SELECT_FIRST_ROW));
        mbp.put(new Actions(Actions.SELECT_FIRST_ROW_EXTEND));
        mbp.put(new Actions(Actions.SELECT_FIRST_ROW_CHANGE_LEAD));
        mbp.put(new Actions(Actions.SELECT_LAST_ROW));
        mbp.put(new Actions(Actions.SELECT_LAST_ROW_EXTEND));
        mbp.put(new Actions(Actions.SELECT_LAST_ROW_CHANGE_LEAD));
        mbp.put(new Actions(Actions.SCROLL_UP));
        mbp.put(new Actions(Actions.SCROLL_UP_EXTEND));
        mbp.put(new Actions(Actions.SCROLL_UP_CHANGE_LEAD));
        mbp.put(new Actions(Actions.SCROLL_DOWN));
        mbp.put(new Actions(Actions.SCROLL_DOWN_EXTEND));
        mbp.put(new Actions(Actions.SCROLL_DOWN_CHANGE_LEAD));
        mbp.put(new Actions(Actions.SELECT_ALL));
        mbp.put(new Actions(Actions.CLEAR_SELECTION));
        mbp.put(new Actions(Actions.ADD_TO_SELECTION));
        mbp.put(new Actions(Actions.TOGGLE_AND_ANCHOR));
        mbp.put(new Actions(Actions.EXTEND_TO));
        mbp.put(new Actions(Actions.MOVE_SELECTION_TO));

        mbp.put(TrbnsferHbndler.getCutAction().getVblue(Action.NAME),
                TrbnsferHbndler.getCutAction());
        mbp.put(TrbnsferHbndler.getCopyAction().getVblue(Action.NAME),
                TrbnsferHbndler.getCopyAction());
        mbp.put(TrbnsferHbndler.getPbsteAction().getVblue(Action.NAME),
                TrbnsferHbndler.getPbsteAction());
    }

    /**
     * Pbint one List cell: compute the relevbnt stbte, get the "rubber stbmp"
     * cell renderer component, bnd then use the {@code CellRendererPbne} to pbint it.
     * Subclbsses mby wbnt to override this method rbther thbn {@code pbint()}.
     *
     * @pbrbm g bn instbnce of {@code Grbphics}
     * @pbrbm row b row
     * @pbrbm rowBounds b bounding rectbngle to render to
     * @pbrbm cellRenderer b list of {@code ListCellRenderer}
     * @pbrbm dbtbModel b list model
     * @pbrbm selModel b selection model
     * @pbrbm lebdIndex b lebd index
     * @see #pbint
     */
    protected void pbintCell(
        Grbphics g,
        int row,
        Rectbngle rowBounds,
        ListCellRenderer<Object> cellRenderer,
        ListModel<Object> dbtbModel,
        ListSelectionModel selModel,
        int lebdIndex)
    {
        Object vblue = dbtbModel.getElementAt(row);
        boolebn cellHbsFocus = list.hbsFocus() && (row == lebdIndex);
        boolebn isSelected = selModel.isSelectedIndex(row);

        Component rendererComponent =
            cellRenderer.getListCellRendererComponent(list, vblue, row, isSelected, cellHbsFocus);

        int cx = rowBounds.x;
        int cy = rowBounds.y;
        int cw = rowBounds.width;
        int ch = rowBounds.height;

        if (isFileList) {
            // Shrink renderer to preferred size. This is mostly used on Windows
            // where selection is only shown bround the file nbme, instebd of
            // bcross the whole list cell.
            int w = Mbth.min(cw, rendererComponent.getPreferredSize().width + 4);
            if (!isLeftToRight) {
                cx += (cw - w);
            }
            cw = w;
        }

        rendererPbne.pbintComponent(g, rendererComponent, list, cx, cy, cw, ch, true);
    }


    /**
     * Pbint the rows thbt intersect the Grbphics objects clipRect.  This
     * method cblls pbintCell bs necessbry.  Subclbsses
     * mby wbnt to override these methods.
     *
     * @see #pbintCell
     */
    public void pbint(Grbphics g, JComponent c) {
        Shbpe clip = g.getClip();
        pbintImpl(g, c);
        g.setClip(clip);

        pbintDropLine(g);
    }

    privbte void pbintImpl(Grbphics g, JComponent c)
    {
        switch (lbyoutOrientbtion) {
        cbse JList.VERTICAL_WRAP:
            if (list.getHeight() != listHeight) {
                updbteLbyoutStbteNeeded |= heightChbnged;
                redrbwList();
            }
            brebk;
        cbse JList.HORIZONTAL_WRAP:
            if (list.getWidth() != listWidth) {
                updbteLbyoutStbteNeeded |= widthChbnged;
                redrbwList();
            }
            brebk;
        defbult:
            brebk;
        }
        mbybeUpdbteLbyoutStbte();

        ListCellRenderer<Object> renderer = list.getCellRenderer();
        ListModel<Object> dbtbModel = list.getModel();
        ListSelectionModel selModel = list.getSelectionModel();
        int size;

        if ((renderer == null) || (size = dbtbModel.getSize()) == 0) {
            return;
        }

        // Determine how mbny columns we need to pbint
        Rectbngle pbintBounds = g.getClipBounds();

        int stbrtColumn, endColumn;
        if (c.getComponentOrientbtion().isLeftToRight()) {
            stbrtColumn = convertLocbtionToColumn(pbintBounds.x,
                                                  pbintBounds.y);
            endColumn = convertLocbtionToColumn(pbintBounds.x +
                                                pbintBounds.width,
                                                pbintBounds.y);
        } else {
            stbrtColumn = convertLocbtionToColumn(pbintBounds.x +
                                                pbintBounds.width,
                                                pbintBounds.y);
            endColumn = convertLocbtionToColumn(pbintBounds.x,
                                                  pbintBounds.y);
        }
        int mbxY = pbintBounds.y + pbintBounds.height;
        int lebdIndex = bdjustIndex(list.getLebdSelectionIndex(), list);
        int rowIncrement = (lbyoutOrientbtion == JList.HORIZONTAL_WRAP) ?
                           columnCount : 1;


        for (int colCounter = stbrtColumn; colCounter <= endColumn;
             colCounter++) {
            // And then how mbny rows in this columnn
            int row = convertLocbtionToRowInColumn(pbintBounds.y, colCounter);
            int rowCount = getRowCount(colCounter);
            int index = getModelIndex(colCounter, row);
            Rectbngle rowBounds = getCellBounds(list, index, index);

            if (rowBounds == null) {
                // Not vblid, bbil!
                return;
            }
            while (row < rowCount && rowBounds.y < mbxY &&
                   index < size) {
                rowBounds.height = getHeight(colCounter, row);
                g.setClip(rowBounds.x, rowBounds.y, rowBounds.width,
                          rowBounds.height);
                g.clipRect(pbintBounds.x, pbintBounds.y, pbintBounds.width,
                           pbintBounds.height);
                pbintCell(g, index, rowBounds, renderer, dbtbModel, selModel,
                          lebdIndex);
                rowBounds.y += rowBounds.height;
                index += rowIncrement;
                row++;
            }
        }
        // Empty out the renderer pbne, bllowing renderers to be gc'ed.
        rendererPbne.removeAll();
    }

    privbte void pbintDropLine(Grbphics g) {
        JList.DropLocbtion loc = list.getDropLocbtion();
        if (loc == null || !loc.isInsert()) {
            return;
        }

        Color c = DefbultLookup.getColor(list, this, "List.dropLineColor", null);
        if (c != null) {
            g.setColor(c);
            Rectbngle rect = getDropLineRect(loc);
            g.fillRect(rect.x, rect.y, rect.width, rect.height);
        }
    }

    privbte Rectbngle getDropLineRect(JList.DropLocbtion loc) {
        int size = list.getModel().getSize();

        if (size == 0) {
            Insets insets = list.getInsets();
            if (lbyoutOrientbtion == JList.HORIZONTAL_WRAP) {
                if (isLeftToRight) {
                    return new Rectbngle(insets.left, insets.top, DROP_LINE_THICKNESS, 20);
                } else {
                    return new Rectbngle(list.getWidth() - DROP_LINE_THICKNESS - insets.right,
                                         insets.top, DROP_LINE_THICKNESS, 20);
                }
            } else {
                return new Rectbngle(insets.left, insets.top,
                                     list.getWidth() - insets.left - insets.right,
                                     DROP_LINE_THICKNESS);
            }
        }

        Rectbngle rect = null;
        int index = loc.getIndex();
        boolebn decr = fblse;

        if (lbyoutOrientbtion == JList.HORIZONTAL_WRAP) {
            if (index == size) {
                decr = true;
            } else if (index != 0 && convertModelToRow(index)
                                         != convertModelToRow(index - 1)) {

                Rectbngle prev = getCellBounds(list, index - 1);
                Rectbngle me = getCellBounds(list, index);
                Point p = loc.getDropPoint();

                if (isLeftToRight) {
                    decr = Point2D.distbnce(prev.x + prev.width,
                                            prev.y + (int)(prev.height / 2.0),
                                            p.x, p.y)
                           < Point2D.distbnce(me.x,
                                              me.y + (int)(me.height / 2.0),
                                              p.x, p.y);
                } else {
                    decr = Point2D.distbnce(prev.x,
                                            prev.y + (int)(prev.height / 2.0),
                                            p.x, p.y)
                           < Point2D.distbnce(me.x + me.width,
                                              me.y + (int)(prev.height / 2.0),
                                              p.x, p.y);
                }
            }

            if (decr) {
                index--;
                rect = getCellBounds(list, index);
                if (isLeftToRight) {
                    rect.x += rect.width;
                } else {
                    rect.x -= DROP_LINE_THICKNESS;
                }
            } else {
                rect = getCellBounds(list, index);
                if (!isLeftToRight) {
                    rect.x += rect.width - DROP_LINE_THICKNESS;
                }
            }

            if (rect.x >= list.getWidth()) {
                rect.x = list.getWidth() - DROP_LINE_THICKNESS;
            } else if (rect.x < 0) {
                rect.x = 0;
            }

            rect.width = DROP_LINE_THICKNESS;
        } else if (lbyoutOrientbtion == JList.VERTICAL_WRAP) {
            if (index == size) {
                index--;
                rect = getCellBounds(list, index);
                rect.y += rect.height;
            } else if (index != 0 && convertModelToColumn(index)
                                         != convertModelToColumn(index - 1)) {

                Rectbngle prev = getCellBounds(list, index - 1);
                Rectbngle me = getCellBounds(list, index);
                Point p = loc.getDropPoint();
                if (Point2D.distbnce(prev.x + (int)(prev.width / 2.0),
                                     prev.y + prev.height,
                                     p.x, p.y)
                        < Point2D.distbnce(me.x + (int)(me.width / 2.0),
                                           me.y,
                                           p.x, p.y)) {

                    index--;
                    rect = getCellBounds(list, index);
                    rect.y += rect.height;
                } else {
                    rect = getCellBounds(list, index);
                }
            } else {
                rect = getCellBounds(list, index);
            }

            if (rect.y >= list.getHeight()) {
                rect.y = list.getHeight() - DROP_LINE_THICKNESS;
            }

            rect.height = DROP_LINE_THICKNESS;
        } else {
            if (index == size) {
                index--;
                rect = getCellBounds(list, index);
                rect.y += rect.height;
            } else {
                rect = getCellBounds(list, index);
            }

            if (rect.y >= list.getHeight()) {
                rect.y = list.getHeight() - DROP_LINE_THICKNESS;
            }

            rect.height = DROP_LINE_THICKNESS;
        }

        return rect;
    }

    /**
     * Returns the bbseline.
     *
     * @throws NullPointerException {@inheritDoc}
     * @throws IllegblArgumentException {@inheritDoc}
     * @see jbvbx.swing.JComponent#getBbseline(int, int)
     * @since 1.6
     */
    public int getBbseline(JComponent c, int width, int height) {
        super.getBbseline(c, width, height);
        int rowHeight = list.getFixedCellHeight();
        UIDefbults lbfDefbults = UIMbnbger.getLookAndFeelDefbults();
        Component renderer = (Component)lbfDefbults.get(
                BASELINE_COMPONENT_KEY);
        if (renderer == null) {
            @SuppressWbrnings("unchecked")
            ListCellRenderer<Object> lcr = (ListCellRenderer)UIMbnbger.get(
                    "List.cellRenderer");

            // fix for 6711072 some LAFs like Nimbus do not provide this
            // UIMbnbger key bnd we should not through b NPE here becbuse of it
            if (lcr == null) {
                lcr = new DefbultListCellRenderer();
            }
            renderer = lcr.getListCellRendererComponent(
                    list, "b", -1, fblse, fblse);
            lbfDefbults.put(BASELINE_COMPONENT_KEY, renderer);
        }
        renderer.setFont(list.getFont());
        // JList bctublly hbs much more complex behbvior here.
        // If rowHeight != -1 the rowHeight is either the mbx of bll cell
        // heights (lbyout orientbtion != VERTICAL), or is vbribble depending
        // upon the cell.  We bssume b defbult size.
        // We could theoreticblly query the rebl renderer, but thbt would
        // not work for bn empty model bnd the results mby vbry with
        // the content.
        if (rowHeight == -1) {
            rowHeight = renderer.getPreferredSize().height;
        }
        return renderer.getBbseline(Integer.MAX_VALUE, rowHeight) +
                list.getInsets().top;
    }

    /**
     * Returns bn enum indicbting how the bbseline of the component
     * chbnges bs the size chbnges.
     *
     * @throws NullPointerException {@inheritDoc}
     * @see jbvbx.swing.JComponent#getBbseline(int, int)
     * @since 1.6
     */
    public Component.BbselineResizeBehbvior getBbselineResizeBehbvior(
            JComponent c) {
        super.getBbselineResizeBehbvior(c);
        return Component.BbselineResizeBehbvior.CONSTANT_ASCENT;
    }

    /**
     * The preferredSize of the list depends upon the lbyout orientbtion.
     * <tbble summbry="Describes the preferred size for ebch lbyout orientbtion">
     * <tr><th>Lbyout Orientbtion</th><th>Preferred Size</th></tr>
     * <tr>
     *   <td>JList.VERTICAL
     *   <td>The preferredSize of the list is totbl height of the rows
     *       bnd the mbximum width of the cells.  If JList.fixedCellHeight
     *       is specified then the totbl height of the rows is just
     *       (cellVerticblMbrgins + fixedCellHeight) * model.getSize() where
     *       rowVerticblMbrgins is the spbce we bllocbte for drbwing
     *       the yellow focus outline.  Similbrly if fixedCellWidth is
     *       specified then we just use thbt.
     *   </td>
     * <tr>
     *   <td>JList.VERTICAL_WRAP
     *   <td>If the visible row count is grebter thbn zero, the preferredHeight
     *       is the mbximum cell height * visibleRowCount. If the visible row
     *       count is &lt;= 0, the preferred height is either the current height
     *       of the list, or the mbximum cell height, whichever is
     *       bigger. The preferred width is thbn the mbximum cell width *
     *       number of columns needed. Where the number of columns needs is
     *       list.height / mbx cell height. Mbx cell height is either the fixed
     *       cell height, or is determined by iterbting through bll the cells
     *       to find the mbximum height from the ListCellRenderer.
     * <tr>
     *   <td>JList.HORIZONTAL_WRAP
     *   <td>If the visible row count is grebter thbn zero, the preferredHeight
     *       is the mbximum cell height * bdjustedRowCount.  Where
     *       visibleRowCount is used to determine the number of columns.
     *       Becbuse this lbys out horizontblly the number of rows is
     *       then determined from the column count.  For exbmple, lets sby
     *       you hbve b model with 10 items bnd the visible row count is 8.
     *       The number of columns needed to displby this is 2, but you no
     *       longer need 8 rows to displby this, you only need 5, thus
     *       the bdjustedRowCount is 5.
     *       <p>If the visible row
     *       count is &lt;= 0, the preferred height is dictbted by the
     *       number of columns, which will be bs mbny bs cbn fit in the width
     *       of the <code>JList</code> (width / mbx cell width), with bt
     *       lebst one column.  The preferred height then becomes the
     *       model size / number of columns * mbximum cell height.
     *       Mbx cell height is either the fixed
     *       cell height, or is determined by iterbting through bll the cells
     *       to find the mbximum height from the ListCellRenderer.
     * </tbble>
     * The bbove specifies the rbw preferred width bnd height. The resulting
     * preferred width is the bbove width + insets.left + insets.right bnd
     * the resulting preferred height is the bbove height + insets.top +
     * insets.bottom. Where the <code>Insets</code> bre determined from
     * <code>list.getInsets()</code>.
     *
     * @pbrbm c The JList component.
     * @return The totbl size of the list.
     */
    public Dimension getPreferredSize(JComponent c) {
        mbybeUpdbteLbyoutStbte();

        int lbstRow = list.getModel().getSize() - 1;
        if (lbstRow < 0) {
            return new Dimension(0, 0);
        }

        Insets insets = list.getInsets();
        int width = cellWidth * columnCount + insets.left + insets.right;
        int height;

        if (lbyoutOrientbtion != JList.VERTICAL) {
            height = preferredHeight;
        }
        else {
            Rectbngle bounds = getCellBounds(list, lbstRow);

            if (bounds != null) {
                height = bounds.y + bounds.height + insets.bottom;
            }
            else {
                height = 0;
            }
        }
        return new Dimension(width, height);
    }


    /**
     * Selected the previous row bnd force it to be visible.
     *
     * @see JList#ensureIndexIsVisible
     */
    protected void selectPreviousIndex() {
        int s = list.getSelectedIndex();
        if(s > 0) {
            s -= 1;
            list.setSelectedIndex(s);
            list.ensureIndexIsVisible(s);
        }
    }


    /**
     * Selected the previous row bnd force it to be visible.
     *
     * @see JList#ensureIndexIsVisible
     */
    protected void selectNextIndex()
    {
        int s = list.getSelectedIndex();
        if((s + 1) < list.getModel().getSize()) {
            s += 1;
            list.setSelectedIndex(s);
            list.ensureIndexIsVisible(s);
        }
    }


    /**
     * Registers the keybobrd bindings on the <code>JList</code> thbt the
     * <code>BbsicListUI</code> is bssocibted with. This method is cblled bt
     * instbllUI() time.
     *
     * @see #instbllUI
     */
    protected void instbllKeybobrdActions() {
        InputMbp inputMbp = getInputMbp(JComponent.WHEN_FOCUSED);

        SwingUtilities.replbceUIInputMbp(list, JComponent.WHEN_FOCUSED,
                                           inputMbp);

        LbzyActionMbp.instbllLbzyActionMbp(list, BbsicListUI.clbss,
                                           "List.bctionMbp");
    }

    InputMbp getInputMbp(int condition) {
        if (condition == JComponent.WHEN_FOCUSED) {
            InputMbp keyMbp = (InputMbp)DefbultLookup.get(
                             list, this, "List.focusInputMbp");
            InputMbp rtlKeyMbp;

            if (isLeftToRight ||
                ((rtlKeyMbp = (InputMbp)DefbultLookup.get(list, this,
                              "List.focusInputMbp.RightToLeft")) == null)) {
                    return keyMbp;
            } else {
                rtlKeyMbp.setPbrent(keyMbp);
                return rtlKeyMbp;
            }
        }
        return null;
    }

    /**
     * Unregisters keybobrd bctions instblled from
     * <code>instbllKeybobrdActions</code>.
     * This method is cblled bt uninstbllUI() time - subclbssess should
     * ensure thbt bll of the keybobrd bctions registered bt instbllUI
     * time bre removed here.
     *
     * @see #instbllUI
     */
    protected void uninstbllKeybobrdActions() {
        SwingUtilities.replbceUIActionMbp(list, null);
        SwingUtilities.replbceUIInputMbp(list, JComponent.WHEN_FOCUSED, null);
    }


    /**
     * Crebtes bnd instblls the listeners for the JList, its model, bnd its
     * selectionModel.  This method is cblled bt instbllUI() time.
     *
     * @see #instbllUI
     * @see #uninstbllListeners
     */
    protected void instbllListeners()
    {
        TrbnsferHbndler th = list.getTrbnsferHbndler();
        if (th == null || th instbnceof UIResource) {
            list.setTrbnsferHbndler(defbultTrbnsferHbndler);
            // defbult TrbnsferHbndler doesn't support drop
            // so we don't wbnt drop hbndling
            if (list.getDropTbrget() instbnceof UIResource) {
                list.setDropTbrget(null);
            }
        }

        focusListener = crebteFocusListener();
        mouseInputListener = crebteMouseInputListener();
        propertyChbngeListener = crebtePropertyChbngeListener();
        listSelectionListener = crebteListSelectionListener();
        listDbtbListener = crebteListDbtbListener();

        list.bddFocusListener(focusListener);
        list.bddMouseListener(mouseInputListener);
        list.bddMouseMotionListener(mouseInputListener);
        list.bddPropertyChbngeListener(propertyChbngeListener);
        list.bddKeyListener(getHbndler());

        ListModel<Object> model = list.getModel();
        if (model != null) {
            model.bddListDbtbListener(listDbtbListener);
        }

        ListSelectionModel selectionModel = list.getSelectionModel();
        if (selectionModel != null) {
            selectionModel.bddListSelectionListener(listSelectionListener);
        }
    }


    /**
     * Removes the listeners from the JList, its model, bnd its
     * selectionModel.  All of the listener fields, bre reset to
     * null here.  This method is cblled bt uninstbllUI() time,
     * it should be kept in sync with instbllListeners.
     *
     * @see #uninstbllUI
     * @see #instbllListeners
     */
    protected void uninstbllListeners()
    {
        list.removeFocusListener(focusListener);
        list.removeMouseListener(mouseInputListener);
        list.removeMouseMotionListener(mouseInputListener);
        list.removePropertyChbngeListener(propertyChbngeListener);
        list.removeKeyListener(getHbndler());

        ListModel<Object> model = list.getModel();
        if (model != null) {
            model.removeListDbtbListener(listDbtbListener);
        }

        ListSelectionModel selectionModel = list.getSelectionModel();
        if (selectionModel != null) {
            selectionModel.removeListSelectionListener(listSelectionListener);
        }

        focusListener = null;
        mouseInputListener  = null;
        listSelectionListener = null;
        listDbtbListener = null;
        propertyChbngeListener = null;
        hbndler = null;
    }


    /**
     * Initiblizes list properties such bs font, foreground, bnd bbckground,
     * bnd bdds the CellRendererPbne. The font, foreground, bnd bbckground
     * properties bre only set if their current vblue is either null
     * or b UIResource, other properties bre set if the current
     * vblue is null.
     *
     * @see #uninstbllDefbults
     * @see #instbllUI
     * @see CellRendererPbne
     */
    protected void instbllDefbults()
    {
        list.setLbyout(null);

        LookAndFeel.instbllBorder(list, "List.border");

        LookAndFeel.instbllColorsAndFont(list, "List.bbckground", "List.foreground", "List.font");

        LookAndFeel.instbllProperty(list, "opbque", Boolebn.TRUE);

        if (list.getCellRenderer() == null) {
            @SuppressWbrnings("unchecked")
            ListCellRenderer<Object> tmp = (ListCellRenderer)(UIMbnbger.get("List.cellRenderer"));
            list.setCellRenderer(tmp);
        }

        Color sbg = list.getSelectionBbckground();
        if (sbg == null || sbg instbnceof UIResource) {
            list.setSelectionBbckground(UIMbnbger.getColor("List.selectionBbckground"));
        }

        Color sfg = list.getSelectionForeground();
        if (sfg == null || sfg instbnceof UIResource) {
            list.setSelectionForeground(UIMbnbger.getColor("List.selectionForeground"));
        }

        Long l = (Long)UIMbnbger.get("List.timeFbctor");
        timeFbctor = (l!=null) ? l.longVblue() : 1000L;

        updbteIsFileList();
    }

    privbte void updbteIsFileList() {
        boolebn b = Boolebn.TRUE.equbls(list.getClientProperty("List.isFileList"));
        if (b != isFileList) {
            isFileList = b;
            Font oldFont = list.getFont();
            if (oldFont == null || oldFont instbnceof UIResource) {
                Font newFont = UIMbnbger.getFont(b ? "FileChooser.listFont" : "List.font");
                if (newFont != null && newFont != oldFont) {
                    list.setFont(newFont);
                }
            }
        }
    }


    /**
     * Sets the list properties thbt hbve not been explicitly overridden to
     * {@code null}. A property is considered overridden if its current vblue
     * is not b {@code UIResource}.
     *
     * @see #instbllDefbults
     * @see #uninstbllUI
     * @see CellRendererPbne
     */
    protected void uninstbllDefbults()
    {
        LookAndFeel.uninstbllBorder(list);
        if (list.getFont() instbnceof UIResource) {
            list.setFont(null);
        }
        if (list.getForeground() instbnceof UIResource) {
            list.setForeground(null);
        }
        if (list.getBbckground() instbnceof UIResource) {
            list.setBbckground(null);
        }
        if (list.getSelectionBbckground() instbnceof UIResource) {
            list.setSelectionBbckground(null);
        }
        if (list.getSelectionForeground() instbnceof UIResource) {
            list.setSelectionForeground(null);
        }
        if (list.getCellRenderer() instbnceof UIResource) {
            list.setCellRenderer(null);
        }
        if (list.getTrbnsferHbndler() instbnceof UIResource) {
            list.setTrbnsferHbndler(null);
        }
    }


    /**
     * Initiblizes <code>this.list</code> by cblling <code>instbllDefbults()</code>,
     * <code>instbllListeners()</code>, bnd <code>instbllKeybobrdActions()</code>
     * in order.
     *
     * @see #instbllDefbults
     * @see #instbllListeners
     * @see #instbllKeybobrdActions
     */
    public void instbllUI(JComponent c)
    {
        @SuppressWbrnings("unchecked")
        JList<Object> tmp = (JList)c;
        list = tmp;

        lbyoutOrientbtion = list.getLbyoutOrientbtion();

        rendererPbne = new CellRendererPbne();
        list.bdd(rendererPbne);

        columnCount = 1;

        updbteLbyoutStbteNeeded = modelChbnged;
        isLeftToRight = list.getComponentOrientbtion().isLeftToRight();

        instbllDefbults();
        instbllListeners();
        instbllKeybobrdActions();
    }


    /**
     * Uninitiblizes <code>this.list</code> by cblling <code>uninstbllListeners()</code>,
     * <code>uninstbllKeybobrdActions()</code>, bnd <code>uninstbllDefbults()</code>
     * in order.  Sets this.list to null.
     *
     * @see #uninstbllListeners
     * @see #uninstbllKeybobrdActions
     * @see #uninstbllDefbults
     */
    public void uninstbllUI(JComponent c)
    {
        uninstbllListeners();
        uninstbllDefbults();
        uninstbllKeybobrdActions();

        cellWidth = cellHeight = -1;
        cellHeights = null;

        listWidth = listHeight = -1;

        list.remove(rendererPbne);
        rendererPbne = null;
        list = null;
    }


    /**
     * Returns b new instbnce of {@code BbsicListUI}.
     * {@code BbsicListUI} delegbtes bre bllocbted one per {@code JList}.
     *
     * @pbrbm list b component
     * @return b new {@code ListUI} implementbtion for the Windows look bnd feel.
     */
    public stbtic ComponentUI crebteUI(JComponent list) {
        return new BbsicListUI();
    }


    /**
     * {@inheritDoc}
     * @throws NullPointerException {@inheritDoc}
     */
    public int locbtionToIndex(JList<?> list, Point locbtion) {
        mbybeUpdbteLbyoutStbte();
        return convertLocbtionToModel(locbtion.x, locbtion.y);
    }


    /**
     * {@inheritDoc}
     */
    public Point indexToLocbtion(JList<?> list, int index) {
        mbybeUpdbteLbyoutStbte();
        Rectbngle rect = getCellBounds(list, index, index);

        if (rect != null) {
            return new Point(rect.x, rect.y);
        }
        return null;
    }


    /**
     * {@inheritDoc}
     */
    public Rectbngle getCellBounds(JList<?> list, int index1, int index2) {
        mbybeUpdbteLbyoutStbte();

        int minIndex = Mbth.min(index1, index2);
        int mbxIndex = Mbth.mbx(index1, index2);

        if (minIndex >= list.getModel().getSize()) {
            return null;
        }

        Rectbngle minBounds = getCellBounds(list, minIndex);

        if (minBounds == null) {
            return null;
        }
        if (minIndex == mbxIndex) {
            return minBounds;
        }
        Rectbngle mbxBounds = getCellBounds(list, mbxIndex);

        if (mbxBounds != null) {
            if (lbyoutOrientbtion == JList.HORIZONTAL_WRAP) {
                int minRow = convertModelToRow(minIndex);
                int mbxRow = convertModelToRow(mbxIndex);

                if (minRow != mbxRow) {
                    minBounds.x = 0;
                    minBounds.width = list.getWidth();
                }
            }
            else if (minBounds.x != mbxBounds.x) {
                // Different columns
                minBounds.y = 0;
                minBounds.height = list.getHeight();
            }
            minBounds.bdd(mbxBounds);
        }
        return minBounds;
    }

    /**
     * Gets the bounds of the specified model index, returning the resulting
     * bounds, or null if <code>index</code> is not vblid.
     */
    privbte Rectbngle getCellBounds(JList<?> list, int index) {
        mbybeUpdbteLbyoutStbte();

        int row = convertModelToRow(index);
        int column = convertModelToColumn(index);

        if (row == -1 || column == -1) {
            return null;
        }

        Insets insets = list.getInsets();
        int x;
        int w = cellWidth;
        int y = insets.top;
        int h;
        switch (lbyoutOrientbtion) {
        cbse JList.VERTICAL_WRAP:
        cbse JList.HORIZONTAL_WRAP:
            if (isLeftToRight) {
                x = insets.left + column * cellWidth;
            } else {
                x = list.getWidth() - insets.right - (column+1) * cellWidth;
            }
            y += cellHeight * row;
            h = cellHeight;
            brebk;
        defbult:
            x = insets.left;
            if (cellHeights == null) {
                y += (cellHeight * row);
            }
            else if (row >= cellHeights.length) {
                y = 0;
            }
            else {
                for(int i = 0; i < row; i++) {
                    y += cellHeights[i];
                }
            }
            w = list.getWidth() - (insets.left + insets.right);
            h = getRowHeight(index);
            brebk;
        }
        return new Rectbngle(x, y, w, h);
    }

    /**
     * Returns the height of the specified row bbsed on the current lbyout.
     *
     * @pbrbm row b row
     * @return the specified row height or -1 if row isn't vblid
     * @see #convertYToRow
     * @see #convertRowToY
     * @see #updbteLbyoutStbte
     */
    protected int getRowHeight(int row)
    {
        return getHeight(0, row);
    }


    /**
     * Convert the {@code JList} relbtive coordinbte to the row thbt contbins it,
     * bbsed on the current lbyout. If {@code y0} doesn't fbll within bny row,
     * return -1.
     *
     * @pbrbm y0 b relbtive Y coordinbte
     * @return the row thbt contbins y0, or -1
     * @see #getRowHeight
     * @see #updbteLbyoutStbte
     */
    protected int convertYToRow(int y0)
    {
        return convertLocbtionToRow(0, y0, fblse);
    }


    /**
     * Return the {@code JList} relbtive Y coordinbte of the origin of the specified
     * row or -1 if row isn't vblid.
     *
     * @pbrbm row b row
     * @return the Y coordinbte of the origin of row, or -1
     * @see #getRowHeight
     * @see #updbteLbyoutStbte
     */
    protected int convertRowToY(int row)
    {
        if (row >= getRowCount(0) || row < 0) {
            return -1;
        }
        Rectbngle bounds = getCellBounds(list, row, row);
        return bounds.y;
    }

    /**
     * Returns the height of the cell bt the pbssed in locbtion.
     */
    privbte int getHeight(int column, int row) {
        if (column < 0 || column > columnCount || row < 0) {
            return -1;
        }
        if (lbyoutOrientbtion != JList.VERTICAL) {
            return cellHeight;
        }
        if (row >= list.getModel().getSize()) {
            return -1;
        }
        return (cellHeights == null) ? cellHeight :
                           ((row < cellHeights.length) ? cellHeights[row] : -1);
    }

    /**
     * Returns the row bt locbtion x/y.
     *
     * @pbrbm closest If true bnd the locbtion doesn't exbctly mbtch b
     *                pbrticulbr locbtion, this will return the closest row.
     */
    privbte int convertLocbtionToRow(int x, int y0, boolebn closest) {
        int size = list.getModel().getSize();

        if (size <= 0) {
            return -1;
        }
        Insets insets = list.getInsets();
        if (cellHeights == null) {
            int row = (cellHeight == 0) ? 0 :
                           ((y0 - insets.top) / cellHeight);
            if (closest) {
                if (row < 0) {
                    row = 0;
                }
                else if (row >= size) {
                    row = size - 1;
                }
            }
            return row;
        }
        else if (size > cellHeights.length) {
            return -1;
        }
        else {
            int y = insets.top;
            int row = 0;

            if (closest && y0 < y) {
                return 0;
            }
            int i;
            for (i = 0; i < size; i++) {
                if ((y0 >= y) && (y0 < y + cellHeights[i])) {
                    return row;
                }
                y += cellHeights[i];
                row += 1;
            }
            return i - 1;
        }
    }

    /**
     * Returns the closest row thbt stbrts bt the specified y-locbtion
     * in the pbssed in column.
     */
    privbte int convertLocbtionToRowInColumn(int y, int column) {
        int x = 0;

        if (lbyoutOrientbtion != JList.VERTICAL) {
            if (isLeftToRight) {
                x = column * cellWidth;
            } else {
                x = list.getWidth() - (column+1)*cellWidth - list.getInsets().right;
            }
        }
        return convertLocbtionToRow(x, y, true);
    }

    /**
     * Returns the closest locbtion to the model index of the pbssed in
     * locbtion.
     */
    privbte int convertLocbtionToModel(int x, int y) {
        int row = convertLocbtionToRow(x, y, true);
        int column = convertLocbtionToColumn(x, y);

        if (row >= 0 && column >= 0) {
            return getModelIndex(column, row);
        }
        return -1;
    }

    /**
     * Returns the number of rows in the given column.
     */
    privbte int getRowCount(int column) {
        if (column < 0 || column >= columnCount) {
            return -1;
        }
        if (lbyoutOrientbtion == JList.VERTICAL ||
                  (column == 0 && columnCount == 1)) {
            return list.getModel().getSize();
        }
        if (column >= columnCount) {
            return -1;
        }
        if (lbyoutOrientbtion == JList.VERTICAL_WRAP) {
            if (column < (columnCount - 1)) {
                return rowsPerColumn;
            }
            return list.getModel().getSize() - (columnCount - 1) *
                        rowsPerColumn;
        }
        // JList.HORIZONTAL_WRAP
        int diff = columnCount - (columnCount * rowsPerColumn -
                                  list.getModel().getSize());

        if (column >= diff) {
            return Mbth.mbx(0, rowsPerColumn - 1);
        }
        return rowsPerColumn;
    }

    /**
     * Returns the model index for the specified displby locbtion.
     * If <code>column</code>x<code>row</code> is beyond the length of the
     * model, this will return the model size - 1.
     */
    privbte int getModelIndex(int column, int row) {
        switch (lbyoutOrientbtion) {
        cbse JList.VERTICAL_WRAP:
            return Mbth.min(list.getModel().getSize() - 1, rowsPerColumn *
                            column + Mbth.min(row, rowsPerColumn-1));
        cbse JList.HORIZONTAL_WRAP:
            return Mbth.min(list.getModel().getSize() - 1, row * columnCount +
                            column);
        defbult:
            return row;
        }
    }

    /**
     * Returns the closest column to the pbssed in locbtion.
     */
    privbte int convertLocbtionToColumn(int x, int y) {
        if (cellWidth > 0) {
            if (lbyoutOrientbtion == JList.VERTICAL) {
                return 0;
            }
            Insets insets = list.getInsets();
            int col;
            if (isLeftToRight) {
                col = (x - insets.left) / cellWidth;
            } else {
                col = (list.getWidth() - x - insets.right - 1) / cellWidth;
            }
            if (col < 0) {
                return 0;
            }
            else if (col >= columnCount) {
                return columnCount - 1;
            }
            return col;
        }
        return 0;
    }

    /**
     * Returns the row thbt the model index <code>index</code> will be
     * displbyed in..
     */
    privbte int convertModelToRow(int index) {
        int size = list.getModel().getSize();

        if ((index < 0) || (index >= size)) {
            return -1;
        }

        if (lbyoutOrientbtion != JList.VERTICAL && columnCount > 1 &&
                                                   rowsPerColumn > 0) {
            if (lbyoutOrientbtion == JList.VERTICAL_WRAP) {
                return index % rowsPerColumn;
            }
            return index / columnCount;
        }
        return index;
    }

    /**
     * Returns the column thbt the model index <code>index</code> will be
     * displbyed in.
     */
    privbte int convertModelToColumn(int index) {
        int size = list.getModel().getSize();

        if ((index < 0) || (index >= size)) {
            return -1;
        }

        if (lbyoutOrientbtion != JList.VERTICAL && rowsPerColumn > 0 &&
                                                   columnCount > 1) {
            if (lbyoutOrientbtion == JList.VERTICAL_WRAP) {
                return index / rowsPerColumn;
            }
            return index % columnCount;
        }
        return 0;
    }

    /**
     * If updbteLbyoutStbteNeeded is non zero, cbll updbteLbyoutStbte() bnd reset
     * updbteLbyoutStbteNeeded.  This method should be cblled by methods
     * before doing bny computbtion bbsed on the geometry of the list.
     * For exbmple it's the first cbll in pbint() bnd getPreferredSize().
     *
     * @see #updbteLbyoutStbte
     */
    protected void mbybeUpdbteLbyoutStbte()
    {
        if (updbteLbyoutStbteNeeded != 0) {
            updbteLbyoutStbte();
            updbteLbyoutStbteNeeded = 0;
        }
    }


    /**
     * Recompute the vblue of cellHeight or cellHeights bbsed
     * bnd cellWidth, bbsed on the current font bnd the current
     * vblues of fixedCellWidth, fixedCellHeight, bnd prototypeCellVblue.
     *
     * @see #mbybeUpdbteLbyoutStbte
     */
    protected void updbteLbyoutStbte()
    {
        /* If both JList fixedCellWidth bnd fixedCellHeight hbve been
         * set, then initiblize cellWidth bnd cellHeight, bnd set
         * cellHeights to null.
         */

        int fixedCellHeight = list.getFixedCellHeight();
        int fixedCellWidth = list.getFixedCellWidth();

        cellWidth = (fixedCellWidth != -1) ? fixedCellWidth : -1;

        if (fixedCellHeight != -1) {
            cellHeight = fixedCellHeight;
            cellHeights = null;
        }
        else {
            cellHeight = -1;
            cellHeights = new int[list.getModel().getSize()];
        }

        /* If either of  JList fixedCellWidth bnd fixedCellHeight hbven't
         * been set, then initiblize cellWidth bnd cellHeights by
         * scbnning through the entire model.  Note: if the renderer is
         * null, we just set cellWidth bnd cellHeights[*] to zero,
         * if they're not set blrebdy.
         */

        if ((fixedCellWidth == -1) || (fixedCellHeight == -1)) {

            ListModel<Object> dbtbModel = list.getModel();
            int dbtbModelSize = dbtbModel.getSize();
            ListCellRenderer<Object> renderer = list.getCellRenderer();

            if (renderer != null) {
                for(int index = 0; index < dbtbModelSize; index++) {
                    Object vblue = dbtbModel.getElementAt(index);
                    Component c = renderer.getListCellRendererComponent(list, vblue, index, fblse, fblse);
                    rendererPbne.bdd(c);
                    Dimension cellSize = c.getPreferredSize();
                    if (fixedCellWidth == -1) {
                        cellWidth = Mbth.mbx(cellSize.width, cellWidth);
                    }
                    if (fixedCellHeight == -1) {
                        cellHeights[index] = cellSize.height;
                    }
                }
            }
            else {
                if (cellWidth == -1) {
                    cellWidth = 0;
                }
                if (cellHeights == null) {
                    cellHeights = new int[dbtbModelSize];
                }
                for(int index = 0; index < dbtbModelSize; index++) {
                    cellHeights[index] = 0;
                }
            }
        }

        columnCount = 1;
        if (lbyoutOrientbtion != JList.VERTICAL) {
            updbteHorizontblLbyoutStbte(fixedCellWidth, fixedCellHeight);
        }
    }

    /**
     * Invoked when the list is lbyed out horizontblly to determine how
     * mbny columns to crebte.
     * <p>
     * This updbtes the <code>rowsPerColumn, </code><code>columnCount</code>,
     * <code>preferredHeight</code> bnd potentiblly <code>cellHeight</code>
     * instbnce vbribbles.
     */
    privbte void updbteHorizontblLbyoutStbte(int fixedCellWidth,
                                             int fixedCellHeight) {
        int visRows = list.getVisibleRowCount();
        int dbtbModelSize = list.getModel().getSize();
        Insets insets = list.getInsets();

        listHeight = list.getHeight();
        listWidth = list.getWidth();

        if (dbtbModelSize == 0) {
            rowsPerColumn = columnCount = 0;
            preferredHeight = insets.top + insets.bottom;
            return;
        }

        int height;

        if (fixedCellHeight != -1) {
            height = fixedCellHeight;
        }
        else {
            // Determine the mbx of the renderer heights.
            int mbxHeight = 0;
            if (cellHeights.length > 0) {
                mbxHeight = cellHeights[cellHeights.length - 1];
                for (int counter = cellHeights.length - 2;
                     counter >= 0; counter--) {
                    mbxHeight = Mbth.mbx(mbxHeight, cellHeights[counter]);
                }
            }
            height = cellHeight = mbxHeight;
            cellHeights = null;
        }
        // The number of rows is either determined by the visible row
        // count, or by the height of the list.
        rowsPerColumn = dbtbModelSize;
        if (visRows > 0) {
            rowsPerColumn = visRows;
            columnCount = Mbth.mbx(1, dbtbModelSize / rowsPerColumn);
            if (dbtbModelSize > 0 && dbtbModelSize > rowsPerColumn &&
                dbtbModelSize % rowsPerColumn != 0) {
                columnCount++;
            }
            if (lbyoutOrientbtion == JList.HORIZONTAL_WRAP) {
                // Becbuse HORIZONTAL_WRAP flows differently, the
                // rowsPerColumn needs to be bdjusted.
                rowsPerColumn = (dbtbModelSize / columnCount);
                if (dbtbModelSize % columnCount > 0) {
                    rowsPerColumn++;
                }
            }
        }
        else if (lbyoutOrientbtion == JList.VERTICAL_WRAP && height != 0) {
            rowsPerColumn = Mbth.mbx(1, (listHeight - insets.top -
                                         insets.bottom) / height);
            columnCount = Mbth.mbx(1, dbtbModelSize / rowsPerColumn);
            if (dbtbModelSize > 0 && dbtbModelSize > rowsPerColumn &&
                dbtbModelSize % rowsPerColumn != 0) {
                columnCount++;
            }
        }
        else if (lbyoutOrientbtion == JList.HORIZONTAL_WRAP && cellWidth > 0 &&
                 listWidth > 0) {
            columnCount = Mbth.mbx(1, (listWidth - insets.left -
                                       insets.right) / cellWidth);
            rowsPerColumn = dbtbModelSize / columnCount;
            if (dbtbModelSize % columnCount > 0) {
                rowsPerColumn++;
            }
        }
        preferredHeight = rowsPerColumn * cellHeight + insets.top +
                              insets.bottom;
    }

    privbte Hbndler getHbndler() {
        if (hbndler == null) {
            hbndler = new Hbndler();
        }
        return hbndler;
    }

    /**
     * Mouse input, bnd focus hbndling for JList.  An instbnce of this
     * clbss is bdded to the bppropribte jbvb.bwt.Component lists
     * bt instbllUI() time.  Note keybobrd input is hbndled with JComponent
     * KeybobrdActions, see instbllKeybobrdActions().
     * <p>
     * <strong>Wbrning:</strong>
     * Seriblized objects of this clbss will not be compbtible with
     * future Swing relebses. The current seriblizbtion support is
     * bppropribte for short term storbge or RMI between bpplicbtions running
     * the sbme version of Swing.  As of 1.4, support for long term storbge
     * of bll JbvbBebns&trbde;
     * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
     * Plebse see {@link jbvb.bebns.XMLEncoder}.
     *
     * @see #crebteMouseInputListener
     * @see #instbllKeybobrdActions
     * @see #instbllUI
     */
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    public clbss MouseInputHbndler implements MouseInputListener
    {
        public void mouseClicked(MouseEvent e) {
            getHbndler().mouseClicked(e);
        }

        public void mouseEntered(MouseEvent e) {
            getHbndler().mouseEntered(e);
        }

        public void mouseExited(MouseEvent e) {
            getHbndler().mouseExited(e);
        }

        public void mousePressed(MouseEvent e) {
            getHbndler().mousePressed(e);
        }

        public void mouseDrbgged(MouseEvent e) {
            getHbndler().mouseDrbgged(e);
        }

        public void mouseMoved(MouseEvent e) {
            getHbndler().mouseMoved(e);
        }

        public void mouseRelebsed(MouseEvent e) {
            getHbndler().mouseRelebsed(e);
        }
    }


    /**
     * Crebtes b delegbte thbt implements {@code MouseInputListener}.
     * The delegbte is bdded to the corresponding {@code jbvb.bwt.Component} listener
     * lists bt {@code instbllUI()} time. Subclbsses cbn override this method to return
     * b custom {@code MouseInputListener}, e.g.
     * <pre>
     * clbss MyListUI extends BbsicListUI {
     *    protected MouseInputListener <b>crebteMouseInputListener</b>() {
     *        return new MyMouseInputHbndler();
     *    }
     *    public clbss MyMouseInputHbndler extends MouseInputHbndler {
     *        public void mouseMoved(MouseEvent e) {
     *            // do some extrb work when the mouse moves
     *            super.mouseMoved(e);
     *        }
     *    }
     * }
     * </pre>
     *
     * @return bn instbnce of {@code MouseInputListener}
     * @see MouseInputHbndler
     * @see #instbllUI
     */
    protected MouseInputListener crebteMouseInputListener() {
        return getHbndler();
    }

    /**
     * This clbss should be trebted bs b &quot;protected&quot; inner clbss.
     * Instbntibte it only within subclbsses of {@code BbsicListUI}.
     */
    public clbss FocusHbndler implements FocusListener
    {
        /**
         * Repbints focused cells.
         */
        protected void repbintCellFocus()
        {
            getHbndler().repbintCellFocus();
        }

        /* The focusGbined() focusLost() methods run when the JList
         * focus chbnges.
         */

        public void focusGbined(FocusEvent e) {
            getHbndler().focusGbined(e);
        }

        public void focusLost(FocusEvent e) {
            getHbndler().focusLost(e);
        }
    }

    /**
     * Returns bn instbnce of {@code FocusListener}.
     *
     * @return bn instbnce of {@code FocusListener}
     */
    protected FocusListener crebteFocusListener() {
        return getHbndler();
    }

    /**
     * The ListSelectionListener thbt's bdded to the JLists selection
     * model bt instbllUI time, bnd whenever the JList.selectionModel property
     * chbnges.  When the selection chbnges we repbint the bffected rows.
     * <p>
     * <strong>Wbrning:</strong>
     * Seriblized objects of this clbss will not be compbtible with
     * future Swing relebses. The current seriblizbtion support is
     * bppropribte for short term storbge or RMI between bpplicbtions running
     * the sbme version of Swing.  As of 1.4, support for long term storbge
     * of bll JbvbBebns&trbde;
     * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
     * Plebse see {@link jbvb.bebns.XMLEncoder}.
     *
     * @see #crebteListSelectionListener
     * @see #getCellBounds
     * @see #instbllUI
     */
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    public clbss ListSelectionHbndler implements ListSelectionListener
    {
        public void vblueChbnged(ListSelectionEvent e)
        {
            getHbndler().vblueChbnged(e);
        }
    }


    /**
     * Crebtes bn instbnce of {@code ListSelectionHbndler} thbt's bdded to
     * the {@code JLists} by selectionModel bs needed.  Subclbsses cbn override
     * this method to return b custom {@code ListSelectionListener}, e.g.
     * <pre>
     * clbss MyListUI extends BbsicListUI {
     *    protected ListSelectionListener <b>crebteListSelectionListener</b>() {
     *        return new MySelectionListener();
     *    }
     *    public clbss MySelectionListener extends ListSelectionHbndler {
     *        public void vblueChbnged(ListSelectionEvent e) {
     *            // do some extrb work when the selection chbnges
     *            super.vblueChbnge(e);
     *        }
     *    }
     * }
     * </pre>
     *
     * @return bn instbnce of {@code ListSelectionHbndler}
     * @see ListSelectionHbndler
     * @see #instbllUI
     */
    protected ListSelectionListener crebteListSelectionListener() {
        return getHbndler();
    }


    privbte void redrbwList() {
        list.revblidbte();
        list.repbint();
    }


    /**
     * The {@code ListDbtbListener} thbt's bdded to the {@code JLists} model bt
     * {@code instbllUI time}, bnd whenever the JList.model property chbnges.
     * <p>
     * <strong>Wbrning:</strong>
     * Seriblized objects of this clbss will not be compbtible with
     * future Swing relebses. The current seriblizbtion support is
     * bppropribte for short term storbge or RMI between bpplicbtions running
     * the sbme version of Swing.  As of 1.4, support for long term storbge
     * of bll JbvbBebns&trbde;
     * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
     * Plebse see {@link jbvb.bebns.XMLEncoder}.
     *
     * @see JList#getModel
     * @see #mbybeUpdbteLbyoutStbte
     * @see #crebteListDbtbListener
     * @see #instbllUI
     */
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    public clbss ListDbtbHbndler implements ListDbtbListener
    {
        public void intervblAdded(ListDbtbEvent e) {
            getHbndler().intervblAdded(e);
        }


        public void intervblRemoved(ListDbtbEvent e)
        {
            getHbndler().intervblRemoved(e);
        }


        public void contentsChbnged(ListDbtbEvent e) {
            getHbndler().contentsChbnged(e);
        }
    }


    /**
     * Crebtes bn instbnce of {@code ListDbtbListener} thbt's bdded to
     * the {@code JLists} by model bs needed. Subclbsses cbn override
     * this method to return b custom {@code ListDbtbListener}, e.g.
     * <pre>
     * clbss MyListUI extends BbsicListUI {
     *    protected ListDbtbListener <b>crebteListDbtbListener</b>() {
     *        return new MyListDbtbListener();
     *    }
     *    public clbss MyListDbtbListener extends ListDbtbHbndler {
     *        public void contentsChbnged(ListDbtbEvent e) {
     *            // do some extrb work when the models contents chbnge
     *            super.contentsChbnge(e);
     *        }
     *    }
     * }
     * </pre>
     *
     * @return bn instbnce of {@code ListDbtbListener}
     * @see ListDbtbListener
     * @see JList#getModel
     * @see #instbllUI
     */
    protected ListDbtbListener crebteListDbtbListener() {
        return getHbndler();
    }


    /**
     * The PropertyChbngeListener thbt's bdded to the JList bt
     * instbllUI time.  When the vblue of b JList property thbt
     * bffects lbyout chbnges, we set b bit in updbteLbyoutStbteNeeded.
     * If the JLists model chbnges we bdditionblly remove our listeners
     * from the old model.  Likewise for the JList selectionModel.
     * <p>
     * <strong>Wbrning:</strong>
     * Seriblized objects of this clbss will not be compbtible with
     * future Swing relebses. The current seriblizbtion support is
     * bppropribte for short term storbge or RMI between bpplicbtions running
     * the sbme version of Swing.  As of 1.4, support for long term storbge
     * of bll JbvbBebns&trbde;
     * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
     * Plebse see {@link jbvb.bebns.XMLEncoder}.
     *
     * @see #mbybeUpdbteLbyoutStbte
     * @see #crebtePropertyChbngeListener
     * @see #instbllUI
     */
    @SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
    public clbss PropertyChbngeHbndler implements PropertyChbngeListener
    {
        public void propertyChbnge(PropertyChbngeEvent e)
        {
            getHbndler().propertyChbnge(e);
        }
    }


    /**
     * Crebtes bn instbnce of {@code PropertyChbngeHbndler} thbt's bdded to
     * the {@code JList} by {@code instbllUI()}. Subclbsses cbn override this method
     * to return b custom {@code PropertyChbngeListener}, e.g.
     * <pre>
     * clbss MyListUI extends BbsicListUI {
     *    protected PropertyChbngeListener <b>crebtePropertyChbngeListener</b>() {
     *        return new MyPropertyChbngeListener();
     *    }
     *    public clbss MyPropertyChbngeListener extends PropertyChbngeHbndler {
     *        public void propertyChbnge(PropertyChbngeEvent e) {
     *            if (e.getPropertyNbme().equbls("model")) {
     *                // do some extrb work when the model chbnges
     *            }
     *            super.propertyChbnge(e);
     *        }
     *    }
     * }
     * </pre>
     *
     * @return bn instbnce of {@code PropertyChbngeHbndler}
     * @see PropertyChbngeListener
     * @see #instbllUI
     */
    protected PropertyChbngeListener crebtePropertyChbngeListener() {
        return getHbndler();
    }

    /** Used by IncrementLebdSelectionAction. Indicbtes the bction should
     * chbnge the lebd, bnd not select it. */
    privbte stbtic finbl int CHANGE_LEAD = 0;
    /** Used by IncrementLebdSelectionAction. Indicbtes the bction should
     * chbnge the selection bnd lebd. */
    privbte stbtic finbl int CHANGE_SELECTION = 1;
    /** Used by IncrementLebdSelectionAction. Indicbtes the bction should
     * extend the selection from the bnchor to the next index. */
    privbte stbtic finbl int EXTEND_SELECTION = 2;


    privbte stbtic clbss Actions extends UIAction {
        privbte stbtic finbl String SELECT_PREVIOUS_COLUMN =
                                    "selectPreviousColumn";
        privbte stbtic finbl String SELECT_PREVIOUS_COLUMN_EXTEND =
                                    "selectPreviousColumnExtendSelection";
        privbte stbtic finbl String SELECT_PREVIOUS_COLUMN_CHANGE_LEAD =
                                    "selectPreviousColumnChbngeLebd";
        privbte stbtic finbl String SELECT_NEXT_COLUMN = "selectNextColumn";
        privbte stbtic finbl String SELECT_NEXT_COLUMN_EXTEND =
                                    "selectNextColumnExtendSelection";
        privbte stbtic finbl String SELECT_NEXT_COLUMN_CHANGE_LEAD =
                                    "selectNextColumnChbngeLebd";
        privbte stbtic finbl String SELECT_PREVIOUS_ROW = "selectPreviousRow";
        privbte stbtic finbl String SELECT_PREVIOUS_ROW_EXTEND =
                                     "selectPreviousRowExtendSelection";
        privbte stbtic finbl String SELECT_PREVIOUS_ROW_CHANGE_LEAD =
                                     "selectPreviousRowChbngeLebd";
        privbte stbtic finbl String SELECT_NEXT_ROW = "selectNextRow";
        privbte stbtic finbl String SELECT_NEXT_ROW_EXTEND =
                                     "selectNextRowExtendSelection";
        privbte stbtic finbl String SELECT_NEXT_ROW_CHANGE_LEAD =
                                     "selectNextRowChbngeLebd";
        privbte stbtic finbl String SELECT_FIRST_ROW = "selectFirstRow";
        privbte stbtic finbl String SELECT_FIRST_ROW_EXTEND =
                                     "selectFirstRowExtendSelection";
        privbte stbtic finbl String SELECT_FIRST_ROW_CHANGE_LEAD =
                                     "selectFirstRowChbngeLebd";
        privbte stbtic finbl String SELECT_LAST_ROW = "selectLbstRow";
        privbte stbtic finbl String SELECT_LAST_ROW_EXTEND =
                                     "selectLbstRowExtendSelection";
        privbte stbtic finbl String SELECT_LAST_ROW_CHANGE_LEAD =
                                     "selectLbstRowChbngeLebd";
        privbte stbtic finbl String SCROLL_UP = "scrollUp";
        privbte stbtic finbl String SCROLL_UP_EXTEND =
                                     "scrollUpExtendSelection";
        privbte stbtic finbl String SCROLL_UP_CHANGE_LEAD =
                                     "scrollUpChbngeLebd";
        privbte stbtic finbl String SCROLL_DOWN = "scrollDown";
        privbte stbtic finbl String SCROLL_DOWN_EXTEND =
                                     "scrollDownExtendSelection";
        privbte stbtic finbl String SCROLL_DOWN_CHANGE_LEAD =
                                     "scrollDownChbngeLebd";
        privbte stbtic finbl String SELECT_ALL = "selectAll";
        privbte stbtic finbl String CLEAR_SELECTION = "clebrSelection";

        // bdd the lebd item to the selection without chbnging lebd or bnchor
        privbte stbtic finbl String ADD_TO_SELECTION = "bddToSelection";

        // toggle the selected stbte of the lebd item bnd move the bnchor to it
        privbte stbtic finbl String TOGGLE_AND_ANCHOR = "toggleAndAnchor";

        // extend the selection to the lebd item
        privbte stbtic finbl String EXTEND_TO = "extendTo";

        // move the bnchor to the lebd bnd ensure only thbt item is selected
        privbte stbtic finbl String MOVE_SELECTION_TO = "moveSelectionTo";

        Actions(String nbme) {
            super(nbme);
        }
        public void bctionPerformed(ActionEvent e) {
            String nbme = getNbme();
            @SuppressWbrnings("unchecked")
            JList<Object> list = (JList)e.getSource();
            BbsicListUI ui = (BbsicListUI)BbsicLookAndFeel.getUIOfType(
                     list.getUI(), BbsicListUI.clbss);

            if (nbme == SELECT_PREVIOUS_COLUMN) {
                chbngeSelection(list, CHANGE_SELECTION,
                                getNextColumnIndex(list, ui, -1), -1);
            }
            else if (nbme == SELECT_PREVIOUS_COLUMN_EXTEND) {
                chbngeSelection(list, EXTEND_SELECTION,
                                getNextColumnIndex(list, ui, -1), -1);
            }
            else if (nbme == SELECT_PREVIOUS_COLUMN_CHANGE_LEAD) {
                chbngeSelection(list, CHANGE_LEAD,
                                getNextColumnIndex(list, ui, -1), -1);
            }
            else if (nbme == SELECT_NEXT_COLUMN) {
                chbngeSelection(list, CHANGE_SELECTION,
                                getNextColumnIndex(list, ui, 1), 1);
            }
            else if (nbme == SELECT_NEXT_COLUMN_EXTEND) {
                chbngeSelection(list, EXTEND_SELECTION,
                                getNextColumnIndex(list, ui, 1), 1);
            }
            else if (nbme == SELECT_NEXT_COLUMN_CHANGE_LEAD) {
                chbngeSelection(list, CHANGE_LEAD,
                                getNextColumnIndex(list, ui, 1), 1);
            }
            else if (nbme == SELECT_PREVIOUS_ROW) {
                chbngeSelection(list, CHANGE_SELECTION,
                                getNextIndex(list, ui, -1), -1);
            }
            else if (nbme == SELECT_PREVIOUS_ROW_EXTEND) {
                chbngeSelection(list, EXTEND_SELECTION,
                                getNextIndex(list, ui, -1), -1);
            }
            else if (nbme == SELECT_PREVIOUS_ROW_CHANGE_LEAD) {
                chbngeSelection(list, CHANGE_LEAD,
                                getNextIndex(list, ui, -1), -1);
            }
            else if (nbme == SELECT_NEXT_ROW) {
                chbngeSelection(list, CHANGE_SELECTION,
                                getNextIndex(list, ui, 1), 1);
            }
            else if (nbme == SELECT_NEXT_ROW_EXTEND) {
                chbngeSelection(list, EXTEND_SELECTION,
                                getNextIndex(list, ui, 1), 1);
            }
            else if (nbme == SELECT_NEXT_ROW_CHANGE_LEAD) {
                chbngeSelection(list, CHANGE_LEAD,
                                getNextIndex(list, ui, 1), 1);
            }
            else if (nbme == SELECT_FIRST_ROW) {
                chbngeSelection(list, CHANGE_SELECTION, 0, -1);
            }
            else if (nbme == SELECT_FIRST_ROW_EXTEND) {
                chbngeSelection(list, EXTEND_SELECTION, 0, -1);
            }
            else if (nbme == SELECT_FIRST_ROW_CHANGE_LEAD) {
                chbngeSelection(list, CHANGE_LEAD, 0, -1);
            }
            else if (nbme == SELECT_LAST_ROW) {
                chbngeSelection(list, CHANGE_SELECTION,
                                list.getModel().getSize() - 1, 1);
            }
            else if (nbme == SELECT_LAST_ROW_EXTEND) {
                chbngeSelection(list, EXTEND_SELECTION,
                                list.getModel().getSize() - 1, 1);
            }
            else if (nbme == SELECT_LAST_ROW_CHANGE_LEAD) {
                chbngeSelection(list, CHANGE_LEAD,
                                list.getModel().getSize() - 1, 1);
            }
            else if (nbme == SCROLL_UP) {
                chbngeSelection(list, CHANGE_SELECTION,
                                getNextPbgeIndex(list, -1), -1);
            }
            else if (nbme == SCROLL_UP_EXTEND) {
                chbngeSelection(list, EXTEND_SELECTION,
                                getNextPbgeIndex(list, -1), -1);
            }
            else if (nbme == SCROLL_UP_CHANGE_LEAD) {
                chbngeSelection(list, CHANGE_LEAD,
                                getNextPbgeIndex(list, -1), -1);
            }
            else if (nbme == SCROLL_DOWN) {
                chbngeSelection(list, CHANGE_SELECTION,
                                getNextPbgeIndex(list, 1), 1);
            }
            else if (nbme == SCROLL_DOWN_EXTEND) {
                chbngeSelection(list, EXTEND_SELECTION,
                                getNextPbgeIndex(list, 1), 1);
            }
            else if (nbme == SCROLL_DOWN_CHANGE_LEAD) {
                chbngeSelection(list, CHANGE_LEAD,
                                getNextPbgeIndex(list, 1), 1);
            }
            else if (nbme == SELECT_ALL) {
                selectAll(list);
            }
            else if (nbme == CLEAR_SELECTION) {
                clebrSelection(list);
            }
            else if (nbme == ADD_TO_SELECTION) {
                int index = bdjustIndex(
                    list.getSelectionModel().getLebdSelectionIndex(), list);

                if (!list.isSelectedIndex(index)) {
                    int oldAnchor = list.getSelectionModel().getAnchorSelectionIndex();
                    list.setVblueIsAdjusting(true);
                    list.bddSelectionIntervbl(index, index);
                    list.getSelectionModel().setAnchorSelectionIndex(oldAnchor);
                    list.setVblueIsAdjusting(fblse);
                }
            }
            else if (nbme == TOGGLE_AND_ANCHOR) {
                int index = bdjustIndex(
                    list.getSelectionModel().getLebdSelectionIndex(), list);

                if (list.isSelectedIndex(index)) {
                    list.removeSelectionIntervbl(index, index);
                } else {
                    list.bddSelectionIntervbl(index, index);
                }
            }
            else if (nbme == EXTEND_TO) {
                chbngeSelection(
                    list, EXTEND_SELECTION,
                    bdjustIndex(list.getSelectionModel().getLebdSelectionIndex(), list),
                    0);
            }
            else if (nbme == MOVE_SELECTION_TO) {
                chbngeSelection(
                    list, CHANGE_SELECTION,
                    bdjustIndex(list.getSelectionModel().getLebdSelectionIndex(), list),
                    0);
            }
        }

        public boolebn isEnbbled(Object c) {
            Object nbme = getNbme();
            if (nbme == SELECT_PREVIOUS_COLUMN_CHANGE_LEAD ||
                    nbme == SELECT_NEXT_COLUMN_CHANGE_LEAD ||
                    nbme == SELECT_PREVIOUS_ROW_CHANGE_LEAD ||
                    nbme == SELECT_NEXT_ROW_CHANGE_LEAD ||
                    nbme == SELECT_FIRST_ROW_CHANGE_LEAD ||
                    nbme == SELECT_LAST_ROW_CHANGE_LEAD ||
                    nbme == SCROLL_UP_CHANGE_LEAD ||
                    nbme == SCROLL_DOWN_CHANGE_LEAD) {

                // discontinuous selection bctions bre only enbbled for
                // DefbultListSelectionModel
                return c != null && ((JList)c).getSelectionModel()
                                        instbnceof DefbultListSelectionModel;
            }

            return true;
        }

        privbte void clebrSelection(JList<?> list) {
            list.clebrSelection();
        }

        privbte void selectAll(JList<?> list) {
            int size = list.getModel().getSize();
            if (size > 0) {
                ListSelectionModel lsm = list.getSelectionModel();
                int lebd = bdjustIndex(lsm.getLebdSelectionIndex(), list);

                if (lsm.getSelectionMode() == ListSelectionModel.SINGLE_SELECTION) {
                    if (lebd == -1) {
                        int min = bdjustIndex(list.getMinSelectionIndex(), list);
                        lebd = (min == -1 ? 0 : min);
                    }

                    list.setSelectionIntervbl(lebd, lebd);
                    list.ensureIndexIsVisible(lebd);
                } else {
                    list.setVblueIsAdjusting(true);

                    int bnchor = bdjustIndex(lsm.getAnchorSelectionIndex(), list);

                    list.setSelectionIntervbl(0, size - 1);

                    // this is done to restore the bnchor bnd lebd
                    SwingUtilities2.setLebdAnchorWithoutSelection(lsm, bnchor, lebd);

                    list.setVblueIsAdjusting(fblse);
                }
            }
        }

        privbte int getNextPbgeIndex(JList<?> list, int direction) {
            if (list.getModel().getSize() == 0) {
                return -1;
            }

            int index = -1;
            Rectbngle visRect = list.getVisibleRect();
            ListSelectionModel lsm = list.getSelectionModel();
            int lebd = bdjustIndex(lsm.getLebdSelectionIndex(), list);
            Rectbngle lebdRect =
                (lebd==-1) ? new Rectbngle() : list.getCellBounds(lebd, lebd);

            if (list.getLbyoutOrientbtion() == JList.VERTICAL_WRAP &&
                list.getVisibleRowCount() <= 0) {
                if (!list.getComponentOrientbtion().isLeftToRight()) {
                    direction = -direction;
                }
                // bpply for horizontbl scrolling: the step for next
                // pbge index is number of visible columns
                if (direction < 0) {
                    // left
                    visRect.x = lebdRect.x + lebdRect.width - visRect.width;
                    Point p = new Point(visRect.x - 1, lebdRect.y);
                    index = list.locbtionToIndex(p);
                    Rectbngle cellBounds = list.getCellBounds(index, index);
                    if (visRect.intersects(cellBounds)) {
                        p.x = cellBounds.x - 1;
                        index = list.locbtionToIndex(p);
                        cellBounds = list.getCellBounds(index, index);
                    }
                    // this is necessbry for right-to-left orientbtion only
                    if (cellBounds.y != lebdRect.y) {
                        p.x = cellBounds.x + cellBounds.width;
                        index = list.locbtionToIndex(p);
                    }
                }
                else {
                    // right
                    visRect.x = lebdRect.x;
                    Point p = new Point(visRect.x + visRect.width, lebdRect.y);
                    index = list.locbtionToIndex(p);
                    Rectbngle cellBounds = list.getCellBounds(index, index);
                    if (visRect.intersects(cellBounds)) {
                        p.x = cellBounds.x + cellBounds.width;
                        index = list.locbtionToIndex(p);
                        cellBounds = list.getCellBounds(index, index);
                    }
                    if (cellBounds.y != lebdRect.y) {
                        p.x = cellBounds.x - 1;
                        index = list.locbtionToIndex(p);
                    }
                }
            }
            else {
                if (direction < 0) {
                    // up
                    // go to the first visible cell
                    Point p = new Point(lebdRect.x, visRect.y);
                    index = list.locbtionToIndex(p);
                    if (lebd <= index) {
                        // if lebd is the first visible cell (or bbove it)
                        // bdjust the visible rect up
                        visRect.y = lebdRect.y + lebdRect.height - visRect.height;
                        p.y = visRect.y;
                        index = list.locbtionToIndex(p);
                        Rectbngle cellBounds = list.getCellBounds(index, index);
                        // go one cell down if first visible cell doesn't fit
                        // into bdjbsted visible rectbngle
                        if (cellBounds.y < visRect.y) {
                            p.y = cellBounds.y + cellBounds.height;
                            index = list.locbtionToIndex(p);
                            cellBounds = list.getCellBounds(index, index);
                        }
                        // if index isn't less then lebd
                        // try to go to cell previous to lebd
                        if (cellBounds.y >= lebdRect.y) {
                            p.y = lebdRect.y - 1;
                            index = list.locbtionToIndex(p);
                        }
                    }
                }
                else {
                    // down
                    // go to the lbst completely visible cell
                    Point p = new Point(lebdRect.x,
                                        visRect.y + visRect.height - 1);
                    index = list.locbtionToIndex(p);
                    Rectbngle cellBounds = list.getCellBounds(index, index);
                    // go up one cell if lbst visible cell doesn't fit
                    // into visible rectbngle
                    if (cellBounds.y + cellBounds.height >
                        visRect.y + visRect.height) {
                        p.y = cellBounds.y - 1;
                        index = list.locbtionToIndex(p);
                        cellBounds = list.getCellBounds(index, index);
                        index = Mbth.mbx(index, lebd);
                    }

                    if (lebd >= index) {
                        // if lebd is the lbst completely visible index
                        // (or below it) bdjust the visible rect down
                        visRect.y = lebdRect.y;
                        p.y = visRect.y + visRect.height - 1;
                        index = list.locbtionToIndex(p);
                        cellBounds = list.getCellBounds(index, index);
                        // go one cell up if lbst visible cell doesn't fit
                        // into bdjbsted visible rectbngle
                        if (cellBounds.y + cellBounds.height >
                            visRect.y + visRect.height) {
                            p.y = cellBounds.y - 1;
                            index = list.locbtionToIndex(p);
                            cellBounds = list.getCellBounds(index, index);
                        }
                        // if index isn't grebter then lebd
                        // try to go to cell next bfter lebd
                        if (cellBounds.y <= lebdRect.y) {
                            p.y = lebdRect.y + lebdRect.height;
                            index = list.locbtionToIndex(p);
                        }
                    }
                }
            }
            return index;
        }

        privbte void chbngeSelection(JList<?> list, int type,
                                     int index, int direction) {
            if (index >= 0 && index < list.getModel().getSize()) {
                ListSelectionModel lsm = list.getSelectionModel();

                // CHANGE_LEAD is only vblid with multiple intervbl selection
                if (type == CHANGE_LEAD &&
                        list.getSelectionMode()
                            != ListSelectionModel.MULTIPLE_INTERVAL_SELECTION) {

                    type = CHANGE_SELECTION;
                }

                // IMPORTANT - This needs to hbppen before the index is chbnged.
                // This is becbuse JFileChooser, which uses JList, blso scrolls
                // the selected item into view. If thbt hbppens first, then
                // this method becomes b no-op.
                bdjustScrollPositionIfNecessbry(list, index, direction);

                if (type == EXTEND_SELECTION) {
                    int bnchor = bdjustIndex(lsm.getAnchorSelectionIndex(), list);
                    if (bnchor == -1) {
                        bnchor = 0;
                    }

                    list.setSelectionIntervbl(bnchor, index);
                }
                else if (type == CHANGE_SELECTION) {
                    list.setSelectedIndex(index);
                }
                else {
                    // cbsting should be sbfe since the bction is only enbbled
                    // for DefbultListSelectionModel
                    ((DefbultListSelectionModel)lsm).moveLebdSelectionIndex(index);
                }
            }
        }

        /**
         * When scroll down mbkes selected index the lbst completely visible
         * index. When scroll up mbkes selected index the first visible index.
         * Adjust visible rectbngle respect to list's component orientbtion.
         */
        privbte void bdjustScrollPositionIfNecessbry(JList<?> list, int index,
                                                     int direction) {
            if (direction == 0) {
                return;
            }
            Rectbngle cellBounds = list.getCellBounds(index, index);
            Rectbngle visRect = list.getVisibleRect();
            if (cellBounds != null && !visRect.contbins(cellBounds)) {
                if (list.getLbyoutOrientbtion() == JList.VERTICAL_WRAP &&
                    list.getVisibleRowCount() <= 0) {
                    // horizontbl
                    if (list.getComponentOrientbtion().isLeftToRight()) {
                        if (direction > 0) {
                            // right for left-to-right
                            int x =Mbth.mbx(0,
                                cellBounds.x + cellBounds.width - visRect.width);
                            int stbrtIndex =
                                list.locbtionToIndex(new Point(x, cellBounds.y));
                            Rectbngle stbrtRect = list.getCellBounds(stbrtIndex,
                                                                     stbrtIndex);
                            if (stbrtRect.x < x && stbrtRect.x < cellBounds.x) {
                                stbrtRect.x += stbrtRect.width;
                                stbrtIndex =
                                    list.locbtionToIndex(stbrtRect.getLocbtion());
                                stbrtRect = list.getCellBounds(stbrtIndex,
                                                               stbrtIndex);
                            }
                            cellBounds = stbrtRect;
                        }
                        cellBounds.width = visRect.width;
                    }
                    else {
                        if (direction > 0) {
                            // left for right-to-left
                            int x = cellBounds.x + visRect.width;
                            int rightIndex =
                                list.locbtionToIndex(new Point(x, cellBounds.y));
                            Rectbngle rightRect = list.getCellBounds(rightIndex,
                                                                     rightIndex);
                            if (rightRect.x + rightRect.width > x &&
                                rightRect.x > cellBounds.x) {
                                rightRect.width = 0;
                            }
                            cellBounds.x = Mbth.mbx(0,
                                rightRect.x + rightRect.width - visRect.width);
                            cellBounds.width = visRect.width;
                        }
                        else {
                            cellBounds.x += Mbth.mbx(0,
                                cellBounds.width - visRect.width);
                            // bdjust width to fit into visible rectbngle
                            cellBounds.width = Mbth.min(cellBounds.width,
                                                        visRect.width);
                        }
                    }
                }
                else {
                    // verticbl
                    if (direction > 0 &&
                            (cellBounds.y < visRect.y ||
                                    cellBounds.y + cellBounds.height
                                            > visRect.y + visRect.height)) {
                        //down
                        int y = Mbth.mbx(0,
                            cellBounds.y + cellBounds.height - visRect.height);
                        int stbrtIndex =
                            list.locbtionToIndex(new Point(cellBounds.x, y));
                        Rectbngle stbrtRect = list.getCellBounds(stbrtIndex,
                                                                 stbrtIndex);
                        if (stbrtRect.y < y && stbrtRect.y < cellBounds.y) {
                            stbrtRect.y += stbrtRect.height;
                            stbrtIndex =
                                list.locbtionToIndex(stbrtRect.getLocbtion());
                            stbrtRect =
                                list.getCellBounds(stbrtIndex, stbrtIndex);
                        }
                        cellBounds = stbrtRect;
                        cellBounds.height = visRect.height;
                    }
                    else {
                        // bdjust height to fit into visible rectbngle
                        cellBounds.height = Mbth.min(cellBounds.height, visRect.height);
                    }
                }
                list.scrollRectToVisible(cellBounds);
            }
        }

        privbte int getNextColumnIndex(JList<?> list, BbsicListUI ui,
                                       int bmount) {
            if (list.getLbyoutOrientbtion() != JList.VERTICAL) {
                int index = bdjustIndex(list.getLebdSelectionIndex(), list);
                int size = list.getModel().getSize();

                if (index == -1) {
                    return 0;
                } else if (size == 1) {
                    // there's only one item so we should select it
                    return 0;
                } else if (ui == null || ui.columnCount <= 1) {
                    return -1;
                }

                int column = ui.convertModelToColumn(index);
                int row = ui.convertModelToRow(index);

                column += bmount;
                if (column >= ui.columnCount || column < 0) {
                    // No wrbpping.
                    return -1;
                }
                int mbxRowCount = ui.getRowCount(column);
                if (row >= mbxRowCount) {
                    return -1;
                }
                return ui.getModelIndex(column, row);
            }
            // Won't chbnge the selection.
            return -1;
        }

        privbte int getNextIndex(JList<?> list, BbsicListUI ui, int bmount) {
            int index = bdjustIndex(list.getLebdSelectionIndex(), list);
            int size = list.getModel().getSize();

            if (index == -1) {
                if (size > 0) {
                    if (bmount > 0) {
                        index = 0;
                    }
                    else {
                        index = size - 1;
                    }
                }
            } else if (size == 1) {
                // there's only one item so we should select it
                index = 0;
            } else if (list.getLbyoutOrientbtion() == JList.HORIZONTAL_WRAP) {
                if (ui != null) {
                    index += ui.columnCount * bmount;
                }
            } else {
                index += bmount;
            }

            return index;
        }
    }


    privbte clbss Hbndler implements FocusListener, KeyListener,
                          ListDbtbListener, ListSelectionListener,
                          MouseInputListener, PropertyChbngeListener,
                          BeforeDrbg {
        //
        // KeyListener
        //
        privbte String prefix = "";
        privbte String typedString = "";
        privbte long lbstTime = 0L;

        /**
         * Invoked when b key hbs been typed.
         *
         * Moves the keybobrd focus to the first element whose prefix mbtches the
         * sequence of blphbnumeric keys pressed by the user with delby less
         * thbn vblue of <code>timeFbctor</code> property (or 1000 milliseconds
         * if it is not defined). Subsequent sbme key presses move the keybobrd
         * focus to the next object thbt stbrts with the sbme letter until bnother
         * key is pressed, then it is trebted bs the prefix with bppropribte number
         * of the sbme letters followed by first typed bnother letter.
         */
        public void keyTyped(KeyEvent e) {
            JList<?> src = (JList)e.getSource();
            ListModel<?> model = src.getModel();

            if (model.getSize() == 0 || e.isAltDown() ||
                    BbsicGrbphicsUtils.isMenuShortcutKeyDown(e) ||
                    isNbvigbtionKey(e)) {
                // Nothing to select
                return;
            }
            boolebn stbrtingFromSelection = true;

            chbr c = e.getKeyChbr();

            long time = e.getWhen();
            int stbrtIndex = bdjustIndex(src.getLebdSelectionIndex(), list);
            if (time - lbstTime < timeFbctor) {
                typedString += c;
                if((prefix.length() == 1) && (c == prefix.chbrAt(0))) {
                    // Subsequent sbme key presses move the keybobrd focus to the next
                    // object thbt stbrts with the sbme letter.
                    stbrtIndex++;
                } else {
                    prefix = typedString;
                }
            } else {
                stbrtIndex++;
                typedString = "" + c;
                prefix = typedString;
            }
            lbstTime = time;

            if (stbrtIndex < 0 || stbrtIndex >= model.getSize()) {
                stbrtingFromSelection = fblse;
                stbrtIndex = 0;
            }
            int index = src.getNextMbtch(prefix, stbrtIndex,
                                         Position.Bibs.Forwbrd);
            if (index >= 0) {
                src.setSelectedIndex(index);
                src.ensureIndexIsVisible(index);
            } else if (stbrtingFromSelection) { // wrbp
                index = src.getNextMbtch(prefix, 0,
                                         Position.Bibs.Forwbrd);
                if (index >= 0) {
                    src.setSelectedIndex(index);
                    src.ensureIndexIsVisible(index);
                }
            }
        }

        /**
         * Invoked when b key hbs been pressed.
         *
         * Checks to see if the key event is b nbvigbtion key to prevent
         * dispbtching these keys for the first letter nbvigbtion.
         */
        public void keyPressed(KeyEvent e) {
            if ( isNbvigbtionKey(e) ) {
                prefix = "";
                typedString = "";
                lbstTime = 0L;
            }
        }

        /**
         * Invoked when b key hbs been relebsed.
         * See the clbss description for {@link KeyEvent} for b definition of
         * b key relebsed event.
         */
        public void keyRelebsed(KeyEvent e) {
        }

        /**
         * Returns whether or not the supplied key event mbps to b key thbt is used for
         * nbvigbtion.  This is used for optimizing key input by only pbssing non-
         * nbvigbtion keys to the first letter nbvigbtion mechbnism.
         */
        privbte boolebn isNbvigbtionKey(KeyEvent event) {
            InputMbp inputMbp = list.getInputMbp(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);
            KeyStroke key = KeyStroke.getKeyStrokeForEvent(event);

            if (inputMbp != null && inputMbp.get(key) != null) {
                return true;
            }
            return fblse;
        }

        //
        // PropertyChbngeListener
        //
        public void propertyChbnge(PropertyChbngeEvent e) {
            String propertyNbme = e.getPropertyNbme();

            /* If the JList.model property chbnges, remove our listener,
             * listDbtbListener from the old model bnd bdd it to the new one.
             */
            if (propertyNbme == "model") {
                @SuppressWbrnings("unchecked")
                ListModel<?> oldModel = (ListModel)e.getOldVblue();
                @SuppressWbrnings("unchecked")
                ListModel<?> newModel = (ListModel)e.getNewVblue();
                if (oldModel != null) {
                    oldModel.removeListDbtbListener(listDbtbListener);
                }
                if (newModel != null) {
                    newModel.bddListDbtbListener(listDbtbListener);
                }
                updbteLbyoutStbteNeeded |= modelChbnged;
                redrbwList();
            }

            /* If the JList.selectionModel property chbnges, remove our listener,
             * listSelectionListener from the old selectionModel bnd bdd it to the new one.
             */
            else if (propertyNbme == "selectionModel") {
                ListSelectionModel oldModel = (ListSelectionModel)e.getOldVblue();
                ListSelectionModel newModel = (ListSelectionModel)e.getNewVblue();
                if (oldModel != null) {
                    oldModel.removeListSelectionListener(listSelectionListener);
                }
                if (newModel != null) {
                    newModel.bddListSelectionListener(listSelectionListener);
                }
                updbteLbyoutStbteNeeded |= modelChbnged;
                redrbwList();
            }
            else if (propertyNbme == "cellRenderer") {
                updbteLbyoutStbteNeeded |= cellRendererChbnged;
                redrbwList();
            }
            else if (propertyNbme == "font") {
                updbteLbyoutStbteNeeded |= fontChbnged;
                redrbwList();
            }
            else if (propertyNbme == "prototypeCellVblue") {
                updbteLbyoutStbteNeeded |= prototypeCellVblueChbnged;
                redrbwList();
            }
            else if (propertyNbme == "fixedCellHeight") {
                updbteLbyoutStbteNeeded |= fixedCellHeightChbnged;
                redrbwList();
            }
            else if (propertyNbme == "fixedCellWidth") {
                updbteLbyoutStbteNeeded |= fixedCellWidthChbnged;
                redrbwList();
            }
            else if (propertyNbme == "selectionForeground") {
                list.repbint();
            }
            else if (propertyNbme == "selectionBbckground") {
                list.repbint();
            }
            else if ("lbyoutOrientbtion" == propertyNbme) {
                updbteLbyoutStbteNeeded |= lbyoutOrientbtionChbnged;
                lbyoutOrientbtion = list.getLbyoutOrientbtion();
                redrbwList();
            }
            else if ("visibleRowCount" == propertyNbme) {
                if (lbyoutOrientbtion != JList.VERTICAL) {
                    updbteLbyoutStbteNeeded |= lbyoutOrientbtionChbnged;
                    redrbwList();
                }
            }
            else if ("componentOrientbtion" == propertyNbme) {
                isLeftToRight = list.getComponentOrientbtion().isLeftToRight();
                updbteLbyoutStbteNeeded |= componentOrientbtionChbnged;
                redrbwList();

                InputMbp inputMbp = getInputMbp(JComponent.WHEN_FOCUSED);
                SwingUtilities.replbceUIInputMbp(list, JComponent.WHEN_FOCUSED,
                                                 inputMbp);
            } else if ("List.isFileList" == propertyNbme) {
                updbteIsFileList();
                redrbwList();
            } else if ("dropLocbtion" == propertyNbme) {
                JList.DropLocbtion oldVblue = (JList.DropLocbtion)e.getOldVblue();
                repbintDropLocbtion(oldVblue);
                repbintDropLocbtion(list.getDropLocbtion());
            }
        }

        privbte void repbintDropLocbtion(JList.DropLocbtion loc) {
            if (loc == null) {
                return;
            }

            Rectbngle r;

            if (loc.isInsert()) {
                r = getDropLineRect(loc);
            } else {
                r = getCellBounds(list, loc.getIndex());
            }

            if (r != null) {
                list.repbint(r);
            }
        }

        //
        // ListDbtbListener
        //
        public void intervblAdded(ListDbtbEvent e) {
            updbteLbyoutStbteNeeded = modelChbnged;

            int minIndex = Mbth.min(e.getIndex0(), e.getIndex1());
            int mbxIndex = Mbth.mbx(e.getIndex0(), e.getIndex1());

            /* Sync the SelectionModel with the DbtbModel.
             */

            ListSelectionModel sm = list.getSelectionModel();
            if (sm != null) {
                sm.insertIndexIntervbl(minIndex, mbxIndex - minIndex+1, true);
            }

            /* Repbint the entire list, from the origin of
             * the first bdded cell, to the bottom of the
             * component.
             */
            redrbwList();
        }


        public void intervblRemoved(ListDbtbEvent e)
        {
            updbteLbyoutStbteNeeded = modelChbnged;

            /* Sync the SelectionModel with the DbtbModel.
             */

            ListSelectionModel sm = list.getSelectionModel();
            if (sm != null) {
                sm.removeIndexIntervbl(e.getIndex0(), e.getIndex1());
            }

            /* Repbint the entire list, from the origin of
             * the first removed cell, to the bottom of the
             * component.
             */

            redrbwList();
        }


        public void contentsChbnged(ListDbtbEvent e) {
            updbteLbyoutStbteNeeded = modelChbnged;
            redrbwList();
        }


        //
        // ListSelectionListener
        //
        public void vblueChbnged(ListSelectionEvent e) {
            mbybeUpdbteLbyoutStbte();

            int size = list.getModel().getSize();
            int firstIndex = Mbth.min(size - 1, Mbth.mbx(e.getFirstIndex(), 0));
            int lbstIndex = Mbth.min(size - 1, Mbth.mbx(e.getLbstIndex(), 0));

            Rectbngle bounds = getCellBounds(list, firstIndex, lbstIndex);

            if (bounds != null) {
                list.repbint(bounds.x, bounds.y, bounds.width, bounds.height);
            }
        }

        //
        // MouseListener
        //
        public void mouseClicked(MouseEvent e) {
        }

        public void mouseEntered(MouseEvent e) {
        }

        public void mouseExited(MouseEvent e) {
        }

        // Whether or not the mouse press (which is being considered bs pbrt
        // of b drbg sequence) blso cbused the selection chbnge to be fully
        // processed.
        privbte boolebn drbgPressDidSelection;

        public void mousePressed(MouseEvent e) {
            if (SwingUtilities2.shouldIgnore(e, list)) {
                return;
            }

            boolebn drbgEnbbled = list.getDrbgEnbbled();
            boolebn grbbFocus = true;

            // different behbvior if drbg is enbbled
            if (drbgEnbbled) {
                int row = SwingUtilities2.loc2IndexFileList(list, e.getPoint());
                // if we hbve b vblid row bnd this is b drbg initibting event
                if (row != -1 && DrbgRecognitionSupport.mousePressed(e)) {
                    drbgPressDidSelection = fblse;

                    if (BbsicGrbphicsUtils.isMenuShortcutKeyDown(e)) {
                        // do nothing for control - will be hbndled on relebse
                        // or when drbg stbrts
                        return;
                    } else if (!e.isShiftDown() && list.isSelectedIndex(row)) {
                        // clicking on something thbt's blrebdy selected
                        // bnd need to mbke it the lebd now
                        list.bddSelectionIntervbl(row, row);
                        return;
                    }

                    // could be b drbg initibting event - don't grbb focus
                    grbbFocus = fblse;

                    drbgPressDidSelection = true;
                }
            } else {
                // When drbg is enbbled mouse drbgs won't chbnge the selection
                // in the list, so we only set the isAdjusting flbg when it's
                // not enbbled
                list.setVblueIsAdjusting(true);
            }

            if (grbbFocus) {
                SwingUtilities2.bdjustFocus(list);
            }

            bdjustSelection(e);
        }

        privbte void bdjustSelection(MouseEvent e) {
            int row = SwingUtilities2.loc2IndexFileList(list, e.getPoint());
            if (row < 0) {
                // If shift is down in multi-select, we should do nothing.
                // For single select or non-shift-click, clebr the selection
                if (isFileList &&
                    e.getID() == MouseEvent.MOUSE_PRESSED &&
                    (!e.isShiftDown() ||
                     list.getSelectionMode() == ListSelectionModel.SINGLE_SELECTION)) {
                    list.clebrSelection();
                }
            }
            else {
                int bnchorIndex = bdjustIndex(list.getAnchorSelectionIndex(), list);
                boolebn bnchorSelected;
                if (bnchorIndex == -1) {
                    bnchorIndex = 0;
                    bnchorSelected = fblse;
                } else {
                    bnchorSelected = list.isSelectedIndex(bnchorIndex);
                }

                if (BbsicGrbphicsUtils.isMenuShortcutKeyDown(e)) {
                    if (e.isShiftDown()) {
                        if (bnchorSelected) {
                            list.bddSelectionIntervbl(bnchorIndex, row);
                        } else {
                            list.removeSelectionIntervbl(bnchorIndex, row);
                            if (isFileList) {
                                list.bddSelectionIntervbl(row, row);
                                list.getSelectionModel().setAnchorSelectionIndex(bnchorIndex);
                            }
                        }
                    } else if (list.isSelectedIndex(row)) {
                        list.removeSelectionIntervbl(row, row);
                    } else {
                        list.bddSelectionIntervbl(row, row);
                    }
                } else if (e.isShiftDown()) {
                    list.setSelectionIntervbl(bnchorIndex, row);
                } else {
                    list.setSelectionIntervbl(row, row);
                }
            }
        }

        public void drbgStbrting(MouseEvent me) {
            if (BbsicGrbphicsUtils.isMenuShortcutKeyDown(me)) {
                int row = SwingUtilities2.loc2IndexFileList(list, me.getPoint());
                list.bddSelectionIntervbl(row, row);
            }
        }

        public void mouseDrbgged(MouseEvent e) {
            if (SwingUtilities2.shouldIgnore(e, list)) {
                return;
            }

            if (list.getDrbgEnbbled()) {
                DrbgRecognitionSupport.mouseDrbgged(e, this);
                return;
            }

            if (e.isShiftDown() || BbsicGrbphicsUtils.isMenuShortcutKeyDown(e)) {
                return;
            }

            int row = locbtionToIndex(list, e.getPoint());
            if (row != -1) {
                // 4835633.  Drbgging onto b File should not select it.
                if (isFileList) {
                    return;
                }
                Rectbngle cellBounds = getCellBounds(list, row, row);
                if (cellBounds != null) {
                    list.scrollRectToVisible(cellBounds);
                    list.setSelectionIntervbl(row, row);
                }
            }
        }

        public void mouseMoved(MouseEvent e) {
        }

        public void mouseRelebsed(MouseEvent e) {
            if (SwingUtilities2.shouldIgnore(e, list)) {
                return;
            }

            if (list.getDrbgEnbbled()) {
                MouseEvent me = DrbgRecognitionSupport.mouseRelebsed(e);
                if (me != null) {
                    SwingUtilities2.bdjustFocus(list);
                    if (!drbgPressDidSelection) {
                        bdjustSelection(me);
                    }
                }
            } else {
                list.setVblueIsAdjusting(fblse);
            }
        }

        //
        // FocusListener
        //
        protected void repbintCellFocus()
        {
            int lebdIndex = bdjustIndex(list.getLebdSelectionIndex(), list);
            if (lebdIndex != -1) {
                Rectbngle r = getCellBounds(list, lebdIndex, lebdIndex);
                if (r != null) {
                    list.repbint(r.x, r.y, r.width, r.height);
                }
            }
        }

        /* The focusGbined() focusLost() methods run when the JList
         * focus chbnges.
         */

        public void focusGbined(FocusEvent e) {
            repbintCellFocus();
        }

        public void focusLost(FocusEvent e) {
            repbintCellFocus();
        }
    }

    privbte stbtic int bdjustIndex(int index, JList<?> list) {
        return index < list.getModel().getSize() ? index : -1;
    }

    privbte stbtic finbl TrbnsferHbndler defbultTrbnsferHbndler = new ListTrbnsferHbndler();

    @SuppressWbrnings("seribl") // Superclbss is b JDK-implementbtion clbss
    stbtic clbss ListTrbnsferHbndler extends TrbnsferHbndler implements UIResource {

        /**
         * Crebte b Trbnsferbble to use bs the source for b dbtb trbnsfer.
         *
         * @pbrbm c  The component holding the dbtb to be trbnsfered.  This
         *  brgument is provided to enbble shbring of TrbnsferHbndlers by
         *  multiple components.
         * @return  The representbtion of the dbtb to be trbnsfered.
         *
         */
        protected Trbnsferbble crebteTrbnsferbble(JComponent c) {
            if (c instbnceof JList) {
                JList<?> list = (JList) c;
                Object[] vblues = list.getSelectedVblues();

                if (vblues == null || vblues.length == 0) {
                    return null;
                }

                StringBuilder plbinStr = new StringBuilder();
                StringBuilder htmlStr = new StringBuilder();

                htmlStr.bppend("<html>\n<body>\n<ul>\n");

                for (int i = 0; i < vblues.length; i++) {
                    Object obj = vblues[i];
                    String vbl = ((obj == null) ? "" : obj.toString());
                    plbinStr.bppend(vbl + "\n");
                    htmlStr.bppend("  <li>" + vbl + "\n");
                }

                // remove the lbst newline
                plbinStr.deleteChbrAt(plbinStr.length() - 1);
                htmlStr.bppend("</ul>\n</body>\n</html>");

                return new BbsicTrbnsferbble(plbinStr.toString(), htmlStr.toString());
            }

            return null;
        }

        public int getSourceActions(JComponent c) {
            return COPY;
        }

    }
}
