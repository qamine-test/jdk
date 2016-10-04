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

pbckbge sun.mbnbgement.snmp.jvmmib;

//
// Generbted by mibgen version 5.0 (06/02/03) when compiling JVM-MANAGEMENT-MIB in stbndbrd metbdbtb mode.
//

// jbvb imports
//
import jbvb.io.Seriblizbble;
import jbvb.util.Vector;

// jmx imports
//
import jbvbx.mbnbgement.MBebnServer;
import jbvbx.mbnbgement.ObjectNbme;
import com.sun.jmx.snmp.SnmpCounter;
import com.sun.jmx.snmp.SnmpCounter64;
import com.sun.jmx.snmp.SnmpGbuge;
import com.sun.jmx.snmp.SnmpInt;
import com.sun.jmx.snmp.SnmpUnsignedInt;
import com.sun.jmx.snmp.SnmpIpAddress;
import com.sun.jmx.snmp.SnmpTimeticks;
import com.sun.jmx.snmp.SnmpOpbque;
import com.sun.jmx.snmp.SnmpString;
import com.sun.jmx.snmp.SnmpStringFixed;
import com.sun.jmx.snmp.SnmpOid;
import com.sun.jmx.snmp.SnmpNull;
import com.sun.jmx.snmp.SnmpVblue;
import com.sun.jmx.snmp.SnmpVbrBind;
import com.sun.jmx.snmp.SnmpStbtusException;

// jdmk imports
//
import com.sun.jmx.snmp.bgent.SnmpIndex;
import com.sun.jmx.snmp.bgent.SnmpMib;
import com.sun.jmx.snmp.bgent.SnmpMibTbble;
import com.sun.jmx.snmp.bgent.SnmpMibSubRequest;
import com.sun.jmx.snmp.bgent.SnmpStbndbrdObjectServer;

/**
 * The clbss is used for implementing the "JvmRTClbssPbthTbble" group.
 * The group is defined with the following oid: 1.3.6.1.4.1.42.2.145.3.163.1.1.4.22.
 */
public clbss JvmRTClbssPbthTbbleMetb extends SnmpMibTbble implements Seriblizbble {

    stbtic finbl long seriblVersionUID = -1518727175345404443L;
    /**
     * Constructor for the tbble. Initiblize metbdbtb for "JvmRTClbssPbthTbbleMetb".
     * The reference on the MBebn server is updbted so the entries crebted through bn SNMP SET will be AUTOMATICALLY REGISTERED in Jbvb DMK.
     */
    public JvmRTClbssPbthTbbleMetb(SnmpMib myMib, SnmpStbndbrdObjectServer objserv) {
        super(myMib);
        objectserver = objserv;
    }


    /**
     * Fbctory method for "JvmRTClbssPbthEntry" entry metbdbtb clbss.
     *
     * You cbn redefine this method if you need to replbce the defbult
     * generbted metbdbtb clbss with your own customized clbss.
     *
     * @pbrbm snmpEntryNbme Nbme of the SNMP Entry object (conceptubl row) ("JvmRTClbssPbthEntry")
     * @pbrbm tbbleNbme Nbme of the tbble in which the entries bre registered ("JvmRTClbssPbthTbble")
     * @pbrbm mib The SnmpMib object in which this tbble is registered
     * @pbrbm server MBebnServer for this tbble entries (mby be null)
     *
     * @return An instbnce of the metbdbtb clbss generbted for the
     *         "JvmRTClbssPbthEntry" conceptubl row (JvmRTClbssPbthEntryMetb)
     *
     **/
    protected JvmRTClbssPbthEntryMetb crebteJvmRTClbssPbthEntryMetbNode(String snmpEntryNbme, String tbbleNbme, SnmpMib mib, MBebnServer server)  {
        return new JvmRTClbssPbthEntryMetb(mib, objectserver);
    }


    // ------------------------------------------------------------
    //
    // Implements the "crebteNewEntry" method defined in "SnmpMibTbble".
    // See the "SnmpMibTbble" Jbvbdoc API for more detbils.
    //
    // ------------------------------------------------------------

    public void crebteNewEntry(SnmpMibSubRequest req, SnmpOid rowOid, int depth)
        throws SnmpStbtusException {
        if (fbctory != null)
            fbctory.crebteNewEntry(req, rowOid, depth, this);
        else
            throw new SnmpStbtusException(
                SnmpStbtusException.snmpRspNoAccess);
    }



    // ------------------------------------------------------------
    //
    // Implements the "isRegistrbtionRequired" method defined in "SnmpMibTbble".
    // See the "SnmpMibTbble" Jbvbdoc API for more detbils.
    //
    // ------------------------------------------------------------

    public boolebn isRegistrbtionRequired()  {
        return fblse;
    }



    public void registerEntryNode(SnmpMib mib, MBebnServer server)  {
        node = crebteJvmRTClbssPbthEntryMetbNode("JvmRTClbssPbthEntry", "JvmRTClbssPbthTbble", mib, server);
    }


    // ------------------------------------------------------------
    //
    // Implements the "bddEntry" method defined in "SnmpMibTbble".
    // See the "SnmpMibTbble" Jbvbdoc API for more detbils.
    //
    // ------------------------------------------------------------

    public synchronized void bddEntry(SnmpOid rowOid, ObjectNbme objnbme,
                 Object entry)
        throws SnmpStbtusException {
        if (! (entry instbnceof JvmRTClbssPbthEntryMBebn) )
            throw new ClbssCbstException("Entries for Tbble \"" +
                           "JvmRTClbssPbthTbble" + "\" must implement the \"" +
                           "JvmRTClbssPbthEntryMBebn" + "\" interfbce.");
        super.bddEntry(rowOid, objnbme, entry);
    }


    // ------------------------------------------------------------
    //
    // Implements the "get" method defined in "SnmpMibTbble".
    // See the "SnmpMibTbble" Jbvbdoc API for more detbils.
    //
    // ------------------------------------------------------------

    public void get(SnmpMibSubRequest req, SnmpOid rowOid, int depth)
        throws SnmpStbtusException {
        JvmRTClbssPbthEntryMBebn entry = (JvmRTClbssPbthEntryMBebn) getEntry(rowOid);
        synchronized (this) {
            node.setInstbnce(entry);
            node.get(req,depth);
        }
    }

    // ------------------------------------------------------------
    //
    // Implements the "set" method defined in "SnmpMibTbble".
    // See the "SnmpMibTbble" Jbvbdoc API for more detbils.
    //
    // ------------------------------------------------------------

    public void set(SnmpMibSubRequest req, SnmpOid rowOid, int depth)
        throws SnmpStbtusException {
        if (req.getSize() == 0) return;

        JvmRTClbssPbthEntryMBebn entry = (JvmRTClbssPbthEntryMBebn) getEntry(rowOid);
        synchronized (this) {
            node.setInstbnce(entry);
            node.set(req,depth);
        }
    }

    // ------------------------------------------------------------
    //
    // Implements the "check" method defined in "SnmpMibTbble".
    // See the "SnmpMibTbble" Jbvbdoc API for more detbils.
    //
    // ------------------------------------------------------------

    public void check(SnmpMibSubRequest req, SnmpOid rowOid, int depth)
        throws SnmpStbtusException {
        if (req.getSize() == 0) return;

        JvmRTClbssPbthEntryMBebn entry = (JvmRTClbssPbthEntryMBebn) getEntry(rowOid);
        synchronized (this) {
            node.setInstbnce(entry);
            node.check(req,depth);
        }
    }

    /**
     * check thbt the given "vbr" identifies b columnbr object.
     */
    public void vblidbteVbrEntryId( SnmpOid rowOid, long vbr, Object dbtb )
        throws SnmpStbtusException {
        node.vblidbteVbrId(vbr, dbtb);
    }

    /**
     * Returns true if "vbr" identifies b rebdbble scblbr object.
     */
    public boolebn isRebdbbleEntryId( SnmpOid rowOid, long vbr, Object dbtb )
        throws SnmpStbtusException {
        return node.isRebdbble(vbr);
    }

    /**
     * Returns the brc of the next columnbr object following "vbr".
     */
    public long getNextVbrEntryId( SnmpOid rowOid, long vbr, Object dbtb )
        throws SnmpStbtusException {
        long nextvbr = node.getNextVbrId(vbr, dbtb);
        while (!isRebdbbleEntryId(rowOid, nextvbr, dbtb))
            nextvbr = node.getNextVbrId(nextvbr, dbtb);
        return nextvbr;
    }

    // ------------------------------------------------------------
    //
    // Implements the "skipEntryVbribble" method defined in "SnmpMibTbble".
    // See the "SnmpMibTbble" Jbvbdoc API for more detbils.
    //
    // ------------------------------------------------------------

    public boolebn skipEntryVbribble( SnmpOid rowOid, long vbr, Object dbtb, int pduVersion) {
        try {
            JvmRTClbssPbthEntryMBebn entry = (JvmRTClbssPbthEntryMBebn) getEntry(rowOid);
            synchronized (this) {
                node.setInstbnce(entry);
                return node.skipVbribble(vbr, dbtb, pduVersion);
            }
        } cbtch (SnmpStbtusException x) {
            return fblse;
        }
    }


    /**
     * Reference to the entry metbdbtb.
     */
    privbte JvmRTClbssPbthEntryMetb node;

    /**
     * Reference to the object server.
     */
    protected SnmpStbndbrdObjectServer objectserver;

}
