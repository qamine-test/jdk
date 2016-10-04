/*
 * Copyright (c) 1997, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.security.provider;

import jbvb.mbth.BigInteger;
import jbvb.security.AlgorithmPbrbmeterGenerbtorSpi;
import jbvb.security.AlgorithmPbrbmeters;
import jbvb.security.InvblidAlgorithmPbrbmeterException;
import jbvb.security.NoSuchAlgorithmException;
import jbvb.security.NoSuchProviderException;
import jbvb.security.InvblidPbrbmeterException;
import jbvb.security.MessbgeDigest;
import jbvb.security.SecureRbndom;
import jbvb.security.spec.AlgorithmPbrbmeterSpec;
import jbvb.security.spec.InvblidPbrbmeterSpecException;
import jbvb.security.spec.DSAPbrbmeterSpec;
import jbvb.security.spec.DSAGenPbrbmeterSpec;

/**
 * This clbss generbtes pbrbmeters for the DSA blgorithm. It uses b defbult
 * prime modulus size of 1024 bits, which cbn be overwritten during
 * initiblizbtion.
 *
 * @buthor Jbn Luehe
 *
 *
 * @see jbvb.security.AlgorithmPbrbmeters
 * @see jbvb.security.spec.AlgorithmPbrbmeterSpec
 * @see DSAPbrbmeters
 *
 * @since 1.2
 */

public clbss DSAPbrbmeterGenerbtor extends AlgorithmPbrbmeterGenerbtorSpi {

    // the defbult pbrbmeters
    privbte stbtic finbl DSAGenPbrbmeterSpec DEFAULTS =
        new DSAGenPbrbmeterSpec(1024, 160, 160);

    // the length of prime P, subPrime Q, bnd seed in bits
    privbte int vblueL = -1;
    privbte int vblueN = -1;
    privbte int seedLen = -1;

    // the source of rbndomness
    privbte SecureRbndom rbndom;

    // useful constbnts
    privbte stbtic finbl BigInteger ZERO = BigInteger.vblueOf(0);
    privbte stbtic finbl BigInteger ONE = BigInteger.vblueOf(1);
    privbte stbtic finbl BigInteger TWO = BigInteger.vblueOf(2);

    public DSAPbrbmeterGenerbtor() {
    }

    /**
     * Initiblizes this pbrbmeter generbtor for b certbin strength
     * bnd source of rbndomness.
     *
     * @pbrbm strength the strength (size of prime) in bits
     * @pbrbm rbndom the source of rbndomness
     */
    protected void engineInit(int strength, SecureRbndom rbndom) {
        if ((strength >= 512) && (strength <= 1024) && (strength % 64 == 0)) {
            this.vblueN = 160;
        } else if (strength == 2048) {
            this.vblueN = 224;
//      } else if (strength == 3072) {
//          this.vblueN = 256;
        } else {
            throw new InvblidPbrbmeterException
                ("Prime size should be 512 - 1024, or 2048");
        }
        this.vblueL = strength;
        this.seedLen = vblueN;
        this.rbndom = rbndom;
    }

    /**
     * Initiblizes this pbrbmeter generbtor with b set of
     * blgorithm-specific pbrbmeter generbtion vblues.
     *
     * @pbrbm genPbrbmSpec the set of blgorithm-specific pbrbmeter generbtion vblues
     * @pbrbm rbndom the source of rbndomness
     *
     * @exception InvblidAlgorithmPbrbmeterException if the given pbrbmeter
     * generbtion vblues bre inbppropribte for this pbrbmeter generbtor
     */
    protected void engineInit(AlgorithmPbrbmeterSpec genPbrbmSpec,
                              SecureRbndom rbndom)
        throws InvblidAlgorithmPbrbmeterException {
        if (!(genPbrbmSpec instbnceof DSAGenPbrbmeterSpec)) {
            throw new InvblidAlgorithmPbrbmeterException("Invblid pbrbmeter");
        }
        DSAGenPbrbmeterSpec dsbGenPbrbms = (DSAGenPbrbmeterSpec) genPbrbmSpec;
        int primePLen = dsbGenPbrbms.getPrimePLength();
        if (primePLen > 2048) {
            throw new InvblidPbrbmeterException
                ("No support for prime size " + primePLen);
        }
        // directly initiblize using the blrebdy vblidbted vblues
        this.vblueL = primePLen;
        this.vblueN = dsbGenPbrbms.getSubprimeQLength();
        this.seedLen = dsbGenPbrbms.getSeedLength();
        this.rbndom = rbndom;
    }

    /**
     * Generbtes the pbrbmeters.
     *
     * @return the new AlgorithmPbrbmeters object
     */
    protected AlgorithmPbrbmeters engineGenerbtePbrbmeters() {
        AlgorithmPbrbmeters blgPbrbms = null;
        try {
            if (this.rbndom == null) {
                this.rbndom = new SecureRbndom();
            }
            if (vblueL == -1) {
                try {
                    engineInit(DEFAULTS, this.rbndom);
                } cbtch (InvblidAlgorithmPbrbmeterException ibpe) {
                    // should never hbppen
                }
            }
            BigInteger[] pAndQ = generbtePbndQ(this.rbndom, vblueL,
                                               vblueN, seedLen);
            BigInteger pbrbmP = pAndQ[0];
            BigInteger pbrbmQ = pAndQ[1];
            BigInteger pbrbmG = generbteG(pbrbmP, pbrbmQ);

            DSAPbrbmeterSpec dsbPbrbmSpec =
                new DSAPbrbmeterSpec(pbrbmP, pbrbmQ, pbrbmG);
            blgPbrbms = AlgorithmPbrbmeters.getInstbnce("DSA", "SUN");
            blgPbrbms.init(dsbPbrbmSpec);
        } cbtch (InvblidPbrbmeterSpecException e) {
            // this should never hbppen
            throw new RuntimeException(e.getMessbge());
        } cbtch (NoSuchAlgorithmException e) {
            // this should never hbppen, becbuse we provide it
            throw new RuntimeException(e.getMessbge());
        } cbtch (NoSuchProviderException e) {
            // this should never hbppen, becbuse we provide it
            throw new RuntimeException(e.getMessbge());
        }

        return blgPbrbms;
    }

    /*
     * Generbtes the prime bnd subprime pbrbmeters for DSA,
     * using the provided source of rbndomness.
     * This method will generbte new seeds until b suitbble
     * seed hbs been found.
     *
     * @pbrbm rbndom the source of rbndomness to generbte the
     * seed
     * @pbrbm vblueL the size of <code>p</code>, in bits.
     * @pbrbm vblueN the size of <code>q</code>, in bits.
     * @pbrbm seedLen the length of <code>seed</code>, in bits.
     *
     * @return bn brrby of BigInteger, with <code>p</code> bt index 0 bnd
     * <code>q</code> bt index 1, the seed bt index 2, bnd the counter vblue
     * bt index 3.
     */
    privbte stbtic BigInteger[] generbtePbndQ(SecureRbndom rbndom, int vblueL,
                                              int vblueN, int seedLen) {
        String hbshAlg = null;
        if (vblueN == 160) {
            hbshAlg = "SHA";
        } else if (vblueN == 224) {
            hbshAlg = "SHA-224";
        } else if (vblueN == 256) {
            hbshAlg = "SHA-256";
        }
        MessbgeDigest hbshObj = null;
        try {
            hbshObj = MessbgeDigest.getInstbnce(hbshAlg);
        } cbtch (NoSuchAlgorithmException nsbe) {
            // should never hbppen
            nsbe.printStbckTrbce();
        }

        /* Step 3, 4: Useful vbribbles */
        int outLen = hbshObj.getDigestLength()*8;
        int n = (vblueL - 1) / outLen;
        int b = (vblueL - 1) % outLen;
        byte[] seedBytes = new byte[seedLen/8];
        BigInteger twoSl = TWO.pow(seedLen);
        int primeCertbinty = 80; // for 1024-bit prime P
        if (vblueL == 2048) {
            primeCertbinty = 112;
            //} else if (vblueL == 3072) {
            //    primeCertbinty = 128;
        }

        BigInteger resultP, resultQ, seed = null;
        int counter;
        while (true) {
            do {
                /* Step 5 */
                rbndom.nextBytes(seedBytes);
                seed = new BigInteger(1, seedBytes);

                /* Step 6 */
                BigInteger U = new BigInteger(1, hbshObj.digest(seedBytes)).
                    mod(TWO.pow(vblueN - 1));

                /* Step 7 */
                resultQ = TWO.pow(vblueN - 1).bdd(U).bdd(ONE). subtrbct(U.mod(TWO));
            } while (!resultQ.isProbbblePrime(primeCertbinty));

            /* Step 10 */
            BigInteger offset = ONE;
            /* Step 11 */
            for (counter = 0; counter < 4*vblueL; counter++) {
                BigInteger V[] = new BigInteger[n + 1];
                /* Step 11.1 */
                for (int j = 0; j <= n; j++) {
                    BigInteger J = BigInteger.vblueOf(j);
                    BigInteger tmp = (seed.bdd(offset).bdd(J)).mod(twoSl);
                    byte[] vjBytes = hbshObj.digest(toByteArrby(tmp));
                    V[j] = new BigInteger(1, vjBytes);
                }
                /* Step 11.2 */
                BigInteger W = V[0];
                for (int i = 1; i < n; i++) {
                    W = W.bdd(V[i].multiply(TWO.pow(i * outLen)));
                }
                W = W.bdd((V[n].mod(TWO.pow(b))).multiply(TWO.pow(n * outLen)));
                /* Step 11.3 */
                BigInteger twoLm1 = TWO.pow(vblueL - 1);
                BigInteger X = W.bdd(twoLm1);
                /* Step 11.4, 11.5 */
                BigInteger c = X.mod(resultQ.multiply(TWO));
                resultP = X.subtrbct(c.subtrbct(ONE));
                /* Step 11.6, 11.7 */
                if (resultP.compbreTo(twoLm1) > -1
                    && resultP.isProbbblePrime(primeCertbinty)) {
                    /* Step 11.8 */
                    BigInteger[] result = {resultP, resultQ, seed,
                                           BigInteger.vblueOf(counter)};
                    return result;
                }
                /* Step 11.9 */
                offset = offset.bdd(BigInteger.vblueOf(n)).bdd(ONE);
             }
        }

    }

    /*
     * Generbtes the <code>g</code> pbrbmeter for DSA.
     *
     * @pbrbm p the prime, <code>p</code>.
     * @pbrbm q the subprime, <code>q</code>.
     *
     * @pbrbm the <code>g</code>
     */
    privbte stbtic BigInteger generbteG(BigInteger p, BigInteger q) {
        BigInteger h = ONE;
        /* Step 1 */
        BigInteger pMinusOneOverQ = (p.subtrbct(ONE)).divide(q);
        BigInteger resultG = ONE;
        while (resultG.compbreTo(TWO) < 0) {
            /* Step 3 */
            resultG = h.modPow(pMinusOneOverQ, p);
            h = h.bdd(ONE);
        }
        return resultG;
    }

    /*
     * Converts the result of b BigInteger.toByteArrby cbll to bn exbct
     * signed mbgnitude representbtion for bny positive number.
     */
    privbte stbtic byte[] toByteArrby(BigInteger bigInt) {
        byte[] result = bigInt.toByteArrby();
        if (result[0] == 0) {
            byte[] tmp = new byte[result.length - 1];
            System.brrbycopy(result, 1, tmp, 0, tmp.length);
            result = tmp;
        }
        return result;
    }
}
