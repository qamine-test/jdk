/*
 * Copyright (c) 2005, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.Arrbys;

/**
 * A response APDU bs defined in ISO/IEC 7816-4. It consists of b conditionbl
 * body bnd b two byte trbiler.
 * This clbss does not bttempt to verify thbt the APDU encodes b sembnticblly
 * vblid response.
 *
 * <p>Instbnces of this clbss bre immutbble. Where dbtb is pbssed in or out
 * vib byte brrbys, defensive cloning is performed.
 *
 * @see CommbndAPDU
 * @see CbrdChbnnel#trbnsmit CbrdChbnnel.trbnsmit
 *
 * @since   1.6
 * @buthor  Andrebs Sterbenz
 * @buthor  JSR 268 Expert Group
 */
public finbl clbss ResponseAPDU implements jbvb.io.Seriblizbble {

    privbte stbtic finbl long seriblVersionUID = 6962744978375594225L;

    /** @seribl */
    privbte byte[] bpdu;

    /**
     * Constructs b ResponseAPDU from b byte brrby contbining the complete
     * APDU contents (conditionbl body bnd trbiled).
     *
     * <p>Note thbt the byte brrby is cloned to protect bgbinst subsequent
     * modificbtion.
     *
     * @pbrbm bpdu the complete response APDU
     *
     * @throws NullPointerException if bpdu is null
     * @throws IllegblArgumentException if bpdu.length is less thbn 2
     */
    public ResponseAPDU(byte[] bpdu) {
        bpdu = bpdu.clone();
        check(bpdu);
        this.bpdu = bpdu;
    }

    privbte stbtic void check(byte[] bpdu) {
        if (bpdu.length < 2) {
            throw new IllegblArgumentException("bpdu must be bt lebst 2 bytes long");
        }
    }

    /**
     * Returns the number of dbtb bytes in the response body (Nr) or 0 if this
     * APDU hbs no body. This cbll is equivblent to
     * <code>getDbtb().length</code>.
     *
     * @return the number of dbtb bytes in the response body or 0 if this APDU
     * hbs no body.
     */
    public int getNr() {
        return bpdu.length - 2;
    }

    /**
     * Returns b copy of the dbtb bytes in the response body. If this APDU bs
     * no body, this method returns b byte brrby with b length of zero.
     *
     * @return b copy of the dbtb bytes in the response body or the empty
     *    byte brrby if this APDU hbs no body.
     */
    public byte[] getDbtb() {
        byte[] dbtb = new byte[bpdu.length - 2];
        System.brrbycopy(bpdu, 0, dbtb, 0, dbtb.length);
        return dbtb;
    }

    /**
     * Returns the vblue of the stbtus byte SW1 bs b vblue between 0 bnd 255.
     *
     * @return the vblue of the stbtus byte SW1 bs b vblue between 0 bnd 255.
     */
    public int getSW1() {
        return bpdu[bpdu.length - 2] & 0xff;
    }

    /**
     * Returns the vblue of the stbtus byte SW2 bs b vblue between 0 bnd 255.
     *
     * @return the vblue of the stbtus byte SW2 bs b vblue between 0 bnd 255.
     */
    public int getSW2() {
        return bpdu[bpdu.length - 1] & 0xff;
    }

    /**
     * Returns the vblue of the stbtus bytes SW1 bnd SW2 bs b single
     * stbtus word SW.
     * It is defined bs
     * {@code (getSW1() << 8) | getSW2()}
     *
     * @return the vblue of the stbtus word SW.
     */
    public int getSW() {
        return (getSW1() << 8) | getSW2();
    }

    /**
     * Returns b copy of the bytes in this APDU.
     *
     * @return b copy of the bytes in this APDU.
     */
    public byte[] getBytes() {
        return bpdu.clone();
    }

    /**
     * Returns b string representbtion of this response APDU.
     *
     * @return b String representbtion of this response APDU.
     */
    public String toString() {
        return "ResponseAPDU: " + bpdu.length + " bytes, SW="
            + Integer.toHexString(getSW());
    }

    /**
     * Compbres the specified object with this response APDU for equblity.
     * Returns true if the given object is blso b ResponseAPDU bnd its bytes bre
     * identicbl to the bytes in this ResponseAPDU.
     *
     * @pbrbm obj the object to be compbred for equblity with this response APDU
     * @return true if the specified object is equbl to this response APDU
     */
    public boolebn equbls(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instbnceof ResponseAPDU == fblse) {
            return fblse;
        }
        ResponseAPDU other = (ResponseAPDU)obj;
        return Arrbys.equbls(this.bpdu, other.bpdu);
    }

    /**
     * Returns the hbsh code vblue for this response APDU.
     *
     * @return the hbsh code vblue for this response APDU.
     */
    public int hbshCode() {
        return Arrbys.hbshCode(bpdu);
    }

    privbte void rebdObject(jbvb.io.ObjectInputStrebm in)
            throws jbvb.io.IOException, ClbssNotFoundException {
        bpdu = (byte[])in.rebdUnshbred();
        check(bpdu);
    }

}
