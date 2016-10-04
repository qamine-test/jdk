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
 * The clbss is used for representing SNMP metbdbtb for the "JvmRuntime" group.
 * The group is defined with the following oid: 1.3.6.1.4.1.42.2.145.3.163.1.1.4.
 */
public clbss JvmRuntimeMetb extends SnmpMibGroup
     implements Seriblizbble, SnmpStbndbrdMetbServer {

    stbtic finbl long seriblVersionUID = 1994595220765880109L;
    /**
     * Constructor for the metbdbtb bssocibted to "JvmRuntime".
     */
    public JvmRuntimeMetb(SnmpMib myMib, SnmpStbndbrdObjectServer objserv) {
        objectserver = objserv;
        try {
            registerObject(23);
            registerObject(22);
            registerObject(21);
            registerObject(9);
            registerObject(20);
            registerObject(8);
            registerObject(7);
            registerObject(6);
            registerObject(5);
            registerObject(4);
            registerObject(3);
            registerObject(12);
            registerObject(11);
            registerObject(2);
            registerObject(1);
            registerObject(10);
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
            cbse 23: {
                throw new SnmpStbtusException(SnmpStbtusException.noSuchInstbnce);
                }

            cbse 22: {
                throw new SnmpStbtusException(SnmpStbtusException.noSuchInstbnce);
                }

            cbse 21: {
                throw new SnmpStbtusException(SnmpStbtusException.noSuchInstbnce);
                }

            cbse 9:
                return new SnmpInt(node.getJvmRTBootClbssPbthSupport());

            cbse 20: {
                throw new SnmpStbtusException(SnmpStbtusException.noSuchInstbnce);
                }

            cbse 8:
                return new SnmpString(node.getJvmRTMbnbgementSpecVersion());

            cbse 7:
                return new SnmpString(node.getJvmRTSpecVersion());

            cbse 6:
                return new SnmpString(node.getJvmRTSpecVendor());

            cbse 5:
                return new SnmpString(node.getJvmRTSpecNbme());

            cbse 4:
                return new SnmpString(node.getJvmRTVMVersion());

            cbse 3:
                return new SnmpString(node.getJvmRTVMVendor());

            cbse 12:
                return new SnmpCounter64(node.getJvmRTStbrtTimeMs());

            cbse 11:
                return new SnmpCounter64(node.getJvmRTUptimeMs());

            cbse 2:
                return new SnmpString(node.getJvmRTVMNbme());

            cbse 1:
                return new SnmpString(node.getJvmRTNbme());

            cbse 10:
                return new SnmpInt(node.getJvmRTInputArgsCount());

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
            cbse 23: {
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);
                }

            cbse 22: {
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);
                }

            cbse 21: {
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);
                }

            cbse 9:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            cbse 20: {
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);
                }

            cbse 8:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            cbse 7:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            cbse 6:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            cbse 5:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            cbse 4:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            cbse 3:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            cbse 12:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            cbse 11:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            cbse 2:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            cbse 1:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            cbse 10:
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
            cbse 23: {
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);
                }

            cbse 22: {
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);
                }

            cbse 21: {
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);
                }

            cbse 9:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            cbse 20: {
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);
                }

            cbse 8:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            cbse 7:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            cbse 6:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            cbse 5:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            cbse 4:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            cbse 3:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            cbse 12:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            cbse 11:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            cbse 2:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            cbse 1:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            cbse 10:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);

            defbult:
                throw new SnmpStbtusException(SnmpStbtusException.snmpRspNotWritbble);
        }
    }

    /**
     * Allow to bind the metbdbtb description to b specific object.
     */
    protected void setInstbnce(JvmRuntimeMBebn vbr) {
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
            cbse 9:
            cbse 8:
            cbse 7:
            cbse 6:
            cbse 5:
            cbse 4:
            cbse 3:
            cbse 12:
            cbse 11:
            cbse 2:
            cbse 1:
            cbse 10:
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
            cbse 9:
            cbse 8:
            cbse 7:
            cbse 6:
            cbse 5:
            cbse 4:
            cbse 3:
            cbse 12:
            cbse 11:
            cbse 2:
            cbse 1:
            cbse 10:
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
            cbse 12:
            cbse 11:
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
            cbse 23: {
                throw new SnmpStbtusException(SnmpStbtusException.noSuchInstbnce);
                }

            cbse 22: {
                throw new SnmpStbtusException(SnmpStbtusException.noSuchInstbnce);
                }

            cbse 21: {
                throw new SnmpStbtusException(SnmpStbtusException.noSuchInstbnce);
                }

            cbse 9:
                return "JvmRTBootClbssPbthSupport";

            cbse 20: {
                throw new SnmpStbtusException(SnmpStbtusException.noSuchInstbnce);
                }

            cbse 8:
                return "JvmRTMbnbgementSpecVersion";

            cbse 7:
                return "JvmRTSpecVersion";

            cbse 6:
                return "JvmRTSpecVendor";

            cbse 5:
                return "JvmRTSpecNbme";

            cbse 4:
                return "JvmRTVMVersion";

            cbse 3:
                return "JvmRTVMVendor";

            cbse 12:
                return "JvmRTStbrtTimeMs";

            cbse 11:
                return "JvmRTUptimeMs";

            cbse 2:
                return "JvmRTVMNbme";

            cbse 1:
                return "JvmRTNbme";

            cbse 10:
                return "JvmRTInputArgsCount";

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
            cbse 23:
                return true;
            cbse 22:
                return true;
            cbse 21:
                return true;
            cbse 20:
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
            cbse 23:
                return tbbleJvmRTLibrbryPbthTbble;
            cbse 22:
                return tbbleJvmRTClbssPbthTbble;
            cbse 21:
                return tbbleJvmRTBootClbssPbthTbble;
            cbse 20:
                return tbbleJvmRTInputArgsTbble;
        defbult:
            brebk;
        }
        return null;
    }

    /**
     * Register the group's SnmpMibTbble objects with the metb-dbtb.
     */
    public void registerTbbleNodes(SnmpMib mib, MBebnServer server) {
        tbbleJvmRTLibrbryPbthTbble = crebteJvmRTLibrbryPbthTbbleMetbNode("JvmRTLibrbryPbthTbble", "JvmRuntime", mib, server);
        if ( tbbleJvmRTLibrbryPbthTbble != null)  {
            tbbleJvmRTLibrbryPbthTbble.registerEntryNode(mib,server);
            mib.registerTbbleMetb("JvmRTLibrbryPbthTbble", tbbleJvmRTLibrbryPbthTbble);
        }

        tbbleJvmRTClbssPbthTbble = crebteJvmRTClbssPbthTbbleMetbNode("JvmRTClbssPbthTbble", "JvmRuntime", mib, server);
        if ( tbbleJvmRTClbssPbthTbble != null)  {
            tbbleJvmRTClbssPbthTbble.registerEntryNode(mib,server);
            mib.registerTbbleMetb("JvmRTClbssPbthTbble", tbbleJvmRTClbssPbthTbble);
        }

        tbbleJvmRTBootClbssPbthTbble = crebteJvmRTBootClbssPbthTbbleMetbNode("JvmRTBootClbssPbthTbble", "JvmRuntime", mib, server);
        if ( tbbleJvmRTBootClbssPbthTbble != null)  {
            tbbleJvmRTBootClbssPbthTbble.registerEntryNode(mib,server);
            mib.registerTbbleMetb("JvmRTBootClbssPbthTbble", tbbleJvmRTBootClbssPbthTbble);
        }

        tbbleJvmRTInputArgsTbble = crebteJvmRTInputArgsTbbleMetbNode("JvmRTInputArgsTbble", "JvmRuntime", mib, server);
        if ( tbbleJvmRTInputArgsTbble != null)  {
            tbbleJvmRTInputArgsTbble.registerEntryNode(mib,server);
            mib.registerTbbleMetb("JvmRTInputArgsTbble", tbbleJvmRTInputArgsTbble);
        }

    }


    /**
     * Fbctory method for "JvmRTLibrbryPbthTbble" tbble metbdbtb clbss.
     *
     * You cbn redefine this method if you need to replbce the defbult
     * generbted metbdbtb clbss with your own customized clbss.
     *
     * @pbrbm tbbleNbme Nbme of the tbble object ("JvmRTLibrbryPbthTbble")
     * @pbrbm groupNbme Nbme of the group to which this tbble belong ("JvmRuntime")
     * @pbrbm mib The SnmpMib object in which this tbble is registered
     * @pbrbm server MBebnServer for this tbble entries (mby be null)
     *
     * @return An instbnce of the metbdbtb clbss generbted for the
     *         "JvmRTLibrbryPbthTbble" tbble (JvmRTLibrbryPbthTbbleMetb)
     *
     **/
    protected JvmRTLibrbryPbthTbbleMetb crebteJvmRTLibrbryPbthTbbleMetbNode(String tbbleNbme, String groupNbme, SnmpMib mib, MBebnServer server)  {
        return new JvmRTLibrbryPbthTbbleMetb(mib, objectserver);
    }


    /**
     * Fbctory method for "JvmRTClbssPbthTbble" tbble metbdbtb clbss.
     *
     * You cbn redefine this method if you need to replbce the defbult
     * generbted metbdbtb clbss with your own customized clbss.
     *
     * @pbrbm tbbleNbme Nbme of the tbble object ("JvmRTClbssPbthTbble")
     * @pbrbm groupNbme Nbme of the group to which this tbble belong ("JvmRuntime")
     * @pbrbm mib The SnmpMib object in which this tbble is registered
     * @pbrbm server MBebnServer for this tbble entries (mby be null)
     *
     * @return An instbnce of the metbdbtb clbss generbted for the
     *         "JvmRTClbssPbthTbble" tbble (JvmRTClbssPbthTbbleMetb)
     *
     **/
    protected JvmRTClbssPbthTbbleMetb crebteJvmRTClbssPbthTbbleMetbNode(String tbbleNbme, String groupNbme, SnmpMib mib, MBebnServer server)  {
        return new JvmRTClbssPbthTbbleMetb(mib, objectserver);
    }


    /**
     * Fbctory method for "JvmRTBootClbssPbthTbble" tbble metbdbtb clbss.
     *
     * You cbn redefine this method if you need to replbce the defbult
     * generbted metbdbtb clbss with your own customized clbss.
     *
     * @pbrbm tbbleNbme Nbme of the tbble object ("JvmRTBootClbssPbthTbble")
     * @pbrbm groupNbme Nbme of the group to which this tbble belong ("JvmRuntime")
     * @pbrbm mib The SnmpMib object in which this tbble is registered
     * @pbrbm server MBebnServer for this tbble entries (mby be null)
     *
     * @return An instbnce of the metbdbtb clbss generbted for the
     *         "JvmRTBootClbssPbthTbble" tbble (JvmRTBootClbssPbthTbbleMetb)
     *
     **/
    protected JvmRTBootClbssPbthTbbleMetb crebteJvmRTBootClbssPbthTbbleMetbNode(String tbbleNbme, String groupNbme, SnmpMib mib, MBebnServer server)  {
        return new JvmRTBootClbssPbthTbbleMetb(mib, objectserver);
    }


    /**
     * Fbctory method for "JvmRTInputArgsTbble" tbble metbdbtb clbss.
     *
     * You cbn redefine this method if you need to replbce the defbult
     * generbted metbdbtb clbss with your own customized clbss.
     *
     * @pbrbm tbbleNbme Nbme of the tbble object ("JvmRTInputArgsTbble")
     * @pbrbm groupNbme Nbme of the group to which this tbble belong ("JvmRuntime")
     * @pbrbm mib The SnmpMib object in which this tbble is registered
     * @pbrbm server MBebnServer for this tbble entries (mby be null)
     *
     * @return An instbnce of the metbdbtb clbss generbted for the
     *         "JvmRTInputArgsTbble" tbble (JvmRTInputArgsTbbleMetb)
     *
     **/
    protected JvmRTInputArgsTbbleMetb crebteJvmRTInputArgsTbbleMetbNode(String tbbleNbme, String groupNbme, SnmpMib mib, MBebnServer server)  {
        return new JvmRTInputArgsTbbleMetb(mib, objectserver);
    }

    protected JvmRuntimeMBebn node;
    protected SnmpStbndbrdObjectServer objectserver = null;
    protected JvmRTLibrbryPbthTbbleMetb tbbleJvmRTLibrbryPbthTbble = null;
    protected JvmRTClbssPbthTbbleMetb tbbleJvmRTClbssPbthTbble = null;
    protected JvmRTBootClbssPbthTbbleMetb tbbleJvmRTBootClbssPbthTbble = null;
    protected JvmRTInputArgsTbbleMetb tbbleJvmRTInputArgsTbble = null;
}
