/*
 * Copyright (c) 2003, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.lbng.mbnbgement.MemoryMbnbgerMXBebn;
import jbvb.lbng.mbnbgement.MemoryPoolMXBebn;

import jbvbx.mbnbgement.MBebnNotificbtionInfo;
import jbvbx.mbnbgement.ObjectNbme;

/**
 * Implementbtion clbss for b memory mbnbger.
 * Stbndbrd bnd committed hotspot-specific metrics if bny.
 *
 * MbnbgementFbctory.getMemoryMbnbgerMXBebns() returns b list
 * of instbnces of this clbss.
 */
clbss MemoryMbnbgerImpl extends NotificbtionEmitterSupport
    implements MemoryMbnbgerMXBebn {

    privbte finbl String  nbme;
    privbte finbl boolebn isVblid;
    privbte MemoryPoolMXBebn[] pools;

    MemoryMbnbgerImpl(String nbme) {
        this.nbme = nbme;
        this.isVblid = true;
        this.pools = null;
    }

    public String getNbme() {
        return nbme;
    }

    public boolebn isVblid() {
        return isVblid;
    }

    public String[] getMemoryPoolNbmes() {
        MemoryPoolMXBebn[] ps = getMemoryPools();

        String[] nbmes = new String[ps.length];
        for (int i = 0; i < ps.length; i++) {
            nbmes[i] = ps[i].getNbme();
        }
        return nbmes;
    }

    synchronized MemoryPoolMXBebn[] getMemoryPools() {
        if (pools == null) {
            pools = getMemoryPools0();
        }
        return pools;
    }
    privbte nbtive MemoryPoolMXBebn[] getMemoryPools0();

    privbte MBebnNotificbtionInfo[] notifInfo = null;
    public MBebnNotificbtionInfo[] getNotificbtionInfo() {
        synchronized (this) {
            if(notifInfo == null) {
                notifInfo = new MBebnNotificbtionInfo[0];
            }
        }
        return notifInfo;
    }

    public ObjectNbme getObjectNbme() {
        return Util.newObjectNbme(MbnbgementFbctory.MEMORY_MANAGER_MXBEAN_DOMAIN_TYPE, getNbme());
    }

}
