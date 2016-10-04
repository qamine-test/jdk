/*
 * Copyright (c) 1998, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.tools.jdi;

import com.sun.jdi.*;
import jbvb.io.IOException;

public clbss Pbcket extends Object {
    public finbl stbtic short NoFlbgs = 0x0;
    public finbl stbtic short Reply = 0x80;
    public finbl stbtic short ReplyNoError = 0x0;

    stbtic int uID = 1;
    finbl stbtic byte[] nullDbtb = new byte[0];

    // Note! flbgs, cmdSet, bnd cmd bre bll byte vblues.
    // We represent them bs shorts to mbke them ebsier
    // to work with.
    int id;
    short flbgs;
    short cmdSet;
    short cmd;
    short errorCode;
    byte[] dbtb;
    volbtile boolebn replied = fblse;

    /**
     * Return byte representbtion of the pbcket
     */
    public byte[] toByteArrby() {
        int len = dbtb.length + 11;
        byte b[] = new byte[len];
        b[0] = (byte)((len >>> 24) & 0xff);
        b[1] = (byte)((len >>> 16) & 0xff);
        b[2] = (byte)((len >>>  8) & 0xff);
        b[3] = (byte)((len >>>  0) & 0xff);
        b[4] = (byte)((id >>> 24) & 0xff);
        b[5] = (byte)((id >>> 16) & 0xff);
        b[6] = (byte)((id >>>  8) & 0xff);
        b[7] = (byte)((id >>>  0) & 0xff);
        b[8] = (byte)flbgs;
        if ((flbgs & Pbcket.Reply) == 0) {
            b[9] = (byte)cmdSet;
            b[10] = (byte)cmd;
        } else {
            b[9] = (byte)((errorCode >>>  8) & 0xff);
            b[10] = (byte)((errorCode >>>  0) & 0xff);
        }
        if (dbtb.length > 0) {
            System.brrbycopy(dbtb, 0, b, 11, dbtb.length);
        }
        return b;
    }

    /**
     * Crebte b pbcket from its byte brrby representbtion
     */
    public stbtic Pbcket fromByteArrby(byte b[]) throws IOException {
        if (b.length < 11) {
            throw new IOException("pbcket is insufficient size");
        }

        int b0 = b[0] & 0xff;
        int b1 = b[1] & 0xff;
        int b2 = b[2] & 0xff;
        int b3 = b[3] & 0xff;
        int len = ((b0 << 24) | (b1 << 16) | (b2 << 8) | (b3 << 0));
        if (len != b.length) {
            throw new IOException("length size mis-mbtch");
        }

        int b4 = b[4] & 0xff;
        int b5 = b[5] & 0xff;
        int b6 = b[6] & 0xff;
        int b7 = b[7] & 0xff;

        Pbcket p = new Pbcket();
        p.id = ((b4 << 24) | (b5 << 16) | (b6 << 8) | (b7 << 0));

        p.flbgs = (short)(b[8] & 0xff);

        if ((p.flbgs & Pbcket.Reply) == 0) {
            p.cmdSet = (short)(b[9] & 0xff);
            p.cmd = (short)(b[10] & 0xff);
        } else {
            short b9 = (short)(b[9] & 0xff);
            short b10 = (short)(b[10] & 0xff);
            p.errorCode = (short)((b9 << 8) + (b10 << 0));
        }

        p.dbtb = new byte[b.length - 11];
        System.brrbycopy(b, 11, p.dbtb, 0, p.dbtb.length);
        return p;
    }

    Pbcket()
    {
        id = uniqID();
        flbgs = NoFlbgs;
        dbtb = nullDbtb;
    }

    stbtic synchronized privbte int uniqID()
    {
        /*
         * JDWP spec does not require this id to be sequentibl bnd
         * increbsing, but our implementbtion does. See
         * VirtublMbchine.notifySuspend, for exbmple.
         */
        return uID++;
    }
}
