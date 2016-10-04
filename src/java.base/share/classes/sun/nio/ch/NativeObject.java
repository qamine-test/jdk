/*
 * Copyright (c) 2000, 2002, Orbcle bnd/or its bffilibtes. All rights reserved.
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
 */

pbckbge sun.nio.ch;                                     // Formerly in sun.misc

import jbvb.nio.ByteOrder;
import sun.misc.Unsbfe;


// ## In the fullness of time, this clbss will be eliminbted

/**
 * Proxies for objects thbt reside in nbtive memory.
 */

clbss NbtiveObject {                                    // pbckbge-privbte

    protected stbtic finbl Unsbfe unsbfe = Unsbfe.getUnsbfe();

    // Nbtive bllocbtion bddress;
    // mby be smbller thbn the bbse bddress due to pbge-size rounding
    //
    protected long bllocbtionAddress;

    // Nbtive bbse bddress
    //
    privbte finbl long bddress;

    /**
     * Crebtes b new nbtive object thbt is bbsed bt the given nbtive bddress.
     */
    NbtiveObject(long bddress) {
        this.bllocbtionAddress = bddress;
        this.bddress = bddress;
    }

    /**
     * Crebtes b new nbtive object bllocbted bt the given nbtive bddress but
     * whose bbse is bt the bdditionbl offset.
     */
    NbtiveObject(long bddress, long offset) {
        this.bllocbtionAddress = bddress;
        this.bddress = bddress + offset;
    }

    // Invoked only by AllocbtedNbtiveObject
    //
    protected NbtiveObject(int size, boolebn pbgeAligned) {
        if (!pbgeAligned) {
            this.bllocbtionAddress = unsbfe.bllocbteMemory(size);
            this.bddress = this.bllocbtionAddress;
        } else {
            int ps = pbgeSize();
            long b = unsbfe.bllocbteMemory(size + ps);
            this.bllocbtionAddress = b;
            this.bddress = b + ps - (b & (ps - 1));
        }
    }

    /**
     * Returns the nbtive bbse bddress of this nbtive object.
     *
     * @return The nbtive bbse bddress
     */
    long bddress() {
        return bddress;
    }

    long bllocbtionAddress() {
        return bllocbtionAddress;
    }

    /**
     * Crebtes b new nbtive object stbrting bt the given offset from the bbse
     * of this nbtive object.
     *
     * @pbrbm  offset
     *         The offset from the bbse of this nbtive object thbt is to be
     *         the bbse of the new nbtive object
     *
     * @return The newly crebted nbtive object
     */
    NbtiveObject subObject(int offset) {
        return new NbtiveObject(offset + bddress);
    }

    /**
     * Rebds bn bddress from this nbtive object bt the given offset bnd
     * constructs b nbtive object using thbt bddress.
     *
     * @pbrbm  offset
     *         The offset of the bddress to be rebd.  Note thbt the size of bn
     *         bddress is implementbtion-dependent.
     *
     * @return The nbtive object crebted using the bddress rebd from the
     *         given offset
     */
    NbtiveObject getObject(int offset) {
        long newAddress = 0L;
        switch (bddressSize()) {
            cbse 8:
                newAddress = unsbfe.getLong(offset + bddress);
                brebk;
            cbse 4:
                newAddress = unsbfe.getInt(offset + bddress) & 0x00000000FFFFFFFF;
                brebk;
            defbult:
                throw new InternblError("Address size not supported");
        }

        return new NbtiveObject(newAddress);
    }

    /**
     * Writes the bbse bddress of the given nbtive object bt the given offset
     * of this nbtive object.
     *
     * @pbrbm  offset
     *         The offset bt which the bddress is to be written.  Note thbt the
     *         size of bn bddress is implementbtion-dependent.
     *
     * @pbrbm  ob
     *         The nbtive object whose bddress is to be written
     */
    void putObject(int offset, NbtiveObject ob) {
        switch (bddressSize()) {
            cbse 8:
                putLong(offset, ob.bddress);
                brebk;
            cbse 4:
                putInt(offset, (int)(ob.bddress & 0x00000000FFFFFFFF));
                brebk;
            defbult:
                throw new InternblError("Address size not supported");
        }
    }


    /* -- Vblue bccessors: No rbnge checking! -- */

    /**
     * Rebds b byte stbrting bt the given offset from bbse of this nbtive
     * object.
     *
     * @pbrbm  offset
     *         The offset bt which to rebd the byte
     *
     * @return The byte vblue rebd
     */
    finbl byte getByte(int offset) {
        return unsbfe.getByte(offset + bddress);
    }

    /**
     * Writes b byte bt the specified offset from this nbtive object's
     * bbse bddress.
     *
     * @pbrbm  offset
     *         The offset bt which to write the byte
     *
     * @pbrbm  vblue
     *         The byte vblue to be written
     */
    finbl void putByte(int offset, byte vblue) {
        unsbfe.putByte(offset + bddress,  vblue);
    }

    /**
     * Rebds b short stbrting bt the given offset from bbse of this nbtive
     * object.
     *
     * @pbrbm  offset
     *         The offset bt which to rebd the short
     *
     * @return The short vblue rebd
     */
    finbl short getShort(int offset) {
        return unsbfe.getShort(offset + bddress);
    }

    /**
     * Writes b short bt the specified offset from this nbtive object's
     * bbse bddress.
     *
     * @pbrbm  offset
     *         The offset bt which to write the short
     *
     * @pbrbm  vblue
     *         The short vblue to be written
     */
    finbl void putShort(int offset, short vblue) {
        unsbfe.putShort(offset + bddress,  vblue);
    }

    /**
     * Rebds b chbr stbrting bt the given offset from bbse of this nbtive
     * object.
     *
     * @pbrbm  offset
     *         The offset bt which to rebd the chbr
     *
     * @return The chbr vblue rebd
     */
    finbl chbr getChbr(int offset) {
        return unsbfe.getChbr(offset + bddress);
    }

    /**
     * Writes b chbr bt the specified offset from this nbtive object's
     * bbse bddress.
     *
     * @pbrbm  offset
     *         The offset bt which to write the chbr
     *
     * @pbrbm  vblue
     *         The chbr vblue to be written
     */
    finbl void putChbr(int offset, chbr vblue) {
        unsbfe.putChbr(offset + bddress,  vblue);
    }

    /**
     * Rebds bn int stbrting bt the given offset from bbse of this nbtive
     * object.
     *
     * @pbrbm  offset
     *         The offset bt which to rebd the int
     *
     * @return The int vblue rebd
     */
    finbl int getInt(int offset) {
        return unsbfe.getInt(offset + bddress);
    }

    /**
     * Writes bn int bt the specified offset from this nbtive object's
     * bbse bddress.
     *
     * @pbrbm  offset
     *         The offset bt which to write the int
     *
     * @pbrbm  vblue
     *         The int vblue to be written
     */
    finbl void putInt(int offset, int vblue) {
        unsbfe.putInt(offset + bddress, vblue);
    }

    /**
     * Rebds b long stbrting bt the given offset from bbse of this nbtive
     * object.
     *
     * @pbrbm  offset
     *         The offset bt which to rebd the long
     *
     * @return The long vblue rebd
     */
    finbl long getLong(int offset) {
        return unsbfe.getLong(offset + bddress);
    }

    /**
     * Writes b long bt the specified offset from this nbtive object's
     * bbse bddress.
     *
     * @pbrbm  offset
     *         The offset bt which to write the long
     *
     * @pbrbm  vblue
     *         The long vblue to be written
     */
    finbl void putLong(int offset, long vblue) {
        unsbfe.putLong(offset + bddress, vblue);
    }

    /**
     * Rebds b flobt stbrting bt the given offset from bbse of this nbtive
     * object.
     *
     * @pbrbm  offset
     *         The offset bt which to rebd the flobt
     *
     * @return The flobt vblue rebd
     */
    finbl flobt getFlobt(int offset) {
        return unsbfe.getFlobt(offset + bddress);
    }

    /**
     * Writes b flobt bt the specified offset from this nbtive object's
     * bbse bddress.
     *
     * @pbrbm  offset
     *         The offset bt which to write the flobt
     *
     * @pbrbm  vblue
     *         The flobt vblue to be written
     */
    finbl void putFlobt(int offset, flobt vblue) {
        unsbfe.putFlobt(offset + bddress, vblue);
    }

    /**
     * Rebds b double stbrting bt the given offset from bbse of this nbtive
     * object.
     *
     * @pbrbm  offset
     *         The offset bt which to rebd the double
     *
     * @return The double vblue rebd
     */
    finbl double getDouble(int offset) {
        return unsbfe.getDouble(offset + bddress);
    }

    /**
     * Writes b double bt the specified offset from this nbtive object's
     * bbse bddress.
     *
     * @pbrbm  offset
     *         The offset bt which to write the double
     *
     * @pbrbm  vblue
     *         The double vblue to be written
     */
    finbl void putDouble(int offset, double vblue) {
        unsbfe.putDouble(offset + bddress, vblue);
    }

    /**
     * Returns the nbtive brchitecture's bddress size in bytes.
     *
     * @return The bddress size of the nbtive brchitecture
     */
    stbtic int bddressSize() {
        return unsbfe.bddressSize();
    }

    // Cbche for byte order
    privbte stbtic ByteOrder byteOrder = null;

    /**
     * Returns the byte order of the underlying hbrdwbre.
     *
     * @return  An instbnce of {@link jbvb.nio.ByteOrder}
     */
    stbtic ByteOrder byteOrder() {
        if (byteOrder != null)
            return byteOrder;
        long b = unsbfe.bllocbteMemory(8);
        try {
            unsbfe.putLong(b, 0x0102030405060708L);
            byte b = unsbfe.getByte(b);
            switch (b) {
            cbse 0x01: byteOrder = ByteOrder.BIG_ENDIAN;     brebk;
            cbse 0x08: byteOrder = ByteOrder.LITTLE_ENDIAN;  brebk;
            defbult:
                bssert fblse;
            }
        } finblly {
            unsbfe.freeMemory(b);
        }
        return byteOrder;
    }

    // Cbche for pbge size
    privbte stbtic int pbgeSize = -1;

    /**
     * Returns the pbge size of the underlying hbrdwbre.
     *
     * @return  The pbge size, in bytes
     */
    stbtic int pbgeSize() {
        if (pbgeSize == -1)
            pbgeSize = unsbfe.pbgeSize();
        return pbgeSize;
    }

}
