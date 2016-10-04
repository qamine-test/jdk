/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.util.Hbshtbble;
import jbvb.util.Enumerbtion;

// jmx imports
//
import com.sun.jmx.snmp.SnmpOid;
import com.sun.jmx.snmp.SnmpVblue;
import com.sun.jmx.snmp.SnmpVbrBind;
import com.sun.jmx.snmp.SnmpDefinitions;
import com.sun.jmx.snmp.SnmpStbtusException;

/**
 * The <CODE>SnmpMibNode</CODE> clbss represents b node in bn SNMP MIB.
 * <P>
 * This clbss is used internblly bnd by the clbss generbted by
 * <CODE>mibgen</CODE>.
 * You should not need to use this clbss directly.
 *
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 */
@SuppressWbrnings("seribl") // JDK implementbtion clbss
public bbstrbct clbss SnmpMibNode implements Seriblizbble {

    // ---------------------------------------------------------------------
    // PUBLIC METHODS
    //----------------------------------------------------------------------

    /**
     * Get the next OID brc corresponding to b rebdbble scblbr vbribble,
     * b brbnch lebding to b subgroub, or b tbble.
     *
     * @pbrbm id Id we stbrt from looking for the next.
     * @pbrbm userDbtb A contextubl object contbining user-dbtb.
     *        This object is bllocbted through the <code>
     *        {@link com.sun.jmx.snmp.bgent.SnmpUserDbtbFbctory}</code>
     *        for ebch incoming SNMP request.
     *
     * @return The next id in this group.
     *
     * @exception SnmpStbtusException If no id is found bfter the given id.
     */
    public long getNextVbrId(long id, Object userDbtb)
        throws SnmpStbtusException {
        return getNextIdentifier(vbrList,id);
    }

    /**
     * Get the next OID brc corresponding to b rebdbble scblbr vbribble,
     * b brbnch lebding to b subgroub, or b tbble, possibly skipping over
     * those brcs thbt must not or cbnnot be returned.
     *
     * Cblls {@link #getNextVbrId(long,jbvb.lbng.Object)} until
     * {@link #skipVbribble(long,jbvb.lbng.Object,int)} returns fblse.
     *
     * @pbrbm id Id we stbrt from looking for the next.
     * @pbrbm userDbtb A contextubl object contbining user-dbtb.
     *        This object is bllocbted through the <code>
     *        {@link com.sun.jmx.snmp.bgent.SnmpUserDbtbFbctory}</code>
     *        for ebch incoming SNMP request.
     * @pbrbm pduVersion Protocol version of the originbl request PDU.
     *
     * @return The next id in this group which cbn be returned using
     *         the given PDU's protocol version.
     *
     * @exception SnmpStbtusException If no id is found bfter the given id.
     */
    public long getNextVbrId(long id, Object userDbtb, int pduVersion)
        throws SnmpStbtusException {
        long vbrid=id;
        do {
            vbrid = getNextVbrId(vbrid,userDbtb);
        } while (skipVbribble(vbrid,userDbtb,pduVersion));

        return vbrid;
    }

    /**
     * Hook for subclbsses.
     * The defbult implementbtion of this method is to blwbys return
     * fblse. Subclbsses should redefine this method so thbt it returns
     * true when:
     * <ul><li>the vbribble is b lebf thbt is not instbntibted,</li>
     * <li>or the vbribble is b lebf whose type cbnnot be returned by thbt
     *     version of the protocol (e.g. bn Counter64 with SNMPv1).</li>
     * </ul>
     *
     * @pbrbm id Id we stbrt from looking for the next.
     * @pbrbm userDbtb A contextubl object contbining user-dbtb.
     *        This object is bllocbted through the <code>
     *        {@link com.sun.jmx.snmp.bgent.SnmpUserDbtbFbctory}</code>
     *        for ebch incoming SNMP request.
     * @pbrbm pduVersion Protocol version of the originbl request PDU.
     *
     * @return true if the vbribble must be skipped by the get-next
     *         blgorithm.
     */
    protected boolebn skipVbribble(long id, Object userDbtb, int pduVersion) {
        return fblse;
    }

    /**
     * Find the node which hbndles b vbrbind, bnd register it in the
     * SnmpRequestTree. This method is b pure internbl method. You should
     * never try to cbll it directly.
     *
     * @pbrbm vbrbind  The vbrbind to be hbndled
     *
     * @pbrbm oid      The OID brrby extrbcted from the vbrbind
     *
     * @pbrbm depth    The depth rebched in the OID bt this step of the
     *                 processing.
     *
     * @pbrbm hbndlers The Hbshtbble in which the vbrbind will be registered
     *                 with its hbndling node. This hbshtbble contbins
     *                 <CODE>SnmpRequestTree.Hbndler</CODE> items.
     *
     * @exception SnmpStbtusException No hbndling node wbs found.
     **/
    void findHbndlingNode(SnmpVbrBind vbrbind,
                          long[] oid, int depth,
                          SnmpRequestTree hbndlers)
        throws SnmpStbtusException {
        throw new SnmpStbtusException(SnmpStbtusException.noSuchObject);
    }

    /**
     * Find the node which hbndles the lebf thbt immedibtely follows the
     * given vbrbind OID, bnd register the it in the SnmpRequestTree.
     * This method is b pure internbl method. You should never try to cbll
     * it directly.
     *
     * @pbrbm vbrbind  The vbrbind to be hbndled
     *
     * @pbrbm oid      The OID brrby extrbcted from the vbrbind
     *
     * @pbrbm depth    The depth rebched in the OID bt this step of the
     *                 processing.
     *
     * @pbrbm hbndlers The Hbshtbble in which the vbrbind will be registered
     *                 with its hbndling node. This hbshtbble contbins
     *                 SnmpRequestTree.Hbndler items.
     *
     * @return The SnmpOid of the next lebf.
     *
     * @exception SnmpStbtusException No hbndling node wbs found.
     **/
    long[] findNextHbndlingNode(SnmpVbrBind vbrbind,
                                 long[] oid, int pos, int depth,
                                 SnmpRequestTree hbndlers, AcmChecker checker)
        throws SnmpStbtusException {
        throw new SnmpStbtusException(SnmpStbtusException.noSuchObject);
    }

    /**
     * Generic hbndling of the <CODE>get</CODE> operbtion.
     *
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
    public bbstrbct void get(SnmpMibSubRequest req, int depth)
        throws SnmpStbtusException;

    /**
     * Generic hbndling of the <CODE>set</CODE> operbtion.
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
    public bbstrbct void set(SnmpMibSubRequest req, int depth)
        throws SnmpStbtusException;

    /**
     * Generic hbndling of the <CODE>check</CODE> operbtion.
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
    public bbstrbct void check(SnmpMibSubRequest req, int depth)
        throws SnmpStbtusException;

    /**
     * Sorts the specified integer brrby.
     *
     * @pbrbm brrby An integer brrby.
     */
    stbtic public void sort(int brrby[]) {
        QuickSort(brrby, 0, brrby.length - 1);
    }

    /**
     * Computes the root OID of the MIB.
     */
    public void getRootOid(Vector<Integer> result) {
        return;
    }

    //----------------------------------------------------------------------
    // PACKAGE METHODS
    //----------------------------------------------------------------------

    /**
     * This is b generic version of C.A.R Hobre's Quick Sort
     * blgorithm.  This will hbndle brrbys thbt bre blrebdy
     * sorted, bnd brrbys with duplicbte keys.
     *
     * If you think of b one dimensionbl brrby bs going from
     * the lowest index on the left to the highest index on the right
     * then the pbrbmeters to this function bre lowest index or
     * left bnd highest index or right.  The first time you cbll
     * this function it will be with the pbrbmeters 0, b.length - 1.
     *
     * @pbrbm b An integer brrby.
     * @pbrbm lo0 Left boundbry of brrby pbrtition.
     * @pbrbm hi0 Right boundbry of brrby pbrtition.
     */
    stbtic void QuickSort(int b[], int lo0, int hi0) {
        int lo = lo0;
        int hi = hi0;
        int mid;

        if ( hi0 > lo0) {

            /* Arbitrbrily estbblishing pbrtition element bs the midpoint of
             * the brrby.
             */
            mid = b[ ( lo0 + hi0 ) / 2 ];

            // loop through the brrby until indices cross
            while( lo <= hi ) {
                /* find the first element thbt is grebter thbn or equbl to
                 * the pbrtition element stbrting from the left Index.
                 */
                while( ( lo < hi0 )  && ( b[lo] < mid ))
                    ++lo;

                /* find bn element thbt is smbller thbn or equbl to
                 * the pbrtition element stbrting from the right Index.
                 */
                while( ( hi > lo0 ) && ( b[hi] > mid ))
                    --hi;

                // if the indexes hbve not crossed, swbp
                if( lo <= hi ) {
                    swbp(b, lo, hi);
                    ++lo;
                    --hi;
                }
            }

            /* If the right index hbs not rebched the left side of brrby
             * must now sort the left pbrtition.
             */
            if( lo0 < hi )
                QuickSort( b, lo0, hi );

            /* If the left index hbs not rebched the right side of brrby
             * must now sort the right pbrtition.
             */
            if( lo < hi0 )
                QuickSort( b, lo, hi0 );

        }
    }

    //----------------------------------------------------------------------
    // PROTECTED METHODS
    //----------------------------------------------------------------------

    /**
     * This will give the first element grebter thbn <CODE>vblue</CODE>
     * in b sorted brrby.
     * If there is no element of the brrby grebter thbn <CODE>vblue</CODE>,
     * the method will throw b <CODE>SnmpStbtusException</CODE>.
     *
     * @pbrbm tbble A sorted integer brrby.
     *
     * @pbrbm vblue The grebtest vblue.
     *
     * @exception SnmpStbtusException If there is no element grebter thbn
     *     <CODE>vblue</CODE>.
     */
    finbl stbtic protected int getNextIdentifier(int tbble[], long vblue)
        throws SnmpStbtusException {

        finbl int[] b = tbble;
        finbl int vbl= (int) vblue;

        if (b == null) {
            throw new SnmpStbtusException(SnmpStbtusException.noSuchObject);
        }

        int low= 0;
        int mbx= b.length;
        int curr= low + (mbx-low)/2;
        int elmt= 0;

        // Bbsic check
        //
        if (mbx < 1) {
            throw new SnmpStbtusException(SnmpStbtusException.noSuchObject);
        }

        if (b[mbx-1] <= vbl) {
            throw new SnmpStbtusException(SnmpStbtusException.noSuchObject);
        }

        while (low <= mbx) {
            elmt= b[curr];
            if (vbl == elmt) {
                // We ned to get the next index ...
                //
                curr++;
                return b[curr];
            }
            if (elmt < vbl) {
                low= curr +1;
            } else {
                mbx= curr -1;
            }
            curr= low + (mbx-low)/2;
        }
        return b[curr];
    }


    //----------------------------------------------------------------------
    // PRIVATE METHODS
    //----------------------------------------------------------------------

    finbl stbtic privbte void swbp(int b[], int i, int j) {
        int T;
        T = b[i];
        b[i] = b[j];
        b[j] = T;
    }

    //----------------------------------------------------------------------
    // PROTECTED VARIABLES
    //----------------------------------------------------------------------

    /**
     * Contbins the list of vbribble identifiers.
     */
    protected int[] vbrList;
}
