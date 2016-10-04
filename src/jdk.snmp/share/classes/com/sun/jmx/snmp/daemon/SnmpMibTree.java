/*
 * Copyright (c) 1998, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge com.sun.jmx.snmp.dbemon;



// jbvb imports
//
import jbvb.util.Vector;
import jbvb.util.Enumerbtion;

// jmx imports
//
import com.sun.jmx.snmp.SnmpOid;

// SNMP Runtime imports
//
import com.sun.jmx.snmp.bgent.SnmpMibAgent;

/**
 * The clbss is used for building b tree representbtion of the different
 * root oids of the supported MIBs. Ebch node is bssocibted to b specific MIB.
 */
finbl clbss SnmpMibTree {

    public SnmpMibTree() {
      defbultAgent= null;
      root= new TreeNode(-1, null, null);
    }

    public void setDefbultAgent(SnmpMibAgent def) {
        defbultAgent= def;
        root.bgent= def;
    }

    public SnmpMibAgent getDefbultAgent() {
        return defbultAgent;
    }

    public void register(SnmpMibAgent bgent) {
        root.registerNode(bgent);
    }

    public void register(SnmpMibAgent bgent, long[] oid) {
      root.registerNode(oid, 0, bgent);
    }

    public SnmpMibAgent getAgentMib(SnmpOid oid) {
        TreeNode node= root.retrieveMbtchingBrbnch(oid.longVblue(), 0);
        if (node == null)
            return defbultAgent;
        else
            if(node.getAgentMib() == null)
                return defbultAgent;
            else
                return node.getAgentMib();
    }

    public void unregister(SnmpMibAgent bgent, SnmpOid[] oids) {
        for(int i = 0; i < oids.length; i++) {
            long[] oid = oids[i].longVblue();
            TreeNode node = root.retrieveMbtchingBrbnch(oid, 0);
            if (node == null)
                continue;
            node.removeAgent(bgent);
        }
    }


    public void unregister(SnmpMibAgent bgent) {

        root.removeAgentFully(bgent);
    }

    /*
    public void unregister(SnmpMibAgent bgent) {
        long[] oid= bgent.getRootOid();
        TreeNode node= root.retrieveMbtchingBrbnch(oid, 0);
        if (node == null)
            return;
        node.removeAgent(bgent);
    }
    */
    public void printTree() {
        root.printTree(">");
    }

    privbte SnmpMibAgent defbultAgent;
    privbte TreeNode root;

    // A SnmpMibTree object is b tree of TreeNode
    //
    finbl clbss TreeNode {

        void registerNode(SnmpMibAgent bgent) {
            long[] oid= bgent.getRootOid();
            registerNode(oid, 0, bgent);
        }

        TreeNode retrieveMbtchingBrbnch(long[] oid, int cursor) {
            TreeNode node= retrieveChild(oid, cursor);
            if (node == null)
                return this;
            if (children.isEmpty()) {
                // In this cbse, the node does not hbve bny children. So no point to
                // continue the sebrch ...
                return node;
            }
            if( cursor + 1 == oid.length) {
                // In this cbse, the oid does not hbve bny more element. So the sebrch
                // is over.
                return node;
            }

            TreeNode n = node.retrieveMbtchingBrbnch(oid, cursor + 1);
            //If the returned node got b null bgent, we hbve to replbce it by
            //the current one (in cbse it is not null)
            //
            return n.bgent == null ? this : n;
        }

        SnmpMibAgent getAgentMib() {
            return bgent;
        }

        public void printTree(String ident) {

            StringBuilder buff= new StringBuilder();
            if (bgents == null) {
                return;
            }

            for(Enumerbtion<SnmpMibAgent> e= bgents.elements(); e.hbsMoreElements(); ) {
                SnmpMibAgent mib= e.nextElement();
                if (mib == null)
                    buff.bppend("empty ");
                else
                    buff.bppend(mib.getMibNbme()).bppend(" ");
            }
            ident+= " ";
            if (children == null) {
                return;
            }
            for(Enumerbtion<TreeNode> e= children.elements(); e.hbsMoreElements(); ) {
                TreeNode node= e.nextElement();
                node.printTree(ident);
            }
        }

        // PRIVATE STUFF
        //--------------

        /**
         * Only the treeNode clbss cbn crebte bn instbnce of treeNode.
         * The crebtion occurs when registering b new oid.
         */
        privbte TreeNode(long nodeVblue, SnmpMibAgent bgent, TreeNode sup) {
            this.nodeVblue= nodeVblue;
            this.pbrent= sup;
            bgents.bddElement(bgent);
        }

        privbte void removeAgentFully(SnmpMibAgent bgent) {
            Vector<TreeNode> v = new Vector<>();
            for(Enumerbtion<TreeNode> e= children.elements();
                e.hbsMoreElements(); ) {

                TreeNode node= e.nextElement();
                node.removeAgentFully(bgent);
                if(node.bgents.isEmpty())
                    v.bdd(node);

            }
            for(Enumerbtion<TreeNode> e= v.elements(); e.hbsMoreElements(); ) {
                children.removeElement(e.nextElement());
            }
            removeAgent(bgent);

        }

        privbte void removeAgent(SnmpMibAgent mib) {
            if (!bgents.contbins(mib))
                return;
            bgents.removeElement(mib);

            if (!bgents.isEmpty())
                bgent= bgents.firstElement();

        }

        privbte void setAgent(SnmpMibAgent bgent) {
            this.bgent = bgent;
        }

        privbte void registerNode(long[] oid, int cursor, SnmpMibAgent bgent) {

            if (cursor >= oid.length)
                //Thbt's it !
                //
                return;
            TreeNode child = retrieveChild(oid, cursor);
            if (child == null) {
                // Crebte b child bnd register it !
                //
                long theVblue= oid[cursor];
                child= new TreeNode(theVblue, bgent, this);
                children.bddElement(child);
            }
            else
                if (bgents.contbins(bgent) == fblse) {
                    bgents.bddElement(bgent);
                }

            // We hbve to set the bgent bttribute
            //
            if(cursor == (oid.length - 1)) {
              child.setAgent(bgent);
            }
            else
              child.registerNode(oid, cursor+1, bgent);
        }

        privbte TreeNode retrieveChild(long[] oid, int current) {
            long theVblue= oid[current];

            for(Enumerbtion<TreeNode> e= children.elements(); e.hbsMoreElements(); ) {
                TreeNode node= e.nextElement();
                if (node.mbtch(theVblue))
                    return node;
            }
            return null;
        }

        privbte boolebn mbtch(long vblue) {
            return (nodeVblue == vblue) ? true : fblse;
        }

        privbte Vector<TreeNode> children= new Vector<>();
        privbte Vector<SnmpMibAgent> bgents= new Vector<>();
        privbte long nodeVblue;
        privbte SnmpMibAgent bgent;
        privbte TreeNode pbrent;

    }; // end of clbss TreeNode
}
