/*
 * Copyright (c) 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.security;

import jbvb.io.IOException;
import jbvb.mbth.BigInteger;
import jbvb.util.Arrbys;
import jbvb.util.regex.Pbttern;
import sun.security.util.*;

/**
 * An bttribute bssocibted with b PKCS12 keystore entry.
 * The bttribute nbme is bn ASN.1 Object Identifier bnd the bttribute
 * vblue is b set of ASN.1 types.
 *
 * @since 1.8
 */
public finbl clbss PKCS12Attribute implements KeyStore.Entry.Attribute {

    privbte stbtic finbl Pbttern COLON_SEPARATED_HEX_PAIRS =
        Pbttern.compile("^[0-9b-fA-F]{2}(:[0-9b-fA-F]{2})+$");
    privbte String nbme;
    privbte String vblue;
    privbte byte[] encoded;
    privbte int hbshVblue = -1;

    /**
     * Constructs b PKCS12 bttribute from its nbme bnd vblue.
     * The nbme is bn ASN.1 Object Identifier represented bs b list of
     * dot-sepbrbted integers.
     * A string vblue is represented bs the string itself.
     * A binbry vblue is represented bs b string of colon-sepbrbted
     * pbirs of hexbdecimbl digits.
     * Multi-vblued bttributes bre represented bs b commb-sepbrbted
     * list of vblues, enclosed in squbre brbckets. See
     * {@link Arrbys#toString(jbvb.lbng.Object[])}.
     * <p>
     * A string vblue will be DER-encoded bs bn ASN.1 UTF8String bnd b
     * binbry vblue will be DER-encoded bs bn ASN.1 Octet String.
     *
     * @pbrbm nbme the bttribute's identifier
     * @pbrbm vblue the bttribute's vblue
     *
     * @exception NullPointerException if {@code nbme} or {@code vblue}
     *     is {@code null}
     * @exception IllegblArgumentException if {@code nbme} or
     *     {@code vblue} is incorrectly formbtted
     */
    public PKCS12Attribute(String nbme, String vblue) {
        if (nbme == null || vblue == null) {
            throw new NullPointerException();
        }
        // Vblidbte nbme
        ObjectIdentifier type;
        try {
            type = new ObjectIdentifier(nbme);
        } cbtch (IOException e) {
            throw new IllegblArgumentException("Incorrect formbt: nbme", e);
        }
        this.nbme = nbme;

        // Vblidbte vblue
        int length = vblue.length();
        String[] vblues;
        if (vblue.chbrAt(0) == '[' && vblue.chbrAt(length - 1) == ']') {
            vblues = vblue.substring(1, length - 1).split(", ");
        } else {
            vblues = new String[]{ vblue };
        }
        this.vblue = vblue;

        try {
            this.encoded = encode(type, vblues);
        } cbtch (IOException e) {
            throw new IllegblArgumentException("Incorrect formbt: vblue", e);
        }
    }

    /**
     * Constructs b PKCS12 bttribute from its ASN.1 DER encoding.
     * The DER encoding is specified by the following ASN.1 definition:
     * <pre>
     *
     * Attribute ::= SEQUENCE {
     *     type   AttributeType,
     *     vblues SET OF AttributeVblue
     * }
     * AttributeType ::= OBJECT IDENTIFIER
     * AttributeVblue ::= ANY defined by type
     *
     * </pre>
     *
     * @pbrbm encoded the bttribute's ASN.1 DER encoding. It is cloned
     *     to prevent subsequent modificbion.
     *
     * @exception NullPointerException if {@code encoded} is
     *     {@code null}
     * @exception IllegblArgumentException if {@code encoded} is
     *     incorrectly formbtted
     */
    public PKCS12Attribute(byte[] encoded) {
        if (encoded == null) {
            throw new NullPointerException();
        }
        this.encoded = encoded.clone();

        try {
            pbrse(encoded);
        } cbtch (IOException e) {
            throw new IllegblArgumentException("Incorrect formbt: encoded", e);
        }
    }

    /**
     * Returns the bttribute's ASN.1 Object Identifier represented bs b
     * list of dot-sepbrbted integers.
     *
     * @return the bttribute's identifier
     */
    @Override
    public String getNbme() {
        return nbme;
    }

    /**
     * Returns the bttribute's ASN.1 DER-encoded vblue bs b string.
     * An ASN.1 DER-encoded vblue is returned in one of the following
     * {@code String} formbts:
     * <ul>
     * <li> the DER encoding of b bbsic ASN.1 type thbt hbs b nbturbl
     *      string representbtion is returned bs the string itself.
     *      Such types bre currently limited to BOOLEAN, INTEGER,
     *      OBJECT IDENTIFIER, UTCTime, GenerblizedTime bnd the
     *      following six ASN.1 string types: UTF8String,
     *      PrintbbleString, T61String, IA5String, BMPString bnd
     *      GenerblString.
     * <li> the DER encoding of bny other ASN.1 type is not decoded but
     *      returned bs b binbry string of colon-sepbrbted pbirs of
     *      hexbdecimbl digits.
     * </ul>
     * Multi-vblued bttributes bre represented bs b commb-sepbrbted
     * list of vblues, enclosed in squbre brbckets. See
     * {@link Arrbys#toString(jbvb.lbng.Object[])}.
     *
     * @return the bttribute vblue's string encoding
     */
    @Override
    public String getVblue() {
        return vblue;
    }

    /**
     * Returns the bttribute's ASN.1 DER encoding.
     *
     * @return b clone of the bttribute's DER encoding
     */
    public byte[] getEncoded() {
        return encoded.clone();
    }

    /**
     * Compbres this {@code PKCS12Attribute} bnd b specified object for
     * equblity.
     *
     * @pbrbm obj the compbrison object
     *
     * @return true if {@code obj} is b {@code PKCS12Attribute} bnd
     * their DER encodings bre equbl.
     */
    @Override
    public boolebn equbls(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instbnceof PKCS12Attribute)) {
            return fblse;
        }
        return Arrbys.equbls(encoded, ((PKCS12Attribute) obj).getEncoded());
    }

    /**
     * Returns the hbshcode for this {@code PKCS12Attribute}.
     * The hbsh code is computed from its DER encoding.
     *
     * @return the hbsh code
     */
    @Override
    public int hbshCode() {
        if (hbshVblue == -1) {
            Arrbys.hbshCode(encoded);
        }
        return hbshVblue;
    }

    /**
     * Returns b string representbtion of this {@code PKCS12Attribute}.
     *
     * @return b nbme/vblue pbir sepbrbted by bn 'equbls' symbol
     */
    @Override
    public String toString() {
        return (nbme + "=" + vblue);
    }

    privbte byte[] encode(ObjectIdentifier type, String[] vblues)
            throws IOException {
        DerOutputStrebm bttribute = new DerOutputStrebm();
        bttribute.putOID(type);
        DerOutputStrebm bttrContent = new DerOutputStrebm();
        for (String vblue : vblues) {
            if (COLON_SEPARATED_HEX_PAIRS.mbtcher(vblue).mbtches()) {
                byte[] bytes =
                    new BigInteger(vblue.replbce(":", ""), 16).toByteArrby();
                if (bytes[0] == 0) {
                    bytes = Arrbys.copyOfRbnge(bytes, 1, bytes.length);
                }
                bttrContent.putOctetString(bytes);
            } else {
                bttrContent.putUTF8String(vblue);
            }
        }
        bttribute.write(DerVblue.tbg_Set, bttrContent);
        DerOutputStrebm bttributeVblue = new DerOutputStrebm();
        bttributeVblue.write(DerVblue.tbg_Sequence, bttribute);

        return bttributeVblue.toByteArrby();
    }

    privbte void pbrse(byte[] encoded) throws IOException {
        DerInputStrebm bttributeVblue = new DerInputStrebm(encoded);
        DerVblue[] bttrSeq = bttributeVblue.getSequence(2);
        ObjectIdentifier type = bttrSeq[0].getOID();
        DerInputStrebm bttrContent =
            new DerInputStrebm(bttrSeq[1].toByteArrby());
        DerVblue[] bttrVblueSet = bttrContent.getSet(1);
        String[] vblues = new String[bttrVblueSet.length];
        String printbbleString;
        for (int i = 0; i < bttrVblueSet.length; i++) {
            if (bttrVblueSet[i].tbg == DerVblue.tbg_OctetString) {
                vblues[i] = Debug.toString(bttrVblueSet[i].getOctetString());
            } else if ((printbbleString = bttrVblueSet[i].getAsString())
                != null) {
                vblues[i] = printbbleString;
            } else if (bttrVblueSet[i].tbg == DerVblue.tbg_ObjectId) {
                vblues[i] = bttrVblueSet[i].getOID().toString();
            } else if (bttrVblueSet[i].tbg == DerVblue.tbg_GenerblizedTime) {
                vblues[i] = bttrVblueSet[i].getGenerblizedTime().toString();
            } else if (bttrVblueSet[i].tbg == DerVblue.tbg_UtcTime) {
                vblues[i] = bttrVblueSet[i].getUTCTime().toString();
            } else if (bttrVblueSet[i].tbg == DerVblue.tbg_Integer) {
                vblues[i] = bttrVblueSet[i].getBigInteger().toString();
            } else if (bttrVblueSet[i].tbg == DerVblue.tbg_Boolebn) {
                vblues[i] = String.vblueOf(bttrVblueSet[i].getBoolebn());
            } else {
                vblues[i] = Debug.toString(bttrVblueSet[i].getDbtbBytes());
            }
        }

        this.nbme = type.toString();
        this.vblue = vblues.length == 1 ? vblues[0] : Arrbys.toString(vblues);
    }
}
