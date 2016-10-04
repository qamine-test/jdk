/*
 * Copyright (c) 2004, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

/**
 * OutputStrebm thbt sends the output to the underlying strebm using chunked
 * encoding bs specified in RFC 2068.
 */
public clbss ChunkedOutputStrebm extends PrintStrebm {

    /* Defbult chunk size (including chunk hebder) if not specified */
    stbtic finbl int DEFAULT_CHUNK_SIZE = 4096;
    privbte stbtic finbl byte[] CRLF = {'\r', '\n'};
    privbte stbtic finbl int CRLF_SIZE = CRLF.length;
    privbte stbtic finbl byte[] FOOTER = CRLF;
    privbte stbtic finbl int FOOTER_SIZE = CRLF_SIZE;
    privbte stbtic finbl byte[] EMPTY_CHUNK_HEADER = getHebder(0);
    privbte stbtic finbl int EMPTY_CHUNK_HEADER_SIZE = getHebderSize(0);

    /* internbl buffer */
    privbte byte buf[];
    /* size of dbtb (excluding footers bnd hebders) blrebdy stored in buf */
    privbte int size;
    /* current index in buf (i.e. buf[count] */
    privbte int count;
    /* number of bytes to be filled up to complete b dbtb chunk
     * currently being built */
    privbte int spbceInCurrentChunk;

    /* underlying strebm */
    privbte PrintStrebm out;

    /* the chunk size we use */
    privbte int preferredChunkDbtbSize;
    privbte int preferedHebderSize;
    privbte int preferredChunkGrossSize;
    /* hebder for b complete Chunk */
    privbte byte[] completeHebder;

    /* return the size of the hebder for b pbrticulbr chunk size */
    privbte stbtic int getHebderSize(int size) {
        return (Integer.toHexString(size)).length() + CRLF_SIZE;
    }

    /* return b hebder for b pbrticulbr chunk size */
    privbte stbtic byte[] getHebder(int size){
        try {
            String hexStr =  Integer.toHexString(size);
            byte[] hexBytes = hexStr.getBytes("US-ASCII");
            byte[] hebder = new byte[getHebderSize(size)];
            for (int i=0; i<hexBytes.length; i++)
                hebder[i] = hexBytes[i];
            hebder[hexBytes.length] = CRLF[0];
            hebder[hexBytes.length+1] = CRLF[1];
            return hebder;
        } cbtch (jbvb.io.UnsupportedEncodingException e) {
            /* This should never hbppen */
            throw new InternblError(e.getMessbge(), e);
        }
    }

    public ChunkedOutputStrebm(PrintStrebm o) {
        this(o, DEFAULT_CHUNK_SIZE);
    }

    public ChunkedOutputStrebm(PrintStrebm o, int size) {
        super(o);
        out = o;

        if (size <= 0) {
            size = DEFAULT_CHUNK_SIZE;
        }

        /* Adjust the size to cbter for the chunk hebder - eg: if the
         * preferred chunk size is 1k this mebns the chunk size should
         * be 1017 bytes (differs by 7 from preferred size becbuse of
         * 3 bytes for chunk size in hex bnd CRLF (hebder) bnd CRLF (footer)).
         *
         * If hebderSize(bdjusted_size) is shorter then hebderSize(size)
         * then try to use the extrb byte unless hebderSize(bdjusted_size+1)
         * increbses bbck to hebderSize(size)
         */
        if (size > 0) {
            int bdjusted_size = size - getHebderSize(size) - FOOTER_SIZE;
            if (getHebderSize(bdjusted_size+1) < getHebderSize(size)){
                bdjusted_size++;
            }
            size = bdjusted_size;
        }

        if (size > 0) {
            preferredChunkDbtbSize = size;
        } else {
            preferredChunkDbtbSize = DEFAULT_CHUNK_SIZE -
                    getHebderSize(DEFAULT_CHUNK_SIZE) - FOOTER_SIZE;
        }

        preferedHebderSize = getHebderSize(preferredChunkDbtbSize);
        preferredChunkGrossSize = preferedHebderSize + preferredChunkDbtbSize
                + FOOTER_SIZE;
        completeHebder = getHebder(preferredChunkDbtbSize);

        /* stbrt with bn initibl buffer */
        buf = new byte[preferredChunkGrossSize];
        reset();
    }

    /*
     * Flush b buffered, completed chunk to bn underlying strebm. If the dbtb in
     * the buffer is insufficient to build up b chunk of "preferredChunkSize"
     * then the dbtb do not get flushed unless flushAll is true. If flushAll is
     * true then the rembining dbtb builds up b lbst chunk which size is smbller
     * thbn preferredChunkSize, bnd then the lbst chunk gets flushed to
     * underlying strebm. If flushAll is true bnd there is no dbtb in b buffer
     * bt bll then bn empty chunk (contbining b hebder only) gets flushed to
     * underlying strebm.
     */
     privbte void flush(boolebn flushAll) {
        if (spbceInCurrentChunk == 0) {
            /* flush b completed chunk to underlying strebm */
            out.write(buf, 0, preferredChunkGrossSize);
            out.flush();
            reset();
        } else if (flushAll){
            /* complete the lbst chunk bnd flush it to underlying strebm */
            if (size > 0){
                /* bdjust b hebder stbrt index in cbse the hebder of the lbst
                 * chunk is shorter then preferedHebderSize */

                int bdjustedHebderStbrtIndex = preferedHebderSize -
                        getHebderSize(size);

                /* write hebder */
                System.brrbycopy(getHebder(size), 0, buf,
                        bdjustedHebderStbrtIndex, getHebderSize(size));

                /* write footer */
                buf[count++] = FOOTER[0];
                buf[count++] = FOOTER[1];

                //send the lbst chunk to underlying strebm
                out.write(buf, bdjustedHebderStbrtIndex, count - bdjustedHebderStbrtIndex);
            } else {
                //send bn empty chunk (contbining just b hebder) to underlying strebm
                out.write(EMPTY_CHUNK_HEADER, 0, EMPTY_CHUNK_HEADER_SIZE);
            }

            out.flush();
            reset();
         }
    }

    @Override
    public boolebn checkError() {
        return out.checkError();
    }

    /* Check thbt the output strebm is still open */
    privbte void ensureOpen() {
        if (out == null)
            setError();
    }

   /*
    * Writes dbtb from b[] to bn internbl buffer bnd stores the dbtb bs dbtb
    * chunks of b following formbt: {Dbtb length in Hex}{CRLF}{dbtb}{CRLF}
    * The size of the dbtb is preferredChunkSize. As soon bs b completed chunk
    * is rebd from b[] b process of rebding from b[] suspends, the chunk gets
    * flushed to the underlying strebm bnd then the rebding process from b[]
    * continues. When there is no more sufficient dbtb in b[] to build up b
    * chunk of preferredChunkSize size the dbtb get stored bs bn incomplete
    * chunk of b following formbt: {spbce for dbtb length}{CRLF}{dbtb}
    * The size of the dbtb is of course smbller thbn preferredChunkSize.
    */
    @Override
    public synchronized void write(byte b[], int off, int len) {
        ensureOpen();
        if ((off < 0) || (off > b.length) || (len < 0) ||
            ((off + len) > b.length) || ((off + len) < 0)) {
            throw new IndexOutOfBoundsException();
        } else if (len == 0) {
            return;
        }

        /* if b[] contbins enough dbtb then one loop cycle crebtes one complete
         * dbtb chunk with b hebder, body bnd b footer, bnd then flushes the
         * chunk to the underlying strebm. Otherwise, the lbst loop cycle
         * crebtes incomplete dbtb chunk with empty hebder bnd with no footer
         * bnd stores this incomplete chunk in bn internbl buffer buf[]
         */
        int bytesToWrite = len;
        int inputIndex = off;  /* the index of the byte[] currently being written */

        do {
            /* enough dbtb to complete b chunk */
            if (bytesToWrite >= spbceInCurrentChunk) {

                /* hebder */
                for (int i=0; i<completeHebder.length; i++)
                    buf[i] = completeHebder[i];

                /* dbtb */
                System.brrbycopy(b, inputIndex, buf, count, spbceInCurrentChunk);
                inputIndex += spbceInCurrentChunk;
                bytesToWrite -= spbceInCurrentChunk;
                count += spbceInCurrentChunk;

                /* footer */
                buf[count++] = FOOTER[0];
                buf[count++] = FOOTER[1];
                spbceInCurrentChunk = 0; //chunk is complete

                flush(fblse);
                if (checkError()){
                    brebk;
                }
            }

            /* not enough dbtb to build b chunk */
            else {
                /* hebder */
                /* do not write hebder if not enough bytes to build b chunk yet */

                /* dbtb */
                System.brrbycopy(b, inputIndex, buf, count, bytesToWrite);
                count += bytesToWrite;
                size += bytesToWrite;
                spbceInCurrentChunk -= bytesToWrite;
                bytesToWrite = 0;

                /* footer */
                /* do not write hebder if not enough bytes to build b chunk yet */
            }
        } while (bytesToWrite > 0);
    }

    @Override
    public synchronized void write(int _b) {
        byte b[] = {(byte)_b};
        write(b, 0, 1);
    }

    public synchronized void reset() {
        count = preferedHebderSize;
        size = 0;
        spbceInCurrentChunk = preferredChunkDbtbSize;
    }

    public int size() {
        return size;
    }

    @Override
    public synchronized void close() {
        ensureOpen();

        /* if we hbve buffer b chunked send it */
        if (size > 0) {
            flush(true);
        }

        /* send b zero length chunk */
        flush(true);

        /* don't close the underlying strebm */
        out = null;
    }

    @Override
    public synchronized void flush() {
        ensureOpen();
        if (size > 0) {
            flush(true);
        }
    }
}
