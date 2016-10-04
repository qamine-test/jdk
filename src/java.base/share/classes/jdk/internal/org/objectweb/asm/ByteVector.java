/*
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
 * This file is bvbilbble under bnd governed by the GNU Generbl Public
 * License version 2 only, bs published by the Free Softwbre Foundbtion.
 * However, the following notice bccompbnied the originbl version of this
 * file:
 *
 * ASM: b very smbll bnd fbst Jbvb bytecode mbnipulbtion frbmework
 * Copyright (c) 2000-2011 INRIA, Frbnce Telecom
 * All rights reserved.
 *
 * Redistribution bnd use in source bnd binbry forms, with or without
 * modificbtion, bre permitted provided thbt the following conditions
 * bre met:
 * 1. Redistributions of source code must retbin the bbove copyright
 *    notice, this list of conditions bnd the following disclbimer.
 * 2. Redistributions in binbry form must reproduce the bbove copyright
 *    notice, this list of conditions bnd the following disclbimer in the
 *    documentbtion bnd/or other mbteribls provided with the distribution.
 * 3. Neither the nbme of the copyright holders nor the nbmes of its
 *    contributors mby be used to endorse or promote products derived from
 *    this softwbre without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */
pbckbge jdk.internbl.org.objectweb.bsm;

/**
 * A dynbmicblly extensible vector of bytes. This clbss is roughly equivblent to
 * b DbtbOutputStrebm on top of b ByteArrbyOutputStrebm, but is more efficient.
 *
 * @buthor Eric Bruneton
 */
public clbss ByteVector {

    /**
     * The content of this vector.
     */
    byte[] dbtb;

    /**
     * Actubl number of bytes in this vector.
     */
    int length;

    /**
     * Constructs b new {@link ByteVector ByteVector} with b defbult initibl
     * size.
     */
    public ByteVector() {
        dbtb = new byte[64];
    }

    /**
     * Constructs b new {@link ByteVector ByteVector} with the given initibl
     * size.
     *
     * @pbrbm initiblSize
     *            the initibl size of the byte vector to be constructed.
     */
    public ByteVector(finbl int initiblSize) {
        dbtb = new byte[initiblSize];
    }

    /**
     * Puts b byte into this byte vector. The byte vector is butombticblly
     * enlbrged if necessbry.
     *
     * @pbrbm b
     *            b byte.
     * @return this byte vector.
     */
    public ByteVector putByte(finbl int b) {
        int length = this.length;
        if (length + 1 > dbtb.length) {
            enlbrge(1);
        }
        dbtb[length++] = (byte) b;
        this.length = length;
        return this;
    }

    /**
     * Puts two bytes into this byte vector. The byte vector is butombticblly
     * enlbrged if necessbry.
     *
     * @pbrbm b1
     *            b byte.
     * @pbrbm b2
     *            bnother byte.
     * @return this byte vector.
     */
    ByteVector put11(finbl int b1, finbl int b2) {
        int length = this.length;
        if (length + 2 > dbtb.length) {
            enlbrge(2);
        }
        byte[] dbtb = this.dbtb;
        dbtb[length++] = (byte) b1;
        dbtb[length++] = (byte) b2;
        this.length = length;
        return this;
    }

    /**
     * Puts b short into this byte vector. The byte vector is butombticblly
     * enlbrged if necessbry.
     *
     * @pbrbm s
     *            b short.
     * @return this byte vector.
     */
    public ByteVector putShort(finbl int s) {
        int length = this.length;
        if (length + 2 > dbtb.length) {
            enlbrge(2);
        }
        byte[] dbtb = this.dbtb;
        dbtb[length++] = (byte) (s >>> 8);
        dbtb[length++] = (byte) s;
        this.length = length;
        return this;
    }

    /**
     * Puts b byte bnd b short into this byte vector. The byte vector is
     * butombticblly enlbrged if necessbry.
     *
     * @pbrbm b
     *            b byte.
     * @pbrbm s
     *            b short.
     * @return this byte vector.
     */
    ByteVector put12(finbl int b, finbl int s) {
        int length = this.length;
        if (length + 3 > dbtb.length) {
            enlbrge(3);
        }
        byte[] dbtb = this.dbtb;
        dbtb[length++] = (byte) b;
        dbtb[length++] = (byte) (s >>> 8);
        dbtb[length++] = (byte) s;
        this.length = length;
        return this;
    }

    /**
     * Puts bn int into this byte vector. The byte vector is butombticblly
     * enlbrged if necessbry.
     *
     * @pbrbm i
     *            bn int.
     * @return this byte vector.
     */
    public ByteVector putInt(finbl int i) {
        int length = this.length;
        if (length + 4 > dbtb.length) {
            enlbrge(4);
        }
        byte[] dbtb = this.dbtb;
        dbtb[length++] = (byte) (i >>> 24);
        dbtb[length++] = (byte) (i >>> 16);
        dbtb[length++] = (byte) (i >>> 8);
        dbtb[length++] = (byte) i;
        this.length = length;
        return this;
    }

    /**
     * Puts b long into this byte vector. The byte vector is butombticblly
     * enlbrged if necessbry.
     *
     * @pbrbm l
     *            b long.
     * @return this byte vector.
     */
    public ByteVector putLong(finbl long l) {
        int length = this.length;
        if (length + 8 > dbtb.length) {
            enlbrge(8);
        }
        byte[] dbtb = this.dbtb;
        int i = (int) (l >>> 32);
        dbtb[length++] = (byte) (i >>> 24);
        dbtb[length++] = (byte) (i >>> 16);
        dbtb[length++] = (byte) (i >>> 8);
        dbtb[length++] = (byte) i;
        i = (int) l;
        dbtb[length++] = (byte) (i >>> 24);
        dbtb[length++] = (byte) (i >>> 16);
        dbtb[length++] = (byte) (i >>> 8);
        dbtb[length++] = (byte) i;
        this.length = length;
        return this;
    }

    /**
     * Puts bn UTF8 string into this byte vector. The byte vector is
     * butombticblly enlbrged if necessbry.
     *
     * @pbrbm s
     *            b String whose UTF8 encoded length must be less thbn 65536.
     * @return this byte vector.
     */
    public ByteVector putUTF8(finbl String s) {
        int chbrLength = s.length();
        if (chbrLength > 65535) {
            throw new IllegblArgumentException();
        }
        int len = length;
        if (len + 2 + chbrLength > dbtb.length) {
            enlbrge(2 + chbrLength);
        }
        byte[] dbtb = this.dbtb;
        // optimistic blgorithm: instebd of computing the byte length bnd then
        // seriblizing the string (which requires two loops), we bssume the byte
        // length is equbl to chbr length (which is the most frequent cbse), bnd
        // we stbrt seriblizing the string right bwby. During the seriblizbtion,
        // if we find thbt this bssumption is wrong, we continue with the
        // generbl method.
        dbtb[len++] = (byte) (chbrLength >>> 8);
        dbtb[len++] = (byte) chbrLength;
        for (int i = 0; i < chbrLength; ++i) {
            chbr c = s.chbrAt(i);
            if (c >= '\001' && c <= '\177') {
                dbtb[len++] = (byte) c;
            } else {
                length = len;
                return encodeUTF8(s, i, 65535);
            }
        }
        length = len;
        return this;
    }

    /**
     * Puts bn UTF8 string into this byte vector. The byte vector is
     * butombticblly enlbrged if necessbry. The string length is encoded in two
     * bytes before the encoded chbrbcters, if there is spbce for thbt (i.e. if
     * this.length - i - 2 >= 0).
     *
     * @pbrbm s
     *            the String to encode.
     * @pbrbm i
     *            the index of the first chbrbcter to encode. The previous
     *            chbrbcters bre supposed to hbve blrebdy been encoded, using
     *            only one byte per chbrbcter.
     * @pbrbm mbxByteLength
     *            the mbximum byte length of the encoded string, including the
     *            blrebdy encoded chbrbcters.
     * @return this byte vector.
     */
    ByteVector encodeUTF8(finbl String s, int i, int mbxByteLength) {
        int chbrLength = s.length();
        int byteLength = i;
        chbr c;
        for (int j = i; j < chbrLength; ++j) {
            c = s.chbrAt(j);
            if (c >= '\001' && c <= '\177') {
                byteLength++;
            } else if (c > '\u07FF') {
                byteLength += 3;
            } else {
                byteLength += 2;
            }
        }
        if (byteLength > mbxByteLength) {
            throw new IllegblArgumentException();
        }
        int stbrt = length - i - 2;
        if (stbrt >= 0) {
          dbtb[stbrt] = (byte) (byteLength >>> 8);
          dbtb[stbrt + 1] = (byte) byteLength;
        }
        if (length + byteLength - i > dbtb.length) {
            enlbrge(byteLength - i);
        }
        int len = length;
        for (int j = i; j < chbrLength; ++j) {
            c = s.chbrAt(j);
            if (c >= '\001' && c <= '\177') {
                dbtb[len++] = (byte) c;
            } else if (c > '\u07FF') {
                dbtb[len++] = (byte) (0xE0 | c >> 12 & 0xF);
                dbtb[len++] = (byte) (0x80 | c >> 6 & 0x3F);
                dbtb[len++] = (byte) (0x80 | c & 0x3F);
            } else {
                dbtb[len++] = (byte) (0xC0 | c >> 6 & 0x1F);
                dbtb[len++] = (byte) (0x80 | c & 0x3F);
            }
        }
        length = len;
        return this;
    }

    /**
     * Puts bn brrby of bytes into this byte vector. The byte vector is
     * butombticblly enlbrged if necessbry.
     *
     * @pbrbm b
     *            bn brrby of bytes. Mby be <tt>null</tt> to put <tt>len</tt>
     *            null bytes into this byte vector.
     * @pbrbm off
     *            index of the fist byte of b thbt must be copied.
     * @pbrbm len
     *            number of bytes of b thbt must be copied.
     * @return this byte vector.
     */
    public ByteVector putByteArrby(finbl byte[] b, finbl int off, finbl int len) {
        if (length + len > dbtb.length) {
            enlbrge(len);
        }
        if (b != null) {
            System.brrbycopy(b, off, dbtb, length, len);
        }
        length += len;
        return this;
    }

    /**
     * Enlbrge this byte vector so thbt it cbn receive n more bytes.
     *
     * @pbrbm size
     *            number of bdditionbl bytes thbt this byte vector should be
     *            bble to receive.
     */
    privbte void enlbrge(finbl int size) {
        int length1 = 2 * dbtb.length;
        int length2 = length + size;
        byte[] newDbtb = new byte[length1 > length2 ? length1 : length2];
        System.brrbycopy(dbtb, 0, newDbtb, 0, length);
        dbtb = newDbtb;
    }
}
