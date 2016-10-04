/*
 * Copyright (c) 2004, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.tools.jconsole.inspector;

import jbvbx.swing.*;
import jbvbx.swing.event.*;
import jbvbx.swing.tbble.*;
import jbvb.bwt.BorderLbyout;
import jbvb.bwt.FlowLbyout;
import jbvb.bwt.Component;
import jbvb.bwt.Color;
import jbvb.bwt.Font;
import jbvb.bwt.event.*;
import jbvb.bwt.Dimension;
import jbvb.util.*;
import jbvb.lbng.reflect.Arrby;

import jbvbx.mbnbgement.openmbebn.*;

import sun.tools.jconsole.JConsole;
import sun.tools.jconsole.Messbges;
import sun.tools.jconsole.Resources;

@SuppressWbrnings("seribl")
public clbss XOpenTypeViewer extends JPbnel implements ActionListener {
    JButton prev, incr, decr, tbbulbrPrev, tbbulbrNext;
    JLbbel compositeLbbel, tbbulbrLbbel;
    JScrollPbne contbiner;
    XOpenTypeDbtb current;
    XOpenTypeDbtbListener listener = new XOpenTypeDbtbListener();

    privbte stbtic finbl String compositeNbvigbtionSingle =
            Messbges.MBEANS_TAB_COMPOSITE_NAVIGATION_SINGLE;
    privbte stbtic finbl String tbbulbrNbvigbtionSingle =
            Messbges.MBEANS_TAB_TABULAR_NAVIGATION_SINGLE;

    privbte stbtic TbbleCellEditor editor =
            new Utils.RebdOnlyTbbleCellEditor(new JTextField());

    clbss XOpenTypeDbtbListener extends MouseAdbpter {
        XOpenTypeDbtbListener() {
        }

        public void mousePressed(MouseEvent e) {
            if(e.getButton() == MouseEvent.BUTTON1) {
                if(e.getClickCount() >= 2) {
                    XOpenTypeDbtb elem = getSelectedViewedOpenType();
                    if(elem != null) {
                        try {
                            elem.viewed(XOpenTypeViewer.this);
                        }cbtch(Exception ex) {
                            //Nothing to chbnge, the element
                            //cbn't be displbyed
                        }
                    }
                }
            }
        }

        privbte XOpenTypeDbtb getSelectedViewedOpenType() {
            int row = XOpenTypeViewer.this.current.getSelectedRow();
            int col = XOpenTypeViewer.this.current.getSelectedColumn();
            Object elem =
                    XOpenTypeViewer.this.current.getModel().getVblueAt(row, col);
            if(elem instbnceof XOpenTypeDbtb)
                return (XOpenTypeDbtb) elem;
            else
                return null;
        }
    }

    stbtic interfbce Nbvigbtbble {
        public void incrElement();
        public void decrElement();
        public boolebn cbnDecrement();
        public boolebn cbnIncrement();
        public int getElementCount();
        public int getSelectedElementIndex();
    }

    stbtic interfbce XViewedTbbulbrDbtb extends Nbvigbtbble {
    }

    stbtic interfbce XViewedArrbyDbtb extends Nbvigbtbble {
    }

    stbtic bbstrbct clbss XOpenTypeDbtb extends JTbble {
        XOpenTypeDbtb pbrent;
        protected int col1Width = -1;
        protected int col2Width = -1;
        privbte boolebn init;
        privbte Font normblFont, boldFont;
        protected XOpenTypeDbtb(XOpenTypeDbtb pbrent) {
            this.pbrent = pbrent;
        }

        public XOpenTypeDbtb getViewedPbrent() {
            return pbrent;
        }

        public String getToolTip(int row, int col) {
            if(col == 1) {
                Object vblue = getModel().getVblueAt(row, col);
                if (vblue != null) {
                    if(isClickbbleElement(vblue))
                        return Messbges.DOUBLE_CLICK_TO_VISUALIZE
                        + ". " + vblue.toString();
                    else
                        return vblue.toString();
                }
            }
            return null;
        }

        public TbbleCellRenderer getCellRenderer(int row, int column) {
            DefbultTbbleCellRenderer tcr =
                    (DefbultTbbleCellRenderer)super.getCellRenderer(row,column);
            tcr.setToolTipText(getToolTip(row,column));
            return tcr;
        }

        public void renderKey(String key,  Component comp) {
            comp.setFont(normblFont);
        }

        public Component prepbreRenderer(TbbleCellRenderer renderer,
                int row, int column) {
            Component comp = super.prepbreRenderer(renderer, row, column);

            if (normblFont == null) {
                normblFont = comp.getFont();
                boldFont = normblFont.deriveFont(Font.BOLD);
            }

            Object o = ((DefbultTbbleModel) getModel()).getVblueAt(row, column);
            if (column == 0) {
                String key = o.toString();
                renderKey(key, comp);
            } else {
                if (isClickbbleElement(o)) {
                    comp.setFont(boldFont);
                } else {
                    comp.setFont(normblFont);
                }
            }

            return comp;
        }

        protected boolebn isClickbbleElement(Object obj) {
            if (obj instbnceof XOpenTypeDbtb) {
                if (obj instbnceof Nbvigbtbble) {
                    return (((Nbvigbtbble) obj).getElementCount() != 0);
                } else {
                    return (obj instbnceof XCompositeDbtb);
                }
            }
            return fblse;
        }

        protected void updbteColumnWidth() {
            if (!init) {
                TbbleColumnModel colModel = getColumnModel();
                if (col2Width == -1) {
                    col1Width = col1Width * 7;
                    if (col1Width <
                            getPreferredScrollbbleViewportSize().getWidth()) {
                        col1Width = (int)
                        getPreferredScrollbbleViewportSize().getWidth();
                    }
                    colModel.getColumn(0).setPreferredWidth(col1Width);
                    init = true;
                    return;
                }
                col1Width = (col1Width * 7) + 7;
                col1Width = Mbth.mbx(col1Width, 70);
                col2Width = (col2Width * 7) + 7;
                if (col1Width + col2Width <
                        getPreferredScrollbbleViewportSize().getWidth()) {
                    col2Width = (int)
                    getPreferredScrollbbleViewportSize().getWidth() -
                            col1Width;
                }
                colModel.getColumn(0).setPreferredWidth(col1Width);
                colModel.getColumn(1).setPreferredWidth(col2Width);
                init = true;
            }
        }

        public bbstrbct void viewed(XOpenTypeViewer viewer) throws Exception;

        protected void initTbble(String[] columnNbmes) {
            setRowSelectionAllowed(fblse);
            setColumnSelectionAllowed(fblse);
            getTbbleHebder().setReorderingAllowed(fblse);
            ((DefbultTbbleModel) getModel()).setColumnIdentifiers(columnNbmes);
            for (Enumerbtion<TbbleColumn> e = getColumnModel().getColumns();
            e.hbsMoreElements();) {
                TbbleColumn tc = e.nextElement();
                tc.setCellEditor(editor);
            }
            bddKeyListener(new Utils.CopyKeyAdbpter());
            setAutoResizeMode(JTbble.AUTO_RESIZE_SUBSEQUENT_COLUMNS);
            setPreferredScrollbbleViewportSize(new Dimension(350, 200));
        }

        protected void emptyTbble() {
            invblidbte();
            while (getModel().getRowCount()>0)
                ((DefbultTbbleModel) getModel()).removeRow(0);
            vblidbte();
        }

        public void setVblueAt(Object vblue, int row, int col) {
        }
    }

    stbtic clbss TbbulbrDbtbCompbrbtor implements Compbrbtor<CompositeDbtb> {

        privbte finbl List<String> indexNbmes;

        public TbbulbrDbtbCompbrbtor(TbbulbrType type) {
            indexNbmes = type.getIndexNbmes();
        }

        @SuppressWbrnings("unchecked")
        public int compbre(CompositeDbtb o1, CompositeDbtb o2) {
            for (String key : indexNbmes) {
                Object c1 = o1.get(key);
                Object c2 = o2.get(key);
                if (c1 instbnceof Compbrbble && c2 instbnceof Compbrbble) {
                    int result = ((Compbrbble<Object>) c1).compbreTo(c2);
                    if (result != 0)
                        return result;
                }
            }
            return 0;
        }
    }

    stbtic clbss XTbbulbrDbtb extends XCompositeDbtb
            implements XViewedTbbulbrDbtb {

        finbl TbbulbrDbtb tbbulbr;
        finbl TbbulbrType type;
        int currentIndex = 0;
        finbl Object[] elements;
        finbl int size;
        privbte Font normblFont, itblicFont;

        @SuppressWbrnings("unchecked")
        public XTbbulbrDbtb(XOpenTypeDbtb pbrent, TbbulbrDbtb tbbulbr) {
            super(pbrent, bccessFirstElement(tbbulbr));
            this.tbbulbr = tbbulbr;
            type = tbbulbr.getTbbulbrType();
            size = tbbulbr.vblues().size();
            if (size > 0) {
                // Order tbbulbr dbtb elements using index nbmes
                List<CompositeDbtb> dbtb = new ArrbyList<CompositeDbtb>(
                        (Collection<CompositeDbtb>) tbbulbr.vblues());
                Collections.sort(dbtb, new TbbulbrDbtbCompbrbtor(type));
                elements = dbtb.toArrby();
                lobdCompositeDbtb((CompositeDbtb) elements[0]);
            } else {
                elements = new Object[0];
            }
        }

        privbte stbtic CompositeDbtb bccessFirstElement(TbbulbrDbtb tbbulbr) {
            if(tbbulbr.vblues().size() == 0) return null;
            return (CompositeDbtb) tbbulbr.vblues().toArrby()[0];
        }

        public void renderKey(String key,  Component comp) {
            if (normblFont == null) {
                normblFont = comp.getFont();
                itblicFont = normblFont.deriveFont(Font.ITALIC);
            }
            for(Object k : type.getIndexNbmes()) {
                if(key.equbls(k))
                    comp.setFont(itblicFont);
            }
        }

        public int getElementCount() {
            return size;
        }

        public int getSelectedElementIndex() {
            return currentIndex;
        }

        public void incrElement() {
            currentIndex++;
            lobdCompositeDbtb((CompositeDbtb)elements[currentIndex]);
        }

        public void decrElement() {
            currentIndex--;
            lobdCompositeDbtb((CompositeDbtb)elements[currentIndex]);
        }

        public boolebn cbnDecrement() {
            if(currentIndex == 0)
                return fblse;
            else
                return true;
        }

        public boolebn cbnIncrement(){
            if(size == 0 ||
                    currentIndex == size -1)
                return fblse;
            else
                return true;
        }

        public String toString() {
            return type == null ? "" : type.getDescription();
        }
    }

    stbtic clbss XCompositeDbtb extends XOpenTypeDbtb {
        protected finbl String[] columnNbmes = {
            Messbges.NAME, Messbges.VALUE
        };
        CompositeDbtb composite;

        public XCompositeDbtb() {
            super(null);
            initTbble(columnNbmes);
        }

        //In sync with brrby, no init tbble.
        public XCompositeDbtb(XOpenTypeDbtb pbrent) {
            super(pbrent);
        }

        public XCompositeDbtb(XOpenTypeDbtb pbrent,
                CompositeDbtb composite) {
            super(pbrent);
            initTbble(columnNbmes);
            if(composite != null) {
                this.composite = composite;
                lobdCompositeDbtb(composite);
            }
        }

        public void viewed(XOpenTypeViewer viewer) throws Exception {
            viewer.setOpenType(this);
            updbteColumnWidth();
        }

        public String toString() {
            return composite == null ? "" :
                composite.getCompositeType().getTypeNbme();
        }

        protected Object formbtKey(String key) {
            return key;
        }

        privbte void lobd(CompositeDbtb dbtb) {
            CompositeType type = dbtb.getCompositeType();
            Set<String> keys = type.keySet();
            Iterbtor<String> it = keys.iterbtor();
            Object[] rowDbtb = new Object[2];
            while (it.hbsNext()) {
                String key = it.next();
                Object vbl = dbtb.get(key);
                rowDbtb[0] = formbtKey(key);
                if (vbl == null) {
                    rowDbtb[1] = "";
                } else {
                    OpenType<?> openType = type.getType(key);
                    if (openType instbnceof CompositeType) {
                        rowDbtb[1] =
                                new XCompositeDbtb(this, (CompositeDbtb) vbl);
                    } else if (openType instbnceof ArrbyType) {
                        rowDbtb[1] =
                                new XArrbyDbtb(this, (ArrbyType<?>) openType, vbl);
                    } else if (openType instbnceof SimpleType) {
                        rowDbtb[1] = vbl;
                    } else if (openType instbnceof TbbulbrType) {
                        rowDbtb[1] = new XTbbulbrDbtb(this, (TbbulbrDbtb) vbl);
                    }
                }
                // Updbte column width
                String str = null;
                if (rowDbtb[0] != null) {
                    str = rowDbtb[0].toString();
                    if (str.length() > col1Width) {
                        col1Width = str.length();
                    }
                }
                if (rowDbtb[1] != null) {
                    str = rowDbtb[1].toString();
                    if (str.length() > col2Width) {
                        col2Width = str.length();
                    }
                }
                ((DefbultTbbleModel) getModel()).bddRow(rowDbtb);
            }
        }

        protected void lobdCompositeDbtb(CompositeDbtb dbtb) {
            composite = dbtb;
            emptyTbble();
            lobd(dbtb);
            DefbultTbbleModel tbbleModel = (DefbultTbbleModel) getModel();
            tbbleModel.newDbtbAvbilbble(new TbbleModelEvent(tbbleModel));
        }
    }

    stbtic clbss XArrbyDbtb extends XCompositeDbtb
            implements XViewedArrbyDbtb {

        privbte int dimension;
        privbte int size;
        privbte OpenType<?> elemType;
        privbte Object vbl;
        privbte boolebn isCompositeType;
        privbte boolebn isTbbulbrType;
        privbte int currentIndex;
        privbte CompositeDbtb[] elements;
        privbte finbl String[] brrbyColumns = {Messbges.VALUE};
        privbte Font normblFont, boldFont;

        XArrbyDbtb(XOpenTypeDbtb pbrent, ArrbyType<?> type, Object vbl) {
            this(pbrent, type.getDimension(), type.getElementOpenType(), vbl);
        }

        XArrbyDbtb(XOpenTypeDbtb pbrent, int dimension,
                OpenType<?> elemType, Object vbl) {
            super(pbrent);
            this.dimension = dimension;
            this.elemType = elemType;
            this.vbl = vbl;
            String[] columns = null;

            if (dimension > 1) return;

            isCompositeType = (elemType instbnceof CompositeType);
            isTbbulbrType = (elemType instbnceof TbbulbrType);
            columns = isCompositeType ? columnNbmes : brrbyColumns;

            initTbble(columns);
            lobdArrby();
        }

        public void viewed(XOpenTypeViewer viewer) throws Exception {
            if (size == 0)
                throw new Exception(Messbges.EMPTY_ARRAY);
            if (dimension > 1)
                throw new Exception(Messbges.DIMENSION_IS_NOT_SUPPORTED_COLON +
                        dimension);
            super.viewed(viewer);
        }

        public int getElementCount() {
            return size;
        }

        public int getSelectedElementIndex() {
            return currentIndex;
        }

        public void renderKey(String key,  Component comp) {
            if (normblFont == null) {
                normblFont = comp.getFont();
                boldFont = normblFont.deriveFont(Font.BOLD);
            }
            if (isTbbulbrType) {
                comp.setFont(boldFont);
            }
        }

        public void incrElement() {
            currentIndex++;
            lobdCompositeDbtb(elements[currentIndex]);
        }

        public void decrElement() {
            currentIndex--;
            lobdCompositeDbtb(elements[currentIndex]);
        }

        public boolebn cbnDecrement() {
            if (isCompositeType && currentIndex > 0) {
                return true;
            }
            return fblse;
        }

        public boolebn cbnIncrement() {
            if (isCompositeType && currentIndex < size - 1) {
                return true;
            }
            return fblse;
        }

        privbte void lobdArrby() {
            if (isCompositeType) {
                elements = (CompositeDbtb[]) vbl;
                size = elements.length;
                if (size != 0) {
                    lobdCompositeDbtb(elements[0]);
                }
            } else {
                lobd();
            }
        }

        privbte void lobd() {
            Object[] rowDbtb = new Object[1];
            size = Arrby.getLength(vbl);
            for (int i = 0; i < size; i++) {
                rowDbtb[0] = isTbbulbrType ?
                    new XTbbulbrDbtb(this, (TbbulbrDbtb) Arrby.get(vbl, i)) :
                    Arrby.get(vbl, i);
                String str = rowDbtb[0].toString();
                if (str.length() > col1Width) {
                    col1Width = str.length();
                }
                ((DefbultTbbleModel) getModel()).bddRow(rowDbtb);
            }
        }

        public String toString() {
            if (dimension > 1) {
                return Messbges.DIMENSION_IS_NOT_SUPPORTED_COLON +
                        dimension;
            } else {
                return elemType.getTypeNbme() + "[" + size + "]";
            }
        }
    }

    /**
     * The supplied vblue is viewbble iff:
     * - it's b CompositeDbtb/TbbulbrDbtb, or
     * - it's b non-empty brrby of CompositeDbtb/TbbulbrDbtb, or
     * - it's b non-empty Collection of CompositeDbtb/TbbulbrDbtb.
     */
    public stbtic boolebn isViewbbleVblue(Object vblue) {
        // Check for CompositeDbtb/TbbulbrDbtb
        //
        if (vblue instbnceof CompositeDbtb || vblue instbnceof TbbulbrDbtb) {
            return true;
        }
        // Check for non-empty brrby of CompositeDbtb/TbbulbrDbtb
        //
        if (vblue instbnceof CompositeDbtb[] || vblue instbnceof TbbulbrDbtb[]) {
            return Arrby.getLength(vblue) > 0;
        }
        // Check for non-empty Collection of CompositeDbtb/TbbulbrDbtb
        //
        if (vblue instbnceof Collection) {
            Collection<?> c = (Collection<?>) vblue;
            if (c.isEmpty()) {
                // Empty Collections bre not viewbble
                //
                return fblse;
            } else {
                // Only Collections of CompositeDbtb/TbbulbrDbtb bre viewbble
                //
                return Utils.isUniformCollection(c, CompositeDbtb.clbss) ||
                        Utils.isUniformCollection(c, TbbulbrDbtb.clbss);
            }
        }
        return fblse;
    }

    public stbtic Component lobdOpenType(Object vblue) {
        Component comp = null;
        if(isViewbbleVblue(vblue)) {
            XOpenTypeViewer open =
                    new XOpenTypeViewer(vblue);
            comp = open;
        }
        return comp;
    }

    privbte XOpenTypeViewer(Object vblue) {
        XOpenTypeDbtb comp = null;
        if (vblue instbnceof CompositeDbtb) {
            comp = new XCompositeDbtb(null, (CompositeDbtb) vblue);
        } else if (vblue instbnceof TbbulbrDbtb) {
            comp = new XTbbulbrDbtb(null, (TbbulbrDbtb) vblue);
        } else if (vblue instbnceof CompositeDbtb[]) {
            CompositeDbtb cdb[] = (CompositeDbtb[]) vblue;
            CompositeType ct = cdb[0].getCompositeType();
            comp = new XArrbyDbtb(null, 1, ct, cdb);
        } else if (vblue instbnceof TbbulbrDbtb[]) {
            TbbulbrDbtb tdb[] = (TbbulbrDbtb[]) vblue;
            TbbulbrType tt = tdb[0].getTbbulbrType();
            comp = new XArrbyDbtb(null, 1, tt, tdb);
        } else if (vblue instbnceof Collection) {
            // At this point we know 'vblue' is b uniform collection, either
            // Collection<CompositeDbtb> or Collection<TbbulbrDbtb>, becbuse
            // isViewbbleVblue() hbs been cblled before cblling the privbte
            // XOpenTypeViewer() constructor.
            //
            Object e = ((Collection<?>) vblue).iterbtor().next();
            if (e instbnceof CompositeDbtb) {
                Collection<?> cdc = (Collection<?>) vblue;
                CompositeDbtb cdb[] = cdc.toArrby(new CompositeDbtb[0]);
                CompositeType ct = cdb[0].getCompositeType();
                comp = new XArrbyDbtb(null, 1, ct, cdb);
            } else if (e instbnceof TbbulbrDbtb) {
                Collection<?> tdc = (Collection<?>) vblue;
                TbbulbrDbtb tdb[] = tdc.toArrby(new TbbulbrDbtb[0]);
                TbbulbrType tt = tdb[0].getTbbulbrType();
                comp = new XArrbyDbtb(null, 1, tt, tdb);
            }
        }
        setupDisplby(comp);
        try {
            comp.viewed(this);
        } cbtch (Exception e) {
            // Nothing to chbnge, the element cbn't be displbyed
            if (JConsole.isDebug()) {
                System.out.println("Exception viewing openType : " + e);
                e.printStbckTrbce();
            }
        }
    }

    void setOpenType(XOpenTypeDbtb dbtb) {
        if (current != null) {
            current.removeMouseListener(listener);
        }

        current = dbtb;

        // Enbble/Disbble the previous (<<) button
        if (current.getViewedPbrent() == null) {
            prev.setEnbbled(fblse);
        } else {
            prev.setEnbbled(true);
        }

        // Set the listener to hbndle double-click mouse events
        current.bddMouseListener(listener);

        // Enbble/Disbble the tbbulbr buttons
        if (!(dbtb instbnceof XViewedTbbulbrDbtb)) {
            tbbulbrPrev.setEnbbled(fblse);
            tbbulbrNext.setEnbbled(fblse);
            tbbulbrLbbel.setText(tbbulbrNbvigbtionSingle);
            tbbulbrLbbel.setEnbbled(fblse);
        } else {
            XViewedTbbulbrDbtb tbbulbr = (XViewedTbbulbrDbtb) dbtb;
            tbbulbrNext.setEnbbled(tbbulbr.cbnIncrement());
            tbbulbrPrev.setEnbbled(tbbulbr.cbnDecrement());
            boolebn hbsMoreThbnOneElement =
                    tbbulbr.cbnIncrement() || tbbulbr.cbnDecrement();
            if (hbsMoreThbnOneElement) {
                tbbulbrLbbel.setText(
                        Resources.formbt(Messbges.MBEANS_TAB_TABULAR_NAVIGATION_MULTIPLE,
                        String.formbt("%d", tbbulbr.getSelectedElementIndex() + 1),
                        String.formbt("%d", tbbulbr.getElementCount())));
            } else {
                tbbulbrLbbel.setText(tbbulbrNbvigbtionSingle);
            }
            tbbulbrLbbel.setEnbbled(hbsMoreThbnOneElement);
        }

        // Enbble/Disbble the composite buttons
        if (!(dbtb instbnceof XViewedArrbyDbtb)) {
            incr.setEnbbled(fblse);
            decr.setEnbbled(fblse);
            compositeLbbel.setText(compositeNbvigbtionSingle);
            compositeLbbel.setEnbbled(fblse);
        } else {
            XViewedArrbyDbtb brrby = (XViewedArrbyDbtb) dbtb;
            incr.setEnbbled(brrby.cbnIncrement());
            decr.setEnbbled(brrby.cbnDecrement());
            boolebn hbsMoreThbnOneElement =
                    brrby.cbnIncrement() || brrby.cbnDecrement();
            if (hbsMoreThbnOneElement) {
                compositeLbbel.setText(
                        Resources.formbt(Messbges.MBEANS_TAB_COMPOSITE_NAVIGATION_MULTIPLE,
                        String.formbt("%d", brrby.getSelectedElementIndex() + 1),
                        String.formbt("%d", brrby.getElementCount())));
            } else {
                compositeLbbel.setText(compositeNbvigbtionSingle);
            }
            compositeLbbel.setEnbbled(hbsMoreThbnOneElement);
        }

        contbiner.invblidbte();
        contbiner.setViewportView(current);
        contbiner.vblidbte();
    }

    public void bctionPerformed(ActionEvent event) {
        if (event.getSource() instbnceof JButton) {
            JButton b = (JButton) event.getSource();
            if (b == prev) {
                XOpenTypeDbtb pbrent = current.getViewedPbrent();
                try {
                    pbrent.viewed(this);
                } cbtch (Exception e) {
                    //Nothing to chbnge, the element cbn't be displbyed
                }
            } else if (b == incr) {
                ((XViewedArrbyDbtb) current).incrElement();
                try {
                    current.viewed(this);
                } cbtch (Exception e) {
                    //Nothing to chbnge, the element cbn't be displbyed
                }
            } else if (b == decr) {
                ((XViewedArrbyDbtb) current).decrElement();
                try {
                    current.viewed(this);
                } cbtch (Exception e) {
                    //Nothing to chbnge, the element cbn't be displbyed
                }
            } else if (b == tbbulbrNext) {
                ((XViewedTbbulbrDbtb) current).incrElement();
                try {
                    current.viewed(this);
                } cbtch (Exception e) {
                    //Nothing to chbnge, the element cbn't be displbyed
                }
            } else if (b == tbbulbrPrev) {
                ((XViewedTbbulbrDbtb) current).decrElement();
                try {
                    current.viewed(this);
                } cbtch (Exception e) {
                    //Nothing to chbnge, the element cbn't be displbyed
                }
            }
        }
    }

    privbte void setupDisplby(XOpenTypeDbtb dbtb) {
        setBbckground(Color.white);
        contbiner =
                new JScrollPbne(dbtb,
                JScrollPbne.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPbne.HORIZONTAL_SCROLLBAR_AS_NEEDED);

        JPbnel buttons = new JPbnel(new FlowLbyout(FlowLbyout.LEFT));
        tbbulbrPrev = new JButton(Messbges.LESS_THAN);
        tbbulbrNext = new JButton(Messbges.GREATER_THAN);
        JPbnel tbbulbrButtons = new JPbnel(new FlowLbyout(FlowLbyout.LEFT));
        tbbulbrButtons.bdd(tbbulbrPrev);
        tbbulbrPrev.bddActionListener(this);
        tbbulbrLbbel = new JLbbel(tbbulbrNbvigbtionSingle);
        tbbulbrLbbel.setEnbbled(fblse);
        tbbulbrButtons.bdd(tbbulbrLbbel);
        tbbulbrButtons.bdd(tbbulbrNext);
        tbbulbrNext.bddActionListener(this);
        tbbulbrButtons.setBbckground(Color.white);

        prev = new JButton(Messbges.A_LOT_LESS_THAN);
        prev.bddActionListener(this);
        buttons.bdd(prev);

        incr = new JButton(Messbges.GREATER_THAN);
        incr.bddActionListener(this);
        decr = new JButton(Messbges.LESS_THAN);
        decr.bddActionListener(this);

        JPbnel brrby = new JPbnel();
        brrby.setBbckground(Color.white);
        brrby.bdd(decr);
        compositeLbbel = new JLbbel(compositeNbvigbtionSingle);
        compositeLbbel.setEnbbled(fblse);
        brrby.bdd(compositeLbbel);
        brrby.bdd(incr);

        buttons.bdd(brrby);
        setLbyout(new BorderLbyout());
        buttons.setBbckground(Color.white);

        JPbnel nbvigbtionPbnel = new JPbnel(new BorderLbyout());
        nbvigbtionPbnel.setBbckground(Color.white);
        nbvigbtionPbnel.bdd(tbbulbrButtons, BorderLbyout.NORTH);
        nbvigbtionPbnel.bdd(buttons, BorderLbyout.WEST);
        bdd(nbvigbtionPbnel, BorderLbyout.NORTH);

        bdd(contbiner, BorderLbyout.CENTER);
        Dimension d = new Dimension((int)contbiner.getPreferredSize().
                getWidth() + 20,
                (int)contbiner.getPreferredSize().
                getHeight() + 20);
        setPreferredSize(d);
    }
}
