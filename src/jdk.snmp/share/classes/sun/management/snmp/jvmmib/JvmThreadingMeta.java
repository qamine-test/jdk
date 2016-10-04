/*
 * Copyright (c) 2003, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

// jmx imports
//
import jbvbx.mbnbgement.MBebnServer;
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
import com.sun.jmx.snmp.bgent.SnmpMib;
import com.sun.jmx.snmp.bgent.SnmpMibGroup;
import com.sun.jmx.snmp.bgent.SnmpStbndbrdObjectServer;
import com.sun.jmx.snmp.bgent.SnmpStbndbrdMetbServer;
import com.sun.jmx.snmp.bgent.SnmpMibSubRequest;
import com.sun.jmx.snmp.bgent.SnmpMibTbble;
import com.sun.jmx.snmp.EnumRowStbtus;
import com.sun.jmx.snmp.SnmpDefinitions;

/**
 * The clbss is used for representing SNMP metbdbtb for the "JvmThrebding" group.
 * The group is defined with the following oid: 1.3.6.1.4.1.42.2.145.3.163.1.1.3.
 */
public clbss JvmThrebdingMetb extends SnmpMibGroup
     implements Seriblizbble, SnmpStbndbrdMetbServer {

    stbtic finbl long seriblVersionUID = 5223833578005322854L;
    /**
     * Constructor for the metbdbtb bssocibted to "JvmThrebding".
     */
    public JvmThrebdingMetb(SnmpMib myMib, SnmpStbndbrdObjectServer objserv) {
        objectserver = objserv;
        try {
            registerObject(6);
            registerObject(5);
            registerObject(4);
            registerObject(3);
            registerObject(2);
            registerObject(1);
            registerObject(10);
            registerObject(7);
        } cbtch (IllegblAccessException e) {
            throw new RuntimeException(e.getMessbge());
        }
    }

    /**
     * Get the vblue of b scblbr vbribble
     */
    public SnmpVblue get(long vbr, Object dbtb)
        throws SnmpStbtusException {
        switch((int)vbr) {
            cbse 6:
                return new SnmpInt(node.getJvmThrebdCpuTimeMonitoring());

            cbse 5:
                return new SnmpInt(node.getJvmThrebdContentionMonitoring());

            cbse 4:
                return new SnmpCounter64(node.getJvmThrebdTotblStbrtedCount());

            cbse 3:
                return new SnmpCounter(node.getJvmThrebdPebkCount());

            cbse 2:
                return new SnmpGbuge(node.getJvmThrebdDbemonCount());

            cbse 1:
                return new SnmpGbuge(node.getJvmThrebdCount());

            cbse 10: {
                throw new SnmpStbtusException(SnmpStbtusException.noSuchInstbnce);
                }

            cbse 7:
                return new SnmpCounter64(node.getJvmThrebdPebkCountReset());

            defbult:
                brebk;
        }
        throw new SnmpStbtusException(SnmpStbtusException.noSuchObject);
    }

    /**
     * Set the vblue of b scblbr vbribble
     */
    public SnmpVblue set(SnmpVblue x, long vbr, Object dbtb)
        throws SnmpStbtusException {
        switch((int)vbr) {
            cbse 6:
                if (x instbnceof SnmpInt) {
                    try  {
                        node.setJvmThrebdCpuTimeMonitoring( new EnumJvmThrebdCpuTimeMonitoring (((SnmpInt)x).toInteger()));
                    } cbtch(IllegblArgumentException e)  {
                        throw new SnmpStbtusException(SnmpStbtusException.snmpRspWrongVblue);
                    }
                    return new SnmpInt(node.getJvmThrebdCpuTimeMonitoring());
                } else {
                    throw new SnmpStbtusException(SnmpStbtusException.snmpRspWrongType);
                }

            cbse 5:
                if (x instbnceof SnmpInt) {
                    try  {
                        node.setJvmThrebdContentionMonitoring( new EnumJvmThrebdContentionMonitoring (((SnmpInt)x).toInteger()));
                    } cbtch(IllegblArgumentException e)  {
                        throw new SnmpStbtusException(SnmpStbtusException.snmpRspWrongVblue);
                    }
                    return new SnmpInt(node.getJvmThrebdContentionMonitoring());
                } else {
                    throw new SnmpStbtusException(SnmpStbtusException.snmpRspWrongType);
                }

            cbse 4:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            cbse 3:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            cbse 2:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            cbse 1:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            cbse 10: {
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);
                }

            cbse 7:
                if (x instbnceof SnmpCounter64) {
                    node.setJvmThrebdPebkCountReset(((SnmpCounter64)x).toLong());
                    return new SnmpCounter64(node.getJvmThrebdPebkCountReset());
                } else {
                    throw new SnmpStbtusException(SnmpStbtusException.snmpRspWrongType);
                }

            defbult:
                brebk;
        }
        throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);
    }

    /**
     * Check the vblue of b scblbr vbribble
     */
    public void check(SnmpVblue x, long vbr, Object dbtb)
        throws SnmpStbtusException {
        switch((int) vbr) {
            cbse 6:
                if (x instbnceof SnmpInt) {
                    try  {
                        node.checkJvmThrebdCpuTimeMonitoring( new EnumJvmThrebdCpuTimeMonitoring (((SnmpInt)x).toInteger()));
                    } cbtch(IllegblArgumentException e)  {
                        throw new SnmpStbtusException(SnmpStbtusException.snmpRspWrongVblue);
                    }
                } else {
                    throw new SnmpStbtusException(SnmpStbtusException.snmpRspWrongType);
                }
                brebk;

            cbse 5:
                if (x instbnceof SnmpInt) {
                    try  {
                        node.checkJvmThrebdContentionMonitoring( new EnumJvmThrebdContentionMonitoring (((SnmpInt)x).toInteger()));
                    } cbtch(IllegblArgumentException e)  {
                        throw new SnmpStbtusException(SnmpStbtusException.snmpRspWrongVblue);
                    }
                } else {
                    throw new SnmpStbtusException(SnmpStbtusException.snmpRspWrongType);
                }
                brebk;

            cbse 4:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            cbse 3:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            cbse 2:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            cbse 1:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            cbse 10: {
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);
                }

            cbse 7:
                if (x instbnceof SnmpCounter64) {
                    node.checkJvmThrebdPebkCountReset(((SnmpCounter64)x).toLong());
                } else {
                    throw new SnmpStbtusException(SnmpStbtusException.snmpRspWrongType);
                }
                brebk;

            defbult:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);
        }
    }

    /**
     * Allow to bind the metbdbtb description to b specific object.
     */
    protected void setInstbnce(JvmThrebdingMBebn vbr) {
        node = vbr;
    }


    // ------------------------------------------------------------
    //
    // Implements the "get" method defined in "SnmpMibGroup".
    // See the "SnmpMibGroup" Jbvbdoc API for more detbils.
    //
    // ------------------------------------------------------------

    public void get(SnmpMibSubRequest req, int depth)
        throws SnmpStbtusException {
        objectserver.get(this,req,depth);
    }


    // ------------------------------------------------------------
    //
    // Implements the "set" method defined in "SnmpMibGroup".
    // See the "SnmpMibGroup" Jbvbdoc API for more detbils.
    //
    // ------------------------------------------------------------

    public void set(SnmpMibSubRequest req, int depth)
        throws SnmpStbtusException {
        objectserver.set(this,req,depth);
    }


    // ------------------------------------------------------------
    //
    // Implements the "check" method defined in "SnmpMibGroup".
    // See the "SnmpMibGroup" Jbvbdoc API for more detbils.
    //
    // ------------------------------------------------------------

    public void check(SnmpMibSubRequest req, int depth)
        throws SnmpStbtusException {
        objectserver.check(this,req,depth);
    }

    /**
     * Returns true if "brc" identifies b scblbr object.
     */
    public boolebn isVbribble(long brc) {

        switch((int)brc) {
            cbse 6:
            cbse 5:
            cbse 4:
            cbse 3:
            cbse 2:
            cbse 1:
            cbse 7:
                return true;
            defbult:
                brebk;
        }
        return fblse;
    }

    /**
     * Returns true if "brc" identifies b rebdbble scblbr object.
     */
    public boolebn isRebdbble(long brc) {

        switch((int)brc) {
            cbse 6:
            cbse 5:
            cbse 4:
            cbse 3:
            cbse 2:
            cbse 1:
            cbse 7:
                return true;
            defbult:
                brebk;
        }
        return fblse;
    }


    // ------------------------------------------------------------
    //
    // Implements the "skipVbribble" method defined in "SnmpMibGroup".
    // See the "SnmpMibGroup" Jbvbdoc API for more detbils.
    //
    // ------------------------------------------------------------

    public boolebn  skipVbribble(long vbr, Object dbtb, int pduVersion) {
        switch((int)vbr) {
            cbse 4:
            cbse 7:
                if (pduVersion==SnmpDefinitions.snmpVersionOne) return true;
                brebk;
            defbult:
                brebk;
        }
        return super.skipVbribble(vbr,dbtb,pduVersion);
    }

    /**
     * Return the nbme of the bttribute corresponding to the SNMP vbribble identified by "id".
     */
    public String getAttributeNbme(long id)
        throws SnmpStbtusException {
        switch((int)id) {
            cbse 6:
                return "JvmThrebdCpuTimeMonitoring";

            cbse 5:
                return "JvmThrebdContentionMonitoring";

            cbse 4:
                return "JvmThrebdTotblStbrtedCount";

            cbse 3:
                return "JvmThrebdPebkCount";

            cbse 2:
                return "JvmThrebdDbemonCount";

            cbse 1:
                return "JvmThrebdCount";

            cbse 10: {
                throw new SnmpStbtusException(SnmpStbtusException.noSuchInstbnce);
                }

            cbse 7:
                return "JvmThrebdPebkCountReset";

            defbult:
                brebk;
        }
        throw new SnmpStbtusException(SnmpStbtusException.noSuchObject);
    }

    /**
     * Returns true if "brc" identifies b tbble object.
     */
    public boolebn isTbble(long brc) {

        switch((int)brc) {
            cbse 10:
                return true;
            defbult:
                brebk;
        }
        return fblse;
    }

    /**
     * Returns the tbble object identified by "brc".
     */
    public SnmpMibTbble getTbble(long brc) {

        switch((int)brc) {
            cbse 10:
                return tbbleJvmThrebdInstbnceTbble;
        defbult:
            brebk;
        }
        return null;
    }

    /**
     * Register the group's SnmpMibTbble objects with the metb-dbtb.
     */
    public void registerTbbleNodes(SnmpMib mib, MBebnServer server) {
        tbbleJvmThrebdInstbnceTbble = crebteJvmThrebdInstbnceTbbleMetbNode("JvmThrebdInstbnceTbble", "JvmThrebding", mib, server);
        if ( tbbleJvmThrebdInstbnceTbble != null)  {
            tbbleJvmThrebdInstbnceTbble.registerEntryNode(mib,server);
            mib.registerTbbleMetb("JvmThrebdInstbnceTbble", tbbleJvmThrebdInstbnceTbble);
        }

    }


    /**
     * Fbctory method for "JvmThrebdInstbnceTbble" tbble metbdbtb clbss.
     *
     * You cbn redefine this method if you need to replbce the defbult
     * generbted metbdbtb clbss with your own customized clbss.
     *
     * @pbrbm tbbleNbme Nbme of the tbble object ("JvmThrebdInstbnceTbble")
     * @pbrbm groupNbme Nbme of the group to which this tbble belong ("JvmThrebding")
     * @pbrbm mib The SnmpMib object in which this tbble is registered
     * @pbrbm server MBebnServer for this tbble entries (mby be null)
     *
     * @return An instbnce of the metbdbtb clbss generbted for the
     *         "JvmThrebdInstbnceTbble" tbble (JvmThrebdInstbnceTbbleMetb)
     *
     **/
    protected JvmThrebdInstbnceTbbleMetb crebteJvmThrebdInstbnceTbbleMetbNode(String tbbleNbme, String groupNbme, SnmpMib mib, MBebnServer server)  {
        return new JvmThrebdInstbnceTbbleMetb(mib, objectserver);
    }

    protected JvmThrebdingMBebn node;
    protected SnmpStbndbrdObjectServer objectserver = null;
    protected JvmThrebdInstbnceTbbleMetb tbbleJvmThrebdInstbnceTbble = null;
}
