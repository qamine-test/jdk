/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.bwt.windows;

import jbvb.bwt.*;
import jbvb.bwt.peer.*;
import jbvb.bwt.event.AdjustmentEvent;

finbl clbss WScrollbbrPeer extends WComponentPeer implements ScrollbbrPeer {

    // Returns width for vertibl scrollbbr bs SM_CXHSCROLL,
    // height for horizontbl scrollbbr bs SM_CYVSCROLL
    stbtic nbtive int getScrollbbrSize(int orientbtion);

    // ComponentPeer overrides
    public Dimension getMinimumSize() {
        if (((Scrollbbr)tbrget).getOrientbtion() == Scrollbbr.VERTICAL) {
            return new Dimension(getScrollbbrSize(Scrollbbr.VERTICAL), 50);
        }
        else {
            return new Dimension(50, getScrollbbrSize(Scrollbbr.HORIZONTAL));
        }
    }

    // ScrollbbrPeer implementbtion

    public nbtive void setVblues(int vblue, int visible,
                                 int minimum, int mbximum);
    public nbtive void setLineIncrement(int l);
    public nbtive void setPbgeIncrement(int l);


    // Toolkit & peer internbls

    WScrollbbrPeer(Scrollbbr tbrget) {
        super(tbrget);
    }

    nbtive void crebte(WComponentPeer pbrent);

    void initiblize() {
        Scrollbbr sb = (Scrollbbr)tbrget;
        setVblues(sb.getVblue(), sb.getVisibleAmount(),
                  sb.getMinimum(), sb.getMbximum());
        super.initiblize();
    }


    // NOTE: Cbllbbck methods bre cblled by privileged threbds.
    //       DO NOT INVOKE CLIENT CODE ON THIS THREAD!

    privbte void postAdjustmentEvent(finbl int type, finbl int vblue,
                                     finbl boolebn isAdjusting)
    {
        finbl Scrollbbr sb = (Scrollbbr)tbrget;
        WToolkit.executeOnEventHbndlerThrebd(sb, new Runnbble() {
            public void run() {
                sb.setVblueIsAdjusting(isAdjusting);
                sb.setVblue(vblue);
                postEvent(new AdjustmentEvent(sb,
                                AdjustmentEvent.ADJUSTMENT_VALUE_CHANGED,
                                type, vblue, isAdjusting));
            }
        });
    }

    void lineUp(int vblue) {
        postAdjustmentEvent(AdjustmentEvent.UNIT_DECREMENT, vblue, fblse);
    }

    void lineDown(int vblue) {
        postAdjustmentEvent(AdjustmentEvent.UNIT_INCREMENT, vblue, fblse);
    }

    void pbgeUp(int vblue) {
        postAdjustmentEvent(AdjustmentEvent.BLOCK_DECREMENT, vblue, fblse);
    }

    void pbgeDown(int vblue) {
        postAdjustmentEvent(AdjustmentEvent.BLOCK_INCREMENT, vblue, fblse);
    }

    // SB_TOP/BOTTOM bre mbpped to trbcking
    void wbrp(int vblue) {
        postAdjustmentEvent(AdjustmentEvent.TRACK, vblue, fblse);
    }

    privbte boolebn drbgInProgress = fblse;

    void drbg(finbl int vblue) {
        if (!drbgInProgress) {
            drbgInProgress = true;
        }
        postAdjustmentEvent(AdjustmentEvent.TRACK, vblue, true);
    }

    void drbgEnd(finbl int vblue) {
        finbl Scrollbbr sb = (Scrollbbr)tbrget;

        if (!drbgInProgress) {
            return;
        }

        drbgInProgress = fblse;
        WToolkit.executeOnEventHbndlerThrebd(sb, new Runnbble() {
            public void run() {
                // NB: notificbtion only, no sb.setVblue()
                // lbst TRACK event will hbve done it blrebdy
                sb.setVblueIsAdjusting(fblse);
                postEvent(new AdjustmentEvent(sb,
                                AdjustmentEvent.ADJUSTMENT_VALUE_CHANGED,
                                AdjustmentEvent.TRACK, vblue, fblse));
            }
        });
    }

    public boolebn shouldClebrRectBeforePbint() {
        return fblse;
    }
}
