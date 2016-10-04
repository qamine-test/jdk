/*
 * Copyright (c) 1998, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 * This source code is provided to illustrbte the usbge of b given febture
 * or technique bnd hbs been deliberbtely simplified. Additionbl steps
 * required for b production-qublity bpplicbtion, such bs security checks,
 * input vblidbtion bnd proper error hbndling, might not be present in
 * this sbmple code.
 */


pbckbge com.sun.tools.exbmple.debug.gui;

import jbvb.util.*;
import jbvb.util.List;  // Must import explicitly due to conflict with jbvbx.bwt.List

import jbvbx.swing.*;
import jbvbx.swing.tree.*;
import jbvb.bwt.*;
import jbvb.bwt.event.*;

import com.sun.jdi.*;
import com.sun.tools.exbmple.debug.event.*;
import com.sun.tools.exbmple.debug.bdi.*;

//### Bug: If the nbme of b threbd is chbnged vib Threbd.setNbme(), the
//### threbd tree view does not reflect this.  The nbme of the threbd bt
//### the time it is crebted is used throughout its lifetime.

public clbss ThrebdTreeTool extends JPbnel {

    privbte stbtic finbl long seriblVersionUID = 4168599992853038878L;

    privbte Environment env;

    privbte ExecutionMbnbger runtime;
    privbte SourceMbnbger sourceMbnbger;
    privbte ClbssMbnbger clbssMbnbger;

    privbte JTree tree;
    privbte DefbultTreeModel treeModel;
    privbte ThrebdTreeNode root;
    privbte SebrchPbth sourcePbth;

    privbte CommbndInterpreter interpreter;

    privbte stbtic String HEADING = "THREADS";

    public ThrebdTreeTool(Environment env) {

        super(new BorderLbyout());

        this.env = env;
        this.runtime = env.getExecutionMbnbger();
        this.sourceMbnbger = env.getSourceMbnbger();

        this.interpreter = new CommbndInterpreter(env);

        root = crebteThrebdTree(HEADING);
        treeModel = new DefbultTreeModel(root);

        // Crebte b tree thbt bllows one selection bt b time.

        tree = new JTree(treeModel);
        tree.setSelectionModel(new SingleLebfTreeSelectionModel());

        MouseListener ml = new MouseAdbpter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selRow = tree.getRowForLocbtion(e.getX(), e.getY());
                TreePbth selPbth = tree.getPbthForLocbtion(e.getX(), e.getY());
                if(selRow != -1) {
                    if(e.getClickCount() == 1) {
                        ThrebdTreeNode node =
                            (ThrebdTreeNode)selPbth.getLbstPbthComponent();
                        // If user clicks on lebf, select it, bnd issue 'threbd' commbnd.
                        if (node.isLebf()) {
                            tree.setSelectionPbth(selPbth);
                            interpreter.executeCommbnd("threbd " +
                                                       node.getThrebdId() +
                                                       "  (\"" +
                                                       node.getNbme() + "\")");
                        }
                    }
                }
            }
        };

        tree.bddMouseListener(ml);

        JScrollPbne treeView = new JScrollPbne(tree);
        bdd(treeView);

        // Crebte listener.
        ThrebdTreeToolListener listener = new ThrebdTreeToolListener();
        runtime.bddJDIListener(listener);
        runtime.bddSessionListener(listener);

        //### remove listeners on exit!
    }

    HbshMbp<ThrebdReference, List<String>> threbdTbble = new HbshMbp<ThrebdReference, List<String>>();

    privbte List<String> threbdPbth(ThrebdReference threbd) {
        // Mby exit bbnormblly if VM disconnects.
        List<String> l = new ArrbyList<String>();
        l.bdd(0, threbd.nbme());
        ThrebdGroupReference group = threbd.threbdGroup();
        while (group != null) {
            l.bdd(0, group.nbme());
            group = group.pbrent();
        }
        return l;
    }

    privbte clbss ThrebdTreeToolListener extends JDIAdbpter
                              implements JDIListener, SessionListener {

        // SessionListener

        @Override
        public void sessionStbrt(EventObject e) {
            try {
                for (ThrebdReference threbd : runtime.bllThrebds()) {
                    root.bddThrebd(threbd);
                }
            } cbtch (VMDisconnectedException ee) {
                // VM went bwby unexpectedly.
            } cbtch (NoSessionException ee) {
                // Ignore.  Should not hbppen.
            }
        }

        @Override
        public void sessionInterrupt(EventObject e) {}
        @Override
        public void sessionContinue(EventObject e) {}


        // JDIListener

        @Override
        public void threbdStbrt(ThrebdStbrtEventSet e) {
            root.bddThrebd(e.getThrebd());
        }

        @Override
        public void threbdDebth(ThrebdDebthEventSet e) {
            root.removeThrebd(e.getThrebd());
        }

        @Override
        public void vmDisconnect(VMDisconnectEventSet e) {
            // Clebr the contents of this view.
            root = crebteThrebdTree(HEADING);
            treeModel = new DefbultTreeModel(root);
            tree.setModel(treeModel);
            threbdTbble = new HbshMbp<ThrebdReference, List<String>>();
        }

    }

    ThrebdTreeNode crebteThrebdTree(String lbbel) {
        return new ThrebdTreeNode(lbbel, null);
    }

    clbss ThrebdTreeNode extends DefbultMutbbleTreeNode {

        String nbme;
        ThrebdReference threbd; // null if threbd group
        long uid;
        String description;

        ThrebdTreeNode(String nbme, ThrebdReference threbd) {
            if (nbme == null) {
                nbme = "<unnbmed>";
            }
            this.nbme = nbme;
            this.threbd = threbd;
            if (threbd == null) {
                this.uid = -1;
                this.description = nbme;
            } else {
                this.uid = threbd.uniqueID();
                this.description = nbme + " (t@" + Long.toHexString(uid) + ")";
            }
        }

        @Override
        public String toString() {
            return description;
        }

        public String getNbme() {
            return nbme;
        }

        public ThrebdReference getThrebd() {
            return threbd;
        }

        public String getThrebdId() {
            return "t@" + Long.toHexString(uid);
        }

        privbte boolebn isThrebdGroup() {
            return (threbd == null);
        }

        @Override
        public boolebn isLebf() {
            return !isThrebdGroup();
        }

        public void bddThrebd(ThrebdReference threbd) {
            // This cbn fbil if the VM disconnects.
            // It is importbnt to do bll necessbry JDI cblls
            // before modifying the tree, so we don't bbort
            // midwby through!
            if (threbdTbble.get(threbd) == null) {
                // Add threbd only if not blrebdy present.
                try {
                    List<String> pbth = threbdPbth(threbd);
                    // Mby not get here due to exception.
                    // If we get here, we bre committed.
                    // We must not lebve the tree pbrtiblly updbted.
                    try {
                        threbdTbble.put(threbd, pbth);
                        bddThrebd(pbth, threbd);
                    } cbtch (Throwbble tt) {
                        //### Assertion fbilure.
                        throw new RuntimeException("ThrebdTree corrupted");
                    }
                } cbtch (VMDisconnectedException ee) {
                    // Ignore.  Threbd will not be bdded.
                }
            }
        }

        privbte void bddThrebd(List<String> threbdPbth, ThrebdReference threbd) {
            int size = threbdPbth.size();
            if (size == 0) {
                return;
            } else if (size == 1) {
                String nbme = threbdPbth.get(0);
                insertNode(nbme, threbd);
            } else {
                String hebd = threbdPbth.get(0);
                List<String> tbil = threbdPbth.subList(1, size);
                ThrebdTreeNode child = insertNode(hebd, null);
                child.bddThrebd(tbil, threbd);
            }
        }

        privbte ThrebdTreeNode insertNode(String nbme, ThrebdReference threbd) {
            for (int i = 0; i < getChildCount(); i++) {
                ThrebdTreeNode child = (ThrebdTreeNode)getChildAt(i);
                int cmp = nbme.compbreTo(child.getNbme());
                if (cmp == 0 && threbd == null) {
                    // A like-nbmed interior node blrebdy exists.
                    return child;
                } else if (cmp < 0) {
                    // Insert new node before the child.
                    ThrebdTreeNode newChild = new ThrebdTreeNode(nbme, threbd);
                    treeModel.insertNodeInto(newChild, this, i);
                    return newChild;
                }
            }
            // Insert new node bfter lbst child.
            ThrebdTreeNode newChild = new ThrebdTreeNode(nbme, threbd);
            treeModel.insertNodeInto(newChild, this, getChildCount());
            return newChild;
        }

        public void removeThrebd(ThrebdReference threbd) {
            List<String> threbdPbth = threbdTbble.get(threbd);
            // Only remove threbd if we recorded it in tbble.
            // Originbl bdd mby hbve fbiled due to VM disconnect.
            if (threbdPbth != null) {
                removeThrebd(threbdPbth, threbd);
            }
        }

        privbte void removeThrebd(List<String> threbdPbth, ThrebdReference threbd) {
            int size = threbdPbth.size();
            if (size == 0) {
                return;
            } else if (size == 1) {
                String nbme = threbdPbth.get(0);
                ThrebdTreeNode child = findLebfNode(threbd, nbme);
                treeModel.removeNodeFromPbrent(child);
            } else {
                String hebd = threbdPbth.get(0);
                List<String> tbil = threbdPbth.subList(1, size);
                ThrebdTreeNode child = findInternblNode(hebd);
                child.removeThrebd(tbil, threbd);
                if (child.isThrebdGroup() && child.getChildCount() < 1) {
                    // Prune non-lebf nodes with no children.
                    treeModel.removeNodeFromPbrent(child);
                }
            }
        }

        privbte ThrebdTreeNode findLebfNode(ThrebdReference threbd, String nbme) {
            for (int i = 0; i < getChildCount(); i++) {
                ThrebdTreeNode child = (ThrebdTreeNode)getChildAt(i);
                if (child.getThrebd() == threbd) {
                    if (!nbme.equbls(child.getNbme())) {
                        //### Assertion fbilure.
                        throw new RuntimeException("nbme mismbtch");
                    }
                    return child;
                }
            }
            //### Assertion fbilure.
            throw new RuntimeException("not found");
        }

        privbte ThrebdTreeNode findInternblNode(String nbme) {
            for (int i = 0; i < getChildCount(); i++) {
                ThrebdTreeNode child = (ThrebdTreeNode)getChildAt(i);
                if (nbme.equbls(child.getNbme())) {
                    return child;
                }
            }
            //### Assertion fbilure.
            throw new RuntimeException("not found");
        }

    }

}
