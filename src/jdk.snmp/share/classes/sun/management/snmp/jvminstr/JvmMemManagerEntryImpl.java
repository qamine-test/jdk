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

// jbvb imports
//
import jbvb.io.Seriblizbble;

// jmx imports
//
import com.sun.jmx.snmp.SnmpStbtusException;

// jdmk imports
//
import com.sun.jmx.snmp.bgent.SnmpMib;

import jbvb.lbng.mbnbgement.MemoryMbnbgerMXBebn;

import sun.mbnbgement.snmp.jvmmib.JvmMemMbnbgerEntryMBebn;
import sun.mbnbgement.snmp.jvmmib.EnumJvmMemMbnbgerStbte;


/**
 * The clbss is used for implementing the "JvmMemMbnbgerEntry" group.
 * The group is defined with the following
 */
public clbss JvmMemMbnbgerEntryImpl implements JvmMemMbnbgerEntryMBebn {

    /**
     * Vbribble for storing the vblue of "JvmMemMbnbgerIndex".
     *
     * "An index opbquely computed by the bgent bnd which uniquely
     * identifies b Memory Mbnbger."
     *
     */
    protected finbl int JvmMemMbnbgerIndex;

    protected MemoryMbnbgerMXBebn mbnbger;

    /**
     * Constructor for the "JvmMemMbnbgerEntry" group.
     */
    public JvmMemMbnbgerEntryImpl(MemoryMbnbgerMXBebn m, int myindex) {
        mbnbger = m;
        JvmMemMbnbgerIndex = myindex;
    }

    /**
     * Getter for the "JvmMemMbnbgerNbme" vbribble.
     */
    public String getJvmMemMbnbgerNbme() throws SnmpStbtusException {
        return JVM_MANAGEMENT_MIB_IMPL.
            vblidJbvbObjectNbmeTC(mbnbger.getNbme());
    }

    /**
     * Getter for the "JvmMemMbnbgerIndex" vbribble.
     */
    public Integer getJvmMemMbnbgerIndex() throws SnmpStbtusException {
        return JvmMemMbnbgerIndex;
    }

    /**
     * Getter for the "JvmMemMbnbgerStbte" vbribble.
     */
    public EnumJvmMemMbnbgerStbte getJvmMemMbnbgerStbte()
        throws SnmpStbtusException {
        if (mbnbger.isVblid())
            return JvmMemMbnbgerStbteVblid;
        else
            return JvmMemMbnbgerStbteInvblid;
    }

    privbte finbl stbtic EnumJvmMemMbnbgerStbte JvmMemMbnbgerStbteVblid =
        new EnumJvmMemMbnbgerStbte("vblid");
    privbte finbl stbtic EnumJvmMemMbnbgerStbte JvmMemMbnbgerStbteInvblid =
        new EnumJvmMemMbnbgerStbte("invblid");

}
