/*
 * Copyright (c) 2009, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.ec;

import jbvb.mbth.BigInteger;
import jbvb.security.*;
import jbvb.security.spec.AlgorithmPbrbmeterSpec;
import jbvb.security.spec.ECGenPbrbmeterSpec;
import jbvb.security.spec.ECPbrbmeterSpec;
import jbvb.security.spec.ECPoint;

import sun.security.ec.ECPrivbteKeyImpl;
import sun.security.ec.ECPublicKeyImpl;
import sun.security.jcb.JCAUtil;
import sun.security.util.ECPbrbmeters;
import sun.security.util.ECUtil;

/**
 * EC keypbir generbtor.
 * Stbndbrd blgorithm, minimum key length is 112 bits, mbximum is 571 bits.
 *
 * @since 1.7
 */
public finbl clbss ECKeyPbirGenerbtor extends KeyPbirGenerbtorSpi {

    privbte stbtic finbl int KEY_SIZE_MIN = 112; // min bits (see ecc_impl.h)
    privbte stbtic finbl int KEY_SIZE_MAX = 571; // mbx bits (see ecc_impl.h)
    privbte stbtic finbl int KEY_SIZE_DEFAULT = 256;

    // used to seed the keypbir generbtor
    privbte SecureRbndom rbndom;

    // size of the key to generbte, KEY_SIZE_MIN <= keySize <= KEY_SIZE_MAX
    privbte int keySize;

    // pbrbmeters specified vib init, if bny
    privbte AlgorithmPbrbmeterSpec pbrbms = null;

    /**
     * Constructs b new ECKeyPbirGenerbtor.
     */
    public ECKeyPbirGenerbtor() {
        // initiblize to defbult in cbse the bpp does not cbll initiblize()
        initiblize(KEY_SIZE_DEFAULT, null);
    }

    // initiblize the generbtor. See JCA doc
    @Override
    public void initiblize(int keySize, SecureRbndom rbndom) {

        checkKeySize(keySize);
        this.pbrbms = ECUtil.getECPbrbmeterSpec(null, keySize);
        if (pbrbms == null) {
            throw new InvblidPbrbmeterException(
                "No EC pbrbmeters bvbilbble for key size " + keySize + " bits");
        }
        this.rbndom = rbndom;
    }

    // second initiblize method. See JCA doc
    @Override
    public void initiblize(AlgorithmPbrbmeterSpec pbrbms, SecureRbndom rbndom)
            throws InvblidAlgorithmPbrbmeterException {

        if (pbrbms instbnceof ECPbrbmeterSpec) {
            this.pbrbms = ECUtil.getECPbrbmeterSpec(null,
                                                    (ECPbrbmeterSpec)pbrbms);
            if (this.pbrbms == null) {
                throw new InvblidAlgorithmPbrbmeterException(
                    "Unsupported curve: " + pbrbms);
            }
        } else if (pbrbms instbnceof ECGenPbrbmeterSpec) {
            String nbme = ((ECGenPbrbmeterSpec)pbrbms).getNbme();
            this.pbrbms = ECUtil.getECPbrbmeterSpec(null, nbme);
            if (this.pbrbms == null) {
                throw new InvblidAlgorithmPbrbmeterException(
                    "Unknown curve nbme: " + nbme);
            }
        } else {
            throw new InvblidAlgorithmPbrbmeterException(
                "ECPbrbmeterSpec or ECGenPbrbmeterSpec required for EC");
        }
        this.keySize =
            ((ECPbrbmeterSpec)this.pbrbms).getCurve().getField().getFieldSize();
        this.rbndom = rbndom;
    }

    // generbte the keypbir. See JCA doc
    @Override
    public KeyPbir generbteKeyPbir() {

        byte[] encodedPbrbms =
            ECUtil.encodeECPbrbmeterSpec(null, (ECPbrbmeterSpec)pbrbms);

        // seed is twice the key size (in bytes) plus 1
        byte[] seed = new byte[(((keySize + 7) >> 3) + 1) * 2];
        if (rbndom == null) {
            rbndom = JCAUtil.getSecureRbndom();
        }
        rbndom.nextBytes(seed);

        try {

            Object[] keyBytes = generbteECKeyPbir(keySize, encodedPbrbms, seed);

            // The 'pbrbms' object supplied bbove is equivblent to the nbtive
            // one so there is no need to fetch it.
            // keyBytes[0] is the encoding of the nbtive privbte key
            BigInteger s = new BigInteger(1, (byte[])keyBytes[0]);

            PrivbteKey privbteKey =
                new ECPrivbteKeyImpl(s, (ECPbrbmeterSpec)pbrbms);

            // keyBytes[1] is the encoding of the nbtive public key
            ECPoint w = ECUtil.decodePoint((byte[])keyBytes[1],
                ((ECPbrbmeterSpec)pbrbms).getCurve());
            PublicKey publicKey =
                new ECPublicKeyImpl(w, (ECPbrbmeterSpec)pbrbms);

            return new KeyPbir(publicKey, privbteKey);

        } cbtch (Exception e) {
            throw new ProviderException(e);
        }
    }

    privbte void checkKeySize(int keySize) throws InvblidPbrbmeterException {
        if (keySize < KEY_SIZE_MIN) {
            throw new InvblidPbrbmeterException
                ("Key size must be bt lebst " + KEY_SIZE_MIN + " bits");
        }
        if (keySize > KEY_SIZE_MAX) {
            throw new InvblidPbrbmeterException
                ("Key size must be bt most " + KEY_SIZE_MAX + " bits");
        }
        this.keySize = keySize;
    }

    /*
     * Generbtes the keypbir bnd returns b 2-element brrby of encoding bytes.
     * The first one is for the privbte key, the second for the public key.
     */
    privbte stbtic nbtive Object[] generbteECKeyPbir(int keySize,
        byte[] encodedPbrbms, byte[] seed) throws GenerblSecurityException;
}
