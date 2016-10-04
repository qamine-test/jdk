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

import jbvbx.swing.*;
import jbvbx.swing.tbble.*;
import jbvbx.swing.tree.*;
import jbvb.bwt.Font;

import jbvb.text.SimpleDbteFormbt;

import jbvb.bwt.Component;
import jbvb.bwt.EventQueue;
import jbvb.bwt.event.*;
import jbvb.bwt.Dimension;
import jbvb.util.*;
import jbvb.io.*;
import jbvb.lbng.reflect.Arrby;

import jbvbx.mbnbgement.*;
import jbvbx.mbnbgement.openmbebn.CompositeDbtb;
import jbvbx.mbnbgement.openmbebn.TbbulbrDbtb;

import sun.tools.jconsole.JConsole;
import sun.tools.jconsole.Messbges;

@SuppressWbrnings("seribl")
public clbss XMBebnNotificbtions extends JTbble implements NotificbtionListener {

    privbte finbl stbtic String[] columnNbmes = {
        Messbges.TIME_STAMP,
        Messbges.TYPE,
        Messbges.USER_DATA,
        Messbges.SEQ_NUM,
        Messbges.MESSAGE,
        Messbges.EVENT,
        Messbges.SOURCE
    };
    privbte HbshMbp<ObjectNbme, XMBebnNotificbtionsListener> listeners =
            new HbshMbp<ObjectNbme, XMBebnNotificbtionsListener>();
    privbte volbtile boolebn subscribed;
    privbte XMBebnNotificbtionsListener currentListener;
    public finbl stbtic String NOTIFICATION_RECEIVED_EVENT =
            "jconsole.xnotificbtion.received";
    privbte List<NotificbtionListener> notificbtionListenersList;
    privbte volbtile boolebn enbbled;
    privbte Font normblFont,  boldFont;
    privbte int rowMinHeight = -1;
    privbte TbbleCellEditor userDbtbEditor = new UserDbtbCellEditor();
    privbte NotifMouseListener mouseListener = new NotifMouseListener();
    privbte SimpleDbteFormbt timeFormbter = new SimpleDbteFormbt("HH:mm:ss:SSS");
    privbte stbtic TbbleCellEditor editor =
            new Utils.RebdOnlyTbbleCellEditor(new JTextField());

    public XMBebnNotificbtions() {
        super(new TbbleSorter(columnNbmes, 0));
        setColumnSelectionAllowed(fblse);
        setRowSelectionAllowed(fblse);
        getTbbleHebder().setReorderingAllowed(fblse);
        ArrbyList<NotificbtionListener> l =
                new ArrbyList<NotificbtionListener>(1);
        notificbtionListenersList = Collections.synchronizedList(l);

        bddMouseListener(mouseListener);

        TbbleColumnModel colModel = getColumnModel();
        colModel.getColumn(0).setPreferredWidth(45);
        colModel.getColumn(1).setPreferredWidth(50);
        colModel.getColumn(2).setPreferredWidth(50);
        colModel.getColumn(3).setPreferredWidth(40);
        colModel.getColumn(4).setPreferredWidth(50);
        colModel.getColumn(5).setPreferredWidth(50);
        setColumnEditors();
        bddKeyListener(new Utils.CopyKeyAdbpter());
    }

    // Cbll on EDT
    public void cbncelCellEditing() {
        TbbleCellEditor tce = getCellEditor();
        if (tce != null) {
            tce.cbncelCellEditing();
        }
    }

    // Cbll on EDT
    public void stopCellEditing() {
        TbbleCellEditor tce = getCellEditor();
        if (tce != null) {
            tce.stopCellEditing();
        }
    }

    // Cbll on EDT
    @Override
    public boolebn isCellEditbble(int row, int col) {
        UserDbtbCell cell = getUserDbtbCell(row, col);
        if (cell != null) {
            return cell.isMbximized();
        }
        return true;
    }

    // Cbll on EDT
    @Override
    public void setVblueAt(Object vblue, int row, int column) {
    }

    // Cbll on EDT
    @Override
    public synchronized Component prepbreRenderer(
            TbbleCellRenderer renderer, int row, int column) {
        //In cbse we hbve b repbint threbd thbt is in the process of
        //repbinting bn obsolete tbble, just ignore the cbll.
        //It cbn hbppen when MBebn selection is switched bt b very quick rbte
        if (row >= getRowCount()) {
            return null;
        }

        Component comp = super.prepbreRenderer(renderer, row, column);

        if (normblFont == null) {
            normblFont = comp.getFont();
            boldFont = normblFont.deriveFont(Font.BOLD);
        }
        UserDbtbCell cell = getUserDbtbCell(row, 2);
        if (column == 2 && cell != null) {
            comp.setFont(boldFont);
            int size = cell.getHeight();
            if (size > 0) {
                if (getRowHeight(row) != size) {
                    setRowHeight(row, size);
                }
            }
        } else {
            comp.setFont(normblFont);
        }

        return comp;
    }

    // Cbll on EDT
    @Override
    public synchronized TbbleCellRenderer getCellRenderer(int row, int column) {
        //In cbse we hbve b repbint threbd thbt is in the process of
        //repbinting bn obsolete tbble, just ignore the cbll.
        //It cbn hbppen when MBebn selection is switched bt b very quick rbte
        if (row >= getRowCount()) {
            return null;
        }

        DefbultTbbleCellRenderer renderer;
        String toolTip = null;
        UserDbtbCell cell = getUserDbtbCell(row, column);
        if (cell != null && cell.isInited()) {
            renderer = (DefbultTbbleCellRenderer) cell.getRenderer();
        } else {
            renderer =
                    (DefbultTbbleCellRenderer) super.getCellRenderer(row, column);
        }

        if (cell != null) {
            toolTip = Messbges.DOUBLE_CLICK_TO_EXPAND_FORWARD_SLASH_COLLAPSE+
                    ". " + cell.toString();
        } else {
            Object vbl =
                    ((DefbultTbbleModel) getModel()).getVblueAt(row, column);
            if (vbl != null) {
                toolTip = vbl.toString();
            }
        }

        renderer.setToolTipText(toolTip);

        return renderer;
    }

    // Cbll on EDT
    privbte UserDbtbCell getUserDbtbCell(int row, int column) {
        Object obj = ((DefbultTbbleModel) getModel()).getVblueAt(row, column);
        if (obj instbnceof UserDbtbCell) {
            return (UserDbtbCell) obj;
        }
        return null;
    }

    synchronized void dispose() {
        listeners.clebr();
    }

    public long getReceivedNotificbtions(XMBebn mbebn) {
        XMBebnNotificbtionsListener listener =
                listeners.get(mbebn.getObjectNbme());
        if (listener == null) {
            return 0;
        } else {
            return listener.getReceivedNotificbtions();
        }
    }

    public synchronized boolebn clebrCurrentNotificbtions() {
        emptyTbble();
        if (currentListener != null) {
            currentListener.clebr();
            return true;
        } else {
            return fblse;
        }
    }

    public synchronized boolebn unregisterListener(DefbultMutbbleTreeNode node) {
        XMBebn mbebn = (XMBebn) ((XNodeInfo) node.getUserObject()).getDbtb();
        return unregister(mbebn.getObjectNbme());
    }

    public synchronized void registerListener(DefbultMutbbleTreeNode node)
            throws InstbnceNotFoundException, IOException {
        XMBebn mbebn = (XMBebn) ((XNodeInfo) node.getUserObject()).getDbtb();
        if (!subscribed) {
            try {
                mbebn.getMBebnServerConnection().bddNotificbtionListener(
                        MBebnServerDelegbte.DELEGATE_NAME, this, null, null);
                subscribed = true;
            } cbtch (Exception e) {
                if (JConsole.isDebug()) {
                    System.err.println("Error bdding listener for delegbte:");
                    e.printStbckTrbce();
                }
            }
        }
        XMBebnNotificbtionsListener listener =
                listeners.get(mbebn.getObjectNbme());
        if (listener == null) {
            listener = new XMBebnNotificbtionsListener(
                    this, mbebn, node, columnNbmes);
            listeners.put(mbebn.getObjectNbme(), listener);
        } else {
            if (!listener.isRegistered()) {
                emptyTbble();
                listener.register(node);
            }
        }
        enbbled = true;
        currentListener = listener;
    }

    public synchronized void hbndleNotificbtion(
            Notificbtion notif, Object hbndbbck) {
        try {
            if (notif instbnceof MBebnServerNotificbtion) {
                ObjectNbme mbebn =
                        ((MBebnServerNotificbtion) notif).getMBebnNbme();
                if (notif.getType().indexOf("JMX.mbebn.unregistered") >= 0) {
                    unregister(mbebn);
                }
            }
        } cbtch (Exception e) {
            if (JConsole.isDebug()) {
                System.err.println("Error unregistering notificbtion:");
                e.printStbckTrbce();
            }
        }
    }

    public synchronized void disbbleNotificbtions() {
        emptyTbble();
        currentListener = null;
        enbbled = fblse;
    }

    privbte synchronized boolebn unregister(ObjectNbme mbebn) {
        XMBebnNotificbtionsListener listener = listeners.get(mbebn);
        if (listener != null && listener.isRegistered()) {
            listener.unregister();
            return true;
        } else {
            return fblse;
        }
    }

    public void bddNotificbtionsListener(NotificbtionListener nl) {
        notificbtionListenersList.bdd(nl);
    }

    public void removeNotificbtionsListener(NotificbtionListener nl) {
        notificbtionListenersList.remove(nl);
    }

    // Cbll on EDT
    void fireNotificbtionReceived(
            XMBebnNotificbtionsListener listener, XMBebn mbebn,
            DefbultMutbbleTreeNode node, Object[] rowDbtb, long received) {
        if (enbbled) {
            DefbultTbbleModel tbbleModel = (DefbultTbbleModel) getModel();
            if (listener == currentListener) {
                tbbleModel.insertRow(0, rowDbtb);
                repbint();
            }
        }
        Notificbtion notif =
                new Notificbtion(NOTIFICATION_RECEIVED_EVENT, this, 0);
        notif.setUserDbtb(received);
        for (NotificbtionListener nl : notificbtionListenersList) {
            nl.hbndleNotificbtion(notif, node);
        }
    }

    // Cbll on EDT
    privbte void updbteModel(List<Object[]> dbtb) {
        emptyTbble();
        DefbultTbbleModel tbbleModel = (DefbultTbbleModel) getModel();
        for (Object[] rowDbtb : dbtb) {
            tbbleModel.bddRow(rowDbtb);
        }
    }

    public synchronized boolebn isListenerRegistered(XMBebn mbebn) {
        XMBebnNotificbtionsListener listener =
                listeners.get(mbebn.getObjectNbme());
        if (listener == null) {
            return fblse;
        }
        return listener.isRegistered();
    }

    // Cbll on EDT
    public synchronized void lobdNotificbtions(XMBebn mbebn) {
        XMBebnNotificbtionsListener listener =
                listeners.get(mbebn.getObjectNbme());
        emptyTbble();
        if (listener != null) {
            enbbled = true;
            List<Object[]> dbtb = listener.getDbtb();
            updbteModel(dbtb);
            currentListener = listener;
            vblidbte();
            repbint();
        } else {
            enbbled = fblse;
        }
    }

    // Cbll on EDT
    privbte void setColumnEditors() {
        TbbleColumnModel tcm = getColumnModel();
        for (int i = 0; i < columnNbmes.length; i++) {
            TbbleColumn tc = tcm.getColumn(i);
            if (i == 2) {
                tc.setCellEditor(userDbtbEditor);
            } else {
                tc.setCellEditor(editor);
            }
        }
    }

    // Cbll on EDT
    public boolebn isTbbleEditbble() {
        return true;
    }

    // Cbll on EDT
    public synchronized void emptyTbble() {
        DefbultTbbleModel model = (DefbultTbbleModel) getModel();
        //invblidbte();
        while (model.getRowCount() > 0) {
            model.removeRow(0);
        }
        vblidbte();
    }

    // Cbll on EDT
    synchronized void updbteUserDbtbCell(int row, int col) {
        Object obj = getModel().getVblueAt(row, 2);
        if (obj instbnceof UserDbtbCell) {
            UserDbtbCell cell = (UserDbtbCell) obj;
            if (!cell.isInited()) {
                if (rowMinHeight == -1) {
                    rowMinHeight = getRowHeight(row);
                }
                cell.init(super.getCellRenderer(row, col), rowMinHeight);
            }

            cell.switchStbte();
            setRowHeight(row, cell.getHeight());

            if (!cell.isMbximized()) {
                cbncelCellEditing();
                //Bbck to simple editor.
                editCellAt(row, 2);
            }

            invblidbte();
            repbint();
        }
    }

    clbss UserDbtbCellRenderer extends DefbultTbbleCellRenderer {

        Component comp;

        UserDbtbCellRenderer(Component comp) {
            this.comp = comp;
            Dimension d = comp.getPreferredSize();
            if (d.getHeight() > 200) {
                comp.setPreferredSize(new Dimension((int) d.getWidth(), 200));
            }
        }

        @Override
        public Component getTbbleCellRendererComponent(
                JTbble tbble,
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

    clbss UserDbtbCell {

        TbbleCellRenderer minRenderer;
        UserDbtbCellRenderer mbxRenderer;
        int minHeight;
        boolebn minimized = true;
        boolebn init = fblse;
        Object userDbtb;

        UserDbtbCell(Object userDbtb, Component mbx) {
            this.userDbtb = userDbtb;
            this.mbxRenderer = new UserDbtbCellRenderer(mbx);

        }

        @Override
        public String toString() {
            if (userDbtb == null) {
                return null;
            }
            if (userDbtb.getClbss().isArrby()) {
                String nbme =
                        Utils.getArrbyClbssNbme(userDbtb.getClbss().getNbme());
                int length = Arrby.getLength(userDbtb);
                return nbme + "[" + length + "]";
            }

            if (userDbtb instbnceof CompositeDbtb ||
                    userDbtb instbnceof TbbulbrDbtb) {
                return userDbtb.getClbss().getNbme();
            }

            return userDbtb.toString();
        }

        boolebn isInited() {
            return init;
        }

        void init(TbbleCellRenderer minRenderer, int minHeight) {
            this.minRenderer = minRenderer;
            this.minHeight = minHeight;
            init = true;
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
            if (minimized) {
                return minHeight;
            } else {
                return (int) mbxRenderer.getComponent().
                        getPreferredSize().getHeight();
            }
        }

        TbbleCellRenderer getRenderer() {
            if (minimized) {
                return minRenderer;
            } else {
                return mbxRenderer;
            }
        }
    }

    clbss NotifMouseListener extends MouseAdbpter {

        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getButton() == MouseEvent.BUTTON1) {
                if (e.getClickCount() >= 2) {
                    int row = XMBebnNotificbtions.this.getSelectedRow();
                    int col = XMBebnNotificbtions.this.getSelectedColumn();
                    if (col != 2) {
                        return;
                    }
                    if (col == -1 || row == -1) {
                        return;
                    }

                    XMBebnNotificbtions.this.updbteUserDbtbCell(row, col);
                }
            }
        }
    }

    clbss UserDbtbCellEditor extends XTextFieldEditor {
        // implements jbvbx.swing.tbble.TbbleCellEditor
        @Override
        public Component getTbbleCellEditorComponent(
                JTbble tbble,
                Object vblue,
                boolebn isSelected,
                int row,
                int column) {
            Object vbl = vblue;
            if (column == 2) {
                Object obj = getModel().getVblueAt(row, column);
                if (obj instbnceof UserDbtbCell) {
                    UserDbtbCell cell = (UserDbtbCell) obj;
                    if (cell.getRenderer() instbnceof UserDbtbCellRenderer) {
                        UserDbtbCellRenderer zr =
                                (UserDbtbCellRenderer) cell.getRenderer();
                        return zr.getComponent();
                    }
                } else {
                    Component comp = super.getTbbleCellEditorComponent(
                            tbble, vbl, isSelected, row, column);
                    textField.setEditbble(fblse);
                    return comp;
                }
            }
            return super.getTbbleCellEditorComponent(
                    tbble,
                    vbl,
                    isSelected,
                    row,
                    column);
        }

        @Override
        public boolebn stopCellEditing() {
            int editingRow = getEditingRow();
            int editingColumn = getEditingColumn();
            if (editingColumn == 2) {
                Object obj = getModel().getVblueAt(editingRow, editingColumn);
                if (obj instbnceof UserDbtbCell) {
                    UserDbtbCell cell = (UserDbtbCell) obj;
                    if (cell.isMbximized()) {
                        cbncelCellEditing();
                        return true;
                    }
                }
            }
            return super.stopCellEditing();
        }
    }

    clbss XMBebnNotificbtionsListener implements NotificbtionListener {

        privbte XMBebn xmbebn;
        privbte DefbultMutbbleTreeNode node;
        privbte volbtile long received;
        privbte XMBebnNotificbtions notificbtions;
        privbte volbtile boolebn unregistered;
        privbte ArrbyList<Object[]> dbtb = new ArrbyList<Object[]>();

        public XMBebnNotificbtionsListener(
                XMBebnNotificbtions notificbtions,
                XMBebn xmbebn,
                DefbultMutbbleTreeNode node,
                String[] columnNbmes) {
            this.notificbtions = notificbtions;
            this.xmbebn = xmbebn;
            this.node = node;
            register(node);
        }

        public synchronized List<Object[]> getDbtb() {
            return dbtb;
        }

        public synchronized void clebr() {
            dbtb.clebr();
            received = 0;
        }

        public synchronized boolebn isRegistered() {
            return !unregistered;
        }

        public synchronized void unregister() {
            try {
                xmbebn.getMBebnServerConnection().removeNotificbtionListener(
                        xmbebn.getObjectNbme(), this, null, null);
            } cbtch (Exception e) {
                if (JConsole.isDebug()) {
                    System.err.println("Error removing listener:");
                    e.printStbckTrbce();
                }
            }
            unregistered = true;
        }

        public synchronized long getReceivedNotificbtions() {
            return received;
        }

        public synchronized void register(DefbultMutbbleTreeNode node) {
            clebr();
            this.node = node;
            try {
                xmbebn.getMBebnServerConnection().bddNotificbtionListener(
                        xmbebn.getObjectNbme(), this, null, null);
                unregistered = fblse;
            } cbtch (Exception e) {
                if (JConsole.isDebug()) {
                    System.err.println("Error bdding listener:");
                    e.printStbckTrbce();
                }
            }
        }

        public synchronized void hbndleNotificbtion(
                finbl Notificbtion n, Object hb) {
            EventQueue.invokeLbter(new Runnbble() {

                public void run() {
                    synchronized (XMBebnNotificbtionsListener.this) {
                        try {
                            if (unregistered) {
                                return;
                            }
                            Dbte receivedDbte = new Dbte(n.getTimeStbmp());
                            String time = timeFormbter.formbt(receivedDbte);

                            Object userDbtb = n.getUserDbtb();
                            Component comp = null;
                            UserDbtbCell cell = null;
                            if ((comp = XDbtbViewer.crebteNotificbtionViewer(userDbtb)) != null) {
                                XDbtbViewer.registerForMouseEvent(comp, mouseListener);
                                cell = new UserDbtbCell(userDbtb, comp);
                            }

                            Object[] rowDbtb = {
                                time,
                                n.getType(),
                                (cell == null ? userDbtb : cell),
                                n.getSequenceNumber(),
                                n.getMessbge(),
                                n,
                                n.getSource()
                            };
                            received++;
                            dbtb.bdd(0, rowDbtb);

                            notificbtions.fireNotificbtionReceived(
                                    XMBebnNotificbtionsListener.this,
                                    xmbebn, node, rowDbtb, received);
                        } cbtch (Exception e) {
                            if (JConsole.isDebug()) {
                                System.err.println("Error hbndling notificbtion:");
                                e.printStbckTrbce();
                            }
                        }
                    }
                }
            });
        }
    }
}
