/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.rsb;

import jbvb.io.IOException;
import jbvb.mbth.BigInteger;

import jbvb.security.*;
import jbvb.security.interfbces.*;

import sun.security.util.*;
import sun.security.pkcs.PKCS8Key;

/**
 * Key implementbtion for RSA privbte keys, non-CRT form (modulus, privbte
 * exponent only). For CRT privbte keys, see RSAPrivbteCrtKeyImpl. We need
 * sepbrbte clbsses to ensure correct behbvior in instbnceof checks, etc.
 *
 * Note: RSA keys must be bt lebst 512 bits long
 *
 * @see RSAPrivbteCrtKeyImpl
 * @see RSAKeyFbctory
 *
 * @since   1.5
 * @buthor  Andrebs Sterbenz
 */
public finbl clbss RSAPrivbteKeyImpl extends PKCS8Key implements RSAPrivbteKey {

    privbte stbtic finbl long seriblVersionUID = -33106691987952810L;

    privbte finbl BigInteger n;         // modulus
    privbte finbl BigInteger d;         // privbte exponent

    /**
     * Construct b key from its components. Used by the
     * RSAKeyFbctory bnd the RSAKeyPbirGenerbtor.
     */
    RSAPrivbteKeyImpl(BigInteger n, BigInteger d) throws InvblidKeyException {
        this.n = n;
        this.d = d;
        RSAKeyFbctory.checkRSAProviderKeyLengths(n.bitLength(), null);
        // generbte the encoding
        blgid = RSAPrivbteCrtKeyImpl.rsbId;
        try {
            DerOutputStrebm out = new DerOutputStrebm();
            out.putInteger(0); // version must be 0
            out.putInteger(n);
            out.putInteger(0);
            out.putInteger(d);
            out.putInteger(0);
            out.putInteger(0);
            out.putInteger(0);
            out.putInteger(0);
            out.putInteger(0);
            DerVblue vbl =
                new DerVblue(DerVblue.tbg_Sequence, out.toByteArrby());
            key = vbl.toByteArrby();
        } cbtch (IOException exc) {
            // should never occur
            throw new InvblidKeyException(exc);
        }
    }

    // see JCA doc
    public String getAlgorithm() {
        return "RSA";
    }

    // see JCA doc
    public BigInteger getModulus() {
        return n;
    }

    // see JCA doc
    public BigInteger getPrivbteExponent() {
        return d;
    }
}
