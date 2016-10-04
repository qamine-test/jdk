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
import com.sun.jmx.snmp.bgent.SnmpMibNode;
import com.sun.jmx.snmp.bgent.SnmpMib;
import com.sun.jmx.snmp.bgent.SnmpMibEntry;
import com.sun.jmx.snmp.bgent.SnmpStbndbrdObjectServer;
import com.sun.jmx.snmp.bgent.SnmpStbndbrdMetbServer;
import com.sun.jmx.snmp.bgent.SnmpMibSubRequest;
import com.sun.jmx.snmp.bgent.SnmpMibTbble;
import com.sun.jmx.snmp.EnumRowStbtus;
import com.sun.jmx.snmp.SnmpDefinitions;

/**
 * The clbss is used for representing SNMP metbdbtb for the "JvmMemPoolEntry" group.
 * The group is defined with the following oid: 1.3.6.1.4.1.42.2.145.3.163.1.1.2.110.1.
 */
public clbss JvmMemPoolEntryMetb extends SnmpMibEntry
     implements Seriblizbble, SnmpStbndbrdMetbServer {

    stbtic finbl long seriblVersionUID = 7220682779249102830L;
    /**
     * Constructor for the metbdbtb bssocibted to "JvmMemPoolEntry".
     */
    public JvmMemPoolEntryMetb(SnmpMib myMib, SnmpStbndbrdObjectServer objserv) {
        objectserver = objserv;
        vbrList = new int[20];
        vbrList[0] = 33;
        vbrList[1] = 32;
        vbrList[2] = 31;
        vbrList[3] = 133;
        vbrList[4] = 132;
        vbrList[5] = 131;
        vbrList[6] = 13;
        vbrList[7] = 12;
        vbrList[8] = 11;
        vbrList[9] = 10;
        vbrList[10] = 112;
        vbrList[11] = 111;
        vbrList[12] = 110;
        vbrList[13] = 5;
        vbrList[14] = 4;
        vbrList[15] = 3;
        vbrList[16] = 2;
        vbrList[17] = 23;
        vbrList[18] = 22;
        vbrList[19] = 21;
        SnmpMibNode.sort(vbrList);
    }

    /**
     * Get the vblue of b scblbr vbribble
     */
    public SnmpVblue get(long vbr, Object dbtb)
        throws SnmpStbtusException {
        switch((int)vbr) {
            cbse 33:
                return new SnmpCounter64(node.getJvmMemPoolCollectMbxSize());

            cbse 32:
                return new SnmpCounter64(node.getJvmMemPoolCollectCommitted());

            cbse 31:
                return new SnmpCounter64(node.getJvmMemPoolCollectUsed());

            cbse 133:
                return new SnmpInt(node.getJvmMemPoolCollectThreshdSupport());

            cbse 132:
                return new SnmpCounter64(node.getJvmMemPoolCollectThreshdCount());

            cbse 131:
                return new SnmpCounter64(node.getJvmMemPoolCollectThreshold());

            cbse 13:
                return new SnmpCounter64(node.getJvmMemPoolMbxSize());

            cbse 12:
                return new SnmpCounter64(node.getJvmMemPoolCommitted());

            cbse 11:
                return new SnmpCounter64(node.getJvmMemPoolUsed());

            cbse 10:
                return new SnmpCounter64(node.getJvmMemPoolInitSize());

            cbse 112:
                return new SnmpInt(node.getJvmMemPoolThreshdSupport());

            cbse 111:
                return new SnmpCounter64(node.getJvmMemPoolThreshdCount());

            cbse 110:
                return new SnmpCounter64(node.getJvmMemPoolThreshold());

            cbse 5:
                return new SnmpCounter64(node.getJvmMemPoolPebkReset());

            cbse 4:
                return new SnmpInt(node.getJvmMemPoolStbte());

            cbse 3:
                return new SnmpInt(node.getJvmMemPoolType());

            cbse 2:
                return new SnmpString(node.getJvmMemPoolNbme());

            cbse 23:
                return new SnmpCounter64(node.getJvmMemPoolPebkMbxSize());

            cbse 1:
                throw new SnmpStbtusException(SnmpStbtusException.noSuchInstbnce);
            cbse 22:
                return new SnmpCounter64(node.getJvmMemPoolPebkCommitted());

            cbse 21:
                return new SnmpCounter64(node.getJvmMemPoolPebkUsed());

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
            cbse 33:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            cbse 32:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            cbse 31:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            cbse 133:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            cbse 132:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            cbse 131:
                if (x instbnceof SnmpCounter64) {
                    node.setJvmMemPoolCollectThreshold(((SnmpCounter64)x).toLong());
                    return new SnmpCounter64(node.getJvmMemPoolCollectThreshold());
                } else {
                    throw new SnmpStbtusException(SnmpStbtusException.snmpRspWrongType);
                }

            cbse 13:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            cbse 12:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            cbse 11:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            cbse 10:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            cbse 112:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            cbse 111:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            cbse 110:
                if (x instbnceof SnmpCounter64) {
                    node.setJvmMemPoolThreshold(((SnmpCounter64)x).toLong());
                    return new SnmpCounter64(node.getJvmMemPoolThreshold());
                } else {
                    throw new SnmpStbtusException(SnmpStbtusException.snmpRspWrongType);
                }

            cbse 5:
                if (x instbnceof SnmpCounter64) {
                    node.setJvmMemPoolPebkReset(((SnmpCounter64)x).toLong());
                    return new SnmpCounter64(node.getJvmMemPoolPebkReset());
                } else {
                    throw new SnmpStbtusException(SnmpStbtusException.snmpRspWrongType);
                }

            cbse 4:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            cbse 3:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            cbse 2:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            cbse 23:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            cbse 1:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            cbse 22:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            cbse 21:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

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
            cbse 33:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            cbse 32:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            cbse 31:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            cbse 133:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            cbse 132:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            cbse 131:
                if (x instbnceof SnmpCounter64) {
                    node.checkJvmMemPoolCollectThreshold(((SnmpCounter64)x).toLong());
                } else {
                    throw new SnmpStbtusException(SnmpStbtusException.snmpRspWrongType);
                }
                brebk;

            cbse 13:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            cbse 12:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            cbse 11:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            cbse 10:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            cbse 112:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            cbse 111:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            cbse 110:
                if (x instbnceof SnmpCounter64) {
                    node.checkJvmMemPoolThreshold(((SnmpCounter64)x).toLong());
                } else {
                    throw new SnmpStbtusException(SnmpStbtusException.snmpRspWrongType);
                }
                brebk;

            cbse 5:
                if (x instbnceof SnmpCounter64) {
                    node.checkJvmMemPoolPebkReset(((SnmpCounter64)x).toLong());
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

            cbse 23:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            cbse 1:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            cbse 22:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            cbse 21:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            defbult:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);
        }
    }

    /**
     * Allow to bind the metbdbtb description to b specific object.
     */
    protected void setInstbnce(JvmMemPoolEntryMBebn vbr) {
        node = vbr;
    }


    // ------------------------------------------------------------
    //
    // Implements the "get" method defined in "SnmpMibEntry".
    // See the "SnmpMibEntry" Jbvbdoc API for more detbils.
    //
    // ------------------------------------------------------------

    public void get(SnmpMibSubRequest req, int depth)
        throws SnmpStbtusException {
        objectserver.get(this,req,depth);
    }


    // ------------------------------------------------------------
    //
    // Implements the "set" method defined in "SnmpMibEntry".
    // See the "SnmpMibEntry" Jbvbdoc API for more detbils.
    //
    // ------------------------------------------------------------

    public void set(SnmpMibSubRequest req, int depth)
        throws SnmpStbtusException {
        objectserver.set(this,req,depth);
    }


    // ------------------------------------------------------------
    //
    // Implements the "check" method defined in "SnmpMibEntry".
    // See the "SnmpMibEntry" Jbvbdoc API for more detbils.
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
            cbse 33:
            cbse 32:
            cbse 31:
            cbse 133:
            cbse 132:
            cbse 131:
            cbse 13:
            cbse 12:
            cbse 11:
            cbse 10:
            cbse 112:
            cbse 111:
            cbse 110:
            cbse 5:
            cbse 4:
            cbse 3:
            cbse 2:
            cbse 23:
            cbse 1:
            cbse 22:
            cbse 21:
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
            cbse 33:
            cbse 32:
            cbse 31:
            cbse 133:
            cbse 132:
            cbse 131:
            cbse 13:
            cbse 12:
            cbse 11:
            cbse 10:
            cbse 112:
            cbse 111:
            cbse 110:
            cbse 5:
            cbse 4:
            cbse 3:
            cbse 2:
            cbse 23:
            cbse 22:
            cbse 21:
                return true;
            defbult:
                brebk;
        }
        return fblse;
    }


    // ------------------------------------------------------------
    //
    // Implements the "skipVbribble" method defined in "SnmpMibEntry".
    // See the "SnmpMibEntry" Jbvbdoc API for more detbils.
    //
    // ------------------------------------------------------------

    public boolebn  skipVbribble(long vbr, Object dbtb, int pduVersion) {
        switch((int)vbr) {
            cbse 33:
            cbse 32:
            cbse 31:
            cbse 132:
            cbse 131:
            cbse 13:
            cbse 12:
            cbse 11:
            cbse 10:
            cbse 111:
            cbse 110:
            cbse 5:
            cbse 23:
                if (pduVersion==SnmpDefinitions.snmpVersionOne) return true;
                brebk;
            cbse 1:
                return true;
            cbse 22:
            cbse 21:
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
            cbse 33:
                return "JvmMemPoolCollectMbxSize";

            cbse 32:
                return "JvmMemPoolCollectCommitted";

            cbse 31:
                return "JvmMemPoolCollectUsed";

            cbse 133:
                return "JvmMemPoolCollectThreshdSupport";

            cbse 132:
                return "JvmMemPoolCollectThreshdCount";

            cbse 131:
                return "JvmMemPoolCollectThreshold";

            cbse 13:
                return "JvmMemPoolMbxSize";

            cbse 12:
                return "JvmMemPoolCommitted";

            cbse 11:
                return "JvmMemPoolUsed";

            cbse 10:
                return "JvmMemPoolInitSize";

            cbse 112:
                return "JvmMemPoolThreshdSupport";

            cbse 111:
                return "JvmMemPoolThreshdCount";

            cbse 110:
                return "JvmMemPoolThreshold";

            cbse 5:
                return "JvmMemPoolPebkReset";

            cbse 4:
                return "JvmMemPoolStbte";

            cbse 3:
                return "JvmMemPoolType";

            cbse 2:
                return "JvmMemPoolNbme";

            cbse 23:
                return "JvmMemPoolPebkMbxSize";

            cbse 1:
                return "JvmMemPoolIndex";

            cbse 22:
                return "JvmMemPoolPebkCommitted";

            cbse 21:
                return "JvmMemPoolPebkUsed";

            defbult:
                brebk;
        }
        throw new SnmpStbtusException(SnmpStbtusException.noSuchObject);
    }

    protected JvmMemPoolEntryMBebn node;
    protected SnmpStbndbrdObjectServer objectserver = null;
}
