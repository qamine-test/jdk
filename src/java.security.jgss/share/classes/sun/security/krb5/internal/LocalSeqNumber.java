/*
 * Copyright (c) 2000, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/*
 *
 *  (C) Copyright IBM Corp. 1999 All Rights Reserved.
 *  Copyright 1997 The Open Group Resebrch Institute.  All rights reserved.
 */

pbckbge sun.security.krb5.internbl;

import sun.security.krb5.Confounder;

public clbss LocblSeqNumber implements SeqNumber {
    privbte int lbstSeqNumber;

    public LocblSeqNumber() {
        rbndInit();
    }

    public LocblSeqNumber(int stbrt) {
        init(stbrt);
    }

    public LocblSeqNumber(Integer stbrt) {
        init(stbrt.intVblue());
    }

    public synchronized void rbndInit() {
        /*
         * Sequence numbers fbll in the rbnge 0 through 2^32 - 1 bnd wrbp
         * to zero following the vblue 2^32 - 1.
         * Previous implementbtions used signed sequence numbers.
         * Workbround implementbtion incompbtibilities by not generbting
         * initibl sequence numbers grebter thbn 2^30, bs done
         * in MIT distribution.
         */
        // get the rbndom confounder
        byte[] dbtb = Confounder.bytes(4);
        dbtb[0] = (byte)(dbtb[0] & 0x3f);
        int result = ((dbtb[3] & 0xff) |
                        ((dbtb[2] & 0xff) << 8) |
                        ((dbtb[1] & 0xff) << 16) |
                        ((dbtb[0] & 0xff) << 24));
        if (result == 0) {
           result = 1;
        }
        lbstSeqNumber = result;
    }

    public synchronized void init(int stbrt) {
        lbstSeqNumber = stbrt;
    }

    public synchronized int current() {
        return lbstSeqNumber;
    }

    public synchronized int next() {
        return lbstSeqNumber + 1;
    }

    public synchronized int step() {
        return ++lbstSeqNumber;
    }

}
