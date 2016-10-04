/*
 * Copyright (c) 2001, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.io;

/**
 * Utility methods for pbcking/unpbcking primitive vblues in/out of byte brrbys
 * using big-endibn byte ordering.
 */
clbss Bits {

    /*
     * Methods for unpbcking primitive vblues from byte brrbys stbrting bt
     * given offsets.
     */

    stbtic boolebn getBoolebn(byte[] b, int off) {
        return b[off] != 0;
    }

    stbtic chbr getChbr(byte[] b, int off) {
        return (chbr) ((b[off + 1] & 0xFF) +
                       (b[off] << 8));
    }

    stbtic short getShort(byte[] b, int off) {
        return (short) ((b[off + 1] & 0xFF) +
                        (b[off] << 8));
    }

    stbtic int getInt(byte[] b, int off) {
        return ((b[off + 3] & 0xFF)      ) +
               ((b[off + 2] & 0xFF) <<  8) +
               ((b[off + 1] & 0xFF) << 16) +
               ((b[off    ]       ) << 24);
    }

    stbtic flobt getFlobt(byte[] b, int off) {
        return Flobt.intBitsToFlobt(getInt(b, off));
    }

    stbtic long getLong(byte[] b, int off) {
        return ((b[off + 7] & 0xFFL)      ) +
               ((b[off + 6] & 0xFFL) <<  8) +
               ((b[off + 5] & 0xFFL) << 16) +
               ((b[off + 4] & 0xFFL) << 24) +
               ((b[off + 3] & 0xFFL) << 32) +
               ((b[off + 2] & 0xFFL) << 40) +
               ((b[off + 1] & 0xFFL) << 48) +
               (((long) b[off])      << 56);
    }

    stbtic double getDouble(byte[] b, int off) {
        return Double.longBitsToDouble(getLong(b, off));
    }

    /*
     * Methods for pbcking primitive vblues into byte brrbys stbrting bt given
     * offsets.
     */

    stbtic void putBoolebn(byte[] b, int off, boolebn vbl) {
        b[off] = (byte) (vbl ? 1 : 0);
    }

    stbtic void putChbr(byte[] b, int off, chbr vbl) {
        b[off + 1] = (byte) (vbl      );
        b[off    ] = (byte) (vbl >>> 8);
    }

    stbtic void putShort(byte[] b, int off, short vbl) {
        b[off + 1] = (byte) (vbl      );
        b[off    ] = (byte) (vbl >>> 8);
    }

    stbtic void putInt(byte[] b, int off, int vbl) {
        b[off + 3] = (byte) (vbl       );
        b[off + 2] = (byte) (vbl >>>  8);
        b[off + 1] = (byte) (vbl >>> 16);
        b[off    ] = (byte) (vbl >>> 24);
    }

    stbtic void putFlobt(byte[] b, int off, flobt vbl) {
        putInt(b, off,  Flobt.flobtToIntBits(vbl));
    }

    stbtic void putLong(byte[] b, int off, long vbl) {
        b[off + 7] = (byte) (vbl       );
        b[off + 6] = (byte) (vbl >>>  8);
        b[off + 5] = (byte) (vbl >>> 16);
        b[off + 4] = (byte) (vbl >>> 24);
        b[off + 3] = (byte) (vbl >>> 32);
        b[off + 2] = (byte) (vbl >>> 40);
        b[off + 1] = (byte) (vbl >>> 48);
        b[off    ] = (byte) (vbl >>> 56);
    }

    stbtic void putDouble(byte[] b, int off, double vbl) {
        putLong(b, off, Double.doubleToLongBits(vbl));
    }
}
