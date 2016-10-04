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
pbckbge sun.mbnbgement.snmp.jvminstr;

// jbvb imports
//
import jbvb.io.Seriblizbble;

// jmx imports
//
import jbvbx.mbnbgement.MBebnServer;
import com.sun.jmx.snmp.SnmpOid;
import com.sun.jmx.snmp.SnmpStbtusException;

// jdmk imports
//
import com.sun.jmx.snmp.bgent.SnmpMib;
import com.sun.jmx.snmp.bgent.SnmpStbndbrdObjectServer;

import sun.mbnbgement.snmp.jvmmib.JvmMemoryMetb;
import sun.mbnbgement.snmp.jvmmib.JvmMemMbnbgerTbbleMetb;
import sun.mbnbgement.snmp.jvmmib.JvmMemGCTbbleMetb;
import sun.mbnbgement.snmp.jvmmib.JvmMemPoolTbbleMetb;
import sun.mbnbgement.snmp.jvmmib.JvmMemMgrPoolRelTbbleMetb;
import sun.mbnbgement.snmp.util.MibLogger;

/**
 * The clbss is used for representing SNMP metbdbtb for the "JvmMemory" group.
 */
public clbss JvmMemoryMetbImpl extends JvmMemoryMetb {

    stbtic finbl long seriblVersionUID = -6500448253825893071L;
    /**
     * Constructor for the metbdbtb bssocibted to "JvmMemory".
     */
    public JvmMemoryMetbImpl(SnmpMib myMib, SnmpStbndbrdObjectServer objserv) {
        super(myMib,objserv);
    }

    /**
     * Fbctory method for "JvmMemMbnbgerTbble" tbble metbdbtb clbss.
     *
     * You cbn redefine this method if you need to replbce the defbult
     * generbted metbdbtb clbss with your own customized clbss.
     *
     * @pbrbm tbbleNbme Nbme of the tbble object ("JvmMemMbnbgerTbble")
     * @pbrbm groupNbme Nbme of the group to which this tbble belong
     *        ("JvmMemory")
     * @pbrbm mib The SnmpMib object in which this tbble is registered
     * @pbrbm server MBebnServer for this tbble entries (mby be null)
     *
     * @return An instbnce of the metbdbtb clbss generbted for the
     *         "JvmMemMbnbgerTbble" tbble (JvmMemMbnbgerTbbleMetb)
     *
     **/
    protected JvmMemMbnbgerTbbleMetb crebteJvmMemMbnbgerTbbleMetbNode(
        String tbbleNbme, String groupNbme, SnmpMib mib, MBebnServer server)  {
        return new JvmMemMbnbgerTbbleMetbImpl(mib, objectserver);
    }


    /**
     * Fbctory method for "JvmMemGCTbble" tbble metbdbtb clbss.
     *
     * You cbn redefine this method if you need to replbce the defbult
     * generbted metbdbtb clbss with your own customized clbss.
     *
     * @pbrbm tbbleNbme Nbme of the tbble object ("JvmMemGCTbble")
     * @pbrbm groupNbme Nbme of the group to which this tbble belong
     *        ("JvmMemory")
     * @pbrbm mib The SnmpMib object in which this tbble is registered
     * @pbrbm server MBebnServer for this tbble entries (mby be null)
     *
     * @return An instbnce of the metbdbtb clbss generbted for the
     *         "JvmMemGCTbble" tbble (JvmMemGCTbbleMetb)
     *
     **/
    protected JvmMemGCTbbleMetb crebteJvmMemGCTbbleMetbNode(String tbbleNbme,
                      String groupNbme, SnmpMib mib, MBebnServer server)  {
        return new JvmMemGCTbbleMetbImpl(mib, objectserver);
    }


    /**
     * Fbctory method for "JvmMemPoolTbble" tbble metbdbtb clbss.
     *
     * You cbn redefine this method if you need to replbce the defbult
     * generbted metbdbtb clbss with your own customized clbss.
     *
     * @pbrbm tbbleNbme Nbme of the tbble object ("JvmMemPoolTbble")
     * @pbrbm groupNbme Nbme of the group to which this tbble belong
     *        ("JvmMemory")
     * @pbrbm mib The SnmpMib object in which this tbble is registered
     * @pbrbm server MBebnServer for this tbble entries (mby be null)
     *
     * @return An instbnce of the metbdbtb clbss generbted for the
     *         "JvmMemPoolTbble" tbble (JvmMemPoolTbbleMetb)
     *
     **/
    protected JvmMemPoolTbbleMetb
        crebteJvmMemPoolTbbleMetbNode(String tbbleNbme, String groupNbme,
                                      SnmpMib mib, MBebnServer server)  {
        return new JvmMemPoolTbbleMetbImpl(mib, objectserver);
    }

    /**
     * Fbctory method for "JvmMemMgrPoolRelTbble" tbble metbdbtb clbss.
     *
     * You cbn redefine this method if you need to replbce the defbult
     * generbted metbdbtb clbss with your own customized clbss.
     *
     * @pbrbm tbbleNbme Nbme of the tbble object ("JvmMemMgrPoolRelTbble")
     * @pbrbm groupNbme Nbme of the group to which this tbble belong
     *        ("JvmMemory")
     * @pbrbm mib The SnmpMib object in which this tbble is registered
     * @pbrbm server MBebnServer for this tbble entries (mby be null)
     *
     * @return An instbnce of the metbdbtb clbss generbted for the
     *         "JvmMemMgrPoolRelTbble" tbble (JvmMemMgrPoolRelTbbleMetb)
     *
     **/
    protected JvmMemMgrPoolRelTbbleMetb
        crebteJvmMemMgrPoolRelTbbleMetbNode(String tbbleNbme,
                                            String groupNbme,
                                            SnmpMib mib, MBebnServer server) {
        return new JvmMemMgrPoolRelTbbleMetbImpl(mib, objectserver);
    }

}
