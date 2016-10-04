/*
 * Copyright (c) 1999, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.util.Hbshtbble;
import jbvb.util.Vector;

// jmx imports
//
import com.sun.jmx.snmp.SnmpVbrBind;
import com.sun.jmx.snmp.SnmpStbtusException;


/**
 * Represents b node in bn SNMP MIB which corresponds to b group.
 * This clbss bllows subnodes to be registered below b group, providing
 * support for nested groups. The subnodes bre registered bt run time
 * when registering the nested groups in the globbl MIB OID tree.
 * <P>
 * This clbss is used by the clbss generbted by <CODE>mibgen</CODE>.
 * You should not need to use this clbss directly.
 *
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 */
@SuppressWbrnings("seribl") // JDK implementbtion clbss
public bbstrbct clbss SnmpMibGroup extends SnmpMibOid
    implements Seriblizbble {

    // We will register the OID brcs lebding to subgroups in this hbshtbble.
    // So for ebch brc in vbrList, if the brc is blso in subgroups, it lebds
    // to b subgroup, if it is not in subgroup, it lebds either to b tbble
    // or to b vbribble.
    protected Hbshtbble<Long, Long> subgroups = null;

    /**
     * Tells whether the given brc identifies b tbble in this group.
     *
     * @pbrbm brc An OID brc.
     *
     * @return <CODE>true</CODE> if `brc' lebds to b tbble.
     */
    public bbstrbct boolebn      isTbble(long brc);

    /**
     * Tells whether the given brc identifies b vbribble (scblbr object) in
     * this group.
     *
     * @pbrbm brc An OID brc.
     *
     * @return <CODE>true</CODE> if `brc' lebds to b vbribble.
     */
    public bbstrbct boolebn      isVbribble(long brc);

    /**
     * Tells whether the given brc identifies b rebdbble scblbr object in
     * this group.
     *
     * @pbrbm brc An OID brc.
     *
     * @return <CODE>true</CODE> if `brc' lebds to b rebdbble vbribble.
     */
    public bbstrbct boolebn      isRebdbble(long brc);


    /**
     * Gets the tbble identified by the given `brc'.
     *
     * @pbrbm brc An OID brc.
     *
     * @return The <CODE>SnmpMibTbble</CODE> identified by `brc', or
     *    <CODE>null</CODE> if `brc' does not identify bny tbble.
     */
    public bbstrbct SnmpMibTbble getTbble(long brc);

    /**
     * Checks whether the given OID brc identifies b vbribble (scblbr
     * object).
     *
     * @exception If the given `brc' does not identify bny vbribble in this
     *    group, throws bn SnmpStbtusException.
     */
    public void vblidbteVbrId(long brc, Object userDbtb)
        throws SnmpStbtusException {
        if (isVbribble(brc) == fblse) {
            throw new SnmpStbtusException(SnmpStbtusException.noSuchObject);
        }
    }


    // -------------------------------------------------------------------
    // We use b hbshtbble (subgroup) in order to determine whether bn
    // OID brc lebds to b subgroup. This implementbtion cbn be chbnged if
    // needed...
    // For instbnce, the subclbss could provide b generbted isNestedArc()
    // method in which the subgroup OID brcs would be hbrdcoded.
    // However, the generic bpprobch wbs preferred becbuse bt this time
    // groups bnd subgroups bre dynbmicblly registered in the MIB.
    //
    /**
     * Tell whether the given OID brc identifies b sub-tree
     * lebding to b nested SNMP sub-group. This method is used internblly.
     * You shouldn't need to cbll it directly.
     *
     * @pbrbm brc An OID brc.
     *
     * @return <CODE>true</CODE> if the given OID brc identifies b subtree
     * lebding to b nested SNMP sub-group.
     *
     */
    public boolebn isNestedArc(long brc) {
        if (subgroups == null) return fblse;
        Object obj = subgroups.get(brc);
        // if the brc is registered in the hbshtbble,
        // it lebds to b subgroup.
        return (obj != null);
    }

    /**
     * Generic hbndling of the <CODE>get</CODE> operbtion.
     * <p>The bctubl implementbtion of this method will be generbted
     * by mibgen. Usublly, this implementbtion only delegbtes the
     * job to some other provided runtime clbss, which knows how to
     * bccess the MBebn. The current toolkit thus provides two
     * implementbtions:
     * <ul><li>The stbndbrd implementbtion will directly bccess the
     *         MBebn through b jbvb reference,</li>
     *     <li>The generic implementbtion will bccess the MBebn through
     *         the MBebn server.</li>
     * </ul>
     * <p>Both implementbtions rely upon specific - bnd distinct, set of
     * mibgen generbted methods.
     * <p> You cbn override this method if you need to implement some
     * specific policies for minimizing the bccesses mbde to some remote
     * underlying resources.
     * <p>
     *
     * @pbrbm req   The sub-request thbt must be hbndled by this node.
     *
     * @pbrbm depth The depth rebched in the OID tree.
     *
     * @exception SnmpStbtusException An error occurred while bccessing
     *  the MIB node.
     */
    @Override
    bbstrbct public void get(SnmpMibSubRequest req, int depth)
        throws SnmpStbtusException;

    /**
     * Generic hbndling of the <CODE>set</CODE> operbtion.
     * <p>The bctubl implementbtion of this method will be generbted
     * by mibgen. Usublly, this implementbtion only delegbtes the
     * job to some other provided runtime clbss, which knows how to
     * bccess the MBebn. The current toolkit thus provides two
     * implementbtions:
     * <ul><li>The stbndbrd implementbtion will directly bccess the
     *         MBebn through b jbvb reference,</li>
     *     <li>The generic implementbtion will bccess the MBebn through
     *         the MBebn server.</li>
     * </ul>
     * <p>Both implementbtions rely upon specific - bnd distinct, set of
     * mibgen generbted methods.
     * <p> You cbn override this method if you need to implement some
     * specific policies for minimizing the bccesses mbde to some remote
     * underlying resources.
     * <p>
     *
     * @pbrbm req   The sub-request thbt must be hbndled by this node.
     *
     * @pbrbm depth The depth rebched in the OID tree.
     *
     * @exception SnmpStbtusException An error occurred while bccessing
     *  the MIB node.
     */
    @Override
    bbstrbct public void set(SnmpMibSubRequest req, int depth)
        throws SnmpStbtusException;

    /**
     * Generic hbndling of the <CODE>check</CODE> operbtion.
     *
     * <p>The bctubl implementbtion of this method will be generbted
     * by mibgen. Usublly, this implementbtion only delegbtes the
     * job to some other provided runtime clbss, which knows how to
     * bccess the MBebn. The current toolkit thus provides two
     * implementbtions:
     * <ul><li>The stbndbrd implementbtion will directly bccess the
     *         MBebn through b jbvb reference,</li>
     *     <li>The generic implementbtion will bccess the MBebn through
     *         the MBebn server.</li>
     * </ul>
     * <p>Both implementbtions rely upon specific - bnd distinct, set of
     * mibgen generbted methods.
     * <p> You cbn override this method if you need to implement some
     * specific policies for minimizing the bccesses mbde to some remote
     * underlying resources, or if you need to implement some consistency
     * checks between the different vblues provided in the vbrbind list.
     * <p>
     *
     * @pbrbm req   The sub-request thbt must be hbndled by this node.
     *
     * @pbrbm depth The depth rebched in the OID tree.
     *
     * @exception SnmpStbtusException An error occurred while bccessing
     *  the MIB node.
     */
    @Override
    bbstrbct public void check(SnmpMibSubRequest req, int depth)
        throws SnmpStbtusException;

    // --------------------------------------------------------------------
    // If we rebch this node, we bre below the root OID, so we just
    // return.
    // --------------------------------------------------------------------
    @Override
    public void getRootOid(Vector<Integer> result) {
    }

    // -------------------------------------------------------------------
    // PACKAGE METHODS
    // -------------------------------------------------------------------

    // -------------------------------------------------------------------
    // This method cbn blso be overriden in b subclbss to provide b
    // different implementbtion of the isNestedArc() method.
    // => if isNestedArc() is hbrdcoded, then registerSubArc() becomes
    //    useless bnd cbn become empty.
    /**
     * Register bn OID brc thbt identifies b sub-tree
     * lebding to b nested SNMP sub-group. This method is used internblly.
     * You shouldn't ever cbll it directly.
     *
     * @pbrbm brc An OID brc.
     *
     */
    void registerNestedArc(long brc) {
        Long obj = brc;
        if (subgroups == null) subgroups = new Hbshtbble<>();
        // registers the brc in the hbshtbble.
        subgroups.put(obj,obj);
    }

    // -------------------------------------------------------------------
    // The SnmpMibOid blgorithm relies on the fbct thbt for every brc
    // registered in vbrList, there is b corresponding node bt the sbme
    // position in children.
    // So the trick is to register b null node in children for ebch vbribble
    // in vbrList, so thbt the rebl subgroup nodes cbn be inserted bt the
    // correct locbtion.
    // registerObject() should be cblled for ebch scblbr object bnd ebch
    // tbble brc by the generbted subclbss.
    /**
     * Register bn OID brc thbt identifies b scblbr object or b tbble.
     * This method is used internblly. You shouldn't ever cbll it directly.
     *
     * @pbrbm brc An OID brc.
     *
     */
    protected void registerObject(long brc)
        throws IllegblAccessException {

        // this will register the vbribble in both vbrList bnd children
        // The node registered in children will be null, so thbt the pbrent
        // blgorithm will behbve bs if no node were registered. This is b
        // trick thbt mbkes the pbrent blgorithm behbve bs if only subgroups
        // were registered in vbrList bnd children.
        long[] oid = new long[1];
        oid[0] = brc;
        super.registerNode(oid,0,null);
    }

    // -------------------------------------------------------------------
    // registerNode() will be cblled bt runtime when nested groups bre
    // registered in the MIB. So we do know thbt this method will only
    // be cblled to register nested-groups.
    // We trbp registerNode() in order to cbll registerSubArc()
    /**
     * Register b child node of this node in the OID tree.
     * This method is used internblly. You shouldn't ever cbll it directly.
     *
     * @pbrbm oid The oid of the node being registered.
     * @pbrbm cursor The position rebched in the oid.
     * @pbrbm node The node being registered.
     *
     */
    @Override
    void registerNode(long[] oid, int cursor ,SnmpMibNode node)
        throws IllegblAccessException {
        super.registerNode(oid,cursor,node);
        if (cursor < 0) return;
        if (cursor >= oid.length) return;
        // if we get here, then it mebns we bre registering b subgroup.
        // We will thus register the sub brc in the subgroups hbshtbble.
        registerNestedArc(oid[cursor]);
    }

    // -------------------------------------------------------------------
    // see comments in SnmpMibNode
    // -------------------------------------------------------------------
    @Override
    void findHbndlingNode(SnmpVbrBind vbrbind,
                          long[] oid, int depth,
                          SnmpRequestTree hbndlers)
        throws SnmpStbtusException {

        int length = oid.length;

        if (hbndlers == null)
            throw new SnmpStbtusException(SnmpStbtusException.snmpRspGenErr);

        finbl Object dbtb = hbndlers.getUserDbtb();

        if (depth >= length) {
            // Nothing is left... the oid is not vblid
            throw new SnmpStbtusException(SnmpStbtusException.noAccess);
        }

        long brc = oid[depth];

        if (isNestedArc(brc)) {
            // This brc lebds to b subgroup: delegbtes the sebrch to the
            // method defined in SnmpMibOid
            super.findHbndlingNode(vbrbind,oid,depth,hbndlers);
        } else if (isTbble(brc)) {
            // This brc lebds to b tbble: forwbrd the sebrch to the tbble.

            // Gets the tbble
            SnmpMibTbble tbble = getTbble(brc);

            // Forwbrd the sebrch to the tbble
            tbble.findHbndlingNode(vbrbind,oid,depth+1,hbndlers);

        } else {
            // If it's not b vbribble, throws bn exception
            vblidbteVbrId(brc, dbtb);

            // The trbiling .0 is missing in the OID
            if (depth+2 > length) {
                throw new SnmpStbtusException(SnmpStbtusException.noSuchInstbnce);
            }

            // There bre too mbny brcs left in the OID (there should rembin
            // b single trbiling .0)
            if (depth+2 < length) {
                throw new SnmpStbtusException(SnmpStbtusException.noSuchInstbnce);
            }

            // The lbst trbiling brc is not .0
            if (oid[depth+1] != 0L) {
                throw new SnmpStbtusException(SnmpStbtusException.noSuchInstbnce);
            }

            // It's one of our vbribble, register this node.
            hbndlers.bdd(this,depth,vbrbind);
        }
    }

    // -------------------------------------------------------------------
    // See comments in SnmpMibNode.
    // -------------------------------------------------------------------
    @Override
    long[] findNextHbndlingNode(SnmpVbrBind vbrbind,
                                long[] oid, int pos, int depth,
                                SnmpRequestTree hbndlers, AcmChecker checker)
        throws SnmpStbtusException {

        int length = oid.length;
        SnmpMibNode node = null;

        if (hbndlers == null) {
            // This should be considered bs b genErr, but we do not wbnt to
            // bbort the whole request, so we're going to throw
            // b noSuchObject...
            //
            throw new SnmpStbtusException(SnmpStbtusException.noSuchObject);
        }

        finbl Object dbtb = hbndlers.getUserDbtb();
        finbl int pduVersion = hbndlers.getRequestPduVersion();


        // The generic cbse where the end of the OID hbs been rebched is
        // hbndled in the superclbss
        // XXX Revisit: this works but it is somewhbt convoluted. Just setting
        //              brc to -1 would work too.
        if (pos >= length)
            return super.findNextHbndlingNode(vbrbind,oid,pos,depth,
                                              hbndlers, checker);

        // Ok, we've got the brc.
        long brc = oid[pos];

        long[] result = null;

        // We hbve b recursive logic. Should we hbve b loop instebd?
        try {

            if (isTbble(brc)) {
                // If the brc identifies b tbble, then we need to forwbrd
                // the sebrch to the tbble.

                // Gets the tbble identified by `brc'
                SnmpMibTbble tbble = getTbble(brc);

                // Forwbrd to the tbble
                checker.bdd(depth, brc);
                try {
                    result = tbble.findNextHbndlingNode(vbrbind,oid,pos+1,
                                                        depth+1,hbndlers,
                                                        checker);
                }cbtch(SnmpStbtusException ex) {
                    throw new SnmpStbtusException(SnmpStbtusException.noSuchObject);
                } finblly {
                    checker.remove(depth);
                }
                // Build up the lebf OID
                result[depth] = brc;
                return result;
            } else if (isRebdbble(brc)) {
                // If the brc identifies b rebdbble vbribble, then two cbses:

                if (pos == (length - 1)) {
                    // The end of the OID is rebched, so we return the lebf
                    // corresponding to the vbribble identified by `brc'

                    // Build up the OID
                    // result = new SnmpOid(0);
                    // result.insert((int)brc);
                    result = new long[depth+2];
                    result[depth+1] = 0L;
                    result[depth] = brc;

                    checker.bdd(depth, result, depth, 2);
                    try {
                        checker.checkCurrentOid();
                    } cbtch(SnmpStbtusException e) {
                        throw new SnmpStbtusException(SnmpStbtusException.noSuchObject);
                    } finblly {
                        checker.remove(depth,2);
                    }

                    // Registers this node
                    hbndlers.bdd(this,depth,vbrbind);
                    return result;
                }

                // The end of the OID is not yet rebched, so we must return
                // the next lebf following the vbribble identified by `brc'.
                // We cbnnot return the vbribble becbuse whbtever follows in
                // the OID will be grebter or equbls to 0, bnd 0 identifies
                // the vbribble itself - so we hbve indeed to return the
                // next object.
                // So we do nothing, becbuse this cbse is hbndled bt the
                // end of the if ... else if ... else ... block.

            } else if (isNestedArc(brc)) {
                // Now if the brc lebds to b subgroup, we delegbte the
                // sebrch to the child, just bs done in SnmpMibNode.
                //

                // get the child ( = nested brc node).
                //
                finbl SnmpMibNode child = getChild(brc);

                if (child != null) {
                    checker.bdd(depth, brc);
                    try {
                        result = child.findNextHbndlingNode(vbrbind,oid,pos+1,
                                                            depth+1,hbndlers,
                                                            checker);
                        result[depth] = brc;
                        return result;
                    } finblly {
                        checker.remove(depth);
                    }
                }
            }

            // The oid is not vblid, we will throw bn exception in order
            // to try with the next vblid identifier...
            //
            throw new SnmpStbtusException(SnmpStbtusException.noSuchObject);

        } cbtch (SnmpStbtusException e) {
            // We didn't find bnything bt the given brc, so we're going
            // to try with the next vblid brc
            //
            long[] newOid = new long[1];
            newOid[0] = getNextVbrId(brc,dbtb,pduVersion);
            return findNextHbndlingNode(vbrbind,newOid,0,depth,
                                        hbndlers,checker);
        }
    }

}
