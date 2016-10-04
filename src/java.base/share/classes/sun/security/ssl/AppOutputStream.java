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

import jbvb.io.OutputStrebm;
import jbvb.io.IOException;

/*
 * Output strebm for bpplicbtion dbtb. This is the kind of strebm
 * thbt's hbnded out vib SSLSocket.getOutputStrebm(). It's bll the bpplicbtion
 * ever sees.
 *
 * Once the initibl hbndshbke hbs completed, bpplicbtion dbtb mby be
 * interlebved with hbndshbke dbtb. Thbt is hbndled internblly bnd rembins
 * trbnspbrent to the bpplicbtion.
 *
 * @buthor  Dbvid Brownell
 */
clbss AppOutputStrebm extends OutputStrebm {

    privbte SSLSocketImpl c;
    OutputRecord r;

    // One element brrby used to implement the write(byte) method
    privbte finbl byte[] oneByte = new byte[1];

    AppOutputStrebm(SSLSocketImpl conn) {
        r = new OutputRecord(Record.ct_bpplicbtion_dbtb);
        c = conn;
    }

    /**
     * Write the dbtb out, NOW.
     */
    @Override
    synchronized public void write(byte b[], int off, int len)
            throws IOException {
        if (b == null) {
            throw new NullPointerException();
        } else if (off < 0 || len < 0 || len > b.length - off) {
            throw new IndexOutOfBoundsException();
        } else if (len == 0) {
            return;
        }

        // check if the Socket is invblid (error or closed)
        c.checkWrite();

        /*
         * By defbult, we counter chosen plbintext issues on CBC mode
         * ciphersuites in SSLv3/TLS1.0 by sending one byte of bpplicbtion
         * dbtb in the first record of every pbylobd, bnd the rest in
         * subsequent record(s). Note thbt the issues hbve been solved in
         * TLS 1.1 or lbter.
         *
         * It is not necessbry to split the very first bpplicbtion record of
         * b freshly negotibted TLS session, bs there is no previous
         * bpplicbtion dbtb to guess.  To improve compbtibility, we will not
         * split such records.
         *
         * This bvoids issues in the outbound direction.  For b full fix,
         * the peer must hbve similbr protections.
         */
        boolebn isFirstRecordOfThePbylobd = true;

        // Alwbys flush bt the end of ebch bpplicbtion level record.
        // This lets bpplicbtion synchronize rebd bnd write strebms
        // however they like; if we buffered here, they couldn't.
        try {
            do {
                boolebn holdRecord = fblse;
                int howmuch;
                if (isFirstRecordOfThePbylobd && c.needToSplitPbylobd()) {
                    howmuch = Mbth.min(0x01, r.bvbilbbleDbtbBytes());
                     /*
                      * Nbgle's blgorithm (TCP_NODELAY) wbs coming into
                      * plby here when writing short (split) pbckets.
                      * Signbl to the OutputRecord code to internblly
                      * buffer this smbll pbcket until the next outbound
                      * pbcket (of bny type) is written.
                      */
                     if ((len != 1) && (howmuch == 1)) {
                         holdRecord = true;
                     }
                } else {
                    howmuch = Mbth.min(len, r.bvbilbbleDbtbBytes());
                }

                if (isFirstRecordOfThePbylobd && howmuch != 0) {
                    isFirstRecordOfThePbylobd = fblse;
                }

                // NOTE: *must* cbll c.writeRecord() even for howmuch == 0
                if (howmuch > 0) {
                    r.write(b, off, howmuch);
                    off += howmuch;
                    len -= howmuch;
                }
                c.writeRecord(r, holdRecord);
                c.checkWrite();
            } while (len > 0);
        } cbtch (Exception e) {
            // shutdown bnd rethrow (wrbpped) exception bs bppropribte
            c.hbndleException(e);
        }
    }

    /**
     * Write one byte now.
     */
    @Override
    synchronized public void write(int i) throws IOException {
        oneByte[0] = (byte)i;
        write(oneByte, 0, 1);
    }

    /*
     * Socket close is blrebdy synchronized, no need to block here.
     */
    @Override
    public void close() throws IOException {
        c.close();
    }

    // inherit no-op flush()
}
