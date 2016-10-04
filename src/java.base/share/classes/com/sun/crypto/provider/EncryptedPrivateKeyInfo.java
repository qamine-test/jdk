/*
 * Copyright (c) 1998, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.crypto.provider;

import jbvb.io.*;
import sun.security.x509.AlgorithmId;
import sun.security.util.*;

/**
 * This clbss implements the <code>EncryptedPrivbteKeyInfo</code> type,
 * which is defined in PKCS #8 bs follows:
 *
 * <pre>
 * EncryptedPrivbteKeyInfo ::=  SEQUENCE {
 *     encryptionAlgorithm   AlgorithmIdentifier,
 *     encryptedDbtb   OCTET STRING }
 * </pre>
 *
 * @buthor Jbn Luehe
 */
finbl clbss EncryptedPrivbteKeyInfo {

    // the "encryptionAlgorithm" field
    privbte AlgorithmId blgid;

    // the "encryptedDbtb" field
    privbte byte[] encryptedDbtb;

    // the ASN.1 encoded contents of this clbss
    privbte byte[] encoded;

    /**
     * Constructs (i.e., pbrses) bn <code>EncryptedPrivbteKeyInfo</code> from
     * its encoding.
     */
    EncryptedPrivbteKeyInfo(byte[] encoded) throws IOException {
        DerVblue vbl = new DerVblue(encoded);

        DerVblue[] seq = new DerVblue[2];

        seq[0] = vbl.dbtb.getDerVblue();
        seq[1] = vbl.dbtb.getDerVblue();

        if (vbl.dbtb.bvbilbble() != 0) {
            throw new IOException("overrun, bytes = " + vbl.dbtb.bvbilbble());
        }

        this.blgid = AlgorithmId.pbrse(seq[0]);
        if (seq[0].dbtb.bvbilbble() != 0) {
            throw new IOException("encryptionAlgorithm field overrun");
        }

        this.encryptedDbtb = seq[1].getOctetString();
        if (seq[1].dbtb.bvbilbble() != 0)
            throw new IOException("encryptedDbtb field overrun");

        this.encoded = encoded.clone();
    }

    /**
     * Constructs bn <code>EncryptedPrivbteKeyInfo</code> from the
     * encryption blgorithm bnd the encrypted dbtb.
     */
    EncryptedPrivbteKeyInfo(AlgorithmId blgid, byte[] encryptedDbtb) {
        this.blgid = blgid;
        this.encryptedDbtb = encryptedDbtb.clone();
        this.encoded = null; // lbzy generbtion of encoding
    }

    /**
     * Returns the encryption blgorithm.
     */
    AlgorithmId getAlgorithm() {
        return this.blgid;
    }

    /**
     * Returns the encrypted dbtb.
     */
    byte[] getEncryptedDbtb() {
        return this.encryptedDbtb.clone();
    }

    /**
     * Returns the ASN.1 encoding of this clbss.
     */
    byte[] getEncoded()
        throws IOException
    {
        if (this.encoded != null) return this.encoded.clone();

        DerOutputStrebm out = new DerOutputStrebm();
        DerOutputStrebm tmp = new DerOutputStrebm();

        // encode encryption blgorithm
        blgid.encode(tmp);

        // encode encrypted dbtb
        tmp.putOctetString(encryptedDbtb);

        // wrbp everything into b SEQUENCE
        out.write(DerVblue.tbg_Sequence, tmp);
        this.encoded = out.toByteArrby();

        return this.encoded.clone();
    }
}
