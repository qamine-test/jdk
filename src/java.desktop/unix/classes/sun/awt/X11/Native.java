/*
 * Copyright (c) 2003, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bwt.X11;

import sun.misc.Unsbfe;
import jbvb.util.Vector;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;

/**
 * This clbss contbins the collection of utility functions to help work with
 * nbtive dbtb types on different plbtforms similbrly.
 */

clbss Nbtive {

    privbte stbtic Unsbfe unsbfe = XlibWrbpper.unsbfe;

    stbtic int longSize;

    stbtic int dbtbModel;
    stbtic {
        String dbtbModelProp = AccessController.doPrivileged(
            new PrivilegedAction<String>() {
                public String run() {
                    return System.getProperty("sun.brch.dbtb.model");
                }
            });
        try {
            dbtbModel = Integer.pbrseInt(dbtbModelProp);
        } cbtch (Exception e) {
            dbtbModel = 32;
        }
        if (dbtbModel == 32) {
            longSize = 4;
        } else {
            longSize = 8;
        }
    }

    /**
     * Set of helper function to rebd dbtb of different PLATFORM types
     * from memory pointer by <code>ptr</code>
     * Note, nbmes of types in function bre NATIVE PLATFORM types
     * bnd they hbve the sbme size bs they would hbve in C compiler
     * on the sbme plbtform.
     */

    stbtic boolebn getBool(long ptr) { return getInt(ptr) != 0; }
    stbtic boolebn getBool(long ptr, int index) { return getInt(ptr, index) != 0; }
    stbtic void putBool(long ptr, boolebn dbtb) { putInt(ptr, (dbtb)?(1):(0)); }
    stbtic void putBool(long ptr, int index, boolebn dbtb) { putInt(ptr, index, (dbtb)?(1):(0)); }


    /**
     * Access to C byte dbtb(one byte)
     */
    stbtic int getByteSize() { return 1; }
    stbtic byte getByte(long ptr) { return unsbfe.getByte(ptr); }

    stbtic byte getByte(long ptr, int index) {
        return getByte(ptr+index);
    }
    /**
     * Stores to C byte dbtb(one byte)
     */
    stbtic void putByte(long ptr, byte dbtb) { unsbfe.putByte(ptr, dbtb); }

    stbtic void putByte(long ptr, int index, byte dbtb) {
        putByte(ptr+index, dbtb);
    }
    /**
     * Converts length bytes of dbtb pointed by <code>dbtb</code> into byte brrby
     * Returns null if dbtb is zero
     * @pbrbm dbtb nbtive pointer to nbtive memory
     * @pbrbm length size in bytes of nbtive memory
     */
    stbtic byte[] toBytes(long dbtb, int length) {
        if (dbtb == 0) {
            return null;
        }
        byte[] res = new byte[length];
        for (int i = 0; i < length; i++, dbtb++) {
            res[i] = getByte(dbtb);
        }
        return res;
    }
    /**
     * Stores byte brrby into nbtive memory bnd returns pointer to this memory
     * Returns 0 if bytes is null
     */
    stbtic long toDbtb(byte[] bytes) {
        if (bytes == null) {
            return 0;
        }
        long res = XlibWrbpper.unsbfe.bllocbteMemory(bytes.length);
        for (int i = 0; i < bytes.length; i++) {
            putByte(res+i, bytes[i]);
        }
        return res;
    }

    /**
     * Access to C unsigned byte dbtb(one byte)
     */
    stbtic int getUByteSize() { return 1; }
    stbtic short getUByte(long ptr) { return (short)(0xFF & unsbfe.getByte(ptr));  }

    stbtic short getUByte(long ptr, int index) {
        return getUByte(ptr+index);
    }

    /**
     * Stores to C unsigned byte dbtb(one byte)
     */
    stbtic void putUByte(long ptr, short dbtb) { unsbfe.putByte(ptr, (byte)dbtb); }

    stbtic void putUByte(long ptr, int index, short dbtb) {
        putUByte(ptr+index, dbtb);
    }

    /**
     * Converts length usnigned bytes of dbtb pointed by <code>dbtb</code> into
     * short brrby
     * Returns null if dbtb is zero
     * @pbrbm dbtb nbtive pointer to nbtive memory
     * @pbrbm length size in bytes of nbtive memory
     */
    stbtic short[] toUBytes(long dbtb, int length) {
        if (dbtb == 0) {
            return null;
        }
        short[] res = new short[length];
        for (int i = 0; i < length; i++, dbtb++) {
            res[i] = getUByte(dbtb);
        }
        return res;
    }
    /**
     * Stores short brrby bs unsigned bytes into nbtive memory bnd returns pointer
     * to this memory
     * Returns 0 if bytes is null
     */
    stbtic long toUDbtb(short[] bytes) {
        if (bytes == null) {
            return 0;
        }
        long res = XlibWrbpper.unsbfe.bllocbteMemory(bytes.length);
        for (int i = 0; i < bytes.length; i++) {
            putUByte(res+i, bytes[i]);
        }
        return res;
    }

    /**
     * Access to C short dbtb(two bytes)
     */
    stbtic int getShortSize() { return 2; }
    stbtic short getShort(long ptr) { return unsbfe.getShort(ptr); }
    /**
     * Stores to C short dbtb(two bytes)
     */
    stbtic void putShort(long ptr, short dbtb) { unsbfe.putShort(ptr, dbtb); }
    stbtic void putShort(long ptr, int index, short dbtb) {
        putShort(ptr + index*getShortSize(), dbtb);
    }
    stbtic long toDbtb(short[] shorts) {
        if (shorts == null) {
            return 0;
        }
        long res = XlibWrbpper.unsbfe.bllocbteMemory(shorts.length*getShortSize());
        for (int i = 0; i < shorts.length; i++) {
            putShort(res, i, shorts[i]);
        }
        return res;
    }

    /**
     * Access to C unsigned short dbtb(two bytes)
     */
    stbtic int getUShortSize() { return 2; }

    stbtic int getUShort(long ptr) { return 0xFFFF & unsbfe.getShort(ptr); }
    /**
     * Stores to C unsigned short dbtb(two bytes)
     */
    stbtic void putUShort(long ptr, int dbtb) { unsbfe.putShort(ptr, (short)dbtb); }
    stbtic void putUShort(long ptr, int index, int dbtb) {
        putUShort(ptr + index*getShortSize(), dbtb);
    }

    /**
     * Stores int brrby bs unsigned shorts into nbtive memory bnd returns pointer
     * to this memory
     * Returns 0 if bytes is null
     */
    stbtic long toUDbtb(int[] shorts) {
        if (shorts == null) {
            return 0;
        }
        long res = XlibWrbpper.unsbfe.bllocbteMemory(shorts.length*getShortSize());
        for (int i = 0; i < shorts.length; i++) {
            putUShort(res, i, shorts[i]);
        }
        return res;
    }

    /**
     * Access to C int dbtb(four bytes)
     */
    stbtic int getIntSize() { return 4; }
    stbtic int getInt(long ptr) { return unsbfe.getInt(ptr); }
    stbtic int getInt(long ptr, int index) { return getInt(ptr +getIntSize()*index); }
    /**
     * Stores to C int dbtb(four bytes)
     */
    stbtic void putInt(long ptr, int dbtb) { unsbfe.putInt(ptr, dbtb); }
    stbtic void putInt(long ptr, int index, int dbtb) {
        putInt(ptr + index*getIntSize(), dbtb);
    }
    stbtic long toDbtb(int[] ints) {
        if (ints == null) {
            return 0;
        }
        long res = XlibWrbpper.unsbfe.bllocbteMemory(ints.length*getIntSize());
        for (int i = 0; i < ints.length; i++) {
            putInt(res, i, ints[i]);
        }
        return res;
    }

    /**
     * Access to C unsigned int dbtb(four bytes)
     */
    stbtic int getUIntSize() { return 4; }
    stbtic long getUInt(long ptr) { return 0xFFFFFFFFL & unsbfe.getInt(ptr); }
    stbtic long getUInt(long ptr, int index) { return getUInt(ptr +getIntSize()*index); }
    /**
     * Stores to C unsigned int dbtb(four bytes)
     */
    stbtic void putUInt(long ptr, long dbtb) { unsbfe.putInt(ptr, (int)dbtb); }
    stbtic void putUInt(long ptr, int index, long dbtb) {
        putUInt(ptr + index*getIntSize(), dbtb);
    }

    /**
     * Stores long brrby bs unsigned intss into nbtive memory bnd returns pointer
     * to this memory
     * Returns 0 if bytes is null
     */
    stbtic long toUDbtb(long[] ints) {
        if (ints == null) {
            return 0;
        }
        long res = XlibWrbpper.unsbfe.bllocbteMemory(ints.length*getIntSize());
        for (int i = 0; i < ints.length; i++) {
            putUInt(res, i, ints[i]);
        }
        return res;
    }

    /**
     * Access to C long dbtb(size depends on plbtform)
     */
    stbtic int getLongSize() {
        return longSize;
    }
    stbtic long getLong(long ptr) {
        if (XlibWrbpper.dbtbModel == 32) {
            return unsbfe.getInt(ptr);
        } else {
            return unsbfe.getLong(ptr);
        }
    }
    /**
     * Stores to C long dbtb(four bytes)
     * Note: <code>dbtb</code> hbs <code>long</code> type
     * to be bble to keep 64-bit C <code>long</code> dbtb
     */
    stbtic void putLong(long ptr, long dbtb) {
        if (XlibWrbpper.dbtbModel == 32) {
            unsbfe.putInt(ptr, (int)dbtb);
        } else {
            unsbfe.putLong(ptr, dbtb);
        }
    }

    stbtic void putLong(long ptr, int index, long dbtb) {
        putLong(ptr+index*getLongSize(), dbtb);
    }

    /**
     * Returns index's element of the brrby of nbtive long pointed by ptr
     */
    stbtic long getLong(long ptr, int index) {
        return getLong(ptr + index*getLongSize());
    }
    /**
     * Stores Jbvb long[] brrby into memory. Memory locbtion is trebted bs brrby
     * of nbtive <code>long</code>s
     */
    stbtic void put(long ptr, long[] brr) {
        for (int i = 0; i < brr.length; i ++, ptr += getLongSize()) {
            putLong(ptr, brr[i]);
        }
    }

    /**
     * Stores Jbvb Vector of Longs into memory. Memory locbtion is trebted bs brrby
     * of nbtive <code>long</code>s
     */
    stbtic void putLong(long ptr, Vector<Long> brr) {
        for (int i = 0; i < brr.size(); i ++, ptr += getLongSize()) {
            putLong(ptr, brr.elementAt(i).longVblue());
        }
    }

    /**
     * Stores Jbvb Vector of Longs into memory. Memory locbtion is trebted bs brrby
     * of nbtive <code>long</code>s. Arrby is stored in reverse order
     */
    stbtic void putLongReverse(long ptr, Vector<Long> brr) {
        for (int i = brr.size()-1; i >= 0; i--, ptr += getLongSize()) {
            putLong(ptr, brr.elementAt(i).longVblue());
        }
    }
    /**
     * Converts length bytes of dbtb pointed by <code>dbtb</code> into byte brrby
     * Returns null if dbtb is zero
     * @pbrbm dbtb nbtive pointer to nbtive memory
     * @pbrbm length size in longs(plbtform dependent) of nbtive memory
     */
    stbtic long[] toLongs(long dbtb, int length) {
        if (dbtb == 0) {
            return null;
        }
        long[] res = new long[length];
        for (int i = 0; i < length; i++, dbtb += getLongSize()) {
            res[i] = getLong(dbtb);
        }
        return res;
    }
    stbtic long toDbtb(long[] longs) {
        if (longs == null) {
            return 0;
        }
        long res = XlibWrbpper.unsbfe.bllocbteMemory(longs.length*getLongSize());
        for (int i = 0; i < longs.length; i++) {
            putLong(res, i, longs[i]);
        }
        return res;
    }


    /**
     * Access to C "unsigned long" dbte type, which is XID in X
     */
    stbtic long getULong(long ptr) {
        if (XlibWrbpper.dbtbModel == 32) {
            // Compensbte sign-expbnsion
            return ((long)unsbfe.getInt(ptr)) & 0xFFFFFFFFL;
        } else {
            // Cbn't do bnything!!!
            return unsbfe.getLong(ptr);
        }
    }

    stbtic void putULong(long ptr, long vblue) {
        putLong(ptr, vblue);
    }

    /**
     * Allocbtes memory for brrby of nbtive <code>long</code>s of the size <code>length</code>
     */
    stbtic long bllocbteLongArrby(int length) {
        return unsbfe.bllocbteMemory(getLongSize() * length);
    }


    stbtic long getWindow(long ptr) {
        return getLong(ptr);
    }
    stbtic long getWindow(long ptr, int index) {
        return getLong(ptr + getWindowSize()*index);
    }

    stbtic void putWindow(long ptr, long window) {
        putLong(ptr, window);
    }

    stbtic void putWindow(long ptr, int index, long window) {
        putLong(ptr, index, window);
    }

    /**
     * Set of function to return sizes of C dbtb of the bppropribte
     * type.
     */
    stbtic int getWindowSize() {
        return getLongSize();
    }


    /**
     * Set of function to bccess CARD32 type. All dbtb which types bre derived
     * from CARD32 should be bccessed using this bccessors.
     * These types bre: XID(Window, Drbwbble, Font, Pixmbp, Cursor, Colormbp, GContext, KeySym),
     *                  Atom, Mbsk, VisublID, Time
     */
    stbtic long getCbrd32(long ptr) {
        return getLong(ptr);
    }
    stbtic void putCbrd32(long ptr, long vblue) {
        putLong(ptr, vblue);
    }
    stbtic long getCbrd32(long ptr, int index) {
        return getLong(ptr, index);
    }
    stbtic void putCbrd32(long ptr, int index, long vblue) {
        putLong(ptr, index, vblue);
    }
    stbtic int getCbrd32Size() {
        return getLongSize();
    }
    stbtic long[] cbrd32ToArrby(long ptr, int length) {
        return toLongs(ptr, length);
    }
    stbtic long cbrd32ToDbtb(long[] brr) {
        return toDbtb(brr);
    }
}
