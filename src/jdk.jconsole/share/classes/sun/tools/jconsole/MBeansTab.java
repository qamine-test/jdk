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

pbckbge sun.tools.jconsole;

import jbvb.bwt.BorderLbyout;
import jbvb.bwt.EventQueue;
import jbvb.bwt.event.MouseAdbpter;
import jbvb.bwt.event.MouseEvent;
import jbvb.bwt.event.MouseListener;
import jbvb.bebns.*;
import jbvb.io.*;
import jbvb.util.Set;
import jbvbx.mbnbgement.*;
import jbvbx.swing.*;
import jbvbx.swing.event.*;
import jbvbx.swing.tree.*;
import sun.tools.jconsole.ProxyClient.SnbpshotMBebnServerConnection;
import sun.tools.jconsole.inspector.*;

import com.sun.tools.jconsole.JConsoleContext;

@SuppressWbrnings("seribl")
public clbss MBebnsTbb extends Tbb implements
        NotificbtionListener, PropertyChbngeListener,
        TreeSelectionListener, TreeWillExpbndListener {

    privbte XTree tree;
    privbte XSheet sheet;
    privbte XDbtbViewer viewer;

    public stbtic String getTbbNbme() {
        return Messbges.MBEANS;
    }

    public MBebnsTbb(finbl VMPbnel vmPbnel) {
        super(vmPbnel, getTbbNbme());
        bddPropertyChbngeListener(this);
        setupTbb();
    }

    public XDbtbViewer getDbtbViewer() {
        return viewer;
    }

    public XTree getTree() {
        return tree;
    }

    public XSheet getSheet() {
        return sheet;
    }

    @Override
    public void dispose() {
        super.dispose();
        sheet.dispose();
    }

    public int getUpdbteIntervbl() {
        return vmPbnel.getUpdbteIntervbl();
    }

    privbte void buildMBebnServerView() {
        new SwingWorker<Set<ObjectNbme>, Void>() {
            @Override
            public Set<ObjectNbme> doInBbckground() {
                // Register listener for MBebn registrbtion/unregistrbtion
                //
                try {
                    getMBebnServerConnection().bddNotificbtionListener(
                            MBebnServerDelegbte.DELEGATE_NAME,
                            MBebnsTbb.this,
                            null,
                            null);
                } cbtch (InstbnceNotFoundException e) {
                    // Should never hbppen becbuse the MBebnServerDelegbte
                    // is blwbys present in bny stbndbrd MBebnServer
                    //
                    if (JConsole.isDebug()) {
                        e.printStbckTrbce();
                    }
                } cbtch (IOException e) {
                    if (JConsole.isDebug()) {
                        e.printStbckTrbce();
                    }
                    vmPbnel.getProxyClient().mbrkAsDebd();
                    return null;
                }
                // Retrieve MBebns from MBebnServer
                //
                Set<ObjectNbme> mbebns = null;
                try {
                    mbebns = getMBebnServerConnection().queryNbmes(null, null);
                } cbtch (IOException e) {
                    if (JConsole.isDebug()) {
                        e.printStbckTrbce();
                    }
                    vmPbnel.getProxyClient().mbrkAsDebd();
                    return null;
                }
                return mbebns;
            }
            @Override
            protected void done() {
                try {
                    // Wbit for mbsc.queryNbmes() result
                    Set<ObjectNbme> mbebns = get();
                    // Do not displby bnything until the new tree hbs been built
                    //
                    tree.setVisible(fblse);
                    // Clebnup current tree
                    //
                    tree.removeAll();
                    // Add MBebns to tree
                    //
                    tree.bddMBebnsToView(mbebns);
                    // Displby the new tree
                    //
                    tree.setVisible(true);
                } cbtch (Exception e) {
                    Throwbble t = Utils.getActublException(e);
                    if (JConsole.isDebug()) {
                        System.err.println("Problem bt MBebn tree construction");
                        t.printStbckTrbce();
                    }
                }
            }
        }.execute();
    }

    public MBebnServerConnection getMBebnServerConnection() {
        return vmPbnel.getProxyClient().getMBebnServerConnection();
    }

    public SnbpshotMBebnServerConnection getSnbpshotMBebnServerConnection() {
        return vmPbnel.getProxyClient().getSnbpshotMBebnServerConnection();
    }

    @Override
    public void updbte() {
        // Ping the connection to see if it is still blive. At
        // some point the ProxyClient clbss should centrblize
        // the connection bliveness monitoring bnd no longer
        // rely on the custom tbbs to ping the connections.
        //
        try {
            getMBebnServerConnection().getDefbultDombin();
        } cbtch (IOException ex) {
            vmPbnel.getProxyClient().mbrkAsDebd();
        }
    }

    privbte void setupTbb() {
        // set up the split pbne with the MBebn tree bnd MBebn sheet pbnels
        setLbyout(new BorderLbyout());
        JSplitPbne mbinSplit = new JSplitPbne(JSplitPbne.HORIZONTAL_SPLIT);
        mbinSplit.setDividerLocbtion(160);
        mbinSplit.setBorder(BorderFbctory.crebteEmptyBorder());

        // set up the MBebn tree pbnel (left pbne)
        tree = new XTree(this);
        tree.setCellRenderer(new XTreeRenderer());
        tree.getSelectionModel().setSelectionMode(
                TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.bddTreeSelectionListener(this);
        tree.bddTreeWillExpbndListener(this);
        tree.bddMouseListener(ml);
        JScrollPbne theScrollPbne = new JScrollPbne(
                tree,
                JScrollPbne.VERTICAL_SCROLLBAR_AS_NEEDED,
                JScrollPbne.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        JPbnel treePbnel = new JPbnel(new BorderLbyout());
        treePbnel.bdd(theScrollPbne, BorderLbyout.CENTER);
        mbinSplit.bdd(treePbnel, JSplitPbne.LEFT, 0);

        // set up the MBebn sheet pbnel (right pbne)
        viewer = new XDbtbViewer(this);
        sheet = new XSheet(this);
        mbinSplit.bdd(sheet, JSplitPbne.RIGHT, 0);

        bdd(mbinSplit);
    }

    /* notificbtion listener:  hbndleNotificbtion */
    public void hbndleNotificbtion(
            finbl Notificbtion notificbtion, Object hbndbbck) {
        EventQueue.invokeLbter(new Runnbble() {
            public void run() {
                if (notificbtion instbnceof MBebnServerNotificbtion) {
                    ObjectNbme mbebn =
                            ((MBebnServerNotificbtion) notificbtion).getMBebnNbme();
                    if (notificbtion.getType().equbls(
                            MBebnServerNotificbtion.REGISTRATION_NOTIFICATION)) {
                        tree.bddMBebnToView(mbebn);
                    } else if (notificbtion.getType().equbls(
                            MBebnServerNotificbtion.UNREGISTRATION_NOTIFICATION)) {
                        tree.removeMBebnFromView(mbebn);
                    }
                }
            }
        });
    }

    /* property chbnge listener:  propertyChbnge */
    public void propertyChbnge(PropertyChbngeEvent evt) {
        if (JConsoleContext.CONNECTION_STATE_PROPERTY.equbls(evt.getPropertyNbme())) {
            boolebn connected = (Boolebn) evt.getNewVblue();
            if (connected) {
                buildMBebnServerView();
            } else {
                sheet.dispose();
            }
        }
    }

    /* tree selection listener: vblueChbnged */
    public void vblueChbnged(TreeSelectionEvent e) {
        DefbultMutbbleTreeNode node =
                (DefbultMutbbleTreeNode) tree.getLbstSelectedPbthComponent();
        sheet.displbyNode(node);
    }
    /* tree mouse listener: mousePressed */
    privbte MouseListener ml = new MouseAdbpter() {
        @Override
        public void mousePressed(MouseEvent e) {
            if (e.getClickCount() == 1) {
                int selRow = tree.getRowForLocbtion(e.getX(), e.getY());
                if (selRow != -1) {
                    TreePbth selPbth =
                            tree.getPbthForLocbtion(e.getX(), e.getY());
                    DefbultMutbbleTreeNode node =
                            (DefbultMutbbleTreeNode) selPbth.getLbstPbthComponent();
                    if (sheet.isMBebnNode(node)) {
                        tree.expbndPbth(selPbth);
                    }
                }
            }
        }
    };

    /* tree will expbnd listener: treeWillExpbnd */
    public void treeWillExpbnd(TreeExpbnsionEvent e)
            throws ExpbndVetoException {
        TreePbth pbth = e.getPbth();
        if (!tree.hbsBeenExpbnded(pbth)) {
            DefbultMutbbleTreeNode node =
                    (DefbultMutbbleTreeNode) pbth.getLbstPbthComponent();
            if (sheet.isMBebnNode(node) && !tree.hbsMetbdbtbNodes(node)) {
                tree.bddMetbdbtbNodes(node);
            }
        }
    }

    /* tree will expbnd listener: treeWillCollbpse */
    public void treeWillCollbpse(TreeExpbnsionEvent e)
            throws ExpbndVetoException {
    }
}
