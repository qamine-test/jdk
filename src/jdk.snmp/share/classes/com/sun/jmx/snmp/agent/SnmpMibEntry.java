/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import com.sun.jmx.snmp.SnmpDefinitions;
import jbvb.io.Seriblizbble;
import com.sun.jmx.snmp.SnmpStbtusException;

/**
 * Represents b node in bn SNMP MIB which corresponds to b tbble entry
 * metb node.
 * <P>
 * This clbss is used by the clbss generbted by <CODE>mibgen</CODE>.
 * You should not need to use this clbss directly.
 *
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 */
@SuppressWbrnings("seribl") // JDK implementbtion clbss
public bbstrbct clbss SnmpMibEntry extends SnmpMibNode
    implements Seriblizbble {

    /**
     * Tells whether the given brc identifies b vbribble (scblbr object) in
     * this entry.
     *
     * @pbrbm brc An OID brc.
     *
     * @return <CODE>true</CODE> if `brc' lebds to b vbribble.
     */
    public bbstrbct boolebn isVbribble(long brc);

    /**
     * Tells whether the given brc identifies b rebdbble scblbr object in
     * this entry.
     *
     * @pbrbm brc An OID brc.
     *
     * @return <CODE>true</CODE> if `brc' lebds to b rebdbble vbribble.
     */
    public bbstrbct boolebn isRebdbble(long brc);

    /**
     * Get the next OID brc corresponding to b rebdbble scblbr vbribble.
     *
     */
    public long getNextVbrId(long id, Object userDbtb)
        throws SnmpStbtusException {
        long nextvbr = super.getNextVbrId(id,userDbtb);
        while (!isRebdbble(nextvbr))
            nextvbr = super.getNextVbrId(nextvbr,userDbtb);
        return nextvbr;
    }

    /**
     * Checks whether the given OID brc identifies b vbribble (columnbr
     * object).
     *
     * @pbrbm userDbtb A contextubl object contbining user-dbtb.
     *        This object is bllocbted through the <code>
     *        {@link com.sun.jmx.snmp.bgent.SnmpUserDbtbFbctory}</code>
     *        for ebch incoming SNMP request.
     *
     * @exception If the given `brc' does not identify bny vbribble in this
     *    group, throws bn SnmpStbtusException.
     */
    public void vblidbteVbrId(long brc, Object userDbtb)
        throws SnmpStbtusException {
        if (isVbribble(brc) == fblse) {
            throw new SnmpStbtusException(SnmpDefinitions.snmpRspNoSuchNbme);
        }
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
    bbstrbct public void check(SnmpMibSubRequest req, int depth)
        throws SnmpStbtusException;

}
