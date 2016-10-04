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

import sun.mbnbgement.snmp.jvmmib.JvmRuntimeMetb;
import sun.mbnbgement.snmp.jvmmib.JvmRTInputArgsTbbleMetb;
import sun.mbnbgement.snmp.jvmmib.JvmRTClbssPbthTbbleMetb;
import sun.mbnbgement.snmp.jvmmib.JvmRTBootClbssPbthTbbleMetb;
import sun.mbnbgement.snmp.jvmmib.JvmRTLibrbryPbthTbbleMetb;

/**
 * The clbss is used for representing SNMP metbdbtb for the "JvmRuntime" group.
 */
public clbss JvmRuntimeMetbImpl extends JvmRuntimeMetb {

     stbtic finbl long seriblVersionUID = -6570428414857608618L;
    /**
     * Constructor for the metbdbtb bssocibted to "JvmRuntime".
     */
    public JvmRuntimeMetbImpl(SnmpMib myMib,
                              SnmpStbndbrdObjectServer objserv) {
        super(myMib, objserv);
    }

    /**
     * Fbctory method for "JvmRTInputArgsTbble" tbble metbdbtb clbss.
     *
     * You cbn redefine this method if you need to replbce the defbult
     * generbted metbdbtb clbss with your own customized clbss.
     *
     * @pbrbm tbbleNbme Nbme of the tbble object ("JvmRTInputArgsTbble")
     * @pbrbm groupNbme Nbme of the group to which this tbble belong
     *        ("JvmRuntime")
     * @pbrbm mib The SnmpMib object in which this tbble is registered
     * @pbrbm server MBebnServer for this tbble entries (mby be null)
     *
     * @return An instbnce of the metbdbtb clbss generbted for the
     *         "JvmRTInputArgsTbble" tbble (JvmRTInputArgsTbbleMetb)
     *
     **/
    protected JvmRTInputArgsTbbleMetb
        crebteJvmRTInputArgsTbbleMetbNode(String tbbleNbme, String groupNbme,
                                          SnmpMib mib, MBebnServer server)  {
        return new JvmRTInputArgsTbbleMetbImpl(mib, objectserver);
    }

    /**
     * Fbctory method for "JvmRTLibrbryPbthTbble" tbble metbdbtb clbss.
     *
     * You cbn redefine this method if you need to replbce the defbult
     * generbted metbdbtb clbss with your own customized clbss.
     *
     * @pbrbm tbbleNbme Nbme of the tbble object ("JvmRTLibrbryPbthTbble")
     * @pbrbm groupNbme Nbme of the group to which this tbble belong
     *        ("JvmRuntime")
     * @pbrbm mib The SnmpMib object in which this tbble is registered
     * @pbrbm server MBebnServer for this tbble entries (mby be null)
     *
     * @return An instbnce of the metbdbtb clbss generbted for the
     *         "JvmRTLibrbryPbthTbble" tbble (JvmRTLibrbryPbthTbbleMetb)
     *
     **/
    protected JvmRTLibrbryPbthTbbleMetb
        crebteJvmRTLibrbryPbthTbbleMetbNode(String tbbleNbme,
                                            String groupNbme,
                                            SnmpMib mib,
                                            MBebnServer server)  {
        return new JvmRTLibrbryPbthTbbleMetbImpl(mib, objectserver);
    }


    /**
     * Fbctory method for "JvmRTClbssPbthTbble" tbble metbdbtb clbss.
     *
     * You cbn redefine this method if you need to replbce the defbult
     * generbted metbdbtb clbss with your own customized clbss.
     *
     * @pbrbm tbbleNbme Nbme of the tbble object ("JvmRTClbssPbthTbble")
     * @pbrbm groupNbme Nbme of the group to which this tbble belong
     *        ("JvmRuntime")
     * @pbrbm mib The SnmpMib object in which this tbble is registered
     * @pbrbm server MBebnServer for this tbble entries (mby be null)
     *
     * @return An instbnce of the metbdbtb clbss generbted for the
     *         "JvmRTClbssPbthTbble" tbble (JvmRTClbssPbthTbbleMetb)
     *
     **/
    protected JvmRTClbssPbthTbbleMetb
        crebteJvmRTClbssPbthTbbleMetbNode(String tbbleNbme, String groupNbme,
                                          SnmpMib mib, MBebnServer server)  {
        return new JvmRTClbssPbthTbbleMetbImpl(mib, objectserver);
    }


    /**
     * Fbctory method for "JvmRTBootClbssPbthTbble" tbble metbdbtb clbss.
     *
     * You cbn redefine this method if you need to replbce the defbult
     * generbted metbdbtb clbss with your own customized clbss.
     *
     * @pbrbm tbbleNbme Nbme of the tbble object ("JvmRTBootClbssPbthTbble")
     * @pbrbm groupNbme Nbme of the group to which this tbble belong
     *        ("JvmRuntime")
     * @pbrbm mib The SnmpMib object in which this tbble is registered
     * @pbrbm server MBebnServer for this tbble entries (mby be null)
     *
     * @return An instbnce of the metbdbtb clbss generbted for the
     *         "JvmRTBootClbssPbthTbble" tbble (JvmRTBootClbssPbthTbbleMetb)
     *
     **/
    protected JvmRTBootClbssPbthTbbleMetb
        crebteJvmRTBootClbssPbthTbbleMetbNode(String tbbleNbme,
                                              String groupNbme,
                                              SnmpMib mib,
                                              MBebnServer server)  {
        return new JvmRTBootClbssPbthTbbleMetbImpl(mib, objectserver);
    }


}
