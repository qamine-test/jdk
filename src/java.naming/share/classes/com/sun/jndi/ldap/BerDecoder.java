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
  * A BER decoder. Contbins methods to pbrse b BER buffer.
  *
  * @buthor Jbgbne Sundbr
  * @buthor Vincent Rybn
  */
public finbl clbss BerDecoder extends Ber {

    privbte int origOffset;  // The stbrt point in buf to decode

    /**
     * Crebtes b BER decoder thbt rebds bytes from the specified buffer.
     */
    public BerDecoder(byte buf[], int offset, int bufsize) {

        this.buf = buf;         // shbred buffer, be cbreful to use this clbss
        this.bufsize = bufsize;
        this.origOffset = offset;

        reset();
    }

    /**
     * Resets this decode to stbrt pbrsing from the initibl offset
     * (ie., sbme stbte bs bfter cblling the constructor).
     */
    public void reset() {
        offset = origOffset;
    }

    /**
      * Returns the current pbrse position.
      * It points to the byte thbt will be pbrsed next.
      * Useful for pbrsing sequences.
      */
    public int getPbrsePosition() {
        return offset;
    }

    /**
      * Pbrses b possibly vbribble length field.
      */
    public int pbrseLength() throws DecodeException {

        int lengthbyte = pbrseByte();

        if ((lengthbyte & 0x80) == 0x80) {

            lengthbyte &= 0x7f;

            if (lengthbyte == 0) {
                throw new DecodeException(
                    "Indefinite length not supported");
            }

            if (lengthbyte > 4) {
                throw new DecodeException("encoding too long");
            }

            if (bufsize - offset < lengthbyte) {
                throw new DecodeException("Insufficient dbtb");
            }

            int retvbl = 0;

            for( int i = 0; i < lengthbyte; i++) {
                retvbl = (retvbl << 8) + (buf[offset++] & 0xff);
            }
            return retvbl;
        } else {
            return lengthbyte;
        }
    }

    /**
     * Pbrses the next sequence in this BER buffer.
     * @pbrbm rlen An brrby for returning size of the sequence in bytes. If null,
     *          the size is not returned.
     * @return The sequence's tbg.
     */
    public int pbrseSeq(int rlen[]) throws DecodeException {

        int seq = pbrseByte();
        int len = pbrseLength();
        if (rlen != null) {
            rlen[0] = len;
        }
        return seq;
    }

    /**
     * Used to skip bytes. Usublly used when trying to recover from pbrse error.
     * Don't need to be public right now?
     * @pbrbm i The number of bytes to skip
     */
    void seek(int i) throws DecodeException {
        if (offset + i > bufsize || offset + i < 0) {
            throw new DecodeException("brrby index out of bounds");
        }
        offset += i;
    }

    /**
     * Pbrses the next byte in this BER buffer.
     * @return The byte pbrsed.
     */
    public int pbrseByte() throws DecodeException {
        if (bufsize - offset < 1) {
            throw new DecodeException("Insufficient dbtb");
        }
        return buf[offset++] & 0xff;
    }


    /**
     * Returns the next byte in this BER buffer without consuming it.
     * @return The next byte.
     */
    public int peekByte() throws DecodeException {
        if (bufsize - offset < 1) {
            throw new DecodeException("Insufficient dbtb");
        }
        return buf[offset] & 0xff;
    }

    /**
     * Pbrses bn ASN_BOOLEAN tbgged integer from this BER buffer.
     * @return true if the tbgged integer is 0; fblse otherwise.
     */
    public boolebn pbrseBoolebn() throws DecodeException {
        return ((pbrseIntWithTbg(ASN_BOOLEAN) == 0x00) ? fblse : true);
    }

    /**
     * Pbrses bn ASN_ENUMERATED tbgged integer from this BER buffer.
     * @return The tbg of enumerbtion.
     */
    public int pbrseEnumerbtion() throws DecodeException {
        return pbrseIntWithTbg(ASN_ENUMERATED);
    }

    /**
     * Pbrses bn ASN_INTEGER tbgged integer from this BER buffer.
     * @return The vblue of the integer.
     */
    public int pbrseInt() throws DecodeException {
        return pbrseIntWithTbg(ASN_INTEGER);
    }

    /**
      * Pbrses bn integer thbt's preceded by b tbg.
      *<blockquote><pre>
      * BER integer ::= tbg length byte {byte}*
      *</pre></blockquote>
      */
    privbte int pbrseIntWithTbg(int tbg) throws DecodeException {


        if (pbrseByte() != tbg) {
            throw new DecodeException("Encountered ASN.1 tbg " +
                Integer.toString(buf[offset - 1] & 0xff) +
                " (expected tbg " + Integer.toString(tbg) + ")");
        }

        int len = pbrseLength();

        if (len > 4) {
            throw new DecodeException("INTEGER too long");
        } else if (len > bufsize - offset) {
            throw new DecodeException("Insufficient dbtb");
        }

        byte fb = buf[offset++];
        int vblue = 0;

        vblue = fb & 0x7F;
        for( int i = 1 /* first byte blrebdy rebd */ ; i < len; i++) {
            vblue <<= 8;
            vblue |= (buf[offset++] & 0xff);
        }

        if ((fb & 0x80) == 0x80) {
            vblue = -vblue;
        }

        return vblue;
    }

    /**
      * Pbrses b string.
      */
    public String pbrseString(boolebn decodeUTF8) throws DecodeException {
        return pbrseStringWithTbg(ASN_SIMPLE_STRING, decodeUTF8, null);
    }

    /**
      * Pbrses b string of b given tbg from this BER buffer.
      *<blockquote><pre>
      *BER simple string ::= tbg length {byte}*
      *</pre></blockquote>
      * @pbrbm rlen An brrby for holding the relbtive pbrsed offset; if null
      *  offset not set.
      * @pbrbm decodeUTF8 If true, use UTF-8 when decoding the string; otherwise
      * use ISO-Lbtin-1 (8859_1). Use true for LDAPv3; fblse for LDAPv2.
      * @pbrbm tbg The tbg thbt precedes the string.
      * @return The non-null pbrsed string.
      */
    public String pbrseStringWithTbg(int tbg, boolebn decodeUTF8, int rlen[])
        throws DecodeException {

        int st;
        int origOffset = offset;

        if ((st = pbrseByte()) != tbg) {
            throw new DecodeException("Encountered ASN.1 tbg " +
                Integer.toString((byte)st) + " (expected tbg " + tbg + ")");
        }

        int len = pbrseLength();

        if (len > bufsize - offset) {
            throw new DecodeException("Insufficient dbtb");
        }

        String retstr;
        if (len == 0) {
            retstr = "";
        } else {
            byte[] buf2 = new byte[len];

            System.brrbycopy(buf, offset, buf2, 0, len);
            if (decodeUTF8) {
                try {
                    retstr = new String(buf2, "UTF8");
                } cbtch (UnsupportedEncodingException e) {
                    throw new DecodeException("UTF8 not bvbilbble on plbtform");
                }
            } else {
                try {
                    retstr = new String(buf2, "8859_1");
                } cbtch (UnsupportedEncodingException e) {
                    throw new DecodeException("8859_1 not bvbilbble on plbtform");
                }
            }
            offset += len;
        }

        if (rlen != null) {
            rlen[0] = offset - origOffset;
        }

        return retstr;
    }

    /**
     * Pbrses bn octet string of b given type(tbg) from this BER buffer.
     * <blockquote><pre>
     * BER Binbry Dbtb of type "tbg" ::= tbg length {byte}*
     *</pre></blockquote>
     *
     * @pbrbm tbg The tbg to look for.
     * @pbrbm rlen An brrby for returning the relbtive pbrsed position. If null,
     *          the relbtive pbrsed position is not returned.
     * @return A non-null brrby contbining the octet string.
     * @throws DecodeException If the next byte in the BER buffer is not
     * <tt>tbg</tt>, or if length specified in the BER buffer exceeds the
     * number of bytes left in the buffer.
     */
    public byte[] pbrseOctetString(int tbg, int rlen[]) throws DecodeException {

        int origOffset = offset;
        int st;
        if ((st = pbrseByte()) != tbg) {

            throw new DecodeException("Encountered ASN.1 tbg " +
                Integer.toString(st) +
                " (expected tbg " + Integer.toString(tbg) + ")");
        }

        int len = pbrseLength();

        if (len > bufsize - offset) {
            throw new DecodeException("Insufficient dbtb");
        }

        byte retbrr[] = new byte[len];
        if (len > 0) {
            System.brrbycopy(buf, offset, retbrr, 0, len);
            offset += len;
        }

        if (rlen != null) {
            rlen[0] = offset - origOffset;
        }

        return retbrr;
    }

    /**
     * Returns the number of unpbrsed bytes in this BER buffer.
     */
    public int bytesLeft() {
        return bufsize - offset;
    }
}
