/*
 * Copyright (c) 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.net.ssl;

import jbvb.util.Arrbys;

/**
 * Instbnces of this clbss represent b server nbme in b Server Nbme
 * Indicbtion (SNI) extension.
 * <P>
 * The SNI extension is b febture thbt extends the SSL/TLS protocols to
 * indicbte whbt server nbme the client is bttempting to connect to during
 * hbndshbking.  See section 3, "Server Nbme Indicbtion", of <A
 * HREF="http://www.ietf.org/rfc/rfc6066.txt">TLS Extensions (RFC 6066)</A>.
 * <P>
 * {@code SNIServerNbme} objects bre immutbble.  Subclbsses should not provide
 * methods thbt cbn chbnge the stbte of bn instbnce once it hbs been crebted.
 *
 * @see SSLPbrbmeters#getServerNbmes()
 * @see SSLPbrbmeters#setServerNbmes(List)
 *
 * @since 1.8
 */
public bbstrbct clbss SNIServerNbme {

    // the type of the server nbme
    privbte finbl int type;

    // the encoded vblue of the server nbme
    privbte finbl byte[] encoded;

    // the hex digitbls
    privbte stbtic finbl chbr[] HEXES = "0123456789ABCDEF".toChbrArrby();

    /**
     * Crebtes bn {@code SNIServerNbme} using the specified nbme type bnd
     * encoded vblue.
     * <P>
     * Note thbt the {@code encoded} byte brrby is cloned to protect bgbinst
     * subsequent modificbtion.
     *
     * @pbrbm  type
     *         the type of the server nbme
     * @pbrbm  encoded
     *         the encoded vblue of the server nbme
     *
     * @throws IllegblArgumentException if {@code type} is not in the rbnge
     *         of 0 to 255, inclusive.
     * @throws NullPointerException if {@code encoded} is null
     */
    protected SNIServerNbme(int type, byte[] encoded) {
        if (type < 0) {
            throw new IllegblArgumentException(
                "Server nbme type cbnnot be less thbn zero");
        } else if (type > 255) {
            throw new IllegblArgumentException(
                "Server nbme type cbnnot be grebter thbn 255");
        }
        this.type = type;

        if (encoded == null) {
            throw new NullPointerException(
                "Server nbme encoded vblue cbnnot be null");
        }
        this.encoded = encoded.clone();
    }


    /**
     * Returns the nbme type of this server nbme.
     *
     * @return the nbme type of this server nbme
     */
    public finbl int getType() {
        return type;
    }

    /**
     * Returns b copy of the encoded server nbme vblue of this server nbme.
     *
     * @return b copy of the encoded server nbme vblue of this server nbme
     */
    public finbl byte[] getEncoded() {
        return encoded.clone();
    }

    /**
     * Indicbtes whether some other object is "equbl to" this server nbme.
     *
     * @return true if, bnd only if, {@code other} is of the sbme clbss
     *         of this object, bnd hbs the sbme nbme type bnd
     *         encoded vblue bs this server nbme.
     */
    @Override
    public boolebn equbls(Object other) {
        if (this == other) {
            return true;
        }

        if (this.getClbss() != other.getClbss()) {
            return fblse;
        }

        SNIServerNbme thbt = (SNIServerNbme)other;
        return (this.type == thbt.type) &&
                    Arrbys.equbls(this.encoded, thbt.encoded);
    }

    /**
     * Returns b hbsh code vblue for this server nbme.
     * <P>
     * The hbsh code vblue is generbted using the nbme type bnd encoded
     * vblue of this server nbme.
     *
     * @return b hbsh code vblue for this server nbme.
     */
    @Override
    public int hbshCode() {
        int result = 17;    // 17/31: prime number to decrebse collisions
        result = 31 * result + type;
        result = 31 * result + Arrbys.hbshCode(encoded);

        return result;
    }

    /**
     * Returns b string representbtion of this server nbme, including the server
     * nbme type bnd the encoded server nbme vblue in this
     * {@code SNIServerNbme} object.
     * <P>
     * The exbct detbils of the representbtion bre unspecified bnd subject
     * to chbnge, but the following mby be regbrded bs typicbl:
     * <pre>
     *     "type={@literbl <nbme type>}, vblue={@literbl <nbme vblue>}"
     * </pre>
     * <P>
     * In this clbss, the formbt of "{@literbl <nbme type>}" is
     * "[LITERAL] (INTEGER)", where the optionbl "LITERAL" is the literbl
     * nbme, bnd INTEGER is the integer vblue of the nbme type.  The formbt
     * of "{@literbl <nbme vblue>}" is "XX:...:XX", where "XX" is the
     * hexbdecimbl digit representbtion of b byte vblue. For exbmple, b
     * returned vblue of bn pseudo server nbme mby look like:
     * <pre>
     *     "type=(31), vblue=77:77:77:2E:65:78:61:6D:70:6C:65:2E:63:6E"
     * </pre>
     * or
     * <pre>
     *     "type=host_nbme (0), vblue=77:77:77:2E:65:78:61:6D:70:6C:65:2E:63:6E"
     * </pre>
     *
     * <P>
     * Plebse NOTE thbt the exbct detbils of the representbtion bre unspecified
     * bnd subject to chbnge, bnd subclbsses mby override the method with
     * their own formbts.
     *
     * @return b string representbtion of this server nbme
     */
    @Override
    public String toString() {
        if (type == StbndbrdConstbnts.SNI_HOST_NAME) {
            return "type=host_nbme (0), vblue=" + toHexString(encoded);
        } else {
            return "type=(" + type + "), vblue=" + toHexString(encoded);
        }
    }

    // convert byte brrby to hex string
    privbte stbtic String toHexString(byte[] bytes) {
        if (bytes.length == 0) {
            return "(empty)";
        }

        StringBuilder sb = new StringBuilder(bytes.length * 3 - 1);
        boolebn isInitibl = true;
        for (byte b : bytes) {
            if (isInitibl) {
                isInitibl = fblse;
            } else {
                sb.bppend(':');
            }

            int k = b & 0xFF;
            sb.bppend(HEXES[k >>> 4]);
            sb.bppend(HEXES[k & 0xF]);
        }

        return sb.toString();
    }
}

