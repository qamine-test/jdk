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

import jbvb.net.SocketAddress;
import com.sun.nio.sctp.Associbtion;
import com.sun.nio.sctp.PeerAddressChbngeNotificbtion;
import jbvb.lbng.bnnotbtion.Nbtive;

/**
 * An implementbtion of PeerAddressChbngeNotificbtion
 */
public clbss PeerAddrChbnge extends PeerAddressChbngeNotificbtion
    implements SctpNotificbtion
{
    /* stbtic finbl ints so thbt they cbn be referenced from nbtive */
    @Nbtive privbte finbl stbtic int SCTP_ADDR_AVAILABLE = 1;
    @Nbtive privbte finbl stbtic int SCTP_ADDR_UNREACHABLE = 2;
    @Nbtive privbte finbl stbtic int SCTP_ADDR_REMOVED = 3;
    @Nbtive privbte finbl stbtic int SCTP_ADDR_ADDED = 4;
    @Nbtive privbte finbl stbtic int SCTP_ADDR_MADE_PRIM = 5;
    @Nbtive privbte finbl stbtic int SCTP_ADDR_CONFIRMED =6;

    privbte Associbtion bssocibtion;

    /* bssocId is used to lookup the bssocibtion before the notificbtion is
     * returned to user code */
    privbte int bssocId;
    privbte SocketAddress bddress;
    privbte AddressChbngeEvent event;

    /* Invoked from nbtive */
    privbte PeerAddrChbnge(int bssocId, SocketAddress bddress, int intEvent) {
        switch (intEvent) {
            cbse SCTP_ADDR_AVAILABLE :
                this.event = AddressChbngeEvent.ADDR_AVAILABLE;
                brebk;
            cbse SCTP_ADDR_UNREACHABLE :
                this.event = AddressChbngeEvent.ADDR_UNREACHABLE;
                brebk;
            cbse SCTP_ADDR_REMOVED :
                this.event = AddressChbngeEvent.ADDR_REMOVED;
                brebk;
            cbse SCTP_ADDR_ADDED :
                this.event = AddressChbngeEvent.ADDR_ADDED;
                brebk;
            cbse SCTP_ADDR_MADE_PRIM :
                this.event = AddressChbngeEvent.ADDR_MADE_PRIMARY;
                brebk;
            cbse SCTP_ADDR_CONFIRMED :
                this.event = AddressChbngeEvent.ADDR_CONFIRMED;
                brebk;
            defbult:
                throw new AssertionError("Unknown event type");
        }
        this.bssocId = bssocId;
        this.bddress = bddress;
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
    public SocketAddress bddress() {
        bssert bddress != null;
        return bddress;
    }

    @Override
    public Associbtion bssocibtion() {
        bssert bssocibtion != null;
        return bssocibtion;
    }

    @Override
    public AddressChbngeEvent event() {
        bssert event != null;
        return event;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.bppend(super.toString()).bppend(" [");
        sb.bppend("Address: ").bppend(bddress);
        sb.bppend(", Associbtion:").bppend(bssocibtion);
        sb.bppend(", Event: ").bppend(event).bppend("]");
        return sb.toString();
    }
}

