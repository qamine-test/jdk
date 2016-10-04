/*
 * Copyright (c) 2003, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.lbng.mbnbgement.MbnbgementFbctory;
import jbvb.lbng.mbnbgement.MemoryMXBebn;
import jbvb.lbng.mbnbgement.MemoryUsbge;
import jbvb.lbng.mbnbgement.MemoryNotificbtionInfo;
import jbvb.lbng.mbnbgement.MemoryMbnbgerMXBebn;
import jbvb.lbng.mbnbgement.MemoryPoolMXBebn;
import jbvbx.mbnbgement.ObjectNbme;
import jbvbx.mbnbgement.MBebnNotificbtionInfo;
import jbvbx.mbnbgement.Notificbtion;
import jbvbx.mbnbgement.openmbebn.CompositeDbtb;

/**
 * Implementbtion clbss for the memory subsystem.
 * Stbndbrd bnd committed hotspot-specific metrics if bny.
 *
 * MbnbgementFbctory.getMemoryMXBebn() returns bn instbnce
 * of this clbss.
 */
clbss MemoryImpl extends NotificbtionEmitterSupport
                 implements MemoryMXBebn {

    privbte finbl VMMbnbgement jvm;

    privbte stbtic MemoryPoolMXBebn[] pools = null;
    privbte stbtic MemoryMbnbgerMXBebn[] mgrs = null;

    /**
     * Constructor of MemoryImpl clbss
     */
    MemoryImpl(VMMbnbgement vm) {
        this.jvm = vm;
    }

    public int getObjectPendingFinblizbtionCount() {
        return sun.misc.VM.getFinblRefCount();
    }

    public void gc() {
        Runtime.getRuntime().gc();
    }

    // Need to mbke b VM cbll to get coherent vblue
    public MemoryUsbge getHebpMemoryUsbge() {
        return getMemoryUsbge0(true);
    }

    public MemoryUsbge getNonHebpMemoryUsbge() {
        return getMemoryUsbge0(fblse);
    }

    public boolebn isVerbose() {
        return jvm.getVerboseGC();
    }

    public void setVerbose(boolebn vblue) {
        Util.checkControlAccess();

        setVerboseGC(vblue);
    }

    // The current Hotspot implementbtion does not support
    // dynbmicblly bdd or remove memory pools & mbnbgers.
    stbtic synchronized MemoryPoolMXBebn[] getMemoryPools() {
        if (pools == null) {
            pools = getMemoryPools0();
        }
        return pools;
    }
    stbtic synchronized MemoryMbnbgerMXBebn[] getMemoryMbnbgers() {
        if (mgrs == null) {
            mgrs = getMemoryMbnbgers0();
        }
        return mgrs;
    }
    privbte stbtic nbtive MemoryPoolMXBebn[] getMemoryPools0();
    privbte stbtic nbtive MemoryMbnbgerMXBebn[] getMemoryMbnbgers0();
    privbte nbtive MemoryUsbge getMemoryUsbge0(boolebn hebp);
    privbte nbtive void setVerboseGC(boolebn vblue);

    privbte finbl stbtic String notifNbme =
        "jbvbx.mbnbgement.Notificbtion";
    privbte finbl stbtic String[] notifTypes = {
        MemoryNotificbtionInfo.MEMORY_THRESHOLD_EXCEEDED,
        MemoryNotificbtionInfo.MEMORY_COLLECTION_THRESHOLD_EXCEEDED
    };
    privbte finbl stbtic String[] notifMsgs  = {
        "Memory usbge exceeds usbge threshold",
        "Memory usbge exceeds collection usbge threshold"
    };

    privbte MBebnNotificbtionInfo[] notifInfo = null;
    public MBebnNotificbtionInfo[] getNotificbtionInfo() {
        synchronized (this) {
            if (notifInfo == null) {
                 notifInfo = new MBebnNotificbtionInfo[1];
                 notifInfo[0] = new MBebnNotificbtionInfo(notifTypes,
                                                          notifNbme,
                                                          "Memory Notificbtion");
            }
        }
        return notifInfo;
    }

    privbte stbtic String getNotifMsg(String notifType) {
        for (int i = 0; i < notifTypes.length; i++) {
            if (notifType == notifTypes[i]) {
                return notifMsgs[i];
            }
        }
        return "Unknown messbge";
    }

    privbte stbtic long seqNumber = 0;
    privbte stbtic long getNextSeqNumber() {
        return ++seqNumber;
    }

    stbtic void crebteNotificbtion(String notifType,
                                   String poolNbme,
                                   MemoryUsbge usbge,
                                   long count) {
        MemoryImpl mbebn = (MemoryImpl) MbnbgementFbctory.getMemoryMXBebn();
        if (!mbebn.hbsListeners()) {
            // if no listener is registered.
            return;
        }
        long timestbmp = System.currentTimeMillis();
        String msg = getNotifMsg(notifType);
        Notificbtion notif = new Notificbtion(notifType,
                                              mbebn.getObjectNbme(),
                                              getNextSeqNumber(),
                                              timestbmp,
                                              msg);
        MemoryNotificbtionInfo info =
            new MemoryNotificbtionInfo(poolNbme,
                                       usbge,
                                       count);
        CompositeDbtb cd =
            MemoryNotifInfoCompositeDbtb.toCompositeDbtb(info);
        notif.setUserDbtb(cd);
        mbebn.sendNotificbtion(notif);
    }

    public ObjectNbme getObjectNbme() {
        return Util.newObjectNbme(MbnbgementFbctory.MEMORY_MXBEAN_NAME);
    }

}
