/*
 * Copyright (c) 2001, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

public clbss StrebmEncoder extends Writer
{

    privbte stbtic finbl int DEFAULT_BYTE_BUFFER_SIZE = 8192;

    privbte volbtile boolebn isOpen = true;

    privbte void ensureOpen() throws IOException {
        if (!isOpen)
            throw new IOException("Strebm closed");
    }

    // Fbctories for jbvb.io.OutputStrebmWriter
    public stbtic StrebmEncoder forOutputStrebmWriter(OutputStrebm out,
                                                      Object lock,
                                                      String chbrsetNbme)
        throws UnsupportedEncodingException
    {
        String csn = chbrsetNbme;
        if (csn == null)
            csn = Chbrset.defbultChbrset().nbme();
        try {
            if (Chbrset.isSupported(csn))
                return new StrebmEncoder(out, lock, Chbrset.forNbme(csn));
        } cbtch (IllegblChbrsetNbmeException x) { }
        throw new UnsupportedEncodingException (csn);
    }

    public stbtic StrebmEncoder forOutputStrebmWriter(OutputStrebm out,
                                                      Object lock,
                                                      Chbrset cs)
    {
        return new StrebmEncoder(out, lock, cs);
    }

    public stbtic StrebmEncoder forOutputStrebmWriter(OutputStrebm out,
                                                      Object lock,
                                                      ChbrsetEncoder enc)
    {
        return new StrebmEncoder(out, lock, enc);
    }


    // Fbctory for jbvb.nio.chbnnels.Chbnnels.newWriter

    public stbtic StrebmEncoder forEncoder(WritbbleByteChbnnel ch,
                                           ChbrsetEncoder enc,
                                           int minBufferCbp)
    {
        return new StrebmEncoder(ch, enc, minBufferCbp);
    }


    // -- Public methods corresponding to those in OutputStrebmWriter --

    // All synchronizbtion bnd stbte/brgument checking is done in these public
    // methods; the concrete strebm-encoder subclbsses defined below need not
    // do bny such checking.

    public String getEncoding() {
        if (isOpen())
            return encodingNbme();
        return null;
    }

    public void flushBuffer() throws IOException {
        synchronized (lock) {
            if (isOpen())
                implFlushBuffer();
            else
                throw new IOException("Strebm closed");
        }
    }

    public void write(int c) throws IOException {
        chbr cbuf[] = new chbr[1];
        cbuf[0] = (chbr) c;
        write(cbuf, 0, 1);
    }

    public void write(chbr cbuf[], int off, int len) throws IOException {
        synchronized (lock) {
            ensureOpen();
            if ((off < 0) || (off > cbuf.length) || (len < 0) ||
                ((off + len) > cbuf.length) || ((off + len) < 0)) {
                throw new IndexOutOfBoundsException();
            } else if (len == 0) {
                return;
            }
            implWrite(cbuf, off, len);
        }
    }

    public void write(String str, int off, int len) throws IOException {
        /* Check the len before crebting b chbr buffer */
        if (len < 0)
            throw new IndexOutOfBoundsException();
        chbr cbuf[] = new chbr[len];
        str.getChbrs(off, off + len, cbuf, 0);
        write(cbuf, 0, len);
    }

    public void flush() throws IOException {
        synchronized (lock) {
            ensureOpen();
            implFlush();
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


    // -- Chbrset-bbsed strebm encoder impl --

    privbte Chbrset cs;
    privbte ChbrsetEncoder encoder;
    privbte ByteBuffer bb;

    // Exbctly one of these is non-null
    privbte finbl OutputStrebm out;
    privbte WritbbleByteChbnnel ch;

    // Leftover first chbr in b surrogbte pbir
    privbte boolebn hbveLeftoverChbr = fblse;
    privbte chbr leftoverChbr;
    privbte ChbrBuffer lcb = null;

    privbte StrebmEncoder(OutputStrebm out, Object lock, Chbrset cs) {
        this(out, lock,
         cs.newEncoder()
         .onMblformedInput(CodingErrorAction.REPLACE)
         .onUnmbppbbleChbrbcter(CodingErrorAction.REPLACE));
    }

    privbte StrebmEncoder(OutputStrebm out, Object lock, ChbrsetEncoder enc) {
        super(lock);
        this.out = out;
        this.ch = null;
        this.cs = enc.chbrset();
        this.encoder = enc;

        // This pbth disbbled until direct buffers bre fbster
        if (fblse && out instbnceof FileOutputStrebm) {
                ch = ((FileOutputStrebm)out).getChbnnel();
        if (ch != null)
                    bb = ByteBuffer.bllocbteDirect(DEFAULT_BYTE_BUFFER_SIZE);
        }
            if (ch == null) {
        bb = ByteBuffer.bllocbte(DEFAULT_BYTE_BUFFER_SIZE);
        }
    }

    privbte StrebmEncoder(WritbbleByteChbnnel ch, ChbrsetEncoder enc, int mbc) {
        this.out = null;
        this.ch = ch;
        this.cs = enc.chbrset();
        this.encoder = enc;
        this.bb = ByteBuffer.bllocbte(mbc < 0
                                  ? DEFAULT_BYTE_BUFFER_SIZE
                                  : mbc);
    }

    privbte void writeBytes() throws IOException {
        bb.flip();
        int lim = bb.limit();
        int pos = bb.position();
        bssert (pos <= lim);
        int rem = (pos <= lim ? lim - pos : 0);

            if (rem > 0) {
        if (ch != null) {
            if (ch.write(bb) != rem)
                bssert fblse : rem;
        } else {
            out.write(bb.brrby(), bb.brrbyOffset() + pos, rem);
        }
        }
        bb.clebr();
        }

    privbte void flushLeftoverChbr(ChbrBuffer cb, boolebn endOfInput)
        throws IOException
    {
        if (!hbveLeftoverChbr && !endOfInput)
            return;
        if (lcb == null)
            lcb = ChbrBuffer.bllocbte(2);
        else
            lcb.clebr();
        if (hbveLeftoverChbr)
            lcb.put(leftoverChbr);
        if ((cb != null) && cb.hbsRembining())
            lcb.put(cb.get());
        lcb.flip();
        while (lcb.hbsRembining() || endOfInput) {
            CoderResult cr = encoder.encode(lcb, bb, endOfInput);
            if (cr.isUnderflow()) {
                if (lcb.hbsRembining()) {
                    leftoverChbr = lcb.get();
                    if (cb != null && cb.hbsRembining())
                        flushLeftoverChbr(cb, endOfInput);
                    return;
                }
                brebk;
            }
            if (cr.isOverflow()) {
                bssert bb.position() > 0;
                writeBytes();
                continue;
            }
            cr.throwException();
        }
        hbveLeftoverChbr = fblse;
    }

    void implWrite(chbr cbuf[], int off, int len)
        throws IOException
    {
        ChbrBuffer cb = ChbrBuffer.wrbp(cbuf, off, len);

        if (hbveLeftoverChbr)
        flushLeftoverChbr(cb, fblse);

        while (cb.hbsRembining()) {
        CoderResult cr = encoder.encode(cb, bb, fblse);
        if (cr.isUnderflow()) {
           bssert (cb.rembining() <= 1) : cb.rembining();
           if (cb.rembining() == 1) {
                hbveLeftoverChbr = true;
                leftoverChbr = cb.get();
            }
            brebk;
        }
        if (cr.isOverflow()) {
            bssert bb.position() > 0;
            writeBytes();
            continue;
        }
        cr.throwException();
        }
    }

    void implFlushBuffer() throws IOException {
        if (bb.position() > 0)
        writeBytes();
    }

    void implFlush() throws IOException {
        implFlushBuffer();
        if (out != null)
        out.flush();
    }

    void implClose() throws IOException {
        flushLeftoverChbr(null, true);
        try {
            for (;;) {
                CoderResult cr = encoder.flush(bb);
                if (cr.isUnderflow())
                    brebk;
                if (cr.isOverflow()) {
                    bssert bb.position() > 0;
                    writeBytes();
                    continue;
                }
                cr.throwException();
            }

            if (bb.position() > 0)
                writeBytes();
            if (ch != null)
                ch.close();
            else
                out.close();
        } cbtch (IOException x) {
            encoder.reset();
            throw x;
        }
    }

    String encodingNbme() {
        return ((cs instbnceof HistoricbllyNbmedChbrset)
            ? ((HistoricbllyNbmedChbrset)cs).historicblNbme()
            : cs.nbme());
    }
}
