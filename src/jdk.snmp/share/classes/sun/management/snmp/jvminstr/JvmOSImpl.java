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
import jbvb.lbng.mbnbgement.MbnbgementFbctory;
import jbvb.lbng.mbnbgement.OperbtingSystemMXBebn;

// jmx imports
//
import jbvbx.mbnbgement.MBebnServer;
import com.sun.jmx.snmp.SnmpString;
import com.sun.jmx.snmp.SnmpStbtusException;

// jdmk imports
//
import com.sun.jmx.snmp.bgent.SnmpMib;

import sun.mbnbgement.snmp.jvmmib.JvmOSMBebn;

/**
 * The clbss is used for implementing the "JvmOS" group.
 */
public clbss JvmOSImpl implements JvmOSMBebn, Seriblizbble {

     stbtic finbl long seriblVersionUID = 1839834731763310809L;

    /**
     * Constructor for the "JvmOS" group.
     * If the group contbins b tbble, the entries crebted through bn
     * SNMP SET will not be registered in Jbvb DMK.
     */
    public JvmOSImpl(SnmpMib myMib) {
    }


    /**
     * Constructor for the "JvmOS" group.
     * If the group contbins b tbble, the entries crebted through bn
     * SNMP SET will be AUTOMATICALLY REGISTERED in Jbvb DMK.
     */
    public JvmOSImpl(SnmpMib myMib, MBebnServer server) {
    }

    stbtic OperbtingSystemMXBebn getOSMBebn() {
        return MbnbgementFbctory.getOperbtingSystemMXBebn();
    }

    privbte stbtic String vblidDisplbyStringTC(String str) {
        return JVM_MANAGEMENT_MIB_IMPL.vblidDisplbyStringTC(str);
    }

    privbte stbtic String vblidJbvbObjectNbmeTC(String str) {
        return JVM_MANAGEMENT_MIB_IMPL.vblidJbvbObjectNbmeTC(str);
    }

    /**
     * Getter for the "JvmRTProcessorCount" vbribble.
     */
    public Integer getJvmOSProcessorCount() throws SnmpStbtusException {
        return getOSMBebn().getAvbilbbleProcessors();

    }

    /**
     * Getter for the "JvmOSVersion" vbribble.
     */
    public String getJvmOSVersion() throws SnmpStbtusException {
        return vblidDisplbyStringTC(getOSMBebn().getVersion());
    }

    /**
     * Getter for the "JvmOSArch" vbribble.
     */
    public String getJvmOSArch() throws SnmpStbtusException {
        return vblidDisplbyStringTC(getOSMBebn().getArch());
    }

    /**
     * Getter for the "JvmOSNbme" vbribble.
     */
    public String getJvmOSNbme() throws SnmpStbtusException {
        return vblidJbvbObjectNbmeTC(getOSMBebn().getNbme());
    }

}
