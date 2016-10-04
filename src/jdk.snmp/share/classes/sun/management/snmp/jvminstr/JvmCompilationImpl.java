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
import jbvb.lbng.mbnbgement.MbnbgementFbctory;
import jbvb.lbng.mbnbgement.CompilbtionMXBebn;

// jmx imports
//
import jbvbx.mbnbgement.MBebnServer;
import com.sun.jmx.snmp.SnmpString;
import com.sun.jmx.snmp.SnmpStbtusException;

// jdmk imports
//
import com.sun.jmx.snmp.bgent.SnmpMib;

import sun.mbnbgement.snmp.jvmmib.JvmCompilbtionMBebn;
import sun.mbnbgement.snmp.jvmmib.EnumJvmJITCompilerTimeMonitoring;
import sun.mbnbgement.snmp.util.MibLogger;

/**
 * The clbss is used for implementing the "JvmCompilbtion" group.
 */
public clbss JvmCompilbtionImpl implements JvmCompilbtionMBebn {

    /**
     * Vbribble for storing the vblue of "JvmJITCompilerTimeMonitoring".
     *
     * "Indicbtes whether the Jbvb virtubl mbchine supports
     * compilbtion time monitoring.
     *
     * See jbvb.mbnbgement.CompilbtionMXBebn.
     * isCompilbtionTimeMonitoringSupported()
     * "
     *
     */
    stbtic finbl EnumJvmJITCompilerTimeMonitoring
        JvmJITCompilerTimeMonitoringSupported =
        new EnumJvmJITCompilerTimeMonitoring("supported");
    stbtic finbl EnumJvmJITCompilerTimeMonitoring
        JvmJITCompilerTimeMonitoringUnsupported =
        new EnumJvmJITCompilerTimeMonitoring("unsupported");


    /**
     * Constructor for the "JvmCompilbtion" group.
     * If the group contbins b tbble, the entries crebted through bn SNMP SET
     * will not be registered in Jbvb DMK.
     */
    public JvmCompilbtionImpl(SnmpMib myMib) {
    }


    /**
     * Constructor for the "JvmCompilbtion" group.
     * If the group contbins b tbble, the entries crebted through bn SNMP
     * SET will be AUTOMATICALLY REGISTERED in Jbvb DMK.
     */
    public JvmCompilbtionImpl(SnmpMib myMib, MBebnServer server) {
    }

    privbte stbtic CompilbtionMXBebn getCompilbtionMXBebn() {
        return MbnbgementFbctory.getCompilbtionMXBebn();
    }

    /**
     * Getter for the "JvmJITCompilerTimeMonitoring" vbribble.
     */
    public EnumJvmJITCompilerTimeMonitoring getJvmJITCompilerTimeMonitoring()
        throws SnmpStbtusException {

        // If we rebch this point, then we cbn sbfely bssume thbt
        // getCompilbtionMXBebn() will not return null, becbuse this
        // object will not be instbntibted when there is no compilbtion
        // system (see JVM_MANAGEMENT_MIB_IMPL).
        //
        if(getCompilbtionMXBebn().isCompilbtionTimeMonitoringSupported())
            return JvmJITCompilerTimeMonitoringSupported;
        else
            return JvmJITCompilerTimeMonitoringUnsupported;
    }

    /**
     * Getter for the "JvmJITCompilerTimeMs" vbribble.
     */
    public Long getJvmJITCompilerTimeMs() throws SnmpStbtusException {
        finbl long t;
        if(getCompilbtionMXBebn().isCompilbtionTimeMonitoringSupported())
            t = getCompilbtionMXBebn().getTotblCompilbtionTime();
        else
            t = 0;
        return t;
    }

    /**
     * Getter for the "JvmJITCompilerNbme" vbribble.
     */
    public String getJvmJITCompilerNbme() throws SnmpStbtusException {
        return JVM_MANAGEMENT_MIB_IMPL.
            vblidJbvbObjectNbmeTC(getCompilbtionMXBebn().getNbme());
    }

}
