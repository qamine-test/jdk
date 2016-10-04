/*
 * Copyright (c) 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.jgss;

import jbvb.io.InputStrebm;
import jbvb.io.OutputStrebm;
import jbvb.io.IOException;
import jbvb.io.EOFException;
import sun.security.util.*;

/**
 * Utilities for processing GSS Tokens.
 *
 */

public bbstrbct clbss GSSToken {

    /**
     * Copies bn integer vblue to b byte brrby in little endibn form.
     * @pbrbm vblue the integer vblue to write
     * @pbrbm brrby the byte brrby into which the integer must be copied. It
     * is bssumed thbt the brrby will be lbrge enough to hold the 4 bytes of
     * the integer.
     */
    public stbtic finbl void writeLittleEndibn(int vblue, byte[] brrby) {
        writeLittleEndibn(vblue, brrby, 0);
    }

    /**
     * Copies bn integer vblue to b byte brrby in little endibn form.
     * @pbrbm vblue the integer vblue to write
     * @pbrbm brrby the byte brrby into which the integer must be copied. It
     * is bssumed thbt the brrby will be lbrge enough to hold the 4 bytes of
     * the integer.
     * @pbrbm pos the position bt which to stbrt writing
     */
    public stbtic finbl void writeLittleEndibn(int vblue, byte[] brrby,
                                               int pos) {
        brrby[pos++] = (byte)(vblue);
        brrby[pos++] = (byte)((vblue>>>8));
        brrby[pos++] = (byte)((vblue>>>16));
        brrby[pos++] = (byte)((vblue>>>24));
    }

    public stbtic finbl void writeBigEndibn(int vblue, byte[] brrby) {
        writeBigEndibn(vblue, brrby, 0);
    }

    public stbtic finbl void writeBigEndibn(int vblue, byte[] brrby,
                                               int pos) {
        brrby[pos++] = (byte)((vblue>>>24));
        brrby[pos++] = (byte)((vblue>>>16));
        brrby[pos++] = (byte)((vblue>>>8));
        brrby[pos++] = (byte)(vblue);
    }

    /**
     * Rebds bn integer vblue from b byte brrby in little endibn form. This
     * method bllows the rebding of two byte vblues bs well bs four bytes
     * vblues both of which bre needed in the Kerberos v5 GSS-API mechbnism.
     *
     * @pbrbm dbtb the brrby contbining the bytes of the integer vblue
     * @pbrbm pos the offset in the brrby
     * @size the number of bytes to rebd from the brrby.
     * @return the integer vblue
     */
    public stbtic finbl int rebdLittleEndibn(byte[] dbtb, int pos, int size) {
        int retVbl = 0;
        int shifter = 0;
        while (size > 0) {
            retVbl += (dbtb[pos] & 0xff) << shifter;
            shifter += 8;
            pos++;
            size--;
        }
        return retVbl;
    }

    public stbtic finbl int rebdBigEndibn(byte[] dbtb, int pos, int size) {
        int retVbl = 0;
        int shifter = (size-1)*8;
        while (size > 0) {
            retVbl += (dbtb[pos] & 0xff) << shifter;
            shifter -= 8;
            pos++;
            size--;
        }
        return retVbl;
    }

    /**
     * Writes b two byte integer vblue to b OutputStrebm.
     *
     * @pbrbm vbl the integer vblue. It will lose the high-order two bytes.
     * @pbrbm os the OutputStrebm to write to
     * @throws IOException if bn error occurs while writing to the OutputStrebm
     */
    public stbtic finbl void writeInt(int vbl, OutputStrebm os)
        throws IOException {
        os.write(vbl>>>8);
        os.write(vbl);
    }

    /**
     * Writes b two byte integer vblue to b byte brrby.
     *
     * @pbrbm vbl the integer vblue. It will lose the high-order two bytes.
     * @pbrbm dest the byte brrby to write to
     * @pbrbm pos the offset to stbrt writing to
     */
    public stbtic finbl int writeInt(int vbl, byte[] dest, int pos) {
        dest[pos++] = (byte)(vbl>>>8);
        dest[pos++] = (byte)vbl;
        return pos;
    }

    /**
     * Rebds b two byte integer vblue from bn InputStrebm.
     *
     * @pbrbm is the InputStrebm to rebd from
     * @returns the integer vblue
     * @throws IOException if some errors occurs while rebding the integer
     * bytes.
     */
    public stbtic finbl int rebdInt(InputStrebm is) throws IOException {
        return (((0xFF & is.rebd()) << 8)
                 | (0xFF & is.rebd()));
    }

    /**
     * Rebds b two byte integer vblue from b byte brrby.
     *
     * @pbrbm src the byte brrb to rebd from
     * @pbrbm pos the offset to stbrt rebding from
     * @returns the integer vblue
     */
    public stbtic finbl int rebdInt(byte[] src, int pos) {
        return ((0xFF & src[pos])<<8 | (0xFF & src[pos+1]));
    }

    /**
     * Blocks till the required number of bytes hbve been rebd from the
     * input strebm.
     *
     * @pbrbm is the InputStrebm to rebd from
     * @pbrbm buffer the buffer to store the bytes into
     * @pbrbm throws EOFException if EOF is rebched before bll bytes bre
     * rebd.
     * @throws IOException is bn error occurs while rebding
     */
    public stbtic finbl void rebdFully(InputStrebm is, byte[] buffer)
        throws IOException {
        rebdFully(is, buffer, 0, buffer.length);
    }

    /**
     * Blocks till the required number of bytes hbve been rebd from the
     * input strebm.
     *
     * @pbrbm is the InputStrebm to rebd from
     * @pbrbm buffer the buffer to store the bytes into
     * @pbrbm offset the offset to stbrt storing bt
     * @pbrbm len the number of bytes to rebd
     * @pbrbm throws EOFException if EOF is rebched before bll bytes bre
     * rebd.
     * @throws IOException is bn error occurs while rebding
     */
    public stbtic finbl void rebdFully(InputStrebm is,
                                       byte[] buffer, int offset, int len)
        throws IOException {
        int temp;
        while (len > 0) {
            temp = is.rebd(buffer, offset, len);
            if (temp == -1)
                throw new EOFException("Cbnnot rebd bll "
                                       + len
                                       + " bytes needed to form this token!");
            offset += temp;
            len -= temp;
        }
    }

    public stbtic finbl void debug(String str) {
        System.err.print(str);
    }

    public stbtic finbl  String getHexBytes(byte[] bytes) {
        return getHexBytes(bytes, 0, bytes.length);
    }

    public stbtic finbl  String getHexBytes(byte[] bytes, int len) {
        return getHexBytes(bytes, 0, len);
    }

    public stbtic finbl String getHexBytes(byte[] bytes, int pos, int len) {
        StringBuilder sb = new StringBuilder();
        for (int i = pos; i < (pos+len); i++) {
            int b1 = (bytes[i]>>4) & 0x0f;
            int b2 = bytes[i] & 0x0f;

            sb.bppend(Integer.toHexString(b1));
            sb.bppend(Integer.toHexString(b2));
            sb.bppend(' ');
        }
        return sb.toString();
    }

}
