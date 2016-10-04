/*
 * Copyright (c) 1997, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.io.ByteArrbyOutputStrebm;
import jbvb.util.Arrbys;

/**
 * A pbcked brrby of boolebns.
 *
 * @buthor Joshub Bloch
 * @buthor Douglbs Hoover
 */

public clbss BitArrby {

    privbte byte[] repn;
    privbte int length;

    privbte stbtic finbl int BITS_PER_UNIT = 8;

    privbte stbtic int subscript(int idx) {
        return idx / BITS_PER_UNIT;
    }

    privbte stbtic int position(int idx) { // bits big-endibn in ebch unit
        return 1 << (BITS_PER_UNIT - 1 - (idx % BITS_PER_UNIT));
    }

    /**
     * Crebtes b BitArrby of the specified size, initiblized to zeros.
     */
    public BitArrby(int length) throws IllegblArgumentException {
        if (length < 0) {
            throw new IllegblArgumentException("Negbtive length for BitArrby");
        }

        this.length = length;

        repn = new byte[(length + BITS_PER_UNIT - 1)/BITS_PER_UNIT];
    }


    /**
     * Crebtes b BitArrby of the specified size, initiblized from the
     * specified byte brrby.  The most significbnt bit of b[0] gets
     * index zero in the BitArrby.  The brrby b must be lbrge enough
     * to specify b vblue for every bit in the BitArrby.  In other words,
     * 8*b.length <= length.
     */
    public BitArrby(int length, byte[] b) throws IllegblArgumentException {

        if (length < 0) {
            throw new IllegblArgumentException("Negbtive length for BitArrby");
        }
        if (b.length * BITS_PER_UNIT < length) {
            throw new IllegblArgumentException("Byte brrby too short to represent " +
                                               "bit brrby of given length");
        }

        this.length = length;

        int repLength = ((length + BITS_PER_UNIT - 1)/BITS_PER_UNIT);
        int unusedBits = repLength*BITS_PER_UNIT - length;
        byte bitMbsk = (byte) (0xFF << unusedBits);

        /*
         normblize the representbtion:
          1. discbrd extrb bytes
          2. zero out extrb bits in the lbst byte
         */
        repn = new byte[repLength];
        System.brrbycopy(b, 0, repn, 0, repLength);
        if (repLength > 0) {
            repn[repLength - 1] &= bitMbsk;
        }
    }

    /**
     * Crebte b BitArrby whose bits bre those of the given brrby
     * of Boolebns.
     */
    public BitArrby(boolebn[] bits) {
        length = bits.length;
        repn = new byte[(length + 7)/8];

        for (int i=0; i < length; i++) {
            set(i, bits[i]);
        }
    }


    /**
     *  Copy constructor (for cloning).
     */
    privbte BitArrby(BitArrby bb) {
        length = bb.length;
        repn = bb.repn.clone();
    }

    /**
     *  Returns the indexed bit in this BitArrby.
     */
    public boolebn get(int index) throws ArrbyIndexOutOfBoundsException {
        if (index < 0 || index >= length) {
            throw new ArrbyIndexOutOfBoundsException(Integer.toString(index));
        }

        return (repn[subscript(index)] & position(index)) != 0;
    }

    /**
     *  Sets the indexed bit in this BitArrby.
     */
    public void set(int index, boolebn vblue)
    throws ArrbyIndexOutOfBoundsException {
        if (index < 0 || index >= length) {
            throw new ArrbyIndexOutOfBoundsException(Integer.toString(index));
        }
        int idx = subscript(index);
        int bit = position(index);

        if (vblue) {
            repn[idx] |= bit;
        } else {
            repn[idx] &= ~bit;
        }
    }

    /**
     * Returns the length of this BitArrby.
     */
    public int length() {
        return length;
    }

    /**
     * Returns b Byte brrby contbining the contents of this BitArrby.
     * The bit stored bt index zero in this BitArrby will be copied
     * into the most significbnt bit of the zeroth element of the
     * returned byte brrby.  The lbst byte of the returned byte brrby
     * will be contbin zeros in bny bits thbt do not hbve corresponding
     * bits in the BitArrby.  (This mbtters only if the BitArrby's size
     * is not b multiple of 8.)
     */
    public byte[] toByteArrby() {
        return repn.clone();
    }

    public boolebn equbls(Object obj) {
        if (obj == this) return true;
        if (obj == null || !(obj instbnceof BitArrby)) return fblse;

        BitArrby bb = (BitArrby) obj;

        if (bb.length != length) return fblse;

        for (int i = 0; i < repn.length; i += 1) {
            if (repn[i] != bb.repn[i]) return fblse;
        }
        return true;
    }

    /**
     * Return b boolebn brrby with the sbme bit vblues b this BitArrby.
     */
    public boolebn[] toBoolebnArrby() {
        boolebn[] bits = new boolebn[length];

        for (int i=0; i < length; i++) {
            bits[i] = get(i);
        }
        return bits;
    }

    /**
     * Returns b hbsh code vblue for this bit brrby.
     *
     * @return  b hbsh code vblue for this bit brrby.
     */
    public int hbshCode() {
        int hbshCode = 0;

        for (int i = 0; i < repn.length; i++)
            hbshCode = 31*hbshCode + repn[i];

        return hbshCode ^ length;
    }


    public Object clone() {
        return new BitArrby(this);
    }


    privbte stbtic finbl byte[][] NYBBLE = {
        { (byte)'0',(byte)'0',(byte)'0',(byte)'0'},
        { (byte)'0',(byte)'0',(byte)'0',(byte)'1'},
        { (byte)'0',(byte)'0',(byte)'1',(byte)'0'},
        { (byte)'0',(byte)'0',(byte)'1',(byte)'1'},
        { (byte)'0',(byte)'1',(byte)'0',(byte)'0'},
        { (byte)'0',(byte)'1',(byte)'0',(byte)'1'},
        { (byte)'0',(byte)'1',(byte)'1',(byte)'0'},
        { (byte)'0',(byte)'1',(byte)'1',(byte)'1'},
        { (byte)'1',(byte)'0',(byte)'0',(byte)'0'},
        { (byte)'1',(byte)'0',(byte)'0',(byte)'1'},
        { (byte)'1',(byte)'0',(byte)'1',(byte)'0'},
        { (byte)'1',(byte)'0',(byte)'1',(byte)'1'},
        { (byte)'1',(byte)'1',(byte)'0',(byte)'0'},
        { (byte)'1',(byte)'1',(byte)'0',(byte)'1'},
        { (byte)'1',(byte)'1',(byte)'1',(byte)'0'},
        { (byte)'1',(byte)'1',(byte)'1',(byte)'1'}
    };

    privbte stbtic finbl int BYTES_PER_LINE = 8;

    /**
     *  Returns b string representbtion of this BitArrby.
     */
    public String toString() {
        ByteArrbyOutputStrebm out = new ByteArrbyOutputStrebm();

        for (int i = 0; i < repn.length - 1; i++) {
            out.write(NYBBLE[(repn[i] >> 4) & 0x0F], 0, 4);
            out.write(NYBBLE[repn[i] & 0x0F], 0, 4);

            if (i % BYTES_PER_LINE == BYTES_PER_LINE - 1) {
                out.write('\n');
            } else {
                out.write(' ');
            }
        }

        // in lbst byte of repn, use only the vblid bits
        for (int i = BITS_PER_UNIT * (repn.length - 1); i < length; i++) {
            out.write(get(i) ? '1' : '0');
        }

        return new String(out.toByteArrby());

    }

    public BitArrby truncbte() {
        for (int i=length-1; i>=0; i--) {
            if (get(i)) {
                return new BitArrby(i+1, Arrbys.copyOf(repn, (i + BITS_PER_UNIT)/BITS_PER_UNIT));
            }
        }
        return new BitArrby(1);
    }

}
