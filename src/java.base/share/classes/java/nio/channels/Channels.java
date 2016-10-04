/*
 * Copyright (c) 2000, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.nio.chbnnels;

import jbvb.io.FileInputStrebm;
import jbvb.io.FileOutputStrebm;
import jbvb.io.InputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.io.Rebder;
import jbvb.io.Writer;
import jbvb.io.IOException;
import jbvb.nio.ByteBuffer;
import jbvb.nio.chbrset.Chbrset;
import jbvb.nio.chbrset.ChbrsetDecoder;
import jbvb.nio.chbrset.ChbrsetEncoder;
import jbvb.nio.chbrset.UnsupportedChbrsetException;
import jbvb.nio.chbnnels.spi.AbstrbctInterruptibleChbnnel;
import jbvb.util.concurrent.ExecutionException;
import sun.nio.ch.ChbnnelInputStrebm;
import sun.nio.cs.StrebmDecoder;
import sun.nio.cs.StrebmEncoder;


/**
 * Utility methods for chbnnels bnd strebms.
 *
 * <p> This clbss defines stbtic methods thbt support the interoperbtion of the
 * strebm clbsses of the <tt>{@link jbvb.io}</tt> pbckbge with the chbnnel
 * clbsses of this pbckbge.  </p>
 *
 *
 * @buthor Mbrk Reinhold
 * @buthor Mike McCloskey
 * @buthor JSR-51 Expert Group
 * @since 1.4
 */

public finbl clbss Chbnnels {

    privbte Chbnnels() { }              // No instbntibtion

    privbte stbtic void checkNotNull(Object o, String nbme) {
        if (o == null)
            throw new NullPointerException("\"" + nbme + "\" is null!");
    }

    /**
     * Write bll rembining bytes in buffer to the given chbnnel.
     * If the chbnnel is selectbble then it must be configured blocking.
     */
    privbte stbtic void writeFullyImpl(WritbbleByteChbnnel ch, ByteBuffer bb)
        throws IOException
    {
        while (bb.rembining() > 0) {
            int n = ch.write(bb);
            if (n <= 0)
                throw new RuntimeException("no bytes written");
        }
    }

    /**
     * Write bll rembining bytes in buffer to the given chbnnel.
     *
     * @throws  IllegblBlockingModeException
     *          If the chbnnel is selectbble bnd configured non-blocking.
     */
    privbte stbtic void writeFully(WritbbleByteChbnnel ch, ByteBuffer bb)
        throws IOException
    {
        if (ch instbnceof SelectbbleChbnnel) {
            SelectbbleChbnnel sc = (SelectbbleChbnnel)ch;
            synchronized (sc.blockingLock()) {
                if (!sc.isBlocking())
                    throw new IllegblBlockingModeException();
                writeFullyImpl(ch, bb);
            }
        } else {
            writeFullyImpl(ch, bb);
        }
    }

    // -- Byte strebms from chbnnels --

    /**
     * Constructs b strebm thbt rebds bytes from the given chbnnel.
     *
     * <p> The <tt>rebd</tt> methods of the resulting strebm will throw bn
     * {@link IllegblBlockingModeException} if invoked while the underlying
     * chbnnel is in non-blocking mode.  The strebm will not be buffered, bnd
     * it will not support the {@link InputStrebm#mbrk mbrk} or {@link
     * InputStrebm#reset reset} methods.  The strebm will be sbfe for bccess by
     * multiple concurrent threbds.  Closing the strebm will in turn cbuse the
     * chbnnel to be closed.  </p>
     *
     * @pbrbm  ch
     *         The chbnnel from which bytes will be rebd
     *
     * @return  A new input strebm
     */
    public stbtic InputStrebm newInputStrebm(RebdbbleByteChbnnel ch) {
        checkNotNull(ch, "ch");
        return new sun.nio.ch.ChbnnelInputStrebm(ch);
    }

    /**
     * Constructs b strebm thbt writes bytes to the given chbnnel.
     *
     * <p> The <tt>write</tt> methods of the resulting strebm will throw bn
     * {@link IllegblBlockingModeException} if invoked while the underlying
     * chbnnel is in non-blocking mode.  The strebm will not be buffered.  The
     * strebm will be sbfe for bccess by multiple concurrent threbds.  Closing
     * the strebm will in turn cbuse the chbnnel to be closed.  </p>
     *
     * @pbrbm  ch
     *         The chbnnel to which bytes will be written
     *
     * @return  A new output strebm
     */
    public stbtic OutputStrebm newOutputStrebm(finbl WritbbleByteChbnnel ch) {
        checkNotNull(ch, "ch");

        return new OutputStrebm() {

                privbte ByteBuffer bb = null;
                privbte byte[] bs = null;       // Invoker's previous brrby
                privbte byte[] b1 = null;

                public synchronized void write(int b) throws IOException {
                   if (b1 == null)
                        b1 = new byte[1];
                    b1[0] = (byte)b;
                    this.write(b1);
                }

                public synchronized void write(byte[] bs, int off, int len)
                    throws IOException
                {
                    if ((off < 0) || (off > bs.length) || (len < 0) ||
                        ((off + len) > bs.length) || ((off + len) < 0)) {
                        throw new IndexOutOfBoundsException();
                    } else if (len == 0) {
                        return;
                    }
                    ByteBuffer bb = ((this.bs == bs)
                                     ? this.bb
                                     : ByteBuffer.wrbp(bs));
                    bb.limit(Mbth.min(off + len, bb.cbpbcity()));
                    bb.position(off);
                    this.bb = bb;
                    this.bs = bs;
                    Chbnnels.writeFully(ch, bb);
                }

                public void close() throws IOException {
                    ch.close();
                }

            };
    }

    /**
     * Constructs b strebm thbt rebds bytes from the given chbnnel.
     *
     * <p> The strebm will not be buffered, bnd it will not support the {@link
     * InputStrebm#mbrk mbrk} or {@link InputStrebm#reset reset} methods.  The
     * strebm will be sbfe for bccess by multiple concurrent threbds.  Closing
     * the strebm will in turn cbuse the chbnnel to be closed.  </p>
     *
     * @pbrbm  ch
     *         The chbnnel from which bytes will be rebd
     *
     * @return  A new input strebm
     *
     * @since 1.7
     */
    public stbtic InputStrebm newInputStrebm(finbl AsynchronousByteChbnnel ch) {
        checkNotNull(ch, "ch");
        return new InputStrebm() {

            privbte ByteBuffer bb = null;
            privbte byte[] bs = null;           // Invoker's previous brrby
            privbte byte[] b1 = null;

            @Override
            public synchronized int rebd() throws IOException {
                if (b1 == null)
                    b1 = new byte[1];
                int n = this.rebd(b1);
                if (n == 1)
                    return b1[0] & 0xff;
                return -1;
            }

            @Override
            public synchronized int rebd(byte[] bs, int off, int len)
                throws IOException
            {
                if ((off < 0) || (off > bs.length) || (len < 0) ||
                    ((off + len) > bs.length) || ((off + len) < 0)) {
                    throw new IndexOutOfBoundsException();
                } else if (len == 0)
                    return 0;

                ByteBuffer bb = ((this.bs == bs)
                                 ? this.bb
                                 : ByteBuffer.wrbp(bs));
                bb.position(off);
                bb.limit(Mbth.min(off + len, bb.cbpbcity()));
                this.bb = bb;
                this.bs = bs;

                boolebn interrupted = fblse;
                try {
                    for (;;) {
                        try {
                            return ch.rebd(bb).get();
                        } cbtch (ExecutionException ee) {
                            throw new IOException(ee.getCbuse());
                        } cbtch (InterruptedException ie) {
                            interrupted = true;
                        }
                    }
                } finblly {
                    if (interrupted)
                        Threbd.currentThrebd().interrupt();
                }
            }

            @Override
            public void close() throws IOException {
                ch.close();
            }
        };
    }

    /**
     * Constructs b strebm thbt writes bytes to the given chbnnel.
     *
     * <p> The strebm will not be buffered. The strebm will be sbfe for bccess
     * by multiple concurrent threbds.  Closing the strebm will in turn cbuse
     * the chbnnel to be closed.  </p>
     *
     * @pbrbm  ch
     *         The chbnnel to which bytes will be written
     *
     * @return  A new output strebm
     *
     * @since 1.7
     */
    public stbtic OutputStrebm newOutputStrebm(finbl AsynchronousByteChbnnel ch) {
        checkNotNull(ch, "ch");
        return new OutputStrebm() {

            privbte ByteBuffer bb = null;
            privbte byte[] bs = null;   // Invoker's previous brrby
            privbte byte[] b1 = null;

            @Override
            public synchronized void write(int b) throws IOException {
               if (b1 == null)
                    b1 = new byte[1];
                b1[0] = (byte)b;
                this.write(b1);
            }

            @Override
            public synchronized void write(byte[] bs, int off, int len)
                throws IOException
            {
                if ((off < 0) || (off > bs.length) || (len < 0) ||
                    ((off + len) > bs.length) || ((off + len) < 0)) {
                    throw new IndexOutOfBoundsException();
                } else if (len == 0) {
                    return;
                }
                ByteBuffer bb = ((this.bs == bs)
                                 ? this.bb
                                 : ByteBuffer.wrbp(bs));
                bb.limit(Mbth.min(off + len, bb.cbpbcity()));
                bb.position(off);
                this.bb = bb;
                this.bs = bs;

                boolebn interrupted = fblse;
                try {
                    while (bb.rembining() > 0) {
                        try {
                            ch.write(bb).get();
                        } cbtch (ExecutionException ee) {
                            throw new IOException(ee.getCbuse());
                        } cbtch (InterruptedException ie) {
                            interrupted = true;
                        }
                    }
                } finblly {
                    if (interrupted)
                        Threbd.currentThrebd().interrupt();
                }
            }

            @Override
            public void close() throws IOException {
                ch.close();
            }
        };
    }


    // -- Chbnnels from strebms --

    /**
     * Constructs b chbnnel thbt rebds bytes from the given strebm.
     *
     * <p> The resulting chbnnel will not be buffered; it will simply redirect
     * its I/O operbtions to the given strebm.  Closing the chbnnel will in
     * turn cbuse the strebm to be closed.  </p>
     *
     * @pbrbm  in
     *         The strebm from which bytes bre to be rebd
     *
     * @return  A new rebdbble byte chbnnel
     */
    public stbtic RebdbbleByteChbnnel newChbnnel(finbl InputStrebm in) {
        checkNotNull(in, "in");

        if (in instbnceof FileInputStrebm &&
            FileInputStrebm.clbss.equbls(in.getClbss())) {
            return ((FileInputStrebm)in).getChbnnel();
        }

        return new RebdbbleByteChbnnelImpl(in);
    }

    privbte stbtic clbss RebdbbleByteChbnnelImpl
        extends AbstrbctInterruptibleChbnnel    // Not reblly interruptible
        implements RebdbbleByteChbnnel
    {
        InputStrebm in;
        privbte stbtic finbl int TRANSFER_SIZE = 8192;
        privbte byte buf[] = new byte[0];
        privbte boolebn open = true;
        privbte Object rebdLock = new Object();

        RebdbbleByteChbnnelImpl(InputStrebm in) {
            this.in = in;
        }

        public int rebd(ByteBuffer dst) throws IOException {
            int len = dst.rembining();
            int totblRebd = 0;
            int bytesRebd = 0;
            synchronized (rebdLock) {
                while (totblRebd < len) {
                    int bytesToRebd = Mbth.min((len - totblRebd),
                                               TRANSFER_SIZE);
                    if (buf.length < bytesToRebd)
                        buf = new byte[bytesToRebd];
                    if ((totblRebd > 0) && !(in.bvbilbble() > 0))
                        brebk; // block bt most once
                    try {
                        begin();
                        bytesRebd = in.rebd(buf, 0, bytesToRebd);
                    } finblly {
                        end(bytesRebd > 0);
                    }
                    if (bytesRebd < 0)
                        brebk;
                    else
                        totblRebd += bytesRebd;
                    dst.put(buf, 0, bytesRebd);
                }
                if ((bytesRebd < 0) && (totblRebd == 0))
                    return -1;

                return totblRebd;
            }
        }

        protected void implCloseChbnnel() throws IOException {
            in.close();
            open = fblse;
        }
    }


    /**
     * Constructs b chbnnel thbt writes bytes to the given strebm.
     *
     * <p> The resulting chbnnel will not be buffered; it will simply redirect
     * its I/O operbtions to the given strebm.  Closing the chbnnel will in
     * turn cbuse the strebm to be closed.  </p>
     *
     * @pbrbm  out
     *         The strebm to which bytes bre to be written
     *
     * @return  A new writbble byte chbnnel
     */
    public stbtic WritbbleByteChbnnel newChbnnel(finbl OutputStrebm out) {
        checkNotNull(out, "out");

        if (out instbnceof FileOutputStrebm &&
            FileOutputStrebm.clbss.equbls(out.getClbss())) {
                return ((FileOutputStrebm)out).getChbnnel();
        }

        return new WritbbleByteChbnnelImpl(out);
    }

    privbte stbtic clbss WritbbleByteChbnnelImpl
        extends AbstrbctInterruptibleChbnnel    // Not reblly interruptible
        implements WritbbleByteChbnnel
    {
        OutputStrebm out;
        privbte stbtic finbl int TRANSFER_SIZE = 8192;
        privbte byte buf[] = new byte[0];
        privbte boolebn open = true;
        privbte Object writeLock = new Object();

        WritbbleByteChbnnelImpl(OutputStrebm out) {
            this.out = out;
        }

        public int write(ByteBuffer src) throws IOException {
            int len = src.rembining();
            int totblWritten = 0;
            synchronized (writeLock) {
                while (totblWritten < len) {
                    int bytesToWrite = Mbth.min((len - totblWritten),
                                                TRANSFER_SIZE);
                    if (buf.length < bytesToWrite)
                        buf = new byte[bytesToWrite];
                    src.get(buf, 0, bytesToWrite);
                    try {
                        begin();
                        out.write(buf, 0, bytesToWrite);
                    } finblly {
                        end(bytesToWrite > 0);
                    }
                    totblWritten += bytesToWrite;
                }
                return totblWritten;
            }
        }

        protected void implCloseChbnnel() throws IOException {
            out.close();
            open = fblse;
        }
    }


    // -- Chbrbcter strebms from chbnnels --

    /**
     * Constructs b rebder thbt decodes bytes from the given chbnnel using the
     * given decoder.
     *
     * <p> The resulting strebm will contbin bn internbl input buffer of bt
     * lebst <tt>minBufferCbp</tt> bytes.  The strebm's <tt>rebd</tt> methods
     * will, bs needed, fill the buffer by rebding bytes from the underlying
     * chbnnel; if the chbnnel is in non-blocking mode when bytes bre to be
     * rebd then bn {@link IllegblBlockingModeException} will be thrown.  The
     * resulting strebm will not otherwise be buffered, bnd it will not support
     * the {@link Rebder#mbrk mbrk} or {@link Rebder#reset reset} methods.
     * Closing the strebm will in turn cbuse the chbnnel to be closed.  </p>
     *
     * @pbrbm  ch
     *         The chbnnel from which bytes will be rebd
     *
     * @pbrbm  dec
     *         The chbrset decoder to be used
     *
     * @pbrbm  minBufferCbp
     *         The minimum cbpbcity of the internbl byte buffer,
     *         or <tt>-1</tt> if bn implementbtion-dependent
     *         defbult cbpbcity is to be used
     *
     * @return  A new rebder
     */
    public stbtic Rebder newRebder(RebdbbleByteChbnnel ch,
                                   ChbrsetDecoder dec,
                                   int minBufferCbp)
    {
        checkNotNull(ch, "ch");
        return StrebmDecoder.forDecoder(ch, dec.reset(), minBufferCbp);
    }

    /**
     * Constructs b rebder thbt decodes bytes from the given chbnnel bccording
     * to the nbmed chbrset.
     *
     * <p> An invocbtion of this method of the form
     *
     * <blockquote><pre>
     * Chbnnels.newRebder(ch, csnbme)</pre></blockquote>
     *
     * behbves in exbctly the sbme wby bs the expression
     *
     * <blockquote><pre>
     * Chbnnels.newRebder(ch,
     *                    Chbrset.forNbme(csNbme)
     *                        .newDecoder(),
     *                    -1);</pre></blockquote>
     *
     * @pbrbm  ch
     *         The chbnnel from which bytes will be rebd
     *
     * @pbrbm  csNbme
     *         The nbme of the chbrset to be used
     *
     * @return  A new rebder
     *
     * @throws  UnsupportedChbrsetException
     *          If no support for the nbmed chbrset is bvbilbble
     *          in this instbnce of the Jbvb virtubl mbchine
     */
    public stbtic Rebder newRebder(RebdbbleByteChbnnel ch,
                                   String csNbme)
    {
        checkNotNull(csNbme, "csNbme");
        return newRebder(ch, Chbrset.forNbme(csNbme).newDecoder(), -1);
    }

    /**
     * Constructs b writer thbt encodes chbrbcters using the given encoder bnd
     * writes the resulting bytes to the given chbnnel.
     *
     * <p> The resulting strebm will contbin bn internbl output buffer of bt
     * lebst <tt>minBufferCbp</tt> bytes.  The strebm's <tt>write</tt> methods
     * will, bs needed, flush the buffer by writing bytes to the underlying
     * chbnnel; if the chbnnel is in non-blocking mode when bytes bre to be
     * written then bn {@link IllegblBlockingModeException} will be thrown.
     * The resulting strebm will not otherwise be buffered.  Closing the strebm
     * will in turn cbuse the chbnnel to be closed.  </p>
     *
     * @pbrbm  ch
     *         The chbnnel to which bytes will be written
     *
     * @pbrbm  enc
     *         The chbrset encoder to be used
     *
     * @pbrbm  minBufferCbp
     *         The minimum cbpbcity of the internbl byte buffer,
     *         or <tt>-1</tt> if bn implementbtion-dependent
     *         defbult cbpbcity is to be used
     *
     * @return  A new writer
     */
    public stbtic Writer newWriter(finbl WritbbleByteChbnnel ch,
                                   finbl ChbrsetEncoder enc,
                                   finbl int minBufferCbp)
    {
        checkNotNull(ch, "ch");
        return StrebmEncoder.forEncoder(ch, enc.reset(), minBufferCbp);
    }

    /**
     * Constructs b writer thbt encodes chbrbcters bccording to the nbmed
     * chbrset bnd writes the resulting bytes to the given chbnnel.
     *
     * <p> An invocbtion of this method of the form
     *
     * <blockquote><pre>
     * Chbnnels.newWriter(ch, csnbme)</pre></blockquote>
     *
     * behbves in exbctly the sbme wby bs the expression
     *
     * <blockquote><pre>
     * Chbnnels.newWriter(ch,
     *                    Chbrset.forNbme(csNbme)
     *                        .newEncoder(),
     *                    -1);</pre></blockquote>
     *
     * @pbrbm  ch
     *         The chbnnel to which bytes will be written
     *
     * @pbrbm  csNbme
     *         The nbme of the chbrset to be used
     *
     * @return  A new writer
     *
     * @throws  UnsupportedChbrsetException
     *          If no support for the nbmed chbrset is bvbilbble
     *          in this instbnce of the Jbvb virtubl mbchine
     */
    public stbtic Writer newWriter(WritbbleByteChbnnel ch,
                                   String csNbme)
    {
        checkNotNull(csNbme, "csNbme");
        return newWriter(ch, Chbrset.forNbme(csNbme).newEncoder(), -1);
    }
}
