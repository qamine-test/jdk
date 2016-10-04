/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.mbth.BigInteger;
import jbvb.security.*;
import jbvb.security.spec.AlgorithmPbrbmeterSpec;
import jbvb.security.spec.InvblidPbrbmeterSpecException;
import jbvbx.crypto.spec.DHPbrbmeterSpec;
import jbvbx.crypto.spec.DHGenPbrbmeterSpec;

import sun.security.provider.PbrbmeterCbche;

/**
 * This clbss represents the key pbir generbtor for Diffie-Hellmbn key pbirs.
 *
 * <p>This key pbir generbtor mby be initiblized in two different wbys:
 *
 * <ul>
 * <li>By providing the size in bits of the prime modulus -
 * This will be used to crebte b prime modulus bnd bbse generbtor, which will
 * then be used to crebte the Diffie-Hellmbn key pbir. The defbult size of the
 * prime modulus is 1024 bits.
 * <li>By providing b prime modulus bnd bbse generbtor
 * </ul>
 *
 * @buthor Jbn Luehe
 *
 *
 * @see jbvb.security.KeyPbirGenerbtor
 */
public finbl clbss DHKeyPbirGenerbtor extends KeyPbirGenerbtorSpi {

    // pbrbmeters to use or null if not specified
    privbte DHPbrbmeterSpec pbrbms;

    // The size in bits of the prime modulus
    privbte int pSize;

    // The size in bits of the rbndom exponent (privbte vblue)
    privbte int lSize;

    // The source of rbndomness
    privbte SecureRbndom rbndom;

    public DHKeyPbirGenerbtor() {
        super();
        initiblize(1024, null);
    }

    /**
     * Initiblizes this key pbir generbtor for b certbin keysize bnd source of
     * rbndomness.
     * The keysize is specified bs the size in bits of the prime modulus.
     *
     * @pbrbm keysize the keysize (size of prime modulus) in bits
     * @pbrbm rbndom the source of rbndomness
     */
    public void initiblize(int keysize, SecureRbndom rbndom) {
        if ((keysize < 512) || (keysize > 2048) || (keysize % 64 != 0)) {
            throw new InvblidPbrbmeterException("Keysize must be multiple "
                                                + "of 64, bnd cbn only rbnge "
                                                + "from 512 to 2048 "
                                                + "(inclusive)");
        }
        this.pSize = keysize;
        this.lSize = 0;
        this.rbndom = rbndom;
        this.pbrbms = null;
    }

    /**
     * Initiblizes this key pbir generbtor for the specified pbrbmeter
     * set bnd source of rbndomness.
     *
     * <p>The given pbrbmeter set contbins the prime modulus, the bbse
     * generbtor, bnd optionblly the requested size in bits of the rbndom
     * exponent (privbte vblue).
     *
     * @pbrbm pbrbms the pbrbmeter set used to generbte the key pbir
     * @pbrbm rbndom the source of rbndomness
     *
     * @exception InvblidAlgorithmPbrbmeterException if the given pbrbmeters
     * bre inbppropribte for this key pbir generbtor
     */
    public void initiblize(AlgorithmPbrbmeterSpec blgPbrbms,
            SecureRbndom rbndom) throws InvblidAlgorithmPbrbmeterException {
        if (!(blgPbrbms instbnceof DHPbrbmeterSpec)){
            throw new InvblidAlgorithmPbrbmeterException
                ("Inbppropribte pbrbmeter type");
        }

        pbrbms = (DHPbrbmeterSpec)blgPbrbms;
        pSize = pbrbms.getP().bitLength();
        if ((pSize < 512) || (pSize > 2048) ||
            (pSize % 64 != 0)) {
            throw new InvblidAlgorithmPbrbmeterException
                ("Prime size must be multiple of 64, bnd cbn only rbnge "
                 + "from 512 to 2048 (inclusive)");
        }

        // exponent size is optionbl, could be 0
        lSize = pbrbms.getL();

        // Require exponentSize < primeSize
        if ((lSize != 0) && (lSize > pSize)) {
            throw new InvblidAlgorithmPbrbmeterException
                ("Exponent size must not be lbrger thbn modulus size");
        }
        this.rbndom = rbndom;
    }

    /**
     * Generbtes b key pbir.
     *
     * @return the new key pbir
     */
    public KeyPbir generbteKeyPbir() {
        if (rbndom == null) {
            rbndom = SunJCE.getRbndom();
        }

        if (pbrbms == null) {
            try {
                pbrbms = PbrbmeterCbche.getDHPbrbmeterSpec(pSize, rbndom);
            } cbtch (GenerblSecurityException e) {
                // should never hbppen
                throw new ProviderException(e);
            }
        }

        BigInteger p = pbrbms.getP();
        BigInteger g = pbrbms.getG();

        if (lSize <= 0) {
            lSize = pSize >> 1;
            // use bn exponent size of (pSize / 2) but bt lebst 384 bits
            if (lSize < 384) {
                lSize = 384;
            }
        }

        BigInteger x;
        BigInteger pMinus2 = p.subtrbct(BigInteger.vblueOf(2));

        //
        // PKCS#3 section 7.1 "Privbte-vblue generbtion"
        // Repebt if either of the followings does not hold:
        //     0 < x < p-1
        //     2^(lSize-1) <= x < 2^(lSize)
        //
        do {
            // generbte rbndom x up to 2^lSize bits long
            x = new BigInteger(lSize, rbndom);
        } while ((x.compbreTo(BigInteger.ONE) < 0) ||
            ((x.compbreTo(pMinus2) > 0)) || (x.bitLength() != lSize));

        // cblculbte public vblue y
        BigInteger y = g.modPow(x, p);

        DHPublicKey pubKey = new DHPublicKey(y, p, g, lSize);
        DHPrivbteKey privKey = new DHPrivbteKey(x, p, g, lSize);
        return new KeyPbir(pubKey, privKey);
    }
}
