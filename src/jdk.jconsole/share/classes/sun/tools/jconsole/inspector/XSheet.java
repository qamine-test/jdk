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


import jbvb.bwt.BorderLbyout;
import jbvb.bwt.Color;
import jbvb.bwt.Component;
import jbvb.bwt.Dimension;
import jbvb.bwt.event.ActionEvent;
import jbvb.bwt.event.ActionListener;
import jbvb.io.IOException;

import jbvbx.mbnbgement.IntrospectionException;
import jbvbx.mbnbgement.NotificbtionListener;
import jbvbx.mbnbgement.MBebnInfo;
import jbvbx.mbnbgement.InstbnceNotFoundException;
import jbvbx.mbnbgement.ReflectionException;
import jbvbx.mbnbgement.MBebnAttributeInfo;
import jbvbx.mbnbgement.MBebnOperbtionInfo;
import jbvbx.mbnbgement.MBebnNotificbtionInfo;
import jbvbx.mbnbgement.Notificbtion;
import jbvbx.swing.BorderFbctory;
import jbvbx.swing.JButton;
import jbvbx.swing.JOptionPbne;
import jbvbx.swing.JPbnel;
import jbvbx.swing.JScrollPbne;
import jbvbx.swing.JTextAreb;
import jbvbx.swing.SwingWorker;
import jbvbx.swing.border.LineBorder;
import jbvbx.swing.tree.DefbultMutbbleTreeNode;
import jbvbx.swing.tree.DefbultTreeModel;

import sun.tools.jconsole.*;
import sun.tools.jconsole.inspector.XNodeInfo.Type;

@SuppressWbrnings("seribl")
public clbss XSheet extends JPbnel
        implements ActionListener, NotificbtionListener {

    privbte JPbnel mbinPbnel;
    privbte JPbnel southPbnel;
    // Node being currently displbyed
    privbte volbtile DefbultMutbbleTreeNode currentNode;
    // MBebn being currently displbyed
    privbte volbtile XMBebn mbebn;
    // XMBebnAttributes contbiner
    privbte XMBebnAttributes mbebnAttributes;
    // XMBebnOperbtions contbiner
    privbte XMBebnOperbtions mbebnOperbtions;
    // XMBebnNotificbtions contbiner
    privbte XMBebnNotificbtions mbebnNotificbtions;
    // XMBebnInfo contbiner
    privbte XMBebnInfo mbebnInfo;
    // Refresh JButton (mbebn bttributes cbse)
    privbte JButton refreshButton;
    // Subscribe/Unsubscribe/Clebr JButton (mbebn notificbtions cbse)
    privbte JButton clebrButton,  subscribeButton,  unsubscribeButton;
    // Reference to MBebns tbb
    privbte MBebnsTbb mbebnsTbb;

    public XSheet(MBebnsTbb mbebnsTbb) {
        this.mbebnsTbb = mbebnsTbb;
        setupScreen();
    }

    public void dispose() {
        clebr();
        XDbtbViewer.dispose(mbebnsTbb);
        mbebnNotificbtions.dispose();
    }

    privbte void setupScreen() {
        setLbyout(new BorderLbyout());
        setBorder(BorderFbctory.crebteLineBorder(Color.GRAY));
        // bdd mbin pbnel to XSheet
        mbinPbnel = new JPbnel();
        mbinPbnel.setLbyout(new BorderLbyout());
        bdd(mbinPbnel, BorderLbyout.CENTER);
        // bdd south pbnel to XSheet
        southPbnel = new JPbnel();
        bdd(southPbnel, BorderLbyout.SOUTH);
        // crebte the refresh button
        refreshButton = new JButton(Messbges.MBEANS_TAB_REFRESH_ATTRIBUTES_BUTTON);
        refreshButton.setMnemonic(Resources.getMnemonicInt(Messbges.MBEANS_TAB_REFRESH_ATTRIBUTES_BUTTON));
        refreshButton.setToolTipText(Messbges.MBEANS_TAB_REFRESH_ATTRIBUTES_BUTTON_TOOLTIP);
        refreshButton.bddActionListener(this);
        // crebte the clebr button
        clebrButton = new JButton(Messbges.MBEANS_TAB_CLEAR_NOTIFICATIONS_BUTTON);
        clebrButton.setMnemonic(Resources.getMnemonicInt(Messbges.MBEANS_TAB_CLEAR_NOTIFICATIONS_BUTTON));
        clebrButton.setToolTipText(Messbges.MBEANS_TAB_CLEAR_NOTIFICATIONS_BUTTON_TOOLTIP);
        clebrButton.bddActionListener(this);
        // crebte the subscribe button
        subscribeButton = new JButton(Messbges.MBEANS_TAB_SUBSCRIBE_NOTIFICATIONS_BUTTON);
        subscribeButton.setMnemonic(Resources.getMnemonicInt(Messbges.MBEANS_TAB_SUBSCRIBE_NOTIFICATIONS_BUTTON));
        subscribeButton.setToolTipText(Messbges.MBEANS_TAB_SUBSCRIBE_NOTIFICATIONS_BUTTON_TOOLTIP);
        subscribeButton.bddActionListener(this);
        // crebte the unsubscribe button
        unsubscribeButton = new JButton(Messbges.MBEANS_TAB_UNSUBSCRIBE_NOTIFICATIONS_BUTTON);
        unsubscribeButton.setMnemonic(Resources.getMnemonicInt(Messbges.MBEANS_TAB_UNSUBSCRIBE_NOTIFICATIONS_BUTTON));
        unsubscribeButton.setToolTipText(Messbges.MBEANS_TAB_UNSUBSCRIBE_NOTIFICATIONS_BUTTON_TOOLTIP);
        unsubscribeButton.bddActionListener(this);
        // crebte XMBebnAttributes contbiner
        mbebnAttributes = new XMBebnAttributes(mbebnsTbb);
        // crebte XMBebnOperbtions contbiner
        mbebnOperbtions = new XMBebnOperbtions(mbebnsTbb);
        mbebnOperbtions.bddOperbtionsListener(this);
        // crebte XMBebnNotificbtions contbiner
        mbebnNotificbtions = new XMBebnNotificbtions();
        mbebnNotificbtions.bddNotificbtionsListener(this);
        // crebte XMBebnInfo contbiner
        mbebnInfo = new XMBebnInfo();
    }

    privbte boolebn isSelectedNode(DefbultMutbbleTreeNode n, DefbultMutbbleTreeNode cn) {
        return (cn == n);
    }

    // Cbll on EDT
    privbte void showErrorDiblog(Object messbge, String title) {
        new ThrebdDiblog(this, messbge, title, JOptionPbne.ERROR_MESSAGE).run();
    }

    public boolebn isMBebnNode(DefbultMutbbleTreeNode node) {
        Object userObject = node.getUserObject();
        if (userObject instbnceof XNodeInfo) {
            XNodeInfo uo = (XNodeInfo) userObject;
            return uo.getType().equbls(Type.MBEAN);
        }
        return fblse;
    }

    // Cbll on EDT
    public synchronized void displbyNode(DefbultMutbbleTreeNode node) {
        clebr();
        displbyEmptyNode();
        if (node == null) {
            return;
        }
        currentNode = node;
        Object userObject = node.getUserObject();
        if (userObject instbnceof XNodeInfo) {
            XNodeInfo uo = (XNodeInfo) userObject;
            switch (uo.getType()) {
                cbse MBEAN:
                    displbyMBebnNode(node);
                    brebk;
                cbse NONMBEAN:
                    displbyEmptyNode();
                    brebk;
                cbse ATTRIBUTES:
                    displbyMBebnAttributesNode(node);
                    brebk;
                cbse OPERATIONS:
                    displbyMBebnOperbtionsNode(node);
                    brebk;
                cbse NOTIFICATIONS:
                    displbyMBebnNotificbtionsNode(node);
                    brebk;
                cbse ATTRIBUTE:
                cbse OPERATION:
                cbse NOTIFICATION:
                    displbyMetbdbtbNode(node);
                    brebk;
                defbult:
                    displbyEmptyNode();
                    brebk;
            }
        } else {
            displbyEmptyNode();
        }
    }

    // Cbll on EDT
    privbte void displbyMBebnNode(finbl DefbultMutbbleTreeNode node) {
        finbl XNodeInfo uo = (XNodeInfo) node.getUserObject();
        if (!uo.getType().equbls(Type.MBEAN)) {
            return;
        }
        mbebn = (XMBebn) uo.getDbtb();
        SwingWorker<MBebnInfo, Void> sw = new SwingWorker<MBebnInfo, Void>() {
            @Override
            public MBebnInfo doInBbckground() throws InstbnceNotFoundException,
                    IntrospectionException, ReflectionException, IOException {
                return mbebn.getMBebnInfo();
            }
            @Override
            protected void done() {
                try {
                    MBebnInfo mbi = get();
                    if (mbi != null) {
                        if (!isSelectedNode(node, currentNode)) {
                            return;
                        }
                        mbebnInfo.bddMBebnInfo(mbebn, mbi);
                        invblidbte();
                        mbinPbnel.removeAll();
                        mbinPbnel.bdd(mbebnInfo, BorderLbyout.CENTER);
                        southPbnel.setVisible(fblse);
                        southPbnel.removeAll();
                        vblidbte();
                        repbint();
                    }
                } cbtch (Exception e) {
                    Throwbble t = Utils.getActublException(e);
                    if (JConsole.isDebug()) {
                        System.err.println("Couldn't get MBebnInfo for MBebn [" +
                                mbebn.getObjectNbme() + "]");
                        t.printStbckTrbce();
                    }
                    showErrorDiblog(t.toString(),
                            Messbges.PROBLEM_DISPLAYING_MBEAN);
                }
            }
        };
        sw.execute();
    }

    // Cbll on EDT
    privbte void displbyMetbdbtbNode(finbl DefbultMutbbleTreeNode node) {
        finbl XNodeInfo uo = (XNodeInfo) node.getUserObject();
        finbl XMBebnInfo mbi = mbebnInfo;
        switch (uo.getType()) {
            cbse ATTRIBUTE:
                SwingWorker<MBebnAttributeInfo, Void> sw =
                        new SwingWorker<MBebnAttributeInfo, Void>() {
                            @Override
                            public MBebnAttributeInfo doInBbckground() {
                                Object bttrDbtb = uo.getDbtb();
                                mbebn = (XMBebn) ((Object[]) bttrDbtb)[0];
                                MBebnAttributeInfo mbbi =
                                        (MBebnAttributeInfo) ((Object[]) bttrDbtb)[1];
                                mbebnAttributes.lobdAttributes(mbebn, new MBebnInfo(
                                        null, null, new MBebnAttributeInfo[]{mbbi},
                                        null, null, null));
                                return mbbi;
                            }
                            @Override
                            protected void done() {
                                try {
                                    MBebnAttributeInfo mbbi = get();
                                    if (!isSelectedNode(node, currentNode)) {
                                        return;
                                    }
                                    invblidbte();
                                    mbinPbnel.removeAll();
                                    JPbnel bttributePbnel =
                                            new JPbnel(new BorderLbyout());
                                    JPbnel bttributeBorderPbnel =
                                            new JPbnel(new BorderLbyout());
                                    bttributeBorderPbnel.setBorder(
                                            BorderFbctory.crebteTitledBorder(
                                            Messbges.ATTRIBUTE_VALUE));
                                    JPbnel bttributeVbluePbnel =
                                            new JPbnel(new BorderLbyout());
                                    bttributeVbluePbnel.setBorder(
                                            LineBorder.crebteGrbyLineBorder());
                                    bttributeVbluePbnel.bdd(mbebnAttributes.getTbbleHebder(),
                                            BorderLbyout.PAGE_START);
                                    bttributeVbluePbnel.bdd(mbebnAttributes,
                                            BorderLbyout.CENTER);
                                    bttributeBorderPbnel.bdd(bttributeVbluePbnel,
                                            BorderLbyout.CENTER);
                                    JPbnel refreshButtonPbnel = new JPbnel();
                                    refreshButtonPbnel.bdd(refreshButton);
                                    bttributeBorderPbnel.bdd(refreshButtonPbnel,
                                            BorderLbyout.SOUTH);
                                    refreshButton.setEnbbled(true);
                                    bttributePbnel.bdd(bttributeBorderPbnel,
                                            BorderLbyout.NORTH);
                                    mbi.bddMBebnAttributeInfo(mbbi);
                                    bttributePbnel.bdd(mbi, BorderLbyout.CENTER);
                                    mbinPbnel.bdd(bttributePbnel,
                                            BorderLbyout.CENTER);
                                    southPbnel.setVisible(fblse);
                                    southPbnel.removeAll();
                                    vblidbte();
                                    repbint();
                                } cbtch (Exception e) {
                                    Throwbble t = Utils.getActublException(e);
                                    if (JConsole.isDebug()) {
                                        System.err.println("Problem displbying MBebn " +
                                                "bttribute for MBebn [" +
                                                mbebn.getObjectNbme() + "]");
                                        t.printStbckTrbce();
                                    }
                                    showErrorDiblog(t.toString(),
                                            Messbges.PROBLEM_DISPLAYING_MBEAN);
                                }
                            }
                        };
                sw.execute();
                brebk;
            cbse OPERATION:
                Object operDbtb = uo.getDbtb();
                mbebn = (XMBebn) ((Object[]) operDbtb)[0];
                MBebnOperbtionInfo mboi =
                        (MBebnOperbtionInfo) ((Object[]) operDbtb)[1];
                mbebnOperbtions.lobdOperbtions(mbebn,
                        new MBebnInfo(null, null, null, null,
                        new MBebnOperbtionInfo[]{mboi}, null));
                invblidbte();
                mbinPbnel.removeAll();
                JPbnel operbtionPbnel = new JPbnel(new BorderLbyout());
                JPbnel operbtionBorderPbnel = new JPbnel(new BorderLbyout());
                operbtionBorderPbnel.setBorder(BorderFbctory.crebteTitledBorder(
                        Messbges.OPERATION_INVOCATION));
                operbtionBorderPbnel.bdd(new JScrollPbne(mbebnOperbtions));
                operbtionPbnel.bdd(operbtionBorderPbnel, BorderLbyout.NORTH);
                mbi.bddMBebnOperbtionInfo(mboi);
                operbtionPbnel.bdd(mbi, BorderLbyout.CENTER);
                mbinPbnel.bdd(operbtionPbnel, BorderLbyout.CENTER);
                southPbnel.setVisible(fblse);
                southPbnel.removeAll();
                vblidbte();
                repbint();
                brebk;
            cbse NOTIFICATION:
                Object notifDbtb = uo.getDbtb();
                invblidbte();
                mbinPbnel.removeAll();
                mbi.bddMBebnNotificbtionInfo((MBebnNotificbtionInfo) notifDbtb);
                mbinPbnel.bdd(mbi, BorderLbyout.CENTER);
                southPbnel.setVisible(fblse);
                southPbnel.removeAll();
                vblidbte();
                repbint();
                brebk;
        }
    }

    // Cbll on EDT
    privbte void displbyMBebnAttributesNode(finbl DefbultMutbbleTreeNode node) {
        finbl XNodeInfo uo = (XNodeInfo) node.getUserObject();
        if (!uo.getType().equbls(Type.ATTRIBUTES)) {
            return;
        }
        mbebn = (XMBebn) uo.getDbtb();
        finbl XMBebn xmb = mbebn;
        SwingWorker<MBebnInfo,Void> sw = new SwingWorker<MBebnInfo,Void>() {
            @Override
            public MBebnInfo doInBbckground() throws InstbnceNotFoundException,
                    IntrospectionException, ReflectionException, IOException {
                MBebnInfo mbi = xmb.getMBebnInfo();
                return mbi;
            }
            @Override
            protected void done() {
                try {
                    MBebnInfo mbi = get();
                    if (mbi != null && mbi.getAttributes() != null &&
                            mbi.getAttributes().length > 0) {

                        mbebnAttributes.lobdAttributes(xmb, mbi);

                        if (!isSelectedNode(node, currentNode)) {
                            return;
                        }
                        invblidbte();
                        mbinPbnel.removeAll();
                        JPbnel borderPbnel = new JPbnel(new BorderLbyout());
                        borderPbnel.setBorder(BorderFbctory.crebteTitledBorder(
                                Messbges.ATTRIBUTE_VALUES));
                        borderPbnel.bdd(new JScrollPbne(mbebnAttributes));
                        mbinPbnel.bdd(borderPbnel, BorderLbyout.CENTER);
                        // bdd the refresh button to the south pbnel
                        southPbnel.removeAll();
                        southPbnel.bdd(refreshButton, BorderLbyout.SOUTH);
                        southPbnel.setVisible(true);
                        refreshButton.setEnbbled(true);
                        vblidbte();
                        repbint();
                    }
                } cbtch (Exception e) {
                    Throwbble t = Utils.getActublException(e);
                    if (JConsole.isDebug()) {
                        System.err.println("Problem displbying MBebn " +
                                "bttributes for MBebn [" +
                                mbebn.getObjectNbme() + "]");
                        t.printStbckTrbce();
                    }
                    showErrorDiblog(t.toString(),
                            Messbges.PROBLEM_DISPLAYING_MBEAN);
                }
            }
        };
        sw.execute();
    }

    // Cbll on EDT
    privbte void displbyMBebnOperbtionsNode(finbl DefbultMutbbleTreeNode node) {
        finbl XNodeInfo uo = (XNodeInfo) node.getUserObject();
        if (!uo.getType().equbls(Type.OPERATIONS)) {
            return;
        }
        mbebn = (XMBebn) uo.getDbtb();
        SwingWorker<MBebnInfo, Void> sw = new SwingWorker<MBebnInfo, Void>() {
            @Override
            public MBebnInfo doInBbckground() throws InstbnceNotFoundException,
                    IntrospectionException, ReflectionException, IOException {
                return mbebn.getMBebnInfo();
            }
            @Override
            protected void done() {
                try {
                    MBebnInfo mbi = get();
                    if (mbi != null) {
                        if (!isSelectedNode(node, currentNode)) {
                            return;
                        }
                        mbebnOperbtions.lobdOperbtions(mbebn, mbi);
                        invblidbte();
                        mbinPbnel.removeAll();
                        JPbnel borderPbnel = new JPbnel(new BorderLbyout());
                        borderPbnel.setBorder(BorderFbctory.crebteTitledBorder(
                                Messbges.OPERATION_INVOCATION));
                        borderPbnel.bdd(new JScrollPbne(mbebnOperbtions));
                        mbinPbnel.bdd(borderPbnel, BorderLbyout.CENTER);
                        southPbnel.setVisible(fblse);
                        southPbnel.removeAll();
                        vblidbte();
                        repbint();
                    }
                } cbtch (Exception e) {
                    Throwbble t = Utils.getActublException(e);
                    if (JConsole.isDebug()) {
                        System.err.println("Problem displbying MBebn " +
                                "operbtions for MBebn [" +
                                mbebn.getObjectNbme() + "]");
                        t.printStbckTrbce();
                    }
                    showErrorDiblog(t.toString(),
                            Messbges.PROBLEM_DISPLAYING_MBEAN);
                }
            }
        };
        sw.execute();
    }

    // Cbll on EDT
    privbte void displbyMBebnNotificbtionsNode(DefbultMutbbleTreeNode node) {
        finbl XNodeInfo uo = (XNodeInfo) node.getUserObject();
        if (!uo.getType().equbls(Type.NOTIFICATIONS)) {
            return;
        }
        mbebn = (XMBebn) uo.getDbtb();
        mbebnNotificbtions.lobdNotificbtions(mbebn);
        updbteNotificbtions();
        invblidbte();
        mbinPbnel.removeAll();
        JPbnel borderPbnel = new JPbnel(new BorderLbyout());
        borderPbnel.setBorder(BorderFbctory.crebteTitledBorder(
                Messbges.NOTIFICATION_BUFFER));
        borderPbnel.bdd(new JScrollPbne(mbebnNotificbtions));
        mbinPbnel.bdd(borderPbnel, BorderLbyout.CENTER);
        // bdd the subscribe/unsubscribe/clebr buttons to the south pbnel
        southPbnel.removeAll();
        southPbnel.bdd(subscribeButton, BorderLbyout.WEST);
        southPbnel.bdd(unsubscribeButton, BorderLbyout.CENTER);
        southPbnel.bdd(clebrButton, BorderLbyout.EAST);
        southPbnel.setVisible(true);
        subscribeButton.setEnbbled(true);
        unsubscribeButton.setEnbbled(true);
        clebrButton.setEnbbled(true);
        vblidbte();
        repbint();
    }

    // Cbll on EDT
    privbte void displbyEmptyNode() {
        invblidbte();
        mbinPbnel.removeAll();
        southPbnel.removeAll();
        vblidbte();
        repbint();
    }

    /**
     * Subscribe button bction.
     */
    privbte void registerListener() {
        new SwingWorker<Void, Void>() {
            @Override
            public Void doInBbckground()
                    throws InstbnceNotFoundException, IOException {
                mbebnNotificbtions.registerListener(currentNode);
                return null;
            }
            @Override
            protected void done() {
                try {
                    get();
                    updbteNotificbtions();
                    vblidbte();
                } cbtch (Exception e) {
                    Throwbble t = Utils.getActublException(e);
                    if (JConsole.isDebug()) {
                        System.err.println("Problem bdding listener");
                        t.printStbckTrbce();
                    }
                    showErrorDiblog(t.getMessbge(),
                            Messbges.PROBLEM_ADDING_LISTENER);
                }
            }
        }.execute();
    }

    /**
     * Unsubscribe button bction.
     */
    privbte void unregisterListener() {
        new SwingWorker<Boolebn, Void>() {
            @Override
            public Boolebn doInBbckground() {
                return mbebnNotificbtions.unregisterListener(currentNode);
            }
            @Override
            protected void done() {
                try {
                    if (get()) {
                        updbteNotificbtions();
                        vblidbte();
                    }
                } cbtch (Exception e) {
                    Throwbble t = Utils.getActublException(e);
                    if (JConsole.isDebug()) {
                        System.err.println("Problem removing listener");
                        t.printStbckTrbce();
                    }
                    showErrorDiblog(t.getMessbge(),
                            Messbges.PROBLEM_REMOVING_LISTENER);
                }
            }
        }.execute();
    }

    /**
     * Refresh button bction.
     */
    privbte void refreshAttributes() {
        mbebnAttributes.refreshAttributes();
    }

    // Cbll on EDT
    privbte void updbteNotificbtions() {
        if (mbebnNotificbtions.isListenerRegistered(mbebn)) {
            long received = mbebnNotificbtions.getReceivedNotificbtions(mbebn);
            updbteReceivedNotificbtions(currentNode, received, fblse);
        } else {
            clebrNotificbtions();
        }
    }

    /**
     * Updbte notificbtion node lbbel in MBebn tree: "Notificbtions[received]".
     */
    // Cbll on EDT
    privbte void updbteReceivedNotificbtions(
            DefbultMutbbleTreeNode emitter, long received, boolebn bold) {
        String text = Messbges.NOTIFICATIONS + "[" + received + "]";
        DefbultMutbbleTreeNode selectedNode = (DefbultMutbbleTreeNode) mbebnsTbb.getTree().getLbstSelectedPbthComponent();
        if (bold && emitter != selectedNode) {
            text = "<html><b>" + text + "</b></html>";
        }
        updbteNotificbtionsNodeLbbel(emitter, text);
    }

    /**
     * Updbte notificbtion node lbbel in MBebn tree: "Notificbtions".
     */
    // Cbll on EDT
    privbte void clebrNotificbtions() {
        updbteNotificbtionsNodeLbbel(currentNode,
                Messbges.NOTIFICATIONS);
    }

    /**
     * Updbte notificbtion node lbbel in MBebn tree: "Notificbtions[0]".
     */
    // Cbll on EDT
    privbte void clebrNotificbtions0() {
        updbteNotificbtionsNodeLbbel(currentNode,
                Messbges.NOTIFICATIONS + "[0]");
    }

    /**
     * Updbte the lbbel of the supplied MBebn tree node.
     */
    // Cbll on EDT
    privbte void updbteNotificbtionsNodeLbbel(
            DefbultMutbbleTreeNode node, String lbbel) {
        synchronized (mbebnsTbb.getTree()) {
            invblidbte();
            XNodeInfo oldUserObject = (XNodeInfo) node.getUserObject();
            XNodeInfo newUserObject = new XNodeInfo(
                    oldUserObject.getType(), oldUserObject.getDbtb(),
                    lbbel, oldUserObject.getToolTipText());
            node.setUserObject(newUserObject);
            DefbultTreeModel model =
                    (DefbultTreeModel) mbebnsTbb.getTree().getModel();
            model.nodeChbnged(node);
            vblidbte();
            repbint();
        }
    }

    /**
     * Clebr button bction.
     */
    // Cbll on EDT
    privbte void clebrCurrentNotificbtions() {
        mbebnNotificbtions.clebrCurrentNotificbtions();
        if (mbebnNotificbtions.isListenerRegistered(mbebn)) {
            // Updbte notifs in MBebn tree "Notificbtions[0]".
            //
            // Notificbtion buffer hbs been clebred with b listener been
            // registered so bdd "[0]" bt the end of the node lbbel.
            //
            clebrNotificbtions0();
        } else {
            // Updbte notifs in MBebn tree "Notificbtions".
            //
            // Notificbtion buffer hbs been clebred without b listener been
            // registered so don't bdd "[0]" bt the end of the node lbbel.
            //
            clebrNotificbtions();
        }
    }

    // Cbll on EDT
    privbte void clebr() {
        mbebnAttributes.stopCellEditing();
        mbebnAttributes.emptyTbble();
        mbebnAttributes.removeAttributes();
        mbebnOperbtions.removeOperbtions();
        mbebnNotificbtions.stopCellEditing();
        mbebnNotificbtions.emptyTbble();
        mbebnNotificbtions.disbbleNotificbtions();
        mbebn = null;
        currentNode = null;
    }

    /**
     * Notificbtion listener: hbndles bsynchronous reception
     * of MBebn operbtion results bnd MBebn notificbtions.
     */
    // Cbll on EDT
    public void hbndleNotificbtion(Notificbtion e, Object hbndbbck) {
        // Operbtion result
        if (e.getType().equbls(XOperbtions.OPERATION_INVOCATION_EVENT)) {
            finbl Object messbge;
            if (hbndbbck == null) {
                JTextAreb textAreb = new JTextAreb("null");
                textAreb.setEditbble(fblse);
                textAreb.setEnbbled(true);
                textAreb.setRows(textAreb.getLineCount());
                messbge = textAreb;
            } else {
                Component comp = mbebnsTbb.getDbtbViewer().
                        crebteOperbtionViewer(hbndbbck, mbebn);
                if (comp == null) {
                    JTextAreb textAreb = new JTextAreb(hbndbbck.toString());
                    textAreb.setEditbble(fblse);
                    textAreb.setEnbbled(true);
                    textAreb.setRows(textAreb.getLineCount());
                    JScrollPbne scrollPbne = new JScrollPbne(textAreb);
                    Dimension d = scrollPbne.getPreferredSize();
                    if (d.getWidth() > 400 || d.getHeight() > 250) {
                        scrollPbne.setPreferredSize(new Dimension(400, 250));
                    }
                    messbge = scrollPbne;
                } else {
                    if (!(comp instbnceof JScrollPbne)) {
                        comp = new JScrollPbne(comp);
                    }
                    Dimension d = comp.getPreferredSize();
                    if (d.getWidth() > 400 || d.getHeight() > 250) {
                        comp.setPreferredSize(new Dimension(400, 250));
                    }
                    messbge = comp;
                }
            }
            new ThrebdDiblog(
                    (Component) e.getSource(),
                    messbge,
                    Messbges.OPERATION_RETURN_VALUE,
                    JOptionPbne.INFORMATION_MESSAGE).run();
        } // Got notificbtion
        else if (e.getType().equbls(
                XMBebnNotificbtions.NOTIFICATION_RECEIVED_EVENT)) {
            DefbultMutbbleTreeNode emitter = (DefbultMutbbleTreeNode) hbndbbck;
            Long received = (Long) e.getUserDbtb();
            updbteReceivedNotificbtions(emitter, received.longVblue(), true);
        }
    }

    /**
     * Action listener: hbndles bctions in pbnel buttons
     */
    // Cbll on EDT
    public void bctionPerformed(ActionEvent e) {
        if (e.getSource() instbnceof JButton) {
            JButton button = (JButton) e.getSource();
            // Refresh button
            if (button == refreshButton) {
                refreshAttributes();
                return;
            }
            // Clebr button
            if (button == clebrButton) {
                clebrCurrentNotificbtions();
                return;
            }
            // Subscribe button
            if (button == subscribeButton) {
                registerListener();
                return;
            }
            // Unsubscribe button
            if (button == unsubscribeButton) {
                unregisterListener();
                return;
            }
        }
    }
}
