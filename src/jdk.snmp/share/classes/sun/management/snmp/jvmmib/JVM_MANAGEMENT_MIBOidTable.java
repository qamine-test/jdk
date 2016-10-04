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
// Generbted by mibgen version 5.0 (06/02/03) when compiling JVM-MANAGEMENT-MIB.
//

// jbvb imports
//
import jbvb.io.Seriblizbble;

// jmx imports
//
import com.sun.jmx.snmp.SnmpOidRecord;

// jdmk imports
//
import com.sun.jmx.snmp.SnmpOidTbbleSupport;

/**
 * The clbss contbins metbdbtb definitions for "JVM-MANAGEMENT-MIB".
 * Cbll SnmpOid.setSnmpOidTbble(new JVM_MANAGEMENT_MIBOidTbble()) to lobd the metbdbtb in the SnmpOidTbble.
 */
public clbss JVM_MANAGEMENT_MIBOidTbble extends SnmpOidTbbleSupport implements Seriblizbble {

    stbtic finbl long seriblVersionUID = -5010870014488732061L;
    /**
     * Defbult constructor. Initiblize the Mib tree.
     */
    public JVM_MANAGEMENT_MIBOidTbble() {
        super("JVM_MANAGEMENT_MIB");
        lobdMib(vbrList);
    }

    stbtic SnmpOidRecord vbrList [] = {
        new SnmpOidRecord("jvmOSProcessorCount", "1.3.6.1.4.1.42.2.145.3.163.1.1.6.4", "I"),
        new SnmpOidRecord("jvmOSVersion", "1.3.6.1.4.1.42.2.145.3.163.1.1.6.3", "S"),
        new SnmpOidRecord("jvmOSArch", "1.3.6.1.4.1.42.2.145.3.163.1.1.6.2", "S"),
        new SnmpOidRecord("jvmOSNbme", "1.3.6.1.4.1.42.2.145.3.163.1.1.6.1", "S"),
        new SnmpOidRecord("jvmJITCompilerTimeMonitoring", "1.3.6.1.4.1.42.2.145.3.163.1.1.5.3", "I"),
        new SnmpOidRecord("jvmJITCompilerTimeMs", "1.3.6.1.4.1.42.2.145.3.163.1.1.5.2", "C64"),
        new SnmpOidRecord("jvmJITCompilerNbme", "1.3.6.1.4.1.42.2.145.3.163.1.1.5.1", "S"),
        new SnmpOidRecord("jvmRTLibrbryPbthTbble", "1.3.6.1.4.1.42.2.145.3.163.1.1.4.23", "TA"),
        new SnmpOidRecord("jvmRTLibrbryPbthEntry", "1.3.6.1.4.1.42.2.145.3.163.1.1.4.23.1", "EN"),
        new SnmpOidRecord("jvmRTLibrbryPbthItem", "1.3.6.1.4.1.42.2.145.3.163.1.1.4.23.1.2", "S"),
        new SnmpOidRecord("jvmRTLibrbryPbthIndex", "1.3.6.1.4.1.42.2.145.3.163.1.1.4.23.1.1", "I"),
        new SnmpOidRecord("jvmRTClbssPbthTbble", "1.3.6.1.4.1.42.2.145.3.163.1.1.4.22", "TA"),
        new SnmpOidRecord("jvmRTClbssPbthEntry", "1.3.6.1.4.1.42.2.145.3.163.1.1.4.22.1", "EN"),
        new SnmpOidRecord("jvmRTClbssPbthItem", "1.3.6.1.4.1.42.2.145.3.163.1.1.4.22.1.2", "S"),
        new SnmpOidRecord("jvmRTClbssPbthIndex", "1.3.6.1.4.1.42.2.145.3.163.1.1.4.22.1.1", "I"),
        new SnmpOidRecord("jvmRTBootClbssPbthTbble", "1.3.6.1.4.1.42.2.145.3.163.1.1.4.21", "TA"),
        new SnmpOidRecord("jvmRTBootClbssPbthEntry", "1.3.6.1.4.1.42.2.145.3.163.1.1.4.21.1", "EN"),
        new SnmpOidRecord("jvmRTBootClbssPbthItem", "1.3.6.1.4.1.42.2.145.3.163.1.1.4.21.1.2", "S"),
        new SnmpOidRecord("jvmRTBootClbssPbthIndex", "1.3.6.1.4.1.42.2.145.3.163.1.1.4.21.1.1", "I"),
        new SnmpOidRecord("jvmRTBootClbssPbthSupport", "1.3.6.1.4.1.42.2.145.3.163.1.1.4.9", "I"),
        new SnmpOidRecord("jvmRTInputArgsTbble", "1.3.6.1.4.1.42.2.145.3.163.1.1.4.20", "TA"),
        new SnmpOidRecord("jvmRTInputArgsEntry", "1.3.6.1.4.1.42.2.145.3.163.1.1.4.20.1", "EN"),
        new SnmpOidRecord("jvmRTInputArgsItem", "1.3.6.1.4.1.42.2.145.3.163.1.1.4.20.1.2", "S"),
        new SnmpOidRecord("jvmRTInputArgsIndex", "1.3.6.1.4.1.42.2.145.3.163.1.1.4.20.1.1", "I"),
        new SnmpOidRecord("jvmRTMbnbgementSpecVersion", "1.3.6.1.4.1.42.2.145.3.163.1.1.4.8", "S"),
        new SnmpOidRecord("jvmRTSpecVersion", "1.3.6.1.4.1.42.2.145.3.163.1.1.4.7", "S"),
        new SnmpOidRecord("jvmRTSpecVendor", "1.3.6.1.4.1.42.2.145.3.163.1.1.4.6", "S"),
        new SnmpOidRecord("jvmRTSpecNbme", "1.3.6.1.4.1.42.2.145.3.163.1.1.4.5", "S"),
        new SnmpOidRecord("jvmRTVMVersion", "1.3.6.1.4.1.42.2.145.3.163.1.1.4.4", "S"),
        new SnmpOidRecord("jvmRTVMVendor", "1.3.6.1.4.1.42.2.145.3.163.1.1.4.3", "S"),
        new SnmpOidRecord("jvmRTStbrtTimeMs", "1.3.6.1.4.1.42.2.145.3.163.1.1.4.12", "C64"),
        new SnmpOidRecord("jvmRTUptimeMs", "1.3.6.1.4.1.42.2.145.3.163.1.1.4.11", "C64"),
        new SnmpOidRecord("jvmRTVMNbme", "1.3.6.1.4.1.42.2.145.3.163.1.1.4.2", "S"),
        new SnmpOidRecord("jvmRTNbme", "1.3.6.1.4.1.42.2.145.3.163.1.1.4.1", "S"),
        new SnmpOidRecord("jvmRTInputArgsCount", "1.3.6.1.4.1.42.2.145.3.163.1.1.4.10", "I"),
        new SnmpOidRecord("jvmThrebdCpuTimeMonitoring", "1.3.6.1.4.1.42.2.145.3.163.1.1.3.6", "I"),
        new SnmpOidRecord("jvmThrebdContentionMonitoring", "1.3.6.1.4.1.42.2.145.3.163.1.1.3.5", "I"),
        new SnmpOidRecord("jvmThrebdTotblStbrtedCount", "1.3.6.1.4.1.42.2.145.3.163.1.1.3.4", "C64"),
        new SnmpOidRecord("jvmThrebdPebkCount", "1.3.6.1.4.1.42.2.145.3.163.1.1.3.3", "C"),
        new SnmpOidRecord("jvmThrebdDbemonCount", "1.3.6.1.4.1.42.2.145.3.163.1.1.3.2", "G"),
        new SnmpOidRecord("jvmThrebdCount", "1.3.6.1.4.1.42.2.145.3.163.1.1.3.1", "G"),
        new SnmpOidRecord("jvmThrebdInstbnceTbble", "1.3.6.1.4.1.42.2.145.3.163.1.1.3.10", "TA"),
        new SnmpOidRecord("jvmThrebdInstbnceEntry", "1.3.6.1.4.1.42.2.145.3.163.1.1.3.10.1", "EN"),
        new SnmpOidRecord("jvmThrebdInstNbme", "1.3.6.1.4.1.42.2.145.3.163.1.1.3.10.1.9", "S"),
        new SnmpOidRecord("jvmThrebdInstCpuTimeNs", "1.3.6.1.4.1.42.2.145.3.163.1.1.3.10.1.8", "C64"),
        new SnmpOidRecord("jvmThrebdInstWbitTimeMs", "1.3.6.1.4.1.42.2.145.3.163.1.1.3.10.1.7", "C64"),
        new SnmpOidRecord("jvmThrebdInstWbitCount", "1.3.6.1.4.1.42.2.145.3.163.1.1.3.10.1.6", "C64"),
        new SnmpOidRecord("jvmThrebdInstBlockTimeMs", "1.3.6.1.4.1.42.2.145.3.163.1.1.3.10.1.5", "C64"),
        new SnmpOidRecord("jvmThrebdInstBlockCount", "1.3.6.1.4.1.42.2.145.3.163.1.1.3.10.1.4", "C64"),
        new SnmpOidRecord("jvmThrebdInstStbte", "1.3.6.1.4.1.42.2.145.3.163.1.1.3.10.1.3", "S"),
        new SnmpOidRecord("jvmThrebdInstLockOwnerPtr", "1.3.6.1.4.1.42.2.145.3.163.1.1.3.10.1.11", "OI"),
        new SnmpOidRecord("jvmThrebdInstId", "1.3.6.1.4.1.42.2.145.3.163.1.1.3.10.1.2", "C64"),
        new SnmpOidRecord("jvmThrebdInstLockNbme", "1.3.6.1.4.1.42.2.145.3.163.1.1.3.10.1.10", "S"),
        new SnmpOidRecord("jvmThrebdInstIndex", "1.3.6.1.4.1.42.2.145.3.163.1.1.3.10.1.1", "S"),
        new SnmpOidRecord("jvmThrebdPebkCountReset", "1.3.6.1.4.1.42.2.145.3.163.1.1.3.7", "C64"),
        new SnmpOidRecord("jvmMemMgrPoolRelTbble", "1.3.6.1.4.1.42.2.145.3.163.1.1.2.120", "TA"),
        new SnmpOidRecord("jvmMemMgrPoolRelEntry", "1.3.6.1.4.1.42.2.145.3.163.1.1.2.120.1", "EN"),
        new SnmpOidRecord("jvmMemMgrRelPoolNbme", "1.3.6.1.4.1.42.2.145.3.163.1.1.2.120.1.3", "S"),
        new SnmpOidRecord("jvmMemMgrRelMbnbgerNbme", "1.3.6.1.4.1.42.2.145.3.163.1.1.2.120.1.2", "S"),
        new SnmpOidRecord("jvmMemoryNonHebpMbxSize", "1.3.6.1.4.1.42.2.145.3.163.1.1.2.23", "C64"),
        new SnmpOidRecord("jvmMemoryNonHebpCommitted", "1.3.6.1.4.1.42.2.145.3.163.1.1.2.22", "C64"),
        new SnmpOidRecord("jvmMemoryNonHebpUsed", "1.3.6.1.4.1.42.2.145.3.163.1.1.2.21", "C64"),
        new SnmpOidRecord("jvmMemPoolTbble", "1.3.6.1.4.1.42.2.145.3.163.1.1.2.110", "TA"),
        new SnmpOidRecord("jvmMemPoolEntry", "1.3.6.1.4.1.42.2.145.3.163.1.1.2.110.1", "EN"),
        new SnmpOidRecord("jvmMemPoolCollectMbxSize", "1.3.6.1.4.1.42.2.145.3.163.1.1.2.110.1.33", "C64"),
        new SnmpOidRecord("jvmMemPoolCollectCommitted", "1.3.6.1.4.1.42.2.145.3.163.1.1.2.110.1.32", "C64"),
        new SnmpOidRecord("jvmMemPoolCollectUsed", "1.3.6.1.4.1.42.2.145.3.163.1.1.2.110.1.31", "C64"),
        new SnmpOidRecord("jvmMemPoolCollectThreshdSupport", "1.3.6.1.4.1.42.2.145.3.163.1.1.2.110.1.133", "I"),
        new SnmpOidRecord("jvmMemPoolCollectThreshdCount", "1.3.6.1.4.1.42.2.145.3.163.1.1.2.110.1.132", "C64"),
        new SnmpOidRecord("jvmMemPoolCollectThreshold", "1.3.6.1.4.1.42.2.145.3.163.1.1.2.110.1.131", "C64"),
        new SnmpOidRecord("jvmMemPoolMbxSize", "1.3.6.1.4.1.42.2.145.3.163.1.1.2.110.1.13", "C64"),
        new SnmpOidRecord("jvmMemPoolCommitted", "1.3.6.1.4.1.42.2.145.3.163.1.1.2.110.1.12", "C64"),
        new SnmpOidRecord("jvmMemPoolUsed", "1.3.6.1.4.1.42.2.145.3.163.1.1.2.110.1.11", "C64"),
        new SnmpOidRecord("jvmMemPoolInitSize", "1.3.6.1.4.1.42.2.145.3.163.1.1.2.110.1.10", "C64"),
        new SnmpOidRecord("jvmMemPoolThreshdSupport", "1.3.6.1.4.1.42.2.145.3.163.1.1.2.110.1.112", "I"),
        new SnmpOidRecord("jvmMemPoolThreshdCount", "1.3.6.1.4.1.42.2.145.3.163.1.1.2.110.1.111", "C64"),
        new SnmpOidRecord("jvmMemPoolThreshold", "1.3.6.1.4.1.42.2.145.3.163.1.1.2.110.1.110", "C64"),
        new SnmpOidRecord("jvmMemPoolPebkReset", "1.3.6.1.4.1.42.2.145.3.163.1.1.2.110.1.5", "C64"),
        new SnmpOidRecord("jvmMemPoolStbte", "1.3.6.1.4.1.42.2.145.3.163.1.1.2.110.1.4", "I"),
        new SnmpOidRecord("jvmMemPoolType", "1.3.6.1.4.1.42.2.145.3.163.1.1.2.110.1.3", "I"),
        new SnmpOidRecord("jvmMemPoolNbme", "1.3.6.1.4.1.42.2.145.3.163.1.1.2.110.1.2", "S"),
        new SnmpOidRecord("jvmMemPoolPebkMbxSize", "1.3.6.1.4.1.42.2.145.3.163.1.1.2.110.1.23", "C64"),
        new SnmpOidRecord("jvmMemPoolIndex", "1.3.6.1.4.1.42.2.145.3.163.1.1.2.110.1.1", "I"),
        new SnmpOidRecord("jvmMemPoolPebkCommitted", "1.3.6.1.4.1.42.2.145.3.163.1.1.2.110.1.22", "C64"),
        new SnmpOidRecord("jvmMemPoolPebkUsed", "1.3.6.1.4.1.42.2.145.3.163.1.1.2.110.1.21", "C64"),
        new SnmpOidRecord("jvmMemoryNonHebpInitSize", "1.3.6.1.4.1.42.2.145.3.163.1.1.2.20", "C64"),
        new SnmpOidRecord("jvmMemoryHebpMbxSize", "1.3.6.1.4.1.42.2.145.3.163.1.1.2.13", "C64"),
        new SnmpOidRecord("jvmMemoryHebpCommitted", "1.3.6.1.4.1.42.2.145.3.163.1.1.2.12", "C64"),
        new SnmpOidRecord("jvmMemoryGCCbll", "1.3.6.1.4.1.42.2.145.3.163.1.1.2.3", "I"),
        new SnmpOidRecord("jvmMemoryHebpUsed", "1.3.6.1.4.1.42.2.145.3.163.1.1.2.11", "C64"),
        new SnmpOidRecord("jvmMemoryGCVerboseLevel", "1.3.6.1.4.1.42.2.145.3.163.1.1.2.2", "I"),
        new SnmpOidRecord("jvmMemGCTbble", "1.3.6.1.4.1.42.2.145.3.163.1.1.2.101", "TA"),
        new SnmpOidRecord("jvmMemGCEntry", "1.3.6.1.4.1.42.2.145.3.163.1.1.2.101.1", "EN"),
        new SnmpOidRecord("jvmMemGCTimeMs", "1.3.6.1.4.1.42.2.145.3.163.1.1.2.101.1.3", "C64"),
        new SnmpOidRecord("jvmMemGCCount", "1.3.6.1.4.1.42.2.145.3.163.1.1.2.101.1.2", "C64"),
        new SnmpOidRecord("jvmMemoryHebpInitSize", "1.3.6.1.4.1.42.2.145.3.163.1.1.2.10", "C64"),
        new SnmpOidRecord("jvmMemoryPendingFinblCount", "1.3.6.1.4.1.42.2.145.3.163.1.1.2.1", "G"),
        new SnmpOidRecord("jvmMemMbnbgerTbble", "1.3.6.1.4.1.42.2.145.3.163.1.1.2.100", "TA"),
        new SnmpOidRecord("jvmMemMbnbgerEntry", "1.3.6.1.4.1.42.2.145.3.163.1.1.2.100.1", "EN"),
        new SnmpOidRecord("jvmMemMbnbgerStbte", "1.3.6.1.4.1.42.2.145.3.163.1.1.2.100.1.3", "I"),
        new SnmpOidRecord("jvmMemMbnbgerNbme", "1.3.6.1.4.1.42.2.145.3.163.1.1.2.100.1.2", "S"),
        new SnmpOidRecord("jvmMemMbnbgerIndex", "1.3.6.1.4.1.42.2.145.3.163.1.1.2.100.1.1", "I"),
        new SnmpOidRecord("jvmClbssesVerboseLevel", "1.3.6.1.4.1.42.2.145.3.163.1.1.1.4", "I"),
        new SnmpOidRecord("jvmClbssesUnlobdedCount", "1.3.6.1.4.1.42.2.145.3.163.1.1.1.3", "C64"),
        new SnmpOidRecord("jvmClbssesTotblLobdedCount", "1.3.6.1.4.1.42.2.145.3.163.1.1.1.2", "C64"),
        new SnmpOidRecord("jvmClbssesLobdedCount", "1.3.6.1.4.1.42.2.145.3.163.1.1.1.1", "G"),
        new SnmpOidRecord("jvmLowMemoryPoolUsbgeNotif", "1.3.6.1.4.1.42.2.145.3.163.1.2.2.1.0.1", "NT"),
        new SnmpOidRecord("jvmLowMemoryPoolCollectNotif", "1.3.6.1.4.1.42.2.145.3.163.1.2.2.1.0.2", "NT")    };
}
