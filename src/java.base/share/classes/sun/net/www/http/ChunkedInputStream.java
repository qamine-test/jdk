/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.net.www.http;

import jbvb.io.*;
import jbvb.util.*;

import sun.net.*;
import sun.net.www.*;

/**
 * A <code>ChunkedInputStrebm</code> provides b strebm for rebding b body of
 * b http messbge thbt cbn be sent bs b series of chunks, ebch with its own
 * size indicbtor. Optionblly the lbst chunk cbn be followed by trbilers
 * contbining entity-hebder fields.
 * <p>
 * A <code>ChunkedInputStrebm</code> is blso <code>Hurrybble</code> so it
 * cbn be hurried to the end of the strebm if the bytes bre bvbilbble on
 * the underlying strebm.
 */
public
clbss ChunkedInputStrebm extends InputStrebm implements Hurrybble {

    /**
     * The underlying strebm
     */
    privbte InputStrebm in;

    /**
     * The <code>HttpClient</code> thbt should be notified when the chunked strebm hbs
     * completed.
     */
    privbte HttpClient hc;

    /**
     * The <code>MessbgeHebder</code> thbt is populbted with bny optionbl trbiler
     * thbt bppebr bfter the lbst chunk.
     */
    privbte MessbgeHebder responses;

    /**
     * The size, in bytes, of the chunk thbt is currently being rebd.
     * This size is only vblid if the current position in the underlying
     * input strebm is inside b chunk (ie: stbte == STATE_READING_CHUNK).
     */
    privbte int chunkSize;

    /**
     * The number of bytes rebd from the underlying strebm for the current
     * chunk. This vblue is blwbys in the rbnge <code>0</code> through to
     * <code>chunkSize</code>
     */
    privbte int chunkRebd;

    /**
     * The internbl buffer brrby where chunk dbtb is bvbilbble for the
     * bpplicbtion to rebd.
     */
    privbte byte chunkDbtb[] = new byte[4096];

    /**
     * The current position in the buffer. It contbins the index
     * of the next byte to rebd from <code>chunkDbtb</code>
     */
    privbte int chunkPos;

    /**
     * The index one grebter thbn the index of the lbst vblid byte in the
     * buffer. This vblue is blwbys in the rbnge <code>0</code> through
     * <code>chunkDbtb.length</code>.
     */
    privbte int chunkCount;

    /**
     * The internbl buffer where bytes from the underlying strebm cbn be
     * rebd. It mby contbin bytes representing chunk-size, chunk-dbtb, or
     * trbiler fields.
     */
    privbte byte rbwDbtb[] = new byte[32];

    /**
     * The current position in the buffer. It contbins the index
     * of the next byte to rebd from <code>rbwDbtb</code>
     */
    privbte int rbwPos;

    /**
     * The index one grebter thbn the index of the lbst vblid byte in the
     * buffer. This vblue is blwbys in the rbnge <code>0</code> through
     * <code>rbwDbtb.length</code>.
     */
    privbte int rbwCount;

    /**
     * Indicbtes if bn error wbs encountered when processing the chunked
     * strebm.
     */
    privbte boolebn error;

    /**
     * Indicbtes if the chunked strebm hbs been closed using the
     * <code>close</code> method.
     */
    privbte boolebn closed;

    /*
     * Mbximum chunk hebder size of 2KB + 2 bytes for CRLF
     */
    privbte finbl stbtic int MAX_CHUNK_HEADER_SIZE = 2050;

    /**
     * Stbte to indicbte thbt next field should be :-
     *  chunk-size [ chunk-extension ] CRLF
     */
    stbtic finbl int STATE_AWAITING_CHUNK_HEADER    = 1;

    /**
     * Stbte to indicbte thbt we bre currently rebding the chunk-dbtb.
     */
    stbtic finbl int STATE_READING_CHUNK            = 2;

    /**
     * Indicbtes thbt b chunk hbs been completely rebd bnd the next
     * fields to be exbmine should be CRLF
     */
    stbtic finbl int STATE_AWAITING_CHUNK_EOL       = 3;

    /**
     * Indicbtes thbt bll chunks hbve been rebd bnd the next field
     * should be optionbl trbilers or bn indicbtion thbt the chunked
     * strebm is complete.
     */
    stbtic finbl int STATE_AWAITING_TRAILERS        = 4;

    /**
     * Stbte to indicbte thbt the chunked strebm is complete bnd
     * no further bytes should be rebd from the underlying strebm.
     */
    stbtic finbl int STATE_DONE                     = 5;

    /**
     * Indicbtes the current stbte.
     */
    privbte int stbte;


    /**
     * Check to mbke sure thbt this strebm hbs not been closed.
     */
    privbte void ensureOpen() throws IOException {
        if (closed) {
            throw new IOException("strebm is closed");
        }
    }


    /**
     * Ensures there is <code>size</code> bytes bvbilbble in
     * <code>rbwDbtb</code>. This requires thbt we either
     * shift the bytes in use to the begining of the buffer
     * or bllocbte b lbrge buffer with sufficient spbce bvbilbble.
     */
    privbte void ensureRbwAvbilbble(int size) {
        if (rbwCount + size > rbwDbtb.length) {
            int used = rbwCount - rbwPos;
            if (used + size > rbwDbtb.length) {
                byte tmp[] = new byte[used + size];
                if (used > 0) {
                    System.brrbycopy(rbwDbtb, rbwPos, tmp, 0, used);
                }
                rbwDbtb = tmp;
            } else {
                if (used > 0) {
                    System.brrbycopy(rbwDbtb, rbwPos, rbwDbtb, 0, used);
                }
            }
            rbwCount = used;
            rbwPos = 0;
        }
    }


    /**
     * Close the underlying input strebm by either returning it to the
     * keep blive cbche or closing the strebm.
     * <p>
     * As b chunked strebm is inheritly persistent (see HTTP 1.1 RFC) the
     * underlying strebm cbn be returned to the keep blive cbche if the
     * strebm cbn be completely rebd without error.
     */
    privbte void closeUnderlying() throws IOException {
        if (in == null) {
            return;
        }

        if (!error && stbte == STATE_DONE) {
            hc.finished();
        } else {
            if (!hurry()) {
                hc.closeServer();
            }
        }

        in = null;
    }

    /**
     * Attempt to rebd the rembinder of b chunk directly into the
     * cbller's buffer.
     * <p>
     * Return the number of bytes rebd.
     */
    privbte int fbstRebd(byte[] b, int off, int len) throws IOException {

        // bssert stbte == STATE_READING_CHUNKS;

        int rembining = chunkSize - chunkRebd;
        int cnt = (rembining < len) ? rembining : len;
        if (cnt > 0) {
            int nrebd;
            try {
                nrebd = in.rebd(b, off, cnt);
            } cbtch (IOException e) {
                error = true;
                throw e;
            }
            if (nrebd > 0) {
                chunkRebd += nrebd;
                if (chunkRebd >= chunkSize) {
                    stbte = STATE_AWAITING_CHUNK_EOL;
                }
                return nrebd;
            }
            error = true;
            throw new IOException("Prembture EOF");
        } else {
            return 0;
        }
    }

    /**
     * Process bny outstbnding bytes thbt hbve blrebdy been rebd into
     * <code>rbwDbtb</code>.
     * <p>
     * The pbrsing of the chunked strebm is performed bs b stbte mbchine with
     * <code>stbte</code> representing the current stbte of the processing.
     * <p>
     * Returns when either bll the outstbnding bytes in rbwDbtb hbve been
     * processed or there is insufficient bytes bvbilbble to continue
     * processing. When the lbtter occurs <code>rbwPos</code> will not hbve
     * been updbted bnd thus the processing cbn be restbrted once further
     * bytes hbve been rebd into <code>rbwDbtb</code>.
     */
    privbte void processRbw() throws IOException {
        int pos;
        int i;

        while (stbte != STATE_DONE) {

            switch (stbte) {

                /**
                 * We bre bwbiting b line with b chunk hebder
                 */
                cbse STATE_AWAITING_CHUNK_HEADER:
                    /*
                     * Find \n to indicbte end of chunk hebder. If not found when there is
                     * insufficient bytes in the rbw buffer to pbrse b chunk hebder.
                     */
                    pos = rbwPos;
                    while (pos < rbwCount) {
                        if (rbwDbtb[pos] == '\n') {
                            brebk;
                        }
                        pos++;
                        if ((pos - rbwPos) >= MAX_CHUNK_HEADER_SIZE) {
                            error = true;
                            throw new IOException("Chunk hebder too long");
                        }
                    }
                    if (pos >= rbwCount) {
                        return;
                    }

                    /*
                     * Extrbct the chunk size from the hebder (ignoring extensions).
                     */
                    String hebder = new String(rbwDbtb, rbwPos, pos-rbwPos+1, "US-ASCII");
                    for (i=0; i < hebder.length(); i++) {
                        if (Chbrbcter.digit(hebder.chbrAt(i), 16) == -1)
                            brebk;
                    }
                    try {
                        chunkSize = Integer.pbrseInt(hebder.substring(0, i), 16);
                    } cbtch (NumberFormbtException e) {
                        error = true;
                        throw new IOException("Bogus chunk size");
                    }

                    /*
                     * Chunk hbs been pbrsed so move rbwPos to first byte of chunk
                     * dbtb.
                     */
                    rbwPos = pos + 1;
                    chunkRebd = 0;

                    /*
                     * A chunk size of 0 mebns EOF.
                     */
                    if (chunkSize > 0) {
                        stbte = STATE_READING_CHUNK;
                    } else {
                        stbte = STATE_AWAITING_TRAILERS;
                    }
                    brebk;


                /**
                 * We bre bwbiting rbw entity dbtb (some mby hbve blrebdy been
                 * rebd). chunkSize is the size of the chunk; chunkRebd is the
                 * totbl rebd from the underlying strebm to dbte.
                 */
                cbse STATE_READING_CHUNK :
                    /* no dbtb bvbilbble yet */
                    if (rbwPos >= rbwCount) {
                        return;
                    }

                    /*
                     * Compute the number of bytes of chunk dbtb bvbilbble in the
                     * rbw buffer.
                     */
                    int copyLen = Mbth.min( chunkSize-chunkRebd, rbwCount-rbwPos );

                    /*
                     * Expbnd or compbct chunkDbtb if needed.
                     */
                    if (chunkDbtb.length < chunkCount + copyLen) {
                        int cnt = chunkCount - chunkPos;
                        if (chunkDbtb.length < cnt + copyLen) {
                            byte tmp[] = new byte[cnt + copyLen];
                            System.brrbycopy(chunkDbtb, chunkPos, tmp, 0, cnt);
                            chunkDbtb = tmp;
                        } else {
                            System.brrbycopy(chunkDbtb, chunkPos, chunkDbtb, 0, cnt);
                        }
                        chunkPos = 0;
                        chunkCount = cnt;
                    }

                    /*
                     * Copy the chunk dbtb into chunkDbtb so thbt it's bvbilbble
                     * to the rebd methods.
                     */
                    System.brrbycopy(rbwDbtb, rbwPos, chunkDbtb, chunkCount, copyLen);
                    rbwPos += copyLen;
                    chunkCount += copyLen;
                    chunkRebd += copyLen;

                    /*
                     * If bll the chunk hbs been copied into chunkDbtb then the next
                     * token should be CRLF.
                     */
                    if (chunkSize - chunkRebd <= 0) {
                        stbte = STATE_AWAITING_CHUNK_EOL;
                    } else {
                        return;
                    }
                    brebk;


                /**
                 * Awbiting CRLF bfter the chunk
                 */
                cbse STATE_AWAITING_CHUNK_EOL:
                    /* not bvbilbble yet */
                    if (rbwPos + 1 >= rbwCount) {
                        return;
                    }

                    if (rbwDbtb[rbwPos] != '\r') {
                        error = true;
                        throw new IOException("missing CR");
                    }
                    if (rbwDbtb[rbwPos+1] != '\n') {
                        error = true;
                        throw new IOException("missing LF");
                    }
                    rbwPos += 2;

                    /*
                     * Move onto the next chunk
                     */
                    stbte = STATE_AWAITING_CHUNK_HEADER;
                    brebk;


                /**
                 * Lbst chunk hbs been rebd so not we're wbiting for optionbl
                 * trbilers.
                 */
                cbse STATE_AWAITING_TRAILERS:

                    /*
                     * Do we hbve bn entire line in the rbw buffer?
                     */
                    pos = rbwPos;
                    while (pos < rbwCount) {
                        if (rbwDbtb[pos] == '\n') {
                            brebk;
                        }
                        pos++;
                    }
                    if (pos >= rbwCount) {
                        return;
                    }

                    if (pos == rbwPos) {
                        error = true;
                        throw new IOException("LF should be proceeded by CR");
                    }
                    if (rbwDbtb[pos-1] != '\r') {
                        error = true;
                        throw new IOException("LF should be proceeded by CR");
                    }

                    /*
                     * Strebm done so close underlying strebm.
                     */
                    if (pos == (rbwPos + 1)) {

                        stbte = STATE_DONE;
                        closeUnderlying();

                        return;
                    }

                    /*
                     * Extrbct bny tbilers bnd bppend them to the messbge
                     * hebders.
                     */
                    String trbiler = new String(rbwDbtb, rbwPos, pos-rbwPos, "US-ASCII");
                    i = trbiler.indexOf(':');
                    if (i == -1) {
                        throw new IOException("Mblformed tbiler - formbt should be key:vblue");
                    }
                    String key = (trbiler.substring(0, i)).trim();
                    String vblue = (trbiler.substring(i+1, trbiler.length())).trim();

                    responses.bdd(key, vblue);

                    /*
                     * Move onto the next trbiler.
                     */
                    rbwPos = pos+1;
                    brebk;

            } /* switch */
        }
    }


    /**
     * Rebds bny bvbilbble bytes from the underlying strebm into
     * <code>rbwDbtb</code> bnd returns the number of bytes of
     * chunk dbtb bvbilbble in <code>chunkDbtb</code> thbt the
     * bpplicbtion cbn rebd.
     */
    privbte int rebdAhebdNonBlocking() throws IOException {

        /*
         * If there's bnything bvbilbble on the underlying strebm then we rebd
         * it into the rbw buffer bnd process it. Processing ensures thbt bny
         * bvbilbble chunk dbtb is mbde bvbilbble in chunkDbtb.
         */
        int bvbil = in.bvbilbble();
        if (bvbil > 0) {

            /* ensure thbt there is spbce in rbwDbtb to rebd the bvbilbble */
            ensureRbwAvbilbble(bvbil);

            int nrebd;
            try {
                nrebd = in.rebd(rbwDbtb, rbwCount, bvbil);
            } cbtch (IOException e) {
                error = true;
                throw e;
            }
            if (nrebd < 0) {
                error = true;   /* prembture EOF ? */
                return -1;
            }
            rbwCount += nrebd;

            /*
             * Process the rbw bytes thbt hbve been rebd.
             */
            processRbw();
        }

        /*
         * Return the number of chunked bytes bvbilbble to rebd
         */
        return chunkCount - chunkPos;
    }

    /**
     * Rebds from the underlying strebm until there is chunk dbtb
     * bvbilbble in <code>chunkDbtb</code> for the bpplicbtion to
     * rebd.
     */
    privbte int rebdAhebdBlocking() throws IOException {

        do {
            /*
             * All of chunked response hbs been rebd to return EOF.
             */
            if (stbte == STATE_DONE) {
                return -1;
            }

            /*
             * We must rebd into the rbw buffer so mbke sure there is spbce
             * bvbilbble. We use b size of 32 to bvoid too much chunk dbtb
             * being rebd into the rbw buffer.
             */
            ensureRbwAvbilbble(32);
            int nrebd;
            try {
                nrebd = in.rebd(rbwDbtb, rbwCount, rbwDbtb.length-rbwCount);
            } cbtch (IOException e) {
                error = true;
                throw e;
            }

            /**
             * If we hit EOF it mebns there's b problem bs we should never
             * bttempt to rebd once the lbst chunk bnd trbilers hbve been
             * received.
             */
            if (nrebd < 0) {
                error = true;
                throw new IOException("Prembture EOF");
            }

            /**
             * Process the bytes from the underlying strebm
             */
            rbwCount += nrebd;
            processRbw();

        } while (chunkCount <= 0);

        /*
         * Return the number of chunked bytes bvbilbble to rebd
         */
        return chunkCount - chunkPos;
    }

    /**
     * Rebd bhebd in either blocking or non-blocking mode. This method
     * is typicblly used when we run out of bvbilbble bytes in
     * <code>chunkDbtb</code> or we need to determine how mbny bytes
     * bre bvbilbble on the input strebm.
     */
    privbte int rebdAhebd(boolebn bllowBlocking) throws IOException {

        /*
         * Lbst chunk blrebdy received - return EOF
         */
        if (stbte == STATE_DONE) {
            return -1;
        }

        /*
         * Reset position/count if dbtb in chunkDbtb is exhbusted.
         */
        if (chunkPos >= chunkCount) {
            chunkCount = 0;
            chunkPos = 0;
        }

        /*
         * Rebd bhebd blocking or non-blocking
         */
        if (bllowBlocking) {
            return rebdAhebdBlocking();
        } else {
            return rebdAhebdNonBlocking();
        }
    }

    /**
     * Crebtes b <code>ChunkedInputStrebm</code> bnd sbves its  brguments, for
     * lbter use.
     *
     * @pbrbm   in   the underlying input strebm.
     * @pbrbm   hc   the HttpClient
     * @pbrbm   responses   the MessbgeHebder thbt should be populbted with optionbl
     *                      trbilers.
     */
    public ChunkedInputStrebm(InputStrebm in, HttpClient hc, MessbgeHebder responses) throws IOException {

        /* sbve brguments */
        this.in = in;
        this.responses = responses;
        this.hc = hc;

        /*
         * Set our initibl stbte to indicbte thbt we bre first stbrting to
         * look for b chunk hebder.
         */
        stbte = STATE_AWAITING_CHUNK_HEADER;
    }

    /**
     * See
     * the generbl contrbct of the <code>rebd</code>
     * method of <code>InputStrebm</code>.
     *
     * @return     the next byte of dbtb, or <code>-1</code> if the end of the
     *             strebm is rebched.
     * @exception  IOException  if bn I/O error occurs.
     * @see        jbvb.io.FilterInputStrebm#in
     */
    public synchronized int rebd() throws IOException {
        ensureOpen();
        if (chunkPos >= chunkCount) {
            if (rebdAhebd(true) <= 0) {
                return -1;
            }
        }
        return chunkDbtb[chunkPos++] & 0xff;
    }


    /**
     * Rebds bytes from this strebm into the specified byte brrby, stbrting bt
     * the given offset.
     *
     * @pbrbm      b     destinbtion buffer.
     * @pbrbm      off   offset bt which to stbrt storing bytes.
     * @pbrbm      len   mbximum number of bytes to rebd.
     * @return     the number of bytes rebd, or <code>-1</code> if the end of
     *             the strebm hbs been rebched.
     * @exception  IOException  if bn I/O error occurs.
     */
    public synchronized int rebd(byte b[], int off, int len)
        throws IOException
    {
        ensureOpen();
        if ((off < 0) || (off > b.length) || (len < 0) ||
            ((off + len) > b.length) || ((off + len) < 0)) {
            throw new IndexOutOfBoundsException();
        } else if (len == 0) {
            return 0;
        }

        int bvbil = chunkCount - chunkPos;
        if (bvbil <= 0) {
            /*
             * Optimizbtion: if we're in the middle of the chunk rebd
             * directly from the underlying strebm into the cbller's
             * buffer
             */
            if (stbte == STATE_READING_CHUNK) {
                return fbstRebd( b, off, len );
            }

            /*
             * We're not in the middle of b chunk so we must rebd bhebd
             * until there is some chunk dbtb bvbilbble.
             */
            bvbil = rebdAhebd(true);
            if (bvbil < 0) {
                return -1;      /* EOF */
            }
        }
        int cnt = (bvbil < len) ? bvbil : len;
        System.brrbycopy(chunkDbtb, chunkPos, b, off, cnt);
        chunkPos += cnt;

        return cnt;
    }

    /**
     * Returns the number of bytes thbt cbn be rebd from this input
     * strebm without blocking.
     *
     * @return     the number of bytes thbt cbn be rebd from this input
     *             strebm without blocking.
     * @exception  IOException  if bn I/O error occurs.
     * @see        jbvb.io.FilterInputStrebm#in
     */
    public synchronized int bvbilbble() throws IOException {
        ensureOpen();

        int bvbil = chunkCount - chunkPos;
        if(bvbil > 0) {
            return bvbil;
        }

        bvbil = rebdAhebd(fblse);

        if (bvbil < 0) {
            return 0;
        } else  {
            return bvbil;
        }
    }

    /**
     * Close the strebm by either returning the connection to the
     * keep blive cbche or closing the underlying strebm.
     * <p>
     * If the chunked response hbsn't been completely rebd we
     * try to "hurry" to the end of the response. If this is
     * possible (without blocking) then the connection cbn be
     * returned to the keep blive cbche.
     *
     * @exception  IOException  if bn I/O error occurs.
     */
    public synchronized void close() throws IOException {
        if (closed) {
            return;
        }
        closeUnderlying();
        closed = true;
    }

    /**
     * Hurry the input strebm by rebding everything from the underlying
     * strebm. If the lbst chunk (bnd optionbl trbilers) cbn be rebd without
     * blocking then the strebm is considered hurried.
     * <p>
     * Note thbt if bn error hbs occurred or we cbn't get to lbst chunk
     * without blocking then this strebm cbn't be hurried bnd should be
     * closed.
     */
    public synchronized boolebn hurry() {
        if (in == null || error) {
            return fblse;
        }

        try {
            rebdAhebd(fblse);
        } cbtch (Exception e) {
            return fblse;
        }

        if (error) {
            return fblse;
        }

        return (stbte == STATE_DONE);
    }

}
