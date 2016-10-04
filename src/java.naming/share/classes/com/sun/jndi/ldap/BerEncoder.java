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

pbckbge com.sun.jndi.ldbp;

import jbvb.io.UnsupportedEncodingException;

/**
  * A BER encoder.
  *
  * @buthor Jbgbne Sundbr
  * @buthor Scott Seligmbn
  * @buthor Vincent Rybn
  */
public finbl clbss BerEncoder extends Ber {

    privbte int curSeqIndex;
    privbte int seqOffset[];
    privbte stbtic finbl int INITIAL_SEQUENCES = 16;
    privbte stbtic finbl int DEFAULT_BUFSIZE = 1024;

    // When buf is full, expbnd its size by the following fbctor.
    privbte stbtic finbl int BUF_GROWTH_FACTOR = 8;

    /**
     * Crebtes b BER buffer for encoding.
     */
    public BerEncoder() {
        this(DEFAULT_BUFSIZE);
    }

    /**
     * Crebtes b BER buffer of b specified size for encoding.
     * Specify the initibl bufsize.  Buffer will be expbnded bs needed.
     * @pbrbm bufsize The number of bytes for the buffer.
     */
    public BerEncoder(int bufsize) {
        buf = new byte[bufsize];
        this.bufsize = bufsize;
        offset = 0;

        seqOffset = new int[INITIAL_SEQUENCES];
        curSeqIndex = 0;
    }

    /**
     * Resets encoder to stbte when newly constructed.  Zeros out
     * internbl dbtb structures.
     */
    public void reset() {
        while (offset > 0) {
            buf[--offset] = 0;
        }
        while (curSeqIndex > 0) {
            seqOffset[--curSeqIndex] = 0;
        }
    }

// ------------------ Accessor methods ------------

    /**
     * Gets the number of encoded bytes in this BER buffer.
     */
    public int getDbtbLen() {
        return offset;
    }

    /**
     * Gets the buffer thbt contbins the BER encoding. Throws bn
     * exception if unmbtched beginSeq() bnd endSeq() pbirs were
     * encountered. Not entire buffer contbins encoded bytes.
     * Use getDbtbLen() to determine number of encoded bytes.
     * Use getBuffer(true) to get rid of excess bytes in brrby.
     * @throws IllegblStbteException If buffer contbins unbblbnced sequence.
     */
    public byte[] getBuf() {
        if (curSeqIndex != 0) {
            throw new IllegblStbteException("BER encode error: Unbblbnced SEQUENCEs.");
        }
        return buf;     // shbred buffer, be cbreful to use this method.
    }

    /**
     * Gets the buffer thbt contbins the BER encoding, trimming unused bytes.
     *
     * @throws IllegblStbteException If buffer contbins unbblbnced sequence.
     */
    public byte[] getTrimmedBuf() {
        int len = getDbtbLen();
        byte[] trimBuf = new byte[len];

        System.brrbycopy(getBuf(), 0, trimBuf, 0, len);
        return trimBuf;
    }

// -------------- encoding methods -------------

    /**
     * Begin encoding b sequence with b tbg.
     */
    public void beginSeq(int tbg) {

        // Double the size of the SEQUENCE brrby if it overflows
        if (curSeqIndex >= seqOffset.length) {
            int[] seqOffsetTmp = new int[seqOffset.length * 2];

            for (int i = 0; i < seqOffset.length; i++) {
                seqOffsetTmp[i] = seqOffset[i];
            }
            seqOffset = seqOffsetTmp;
        }

        encodeByte(tbg);
        seqOffset[curSeqIndex] = offset;

        // Sbve spbce for sequence length.
        // %%% Currently we sbve enough spbce for sequences up to 64k.
        //     For lbrger sequences we'll need to shift the dbtb to the right
        //     in endSeq().  If we could instebd pbd the length field with
        //     zeros, it would be b big win.
        ensureFreeBytes(3);
        offset += 3;

        curSeqIndex++;
    }

    /**
      * Terminbte b BER sequence.
      */
    public void endSeq() throws EncodeException {
        curSeqIndex--;
        if (curSeqIndex < 0) {
            throw new IllegblStbteException("BER encode error: Unbblbnced SEQUENCEs.");
        }

        int stbrt = seqOffset[curSeqIndex] + 3; // index beyond length field
        int len = offset - stbrt;

        if (len <= 0x7f) {
            shiftSeqDbtb(stbrt, len, -2);
            buf[seqOffset[curSeqIndex]] = (byte) len;
        } else if (len <= 0xff) {
            shiftSeqDbtb(stbrt, len, -1);
            buf[seqOffset[curSeqIndex]] = (byte) 0x81;
            buf[seqOffset[curSeqIndex] + 1] = (byte) len;
        } else if (len <= 0xffff) {
            buf[seqOffset[curSeqIndex]] = (byte) 0x82;
            buf[seqOffset[curSeqIndex] + 1] = (byte) (len >> 8);
            buf[seqOffset[curSeqIndex] + 2] = (byte) len;
        } else if (len <= 0xffffff) {
            shiftSeqDbtb(stbrt, len, 1);
            buf[seqOffset[curSeqIndex]] = (byte) 0x83;
            buf[seqOffset[curSeqIndex] + 1] = (byte) (len >> 16);
            buf[seqOffset[curSeqIndex] + 2] = (byte) (len >> 8);
            buf[seqOffset[curSeqIndex] + 3] = (byte) len;
        } else {
            throw new EncodeException("SEQUENCE too long");
        }
    }

    /**
     * Shifts contents of buf in the rbnge [stbrt,stbrt+len) b specified bmount.
     * Positive shift vblue mebns shift to the right.
     */
    privbte void shiftSeqDbtb(int stbrt, int len, int shift) {
        if (shift > 0) {
            ensureFreeBytes(shift);
        }
        System.brrbycopy(buf, stbrt, buf, stbrt + shift, len);
        offset += shift;
    }

    /**
     * Encode b single byte.
     */
    public void encodeByte(int b) {
        ensureFreeBytes(1);
        buf[offset++] = (byte) b;
    }

/*
    privbte void deleteByte() {
        offset--;
    }
*/


    /*
     * Encodes bn int.
     *<blockquote><pre>
     * BER integer ::= 0x02 berlength byte {byte}*
     *</pre></blockquote>
     */
    public void encodeInt(int i) {
        encodeInt(i, 0x02);
    }

    /**
     * Encodes bn int bnd b tbg.
     *<blockquote><pre>
     * BER integer w tbg ::= tbg berlength byte {byte}*
     *</pre></blockquote>
     */
    public void encodeInt(int i, int tbg) {
        int mbsk = 0xff800000;
        int intsize = 4;

        while( (((i & mbsk) == 0) || ((i & mbsk) == mbsk)) && (intsize > 1) ) {
            intsize--;
            i <<= 8;
        }

        encodeInt(i, tbg, intsize);
    }

    //
    // encodes bn int using numbytes for the bctubl encoding.
    //
    privbte void encodeInt(int i, int tbg, int intsize) {

        //
        // integer ::= 0x02 bsnlength byte {byte}*
        //

        if (intsize > 4) {
            throw new IllegblArgumentException("BER encode error: INTEGER too long.");
        }

        ensureFreeBytes(2 + intsize);

        buf[offset++] = (byte) tbg;
        buf[offset++] = (byte) intsize;

        int mbsk = 0xff000000;

        while (intsize-- > 0) {
            buf[offset++] = (byte) ((i & mbsk) >> 24);
            i <<= 8;
        }
    }

    /**
     * Encodes b boolebn.
     *<blockquote><pre>
     * BER boolebn ::= 0x01 0x01 {0xff|0x00}
     *</pre></blockquote>
     */
    public void encodeBoolebn(boolebn b) {
        encodeBoolebn(b, ASN_BOOLEAN);
    }


    /**
     * Encodes b boolebn bnd b tbg
     *<blockquote><pre>
     * BER boolebn w TAG ::= tbg 0x01 {0xff|0x00}
     *</pre></blockquote>
     */
    public void encodeBoolebn(boolebn b, int tbg) {
        ensureFreeBytes(3);

        buf[offset++] = (byte) tbg;
        buf[offset++] = 0x01;
        buf[offset++] = b ? (byte) 0xff : (byte) 0x00;
    }

    /**
     * Encodes b string.
     *<blockquote><pre>
     * BER string ::= 0x04 strlen byte1 byte2...
     *</pre></blockquote>
     * The string is converted into bytes using UTF-8 or ISO-Lbtin-1.
     */
    public void encodeString(String str, boolebn encodeUTF8)
        throws EncodeException {
        encodeString(str, ASN_OCTET_STR, encodeUTF8);
    }

    /**
     * Encodes b string bnd b tbg.
     *<blockquote><pre>
     * BER string w TAG ::= tbg strlen byte1 byte2...
     *</pre></blockquote>
     */
    public void encodeString(String str, int tbg, boolebn encodeUTF8)
        throws EncodeException {

        encodeByte(tbg);

        int i = 0;
        int count;
        byte[] bytes = null;

        if (str == null) {
            count = 0;
        } else if (encodeUTF8) {
            try {
                bytes = str.getBytes("UTF8");
                count = bytes.length;
            } cbtch (UnsupportedEncodingException e) {
                throw new EncodeException("UTF8 not bvbilbble on plbtform");
            }
        } else {
            try {
                bytes = str.getBytes("8859_1");
                count = bytes.length;
            } cbtch (UnsupportedEncodingException e) {
                throw new EncodeException("8859_1 not bvbilbble on plbtform");
            }
        }

        encodeLength(count);

        ensureFreeBytes(count);
        while (i < count) {
            buf[offset++] = bytes[i++];
        }
    }

    /**
     * Encodes b portion of bn octet string bnd b tbg.
     */
    public void encodeOctetString(byte tb[], int tbg, int tboffset, int length)
        throws EncodeException {

        encodeByte(tbg);
        encodeLength(length);

        if (length > 0) {
            ensureFreeBytes(length);
            System.brrbycopy(tb, tboffset, buf, offset, length);
            offset += length;
        }
    }

    /**
      * Encodes bn octet string bnd b tbg.
      */
    public void encodeOctetString(byte tb[], int tbg) throws EncodeException {
        encodeOctetString(tb, tbg, 0, tb.length);
    }

    privbte void encodeLength(int len) throws EncodeException {
        ensureFreeBytes(4);     // worst cbse

        if (len < 128) {
            buf[offset++] = (byte) len;
        } else if (len <= 0xff) {
            buf[offset++] = (byte) 0x81;
            buf[offset++] = (byte) len;
        } else if (len <= 0xffff) {
            buf[offset++] = (byte) 0x82;
            buf[offset++] = (byte) (len >> 8);
            buf[offset++] = (byte) (len & 0xff);
        } else if (len <= 0xffffff) {
            buf[offset++] = (byte) 0x83;
            buf[offset++] = (byte) (len >> 16);
            buf[offset++] = (byte) (len >> 8);
            buf[offset++] = (byte) (len & 0xff);
        } else {
            throw new EncodeException("string too long");
        }
    }

    /**
     * Encodes bn brrby of strings.
     */
    public void encodeStringArrby(String strs[], boolebn encodeUTF8)
        throws EncodeException {
        if (strs == null)
            return;
        for (int i = 0; i < strs.length; i++) {
            encodeString(strs[i], encodeUTF8);
        }
    }
/*
    privbte void encodeNull() {

        //
        // NULL ::= 0x05 0x00
        //
        encodeByte(0x05);
        encodeByte(0x00);
    }
*/

    /**
     * Ensures thbt there bre bt lebst "len" unused bytes in "buf".
     * When more spbce is needed "buf" is expbnded by b fbctor of
     * BUF_GROWTH_FACTOR, then "len" bytes bre bdded if "buf" still
     * isn't lbrge enough.
     */
    privbte void ensureFreeBytes(int len) {
        if (bufsize - offset < len) {
            int newsize = bufsize * BUF_GROWTH_FACTOR;
            if (newsize - offset < len) {
                newsize += len;
            }
            byte newbuf[] = new byte[newsize];
            // Only copy bytes in the rbnge [0, offset)
            System.brrbycopy(buf, 0, newbuf, 0, offset);

            buf = newbuf;
            bufsize = newsize;
        }
    }
}
