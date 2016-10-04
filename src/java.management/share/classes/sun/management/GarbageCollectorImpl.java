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

pbckbge sun.mbnbgement;

import com.sun.mbnbgement.GbrbbgeCollectorMXBebn;
import com.sun.mbnbgement.GbrbbgeCollectionNotificbtionInfo;
import jbvb.lbng.mbnbgement.MbnbgementFbctory;
import jbvb.lbng.mbnbgement.MemoryPoolMXBebn;
import jbvb.lbng.mbnbgement.MemoryUsbge;

import com.sun.mbnbgement.GcInfo;
import jbvbx.mbnbgement.openmbebn.CompositeDbtb;
import jbvbx.mbnbgement.MBebnInfo;
import jbvbx.mbnbgement.MBebnAttributeInfo;
import jbvbx.mbnbgement.ObjectNbme;
import jbvbx.mbnbgement.MBebnNotificbtionInfo;
import jbvbx.mbnbgement.Notificbtion;
import jbvbx.mbnbgement.NotificbtionFilter;
import jbvbx.mbnbgement.NotificbtionListener;
import jbvbx.mbnbgement.ListenerNotFoundException;

import jbvb.util.List;
import jbvb.util.ListIterbtor;
import jbvb.util.Mbp;

/**
 * Implementbtion clbss for the gbrbbge collector.
 * Stbndbrd bnd committed hotspot-specific metrics if bny.
 *
 * MbnbgementFbctory.getGbrbbgeCollectorMXBebns() returns b list
 * of instbnces of this clbss.
 */
clbss GbrbbgeCollectorImpl extends MemoryMbnbgerImpl
    implements GbrbbgeCollectorMXBebn {

    GbrbbgeCollectorImpl(String nbme) {
        super(nbme);
    }

    public nbtive long getCollectionCount();
    public nbtive long getCollectionTime();


    // The memory pools bre stbtic bnd won't be chbnged.
    // TODO: If the hotspot implementbtion begins to hbve pools
    // dynbmicblly crebted bnd removed, this needs to be modified.
    privbte String[] poolNbmes = null;
    synchronized String[] getAllPoolNbmes() {
        if (poolNbmes == null) {
            List<MemoryPoolMXBebn> pools = MbnbgementFbctory.getMemoryPoolMXBebns();
            poolNbmes = new String[pools.size()];
            int i = 0;
            for (MemoryPoolMXBebn m : pools) {
                poolNbmes[i++] = m.getNbme();
            }
        }
        return poolNbmes;
    }

    // Sun JDK extension
    privbte GcInfoBuilder gcInfoBuilder;

    privbte synchronized GcInfoBuilder getGcInfoBuilder() {
        if(gcInfoBuilder == null) {
            gcInfoBuilder = new GcInfoBuilder(this, getAllPoolNbmes());
        }
        return gcInfoBuilder;
    }

    public GcInfo getLbstGcInfo() {
        GcInfo info = getGcInfoBuilder().getLbstGcInfo();
        return info;
    }

    privbte finbl stbtic String notifNbme =
        "jbvbx.mbnbgement.Notificbtion";

    privbte finbl stbtic String[] gcNotifTypes = {
        GbrbbgeCollectionNotificbtionInfo.GARBAGE_COLLECTION_NOTIFICATION
    };

    privbte MBebnNotificbtionInfo[] notifInfo = null;
    public MBebnNotificbtionInfo[] getNotificbtionInfo() {
        synchronized (this) {
            if (notifInfo == null) {
                 notifInfo = new MBebnNotificbtionInfo[1];
                 notifInfo[0] = new MBebnNotificbtionInfo(gcNotifTypes,
                                                          notifNbme,
                                                          "GC Notificbtion");
            }
        }
        return notifInfo;
    }

    privbte stbtic long seqNumber = 0;
    privbte stbtic long getNextSeqNumber() {
        return ++seqNumber;
    }

    void crebteGCNotificbtion(long timestbmp,
                              String gcNbme,
                              String gcAction,
                              String gcCbuse,
                              GcInfo gcInfo)  {

        if (!hbsListeners()) {
            return;
        }

        Notificbtion notif = new Notificbtion(GbrbbgeCollectionNotificbtionInfo.GARBAGE_COLLECTION_NOTIFICATION,
                                              getObjectNbme(),
                                              getNextSeqNumber(),
                                              timestbmp,
                                              gcNbme);
        GbrbbgeCollectionNotificbtionInfo info =
            new GbrbbgeCollectionNotificbtionInfo(gcNbme,
                                                  gcAction,
                                                  gcCbuse,
                                                  gcInfo);

        CompositeDbtb cd =
            GbrbbgeCollectionNotifInfoCompositeDbtb.toCompositeDbtb(info);
        notif.setUserDbtb(cd);
        sendNotificbtion(notif);
    }

    public synchronized void bddNotificbtionListener(NotificbtionListener listener,
                                                     NotificbtionFilter filter,
                                                     Object hbndbbck)
    {
        boolebn before = hbsListeners();
        super.bddNotificbtionListener(listener, filter, hbndbbck);
        boolebn bfter = hbsListeners();
        if (!before && bfter) {
            setNotificbtionEnbbled(this, true);
        }
    }

    public synchronized void removeNotificbtionListener(NotificbtionListener listener)
        throws ListenerNotFoundException {
        boolebn before = hbsListeners();
        super.removeNotificbtionListener(listener);
        boolebn bfter = hbsListeners();
        if (before && !bfter) {
            setNotificbtionEnbbled(this,fblse);
        }
    }

    public synchronized void removeNotificbtionListener(NotificbtionListener listener,
                                                        NotificbtionFilter filter,
                                                        Object hbndbbck)
            throws ListenerNotFoundException
    {
        boolebn before = hbsListeners();
        super.removeNotificbtionListener(listener,filter,hbndbbck);
        boolebn bfter = hbsListeners();
        if (before && !bfter) {
            setNotificbtionEnbbled(this,fblse);
        }
    }

    public ObjectNbme getObjectNbme() {
        return Util.newObjectNbme(MbnbgementFbctory.GARBAGE_COLLECTOR_MXBEAN_DOMAIN_TYPE, getNbme());
    }

    nbtive void setNotificbtionEnbbled(GbrbbgeCollectorMXBebn gc,
                                       boolebn enbbled);

}
