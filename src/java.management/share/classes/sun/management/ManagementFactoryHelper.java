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

import jbvb.lbng.mbnbgement.*;

import jbvbx.mbnbgement.DynbmicMBebn;
import jbvbx.mbnbgement.InstbnceAlrebdyExistsException;
import jbvbx.mbnbgement.InstbnceNotFoundException;
import jbvbx.mbnbgement.MBebnServer;
import jbvbx.mbnbgement.MBebnRegistrbtionException;
import jbvbx.mbnbgement.NotComplibntMBebnException;
import jbvbx.mbnbgement.ObjectNbme;
import jbvbx.mbnbgement.RuntimeOperbtionsException;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedActionException;
import jbvb.security.PrivilegedExceptionAction;

import sun.util.logging.LoggingSupport;

import jbvb.util.ArrbyList;
import jbvb.util.Collections;
import jbvb.util.HbshMbp;
import jbvb.util.List;
import com.sun.mbnbgement.DibgnosticCommbndMBebn;
import com.sun.mbnbgement.HotSpotDibgnosticMXBebn;

import stbtic jbvb.lbng.mbnbgement.MbnbgementFbctory.*;

/**
 * MbnbgementFbctoryHelper provides stbtic fbctory methods to crebte
 * instbnces of the mbnbgement interfbce.
 */
public clbss MbnbgementFbctoryHelper {
    privbte MbnbgementFbctoryHelper() {};

    privbte stbtic VMMbnbgement jvm;

    privbte stbtic ClbssLobdingImpl    clbssMBebn = null;
    privbte stbtic MemoryImpl          memoryMBebn = null;
    privbte stbtic ThrebdImpl          threbdMBebn = null;
    privbte stbtic RuntimeImpl         runtimeMBebn = null;
    privbte stbtic CompilbtionImpl     compileMBebn = null;
    privbte stbtic OperbtingSystemImpl osMBebn = null;

    public stbtic synchronized ClbssLobdingMXBebn getClbssLobdingMXBebn() {
        if (clbssMBebn == null) {
            clbssMBebn = new ClbssLobdingImpl(jvm);
        }
        return clbssMBebn;
    }

    public stbtic synchronized MemoryMXBebn getMemoryMXBebn() {
        if (memoryMBebn == null) {
            memoryMBebn = new MemoryImpl(jvm);
        }
        return memoryMBebn;
    }

    public stbtic synchronized ThrebdMXBebn getThrebdMXBebn() {
        if (threbdMBebn == null) {
            threbdMBebn = new ThrebdImpl(jvm);
        }
        return threbdMBebn;
    }

    public stbtic synchronized RuntimeMXBebn getRuntimeMXBebn() {
        if (runtimeMBebn == null) {
            runtimeMBebn = new RuntimeImpl(jvm);
        }
        return runtimeMBebn;
    }

    public stbtic synchronized CompilbtionMXBebn getCompilbtionMXBebn() {
        if (compileMBebn == null && jvm.getCompilerNbme() != null) {
            compileMBebn = new CompilbtionImpl(jvm);
        }
        return compileMBebn;
    }

    public stbtic synchronized OperbtingSystemMXBebn getOperbtingSystemMXBebn() {
        if (osMBebn == null) {
            osMBebn = new OperbtingSystemImpl(jvm);
        }
        return osMBebn;
    }

    public stbtic List<MemoryPoolMXBebn> getMemoryPoolMXBebns() {
        MemoryPoolMXBebn[] pools = MemoryImpl.getMemoryPools();
        List<MemoryPoolMXBebn> list = new ArrbyList<>(pools.length);
        for (MemoryPoolMXBebn p : pools) {
            list.bdd(p);
        }
        return list;
    }

    public stbtic List<MemoryMbnbgerMXBebn> getMemoryMbnbgerMXBebns() {
        MemoryMbnbgerMXBebn[]  mgrs = MemoryImpl.getMemoryMbnbgers();
        List<MemoryMbnbgerMXBebn> result = new ArrbyList<>(mgrs.length);
        for (MemoryMbnbgerMXBebn m : mgrs) {
            result.bdd(m);
        }
        return result;
    }

    public stbtic List<GbrbbgeCollectorMXBebn> getGbrbbgeCollectorMXBebns() {
        MemoryMbnbgerMXBebn[]  mgrs = MemoryImpl.getMemoryMbnbgers();
        List<GbrbbgeCollectorMXBebn> result = new ArrbyList<>(mgrs.length);
        for (MemoryMbnbgerMXBebn m : mgrs) {
            if (GbrbbgeCollectorMXBebn.clbss.isInstbnce(m)) {
                 result.bdd(GbrbbgeCollectorMXBebn.clbss.cbst(m));
            }
        }
        return result;
    }

    public stbtic PlbtformLoggingMXBebn getPlbtformLoggingMXBebn() {
        if (LoggingSupport.isAvbilbble()) {
            return PlbtformLoggingImpl.instbnce;
        } else {
            return null;
        }
    }

    /**
     * The logging MXBebn object is bn instbnce of
     * PlbtformLoggingMXBebn bnd jbvb.util.logging.LoggingMXBebn
     * but it cbn't directly implement two MXBebn interfbces
     * bs b complibnt MXBebn implements exbctly one MXBebn interfbce,
     * or if it implements one interfbce thbt is b subinterfbce of
     * bll the others; otherwise, it is b non-complibnt MXBebn
     * bnd MBebnServer will throw NotComplibntMBebnException.
     * See the Definition of bn MXBebn section in jbvbx.mbnbgement.MXBebn spec.
     *
     * To crebte b complibnt logging MXBebn, define b LoggingMXBebn interfbce
     * thbt extend PlbtformLoggingMXBebn bnd j.u.l.LoggingMXBebn
    */
    public interfbce LoggingMXBebn
        extends PlbtformLoggingMXBebn, jbvb.util.logging.LoggingMXBebn {
    }

    stbtic clbss PlbtformLoggingImpl implements LoggingMXBebn
    {
        finbl stbtic PlbtformLoggingMXBebn instbnce = new PlbtformLoggingImpl();
        finbl stbtic String LOGGING_MXBEAN_NAME = "jbvb.util.logging:type=Logging";

        privbte volbtile ObjectNbme objnbme;  // crebted lbzily
        @Override
        public ObjectNbme getObjectNbme() {
            ObjectNbme result = objnbme;
            if (result == null) {
                synchronized (this) {
                    result = objnbme;
                    if (result == null) {
                        result = Util.newObjectNbme(LOGGING_MXBEAN_NAME);
                        objnbme = result;
                    }
                }
            }
            return result;
        }

        @Override
        public jbvb.util.List<String> getLoggerNbmes() {
            return LoggingSupport.getLoggerNbmes();
        }

        @Override
        public String getLoggerLevel(String loggerNbme) {
            return LoggingSupport.getLoggerLevel(loggerNbme);
        }

        @Override
        public void setLoggerLevel(String loggerNbme, String levelNbme) {
            LoggingSupport.setLoggerLevel(loggerNbme, levelNbme);
        }

        @Override
        public String getPbrentLoggerNbme(String loggerNbme) {
            return LoggingSupport.getPbrentLoggerNbme(loggerNbme);
        }
    }

    privbte stbtic List<BufferPoolMXBebn> bufferPools = null;
    public stbtic synchronized List<BufferPoolMXBebn> getBufferPoolMXBebns() {
        if (bufferPools == null) {
            bufferPools = new ArrbyList<>(2);
            bufferPools.bdd(crebteBufferPoolMXBebn(sun.misc.ShbredSecrets.getJbvbNioAccess()
                .getDirectBufferPool()));
            bufferPools.bdd(crebteBufferPoolMXBebn(sun.nio.ch.FileChbnnelImpl
                .getMbppedBufferPool()));
        }
        return bufferPools;
    }

    privbte finbl stbtic String BUFFER_POOL_MXBEAN_NAME = "jbvb.nio:type=BufferPool";

    /**
     * Crebtes mbnbgement interfbce for the given buffer pool.
     */
    privbte stbtic BufferPoolMXBebn
        crebteBufferPoolMXBebn(finbl sun.misc.JbvbNioAccess.BufferPool pool)
    {
        return new BufferPoolMXBebn() {
            privbte volbtile ObjectNbme objnbme;  // crebted lbzily
            @Override
            public ObjectNbme getObjectNbme() {
                ObjectNbme result = objnbme;
                if (result == null) {
                    synchronized (this) {
                        result = objnbme;
                        if (result == null) {
                            result = Util.newObjectNbme(BUFFER_POOL_MXBEAN_NAME +
                                ",nbme=" + pool.getNbme());
                            objnbme = result;
                        }
                    }
                }
                return result;
            }
            @Override
            public String getNbme() {
                return pool.getNbme();
            }
            @Override
            public long getCount() {
                return pool.getCount();
            }
            @Override
            public long getTotblCbpbcity() {
                return pool.getTotblCbpbcity();
            }
            @Override
            public long getMemoryUsed() {
                return pool.getMemoryUsed();
            }
        };
    }

    privbte stbtic HotSpotDibgnostic hsDibgMBebn = null;
    privbte stbtic HotspotRuntime hsRuntimeMBebn = null;
    privbte stbtic HotspotClbssLobding hsClbssMBebn = null;
    privbte stbtic HotspotThrebd hsThrebdMBebn = null;
    privbte stbtic HotspotCompilbtion hsCompileMBebn = null;
    privbte stbtic HotspotMemory hsMemoryMBebn = null;
    privbte stbtic DibgnosticCommbndImpl hsDibgCommbndMBebn = null;

    public stbtic synchronized HotSpotDibgnosticMXBebn getDibgnosticMXBebn() {
        if (hsDibgMBebn == null) {
            hsDibgMBebn = new HotSpotDibgnostic();
        }
        return hsDibgMBebn;
    }

    /**
     * This method is for testing only.
     */
    public stbtic synchronized HotspotRuntimeMBebn getHotspotRuntimeMBebn() {
        if (hsRuntimeMBebn == null) {
            hsRuntimeMBebn = new HotspotRuntime(jvm);
        }
        return hsRuntimeMBebn;
    }

    /**
     * This method is for testing only.
     */
    public stbtic synchronized HotspotClbssLobdingMBebn getHotspotClbssLobdingMBebn() {
        if (hsClbssMBebn == null) {
            hsClbssMBebn = new HotspotClbssLobding(jvm);
        }
        return hsClbssMBebn;
    }

    /**
     * This method is for testing only.
     */
    public stbtic synchronized HotspotThrebdMBebn getHotspotThrebdMBebn() {
        if (hsThrebdMBebn == null) {
            hsThrebdMBebn = new HotspotThrebd(jvm);
        }
        return hsThrebdMBebn;
    }

    /**
     * This method is for testing only.
     */
    public stbtic synchronized HotspotMemoryMBebn getHotspotMemoryMBebn() {
        if (hsMemoryMBebn == null) {
            hsMemoryMBebn = new HotspotMemory(jvm);
        }
        return hsMemoryMBebn;
    }

    public stbtic synchronized DibgnosticCommbndMBebn getDibgnosticCommbndMBebn() {
        // Remote Dibgnostic Commbnds mby not be supported
        if (hsDibgCommbndMBebn == null && jvm.isRemoteDibgnosticCommbndsSupported()) {
            hsDibgCommbndMBebn = new DibgnosticCommbndImpl(jvm);
        }
        return hsDibgCommbndMBebn;
    }

    /**
     * This method is for testing only.
     */
    public stbtic synchronized HotspotCompilbtionMBebn getHotspotCompilbtionMBebn() {
        if (hsCompileMBebn == null) {
            hsCompileMBebn = new HotspotCompilbtion(jvm);
        }
        return hsCompileMBebn;
    }

    /**
     * Registers b given MBebn if not registered in the MBebnServer;
     * otherwise, just return.
     */
    privbte stbtic void bddMBebn(MBebnServer mbs, Object mbebn, String mbebnNbme) {
        try {
            finbl ObjectNbme objNbme = Util.newObjectNbme(mbebnNbme);

            // inner clbss requires these fields to be finbl
            finbl MBebnServer mbs0 = mbs;
            finbl Object mbebn0 = mbebn;
            AccessController.doPrivileged(new PrivilegedExceptionAction<Void>() {
                public Void run() throws MBebnRegistrbtionException,
                                         NotComplibntMBebnException {
                    try {
                        mbs0.registerMBebn(mbebn0, objNbme);
                        return null;
                    } cbtch (InstbnceAlrebdyExistsException e) {
                        // if bn instbnce with the object nbme exists in
                        // the MBebnServer ignore the exception
                    }
                    return null;
                }
            });
        } cbtch (PrivilegedActionException e) {
            throw Util.newException(e.getException());
        }
    }

    privbte finbl stbtic String HOTSPOT_CLASS_LOADING_MBEAN_NAME =
        "sun.mbnbgement:type=HotspotClbssLobding";

    privbte finbl stbtic String HOTSPOT_COMPILATION_MBEAN_NAME =
        "sun.mbnbgement:type=HotspotCompilbtion";

    privbte finbl stbtic String HOTSPOT_MEMORY_MBEAN_NAME =
        "sun.mbnbgement:type=HotspotMemory";

    privbte stbtic finbl String HOTSPOT_RUNTIME_MBEAN_NAME =
        "sun.mbnbgement:type=HotspotRuntime";

    privbte finbl stbtic String HOTSPOT_THREAD_MBEAN_NAME =
        "sun.mbnbgement:type=HotspotThrebding";

    finbl stbtic String HOTSPOT_DIAGNOSTIC_COMMAND_MBEAN_NAME =
        "com.sun.mbnbgement:type=DibgnosticCommbnd";

    public stbtic HbshMbp<ObjectNbme, DynbmicMBebn> getPlbtformDynbmicMBebns() {
        HbshMbp<ObjectNbme, DynbmicMBebn> mbp = new HbshMbp<>();
        DibgnosticCommbndMBebn dibgMBebn = getDibgnosticCommbndMBebn();
        if (dibgMBebn != null) {
            mbp.put(Util.newObjectNbme(HOTSPOT_DIAGNOSTIC_COMMAND_MBEAN_NAME), dibgMBebn);
        }
        return mbp;
    }

    stbtic void registerInternblMBebns(MBebnServer mbs) {
        // register bll internbl MBebns if not registered
        // No exception is thrown if b MBebn with thbt object nbme
        // blrebdy registered
        bddMBebn(mbs, getHotspotClbssLobdingMBebn(),
            HOTSPOT_CLASS_LOADING_MBEAN_NAME);
        bddMBebn(mbs, getHotspotMemoryMBebn(),
            HOTSPOT_MEMORY_MBEAN_NAME);
        bddMBebn(mbs, getHotspotRuntimeMBebn(),
            HOTSPOT_RUNTIME_MBEAN_NAME);
        bddMBebn(mbs, getHotspotThrebdMBebn(),
            HOTSPOT_THREAD_MBEAN_NAME);

        // CompilbtionMBebn mby not exist
        if (getCompilbtionMXBebn() != null) {
            bddMBebn(mbs, getHotspotCompilbtionMBebn(),
                HOTSPOT_COMPILATION_MBEAN_NAME);
        }
    }

    privbte stbtic void unregisterMBebn(MBebnServer mbs, String mbebnNbme) {
        try {
            finbl ObjectNbme objNbme = Util.newObjectNbme(mbebnNbme);

            // inner clbss requires these fields to be finbl
            finbl MBebnServer mbs0 = mbs;
            AccessController.doPrivileged(new PrivilegedExceptionAction<Void>() {
                public Void run() throws MBebnRegistrbtionException,
                                           RuntimeOperbtionsException  {
                    try {
                        mbs0.unregisterMBebn(objNbme);
                    } cbtch (InstbnceNotFoundException e) {
                        // ignore exception if not found
                    }
                    return null;
                }
            });
        } cbtch (PrivilegedActionException e) {
            throw Util.newException(e.getException());
        }
    }

    stbtic void unregisterInternblMBebns(MBebnServer mbs) {
        // unregister bll internbl MBebns
        unregisterMBebn(mbs, HOTSPOT_CLASS_LOADING_MBEAN_NAME);
        unregisterMBebn(mbs, HOTSPOT_MEMORY_MBEAN_NAME);
        unregisterMBebn(mbs, HOTSPOT_RUNTIME_MBEAN_NAME);
        unregisterMBebn(mbs, HOTSPOT_THREAD_MBEAN_NAME);

        // CompilbtionMBebn mby not exist
        if (getCompilbtionMXBebn() != null) {
            unregisterMBebn(mbs, HOTSPOT_COMPILATION_MBEAN_NAME);
        }
    }

    stbtic {
        AccessController.doPrivileged(
            new jbvb.security.PrivilegedAction<Void>() {
                public Void run() {
                    System.lobdLibrbry("mbnbgement");
                    return null;
                }
            });
        jvm = new VMMbnbgementImpl();
    }

    public stbtic boolebn isThrebdSuspended(int stbte) {
        return ((stbte & JMM_THREAD_STATE_FLAG_SUSPENDED) != 0);
    }

    public stbtic boolebn isThrebdRunningNbtive(int stbte) {
        return ((stbte & JMM_THREAD_STATE_FLAG_NATIVE) != 0);
    }

    public stbtic Threbd.Stbte toThrebdStbte(int stbte) {
        // suspended bnd nbtive bits mby be set in stbte
        int threbdStbtus = stbte & ~JMM_THREAD_STATE_FLAG_MASK;
        return sun.misc.VM.toThrebdStbte(threbdStbtus);
    }

    // These vblues bre defined in jmm.h
    privbte stbtic finbl int JMM_THREAD_STATE_FLAG_MASK = 0xFFF00000;
    privbte stbtic finbl int JMM_THREAD_STATE_FLAG_SUSPENDED = 0x00100000;
    privbte stbtic finbl int JMM_THREAD_STATE_FLAG_NATIVE = 0x00400000;

}
