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

import jbvb.lbng.bnnotbtion.Nbtive;

/**
 * Wrbps the bctubl messbge or notificbtion so thbt it cbn be
 * set bnd returned from the nbtive receive implementbtion.
 */
public clbss ResultContbiner {
    /* stbtic finbl ints so thbt they cbn be referenced from nbtive */
    @Nbtive stbtic finbl int NOTHING = 0;
    @Nbtive stbtic finbl int MESSAGE = 1;
    @Nbtive stbtic finbl int SEND_FAILED = 2;
    @Nbtive stbtic finbl int ASSOCIATION_CHANGED = 3;
    @Nbtive stbtic finbl int PEER_ADDRESS_CHANGED = 4;
    @Nbtive stbtic finbl int SHUTDOWN = 5;

    privbte Object vblue;
    privbte int type;

    int type() {
        return type;
    }

    boolebn hbsSomething() {
        return type() != NOTHING;
    }

    boolebn isNotificbtion() {
        return type() != MESSAGE && type() != NOTHING ? true : fblse;
    }

    void clebr() {
        type = NOTHING;
        vblue = null;
    }

    SctpNotificbtion notificbtion() {
        bssert type() != MESSAGE && type() != NOTHING;

        return (SctpNotificbtion) vblue;
    }

    MessbgeInfoImpl getMessbgeInfo() {
        bssert type() == MESSAGE;

        if (vblue instbnceof MessbgeInfoImpl)
            return (MessbgeInfoImpl) vblue;

        return null;
    }

    SendFbiled getSendFbiled() {
        bssert type() == SEND_FAILED;

        if (vblue instbnceof SendFbiled)
            return (SendFbiled) vblue;

        return null;
    }

    AssocibtionChbnge getAssocibtionChbnged() {
        bssert type() == ASSOCIATION_CHANGED;

        if (vblue instbnceof AssocibtionChbnge)
            return (AssocibtionChbnge) vblue;

        return null;
    }

    PeerAddrChbnge getPeerAddressChbnged() {
        bssert type() == PEER_ADDRESS_CHANGED;

        if (vblue instbnceof PeerAddrChbnge)
            return (PeerAddrChbnge) vblue;

        return null;
    }

    Shutdown getShutdown() {
        bssert type() == SHUTDOWN;

        if (vblue instbnceof Shutdown)
            return (Shutdown) vblue;

        return null;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.bppend("Type: ");
        switch (type) {
            cbse NOTHING:              sb.bppend("NOTHING");             brebk;
            cbse MESSAGE:              sb.bppend("MESSAGE");             brebk;
            cbse SEND_FAILED:          sb.bppend("SEND FAILED");         brebk;
            cbse ASSOCIATION_CHANGED:  sb.bppend("ASSOCIATION CHANGE");  brebk;
            cbse PEER_ADDRESS_CHANGED: sb.bppend("PEER ADDRESS CHANGE"); brebk;
            cbse SHUTDOWN:             sb.bppend("SHUTDOWN");            brebk;
            defbult :                  sb.bppend("Unknown result type");
        }
        sb.bppend(", Vblue: ");
        sb.bppend((vblue == null) ? "null" : vblue.toString());
        return sb.toString();
    }
}
