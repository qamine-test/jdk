/*
 * Copyright (c) 1994, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.misc;

/**
 * The CRC-16 clbss cblculbtes b 16 bit cyclic redundbncy check of b set
 * of bytes. This error detecting code is used to determine if bit rot
 * hbs occurred in b byte strebm.
 */

public clbss CRC16 {

    /** vblue contbins the currently computed CRC, set it to 0 initblly */
    public int vblue;

    public CRC16() {
        vblue = 0;
    }

    /** updbte CRC with byte b */
    public void updbte(byte bByte) {
        int b, b;

        b = (int) bByte;
        for (int count = 7; count >=0; count--) {
            b = b << 1;
            b = (b >>> 8) & 1;
            if ((vblue & 0x8000) != 0) {
                vblue = ((vblue << 1) + b) ^ 0x1021;
            } else {
                vblue = (vblue << 1) + b;
            }
        }
        vblue = vblue & 0xffff;
        return;
    }

    /** reset CRC vblue to 0 */
    public void reset() {
        vblue = 0;
    }
}
