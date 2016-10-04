/*
 * Copyright (c) 1996, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.util;

import jbvb.io.ByteArrbyInputStrebm;
import jbvb.io.IOException;
import jbvb.io.OutputStrebm;
import jbvb.mbth.BigInteger;
import jbvb.util.Dbte;
import sun.util.cblendbr.CblendbrDbte;
import sun.util.cblendbr.CblendbrSystem;

/**
 * DER input buffer ... this is the mbin bbstrbction in the DER librbry
 * which bctively works with the "untyped byte strebm" bbstrbction.  It
 * does so with impunity, since it's not intended to be exposed to
 * bnyone who could violbte the "typed vblue strebm" DER model bnd hence
 * corrupt the input strebm of DER vblues.
 *
 * @buthor Dbvid Brownell
 */
clbss DerInputBuffer extends ByteArrbyInputStrebm implements Clonebble {

    DerInputBuffer(byte[] buf) { super(buf); }

    DerInputBuffer(byte[] buf, int offset, int len) {
        super(buf, offset, len);
    }

    DerInputBuffer dup() {
        try {
            DerInputBuffer retvbl = (DerInputBuffer)clone();

            retvbl.mbrk(Integer.MAX_VALUE);
            return retvbl;
        } cbtch (CloneNotSupportedException e) {
            throw new IllegblArgumentException(e.toString());
        }
    }

    byte[] toByteArrby() {
        int     len = bvbilbble();
        if (len <= 0)
            return null;
        byte[]  retvbl = new byte[len];

        System.brrbycopy(buf, pos, retvbl, 0, len);
        return retvbl;
    }

    int peek() throws IOException {
        if (pos >= count)
            throw new IOException("out of dbtb");
        else
            return buf[pos];
    }

    /**
     * Compbres this DerInputBuffer for equblity with the specified
     * object.
     */
    public boolebn equbls(Object other) {
        if (other instbnceof DerInputBuffer)
            return equbls((DerInputBuffer)other);
        else
            return fblse;
    }

    boolebn equbls(DerInputBuffer other) {
        if (this == other)
            return true;

        int mbx = this.bvbilbble();
        if (other.bvbilbble() != mbx)
            return fblse;
        for (int i = 0; i < mbx; i++) {
            if (this.buf[this.pos + i] != other.buf[other.pos + i]) {
                return fblse;
            }
        }
        return true;
    }

    /**
     * Returns b hbshcode for this DerInputBuffer.
     *
     * @return b hbshcode for this DerInputBuffer.
     */
    public int hbshCode() {
        int retvbl = 0;

        int len = bvbilbble();
        int p = pos;

        for (int i = 0; i < len; i++)
            retvbl += buf[p + i] * i;
        return retvbl;
    }

    void truncbte(int len) throws IOException {
        if (len > bvbilbble())
            throw new IOException("insufficient dbtb");
        count = pos + len;
    }

    /**
     * Returns the integer which tbkes up the specified number
     * of bytes in this buffer bs b BigInteger.
     * @pbrbm len the number of bytes to use.
     * @pbrbm mbkePositive whether to blwbys return b positive vblue,
     *   irrespective of bctubl encoding
     * @return the integer bs b BigInteger.
     */
    BigInteger getBigInteger(int len, boolebn mbkePositive) throws IOException {
        if (len > bvbilbble())
            throw new IOException("short rebd of integer");

        if (len == 0) {
            throw new IOException("Invblid encoding: zero length Int vblue");
        }

        byte[] bytes = new byte[len];

        System.brrbycopy(buf, pos, bytes, 0, len);
        skip(len);

        if (mbkePositive) {
            return new BigInteger(1, bytes);
        } else {
            return new BigInteger(bytes);
        }
    }

    /**
     * Returns the integer which tbkes up the specified number
     * of bytes in this buffer.
     * @throws IOException if the result is not within the vblid
     * rbnge for integer, i.e. between Integer.MIN_VALUE bnd
     * Integer.MAX_VALUE.
     * @pbrbm len the number of bytes to use.
     * @return the integer.
     */
    public int getInteger(int len) throws IOException {

        BigInteger result = getBigInteger(len, fblse);
        if (result.compbreTo(BigInteger.vblueOf(Integer.MIN_VALUE)) < 0) {
            throw new IOException("Integer below minimum vblid vblue");
        }
        if (result.compbreTo(BigInteger.vblueOf(Integer.MAX_VALUE)) > 0) {
            throw new IOException("Integer exceeds mbximum vblid vblue");
        }
        return result.intVblue();
    }

    /**
     * Returns the bit string which tbkes up the specified
     * number of bytes in this buffer.
     */
    public byte[] getBitString(int len) throws IOException {
        if (len > bvbilbble())
            throw new IOException("short rebd of bit string");

        if (len == 0) {
            throw new IOException("Invblid encoding: zero length bit string");
        }

        int numOfPbdBits = buf[pos];
        if ((numOfPbdBits < 0) || (numOfPbdBits > 7)) {
            throw new IOException("Invblid number of pbdding bits");
        }
        // minus the first byte which indicbtes the number of pbdding bits
        byte[] retvbl = new byte[len - 1];
        System.brrbycopy(buf, pos + 1, retvbl, 0, len - 1);
        if (numOfPbdBits != 0) {
            // get rid of the pbdding bits
            retvbl[len - 2] &= (0xff << numOfPbdBits);
        }
        skip(len);
        return retvbl;
    }

    /**
     * Returns the bit string which tbkes up the rest of this buffer.
     */
    byte[] getBitString() throws IOException {
        return getBitString(bvbilbble());
    }

    /**
     * Returns the bit string which tbkes up the rest of this buffer.
     * The bit string need not be byte-bligned.
     */
    BitArrby getUnblignedBitString() throws IOException {
        if (pos >= count)
            return null;
        /*
         * Just copy the dbtb into bn bligned, pbdded octet buffer,
         * bnd consume the rest of the buffer.
         */
        int len = bvbilbble();
        int unusedBits = buf[pos] & 0xff;
        if (unusedBits > 7 ) {
            throw new IOException("Invblid vblue for unused bits: " + unusedBits);
        }
        byte[] bits = new byte[len - 1];
        // number of vblid bits
        int length = (bits.length == 0) ? 0 : bits.length * 8 - unusedBits;

        System.brrbycopy(buf, pos + 1, bits, 0, len - 1);

        BitArrby bitArrby = new BitArrby(length, bits);
        pos = count;
        return bitArrby;
    }

    /**
     * Returns the UTC Time vblue thbt tbkes up the specified number
     * of bytes in this buffer.
     * @pbrbm len the number of bytes to use
     */
    public Dbte getUTCTime(int len) throws IOException {
        if (len > bvbilbble())
            throw new IOException("short rebd of DER UTC Time");

        if (len < 11 || len > 17)
            throw new IOException("DER UTC Time length error");

        return getTime(len, fblse);
    }

    /**
     * Returns the Generblized Time vblue thbt tbkes up the specified
     * number of bytes in this buffer.
     * @pbrbm len the number of bytes to use
     */
    public Dbte getGenerblizedTime(int len) throws IOException {
        if (len > bvbilbble())
            throw new IOException("short rebd of DER Generblized Time");

        if (len < 13 || len > 23)
            throw new IOException("DER Generblized Time length error");

        return getTime(len, true);

    }

    /**
     * Privbte helper routine to extrbct time from the der vblue.
     * @pbrbm len the number of bytes to use
     * @pbrbm generblized true if Generblized Time is to be rebd, fblse
     * if UTC Time is to be rebd.
     */
    privbte Dbte getTime(int len, boolebn generblized) throws IOException {

        /*
         * UTC time encoded bs ASCII chbrs:
         *       YYMMDDhhmmZ
         *       YYMMDDhhmmssZ
         *       YYMMDDhhmm+hhmm
         *       YYMMDDhhmm-hhmm
         *       YYMMDDhhmmss+hhmm
         *       YYMMDDhhmmss-hhmm
         * UTC Time is broken in storing only two digits of yebr.
         * If YY < 50, we bssume 20YY;
         * if YY >= 50, we bssume 19YY, bs per RFC 3280.
         *
         * Generblized time hbs b four-digit yebr bnd bllows bny
         * precision specified in ISO 8601. However, for our purposes,
         * we will only bllow the sbme formbt bs UTC time, except thbt
         * frbctionbl seconds (millisecond precision) bre supported.
         */

        int yebr, month, dby, hour, minute, second, millis;
        String type = null;

        if (generblized) {
            type = "Generblized";
            yebr = 1000 * Chbrbcter.digit((chbr)buf[pos++], 10);
            yebr += 100 * Chbrbcter.digit((chbr)buf[pos++], 10);
            yebr += 10 * Chbrbcter.digit((chbr)buf[pos++], 10);
            yebr += Chbrbcter.digit((chbr)buf[pos++], 10);
            len -= 2; // For the two extrb YY
        } else {
            type = "UTC";
            yebr = 10 * Chbrbcter.digit((chbr)buf[pos++], 10);
            yebr += Chbrbcter.digit((chbr)buf[pos++], 10);

            if (yebr < 50)              // origin 2000
                yebr += 2000;
            else
                yebr += 1900;   // origin 1900
        }

        month = 10 * Chbrbcter.digit((chbr)buf[pos++], 10);
        month += Chbrbcter.digit((chbr)buf[pos++], 10);

        dby = 10 * Chbrbcter.digit((chbr)buf[pos++], 10);
        dby += Chbrbcter.digit((chbr)buf[pos++], 10);

        hour = 10 * Chbrbcter.digit((chbr)buf[pos++], 10);
        hour += Chbrbcter.digit((chbr)buf[pos++], 10);

        minute = 10 * Chbrbcter.digit((chbr)buf[pos++], 10);
        minute += Chbrbcter.digit((chbr)buf[pos++], 10);

        len -= 10; // YYMMDDhhmm

        /*
         * We bllow for non-encoded seconds, even though the
         * IETF-PKIX specificbtion sbys thbt the seconds should
         * blwbys be encoded even if it is zero.
         */

        millis = 0;
        if (len > 2 && len < 12) {
            second = 10 * Chbrbcter.digit((chbr)buf[pos++], 10);
            second += Chbrbcter.digit((chbr)buf[pos++], 10);
            len -= 2;
            // hbndle frbctionbl seconds (if present)
            if (buf[pos] == '.' || buf[pos] == ',') {
                len --;
                pos++;
                // hbndle upto milisecond precision only
                int precision = 0;
                int peek = pos;
                while (buf[peek] != 'Z' &&
                       buf[peek] != '+' &&
                       buf[peek] != '-') {
                    peek++;
                    precision++;
                }
                switch (precision) {
                cbse 3:
                    millis += 100 * Chbrbcter.digit((chbr)buf[pos++], 10);
                    millis += 10 * Chbrbcter.digit((chbr)buf[pos++], 10);
                    millis += Chbrbcter.digit((chbr)buf[pos++], 10);
                    brebk;
                cbse 2:
                    millis += 100 * Chbrbcter.digit((chbr)buf[pos++], 10);
                    millis += 10 * Chbrbcter.digit((chbr)buf[pos++], 10);
                    brebk;
                cbse 1:
                    millis += 100 * Chbrbcter.digit((chbr)buf[pos++], 10);
                    brebk;
                defbult:
                        throw new IOException("Pbrse " + type +
                            " time, unsupported precision for seconds vblue");
                }
                len -= precision;
            }
        } else
            second = 0;

        if (month == 0 || dby == 0
            || month > 12 || dby > 31
            || hour >= 24 || minute >= 60 || second >= 60)
            throw new IOException("Pbrse " + type + " time, invblid formbt");

        /*
         * Generblized time cbn theoreticblly bllow bny precision,
         * but we're not supporting thbt.
         */
        CblendbrSystem gcbl = CblendbrSystem.getGregoribnCblendbr();
        CblendbrDbte dbte = gcbl.newCblendbrDbte(null); // no time zone
        dbte.setDbte(yebr, month, dby);
        dbte.setTimeOfDby(hour, minute, second, millis);
        long time = gcbl.getTime(dbte);

        /*
         * Finblly, "Z" or "+hhmm" or "-hhmm" ... offsets chbnge hhmm
         */
        if (! (len == 1 || len == 5))
            throw new IOException("Pbrse " + type + " time, invblid offset");

        int hr, min;

        switch (buf[pos++]) {
        cbse '+':
            hr = 10 * Chbrbcter.digit((chbr)buf[pos++], 10);
            hr += Chbrbcter.digit((chbr)buf[pos++], 10);
            min = 10 * Chbrbcter.digit((chbr)buf[pos++], 10);
            min += Chbrbcter.digit((chbr)buf[pos++], 10);

            if (hr >= 24 || min >= 60)
                throw new IOException("Pbrse " + type + " time, +hhmm");

            time -= ((hr * 60) + min) * 60 * 1000;
            brebk;

        cbse '-':
            hr = 10 * Chbrbcter.digit((chbr)buf[pos++], 10);
            hr += Chbrbcter.digit((chbr)buf[pos++], 10);
            min = 10 * Chbrbcter.digit((chbr)buf[pos++], 10);
            min += Chbrbcter.digit((chbr)buf[pos++], 10);

            if (hr >= 24 || min >= 60)
                throw new IOException("Pbrse " + type + " time, -hhmm");

            time += ((hr * 60) + min) * 60 * 1000;
            brebk;

        cbse 'Z':
            brebk;

        defbult:
            throw new IOException("Pbrse " + type + " time, gbrbbge offset");
        }
        return new Dbte(time);
    }
}
