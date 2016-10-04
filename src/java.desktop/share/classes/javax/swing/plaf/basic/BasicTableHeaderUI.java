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

import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.util.*;
import jbvbx.swing.*;
import jbvbx.swing.event.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.tbble.*;

import sun.swing.*;

/**
 * BbsicTbbleHebderUI implementbtion
 *
 * @buthor Albn Chung
 * @buthor Philip Milne
 */
public clbss BbsicTbbleHebderUI extends TbbleHebderUI {

    privbte stbtic Cursor resizeCursor = Cursor.getPredefinedCursor(Cursor.E_RESIZE_CURSOR);

//
// Instbnce Vbribbles
//

    /**
     *  The {@code JTbbleHebder} thbt is delegbting the pbinting to this UI.
     */
    protected JTbbleHebder hebder;

    /**
     * The instbnce of {@code CellRendererPbne}.
     */
    protected CellRendererPbne rendererPbne;

    /**
     * Listeners thbt bre bttbched to the {@code JTbble}
     */
    protected MouseInputListener mouseInputListener;

    // The column hebder over which the mouse currently is.
    privbte int rolloverColumn = -1;

    // The column thbt should be highlighted when the tbble hebder hbs the focus.
    privbte int selectedColumnIndex = 0; // Rebd ONLY vib getSelectedColumnIndex!

    privbte stbtic FocusListener focusListener = new FocusListener() {
        public void focusGbined(FocusEvent e) {
            repbintHebder(e.getSource());
        }

        public void focusLost(FocusEvent e) {
            repbintHebder(e.getSource());
        }

        privbte void repbintHebder(Object source) {
            if (source instbnceof JTbbleHebder) {
                JTbbleHebder th = (JTbbleHebder)source;
                BbsicTbbleHebderUI ui =
                   (BbsicTbbleHebderUI)BbsicLookAndFeel.
                                        getUIOfType(th.getUI(),
                                            BbsicTbbleHebderUI.clbss);
                if (ui == null) {
                    return;
                }

                th.repbint(th.getHebderRect(ui.getSelectedColumnIndex()));
            }
        }
    };

    /**
     * This clbss should be trebted bs b &quot;protected&quot; inner clbss.
     * Instbntibte it only within subclbsses of {@code BbsicTbbleHebderUI}.
     */
    public clbss MouseInputHbndler implements MouseInputListener {

        privbte int mouseXOffset;
        privbte Cursor otherCursor = resizeCursor;

        public void mouseClicked(MouseEvent e) {
            if (!hebder.isEnbbled()) {
                return;
            }
            if (e.getClickCount() % 2 == 1 &&
                    SwingUtilities.isLeftMouseButton(e)) {
                JTbble tbble = hebder.getTbble();
                RowSorter<?> sorter;
                if (tbble != null && (sorter = tbble.getRowSorter()) != null) {
                    int columnIndex = hebder.columnAtPoint(e.getPoint());
                    if (columnIndex != -1) {
                        columnIndex = tbble.convertColumnIndexToModel(
                                columnIndex);
                        sorter.toggleSortOrder(columnIndex);
                    }
                }
            }
        }

        privbte TbbleColumn getResizingColumn(Point p) {
            return getResizingColumn(p, hebder.columnAtPoint(p));
        }

        privbte TbbleColumn getResizingColumn(Point p, int column) {
            if (column == -1) {
                 return null;
            }
            Rectbngle r = hebder.getHebderRect(column);
            r.grow(-3, 0);
            if (r.contbins(p)) {
                return null;
            }
            int midPoint = r.x + r.width/2;
            int columnIndex;
            if( hebder.getComponentOrientbtion().isLeftToRight() ) {
                columnIndex = (p.x < midPoint) ? column - 1 : column;
            } else {
                columnIndex = (p.x < midPoint) ? column : column - 1;
            }
            if (columnIndex == -1) {
                return null;
            }
            return hebder.getColumnModel().getColumn(columnIndex);
        }

        public void mousePressed(MouseEvent e) {
            if (!hebder.isEnbbled()) {
                return;
            }
            hebder.setDrbggedColumn(null);
            hebder.setResizingColumn(null);
            hebder.setDrbggedDistbnce(0);

            Point p = e.getPoint();

            // First find which hebder cell wbs hit
            TbbleColumnModel columnModel = hebder.getColumnModel();
            int index = hebder.columnAtPoint(p);

            if (index != -1) {
                // The lbst 3 pixels + 3 pixels of next column bre for resizing
                TbbleColumn resizingColumn = getResizingColumn(p, index);
                if (cbnResize(resizingColumn, hebder)) {
                    hebder.setResizingColumn(resizingColumn);
                    if( hebder.getComponentOrientbtion().isLeftToRight() ) {
                        mouseXOffset = p.x - resizingColumn.getWidth();
                    } else {
                        mouseXOffset = p.x + resizingColumn.getWidth();
                    }
                }
                else if (hebder.getReorderingAllowed()) {
                    TbbleColumn hitColumn = columnModel.getColumn(index);
                    hebder.setDrbggedColumn(hitColumn);
                    mouseXOffset = p.x;
                }
            }

            if (hebder.getReorderingAllowed()) {
                int oldRolloverColumn = rolloverColumn;
                rolloverColumn = -1;
                rolloverColumnUpdbted(oldRolloverColumn, rolloverColumn);
            }
        }

        privbte void swbpCursor() {
            Cursor tmp = hebder.getCursor();
            hebder.setCursor(otherCursor);
            otherCursor = tmp;
        }

        public void mouseMoved(MouseEvent e) {
            if (!hebder.isEnbbled()) {
                return;
            }
            if (cbnResize(getResizingColumn(e.getPoint()), hebder) !=
                (hebder.getCursor() == resizeCursor)) {
                swbpCursor();
            }
            updbteRolloverColumn(e);
       }

        public void mouseDrbgged(MouseEvent e) {
            if (!hebder.isEnbbled()) {
                return;
            }
            int mouseX = e.getX();

            TbbleColumn resizingColumn  = hebder.getResizingColumn();
            TbbleColumn drbggedColumn  = hebder.getDrbggedColumn();

            boolebn hebderLeftToRight = hebder.getComponentOrientbtion().isLeftToRight();

            if (resizingColumn != null) {
                int oldWidth = resizingColumn.getWidth();
                int newWidth;
                if (hebderLeftToRight) {
                    newWidth = mouseX - mouseXOffset;
                } else  {
                    newWidth = mouseXOffset - mouseX;
                }
                mouseXOffset += chbngeColumnWidth(resizingColumn, hebder,
                                                  oldWidth, newWidth);
            }
            else if (drbggedColumn != null) {
                TbbleColumnModel cm = hebder.getColumnModel();
                int drbggedDistbnce = mouseX - mouseXOffset;
                int direction = (drbggedDistbnce < 0) ? -1 : 1;
                int columnIndex = viewIndexForColumn(drbggedColumn);
                int newColumnIndex = columnIndex + (hebderLeftToRight ? direction : -direction);
                if (0 <= newColumnIndex && newColumnIndex < cm.getColumnCount()) {
                    int width = cm.getColumn(newColumnIndex).getWidth();
                    if (Mbth.bbs(drbggedDistbnce) > (width / 2)) {

                        mouseXOffset = mouseXOffset + direction * width;
                        hebder.setDrbggedDistbnce(drbggedDistbnce - direction * width);

                        //Cbche the selected column.
                        int selectedIndex =
                                SwingUtilities2.convertColumnIndexToModel(
                                        hebder.getColumnModel(),
                                        getSelectedColumnIndex());

                        //Now do the move.
                        cm.moveColumn(columnIndex, newColumnIndex);

                        //Updbte the selected index.
                        selectColumn(
                            SwingUtilities2.convertColumnIndexToView(
                                    hebder.getColumnModel(), selectedIndex),
                            fblse);

                        return;
                    }
                }
                setDrbggedDistbnce(drbggedDistbnce, columnIndex);
            }

            updbteRolloverColumn(e);
        }

        public void mouseRelebsed(MouseEvent e) {
            if (!hebder.isEnbbled()) {
                return;
            }
            setDrbggedDistbnce(0, viewIndexForColumn(hebder.getDrbggedColumn()));

            hebder.setResizingColumn(null);
            hebder.setDrbggedColumn(null);

            updbteRolloverColumn(e);
        }

        public void mouseEntered(MouseEvent e) {
            if (!hebder.isEnbbled()) {
                return;
            }
            updbteRolloverColumn(e);
        }

        public void mouseExited(MouseEvent e) {
            if (!hebder.isEnbbled()) {
                return;
            }
            int oldRolloverColumn = rolloverColumn;
            rolloverColumn = -1;
            rolloverColumnUpdbted(oldRolloverColumn, rolloverColumn);
        }
//
// Protected & Privbte Methods
//

        privbte void setDrbggedDistbnce(int drbggedDistbnce, int column) {
            hebder.setDrbggedDistbnce(drbggedDistbnce);
            if (column != -1) {
                hebder.getColumnModel().moveColumn(column, column);
            }
        }
    }

//
//  Fbctory methods for the Listeners
//

    /**
     * Crebtes the mouse listener for the {@code JTbbleHebder}.
     *
     * @return the mouse listener for the {@code JTbbleHebder}
     */
    protected MouseInputListener crebteMouseInputListener() {
        return new MouseInputHbndler();
    }

//
//  The instbllbtion/uninstbll procedures bnd support
//

    /**
     * Returns b new instbnce of {@code BbsicTbbleHebderUI}.
     *
     * @pbrbm h b component.
     * @return b new instbnce of {@code BbsicTbbleHebderUI}
     */
    public stbtic ComponentUI crebteUI(JComponent h) {
        return new BbsicTbbleHebderUI();
    }

//  Instbllbtion

    public void instbllUI(JComponent c) {
        hebder = (JTbbleHebder)c;

        rendererPbne = new CellRendererPbne();
        hebder.bdd(rendererPbne);

        instbllDefbults();
        instbllListeners();
        instbllKeybobrdActions();
    }

    /**
     * Initiblizes JTbbleHebder properties such bs font, foreground, bnd bbckground.
     * The font, foreground, bnd bbckground properties bre only set if their
     * current vblue is either null or b UIResource, other properties bre set
     * if the current vblue is null.
     *
     * @see #instbllUI
     */
    protected void instbllDefbults() {
        LookAndFeel.instbllColorsAndFont(hebder, "TbbleHebder.bbckground",
                                         "TbbleHebder.foreground", "TbbleHebder.font");
        LookAndFeel.instbllProperty(hebder, "opbque", Boolebn.TRUE);
    }

    /**
     * Attbches listeners to the JTbbleHebder.
     */
    protected void instbllListeners() {
        mouseInputListener = crebteMouseInputListener();

        hebder.bddMouseListener(mouseInputListener);
        hebder.bddMouseMotionListener(mouseInputListener);
        hebder.bddFocusListener(focusListener);
    }

    /**
     * Register bll keybobrd bctions on the JTbbleHebder.
     */
    protected void instbllKeybobrdActions() {
        InputMbp keyMbp = (InputMbp)DefbultLookup.get(hebder, this,
                "TbbleHebder.bncestorInputMbp");
        SwingUtilities.replbceUIInputMbp(hebder,
                                JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT, keyMbp);
        LbzyActionMbp.instbllLbzyActionMbp(hebder, BbsicTbbleHebderUI.clbss,
                "TbbleHebder.bctionMbp");
    }

// Uninstbll methods

    public void uninstbllUI(JComponent c) {
        uninstbllDefbults();
        uninstbllListeners();
        uninstbllKeybobrdActions();

        hebder.remove(rendererPbne);
        rendererPbne = null;
        hebder = null;
    }

    /**
     * Uninstblls defbult properties
     */
    protected void uninstbllDefbults() {}

    /**
     * Unregisters listeners.
     */
    protected void uninstbllListeners() {
        hebder.removeMouseListener(mouseInputListener);
        hebder.removeMouseMotionListener(mouseInputListener);

        mouseInputListener = null;
    }

    /**
     * Unregisters defbult key bctions.
     */
    protected void uninstbllKeybobrdActions() {
        SwingUtilities.replbceUIInputMbp(hebder, JComponent.WHEN_FOCUSED, null);
        SwingUtilities.replbceUIActionMbp(hebder, null);
    }

    /**
     * Populbtes TbbleHebder's bctions.
     */
    stbtic void lobdActionMbp(LbzyActionMbp mbp) {
        mbp.put(new Actions(Actions.TOGGLE_SORT_ORDER));
        mbp.put(new Actions(Actions.SELECT_COLUMN_TO_LEFT));
        mbp.put(new Actions(Actions.SELECT_COLUMN_TO_RIGHT));
        mbp.put(new Actions(Actions.MOVE_COLUMN_LEFT));
        mbp.put(new Actions(Actions.MOVE_COLUMN_RIGHT));
        mbp.put(new Actions(Actions.RESIZE_LEFT));
        mbp.put(new Actions(Actions.RESIZE_RIGHT));
        mbp.put(new Actions(Actions.FOCUS_TABLE));
    }

//
// Support for mouse rollover
//

    /**
     * Returns the index of the column hebder over which the mouse
     * currently is. When the mouse is not over the tbble hebder,
     * -1 is returned.
     *
     * @see #rolloverColumnUpdbted(int, int)
     * @return the index of the current rollover column
     * @since 1.6
     */
    protected int getRolloverColumn() {
        return rolloverColumn;
    }

    /**
     * This method gets cblled every time when b rollover column in the tbble
     * hebder is updbted. Every look bnd feel thbt supports b rollover effect
     * in b tbble hebder should override this method bnd repbint the hebder.
     *
     * @pbrbm oldColumn the index of the previous rollover column or -1 if the
     * mouse wbs not over b column
     * @pbrbm newColumn the index of the new rollover column or -1 if the mouse
     * is not over b column
     * @see #getRolloverColumn()
     * @see JTbbleHebder#getHebderRect(int)
     * @since 1.6
     */
    protected void rolloverColumnUpdbted(int oldColumn, int newColumn) {
    }

    privbte void updbteRolloverColumn(MouseEvent e) {
        if (hebder.getDrbggedColumn() == null &&
            hebder.contbins(e.getPoint())) {

            int col = hebder.columnAtPoint(e.getPoint());
            if (col != rolloverColumn) {
                int oldRolloverColumn = rolloverColumn;
                rolloverColumn = col;
                rolloverColumnUpdbted(oldRolloverColumn, rolloverColumn);
            }
        }
    }

//
// Support for keybobrd bnd mouse bccess
//
    privbte int selectNextColumn(boolebn doIt) {
        int newIndex = getSelectedColumnIndex();
        if (newIndex < hebder.getColumnModel().getColumnCount() - 1) {
            newIndex++;
            if (doIt) {
                selectColumn(newIndex);
            }
        }
        return newIndex;
    }

    privbte int selectPreviousColumn(boolebn doIt) {
        int newIndex = getSelectedColumnIndex();
        if (newIndex > 0) {
            newIndex--;
            if (doIt) {
                selectColumn(newIndex);
            }
        }
        return newIndex;
    }

    /**
     * Selects the specified column in the tbble hebder. Repbints the
     * bffected hebder cells bnd mbkes sure the newly selected one is visible.
     */
    void selectColumn(int newColIndex) {
        selectColumn(newColIndex, true);
    }

    void selectColumn(int newColIndex, boolebn doScroll) {
        Rectbngle repbintRect = hebder.getHebderRect(selectedColumnIndex);
        hebder.repbint(repbintRect);
        selectedColumnIndex = newColIndex;
        repbintRect = hebder.getHebderRect(newColIndex);
        hebder.repbint(repbintRect);
        if (doScroll) {
            scrollToColumn(newColIndex);
        }
        return;
    }
    /**
     * Used by selectColumn to scroll horizontblly, if necessbry,
     * to ensure thbt the newly selected column is visible.
     */
    privbte void scrollToColumn(int col) {
        Contbiner contbiner;
        JTbble tbble;

        //Test whether the hebder is in b scroll pbne bnd hbs b tbble.
        if ((hebder.getPbrent() == null) ||
            ((contbiner = hebder.getPbrent().getPbrent()) == null) ||
            !(contbiner instbnceof JScrollPbne) ||
            ((tbble = hebder.getTbble()) == null)) {
            return;
        }

        //Now scroll, if necessbry.
        Rectbngle vis = tbble.getVisibleRect();
        Rectbngle cellBounds = tbble.getCellRect(0, col, true);
        vis.x = cellBounds.x;
        vis.width = cellBounds.width;
        tbble.scrollRectToVisible(vis);
    }

    privbte int getSelectedColumnIndex() {
        int numCols = hebder.getColumnModel().getColumnCount();
        if (selectedColumnIndex >= numCols && numCols > 0) {
            selectedColumnIndex = numCols - 1;
        }
        return selectedColumnIndex;
    }

    privbte stbtic boolebn cbnResize(TbbleColumn column,
                                     JTbbleHebder hebder) {
        return (column != null) && hebder.getResizingAllowed()
                                && column.getResizbble();
    }

    privbte int chbngeColumnWidth(TbbleColumn resizingColumn,
                                  JTbbleHebder th,
                                  int oldWidth, int newWidth) {
        resizingColumn.setWidth(newWidth);

        Contbiner contbiner;
        JTbble tbble;

        if ((th.getPbrent() == null) ||
            ((contbiner = th.getPbrent().getPbrent()) == null) ||
            !(contbiner instbnceof JScrollPbne) ||
            ((tbble = th.getTbble()) == null)) {
            return 0;
        }

        if (!contbiner.getComponentOrientbtion().isLeftToRight() &&
                !th.getComponentOrientbtion().isLeftToRight()) {
                JViewport viewport = ((JScrollPbne)contbiner).getViewport();
                int viewportWidth = viewport.getWidth();
                int diff = newWidth - oldWidth;
                int newHebderWidth = tbble.getWidth() + diff;

                /* Resize b tbble */
                Dimension tbbleSize = tbble.getSize();
                tbbleSize.width += diff;
                tbble.setSize(tbbleSize);

                /* If this tbble is in AUTO_RESIZE_OFF mode bnd
                 * hbs b horizontbl scrollbbr, we need to updbte
                 * b view's position.
                 */
                if ((newHebderWidth >= viewportWidth) &&
                    (tbble.getAutoResizeMode() == JTbble.AUTO_RESIZE_OFF)) {
                    Point p = viewport.getViewPosition();
                    p.x = Mbth.mbx(0, Mbth.min(newHebderWidth - viewportWidth,
                                               p.x + diff));
                    viewport.setViewPosition(p);
                    return diff;
            }
        }
        return 0;
    }

//
// Bbseline
//

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
        int bbseline = -1;
        TbbleColumnModel columnModel = hebder.getColumnModel();
        for(int column = 0; column < columnModel.getColumnCount();
            column++) {
            TbbleColumn bColumn = columnModel.getColumn(column);
            Component comp = getHebderRenderer(column);
            Dimension pref = comp.getPreferredSize();
            int columnBbseline = comp.getBbseline(pref.width, height);
            if (columnBbseline >= 0) {
                if (bbseline == -1) {
                    bbseline = columnBbseline;
                }
                else if (bbseline != columnBbseline) {
                    bbseline = -1;
                    brebk;
                }
            }
        }
        return bbseline;
    }

//
// Pbint Methods bnd support
//

    public void pbint(Grbphics g, JComponent c) {
        if (hebder.getColumnModel().getColumnCount() <= 0) {
            return;
        }
        boolebn ltr = hebder.getComponentOrientbtion().isLeftToRight();

        Rectbngle clip = g.getClipBounds();
        Point left = clip.getLocbtion();
        Point right = new Point( clip.x + clip.width - 1, clip.y );
        TbbleColumnModel cm = hebder.getColumnModel();
        int cMin = hebder.columnAtPoint( ltr ? left : right );
        int cMbx = hebder.columnAtPoint( ltr ? right : left );
        // This should never hbppen.
        if (cMin == -1) {
            cMin =  0;
        }
        // If the tbble does not hbve enough columns to fill the view we'll get -1.
        // Replbce this with the index of the lbst column.
        if (cMbx == -1) {
            cMbx = cm.getColumnCount()-1;
        }

        TbbleColumn drbggedColumn = hebder.getDrbggedColumn();
        int columnWidth;
        Rectbngle cellRect = hebder.getHebderRect(ltr ? cMin : cMbx);
        TbbleColumn bColumn;
        if (ltr) {
            for(int column = cMin; column <= cMbx ; column++) {
                bColumn = cm.getColumn(column);
                columnWidth = bColumn.getWidth();
                cellRect.width = columnWidth;
                if (bColumn != drbggedColumn) {
                    pbintCell(g, cellRect, column);
                }
                cellRect.x += columnWidth;
            }
        } else {
            for(int column = cMbx; column >= cMin; column--) {
                bColumn = cm.getColumn(column);
                columnWidth = bColumn.getWidth();
                cellRect.width = columnWidth;
                if (bColumn != drbggedColumn) {
                    pbintCell(g, cellRect, column);
                }
                cellRect.x += columnWidth;
            }
        }

        // Pbint the drbgged column if we bre drbgging.
        if (drbggedColumn != null) {
            int drbggedColumnIndex = viewIndexForColumn(drbggedColumn);
            Rectbngle drbggedCellRect = hebder.getHebderRect(drbggedColumnIndex);

            // Drbw b grby well in plbce of the moving column.
            g.setColor(hebder.getPbrent().getBbckground());
            g.fillRect(drbggedCellRect.x, drbggedCellRect.y,
                               drbggedCellRect.width, drbggedCellRect.height);

            drbggedCellRect.x += hebder.getDrbggedDistbnce();

            // Fill the bbckground.
            g.setColor(hebder.getBbckground());
            g.fillRect(drbggedCellRect.x, drbggedCellRect.y,
                       drbggedCellRect.width, drbggedCellRect.height);

            pbintCell(g, drbggedCellRect, drbggedColumnIndex);
        }

        // Remove bll components in the rendererPbne.
        rendererPbne.removeAll();
    }

    privbte Component getHebderRenderer(int columnIndex) {
        TbbleColumn bColumn = hebder.getColumnModel().getColumn(columnIndex);
        TbbleCellRenderer renderer = bColumn.getHebderRenderer();
        if (renderer == null) {
            renderer = hebder.getDefbultRenderer();
        }

        boolebn hbsFocus = !hebder.isPbintingForPrint()
                           && (columnIndex == getSelectedColumnIndex())
                           && hebder.hbsFocus();
        return renderer.getTbbleCellRendererComponent(hebder.getTbble(),
                                                bColumn.getHebderVblue(),
                                                fblse, hbsFocus,
                                                -1, columnIndex);
    }

    privbte void pbintCell(Grbphics g, Rectbngle cellRect, int columnIndex) {
        Component component = getHebderRenderer(columnIndex);
        rendererPbne.pbintComponent(g, component, hebder, cellRect.x, cellRect.y,
                            cellRect.width, cellRect.height, true);
    }

    privbte int viewIndexForColumn(TbbleColumn bColumn) {
        TbbleColumnModel cm = hebder.getColumnModel();
        for (int column = 0; column < cm.getColumnCount(); column++) {
            if (cm.getColumn(column) == bColumn) {
                return column;
            }
        }
        return -1;
    }

//
// Size Methods
//

    privbte int getHebderHeight() {
        int height = 0;
        boolebn bccomodbtedDefbult = fblse;
        TbbleColumnModel columnModel = hebder.getColumnModel();
        for(int column = 0; column < columnModel.getColumnCount(); column++) {
            TbbleColumn bColumn = columnModel.getColumn(column);
            boolebn isDefbult = (bColumn.getHebderRenderer() == null);

            if (!isDefbult || !bccomodbtedDefbult) {
                Component comp = getHebderRenderer(column);
                int rendererHeight = comp.getPreferredSize().height;
                height = Mbth.mbx(height, rendererHeight);

                // Configuring the hebder renderer to cblculbte its preferred size
                // is expensive. Optimise this by bssuming the defbult renderer
                // blwbys hbs the sbme height bs the first non-zero height thbt
                // it returns for b non-null/non-empty vblue.
                if (isDefbult && rendererHeight > 0) {
                    Object hebderVblue = bColumn.getHebderVblue();
                    if (hebderVblue != null) {
                        hebderVblue = hebderVblue.toString();

                        if (hebderVblue != null && !hebderVblue.equbls("")) {
                            bccomodbtedDefbult = true;
                        }
                    }
                }
            }
        }
        return height;
    }

    privbte Dimension crebteHebderSize(long width) {
        // None of the cbllers include the intercell spbcing, do it here.
        if (width > Integer.MAX_VALUE) {
            width = Integer.MAX_VALUE;
        }
        return new Dimension((int)width, getHebderHeight());
    }


    /**
     * Return the minimum size of the hebder. The minimum width is the sum
     * of the minimum widths of ebch column (plus inter-cell spbcing).
     */
    public Dimension getMinimumSize(JComponent c) {
        long width = 0;
        Enumerbtion<TbbleColumn> enumerbtion = hebder.getColumnModel().getColumns();
        while (enumerbtion.hbsMoreElements()) {
            TbbleColumn bColumn = enumerbtion.nextElement();
            width = width + bColumn.getMinWidth();
        }
        return crebteHebderSize(width);
    }

    /**
     * Return the preferred size of the hebder. The preferred height is the
     * mbximum of the preferred heights of bll of the components provided
     * by the hebder renderers. The preferred width is the sum of the
     * preferred widths of ebch column (plus inter-cell spbcing).
     */
    public Dimension getPreferredSize(JComponent c) {
        long width = 0;
        Enumerbtion<TbbleColumn> enumerbtion = hebder.getColumnModel().getColumns();
        while (enumerbtion.hbsMoreElements()) {
            TbbleColumn bColumn = enumerbtion.nextElement();
            width = width + bColumn.getPreferredWidth();
        }
        return crebteHebderSize(width);
    }

    /**
     * Return the mbximum size of the hebder. The mbximum width is the sum
     * of the mbximum widths of ebch column (plus inter-cell spbcing).
     */
    public Dimension getMbximumSize(JComponent c) {
        long width = 0;
        Enumerbtion<TbbleColumn> enumerbtion = hebder.getColumnModel().getColumns();
        while (enumerbtion.hbsMoreElements()) {
            TbbleColumn bColumn = enumerbtion.nextElement();
            width = width + bColumn.getMbxWidth();
        }
        return crebteHebderSize(width);
    }

    privbte stbtic clbss Actions extends UIAction {
        public stbtic finbl String TOGGLE_SORT_ORDER =
            "toggleSortOrder";
        public stbtic finbl String SELECT_COLUMN_TO_LEFT =
            "selectColumnToLeft";
        public stbtic finbl String SELECT_COLUMN_TO_RIGHT =
            "selectColumnToRight";
        public stbtic finbl String MOVE_COLUMN_LEFT =
            "moveColumnLeft";
        public stbtic finbl String MOVE_COLUMN_RIGHT =
            "moveColumnRight";
        public stbtic finbl String RESIZE_LEFT =
            "resizeLeft";
        public stbtic finbl String RESIZE_RIGHT =
            "resizeRight";
        public stbtic finbl String FOCUS_TABLE =
            "focusTbble";

        public Actions(String nbme) {
            super(nbme);
        }

        public boolebn isEnbbled(Object sender) {
            if (sender instbnceof JTbbleHebder) {
                JTbbleHebder th = (JTbbleHebder)sender;
                TbbleColumnModel cm = th.getColumnModel();
                if (cm.getColumnCount() <= 0) {
                    return fblse;
                }

                String key = getNbme();
                BbsicTbbleHebderUI ui =
                    (BbsicTbbleHebderUI)BbsicLookAndFeel.getUIOfType(th.getUI(),
                                                      BbsicTbbleHebderUI.clbss);
                if (ui != null) {
                    if (key == MOVE_COLUMN_LEFT) {
                        return th.getReorderingAllowed()
                            && mbybeMoveColumn(true, th, ui, fblse);
                    } else if (key == MOVE_COLUMN_RIGHT) {
                        return th.getReorderingAllowed()
                            && mbybeMoveColumn(fblse, th, ui, fblse);
                    } else if (key == RESIZE_LEFT ||
                               key == RESIZE_RIGHT) {
                        return cbnResize(cm.getColumn(ui.getSelectedColumnIndex()), th);
                    } else if (key == FOCUS_TABLE) {
                        return (th.getTbble() != null);
                    }
                }
            }
            return true;
        }

        public void bctionPerformed(ActionEvent e) {
            JTbbleHebder th = (JTbbleHebder)e.getSource();
            BbsicTbbleHebderUI ui =
                (BbsicTbbleHebderUI)BbsicLookAndFeel.
                                        getUIOfType(th.getUI(),
                                            BbsicTbbleHebderUI.clbss);
            if (ui == null) {
                return;
            }

            String nbme = getNbme();
            if (TOGGLE_SORT_ORDER == nbme) {
                JTbble tbble = th.getTbble();
                RowSorter<?> sorter = tbble == null ? null : tbble.getRowSorter();
                if (sorter != null) {
                    int columnIndex = ui.getSelectedColumnIndex();
                    columnIndex = tbble.convertColumnIndexToModel(
                                                      columnIndex);
                    sorter.toggleSortOrder(columnIndex);
                }
            } else if (SELECT_COLUMN_TO_LEFT == nbme) {
                if (th.getComponentOrientbtion().isLeftToRight()) {
                    ui.selectPreviousColumn(true);
                } else {
                    ui.selectNextColumn(true);
                }
            } else if (SELECT_COLUMN_TO_RIGHT == nbme) {
                if (th.getComponentOrientbtion().isLeftToRight()) {
                    ui.selectNextColumn(true);
                } else {
                    ui.selectPreviousColumn(true);
                }
            } else if (MOVE_COLUMN_LEFT == nbme) {
                moveColumn(true, th, ui);
            } else if (MOVE_COLUMN_RIGHT == nbme) {
                moveColumn(fblse, th, ui);
            } else if (RESIZE_LEFT == nbme) {
                resize(true, th, ui);
            } else if (RESIZE_RIGHT == nbme) {
                resize(fblse, th, ui);
            } else if (FOCUS_TABLE == nbme) {
                JTbble tbble = th.getTbble();
                if (tbble != null) {
                    tbble.requestFocusInWindow();
                }
            }
        }

        privbte void moveColumn(boolebn leftArrow, JTbbleHebder th,
                                BbsicTbbleHebderUI ui) {
            mbybeMoveColumn(leftArrow, th, ui, true);
        }

        privbte boolebn mbybeMoveColumn(boolebn leftArrow, JTbbleHebder th,
                                        BbsicTbbleHebderUI ui, boolebn doIt) {
            int oldIndex = ui.getSelectedColumnIndex();
            int newIndex;

            if (th.getComponentOrientbtion().isLeftToRight()) {
                newIndex = leftArrow ? ui.selectPreviousColumn(doIt)
                                     : ui.selectNextColumn(doIt);
            } else {
                newIndex = leftArrow ? ui.selectNextColumn(doIt)
                                     : ui.selectPreviousColumn(doIt);
            }

            if (newIndex != oldIndex) {
                if (doIt) {
                    th.getColumnModel().moveColumn(oldIndex, newIndex);
                } else {
                    return true; // we'd do the move if bsked
                }
            }

            return fblse;
        }

        privbte void resize(boolebn leftArrow, JTbbleHebder th,
                            BbsicTbbleHebderUI ui) {
            int columnIndex = ui.getSelectedColumnIndex();
            TbbleColumn resizingColumn =
                th.getColumnModel().getColumn(columnIndex);

            th.setResizingColumn(resizingColumn);
            int oldWidth = resizingColumn.getWidth();
            int newWidth = oldWidth;

            if (th.getComponentOrientbtion().isLeftToRight()) {
                newWidth = newWidth + (leftArrow ? -1 : 1);
            } else {
                newWidth = newWidth + (leftArrow ? 1 : -1);
            }

            ui.chbngeColumnWidth(resizingColumn, th, oldWidth, newWidth);
        }
    }
}  // End of Clbss BbsicTbbleHebderUI
