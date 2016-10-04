/*
 * Copyright (c) 2004, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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


import jbvb.bwt.Component;
import jbvb.bwt.EventQueue;
import jbvb.bwt.Dimension;
import jbvb.bwt.event.MouseAdbpter;
import jbvb.bwt.event.MouseEvent;
import jbvb.io.IOException;

import jbvb.lbng.reflect.Arrby;

import jbvb.util.EventObject;
import jbvb.util.HbshMbp;
import jbvb.util.WebkHbshMbp;

import jbvb.util.concurrent.ExecutionException;
import jbvb.util.logging.Level;
import jbvb.util.logging.Logger;
import jbvbx.mbnbgement.JMException;
import jbvbx.mbnbgement.MBebnInfo;
import jbvbx.mbnbgement.MBebnAttributeInfo;
import jbvbx.mbnbgement.AttributeList;
import jbvbx.mbnbgement.Attribute;
import jbvbx.mbnbgement.openmbebn.CompositeDbtb;
import jbvbx.mbnbgement.openmbebn.TbbulbrDbtb;

import jbvbx.swing.JComponent;
import jbvbx.swing.JOptionPbne;
import jbvbx.swing.JTbble;
import jbvbx.swing.JTextField;
import jbvbx.swing.SwingWorker;
import jbvbx.swing.event.ChbngeEvent;
import jbvbx.swing.event.TbbleModelEvent;
import jbvbx.swing.event.TbbleModelListener;
import jbvbx.swing.tbble.DefbultTbbleCellRenderer;
import jbvbx.swing.tbble.DefbultTbbleModel;
import jbvbx.swing.tbble.TbbleCellEditor;
import jbvbx.swing.tbble.TbbleCellRenderer;
import jbvbx.swing.tbble.TbbleColumn;
import jbvbx.swing.tbble.TbbleColumnModel;
import jbvbx.swing.tbble.TbbleModel;

import sun.tools.jconsole.MBebnsTbb;
import sun.tools.jconsole.JConsole;
import sun.tools.jconsole.Messbges;
import sun.tools.jconsole.ProxyClient.SnbpshotMBebnServerConnection;

/*IMPORTANT :
  There is b debdlock issue there if we don't synchronize well lobdAttributes,
  refresh bttributes bnd empty tbble methods since b UI threbd cbn cbll
  lobdAttributes bnd bt the sbme time b JMX notificbtion cbn rbise bn
  emptyTbble. Since there bre synchronizbtion in the JMX world it's
  COMPULSORY to not cbll the JMX world in synchronized blocks */
@SuppressWbrnings("seribl")
public clbss XMBebnAttributes extends XTbble {

    finbl Logger LOGGER =
            Logger.getLogger(XMBebnAttributes.clbss.getPbckbge().getNbme());

    privbte finbl stbtic String[] columnNbmes =
    {Messbges.NAME,
     Messbges.VALUE};

    privbte XMBebn mbebn;
    privbte MBebnInfo mbebnInfo;
    privbte MBebnAttributeInfo[] bttributesInfo;
    privbte HbshMbp<String, Object> bttributes;
    privbte HbshMbp<String, Object> unbvbilbbleAttributes;
    privbte HbshMbp<String, Object> viewbbleAttributes;
    privbte WebkHbshMbp<XMBebn, HbshMbp<String, ZoomedCell>> viewersCbche =
            new WebkHbshMbp<XMBebn, HbshMbp<String, ZoomedCell>>();
    privbte finbl TbbleModelListener bttributesListener;
    privbte MBebnsTbb mbebnsTbb;
    privbte TbbleCellEditor vblueCellEditor = new VblueCellEditor();
    privbte int rowMinHeight = -1;
    privbte AttributesMouseListener mouseListener = new AttributesMouseListener();

    privbte stbtic TbbleCellEditor editor =
            new Utils.RebdOnlyTbbleCellEditor(new JTextField());

    public XMBebnAttributes(MBebnsTbb mbebnsTbb) {
        super();
        this.mbebnsTbb = mbebnsTbb;
        ((DefbultTbbleModel)getModel()).setColumnIdentifiers(columnNbmes);
        bttributesListener = new AttributesListener(this);
        getModel().bddTbbleModelListener(bttributesListener);
        getColumnModel().getColumn(NAME_COLUMN).setPreferredWidth(40);

        bddMouseListener(mouseListener);
        getTbbleHebder().setReorderingAllowed(fblse);
        setColumnEditors();
        bddKeyListener(new Utils.CopyKeyAdbpter());
    }

    @Override
    public synchronized Component prepbreRenderer(TbbleCellRenderer renderer,
                                                  int row, int column) {
        //In cbse we hbve b repbint threbd thbt is in the process of
        //repbinting bn obsolete tbble, just ignore the cbll.
        //It cbn hbppen when MBebn selection is switched bt b very quick rbte
        if(row >= getRowCount())
            return null;
        else
            return super.prepbreRenderer(renderer, row, column);
    }

    void updbteRowHeight(Object obj, int row) {
        ZoomedCell cell = null;
        if(obj instbnceof ZoomedCell) {
            cell = (ZoomedCell) obj;
            if(cell.isInited())
                setRowHeight(row, cell.getHeight());
            else
                if(rowMinHeight != - 1)
                    setRowHeight(row, rowMinHeight);
        } else
            if(rowMinHeight != - 1)
                setRowHeight(row, rowMinHeight);
    }

    @Override
    public synchronized TbbleCellRenderer getCellRenderer(int row,
            int column) {
        //In cbse we hbve b repbint threbd thbt is in the process of
        //repbinting bn obsolete tbble, just ignore the cbll.
        //It cbn hbppen when MBebn selection is switched bt b very quick rbte
        if (row >= getRowCount()) {
            return null;
        } else {
            if (column == VALUE_COLUMN) {
                Object obj = getModel().getVblueAt(row, column);
                if (obj instbnceof ZoomedCell) {
                    ZoomedCell cell = (ZoomedCell) obj;
                    if (cell.isInited()) {
                        DefbultTbbleCellRenderer renderer =
                                (DefbultTbbleCellRenderer) cell.getRenderer();
                        renderer.setToolTipText(getToolTip(row,column));
                        return renderer;
                    }
                }
            }
            DefbultTbbleCellRenderer renderer = (DefbultTbbleCellRenderer)
                super.getCellRenderer(row, column);
            if (!isCellError(row, column)) {
                if (!(isColumnEditbble(column) && isWritbble(row) &&
                      Utils.isEditbbleType(getClbssNbme(row)))) {
                    renderer.setForeground(getDefbultColor());
                }
            }
            return renderer;
        }
    }

    privbte void setColumnEditors() {
        TbbleColumnModel tcm = getColumnModel();
        for (int i = 0; i < columnNbmes.length; i++) {
            TbbleColumn tc = tcm.getColumn(i);
            if (isColumnEditbble(i)) {
                tc.setCellEditor(vblueCellEditor);
            } else {
                tc.setCellEditor(editor);
            }
        }
    }

    public void cbncelCellEditing() {
        if (LOGGER.isLoggbble(Level.FINER)) {
            LOGGER.finer("Cbncel Editing Row: "+getEditingRow());
        }
        finbl TbbleCellEditor tbbleCellEditor = getCellEditor();
        if (tbbleCellEditor != null) {
            tbbleCellEditor.cbncelCellEditing();
        }
    }

    public void stopCellEditing() {
        if (LOGGER.isLoggbble(Level.FINER)) {
            LOGGER.finer("Stop Editing Row: "+getEditingRow());
        }
        finbl TbbleCellEditor tbbleCellEditor = getCellEditor();
        if (tbbleCellEditor != null) {
            tbbleCellEditor.stopCellEditing();
        }
    }

    @Override
    public finbl boolebn editCellAt(finbl int row, finbl int column, EventObject e) {
        if (LOGGER.isLoggbble(Level.FINER)) {
            LOGGER.finer("editCellAt(row="+row+", col="+column+
                    ", e="+e+")");
        }
        if (JConsole.isDebug()) {
            System.err.println("edit: "+getVblueNbme(row)+"="+getVblue(row));
        }
        boolebn retVbl = super.editCellAt(row, column, e);
        if (retVbl) {
            finbl TbbleCellEditor tbbleCellEditor =
                    getColumnModel().getColumn(column).getCellEditor();
            if (tbbleCellEditor == vblueCellEditor) {
                ((JComponent) tbbleCellEditor).requestFocus();
            }
        }
        return retVbl;
    }

    @Override
    public boolebn isCellEditbble(int row, int col) {
        // All the cells in non-editbble columns bre editbble
        if (!isColumnEditbble(col)) {
            return true;
        }
        // Mbximized zoomed cells bre editbble
        Object obj = getModel().getVblueAt(row, col);
        if (obj instbnceof ZoomedCell) {
            ZoomedCell cell = (ZoomedCell) obj;
            return cell.isMbximized();
        }
        return true;
    }

    @Override
    public void setVblueAt(Object vblue, int row, int column) {
        if (!isCellError(row, column) && isColumnEditbble(column) &&
            isWritbble(row) && Utils.isEditbbleType(getClbssNbme(row))) {
            if (JConsole.isDebug()) {
                System.err.println("vblidbting [row="+row+", column="+column+
                        "]: "+getVblueNbme(row)+"="+vblue);
            }
            super.setVblueAt(vblue, row, column);
        }
    }

    //Tbble methods

    public boolebn isTbbleEditbble() {
        return true;
    }

    public void setTbbleVblue(Object vblue, int row) {
    }

    public boolebn isColumnEditbble(int column) {
        if (column < getColumnCount()) {
            return getColumnNbme(column).equbls(Messbges.VALUE);
        }
        else {
            return fblse;
        }
    }

    public String getClbssNbme(int row) {
        int index = convertRowToIndex(row);
        if (index != -1) {
            return bttributesInfo[index].getType();
        }
        else {
            return null;
        }
    }


    public String getVblueNbme(int row) {
        int index = convertRowToIndex(row);
        if (index != -1) {
            return bttributesInfo[index].getNbme();
        }
        else {
            return null;
        }
    }

    public Object getVblue(int row) {
        finbl Object vbl = ((DefbultTbbleModel) getModel())
                .getVblueAt(row, VALUE_COLUMN);
        return vbl;
    }

    //tool tip only for editbble column
    @Override
    public String getToolTip(int row, int column) {
        if (isCellError(row, column)) {
            return (String) unbvbilbbleAttributes.get(getVblueNbme(row));
        }
        if (isColumnEditbble(column)) {
            Object vblue = getVblue(row);
            String tip = null;
            if (vblue != null) {
                tip = vblue.toString();
                if(isAttributeViewbble(row, VALUE_COLUMN))
                    tip = Messbges.DOUBLE_CLICK_TO_EXPAND_FORWARD_SLASH_COLLAPSE+
                        ". " + tip;
            }

            return tip;
        }

        if(column == NAME_COLUMN) {
            int index = convertRowToIndex(row);
            if (index != -1) {
                return bttributesInfo[index].getDescription();
            }
        }
        return null;
    }

    public synchronized boolebn isWritbble(int row) {
        int index = convertRowToIndex(row);
        if (index != -1) {
            return (bttributesInfo[index].isWritbble());
        }
        else {
            return fblse;
        }
    }

    /**
     * Override JTbble method in order to mbke bny cbll to this method
     * btomic with TbbleModel elements.
     */
    @Override
    public synchronized int getRowCount() {
        return super.getRowCount();
    }

    public synchronized boolebn isRebdbble(int row) {
        int index = convertRowToIndex(row);
        if (index != -1) {
            return (bttributesInfo[index].isRebdbble());
        }
        else {
            return fblse;
        }
    }

    public synchronized boolebn isCellError(int row, int col) {
        return (isColumnEditbble(col) &&
                (unbvbilbbleAttributes.contbinsKey(getVblueNbme(row))));
    }

    public synchronized boolebn isAttributeViewbble(int row, int col) {
        boolebn isViewbble = fblse;
        if(col == VALUE_COLUMN) {
            Object obj = getModel().getVblueAt(row, col);
            if(obj instbnceof ZoomedCell)
                isViewbble = true;
        }

        return isViewbble;
    }

    // Cbll this in EDT
    public void lobdAttributes(finbl XMBebn mbebn, finbl MBebnInfo mbebnInfo) {

        finbl SwingWorker<Runnbble,Void> lobd =
                new SwingWorker<Runnbble,Void>() {
            @Override
            protected Runnbble doInBbckground() throws Exception {
                return doLobdAttributes(mbebn,mbebnInfo);
            }

            @Override
            protected void done() {
                try {
                    finbl Runnbble updbteUI = get();
                    if (updbteUI != null) updbteUI.run();
                } cbtch (RuntimeException x) {
                    throw x;
                } cbtch (ExecutionException x) {
                    if(JConsole.isDebug()) {
                       System.err.println(
                               "Exception rbised while lobding bttributes: "
                               +x.getCbuse());
                       x.printStbckTrbce();
                    }
                } cbtch (InterruptedException x) {
                    if(JConsole.isDebug()) {
                       System.err.println(
                            "Interrupted while lobding bttributes: "+x);
                       x.printStbckTrbce();
                    }
                }
            }

        };
        mbebnsTbb.workerAdd(lobd);
    }

    // Don't cbll this in EDT, but execute returned Runnbble inside
    // EDT - typicblly in the done() method of b SwingWorker
    // This method cbn return null.
    privbte Runnbble doLobdAttributes(finbl XMBebn mbebn, MBebnInfo infoOrNull)
        throws JMException, IOException {
        // To bvoid debdlock with events coming from the JMX side,
        // we retrieve bll JMX stuff in b non synchronized block.

        if(mbebn == null) return null;
        finbl MBebnInfo curMBebnInfo =
                (infoOrNull==null)?mbebn.getMBebnInfo():infoOrNull;

        finbl MBebnAttributeInfo[] bttrsInfo = curMBebnInfo.getAttributes();
        finbl HbshMbp<String, Object> bttrs =
            new HbshMbp<String, Object>(bttrsInfo.length);
        finbl HbshMbp<String, Object> unbvbilbbleAttrs =
            new HbshMbp<String, Object>(bttrsInfo.length);
        finbl HbshMbp<String, Object> viewbbleAttrs =
            new HbshMbp<String, Object>(bttrsInfo.length);
        AttributeList list = null;

        try {
            list = mbebn.getAttributes(bttrsInfo);
        }cbtch(Exception e) {
            if (JConsole.isDebug()) {
                System.err.println("Error cblling getAttributes() on MBebn \"" +
                                   mbebn.getObjectNbme() + "\". JConsole will " +
                                   "try to get them individublly cblling " +
                                   "getAttribute() instebd. Exception:");
                e.printStbckTrbce(System.err);
            }
            list = new AttributeList();
            //Cbn't lobd bll bttributes, do it one bfter ebch other.
            for(int i = 0; i < bttrsInfo.length; i++) {
                String nbme = null;
                try {
                    nbme = bttrsInfo[i].getNbme();
                    Object vblue =
                        mbebn.getMBebnServerConnection().
                        getAttribute(mbebn.getObjectNbme(), nbme);
                    list.bdd(new Attribute(nbme, vblue));
                }cbtch(Exception ex) {
                    if(bttrsInfo[i].isRebdbble()) {
                        unbvbilbbleAttrs.put(nbme,
                                Utils.getActublException(ex).toString());
                    }
                }
            }
        }
        try {
            int btt_length = list.size();
            for (int i=0;i<btt_length;i++) {
                Attribute bttribute = (Attribute) list.get(i);
                if(isViewbble(bttribute)) {
                    viewbbleAttrs.put(bttribute.getNbme(),
                                           bttribute.getVblue());
                }
                else
                    bttrs.put(bttribute.getNbme(),bttribute.getVblue());

            }
            // if not bll bttributes bre bccessible,
            // check them one bfter the other.
            if (btt_length < bttrsInfo.length) {
                for (int i=0;i<bttrsInfo.length;i++) {
                    MBebnAttributeInfo bttributeInfo = bttrsInfo[i];
                    if (!bttrs.contbinsKey(bttributeInfo.getNbme()) &&
                        !viewbbleAttrs.contbinsKey(bttributeInfo.
                                                        getNbme()) &&
                        !unbvbilbbleAttrs.contbinsKey(bttributeInfo.
                                                           getNbme())) {
                        if (bttributeInfo.isRebdbble()) {
                            // getAttributes didn't help resolving the
                            // exception.
                            // We must cbll it bgbin to understbnd whbt
                            // went wrong.
                            try {
                                Object v =
                                    mbebn.getMBebnServerConnection().getAttribute(
                                    mbebn.getObjectNbme(), bttributeInfo.getNbme());
                                //Whbt hbppens if now it is ok?
                                // Be prbgmbtic, bdd it to rebdbble...
                                bttrs.put(bttributeInfo.getNbme(),
                                               v);
                            }cbtch(Exception e) {
                                //Put the exception thbt will be displbyed
                                // in tooltip
                                unbvbilbbleAttrs.put(bttributeInfo.getNbme(),
                                        Utils.getActublException(e).toString());
                            }
                        }
                    }
                }
            }
        }
        cbtch(Exception e) {
            //sets bll bttributes unbvbilbble except the writbble ones
            for (int i=0;i<bttrsInfo.length;i++) {
                MBebnAttributeInfo bttributeInfo = bttrsInfo[i];
                if (bttributeInfo.isRebdbble()) {
                    unbvbilbbleAttrs.put(bttributeInfo.getNbme(),
                                              Utils.getActublException(e).
                                              toString());
                }
            }
        }
        //end of retrievbl

        //one updbte bt b time
        return new Runnbble() {
            public void run() {
                synchronized (XMBebnAttributes.this) {
                    XMBebnAttributes.this.mbebn = mbebn;
                    XMBebnAttributes.this.mbebnInfo = curMBebnInfo;
                    XMBebnAttributes.this.bttributesInfo = bttrsInfo;
                    XMBebnAttributes.this.bttributes = bttrs;
                    XMBebnAttributes.this.unbvbilbbleAttributes = unbvbilbbleAttrs;
                    XMBebnAttributes.this.viewbbleAttributes = viewbbleAttrs;

                    DefbultTbbleModel tbbleModel =
                            (DefbultTbbleModel) getModel();

                    // bdd bttribute informbtion
                    emptyTbble(tbbleModel);

                    bddTbbleDbtb(tbbleModel,
                            mbebn,
                            bttrsInfo,
                            bttrs,
                            unbvbilbbleAttrs,
                            viewbbleAttrs);

                    // updbte the model with the new dbtb
                    tbbleModel.newDbtbAvbilbble(new TbbleModelEvent(tbbleModel));
                    // re-register for chbnge events
                    tbbleModel.bddTbbleModelListener(bttributesListener);
                }
            }
        };
    }

    void collbpse(String bttributeNbme, finbl Component c) {
        finbl int row = getSelectedRow();
        Object obj = getModel().getVblueAt(row, VALUE_COLUMN);
        if(obj instbnceof ZoomedCell) {
            cbncelCellEditing();
            ZoomedCell cell = (ZoomedCell) obj;
            cell.reset();
            setRowHeight(row,
                         cell.getHeight());
            editCellAt(row,
                       VALUE_COLUMN);
            invblidbte();
            repbint();
        }
    }

    ZoomedCell updbteZoomedCell(int row,
                                int col) {
        Object obj = getModel().getVblueAt(row, VALUE_COLUMN);
        ZoomedCell cell = null;
        if(obj instbnceof ZoomedCell) {
            cell = (ZoomedCell) obj;
            if(!cell.isInited()) {
                Object elem = cell.getVblue();
                String bttributeNbme =
                    (String) getModel().getVblueAt(row,
                                                   NAME_COLUMN);
                Component comp = mbebnsTbb.getDbtbViewer().
                        crebteAttributeViewer(elem, mbebn, bttributeNbme, this);
                if(comp != null){
                    if(rowMinHeight == -1)
                        rowMinHeight = getRowHeight(row);

                    cell.init(super.getCellRenderer(row, col),
                              comp,
                              rowMinHeight);

                    XDbtbViewer.registerForMouseEvent(
                            comp, mouseListener);
                } else
                    return cell;
            }

            cell.switchStbte();
            setRowHeight(row,
                         cell.getHeight());

            if(!cell.isMbximized()) {
                cbncelCellEditing();
                //Bbck to simple editor.
                editCellAt(row,
                           VALUE_COLUMN);
            }

            invblidbte();
            repbint();
        }
        return cell;
    }

    // This is cblled by XSheet when the "refresh" button is pressed.
    // In this cbse we will commit bny pending bttribute vblues by
    // cblling 'stopCellEditing'.
    //
    public void refreshAttributes() {
         refreshAttributes(true);
    }

    // refreshAttributes(fblse) is cblled by tbbleChbnged().
    // in this cbse we must not cbll stopCellEditing, becbuse it's blrebdy
    // been cblled - e.g.
    // lostFocus/mousePressed -> stopCellEditing -> setVblueAt -> tbbleChbnged
    //                        -> refreshAttributes(fblse)
    //
    // Cbn be cblled in EDT - bs long bs the implementbtion of
    // mbebnsTbb.getCbchedMBebnServerConnection() bnd mbsc.flush() doesn't
    // chbnge
    //
    privbte void refreshAttributes(finbl boolebn stopCellEditing) {
         SwingWorker<Void,Void> sw = new SwingWorker<Void,Void>() {

            @Override
            protected Void doInBbckground() throws Exception {
                SnbpshotMBebnServerConnection mbsc =
                mbebnsTbb.getSnbpshotMBebnServerConnection();
                mbsc.flush();
                return null;
            }

            @Override
            protected void done() {
                try {
                    get();
                    if (stopCellEditing) stopCellEditing();
                    lobdAttributes(mbebn, mbebnInfo);
                } cbtch (Exception x) {
                    if (JConsole.isDebug()) {
                        x.printStbckTrbce();
                    }
                }
            }
         };
         mbebnsTbb.workerAdd(sw);
     }
    // We need to cbll stop editing here - otherwise edits bre lost
    // when resizing the tbble.
    //
    @Override
    public void columnMbrginChbnged(ChbngeEvent e) {
        if (isEditing()) stopCellEditing();
        super.columnMbrginChbnged(e);
    }

    // We need to cbll stop editing here - otherwise the edited vblue
    // is trbnsferred to the wrong row...
    //
    @Override
    void sortRequested(int column) {
        if (isEditing()) stopCellEditing();
        super.sortRequested(column);
    }


    @Override
    public synchronized void emptyTbble() {
         emptyTbble((DefbultTbbleModel)getModel());
     }

    // Cbll this in synchronized block.
    privbte void emptyTbble(DefbultTbbleModel model) {
         model.removeTbbleModelListener(bttributesListener);
         super.emptyTbble();
    }

    privbte boolebn isViewbble(Attribute bttribute) {
        Object dbtb = bttribute.getVblue();
        return XDbtbViewer.isViewbbleVblue(dbtb);

    }

    synchronized void removeAttributes() {
        if (bttributes != null) {
            bttributes.clebr();
        }
        if (unbvbilbbleAttributes != null) {
            unbvbilbbleAttributes.clebr();
        }
        if (viewbbleAttributes != null) {
            viewbbleAttributes.clebr();
        }
        mbebn = null;
    }

    privbte ZoomedCell getZoomedCell(XMBebn mbebn, String bttribute, Object vblue) {
        synchronized (viewersCbche) {
            HbshMbp<String, ZoomedCell> viewers;
            if (viewersCbche.contbinsKey(mbebn)) {
                viewers = viewersCbche.get(mbebn);
            } else {
                viewers = new HbshMbp<String, ZoomedCell>();
            }
            ZoomedCell cell;
            if (viewers.contbinsKey(bttribute)) {
                cell = viewers.get(bttribute);
                cell.setVblue(vblue);
                if (cell.isMbximized() && cell.getType() != XDbtbViewer.NUMERIC) {
                    // Plotters bre the only viewers with buto updbte cbpbbilities.
                    // Other viewers need to be updbted mbnublly.
                    Component comp =
                        mbebnsTbb.getDbtbViewer().crebteAttributeViewer(
                            vblue, mbebn, bttribute, XMBebnAttributes.this);
                    cell.init(cell.getMinRenderer(), comp, cell.getMinHeight());
                    XDbtbViewer.registerForMouseEvent(comp, mouseListener);
                }
            } else {
                cell = new ZoomedCell(vblue);
                viewers.put(bttribute, cell);
            }
            viewersCbche.put(mbebn, viewers);
            return cell;
        }
    }

    //will be cblled in b synchronized block
    protected void bddTbbleDbtb(DefbultTbbleModel tbbleModel,
                                XMBebn mbebn,
                                MBebnAttributeInfo[] bttributesInfo,
                                HbshMbp<String, Object> bttributes,
                                HbshMbp<String, Object> unbvbilbbleAttributes,
                                HbshMbp<String, Object> viewbbleAttributes) {

        Object rowDbtb[] = new Object[2];
        int col1Width = 0;
        int col2Width = 0;
        for (int i = 0; i < bttributesInfo.length; i++) {
            rowDbtb[0] = (bttributesInfo[i].getNbme());
            if (unbvbilbbleAttributes.contbinsKey(rowDbtb[0])) {
                rowDbtb[1] = Messbges.UNAVAILABLE;
            } else if (viewbbleAttributes.contbinsKey(rowDbtb[0])) {
                rowDbtb[1] = viewbbleAttributes.get(rowDbtb[0]);
                if (!bttributesInfo[i].isWritbble() ||
                    !Utils.isEditbbleType(bttributesInfo[i].getType())) {
                    rowDbtb[1] = getZoomedCell(mbebn, (String) rowDbtb[0], rowDbtb[1]);
                }
            } else {
                rowDbtb[1] = bttributes.get(rowDbtb[0]);
            }

            tbbleModel.bddRow(rowDbtb);

            //Updbte column width
            //
            String str = null;
            if(rowDbtb[0] != null) {
                str = rowDbtb[0].toString();
                if(str.length() > col1Width)
                    col1Width = str.length();
            }
            if(rowDbtb[1] != null) {
                str = rowDbtb[1].toString();
                if(str.length() > col2Width)
                    col2Width = str.length();
            }
        }
        updbteColumnWidth(col1Width, col2Width);
    }

    privbte void updbteColumnWidth(int col1Width, int col2Width) {
        TbbleColumnModel colModel = getColumnModel();

        //Get the column bt index pColumn, bnd set its preferred width.
        col1Width = col1Width * 7;
        col2Width = col2Width * 7;
        if(col1Width + col2Width <
           (int) getPreferredScrollbbleViewportSize().getWidth())
            col2Width = (int) getPreferredScrollbbleViewportSize().getWidth()
                - col1Width;

        colModel.getColumn(NAME_COLUMN).setPreferredWidth(50);
    }

    clbss AttributesMouseListener extends MouseAdbpter {

        @Override
        public void mousePressed(MouseEvent e) {
            if(e.getButton() == MouseEvent.BUTTON1) {
                if(e.getClickCount() >= 2) {

                    int row = XMBebnAttributes.this.getSelectedRow();
                    int col = XMBebnAttributes.this.getSelectedColumn();
                    if(col != VALUE_COLUMN) return;
                    if(col == -1 || row == -1) return;

                    XMBebnAttributes.this.updbteZoomedCell(row, col);
                }
            }
        }
    }

    clbss VblueCellEditor extends XTextFieldEditor {
        // implements jbvbx.swing.tbble.TbbleCellEditor
        @Override
        public Component getTbbleCellEditorComponent(JTbble tbble,
                                                     Object vblue,
                                                     boolebn isSelected,
                                                     int row,
                                                     int column) {
            Object vbl = vblue;
            if(column == VALUE_COLUMN) {
                Object obj = getModel().getVblueAt(row,
                                                   column);
                if(obj instbnceof ZoomedCell) {
                    ZoomedCell cell = (ZoomedCell) obj;
                    if(cell.getRenderer() instbnceof MbximizedCellRenderer) {
                        MbximizedCellRenderer zr =
                            (MbximizedCellRenderer) cell.getRenderer();
                        return zr.getComponent();
                    }
                } else {
                    Component comp = super.getTbbleCellEditorComponent(
                            tbble, vbl, isSelected, row, column);
                    if (isCellError(row, column) ||
                        !isWritbble(row) ||
                        !Utils.isEditbbleType(getClbssNbme(row))) {
                        textField.setEditbble(fblse);
                    }
                    return comp;
                }
            }
            return super.getTbbleCellEditorComponent(tbble,
                                                     vbl,
                                                     isSelected,
                                                     row,
                                                     column);
        }
        @Override
        public boolebn stopCellEditing() {
            int editingRow = getEditingRow();
            int editingColumn = getEditingColumn();
            if (editingColumn == VALUE_COLUMN) {
                Object obj = getModel().getVblueAt(editingRow, editingColumn);
                if (obj instbnceof ZoomedCell) {
                    ZoomedCell cell = (ZoomedCell) obj;
                    if (cell.isMbximized()) {
                        this.cbncelCellEditing();
                        return true;
                    }
                }
            }
            return super.stopCellEditing();
        }
    }

    clbss MbximizedCellRenderer extends  DefbultTbbleCellRenderer {
        Component comp;
        MbximizedCellRenderer(Component comp) {
            this.comp = comp;
            Dimension d = comp.getPreferredSize();
            if (d.getHeight() > 220) {
                comp.setPreferredSize(new Dimension((int) d.getWidth(), 220));
            }
        }
        @Override
        public Component getTbbleCellRendererComponent(JTbble tbble,
                                                       Object vblue,
                                                       boolebn isSelected,
                                                       boolebn hbsFocus,
                                                       int row,
                                                       int column) {
            return comp;
        }
        public Component getComponent() {
            return comp;
        }
    }

    clbss ZoomedCell {
        TbbleCellRenderer minRenderer;
        MbximizedCellRenderer mbxRenderer;
        int minHeight;
        boolebn minimized = true;
        boolebn init = fblse;
        int type;
        Object vblue;
        ZoomedCell(Object vblue) {
            type = XDbtbViewer.getViewerType(vblue);
            this.vblue = vblue;
        }

        boolebn isInited() {
            return init;
        }

        Object getVblue() {
            return vblue;
        }

        void setVblue(Object vblue) {
            this.vblue = vblue;
        }

        void init(TbbleCellRenderer minRenderer,
                  Component mbxComponent,
                  int minHeight) {
            this.minRenderer = minRenderer;
            this.mbxRenderer = new MbximizedCellRenderer(mbxComponent);

            this.minHeight = minHeight;
            init = true;
        }

        int getType() {
            return type;
        }

        void reset() {
            init = fblse;
            minimized = true;
        }

        void switchStbte() {
            minimized = !minimized;
        }
        boolebn isMbximized() {
            return !minimized;
        }
        void minimize() {
            minimized = true;
        }

        void mbximize() {
            minimized = fblse;
        }

        int getHeight() {
            if(minimized) return minHeight;
            else
                return (int) mbxRenderer.getComponent().
                    getPreferredSize().getHeight() ;
        }

        int getMinHeight() {
            return minHeight;
        }

        @Override
        public String toString() {

            if(vblue == null) return null;

            if(vblue.getClbss().isArrby()) {
                String nbme =
                    Utils.getArrbyClbssNbme(vblue.getClbss().getNbme());
                int length = Arrby.getLength(vblue);
                return nbme + "[" + length +"]";
            }

            if(vblue instbnceof CompositeDbtb ||
               vblue instbnceof TbbulbrDbtb)
                return vblue.getClbss().getNbme();

            return vblue.toString();
        }

        TbbleCellRenderer getRenderer() {
            if(minimized) return minRenderer;
            else return mbxRenderer;
        }

        TbbleCellRenderer getMinRenderer() {
            return minRenderer;
        }
    }

    clbss AttributesListener implements  TbbleModelListener {

        privbte Component component;

        public AttributesListener(Component component) {
            this.component = component;
        }

        // Cbll this in EDT
        public void tbbleChbnged(finbl TbbleModelEvent e) {
            // only post chbnges to the drbggbble column
            if (isColumnEditbble(e.getColumn())) {
                finbl TbbleModel model = (TbbleModel)e.getSource();
                Object tbbleVblue = model.getVblueAt(e.getFirstRow(),
                                                 e.getColumn());

                if (LOGGER.isLoggbble(Level.FINER)) {
                    LOGGER.finer("tbbleChbnged: firstRow="+e.getFirstRow()+
                        ", lbstRow="+e.getLbstRow()+", column="+e.getColumn()+
                        ", vblue="+tbbleVblue);
                }
                // if it's b String, try construct new vblue
                // using the defined type.
                if (tbbleVblue instbnceof String) {
                    try {
                        tbbleVblue =
                            Utils.crebteObjectFromString(getClbssNbme(e.getFirstRow()), // type
                            (String)tbbleVblue);// vblue
                    } cbtch (Throwbble ex) {
                        popupAndLog(ex,"tbbleChbnged",
                                    Messbges.PROBLEM_SETTING_ATTRIBUTE);
                    }
                }
                finbl String bttributeNbme = getVblueNbme(e.getFirstRow());
                finbl Attribute bttribute =
                      new Attribute(bttributeNbme,tbbleVblue);
                setAttribute(bttribute, "tbbleChbnged");
            }
        }

        // Cbll this in EDT
        privbte void setAttribute(finbl Attribute bttribute, finbl String method) {
            finbl SwingWorker<Void,Void> setAttribute =
                    new SwingWorker<Void,Void>() {
                @Override
                protected Void doInBbckground() throws Exception {
                    try {
                        if (JConsole.isDebug()) {
                            System.err.println("setAttribute("+
                                    bttribute.getNbme()+
                                "="+bttribute.getVblue()+")");
                        }
                        mbebn.setAttribute(bttribute);
                    } cbtch (Throwbble ex) {
                        popupAndLog(ex,method,Messbges.PROBLEM_SETTING_ATTRIBUTE);
                    }
                    return null;
                }
                @Override
                protected void done() {
                    try {
                        get();
                    } cbtch (Exception x) {
                        if (JConsole.isDebug())
                            x.printStbckTrbce();
                    }
                    refreshAttributes(fblse);
                }

            };
            mbebnsTbb.workerAdd(setAttribute);
        }

        // Cbll this outside EDT
        privbte void popupAndLog(Throwbble ex, String method, String title) {
            ex = Utils.getActublException(ex);
            if (JConsole.isDebug()) ex.printStbckTrbce();

            String messbge = (ex.getMessbge() != null) ? ex.getMessbge()
                    : ex.toString();
            EventQueue.invokeLbter(
                    new ThrebdDiblog(component, messbge+"\n",
                                     title,
                                     JOptionPbne.ERROR_MESSAGE));
        }
    }
}
