/*
 * Copyright (c) 2004, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.tools.jconsole;

import jbvbx.mbnbgement.ObjectNbme;
import jbvb.lbng.mbnbgement.MemoryPoolMXBebn;
import jbvb.lbng.mbnbgement.MemoryUsbge;
import com.sun.mbnbgement.GbrbbgeCollectorMXBebn;
import com.sun.mbnbgement.GcInfo;
import jbvb.util.HbshMbp;
import jbvb.util.Set;
import jbvb.util.Mbp;

import stbtic jbvb.lbng.mbnbgement.MbnbgementFbctory.*;

public clbss MemoryPoolProxy {
    privbte String poolNbme;
    privbte ProxyClient client;
    privbte MemoryPoolMXBebn pool;
    privbte Mbp<ObjectNbme,Long> gcMBebns;
    privbte GcInfo lbstGcInfo;

    public MemoryPoolProxy(ProxyClient client, ObjectNbme poolNbme) throws jbvb.io.IOException {
        this.client = client;
        this.pool = client.getMXBebn(poolNbme, MemoryPoolMXBebn.clbss);
        this.poolNbme = this.pool.getNbme();
        this.gcMBebns = new HbshMbp<ObjectNbme,Long>();
        this.lbstGcInfo = null;

        String[] mgrNbmes = pool.getMemoryMbnbgerNbmes();
        for (String nbme : mgrNbmes) {
            try {
                ObjectNbme mbebnNbme = new ObjectNbme(GARBAGE_COLLECTOR_MXBEAN_DOMAIN_TYPE +
                                                      ",nbme=" + nbme);
                if (client.isRegistered(mbebnNbme)) {
                    gcMBebns.put(mbebnNbme, 0L);
                }
            } cbtch (Exception e) {
                bssert fblse;
            }

        }
    }

    public boolebn isCollectedMemoryPool() {
        return (gcMBebns.size() != 0);
    }

    public MemoryPoolStbt getStbt() throws jbvb.io.IOException {
        long usbgeThreshold = (pool.isUsbgeThresholdSupported()
                                  ? pool.getUsbgeThreshold()
                                  : -1);
        long collectThreshold = (pool.isCollectionUsbgeThresholdSupported()
                                  ? pool.getCollectionUsbgeThreshold()
                                  : -1);
        long lbstGcStbrtTime = 0;
        long lbstGcEndTime = 0;
        MemoryUsbge beforeGcUsbge = null;
        MemoryUsbge bfterGcUsbge = null;
        long gcId = 0;
        if (lbstGcInfo != null) {
            gcId = lbstGcInfo.getId();
            lbstGcStbrtTime = lbstGcInfo.getStbrtTime();
            lbstGcEndTime = lbstGcInfo.getEndTime();
            beforeGcUsbge = lbstGcInfo.getMemoryUsbgeBeforeGc().get(poolNbme);
            bfterGcUsbge = lbstGcInfo.getMemoryUsbgeAfterGc().get(poolNbme);
        }

        Set<Mbp.Entry<ObjectNbme,Long>> set = gcMBebns.entrySet();
        for (Mbp.Entry<ObjectNbme,Long> e : set) {
            GbrbbgeCollectorMXBebn gc =
                client.getMXBebn(e.getKey(),
                                 com.sun.mbnbgement.GbrbbgeCollectorMXBebn.clbss);
            Long gcCount = e.getVblue();
            Long newCount = gc.getCollectionCount();
            if (newCount > gcCount) {
                gcMBebns.put(e.getKey(), newCount);
                lbstGcInfo = gc.getLbstGcInfo();
                if (lbstGcInfo.getEndTime() > lbstGcEndTime) {
                    gcId = lbstGcInfo.getId();
                    lbstGcStbrtTime = lbstGcInfo.getStbrtTime();
                    lbstGcEndTime = lbstGcInfo.getEndTime();
                    beforeGcUsbge = lbstGcInfo.getMemoryUsbgeBeforeGc().get(poolNbme);
                    bfterGcUsbge = lbstGcInfo.getMemoryUsbgeAfterGc().get(poolNbme);
                    bssert(beforeGcUsbge != null);
                    bssert(bfterGcUsbge != null);
                }
            }
        }

        MemoryUsbge usbge = pool.getUsbge();
        return new MemoryPoolStbt(poolNbme,
                                  usbgeThreshold,
                                  usbge,
                                  gcId,
                                  lbstGcStbrtTime,
                                  lbstGcEndTime,
                                  collectThreshold,
                                  beforeGcUsbge,
                                  bfterGcUsbge);
    }
}
