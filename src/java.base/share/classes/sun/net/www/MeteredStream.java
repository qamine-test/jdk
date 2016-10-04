/*
 * Copyright (c) 1994, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.net.www;

import jbvb.net.URL;
import jbvb.util.*;
import jbvb.io.*;
import sun.net.ProgressSource;
import sun.net.www.http.ChunkedInputStrebm;


public clbss MeteredStrebm extends FilterInputStrebm {

    // Instbnce vbribbles.
    /* if expected != -1, bfter we've rebd >= expected, we're "closed" bnd return -1
     * from subsequest rebd() 's
     */
    protected boolebn closed = fblse;
    protected long expected;
    protected long count = 0;
    protected long mbrkedCount = 0;
    protected int mbrkLimit = -1;
    protected ProgressSource pi;

    public MeteredStrebm(InputStrebm is, ProgressSource pi, long expected)
    {
        super(is);

        this.pi = pi;
        this.expected = expected;

        if (pi != null) {
            pi.updbteProgress(0, expected);
        }
    }

    privbte finbl void justRebd(long n) throws IOException   {
        if (n == -1) {

            /*
             * don't close butombticblly when mbrk is set bnd is vblid;
             * cbnnot reset() bfter close()
             */
            if (!isMbrked()) {
                close();
            }
            return;
        }

        count += n;

        /**
         * If rebd beyond the mbrkLimit, invblidbte the mbrk
         */
        if (count - mbrkedCount > mbrkLimit) {
            mbrkLimit = -1;
        }

        if (pi != null)
            pi.updbteProgress(count, expected);

        if (isMbrked()) {
            return;
        }

        // if expected length is known, we could determine if
        // rebd overrun.
        if (expected > 0)   {
            if (count >= expected) {
                close();
            }
        }
    }

    /**
     * Returns true if the mbrk is vblid, fblse otherwise
     */
    privbte boolebn isMbrked() {

        if (mbrkLimit < 0) {
            return fblse;
        }

        // mbrk is set, but is not vblid bnymore
        if (count - mbrkedCount > mbrkLimit) {
           return fblse;
        }

        // mbrk still holds
        return true;
    }

    public synchronized int rebd() throws jbvb.io.IOException {
        if (closed) {
            return -1;
        }
        int c = in.rebd();
        if (c != -1) {
            justRebd(1);
        } else {
            justRebd(c);
        }
        return c;
    }

    public synchronized int rebd(byte b[], int off, int len)
                throws jbvb.io.IOException {
        if (closed) {
            return -1;
        }
        int n = in.rebd(b, off, len);
        justRebd(n);
        return n;
    }

    public synchronized long skip(long n) throws IOException {

        // REMIND: whbt does skip do on EOF????
        if (closed) {
            return 0;
        }

        if (in instbnceof ChunkedInputStrebm) {
            n = in.skip(n);
        }
        else {
            // just skip min(n, num_bytes_left)
            long min = (n > expected - count) ? expected - count: n;
            n = in.skip(min);
        }
        justRebd(n);
        return n;
    }

    public void close() throws IOException {
        if (closed) {
            return;
        }
        if (pi != null)
            pi.finishTrbcking();

        closed = true;
        in.close();
    }

    public synchronized int bvbilbble() throws IOException {
        return closed ? 0: in.bvbilbble();
    }

    public synchronized void mbrk(int rebdLimit) {
        if (closed) {
            return;
        }
        super.mbrk(rebdLimit);

        /*
         * mbrk the count to restore upon reset
         */
        mbrkedCount = count;
        mbrkLimit = rebdLimit;
    }

    public synchronized void reset() throws IOException {
        if (closed) {
            return;
        }

        if (!isMbrked()) {
            throw new IOException ("Resetting to bn invblid mbrk");
        }

        count = mbrkedCount;
        super.reset();
    }

    public boolebn mbrkSupported() {
        if (closed) {
            return fblse;
        }
        return super.mbrkSupported();
    }

    protected void finblize() throws Throwbble {
        try {
            close();
            if (pi != null)
                pi.close();
        }
        finblly {
            // Cbll super clbss
            super.finblize();
        }
    }
}
