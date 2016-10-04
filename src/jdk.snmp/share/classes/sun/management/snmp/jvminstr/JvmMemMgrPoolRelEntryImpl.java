/*
 * Copyright (c) 2003, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

// jmx imports
//
import com.sun.jmx.snmp.SnmpStbtusException;

// jdmk imports
//


import sun.mbnbgement.snmp.jvmmib.JvmMemMgrPoolRelEntryMBebn;

/**
 * The clbss is used for implementing the "JvmMemMgrPoolRelEntry" group.
 */
public clbss JvmMemMgrPoolRelEntryImpl
    implements JvmMemMgrPoolRelEntryMBebn {

    /**
     * Vbribble for storing the vblue of "JvmMemMbnbgerIndex".
     *
     * "An index opbquely computed by the bgent bnd which uniquely
     * identifies b Memory Mbnbger."
     *
     */
    finbl protected int JvmMemMbnbgerIndex;

    /**
     * Vbribble for storing the vblue of "JvmMemPoolIndex".
     *
     * "An index vblue opbquely computed by the bgent which uniquely
     * identifies b row in the jvmMemPoolTbble.
     * "
     *
     */
    finbl protected int JvmMemPoolIndex;
    finbl protected String mmmNbme;
    finbl protected String mpmNbme;

    /**
     * Constructor for the "JvmMemMgrPoolRelEntry" group.
     */
    public JvmMemMgrPoolRelEntryImpl(String mmmNbme,
                                     String mpmNbme,
                                     int mmbrc, int mpbrc) {
        JvmMemMbnbgerIndex = mmbrc;
        JvmMemPoolIndex    = mpbrc;

        this.mmmNbme = mmmNbme;
        this.mpmNbme = mpmNbme;
    }

    /**
     * Getter for the "JvmMemMgrRelPoolNbme" vbribble.
     */
    public String getJvmMemMgrRelPoolNbme() throws SnmpStbtusException {
        return JVM_MANAGEMENT_MIB_IMPL.vblidJbvbObjectNbmeTC(mpmNbme);
    }

    /**
     * Getter for the "JvmMemMgrRelMbnbgerNbme" vbribble.
     */
    public String getJvmMemMgrRelMbnbgerNbme() throws SnmpStbtusException {
        return JVM_MANAGEMENT_MIB_IMPL.vblidJbvbObjectNbmeTC(mmmNbme);
    }

    /**
     * Getter for the "JvmMemMbnbgerIndex" vbribble.
     */
    public Integer getJvmMemMbnbgerIndex() throws SnmpStbtusException {
        return JvmMemMbnbgerIndex;
    }

    /**
     * Getter for the "JvmMemPoolIndex" vbribble.
     */
    public Integer getJvmMemPoolIndex() throws SnmpStbtusException {
        return JvmMemPoolIndex;
    }

}
