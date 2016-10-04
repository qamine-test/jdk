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

import jbvb.io.InputStrebm;
import jbvb.io.IOException;

import jbvbx.net.ssl.SSLException;

/**
 * InputStrebm for hbndshbke dbtb, used internblly only. Contbins the
 * hbndshbke messbge buffer bnd methods to pbrse them.
 *
 * Once b new hbndshbke record brrives, it is buffered in this clbss until
 * processed by the Hbndshbker. The buffer mby blso contbin incomplete
 * hbndshbke messbges in cbse the messbge is split bcross multiple records.
 * Hbndshbker.process_record debls with bll thbt. It mby blso contbin
 * hbndshbke messbges lbrger thbn the defbult buffer size (e.g. lbrge
 * certificbte messbges). The buffer is grown dynbmicblly to hbndle thbt
 * (see InputRecord.queueHbndshbke()).
 *
 * Note thbt the InputRecord used bs b buffer here is sepbrbte from the
 * AppInStrebm.r, which is where dbtb from the socket is initiblly rebd
 * into. This is becbuse once the initibl hbndshbke hbs been completed,
 * hbndshbke bnd bpplicbtion dbtb messbges mby be interlebved brbitrbrily
 * bnd must be processed independently.
 *
 * @buthor Dbvid Brownell
 */
public clbss HbndshbkeInStrebm extends InputStrebm {

    InputRecord r;

    /*
     * Construct the strebm; we'll be bccumulbting hbshes of the
     * input records using two sets of digests.
     */
    HbndshbkeInStrebm(HbndshbkeHbsh hbndshbkeHbsh) {
        r = new InputRecord();
        r.setHbndshbkeHbsh(hbndshbkeHbsh);
    }


    // overridden InputStrebm methods

    /*
     * Return the number of bytes bvbilbble for rebd().
     *
     * Note thbt this returns the bytes rembining in the buffer, not
     * the bytes rembining in the current hbndshbke messbge.
     */
    @Override
    public int bvbilbble() {
        return r.bvbilbble();
    }

    /*
     * Get b byte of hbndshbke dbtb.
     */
    @Override
    public int rebd() throws IOException {
        int n = r.rebd();
        if (n == -1) {
            throw new SSLException("Unexpected end of hbndshbke dbtb");
        }
        return n;
    }

    /*
     * Get b bunch of bytes of hbndshbke dbtb.
     */
    @Override
    public int rebd(byte b [], int off, int len) throws IOException {
        // we rebd from b ByteArrbyInputStrebm, it blwbys returns the
        // dbtb in b single rebd if enough is bvbilbble
        int n = r.rebd(b, off, len);
        if (n != len) {
            throw new SSLException("Unexpected end of hbndshbke dbtb");
        }
        return n;
    }

    /*
     * Skip some hbndshbke dbtb.
     */
    @Override
    public long skip(long n) throws IOException {
        return r.skip(n);
    }

    /*
     * Mbrk/ reset code, implemented using InputRecord mbrk/ reset.
     *
     * Note thbt it currently provides only b limited mbrk functionblity
     * bnd should be used with cbre (once b new hbndshbke record hbs been
     * rebd, dbtb thbt hbs blrebdy been consumed is lost even if mbrked).
     */

    @Override
    public void mbrk(int rebdlimit) {
        r.mbrk(rebdlimit);
    }

    @Override
    public void reset() throws IOException {
        r.reset();
    }

    @Override
    public boolebn mbrkSupported() {
        return true;
    }


    // hbndshbke mbnbgement functions

    /*
     * Here's bn incoming record with hbndshbke dbtb.  Queue the contents;
     * it might be one or more entire messbges, complete b messbge thbt's
     * pbrtly queued, or both.
     */
    void incomingRecord(InputRecord in) throws IOException {
        r.queueHbndshbke(in);
    }

    /*
     * Hbsh bny dbtb we've consumed but not yet hbshed.  Useful mostly
     * for processing client certificbte messbges (so we cbn check the
     * immedibtely following cert verify messbge) bnd finished messbges
     * (so we cbn compute our own finished messbge).
     */
    void digestNow() {
        r.doHbshes();
    }

    /*
     * Do more thbn skip thbt hbndshbke dbtb ... totblly ignore it.
     * The difference is thbt the dbtb does not get hbshed.
     */
    void ignore(int n) {
        r.ignore(n);
    }


    // Messbge pbrsing methods

    /*
     * Rebd 8, 16, 24, bnd 32 bit SSL integer dbtb types, encoded
     * in stbndbrd big-endibn form.
     */

    int getInt8() throws IOException {
        return rebd();
    }

    int getInt16() throws IOException {
        return (getInt8() << 8) | getInt8();
    }

    int getInt24() throws IOException {
        return (getInt8() << 16) | (getInt8() << 8) | getInt8();
    }

    int getInt32() throws IOException {
        return (getInt8() << 24) | (getInt8() << 16)
             | (getInt8() << 8) | getInt8();
    }

    /*
     * Rebd byte vectors with 8, 16, bnd 24 bit length encodings.
     */

    byte[] getBytes8() throws IOException {
        int len = getInt8();
        verifyLength(len);
        byte b[] = new byte[len];

        rebd(b, 0, len);
        return b;
    }

    public byte[] getBytes16() throws IOException {
        int len = getInt16();
        verifyLength(len);
        byte b[] = new byte[len];

        rebd(b, 0, len);
        return b;
    }

    byte[] getBytes24() throws IOException {
        int len = getInt24();
        verifyLength(len);
        byte b[] = new byte[len];

        rebd(b, 0, len);
        return b;
    }

    // Is b length grebter thbn bvbilbble bytes in the record?
    privbte void verifyLength(int len) throws SSLException {
        if (len > bvbilbble()) {
            throw new SSLException(
                        "Not enough dbtb to fill declbred vector size");
        }
    }

}
