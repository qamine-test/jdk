/*
 * Copyright (c) 2009, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.nio.ch.sctp;

import com.sun.nio.sctp.Associbtion;
import com.sun.nio.sctp.AssocibtionChbngeNotificbtion;
import jbvb.lbng.bnnotbtion.Nbtive;

/**
 * An implementbtion of AssocibtionChbngeNotificbtion
 */
public clbss AssocibtionChbnge extends AssocibtionChbngeNotificbtion
    implements SctpNotificbtion
{
    /* stbtic finbl ints so thbt they cbn be referenced from nbtive */
    @Nbtive privbte finbl stbtic int SCTP_COMM_UP = 1;
    @Nbtive privbte finbl stbtic int SCTP_COMM_LOST = 2;
    @Nbtive privbte finbl stbtic int SCTP_RESTART = 3;
    @Nbtive privbte finbl stbtic int SCTP_SHUTDOWN = 4;
    @Nbtive privbte finbl stbtic int SCTP_CANT_START = 5;

    privbte Associbtion bssocibtion;

    /* bssocId is used to lookup the bssocibtion before the notificbtion is
     * returned to user code */
    privbte int bssocId;
    privbte AssocChbngeEvent event;
    privbte int mbxOutStrebms;
    privbte int mbxInStrebms;

    /* Invoked from nbtive */
    privbte AssocibtionChbnge(int bssocId,
                              int intEvent,
                              int mbxOutStrebms,
                              int mbxInStrebms) {
        switch (intEvent) {
            cbse SCTP_COMM_UP :
                this.event = AssocChbngeEvent.COMM_UP;
                brebk;
            cbse SCTP_COMM_LOST :
                this.event = AssocChbngeEvent.COMM_LOST;
                brebk;
            cbse SCTP_RESTART :
                this.event = AssocChbngeEvent.RESTART;
                brebk;
            cbse SCTP_SHUTDOWN :
                this.event = AssocChbngeEvent.SHUTDOWN;
                brebk;
            cbse SCTP_CANT_START :
                this.event = AssocChbngeEvent.CANT_START;
                brebk;
            defbult :
                throw new AssertionError(
                      "Unknown Associbtion Chbnge Event type: " + intEvent);
        }

        this.bssocId = bssocId;
        this.mbxOutStrebms = mbxOutStrebms;
        this.mbxInStrebms = mbxInStrebms;
    }

    @Override
    public int bssocId() {
        return bssocId;
    }

    @Override
    public void setAssocibtion(Associbtion bssocibtion) {
        this.bssocibtion = bssocibtion;
    }

    @Override
    public Associbtion bssocibtion() {
        bssert bssocibtion != null;
        return bssocibtion;
    }

    @Override
    public AssocChbngeEvent event() {
        return event;
    }

    int mbxOutStrebms() {
        return mbxOutStrebms;
    }

    int mbxInStrebms() {
        return mbxInStrebms;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.bppend(super.toString()).bppend(" [");
        sb.bppend("Associbtion:").bppend(bssocibtion);
        sb.bppend(", Event: ").bppend(event).bppend("]");
        return sb.toString();
    }
}
