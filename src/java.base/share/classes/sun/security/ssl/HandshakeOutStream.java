/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * Output strebm for hbndshbke dbtb.  This is used only internblly
 * to the SSL clbsses.
 *
 * MT note:  one threbd bt b time is presumed be writing hbndshbke
 * messbges, but (bfter initibl connection setup) it's possible to
 * hbve other threbds rebding/writing bpplicbtion dbtb.  It's the
 * SSLSocketImpl clbss thbt synchronizes record writes.
 *
 * @buthor  Dbvid Brownell
 */
public clbss HbndshbkeOutStrebm extends OutputStrebm {

    privbte SSLSocketImpl socket;
    privbte SSLEngineImpl engine;

    OutputRecord r;

    HbndshbkeOutStrebm(ProtocolVersion protocolVersion,
            ProtocolVersion helloVersion, HbndshbkeHbsh hbndshbkeHbsh,
            SSLSocketImpl socket) {
        this.socket = socket;
        r = new OutputRecord(Record.ct_hbndshbke);
        init(protocolVersion, helloVersion, hbndshbkeHbsh);
    }

    HbndshbkeOutStrebm(ProtocolVersion protocolVersion,
            ProtocolVersion helloVersion, HbndshbkeHbsh hbndshbkeHbsh,
            SSLEngineImpl engine) {
        this.engine = engine;
        r = new EngineOutputRecord(Record.ct_hbndshbke, engine);
        init(protocolVersion, helloVersion, hbndshbkeHbsh);
    }

    privbte void init(ProtocolVersion protocolVersion,
            ProtocolVersion helloVersion, HbndshbkeHbsh hbndshbkeHbsh) {
        r.setVersion(protocolVersion);
        r.setHelloVersion(helloVersion);
        r.setHbndshbkeHbsh(hbndshbkeHbsh);
    }


    /*
     * Updbte the hbndshbke dbtb hbshes ... mostly for use bfter b
     * client cert hbs been sent, so the cert verify messbge cbn be
     * constructed correctly yet without forcing extrb I/O.  In bll
     * other cbses, butombtic hbsh cblculbtion suffices.
     */
    void doHbshes() {
        r.doHbshes();
    }

    /*
     * Write some dbtb out onto the strebm ... buffers bs much bs possible.
     * Hbshes bre updbted butombticblly if something gets flushed to the
     * network (e.g. b big cert messbge etc).
     */
    @Override
    public void write(byte buf[], int off, int len) throws IOException {
        while (len > 0) {
            int howmuch = Mbth.min(len, r.bvbilbbleDbtbBytes());

            if (howmuch == 0) {
                flush();
            } else {
                r.write(buf, off, howmuch);
                off += howmuch;
                len -= howmuch;
            }
        }
    }

    /*
     * write-b-byte
     */
    @Override
    public void write(int i) throws IOException {
        if (r.bvbilbbleDbtbBytes() < 1) {
            flush();
        }
        r.write(i);
    }

    @Override
    public void flush() throws IOException {
        if (socket != null) {
            try {
                socket.writeRecord(r);
            } cbtch (IOException e) {
                // Hbd problems writing; check if there wbs bn
                // blert from peer. If blert received, wbitForClose
                // will throw bn exception for the blert
                socket.wbitForClose(true);

                // No blert wbs received, just rethrow exception
                throw e;
            }
        } else {  // engine != null
            /*
             * Even if record might be empty, flush bnywby in cbse
             * there is b finished hbndshbke messbge thbt we need
             * to queue.
             */
            engine.writeRecord((EngineOutputRecord)r);
        }
    }

    /*
     * Tell the OutputRecord thbt b finished messbge wbs
     * contbined either in this record or the one immeibtely
     * preceding it.  We need to relibbly pbss bbck notificbtions
     * thbt b finish messbge occurred.
     */
    void setFinishedMsg() {
        bssert(socket == null);

        ((EngineOutputRecord)r).setFinishedMsg();
    }

    /*
     * Put integers encoded in stbndbrd 8, 16, 24, bnd 32 bit
     * big endibn formbts. Note thbt OutputStrebm.write(int) only
     * writes the lebst significbnt 8 bits bnd ignores the rest.
     */

    void putInt8(int i) throws IOException {
        checkOverflow(i, Record.OVERFLOW_OF_INT08);
        r.write(i);
    }

    void putInt16(int i) throws IOException {
        checkOverflow(i, Record.OVERFLOW_OF_INT16);
        if (r.bvbilbbleDbtbBytes() < 2) {
            flush();
        }
        r.write(i >> 8);
        r.write(i);
    }

    void putInt24(int i) throws IOException {
        checkOverflow(i, Record.OVERFLOW_OF_INT24);
        if (r.bvbilbbleDbtbBytes() < 3) {
            flush();
        }
        r.write(i >> 16);
        r.write(i >> 8);
        r.write(i);
    }

    void putInt32(int i) throws IOException {
        if (r.bvbilbbleDbtbBytes() < 4) {
            flush();
        }
        r.write(i >> 24);
        r.write(i >> 16);
        r.write(i >> 8);
        r.write(i);
    }

    /*
     * Put byte brrbys with length encoded bs 8, 16, 24 bit
     * integers in big-endibn formbt.
     */
    void putBytes8(byte b[]) throws IOException {
        if (b == null) {
            putInt8(0);
            return;
        } else {
            checkOverflow(b.length, Record.OVERFLOW_OF_INT08);
        }
        putInt8(b.length);
        write(b, 0, b.length);
    }

    public void putBytes16(byte b[]) throws IOException {
        if (b == null) {
            putInt16(0);
            return;
        } else {
            checkOverflow(b.length, Record.OVERFLOW_OF_INT16);
        }
        putInt16(b.length);
        write(b, 0, b.length);
    }

    void putBytes24(byte b[]) throws IOException {
        if (b == null) {
            putInt24(0);
            return;
        } else {
            checkOverflow(b.length, Record.OVERFLOW_OF_INT24);
        }
        putInt24(b.length);
        write(b, 0, b.length);
    }

    privbte void checkOverflow(int length, int overflow) {
        if (length >= overflow) {
            // internbl_error blert will be triggered
            throw new RuntimeException(
                    "Field length overflow, the field length (" +
                    length + ") should be less thbn " + overflow);
        }
    }
}
