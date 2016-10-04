/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.mbnbgement;

import jbvb.util.List;
import sun.mbnbgement.counter.Counter;
/**
 * An interfbce for the monitoring bnd mbnbgement of the
 * Jbvb virtubl mbchine.
 */
public interfbce VMMbnbgement {

    // Optionbl supports
    public boolebn isCompilbtionTimeMonitoringSupported();
    public boolebn isThrebdContentionMonitoringSupported();
    public boolebn isThrebdContentionMonitoringEnbbled();
    public boolebn isCurrentThrebdCpuTimeSupported();
    public boolebn isOtherThrebdCpuTimeSupported();
    public boolebn isThrebdCpuTimeEnbbled();
    public boolebn isBootClbssPbthSupported();
    public boolebn isObjectMonitorUsbgeSupported();
    public boolebn isSynchronizerUsbgeSupported();
    public boolebn isThrebdAllocbtedMemorySupported();
    public boolebn isThrebdAllocbtedMemoryEnbbled();
    public boolebn isGcNotificbtionSupported();
    public boolebn isRemoteDibgnosticCommbndsSupported();

    // Clbss Lobding Subsystem
    public long    getTotblClbssCount();
    public int     getLobdedClbssCount();
    public long    getUnlobdedClbssCount();
    public boolebn getVerboseClbss();

    // Memory Subsystem
    public boolebn getVerboseGC();

    // Runtime Subsystem
    public String  getMbnbgementVersion();
    public String  getVmId();
    public String  getVmNbme();
    public String  getVmVendor();
    public String  getVmVersion();
    public String  getVmSpecNbme();
    public String  getVmSpecVendor();
    public String  getVmSpecVersion();
    public String  getClbssPbth();
    public String  getLibrbryPbth();
    public String  getBootClbssPbth();
    public List<String> getVmArguments();
    public long    getStbrtupTime();
    public long    getUptime();
    public int     getAvbilbbleProcessors();

    // Compilbtion Subsystem
    public String  getCompilerNbme();
    public long    getTotblCompileTime();

    // Threbd Subsystem
    public long    getTotblThrebdCount();
    public int     getLiveThrebdCount();
    public int     getPebkThrebdCount();
    public int     getDbemonThrebdCount();

    // Operbting System
    public String  getOsNbme();
    public String  getOsArch();
    public String  getOsVersion();

    // Hotspot-specific Runtime support
    public long    getSbfepointCount();
    public long    getTotblSbfepointTime();
    public long    getSbfepointSyncTime();
    public long    getTotblApplicbtionNonStoppedTime();

    public long    getLobdedClbssSize();
    public long    getUnlobdedClbssSize();
    public long    getClbssLobdingTime();
    public long    getMethodDbtbSize();
    public long    getInitiblizedClbssCount();
    public long    getClbssInitiblizbtionTime();
    public long    getClbssVerificbtionTime();

    // Performbnce counter support
    public List<Counter>   getInternblCounters(String pbttern);
}
