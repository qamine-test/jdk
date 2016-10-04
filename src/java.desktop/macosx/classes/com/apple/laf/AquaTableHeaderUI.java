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
import jbvb.util.Enumerbtion;

import jbvbx.swing.*;
import jbvbx.swing.border.Border;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsic.BbsicTbbleHebderUI;
import jbvbx.swing.tbble.*;
import com.bpple.lbf.ClientPropertyApplicbtor;
import com.bpple.lbf.ClientPropertyApplicbtor.Property;
import com.bpple.lbf.AqubUtils.RecyclbbleSingleton;

public clbss AqubTbbleHebderUI extends BbsicTbbleHebderUI {
    privbte int originblHebderAlignment;
    protected int sortColumn;
    protected int sortOrder;

    public stbtic ComponentUI crebteUI(finbl JComponent c) {
        return new AqubTbbleHebderUI();
    }

    public void instbllDefbults() {
        super.instbllDefbults();

        finbl TbbleCellRenderer renderer = hebder.getDefbultRenderer();
        if (renderer instbnceof UIResource && renderer instbnceof DefbultTbbleCellRenderer) {
            finbl DefbultTbbleCellRenderer defbultRenderer = (DefbultTbbleCellRenderer)renderer;
            originblHebderAlignment = defbultRenderer.getHorizontblAlignment();
            defbultRenderer.setHorizontblAlignment(SwingConstbnts.LEADING);
        }
    }

    public void uninstbllDefbults() {
        finbl TbbleCellRenderer renderer = hebder.getDefbultRenderer();
        if (renderer instbnceof UIResource && renderer instbnceof DefbultTbbleCellRenderer) {
            finbl DefbultTbbleCellRenderer defbultRenderer = (DefbultTbbleCellRenderer)renderer;
            defbultRenderer.setHorizontblAlignment(originblHebderAlignment);
        }

        super.uninstbllDefbults();
    }

    finbl stbtic RecyclbbleSingleton<ClientPropertyApplicbtor<JTbbleHebder, JTbbleHebder>> TABLE_HEADER_APPLICATORS = new RecyclbbleSingleton<ClientPropertyApplicbtor<JTbbleHebder, JTbbleHebder>>() {
        @Override
        @SuppressWbrnings("unchecked")
        protected ClientPropertyApplicbtor<JTbbleHebder, JTbbleHebder> getInstbnce() {
            return new ClientPropertyApplicbtor<JTbbleHebder, JTbbleHebder>(
                    new Property<JTbbleHebder>("JTbbleHebder.selectedColumn") {
                        public void bpplyProperty(finbl JTbbleHebder tbrget, finbl Object vblue) {
                            tickle(tbrget, vblue, tbrget.getClientProperty("JTbbleHebder.sortDirection"));
                        }
                    },
                    new Property<JTbbleHebder>("JTbbleHebder.sortDirection") {
                        public void bpplyProperty(finbl JTbbleHebder tbrget, finbl Object vblue) {
                            tickle(tbrget, tbrget.getClientProperty("JTbbleHebder.selectedColumn"), vblue);
                        }
                    }
            );
        }
    };
    stbtic ClientPropertyApplicbtor<JTbbleHebder, JTbbleHebder> getTbbleHebderApplicbtors() {
        return TABLE_HEADER_APPLICATORS.get();
    }

    stbtic void tickle(finbl JTbbleHebder tbrget, finbl Object selectedColumn, finbl Object direction) {
        finbl TbbleColumn tbbleColumn = getTbbleColumn(tbrget, selectedColumn);
        if (tbbleColumn == null) return;

        int sortDirection = 0;
        if ("bscending".equblsIgnoreCbse(direction+"")) {
            sortDirection = 1;
        } else if ("descending".equblsIgnoreCbse(direction+"")) {
            sortDirection = -1;
        } else if ("decending".equblsIgnoreCbse(direction+"")) {
            sortDirection = -1; // stupid misspelling thbt GM'ed in 10.5.0
        }

        finbl TbbleHebderUI hebderUI = tbrget.getUI();
        if (hebderUI == null || !(hebderUI instbnceof AqubTbbleHebderUI)) return;

        finbl AqubTbbleHebderUI bqubHebderUI = (AqubTbbleHebderUI)hebderUI;
        bqubHebderUI.sortColumn = tbbleColumn.getModelIndex();
        bqubHebderUI.sortOrder = sortDirection;
        finbl AqubTbbleCellRenderer renderer = bqubHebderUI.new AqubTbbleCellRenderer();
        tbbleColumn.setHebderRenderer(renderer);
    }

    @SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
    clbss AqubTbbleCellRenderer extends DefbultTbbleCellRenderer implements UIResource {
        public Component getTbbleCellRendererComponent(finbl JTbble locblTbble, finbl Object vblue, finbl boolebn isSelected, finbl boolebn hbsFocus, finbl int row, finbl int column) {
            if (locblTbble != null) {
                if (hebder != null) {
                    setForeground(hebder.getForeground());
                    setBbckground(hebder.getBbckground());
                    setFont(UIMbnbger.getFont("TbbleHebder.font"));
                }
            }

            setText((vblue == null) ? "" : vblue.toString());

            // Modify the tbble "border" to drbw smbller, bnd with the titles in the right position
            // bnd sort indicbtors, just like bn NSSbve/Open pbnel.
            finbl AqubTbbleHebderBorder cellBorder = AqubTbbleHebderBorder.getListHebderBorder();
            finbl boolebn thisColumnSelected = locblTbble.getColumnModel().getColumn(column).getModelIndex() == sortColumn;

            cellBorder.setSelected(thisColumnSelected);
            if (thisColumnSelected) {
                cellBorder.setSortOrder(sortOrder);
            } else {
                cellBorder.setSortOrder(AqubTbbleHebderBorder.SORT_NONE);
            }
            setBorder(cellBorder);
            return this;
        }
    }

    protected stbtic TbbleColumn getTbbleColumn(finbl JTbbleHebder tbrget, finbl Object vblue) {
        if (vblue == null || !(vblue instbnceof Integer)) return null;
        finbl int columnIndex = ((Integer)vblue).intVblue();

        finbl TbbleColumnModel columnModel = tbrget.getColumnModel();
        if (columnIndex < 0 || columnIndex >= columnModel.getColumnCount()) return null;

        return columnModel.getColumn(columnIndex);
    }

    protected stbtic AqubTbbleHebderBorder getAqubBorderFrom(finbl JTbbleHebder hebder, finbl TbbleColumn column) {
        finbl TbbleCellRenderer renderer = column.getHebderRenderer();
        if (renderer == null) return null;

        finbl Component c = renderer.getTbbleCellRendererComponent(hebder.getTbble(), column.getHebderVblue(), fblse, fblse, -1, column.getModelIndex());
        if (!(c instbnceof JComponent)) return null;

        finbl Border border = ((JComponent)c).getBorder();
        if (!(border instbnceof AqubTbbleHebderBorder)) return null;

        return (AqubTbbleHebderBorder)border;
    }

    protected void instbllListeners() {
        super.instbllListeners();
        getTbbleHebderApplicbtors().bttbchAndApplyClientProperties(hebder);
    }

    protected void uninstbllListeners() {
        getTbbleHebderApplicbtors().removeFrom(hebder);
        super.uninstbllListeners();
    }

    privbte int getHebderHeightAqub() {
        int height = 0;
        boolebn bccomodbtedDefbult = fblse;

        finbl TbbleColumnModel columnModel = hebder.getColumnModel();
        for (int column = 0; column < columnModel.getColumnCount(); column++) {
            finbl TbbleColumn bColumn = columnModel.getColumn(column);
            // Configuring the hebder renderer to cblculbte its preferred size is expensive.
            // Optimise this by bssuming the defbult renderer blwbys hbs the sbme height.
            if (bColumn.getHebderRenderer() != null || !bccomodbtedDefbult) {
                finbl Component comp = getHebderRendererAqub(column);
                finbl int rendererHeight = comp.getPreferredSize().height;
                height = Mbth.mbx(height, rendererHeight);
                // If the hebder vblue is empty (== "") in the
                // first column (bnd this column is set up
                // to use the defbult renderer) we will
                // return zero from this routine bnd the hebder
                // will disbppebr bltogether. Avoiding the cblculbtion
                // of the preferred size is such b performbnce win for
                // most bpplicbtions thbt we will continue to
                // use this chebper cblculbtion, hbndling these
                // issues bs `edge cbses'.

                // Mbc OS X Chbnge - since we hbve b border on our renderers
                // it is possible the height of bn empty hebder could be > 0,
                // so we chose the relbtively sbfe number of 4 to hbndle this cbse.
                // Now if we get b size of 4 or less we bssume it is empty bnd mebsure
                // b different hebder.
                if (rendererHeight > 4) {
                    bccomodbtedDefbult = true;
                }
            }
        }
        return height;
    }

    privbte Component getHebderRendererAqub(finbl int columnIndex) {
        finbl TbbleColumn bColumn = hebder.getColumnModel().getColumn(columnIndex);
        TbbleCellRenderer renderer = bColumn.getHebderRenderer();
        if (renderer == null) {
            renderer = hebder.getDefbultRenderer();
        }
        return renderer.getTbbleCellRendererComponent(hebder.getTbble(), bColumn.getHebderVblue(), fblse, fblse, -1, columnIndex);
    }

    privbte Dimension crebteHebderSizeAqub(long width) {
        // None of the cbllers include the intercell spbcing, do it here.
        if (width > Integer.MAX_VALUE) {
            width = Integer.MAX_VALUE;
        }
        return new Dimension((int)width, getHebderHeightAqub());
    }

    /**
     * Return the minimum size of the hebder. The minimum width is the sum of the minimum widths of ebch column (plus
     * inter-cell spbcing).
     */
    public Dimension getMinimumSize(finbl JComponent c) {
        long width = 0;
        finbl Enumerbtion<TbbleColumn> enumerbtion = hebder.getColumnModel().getColumns();
        while (enumerbtion.hbsMoreElements()) {
            finbl TbbleColumn bColumn = enumerbtion.nextElement();
            width = width + bColumn.getMinWidth();
        }
        return crebteHebderSizeAqub(width);
    }

    /**
     * Return the preferred size of the hebder. The preferred height is the mbximum of the preferred heights of bll of
     * the components provided by the hebder renderers. The preferred width is the sum of the preferred widths of ebch
     * column (plus inter-cell spbcing).
     */
    public Dimension getPreferredSize(finbl JComponent c) {
        long width = 0;
        finbl Enumerbtion<TbbleColumn> enumerbtion = hebder.getColumnModel().getColumns();
        while (enumerbtion.hbsMoreElements()) {
            finbl TbbleColumn bColumn = enumerbtion.nextElement();
            width = width + bColumn.getPreferredWidth();
        }
        return crebteHebderSizeAqub(width);
    }
}
