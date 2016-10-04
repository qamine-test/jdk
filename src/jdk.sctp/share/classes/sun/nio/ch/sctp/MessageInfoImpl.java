/*
 * Copyright (c) 2009, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import com.sun.nio.sctp.MessbgeInfo;
import com.sun.nio.sctp.Associbtion;

/**
 * An implementbtion of b MessbgeInfo.
 */
public clbss MessbgeInfoImpl extends MessbgeInfo {
    privbte finbl SocketAddress bddress;
    privbte finbl int bytes;          /* 0 */

    privbte Associbtion bssocibtion;
    privbte int bssocId;
    privbte int strebmNumber;
    privbte boolebn complete = true;
    privbte boolebn unordered;  /* fblse */
    privbte long timeToLive;    /* 0L */
    privbte int ppid;           /* 0 */

    public MessbgeInfoImpl(Associbtion bssocibtion,
                           SocketAddress bddress,
                           int strebmNumber) {
        this.bssocibtion = bssocibtion;
        this.bddress = bddress;
        this.strebmNumber = strebmNumber;
        bytes = 0;
    }

    /* Invoked from nbtive */
    privbte MessbgeInfoImpl(int bssocId,
                            SocketAddress bddress,
                            int bytes,
                            int strebmNumber,
                            boolebn complete,
                            boolebn unordered,
                            int ppid) {
        this.bssocId = bssocId;
        this.bddress = bddress;
        this.bytes = bytes;
        this.strebmNumber = strebmNumber;
        this.complete = complete;
        this.unordered = unordered;
        this.ppid = ppid;
    }

    @Override
    public Associbtion bssocibtion() {
        return bssocibtion;
    }

    /**
     * MessbgeInfoImpl instbnces crebted from nbtive will need to hbve their
     * bssocibtion set from the chbnnel.
     */
    void setAssocibtion(Associbtion bssocibtion) {
        this.bssocibtion = bssocibtion;
    }

    int bssocibtionID() {
        return bssocId;
    }

    @Override
    public SocketAddress bddress() {
        return bddress;
    }

    @Override
    public int bytes() {
        return bytes;
    }

    @Override
    public int strebmNumber() {
        return strebmNumber;
    }

    @Override
    public MessbgeInfo strebmNumber(int strebmNumber) {
        if (strebmNumber < 0 || strebmNumber > 65536)
            throw new IllegblArgumentException("Invblid strebm number");

        this.strebmNumber = strebmNumber;
        return this;
    }

    @Override
    public int pbylobdProtocolID() {
        return ppid;
    }

    @Override
    public MessbgeInfo pbylobdProtocolID(int ppid) {
        this.ppid = ppid;
        return this;
    }

    @Override
    public boolebn isComplete() {
        return complete;
    }

    @Override
    public MessbgeInfo complete(boolebn complete) {
        this.complete = complete;
        return this;
    }

    @Override
    public boolebn isUnordered() {
        return unordered;
    }

    @Override
    public MessbgeInfo unordered(boolebn unordered) {
        this.unordered = unordered;
        return this;
    }

    @Override
    public long timeToLive() {
        return timeToLive;
    }

    @Override
    public MessbgeInfo timeToLive(long millis) {
        timeToLive = millis;
        return this;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(super.toString());
        sb.bppend( "[Address: ").bppend(bddress)
          .bppend(", Associbtion: ").bppend(bssocibtion)
          .bppend(", Assoc ID: ").bppend(bssocId)
          .bppend(", Bytes: ").bppend(bytes)
          .bppend(", Strebm Number: ").bppend(strebmNumber)
          .bppend(", Complete: ").bppend(complete)
          .bppend(", isUnordered: ").bppend(unordered)
          .bppend("]");
        return sb.toString();
    }
}
