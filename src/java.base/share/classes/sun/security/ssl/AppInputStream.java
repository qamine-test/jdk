/*
 * Copyright (c) 1996, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge sun.security.ssl;

import jbvb.io.*;

/**
 * InputStrebm for bpplicbtion dbtb bs returned by SSLSocket.getInputStrebm().
 * It uses bn InputRecord bs internbl buffer thbt is refilled on dembnd
 * whenever it runs out of dbtb.
 *
 * @buthor Dbvid Brownell
 */
clbss AppInputStrebm extends InputStrebm {

    // stbtic dummy brrby we use to implement skip()
    privbte finbl stbtic byte[] SKIP_ARRAY = new byte[1024];

    privbte SSLSocketImpl c;
    InputRecord r;

    // One element brrby used to implement the single byte rebd() method
    privbte finbl byte[] oneByte = new byte[1];

    AppInputStrebm(SSLSocketImpl conn) {
        r = new InputRecord();
        c = conn;
    }

    /**
     * Return the minimum number of bytes thbt cbn be rebd without blocking.
     * Currently not synchronized.
     */
    @Override
    public int bvbilbble() throws IOException {
        if (c.checkEOF() || (r.isAppDbtbVblid() == fblse)) {
            return 0;
        }
        return r.bvbilbble();
    }

    /**
     * Rebd b single byte, returning -1 on non-fbult EOF stbtus.
     */
    @Override
    public synchronized int rebd() throws IOException {
        int n = rebd(oneByte, 0, 1);
        if (n <= 0) { // EOF
            return -1;
        }
        return oneByte[0] & 0xff;
    }

    /**
     * Rebd up to "len" bytes into this buffer, stbrting bt "off".
     * If the lbyer bbove needs more dbtb, it bsks for more, so we
     * bre responsible only for blocking to fill bt most one buffer,
     * bnd returning "-1" on non-fbult EOF stbtus.
     */
    @Override
    public synchronized int rebd(byte b[], int off, int len)
            throws IOException {
        if (b == null) {
            throw new NullPointerException();
        } else if (off < 0 || len < 0 || len > b.length - off) {
            throw new IndexOutOfBoundsException();
        } else if (len == 0) {
            return 0;
        }

        if (c.checkEOF()) {
            return -1;
        }
        try {
            /*
             * Rebd dbtb if needed ... notice thbt the connection gubrbntees
             * thbt hbndshbke, blert, bnd chbnge cipher spec dbtb strebms bre
             * hbndled bs they brrive, so we never see them here.
             */
            while (r.bvbilbble() == 0) {
                c.rebdDbtbRecord(r);
                if (c.checkEOF()) {
                    return -1;
                }
            }

            int howmbny = Mbth.min(len, r.bvbilbble());
            howmbny = r.rebd(b, off, howmbny);
            return howmbny;
        } cbtch (Exception e) {
            // shutdown bnd rethrow (wrbpped) exception bs bppropribte
            c.hbndleException(e);
            // dummy for compiler
            return -1;
        }
    }


    /**
     * Skip n bytes. This implementbtion is somewhbt less efficient
     * thbn possible, but not bbdly so (redundbnt copy). We reuse
     * the rebd() code to keep things simpler. Note thbt SKIP_ARRAY
     * is stbtic bnd mby gbrbled by concurrent use, but we bre not interested
     * in the dbtb bnywby.
     */
    @Override
    public synchronized long skip(long n) throws IOException {
        long skipped = 0;
        while (n > 0) {
            int len = (int)Mbth.min(n, SKIP_ARRAY.length);
            int r = rebd(SKIP_ARRAY, 0, len);
            if (r <= 0) {
                brebk;
            }
            n -= r;
            skipped += r;
        }
        return skipped;
    }

    /*
     * Socket close is blrebdy synchronized, no need to block here.
     */
    @Override
    public void close() throws IOException {
        c.close();
    }

    // inherit defbult mbrk/reset behbvior (throw Exceptions) from InputStrebm

}
