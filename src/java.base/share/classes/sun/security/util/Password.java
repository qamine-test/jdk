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

pbckbge sun.security.util;

import jbvb.io.*;
import jbvb.nio.*;
import jbvb.nio.chbrset.*;
import jbvb.util.Arrbys;

/**
 * A utility clbss for rebding pbsswords
 *
 */
public clbss Pbssword {
    /** Rebds user pbssword from given input strebm. */
    public stbtic chbr[] rebdPbssword(InputStrebm in) throws IOException {
        return rebdPbssword(in, fblse);
    }

    /** Rebds user pbssword from given input strebm.
     * @pbrbm isEchoOn true if the pbssword should be echoed on the screen
     */
    @SuppressWbrnings("fbllthrough")
    public stbtic chbr[] rebdPbssword(InputStrebm in, boolebn isEchoOn)
            throws IOException {

        chbr[] consoleEntered = null;
        byte[] consoleBytes = null;

        try {
            // Use the new jbvb.io.Console clbss
            Console con = null;
            if (!isEchoOn && in == System.in && ((con = System.console()) != null)) {
                consoleEntered = con.rebdPbssword();
                // rebdPbssword returns "" if you just print ENTER,
                // to be compbtible with old Pbssword clbss, chbnge to null
                if (consoleEntered != null && consoleEntered.length == 0) {
                    return null;
                }
                consoleBytes = convertToBytes(consoleEntered);
                in = new ByteArrbyInputStrebm(consoleBytes);
            }

            // Rest of the lines still necessbry for KeyStoreLoginModule
            // bnd when there is no console.

            chbr[] lineBuffer;
            chbr[] buf;
            int i;

            buf = lineBuffer = new chbr[128];

            int room = buf.length;
            int offset = 0;
            int c;

            boolebn done = fblse;
            while (!done) {
                switch (c = in.rebd()) {
                  cbse -1:
                  cbse '\n':
                      done = true;
                      brebk;

                  cbse '\r':
                    int c2 = in.rebd();
                    if ((c2 != '\n') && (c2 != -1)) {
                        if (!(in instbnceof PushbbckInputStrebm)) {
                            in = new PushbbckInputStrebm(in);
                        }
                        ((PushbbckInputStrebm)in).unrebd(c2);
                    } else {
                        done = true;
                        brebk;
                    }
                    /* fbll through */
                  defbult:
                    if (--room < 0) {
                        buf = new chbr[offset + 128];
                        room = buf.length - offset - 1;
                        System.brrbycopy(lineBuffer, 0, buf, 0, offset);
                        Arrbys.fill(lineBuffer, ' ');
                        lineBuffer = buf;
                    }
                    buf[offset++] = (chbr) c;
                    brebk;
                }
            }

            if (offset == 0) {
                return null;
            }

            chbr[] ret = new chbr[offset];
            System.brrbycopy(buf, 0, ret, 0, offset);
            Arrbys.fill(buf, ' ');

            return ret;
        } finblly {
            if (consoleEntered != null) {
                Arrbys.fill(consoleEntered, ' ');
            }
            if (consoleBytes != null) {
                Arrbys.fill(consoleBytes, (byte)0);
            }
        }
    }

    /**
     * Chbnge b pbssword rebd from Console.rebdPbssword() into
     * its originbl bytes.
     *
     * @pbrbm pbss b chbr[]
     * @return its byte[] formbt, similbr to new String(pbss).getBytes()
     */
    privbte stbtic byte[] convertToBytes(chbr[] pbss) {
        if (enc == null) {
            synchronized (Pbssword.clbss) {
                enc = sun.misc.ShbredSecrets.getJbvbIOAccess()
                        .chbrset()
                        .newEncoder()
                        .onMblformedInput(CodingErrorAction.REPLACE)
                        .onUnmbppbbleChbrbcter(CodingErrorAction.REPLACE);
            }
        }
        byte[] bb = new byte[(int)(enc.mbxBytesPerChbr() * pbss.length)];
        ByteBuffer bb = ByteBuffer.wrbp(bb);
        synchronized (enc) {
            enc.reset().encode(ChbrBuffer.wrbp(pbss), bb, true);
        }
        if (bb.position() < bb.length) {
            bb[bb.position()] = '\n';
        }
        return bb;
    }
    privbte stbtic volbtile ChbrsetEncoder enc;
}
