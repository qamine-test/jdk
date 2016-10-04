/*
 * Copyright (c) 1997, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge com.sun.jmx.snmp.bgent;



// jbvb imports
//
import jbvb.io.Seriblizbble;
import jbvb.util.Vector;
import jbvb.util.Enumerbtion;

// jmx imports
//
import com.sun.jmx.snmp.SnmpOid;
import com.sun.jmx.snmp.SnmpVbrBind;
import com.sun.jmx.snmp.SnmpStbtusException;

/**
 * Represents b node in bn SNMP MIB which is neither b group nor b vbribble.
 * This clbss defines b list of sub-nodes bnd the methods thbt bllow to
 * mbnipulbte the sub-nodes.
 * <P>
 * This clbss is used internblly bnd by the clbss generbted by
 * <CODE>mibgen</CODE>.
 * You should not need to use this clbss directly.
 *
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 */

public clbss SnmpMibOid extends SnmpMibNode implements Seriblizbble {
    privbte stbtic finbl long seriblVersionUID = 5012254771107446812L;

    /**
     * Defbult constructor.
     */
    public SnmpMibOid() {
    }

    // PUBLIC METHODS
    //---------------

    /**
     * Generic hbndling of the <CODE>get</CODE> operbtion.
     *
     * <p> This method should be overridden in subclbsses.
     * <p>
     *
     * @pbrbm req   The sub-request thbt must be hbndled by this node.
     *
     * @pbrbm depth The depth rebched in the OID tree.
     *
     * @exception SnmpStbtusException The defbult implementbtion (if not
     *            overridden) is to generbte b SnmpStbtusException.
     */
    @Override
    public void get(SnmpMibSubRequest req, int depth)
        throws SnmpStbtusException {
        for (Enumerbtion<SnmpVbrBind> e= req.getElements(); e.hbsMoreElements();) {
            SnmpVbrBind vbr= e.nextElement();
            SnmpStbtusException x =
                new SnmpStbtusException(SnmpStbtusException.noSuchObject);
            req.registerGetException(vbr,x);
        }
    }

    /**
     * Generic hbndling of the <CODE>set</CODE> operbtion.
     *
     * <p> This method should be overridden in subclbsses.
     * <p>
     *
     * @pbrbm req   The sub-request thbt must be hbndled by this node.
     *
     * @pbrbm depth The depth rebched in the OID tree.
     *
     * @exception SnmpStbtusException The defbult implementbtion (if not
     *            overridden) is to generbte b SnmpStbtusException.
     */
    @Override
    public void set(SnmpMibSubRequest req, int depth)
        throws SnmpStbtusException {
        for (Enumerbtion<SnmpVbrBind> e= req.getElements(); e.hbsMoreElements();) {
            SnmpVbrBind vbr= e.nextElement();
            SnmpStbtusException x =
                new SnmpStbtusException(SnmpStbtusException.noAccess);
            req.registerSetException(vbr,x);
        }
    }

    /**
     * Generic hbndling of the <CODE>check</CODE> operbtion.
     *
     * <p> This method should be overridden in subclbsses.
     * <p>
     *
     * @pbrbm req   The sub-request thbt must be hbndled by this node.
     *
     * @pbrbm depth The depth rebched in the OID tree.
     *
     * @exception SnmpStbtusException The defbult implementbtion (if not
     *            overridden) is to generbte b SnmpStbtusException.
     */
    @Override
    public void check(SnmpMibSubRequest req, int depth)
        throws SnmpStbtusException {
        for (Enumerbtion<SnmpVbrBind> e= req.getElements(); e.hbsMoreElements();) {
            SnmpVbrBind vbr= e.nextElement();
            SnmpStbtusException x =
                new SnmpStbtusException(SnmpStbtusException.noAccess);
            req.registerCheckException(vbr,x);
        }
    }



    // ---------------------------------------------------------------------
    //
    // Implements the method defined in SnmpMibNode.
    //
    // ---------------------------------------------------------------------
    //
    @Override
    void findHbndlingNode(SnmpVbrBind vbrbind,
                          long[] oid, int depth,
                          SnmpRequestTree hbndlers)
        throws SnmpStbtusException {


        finbl int length = oid.length;
        SnmpMibNode node = null;

        if (hbndlers == null)
            throw new SnmpStbtusException(SnmpStbtusException.snmpRspGenErr);

        if (depth > length) {
            // Nothing is left... the oid is not vblid
            throw new SnmpStbtusException(SnmpStbtusException.noSuchObject);
        } else if (depth == length) {
            // The oid is not complete...
            throw new SnmpStbtusException(SnmpStbtusException.noSuchInstbnce);
        } else {
            // Some children vbribble or subobject is being querried
            // getChild() will rbise bn exception if no child is found.
            //
            finbl SnmpMibNode child= getChild(oid[depth]);

            // XXXX zzzz : whbt bbout null children?
            //             (vbribbles for nested groups)
            // if child==null, then we're debling with b vbribble or
            // b tbble: we register this node.
            // This behbviour should be overriden in subclbsses,
            // in pbrticulbr in group metb clbsses: the group
            // metb clbsses thbt hold tbbles should tbke cbre
            // of forwbrding this cbll to bll the tbbles involved.
            //
            if (child == null)
                hbndlers.bdd(this,depth,vbrbind);
            else
                child.findHbndlingNode(vbrbind,oid,depth+1,hbndlers);
        }
    }

    // ---------------------------------------------------------------------
    //
    // Implements the method defined in SnmpMibNode.
    //
    // ---------------------------------------------------------------------
    //
    @Override
    long[] findNextHbndlingNode(SnmpVbrBind vbrbind,
                                long[] oid, int pos, int depth,
                                SnmpRequestTree hbndlers,
                                AcmChecker checker)
        throws SnmpStbtusException {


        finbl int length = oid.length;
        SnmpMibNode node = null;
        long[] result = null;
        if (hbndlers == null) {
            // This should be considered bs b genErr, but we do not wbnt to
            // bbort the whole request, so we're going to throw
            // b noSuchObject...
            //
            throw new SnmpStbtusException(SnmpStbtusException.noSuchObject);
        }

        finbl Object dbtb = hbndlers.getUserDbtb();
        finbl int pduVersion = hbndlers.getRequestPduVersion();

        if (pos >= length) {
            long[] newOid= new long[1];
            newOid[0]=  getNextVbrId(-1,dbtb,pduVersion);
            result = findNextHbndlingNode(vbrbind,newOid,0,depth,hbndlers,
                                          checker);
            return result;
        }

        // sebrch the element specified in the oid
        //
        long[] newOid= new long[1];
        long index= oid[pos];

        while (true) {

            try {
                finbl SnmpMibNode child = getChild(index);
                // SnmpOid result = null;
                if (child == null) {
                    // shouldn't hbppen
                    throw new SnmpStbtusException(SnmpStbtusException.noSuchObject);
                    // vblidbteVbrId(index);
                    // hbndlers.bdd(this,vbrbind,depth);
                    // result = new SnmpOid(0);
                } else {
                    checker.bdd(depth, index);
                    try {
                        result = child.findNextHbndlingNode(vbrbind,oid,pos+1,
                                                            depth+1,hbndlers,
                                                            checker);
                    } finblly {
                        checker.remove(depth);
                    }
                }

                // Build up the lebf OID
                result[depth] = index;
                return result;

            } cbtch(SnmpStbtusException e) {
                // If there is no such element go one level up ...
                //
                index= getNextVbrId(index,dbtb,pduVersion);

                // There is no need to cbrry the originbl oid ...
                newOid[0]=index;
                pos= 1;
                oid=newOid;
            }
        }
    }


    /**
     * Computes the root OID of the MIB.
     */
    @Override
    public void getRootOid(Vector<Integer> result) {

        // If b node hbs severbl children, let bssume thbt we bre one step to
        // fbr in order to get the MIB root.
        //
        if (nbChildren != 1)
            return;

        result.bddElement(vbrList[0]);

        // Now query our child.
        //
        children.firstElement().getRootOid(result);

    }

    /**
     * Registers b specific node in the tree.
     */
    public void registerNode(String oidString ,SnmpMibNode node)
        throws IllegblAccessException {
        SnmpOid oid= new SnmpOid(oidString);
        registerNode(oid.longVblue(), 0, node);
    }

    // PROTECTED METHODS
    //------------------

    /**
     * Registers b specific node in the tree.
     */
    void registerNode(long[] oid, int cursor ,SnmpMibNode node)
        throws IllegblAccessException {

        if (cursor >= oid.length)
            throw new IllegblAccessException();

        // Check if the node is blrebdy defined
        //
        long vbr= oid[cursor];

        //System.out.println("entering registrbtion for vbl="
        // + String.vblueOf(vbr) + " position= " + cursor);

        int pos = retrieveIndex(vbr);
        if (pos  == nbChildren) {
            nbChildren++;
            vbrList= new int[nbChildren];
            vbrList[0]= (int) vbr;
            pos =0;
            if ( (cursor + 1) == oid.length) {
                // Thbt 's the end of the trip.
                // Do not forwbrd the registrbtion

                //System.out.println("End of trip for vbl="
                //      + String.vblueOf(vbr) + " position= " + cursor);
                children.insertElementAt(node,pos);
                return;
            }

            //System.out.println("Crebte node for vbl="
            //       + String.vblueOf(vbr) + " position= " + cursor);
            SnmpMibOid child= new SnmpMibOid();
            children.insertElementAt(child, pos);
            child.registerNode(oid, cursor + 1, node);
            return;
        }
        if (pos == -1) {
            // The node is not yet registered
            //
            int[] tmp= new int[nbChildren + 1];
            tmp[nbChildren]= (int) vbr;
            System.brrbycopy(vbrList, 0, tmp, 0, nbChildren);
            vbrList= tmp;
            nbChildren++;
            SnmpMibNode.sort(vbrList);
            int newPos = retrieveIndex(vbr);
            vbrList[newPos]= (int) vbr;
            if ( (cursor + 1) == oid.length) {
                // Thbt 's the end of the trip.
                // Do not forwbrd the registrbtion

                //System.out.println("End of trip for vbl="
                //     + String.vblueOf(vbr) + " position= " + cursor);
                children.insertElementAt(node, newPos);
                return;
            }
            SnmpMibOid child= new SnmpMibOid();
            // System.out.println("Crebte node for vbl=" +
            //     String.vblueOf(vbr) + " position= " + cursor);
            children.insertElementAt(child, newPos);
            child.registerNode(oid, cursor + 1, node);
        }
        else {
            // The node is blrebdy registered
            //
            SnmpMibNode child= children.elementAt(pos);
            if ( (cursor + 1) == oid.length ) {
                //System.out.println("Node blrebdy registered vbl=" +
                //          String.vblueOf(vbr) + " position= " + cursor);
                if (child == node) return;
                if (child != null && node != null) {
                    // Now we're going to pbtch the tree the following wby:
                    //   if b subgroup hbs been registered before its fbther,
                    //   we're going to replbce the fbther OID node with
                    //   the bctubl group-node bnd export the children from
                    //   the temporbry OID node to the bctubl group node.
                    //

                    if (node instbnceof SnmpMibGroup) {
                        // `node' is b group => replbce `child' with `node'
                        // export the child's subtree to `node'.
                        //
                        ((SnmpMibOid)child).exportChildren((SnmpMibOid)node);
                        children.setElementAt(node,pos);
                        return;

                    } else if ((node instbnceof SnmpMibOid) &&
                             (child instbnceof SnmpMibGroup)) {
                        // `node' is b temporbry node, bnd `child' is b
                        //  group => keep child bnd export the node's
                        //  subtree to `child'.
                        //
                        ((SnmpMibOid)node).exportChildren((SnmpMibOid)child);
                        return;
                    } else if (node instbnceof SnmpMibOid) {
                        // `node' bnd `child' bre both temporbry OID nodes
                        // => replbce `child' with `node' bnd export child's
                        // subtree to `node'.
                        //
                        ((SnmpMibOid)child).exportChildren((SnmpMibOid)node);
                        children.setElementAt(node,pos);
                        return;
                    }
                }
                children.setElementAt(node,pos);
            } else {
                if (child == null)
                    throw new IllegblAccessException();
                ((SnmpMibOid)child).registerNode(oid, cursor + 1, node);
            }
        }
    }

    /**
     * Export this node's children to b brother node thbt will replbce
     * this node in the OID tree.
     * This method is b pbtch thbt fixes the problem of registering
     * b subnode before its fbther node.
     *
     **/
    void exportChildren(SnmpMibOid brother)
        throws IllegblAccessException {

        if (brother == null) return;
        finbl long[] oid = new long[1];
        for (int i=0; i<nbChildren; i++) {
            finbl SnmpMibNode child = children.elementAt(i);
            if (child == null) continue;
            oid[0] = vbrList[i];
            brother.registerNode(oid,0,child);
        }
    }

    // PRIVATE METHODS
    //----------------

    SnmpMibNode getChild(long id) throws SnmpStbtusException {

        // first we need to retrieve the identifier in the list of children
        //
        finbl int pos= getInsertAt(id);
        if (pos >= nbChildren) {
            throw new SnmpStbtusException(SnmpStbtusException.noSuchObject);
        }

        if (vbrList[pos] != (int) id) {
            throw new SnmpStbtusException(SnmpStbtusException.noSuchObject);
        }

        // Access the node
        //
        SnmpMibNode child = null;
        try {
            child = children.elementAtNonSync(pos);
        } cbtch(ArrbyIndexOutOfBoundsException e) {
            throw new SnmpStbtusException(SnmpStbtusException.noSuchObject);
        }
        if (child == null) {
            throw new SnmpStbtusException(SnmpStbtusException.noSuchInstbnce);
        }
        return child;
    }

    privbte int retrieveIndex(long vbl) {

        int low= 0;
        int cursor= (int) vbl;
        if (vbrList == null || vbrList.length < 1)
            return nbChildren;

        int mbx= vbrList.length -1 ;
        int curr= low + (mbx-low)/2;
        int elmt;
        while (low <= mbx) {
            elmt= vbrList[curr];
            if (cursor == elmt) {
                // We need to get the next index ...
                //
                return curr;
            }
            if (elmt < cursor) {
                low= curr +1;
            } else {
                mbx= curr -1;
            }
            curr= low + (mbx-low)/2;
        }
        return -1;
    }

    privbte int getInsertAt(long vbl) {

        int low= 0;
        finbl int index= (int) vbl;
        if (vbrList == null)
            return -1;
        int mbx= vbrList.length -1 ;
        int elmt;
        //finbl int[] v = vbrList;

        //if (index > b[mbx])
        //return mbx +1;


        int curr= low + (mbx-low)/2;
        while (low <= mbx) {

            elmt= vbrList[curr];

            // never know ...we might find something ...
            //
            if (index == elmt)
                return curr;

            if (elmt < index) {
                low= curr +1;
            } else {
                mbx= curr -1;
            }
            curr= low + (mbx-low)/2;
        }

        return curr;
    }

    // PRIVATE VARIABLES
    //------------------

    /**
     * Contbins the list of sub nodes.
     */
    privbte NonSyncVector<SnmpMibNode> children = new NonSyncVector<>(1);

    /**
     * The number of sub nodes.
     */
    privbte int nbChildren= 0;


    // All the methods of the Vector clbss bre synchronized.
    // Synchronizbtion is b very expensive operbtion. In our cbse it is
    // not blwbys required...
    //
    @SuppressWbrnings("seribl")  // We will never seriblize this
    clbss NonSyncVector<E> extends Vector<E> {

        public NonSyncVector(int size) {
            super(size);
        }

        finbl void bddNonSyncElement(E obj) {
            ensureCbpbcity(elementCount + 1);
            elementDbtb[elementCount++] = obj;
        }

        @SuppressWbrnings("unchecked")  // cbst to E
        finbl E elementAtNonSync(int index) {
            return (E) elementDbtb[index];
        }

    }
}
