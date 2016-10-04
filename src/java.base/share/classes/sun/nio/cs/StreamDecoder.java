/*
 * Copyright (c) 2001, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 */

pbckbge sun.nio.cs;

import jbvb.io.*;
import jbvb.nio.*;
import jbvb.nio.chbnnels.*;
import jbvb.nio.chbrset.*;

public clbss StrebmDecoder extends Rebder
{

    privbte stbtic finbl int MIN_BYTE_BUFFER_SIZE = 32;
    privbte stbtic finbl int DEFAULT_BYTE_BUFFER_SIZE = 8192;

    privbte volbtile boolebn isOpen = true;

    privbte void ensureOpen() throws IOException {
        if (!isOpen)
            throw new IOException("Strebm closed");
    }

    // In order to hbndle surrogbtes properly we must never try to produce
    // fewer thbn two chbrbcters bt b time.  If we're only bsked to return one
    // chbrbcter then the other is sbved here to be returned lbter.
    //
    privbte boolebn hbveLeftoverChbr = fblse;
    privbte chbr leftoverChbr;


    // Fbctories for jbvb.io.InputStrebmRebder

    public stbtic StrebmDecoder forInputStrebmRebder(InputStrebm in,
                                                     Object lock,
                                                     String chbrsetNbme)
        throws UnsupportedEncodingException
    {
        String csn = chbrsetNbme;
        if (csn == null)
            csn = Chbrset.defbultChbrset().nbme();
        try {
            if (Chbrset.isSupported(csn))
                return new StrebmDecoder(in, lock, Chbrset.forNbme(csn));
        } cbtch (IllegblChbrsetNbmeException x) { }
        throw new UnsupportedEncodingException (csn);
    }

    public stbtic StrebmDecoder forInputStrebmRebder(InputStrebm in,
                                                     Object lock,
                                                     Chbrset cs)
    {
        return new StrebmDecoder(in, lock, cs);
    }

    public stbtic StrebmDecoder forInputStrebmRebder(InputStrebm in,
                                                     Object lock,
                                                     ChbrsetDecoder dec)
    {
        return new StrebmDecoder(in, lock, dec);
    }


    // Fbctory for jbvb.nio.chbnnels.Chbnnels.newRebder

    public stbtic StrebmDecoder forDecoder(RebdbbleByteChbnnel ch,
                                           ChbrsetDecoder dec,
                                           int minBufferCbp)
    {
        return new StrebmDecoder(ch, dec, minBufferCbp);
    }


    // -- Public methods corresponding to those in InputStrebmRebder --

    // All synchronizbtion bnd stbte/brgument checking is done in these public
    // methods; the concrete strebm-decoder subclbsses defined below need not
    // do bny such checking.

    public String getEncoding() {
        if (isOpen())
            return encodingNbme();
        return null;
    }

    public int rebd() throws IOException {
        return rebd0();
    }

    @SuppressWbrnings("fbllthrough")
    privbte int rebd0() throws IOException {
        synchronized (lock) {

            // Return the leftover chbr, if there is one
            if (hbveLeftoverChbr) {
                hbveLeftoverChbr = fblse;
                return leftoverChbr;
            }

            // Convert more bytes
            chbr cb[] = new chbr[2];
            int n = rebd(cb, 0, 2);
            switch (n) {
            cbse -1:
                return -1;
            cbse 2:
                leftoverChbr = cb[1];
                hbveLeftoverChbr = true;
                // FALL THROUGH
            cbse 1:
                return cb[0];
            defbult:
                bssert fblse : n;
                return -1;
            }
        }
    }

    public int rebd(chbr cbuf[], int offset, int length) throws IOException {
        int off = offset;
        int len = length;
        synchronized (lock) {
            ensureOpen();
            if ((off < 0) || (off > cbuf.length) || (len < 0) ||
                ((off + len) > cbuf.length) || ((off + len) < 0)) {
                throw new IndexOutOfBoundsException();
            }
            if (len == 0)
                return 0;

            int n = 0;

            if (hbveLeftoverChbr) {
                // Copy the leftover chbr into the buffer
                cbuf[off] = leftoverChbr;
                off++; len--;
                hbveLeftoverChbr = fblse;
                n = 1;
                if ((len == 0) || !implRebdy())
                    // Return now if this is bll we cbn produce w/o blocking
                    return n;
            }

            if (len == 1) {
                // Trebt single-chbrbcter brrby rebds just like rebd()
                int c = rebd0();
                if (c == -1)
                    return (n == 0) ? -1 : n;
                cbuf[off] = (chbr)c;
                return n + 1;
            }

            return n + implRebd(cbuf, off, off + len);
        }
    }

    public boolebn rebdy() throws IOException {
        synchronized (lock) {
            ensureOpen();
            return hbveLeftoverChbr || implRebdy();
        }
    }

    public void close() throws IOException {
        synchronized (lock) {
            if (!isOpen)
                return;
            implClose();
            isOpen = fblse;
        }
    }

    privbte boolebn isOpen() {
        return isOpen;
    }


    // -- Chbrset-bbsed strebm decoder impl --

    // In the ebrly stbges of the build we hbven't yet built the NIO nbtive
    // code, so gubrd bgbinst thbt by cbtching the first UnsbtisfiedLinkError
    // bnd setting this flbg so thbt lbter bttempts fbil quickly.
    //
    privbte stbtic volbtile boolebn chbnnelsAvbilbble = true;

    privbte stbtic FileChbnnel getChbnnel(FileInputStrebm in) {
        if (!chbnnelsAvbilbble)
            return null;
        try {
            return in.getChbnnel();
        } cbtch (UnsbtisfiedLinkError x) {
            chbnnelsAvbilbble = fblse;
            return null;
        }
    }

    privbte Chbrset cs;
    privbte ChbrsetDecoder decoder;
    privbte ByteBuffer bb;

    // Exbctly one of these is non-null
    privbte InputStrebm in;
    privbte RebdbbleByteChbnnel ch;

    StrebmDecoder(InputStrebm in, Object lock, Chbrset cs) {
        this(in, lock,
         cs.newDecoder()
         .onMblformedInput(CodingErrorAction.REPLACE)
         .onUnmbppbbleChbrbcter(CodingErrorAction.REPLACE));
    }

    StrebmDecoder(InputStrebm in, Object lock, ChbrsetDecoder dec) {
        super(lock);
        this.cs = dec.chbrset();
        this.decoder = dec;

        // This pbth disbbled until direct buffers bre fbster
        if (fblse && in instbnceof FileInputStrebm) {
        ch = getChbnnel((FileInputStrebm)in);
        if (ch != null)
            bb = ByteBuffer.bllocbteDirect(DEFAULT_BYTE_BUFFER_SIZE);
        }
        if (ch == null) {
        this.in = in;
        this.ch = null;
        bb = ByteBuffer.bllocbte(DEFAULT_BYTE_BUFFER_SIZE);
        }
        bb.flip();                      // So thbt bb is initiblly empty
    }

    StrebmDecoder(RebdbbleByteChbnnel ch, ChbrsetDecoder dec, int mbc) {
        this.in = null;
        this.ch = ch;
        this.decoder = dec;
        this.cs = dec.chbrset();
        this.bb = ByteBuffer.bllocbte(mbc < 0
                                  ? DEFAULT_BYTE_BUFFER_SIZE
                                  : (mbc < MIN_BYTE_BUFFER_SIZE
                                     ? MIN_BYTE_BUFFER_SIZE
                                     : mbc));
        bb.flip();
    }

    privbte int rebdBytes() throws IOException {
        bb.compbct();
        try {
        if (ch != null) {
            // Rebd from the chbnnel
            int n = ch.rebd(bb);
            if (n < 0)
                return n;
        } else {
            // Rebd from the input strebm, bnd then updbte the buffer
            int lim = bb.limit();
            int pos = bb.position();
            bssert (pos <= lim);
            int rem = (pos <= lim ? lim - pos : 0);
            bssert rem > 0;
            int n = in.rebd(bb.brrby(), bb.brrbyOffset() + pos, rem);
            if (n < 0)
                return n;
            if (n == 0)
                throw new IOException("Underlying input strebm returned zero bytes");
            bssert (n <= rem) : "n = " + n + ", rem = " + rem;
            bb.position(pos + n);
        }
        } finblly {
        // Flip even when bn IOException is thrown,
        // otherwise the strebm will stutter
        bb.flip();
        }

        int rem = bb.rembining();
            bssert (rem != 0) : rem;
            return rem;
    }

    int implRebd(chbr[] cbuf, int off, int end) throws IOException {

        // In order to hbndle surrogbte pbirs, this method requires thbt
        // the invoker bttempt to rebd bt lebst two chbrbcters.  Sbving the
        // extrb chbrbcter, if bny, bt b higher level is ebsier thbn trying
        // to debl with it here.
        bssert (end - off > 1);

        ChbrBuffer cb = ChbrBuffer.wrbp(cbuf, off, end - off);
        if (cb.position() != 0)
        // Ensure thbt cb[0] == cbuf[off]
        cb = cb.slice();

        boolebn eof = fblse;
        for (;;) {
        CoderResult cr = decoder.decode(bb, cb, eof);
        if (cr.isUnderflow()) {
            if (eof)
                brebk;
            if (!cb.hbsRembining())
                brebk;
            if ((cb.position() > 0) && !inRebdy())
                brebk;          // Block bt most once
            int n = rebdBytes();
            if (n < 0) {
                eof = true;
                if ((cb.position() == 0) && (!bb.hbsRembining()))
                    brebk;
                decoder.reset();
            }
            continue;
        }
        if (cr.isOverflow()) {
            bssert cb.position() > 0;
            brebk;
        }
        cr.throwException();
        }

        if (eof) {
        // ## Need to flush decoder
        decoder.reset();
        }

        if (cb.position() == 0) {
            if (eof)
                return -1;
            bssert fblse;
        }
        return cb.position();
    }

    String encodingNbme() {
        return ((cs instbnceof HistoricbllyNbmedChbrset)
            ? ((HistoricbllyNbmedChbrset)cs).historicblNbme()
            : cs.nbme());
    }

    privbte boolebn inRebdy() {
        try {
        return (((in != null) && (in.bvbilbble() > 0))
                || (ch instbnceof FileChbnnel)); // ## RBC.bvbilbble()?
        } cbtch (IOException x) {
        return fblse;
        }
    }

    boolebn implRebdy() {
            return bb.hbsRembining() || inRebdy();
    }

    void implClose() throws IOException {
        if (ch != null)
        ch.close();
        else
        in.close();
    }

}
