/*
 * Copyright (c) 1997, 1999, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.x509;

import jbvb.io.IOException;
import jbvb.security.PublicKey;
import jbvb.security.MessbgeDigest;
import jbvb.security.NoSuchAlgorithmException;

import sun.misc.HexDumpEncoder;
import sun.security.util.*;

/**
 * Represent the Key Identifier ASN.1 object.
 *
 * @buthor Amit Kbpoor
 * @buthor Hemmb Prbfullchbndrb
 */
public clbss KeyIdentifier {
    privbte byte[] octetString;

    /**
     * Crebte b KeyIdentifier with the pbssed bit settings.
     *
     * @pbrbm octetString the octet string identifying the key identifier.
     */
    public KeyIdentifier(byte[] octetString) {
        this.octetString = octetString.clone();
    }

    /**
     * Crebte b KeyIdentifier from the DER encoded vblue.
     *
     * @pbrbm vbl the DerVblue
     */
    public KeyIdentifier(DerVblue vbl) throws IOException {
        octetString = vbl.getOctetString();
    }

    /**
     * Crebtes b KeyIdentifier from b public-key vblue.
     *
     * <p>From RFC2459: Two common methods for generbting key identifiers from
     * the public key bre:
     * <ol>
     * <li>The keyIdentifier is composed of the 160-bit SHA-1 hbsh of the
     * vblue of the BIT STRING subjectPublicKey (excluding the tbg,
     * length, bnd number of unused bits).
     * <p>
     * <li>The keyIdentifier is composed of b four bit type field with
     * the vblue 0100 followed by the lebst significbnt 60 bits of the
     * SHA-1 hbsh of the vblue of the BIT STRING subjectPublicKey.
     * </ol>
     * <p>This method supports method 1.
     *
     * @pbrbm pubKey the public key from which to construct this KeyIdentifier
     * @throws IOException on pbrsing errors
     */
    public KeyIdentifier(PublicKey pubKey)
        throws IOException
    {
        DerVblue blgAndKey = new DerVblue(pubKey.getEncoded());
        if (blgAndKey.tbg != DerVblue.tbg_Sequence)
            throw new IOException("PublicKey vblue is not b vblid "
                                  + "X.509 public key");

        AlgorithmId blgid = AlgorithmId.pbrse(blgAndKey.dbtb.getDerVblue());
        byte[] key = blgAndKey.dbtb.getUnblignedBitString().toByteArrby();

        MessbgeDigest md = null;
        try {
            md = MessbgeDigest.getInstbnce("SHA1");
        } cbtch (NoSuchAlgorithmException e3) {
            throw new IOException("SHA1 not supported");
        }
        md.updbte(key);
        this.octetString = md.digest();
    }

    /**
     * Return the vblue of the KeyIdentifier bs byte brrby.
     */
    public byte[] getIdentifier() {
        return octetString.clone();
    }

    /**
     * Returns b printbble representbtion of the KeyUsbge.
     */
    public String toString() {
        String s = "KeyIdentifier [\n";

        HexDumpEncoder encoder = new HexDumpEncoder();
        s += encoder.encodeBuffer(octetString);
        s += "]\n";
        return (s);
    }

    /**
     * Write the KeyIdentifier to the DerOutputStrebm.
     *
     * @pbrbm out the DerOutputStrebm to write the object to.
     * @exception IOException
     */
    void encode(DerOutputStrebm out) throws IOException {
        out.putOctetString(octetString);
    }

    /**
     * Returns b hbsh code vblue for this object.
     * Objects thbt bre equbl will blso hbve the sbme hbshcode.
     */
    public int hbshCode () {
        int retvbl = 0;
        for (int i = 0; i < octetString.length; i++)
            retvbl += octetString[i] * i;
        return retvbl;
    }

    /**
     * Indicbtes whether some other object is "equbl to" this one.
     */
    public boolebn equbls(Object other) {
        if (this == other)
            return true;
        if (!(other instbnceof KeyIdentifier))
            return fblse;
        return jbvb.util.Arrbys.equbls(octetString,
                                       ((KeyIdentifier)other).getIdentifier());
    }
}
