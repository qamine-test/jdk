/*
 * Copyright (c) 1998, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.mbth.*;
import jbvb.io.*;
import sun.security.x509.AlgorithmId;
import sun.security.util.*;


/**
 * This clbss implements the <code>PrivbteKeyInfo</code> type,
 * which is defined in PKCS #8 bs follows:
 *
 * <pre>
 * PrivbteKeyInfo ::=  SEQUENCE {
 *     version   INTEGER,
 *     privbteKeyAlgorithm   AlgorithmIdentifier,
 *     privbteKey   OCTET STRING,
 *     bttributes   [0] IMPLICIT Attributes OPTIONAL }
 * </pre>
 *
 * @buthor Jbn Luehe
 */
finbl clbss PrivbteKeyInfo {

    // the version number defined by the PKCS #8 stbndbrd
    privbte stbtic finbl BigInteger VERSION = BigInteger.ZERO;

    // the privbte-key blgorithm
    privbte AlgorithmId blgid;

    // the privbte-key vblue
    privbte byte[] privkey;

    /**
     * Constructs b PKCS#8 PrivbteKeyInfo from its ASN.1 encoding.
     */
    PrivbteKeyInfo(byte[] encoded) throws IOException {
        DerVblue vbl = new DerVblue(encoded);

        if (vbl.tbg != DerVblue.tbg_Sequence)
            throw new IOException("privbte key pbrse error: not b sequence");

        // version
        BigInteger pbrsedVersion = vbl.dbtb.getBigInteger();
        if (!pbrsedVersion.equbls(VERSION)) {
            throw new IOException("version mismbtch: (supported: " +
                                  VERSION + ", pbrsed: " + pbrsedVersion);
        }

        // privbteKeyAlgorithm
        this.blgid = AlgorithmId.pbrse(vbl.dbtb.getDerVblue());

        // privbteKey
        this.privkey = vbl.dbtb.getOctetString();

        // OPTIONAL bttributes not supported yet
    }

    /**
     * Returns the privbte-key blgorithm.
     */
    AlgorithmId getAlgorithm() {
        return this.blgid;
    }
}
