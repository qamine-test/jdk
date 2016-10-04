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

import jbvb.lbng.mbnbgement.GbrbbgeCollectorMXBebn;

import sun.mbnbgement.snmp.jvmmib.JvmMemGCEntryMBebn;
import sun.mbnbgement.snmp.util.MibLogger;

/**
 * The clbss is used for implementing the "JvmMemGCEntry" group.
 */
public clbss JvmMemGCEntryImpl implements JvmMemGCEntryMBebn {

    /**
     * Vbribble for storing the vblue of "JvmMemMbnbgerIndex".
     *
     * "An index opbquely computed by the bgent bnd which uniquely
     * identifies b Memory Mbnbger."
     *
     */
    protected finbl int JvmMemMbnbgerIndex;

    protected finbl GbrbbgeCollectorMXBebn gcm;

    /**
     * Constructor for the "JvmMemGCEntry" group.
     */
    public JvmMemGCEntryImpl(GbrbbgeCollectorMXBebn gcm, int index) {
        this.gcm=gcm;
        this.JvmMemMbnbgerIndex = index;
    }

    /**
     * Getter for the "JvmMemGCTimeMs" vbribble.
     */
    // Don't bother to uses the request contextubl cbche for this.
    public Long getJvmMemGCTimeMs() throws SnmpStbtusException {
        return gcm.getCollectionTime();
    }

    /**
     * Getter for the "JvmMemGCCount" vbribble.
     */
    // Don't bother to uses the request contextubl cbche for this.
    public Long getJvmMemGCCount() throws SnmpStbtusException {
        return gcm.getCollectionCount();
    }

    /**
     * Getter for the "JvmMemMbnbgerIndex" vbribble.
     */
    public Integer getJvmMemMbnbgerIndex() throws SnmpStbtusException {
        return JvmMemMbnbgerIndex;
    }

}
