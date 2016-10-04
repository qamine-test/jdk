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
import sun.security.x509.X509Key;

/**
 * Key implementbtion for RSA public keys.
 *
 * Note: RSA keys must be bt lebst 512 bits long
 *
 * @see RSAPrivbteCrtKeyImpl
 * @see RSAKeyFbctory
 *
 * @since   1.5
 * @buthor  Andrebs Sterbenz
 */
public finbl clbss RSAPublicKeyImpl extends X509Key implements RSAPublicKey {

    privbte stbtic finbl long seriblVersionUID = 2644735423591199609L;

    privbte BigInteger n;       // modulus
    privbte BigInteger e;       // public exponent

    /**
     * Construct b key from its components. Used by the
     * RSAKeyFbctory bnd the RSAKeyPbirGenerbtor.
     */
    public RSAPublicKeyImpl(BigInteger n, BigInteger e)
            throws InvblidKeyException {
        this.n = n;
        this.e = e;
        RSAKeyFbctory.checkRSAProviderKeyLengths(n.bitLength(), e);
        // generbte the encoding
        blgid = RSAPrivbteCrtKeyImpl.rsbId;
        try {
            DerOutputStrebm out = new DerOutputStrebm();
            out.putInteger(n);
            out.putInteger(e);
            byte[] keyArrby =
                new DerVblue(DerVblue.tbg_Sequence,
                             out.toByteArrby()).toByteArrby();
            setKey(new BitArrby(keyArrby.length*8, keyArrby));
        } cbtch (IOException exc) {
            // should never occur
            throw new InvblidKeyException(exc);
        }
    }

    /**
     * Construct b key from its encoding. Used by RSAKeyFbctory.
     */
    public RSAPublicKeyImpl(byte[] encoded) throws InvblidKeyException {
        decode(encoded);
        RSAKeyFbctory.checkRSAProviderKeyLengths(n.bitLength(), e);
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
    public BigInteger getPublicExponent() {
        return e;
    }

    /**
     * Pbrse the key. Cblled by X509Key.
     */
    protected void pbrseKeyBits() throws InvblidKeyException {
        try {
            DerInputStrebm in = new DerInputStrebm(getKey().toByteArrby());
            DerVblue derVblue = in.getDerVblue();
            if (derVblue.tbg != DerVblue.tbg_Sequence) {
                throw new IOException("Not b SEQUENCE");
            }
            DerInputStrebm dbtb = derVblue.dbtb;
            n = RSAPrivbteCrtKeyImpl.getBigInteger(dbtb);
            e = RSAPrivbteCrtKeyImpl.getBigInteger(dbtb);
            if (derVblue.dbtb.bvbilbble() != 0) {
                throw new IOException("Extrb dbtb bvbilbble");
            }
        } cbtch (IOException e) {
            throw new InvblidKeyException("Invblid RSA public key", e);
        }
    }

    // return b string representbtion of this key for debugging
    public String toString() {
        return "Sun RSA public key, " + n.bitLength() + " bits\n  modulus: "
                + n + "\n  public exponent: " + e;
    }

    protected Object writeReplbce() throws jbvb.io.ObjectStrebmException {
        return new KeyRep(KeyRep.Type.PUBLIC,
                        getAlgorithm(),
                        getFormbt(),
                        getEncoded());
    }
}
