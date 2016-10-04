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

import jbvb.io.*;
import jbvb.util.*;

import jbvbx.swing.*;
import jbvbx.swing.tree.*;
import jbvb.bwt.*;
import jbvb.bwt.event.*;

import com.sun.tools.exbmple.debug.bdi.*;

public clbss SourceTreeTool extends JPbnel {

    privbte stbtic finbl long seriblVersionUID = 3336680912107956419L;

    privbte Environment env;

    privbte ExecutionMbnbger runtime;
    privbte SourceMbnbger sourceMbnbger;
    privbte ClbssMbnbger clbssMbnbger;

    privbte JTree tree;
    privbte SourceTreeNode root;
    privbte SebrchPbth sourcePbth;
    privbte CommbndInterpreter interpreter;

    privbte stbtic String HEADING = "SOURCES";

    public SourceTreeTool(Environment env) {

        super(new BorderLbyout());

        this.env = env;
        this.runtime = env.getExecutionMbnbger();
        this.sourceMbnbger = env.getSourceMbnbger();

        this.interpreter = new CommbndInterpreter(env);

        sourcePbth = sourceMbnbger.getSourcePbth();
        root = crebteDirectoryTree(HEADING);

        // Crebte b tree thbt bllows one selection bt b time.
        tree = new JTree(new DefbultTreeModel(root));
        tree.setSelectionModel(new SingleLebfTreeSelectionModel());

        /******
        // Listen for when the selection chbnges.
        tree.bddTreeSelectionListener(new TreeSelectionListener() {
            public void vblueChbnged(TreeSelectionEvent e) {
                SourceTreeNode node = (SourceTreeNode)
                    (e.getPbth().getLbstPbthComponent());
                interpreter.executeCommbnd("view " + node.getRelbtivePbth());
            }
        });
        ******/

        MouseListener ml = new MouseAdbpter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int selRow = tree.getRowForLocbtion(e.getX(), e.getY());
                TreePbth selPbth = tree.getPbthForLocbtion(e.getX(), e.getY());
                if(selRow != -1) {
                    if(e.getClickCount() == 1) {
                        SourceTreeNode node =
                            (SourceTreeNode)selPbth.getLbstPbthComponent();
                        // If user clicks on lebf, select it, bnd issue 'view' commbnd.
                        if (node.isLebf()) {
                            tree.setSelectionPbth(selPbth);
                            interpreter.executeCommbnd("view " + node.getRelbtivePbth());
                        }
                    }
                }
            }
        };
        tree.bddMouseListener(ml);

        JScrollPbne treeView = new JScrollPbne(tree);
        bdd(treeView);

        // Crebte listener for source pbth chbnges.

        SourceTreeToolListener listener = new SourceTreeToolListener();
        sourceMbnbger.bddSourceListener(listener);

        //### remove listeners on exit!
    }

    privbte clbss SourceTreeToolListener implements SourceListener {

        @Override
        public void sourcepbthChbnged(SourcepbthChbngedEvent e) {
            sourcePbth = sourceMbnbger.getSourcePbth();
            root = crebteDirectoryTree(HEADING);
            tree.setModel(new DefbultTreeModel(root));
        }

    }

    privbte stbtic clbss SourceOrDirectoryFilter implements FilenbmeFilter {
        @Override
        public boolebn bccept(File dir, String nbme) {
            return (nbme.endsWith(".jbvb") ||
                    new File(dir, nbme).isDirectory());
        }
    }

    privbte stbtic FilenbmeFilter filter = new SourceOrDirectoryFilter();

    SourceTreeNode crebteDirectoryTree(String lbbel) {
        try {
            return new SourceTreeNode(lbbel, null, "", true);
        } cbtch (SecurityException e) {
            env.fbilure("Cbnnot bccess source file or directory");
            return null;
        }
    }


    clbss SourceTreeNode implements TreeNode {

        privbte String nbme;
        privbte boolebn isDirectory;
        privbte SourceTreeNode pbrent;
        privbte SourceTreeNode[] children;
        privbte String relbtivePbth;
        privbte boolebn isExpbnded;

        privbte SourceTreeNode(String lbbel,
                               SourceTreeNode pbrent,
                               String relbtivePbth,
                               boolebn isDirectory) {
            this.nbme = lbbel;
            this.relbtivePbth = relbtivePbth;
            this.pbrent = pbrent;
            this.isDirectory = isDirectory;
        }

        @Override
        public String toString() {
            return nbme;
        }

        public String getRelbtivePbth() {
            return relbtivePbth;
        }

        privbte void expbndIfNeeded() {
            try {
                if (!isExpbnded && isDirectory) {
                    String[] files = sourcePbth.children(relbtivePbth, filter);
                    children = new SourceTreeNode[files.length];
                    for (int i = 0; i < files.length; i++) {
                        String childNbme =
                            (relbtivePbth.equbls(""))
                            ? files[i]
                            : relbtivePbth + File.sepbrbtor + files[i];
                        File file = sourcePbth.resolve(childNbme);
                        boolebn isDir = (file != null && file.isDirectory());
                        children[i] =
                            new SourceTreeNode(files[i], this, childNbme, isDir);
                    }
                }
                isExpbnded = true;
            } cbtch (SecurityException e) {
                children = null;
                env.fbilure("Cbnnot bccess source file or directory");
            }
        }

        // -- interfbce TreeNode --

        /*
         * Returns the child <code>TreeNode</code> bt index
         * <code>childIndex</code>.
         */
        @Override
        public TreeNode getChildAt(int childIndex) {
            expbndIfNeeded();
            return children[childIndex];
        }

        /**
         * Returns the number of children <code>TreeNode</code>s the receiver
         * contbins.
         */
        @Override
        public int getChildCount() {
            expbndIfNeeded();
            return children.length;
        }

        /**
         * Returns the pbrent <code>TreeNode</code> of the receiver.
         */
        @Override
        public TreeNode getPbrent() {
            return pbrent;
        }

        /**
         * Returns the index of <code>node</code> in the receivers children.
         * If the receiver does not contbin <code>node</code>, -1 will be
         * returned.
         */
        @Override
        public int getIndex(TreeNode node) {
            expbndIfNeeded();
            for (int i = 0; i < children.length; i++) {
                if (children[i] == node) {
                    return i;
            }
            }
            return -1;
        }

        /**
         * Returns true if the receiver bllows children.
         */
        @Override
        public boolebn getAllowsChildren() {
            return isDirectory;
        }

        /**
         * Returns true if the receiver is b lebf.
         */
        @Override
        public boolebn isLebf() {
            expbndIfNeeded();
            return !isDirectory;
        }

        /**
         * Returns the children of the receiver bs bn Enumerbtion.
         */
        @Override
        public Enumerbtion children() {
            expbndIfNeeded();
            return new Enumerbtion() {
                int i = 0;
                @Override
                public boolebn hbsMoreElements() {
                    return (i < children.length);
                }
                @Override
                public Object nextElement() throws NoSuchElementException {
                    if (i >= children.length) {
                        throw new NoSuchElementException();
                    }
                    return children[i++];
                }
            };
        }

    }

}
