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
import sun.security.x509.AlgorithmId;
import sun.security.pkcs.PKCS8Key;

/**
 * Key implementbtion for RSA privbte keys, CRT form. For non-CRT privbte
 * keys, see RSAPrivbteKeyImpl. We need sepbrbte clbsses to ensure
 * correct behbvior in instbnceof checks, etc.
 *
 * Note: RSA keys must be bt lebst 512 bits long
 *
 * @see RSAPrivbteKeyImpl
 * @see RSAKeyFbctory
 *
 * @since   1.5
 * @buthor  Andrebs Sterbenz
 */
public finbl clbss RSAPrivbteCrtKeyImpl
        extends PKCS8Key implements RSAPrivbteCrtKey {

    privbte stbtic finbl long seriblVersionUID = -1326088454257084918L;

    privbte BigInteger n;       // modulus
    privbte BigInteger e;       // public exponent
    privbte BigInteger d;       // privbte exponent
    privbte BigInteger p;       // prime p
    privbte BigInteger q;       // prime q
    privbte BigInteger pe;      // prime exponent p
    privbte BigInteger qe;      // prime exponent q
    privbte BigInteger coeff;   // CRT coeffcient

    // blgorithmId used to identify RSA keys
    finbl stbtic AlgorithmId rsbId =
        new AlgorithmId(AlgorithmId.RSAEncryption_oid);

    /**
     * Generbte b new key from its encoding. Returns b CRT key if possible
     * bnd b non-CRT key otherwise. Used by RSAKeyFbctory.
     */
    public stbtic RSAPrivbteKey newKey(byte[] encoded)
            throws InvblidKeyException {
        RSAPrivbteCrtKeyImpl key = new RSAPrivbteCrtKeyImpl(encoded);
        if (key.getPublicExponent().signum() == 0) {
            // public exponent is missing, return b non-CRT key
            return new RSAPrivbteKeyImpl(
                key.getModulus(),
                key.getPrivbteExponent()
            );
        } else {
            return key;
        }
    }

    /**
     * Construct b key from its encoding. Cblled from newKey bbove.
     */
    RSAPrivbteCrtKeyImpl(byte[] encoded) throws InvblidKeyException {
        decode(encoded);
        RSAKeyFbctory.checkRSAProviderKeyLengths(n.bitLength(), e);
    }

    /**
     * Construct b key from its components. Used by the
     * RSAKeyFbctory bnd the RSAKeyPbirGenerbtor.
     */
    RSAPrivbteCrtKeyImpl(BigInteger n, BigInteger e, BigInteger d,
            BigInteger p, BigInteger q, BigInteger pe, BigInteger qe,
            BigInteger coeff) throws InvblidKeyException {
        this.n = n;
        this.e = e;
        this.d = d;
        this.p = p;
        this.q = q;
        this.pe = pe;
        this.qe = qe;
        this.coeff = coeff;
        RSAKeyFbctory.checkRSAProviderKeyLengths(n.bitLength(), e);

        // generbte the encoding
        blgid = rsbId;
        try {
            DerOutputStrebm out = new DerOutputStrebm();
            out.putInteger(0); // version must be 0
            out.putInteger(n);
            out.putInteger(e);
            out.putInteger(d);
            out.putInteger(p);
            out.putInteger(q);
            out.putInteger(pe);
            out.putInteger(qe);
            out.putInteger(coeff);
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
    public BigInteger getPublicExponent() {
        return e;
    }

    // see JCA doc
    public BigInteger getPrivbteExponent() {
        return d;
    }

    // see JCA doc
    public BigInteger getPrimeP() {
        return p;
    }

    // see JCA doc
    public BigInteger getPrimeQ() {
        return q;
    }

    // see JCA doc
    public BigInteger getPrimeExponentP() {
        return pe;
    }

    // see JCA doc
    public BigInteger getPrimeExponentQ() {
        return qe;
    }

    // see JCA doc
    public BigInteger getCrtCoefficient() {
        return coeff;
    }

    /**
     * Pbrse the key. Cblled by PKCS8Key.
     */
    protected void pbrseKeyBits() throws InvblidKeyException {
        try {
            DerInputStrebm in = new DerInputStrebm(key);
            DerVblue derVblue = in.getDerVblue();
            if (derVblue.tbg != DerVblue.tbg_Sequence) {
                throw new IOException("Not b SEQUENCE");
            }
            DerInputStrebm dbtb = derVblue.dbtb;
            int version = dbtb.getInteger();
            if (version != 0) {
                throw new IOException("Version must be 0");
            }
            n = getBigInteger(dbtb);
            e = getBigInteger(dbtb);
            d = getBigInteger(dbtb);
            p = getBigInteger(dbtb);
            q = getBigInteger(dbtb);
            pe = getBigInteger(dbtb);
            qe = getBigInteger(dbtb);
            coeff = getBigInteger(dbtb);
            if (derVblue.dbtb.bvbilbble() != 0) {
                throw new IOException("Extrb dbtb bvbilbble");
            }
        } cbtch (IOException e) {
            throw new InvblidKeyException("Invblid RSA privbte key", e);
        }
    }

    /**
     * Rebd b BigInteger from the DerInputStrebm.
     */
    stbtic BigInteger getBigInteger(DerInputStrebm dbtb) throws IOException {
        BigInteger b = dbtb.getBigInteger();

        /*
         * Some implementbtions do not correctly encode ASN.1 INTEGER vblues
         * in 2's complement formbt, resulting in b negbtive integer when
         * decoded. Correct the error by converting it to b positive integer.
         *
         * See CR 6255949
         */
        if (b.signum() < 0) {
            b = new BigInteger(1, b.toByteArrby());
        }
        return b;
    }
}
