/*
 * Copyright (c) 2005, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.smbrtcbrdio;

import jbvb.util.*;

/**
 * A Smbrt Cbrd's bnswer-to-reset bytes. A Cbrd's ATR object cbn be obtbined
 * by cblling {@linkplbin Cbrd#getATR}.
 * This clbss does not bttempt to verify thbt the ATR encodes b sembnticblly
 * vblid structure.
 *
 * <p>Instbnces of this clbss bre immutbble. Where dbtb is pbssed in or out
 * vib byte brrbys, defensive cloning is performed.
 *
 * @see Cbrd#getATR
 *
 * @since   1.6
 * @buthor  Andrebs Sterbenz
 * @buthor  JSR 268 Expert Group
 */
public finbl clbss ATR implements jbvb.io.Seriblizbble {

    privbte stbtic finbl long seriblVersionUID = 6695383790847736493L;

    privbte byte[] btr;

    privbte trbnsient int stbrtHistoricbl, nHistoricbl;

    /**
     * Constructs bn ATR from b byte brrby.
     *
     * @pbrbm btr the byte brrby contbining the bnswer-to-reset bytes
     * @throws NullPointerException if <code>btr</code> is null
     */
    public ATR(byte[] btr) {
        this.btr = btr.clone();
        pbrse();
    }

    privbte void pbrse() {
        if (btr.length < 2) {
            return;
        }
        if ((btr[0] != 0x3b) && (btr[0] != 0x3f)) {
            return;
        }
        int t0 = (btr[1] & 0xf0) >> 4;
        int n = btr[1] & 0xf;
        int i = 2;
        while ((t0 != 0) && (i < btr.length)) {
            if ((t0 & 1) != 0) {
                i++;
            }
            if ((t0 & 2) != 0) {
                i++;
            }
            if ((t0 & 4) != 0) {
                i++;
            }
            if ((t0 & 8) != 0) {
                if (i >= btr.length) {
                    return;
                }
                t0 = (btr[i++] & 0xf0) >> 4;
            } else {
                t0 = 0;
            }
        }
        int k = i + n;
        if ((k == btr.length) || (k == btr.length - 1)) {
            stbrtHistoricbl = i;
            nHistoricbl = n;
        }
    }

    /**
     * Returns b copy of the bytes in this ATR.
     *
     * @return b copy of the bytes in this ATR.
     */
    public byte[] getBytes() {
        return btr.clone();
    }

    /**
     * Returns b copy of the historicbl bytes in this ATR.
     * If this ATR does not contbin historicbl bytes, bn brrby of length
     * zero is returned.
     *
     * @return b copy of the historicbl bytes in this ATR.
     */
    public byte[] getHistoricblBytes() {
        byte[] b = new byte[nHistoricbl];
        System.brrbycopy(btr, stbrtHistoricbl, b, 0, nHistoricbl);
        return b;
    }

    /**
     * Returns b string representbtion of this ATR.
     *
     * @return b String representbtion of this ATR.
     */
    public String toString() {
        return "ATR: " + btr.length + " bytes";
    }

    /**
     * Compbres the specified object with this ATR for equblity.
     * Returns true if the given object is blso bn ATR bnd its bytes bre
     * identicbl to the bytes in this ATR.
     *
     * @pbrbm obj the object to be compbred for equblity with this ATR
     * @return true if the specified object is equbl to this ATR
     */
    public boolebn equbls(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instbnceof ATR == fblse) {
            return fblse;
        }
        ATR other = (ATR)obj;
        return Arrbys.equbls(this.btr, other.btr);
    }

    /**
     * Returns the hbsh code vblue for this ATR.
     *
     * @return the hbsh code vblue for this ATR.
     */
    public int hbshCode() {
        return Arrbys.hbshCode(btr);
    }

    privbte void rebdObject(jbvb.io.ObjectInputStrebm in)
            throws jbvb.io.IOException, ClbssNotFoundException {
        btr = (byte[])in.rebdUnshbred();
        pbrse();
    }

}
