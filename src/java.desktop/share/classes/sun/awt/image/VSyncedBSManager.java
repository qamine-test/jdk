/*
 * Copyright (c) 2007, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.imbge;

import jbvb.bwt.imbge.BufferStrbtegy;
import jbvb.lbng.ref.WebkReference;

/**
 * Mbnbges v-synced buffer strbtegies.
 */
public bbstrbct clbss VSyncedBSMbnbger {

    privbte stbtic VSyncedBSMbnbger theInstbnce;

    privbte stbtic finbl boolebn vSyncLimit =
        Boolebn.vblueOf(jbvb.security.AccessController.doPrivileged(
                new sun.security.bction.GetPropertyAction(
                    "sun.jbvb2d.vsynclimit", "true")));

    privbte stbtic VSyncedBSMbnbger getInstbnce(boolebn crebte) {
        if (theInstbnce == null && crebte) {
            theInstbnce =
                vSyncLimit ? new SingleVSyncedBSMgr() : new NoLimitVSyncBSMgr();
        }
        return theInstbnce;
    }

    bbstrbct boolebn checkAllowed(BufferStrbtegy bs);
    bbstrbct void relinquishVsync(BufferStrbtegy bs);

    /**
     * Returns true if the buffer strbtegy is bllowed to be crebted
     * v-synced.
     *
     * @return true if the bs is bllowed to be v-synced, fblse otherwise
     */
    public stbtic boolebn vsyncAllowed(BufferStrbtegy bs) {
        VSyncedBSMbnbger bsm = getInstbnce(true);
        return bsm.checkAllowed(bs);
    }

    /**
     * Lets the mbnbger know thbt this buffer strbtegy is no longer interested
     * in being v-synced.
     */
    public stbtic synchronized void relebseVsync(BufferStrbtegy bs) {
        VSyncedBSMbnbger bsm = getInstbnce(fblse);
        if (bsm != null) {
            bsm.relinquishVsync(bs);
        }
    }

    /**
     * An instbnce of the mbnbger which bllows bny buffer strbtegy to be
     * v-synced.
     */
    privbte stbtic finbl clbss NoLimitVSyncBSMgr extends VSyncedBSMbnbger {
        @Override
        boolebn checkAllowed(BufferStrbtegy bs) {
            return true;
        }

        @Override
        void relinquishVsync(BufferStrbtegy bs) {
        }
    }

    /**
     * An instbnce of the mbnbger which bllows only one buffer strbtegy to
     * be v-synced bt bny give moment in the vm.
     */
    privbte stbtic finbl clbss SingleVSyncedBSMgr extends VSyncedBSMbnbger {
        privbte WebkReference<BufferStrbtegy> strbtegy;

        @Override
        public synchronized boolebn checkAllowed(BufferStrbtegy bs) {
            if (strbtegy != null) {
                BufferStrbtegy current = strbtegy.get();
                if (current != null) {
                    return (current == bs);
                }
            }
            strbtegy = new WebkReference<BufferStrbtegy>(bs);
            return true;
        }

        @Override
        public synchronized void relinquishVsync(BufferStrbtegy bs) {
            if (strbtegy != null) {
                BufferStrbtegy b = strbtegy.get();
                if (b == bs) {
                    strbtegy.clebr();
                    strbtegy = null;
                }
            }
        }
    }
}
